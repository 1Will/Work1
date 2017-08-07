package com.yongjun.tdms.model.expensemanagement.expenseForm;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ExpenseForm extends BaseInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private ProjectInfo projectInfo;
	private PersonnelFiles applyPeople;
	private ContractManagement contractManagement;
	private Double money = Double.valueOf(0.0D);// 报销金额
	private Date applyDate;
	private String remark;

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public PersonnelFiles getApplyPeople() {
		return applyPeople;
	}

	public void setApplyPeople(PersonnelFiles applyPeople) {
		this.applyPeople = applyPeople;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
