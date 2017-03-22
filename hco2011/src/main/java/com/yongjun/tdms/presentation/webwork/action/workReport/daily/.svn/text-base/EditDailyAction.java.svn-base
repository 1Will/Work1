/*     */ package com.yongjun.tdms.presentation.webwork.action.workReport.daily;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workReport.daily.Daily;
/*     */ import com.yongjun.tdms.model.workReport.weekly.Weekly;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workReport.daily.DailyManager;
/*     */ import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditDailyAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 3022668162752790885L;
/*     */   private final DailyManager dailyManager;
/*     */   private final WeeklyManager weeklyManager;
/*     */   private final UserManager userManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private String startHour;
/*     */   private String startMinute;
/*     */   private String endHour;
/*     */   private String endMinute;
/*     */   private Long weeklyId;
/*     */   private String perType;
/*     */   private Daily daily;
/*     */ 
/*     */   public EditDailyAction(DailyManager dailyManager, WeeklyManager weeklyManager, UserManager userManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/* 110 */     this.dailyManager = dailyManager;
/* 111 */     this.weeklyManager = weeklyManager;
/* 112 */     this.userManager = userManager;
/* 113 */     this.codeValueManager = codeValueManager;
/* 114 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 124 */     this.weeklyId = Long.valueOf(this.request.getParameter("weekly.id"));
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
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 149 */     this.startHour = this.request.getParameter("startHour");
/* 150 */     this.startMinute = this.request.getParameter("startMinute");
/* 151 */     this.endHour = this.request.getParameter("endHour");
/* 152 */     this.endMinute = this.request.getParameter("endMinute");
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
/*     */     try
/*     */     {
/* 179 */       this.dailyManager.storeDaily(this.daily);
/*     */ 
/* 181 */       if (isNew.booleanValue()) {
/* 182 */         addActionMessage(getText("daily.add.success", Arrays.asList(new Object[] { this.daily.getId() })));
/*     */ 
/* 184 */         return "new";
/*     */       }
/*     */ 
/* 187 */       addActionMessage(getText("daily.edit.success", Arrays.asList(new Object[] { this.daily.getId() })));
/*     */ 
/* 189 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 192 */       e.printStackTrace();
/* 193 */       addActionMessage(getText("daily.add.error", Arrays.asList(new Object[] { this.daily.getId() })));
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
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workReport.daily.EditDailyAction
 * JD-Core Version:    0.6.2
 */