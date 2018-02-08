package com.yongjun.tdms.presentation.webwork.action.demo;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.demo.DemoManager;

public class DemoListAction extends ValueListAction {
	private static final long serialVersionUID = 7917678546181985226L;
	
	private List users;
	
	private boolean invalid;
	
	private DemoManager demoManager;
	
	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}
	
	public boolean getInvalid() {
		return this.invalid;
	}
	
	public void setUsers(List list) {
		this.users = list;
	}
	
	public List getUsers() {
		return this.users;
	}
	
	public DemoListAction(DemoManager demoManager) {
		this.demoManager = demoManager;
	}
	
	public void prepare() throws Exception {
		if (this.users == null && this.hasIds("userIds")) {
			this.users = this.demoManager.loadAllDemoes(this.getIds("demoIds"));
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Override
	protected String getAdapterName() {
		return "users";
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
	  	  Map map = super.getRequestParameterMap();
	  	  if (invalid) {
	  		  map.put("includeInvalid", "placeholder");
	  	  }
	  	  return map;
	    }

}
