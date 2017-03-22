/*    */ package com.yongjun.tdms.presentation.webwork.action.security;
/*    */ 
/*    */ import com.yongjun.pluto.model.security.Department;
/*    */ import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.pluto.service.security.UserManager;
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ListDeptUserFrameAction extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = 2721711091444617922L;
/*    */   private final DepartmentManager departmentManager;
/*    */   private final UserManager userManager;
/*    */   private Department deptFrame;
/*    */   private List<User> users;
/*    */   private List<Department> depts;
/* 45 */   private List<User> disUsers = new ArrayList();
/*    */ 
/* 47 */   public ListDeptUserFrameAction(DepartmentManager departmentManager, UserManager userManager) { this.departmentManager = departmentManager;
/* 48 */     this.userManager = userManager; }
/*    */ 
/*    */   public void prepare() throws Exception
/*    */   {
/* 52 */     if ((!StringUtils.isEmpty(this.request.getParameter("first"))) && (this.request.getParameter("first").equals("true")))
/*    */     {
/* 54 */       return;
/*    */     }
/* 56 */     if (hasId("deptFrame.id")) {
/* 57 */       this.deptFrame = this.departmentManager.loadDepartment(getId("deptFrame.id"));
/* 58 */       this.users = this.userManager.getUsersByDeptId(this.deptFrame.getId());
/*    */ 
/* 60 */       this.depts = this.departmentManager.getDeptsByParentDept(getId("deptFrame.id"));
/*    */     }
/*    */     else
/*    */     {
/* 68 */       this.depts = this.departmentManager.getDeptsByStep(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   protected String getAdapterName()
/*    */   {
/* 75 */     return null;
/*    */   }
/*    */ 
/*    */   public List<Department> getDepts() {
/* 79 */     return this.depts;
/*    */   }
/*    */ 
/*    */   public void setDepts(List<Department> depts) {
/* 83 */     this.depts = depts;
/*    */   }
/*    */ 
/*    */   public List<User> getUsers() {
/* 87 */     return this.users;
/*    */   }
/*    */ 
/*    */   public void setUsers(List<User> users) {
/* 91 */     this.users = users;
/*    */   }
/*    */ 
/*    */   public List<User> getDisUsers() {
/* 95 */     return this.disUsers;
/*    */   }
/*    */ 
/*    */   public void setDisUsers(List<User> disUsers) {
/* 99 */     this.disUsers = disUsers;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.security.ListDeptUserFrameAction
 * JD-Core Version:    0.6.2
 */