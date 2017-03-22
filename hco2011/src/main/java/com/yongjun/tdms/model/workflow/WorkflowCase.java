/*     */ package com.yongjun.tdms.model.workflow;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ 
/*     */ public class WorkflowCase extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private CodeValue statue;
/*     */   private String initiatorId;
/*     */   private String url;
/*     */   private Workflow workflow;
/*     */   private int openOrNot;
/*     */   private String remark;
/*     */ 
/*     */   public WorkflowCase()
/*     */   {
/*     */   }
/*     */ 
/*     */   public WorkflowCase(String code, String name, String initiatorId, Workflow workflow, int openOrNot, String remark)
/*     */   {
/*  84 */     this.code = code;
/*  85 */     this.name = name;
/*  86 */     this.initiatorId = initiatorId;
/*  87 */     this.workflow = workflow;
/*  88 */     this.openOrNot = openOrNot;
/*  89 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  97 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 105 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getInitiatorId()
/*     */   {
/* 113 */     return this.initiatorId;
/*     */   }
/*     */ 
/*     */   public void setInitiatorId(String initiatorId)
/*     */   {
/* 121 */     this.initiatorId = initiatorId;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 129 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 137 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public int getOpenOrNot()
/*     */   {
/* 145 */     return this.openOrNot;
/*     */   }
/*     */ 
/*     */   public void setOpenOrNot(int openOrNot)
/*     */   {
/* 153 */     this.openOrNot = openOrNot;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 161 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 169 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public Workflow getWorkflow()
/*     */   {
/* 177 */     return this.workflow;
/*     */   }
/*     */ 
/*     */   public void setWorkflow(Workflow workflow)
/*     */   {
/* 185 */     this.workflow = workflow;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 204 */     return 0;
/*     */   }
/*     */ 
/*     */   public String toString(WorkflowCase workflowCase)
/*     */   {
/* 215 */     StringBuffer stringBuffer = new StringBuffer();
/* 216 */     stringBuffer.append("id=" + workflowCase.getId() + ", ");
/* 217 */     stringBuffer.append("code=" + workflowCase.getCode() + ", ");
/* 218 */     stringBuffer.append("name=" + workflowCase.getName() + ", ");
/* 219 */     stringBuffer.append("initiatorId=" + workflowCase.getInitiatorId() + ", ");
/* 220 */     stringBuffer.append("url=" + workflowCase.getUrl() + ", ");
/* 221 */     stringBuffer.append("workflow=" + workflowCase.getWorkflow().getName() + ", ");
/* 222 */     stringBuffer.append("openOrNot=" + workflowCase.getOpenOrNot() + ", ");
/* 223 */     stringBuffer.append("remark=" + workflowCase.getRemark() + ", ");
/* 224 */     stringBuffer.append("disabled=" + workflowCase.getDisabled());
/* 225 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public CodeValue getStatue()
/*     */   {
/* 233 */     return this.statue;
/*     */   }
/*     */ 
/*     */   public void setStatue(CodeValue statue)
/*     */   {
/* 241 */     this.statue = statue;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/* 249 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 257 */     this.url = url;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workflow.WorkflowCase
 * JD-Core Version:    0.6.2
 */