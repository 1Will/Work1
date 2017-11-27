package com.yongjun.tdms.presentation.webwork.action.workReport.meeting;


import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workReport.meeting.MeetingDay;
import com.yongjun.tdms.model.workReport.meeting.MeetingMonth;
import com.yongjun.tdms.service.workReport.meeting.MeetingDayManager;
import com.yongjun.tdms.service.workReport.meeting.MeetingMonthManager;

public class ListMeetingMonthAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
    private List<MeetingMonth> meetingMonths;
    private final MeetingMonthManager meetingMonthManager;
    private final MeetingDayManager meetingDayManager;
    
	public ListMeetingMonthAction(MeetingMonthManager meetingMonthManager, MeetingDayManager meetingDayManager) {
		super();
		this.meetingMonthManager = meetingMonthManager;
		this.meetingDayManager = meetingDayManager;
	}

	@Override
	protected String getAdapterName() {
		return "meetingMonthHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}
    
	@Override
	public String execute() throws Exception {
		if (isDelete()) {
			 return delete();
		}
		return SUCCESS;
	}
    @Override
	public void prepare() throws Exception {
    	if(hasId("meetingMonthIds")){
    		meetingMonths=meetingMonthManager.loadAllMeetingMonth(getIds("meetingMonthIds"));
    	}
	}

	//删除当月的早会
	private String delete(){
		for(int i=0;i<meetingMonths.size();i++){
			try {
				List<MeetingDay>meetingDays=this.meetingDayManager.loadByKey("meetingMonth", meetingMonths.get(i));
				if(meetingDays!=null){
					this.meetingDayManager.deleteAllMeetingDay(meetingDays);
				}
			} catch (DaoException e) {
				e.printStackTrace();
				addActionMessage(getText("deletemeeting.error"));
				return ERROR;
			}	
		}
    	try {
    		this.meetingMonthManager.deleteAllMeetingMonth(this.meetingMonths);
			addActionMessage(getText("deletemeeting.success"));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("deletemeeting.error"));
			return ERROR;
		}
		
    }
	
}
