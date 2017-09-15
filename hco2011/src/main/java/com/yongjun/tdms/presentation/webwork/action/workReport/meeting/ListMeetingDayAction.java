package com.yongjun.tdms.presentation.webwork.action.workReport.meeting;


import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

public class ListMeetingDayAction extends ValueListAction{
	private static final long serialVersionUID = 1L;


	@Override
	protected String getAdapterName() {
		return "meetingDayHQL";
	}
	
	
}
