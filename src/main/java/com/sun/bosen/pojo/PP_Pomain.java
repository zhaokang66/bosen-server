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
	private float iprice;//单价
	private float price;//金额
	public float getIprice() {
		return iprice;
	}
	public void setIprice(float iprice) {
		this.iprice = iprice;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getNowiReceivedQTY() {
		return nowiReceivedQTY;
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
