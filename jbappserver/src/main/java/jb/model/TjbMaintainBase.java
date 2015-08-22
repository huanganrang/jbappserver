
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
@Table(name = "jb_maintain_base")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TjbMaintainBase implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "JbMaintainBase";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ASSET_ID = "资产ID";
	public static final String ALIAS_MAINTAIN_UNIT = "维护单位";
	public static final String ALIAS_TELPHONE = "联系方式";
	public static final String ALIAS_END_DATE = "维保到期时间";
	public static final String ALIAS_CHECK_CYCLE = "巡检周期";
	public static final String ALIAS_CHECK_MODE = "维保模式";
	public static final String ALIAS_NEXT_CHECK_DATE = "下次巡检时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_ADDTIME = "addtime";
	
	//date formats
	public static final String FORMAT_END_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_NEXT_CHECK_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=36)
	private java.lang.String assetId;
	//@Length(max=126)
	private java.lang.String maintainUnit;
	//@Length(max=16)
	private java.lang.String telphone;
	//
	private java.util.Date endDate;
	//@Length(max=36)
	private java.lang.String checkCycle;
	//@Length(max=36)
	private java.lang.String checkMode;
	//
	private java.util.Date nextCheckDate;
	//@Length(max=512)
	private java.lang.String remark;
	//
	private java.util.Date addtime;
	//columns END


		public TjbMaintainBase(){
		}
		public TjbMaintainBase(String id) {
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
	
	@Column(name = "asset_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getAssetId() {
		return this.assetId;
	}
	
	public void setAssetId(java.lang.String assetId) {
		this.assetId = assetId;
	}
	
	@Column(name = "maintain_unit", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	public java.lang.String getMaintainUnit() {
		return this.maintainUnit;
	}
	
	public void setMaintainUnit(java.lang.String maintainUnit) {
		this.maintainUnit = maintainUnit;
	}
	
	@Column(name = "telphone", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	
	public void setTelphone(java.lang.String telphone) {
		this.telphone = telphone;
	}
	

	@Column(name = "end_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name = "check_cycle", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCheckCycle() {
		return this.checkCycle;
	}
	
	public void setCheckCycle(java.lang.String checkCycle) {
		this.checkCycle = checkCycle;
	}
	
	@Column(name = "check_mode", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCheckMode() {
		return this.checkMode;
	}
	
	public void setCheckMode(java.lang.String checkMode) {
		this.checkMode = checkMode;
	}
	

	@Column(name = "next_check_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getNextCheckDate() {
		return this.nextCheckDate;
	}
	
	public void setNextCheckDate(java.util.Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
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
			.append("AssetId",getAssetId())
			.append("MaintainUnit",getMaintainUnit())
			.append("Telphone",getTelphone())
			.append("EndDate",getEndDate())
			.append("CheckCycle",getCheckCycle())
			.append("CheckMode",getCheckMode())
			.append("NextCheckDate",getNextCheckDate())
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
		if(obj instanceof JbMaintainBase == false) return false;
		if(this == obj) return true;
		JbMaintainBase other = (JbMaintainBase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

