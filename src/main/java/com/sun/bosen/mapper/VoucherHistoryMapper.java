package com.sun.bosen.mapper;

import org.apache.ibatis.annotations.Param;

public interface VoucherHistoryMapper {

	int selectOtherCcode(@Param(value="args") String args);

	void updateOtherCcode(@Param(value="args") String args);

}
