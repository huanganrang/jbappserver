package jb.pageModel;

import jb.absx.F;

public class Appuser implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String username;	
	private java.lang.String group;	
	private java.lang.String fromIp;	
	private java.lang.String id;	

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		if(F.empty(this.id))
			this.id = this.username+"|"+this.group;
		return this.id;
	}

	
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setGroup(java.lang.String group) {
		this.group = group;
	}
	
	public java.lang.String getGroup() {
		return this.group;
	}
	public void setFromIp(java.lang.String fromIp) {
		this.fromIp = fromIp;
	}
	
	public java.lang.String getFromIp() {
		return this.fromIp;
	}

}
