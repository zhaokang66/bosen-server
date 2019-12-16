package com.sun.bosen.mapper;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;

public interface RdrecordMapper {
	void add(Rdrecord data);

	Rdrecord getLastInfo(@Param(value="busType") String busType);

	void test(Rdrecords rdrecordsList);

	void updateUfs();
}
