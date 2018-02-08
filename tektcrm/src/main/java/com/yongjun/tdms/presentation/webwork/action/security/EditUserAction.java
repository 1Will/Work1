/*     */package com.yongjun.tdms.presentation.webwork.action.security;

/*     */
/*     */import com.yongjun.pluto.model.LabelValue;
/*     */
import com.yongjun.pluto.model.base.institution.Institution;
/*     */
import com.yongjun.pluto.model.security.Department;
/*     */
import com.yongjun.pluto.model.security.Group;
/*     */
import com.yongjun.pluto.model.security.GroupType;
/*     */
import com.yongjun.pluto.model.security.ObjectIdentity;
/*     */
import com.yongjun.pluto.model.security.Permission;
/*     */
import com.yongjun.pluto.model.security.Role;
/*     */
import com.yongjun.pluto.model.security.User;
/*     */
import com.yongjun.pluto.model.security.UserAuthorization;
/*     */
import com.yongjun.pluto.model.security.UserType;
/*     */
import com.yongjun.pluto.service.PersisteceManager;
/*     */
import com.yongjun.pluto.service.security.GroupManager;
/*     */
import com.yongjun.pluto.service.security.RoleManager;
/*     */
import com.yongjun.pluto.service.security.UserManager;
/*     */
import com.yongjun.pluto.util.LocaleBuilder;
/*     */
import com.yongjun.pluto.webwork.action.security.AuthorizationAction;
/*     */
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */
import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */
import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */
import java.io.IOException;
/*     */
import java.util.ArrayList;
/*     */
import java.util.Arrays;
/*     */
import java.util.HashMap;
/*     */
import java.util.Iterator;
/*     */
import java.util.List;
/*     */
import java.util.Locale;
/*     */
import java.util.Map;
/*     */
import javax.servlet.http.HttpServletRequest;
/*     */
import org.apache.commons.lang.StringUtils;
/*     */
import org.apache.commons.logging.Log;

/*     */
/*     */public class EditUserAction extends AuthorizationAction
/*     */{
	/*     */private static final long serialVersionUID = -8852779886329075673L;
	/*     */protected final UserManager userManager;
	/*     */protected final LocaleBuilder localeBuilder;
	/*     */protected final GroupManager groupManager;
	/*     */protected final RoleManager roleManager;
	/*     */protected final DepartmentManager departmentManager;
	/*     */private final InstitutionManager institutionManager;
	/*     */private final PersonnelFilesManager personnelFilesManager;
	/*     */private User user;
	/*     */private Department department;
	/*     */private String newPassword;
	/*     */private String confirmPassword;
	/*     */private Group group;
	/*     */private List<Group> groups;
	/*     */private Role role;
	/*     */private List<User> users;
	/*     */private List<Role> roles;
	/*     */private List<UserAuthorization> authorizations;
	/*     */private List<Department> departments;

	/*     */
	/*     */public EditUserAction(UserManager userManager, PersisteceManager persisteceManager,
			LocaleBuilder localeBuilder, GroupManager groupManager, RoleManager roleManager,
			DepartmentManager departmentManager, InstitutionManager institutionManager,
			PersonnelFilesManager personnelFilesManager)
	/*     */{
		/* 100 */super(persisteceManager);
		/* 101 */this.userManager = userManager;
		/* 102 */this.localeBuilder = localeBuilder;
		/* 103 */this.groupManager = groupManager;
		/* 104 */this.roleManager = roleManager;
		/* 105 */this.departmentManager = departmentManager;
		/* 106 */this.institutionManager = institutionManager;
		/* 107 */this.personnelFilesManager = personnelFilesManager;
		/*     */}

	/*     */
	/*     */public String save() throws IOException
	/*     */{
		/* 112 */if (isChangePassword()) {
			/* 113 */return changePassword();
			/*     */}
		/*     */
		/* 116 */if (isDisable()) {
			/* 117 */return disable();
			/*     */}
		/*     */
		/* 120 */if (isRevoke()) {
			/* 121 */return revoke();
			/*     */}
		/*     */
		/* 124 */if (isJoin()) {
			/* 125 */return join();
			/*     */}
		/*     */
		/* 128 */if (isLeave()) {
			/* 129 */return leave();
			/*     */}
		/*     */
		/* 132 */if (isGrantRole()) {
			/* 133 */return grantRole();
			/*     */}
		/*     */
		/* 136 */if (isRevokeRole()) {
			/* 137 */if (hasIds("grantedRoleCodes")) {
				/* 138 */String roleCodes = this.request.getParameter("grantedRoleCodes");
				/* 139 */String[] roleCode = roleCodes.split(",");
				/* 140 */this.roles = this.roleManager.getRolesByRoleCodes(roleCode);
				/*     */}
			/* 142 */return revokeRole();
			/*     */}
		/*     */
		/* 145 */if (isEnabled()) {
			/* 146 */enabled();
			/*     */}
		/*     */
		/* 149 */if (isRevokeDept()) {
			/* 150 */return revokeDept();
			/*     */}
		/*     */
		/* 153 */boolean isNew = this.user.isNew();
		/*     */
		/* 155 */if (!StringUtils.isEmpty(this.request.getParameter("personCode"))) {
			/* 156 */this.user.setCode(this.request.getParameter("personCode"));
			/*     */}
		/*     */
		/* 160 */if (hasId("department.id")) {
			/* 161 */this.department = this.departmentManager.loadDepartment(getId("department.id"));
			/*     */
			/* 164 */this.user.setDepartment(this.department);
			/*     */}
		/*     */
		/* 167 */if (hasId("institution.id")) {
			/* 168 */Institution institution = this.institutionManager.loadInstitution(getId("institution.id"));
			/*     */
			/* 170 */this.user.setInstitustion(institution);
			/*     */}
		/*     */
		/* 174 */String viewAll = this.request.getParameter("viewall.option");
		/* 175 */if (!StringUtils.isEmpty(viewAll)) {
			/* 176 */if ("Y".equals(viewAll))
				/* 177 */this.user.setViewAll(true);
			/*     */else {
				/* 179 */this.user.setViewAll(false);
				/*     */}
			/*     */}
		/*     */
		/* 183 */this.user.setUserType(UserType.SYSTEM_USER);
		/* 184 */if (!StringUtils.isEmpty(this.request.getParameter("privilegeUser")))
			/* 185 */this.user.setPrivilegeUser(true);
		/*     */else {
			/* 187 */this.user.setPrivilegeUser(false);
			/*     */}
		/*     */
		/* 190 */if (StringUtils.isEmpty(this.newPassword)) {
			/* 191 */this.user.setOrganization(this.userManager.getUser().getOrganization());
			/*     */
			/* 193 */this.userManager.storeUser(this.user);
			/* 194 */updatePersonnelFiles();
			/*     */} else {
			/* 196 */this.user.setOrganization(this.userManager.getUser().getOrganization());
			/*     */
			/* 199 */if (null != this.userManager.getUserByLoginName(this.user.getLoginName()))
			/*     */{
				/* 201 */addActionError(getText("user.loginName.repeat",
						Arrays.asList(new Object[] { this.user.getLoginName() })));
				/*     */
				/* 203 */return "error";
				/*     */}
			/* 205 */this.userManager.changePassword(this.user, this.newPassword);
			/*     */}
		/*     */
		/* 208 */if (isNew) {
			/* 209 */addActionMessage(getText("user.add.success", Arrays.asList(new Object[] { this.user.getName() })));
			/*     */
			/* 211 */return "new";
			/*     */}
		/* 213 */addActionMessage(getText("user.edit.success", Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 215 */return "success";
		/*     */}

	/*     */
	/*     */public void updatePersonnelFiles() {
		/* 219 */PersonnelFiles personnelFiles = new PersonnelFiles();
		/* 220 */List pfList = null;
		/*     */try {
			/* 222 */pfList = this.personnelFilesManager.loadByKey("code", this.user.getCode().trim());
			/*     */}
		/*     */catch (Exception e) {
			/* 225 */this.logger.info("查询用户对应的人事档案出错！");
			/*     */}
		/* 227 */if ((null == pfList) || (pfList.isEmpty())) {
			/* 228 */return;
			/*     */}
		/* 230 */personnelFiles = (PersonnelFiles) pfList.get(0);
		/*     */
		/* 233 */personnelFiles.setName(this.user.getName().trim());
		/* 234 */personnelFiles.setBirthday(this.user.getBrith());
		/* 235 */personnelFiles.setEmail(this.user.getEmail().trim());
		/* 236 */personnelFiles.setMobile(this.user.getTelphoneNumber().trim());
		/* 237 */this.personnelFilesManager.storePersonnel(personnelFiles);
		/*     */}

	/*     */
	/*     */private boolean isGrantRole() {
		/* 241 */return hasKey("grant_role");
		/*     */}

	/*     */
	/*     */private boolean isRevokeRole() {
		/* 245 */return hasKey("revoke_role");
		/*     */}

	/*     */
	/*     */private boolean isJoin() {
		/* 249 */return hasKey("join");
		/*     */}

	/*     */
	/*     */private boolean isLeave() {
		/* 253 */return hasKey("leave");
		/*     */}

	/*     */
	/*     */private boolean isChangePassword() {
		/* 257 */return hasKey("change_password");
		/*     */}

	/*     */
	/*     */private boolean isDisable() {
		/* 261 */return hasKey("disable");
		/*     */}

	/*     */
	/*     */private boolean isRevoke() {
		/* 265 */return hasKey("revoke");
		/*     */}

	/*     */
	/*     */private boolean isEnabled() {
		/* 269 */return hasKey("enabled");
		/*     */}

	/*     */
	/*     */private boolean isRevokeDept() {
		/* 273 */return hasKey("revokeDept");
		/*     */}

	/*     */
	/*     */private String enabled() {
		/* 277 */this.user.enable();
		/* 278 */this.userManager.storeUser(this.user);
		/* 279 */return "success";
		/*     */}

	/*     */
	/*     */private String revoke() {
		/* 283 */for (UserAuthorization userAuthorization : this.authorizations) {
			/* 284 */this.user.revokeAuthorization(userAuthorization);
			/*     */}
		/* 286 */this.userManager.storeUser(this.user);
		/* 287 */addActionMessage(getText("user.revoke.success", Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 290 */return "new";
		/*     */}

	/*     */
	/*     */private String disable() {
		/* 294 */this.user.disable();
		/* 295 */this.userManager.storeUser(this.user);
		/* 296 */addActionMessage(getText("user.disable.success", Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 298 */return "success";
		/*     */}

	/*     */
	/*     */public String changePassword()
	/*     */{
		/* 303 */this.userManager.changePassword(this.user, this.newPassword);
		/* 304 */addActionMessage(getText("user.changePassword.success",
				Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 306 */return "success";
		/*     */}

	/*     */
	/*     */public String join() {
		/* 310 */if (hasIds("availableGroupIds")) {
			/* 311 */String availableGroupIds = this.request.getParameter("availableGroupIds");
			/*     */
			/* 313 */String[] availableGroupId = availableGroupIds.split(",");
			/* 314 */Long[] result = new Long[availableGroupId.length];
			/* 315 */for (int i = 0; i < availableGroupId.length; i++) {
				/* 316 */result[i] = Long.valueOf(availableGroupId[i]);
				/*     */}
			/* 318 */for (int i = 0; i < result.length; i++) {
				/* 319 */this.group = this.groupManager.loadGroup(result[i]);
				/* 320 */this.groupManager.loadUsers(this.group);
				/* 321 */this.group.addUser(this.user);
				/* 322 */this.groupManager.storeGroup(this.group);
				/*     */}
			/*     */}
		/* 325 */addActionMessage(getText("user.join.success", Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 327 */return "new";
		/*     */}

	/*     */
	/*     */public String leave() {
		/* 331 */if (hasIds("grantedGroupIds")) {
			/* 332 */String grantedGroupIds = this.request.getParameter("grantedGroupIds");
			/* 333 */String[] grantedGroupId = grantedGroupIds.split(",");
			/* 334 */Long[] result = new Long[grantedGroupId.length];
			/* 335 */for (int i = 0; i < grantedGroupId.length; i++) {
				/* 336 */result[i] = Long.valueOf(grantedGroupId[i]);
				/*     */}
			/* 338 */this.groups = this.groupManager.loadAllGroups(result);
			/*     */}
		/* 340 */for (Group group : this.groups) {
			/* 341 */group.removeUser(this.user);
			/* 342 */this.groupManager.storeGroup(group);
			/*     */}
		/*     */
		/* 345 */addActionMessage(getText("user.leave.success", Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 347 */return "new";
		/*     */}

	/*     */
	/*     */public String grantRole() {
		/* 351 */if (hasIds("availableRoleCodes")) {
			/* 352 */String roleCodes = this.request.getParameter("availableRoleCodes");
			/* 353 */this.roleManager.grantRolesForUser(roleCodes, this.user);
			/*     */}
		/*     */
		/* 357 */addActionMessage(getText("user.grantRole.success", Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 359 */return "new";
		/*     */}

	/*     */
	/*     */public String revokeRole() {
		/* 363 */for (Role role : this.roles) {
			/* 364 */this.user.revokeRole(role);
			/*     */}
		/*     */
		/* 367 */this.userManager.storeUser(this.user);
		/* 368 */addActionMessage(getText("user.revokeRole.success",
				Arrays.asList(new Object[] { this.user.getName() })));
		/*     */
		/* 370 */return "new";
		/*     */}

	/*     */
	/*     */private String revokeDept() {
		/* 374 */for (Iterator i$ = this.departments.iterator(); i$.hasNext();) {
			/* 375 */Department dept = (Department) i$.next();
			/* 376 */dept.removeUser(this.user);
			/* 377 */this.departmentManager.storeDepartment(dept);
			/*     */}
		/* 379 */return "new";
		/*     */}

	/*     */
	/*     */public void prepare() throws Exception {
		/* 383 */if (this.user == null) {
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
		/*     */}

	/*     */
	/*     */public User getUser()
	/*     */{
		/* 429 */return this.user;
		/*     */}

	/*     */
	/*     */public void setUser(User user) {
		/* 433 */this.user = user;
		/*     */}

	/*     */
	/*     */public String getNewPassword() {
		/* 437 */return this.newPassword;
		/*     */}

	/*     */
	/*     */public void setNewPassword(String newPassword) {
		/* 441 */this.newPassword = newPassword;
		/*     */}

	/*     */
	/*     */public String getConfirmPassword() {
		/* 445 */return this.confirmPassword;
		/*     */}

	/*     */
	/*     */public void setConfirmPassword(String confirmPassword) {
		/* 449 */this.confirmPassword = confirmPassword;
		/*     */}

	/*     */
	/*     */public List<Locale> getLocales() {
		/* 453 */return this.localeBuilder.getLocales();
		/*     */}

	/*     */
	/*     */public List<Group> getJoinableAuthorityGroups()
	/*     */{
		/* 463 */List groups = this.groupManager.getGroupsByGroupType(GroupType.AUTHORITY_GROUP);
		/*     */
		/* 465 */groups.removeAll(this.user.getGroups());
		/* 466 */return groups;
		/*     */}

	/*     */
	/*     */public List<Group> getJoinableCommunicationGroups()
	/*     */{
		/* 476 */List groups = this.groupManager.getGroupsByGroupType(GroupType.COMMUNICATION_GROUP);
		/*     */
		/* 478 */groups.removeAll(this.user.getGroups());
		/* 479 */return groups;
		/*     */}

	/*     */
	/*     */public List<Role> getGrantableRoles() {
		/* 483 */List roles = this.roleManager.loadAllRoles();
		/* 484 */roles.removeAll(this.user.getRoles());
		/* 485 */return roles;
		/*     */}

	/*     */
	/*     */public List<UserAuthorization> getUserSortAuthorization() {
		/* 489 */List list = new ArrayList();
		/* 490 */Map<String, List> map = new HashMap<String, List>();
		/* 491 */for (UserAuthorization ua : this.user.getAuthorizations()) {
			/* 492 */List uas = (List) map.get(ua.getPermission().getIdentity().getClazz().getName());
			/*     */
			/* 494 */if (uas == null) {
				/* 495 */uas = new ArrayList();
				/* 496 */map.put(ua.getPermission().getIdentity().getClazz().getName(), uas);
				/*     */}
			/*     */
			/* 499 */uas.add(ua);
			/*     */}
		/* 501 */for (List uas : map.values()) {
			/* 502 */list.addAll(uas);
			/*     */}
		/* 504 */return list;
		/*     */}

	/*     */
	/*     */public List<Department> getDepartments()
	/*     */{
		/* 510 */boolean isNew = this.user.isNew();
		/* 511 */String name = "";
		/* 512 */long id = 0L;
		/* 513 */if (!isNew) {
			/* 514 */name = this.user.getDepartment().getName();
			/* 515 */id = this.user.getDepartment().getId().longValue();
			/*     */}
		/* 517 */return this.departmentManager.getDepartments(isNew, name, Long.valueOf(id));
		/*     */}

	/*     */
	/*     */public List<LabelValue> getUserType()
	/*     */{
		/* 522 */LabelValue[] arrays = wrapEnum(UserType.class);
		/* 523 */List tmp = new ArrayList();
		/* 524 */for (int i = 0; i < arrays.length; i++) {
			/* 525 */tmp.add(arrays[i]);
			/*     */}
		/* 527 */return tmp;
		/*     */}

	/*     */
	/*     */public List getInstitutions()
	/*     */{
		/* 536 */return this.institutionManager.getInstitutions();
		/*     */}
	/*     */
}

/*
 * Location: E:\crm2010\110\crm2009\WEB-INF\classes\ Qualified Name:
 * com.yongjun.tdms.presentation.webwork.action.security.EditUserAction JD-Core
 * Version: 0.6.2
 */