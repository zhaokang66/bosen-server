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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.sun.bosen.service.BirthPurchaseWarehousingService;
import com.sun.bosen.util.ImageUtil;
import com.sun.bosen.util.UploadedImageFile;
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
	BirthPurchaseWarehousingService birthWarehousingService;

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
	public List<PO_Podetails> listPO_Podetails(Integer pOID, boolean bFinished) {
		List<PO_Podetails> list = po_PodetailsService.list(pOID, bFinished);
		//System.out.println(JSONObject.toJSONString(list));
		return list;
	}

	@ResponseBody
	@RequestMapping("/addSignature")
	public void add(HttpSession session, String base64Str) throws IOException {

		String destPath = session.getServletContext().getRealPath("img/signature/outStock");
		System.out.println("destPath:" + destPath);
		System.out.println("base64Str:" + base64Str);
		ImageUtil.base64ToFile(destPath, base64Str, "success.svg");

	}

	@ResponseBody
	@RequestMapping("/submitPO_Podetails")
	public String submitPO_Podetails(@RequestBody PO_Podetails[] data) {
		return birthWarehousingService.add(data);	
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
