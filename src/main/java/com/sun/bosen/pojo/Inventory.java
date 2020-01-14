package com.sun.bosen.pojo;

public class Inventory {
	
	
	private String cInvCode;
	private String cInvName;
	//规格型号
	private String cInvStd;
	private String cInvAddCode;//存货代码
	private ComputationUnit computationUnit;
	private String cVenCode;
	
	public String getcVenCode() {
		return cVenCode;
	}
	public void setcVenCode(String cVenCode) {
		this.cVenCode = cVenCode;
	}
	public String getcInvAddCode() {
		return cInvAddCode;
	}
	public void setcInvAddCode(String cInvAddCode) {
		this.cInvAddCode = cInvAddCode;
	}

	
	public String getcInvCode() {
		return cInvCode;
	}
	public void setcInvCode(String cInvCode) {
		this.cInvCode = cInvCode;
	}
	public String getcInvName() {
		return cInvName;
	}
	public void setcInvName(String cInvName) {
		this.cInvName = cInvName;
	}
	public String getcInvStd() {
		return cInvStd;
	}
	public void setcInvStd(String cInvStd) {
		this.cInvStd = cInvStd;
	}
	public ComputationUnit getComputationUnit() {
		return computationUnit;
	}
	public void setComputationUnit(ComputationUnit computationUnit) {
		this.computationUnit = computationUnit;
	}
}
