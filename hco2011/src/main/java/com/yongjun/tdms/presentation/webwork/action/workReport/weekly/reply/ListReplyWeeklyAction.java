package com.yongjun.tdms.presentation.webwork.action.workReport.weekly.reply;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.workReport.weekly.ReplyWeeklyManager;
import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;

public class ListReplyWeeklyAction extends ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReplyWeeklyManager replyWeeklyManager;
	private WeeklyManager weeklyManager;
	private boolean first = false;
	
	public ListReplyWeeklyAction(ReplyWeeklyManager replyWeeklyManager,WeeklyManager weeklyManager){
		this.replyWeeklyManager=replyWeeklyManager;
		this.weeklyManager=weeklyManager;
	}
	
	@Override
	protected String getAdapterName() {
		return "replyWeeklyHQL";
	}
	
	protected Map getRequestParameterMap(){
		Map map = super.getRequestParameterMap();
		return map;
	}
	
	public String execute(){
		return "success";
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

}
