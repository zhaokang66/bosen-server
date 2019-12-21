package com.sun.bosen.mapper;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.Rdrecord;

public interface MyRdrecordMapper {
	Rdrecord getLastInfo(@Param(value="busType") String busType);

	void add(Rdrecord myRdrecordList);

	void updatecOrderCode(int getiD);

	int getRdrecordId(int getpOID);

	Rdrecord isExists(Rdrecord data);
}
