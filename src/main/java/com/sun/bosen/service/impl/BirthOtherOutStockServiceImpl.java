package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.bosen.mapper.MyRdrecordMapper;
import com.sun.bosen.mapper.RdrecordMapper;
import com.sun.bosen.mapper.RdrecordsMapper;
import com.sun.bosen.pojo.Other;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.BirthOtherOutStockService;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.RdrecordsService;

@Service
public class BirthOtherOutStockServiceImpl implements BirthOtherOutStockService{

	@Autowired
	CurrentStockService CurrentService;
	@Autowired
	RdrecordMapper rdrecordMapper;
	@Autowired
	MyRdrecordMapper myRdrecordMapper;
	@Autowired
	RdrecordService rdrecordService;
	@Autowired
	MyRdrecordService myRdrecordService;
	@Autowired
	RdrecordsService rdrecordsService;
	@Autowired
	MyRdrecordsService myRdrecordsService;
	@Autowired
	RdrecordsMapper rdrecordsMapper;
	@Autowired
	CurrentStockService currentStockService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackForClassName = "Exception")
	public String add(Other[] data) {
		Rdrecord rdrecord = setRdrecordValue(data, "Rdrecord");
		rdrecordService.updateRdrecord(rdrecord, 0);

		Rdrecord myRdrecord = setRdrecordValue(data, "My_Rdrecord");
		myRdrecordService.updateRdrecord(myRdrecord, 0);
						
		for (int i = 0; i < data.length; i++) {
			currentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getcInvCode(), -data[i].getfQuantity());
			
			Rdrecords rdrecords = setRdrecordsValue(data[i], "Rdrecords");
			rdrecordsService.updateRdrecords(rdrecords);
			
			Rdrecords myRdrecords = setRdrecordsValue(data[i], "My_Rdrecords");
			myRdrecordsService.updateRdrecords(myRdrecords);
			
			rdrecordService.updateUfs();
		}
		return "出库成功！";
	}
	

	private Rdrecord setRdrecordValue(Other[] data, String object) {
		Rdrecord rdrecord = new Rdrecord();
		BeanUtils.copyProperties(data[0], rdrecord);
		rdrecord.setcVouchType("09");
		rdrecord.setcBusType("其他出库");
		rdrecord.setcSource("库存");
		rdrecord.setvT_ID(85);
		rdrecord.setcDefine16("0");
		Rdrecord infoID = new Rdrecord();
		if (object.equals("Rdrecord")) {
			infoID = rdrecordMapper.getLastInfo(null);
			if (infoID == null) {
				rdrecord.setiD(0);
				rdrecord.setcCode(String.format("%010d", 1));
			} else {
				rdrecord.setiD(infoID.getiD() + 1);
				Rdrecord info = rdrecordMapper.getLastInfo(rdrecord.getcBusType());
				if (info == null) {
					System.out.println("当前查询结果为空！");
					rdrecord.setcCode(String.format("%010d", 1));
				} else {
					rdrecord.setcCode(String.format("%010d", Integer.parseInt(info.getcCode()) + 1));
				}
			}
		} else if (object.equals("My_Rdrecord")) {
			infoID = myRdrecordMapper.getLastInfo(null);
			if (infoID == null) {
				rdrecord.setiD(0);
				rdrecord.setcCode(String.format("%010d", 1));
			} else {
				rdrecord.setiD(infoID.getiD() + 1);
				Rdrecord info = myRdrecordMapper.getLastInfo(rdrecord.getcBusType());
				if (info == null) {
					System.out.println("当前查询结果为空！");
					rdrecord.setcCode(String.format("%010d", 1));
				} else {
					rdrecord.setcCode(String.format("%010d", Integer.parseInt(info.getcCode()) + 1));
				}
			}
		}
		rdrecord.setStartID(rdrecord.getiD());
		System.out.println(JSONObject.toJSONString(rdrecord, SerializerFeature.WriteMapNullValue));
		return rdrecord;
	}
	
	private Rdrecords setRdrecordsValue(Other data, String object) {
		Rdrecords rdrecords = new Rdrecords();
		BeanUtils.copyProperties(data, rdrecords);
		rdrecords.setiQuantity(data.getfQuantity());
		rdrecords.setcBVencode(data.getcVenCode());
		Map<String, Object> param = new HashMap<String, Object>();
		if (object.equals("Rdrecords")) {
			rdrecords.setiD(rdrecordMapper.getRdrecordId(param));
			rdrecords.setAutoId(rdrecordsMapper.getLastInfoId() + 1);
		} else if (object.equals("My_Rdrecords")) {
			rdrecords.setiD(myRdrecordMapper.getRdrecordId(param));
		}
		System.out.println(JSONObject.toJSONString(rdrecords, SerializerFeature.WriteMapNullValue));
		return rdrecords;	
	}

}
