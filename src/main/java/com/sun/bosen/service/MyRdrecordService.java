package com.sun.bosen.service;

import java.util.Map;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecord;

public interface MyRdrecordService {

	int getRdrecordId(Map<String, Object> param);

	int[] updateRdrecord(Rdrecord myRdrecord, int i);

}
