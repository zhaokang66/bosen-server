package com.sun.bosen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.MyRdrecordsMapper;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.MyRdrecordsService;

@Service
public class MyRdrecordsServiceImpl implements MyRdrecordsService{
	@Autowired
	MyRdrecordsMapper myRdrecordsMapper;

	@Override
	public void addRerdcords(Rdrecords myRdrecordsList) {
		myRdrecordsMapper.addRerdcords(myRdrecordsList);
	}
	
}
