package jb.pageModel;

import java.io.Serializable;

public class UserGroup  implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nGroupNo;
	private String sGroupName;
	private int bCanConfirmEvent;
	private int bCanEditPrjProp;
	private int bCanEditSysProp;
	private int bCanViewDvr;
	public int getNGroupNo() {
		return nGroupNo;
	}
	public void setNGroupNo(int nGroupNo) {
		this.nGroupNo = nGroupNo;
	}
	public String getSGroupName() {
		return sGroupName;
	}
	public void setSGroupName(String sGroupName) {
		this.sGroupName = sGroupName;
	}
	public int getBCanConfirmEvent() {
		return bCanConfirmEvent;
	}
	public void setBCanConfirmEvent(int bCanConfirmEvent) {
		this.bCanConfirmEvent = bCanConfirmEvent;
	}
	public int getBCanEditPrjProp() {
		return bCanEditPrjProp;
	}
	public void setBCanEditPrjProp(int bCanEditPrjProp) {
		this.bCanEditPrjProp = bCanEditPrjProp;
	}
	public int getBCanEditSysProp() {
		return bCanEditSysProp;
	}
	public void setBCanEditSysProp(int bCanEditSysProp) {
		this.bCanEditSysProp = bCanEditSysProp;
	}
	public int getBCanViewDvr() {
		return bCanViewDvr;
	}
	public void setBCanViewDvr(int bCanViewDvr) {
		this.bCanViewDvr = bCanViewDvr;
	}
	
}
