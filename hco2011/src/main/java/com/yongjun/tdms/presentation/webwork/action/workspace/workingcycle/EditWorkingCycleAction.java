/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.workingcycle;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.workspace.workingcycle.WorkingCycle;
/*     */ import com.yongjun.tdms.service.workspace.workingcycle.WorkingCycleManager;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditWorkingCycleAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 7238321317794589577L;
/*     */   private final WorkingCycleManager workingCycleManager;
/*     */   private WorkingCycle workingCycle;
/*     */   private String upStartHour;
/*     */   private String upStartMinute;
/*     */   private String upEndHour;
/*     */   private String upEndMinute;
/*     */   private String downStartHour;
/*     */   private String downStartMinute;
/*     */   private String downEndHour;
/*     */   private String downEndMinute;
/*     */ 
/*     */   public EditWorkingCycleAction(WorkingCycleManager workingCycleManager)
/*     */   {
/*  76 */     this.workingCycleManager = workingCycleManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  83 */     if (hasId("workingCycle.id")) {
/*  84 */       this.workingCycle = this.workingCycleManager.loadWorkingCycle(getId("workingCycle.id"));
/*  85 */       this.upStartHour = new SimpleDateFormat("kk").format(this.workingCycle.getUpStartTime());
/*  86 */       this.upStartMinute = new SimpleDateFormat("mm").format(this.workingCycle.getUpStartTime());
/*  87 */       this.upEndHour = new SimpleDateFormat("kk").format(this.workingCycle.getUpEndTime());
/*  88 */       this.upEndMinute = new SimpleDateFormat("mm").format(this.workingCycle.getUpEndTime());
/*     */ 
/*  90 */       this.downStartHour = new SimpleDateFormat("kk").format(this.workingCycle.getDownStartTime());
/*  91 */       this.downStartMinute = new SimpleDateFormat("mm").format(this.workingCycle.getDownStartTime());
/*  92 */       this.downEndHour = new SimpleDateFormat("kk").format(this.workingCycle.getDownEndTime());
/*  93 */       this.downEndMinute = new SimpleDateFormat("mm").format(this.workingCycle.getDownEndTime());
/*     */     } else {
/*  95 */       this.workingCycle = new WorkingCycle();
/*     */     }
/*  97 */     List list = this.workingCycleManager.loadAllWorkingCycles();
/*  98 */     if ((null != list) && (list.size() > 0))
/*  99 */       this.workingCycle = ((WorkingCycle)list.get(0));
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 109 */     this.upStartHour = this.request.getParameter("upStartHour");
/* 110 */     this.upStartMinute = this.request.getParameter("upStartMinute");
/* 111 */     this.upEndHour = this.request.getParameter("upEndHour");
/* 112 */     this.upEndMinute = this.request.getParameter("upEndMinute");
/* 113 */     this.downStartHour = this.request.getParameter("downStartHour");
/* 114 */     this.downStartMinute = this.request.getParameter("downStartMinute");
/* 115 */     this.downEndHour = this.request.getParameter("downEndHour");
/* 116 */     this.downEndMinute = this.request.getParameter("downEndMinute");
/*     */     try
/*     */     {
/* 119 */       Date upStartTime = new SimpleDateFormat("kk:mm").parse(this.upStartHour + ":" + this.upStartMinute);
/*     */ 
/* 121 */       Date upEndTime = new SimpleDateFormat("kk:mm").parse(this.upEndHour + ":" + this.upEndMinute);
/*     */ 
/* 123 */       Date downStartTime = new SimpleDateFormat("kk:mm").parse(this.downStartHour + ":" + this.downStartMinute);
/*     */ 
/* 125 */       Date downEndTime = new SimpleDateFormat("kk:mm").parse(this.downEndHour + ":" + this.downEndMinute);
/*     */ 
/* 127 */       this.workingCycle.setUpStartTime(upStartTime);
/* 128 */       this.workingCycle.setUpEndTime(upEndTime);
/* 129 */       this.workingCycle.setDownEndTime(downEndTime);
/* 130 */       this.workingCycle.setDownStartTime(downStartTime);
/*     */     } catch (ParseException e) {
/* 132 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 135 */     boolean isNew = this.workingCycle.isNew();
/* 136 */     this.workingCycleManager.storeWorkingCycle(this.workingCycle);
/* 137 */     if (isNew) {
/* 138 */       addActionMessage(getText("workingCycle.add.success"));
/* 139 */       return "new";
/*     */     }
/* 141 */     addActionMessage(getText("workingCycle.edit.success"));
/* 142 */     return "success";
/*     */   }
/*     */ 
/*     */   public WorkingCycle getWorkingCycle()
/*     */   {
/* 151 */     return this.workingCycle;
/*     */   }
/*     */ 
/*     */   public void setWorkingCycle(WorkingCycle workingCycle)
/*     */   {
/* 160 */     this.workingCycle = workingCycle;
/*     */   }
/*     */ 
/*     */   public String getDownEndHour()
/*     */   {
/* 167 */     return this.downEndHour;
/*     */   }
/*     */ 
/*     */   public void setDownEndHour(String downEndHour)
/*     */   {
/* 175 */     this.downEndHour = downEndHour;
/*     */   }
/*     */ 
/*     */   public String getDownEndMinute()
/*     */   {
/* 182 */     return this.downEndMinute;
/*     */   }
/*     */ 
/*     */   public void setDownEndMinute(String downEndMinute)
/*     */   {
/* 190 */     this.downEndMinute = downEndMinute;
/*     */   }
/*     */ 
/*     */   public String getDownStartHour()
/*     */   {
/* 197 */     return this.downStartHour;
/*     */   }
/*     */ 
/*     */   public void setDownStartHour(String downStartHour)
/*     */   {
/* 205 */     this.downStartHour = downStartHour;
/*     */   }
/*     */ 
/*     */   public String getDownStartMinute()
/*     */   {
/* 212 */     return this.downStartMinute;
/*     */   }
/*     */ 
/*     */   public void setDownStartMinute(String downStartMinute)
/*     */   {
/* 220 */     this.downStartMinute = downStartMinute;
/*     */   }
/*     */ 
/*     */   public String getUpEndHour()
/*     */   {
/* 227 */     return this.upEndHour;
/*     */   }
/*     */ 
/*     */   public void setUpEndHour(String upEndHour)
/*     */   {
/* 235 */     this.upEndHour = upEndHour;
/*     */   }
/*     */ 
/*     */   public String getUpEndMinute()
/*     */   {
/* 242 */     return this.upEndMinute;
/*     */   }
/*     */ 
/*     */   public void setUpEndMinute(String upEndMinute)
/*     */   {
/* 250 */     this.upEndMinute = upEndMinute;
/*     */   }
/*     */ 
/*     */   public String getUpStartHour()
/*     */   {
/* 257 */     return this.upStartHour;
/*     */   }
/*     */ 
/*     */   public void setUpStartHour(String upStartHour)
/*     */   {
/* 265 */     this.upStartHour = upStartHour;
/*     */   }
/*     */ 
/*     */   public String getUpStartMinute()
/*     */   {
/* 272 */     return this.upStartMinute;
/*     */   }
/*     */ 
/*     */   public void setUpStartMinute(String upStartMinute)
/*     */   {
/* 280 */     this.upStartMinute = upStartMinute;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.workingcycle.EditWorkingCycleAction
 * JD-Core Version:    0.6.2
 */