package com.yongjun.tdms.presentation.webwork.action.workReport.daily.reply;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.tdms.service.workReport.daily.ReplyManager;

public class ListReplyAction extends ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReplyManager replyManager;
	private DailyManager dailyManager;
	private boolean first = false;
	
	public ListReplyAction(ReplyManager replyManager,DailyManager dailyManager){
		this.replyManager=replyManager;
		this.dailyManager=dailyManager;
	}
	
	@Override
	protected String getAdapterName() {
		if(hasId("daily.id")){
			return "replyDaily";
		}
		if(hasId("backVisit.id")){
			return "replyBackVisit";
		}
		if(hasId("weekly.id")){
			return "replyWeeklyHQL";
		}
		if(hasId("customerInfo.id")){
			return "replyCustomerInfoHQL";
		}
		return null;
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
