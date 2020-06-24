package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.PP_ProductPOMapper;
import com.sun.bosen.pojo.PP_ProductPO;
import com.sun.bosen.service.PP_ProductPOService;

@Service
public class PP_ProductPOServiceImpl implements PP_ProductPOService{

	@Autowired
	PP_ProductPOMapper pp_ProductPOMapper;
	
	@Override
	public List<PP_ProductPO> list(boolean bFinished, int endId, boolean detailsFinshed, String cDepName, String productionCode) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("bFinished", bFinished);
		param.put("endId", endId);
		param.put("detailsFinshed", detailsFinshed);
		param.put("cDepName", cDepName);
		param.put("productionCode", productionCode);
		return pp_ProductPOMapper.list(param);
	}

}
