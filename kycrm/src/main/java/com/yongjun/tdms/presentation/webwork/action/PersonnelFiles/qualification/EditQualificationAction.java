package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.qualification;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.personnelFiles.qualification.Qualification;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.personnelFiles.qualification.QualificationManager;

public class EditQualificationAction extends PrepareAction {
	private static final long serialVersionUID = -6543880456612721423L;
	private final QualificationManager qualificationManager;
	private final PersonnelFilesManager personnelFilesManager;
	private PersonnelFiles personnelFiles;
	private Qualification qualification;

	public EditQualificationAction(QualificationManager qualificationManager,
			PersonnelFilesManager personnelFilesManager) {
		this.qualificationManager = qualificationManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public void prepare() throws Exception {
		if (hasId("qualification.id")) {
			this.qualification = this.qualificationManager.loadQualification(getId("qualification.id"));
		}else {
			this.qualification = new Qualification();
		}

		if (hasId("personnelFile.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
		}
	}

	public String save() {
		boolean isNew = this.qualification.isNew();
		if(isNew){
			this.qualification.setPersonnelFiles(this.personnelFiles);
		}
		try {
			this.qualificationManager.storeQualification(this.qualification);
			if (isNew) {
				addActionMessage(getText("qualification.add.success"));
				return SUCCESS;
			} else {
				addActionMessage(getText("qualification.edit.success"));
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("qualification.save.error"));
		}
		return ERROR;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
}
