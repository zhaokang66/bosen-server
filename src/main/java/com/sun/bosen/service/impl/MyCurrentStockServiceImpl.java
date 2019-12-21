package com.sun.bosen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.service.MyCurrentStockService;
import com.sun.bosen.mapper.MyCurrentStockMapper;

@Service
public class MyCurrentStockServiceImpl implements MyCurrentStockService{

	@Autowired
	MyCurrentStockMapper myCurrentStockMapper;
	
	
	@Override
	public int ifExists(PO_Podetails data) {
		
		return myCurrentStockMapper.ifExists(data);
	}


	@Override
	public void updateCurrentStock(PO_Podetails po_Podetails) {
		myCurrentStockMapper.updateCurrentStock(po_Podetails);
		
	}


	@Override
	public void addCurrentStock(PO_Podetails po_Podetails) {
		myCurrentStockMapper.addCurrentStock(po_Podetails);
		
	}

}
