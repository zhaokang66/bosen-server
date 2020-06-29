package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.PO_Pomain;
import com.sun.bosen.pojo.Rdrecord;

public interface PO_PomainService{
	List<PO_Pomain> list(boolean bFinished,int endId, String orderNumber, String supplier);


}