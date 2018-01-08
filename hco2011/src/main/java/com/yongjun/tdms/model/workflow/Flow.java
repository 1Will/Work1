package com.yongjun.tdms.model.workflow;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import java.util.Set;

public class Flow extends BaseInfoEntity
		implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private Department department;
	private float allowTime;
	private Set<Point> pointSet;
	private int openOrNot;
	private String remark;
	private CodeValue flowCode;//流程编码

	public Flow() {
	}

	public Flow(String code, String name, float allowTime, int openOrNot, String remark, Set<Point> pointSet,
			Department department) {
		this.code = code;
		this.name = name;
		this.allowTime = allowTime;
		this.openOrNot = openOrNot;
		this.remark = remark;
		this.pointSet = pointSet;
		this.department = department;
	}

	public float getAllowTime() {
		return this.allowTime;
	}

	public void setAllowTime(float allowTime) {
		this.allowTime = allowTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getOpenOrNot() {
		return this.openOrNot;
	}

	public void setOpenOrNot(int openOrNot) {
		this.openOrNot = openOrNot;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Point> getPointSet() {
		return this.pointSet;
	}

	public void setPointSet(Set<Point> pointSet) {
		this.pointSet = pointSet;
	}

	public boolean equals(Object obj) {
		return false;
	}

	public int hashCode() {
		return 0;
	}
	
	public CodeValue getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(CodeValue flowCode) {
		this.flowCode = flowCode;
	}

	public String toString(Flow flow) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("id=" + flow.getId() + ", ");
		stringBuffer.append("code=" + flow.getCode() + ", ");
		stringBuffer.append("name=" + flow.getName() + ", ");
		stringBuffer.append("department=" + flow.getDepartment().getName() + ", ");
		stringBuffer.append("allowTime=" + flow.getAllowTime() + ", ");
		stringBuffer.append("openOrNot=" + flow.getOpenOrNot() + ", ");
		stringBuffer.append("remark=" + flow.getRemark() + ", ");
		stringBuffer.append("disabled=" + flow.getDisabled());
		return stringBuffer.toString();
	}
}
