/*     */ package com.yongjun.tdms.presentation.webwork.action.workReport.daily;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.daily.Daily;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workReport.daily.DailyManager;
/*     */ import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;

/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
import java.util.HashMap;
/*     */ import java.util.List;






import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditDailyAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 3022668162752790885L;
/*     */   private final DailyManager dailyManager;
/*     */   private final WeeklyManager weeklyManager;
/*     */   private final UserManager userManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final BackVisitManager backVisitManager;
			private final InstitutionManager institutionManager;
			private final DepartmentManager departmentManager;
			private final DutyManager dutyManager;
			private final EventNewManager eventNewManager;
            private final EventTypeManager eventTypeManager;
/*     */   private String startHour;
/*     */   private String startMinute;
/*     */   private String endHour;
/*     */   private String endMinute;
/*     */   private Long weeklyId;
/*     */   private String perType;
/*     */   private String backVisitIds;
/*     */   private Daily daily;
			private String popWindowFlag;
/*     */ 
/*     */   public EditDailyAction(DailyManager dailyManager, WeeklyManager weeklyManager, UserManager userManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, BackVisitManager backVisitManager,InstitutionManager institutionManager,DepartmentManager departmentManager,DutyManager dutyManager,EventNewManager eventNewManager,EventTypeManager eventTypeManager)
/*     */   {
/* 110 */     this.dailyManager = dailyManager;
/* 111 */     this.weeklyManager = weeklyManager;
/* 112 */     this.userManager = userManager;
/* 113 */     this.codeValueManager = codeValueManager;
/* 114 */     this.personnelFilesManager = personnelFilesManager;
			  this.backVisitManager=backVisitManager;
			  this.institutionManager=institutionManager;
			  this.departmentManager=departmentManager;
			  this.dutyManager=dutyManager;
			  this.eventNewManager=eventNewManager;
			  this.eventTypeManager=eventTypeManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
			  if(hasId("weekly.id")){
/* 124 */     this.weeklyId = Long.valueOf(this.request.getParameter("weekly.id"));
			  }
/* 125 */     if ((this.daily == null) && (hasId("daily.id"))) {
/* 126 */       this.daily = this.dailyManager.loadDaily(getId("daily.id"));
/* 127 */       this.startHour = new SimpleDateFormat("kk").format(this.daily.getStartTime());
/*     */ 
/* 129 */       this.startMinute = new SimpleDateFormat("mm").format(this.daily.getStartTime());
/*     */ 
/* 131 */       this.endHour = new SimpleDateFormat("kk").format(this.daily.getEndTime());
/*     */ 
/* 133 */       this.endMinute = new SimpleDateFormat("mm").format(this.daily.getEndTime());
/*     */     }
/*     */     else
/*     */     {
/* 137 */       this.daily = new Daily();
/*     */     }
/* 139 */     this.perType = permission();
			  if(null!=this.request.getParameter("popWindowFlag")){
					this.popWindowFlag=this.request.getParameter("popWindowFlag");
				}
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 149 */     this.startHour = this.request.getParameter("startHour");
/* 150 */     this.startMinute = this.request.getParameter("startMinute");
/* 151 */     this.endHour = this.request.getParameter("endHour");
/* 152 */     this.endMinute = this.request.getParameter("endMinute");
			//获取记录人
			  if (hasId("personId")) {
				  this.daily.setRapporteur(this.userManager.loadUser(getId("personId")));
		      }
			  
			  if (hasId("inst.id")) {
				  this.daily.setInst(this.institutionManager.loadInstitution(getId("inst.id")));
			    }
			 
			     if (hasId("dept.id")) {
			    	 this.daily.setDept(this.departmentManager.loadDepartment(getId("dept.id")));
			     }
			
			     if (hasId("duty.id")) {
			    	 this.daily.setDuty(this.dutyManager.loadDuty(getId("duty.id")));
			     }
			  

			//获取回访记录的所有id
			  this.backVisitIds =this.request.getParameter("backVisitIds");
			  if(!"".equals(backVisitIds)){
			  String idsTemp[]=backVisitIds.split("-");
			  Long bvtIds[]=new Long[idsTemp.length];
			  for(int i=0;i<idsTemp.length;i++){
				  bvtIds[i]=Long.parseLong(idsTemp[i]);
			  }
			  List<BackVisit> bvtList = backVisitManager.loadAllBackVisit(bvtIds);
			  this.daily.getBvtList().addAll(bvtList);
			  bvtList.clear();
			  }
			  
			  this.daily.setBackVisitContext(this.request.getParameter("daily.backVisitContext"));
/* 153 */     Calendar c = Calendar.getInstance();
/* 154 */     String d = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()) + " ";
/*     */     try {
/* 156 */       Date startTime = new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(d + this.startHour + ":" + this.startMinute);
/*     */ 
/* 158 */       Date endTime = new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(d + this.endHour + ":" + this.endMinute);
/*     */ 
/* 160 */       this.daily.setStartTime(startTime);
/* 161 */       this.daily.setEndTime(endTime);
/* 162 */       this.daily.setWeekDate(this.request.getParameter("daily.week"));
				
/*     */     } catch (ParseException e2) {
/* 164 */       e2.printStackTrace();
/*     */     }
/* 166 */     Boolean isNew = Boolean.valueOf(this.daily.isNew());
/* 167 */     if (hasId("weekly.id")) {
/* 168 */       Weekly weekly = this.weeklyManager.loadWeekly(getId("weekly.id"));
/*     */ 
/* 170 */       this.daily.setWeekly(weekly);
/* 171 */       this.daily.setOrganization(weekly.getOrganization());
/* 172 */       this.daily.setInst(weekly.getInst());
/* 173 */       this.daily.setDept(weekly.getDept());
/* 174 */       this.daily.setDuty(weekly.getDuty());
/* 175 */       this.daily.setRapporteur(weekly.getRapporteur());
/*     */     }
			//提交后保存事件
			  String submit=null;
/*暂时不用*/
			  if ("1".equals(this.request.getParameter("isSaved"))) {
					try {
						EventType eventType=null;
						List<EventType> eventTypes =this.eventTypeManager.loadByKey("code", "10002");
						if(eventTypes!=null&&eventTypes.size()>0){
							eventType=eventTypes.get(0);
						}else {
							  eventType = new EventType();
							    eventType.setId(3L);
						}
					 	EventNew event = new EventNew();
					    event.setEffectflag("E");
					    event.setEventType(eventType);
					    event.setName("日报提交");
					    event.setUserId(this.userManager.getUser().getId()+"");
					    Map<String, String> map = new HashMap<String, String>();
					    String ids =getUser().getId()+"";
					    //查询领导
					    PersonnelFiles pFiles =getPeronnelF().getSuperiorLeader();
					    while (pFiles!=null) {
					    	List<User> leader = userManager.loadByKey("code", pFiles.getCode());
							ids +=","+leader.get(0).getId();
							pFiles = pFiles.getSuperiorLeader();
						}
					    map.put("users", ids);
						map.put("dailyId", this.daily.getId()+"");
						String moreinfo = JSONObject.fromObject(map).toString();
						event.setMoreinfo(moreinfo);
						eventNewManager.storeEventNew(event);
						submit="submit";
					}catch(Exception e){
						e.printStackTrace();
					}
				}
/*	   */				
/* 162 */       this.daily.setIsSaved(this.request.getParameter("isSaved"));
/*     */     try
/*     */     {
/* 179 */       this.dailyManager.storeDaily(this.daily);
/*     */ 
/* 181 */       if (isNew.booleanValue()) {
/* 182 */         addActionMessage(getText("daily.add.success", Arrays.asList(new Object[] { new SimpleDateFormat("yyyy-MM-dd").format(this.daily.getCurrentDate()) })));
/*     */ 
/* 184 */         return "new";
/*     */       }
/*     */ 		if(submit!=null){
/* 187 */       	addActionMessage(getText("daily.submit.success", Arrays.asList(new Object[] { new SimpleDateFormat("yyyy-MM-dd").format(this.daily.getCurrentDate()) })));
				}else{
/* 187 */       	addActionMessage(getText("daily.edit.success", Arrays.asList(new Object[] { new SimpleDateFormat("yyyy-MM-dd").format(this.daily.getCurrentDate()) })));
				}
/* 189 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 192 */       e.printStackTrace();
/* 193 */       addActionMessage(getText("daily.add.error", Arrays.asList(new Object[] { new SimpleDateFormat("yyyy-MM-dd").format(this.daily.getCurrentDate()) })));
/*     */     }
/* 195 */     return "error";
/*     */   }
/*     */ 
/*     */   public String permission()
/*     */   {
/* 205 */     String flag = "0";
/* 206 */     boolean isNew = this.daily.isNew();
/*     */ 
/* 208 */     if (isNew)
/* 209 */       flag = "0";
/* 210 */     else if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser())) || (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理")))
/*     */     {
/* 214 */       flag = "2";
/* 215 */     } else if (dutyByUser(getUser()).equals("部门经理"))
/*     */     {
/* 217 */       if ((null != this.daily) && (null != this.daily.getRapporteur()) && (this.daily.getRapporteur().getId().equals(getUser().getId())))
/*     */       {
/* 219 */         flag = "0";
/*     */       }
/* 221 */       else flag = "1";
/*     */     }
/*     */     else {
/* 224 */       flag = "0";
/*     */     }
/* 226 */     return flag;
/*     */   }
/*     */ 
/*     */   public String dutyByUser(User user)
/*     */   {
/* 234 */     List list = new ArrayList();
/* 235 */     PersonnelFiles personnelFiles = new PersonnelFiles();
/* 236 */     String dutyName = null;
/*     */     try {
/* 238 */       list = this.personnelFilesManager.loadByKey("code", user.getCode());
/*     */     } catch (DaoException e) {
/* 240 */       this.logger.info("查询用户对应的人事档案出错！");
/*     */     }
/* 242 */     if ((null != list) && (list.size() > 0)) {
/* 243 */       personnelFiles = (PersonnelFiles)list.get(0);
/* 244 */       dutyName = personnelFiles.getDuty().getPerType().getName().trim();
/*     */     }
/* 246 */     return dutyName;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllReportType()
/*     */   {
/*     */     try
/*     */     {
/* 256 */       String[] keyNames1 = { "code", "disabled" };
/* 257 */       Object[] keyValues1 = { String.valueOf("049"), Boolean.valueOf(false) };
/* 258 */       List reportTypeList = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 260 */       if (reportTypeList != null) {
/* 261 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 262 */         Object[] keyValues2 = { ((CodeValue)reportTypeList.get(0)).getId(), Boolean.valueOf(false) };
/* 263 */         List reportTypes = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 265 */         if (reportTypes != null)
/* 266 */           return reportTypes;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 270 */       e.printStackTrace();
/* 271 */       return new ArrayList();
/*     */     }
/* 273 */     return new ArrayList();
/*     */   }

			public PersonnelFiles getPeronnelF()
			     throws DaoException
			   {
					List list = this.personnelFilesManager.loadByKey("code", getUser().getCode().trim());
					if ((null != list) && (list.size() > 0)) {
					return (PersonnelFiles)list.get(0);
					}
				    return new PersonnelFiles();
			   }
/*     */ 
/*     */   public Daily getDaily()
/*     */   {
/* 281 */     return this.daily;
/*     */   }
/*     */ 
/*     */   public void setDaily(Daily daily)
/*     */   {
/* 289 */     this.daily = daily;
/*     */   }
/*     */ 
/*     */   public String getEndHour()
/*     */   {
/* 297 */     return this.endHour;
/*     */   }
/*     */ 
/*     */   public void setEndHour(String endHour) {
/* 301 */     this.endHour = endHour;
/*     */   }
/*     */ 
/*     */   public String getEndMinute()
/*     */   {
/* 309 */     return this.endMinute;
/*     */   }
/*     */ 
/*     */   public void setEndMinute(String endMinute)
/*     */   {
/* 317 */     this.endMinute = endMinute;
/*     */   }
/*     */ 
/*     */   public String getStartHour() {
/* 321 */     return this.startHour;
/*     */   }
/*     */ 
/*     */   public void setStartHour(String startHour)
/*     */   {
/* 329 */     this.startHour = startHour;
/*     */   }
/*     */ 
/*     */   public String getStartMinute()
/*     */   {
/* 337 */     return this.startMinute;
/*     */   }
/*     */ 
/*     */   public void setStartMinute(String startMinute)
/*     */   {
/* 345 */     this.startMinute = startMinute;
/*     */   }
/*     */ 
/*     */   public Long getWeeklyId()
/*     */   {
/* 353 */     return this.weeklyId;
/*     */   }
/*     */ 
/*     */   public void setWeeklyId(Long weeklyId)
/*     */   {
/* 361 */     this.weeklyId = weeklyId;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 368 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public String getPerType()
/*     */   {
/* 376 */     return this.perType;
/*     */   }
/*     */ 
/*     */   public void setPerType(String perType)
/*     */   {
/* 384 */     this.perType = perType;
/*     */   }
			public String getBackVisitIds() {
				return backVisitIds;
			}
			public void setBackVisitIds(String backVisitIds) {
				this.backVisitIds = backVisitIds;
			}
			public String getPopWindowFlag() {
				return popWindowFlag;
			}
			public void setPopWindowFlag(String popWindowFlag) {
				this.popWindowFlag = popWindowFlag;
			}
			
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workReport.daily.EditDailyAction
 * JD-Core Version:    0.6.2
 */