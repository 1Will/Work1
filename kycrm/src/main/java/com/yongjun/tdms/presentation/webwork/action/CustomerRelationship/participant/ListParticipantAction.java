package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.participant;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.participant.Participant;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.CustomerRelationship.participant.ParticipantManager;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

@SuppressWarnings("rawtypes")
public class ListParticipantAction extends ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ParticipantManager participantManager;
	private final BackVisitManager backVisitManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	
	private BackVisit backVisit;
	private ProjectInfoPlan projectInfoPlan;
	private String caFlag;
	private String pfFlag;
	private String title;
	List<Participant> participants;

	public ListParticipantAction(ParticipantManager participantManager,BackVisitManager backVisitManager,ProjectInfoPlanManager projectInfoPlanManager) {
		this.participantManager = participantManager;
		this.backVisitManager = backVisitManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
	}
	
	@Override
	protected String getAdapterName() {
		return "participantHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}
	
	public void prepare() throws Exception {
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
	}
	
	
	
	public String execute() throws Exception {
		if(isDelete()){
			delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		this.participants = participantManager.loadAllParticipant(getIds("participantIds"));
		try {
			if(this.participants!=null && this.participants.size()>0){
				participantManager.deleteAllParticipant(this.participants);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String getCaFlag() {
		return caFlag;
	}

	public void setCaFlag(String caFlag) {
		this.caFlag = caFlag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPfFlag() {
		return pfFlag;
	}

	public void setPfFlag(String pfFlag) {
		this.pfFlag = pfFlag;
	}
}
