package com.yongjun.tdms.presentation.webwork.action.workReport.daily.reply;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.tdms.service.workReport.daily.ReplyDailyManager;

public class ListReplyDailyAction extends ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReplyDailyManager replyDailyManager;
	private DailyManager dailyManager;
	private boolean first = false;
	
	public ListReplyDailyAction(ReplyDailyManager replyDailyManager,DailyManager dailyManager){
		this.replyDailyManager=replyDailyManager;
		this.dailyManager=dailyManager;
	}
	
	@Override
	protected String getAdapterName() {
		return "replyDaily";
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
