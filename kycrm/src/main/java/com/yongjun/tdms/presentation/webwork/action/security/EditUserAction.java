package com.yongjun.tdms.presentation.webwork.action.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.pluto.model.security.GroupType;
import com.yongjun.pluto.model.security.Role;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.UserAuthorization;
import com.yongjun.pluto.model.security.UserType;
import com.yongjun.pluto.service.PersisteceManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.RoleManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.LocaleBuilder;
import com.yongjun.pluto.webwork.action.security.AuthorizationAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

public class EditUserAction extends AuthorizationAction {
	private static final long serialVersionUID = -8852779886329075673L;
	protected final UserManager userManager;
	protected final LocaleBuilder localeBuilder;
	protected final GroupManager groupManager;
	protected final RoleManager roleManager;
	protected final DepartmentManager departmentManager;
	private final InstitutionManager institutionManager;
	private final PersonnelFilesManager personnelFilesManager;
	private User user;
	private Department department;
	private String newPassword;
	private String confirmPassword;
	private Group group;
	private List<Group> groups;
	private Role role;
	private List<User> users;
	private List<Role> roles;
	private List<UserAuthorization> authorizations;
	private List<Department> departments;

	public EditUserAction(UserManager userManager, PersisteceManager persisteceManager, LocaleBuilder localeBuilder,
			GroupManager groupManager, RoleManager roleManager, DepartmentManager departmentManager,
			InstitutionManager institutionManager, PersonnelFilesManager personnelFilesManager) {
		super(persisteceManager);
		this.userManager = userManager;
		this.localeBuilder = localeBuilder;
		this.groupManager = groupManager;
		this.roleManager = roleManager;
		this.departmentManager = departmentManager;
		this.institutionManager = institutionManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public String save() throws IOException {
		if (isChangePassword()) {
			return changePassword();
		}

		if (isDisable()) {
			return disable();
		}

		if (isRevoke()) {
			return revoke();
		}

		if (isJoin()) {
			return join();
		}

		if (isLeave()) {
			return leave();
		}

		if (isGrantRole()) {
			return grantRole();
		}

		if (isRevokeRole()) {
			if (hasIds("grantedRoleCodes")) {
				String roleCodes = this.request.getParameter("grantedRoleCodes");
				String[] roleCode = roleCodes.split(",");
				this.roles = this.roleManager.getRolesByRoleCodes(roleCode);
			}
			return revokeRole();
		}

		if (isEnabled()) {
			enabled();
		}

		if (isRevokeDept()) {
			return revokeDept();
		}

		boolean isNew = this.user.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("personCode"))) {
			this.user.setCode(this.request.getParameter("personCode"));
		}

		if (hasId("department.id")) {
			this.department = this.departmentManager.loadDepartment(getId("department.id"));

			this.user.setDepartment(this.department);
		}

		if (hasId("institution.id")) {
			Institution institution = this.institutionManager.loadInstitution(getId("institution.id"));

			this.user.setInstitustion(institution);
		}

		String viewAll = this.request.getParameter("viewall.option");
		if (!StringUtils.isEmpty(viewAll)) {
			if ("Y".equals(viewAll))
				this.user.setViewAll(true);
			else {
				this.user.setViewAll(false);
			}
		}

		this.user.setUserType(UserType.SYSTEM_USER);
		if (!StringUtils.isEmpty(this.request.getParameter("privilegeUser")))
			this.user.setPrivilegeUser(true);
		else {
			this.user.setPrivilegeUser(false);
		}

		if (StringUtils.isEmpty(this.newPassword)) {
			this.user.setOrganization(this.userManager.getUser().getOrganization());

			this.userManager.storeUser(this.user);
			updatePersonnelFiles();
		} else {
			this.user.setOrganization(this.userManager.getUser().getOrganization());

			if (null != this.userManager.getUserByLoginName(this.user.getLoginName())) {
				addActionError(getText("user.loginName.repeat",
						Arrays.asList(new Object[] { this.user.getLoginName() })));

				return "error";
			}
			this.userManager.changePassword(this.user, this.newPassword);
		}

		if (isNew) {
			addActionMessage(getText("user.add.success", Arrays.asList(new Object[] { this.user.getName() })));

			return "new";
		}
		addActionMessage(getText("user.edit.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "success";
	}

	public void updatePersonnelFiles() {
		PersonnelFiles personnelFiles = new PersonnelFiles();
		List pfList = null;
		try {
			pfList = this.personnelFilesManager.loadByKey("code", this.user.getCode().trim());
		} catch (Exception e) {
			this.logger.info("查询用户对应的人事档案出错！");
		}
		if ((null == pfList) || (pfList.isEmpty())) {
			return;
		}
		personnelFiles = (PersonnelFiles) pfList.get(0);

		personnelFiles.setName(this.user.getName().trim());
		personnelFiles.setBirthday(this.user.getBrith());
		personnelFiles.setEmail(this.user.getEmail().trim());
		personnelFiles.setMobile(this.user.getTelphoneNumber().trim());
		this.personnelFilesManager.storePersonnel(personnelFiles);
	}

	private boolean isGrantRole() {
		return hasKey("grant_role");
	}

	private boolean isRevokeRole() {
		return hasKey("revoke_role");
	}

	private boolean isJoin() {
		return hasKey("join");
	}

	private boolean isLeave() {
		return hasKey("leave");
	}

	private boolean isChangePassword() {
		return hasKey("change_password");
	}

	private boolean isDisable() {
		return hasKey("disable");
	}

	private boolean isRevoke() {
		return hasKey("revoke");
	}

	private boolean isEnabled() {
		return hasKey("enabled");
	}

	private boolean isRevokeDept() {
		return hasKey("revokeDept");
	}

	private String enabled() {
		this.user.enable();
		this.userManager.storeUser(this.user);
		return "success";
	}

	private String revoke() {
		for (UserAuthorization userAuthorization : this.authorizations) {
			this.user.revokeAuthorization(userAuthorization);
		}
		this.userManager.storeUser(this.user);
		addActionMessage(getText("user.revoke.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "new";
	}

	private String disable() {
		this.user.disable();
		this.userManager.storeUser(this.user);
		addActionMessage(getText("user.disable.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "success";
	}

	public String changePassword() {
		this.userManager.changePassword(this.user, this.newPassword);
		addActionMessage(getText("user.changePassword.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "success";
	}

	public String join() {
		if (hasIds("availableGroupIds")) {
			String availableGroupIds = this.request.getParameter("availableGroupIds");

			String[] availableGroupId = availableGroupIds.split(",");
			Long[] result = new Long[availableGroupId.length];
			for (int i = 0; i < availableGroupId.length; i++) {
				result[i] = Long.valueOf(availableGroupId[i]);
			}
			for (int i = 0; i < result.length; i++) {
				this.group = this.groupManager.loadGroup(result[i]);
				this.groupManager.loadUsers(this.group);
				this.group.addUser(this.user);
				this.groupManager.storeGroup(this.group);
			}
		}
		addActionMessage(getText("user.join.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "new";
	}

	public String leave() {
		if (hasIds("grantedGroupIds")) {
			String grantedGroupIds = this.request.getParameter("grantedGroupIds");
			String[] grantedGroupId = grantedGroupIds.split(",");
			Long[] result = new Long[grantedGroupId.length];
			for (int i = 0; i < grantedGroupId.length; i++) {
				result[i] = Long.valueOf(grantedGroupId[i]);
			}
			this.groups = this.groupManager.loadAllGroups(result);
		}
		for (Group group : this.groups) {
			group.removeUser(this.user);
			this.groupManager.storeGroup(group);
		}

		addActionMessage(getText("user.leave.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "new";
	}

	public String grantRole() {
		if (hasIds("availableRoleCodes")) {
			String roleCodes = this.request.getParameter("availableRoleCodes");
			this.roleManager.grantRolesForUser(roleCodes, this.user);
		}

		addActionMessage(getText("user.grantRole.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "new";
	}

	public String revokeRole() {
		for (Role role : this.roles) {
			this.user.revokeRole(role);
		}

		this.userManager.storeUser(this.user);
		addActionMessage(getText("user.revokeRole.success", Arrays.asList(new Object[] { this.user.getName() })));

		return "new";
	}

	private String revokeDept() {
		for (Iterator i$ = this.departments.iterator(); i$.hasNext();) {
			Department dept = (Department) i$.next();
			dept.removeUser(this.user);
			this.departmentManager.storeDepartment(dept);
		}
		return "new";
	}

	public void prepare() throws Exception {
		if (this.user == null) {
			if (this.hasId("user.id")) {
				this.user = this.userManager.loadUser(this.getId("user.id"));
				this.userManager.loadAuthorizations(this.user);
				this.userManager.loadGroups(this.user);
				this.userManager.loadRoles(this.user);
			} else {
				this.user = new User();
			}
		}
		if (this.authorizations == null) {
			if (this.hasIds("authorizationIds")) {
				// this.authorizations =
				// this.userManager.loadAllAuthorizations(this.getIds("authorizationIds"));
			}
		}

		if (this.groups == null) {
			if (this.hasIds("groupIds")) {
				this.groups = this.groupManager.loadAllGroups(this.getIds("groupIds"));
				for (Group group : groups) {
					this.groupManager.loadUsers(group);
				}
			}
		}

		if (this.role == null) {
			if (this.hasId("role.id")) {
				this.role = this.roleManager.loadRole(this.getId("role.id"));
			}
		}

		if (this.roles == null) {
			if (this.hasIds("roleIds")) {
				this.roles = this.roleManager.loadAllRoles(this.getIds("roleIds"));
			}
		}

		if (this.departments == null) {
			if (this.hasIds("deptIds")) {
				this.departments = departmentManager.loadAllDepartments(this.getIds("deptIds"));
			}
		}
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNewPassword() {
		return this.newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Locale> getLocales() {
		return this.localeBuilder.getLocales();
	}

	public List<Group> getJoinableAuthorityGroups() {
		List groups = this.groupManager.getGroupsByGroupType(GroupType.AUTHORITY_GROUP);

		groups.removeAll(this.user.getGroups());
		return groups;
	}

	public List<Group> getJoinableCommunicationGroups() {
		List groups = this.groupManager.getGroupsByGroupType(GroupType.COMMUNICATION_GROUP);

		groups.removeAll(this.user.getGroups());
		return groups;
	}

	public List<Role> getGrantableRoles() {
		List roles = this.roleManager.loadAllRoles();
		roles.removeAll(this.user.getRoles());
		return roles;
	}

	public List<UserAuthorization> getUserSortAuthorization() {
		List list = new ArrayList();
		Map<String, List> map = new HashMap<String, List>();
		for (UserAuthorization ua : this.user.getAuthorizations()) {
			List uas = (List) map.get(ua.getPermission().getIdentity().getClazz().getName());

			if (uas == null) {
				uas = new ArrayList();
				map.put(ua.getPermission().getIdentity().getClazz().getName(), uas);
			}

			uas.add(ua);
		}
		for (List uas : map.values()) {
			list.addAll(uas);
		}
		return list;
	}

	public List<Department> getDepartments() {
		boolean isNew = this.user.isNew();
		String name = "";
		long id = 0L;
		if (!isNew) {
			name = this.user.getDepartment().getName();
			id = this.user.getDepartment().getId().longValue();
		}
		return this.departmentManager.getDepartments(isNew, name, Long.valueOf(id));
	}

	public List<LabelValue> getUserType() {
		LabelValue[] arrays = wrapEnum(UserType.class);
		List tmp = new ArrayList();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public List getInstitutions() {
		return this.institutionManager.getInstitutions();
	}

}
