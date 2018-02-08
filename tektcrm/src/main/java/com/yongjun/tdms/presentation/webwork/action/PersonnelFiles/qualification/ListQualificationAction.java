package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.qualification;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.personnelFiles.qualification.Qualification;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.personnelFiles.qualification.QualificationManager;

@SuppressWarnings("rawtypes")
public class ListQualificationAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final QualificationManager qualificationManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final InstitutionManager institutionManager;
	private final UserManager userManager;
	private final GroupManager groupManager;
	private PersonnelFiles personnelFiles;
	private Institution institution;
	private List<Qualification> qualificationList;

	public ListQualificationAction(QualificationManager qualificationManager,PersonnelFilesManager personnelFilesManager,
			InstitutionManager institutionManager,UserManager userManager,GroupManager groupManager) {
		this.personnelFilesManager = personnelFilesManager;
		this.qualificationManager = qualificationManager;
		this.institutionManager = institutionManager;
		this.userManager = userManager;
		this.groupManager = groupManager;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return SUCCESS;
	}

	public void prepare() throws Exception {
		if (hasIds("qualificationIds")) {
			this.qualificationList = this.qualificationManager.loadAllQualification(getIds("qualificationIds"));
		}
		
		if (hasId("personnelFiles.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
		}
		
		if (hasId("institution.id")) {
			this.institution = this.institutionManager.loadInstitution(getId("institution.id"));
		}
	}

	public String delete() {
		try {
			this.qualificationManager.deleteAllQualification(this.qualificationList);
			addActionMessage(getText("qualification.delete.success"));
		} catch (Exception e) {
			addActionMessage(getText("qualification.delete.error"));
		}
		return SUCCESS;
	}

	protected String getAdapterName() {
		return "qualificationHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		Set<User> noticePers = groupManager.getGroupByGroupName("资格证书管理组").getUsers();
		if (noticePers!= null && !noticePers.contains(userManager.getUser())) {
			map.put("dept.id", userManager.getUser().getDepartment());
		}
		return map;
	}

	public List<Qualification> getQualificationList() {
		return qualificationList;
	}

	public void setQualificationList(List<Qualification> qualificationList) {
		this.qualificationList = qualificationList;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}
