package com.yongjun.tdms.model.CustomerRelationship.newStandard;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
public class NewStandard extends BaseInfoEntity{

	private String name;//标准名称
	private CodeValue genre;//标准类型
	private CodeValue partakeGenre;//参与类型
	private long ranking;//排名
	private Date approveTime;//批准年度
	private String approveNumber;//批准文号
	private String approveUnit;//批准单位
	private CustomerInfo  customerInfo;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeValue getGenre() {
		return genre;
	}

	public void setGenre(CodeValue genre) {
		this.genre = genre;
	}

	public CodeValue getPartakeGenre() {
		return partakeGenre;
	}

	public void setPartakeGenre(CodeValue partakeGenre) {
		this.partakeGenre = partakeGenre;
	}

	public long getRanking() {
		return ranking;
	}

	public void setRanking(long ranking) {
		this.ranking = ranking;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproveNumber() {
		return approveNumber;
	}

	public void setApproveNumber(String approveNumber) {
		this.approveNumber = approveNumber;
	}

	public String getApproveUnit() {
		return approveUnit;
	}

	public void setApproveUnit(String approveUnit) {
		this.approveUnit = approveUnit;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

}
