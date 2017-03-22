/*     */ package com.yongjun.tdms.model.task;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Point;
/*     */ import com.yongjun.tdms.model.workflow.WorkflowCase;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Task extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = -5540329561591627459L;
/*     */   private String code;
/*     */   private String name;
/*     */   private WorkflowCase workflowCase;
/*     */   private Flow flow;
/*     */   private Point point;
/*     */   private int statue;
/*     */   private Date approveDate;
/*     */   private int agreeOrNot;
/*     */   private String notAgreeReason;
/*     */   private int completeReview;
/*     */ 
/*     */   public Task()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Task(String code, String name, Flow flow, Point point, int statue, Date approveDate, int agreeOrNot, String notAgreeReason, WorkflowCase workflowCase)
/*     */   {
/* 110 */     this.code = code;
/* 111 */     this.name = name;
/* 112 */     this.flow = flow;
/* 113 */     this.point = point;
/* 114 */     this.statue = statue;
/* 115 */     this.approveDate = approveDate;
/* 116 */     this.agreeOrNot = agreeOrNot;
/* 117 */     this.notAgreeReason = notAgreeReason;
/* 118 */     this.workflowCase = workflowCase;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 128 */     if (arg0 == this) {
/* 129 */       return true;
/*     */     }
/* 131 */     if (!(arg0 instanceof Task)) {
/* 132 */       return false;
/*     */     }
/*     */ 
/* 135 */     Task ccr = (Task)arg0;
/*     */ 
/* 137 */     if (!ccr.getId().equals(getId())) {
/* 138 */       return false;
/*     */     }
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 149 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getAgreeOrNot()
/*     */   {
/* 157 */     return this.agreeOrNot;
/*     */   }
/*     */ 
/*     */   public void setAgreeOrNot(int agreeOrNot)
/*     */   {
/* 165 */     this.agreeOrNot = agreeOrNot;
/*     */   }
/*     */ 
/*     */   public Date getApproveDate()
/*     */   {
/* 173 */     return this.approveDate;
/*     */   }
/*     */ 
/*     */   public void setApproveDate(Date approveDate)
/*     */   {
/* 181 */     this.approveDate = approveDate;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 189 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 197 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Flow getFlow()
/*     */   {
/* 205 */     return this.flow;
/*     */   }
/*     */ 
/*     */   public void setFlow(Flow flow)
/*     */   {
/* 213 */     this.flow = flow;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 221 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 229 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getNotAgreeReason()
/*     */   {
/* 237 */     return this.notAgreeReason;
/*     */   }
/*     */ 
/*     */   public void setNotAgreeReason(String notAgreeReason)
/*     */   {
/* 245 */     this.notAgreeReason = notAgreeReason;
/*     */   }
/*     */ 
/*     */   public Point getPoint()
/*     */   {
/* 253 */     return this.point;
/*     */   }
/*     */ 
/*     */   public void setPoint(Point point)
/*     */   {
/* 261 */     this.point = point;
/*     */   }
/*     */ 
/*     */   public int getStatue()
/*     */   {
/* 269 */     return this.statue;
/*     */   }
/*     */ 
/*     */   public void setStatue(int statue)
/*     */   {
/* 277 */     this.statue = statue;
/*     */   }
/*     */ 
/*     */   public String toString(Task task)
/*     */   {
/* 287 */     StringBuffer stringBuffer = new StringBuffer();
/* 288 */     stringBuffer.append("id=" + task.getId() + ", ");
/* 289 */     stringBuffer.append("code=" + task.getCode() + ", ");
/* 290 */     stringBuffer.append("name=" + task.getName() + ", ");
/* 291 */     stringBuffer.append("workflowCase=" + task.getWorkflowCase().getName() + ", ");
/* 292 */     stringBuffer.append("flow=" + task.getFlow().getName() + ", ");
/* 293 */     stringBuffer.append("point=" + task.getPoint().getName() + ", ");
/* 294 */     stringBuffer.append("statue=" + (task.getStatue() == 0 ? "已审" : "未审") + ", ");
/* 295 */     stringBuffer.append("approveDate=" + task.getApproveDate() + ", ");
/* 296 */     stringBuffer.append("agreeOrNot=" + task.getAgreeOrNot() + ", ");
/* 297 */     stringBuffer.append("notAgreeReason=" + task.getId() + ", ");
/* 298 */     stringBuffer.append("disabled=" + task.getDisabled());
/* 299 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public WorkflowCase getWorkflowCase()
/*     */   {
/* 307 */     return this.workflowCase;
/*     */   }
/*     */ 
/*     */   public void setWorkflowCase(WorkflowCase workflowCase)
/*     */   {
/* 315 */     this.workflowCase = workflowCase;
/*     */   }
/*     */ 
/*     */   public int getCompleteReview() {
/* 319 */     return this.completeReview;
/*     */   }
/*     */ 
/*     */   public void setCompleteReview(int completeReview) {
/* 323 */     this.completeReview = completeReview;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.task.Task
 * JD-Core Version:    0.6.2
 */