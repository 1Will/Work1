/*     */ package com.yongjun.tdms.presentation.webwork.action.task.historytask;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.historytask.HistoryTask;
/*     */ import com.yongjun.tdms.service.historytask.HistoryTaskManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditHistoryTaskAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final HistoryTaskManager historyTaskManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private HistoryTask historyTask;
/*     */ 
/*     */   public EditHistoryTaskAction(HistoryTaskManager historyTaskManager, CodeValueManager codeValueManager, UserManager userManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  74 */     this.historyTaskManager = historyTaskManager;
/*  75 */     this.codeValueManager = codeValueManager;
/*  76 */     this.userManager = userManager;
/*  77 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  86 */     if (hasId("historyTask.id"))
/*  87 */       this.historyTask = this.historyTaskManager.loadHistoryTask(getId("historyTask.id"));
/*     */     else
/*  89 */       this.historyTask = new HistoryTask();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/*  98 */     List codes = null;
/*     */     try {
/* 100 */       codes = new ArrayList();
/* 101 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("042"));
/*     */ 
/* 103 */       if ((null != one) && (one.size() > 0)) {
/* 104 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 106 */         if ((null != list) && (list.size() > 0)) {
/* 107 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 110 */       return codes;
/*     */     } catch (DaoException e) {
/* 112 */       e.printStackTrace();
/*     */     }
/* 114 */     return codes;
/*     */   }
/*     */ 
/*     */   public HistoryTask getHistoryTask()
/*     */   {
/* 122 */     return this.historyTask;
/*     */   }
/*     */ 
/*     */   public void setHistoryTask(HistoryTask historyTask)
/*     */   {
/* 130 */     this.historyTask = historyTask;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.task.historytask.EditHistoryTaskAction
 * JD-Core Version:    0.6.2
 */