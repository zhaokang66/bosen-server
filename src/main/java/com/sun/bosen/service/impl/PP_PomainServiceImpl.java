package com.sun.bosen.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.PP_PomainMapper;
import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.service.PP_PomainService;


@Service
public class PP_PomainServiceImpl implements PP_PomainService{
	
	@Autowired
	PP_PomainMapper pp_PomainMapper;
	
	@Override
	public List<PP_Pomain> list(Integer ID, boolean bFinished, boolean detailsFinshed) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("ID", ID);
		param.put("bFinished", bFinished);
		param.put("detailsFinshed", detailsFinshed);
		return pp_PomainMapper.list(param);
	}

	@Override
	public void updatefInQuantity(PP_Pomain data) {
		Map<String, Object> param= new HashMap<String, Object>();
		if ((data.getfInQuantity() + data.getNowiReceivedQTY()) >= data.getfQuantity()) {
			param.put("ID", data.getId());
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000");
			param.put("closeDate", dateFormat.format(date));
		}
		param.put("mainId", data.getMainId());
		param.put("nowiReceivedQTY", data.getNowiReceivedQTY());	
		pp_PomainMapper.updatefInQuantity(param);
	}

}
