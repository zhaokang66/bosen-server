package com.sun.bosen.mapper;

import com.sun.bosen.pojo.PO_Podetails;

public interface MyCurrentStockMapper {

	int ifExists(PO_Podetails data);

	void updateCurrentStock(PO_Podetails po_Podetails);

	void addCurrentStock(PO_Podetails po_Podetails);

}
