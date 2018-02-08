package com.yongjun.tdms.presentation.webwork.action.base.workType;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

public class ListWorkTypeAction extends ValueListAction {

	private static final long serialVersionUID = 2538193557179949410L;
	
	@Override
	protected String getAdapterName() {
		return "workTypes";
	}

}
