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
import com.sun.bosen.service.MyCurrentStockService;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
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
	@Autowired
	MyCurrentStockService myCurrentStockService;
	@Autowired
	MyRdrecordService myRdrecordService;
	@Autowired
	MyRdrecordsService myRdrecordsService;
	
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
	public String submitPO_Podetails(@RequestBody PO_Podetails[] data) {
		int[] startID = {0,0};
		int[] flag1 = {0,0,0};
		int[] flag2 = {0,0,0};
		for (int i = 0; i < data.length; i++) {
			System.out.println("---------------------------现在是要更新库存量！！！------------------------");
			po_PodetailsService.updateiReceivedQTY(data[i]);//更新T6入库数量
			
			//更新T6库存
			System.out.println("---------------------------现在是要更新T6入库表！！！------------------------");
			updateCurrent("CurrentStock",data[i]);
			//更新自己表的库存
			System.out.println("---------------------------现在是要更新自己的入库表！！！------------------------");
			updateCurrent("My_CurrentStock",data[i]);
			
			//更新T6Rdrecord
			System.out.println("---------------------------现在是要更新T6库存主表！！！------------------------");
			flag1 = updateRecord("Rdrecord",data[i],i,startID[0]);//0 164
			startID[0] = flag1[0];
			//自己的Rdrecord
			System.out.println("---------------------------现在是要更新自己的库存主表！！！------------------------");
			flag2 = updateRecord("My_Rdrecord",data[i],i,startID[1]);
			startID[1] = flag2[0];
			//更新T6 Rdrecords
			System.out.println("---------------------------现在是要更新T6库存子表！！！------------------------");
			updateRdrecords("Rdrecords",data[i],flag1[1],flag1[2]);
			//更新自己的Rdrecords
			System.out.println("---------------------------现在是要更新自己的库存子表！！！------------------------");
			updateRdrecords("My_Rdrecords",data[i],flag2[1],flag2[2]);

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
	private Rdrecord isExistsRdrecord(Rdrecord data,String object) {
		Rdrecord list = new Rdrecord();
		if (object.equals("Rdrecord")) {
			list = rdrecordService.isExists(data);
		}else if (object.equals("My_Rdrecord")) {
			list = myRdrecordService.isExists(data);
		}
		return list;
	}
	protected void updateCurrent(String object,PO_Podetails data) {	
		if (object.equals("CurrentStock")) {
			if (currentStockService.ifExists(data) > 0) {//T6中生出的入库单如存在，则执行更新操作，否则插入新的
				currentStockService.updateCurrentStock(data);
			}else {
				currentStockService.addCurrentStock(data);
			}
		}else if (object.equals("My_CurrentStock")) {
			if (myCurrentStockService.ifExists(data) >  0) {//本表更新库存
				myCurrentStockService.updateCurrentStock(data);
			}else {
				myCurrentStockService.addCurrentStock(data);
			}
		}
	}
	protected int[] updateRecord(String object,PO_Podetails data,int i,int startID) {
		int[] flag = {0,0,0};
		Rdrecord rdrecordList = po_PomainService.getPo_Pomain(data);//从采购订单主表拿到数据
		if (object.equals("Rdrecord")) {	
			Rdrecord infoID = rdrecordService.getLastInfo(null);
			if (infoID == null) {
				rdrecordList.setiD(0);
			}else {
				rdrecordList.setiD(infoID.getiD()+1);
			}
			Rdrecord info = rdrecordService.getLastInfo("普通采购");
			if (info == null) {
				System.out.println("当前查询结果为空！");
				rdrecordList.setcCode(String.format("%010d",1));
			}else {
				rdrecordList.setcCode(String.format("%010d",Integer.parseInt( info.getcCode()) + 1));
			}
			rdrecordList.setcWhCode(data.getcWhCode());
			if (i == 0) {
				startID = rdrecordList.getiD();
			}
			rdrecordList.setstartID(startID);
			Rdrecord isExistsRdrecord = isExistsRdrecord(rdrecordList,"Rdrecord");
			if (i == 0 || isExistsRdrecord == null) {
				//插入当前时间作为入库时间
				Date date = new Date();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000");
				dateFormat.format(date);
				rdrecordList.setdDate(dateFormat.format(date));
				flag[1] = 1;
				rdrecordService.add(rdrecordList);//向rdrecord插入数据
				
			}else {
				if (!isExistsRdrecord.getcOrderCode().equals(rdrecordList.getcOrderCode())) {
					//这里更新cOderCode为‘ ’
					rdrecordService.updatecOrderCode(isExistsRdrecord.getiD());
				}
				flag[1] = 0;
				flag[2] = isExistsRdrecord.getiD();
			}			
		}else if (object.equals("My_Rdrecord")) {
			Rdrecord myRdrecordList = rdrecordList;
			
			
			Rdrecord info = myRdrecordService.getLastInfo("普通采购");
			if (info == null) {
				System.out.println("当前查询结果为空！");
				myRdrecordList.setcCode(String.format("%010d",1));
			}else {
				myRdrecordList.setcCode(String.format("%010d",Integer.parseInt( info.getcCode()) + 1));
			}
			myRdrecordList.setcWhCode(data.getcWhCode());
			System.out.println(JSONObject.toJSONString(myRdrecordList,SerializerFeature.WriteMapNullValue));
			if (i == 0) {
				Rdrecord infoID = myRdrecordService.getLastInfo(null);
				if (infoID == null) {
					startID = 0;
				}else {
					startID = infoID.getiD()+1;
				}
			}
			myRdrecordList.setstartID(startID);
			Rdrecord isExistsRdrecord = isExistsRdrecord(myRdrecordList,"My_Rdrecord");
			if (i == 0 || isExistsRdrecord == null) {
				//插入当前时间作为入库时间
				Date date = new Date();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000");
				dateFormat.format(date);
				myRdrecordList.setdDate(dateFormat.format(date));
				flag[1] = 1;
				myRdrecordService.add(myRdrecordList);//向rdrecord插入数据
				
			}else {
				if (!isExistsRdrecord.getcOrderCode().equals(myRdrecordList.getcOrderCode())) {
					//这里更新cOderCode为‘ ’
					myRdrecordService.updatecOrderCode(isExistsRdrecord.getiD());
				}
				flag[1] = 0;
				flag[2] = isExistsRdrecord.getiD();
			}
		}
		flag[0] = startID;
		return flag;
	}

	protected void updateRdrecords(String object, PO_Podetails data, int flag, int id) {
		Rdrecords rdrecordsList = po_PodetailsService.getPo_podetails(data.getId());
		if (object.equals("Rdrecords")) {
			if (flag == 1) {
				rdrecordsList.setiD(rdrecordService.getRdrecordId(data.getpOID()));
			} else {
				rdrecordsList.setiD(id);
			}
			rdrecordsList.setautoId(rdrecordsService.getLastInfoId() + 1);
			rdrecordsList.setiQuantity(data.getnowiReceivedQTY());
			rdrecordsList.setiNQuantity(data.getnowiReceivedQTY());

			System.out.println(JSONObject.toJSONString(rdrecordsList, SerializerFeature.WriteMapNullValue));
			rdrecordsService.addRerdcords(rdrecordsList);
		} else if (object.equals("My_Rdrecords")) {
			Rdrecords myRdrecordsList = rdrecordsList;
			if (flag == 1) {
				myRdrecordsList.setiD(myRdrecordService.getRdrecordId(data.getpOID()));
			} else {
				myRdrecordsList.setiD(id);
			}
			myRdrecordsList.setiQuantity(data.getnowiReceivedQTY());
			myRdrecordsList.setiNQuantity(data.getnowiReceivedQTY());

			System.out.println(JSONObject.toJSONString(myRdrecordsList, SerializerFeature.WriteMapNullValue));
			myRdrecordsService.addRerdcords(myRdrecordsList);
		}

	}
}
