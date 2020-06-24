package com.sun.bosen.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
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
import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.BitrhMaterialOutStockService;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.MyCurrentStockService;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
import com.sun.bosen.service.PP_PodetailsService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.RdrecordsService;
import com.sun.bosen.util.ImageUtil;

@Service
public class BitrhMaterialOutStockServiceImpl implements BitrhMaterialOutStockService {

	@Autowired
	PP_ProductPOMapper pp_ProductPOMapper;
	@Autowired
	RdrecordMapper rdrecordMapper;
	@Autowired
	RdrecordService rdrecordService;
	@Autowired
	MyRdrecordService myRdrecordService;
	@Autowired
	PP_PomainMapper pp_PomainMapper;
	@Autowired
	RdrecordsMapper rdrecordsMapper;
	@Autowired
	RdrecordsService rdrecordsService;
	@Autowired
	PP_PodetailsService pp_PodetailsService;
	@Autowired
	CurrentStockService currentStockService;
	@Autowired
	MyCurrentStockService myCurrentStockService;
	@Autowired
	MyRdrecordMapper myRdrecordMapper;
	@Autowired
	MyRdrecordsService myRdrecordsService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackForClassName = "Exception")
	public String add(HttpSession session,PP_Podetails[] data) throws IOException {
		
		//生成签名
//		public void add(HttpSession session, String base64Str, String ) throws IOException {
//		String destPath = session.getServletContext().getRealPath("img/signature/OutboundSignature");
//		System.out.println("destPath:" + destPath);
//		System.out.println("base64Str:" + base64Str);

//
//	}
		String base64Str = data[0].getBase64Str();
		String destPath = session.getServletContext().getRealPath("img/signature/OutboundSignature");
		int lastFile = rdrecordService.getLastFile() + 1;
		ImageUtil.base64ToFile(destPath, base64Str, lastFile+".svg");
		
		Rdrecord rdrecord = setRdrecordValue(data, "Rdrecord", lastFile);
		rdrecordService.updateRdrecord(rdrecord, 0);

		Rdrecord myRdrecord = setRdrecordValue(data, "My_Rdrecord", lastFile);
		myRdrecordService.updateRdrecord(myRdrecord, 0);

		for (int i = 0; i < data.length; i++) {
			pp_PodetailsService.updatefOutQuantity(data[i]);

			currentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					-data[i].getNowfOutQuantity());
			myCurrentStockService.updateCurrentStock(data[i].getcWhCode(), data[i].getInventory().getcInvCode(),
					-data[i].getNowfOutQuantity());

			Rdrecords rdrecords = setRdrecordsValue(data[i], "Rdrecords");
			rdrecordsService.updateRdrecords(rdrecords);
			Rdrecords myRdrecords = setRdrecordsValue(data[i], "My_Rdrecords");
			myRdrecordsService.updateRdrecords(myRdrecords);
			
			rdrecordService.updateUfs();
		}
		return "出库成功";
	}

	private Rdrecord setRdrecordValue(PP_Podetails[] data, String object,int lastFile) {
		Rdrecord rdrecord = new Rdrecord();
		String cCode = data[0].getcCode();

		
		int isExists = 0;
		int minMainId = data[0].getMainId();
		for (int i = 1; i < data.length; i++) {
			if (!data[i].getcCode().equals(cCode)) {
				isExists = 1;
			}
			if (data[i].getMainId() < minMainId) {
				minMainId = data[i].getMainId();
			}
		}
		System.out.println("2这是我的自定义8="+rdrecord.getcDefine8());
		
		if (isExists == 0) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ppOutMainId", minMainId);
			rdrecord = pp_ProductPOMapper.getPp_Product(param);
		}
		
		rdrecord.setcBusType("领料");
		rdrecord.setcSource("生产订单");
		rdrecord.setcWhCode(data[0].getcWhCode());
		rdrecord.setcRdCode(data[0].getcRdCode());
		rdrecord.setbRdFlag(0);
		rdrecord.setcVouchType("11");
		rdrecord.setvT_ID(65);
		rdrecord.setcDefine16("0");
		rdrecord.setcDepCode(data[0].getcDepCode());
		rdrecord.setcMaker(data[0].getcMaker());
		rdrecord.setcMemo(data[0].getcMemo());
		rdrecord.setcDefine8(String.valueOf(lastFile));

		
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
		System.out.println("5这是我的自定义8="+rdrecord.getcDefine8());
		System.out.println(JSONObject.toJSONString(rdrecord, SerializerFeature.WriteMapNullValue));
		return rdrecord;
	}

	private Rdrecords setRdrecordsValue(PP_Podetails data, String object) {
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("subId", data.getSubId());
		Rdrecords rdrecords = pp_PomainMapper.getPp_pomain(param1);
		rdrecords.setcInvCode(data.getInventory().getcInvCode());
		rdrecords.setiQuantity(data.getNowfOutQuantity());
		BeanUtils.copyProperties(data, rdrecords);
		rdrecords.setiNQuantity(data.getfQuantity());
		rdrecords.setiMPoIds(data.getSubId());
		int lastAutoID = (rdrecordsMapper.getLastInfoId() == null) ? 0 : rdrecordsMapper.getLastInfoId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID", pp_PomainMapper.getId(data.getMainId()));
		if (object.equals("Rdrecords")) {
			rdrecords.setiD(rdrecordService.getRdrecordId(param));
			rdrecords.setAutoId(lastAutoID + 1);
		} else if (object.equals("My_Rdrecords")) {
			rdrecords.setiD(myRdrecordService.getRdrecordId(param));
		}

		System.out.println(JSONObject.toJSONString(rdrecords, SerializerFeature.WriteMapNullValue));
		return rdrecords;
	}

}
