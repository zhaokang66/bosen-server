package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import com.sun.bosen.pojo.PP_Podetails;

public interface PP_PodetailsMapper {

	List<PP_Podetails> list(Map<String, Object> param);

	void updatefOutQuantity(PP_Podetails pp_Podetails);

}
