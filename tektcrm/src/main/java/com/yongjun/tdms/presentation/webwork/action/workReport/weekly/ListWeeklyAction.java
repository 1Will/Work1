package com.yongjun.tdms.presentation.webwork.action.workReport.weekly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ListWeeklyAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private DutyManager dutyManager;
	private PersonnelFilesManager personnelFilesManager;
	private WeeklyManager weeklyManager;
	private final InstitutionManager institutionManager;
	private final DepartmentManager departmentManager;
	private UserManager userManager;
	private List<Weekly> weeklylist;
	private String isSuperSys;

	public ListWeeklyAction(DutyManager dutyManager, PersonnelFilesManager personnelFilesManager,
			InstitutionManager institutionManager, WeeklyManager weeklyManager, UserManager userManager,
			DepartmentManager departmentManager) {
		this.dutyManager = dutyManager;
		this.personnelFilesManager = personnelFilesManager;
		this.institutionManager = institutionManager;
		this.weeklyManager = weeklyManager;
		this.userManager = userManager;
		this.departmentManager = departmentManager;
	}

	public void prepare() throws Exception {
		if (hasIds("weeklyIds")) {
			this.weeklylist = this.weeklyManager.loadAllWeekly(getIds("weeklyIds"));
		} else {
			this.weeklylist = new ArrayList();
		}

		boolean b = isSuperAdmin();
		if (b)
			this.isSuperSys = "true";
		else
			this.isSuperSys = "false";
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			return disable();
		}
		if (isEnable()) {
			return enable();
		}
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	protected String getAdapterName() {
		return "weeklyHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		User user = getUser();
		 if(request.getParameter("personnelFiles.id")!=null && !"".equals(request.getParameter("personnelFiles.id"))){
				
			 String pCode=this.personnelFilesManager.loadPersonnel(Long.parseLong(request.getParameter("personnelFiles.id"))).getCode();
			 map.put("pcode",pCode);
			 map.remove("user.id");
		 }
		
	         if(hasId("month")){
	       
	        	 String month = request.getParameter("month")+"%";
	        	 map.put("month", month);
	        	 map.put("submitNum", "return");
	             }
		if (user.getName().trim().equals("admin")) {
			return map;
		}

		String flag = permission();

		if (flag.equals("0")) {
			map.put("user.id", user.getId());
		} else {
			List deptIds = getDeptsByuserId(user);
			if ((null != deptIds) && (!deptIds.isEmpty())) {
				map.put("deptIds", deptIds);
			} else {
				deptIds = new ArrayList();
				deptIds.add(Long.valueOf(1L));
				map.put("deptIds", deptIds);
			}
			map.put("deptList", getDeptsByuserId(user));
		}

		if (null != this.request.getParameter("dept.id")) {
			Long rdId = Long.valueOf(this.request.getParameter("dept.id"));
			map.put("dept.id", rdId);
		}
			  
		return map;
	}

	public String permission() {
		String flag = "0";
		if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser())) || (getUser().isPrivilegeUser())
				|| (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理"))) {
			flag = "2";
		} else if (dutyByUser(getUser()).equals("部门经理"))
			flag = "1";
		else {
			flag = "0";
		}
		return flag;
	}

	public String dutyByUser(User user) {
		List list = new ArrayList();
		PersonnelFiles personnelFiles = new PersonnelFiles();
		String dutyName = null;
		try {
			list = this.personnelFilesManager.loadByKey("code", user.getCode());
		} catch (DaoException e) {
			this.logger.info("查询用户对应的人事档案出错！");
		}
		if ((null != list) && (list.size() > 0)) {
			personnelFiles = (PersonnelFiles) list.get(0);
			if (null != personnelFiles.getDuty().getPerType()) {
				dutyName = personnelFiles.getDuty().getPerType().getName().trim();
			}
		}
		return dutyName;
	}

	public List<Long> getDeptsByuserId(User user) {
		List<Department> deptList = new ArrayList();
		try {
			deptList = this.departmentManager.getDeptsByUserId(getUser().getId(), "search");
		} catch (Exception e) {
			this.logger.info("查询当前用户的组织区域出错");
		}
		deptList.add(user.getDepartment());
		List deptListId = new ArrayList();
		for (Department d : deptList) {
			Long dId = d.getId();
			if (!deptListId.contains(dId)) {
				deptListId.add(dId);
			}
		}
		return deptListId;
	}

	public List<Long> getPerDailyIds(User user) {
		Department userDept = user.getDepartment();
		Long deptId = user.getDepartment().getId();
		Set childDeptList = userDept.getChildDepts();
		List<Weekly> dailyList = this.weeklyManager.loadAllWeekly();
		List dailyIds = new ArrayList();
		dailyIds.clear();
		if ((null != dailyList) && (!dailyList.isEmpty())) {
			for (Weekly dObj : dailyList) {
				if (dObj.getDept().getId().equals(deptId)) {
					dailyIds.add(dObj.getId());
				}

				if ((childDeptList.contains(dObj.getDept()))
						&& ((dObj.getDuty().getPerType().getName().trim().equals("部门经理")) || (dObj.getDuty()
								.getPerType().getName().trim().equals("公司经理")))) {
					dailyIds.add(dObj.getId());
				}
			}
		}
		if ((null != dailyIds) && (!dailyIds.isEmpty())) {
			return dailyIds;
		}
		return null;
	}

	public List<Institution> getAllInsts() {
		User user = this.userManager.loadUser(getUser().getId());
		List agencyList = this.institutionManager.loadAllInstitution();
		Institution first = new Institution();
		first.setId(Long.valueOf(-1L));
		first.setName(getText("select.option.all"));
		agencyList.add(0, first);

		return agencyList;
	}

	public List<Department> getAllDepts() {
		List list = new ArrayList();
		Department agency = new Department();
		agency.setId(Long.valueOf(-1L));
		agency.setName(getText("select.option.all"));
		list.add(0, agency);
		return list;
	}

	public List<Duty> getAllDutys() {
		List list = new ArrayList();
		Duty duty = new Duty();
		duty.setId(Long.valueOf(-1L));
		duty.setName(getText("select.option.all"));
		list.add(0, duty);
		return list;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public String disable() {
		try {
			this.weeklyManager.disabledWeekly(this.weeklylist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addActionMessage(getText("weekly.disable.success"));
		return "success";
	}

	public String delete() {
		try {
			this.weeklyManager.deleteAllWeekly(this.weeklylist);
			addActionMessage(getText("weekly.delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("weekly.delete.error"));
		}
		return "success";
	}

	public String enable() {
		this.weeklyManager.enabledWeekly(this.weeklylist);
		addActionMessage(getText("weekly.enable.success"));
		return "success";
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public boolean isSuperAdmin() {
		String[] keyNames = { "code", "organization.id" };
		Object[] keyValues = { this.userManager.getUser().getCode(),
				this.userManager.getUser().getOrganization().getId() };
		try {
			List pf = this.personnelFilesManager.loadByKeyArray(keyNames, keyValues);
			if (null != pf && pf.size() > 0)
				return false;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String getIsSuperSys() {
		return this.isSuperSys;
	}
}
