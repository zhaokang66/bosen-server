package com.sun.bosen.service.impl;

import java.util.List;

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
	public List<Inventory> list(String cInvCCode) {
		
		return inventoryMapper.list(cInvCCode);
	}

	@Override
	public Inventory getInventory(String cInvCode) {
		return inventoryMapper.getInventory(cInvCode);
	}

}
