package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class JbMaintainBase implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String assetId;	
	private java.lang.String maintainUnit;	
	private java.lang.String telphone;	
	private Date endDate;			
	private java.lang.String checkCycle;	
	private java.lang.String checkMode;	
	private Date nextCheckDate;			
	private java.lang.String remark;	
	private Date addtime;			

	private JbRegularCheck jbRegularCheck;
	private JbRepairCheck jbRepairCheck;

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setAssetId(java.lang.String assetId) {
		this.assetId = assetId;
	}
	
	public java.lang.String getAssetId() {
		return this.assetId;
	}
	public void setMaintainUnit(java.lang.String maintainUnit) {
		this.maintainUnit = maintainUnit;
	}
	
	public java.lang.String getMaintainUnit() {
		return this.maintainUnit;
	}
	public void setTelphone(java.lang.String telphone) {
		this.telphone = telphone;
	}
	
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	public void setCheckCycle(java.lang.String checkCycle) {
		this.checkCycle = checkCycle;
	}
	
	public java.lang.String getCheckCycle() {
		return this.checkCycle;
	}
	public void setCheckMode(java.lang.String checkMode) {
		this.checkMode = checkMode;
	}
	
	public java.lang.String getCheckMode() {
		return this.checkMode;
	}
	public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}
	
	public Date getNextCheckDate() {
		return this.nextCheckDate;
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

	public JbRegularCheck getJbRegularCheck() {
		return jbRegularCheck;
	}

	public void setJbRegularCheck(JbRegularCheck jbRegularCheck) {
		this.jbRegularCheck = jbRegularCheck;
	}

	public JbRepairCheck getJbRepairCheck() {
		return jbRepairCheck;
	}

	public void setJbRepairCheck(JbRepairCheck jbRepairCheck) {
		this.jbRepairCheck = jbRepairCheck;
	}

	
}
