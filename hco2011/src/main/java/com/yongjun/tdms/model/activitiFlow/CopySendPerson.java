package com.yongjun.tdms.model.activitiFlow;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;

public class CopySendPerson extends BaseInfoEntity
		implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 1L;
	private long bussnessId;// 绑定业务 可能是请假id 报销id 等等
	private PersonnelFiles person;// 审核人
	private Flow flow;// 流程类型

	public CopySendPerson() {
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

	public long getBussnessId() {
		return bussnessId;
	}

	public void setBussnessId(long bussnessId) {
		this.bussnessId = bussnessId;
	}

	public PersonnelFiles getPerson() {
		return person;
	}

	public void setPerson(PersonnelFiles person) {
		this.person = person;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public CopySendPerson(long bussnessId, PersonnelFiles person, Flow flow) {
		this.bussnessId = bussnessId;
		this.person = person;
		this.flow = flow;
	}

}
