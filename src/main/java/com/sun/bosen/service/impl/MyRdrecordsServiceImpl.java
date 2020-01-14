package com.sun.bosen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.MyRdrecordsMapper;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
import com.sun.bosen.service.PO_PodetailsService;

@Service
public class MyRdrecordsServiceImpl implements MyRdrecordsService{
	@Autowired
	MyRdrecordsMapper myRdrecordsMapper;
	@Autowired
	PO_PodetailsService po_PodetailsService;
	@Autowired
	MyRdrecordService myRdrecordService;

	@Override
	public void updateRdrecords(Rdrecords myRdrecords) {
		myRdrecordsMapper.addRerdcords(myRdrecords);
		
	}
	
}
