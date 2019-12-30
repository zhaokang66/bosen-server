package com.sun.bosen.mapper;

import java.util.Map;

import com.sun.bosen.pojo.PO_Podetails;

public interface MyCurrentStockMapper {

	int ifExists(Map<String,Object> param);

	void updateCurrentStock(Map<String,Object> param);

	void addCurrentStock(Map<String,Object> param);

}
