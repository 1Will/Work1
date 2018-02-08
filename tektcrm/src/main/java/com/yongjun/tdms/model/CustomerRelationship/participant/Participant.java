package com.yongjun.tdms.model.CustomerRelationship.participant;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;

public class Participant extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContactArchives contactArchives;
	private PersonnelFiles personnelFiles;
	private BackVisit backVisit;
	private ProjectInfoPlan projectInfoPlan;
	private String duty;
	private String explain;

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public ContactArchives getContactArchives() {
		return contactArchives;
	}

	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public BackVisit getBackVisit() {
		return backVisit;
	}

	public void setBackVisit(BackVisit backVisit) {
		this.backVisit = backVisit;
	}

	public ProjectInfoPlan getProjectInfoPlan() {
		return projectInfoPlan;
	}

	public void setProjectInfoPlan(ProjectInfoPlan projectInfoPlan) {
		this.projectInfoPlan = projectInfoPlan;
	}
	
}
