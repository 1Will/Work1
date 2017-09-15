package com.yongjun.tdms.presentation.webwork.action.workReport.week;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

public class ListWeekAction extends ValueListAction{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String Month[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
	public String checkbox ;
	public String year ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getRequestParameterMap(){
		Map map = super.getRequestParameterMap();
		
		
		if(!hasId("checkbox")&&!hasId("year")){
			if(!hasId("week.name")){
				String mon ="%"+ new SimpleDateFormat("yyyy-MM").format(new Date())+"%";
				map.put("week.startDate", mon);
			}
		}
		
		if(hasId("year")){
			String mon ="%"+ new SimpleDateFormat("yyyy").format(new Date())+"%";
			map.put("week.startDate", mon);
		}
		
		if(hasId("checkbox")){
			map.remove("week.startDate");
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


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}

}
