		package com.yongjun.tdms.model.activitiFlow;
		import java.util.List;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
		 public class ActiviFlow extends BaseInfoEntity
		 {
			private long runTaskId;
			private long runPointId;
			private CodeValue result;
			private PersonnelFiles transfer;//r如果是转交 则把接受人带过来
			private String remark;
			
			private List<ActiviFlow> activiFlows;
			
			public long getRunTaskId() {
				return runTaskId;
			}
			public void setRunTaskId(long runTaskId) {
				this.runTaskId = runTaskId;
			}
			public long getRunPointId() {
				return runPointId;
			}
			public void setRunPointId(long runPointId) {
				this.runPointId = runPointId;
			}
			public CodeValue getResult() {
				return result;
			}
			public void setResult(CodeValue result) {
				this.result = result;
			}
			public String getRemark() {
				return remark;
			}
			public void setRemark(String remark) {
				this.remark = remark;
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
			public PersonnelFiles getTransfer() {
				return transfer;
			}
			public void setTransfer(PersonnelFiles transfer) {
				this.transfer = transfer;
			}
			public List<ActiviFlow> getActiviFlows() {
				return activiFlows;
			}
			public void setActiviFlows(List<ActiviFlow> activiFlows) {
				this.activiFlows = activiFlows;
			}
			
		}

