/*    */ package com.yongjun.tdms.presentation.webwork.action.base.orgstructure;
/*    */ 
/*    */ import com.yongjun.pluto.model.base.institution.Institution;
/*    */ import com.yongjun.pluto.model.security.Department;
/*    */ import com.yongjun.pluto.webwork.action.BaseAction;
/*    */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*    */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DTreeOrgStructure extends BaseAction
/*    */ {
/*    */   protected final DepartmentManager departmentManager;
/*    */   private final InstitutionManager institutionManager;
/*    */ 
/*    */   public DTreeOrgStructure(DepartmentManager departmentManager, InstitutionManager institutionManager)
/*    */   {
/* 27 */     this.departmentManager = departmentManager;
/* 28 */     this.institutionManager = institutionManager;
/*    */   }
/*    */ 
/*    */   public List<List<Institution>> getAllInstsSortByStep() {
/* 32 */     int count = this.institutionManager.getInstSteps();
/* 33 */     List allInsts = new ArrayList();
/* 34 */     for (int i = 0; i < count; i++) {
/* 35 */       List list = this.institutionManager.getInstsByStep(i);
/* 36 */       if (list != null)
/* 37 */         allInsts.add(list);
/*    */       else {
/* 39 */         allInsts.add(new ArrayList());
/*    */       }
/*    */     }
/* 42 */     if (allInsts == null) {
/* 43 */       allInsts = new ArrayList();
/*    */     }
/* 45 */     return allInsts;
/*    */   }
/*    */ 
/*    */   public List<List<Department>> getAllDeptSortByStep()
/*    */   {
/* 50 */     int count = this.departmentManager.getDeptSteps();
/* 51 */     List allDepts = new ArrayList();
/* 52 */     for (int i = 0; i < count; i++) {
/* 53 */       List list = this.departmentManager.getDeptsByStep(i);
/* 54 */       if (list != null)
/* 55 */         allDepts.add(list);
/*    */       else {
/* 57 */         allDepts.add(new ArrayList());
/*    */       }
/*    */     }
/*    */ 
/* 61 */     if (allDepts == null) {
/* 62 */       allDepts = new ArrayList();
/*    */     }
/* 64 */     return allDepts;
/*    */   }
/*    */ 
/*    */   public int getCount() {
/* 68 */     return this.institutionManager.getInstMaxId();
/*    */   }
/*    */ 
/*    */   public String execute() throws Exception {
/* 72 */     return "success";
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.orgstructure.DTreeOrgStructure
 * JD-Core Version:    0.6.2
 */