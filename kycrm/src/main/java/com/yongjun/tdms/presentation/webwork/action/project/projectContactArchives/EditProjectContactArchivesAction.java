package com.yongjun.tdms.presentation.webwork.action.project.projectContactArchives;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectContactArchives.ProjectContactArchives;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectContactArchives.ProjectContactArchivesManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditProjectContactArchivesAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ProjectContactArchivesManager projectContactArchivesManager;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoManager projectInfoManager;
	private final UserManager userManager;
	private final ContactArchivesManager contactArchivesManager;

	private ProjectContactArchives projectContactArchives;
	private ProjectInfo projectInfo;

	public EditProjectContactArchivesAction(ProjectContactArchivesManager projectContactArchivesManager,
			CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager, UserManager userManager,
			ContactArchivesManager contactArchivesManager) {
		this.projectContactArchivesManager = projectContactArchivesManager;
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.userManager = userManager;
		this.contactArchivesManager = contactArchivesManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfo = projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}

		if (hasId("projectContactArchives.id")) {
			this.projectContactArchives = this.projectContactArchivesManager
					.loadProjectContactArchives(getId("projectContactArchives.id"));
		} else {
			this.projectContactArchives = new ProjectContactArchives();
		}
	}

	public String save() {
		boolean isNew = this.projectContactArchives.isNew();

		if (hasId("businessType.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
			this.projectContactArchives.setBusinessType(cv);
		}
		if (hasId("contactArchives.id")) {
			this.projectContactArchives.setContactArchives(contactArchivesManager
					.loadContactArchives(getId("contactArchives.id")));
		}
		this.projectContactArchives.setProjectInfo(this.projectInfo);
		User u = this.userManager.getUser();
		if (isNew) {
			this.projectContactArchives.setCreator(u.getName());
			this.projectContactArchives.setLastOperator(u.getName());
		} else {
			this.projectContactArchives.setLastOperator(u.getName());
		}
		try {
			this.projectContactArchivesManager.storeProjectContactArchives(projectContactArchives);
		} catch (Exception e) {
			e.printStackTrace();
			if(isNew){
				addActionMessage(getText("projectCon.add.error"));
			}else {
				addActionMessage(getText("projectCon.edit.error"));
			}
			return ERROR;
		}

		if (isNew) {
			addActionMessage(getText("projectCon.add.success"));
			return SUCCESS;
		}
		addActionMessage(getText("projectCon.edit.success"));
		return SUCCESS;
	}

	public List<CodeValue> getAllBusinessType() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("202"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public ProjectContactArchives getProjectContactArchives() {
		return projectContactArchives;
	}

	public void setProjectContactArchives(ProjectContactArchives projectContactArchives) {
		this.projectContactArchives = projectContactArchives;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

}
