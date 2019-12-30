package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.PO_Pomain;
import com.sun.bosen.pojo.Rdrecord;

public interface PO_PomainMapper {
	List<PO_Pomain> list(Map<String,Object> param);

	Rdrecord getPo_Pomain(@Param(value="id")int id);
}
