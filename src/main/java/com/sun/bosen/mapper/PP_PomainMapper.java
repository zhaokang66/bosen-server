package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.pojo.Rdrecords;

public interface PP_PomainMapper {
	List<PP_Pomain> list(Map<String,Object> param);

	void updatefInQuantity(Map<String, Object> param);

	Rdrecords getPp_pomain(@Param(value="mainId") int mainId);

}
				