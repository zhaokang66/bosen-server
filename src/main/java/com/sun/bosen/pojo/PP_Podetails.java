package com.sun.bosen.pojo;

public class PP_Podetails {
	private int mainId;
	private int subId;
	private String cDefine22;
	private Inventory inventory;
	private float fQuantity;
	private float fOutQuantity;
	private float nowfOutQuantity;
	private String cWhCode;
	private float iUnitCost;//单价
	private float iPrice;//金额
	private String cMemo;
	private String cRdCode;
	private String cCode;
	private String cDepCode;
	private String cMaker;
	
	public String getcDepCode() {
		return cDepCode;
	}
	public void setcDepCode(String cDepCode) {
		this.cDepCode = cDepCode;
	}
	public String getcMaker() {
		return cMaker;
	}
	public void setcMaker(String cMaker) {
		this.cMaker = cMaker;
	}
	public String getcCode() {
		return cCode;
	}
	public void setcCode(String cCode) {
		this.cCode = cCode;
	}
	public String getcRdCode() {
		return cRdCode;
	}
	public void setcRdCode(String cRdCode) {
		this.cRdCode = cRdCode;
	}
	public String getcMemo() {
		return cMemo;
	}
	public void setcMemo(String cMemo) {
		this.cMemo = cMemo;
	}
	public String getcWhCode() {
		return cWhCode;
	}
	public void setcWhCode(String cWhCode) {
		this.cWhCode = cWhCode;
	}
	
	public float getiUnitCost() {
		return iUnitCost;
	}
	public void setiUnitCost(float iUnitCost) {
		this.iUnitCost = iUnitCost;
	}
	public float getiPrice() {
		return iPrice;
	}
	public void setiPrice(float iPrice) {
		this.iPrice = iPrice;
	}
	public float getNowfOutQuantity() {
		return nowfOutQuantity;
	}
	public void setNowfOutQuantity(float nowfOutQuantity) {
		this.nowfOutQuantity = nowfOutQuantity;
	}
	public float getfOutQuantity() {
		return fOutQuantity;
	}
	public void setfOutQuantity(float fOutQuantity) {
		this.fOutQuantity = fOutQuantity;
	}
	public int getMainId() {
		return mainId;
	}
	public void setMainId(int mainId) {
		this.mainId = mainId;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getcDefine22() {
		return cDefine22;
	}
	public void setcDefine22(String cDefine22) {
		this.cDefine22 = cDefine22;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public float getfQuantity() {
		return fQuantity;
	}
	public void setfQuantity(float fQuantity) {
		this.fQuantity = fQuantity;
	}
	
}
