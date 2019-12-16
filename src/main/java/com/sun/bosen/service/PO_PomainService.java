package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.PO_Pomain;

public interface PO_PomainService{
	List<PO_Pomain> list(boolean bFinished,int endId);
	PO_Pomain getPo_Pomain(PO_Podetails data);
}