/*     */ package com.yongjun.tdms.presentation.webwork.action.security;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.OrganizationManager;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class ListDeptFrameAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 8068404883875287435L;
/*     */   protected final UserManager userManager;
/*     */   protected final DepartmentManager departmentManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final OrganizationManager organizationManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private User user;
/*     */ 
/*     */   public ListDeptFrameAction(UserManager userManager, DepartmentManager departmentManager, InstitutionManager institutionManager, OrganizationManager organizationManager, CodeValueManager codeValueManager)
/*     */   {
/*  64 */     this.userManager = userManager;
/*  65 */     this.departmentManager = departmentManager;
/*  66 */     this.institutionManager = institutionManager;
/*  67 */     this.organizationManager = organizationManager;
/*  68 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  72 */     this.user = this.userManager.getUser();
/*  73 */     this.user = this.userManager.loadUser(Long.valueOf(this.user.getId().longValue()));
/*     */   }
/*     */ 
/*     */   public int getMaxOrgId()
/*     */   {
/*  81 */     return this.organizationManager.getMaxOrgId();
/*     */   }
/*     */ 
/*     */   public String getCurrentOrgName()
/*     */   {
/*  90 */     return this.userManager.getOrganization().getName();
/*     */   }
/*     */ 
/*     */   public List<List<Institution>> getAllInsts()
/*     */   {
/*  99 */     List insts = new ArrayList();
/*     */     try {
/* 101 */       insts = this.institutionManager.loadByKey("disabled", Integer.valueOf(0));
/*     */     } catch (DaoException e) {
/* 103 */       this.logger.info("查询所有有效的单位出错！");
/*     */     }
/* 105 */     List<Department> depts = new ArrayList();
/*     */     try {
/* 107 */       depts = this.departmentManager.loadByKey("disabled", Integer.valueOf(0));
/*     */     } catch (DaoException e) {
/* 109 */       this.logger.info("查询所有有效的部门出错！");
/*     */     }
/*     */ 
/* 112 */     for (Department dept : depts) {
/* 113 */       Institution inst = dept.getInst();
/* 114 */       insts.add(inst);
/*     */ 
/* 116 */       searchParentInsts(inst, insts);
/*     */     }
/*     */ 
/* 119 */     Set sets = new HashSet();
/* 120 */     sets.addAll(insts);
/*     */ 
/* 122 */     List<Institution> lists = new ArrayList();
/* 123 */     lists.addAll(sets);
/*     */ 
/* 126 */     List<Integer> counts = new ArrayList();
/* 127 */     for (int i = 0; i < lists.size(); i++) {
/* 128 */       if (!counts.contains(Integer.valueOf(((Institution)lists.get(i)).getStep()))) {
/* 129 */         counts.add(Integer.valueOf(((Institution)lists.get(i)).getStep()));
/*     */       }
/*     */     }
/* 132 */     Collections.sort(counts);
/*     */ 
/* 135 */     List allInsts = new ArrayList();
/* 136 */     for (Integer currentStep : counts) {
/* 137 */       List list = new ArrayList();
/* 138 */       for (Institution institution : lists) {
/* 139 */         if (institution.getStep() == currentStep.intValue()) {
/* 140 */           list.add(institution);
/*     */         }
/*     */       }
/* 143 */       allInsts.add(list);
/*     */     }
/* 145 */     return allInsts;
/*     */   }
/*     */ 
/*     */   public List<Institution> searchParentInsts(Institution institution, List<Institution> lists)
/*     */   {
/* 155 */     if (institution.getParentInst() != null) {
/* 156 */       lists.add(institution.getParentInst());
/* 157 */       searchParentInsts(institution.getParentInst(), lists);
/*     */     }
/* 159 */     return lists;
/*     */   }
/*     */ 
/*     */   public List<List<Department>> getAllDepts()
/*     */   {
/* 168 */     List<Department> depts = new ArrayList();
/* 169 */     depts.addAll(this.user.getDepartments());
/* 170 */     depts.add(this.user.getDepartment());
/*     */ 
/* 172 */     List<Integer> counts = new ArrayList();
/* 173 */     for (int i = 0; i < depts.size(); i++) {
/* 174 */       if (!counts.contains(Integer.valueOf(((Department)depts.get(i)).getStep()))) {
/* 175 */         counts.add(Integer.valueOf(((Department)depts.get(i)).getStep()));
/*     */       }
/*     */     }
/*     */ 
/* 179 */     Collections.sort(counts);
/*     */ 
/* 182 */     List allDepts = new ArrayList();
/* 183 */     for (Integer currentStep : counts) {
/* 184 */       List list = new ArrayList();
/* 185 */       for (Department department : depts) {
/* 186 */         if (department.getStep() == currentStep.intValue()) {
/* 187 */           list.add(department);
/*     */         }
/*     */       }
/* 190 */       allDepts.add(list);
/*     */     }
/* 192 */     return allDepts;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllIndustry()
/*     */   {
/* 201 */     List industries = null;
/*     */     try {
/* 203 */       List cvs = this.codeValueManager.loadByKey("code", "002");
/*     */ 
/* 205 */       CodeValue c = null;
/* 206 */       if (cvs != null)
/* 207 */         c = (CodeValue)cvs.get(0);
/*     */       else {
/* 209 */         return new ArrayList();
/*     */       }
/* 211 */       if (c != null) {
/* 212 */         industries = this.codeValueManager.loadByKey("parentCV", c.getId());
/*     */       }
/*     */       else
/* 215 */         return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 219 */       e.printStackTrace();
/*     */     }
/* 221 */     return industries;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllNature()
/*     */   {
/* 229 */     List natures = null;
/*     */     try {
/* 231 */       List cvs = this.codeValueManager.loadByKey("code", "003");
/*     */ 
/* 233 */       CodeValue c = null;
/* 234 */       if (cvs != null)
/* 235 */         c = (CodeValue)cvs.get(0);
/*     */       else {
/* 237 */         return new ArrayList();
/*     */       }
/* 239 */       if (c != null) {
/* 240 */         natures = this.codeValueManager.loadByKey("parentCV", c.getId());
/*     */       }
/*     */       else
/* 243 */         return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 247 */       e.printStackTrace();
/*     */     }
/* 249 */     return natures;
/*     */   }
/*     */ 
/*     */   public int getCount()
/*     */   {
/* 258 */     return this.institutionManager.getInstMaxId();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.security.ListDeptFrameAction
 * JD-Core Version:    0.6.2
 */