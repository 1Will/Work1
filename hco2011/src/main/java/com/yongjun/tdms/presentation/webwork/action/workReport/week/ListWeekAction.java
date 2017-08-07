package com.yongjun.tdms.presentation.webwork.action.workReport.week;

import java.util.Calendar;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

public class ListWeekAction extends ValueListAction{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String Month[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
	public String checkbox ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getRequestParameterMap(){
		Map map = super.getRequestParameterMap();
		if(!hasId("checkbox")){
			if(!hasId("week.name")){
				Calendar cal = Calendar.getInstance();
				String mon ="%"+Month[cal.get(Calendar.MONTH)]+"%";
				map.put("week.name", mon);
			}
		}else {
			checkbox="checkbox";
		}
		return map;
	}
	
	
	@Override
	protected String getAdapterName() {
		return "weekHQL";
	}


	public String getCheckbox() {
		return checkbox;
	}


	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

}
