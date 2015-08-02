
/*
 * @author John
 * @date - 2015-01-21
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "group_uid")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TgroupUid implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "GroupUid";
	public static final String ALIAS_NGROUP_NO = "ngroupNo";
	public static final String ALIAS_UID = "uid";
	public static final String ALIAS_SEQ = "seq";
	public static final String ALIAS_ID = "id";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@NotNull 
	private java.lang.Integer ngroupNo;
	//@NotBlank @Length(max=36)
	private java.lang.String uid;
	//
	private java.lang.Integer seq;
	//@Length(max=36)
	private java.lang.String id;
	
	private Integer utype;
	//columns END


		public TgroupUid(){
		}
		public TgroupUid(String id) {
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
	
	@Column(name = "nGroupNo", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNgroupNo() {
		return this.ngroupNo;
	}
	
	public void setNgroupNo(java.lang.Integer ngroupNo) {
		this.ngroupNo = ngroupNo;
	}
	
	@Column(name = "utype", unique = false, nullable = false, insertable = true, updatable = true, length = 2)
	public java.lang.Integer getUtype() {
		return this.utype;
	}
	
	@Column(name = "uid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getUid() {
		return this.uid;
	}
	
	public void setUid(java.lang.String uid) {
		this.uid = uid;
	}
	
	@Column(name = "seq", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSeq() {
		return this.seq;
	}
	
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}
	public void setUtype(Integer utype) {
		this.utype = utype;
	}
	
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("NgroupNo",getNgroupNo())
			.append("Uid",getUid())
			.append("Seq",getSeq())
			.append("Id",getId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GroupUid == false) return false;
		if(this == obj) return true;
		GroupUid other = (GroupUid)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

