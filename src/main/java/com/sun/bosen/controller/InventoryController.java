package com.sun.bosen.controller;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.bosen.pojo.Inventory;
import com.sun.bosen.service.InventoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	
	@ResponseBody
	@RequestMapping("/listInventory")
	public List<Inventory> listInventory(String cInvCCode, Integer limit) {
		List<Inventory> list = inventoryService.list(cInvCCode, limit);
		return list;
	}


	@ResponseBody
	@RequestMapping("/selectInventory")
	public Inventory selectInventory(String cInvCode) {
		Inventory list = inventoryService.getInventory(cInvCode);
		return list;
	}
}
