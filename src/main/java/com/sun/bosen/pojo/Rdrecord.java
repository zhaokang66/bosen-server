package com.sun.bosen.pojo;

public class Rdrecord {
	private int iD;
	private int bRdFlag = 1;
	private String cVouchType = "01";
	private String cBusType;
	private int iPurorderid;
	private String cSource = "采购订单";
	private String cWhCode;
	private String dDate;
	private int startID = 0;//记录本次开始ID
	private String cCode;
	private String cRdCode = "101";//Rd_Style 表中自定义的账单类型
	private String cDepCode;
	private String cPersonCode;
	private String cPtCode;
	private String cVenCode;
	private String cOrderCode;
	private String cMemo;
	private int vT_ID = 27;
	private String cMaker;
	private String cAccountPDate;
	private String cHandler;
	
	public void setiD(int iD) {
		this.iD = iD;
	}
	public int getiD() {
		return iD;
	}
	public int getbRdFlag() {
		return bRdFlag;
	}
	public void setcHandler(String cHandler) {
		this.cHandler = cHandler;
	}
	public String getcHandler() {
		return cHandler;
	}
	public void setvT_ID(int vT_ID) {
		this.vT_ID = vT_ID;
	}
	public int getvT_ID() {
		return vT_ID;
	}
	public void setstartID(int startID) {
		this.startID = startID;
	}
	public int getstartID() {
		return startID;
	}
	public void setiPurorderid(int iPurorderid) {
		this.iPurorderid = iPurorderid;
	}
	public int getiPurorderid() {
		return iPurorderid;
	}
	public String getcVouchType() {
		return cVouchType;
	}
	public void setcBusType(String cBusType) {
		this.cBusType = cBusType;
	}
	public String getcBusType() {
		return cBusType;
	}
	public String getcSource() {
		return cSource;
	}
	public void setcWhCode(String cWhCode) {
		this.cWhCode = cWhCode;
	}
	public String getcWhCode() {
		return cWhCode;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	public String getdDate() {
		return dDate;
	}
	public void setcCode(String cCode) {
		this.cCode = cCode;
	}
	public String getcCode() {
		return cCode;
	}
	public String getcRdCode() {
		return cRdCode;
	}
	public void setcDepCode(String cDepCode) {
		this.cDepCode = cDepCode;
	}
	public String getcDepCode() {
		return cDepCode;
	}
	public void setcPersonCode(String cPersonCode) {
		this.cPersonCode = cPersonCode;
	}
	public String getcPersonCode() {
		return cPersonCode;
	}
	public void setcPtCode(String cPtCode) {
		this.cPtCode = cPtCode;
	}
	public String getcPtCode() {
		return cPtCode;
	}
	public void setcVenCode(String cVenCode) {
		this.cVenCode = cVenCode;
	}
	public String getcVenCode() {
		return cVenCode;
	}
	public void setcOrderCode(String cOrderCode) {
		this.cOrderCode = cOrderCode;
	}
	public String getcOrderCode() {
		return cOrderCode;
	}
	public void setcMemo(String cMemo) {
		this.cMemo = cMemo;
	}
	public String getcMemo() {
		return cMemo;
	}
	public void setcMaker(String cMaker) {
		this.cMaker = cMaker;
	}
	public String getcMaker() {
		return cMaker;
	}
	public void setcAccountPDate(String cAccountPDate) {
		this.cAccountPDate = cAccountPDate;
	}
	public String getcAccountPDate() {
		return cAccountPDate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
