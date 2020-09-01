package com.sun.bosen.pojo;

public class PP_Pomain {
	
	private int id;
	private int mainId;
	//生产计划编号    
//	private String  cInvCode;
	private Vendor vendor;
	private String dStartDate;
	private String dEndDate;
	private float fInQuantity;//累计入库数量
	private float fQuantity;//投产数量
	private String productionCode;
	private Inventory inventory;
	private float nowiReceivedQTY;
	private String cWhCode;
	private float iUnitCost;//单价
	private float iPrice;//金额
	private String cCode;
	private String cMaker;
	private String cDepCode;
	private String cMemo;
	private String cRdCode;
	private int numberOfOutbound;//本次配套出库数量
	

	public int getNumberOfOutbound() {
		return numberOfOutbound;
	}
	public void setNumberOfOutbound(int numberOfOutbound) {
		this.numberOfOutbound = numberOfOutbound;
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
	public String getcMaker() {
		return cMaker;
	}
	public void setcMaker(String cMaker) {
		this.cMaker = cMaker;
	}
	public String getcDepCode() {
		return cDepCode;
	}
	public void setcDepCode(String cDepCode) {
		this.cDepCode = cDepCode;
	}
	public String getcCode() {
		return cCode;
	}
	public void setcCode(String cCode) {
		this.cCode = cCode;
	}
	public float getNowiReceivedQTY() {
		return nowiReceivedQTY;
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
	public void setNowiReceivedQTY(float nowiReceivedQTY) {
		this.nowiReceivedQTY = nowiReceivedQTY;
	}
	public String getcWhCode() {
		return cWhCode;
	}
	public void setcWhCode(String cWhCode) {
		this.cWhCode = cWhCode;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMainId() {
		return mainId;
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
	public void setMainId(int mainId) {
		this.mainId = mainId;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public String getdStartDate() {
		return dStartDate;
	}
	public void setdStartDate(String dStartDate) {
		this.dStartDate = dStartDate;
	}
	public String getdEndDate() {
		return dEndDate;
	}
	public void setdEndDate(String dEndDate) {
		this.dEndDate = dEndDate;
	}
	public float getfInQuantity() {
		return fInQuantity;
	}
	public void setfInQuantity(float fInQuantity) {
		this.fInQuantity = fInQuantity;
	}
	public float getfQuantity() {
		return fQuantity;
	}
	public void setfQuantity(float fQuantity) {
		this.fQuantity = fQuantity;
	}
	
	
	
	
	
}
