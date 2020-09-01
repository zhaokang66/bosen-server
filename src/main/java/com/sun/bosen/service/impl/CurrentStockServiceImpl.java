package com.sun.bosen.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.CurrentStockMapper;
import com.sun.bosen.service.CurrentStockService;

@Service
public class CurrentStockServiceImpl implements CurrentStockService{
	@Autowired
	CurrentStockMapper currentStackMapper;

	@Override
	public void updateCurrentStock(String cWhCode, String cInvCode, float nowiReceivedQTY) {
		
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("cWhCode", cWhCode);
		param.put("cInvCode", cInvCode);
		param.put("nowiReceivedQTY", nowiReceivedQTY);
		if (currentStackMapper.ifExists(param) > 0) {//T6中生出的入库单如存在，则执行更新操作，否则插入新的
			currentStackMapper.updateCurrentStock(param);
		}else {
			currentStackMapper.addCurrentStock(param);
		}		
	}

	@Override
	public BigDecimal getInStock(String cWhCode, String cInvCode) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("cWhCode", cWhCode);
		param.put("cInvCode", cInvCode);
		// TODO Auto-generated method stub
		return currentStackMapper.getInStock(param);
	}  

}
