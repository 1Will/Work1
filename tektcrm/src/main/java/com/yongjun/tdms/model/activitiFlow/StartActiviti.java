		package com.yongjun.tdms.model.activitiFlow;
		import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;
		 public class StartActiviti extends BaseInfoEntity
		 {
			private Flow flow;//所属流程
			private long bussnessId;//业务id
			private String isSaved;//1表示提交
			private PersonnelFiles applyPerson;//申请人
			private Date createTime;//创建时间
			private String name;//当前业务名称
			private String content;//主题内容
			private String bussnessType;//业务类型 如请假单 报销单等
			private CodeValue bussnessCode;//业务需要修改成什么状态
			private String linkHref ;//业务需要跳转链接
			
			
			public Flow getFlow() {
				return flow;
			}
			public void setFlow(Flow flow) {
				this.flow = flow;
			}
			public long getBussnessId() {
				return bussnessId;
			}
			public void setBussnessId(long bussnessId) {
				this.bussnessId = bussnessId;
			}
			public String getIsSaved() {
				return isSaved;
			}
			public void setIsSaved(String isSaved) {
				this.isSaved = isSaved;
			}
			public PersonnelFiles getApplyPerson() {
				return applyPerson;
			}
			public void setApplyPerson(PersonnelFiles applyPerson) {
				this.applyPerson = applyPerson;
			}
			public Date getCreateTime() {
				return createTime;
			}
			public void setCreateTime(Date createTime) {
				this.createTime = createTime;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
			@Override
			public boolean equals(Object arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public int hashCode() {
				// TODO Auto-generated method stub
				return 0;
			}
			public String getBussnessType() {
				return bussnessType;
			}
			public void setBussnessType(String bussnessType) {
				this.bussnessType = bussnessType;
			}
			public CodeValue getBussnessCode() {
				return bussnessCode;
			}
			public void setBussnessCode(CodeValue bussnessCode) {
				this.bussnessCode = bussnessCode;
			}
			public String getLinkHref() {
				return linkHref;
			}
			public void setLinkHref(String linkHref) {
				this.linkHref = linkHref;
			}
			
			
			
			
					
					
					
		}

