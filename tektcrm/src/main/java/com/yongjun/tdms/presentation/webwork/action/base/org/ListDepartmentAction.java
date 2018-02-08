package com.yongjun.tdms.presentation.webwork.action.base.org;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.DomainModel;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class ListDepartmentAction extends ValueListAction {
	private static final long serialVersionUID = -557026634791401535L;
	private final DepartmentManager departmentManager;
	private final InstitutionManager institutionManager;
	private List<Department> department;
	private List<Institution> institutions;

	public ListDepartmentAction(DepartmentManager departmentManager, InstitutionManager institutionManager) {
		this.departmentManager = departmentManager;
		this.institutionManager = institutionManager;
	}

	public void prepare() {
		if ((null == this.department) && (hasIds("departmentIds"))) {
			this.department = this.departmentManager.loadAllDepartments(getIds("departmentIds"));
		}
		if (hasId("institution.id"))
			setFirst(false);
	}

	public String execute() {
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}

		if (isDelete()) {
			try {
				delete();
			} catch (Exception e) {
				addActionError(getText("department.delete.failed"));
				return "error";
			}
		}
		return "success";
	}

	public void delete() {
		this.departmentManager.deleteAllDepartments(this.department);

		getLogger().logStore((DomainModel) this.department.get(0),
				"(部门代码:" + logContentDepartment(this.department) + "的部门)被删除", "department_manager");
		addActionMessage(getText("department.delete.success"));
	}

	private String logContentDepartment(List<Department> department) {
		String delDept = "";
		Integer index = null;
		for (Department dept : department) {
			delDept = delDept + dept.getCode() + ",";
		}
		index = Integer.valueOf(delDept.lastIndexOf(","));
		delDept = delDept.substring(0, index.intValue());
		return delDept;
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public List<Department> getDepartment() {
		return this.department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	protected String getAdapterName() {
		return "department";
	}

	public List<Department> getParentDepts() {
		List depts = this.departmentManager.createSelectParentGroups(getText("select.option.non"));
		if (depts != null && depts.size() > 0) {
			return depts;
		} else {
			return new ArrayList<Department>();
		}
	}

	public List<Institution> getParentInsts() {
		List insts = this.institutionManager.loadAllInstitution(getText("select.option.all"));
		if (insts != null && insts.size() > 0) {
			return insts;
		} else {
			return new ArrayList<Institution>();
		}
	}

	private String disabled() {
		try {
			this.departmentManager.disabledAllDepartment(this.department);
			addActionMessage(getText("department.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("department.disabled.failer"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.departmentManager.enabledAllDepartment(this.department);
			addActionMessage(getText("department.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("department.enabled.failer"));
		}
		return "success";
	}

	public List<Institution> getInstitutions() {
		return this.institutions;
	}

	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}
}
