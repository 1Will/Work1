package com.yongjun.tdms.model.project.projectInfoPersonnels;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ProjectInfoPersonnels extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ProjectInfo projectInfo;// 项目
	private PersonnelFiles proPerson;// 项目成员
	private String outline;// 主要说明
	private CodeValue businessType;// 业务属性

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProjectInfoPersonnels)) {
			return false;
		}

		ProjectInfoPersonnels contact = (ProjectInfoPersonnels) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return 0;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public PersonnelFiles getProPerson() {
		return proPerson;
	}

	public void setProPerson(PersonnelFiles proPerson) {
		this.proPerson = proPerson;
	}

	public CodeValue getBusinessType() {
		return businessType;
	}

	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	}

}
