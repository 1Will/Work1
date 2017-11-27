package com.yongjun.tdms.model.customercontract.plan;

import java.util.Date;
import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class ReturnPlan extends BaseInfoEntity {
	private static final long serialVersionUID = -390769290028684397L;
	private PersonnelFiles payee;
	private CustomerInfo customerInfo;
	private ContactArchives contactArchives;
	private String phone;
	private Date planDate;
	private ContractManagement contractManagement;
	private Date paytime;
	private CodeValue payment;
	private CodeValue batch;
	private Double sum = Double.valueOf(0.0D);//应收金额
	private Double factSum = Double.valueOf(0.0D);//收款金额
	private String currency;
	private String isOrNot;//收款单是否完成
	private String notOrIs;
	private String billingOrNot;//是否有发票
	private String isBill;//是否开票
	private String remark;
	private PersonnelFiles chargMan;//负责人
	private int percentt;// 百分比
	private CodeValue planState;// 计划状态
	private Date billDate;//开票时间
	private Double billMoney= Double.valueOf(0.0D);//开票金额
    private String  house;//房间
	private CodeValue mold;//收款计划类型
	public String getBillingOrNot() {
		return this.billingOrNot;
	}

	public void setBillingOrNot(String billingOrNot) {
		this.billingOrNot = billingOrNot;
	}

	public Double getFactSum() {
		return this.factSum;
	}

	public void setFactSum(Double factSum) {
		this.factSum = factSum;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getPlanDate() {
		return this.planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public CodeValue getBatch() {
		return this.batch;
	}

	public ContactArchives getContactArchives() {
		return this.contactArchives;
	}

	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}

	public ContractManagement getContractManagement() {
		return this.contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getIsOrNot() {
		return this.isOrNot;
	}

	public void setIsOrNot(String isOrNot) {
		this.isOrNot = isOrNot;
	}

	public String getNotOrIs() {
		return this.notOrIs;
	}

	public void setNotOrIs(String notOrIs) {
		this.notOrIs = notOrIs;
	}

	public PersonnelFiles getPayee() {
		return this.payee;
	}

	public void setPayee(PersonnelFiles payee) {
		this.payee = payee;
	}

	public CodeValue getPayment() {
		return this.payment;
	}

	public void setPayment(CodeValue payment) {
		this.payment = payment;
	}

	public Date getPaytime() {
		return this.paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public Double getSum() {
		return this.sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public void setBatch(CodeValue batch) {
		this.batch = batch;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PersonnelFiles getChargMan() {
		return chargMan;
	}

	public void setChargMan(PersonnelFiles chargMan) {
		this.chargMan = chargMan;
	}

	public int getPercentt() {
		return percentt;
	}

	public void setPercentt(int percentt) {
		this.percentt = percentt;
	}

	public CodeValue getPlanState() {
		return planState;
	}

	public void setPlanState(CodeValue planState) {
		this.planState = planState;
	}

	public String getIsBill() {
		return isBill;
	}

	public void setIsBill(String isBill) {
		this.isBill = isBill;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(Double billMoney) {
		this.billMoney = billMoney;
	}


	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public CodeValue getMold() {
		return mold;
	}

	public void setMold(CodeValue mold) {
		this.mold = mold;
	}
	

}
