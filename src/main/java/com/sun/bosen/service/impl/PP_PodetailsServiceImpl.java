package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.PP_PodetailsMapper;
import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.service.PP_PodetailsService;

@Service
public class PP_PodetailsServiceImpl implements PP_PodetailsService {

	@Autowired
	PP_PodetailsMapper pp_PodetailsMapper;
	@Override
	public List<PP_Podetails> list(Integer mainId) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("mainId", mainId);
		return pp_PodetailsMapper.list(param);
	}
	@Override
	public void updatefOutQuantity(PP_Podetails pp_Podetails) {
		pp_PodetailsMapper.updatefOutQuantity(pp_Podetails);
	}

}
