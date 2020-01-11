package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.PP_Podetails;

public interface PP_PodetailsService {

	List<PP_Podetails> list(Integer mainId);

	void updatefOutQuantity(PP_Podetails pp_Podetails);

}
