package com.yongjun.tdms.presentation.webwork.action.workflow.flow;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.point.PointManager;

public class EditFlowAction extends PrepareAction {
	private static final long serialVersionUID = 732668911930478662L;
	private FlowManager flowManager;
	private DepartmentManager departmentManager;
	private PointManager pointManager;
	private Flow flow;

	public EditFlowAction(FlowManager flowManager, DepartmentManager departmentManager, PointManager pointManager) {
		this.flowManager = flowManager;
		this.departmentManager = departmentManager;
		this.pointManager = pointManager;
	}

	public void prepare() throws Exception {
		if (null == this.flow) {
			if (hasId("flow.id")) {
				this.flow = this.flowManager.loadFlow(getId("flow.id"));
			} else {
				this.flow = new Flow();
			}
		} else {
			this.flow = new Flow();
		}
	}

	public String save() {
		if (!StringUtils.isEmpty(this.request.getParameter("flow.department"))) {
			Department department = this.departmentManager.loadDepartment(getId("flow.department"));
			this.flow.setDepartment(department);
		}
		boolean isNew = this.flow.isNew();
		try {
			if (isNew) {
				if (null == this.flowManager.loadByKey("code", this.flow.getCode())) {
					this.flowManager.storeFlow(this.flow);
				} else {
					addActionMessage(getText("add.exist", Arrays.asList(new Object[] { this.flow.getCode() })));

					return "error";
				}
			} else {
				this.flowManager.storeFlow(this.flow);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.flow.getCode() })));

			return "error";
		}

		if (isNew) {
			addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.flow.getCode() })));

			return "new";
		}

		addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.flow.getCode() })));

		return "success";
	}

	public List<Department> getAllDepartment() {
		List departments = this.departmentManager.loadAllDepartments();
		return departments;
	}

	public Flow getFlow() {
		return this.flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}
}
