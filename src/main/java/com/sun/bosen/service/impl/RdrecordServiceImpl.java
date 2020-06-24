package com.sun.bosen.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.sun.bosen.mapper.RdrecordMapper;
import com.sun.bosen.pojo.OutboundList;
import com.sun.bosen.pojo.Rdrecord;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.MyCurrentStockService;
import com.sun.bosen.service.MyRdrecordService;
import com.sun.bosen.service.MyRdrecordsService;
import com.sun.bosen.service.PO_PodetailsService;
import com.sun.bosen.service.PO_PomainService;
import com.sun.bosen.service.RdrecordService;
import com.sun.bosen.service.RdrecordsService;
import com.sun.bosen.service.WarehouseService;

@Service
public class RdrecordServiceImpl implements RdrecordService{
	@Autowired
	PO_PomainService po_PomainService;
	@Autowired
	PO_PodetailsService po_PodetailsService;
	@Autowired
	CurrentStockService currentStockService;
	@Autowired
	RdrecordsService rdrecordsService;
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	MyCurrentStockService myCurrentStockService;
	@Autowired
	MyRdrecordService myRdrecordService;
	@Autowired
	MyRdrecordsService myRdrecordsService;
	@Autowired
	RdrecordMapper rdrecordMapper;
	
	
	@Override
	public void updateUfs() {
		rdrecordMapper.updateUfs();
	}
	@Override
	public int getRdrecordId(Map<String, Object> param) {//Rdrecords调用
		return rdrecordMapper.getRdrecordId(param);
	}
	@Override
	public int[] updateRdrecord(Rdrecord rdrecord,int i) {
		int[] flag = {0,0};
		Rdrecord isExistsRdrecord = rdrecordMapper.isExists(rdrecord);
		System.out.println(JSONObject.toJSONString(isExistsRdrecord));
		if (i == 0 || isExistsRdrecord == null) {
			// 插入当前时间作为入库时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000");
			dateFormat.format(date);
			rdrecord.setdDate(dateFormat.format(date));
			rdrecord.setdVeriDate(dateFormat.format(date));
			flag[0] = 1;
			
			rdrecordMapper.add(rdrecord);
		} else {
//			if (!isExistsRdrecord.getcOrderCode().equals(rdrecordList.getcOrderCode())) {
//				// 这里更新cOderCode为‘ ’
//				rdrecordMapper.updatecOrderCode(isExistsRdrecord.getiD());
//			}
			flag[0] = 0;
			flag[1] = isExistsRdrecord.getiD();
		}
		System.out.println(JSONObject.toJSONString(rdrecord));
		return flag;
	}
	@Override
	public int getLastFile() {
		return rdrecordMapper.getLastFile();
	}
	@Override
	public List<OutboundList> rdrecordList(String cBusType) {
		return rdrecordMapper.rdrecordList(cBusType);
	}
	@Override
	public String deleteList(int id) {
		String cHandler = rdrecordMapper.isVerify(id);
		if (cHandler==null || cHandler.isEmpty()) {
			rdrecordMapper.deleteList(id);
			return "删除成功";
			
		}
		return "此单据已被审核，不能删除";
		
	}
}