package com.yongjun.tdms.model.project.projectContactArchives;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.project.ProjectInfo;

public class ProjectContactArchives extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ProjectInfo projectInfo;// 项目
	private ContactArchives contactArchives;// 合作客户成员
	private String outline;// 主要说明
	private CodeValue businessType;// 业务属性
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	public ContactArchives getContactArchives() {
		return contactArchives;
	}
	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
	public CodeValue getBusinessType() {
		return businessType;
	}
	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	}

}
