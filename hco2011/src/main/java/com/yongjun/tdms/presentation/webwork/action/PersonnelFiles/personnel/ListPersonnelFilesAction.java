package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListPersonnelFilesAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private InstitutionManager institutionManager;
	private UserManager userManager;
	private DutyManager dutyManager;
	private PersonnelFilesManager personnelFilesManager;
	private final DepartmentManager departmentManager;
	protected final GroupManager groupManager;
	protected final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private List<PersonnelFiles> pfList;
	private String backVisitCheckBox;
	private String sysUser;
	private String popWindowFlag;
	private String employeesIds;
	private String notOpen;

	public ListPersonnelFilesAction(InstitutionManager institutionManager, UserManager userManager,
			DutyManager dutyManager, PersonnelFilesManager personnelFilesManager, DepartmentManager departmentManager,
			GroupManager groupManager, ProjectInfoPersonnelsManager projectInfoPersonnelsManager) {
		this.institutionManager = institutionManager;
		this.userManager = userManager;
		this.dutyManager = dutyManager;
		this.personnelFilesManager = personnelFilesManager;
		this.departmentManager = departmentManager;
		this.groupManager = groupManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
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

	public void prepare() throws Exception {
		if (hasIds("personnelFileIds")) {
			this.pfList = this.personnelFilesManager.loadAllPersonnel(getIds("personnelFileIds"));
		}

		if (hasId("sysUser"))
			this.sysUser = this.request.getParameter("sysUser");
		if (this.request.getParameter("backVisitCheckBox") != null) {
			this.backVisitCheckBox = this.request.getParameter("backVisitCheckBox");
		}
		if (hasId("popWindowFlag")) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
		if (request.getParameter("employeesIds_a") != null || "".equals(request.getParameter("employeesIds_a"))) {
			this.employeesIds = request.getParameter("employeesIds_a");
		}
		if (hasId("sysUser")) {
			this.notOpen = this.request.getParameter("notOpen");
		}
	}

	public List<Institution> getAllInsts() {
		List list = this.institutionManager.loadAllInstitution();

		Institution agency = new Institution();
		agency.setId(Long.valueOf(-1L));
		agency.setName(getText("select.option.all"));
		list.add(0, agency);
		return list;
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
		this.personnelFilesManager.disabledPersonnels(this.pfList);
		addActionMessage(getText("personnel.disable.success"));
		return "success";
	}

	public String delete() {
		try {
			this.personnelFilesManager.deleteAllPersonnel(this.pfList);
			addActionMessage(getText("personnel.delete.success"));
		} catch (Exception e) {
			addActionMessage(getText("personnel.delete.error"));
		}
		return "success";
	}

	public String enable() {
		this.personnelFilesManager.enabledPersonnels(this.pfList);
		addActionMessage(getText("personnel.enable.success"));
		return "success";
	}

	protected String getAdapterName() {
		return "personnelFilesHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		User user = this.userManager.getUser();
		PersonnelFiles personnelFiles = null;
		if (this.notOpen != null && this.notOpen.equals("notOpen")) {
			if (!isPersonnelGroup()) {
				map.put("code", user.getCode());
			}
		}
		try {
			List<PersonnelFiles> tempList = this.personnelFilesManager.loadByKey("code", user.getCode());
			if (tempList != null && tempList.size() > 0) {
				personnelFiles = tempList.get(0);
				if (personnelFiles.getBusinessType() != null) {
					if (personnelFiles.getBusinessType().getName().equals("军品")) {
						map.put("businessType", "%军%");
					} else if (personnelFiles.getBusinessType().getName().equals("民品")) {
						map.put("businessType", "%民%");

					}
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}

		if ((hasId("sysUser"))) {
			List users = this.userManager.loadValidUsers();
			List codes = new ArrayList();
			for (int i = 0; i < users.size(); i++) {
				if (((User) users.get(i)).getCode() != null) {
					codes.add(((User) users.get(i)).getCode());
				}
			}
			if (codes.size() > 0) {
				map.put("validUser", codes);
			}
		}

		if (hasId("projectInfo.id")) {
			try {
				List<String> validUser = new ArrayList<String>();
				List<ProjectInfoPersonnels> projectInfoPersonnelses = this.projectInfoPersonnelsManager.loadByKey(
						"projectInfo.id", getId("projectInfo.id"));
				if (projectInfoPersonnelses != null && projectInfoPersonnelses.size() > 0) {
					for (int i = 0; i < projectInfoPersonnelses.size(); i++) {
						validUser.add(projectInfoPersonnelses.get(i).getProPerson().getCode());
					}
				}
				if (validUser.size() > 0) {
					map.put("validUser", validUser);
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}

		if (hasId("onlyProject.id")) {
			try {
				List<String> users = new ArrayList<String>();
				List<ProjectInfoPersonnels> projectInfoPersonnelses = this.projectInfoPersonnelsManager.loadByKey(
						"projectInfo.id", getId("onlyProject.id"));
				if (projectInfoPersonnelses != null && projectInfoPersonnelses.size() > 0) {
					for (int i = 0; i < projectInfoPersonnelses.size(); i++) {
						users.add(projectInfoPersonnelses.get(i).getProPerson().getCode());
					}
				}
				if (users.size() > 0) {
					map.put("projectPer", users);
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	public String getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	public String getBackVisitCheckBox() {
		return backVisitCheckBox;
	}

	public void setBackVisitCheckBox(String backVisitCheckBox) {
		this.backVisitCheckBox = backVisitCheckBox;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}

	public String getEmployeesIds() {
		return employeesIds;
	}

	public void setEmployeesIds(String employeesIds) {
		this.employeesIds = employeesIds;
	}

	public boolean isPersonnelGroup() {
		boolean isPersonnelGroup = false;
		Set<User> noticePers = groupManager.getGroupByGroupName("人事档案组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
			System.out.println(u.getId() + "==" + user.getId());
			if (user.getId().longValue() == u.getId().longValue()) {
				isPersonnelGroup = true;
			}
		}

		return isPersonnelGroup;
	}

	public String getNotOpen() {
		return notOpen;
	}

	public void setNotOpen(String notOpen) {
		this.notOpen = notOpen;
	}

}
