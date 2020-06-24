package com.sun.bosen.controller;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sun.bosen.pojo.Warehouse;
import com.sun.bosen.service.WarehouseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")
public class WareHouseController {
	
	@Autowired
	WarehouseService warehouseService;

	@ResponseBody
	@RequestMapping("/getcWhCodeList")
	public List<Warehouse> getcWhCodeList() {
		List<Warehouse> list = warehouseService.list();
		System.out.println(JSONObject.toJSONString(list));
		return list;
	}
	
}
