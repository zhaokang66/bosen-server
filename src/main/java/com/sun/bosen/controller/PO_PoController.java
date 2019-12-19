package com.sun.bosen.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.PO_Pomain;
import com.sun.bosen.pojo.testJSON;
import com.sun.bosen.service.PO_PodetailsService;
import com.sun.bosen.service.PO_PomainService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.RdrecordsService;
import com.sun.bosen.service.WarehouseService;
import com.sun.bosen.util.ImageUtil;
import com.sun.bosen.util.Result;
import com.sun.bosen.util.UploadedImageFile;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.pojo.Warehouse;

import Decoder.BASE64Decoder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")

public class PO_PoController {

	@Autowired
	PO_PomainService po_PomainService;
	@Autowired
	PO_PodetailsService po_PodetailsService;
	@Autowired
	CurrentStockService currentStockService;
	@Autowired
	RdrecordService rdrecordService;
	@Autowired
	RdrecordsService rdrecordsService;
	@Autowired
	WarehouseService warehouseService;
	
	// ssm返回json需要添加注解@ResponseBody 和jackson-databind maven依赖
	
	@ResponseBody
	@RequestMapping("/listPO_Pomain")
	public List<PO_Pomain> listPO_Pomain(boolean bFinished, int endId, HttpServletResponse req, HttpServletRequest res)
			throws UnsupportedEncodingException {

		List<PO_Pomain> list = po_PomainService.list(bFinished, endId);
		System.out.println(JSONObject.toJSONString(list));
		return list;
	}

	@ResponseBody
	@RequestMapping("/listPO_Podetails")
	public List<PO_Podetails> listPO_Podetails(Integer  pOID) {
		List<PO_Podetails> list = po_PodetailsService.list(pOID);
		return list;
	}
	


	@ResponseBody
	@RequestMapping("/addSignature")
	public void add(HttpSession session,  String base64Str) throws IOException {
		
		String destPath = session.getServletContext().getRealPath("img/signature/outStock");
		System.out.println("destPath:"+destPath);
		System.out.println("base64Str:"+base64Str);
		ImageUtil.base64ToFile(destPath, base64Str, "success.svg");

	}

	@ResponseBody
	@RequestMapping("/submitPO_Podetails")
	public String submitPO_Podetails(
			@RequestBody PO_Podetails[] data) {
		int flag = 1;
		int startID = 0;
		for (int i = 0; i < data.length; i++) {
			po_PodetailsService.updateiReceivedQTY(data[i]);//更新入库数量
			
			if (currentStockService.ifExists(data[i]) > 0) {//生出的入库单如存在，则执行更新操作，否则插入新的
				currentStockService.updateCurrentStock(data[i]);
			}else {
				currentStockService.addCurrentStock(data[i]);
			}
			
			Rdrecord rdrecordList = po_PomainService.getPo_Pomain(data[i]);//从采购订单主表拿到数据

			rdrecordList.setiD(rdrecordService.getLastInfo(null).getiD()+1);//id自增
			int cCode = Integer.parseInt(rdrecordService.getLastInfo("普通采购").getcCode()) + 1;//得到普通采购类型的cCode字段最后一个值
			rdrecordList.setcCode(String.format("%010d", cCode));//格式化cCods
			rdrecordList.setcWhCode(data[i].getcWhCode());
			System.out.println(JSONObject.toJSONString(rdrecordList,SerializerFeature.WriteMapNullValue));

			//首先每个订单里可能有多条数据，而一个订单至少会对应一个入库单主表， 有多少条数据就会生成多少个入库单子表
			//1.判断数据库中是否有相同的入库单主表,判断条件（cBusType cWhCode cVenCode cDepCode cPersonCode cPtCode）	
				//判断方法返回 id和订单编号 如果id不为空 就证明有入库单存在，则不插入然后判断订单编号和待插入入库编号是否相等，如不相等，则设为空
			
			if (flag == 1) {
				startID = rdrecordList.getiD();
			}
			
			rdrecordList.setstartID(startID);
			Rdrecord isExistsRdrecord = rdrecordService.isExists(rdrecordList);
			Rdrecords rdrecordsList = po_PodetailsService.getPo_podetails(data[i].getId());
			if (flag == 1 || isExistsRdrecord == null) {
				flag = 0;
				//插入当前时间作为入库时间
				Date date = new Date();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000");
				dateFormat.format(date);
				rdrecordList.setdDate(dateFormat.format(date));
				
				rdrecordService.add(rdrecordList);//向rdrecord插入数据
				rdrecordsList.setiD(rdrecordService.getRdrecordId(data[i].getpOID()));
				
			}else {
				if (!isExistsRdrecord.getcOrderCode().equals(rdrecordList.getcOrderCode())) {
					//这里更新cOderCode为‘ ’
					rdrecordService.updatecOrderCode(isExistsRdrecord.getiD());
				}
				rdrecordsList.setiD(isExistsRdrecord.getiD());
			}
			rdrecordsList.setautoId(rdrecordsService.getLastInfoId()+1);
			rdrecordsList.setiQuantity(data[i].getnowiReceivedQTY());
			rdrecordsList.setiNQuantity(data[i].getnowiReceivedQTY());
			 
			System.out.println(JSONObject.toJSONString(rdrecordsList,SerializerFeature.WriteMapNullValue));
			rdrecordsService.addRerdcords(rdrecordsList);
			  
			 rdrecordService.updateUfs(); 
		}
		return "提交成功！";
	}
	@ResponseBody
	@RequestMapping("/getcWhCodeList")
	public List<Warehouse> getcWhCodeList() {
		List<Warehouse> list = warehouseService.list();
		System.out.println(JSONObject.toJSONString(list));
		return list;
	}
	
	public void test() {
		List<PO_Pomain> list = po_PomainService.list(false, -1);
		System.out.println(JSONObject.toJSONString(list));
		
	}

	public boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
		if (imgData == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		try {
			out = new FileOutputStream(imgFilePath);
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgData);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			out.write(b);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
			return true;
		}
	}

}
