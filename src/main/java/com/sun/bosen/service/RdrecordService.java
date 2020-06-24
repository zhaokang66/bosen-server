package com.sun.bosen.service;

import java.util.List;
import java.util.Map;

import com.sun.bosen.pojo.OutboundList;
import com.sun.bosen.pojo.Rdrecord;
public interface RdrecordService {
	void updateUfs();
	int getRdrecordId(Map<String, Object> param);
	int[] updateRdrecord(Rdrecord rdrecord,int i);
	int getLastFile();
	List<OutboundList> rdrecordList(String cBusType);
	String deleteList(int id);
}
