package com.sun.bosen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.RdrecordMapper;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.RdrecordService;

@Service
public class RdrecordServiceImpl implements RdrecordService{
	@Autowired
	RdrecordMapper rdrecordMapper;
	
	@Override
	public void add(Rdrecord data) {
		rdrecordMapper.add(data);
	}

	@Override
	public Rdrecord getLastInfo(String busType) {
		return rdrecordMapper.getLastInfo( busType);
	}

	@Override
	public void test(Rdrecords rdrecordsList) {
		rdrecordMapper.test(rdrecordsList);
	}

	@Override
	public void updateUfs() {
		rdrecordMapper.updateUfs();
		
	}


	@Override
	public Rdrecord isExists(Rdrecord data) {
		
		return rdrecordMapper.isExists(data);
	}

	@Override
	public void updatecOrderCode(int id) {
		
		rdrecordMapper.updatecOrderCode(id);
	}

	@Override
	public int getRdrecordId(int pOID) {
		
		return rdrecordMapper.getRdrecordId(pOID);
	}
}
