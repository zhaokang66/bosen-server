package com.sun.bosen.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sun.bosen.pojo.OutboundList;
import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.pojo.User_Task;
import com.sun.bosen.service.BitrhMaterialOutStockService;
import com.sun.bosen.service.RdrecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")
public class MaterialOutStockController {

	@Autowired
	BitrhMaterialOutStockService bitrhMaterialOutStockService;
	@Autowired
	RdrecordService rdrecordService;
	
	@ResponseBody
	@RequestMapping("/submitpp_Podetails")
	public String submitpp_Podetails(HttpSession session, @RequestBody PP_Podetails[] data) throws IOException {
		
		return bitrhMaterialOutStockService.add(session,data);
	}
	
	@ResponseBody
	@RequestMapping("/rdrecordList")
	public List<OutboundList> rdrecordList(String cBusType, int endId) {
		List<OutboundList> list = rdrecordService.rdrecordList(cBusType ,endId);
		return list;
	}
	@ResponseBody
	@RequestMapping("deleteList")
	public String deleteList(int id) {
		return rdrecordService.deleteList(id);
	}
	
	@ResponseBody
	@RequestMapping("/submitOtherMaterial")
	public String submitOtherMaterial(HttpSession session, @RequestBody Rdrecords[] data) throws IOException {
		
		for(Rdrecords v :data){
			System.out.println(v);
		}
		return bitrhMaterialOutStockService.submitOtherMaterial(session, data);
	}
	
}
