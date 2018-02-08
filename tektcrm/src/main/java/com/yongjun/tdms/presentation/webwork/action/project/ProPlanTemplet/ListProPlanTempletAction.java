package com.yongjun.tdms.presentation.webwork.action.project.ProPlanTemplet;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.projectInfoPlan.ProPlanTemplet;
import com.yongjun.tdms.service.project.projectInfoPlan.ProPlanTempletManager;

public class ListProPlanTempletAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
    private final ProPlanTempletManager proPlanTempletManager;
    private List<ProPlanTemplet> proPlanTemplets;
    private String backCheckBox;
	public ListProPlanTempletAction(ProPlanTempletManager proPlanTempletManager) {
		this.proPlanTempletManager = proPlanTempletManager;
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(request.getParameter("backCheckBox")=="backCheckBox"){
			this.backCheckBox=request.getParameter("backCheckBox");
		}
		return map;
	}

	public void prepare() throws Exception {
		if (hasIds("proPlanTempletIds")) {
			getIds("proPlanTempletIds");
			this.proPlanTemplets=this.proPlanTempletManager.loadAllProPlanTemplet(getIds("proPlanTempletIds"));
		}
		 if(this.request.getParameter("backCheckBox")!=null){
       	  this.backCheckBox =this.request.getParameter("backCheckBox");
         }
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	private String delete() {
		try {
			this.proPlanTempletManager.deleteAllProPlanTemplet(proPlanTemplets);
			addActionMessage(getText("proPlanTemplet.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("proPlanTemplet.delete.failer"));
		}
		return "error";
	}
    
	@Override
	protected String getAdapterName() {
		return "proPlanTempletHOL";
	}

	public List<ProPlanTemplet> getProPlanTemplets() {
		return proPlanTemplets;
	}

	public void setProPlanTemplets(List<ProPlanTemplet> proPlanTemplets) {
		this.proPlanTemplets = proPlanTemplets;
	}

	public String getBackCheckBox() {
		return backCheckBox;
	}

	public void setBackCheckBox(String backCheckBox) {
		this.backCheckBox = backCheckBox;
	}

	public ProPlanTempletManager getProPlanTempletManager() {
		return proPlanTempletManager;
	}

}
