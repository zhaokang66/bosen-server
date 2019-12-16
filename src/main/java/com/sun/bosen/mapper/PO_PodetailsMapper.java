package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecords;


public interface PO_PodetailsMapper {
	List<PO_Podetails> list(Map<String,Object> param);
	void updateiReceivedQTY (PO_Podetails data);
	Rdrecords getPo_podetails(@Param(value="id")int id);
}
