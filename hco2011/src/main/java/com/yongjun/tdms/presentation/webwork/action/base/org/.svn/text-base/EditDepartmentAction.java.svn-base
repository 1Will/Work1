/*     */ package com.yongjun.tdms.presentation.webwork.action.base.org;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.log.service.BusinessLogger;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditDepartmentAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 3269015023775508432L;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final UserManager userManager;
/*     */   private Department department;
/*     */   private Institution institution;
/*     */   private Long instId;
/*     */   private Long parentId;
/*     */   private List<User> users;
/*     */   private User user;
/*  65 */   private List<User> disUsers = new ArrayList();
/*     */ 
/*  67 */   public EditDepartmentAction(DepartmentManager departmentManager, UserManager userManager, InstitutionManager institutionManager) { this.departmentManager = departmentManager;
/*  68 */     this.userManager = userManager;
/*  69 */     this.institutionManager = institutionManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  78 */     if (null == this.department) {
/*  79 */       if (hasId("department.id")) {
/*  80 */         this.department = this.departmentManager.loadDepartment(getId("department.id"));
/*     */ 
/*  82 */         List list = this.departmentManager.displaySortUser(this.department.getId());
/*     */ 
/*  85 */         for (int i = 0; i < list.size(); i++) {
/*  86 */           this.user = this.userManager.loadUser((Long)list.get(i));
/*  87 */           this.disUsers.add(this.user);
/*     */         }
/*     */       } else {
/*  90 */         this.department = new Department();
/*     */       }
/*     */     }
/*     */ 
/*  94 */     if (hasId("institution.id")) {
/*  95 */       this.instId = Long.valueOf(this.request.getParameter("institution.id"));
/*     */     }
/*     */ 
/*  98 */     if (hasId("parentid"))
/*  99 */       this.parentId = Long.valueOf(this.request.getParameter("parentid"));
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/* 110 */     List list = new ArrayList();
/* 111 */     list.addAll(this.department.getChildDepts());
/* 112 */     if (list.size() > 0)
/* 113 */       this.departmentManager.deleteAllDepartments(list);
/*     */     try
/*     */     {
/* 116 */       this.departmentManager.deleteDepartment(this.department);
/* 117 */       this.department = null;
/*     */     } catch (Exception e) {
/* 119 */       addActionMessage(getText("department.delete.error"));
/* 120 */       return "error";
/*     */     }
/*     */ 
/* 123 */     addActionMessage(getText("department.delete.success"));
/* 124 */     return "deleteSuccess";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 133 */     if (isDelete()) {
/* 134 */       return delete();
/*     */     }
/* 136 */     if (isJoin()) {
/* 137 */       if (hasIds("availableUserIds")) {
/* 138 */         String availableUserIds = this.request.getParameter("availableUserIds");
/*     */ 
/* 140 */         String[] availableUserId = availableUserIds.split(",");
/* 141 */         this.departmentManager.joinUsersForDepartment(availableUserId, this.department);
/*     */       }
/*     */ 
/* 145 */       return "new";
/*     */     }
/*     */ 
/* 148 */     if (isLeave()) {
/* 149 */       if (hasIds("grantedUserIds")) {
/* 150 */         String grantedUserIds = this.request.getParameter("grantedUserIds");
/* 151 */         String[] grantedUserId = grantedUserIds.split(",");
/* 152 */         Long[] result = new Long[grantedUserId.length];
/* 153 */         for (int i = 0; i < grantedUserId.length; i++) {
/* 154 */           result[i] = Long.valueOf(grantedUserId[i]);
/*     */         }
/* 156 */         this.users = this.userManager.loadAllUsers(result);
/*     */       }
/* 158 */       return leave();
/*     */     }
/*     */ 
/* 161 */     boolean isNew = this.department.isNew();
/*     */ 
/* 163 */     String logContent = "";
/* 164 */     if (isNew)
/* 165 */       logContent = "被添加";
/*     */     else {
/* 167 */       logContent = "被修改";
/*     */     }
/* 169 */     logContent = "(部门代码:" + this.department.getCode() + ";部门名称:" + this.department.getName() + ")" + logContent;
/* 170 */     getLogger().logStore(this.department, logContent, "department_manager");
/*     */ 
/* 173 */     if (this.parentId != null) {
/* 174 */       Department newParentDept = this.departmentManager.loadDepartment(this.parentId);
/*     */ 
/* 176 */       this.department.setStep(newParentDept.getStep() + 1);
/* 177 */       this.department.setParentDept(newParentDept);
/* 178 */       this.department.setInst(newParentDept.getInst());
/*     */     } else {
/* 180 */       this.department.setParentDept(null);
/* 181 */       this.department.setStep(0);
/* 182 */       if (this.instId != null) {
/* 183 */         this.institution = this.institutionManager.loadInstitution(this.instId);
/* 184 */         this.department.setInst(this.institution);
/*     */       }
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 190 */       if (isNew)
/*     */       {
/* 192 */         if (null == this.departmentManager.loadByKey("code", this.department.getCode()))
/* 193 */           if (null == this.departmentManager.loadByKey("name", this.department.getName())) {
/* 194 */             this.departmentManager.storeDepartment(this.department);
/*     */           } else {
/* 196 */             addActionMessage(getText("department.add.exist", Arrays.asList(new Object[] { this.department.getCode() })));
/*     */ 
/* 198 */             return "error";
/*     */           }
/*     */       }
/*     */       else
/* 202 */         this.departmentManager.storeDepartment(this.department);
/*     */     }
/*     */     catch (DaoException e) {
/* 205 */       e.printStackTrace();
/* 206 */       addActionMessage(getText("department.add.exist", Arrays.asList(new Object[] { this.department.getCode() })));
/*     */ 
/* 210 */       return "error";
/*     */     }
/*     */ 
/* 213 */     if (isNew) {
/* 214 */       addActionMessage(getText("department.add.success", Arrays.asList(new Object[] { this.department.getCode() })));
/*     */ 
/* 216 */       return "new";
/*     */     }
/* 218 */     addActionMessage(getText("department.edit.success", Arrays.asList(new Object[] { this.department.getCode() })));
/*     */ 
/* 220 */     return "success";
/*     */   }
/*     */ 
/*     */   private String logContentUserFromDept(Department dept)
/*     */   {
/* 229 */     String strUser = "";
/* 230 */     Integer index = null;
/* 231 */     Set<User> users = dept.getUsers();
/* 232 */     for (User user : users) {
/* 233 */       strUser = strUser + user.getName() + ",";
/*     */     }
/* 235 */     index = Integer.valueOf(strUser.lastIndexOf(","));
/* 236 */     strUser = strUser.substring(0, index.intValue());
/* 237 */     return strUser;
/*     */   }
/*     */ 
/*     */   private boolean isJoin() {
/* 241 */     if (this.request.getParameter("join").equals("join")) {
/* 242 */       return true;
/*     */     }
/* 244 */     return false;
/*     */   }
/*     */   private boolean isLeave() {
/* 247 */     return hasKey("leave");
/*     */   }
/*     */ 
/*     */   public Department getDepartment() {
/* 251 */     return this.department;
/*     */   }
/*     */ 
/*     */   private boolean isdeleteProductionLine() {
/* 255 */     return hasKey("leave");
/*     */   }
/*     */ 
/*     */   public void setDepartment(Department department) {
/* 259 */     this.department = department;
/*     */   }
/*     */ 
/*     */   public List<User> getUsers()
/*     */   {
/* 338 */     return this.users;
/*     */   }
/*     */ 
/*     */   public void setUsers(List<User> users) {
/* 342 */     this.users = users;
/*     */   }
/*     */   public String leave() {
/* 345 */     String strUser = "";
/* 346 */     for (User user : this.users) {
/* 347 */       this.department.removeUser(user);
/*     */ 
/* 349 */       strUser = strUser + user.getName() + ",";
/*     */     }
/* 351 */     Integer index = null;
/* 352 */     index = Integer.valueOf(strUser.lastIndexOf(","));
/* 353 */     strUser = strUser.substring(0, index.intValue());
/* 354 */     getLogger().logStore(this.department, "(部门名称:[" + this.department.getName() + "]有用户[" + strUser + "]离开)", "department_manager");
/*     */ 
/* 356 */     this.departmentManager.storeDepartment(this.department);
/* 357 */     return "new";
/*     */   }
/*     */   public String join() {
/* 360 */     this.department.addUser(this.user);
/* 361 */     this.departmentManager.storeDepartment(this.department);
/*     */ 
/* 363 */     return "new";
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 386 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 390 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public List<User> getDisUsers() {
/* 394 */     return this.disUsers;
/*     */   }
/*     */ 
/*     */   public void setDisUsers(List<User> disUsers) {
/* 398 */     this.disUsers = disUsers;
/*     */   }
/*     */ 
/*     */   public Institution getInstitution()
/*     */   {
/* 405 */     return this.institution;
/*     */   }
/*     */ 
/*     */   public void setInstitution(Institution institution)
/*     */   {
/* 412 */     this.institution = institution;
/*     */   }
/*     */ 
/*     */   public Long getInstId() {
/* 416 */     return this.instId;
/*     */   }
/*     */ 
/*     */   public void setInstId(Long instId) {
/* 420 */     instId = instId;
/*     */   }
/*     */ 
/*     */   public Long getParentId() {
/* 424 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long parentId) {
/* 428 */     this.parentId = parentId;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.org.EditDepartmentAction
 * JD-Core Version:    0.6.2
 */