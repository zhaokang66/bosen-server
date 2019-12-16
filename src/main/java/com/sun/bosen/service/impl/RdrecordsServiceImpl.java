package com.sun.bosen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.RdrecordsMapper;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.RdrecordsService;
@Service
public class RdrecordsServiceImpl implements RdrecordsService{
	@Autowired
	RdrecordsMapper rdrecordsMapper;
	
	@Override
	public int getLastInfoId() {
		return rdrecordsMapper.getLastInfoId();
	}

	@Override
	public void addRerdcords(Rdrecords info) {
		
		rdrecordsMapper.addRerdcords(info);
	}
}
