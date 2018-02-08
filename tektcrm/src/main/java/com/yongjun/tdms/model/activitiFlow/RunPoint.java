/*     */ package com.yongjun.tdms.model.activitiFlow;
/*     */ 
/*     */ import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;
/*     */ 
/*     */ public class RunPoint extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long bussnessId;//绑定业务  可能是请假id 报销id 等等
/*     */   private int myNum;//流程任务序号
/*     */   private PersonnelFiles inspectPser;//审核人
/*     */   private Flow flow;//流程类型
            private String name;//节点名称
/*     */   private CodeValue result;//审批节点状态
			private Date submitTime;//提交时间
/*     */   private Date inspectTime;//审核时间
/*     */   private String remark;//审核意见
			private String linkHref ;
			private String comments;//备注
			private long duration;//用时,秒数
/*     */ 
/*     */   public RunPoint()
/*     */   {
/*     */   }
/*     */ 
/*     */ 
/*     */
			public boolean equals(Object arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public int hashCode() {
				// TODO Auto-generated method stub
				return 0;
			}
			public long getBussnessId() {
				return bussnessId;
			}
			public void setBussnessId(long bussnessId) {
				this.bussnessId = bussnessId;
			}
			public int getMyNum() {
				return myNum;
			}
			public void setMyNum(int myNum) {
				this.myNum = myNum;
			}
			public PersonnelFiles getInspectPser() {
				return inspectPser;
			}
			public void setInspectPser(PersonnelFiles inspectPser) {
				this.inspectPser = inspectPser;
			}
			public Flow getFlow() {
				return flow;
			}
			public void setFlow(Flow flow) {
				this.flow = flow;
			}
			public Date getInspectTime() {
				return inspectTime;
			}
			public void setInspectTime(Date inspectTime) {
				this.inspectTime = inspectTime;
			}
			public String getRemark() {
				return remark;
			}
			public void setRemark(String remark) {
				this.remark = remark;
			}
			public RunPoint(long bussnessId, int myNum, PersonnelFiles inspectPser, Flow flow, CodeValue result,
					Date inspectTime, String remark,String linkHref, String comments ) {
				this.bussnessId = bussnessId;
				this.myNum = myNum;
				this.inspectPser = inspectPser;
				this.flow = flow;
				this.result = result;
				this.inspectTime = inspectTime;
				this.remark = remark;
				this.linkHref =linkHref;
				this.comments=comments;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public CodeValue getResult() {
				return result;
			}
			public void setResult(CodeValue result) {
				this.result = result;
			}
			public String getLinkHref() {
				return linkHref;
			}
			public void setLinkHref(String linkHref) {
				this.linkHref = linkHref;
			}
			public String getComments() {
				return comments;
			}
			public void setComments(String comments) {
				this.comments = comments;
			}
			public long getDuration() {
				return duration;
			}
			public void setDuration(long duration) {
				this.duration = duration;
			}
			public Date getSubmitTime() {
				return submitTime;
			}
			public void setSubmitTime(Date submitTime) {
				this.submitTime = submitTime;
			}
			
}

