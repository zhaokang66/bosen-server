package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.bosen.mapper.MyRdrecordMapper;
import com.sun.bosen.mapper.PO_PomainMapper;
import com.sun.bosen.mapper.RdrecordMapper;
import com.sun.bosen.mapper.RdrecordsMapper;
import com.sun.bosen.pojo.PO_Podetails;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.BirthPurchaseWarehousingService;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.MyCurrentStockService;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
import com.sun.bosen.service.PO_PodetailsService;
import com.sun.bosen.service.PO_PomainService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.RdrecordsService;

@Service
public class BirthPurchaseWarehousingServiceImpl implements BirthPurchaseWarehousingService {

	@Autowired
	PO_PodetailsService po_PodetailsService;
	@Autowired
	CurrentStockService currentStockService;
	@Autowired
	RdrecordsService rdrecordsService;
	@Autowired
	MyCurrentStockService myCurrentStockService;
	@Autowired
	MyRdrecordService myRdrecordService;
	@Autowired
	MyRdrecordsService myRdrecordsService;
	@Autowired
	RdrecordService rdrecordService;
	@Autowired
	PO_PomainService po_PomainService;
	@Autowired
	RdrecordMapper rdrecordMapper;
	@Autowired
	RdrecordsMapper rdrecordsMapper;
	@Autowired
	MyRdrecordMapper myRdrecordMapper;
	@Autowired
	PO_PomainMapper po_PomainMapper;

	private int rdrecordStartID;
	private int myRdrecordStartID;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackForClassName = "Exception")
	public String add(PO_Podetails[] data) {

		int[] flag1 = { 0, 0 };
		int[] flag2 = { 0, 0 };

		for (int i = 0; i < data.length; i++) {
			po_PodetailsService.updateiReceivedQTY(data[i]);// 更新T6入库数量

			currentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					data[i].getNowiReceivedQTY());
			myCurrentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					data[i].getNowiReceivedQTY());

			Rdrecord rdrecord = setRdrecordValue(data[i], i, "Rdrecord");
			flag1 = rdrecordService.updateRdrecord(rdrecord, i);

			Rdrecord myRdrecord = setRdrecordValue(data[i], i, "MyRdrecord");
			flag2 = myRdrecordService.updateRdrecord(myRdrecord, i);

			Rdrecords rdrecords = setRdrecordsValues(data[i], flag1, "Rdrecords");
			rdrecordsService.updateRdrecords(rdrecords);

			Rdrecords myRdrecords = setRdrecordsValues(data[i], flag2, "MyRdrecords");
			myRdrecordsService.updateRdrecords(myRdrecords);

			rdrecordService.updateUfs();
		}
		return "提交成功！";
	}

	private Rdrecord setRdrecordValue(PO_Podetails data, int i, String object) {
		Rdrecord rdrecord = po_PomainMapper.getPo_Pomain(data.getpOID());
		rdrecord.setcBusType("普通采购");
		rdrecord.setcSource("采购订单");
		rdrecord.setvT_ID(27);
		rdrecord.setcVouchType("01");
		rdrecord.setcRdCode("101");
		rdrecord.setcWhCode(data.getcWhCode());

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
			if (i == 0) {
				rdrecordStartID = rdrecord.getiD();
			}
			rdrecord.setStartID(rdrecordStartID);
		} else if (object.equals("MyRdrecord")) {
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
			if (i == 0) {
				myRdrecordStartID = rdrecord.getiD();
			}
			rdrecord.setStartID(myRdrecordStartID);
		}
		System.out.println(JSONObject.toJSONString(rdrecord, SerializerFeature.WriteMapNullValue));
		return rdrecord;

	}

	private Rdrecords setRdrecordsValues(PO_Podetails data, int[] flag, String object) {
		Rdrecords rdrecords = po_PodetailsService.getPo_podetails(data.getId());
		rdrecords.setiQuantity(data.getNowiReceivedQTY());
		rdrecords.setiNQuantity(data.getNowiReceivedQTY());
		rdrecords.setiPrice(data.getNowiReceivedQTY() * rdrecords.getiUnitCost());
		rdrecords.setiAPrice(data.getNowiReceivedQTY() * rdrecords.getfACost());
		rdrecords.setiSum(data.getNowiReceivedQTY() * rdrecords.getiTaxCost());
		rdrecords.setiTaxPrice((rdrecords.getiTaxCost() - rdrecords.getiUnitCost()) * rdrecords.getiNQuantity());
		if (flag[0] == 1) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pOID", data.getpOID());
			if (object.equals("Rdrecords")) {
				System.out.println("start select rdrecord id");
				rdrecords.setiD(rdrecordService.getRdrecordId(param));
				rdrecords.setAutoId(rdrecordsMapper.getLastInfoId() + 1);
			} else if (object.equals("MyRdrecords")) {
				rdrecords.setiD(myRdrecordService.getRdrecordId(param));
			}
		} else {
			rdrecords.setiD(flag[1]);
			rdrecords.setAutoId(rdrecordsMapper.getLastInfoId() + 1);
		}
		System.out.println(JSONObject.toJSONString(rdrecords, SerializerFeature.WriteMapNullValue));
		System.out.println(rdrecords.getAutoId());
		return rdrecords;
	}

}
