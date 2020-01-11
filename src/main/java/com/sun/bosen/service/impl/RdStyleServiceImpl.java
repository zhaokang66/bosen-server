package com.sun.bosen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.RdStyleMapper;
import com.sun.bosen.pojo.RdStyle;
import com.sun.bosen.service.RdStyleService;

@Service
public class RdStyleServiceImpl implements RdStyleService{

	@Autowired
	RdStyleMapper rdStyleMapper;
	@Override
	public List<RdStyle> list() {
		
		return rdStyleMapper.list();
	}

}
