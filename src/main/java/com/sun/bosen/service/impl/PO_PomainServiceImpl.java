package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.PO_PomainMapper;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.PO_Pomain;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.service.PO_PomainService;

@Service
public class PO_PomainServiceImpl implements PO_PomainService {

	@Autowired
	PO_PomainMapper po_PomainMapper;
	
	@Override
	public List<PO_Pomain> list(boolean bFinished,int endId) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("bFinished", bFinished);
		param.put("endId", endId);
		return po_PomainMapper.list(param);
	}

	@Override
	public Rdrecord getPo_Pomain(PO_Podetails data) {
		return po_PomainMapper.getPo_Pomain(data);
	}

}
