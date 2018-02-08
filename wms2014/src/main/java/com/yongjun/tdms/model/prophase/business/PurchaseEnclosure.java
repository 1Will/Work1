package com.yongjun.tdms.model.prophase.business;

import com.yongjun.tdms.model.BaseInfoEntity;
@SuppressWarnings("serial")
public class PurchaseEnclosure extends  BaseInfoEntity{
    //项目号
	private String projectNo;
	//附件名称
	private String enclosureName;
	//附件描述
	private String enclosureDescribe;
	//附件备注
    private String  memo;
    //传输文件
    private String transportFile;
   //采购单付件明细所关联的采购单
	private PurchaseBill purchaseBill;	
    	 

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getEnclosureName() {
		return enclosureName;
	}

	public void setEnclosureName(String enclosureName) {
		this.enclosureName = enclosureName;
	}

	public String getEnclosureDescribe() {
		return enclosureDescribe;
	}

	public void setEnclosureDescribe(String enclosureDescribe) {
		this.enclosureDescribe = enclosureDescribe;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTransportFile() {
		return transportFile;
	}

	public void setTransportFile(String transportFile) {
		this.transportFile = transportFile;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

}
