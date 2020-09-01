package com.sun.bosen.pojo;

public class Rdrecords {
	private int autoId;
	private int iD;
	private String cInvCode;
	private float iQuantity;
	private float iUnitCost;
	private float iPrice;
	private float iAPrice;
	private String cDefine25;
	private int iPOsID;
	private float fACost;
	private float iNQuantity;
	private float iTaxCost;
	private float iTaxPrice;
	private float iTaxRate;
	private float iSum;
	private String cDefine22;
	private int iMPoIds;
	private String cDefine26;
	private String cDefine27;
	private String cCBGJDXCode;
	private String cBVencode;
	private float fQuantity;//本次出库数量
	private String cInvStd;
	private String cInvName;
	private String cComunitName;
	private String cMemo;
	
	private String cWhCode;
	private String cRdCode;
	private String cDepCode;
	private String base64Str;
	
	public String getBase64Str() {
		return base64Str;
	}
	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}
	public String getcWhCode() {
		return cWhCode;
	}
	public void setcWhCode(String cWhCode) {
		this.cWhCode = cWhCode;
	}
	public String getcRdCode() {
		return cRdCode;
	}
	public void setcRdCode(String cRdCode) {
		this.cRdCode = cRdCode;
	}
	public String getcDepCode() {
		return cDepCode;
	}
	public void setcDepCode(String cDepCode) {
		this.cDepCode = cDepCode;
	}
	public float getfQuantity() {
		return fQuantity;
	}
	public void setfQuantity(float fQuantity) {
		this.fQuantity = fQuantity;
	}
	public String getcMemo() {
		return cMemo;
	}
	public void setcMemo(String cMemo) {
		this.cMemo = cMemo;
	}
	public String getcInvStd() {
		return cInvStd;
	}
	public void setcInvStd(String cInvStd) {
		this.cInvStd = cInvStd;
	}
	public String getcInvName() {
		return cInvName;
	}
	public void setcInvName(String cInvName) {
		this.cInvName = cInvName;
	}
	public String getcComunitName() {
		return cComunitName;
	}
	public void setcComunitName(String cComunitName) {
		this.cComunitName = cComunitName;
	}
	public float getNowfQuantity() {
		return fQuantity;
	}
	public void setNowfQuantity(float fQuantity) {
		this.fQuantity = fQuantity;
	}
	public String getcBVencode() {
		return cBVencode;
	}
	public void setcBVencode(String cBVencode) {
		this.cBVencode = cBVencode;
	}
	private String ccbgjdxname;
	
	public String getcCBGJDXCode() {
		return cCBGJDXCode;
	}
	public void setcCBGJDXCode(String cCBGJDXCode) {
		this.cCBGJDXCode = cCBGJDXCode;
	}
	public String getCcbgjdxname() {
		return ccbgjdxname;
	}
	public void setCcbgjdxname(String ccbgjdxname) {
		this.ccbgjdxname = ccbgjdxname;
	}
	public String getcDefine27() {
		return cDefine27;
	}
	public void setcDefine27(String cDefine27) {
		this.cDefine27 = cDefine27;
	}
	public String getcDefine26() {
		return cDefine26;
	}
	public void setcDefine26(String cDefine26) {
		this.cDefine26 = cDefine26;
	}
	public int getiMPoIds() {
		return iMPoIds;
	}
	public void setiMPoIds(int iMPoIds) {
		this.iMPoIds = iMPoIds;
	}
	public String getcDefine22() {
		return cDefine22;
	}
	public void setcDefine22(String cDefine22) {
		this.cDefine22 = cDefine22;
	}


	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public int getiD() {
		return iD;
	}
	public void setiPOsID(int iPOsID) {
		this.iPOsID = iPOsID;
	}
	public int getiPOsID() {
		return iPOsID;
	}
	public void setcInvCode(String cInvCode) {
		this.cInvCode = cInvCode;
	}
	public String getcInvCode() {
		return cInvCode;
	}
	public void setcDefine25(String cDefine25) {
		this.cDefine25 = cDefine25;
	}
	public String getcDefine25() {
		return cDefine25;
	}
	public void setiQuantity(float iQuantity) {
		this.iQuantity = iQuantity;
	}
	public float getiQuantity() {
		return iQuantity;
	}
	
	public void setiUnitCost(float iUnitCost) {
		this.iUnitCost = iUnitCost;
	}
	public float getiUnitCost() {
		return iUnitCost;
	}
	
	public void setiPrice(float iPrice) {
		this.iPrice = iPrice;
	}
	public float getiPrice() {
		return iPrice;
	}
	
	public void setiAPrice(float iAPrice) {
		this.iAPrice = iAPrice;
	}
	public float getiAPrice() {
		return iAPrice;
	}
	
	
	public void setiNQuantity(float iNQuantity) {
		this.iNQuantity = iNQuantity;
	}
	public float getiNQuantity() {
		return iNQuantity;
	}
	
	public void setiTaxCost(float iTaxCost) {
		this.iTaxCost = iTaxCost;
	}
	public float getiTaxCost() {
		return iTaxCost;
	}
	
	public void setfACost(float fACost) {
		this.fACost = fACost;
	}
	public float getfACost() {
		return fACost;
	}
	
	public void setiTaxPrice(float iTaxPrice) {
		this.iTaxPrice = iTaxPrice;
	}
	public float getiTaxPrice() {
		return iTaxPrice;
	}
	
	public void setiTaxRate(float iTaxRate) {
		this.iTaxRate = iTaxRate;
	}
	public float getiTaxRate() {
		return iTaxRate;
	}
	
	public void setiSum(float iSum) {
		this.iSum = iSum;
	}
	public float getiSum() {
		return iSum;
	}
	@Override
	public String toString() {
		return "Rdrecords [autoId=" + autoId + ", iD=" + iD + ", cInvCode=" + cInvCode + ", iQuantity=" + iQuantity
				+ ", iUnitCost=" + iUnitCost + ", iPrice=" + iPrice + ", iAPrice=" + iAPrice + ", cDefine25="
				+ cDefine25 + ", iPOsID=" + iPOsID + ", fACost=" + fACost + ", iNQuantity=" + iNQuantity + ", iTaxCost="
				+ iTaxCost + ", iTaxPrice=" + iTaxPrice + ", iTaxRate=" + iTaxRate + ", iSum=" + iSum + ", cDefine22="
				+ cDefine22 + ", iMPoIds=" + iMPoIds + ", cDefine26=" + cDefine26 + ", cDefine27=" + cDefine27
				+ ", cCBGJDXCode=" + cCBGJDXCode + ", cBVencode=" + cBVencode + ", fQuantity=" + fQuantity
				+ ", cInvStd=" + cInvStd + ", cInvName=" + cInvName + ", cComunitName=" + cComunitName + ", cMemo="
				+ cMemo + ", cWhCode=" + cWhCode + ", cRdCode=" + cRdCode + ", cDepCode=" + cDepCode + ", base64Str="
				+ base64Str + ", ccbgjdxname=" + ccbgjdxname + "]";
	}


	
	
	
}
