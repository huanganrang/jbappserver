
/*
 * @author John
 * @date - 2015-08-04
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "jb_assets")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TjbAssets implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "JbAssets";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ASSET_NUMBER = "资产编号";
	public static final String ALIAS_FIRST_CATEGORY = "资产种类";
	public static final String ALIAS_SECOND_CATEGORY = "二级分类";
	public static final String ALIAS_DESCRIPTION = "资产描述";
	public static final String ALIAS_FACTORY = "生产厂家";
	public static final String ALIAS_ASSET_TYPE = "型号";
	public static final String ALIAS_SERIAL_NUMBER = "序列号";
	public static final String ALIAS_LOCATION = "位置";
	public static final String ALIAS_DEPT_ID = "部门";
	public static final String ALIAS_PRINCIPAL = "资产负责人";
	public static final String ALIAS_BUY_DATE = "购买日期";
	public static final String ALIAS_MAKE_DATE = "装日期/投产日";
	public static final String ALIAS_SUPPLIER = "供应商名称";
	public static final String ALIAS_SUPPLIER_PHONE = "供应商联系电话";
	public static final String ALIAS_MEASURE = "尺寸";
	public static final String ALIAS_WEIGHT = "重量";
	public static final String ALIAS_STANDARD_POWER = "标准功率";
	public static final String ALIAS_ICON = "图标地址";
	public static final String ALIAS_PARENT_ID = "机柜/机房ID";
	public static final String ALIAS_ROOM_AREA_ID = "区域ID";
	public static final String ALIAS_SCOPE = "范围";
	public static final String ALIAS_UPLACE = "U位数";
	public static final String ALIAS_UID = "uid";
	public static final String ALIAS_ADDTIME = "addtime";
	
	//date formats
	public static final String FORMAT_BUY_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_MAKE_DATE = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=32)
	private java.lang.String assetNumber;
	//@Length(max=4)
	private java.lang.String firstCategory;
	//@Length(max=4)
	private java.lang.String secondCategory;
	//@Length(max=512)
	private java.lang.String description;
	//@Length(max=4)
	private java.lang.String factory;
	//@Length(max=4)
	private java.lang.String assetType;
	//@Length(max=64)
	private java.lang.String serialNumber;
	//@Length(max=128)
	private java.lang.String location;
	//@Length(max=36)
	private java.lang.String deptId;
	//@Length(max=36)
	private java.lang.String principal;
	//
	private java.util.Date buyDate;
	//
	private java.util.Date makeDate;
	//@Length(max=128)
	private java.lang.String supplier;
	//@Length(max=32)
	private java.lang.String supplierPhone;
	//@Length(max=32)
	private java.lang.String measure;
	//
	private java.lang.Float weight;
	//
	private java.lang.Float standardPower;
	//@Length(max=128)
	private java.lang.String icon;
	//@Length(max=36)
	private java.lang.String parentId;
	//@Length(max=36)
	private java.lang.String roomAreaId;
	//@Length(max=128)
	private java.lang.String scope;
	//
	private java.lang.Integer uplace;
	//@Length(max=32)
	private java.lang.String uid;
	//
	private java.util.Date addtime;
	//columns END


		public TjbAssets(){
		}
		public TjbAssets(String id) {
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
	
	@Column(name = "asset_number", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getAssetNumber() {
		return this.assetNumber;
	}
	
	public void setAssetNumber(java.lang.String assetNumber) {
		this.assetNumber = assetNumber;
	}
	
	@Column(name = "first_category", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getFirstCategory() {
		return this.firstCategory;
	}
	
	public void setFirstCategory(java.lang.String firstCategory) {
		this.firstCategory = firstCategory;
	}
	
	@Column(name = "second_category", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getSecondCategory() {
		return this.secondCategory;
	}
	
	public void setSecondCategory(java.lang.String secondCategory) {
		this.secondCategory = secondCategory;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	@Column(name = "factory", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getFactory() {
		return this.factory;
	}
	
	public void setFactory(java.lang.String factory) {
		this.factory = factory;
	}
	
	@Column(name = "asset_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getAssetType() {
		return this.assetType;
	}
	
	public void setAssetType(java.lang.String assetType) {
		this.assetType = assetType;
	}
	
	@Column(name = "serial_number", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Column(name = "location", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getLocation() {
		return this.location;
	}
	
	public void setLocation(java.lang.String location) {
		this.location = location;
	}
	
	@Column(name = "dept_Id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getDeptId() {
		return this.deptId;
	}
	
	public void setDeptId(java.lang.String deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "principal", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getPrincipal() {
		return this.principal;
	}
	
	public void setPrincipal(java.lang.String principal) {
		this.principal = principal;
	}
	

	@Column(name = "buy_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getBuyDate() {
		return this.buyDate;
	}
	
	public void setBuyDate(java.util.Date buyDate) {
		this.buyDate = buyDate;
	}
	

	@Column(name = "make_date", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.util.Date getMakeDate() {
		return this.makeDate;
	}
	
	public void setMakeDate(java.util.Date makeDate) {
		this.makeDate = makeDate;
	}
	
	@Column(name = "supplier", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getSupplier() {
		return this.supplier;
	}
	
	public void setSupplier(java.lang.String supplier) {
		this.supplier = supplier;
	}
	
	@Column(name = "supplier_phone", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getSupplierPhone() {
		return this.supplierPhone;
	}
	
	public void setSupplierPhone(java.lang.String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	
	@Column(name = "measure", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getMeasure() {
		return this.measure;
	}
	
	public void setMeasure(java.lang.String measure) {
		this.measure = measure;
	}
	
	@Column(name = "weight", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getWeight() {
		return this.weight;
	}
	
	public void setWeight(java.lang.Float weight) {
		this.weight = weight;
	}
	
	@Column(name = "standard_power", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getStandardPower() {
		return this.standardPower;
	}
	
	public void setStandardPower(java.lang.Float standardPower) {
		this.standardPower = standardPower;
	}
	
	@Column(name = "icon", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "room_area_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRoomAreaId() {
		return this.roomAreaId;
	}
	
	public void setRoomAreaId(java.lang.String roomAreaId) {
		this.roomAreaId = roomAreaId;
	}
	
	@Column(name = "scope", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getScope() {
		return this.scope;
	}
	
	public void setScope(java.lang.String scope) {
		this.scope = scope;
	}
	
	@Column(name = "u_place", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUplace() {
		return this.uplace;
	}
	
	public void setUplace(java.lang.Integer uplace) {
		this.uplace = uplace;
	}
	
	@Column(name = "uid", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getUid() {
		return this.uid;
	}
	
	public void setUid(java.lang.String uid) {
		this.uid = uid;
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
			.append("AssetNumber",getAssetNumber())
			.append("FirstCategory",getFirstCategory())
			.append("SecondCategory",getSecondCategory())
			.append("Description",getDescription())
			.append("Factory",getFactory())
			.append("AssetType",getAssetType())
			.append("SerialNumber",getSerialNumber())
			.append("Location",getLocation())
			.append("DeptId",getDeptId())
			.append("Principal",getPrincipal())
			.append("BuyDate",getBuyDate())
			.append("MakeDate",getMakeDate())
			.append("Supplier",getSupplier())
			.append("SupplierPhone",getSupplierPhone())
			.append("Measure",getMeasure())
			.append("Weight",getWeight())
			.append("StandardPower",getStandardPower())
			.append("Icon",getIcon())
			.append("ParentId",getParentId())
			.append("RoomAreaId",getRoomAreaId())
			.append("Scope",getScope())
			.append("Uplace",getUplace())
			.append("Uid",getUid())
			.append("Addtime",getAddtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JbAssets == false) return false;
		if(this == obj) return true;
		JbAssets other = (JbAssets)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

