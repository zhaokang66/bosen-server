package com.sun.bosen.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.Rdrecord;

public interface MyRdrecordMapper {
	Rdrecord getLastInfo(@Param(value="busType") String busType);

	void add(Rdrecord myRdrecordList);

	void updatecOrderCode(int getiD);

	int getRdrecordId(Map<String, Object> param);

	Rdrecord isExists(Rdrecord data);
}
