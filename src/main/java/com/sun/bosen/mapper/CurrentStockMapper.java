package com.sun.bosen.mapper;


import com.sun.bosen.pojo.PO_Podetails;

public interface CurrentStockMapper {
	void updateiReceivedQTY(PO_Podetails data);

	int ifExists(PO_Podetails po_Podetails);

	void updateCurrentStock(PO_Podetails po_Podetails);

	void addCurrentStock(PO_Podetails po_Podetails);

}
