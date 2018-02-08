package com.yongjun.tdms.model.prophase.business;

import com.yongjun.tdms.model.BaseInfoEntity;

public class SparePart extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	//备件名称
    private String spareName;
    //备件型号
    private String spareModel;
    //备件规格
    private String spareSpecification;
    //备件数量
    private Integer amount;
    //易损[是][否]
    private boolean damage;
    //安装部位
    private String  installPosition;
    //备注
    private String memo;
    //备品备件所关联的验收单
    private AcceptBill accept;
	@Override
	public int hashCode() {
		
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		
		return false;
	}

	public String getSpareName() {
		return spareName;
	}

	public void setSpareName(String spareName) {
		this.spareName = spareName;
	}

	public String getSpareModel() {
		return spareModel;
	}

	public void setSpareModel(String spareModel) {
		this.spareModel = spareModel;
	}

	public String getSpareSpecification() {
		return spareSpecification;
	}

	public void setSpareSpecification(String spareSpecification) {
		this.spareSpecification = spareSpecification;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public boolean isDamage() {
		return damage;
	}

	public void setDamage(boolean damage) {
		this.damage = damage;
	}

	public AcceptBill getAccept() {
		return accept;
	}

	public void setAccept(AcceptBill accept) {
		this.accept = accept;
	}

	public String getInstallPosition() {
		return installPosition;
	}

	public void setInstallPosition(String installPosition) {
		this.installPosition = installPosition;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
