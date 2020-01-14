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
import com.sun.bosen.pojo.Other;
import com.sun.bosen.service.BirthOtherOutStockService;
import com.sun.bosen.service.BirthOtherWarehousingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")
public class OtherController {

	@Autowired
	BirthOtherWarehousingService birthOtherWarehousingService;
	@Autowired
	BirthOtherOutStockService birthOtherOutStockService;
	
	@ResponseBody
	@RequestMapping("/submitotherInRrecord")
	public String submitotherInRrecord(@RequestBody Other[] data) {
		System.out.println(JSONObject.toJSONString(data));
		return birthOtherWarehousingService.add(data);
	}
	
	@ResponseBody
	@RequestMapping("/submitotherOutRrecord")
	public String submitotherOutRrecord(@RequestBody Other[] data) {
		System.out.println(JSONObject.toJSONString(data));
		return birthOtherOutStockService.add(data);
	}
}
