package com.sun.bosen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.WarehouseMapper;
import com.sun.bosen.pojo.Warehouse;
import com.sun.bosen.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService{
	@Autowired
	WarehouseMapper warehouseMapper;
	
	@Override
	public List<Warehouse> list() {
		
		return warehouseMapper.list();
	}

}
