package com.sun.bosen.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.CurrentStockMapper;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.service.CurrentStockService;

@Service
public class CurrentStockServiceImpl implements CurrentStockService{
	@Autowired
	CurrentStockMapper currentStackMapper;
	
	@Override
	public void updateiReceivedQTY(PO_Podetails data) {
		currentStackMapper.updateiReceivedQTY(data);
	}

	@Override
	public int ifExists(PO_Podetails po_Podetails) {
		return currentStackMapper.ifExists(po_Podetails);
	}

	@Override
	public void updateCurrentStock(PO_Podetails po_Podetails) {
		currentStackMapper.updateCurrentStock(po_Podetails);
		
	}

	@Override
	public void addCurrentStock(PO_Podetails po_Podetails) {
		currentStackMapper.addCurrentStock(po_Podetails);
		
	}

}
