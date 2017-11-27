package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.newStandard;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.newStandard.NewStandard;
import com.yongjun.tdms.model.CustomerRelationship.technology.Technology;
import com.yongjun.tdms.service.CustomerRelationship.newStandard.NewStandardManager;
import com.yongjun.tdms.service.CustomerRelationship.technology.TechnologyManager;

public class ListNewStandardByCustomerAction extends ValueListAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewStandardManager newStandardManager;
	 private List<NewStandard>newStandards;
	 private Long customerInfoId;
	 
		public ListNewStandardByCustomerAction(NewStandardManager newStandardManager) {
		this.newStandardManager = newStandardManager;
	}
		public void prepare() throws Exception {
			newStandards=null;
			if (hasId("customerInfo.id")) {
				this.customerInfoId = getId("customerInfo.id");
			}
			if(hasId("newStandardIds")){
				newStandards=newStandardManager.loadAllNewStandard(getIds("newStandardIds"));
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
				if(newStandards!=null){
					this.newStandardManager.deleteAllNewStandard(newStandards);
					addActionMessage(getText("delete.success"));
				}
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				addActionMessage(getText("delete.error"));
				return ERROR;
			}
		}
		public Long getCustomerInfoId() {
			return customerInfoId;
		}
		public void setCustomerInfoId(Long customerInfoId) {
			this.customerInfoId = customerInfoId;
		}
	@Override
	protected String getAdapterName() {
		return "newStandardHQL";
	}

}
