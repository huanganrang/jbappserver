package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class JbRepairCheck implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String maintainId;	
	private Date repairDate;			
	private java.lang.String repairOrder;	
	private java.lang.String repairPeople;	
	private java.lang.String repairPhone;	
	private java.lang.String summary;	
	private java.lang.String scanFile;	
	private java.lang.String remark;	
	private Date addtime;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setMaintainId(java.lang.String maintainId) {
		this.maintainId = maintainId;
	}
	
	public java.lang.String getMaintainId() {
		return this.maintainId;
	}
	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	
	public Date getRepairDate() {
		return this.repairDate;
	}
	public void setRepairOrder(java.lang.String repairOrder) {
		this.repairOrder = repairOrder;
	}
	
	public java.lang.String getRepairOrder() {
		return this.repairOrder;
	}
	public void setRepairPeople(java.lang.String repairPeople) {
		this.repairPeople = repairPeople;
	}
	
	public java.lang.String getRepairPeople() {
		return this.repairPeople;
	}
	public void setRepairPhone(java.lang.String repairPhone) {
		this.repairPhone = repairPhone;
	}
	
	public java.lang.String getRepairPhone() {
		return this.repairPhone;
	}
	public void setSummary(java.lang.String summary) {
		this.summary = summary;
	}
	
	public java.lang.String getSummary() {
		return this.summary;
	}
	public void setScanFile(java.lang.String scanFile) {
		this.scanFile = scanFile;
	}
	
	public java.lang.String getScanFile() {
		return this.scanFile;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}

}
