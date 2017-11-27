package com.yongjun.tdms.model.workspace.data;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class Data extends BaseInfoEntity {
	private static final long serialVersionUID = 8908956162303433991L;
	private String year;// 年
	private String month;// 月
	private long contractManagementNum;// 合同数量
	private double contractManagementMoney = Double.valueOf(0.0D);;// 合同金额
	private long financialManagementNum;// 收款数量
	private double financialManagementMoney = Double.valueOf(0.0D);;// 收款金额
	private long billingRecordNum;// 开票数量
	private double billingRecordMoney = Double.valueOf(0.0D);;// 开票金额
	private long shouldDaily;// 日报应写次数
	private long actualDaily;// 日报实写次数
	private long shouldWeekly;// 周报应写次数
	private long actualWeekly;// 周报实写次数
	private long Monthly;// 月报次数0未写1已写
	private long projectNum;// 项目数
	private long backvisitNum;// 回访次数
	private PersonnelFiles personnelFiles; // 发生人

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public long getContractManagementNum() {
		return contractManagementNum;
	}

	public void setContractManagementNum(long contractManagementNum) {
		this.contractManagementNum = contractManagementNum;
	}

	public double getContractManagementMoney() {
		return contractManagementMoney;
	}

	public void setContractManagementMoney(double contractManagementMoney) {
		this.contractManagementMoney = contractManagementMoney;
	}

	public long getFinancialManagementNum() {
		return financialManagementNum;
	}

	public void setFinancialManagementNum(long financialManagementNum) {
		this.financialManagementNum = financialManagementNum;
	}

	public double getFinancialManagementMoney() {
		return financialManagementMoney;
	}

	public void setFinancialManagementMoney(double financialManagementMoney) {
		this.financialManagementMoney = financialManagementMoney;
	}

	public long getBillingRecordNum() {
		return billingRecordNum;
	}

	public void setBillingRecordNum(long billingRecordNum) {
		this.billingRecordNum = billingRecordNum;
	}

	public double getBillingRecordMoney() {
		return billingRecordMoney;
	}

	public void setBillingRecordMoney(double billingRecordMoney) {
		this.billingRecordMoney = billingRecordMoney;
	}

	public long getMonthly() {
		return Monthly;
	}

	public void setMonthly(long monthly) {
		Monthly = monthly;
	}

	public long getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(long projectNum) {
		this.projectNum = projectNum;
	}

	public long getBackvisitNum() {
		return backvisitNum;
	}

	public void setBackvisitNum(long backvisitNum) {
		this.backvisitNum = backvisitNum;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public long getShouldDaily() {
		return shouldDaily;
	}

	public void setShouldDaily(long shouldDaily) {
		this.shouldDaily = shouldDaily;
	}

	public long getActualDaily() {
		return actualDaily;
	}

	public void setActualDaily(long actualDaily) {
		this.actualDaily = actualDaily;
	}

	public long getShouldWeekly() {
		return shouldWeekly;
	}

	public void setShouldWeekly(long shouldWeekly) {
		this.shouldWeekly = shouldWeekly;
	}

	public long getActualWeekly() {
		return actualWeekly;
	}

	public void setActualWeekly(long actualWeekly) {
		this.actualWeekly = actualWeekly;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}
}
