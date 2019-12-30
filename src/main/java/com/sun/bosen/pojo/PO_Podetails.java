package com.sun.bosen.pojo;

public class PO_Podetails {
	
	
	
	
	
	
	//采购订单子表标识符
	private int id;
	//采购订单主表标识符
	private int pOID;
	//存货编码
	//private String cInvCode;
	//存货档案
	private Inventory inventory;
	//数量
	//累计到货数量
	private float iReceivedQTY;
	//生产编号
	private String productionCode;
	//本次入库数量
	private float nowiReceivedQTY;
	private String cWhCode;
	private float iQuantity;
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
	public float getiReceivedQTY() {
		return iReceivedQTY;
	}
	public void setiReceivedQTY(float iReceivedQTY) {
		this.iReceivedQTY = iReceivedQTY;
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
	public float getiQuantity() {
		return iQuantity;
	}
	public void setiQuantity(float iQuantity) {
		this.iQuantity = iQuantity;
	}
	public int getpOID() {
		return pOID;
	}
	public void setpOID(int pOID) {
		this.pOID = pOID;
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
}
