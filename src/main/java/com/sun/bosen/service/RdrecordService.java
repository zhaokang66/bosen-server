package com.sun.bosen.service;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
public interface RdrecordService {
	void add(Rdrecord data);

	Rdrecord getLastInfo(String busType);


	void test(Rdrecords rdrecordsList);

	void updateUfs();

	String isExists(PO_Podetails data);
}
