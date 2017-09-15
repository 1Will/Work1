package com.yongjun.tdms.model.project.projectInfoPlan;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ProjectInfoPlan extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ProjectInfo projectInfo;// 项目
	private String name;// 任务名称
	private Date startDate;// 预计开始时间
	private Date endDate;// 预计结束时间
	private Date startFactDate;// 预计开始时间
	private Date endFactDate;// 预计结束时间
	private PersonnelFiles personnelFiles;// 责任人
	private String assist;// 协助者
	private String assistIds;// 协助者带id
	private String outline;// 主要说明
	private int percentt;// 百分比
	private int priority;// 优先级
	private CodeValue planState;// j计划状态
	private ContractManagement contractManagement;
	private String isSaved;// 提交判断

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProjectInfoPlan)) {
			return false;
		}

		ProjectInfoPlan contact = (ProjectInfoPlan) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
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

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public String getAssist() {
		return assist;
	}

	public void setAssist(String assist) {
		this.assist = assist;
	}

	public int getPercentt() {
		return percentt;
	}

	public void setPercentt(int percentt) {
		this.percentt = percentt;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public CodeValue getPlanState() {
		return planState;
	}

	public void setPlanState(CodeValue planState) {
		this.planState = planState;
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

	public Date getStartFactDate() {
		return startFactDate;
	}

	public void setStartFactDate(Date startFactDate) {
		this.startFactDate = startFactDate;
	}

	public Date getEndFactDate() {
		return endFactDate;
	}

	public void setEndFactDate(Date endFactDate) {
		this.endFactDate = endFactDate;
	}

	public String getAssistIds() {
		return assistIds;
	}

	public void setAssistIds(String assistIds) {
		this.assistIds = assistIds;
	}

}
