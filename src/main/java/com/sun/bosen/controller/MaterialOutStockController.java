package com.sun.bosen.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.service.BitrhMaterialOutStockService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")
public class MaterialOutStockController {

	@Autowired
	BitrhMaterialOutStockService bitrhMaterialOutStockService;
	
	@ResponseBody
	@RequestMapping("/submitpp_Podetails")
	public String submitpp_Podetails(@RequestBody PP_Podetails[] data) {
		System.out.println(JSONObject.toJSONString(data));
		return bitrhMaterialOutStockService.add(data);
	}
}
