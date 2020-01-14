package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.PO_PodetailsMapper;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.PO_PodetailsService;


@Service
public class PO_PodetailsServiceImpl implements  PO_PodetailsService {

	@Autowired
	PO_PodetailsMapper po_PodetailsMapper;
	
	@Override
	public List<PO_Podetails> list(int pOID, boolean bFinished) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("pOID", pOID);	
		param.put("bFinished", bFinished);	
		return po_PodetailsMapper.list(param);
	}
	@Override
	public void updateiReceivedQTY(PO_Podetails data) {
		po_PodetailsMapper.updateiReceivedQTY(data);
	}
	@Override
	public Rdrecords getPo_podetails(int id) {
		return po_PodetailsMapper.getPo_podetails(id);
	}

}
