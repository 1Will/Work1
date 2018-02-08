/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.pluto.model.security.Role;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.UserAuthorization;
import com.yongjun.pluto.model.security.UserType;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.PersisteceManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.RoleManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.util.LocaleBuilder;
import com.yongjun.pluto.webwork.action.security.AuthorizationAction;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;


/**
 * @author qs
 * @version $Id: EditUserAction.java 6153 2007-07-31 03:27:08Z qsun $
 */
public class EditUserAction
        extends AuthorizationAction {
	private static final long serialVersionUID = -8852779886329075673L;
	protected final UserManager userManager;
    protected final LocaleBuilder localeBuilder;
    protected final GroupManager groupManager;
    protected final RoleManager roleManager;
    protected final DepartmentManager departmentManager;
    private final FilialeManager filialeManager;
    private final WarehouseManager warehouseManager;
    private User user;
    private String newPassword;
    private String confirmPassword;
    private Group group;
    private List<Group> groups;
    private Role role;
    private List<Role> roles;
    private Department department;
    private Filiale filiale;
    private List<Department> departments;
    private List<UserAuthorization> authorizations;
    private List<Warehouse> warehouses;

    public EditUserAction(UserManager userManager, 
    						PersisteceManager persisteceManager,
    						LocaleBuilder localeBuilder,
    						GroupManager groupManager,
                          RoleManager roleManager,
                          DepartmentManager departmentManager,
                          FilialeManager filialeManager,
                          WarehouseManager warehouseManager) {
    	super(persisteceManager);
    	this.userManager = userManager;
    	this.localeBuilder = localeBuilder;
    	this.groupManager = groupManager;
    	this.roleManager = roleManager;
    	this.departmentManager = departmentManager;
    	this.filialeManager = filialeManager;
    	this.warehouseManager=warehouseManager;
    }
    

    public String save()
            throws IOException {
    	System.out.println("1");
        if (this.isChangePassword()) {
        	System.out.println("2");
            return this.changePassword();
        }

        if (this.isDisable()) {
            return this.disable();
        }

        if (this.isRevoke()) {
            return this.revoke();
        }

        if (this.isJoin()) {
            return this.join();
        }

        if (this.isLeave()) {
            return this.leave();
        }

        if (this.isGrantRole()) {
            return this.grantRole();
        }
        
        if(this.isGrantDept()){
        	return grantDept();
        }
        if(this.isRevokeDept()){
        	return revokeDept();
        }
        if(this.isRevokeWarehouse()){
        	return revokeWarehouse();
        }
        if (this.isRevokeRole()) {
        	if (this.hasIds("grantedRoleCodes")) {
        		String roleCodes = request.getParameter("grantedRoleCodes");
        		String [] roleCode = roleCodes.split(",");
        		this.roles = this.roleManager.getRolesByRoleCodes(roleCode);
        	}
            return this.revokeRole();
        }

        if (this.isEnabled()) {
        	this.enabled();
        }
        
        boolean isNew = this.user.isNew();
        
        if (hasId("department.id")) {
        	Department department = departmentManager.loadDepartment(getId("department.id"));
        	user.setDepartment(department);
        }
        if (hasId("filiale.id")) {
        	Filiale filiale = filialeManager.loadFiliale(getId("filiale.id"));
        	user.setFiliale(filiale);
        }
        String viewAll = request.getParameter("viewall.option");
        if (!StringUtils.isEmpty(viewAll)) {
        	if ("Y".equals(viewAll)) {
        		user.setViewAll(true);
        	} else {
        		user.setViewAll(false);
        	}
        }
        
        //设置用户的用户类型
        String userType=request.getParameter("user.type");
        if (!StringUtils.isEmpty(userType)) {
        	//系统用户
            if (userType.equals(UserType.SYSTEM_USER.toString())) {
        	  user.setUserType(UserType.SYSTEM_USER);
            } else {    //非系统用户
            	user.setUserType(UserType.NON_SYSTEM_USER);
            }
        }
        
        if (StringUtils.isEmpty(this.newPassword)) {
        	this.user.setOrganization(this.userManager.getUser().getOrganization());
        	this.userManager.storeUser(this.user);
          
        } else {
        	this.user.setOrganization(this.userManager.getUser().getOrganization());
        	if (null != this.userManager.getUserByLoginName(user.getLoginName())) {
        		this.addActionError(this.getText("user.loginName.repeat",
                        Arrays.asList(new Object[]{user.getLoginName()})));
        		return 	ERROR;
        	}
            this.userManager.changePassword(this.user, this.newPassword);
        }

        if (isNew) {
            this.addActionMessage(this.getText("user.add.success",
                    Arrays.asList(new Object[]{user.getName()})));
            return NEW;
        } else {
            this.addActionMessage(this.getText("user.edit.success",
                    Arrays.asList(new Object[]{user.getName()})));
            return SUCCESS;
        }
    }

    private String revokeDept() {
      for (Iterator i$ = this.departments.iterator(); i$.hasNext(); ) { Department dept = (Department)i$.next();
        dept.removeUser(this.user);
        this.departmentManager.storeDepartment(dept);
      }
      return "new";
	}
    
    private String revokeWarehouse() {
    	for (Iterator i$ = this.warehouses.iterator(); i$.hasNext(); ) { 
    		Warehouse warehouse = (Warehouse)i$.next();
    		Set set=warehouse.getUsers();
    		set.remove(user);
    		warehouse.setUsers(set);
    		warehouseManager.storeWarehouse(warehouse);
      }
      return "new";
	}
    
	private String grantDept() {
    	department.addUser(this.user);
    	departmentManager.storeDepartment(department);
    	return "new";
	}

	private boolean isGrantRole() {
        return this.hasKey("grant_role");
    }

    private boolean isRevokeRole() {
        return this.hasKey("revoke_role");
    }

    private boolean isJoin() {
        return this.hasKey("join");
    }

    private boolean isLeave() {
        return this.hasKey("leave");
    }

    private boolean isChangePassword() {
        return this.hasKey("change_password");
    }

    private boolean isDisable() {
        return this.hasKey("disable");
    }

    private boolean isRevoke() {
        return this.hasKey("revoke");
    }

    private boolean isEnabled() {
    	return this.hasKey("enabled");
    }
    
    private String enabled() {
    	user.enable();
    	this.userManager.storeUser(user);
    	return SUCCESS;
    }
    private String revoke() {
        for (UserAuthorization userAuthorization : authorizations) {
            this.user.revokeAuthorization(userAuthorization);
        }
        this.userManager.storeUser(user);
        this.addActionMessage(this.getText("user.revoke.success",
                Arrays.asList(new Object[]{user.getName()})));

        return NEW;
    }

    private String disable() {
        user.disable();
        this.userManager.storeUser(user);
        this.addActionMessage(this.getText("user.disable.success",
                Arrays.asList(new Object[]{user.getName()})));
        return SUCCESS;

    }

    public String changePassword() {
    	System.out.println("================");
        this.userManager.changePassword(this.user, this.newPassword);
        this.addActionMessage(this.getText("user.changePassword.success",
                Arrays.asList(new Object[]{user.getName()})));
        return SUCCESS;
    }

    public String join() {
    	if (this.hasIds("availableGroupIds")) {
    		String availableGroupIds = request.getParameter("availableGroupIds");
    		String [] availableGroupId = availableGroupIds.split(",");
            Long[] result = new Long[availableGroupId.length];
            for (int i = 0; i < availableGroupId.length; i++) {
                result[i] = Long.valueOf(availableGroupId[i]);
            }
            for (int i=0; i<result.length; i++) {
                this.group = this.groupManager.loadGroup(result[i]);
                this.groupManager.loadUsers(this.group);
                this.group.addUser(this.user);
                this.groupManager.storeGroup(this.group);
            }
    	}
        this.addActionMessage(this.getText("user.join.success",
                Arrays.asList(new Object[]{user.getName()})));
        return NEW;
    }

    public String leave() {
    	if (this.hasIds("grantedGroupIds")) {
    		String grantedGroupIds = request.getParameter("grantedGroupIds");
    		String [] grantedGroupId = grantedGroupIds.split(",");
            Long[] result = new Long[grantedGroupId.length];
            for (int i = 0; i < grantedGroupId.length; i++) {
                result[i] = Long.valueOf(grantedGroupId[i]);
            }
            this.groups = this.groupManager.loadAllGroups(result);
    	}
        for (Group group : groups) {
            group.removeUser(this.user);
            this.groupManager.storeGroup(group);
        }
        this.addActionMessage(this.getText("user.leave.success",
                Arrays.asList(new Object[]{user.getName()})));
        return NEW;
    }

    public String grantRole() {
    	if (this.hasIds("availableRoleCodes")) {
    		String roleCodes = request.getParameter("availableRoleCodes");
    		this.roleManager.grantRolesForUser(roleCodes, user);
    	}
        this.addActionMessage(this.getText("user.grantRole.success",
                Arrays.asList(new Object[]{user.getName()})));
        return NEW;
    }

    public String revokeRole() {
        for (Role role : roles) {
            this.user.revokeRole(role);
        }
        this.userManager.storeUser(this.user);
        this.addActionMessage(this.getText("user.revokeRole.success",
                Arrays.asList(new Object[]{user.getName()})));
        return NEW;
    }

    public void prepare()
            throws Exception {
    	long sum=0L;
        if (this.user == null) {
            if (this.hasId("user.id")) {
                this.user = this.userManager.loadUser(this.getId("user.id"));
                this.userManager.loadAuthorizations(this.user);
                this.userManager.loadGroups(this.user);
                this.userManager.loadRoles(this.user);
                Set<Warehouse> ableRoleWarehouses=new HashSet<Warehouse>();
                for(Warehouse warehouse:user.getWarehouses()){              	
                	if(!(warehouse.getDisabled())){
                		sum=sum+1;              		
                		ableRoleWarehouses.add(warehouse);               		
                	}                	
                }
                user.setWarehouses(ableRoleWarehouses);
                if(sum==0L){
            		user.setWarehouses(ableRoleWarehouses);
	    		}
            } else {
                this.user = new User();
            }
        }
        if (this.authorizations == null) {
            if (this.hasIds("authorizationIds")) {
//                this.authorizations = this.userManager.loadAllAuthorizations(this.getIds("authorizationIds"));
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
        
        if(this.department == null){
        	if(this.hasId("dept.id")){
        		this.department = this.departmentManager.loadDepartment(this.getId("dept.id"));
        	}
        }
        if(this.filiale == null){
        	if(this.hasId("filiale.id")){
        		this.filiale = this.filialeManager.loadFiliale(this.getId("filiale.id"));
        	}
        }
        
        if(this.departments == null){
        	if(this.hasIds("deptIds")){
        		this.departments = departmentManager.loadAllDepartments(this.getIds("deptIds"));
        	}
        }
        if(this.warehouses == null){
        	if(this.hasIds("whIds")){
        		this.warehouses = warehouseManager.loadAllWarehouse(this.getIds("whIds"));
        	}
        }
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public List<Locale> getLocales() {
        return this.localeBuilder.getLocales();
    }
    public List<Group> getJoinableGroups() {
        List<Group> groups = this.groupManager.loadAllGroups();
        groups.removeAll(this.user.getGroups());
        return groups;
    }
    //获取可用的角色
    public List<Role> getGrantableRoles() {
        List<Role> roles = this.roleManager.loadAllRolesBySortIdx();
        roles.removeAll(this.user.getRoles());
        return roles;
    }
    //获取用户已有的角色，并按角色的sortIdx进行升序
    public List<Role> getUserSortRole() {
    	List<Role> list = new ArrayList<Role>();
    	Map<Integer, Role> map = new HashMap<Integer, Role>();
    	for (Role role : user.getRoles()) {
    		map.put(role.getSortIdx(),role);
    	}
    	Object [] key = map.keySet().toArray();
    	Arrays.sort(key);
    	for (int i=0; i<key.length; i++) {
    		list.add(map.get(key[i]));
    	}
    	return list;
    }
    public List<UserAuthorization> getUserSortAuthorization() {
    	List<UserAuthorization> list = new ArrayList<UserAuthorization>();
    	Map<String , List<UserAuthorization>> map = new HashMap<String , List<UserAuthorization>>();
    	for (UserAuthorization ua : user.getAuthorizations()) {
    		List<UserAuthorization> uas = map.get(ua.getPermission().getIdentity().getClazz().getName());
    		if (uas == null) {
    			uas = new ArrayList<UserAuthorization>();
    			map.put(ua.getPermission().getIdentity().getClazz().getName() , uas);
    		}
    		uas.add(ua);
    	}
    	for (List<UserAuthorization> uas : map.values()) {
    		list.addAll(uas);
    	}
    	return list;
    }
//    @SuppressWarnings("unchecked")
//	public List<Department> getDepartments() {
//    	if(this.hasId("filiale.id")){
//    		Set<Department> sets = filialeManager.loadFiliale(this.getId("filiale.id")).getDepartments();
//    		List<Department> lists = new ArrayList<Department>();
//    		lists.addAll(sets);
//    		return lists;
//    	}else{
//    		return userManager.getDepartments();
//    	}
//    }
//    public List<Filiale> getFiliales() {
//    	List<Filiale> list = this.filialeManager.loadAllFiliale();
//    	
//    	return filialeManager.loadAllFiliale();
//    }
    
    
    /**
     * 获取部门的所有值
     * @return List 部门集合
     */
	public List getDepartments() {
		if(this.user.isNew()){
			return departmentManager.createSelectDepartments(this
					.getText("select.option.all"));
		}else{
			List<Department> list = this.departmentManager.loadAllDepartments();
			list.clear();
			Department dept = new Department();
//			dept.setId(Long.valueOf(0));
			dept.setName(this.user.getDepartment().getName());
			list.add(0, dept);
			return list;
		}
	}
    /**
     * 获取分公司的所有值
     * @return List 分公司集合
     */
	public List getFiliales() {
		return filialeManager.createSelectFilailes(this
				.getText(""));
	}
    
    
    
    
    
    
    /**
     * 获取当前登录用户
     * @return User 用户
     */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
	public List<Department> getGrantableDepts(){
		List depts = departmentManager.loadAllDepartments();
		depts.remove(user.getDepartment());
		depts.removeAll(user.getDepartments());
		return depts;
	}
	private boolean isGrantDept()
	{
	    return hasKey("grant_dept");
	}
	private boolean isRevokeDept() {
		return hasKey("revoke_dept");
	}
	private boolean isRevokeWarehouse(){
		return hasKey("revoke_wh");
	}
    //获取用户类型   系统用户|非系统用户
	public List<LabelValue> getUserType() {  
		LabelValue[] arrays = this.wrapEnum(UserType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
}
