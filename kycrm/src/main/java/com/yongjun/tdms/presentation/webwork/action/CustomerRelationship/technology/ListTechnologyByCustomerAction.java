package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.technology;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.technology.Technology;
import com.yongjun.tdms.service.CustomerRelationship.technology.TechnologyManager;

public class ListTechnologyByCustomerAction extends ValueListAction {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private TechnologyManager technologyManager;
 private List<Technology>technologys;
 private Long customerId;
 
	public ListTechnologyByCustomerAction(TechnologyManager technologyManager) {
	this.technologyManager = technologyManager;
}
	public void prepare() throws Exception {
		technologys=null;
		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
		}
		if(hasId("technologyIds")){
			technologys=technologyManager.loadAllTechnology(getIds("technologyIds"));
    	}
	}
	@Override
	public String execute() throws Exception {
		if (isDelete()) {
			 return delete();
		}
		return SUCCESS;
	}
	private String delete(){
		try {
			if(technologys!=null){
				this.technologyManager.deleteAllTechnology(technologys);
				addActionMessage(getText("delete.success"));
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("delete.error"));
			return ERROR;
		}
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	@Override
	protected String getAdapterName() {
		return "technologyList";
	}
	

}
