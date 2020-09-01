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
	private String base64Str;
	private String cdepcode;//子件组别代码
	private String cdepname;//子件组别名称
	private String librarian;//库管员
	private String taskId;
	private int needOutNumber;
	private float tdQtyD;//分母配比用量
	private float ipsquantity;//分子配比用料
	
	public float getTdQtyD() {
		return tdQtyD;
	}
	public void setTdQtyD(float tdQtyD) {
		this.tdQtyD = tdQtyD;
	}
	public float getIpsquantity() {
		return ipsquantity;
	}
	public void setIpsquantity(float ipsquantity) {
		this.ipsquantity = ipsquantity;
	}
	public int getNeedOutNumber() {
		return needOutNumber;
	}
	public void setNeedOutNumber(int needOutNumber) {
		this.needOutNumber = needOutNumber;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getLibrarian() {
		return librarian;
	}
	public void setLibrarian(String librarian) {
		this.librarian = librarian;
	}
	public String getCdepcode() {
		return cdepcode;
	}
	public void setCdepcode(String cdepcode) {
		this.cdepcode = cdepcode;
	}
	public String getCdepname() {
		return cdepname;
	}
	public void setCdepname(String cdepname) {
		this.cdepname = cdepname;
	}
	public String getBase64Str() {
		return base64Str;
	}
	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}
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
	@Override
	public String toString() {
		return "PP_Podetails [mainId=" + mainId + ", subId=" + subId + ", cDefine22=" + cDefine22 + ", inventory="
				+ inventory + ", fQuantity=" + fQuantity + ", fOutQuantity=" + fOutQuantity + ", nowfOutQuantity="
				+ nowfOutQuantity + ", cWhCode=" + cWhCode + ", iUnitCost=" + iUnitCost + ", iPrice=" + iPrice
				+ ", cMemo=" + cMemo + ", cRdCode=" + cRdCode + ", cCode=" + cCode + ", cDepCode=" + cDepCode
				+ ", cMaker=" + cMaker + ", base64Str=" + base64Str + ", cdepcode=" + cdepcode + ", cdepname="
				+ cdepname + ", librarian=" + librarian + "]";
	}
	
	
}
