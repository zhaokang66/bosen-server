package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.PP_ProductPO;
import com.sun.bosen.pojo.Rdrecord;

public interface PP_ProductPOMapper {

	List<PP_ProductPO> list(Map<String, Object> param);

	Rdrecord getPp_Product(@Param(value="id") int id);

}
