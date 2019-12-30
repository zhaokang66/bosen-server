package com.sun.bosen.service;

public interface CurrentStockService {
	void updateCurrentStock(String cWhCode, String cInvCode, float nowiReceivedQTY);
}
