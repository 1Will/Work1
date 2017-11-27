package com.yongjun.tdms.presentation.webwork.action.workReport.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.daily.Daily;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.daily.DailyManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListDailyAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final DailyManager dailyManager;
	private final UserManager userManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final InstitutionManager institutionManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	protected final GroupManager groupManager;
	private List<Daily> dailys;
	private Long orgId;
	private User loginUser;
	private String isSuperSys;
	private Long weeklyId;
	private Long rapporteurId;

	public ListDailyAction(DailyManager dailyManager, UserManager userManager,
			PersonnelFilesManager personnelFilesManager, InstitutionManager institutionManager,
			DepartmentManager departmentManager, CodeValueManager codeValueManager, GroupManager groupManager) {
		this.dailyManager = dailyManager;
		this.userManager = userManager;
		this.personnelFilesManager = personnelFilesManager;
		this.institutionManager = institutionManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.groupManager = groupManager;
	}

	protected String getAdapterName() {
		return "dailyHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		User user = getUser();
//		PersonnelFiles personnelFiles = null;
//		List<PersonnelFiles> list = null;
//		try {
//			list = this.personnelFilesManager.loadByKey("code", user.getCode());
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//		if (list != null && list.size() > 0) {
//			personnelFiles = list.get(0);
//		}
		if (hasId("month")) {
			String month = request.getParameter("month") + "%";
			map.put("month", month);
			map.put("submitNum", "return");
		}

		if (user.getLoginName().trim().equals("admin")) {
			return map;
		}
//		if (isDailyGroup()) {
//			if (personnelFiles.getBusinessType() != null && personnelFiles.getBusinessType().getName().equals("军品")) {
//				map.put("userids", getUserId("军品"));
//			} else if (personnelFiles.getBusinessType() != null
//					&& personnelFiles.getBusinessType().getName().equals("民品")) {
//				map.put("userids", getUserId("民品"));
//			}
//		} else {
//			map.put("user.id", user.getId());
//		}

		// String flag = permission();
		//
		// if (flag.equals("0")) {
		// map.put("user.id", user.getId());
		// }
		// else
		// {
		// List deptIds = getDeptsByuserId(user);
		// if ((null != deptIds) && (!deptIds.isEmpty())) {
		// map.put("deptIds", deptIds);
		// } else {
		// deptIds = new ArrayList();
		// deptIds.add(Long.valueOf(1L));
		// map.put("deptIds", deptIds);
		// }
		// map.put("deptList", getDeptsByuserId(user));
		// }
		// List dailyIds = getPerDailyIds(user);
		// if ((null != dailyIds) && (!dailyIds.isEmpty())) {
		// map.put("dailyIds", dailyIds);
		// } else {
		// dailyIds = new ArrayList();
		// dailyIds.add(Long.valueOf(1L));
		// map.put("dailyIds", dailyIds);
		// }
		// map.put("deptList", getDeptsByuserId(user));
		// }

		if (null != this.request.getParameter("daily.id")) {
			Long rdId = Long.valueOf(this.request.getParameter("daily.id"));
			map.put("daily.id", rdId);
		}
		if (request.getParameter("personnelFiles.id") != null && !" ".equals(request.getParameter("personnelFiles.id"))) {
			String pcode = this.personnelFilesManager.loadPersonnel(Long.parseLong(request.getParameter("personnelFiles.id"))).getCode();
			map.put("pcode", pcode);
			map.remove("user.id");
			
		}
		return map;
	}

	public List<Long> getPerDailyIds(User user) {
		Department userDept = user.getDepartment();
		Long deptId = user.getDepartment().getId();
		Set childDeptList = userDept.getChildDepts();
		List<Daily> dailyList = this.dailyManager.loadAllDaily();
		List dailyIds = new ArrayList();
		dailyIds.clear();
		if ((null != dailyList) && (!dailyList.isEmpty())) {
			for (Daily dObj : dailyList) {
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

	public String permission() {
		String flag = "0";
		if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser()))
				|| (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理"))) {
			flag = "2";
		} else if (dutyByUser(getUser()).equals("经理"))
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
			dutyName = personnelFiles.getDuty().getPerType().getName().trim();
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

	public void prepare() throws Exception {
		this.orgId = this.userManager.getUser().getOrganization().getId();

		if (hasIds("dailyIdds")) {
			this.dailys = this.dailyManager.loadAllDaily(getIds("dailyIdds"));
		}

		if (this.request.getParameter("weekly.id") != null) {
			setFirst(false);
			this.weeklyId = Long.parseLong(this.request.getParameter("weekly.id"));
		}
		if (this.request.getParameter("rapporteur.id") != null) {
			this.rapporteurId = Long.parseLong(this.request.getParameter("rapporteur.id"));
		}

		boolean b = isSuperManager();
		if (b)
			this.isSuperSys = "true";
		else
			this.isSuperSys = "false";
	}

	private String delete() {
		try {
			this.dailyManager.deleteAllDaily(this.dailys);
			addActionMessage(getText("daily.delete.success"));
		} catch (Exception e) {
			addActionError(getText("daily.delete.error"));
		}
		return "success";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public List<Institution> getAllInsts() {
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

	public boolean isSuperManager() {
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

	public List<CodeValue> getAllReportType() {
		List reportTypeList = new ArrayList();
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { String.valueOf("049"), Boolean.valueOf(false) };
			List reportType = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (reportType != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) reportType.get(0)).getId(), Boolean.valueOf(false) };
				reportTypeList = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
			}
		} catch (DaoException e) {
			return new ArrayList();
		}
		CodeValue cv = new CodeValue();
		cv.setId(Long.valueOf(-1L));
		cv.setName(getText("select.option.all"));
		reportTypeList.add(0, cv);
		return reportTypeList;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public User getLoginUser() {
		this.loginUser = this.userManager.getUser();
		return this.loginUser;
	}

	public void setLoginUser(User u) {
		this.loginUser = u;
	}

	public String getRapporteur() {
		return this.request.getParameter("rapporteur.name");
	}

	public String getIsSuperSys() {
		return this.isSuperSys;
	}

	public Long getWeeklyId() {
		return weeklyId;
	}

	public void setWeeklyId(Long weeklyId) {
		this.weeklyId = weeklyId;
	}

	public Long getRapporteurId() {
		return rapporteurId;
	}

	public void setRapporteurId(Long rapporteurId) {
		this.rapporteurId = rapporteurId;
	}

	public boolean isDailyGroup() {
		boolean isDailyGroup = false;
		Set<User> noticePers = groupManager.getGroupByGroupName("微信日报通知组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
			System.out.println(u.getId() + "==" + user.getId());
			if (user.getId().longValue() == u.getId().longValue()) {
				isDailyGroup = true;
			}
		}

		return isDailyGroup;
	}

	// 根据人数档案军品民品属性code查询所有id
	public List<Long> getUserId(String name) {
		List<Long> uList = new ArrayList<Long>();
		try {
			List<PersonnelFiles> personnelFiles = this.personnelFilesManager.loadByKey("businessType.name", name);

			if (personnelFiles != null && personnelFiles.size() > 0) {
				for (PersonnelFiles p : personnelFiles) {
					List<User> users = this.userManager.loadByKey("code", p.getCode());
					if (users != null && users.size() > 0) {
						uList.add(users.get(0).getId());
					}
				}
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uList;
	}

}
