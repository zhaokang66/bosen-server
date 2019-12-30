package com.sun.bosen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.bosen.mapper.RdrecordsMapper;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.PO_PodetailsService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.RdrecordsService;
@Service
public class RdrecordsServiceImpl implements RdrecordsService{
	@Autowired
	RdrecordsMapper rdrecordsMapper;
	@Autowired
	PO_PodetailsService po_PodetailsService;
	@Autowired
	RdrecordService rdrecordService;


	@Override
	public void updateRdrecords(Rdrecords rdrecords) {
		rdrecordsMapper.addRerdcords(rdrecords);
	}
}
