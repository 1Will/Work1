 package com.yongjun.tdms.presentation.webwork.action.base.org;
 
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.additionalInformation.AdditionalInformation;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
 
 public class EditDepartmentAction extends PrepareAction
 {
   private static final long serialVersionUID = 3269015023775508432L;
   private final DepartmentManager departmentManager;
   private final InstitutionManager institutionManager;
   private final UserManager userManager;
   private Department department;
   private Institution institution;
   private Long instId;
   private Long parentId;
   private List<User> users;
   private User user;
   private List<User> disUsers = new ArrayList();
   

 

public EditDepartmentAction(DepartmentManager departmentManager, UserManager userManager, InstitutionManager institutionManager) { this.departmentManager = departmentManager;
     this.userManager = userManager;
     this.institutionManager = institutionManager;
   }
 
   public void prepare()
     throws Exception
   {
     if (null == this.department) {
       if (hasId("department.id")) {
         this.department = this.departmentManager.loadDepartment(getId("department.id"));
         
         List list = this.departmentManager.displaySortUser(this.department.getId());
 
         for (int i = 0; i < list.size(); i++) {
           this.user = this.userManager.loadUser((Long)list.get(i));
           this.disUsers.add(this.user);
         }
       } else {
         this.department = new Department();
       }
     }
    
     
     
     if (hasId("institution.id")) {
       this.instId = Long.valueOf(this.request.getParameter("institution.id"));
     }
 
     if (hasId("parentid")){
       this.parentId = Long.valueOf(this.request.getParameter("parentid"));
     }
   }
 
   public String delete()
   {
     List list = new ArrayList();
     list.addAll(this.department.getChildDepts());
     if (list.size() > 0)
       this.departmentManager.deleteAllDepartments(list);
     try
     {
       this.departmentManager.deleteDepartment(this.department);
       this.department = null;
     } catch (Exception e) {
       addActionMessage(getText("department.delete.error"));
       return "error";
     }
 
     addActionMessage(getText("department.delete.success"));
     return "deleteSuccess";
   }
 
   public String save()
   {
     if (isDelete()) {
       return delete();
     }
     if (isJoin()) {
       if (hasIds("availableUserIds")) {
         String availableUserIds = this.request.getParameter("availableUserIds");
 
         String[] availableUserId = availableUserIds.split(",");
         this.departmentManager.joinUsersForDepartment(availableUserId, this.department);
       }
 
       return "new";
     }
 
     if (isLeave()) {
       if (hasIds("grantedUserIds")) {
         String grantedUserIds = this.request.getParameter("grantedUserIds");
         String[] grantedUserId = grantedUserIds.split(",");
         Long[] result = new Long[grantedUserId.length];
         for (int i = 0; i < grantedUserId.length; i++) {
           result[i] = Long.valueOf(grantedUserId[i]);
         }
         this.users = this.userManager.loadAllUsers(result);
       }
       return leave();
     }
 
     boolean isNew = this.department.isNew();
 
     String logContent = "";
     if (isNew)
       logContent = "被添加";
     else {
       logContent = "被修改";
     }
     logContent = "(部门代码:" + this.department.getCode() + ";部门名称:" + this.department.getName() + ")" + logContent;
     getLogger().logStore(this.department, logContent, "department_manager");
 
     if (this.parentId != null) {
       Department newParentDept = this.departmentManager.loadDepartment(this.parentId);
 
       this.department.setStep(newParentDept.getStep() + 1);
       this.department.setParentDept(newParentDept);
       this.department.setInst(newParentDept.getInst());
     } else {
	            if(isNew){
       this.department.setParentDept(null);
       this.department.setStep(0);
	            }
       if (this.instId != null) {
         this.institution = this.institutionManager.loadInstitution(this.instId);
         this.department.setInst(this.institution);
       }
     }
 
         this.departmentManager.storeDepartment(this.department);
     if (isNew) {
       addActionMessage(getText("department.add.success", Arrays.asList(new Object[] { this.department.getCode() })));
 
       return "new";
     }
     addActionMessage(getText("department.edit.success", Arrays.asList(new Object[] { this.department.getCode() })));
 
     return "success";
   }
 
   private String logContentUserFromDept(Department dept)
   {
     String strUser = "";
     Integer index = null;
     Set<User> users = dept.getUsers();
     for (User user : users) {
       strUser = strUser + user.getName() + ",";
     }
     index = Integer.valueOf(strUser.lastIndexOf(","));
     strUser = strUser.substring(0, index.intValue());
     return strUser;
   }
 
   private boolean isJoin() {
     if (this.request.getParameter("join").equals("join")) {
       return true;
     }
     return false;
   }
   private boolean isLeave() {
     return hasKey("leave");
   }
 
   public Department getDepartment() {
     return this.department;
   }
 
   private boolean isdeleteProductionLine() {
     return hasKey("leave");
   }
 
   public void setDepartment(Department department) {
     this.department = department;
   }
 
   public List<User> getUsers()
   {
     return this.users;
   }
 
   public void setUsers(List<User> users) {
     this.users = users;
   }
   public String leave() {
     String strUser = "";
     for (User user : this.users) {
       this.department.removeUser(user);
 
       strUser = strUser + user.getName() + ",";
     }
     Integer index = null;
     index = Integer.valueOf(strUser.lastIndexOf(","));
     strUser = strUser.substring(0, index.intValue());
     getLogger().logStore(this.department, "(部门名称:[" + this.department.getName() + "]有用户[" + strUser + "]离开)", "department_manager");
 
     this.departmentManager.storeDepartment(this.department);
     return "new";
   }
   public String join() {
     this.department.addUser(this.user);
     this.departmentManager.storeDepartment(this.department);
 
     return "new";
   }
 
   public User getUser()
   {
     return this.user;
   }
 
   public void setUser(User user) {
     this.user = user;
   }
 
   public List<User> getDisUsers() {
     return this.disUsers;
   }
 
   public void setDisUsers(List<User> disUsers) {
     this.disUsers = disUsers;
   }
 
   public Institution getInstitution()
   {
     return this.institution;
   }
 
   public void setInstitution(Institution institution)
   {
     this.institution = institution;
   }
 
   public Long getInstId() {
     return this.instId;
   }
 
   public void setInstId(Long instId) {
     instId = instId;
   }
 
   public Long getParentId() {
     return this.parentId;
   }
 
   public void setParentId(Long parentId) {
     this.parentId = parentId;
   }



 }

