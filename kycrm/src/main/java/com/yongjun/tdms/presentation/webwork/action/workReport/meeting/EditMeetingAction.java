package com.yongjun.tdms.presentation.webwork.action.workReport.meeting;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.meeting.MeetingDay;
import com.yongjun.tdms.model.workReport.meeting.MeetingMonth;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.meeting.MeetingDayManager;
import com.yongjun.tdms.service.workReport.meeting.MeetingMonthManager;

public class EditMeetingAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
	private final PersonnelFilesManager personnelFilesManager; 
	private final GroupManager groupManager;
	private final MeetingDayManager meetingDayManager;
	private final UserManager userManager;
	private final MeetingMonthManager meetingMonthManager;
	
	
	
	private MeetingMonth meetingMonth;
	private List <PersonnelFiles>hostpersons;
	private PersonnelFiles publisher;
	private List <MeetingDay> meetingDays;
	private int index=0;
	
	public EditMeetingAction(PersonnelFilesManager personnelFilesManager, GroupManager groupManager,
			MeetingDayManager meetingDayManager, UserManager userManager, MeetingMonthManager meetingMonthManager) {
		super();
		this.personnelFilesManager = personnelFilesManager;
		this.groupManager = groupManager;
		this.meetingDayManager = meetingDayManager;
		this.userManager = userManager;
		this.meetingMonthManager = meetingMonthManager;
		
	}

	

	public void prepare() throws Exception {
		if (hasId("meetingMonth.id")) {
			this.meetingMonth = this.meetingMonthManager.loadMeetingMonth(getId("meetingMonth.id"));
		}else{
			this.meetingMonth = new MeetingMonth();
			//获取发布者
			publisher = null;
			try {
				 publisher = personnelFilesManager.loadByKey("code", userManager.getUser().getCode()).get(0);
			} catch (DaoException e1) {
				e1.printStackTrace();
				System.out.println("该用户没有编号code");
			}
			//获取早会主持人及其出厂顺序
	    	Long id=meetingDayManager.loadMaxId();
	    	String lastName="";
	        if(id!=0){
	        	 MeetingDay md=meetingDayManager.loadMeetingDay(id);
	        	 lastName=md.getHostperson().getName();
	        }
	 	   hostpersons=getHostperson();
	 	   for(int i=0;i<hostpersons.size();i++){
	 		   if(hostpersons.get(i).getName().equals(lastName)){
	 			   index=i+1;
	 		   }
	 		   if(index>=hostpersons.size()){
	 			   index=0;
	 		   }
	 	   }
		     }
	}
	//创建早会
    public String save() throws IOException{
    	Boolean isNew=meetingMonth.isNew();
    	String dateMonth=request.getParameter("meetingMonth.date");
		String theme=request.getParameter("meetingMonth.theme");
		String [] strings=request.getParameterValues("weekday");
    	try{
    		
    		meetingMonth.setTheme(theme);
    		if(isNew){
    			if(dateMonth!=null){
        			meetingMonth.setDate(dateMonth);
        		}
        		if(publisher!=null){
        			meetingMonth.setPublisher(publisher);
        		}
        			String weekmeeting="";
            		if(strings!=null && strings.length>0){
            			 weekmeeting=strings[0];
                		for (int i = 1; i < strings.length; i++) {
            				weekmeeting+=","+strings[i];
            			}
            		}else{
            			weekmeeting=request.getParameter("weekmeeting");
            		}
            		meetingMonth.setWeekmeeting(weekmeeting);
    		}
    		meetingMonthManager.storeMeetingMonth(meetingMonth);
    		if(!isNew){
    			addActionMessage(getText("Theme.modification.success"));
    			return SUCCESS;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		addActionMessage(getText("Theme.modification.error"));
			return ERROR;
    	}
		if(isNew){
			try{
	    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		    	Date date = null;
				try {
					date = sdf.parse(dateMonth);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        getmeetingDays(date,theme,strings);
		    	meetingDayManager.saveAllMeetingDay(meetingDays);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		addActionMessage(getText("New.error"));
	        	return ERROR;
	    	}
		}
		addActionMessage(getText("New.success"));
    	return SUCCESS;
    }

	 //获取该月所有的符合早会的日期
    private void getmeetingDays(Date date, String theme,String[]strings )throws IOException{
    	   meetingDays=new ArrayList<MeetingDay>();
    	  //判断给定的月份的天数
	 	   Calendar cal = Calendar.getInstance();
	 	   cal.setTime(date);
	 	   int mouth = cal.get(Calendar.MONTH)+1;
	 	   int year =cal.get(Calendar.YEAR);
	 	   int dayLong=0;
	 	   if(year%4==0 && year%100!=0 || year%400==0){
	 		   if(mouth==2){
	 			   dayLong=29;
	 		   }
	 	   }else{
	 		   if(mouth==2){
	 			  dayLong=28;
	 		   }
	 	   }
	 	   if(mouth==1||mouth==3||mouth==5||mouth==7||mouth==8||mouth==10||mouth==12){
	 		  dayLong=31;
	 	   }
	 	   if(mouth==4||mouth==6||mouth==9||mouth==11){
	 		  dayLong=30;
	 	   }
 	   //判断指定月份日期所对应的星期
 	   String mon=null;
 	   String tue=null;
 	   String wed=null;
 	   String thu=null;
 	   String fri=null;
 	   for (int i = 0; i < strings.length; i++) {
 		  if (strings[i].equals("1")) {
 			  mon="星期一";
		}
 		  if (strings[i].equals("2")) {
 			  tue="星期二";
		}
 		  if (strings[i].equals("3")) {
 			  wed="星期三";
		}
 		  if (strings[i].equals("4")) {
 			  thu="星期四";
		}
 		  if (strings[i].equals("5")) {
 			  fri="星期五";
		} 
	}
 	   for(int i=0;i<dayLong;i++){
	 	   cal.set(Calendar.DATE,i+1);
	 	   int week =cal.get(Calendar.DAY_OF_WEEK)-1;
	 	    date=cal.getTime();
	 	   MeetingDay meetingDay = new MeetingDay();
	 	   if(week==1 && mon!=null){
	 		  meetingDay.setWeek("星期一");
	 	   }
	 	   if(week==2 && tue!=null){
	 		  meetingDay.setWeek("星期二");
	 	   }
	 	   if(week==3 && wed!=null){
	 		  meetingDay.setWeek("星期三");
	 	   }
	 	   if(week==4 && thu!=null){
	 		  meetingDay.setWeek("星期四");
	 	   }
	 	   if(week==5 && fri!=null){
	 		  meetingDay.setWeek("星期五");
	 	   }
	 	   if((week==1 && mon!=null)
	 		  ||(week==5 && fri!=null)
	 		  ||(week==4 && thu!=null)
	 		  ||(week==3 && wed!=null)
	 		  ||(week==2 && tue!=null)){
		 		  meetingDay.setDate(date);
		 		  meetingDay.setHostperson(hostpersons.get((int) index));
		 		  meetingDay.setMeetingMonth(meetingMonth);
		 		  meetingDays.add(meetingDay);
		 		  ++index;
		 		  if((index+1)>hostpersons.size()){
		 			  index=0;
	 		     }
	 	   }
	 	}
    }

	//获得所有早会主持人
	private List<PersonnelFiles> getHostperson() {
		Set<User> users = groupManager.getGroupByGroupName("早会主持人").getUsers();
		List<PersonnelFiles> hostpersons=new ArrayList<PersonnelFiles>();
		for (User user : users) {
			try {
				PersonnelFiles hostperson = personnelFilesManager.loadByKey("code", user.getCode()).get(0);
				hostpersons.add(hostperson);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
	   return hostpersons;
	}
   public PersonnelFiles getPublisher() {
		return publisher;
	}
   public void setPublisher(PersonnelFiles publisher) {
		this.publisher = publisher;
	}
	public MeetingMonth getMeetingMonth() {
		return meetingMonth;
	}
	public void setMeetingMonth(MeetingMonth meetingMonth) {
		this.meetingMonth = meetingMonth;
	}
	
	}
