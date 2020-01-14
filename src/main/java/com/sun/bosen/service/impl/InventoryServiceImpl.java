package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.InventoryMapper;
import com.sun.bosen.pojo.Inventory;
import com.sun.bosen.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	InventoryMapper inventoryMapper;
	
	@Override
	public List<Inventory> list(String cInvCCode, Integer limit) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("cInvCCode", cInvCCode);
		param.put("limit", limit);
		return inventoryMapper.list(param);
	}

	@Override
	public Inventory getInventory(String cInvCode) {
		return inventoryMapper.getInventory(cInvCode);
	}

}
