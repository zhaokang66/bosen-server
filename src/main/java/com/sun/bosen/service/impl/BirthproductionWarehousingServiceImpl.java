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
import com.sun.bosen.mapper.PP_PomainMapper;
import com.sun.bosen.mapper.PP_ProductPOMapper;
import com.sun.bosen.mapper.RdrecordMapper;
import com.sun.bosen.mapper.RdrecordsMapper;
import com.sun.bosen.mapper.VoucherHistoryMapper;
import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.BirthProductionWarehousingService;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.MyCurrentStockService;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
import com.sun.bosen.service.PP_PomainService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.RdrecordsService;

@Service
public class BirthProductionWarehousingServiceImpl implements BirthProductionWarehousingService {

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
	@Autowired
	PP_PomainMapper pp_PomainMapper;
	@Autowired
	VoucherHistoryMapper voucherHistoryMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackForClassName = "Exception")
	public String add(PP_Pomain[] data) {

	
		Rdrecord rdrecord = setRdrecordValue(data, "Rdrecord");
		rdrecordService.updateRdrecord(rdrecord, 0);

		Rdrecord myRdrecord = setRdrecordValue(data, "MyRdrecord");
		myRdrecordService.updateRdrecord(myRdrecord, 0);
		
		for (int i = 0; i < data.length; i++) {
			// 更新入库数量
			pp_PomainService.updatefInQuantity(data[i]);

			// 更新currentStock
			currentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					data[i].getNowiReceivedQTY());
			myCurrentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					data[i].getNowiReceivedQTY());


			Rdrecords rdrecords = setRdrecordsValue(data[i], "Rdrecords");
			rdrecordsService.updateRdrecords(rdrecords);

			Rdrecords myRdrecords = setRdrecordsValue(data[i], "MyRdrecords");
			myRdrecordsService.updateRdrecords(myRdrecords);
			
			voucherHistoryMapper.updateOtherCcode("0411");
			rdrecordService.updateUfs();
		}
		return "添加成功";
	}

	private Rdrecord setRdrecordValue(PP_Pomain[] data, String object) {
			
		Rdrecord rdrecord = new Rdrecord();
		String cCode = data[0].getcCode();
		int isExists = 0;
		int minMainId = data[0].getMainId();
		
		for (int i = 1; i < data.length; i++) {
			if (!data[i].getcCode().equals(cCode)) {//判断数据是否从两个寄两个以上的生产订单拿过来的
				isExists = 1;
			}
			if (data[i].getMainId() < minMainId) {
				minMainId = data[i].getMainId();
			}
		}
		
		
		if (isExists == 0) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ppInMainId", minMainId);
			rdrecord = pp_ProductPOMapper.getPp_Product(param);
		} 
		

		rdrecord.setcMemo(data[0].getcMemo());
		rdrecord.setcBusType("成品入库");
		rdrecord.setcSource("生产订单");
		rdrecord.setcVouchType("10");
		rdrecord.setcRdCode(data[0].getcRdCode());
		rdrecord.setvT_ID(63);
		rdrecord.setiMQuantity(0);
		rdrecord.setcWhCode(data[0].getcWhCode());
		rdrecord.setcDepCode(data[0].getcDepCode());
		rdrecord.setcMaker(data[0].getcMaker());
		rdrecord.setcDefine16("0");
		int lastOtherCcode = voucherHistoryMapper.selectOtherCcode("0411");
		rdrecord.setcCode(String.format("%010d", lastOtherCcode+1));
		Rdrecord infoID = new Rdrecord();
		if (object.equals("Rdrecord")) {
			infoID = rdrecordMapper.getLastInfo(null);
			if (infoID == null) {
				rdrecord.setiD(0);
			} else {
				rdrecord.setiD(infoID.getiD() + 1);
			}
		} else if (object.equals("MyRdrecord")) {
			infoID = myRdrecordMapper.getLastInfo(null);
			if (infoID == null) {
				rdrecord.setiD(0);
			} else {
				rdrecord.setiD(infoID.getiD() + 1);
			}

		}
		rdrecord.setStartID(rdrecord.getiD());
		System.out.println("这个是Rdrecord数据");
		System.out.println(JSONObject.toJSONString(rdrecord, SerializerFeature.WriteMapNullValue));
		return rdrecord;
	}

	private Rdrecords setRdrecordsValue(PP_Pomain data, String object) {
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("mainId", data.getMainId());
		Rdrecords rdrecords = pp_PomainMapper.getPp_pomain(param1);
		rdrecords.setiQuantity(data.getNowiReceivedQTY());
		rdrecords.setiUnitCost(data.getiUnitCost());
		rdrecords.setiPrice(data.getiPrice());
		rdrecords.setiMPoIds(data.getMainId());
		int lastAutoID = (rdrecordsMapper.getLastInfoId() == null) ? 0 : rdrecordsMapper.getLastInfoId();

		rdrecords.setcDefine22(data.getProductionCode());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID", data.getId());
		if (object.equals("Rdrecords")) {
			rdrecords.setiD(rdrecordMapper.getLastInfo("成品入库").getiD());
			rdrecords.setAutoId(lastAutoID + 1);
		} else if (object.equals("MyRdrecords")) {
			rdrecords.setiD(myRdrecordMapper.getLastInfo("成品入库").getiD());
		}
		System.out.println("这个是Rdrecords数据");
		System.out.println(JSONObject.toJSONString(rdrecords, SerializerFeature.WriteMapNullValue));
		return rdrecords;
	}

}
