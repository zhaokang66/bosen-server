package com.sun.bosen.util;

import java.util.Map;

public class Result {

	
	
	private String state;
	private String content;
	private Map<String, Object> mapData;
	
	
	public Map<String, Object> getMapData() {
		return mapData;
	}
	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
