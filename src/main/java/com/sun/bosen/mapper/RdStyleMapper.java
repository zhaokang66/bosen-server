package com.sun.bosen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.RdStyle;

public interface RdStyleMapper {

	List<RdStyle> list(@Param(value="bRdFlag") Integer bRdFlag);

}
