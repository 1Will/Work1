package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.participant;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.participant.Participant;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.participant.ParticipantManager;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

public class EditParticipantAction extends PrepareAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ParticipantManager participantManager;
	private final ContactArchivesManager contactArchivesManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final BackVisitManager backVisitManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private final UserManager userManager;
	
	private Participant participant;
	private ContactArchives contactArchives;
	private PersonnelFiles personnelFiles;
	private BackVisit backVisit;
	private ProjectInfoPlan projectInfoPlan;
	private String caFlag;
	private String title;

	
	
	public EditParticipantAction(ParticipantManager participantManager,ContactArchivesManager contactArchivesManager,
			PersonnelFilesManager personnelFilesManager,BackVisitManager backVisitManager,ProjectInfoPlanManager projectInfoPlanManager,
			UserManager userManager) {
		this.participantManager = participantManager;
		this.contactArchivesManager = contactArchivesManager;
		this.personnelFilesManager = personnelFilesManager;
		this.backVisitManager = backVisitManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
		this.userManager = userManager;
	}


	public void prepare() throws Exception {
		if(hasId("participant.id")){
			this.participant = participantManager.loadParticipant(getId("participant.id"));
		}else {
			this.participant =new Participant();
		}
		
		
		if(hasId("contactArchives.id")){
			this.contactArchives = contactArchivesManager.loadContactArchives(getId("contactArchives.id"));
		}
		
		if(hasId("personnelFiles.id")){
			this.personnelFiles = personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
		}
		
		if(hasId("backVisit.id")){
			this.backVisit = backVisitManager.loadBackVisit(getId("backVisit.id"));
			if(request.getParameter("caFlag")!=null&&!"".equals(request.getParameter("caFlag"))){
				caFlag=request.getParameter("caFlag");
				title="联系人陪同者";
			}else {
				title="回访人陪同者";
			}
		}
		
		if(hasId("projectInfoPlan.id")){
			this.projectInfoPlan = projectInfoPlanManager.loadProjectInfoPlan(getId("projectInfoPlan.id"));
			title="参与者";
		}
		this.participant.setCreator(userManager.getUser().getName());
	}

	
	public String save() {
		boolean isNew = this.participant.isNew();
		if(hasId("contactArchives.id")){
			this.participant.setContactArchives(this.contactArchives);
		}
		
		if(hasId("personnelFiles.id")){
			this.participant.setPersonnelFiles(this.personnelFiles);
		}
		
		if(hasId("backVisit.id")){
			this.participant.setBackVisit(this.backVisit);
		}
		
		if(hasId("projectInfoPlan.id")){
			this.participant.setProjectInfoPlan(this.projectInfoPlan);
		}
		
		try {
			participantManager.storeParticipant(this.participant);
		} catch (Exception e) {
			e.printStackTrace();
			if(isNew){
				addActionMessage(title+getText("participant.add.error"));
				return ERROR;
			}
			addActionMessage(title+getText("participant.edit.error"));
			return ERROR;
		}
		if(isNew){
			addActionMessage(title+getText("participant.add.success"));
			return SUCCESS;
		}
		
		addActionMessage(title+getText("participant.edit.success"));
		return SUCCESS;
	}


	public ContactArchives getContactArchives() {
		return contactArchives;
	}


	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}


	public Participant getParticipant() {
		return participant;
	}


	public void setParticipant(Participant participant) {
		this.participant = participant;
	}


	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}


	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}


	public String getCaFlag() {
		return caFlag;
	}


	public void setCaFlag(String caFlag) {
		this.caFlag = caFlag;
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
}
