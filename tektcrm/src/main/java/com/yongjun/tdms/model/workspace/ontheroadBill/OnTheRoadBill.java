package com.yongjun.tdms.model.workspace.ontheroadBill;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workflow.Flow;

import java.util.Date;

public class OnTheRoadBill extends BaseInfoEntity {
	private static final long serialVersionUID = 8908956162303433991L;
	private String code;
	private Date createDate;
	private PersonnelFiles applyPerson;
	private Department dept;
	private Date startTime;
	private Date endTime;
	private CodeValue status;
	private String betreffzeile;
	private Double manDay = Double.valueOf(0.0D);//出差天数
	private String failReason;
	private ProjectInfo projectInfo;
	private ContractManagement contractManagement;
	private String isSaved;// 提交判断
	private Flow flow;//所属流程
	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public PersonnelFiles getApplyPerson() {
		return this.applyPerson;
	}

	public void setApplyPerson(PersonnelFiles applyPerson) {
		this.applyPerson = applyPerson;
	}

	public String getBetreffzeile() {
		return this.betreffzeile;
	}

	public void setBetreffzeile(String betreffzeile) {
		this.betreffzeile = betreffzeile;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Department getDept() {
		return this.dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public CodeValue getStatus() {
		return this.status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}


	public Double getManDay() {
		return manDay;
	}

	public void setManDay(Double manDay) {
		this.manDay = manDay;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		 sb.append("部门为【"+this.getApplyPerson().getDept().getName()+"】的【"+this.getApplyPerson().getName()+"】在时间为【"+DateUtil.getDate(this.getCreateDate(), "yyyy年MM月dd日")+"】提出了类型为【"+this.getFlow().getName())
		 .append("】的流程，")
		 .append("具体内容是：【从"+DateUtil.getDate(this.getStartTime(), "yyyy-MM-dd HH:mm:ss")+"到"+DateUtil.getDate(this.getEndTime(), "yyyy-MM-dd HH:mm:ss"))
		 .append("期间内出差,共计时(天数)："+this.getManDay())
		 .append("】出差原因为：【"+this.getBetreffzeile()+"】");
		return sb.toString();
	}
}
