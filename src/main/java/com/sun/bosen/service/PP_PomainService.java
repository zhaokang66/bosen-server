package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.pojo.Rdrecords;

public interface PP_PomainService {

	List<PP_Pomain> list(Integer ID);

	void updatefInQuantity(PP_Pomain pp_Pomain);

	Rdrecords getPp_pomain(int mainId);

}
