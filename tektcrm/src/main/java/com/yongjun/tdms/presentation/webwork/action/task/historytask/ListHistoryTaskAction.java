/*     */ package com.yongjun.tdms.presentation.webwork.action.task.historytask;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.historytask.HistoryTask;
/*     */ import com.yongjun.tdms.service.historytask.HistoryTaskManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListHistoryTaskAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final HistoryTaskManager historyTaskManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private List<HistoryTask> historyTaskList;
/*     */ 
/*     */   public ListHistoryTaskAction(HistoryTaskManager historyTaskManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  70 */     this.historyTaskManager = historyTaskManager;
/*  71 */     this.codeValueManager = codeValueManager;
/*  72 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*     */     User user;
/*  81 */     if (hasIds("historyTaskIds")) {
/*  82 */       this.historyTaskList = this.historyTaskManager.loadAllHistoryTask(getIds("historyTaskIds"));
/*     */ 
/*  84 */       user = this.userManager.getUser();
/*     */     }
/*     */     else {
/*  87 */       this.historyTaskList = new ArrayList();
/*  88 */       user = this.userManager.getUser();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  96 */     if (isDisabled()) {
/*  97 */       return disable();
/*     */     }
/*  99 */     if (isEnable()) {
/* 100 */       return enable();
/*     */     }
/* 102 */     if (isDelete()) {
/* 103 */       return delete();
/*     */     }
/* 105 */     getRequestParameterMap();
/* 106 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 114 */     return "historyTask";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 122 */     Map map = super.getRequestParameterMap();
/* 123 */     User u = this.userManager.getUser();
/* 124 */     if (null != u) {
/* 125 */       map.put("historyTask.personnelFilesName", u.getName());
/*     */     }
/* 127 */     return map;
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/* 136 */     System.out.println("失效");
/*     */     try {
/* 138 */       this.historyTaskManager.disabledAllHistoryTask(this.historyTaskList);
/*     */     }
/*     */     catch (Exception e) {
/* 141 */       e.printStackTrace();
/*     */     }
/* 143 */     addActionMessage(getText("disabled.success"));
/* 144 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 152 */       this.historyTaskManager.deleteAllHistoryTask(this.historyTaskList);
/* 153 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e) {
/* 156 */       e.printStackTrace();
/* 157 */       addActionMessage(getText("delete.error"));
/*     */     }
/* 159 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/* 166 */     this.historyTaskManager.enabledAllHistoryTask(this.historyTaskList);
/* 167 */     addActionMessage(getText("enabled.success"));
/* 168 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllHistoryTaskType()
/*     */   {
/* 176 */     List codes = null;
/*     */     try {
/* 178 */       codes = new ArrayList();
/* 179 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("041"));
/*     */ 
/* 181 */       if ((null != one) && (one.size() > 0)) {
/* 182 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 184 */         if ((null != list) && (list.size() > 0)) {
/* 185 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 188 */       CodeValue cv = new CodeValue();
/* 189 */       cv.setId(Long.valueOf(-1L));
/* 190 */       cv.setName(getText("select.option.all"));
/* 191 */       codes.add(0, cv);
/* 192 */       return codes;
/*     */     } catch (DaoException e) {
/* 194 */       e.printStackTrace();
/*     */ 
/* 196 */       CodeValue cv = new CodeValue();
/* 197 */       cv.setId(Long.valueOf(-1L));
/* 198 */       cv.setName(getText("select.option.all"));
/* 199 */       codes.add(0, cv);
/* 200 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.task.historytask.ListHistoryTaskAction
 * JD-Core Version:    0.6.2
 */