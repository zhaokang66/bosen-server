package com.sun.bosen.pojo;

public class User_Task {

	private int id;
	private String username;
	private String task;
	private String time;
	private int distributionId;
	private int status;
	private String cdepname;
	private String productionCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getDistributionId() {
		return distributionId;
	}
	public void setDistributionId(int distributionId) {
		this.distributionId = distributionId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCdepname() {
		return cdepname;
	}
	public void setCdepname(String cdepname) {
		this.cdepname = cdepname;
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
	@Override
	public String toString() {
		return "User_Task [id=" + id + ", username=" + username + ", task=" + task + ", time=" + time
				+ ", distributionId=" + distributionId + ", status=" + status + ", cdepname=" + cdepname
				+ ", productionCode=" + productionCode + "]";
	}
	
	
	
}
