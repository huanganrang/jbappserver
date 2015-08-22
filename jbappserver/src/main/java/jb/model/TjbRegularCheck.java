
/*
 * @author John
 * @date - 2015-08-03
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "jb_regular_check")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TjbRegularCheck implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "JbRegularCheck";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MAINTAIN_ID = "基础表ID";
	public static final String ALIAS_CHECK_DATE = "巡检时间";
	public static final String ALIAS_CHECK_ORDER = "巡检单号";
	public static final String ALIAS_CHECK_PEOPLE = "巡检人";
	public static final String ALIAS_CHECK_PHONE = "联系方式";
	public static final String ALIAS_SUMMARY = "概述";
	public static final String ALIAS_SCAN_FILE = "原件扫描";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_ADDTIME = "addtime";
	
	//date formats
	public static final String FORMAT_CHECK_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String maintainId;
	//
	private java.util.Date checkDate;
	//@Length(max=36)
	private java.lang.String checkOrder;
	//@Length(max=18)
	private java.lang.String checkPeople;
	//@Length(max=18)
	private java.lang.String checkPhone;
	//@Length(max=256)
	private java.lang.String summary;
	//@Length(max=64)
	private java.lang.String scanFile;
	//@Length(max=512)
	private java.lang.String remark;
	//
	private java.util.Date addtime;
	//columns END


		public TjbRegularCheck(){
		}
		public TjbRegularCheck(String id) {
			this.id = id;
		}
	

	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "maintain_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getMaintainId() {
		return this.maintainId;
	}
	
	public void setMaintainId(java.lang.String maintainId) {
		this.maintainId = maintainId;
	}
	

	@Column(name = "check_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getCheckDate() {
		return this.checkDate;
	}
	
	public void setCheckDate(java.util.Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@Column(name = "check_order", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCheckOrder() {
		return this.checkOrder;
	}
	
	public void setCheckOrder(java.lang.String checkOrder) {
		this.checkOrder = checkOrder;
	}
	
	@Column(name = "check_people", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public java.lang.String getCheckPeople() {
		return this.checkPeople;
	}
	
	public void setCheckPeople(java.lang.String checkPeople) {
		this.checkPeople = checkPeople;
	}
	
	@Column(name = "check_phone", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public java.lang.String getCheckPhone() {
		return this.checkPhone;
	}
	
	public void setCheckPhone(java.lang.String checkPhone) {
		this.checkPhone = checkPhone;
	}
	
	@Column(name = "summary", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getSummary() {
		return this.summary;
	}
	
	public void setSummary(java.lang.String summary) {
		this.summary = summary;
	}
	
	@Column(name = "scan_file", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getScanFile() {
		return this.scanFile;
	}
	
	public void setScanFile(java.lang.String scanFile) {
		this.scanFile = scanFile;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MaintainId",getMaintainId())
			.append("CheckDate",getCheckDate())
			.append("CheckOrder",getCheckOrder())
			.append("CheckPeople",getCheckPeople())
			.append("CheckPhone",getCheckPhone())
			.append("Summary",getSummary())
			.append("ScanFile",getScanFile())
			.append("Remark",getRemark())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JbRegularCheck == false) return false;
		if(this == obj) return true;
		JbRegularCheck other = (JbRegularCheck)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

