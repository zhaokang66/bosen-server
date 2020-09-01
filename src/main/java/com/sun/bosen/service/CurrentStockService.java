package com.sun.bosen.service;

import java.math.BigDecimal;

public interface CurrentStockService {
	void updateCurrentStock(String cWhCode, String cInvCode, float nowiReceivedQTY);

	BigDecimal getInStock(String cWhCode, String cInvCode);
}
