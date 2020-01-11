package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecords;



public interface PO_PodetailsService {
	List<PO_Podetails> list(int pOID, boolean bFinished);
	
	void updateiReceivedQTY(PO_Podetails data);

	Rdrecords getPo_podetails(int id);

}
