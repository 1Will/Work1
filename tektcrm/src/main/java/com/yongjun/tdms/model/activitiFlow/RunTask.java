/*     */ package com.yongjun.tdms.model.activitiFlow;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;

import java.util.Date;
import java.util.Set;
/*     */ /**
 * 当前任务列表
 * @author Administrator
 *
 */
/*     */ public class RunTask extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long bussnessId;//绑定业务  可能是请假id 报销id 等等
/*     */   private String name;//任务名称
			private Flow flow;//所属流程
			private PersonnelFiles submitPer;//提交人
			private PersonnelFiles lastAssignPer;//最后审批人
			private Date assignTime;//审批时间
			private Date submitTime;//提交时间
			private Date createTime;//创建时间
			private String content;//业务主体内容
			private PersonnelFiles assignee;//任务办理人
			private String isSaved;// 提交判断
			private int myNum;
			private String linkHref ;
/*     */ 
/*     */  
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 220 */     return 0;
/*     */   }
			public long getBussnessId() {
				return bussnessId;
			}
			public void setBussnessId(long bussnessId) {
				this.bussnessId = bussnessId;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public Flow getFlow() {
				return flow;
			}
			public void setFlow(Flow flow) {
				this.flow = flow;
			}
			public PersonnelFiles getSubmitPer() {
				return submitPer;
			}
			public void setSubmitPer(PersonnelFiles submitPer) {
				this.submitPer = submitPer;
			}
			public Date getSubmitTime() {
				return submitTime;
			}
			public void setSubmitTime(Date submitTime) {
				this.submitTime = submitTime;
			}
			public Date getCreateTime() {
				return createTime;
			}
			public void setCreateTime(Date createTime) {
				this.createTime = createTime;
			}
			public PersonnelFiles getAssignee() {
				return assignee;
			}
			public void setAssignee(PersonnelFiles assignee) {
				this.assignee = assignee;
			}
			public RunTask() {
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
			public RunTask(long bussnessId, String name, Flow flow, PersonnelFiles submitPer, Date submitTime,
					Date createTime, String content, PersonnelFiles assignee,int myNum,String linkHref) {
				this.bussnessId = bussnessId;
				this.name = name;
				this.flow = flow;
				this.submitPer = submitPer;
				this.submitTime = submitTime;
				this.createTime = createTime;
				this.content = content;
				this.assignee = assignee;
				this.myNum=myNum;
				this.linkHref=linkHref;
			}
			public int getMyNum() {
				return myNum;
			}
			public void setMyNum(int myNum) {
				this.myNum = myNum;
			}
			public String getLinkHref() {
				return linkHref;
			}
			public void setLinkHref(String linkHref) {
				this.linkHref = linkHref;
			}
			public PersonnelFiles getLastAssignPer() {
				return lastAssignPer;
			}
			public void setLastAssignPer(PersonnelFiles lastAssignPer) {
				this.lastAssignPer = lastAssignPer;
			}
			public Date getAssignTime() {
				return assignTime;
			}
			public void setAssignTime(Date assignTime) {
				this.assignTime = assignTime;
			}
			public String getIsSaved() {
				return isSaved;
			}
			public void setIsSaved(String isSaved) {
				this.isSaved = isSaved;
			}
			
			

/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workflow.Flow
 * JD-Core Version:    0.6.2
 */