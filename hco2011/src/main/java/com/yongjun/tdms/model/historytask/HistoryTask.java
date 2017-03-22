/*     */ package com.yongjun.tdms.model.historytask;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.WorkflowCase;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class HistoryTask extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private WorkflowCase workflowCase;
/*     */   private Flow flow;
/*     */   private String personnelFilesName;
/*     */   private int statue;
/*     */   private Date approveDate;
/*     */   private int agreeOrNot;
/*     */   private String notAgreeReason;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  76 */     if (arg0 == null) {
/*  77 */       return false;
/*     */     }
/*  79 */     if ((arg0 instanceof HistoryTask)) {
/*  80 */       HistoryTask h = (HistoryTask)arg0;
/*  81 */       if ((this.code == h.code) && (this.name == h.name) && (this.workflowCase == h.workflowCase) && (this.flow == h.flow) && (this.personnelFilesName == h.personnelFilesName))
/*     */       {
/*  83 */         return true;
/*     */       }
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode() {
/*  90 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public int getAgreeOrNot()
/*     */   {
/*  97 */     return this.agreeOrNot;
/*     */   }
/*     */ 
/*     */   public void setAgreeOrNot(int agreeOrNot)
/*     */   {
/* 104 */     this.agreeOrNot = agreeOrNot;
/*     */   }
/*     */ 
/*     */   public Date getApproveDate()
/*     */   {
/* 111 */     return this.approveDate;
/*     */   }
/*     */ 
/*     */   public void setApproveDate(Date approveDate)
/*     */   {
/* 118 */     this.approveDate = approveDate;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 125 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 132 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Flow getFlow()
/*     */   {
/* 139 */     return this.flow;
/*     */   }
/*     */ 
/*     */   public void setFlow(Flow flow)
/*     */   {
/* 146 */     this.flow = flow;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 153 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 160 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getNotAgreeReason()
/*     */   {
/* 167 */     return this.notAgreeReason;
/*     */   }
/*     */ 
/*     */   public void setNotAgreeReason(String notAgreeReason)
/*     */   {
/* 174 */     this.notAgreeReason = notAgreeReason;
/*     */   }
/*     */ 
/*     */   public String getPersonnelFilesName()
/*     */   {
/* 181 */     return this.personnelFilesName;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFilesName(String personnelFilesName)
/*     */   {
/* 188 */     this.personnelFilesName = personnelFilesName;
/*     */   }
/*     */ 
/*     */   public int getStatue()
/*     */   {
/* 195 */     return this.statue;
/*     */   }
/*     */ 
/*     */   public void setStatue(int statue)
/*     */   {
/* 202 */     this.statue = statue;
/*     */   }
/*     */ 
/*     */   public WorkflowCase getWorkflowCase()
/*     */   {
/* 209 */     return this.workflowCase;
/*     */   }
/*     */ 
/*     */   public void setWorkflowCase(WorkflowCase workflowCase)
/*     */   {
/* 216 */     this.workflowCase = workflowCase;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.historytask.HistoryTask
 * JD-Core Version:    0.6.2
 */