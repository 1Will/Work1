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
/*     */ /***
 * 历史任务
 * @author Administrator
 *
 */
/*     */ public class HistoryTaskinst extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */  private long bussnessId;//绑定业务  可能是请假id 报销id 等等
/*     */   private String name;//任务名称
			private Flow flow;//所属流程
			private PersonnelFiles submitPer;//提交人
			private PersonnelFiles lastAssignPer;//最后审批人
			private Date assignTime;//审批时间
			private Date startTime;//提交时间
			private Date endTime;//创建时间
			private long duration;//用时
			private String content;//业务主体内容
			private PersonnelFiles assignee;//任务办理人
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
			public Date getStartTime() {
				return startTime;
			}
			public void setStartTime(Date startTime) {
				this.startTime = startTime;
			}
			public Date getEndTime() {
				return endTime;
			}
			public void setEndTime(Date endTime) {
				this.endTime = endTime;
			}
			public long getDuration() {
				return duration;
			}
			public void setDuration(long duration) {
				this.duration = duration;
			}
			public HistoryTaskinst() {
			}
			public PersonnelFiles getAssignee() {
				return assignee;
			}
			public void setAssignee(PersonnelFiles assignee) {
				this.assignee = assignee;
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
			public HistoryTaskinst(long bussnessId, String name, Flow flow, PersonnelFiles submitPer, Date startTime,
					Date endTime, long duration, String content, PersonnelFiles assignee,int myNum,String linkHref) {
				this.bussnessId = bussnessId;
				this.name = name;
				this.flow = flow;
				this.submitPer = submitPer;
				this.startTime = startTime;
				this.endTime = endTime;
				this.duration = duration;
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
			
			

			
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workflow.Flow
 * JD-Core Version:    0.6.2
 */