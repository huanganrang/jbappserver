
/*
 * @author John
 * @date - 2015-01-20
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Id;

/*@SuppressWarnings("serial")
@Entity
@Table(name = "appuser")
@DynamicInsert(true)
@DynamicUpdate(true)*/
public class Tappuser implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Appuser";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_GROUP = "用户组";
	public static final String ALIAS_FROM_IP = "IP地址";
	public static final String ALIAS_ID = "主键";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String username;
	//@Length(max=36)
	private java.lang.String group;
	//@Length(max=36)
	private java.lang.String fromIp;
	//@Length(max=72)
	private java.lang.String id;
	//columns END


		public Tappuser(){
		}
		public Tappuser(String id) {
			this.id = id;
		}
	

	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 72)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "username", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
	@Column(name = "group", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGroup() {
		return this.group;
	}
	
	public void setGroup(java.lang.String group) {
		this.group = group;
	}
	
	@Column(name = "from_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getFromIp() {
		return this.fromIp;
	}
	
	public void setFromIp(java.lang.String fromIp) {
		this.fromIp = fromIp;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Username",getUsername())
			.append("Group",getGroup())
			.append("FromIp",getFromIp())
			.append("Id",getId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Appuser == false) return false;
		if(this == obj) return true;
		Appuser other = (Appuser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

