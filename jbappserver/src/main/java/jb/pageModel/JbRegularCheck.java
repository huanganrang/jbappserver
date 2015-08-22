package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class JbRegularCheck implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String maintainId;	
	private Date checkDate;			
	private java.lang.String checkOrder;	
	private java.lang.String checkPeople;	
	private java.lang.String checkPhone;	
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
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public Date getCheckDate() {
		return this.checkDate;
	}
	public void setCheckOrder(java.lang.String checkOrder) {
		this.checkOrder = checkOrder;
	}
	
	public java.lang.String getCheckOrder() {
		return this.checkOrder;
	}
	public void setCheckPeople(java.lang.String checkPeople) {
		this.checkPeople = checkPeople;
	}
	
	public java.lang.String getCheckPeople() {
		return this.checkPeople;
	}
	public void setCheckPhone(java.lang.String checkPhone) {
		this.checkPhone = checkPhone;
	}
	
	public java.lang.String getCheckPhone() {
		return this.checkPhone;
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
