package com.yongjun.tdms.model.expensemanagement.expenseForm;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workflow.Flow;

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
	private Integer formNum;//单据数量
	private Integer attachmentNum;//附件数量
	private String isSaved ;//提交判断
	private Flow flow;
	private CodeValue status;
	

	public Integer getFormNum() {
		return formNum;
	}

	public void setFormNum(Integer formNum) {
		this.formNum = formNum;
	}

	public Integer getAttachmentNum() {
		return attachmentNum;
	}

	public void setAttachmentNum(Integer attachmentNum) {
		this.attachmentNum = attachmentNum;
	}

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

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		 sb.append("部门为【"+this.getApplyPeople().getDept().getName()+"】的【"+this.getApplyPeople().getName()+"】在时间为【"+DateUtil.getDate(this.getCreatedTime(), "yyyy年MM月dd日")+"】提出了类型为【"+this.getFlow().getName())
		 .append("】的流程，")
		 .append("具体内容是：【因项目："+this.getProjectInfo().getName()+"需要，特申请报销，金额为"+this.getMoney())
		 .append("元】备注：【"+this.getRemark()+"】");
		return sb.toString();
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public CodeValue getStatus() {
		return status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}
	
	

}
