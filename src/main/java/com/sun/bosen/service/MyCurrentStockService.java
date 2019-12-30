package com.sun.bosen.service;

import com.sun.bosen.pojo.PO_Podetails;

public interface MyCurrentStockService {
	void updateCurrentStock(String cWhCode, String cInvCode,float nowiReceivedQTY);
}
