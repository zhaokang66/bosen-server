package com.sun.bosen.pojo;

import java.util.List;

public class PO_Pomain {

	//采购主表ID
	private int pOID;
	//采购订单号
	private String cPOID;
	//供应商编码
	private String cVenCode;
	//供应商档案
	private Vendor vendor;
	//单据日期
	private String dPoDate;
	//采购订单子表列表
	private List<PO_Podetails> pO_Podetails;
	//备注
	private String cMemo;
	private String cBusType;
	private String cDepCode;
	private String cPersonCode;
	private String cPtCode;
	private String cMaker;
	private String cVerifier;
	
	private String cAccountPDate;
	
	public void setcAccountPDate(String cAccountPDate) {
		this.cAccountPDate = cAccountPDate;
	}
	public String getcAccountPDate() {
		return cAccountPDate;
	}
	public void setcVerifier(String cVerifier) {
		this.cVerifier = cVerifier;
	}
	public String getcVerifier() {
		return cVerifier;
	}
	
	public String getcMemo() {
		return cMemo;
	}
	public void setcMemo(String cMemo) {
		this.cMemo = cMemo;
	}
	public int getpOID() {
		return pOID;
	}
	public void setpOID(int pOID) {
		this.pOID = pOID;
	}
	public String getcPOID() {
		return cPOID;
	}
	public void setcPOID(String cPOID) {
		this.cPOID = cPOID;
	}
	public String getcMaker() {
		return cMaker;
	}
	public void setcMaker(String cMaker) {
		this.cMaker = cMaker;
	}
	public String getcPtCode() {
		return cPtCode;
	}
	public void setcPTCode(String cPtCode) {
		this.cPtCode = cPtCode;
	}
	public String getcPersonCode() {
		return cPersonCode;
	}
	public void setcPersonCode(String cPersonCode) {
		this.cPersonCode = cPersonCode;
	}
	
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public String getdPoDate() {
		return dPoDate;
	}
	public void setdPoDate(String dPoDate) {
		this.dPoDate = dPoDate;
	}
	public void setcBusType(String cBusType) {
		this.cBusType = cBusType;
	}
	public String getcBusType() {
		return cBusType;
	}
	public void setcDepCode(String cDepCode) {
		this.cDepCode = cDepCode;
	}
	public String getcDepCode() {
		return cDepCode;
	}

	public void setcVenCode(String cVenCode) {
		this.cVenCode = cVenCode;
	}
	public String getcVenCode() {
		return cVenCode;
	}
	
	public List<PO_Podetails> getpO_Podetails() {
		return pO_Podetails;
	}
	public void setpO_Podetails(List<PO_Podetails> pO_Podetails) {
		this.pO_Podetails = pO_Podetails;
	}
}
