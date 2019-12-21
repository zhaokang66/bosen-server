package com.sun.bosen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.MyRdrecordMapper;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.service.MyRdrecordService;


@Service
public class MyRdrecordServiceImpl implements MyRdrecordService{

	@Autowired
	MyRdrecordMapper myRdrecordMapper;
	@Override
	public Rdrecord getLastInfo(String busType) {
		
		return myRdrecordMapper.getLastInfo(busType);
	}
	@Override
	public void add(Rdrecord myRdrecordList) {
		myRdrecordMapper.add(myRdrecordList);	
	}
	@Override
	public void updatecOrderCode(int getiD) {
		myRdrecordMapper.updatecOrderCode(getiD);
	}
	@Override
	public int getRdrecordId(int getpOID) {
		return myRdrecordMapper.getRdrecordId(getpOID);
	}
	@Override
	public Rdrecord isExists(Rdrecord data) {
		// TODO Auto-generated method stub
		return myRdrecordMapper.isExists(data);
	}

}
