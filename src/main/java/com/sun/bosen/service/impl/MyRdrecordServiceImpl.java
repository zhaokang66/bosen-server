package com.sun.bosen.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.bosen.mapper.MyRdrecordMapper;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.PO_PomainService;


@Service
public class MyRdrecordServiceImpl implements MyRdrecordService{

	@Autowired
	MyRdrecordMapper myRdrecordMapper;
	@Autowired
	PO_PomainService po_PomainService;

	@Override
	public int[] updateRdrecord(Rdrecord myRdrecord,int i) {
		int[] flag = {0,0};
		Rdrecord isExistsRdrecord = myRdrecordMapper.isExists(myRdrecord);
		if (i == 0 || isExistsRdrecord == null) {
			// 插入当前时间作为入库时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000");
			dateFormat.format(date);
			myRdrecord.setdDate(dateFormat.format(date));
			flag[0] = 1;
			myRdrecordMapper.add(myRdrecord);
		} else {
//			if (!isExistsRdrecord.getcOrderCode().equals(rdrecordList.getcOrderCode())) {
//				// 这里更新cOderCode为‘ ’
//				rdrecordMapper.updatecOrderCode(isExistsRdrecord.getiD());
//			}
			flag[0] = 0;
			flag[1] = isExistsRdrecord.getiD();
		}
		return flag;
	}

	@Override
	public int getRdrecordId(Map<String, Object> param) {
		return myRdrecordMapper.getRdrecordId(param);
	}

}
