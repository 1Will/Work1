package com.yongjun.tdms.presentation.webwork.action.project.ProPlanTemplet;


import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.project.projectInfoPlan.ProPlanTemplet;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.project.projectInfoPlan.ProPlanTempletManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

public class EditProPlanTempletAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private final ProPlanTempletManager proPlanTempletManager;
    private ProPlanTemplet proPlanTemplet;
    private ProjectInfoPlan projectInfoPlan;
    private String backCheckBox;
	public EditProPlanTempletAction(ProjectInfoPlanManager projectInfoPlanManager,ProPlanTempletManager proPlanTempletManager) {
		this.projectInfoPlanManager = projectInfoPlanManager;
		this.proPlanTempletManager=proPlanTempletManager;
	}

	public void prepare() throws Exception {
		if (hasId("proPlanTemplet.id")) {
			this.proPlanTemplet = this.proPlanTempletManager.loadProPlanTemplet(getId("proPlanTemplet.id"));
		}else {
			this.proPlanTemplet=new ProPlanTemplet();
		}
		if(request.getParameter("backCheckBox")!=null&&request.getParameter("backCheckBox").length()>0){
			this.backCheckBox=backCheckBox;
		}
		boolean isNew = this.proPlanTemplet.isNew();
		if(hasId("projectInfoPlan.id") && isNew){
			this.projectInfoPlan=this.projectInfoPlanManager.loadProjectInfoPlan(getId("projectInfoPlan.id"));
			this.proPlanTemplet.setProplanName(projectInfoPlan.getName());
			this.proPlanTemplet.setPriority(projectInfoPlan.getPriority());
			this.proPlanTemplet.setOutline(projectInfoPlan.getOutline());
		}
	}

	public String save() {
		boolean isNew = this.proPlanTemplet.isNew();
		try{
			this.proPlanTempletManager.storeProPlanTemplet(proPlanTemplet);
			if (isNew) {
				addActionMessage(getText("proPlanTemplet.add.success",
						Arrays.asList(new Object[] { this.proPlanTemplet.getName()})));
				return "success";
			}else {
				addActionMessage(getText("proPlanTemplet.edit.success",
						Arrays.asList(new Object[] { this.proPlanTemplet.getName() })));
				return "success";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("proPlanTemplet.add.error",
						Arrays.asList(new Object[] { this.proPlanTemplet.getName() })));
				return "error";
			}else {
				addActionMessage(getText("proPlanTemplet.edit.error",
						Arrays.asList(new Object[] { this.proPlanTemplet.getName() })));
				return "error";
			}
		}
	}

	public ProPlanTemplet getProPlanTemplet() {
		return proPlanTemplet;
	}

	public void setProPlanTemplet(ProPlanTemplet proPlanTemplet) {
		this.proPlanTemplet = proPlanTemplet;
	}

	public ProjectInfoPlan getProjectInfoPlan() {
		return projectInfoPlan;
	}

	public void setProjectInfoPlan(ProjectInfoPlan projectInfoPlan) {
		this.projectInfoPlan = projectInfoPlan;
	}

	public String getBackCheckBox() {
		return backCheckBox;
	}

	public void setBackCheckBox(String backCheckBox) {
		this.backCheckBox = backCheckBox;
	}
		
	}
