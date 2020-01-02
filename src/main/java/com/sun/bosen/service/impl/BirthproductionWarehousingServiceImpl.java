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
import com.sun.bosen.mapper.PP_ProductPOMapper;
import com.sun.bosen.mapper.RdrecordMapper;
import com.sun.bosen.mapper.RdrecordsMapper;
import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.BirthproductionWarehousingService;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.MyCurrentStockService;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
import com.sun.bosen.service.PP_PomainService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.RdrecordsService;

@Service
public class BirthproductionWarehousingServiceImpl implements BirthproductionWarehousingService {
	private int rdrecordStartID;
	private int myRdrecordStartID;
	@Autowired
	PP_PomainService pp_PomainService;
	@Autowired
	MyCurrentStockService myCurrentStockService;
	@Autowired
	CurrentStockService currentStockService;
	@Autowired
	PP_ProductPOMapper pp_ProductPOMapper;
	@Autowired
	RdrecordMapper rdrecordMapper;
	@Autowired
	RdrecordService rdrecordService;
	@Autowired
	MyRdrecordService myRdrecordService;
	@Autowired
	RdrecordsMapper rdrecordsMapper;
	@Autowired
	RdrecordsService rdrecordsService;
	@Autowired
	MyRdrecordsService myRdrecordsService;
	@Autowired
	MyRdrecordMapper myRdrecordMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackForClassName = "Exception")
	public String add(PP_Pomain[] data) {
		int[] flag1 = { 0, 0 };
		int[] flag2 = { 0, 0 };
		for (int i = 0; i < data.length; i++) {
			// 更新入库数量
			pp_PomainService.updatefInQuantity(data[i]);

			// 更新currentStock
			currentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					data[i].getNowiReceivedQTY());
			myCurrentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					data[i].getNowiReceivedQTY());

			// 更新Rdrecord
			Rdrecord rdrecord = setRdrecordValue(data[i], i, "Rdrecord");
			flag1 = rdrecordService.updateRdrecord(rdrecord, i);

			Rdrecord myRdrecord = setRdrecordValue(data[i], i, "MyRdrecord");
			flag2 = myRdrecordService.updateRdrecord(myRdrecord, i);

			// 更新Rdrecords
			Rdrecords rdrecords = setRdrecordsValue(data[i], flag1, "Rdrecords");
			rdrecordsService.updateRdrecords(rdrecords);

			Rdrecords myRdrecords = setRdrecordsValue(data[i], flag2, "MyRdrecords");
			myRdrecordsService.updateRdrecords(myRdrecords);

			rdrecordService.updateUfs();
		}
		return "添加成功";
	}

	private Rdrecord setRdrecordValue(PP_Pomain data, int i, String object) {
		Rdrecord rdrecord = pp_ProductPOMapper.getPp_Product(data.getId());
		rdrecord.setcBusType("成品入库");
		rdrecord.setcSource("生产订单");
		rdrecord.setcVouchType("10");
		rdrecord.setcRdCode("102");
		rdrecord.setvT_ID(63);
		rdrecord.setcPsPcode(data.getInventory().getcInvCode());
		rdrecord.setcWhCode(data.getcWhCode());
		rdrecord.setiMQuantity("0");

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
			rdrecord.setstartID(rdrecordStartID);
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
			rdrecord.setstartID(myRdrecordStartID);
		}
		System.out.println(JSONObject.toJSONString(rdrecord, SerializerFeature.WriteMapNullValue));
		return rdrecord;
	}

	private Rdrecords setRdrecordsValue(PP_Pomain data, int[] flag, String object) {
		Rdrecords rdrecords = pp_PomainService.getPp_pomain(data.getMainId());
		rdrecords.setiQuantity(data.getNowiReceivedQTY());
//		rdrecords.setiNQuantity(data.getNowiReceivedQTY());
		rdrecords.setiUnitCost(data.getIprice());
		rdrecords.setiPrice(data.getPrice());
		rdrecords.setiMPoIds(data.getMainId());
		if (!data.getProductionCode().equals(null)) {
			rdrecords.setcDefine22(data.getProductionCode());
		}
		if (flag[0] == 1) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ID", data.getId());
			if (object.equals("Rdrecords")) {
				rdrecords.setiD(rdrecordService.getRdrecordId(param));
				rdrecords.setautoId(rdrecordsMapper.getLastInfoId() + 1);
			} else if (object.equals("MyRdrecords")) {
				rdrecords.setiD(myRdrecordService.getRdrecordId(param));
			}
		} else {
			rdrecords.setiD(flag[1]);
			rdrecords.setautoId(rdrecordsMapper.getLastInfoId() + 1);
		}
		System.out.println(JSONObject.toJSONString(rdrecords, SerializerFeature.WriteMapNullValue));
		return rdrecords;
	}

}
