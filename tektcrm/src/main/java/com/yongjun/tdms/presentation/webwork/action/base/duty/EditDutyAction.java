package com.yongjun.tdms.presentation.webwork.action.base.duty;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.base.jobs.Jobs;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.jobs.JobsManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class EditDutyAction extends PrepareAction {
	private static final long serialVersionUID = -5962205322448741405L;
	private final DutyManager dutyManager;
	private final JobsManager jobsManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private Duty duty;
	private Long departmentId;
	private Long deptId;


	public EditDutyAction(DutyManager dutyManager, DepartmentManager departmentManager, JobsManager jobsManager,
			UserManager userManager, CodeValueManager codeValueManager) {
		this.dutyManager = dutyManager;
		this.departmentManager = departmentManager;
		this.jobsManager = jobsManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if ((this.duty == null) && (hasId("duty.id"))) {
			this.duty = this.dutyManager.loadDuty(getId("duty.id"));
		} else
			this.duty = new Duty();
	}

	public List<Jobs> getAllJobNames() {
		List jobs = null;
		try {
			jobs = this.jobsManager.loadByKey("disabled", Boolean.valueOf(false));
			if (jobs == null){
				jobs = new ArrayList();
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return jobs;
	}

	public List<Department> getAllDepts() {
		List depts = this.departmentManager.loadAllDepartments();
		if (depts == null) {
			depts = new ArrayList();
		}
		return depts;
	}

	public String save() {
		boolean isNew = this.duty.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("jobName.id"))) {
			this.duty.setJobName(this.jobsManager.loadJobs(Long.valueOf(this.request.getParameter("jobName.id"))));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("dept"))) {
			this.duty.setDept(this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept"))));
		} else {
			this.duty
					.setDept(this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept.id"))));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("perType.id"))) {
			this.duty.setPerType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("perType.id"))));
		}

		this.duty.setOrganization(this.userManager.getUser().getOrganization());
		try {
			this.dutyManager.storeDuty(this.duty);
			if (isNew) {
				addActionMessage(getText("duty.add.success", Arrays.asList(new Object[] { this.duty.getCode() })));

				return "new";
			}

			addActionMessage(getText("duty.edit.success", Arrays.asList(new Object[] { this.duty.getCode() })));

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("code.add.exist", Arrays.asList(new Object[] { this.duty.getCode() })));
		}
		return "error";
	}

	public List<CodeValue> getAllPerType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("049"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public Duty getDuty() {
		return this.duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public Long getDepartmentId() {
		if (hasId("dept.id")) {
			return getId("dept.id");
		}
		return null;
	}

	public Long getDeptId() {
		if (hasId("dept")) {
			return getId("dept");
		}
		return null;
	}
}
