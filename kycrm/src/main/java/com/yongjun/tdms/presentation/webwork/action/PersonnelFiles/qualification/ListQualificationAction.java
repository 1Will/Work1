package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.qualification;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.personnelFiles.qualification.Qualification;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.personnelFiles.qualification.QualificationManager;

@SuppressWarnings("rawtypes")
public class ListQualificationAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final QualificationManager qualificationManager;
	private final PersonnelFilesManager personnelFilesManager;
	private PersonnelFiles personnelFiles;
	private List<Qualification> qualificationList;

	public ListQualificationAction(QualificationManager qualificationManager,PersonnelFilesManager personnelFilesManager) {
		this.personnelFilesManager = personnelFilesManager;
		this.qualificationManager = qualificationManager;
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

}
