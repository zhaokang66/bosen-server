package com.sun.bosen.service;

import java.util.List;
import java.util.Map;

import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.pojo.Rdrecords;

public interface PP_PomainService {

	List<PP_Pomain> list(Integer ID, boolean bFinished, boolean detailsFinshed, String cDepCode, String productionCode);

	void updatefInQuantity(PP_Pomain pp_Pomain);

}
