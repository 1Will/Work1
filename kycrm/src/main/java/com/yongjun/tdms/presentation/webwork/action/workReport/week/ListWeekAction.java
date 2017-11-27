package com.yongjun.tdms.presentation.webwork.action.workReport.week;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;

public class ListWeekAction extends ValueListAction{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String Month[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
	public String checkbox ;
	public String year ;
	
	private UserManager userManager;
	private WeeklyManager weeklyManager;
	
	public ListWeekAction(UserManager userManager,WeeklyManager weeklyManager) {
		// TODO Auto-generated constructor stub
		this.userManager = userManager;
		this.weeklyManager = weeklyManager;
	}
	
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
		if(hasId("filterWeek")){
			User user = userManager.getUser();
			Long id = user.getId();
			try {
				List<Weekly> weeklies = weeklyManager.loadByKey("rapporteur", id);
				List<Long> weeklyIds = new ArrayList<Long>();
				if(weeklies!=null && weeklies.size()>0){
					for (Weekly weekly : weeklies) {
						weeklyIds.add(weekly.getWeek().getId());
					}
					map.put("weeklyIds", weeklyIds);
				}
			} catch (DaoException e) {
				map.remove("weeklyIds");
			}
			
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
