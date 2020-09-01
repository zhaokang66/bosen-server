package com.sun.bosen.mapper;


import java.math.BigDecimal;
import java.util.Map;

import com.sun.bosen.pojo.PO_Podetails;

public interface CurrentStockMapper {

	int ifExists(Map<String,Object> param);

	void updateCurrentStock(Map<String,Object> param);

	void addCurrentStock(Map<String,Object> param);

	BigDecimal getInStock(Map<String, Object> param);

}
