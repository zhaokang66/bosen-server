package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.OutboundList;
import com.sun.bosen.pojo.Rdrecord;

public interface RdrecordMapper {
	void add(Rdrecord data);

	Rdrecord getLastInfo(@Param(value="busType") String busType);
	
	void updateUfs();



	Rdrecord isExists(Rdrecord data);

	void updatecOrderCode(@Param(value="id") int id);
	int getRdrecordId(Map<String, Object> param);
	int test();
	void addTest();
	int getLastFile();

	List<OutboundList> rdrecordList(Map<String,Object> param);

	String isVerify(@Param(value="id") int id);

	void deleteList(@Param(value="id") int id);
	

}
