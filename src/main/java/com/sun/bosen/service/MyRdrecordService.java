package com.sun.bosen.service;

import com.sun.bosen.pojo.Rdrecord;

public interface MyRdrecordService {

	Rdrecord getLastInfo(String busType);

	void add(Rdrecord myRdrecordList);

	void updatecOrderCode(int getiD);

	int getRdrecordId(int getpOID);

	Rdrecord isExists(Rdrecord data);

}
