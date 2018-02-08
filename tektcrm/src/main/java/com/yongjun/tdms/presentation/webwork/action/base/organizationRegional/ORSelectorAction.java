/*     */ package com.yongjun.tdms.presentation.webwork.action.base.organizationRegional;
/*     */ 
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ORSelectorAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected final UserManager userManager;
/*     */   protected final DepartmentManager departmentManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private User user;
/*     */ 
/*     */   public ORSelectorAction(UserManager userManager, DepartmentManager departmentManager, InstitutionManager institutionManager)
/*     */   {
/*  64 */     this.userManager = userManager;
/*  65 */     this.departmentManager = departmentManager;
/*  66 */     this.institutionManager = institutionManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  74 */     if ((this.user == null) && 
/*  75 */       (hasId("userId")))
/*  76 */       this.user = this.userManager.loadUser(getId("userId"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  87 */     if (isAddOR()) {
/*  88 */       addToOrganizationRegional();
/*     */     }
/*  90 */     if (isQuitOR()) {
/*  91 */       quitToOrganizationRegional();
/*     */     }
/*  93 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<List<Institution>> getAllInstsSortByStep()
/*     */   {
/* 103 */     int count = this.institutionManager.getInstSteps();
/* 104 */     List allInsts = new ArrayList();
/* 105 */     for (int i = 0; i < count; i++) {
/* 106 */       List list = this.institutionManager.getInstsByStep(i);
/* 107 */       allInsts.add(list);
/*     */     }
/* 109 */     return allInsts;
/*     */   }
/*     */ 
/*     */   public List<List<Department>> getAllDeptSortByStep()
/*     */   {
/* 119 */     int count = this.departmentManager.getDeptSteps();
/* 120 */     List allDepts = new ArrayList();
/* 121 */     for (int i = 0; i < count; i++) {
/* 122 */       List list = this.departmentManager.getDeptsByStep(i);
/* 123 */       list.removeAll(this.user.getDepartments());
/* 124 */       allDepts.add(list);
/*     */     }
/* 126 */     return allDepts;
/*     */   }
/*     */ 
/*     */   public int getCount()
/*     */   {
/* 136 */     return this.institutionManager.getInstMaxId();
/*     */   }
/*     */ 
/*     */   public List<List<Department>> getDepartments()
/*     */   {
/* 145 */     List<Department> depts = new ArrayList();
/* 146 */     depts.addAll(this.user.getDepartments());
/*     */ 
/* 148 */     List<Integer> counts = new ArrayList();
/* 149 */     for (int i = 0; i < depts.size(); i++) {
/* 150 */       if (!counts.contains(Integer.valueOf(((Department)depts.get(i)).getStep()))) {
/* 151 */         counts.add(Integer.valueOf(((Department)depts.get(i)).getStep()));
/*     */       }
/*     */     }
/*     */ 
/* 155 */     Collections.sort(counts);
/*     */ 
/* 158 */     List allDepts = new ArrayList();
/* 159 */     for (Integer currentStep : counts) {
/* 160 */       List list = new ArrayList();
/* 161 */       for (Department department : depts) {
/* 162 */         if (department.getStep() == currentStep.intValue()) {
/* 163 */           list.add(department);
/*     */         }
/*     */       }
/* 166 */       allDepts.add(list);
/*     */     }
/* 168 */     return allDepts;
/*     */   }
/*     */ 
/*     */   public List<List<Institution>> getInstitutions()
/*     */   {
/* 177 */     List insts = new ArrayList();
/* 178 */     List<Department> depts = new ArrayList();
/* 179 */     depts.addAll(this.user.getDepartments());
/* 180 */     for (Department dept : depts) {
/* 181 */       Institution inst = dept.getInst();
/* 182 */       insts.add(inst);
/*     */ 
/* 184 */       searchParentInsts(inst, insts);
/*     */     }
/*     */ 
/* 187 */     Set sets = new HashSet();
/* 188 */     sets.addAll(insts);
/*     */ 
/* 190 */     List<Institution> lists = new ArrayList();
/* 191 */     lists.addAll(sets);
/*     */ 
/* 194 */     List<Integer> counts = new ArrayList();
/* 195 */     for (int i = 0; i < lists.size(); i++) {
/* 196 */       if (!counts.contains(Integer.valueOf(((Institution)lists.get(i)).getStep()))) {
/* 197 */         counts.add(Integer.valueOf(((Institution)lists.get(i)).getStep()));
/*     */       }
/*     */     }
/* 200 */     Collections.sort(counts);
/*     */ 
/* 203 */     List allInsts = new ArrayList();
/* 204 */     for (Integer currentStep : counts) {
/* 205 */       List list = new ArrayList();
/* 206 */       for (Institution institution : lists) {
/* 207 */         if (institution.getStep() == currentStep.intValue()) {
/* 208 */           list.add(institution);
/*     */         }
/*     */       }
/* 211 */       allInsts.add(list);
/*     */     }
/* 213 */     return allInsts;
/*     */   }
/*     */ 
/*     */   public List<Institution> searchParentInsts(Institution institution, List<Institution> lists)
/*     */   {
/* 224 */     if (institution.getParentInst() != null) {
/* 225 */       lists.add(institution.getParentInst());
/* 226 */       searchParentInsts(institution.getParentInst(), lists);
/*     */     }
/* 228 */     return lists;
/*     */   }
/*     */ 
/*     */   public boolean isAddOR() {
/* 232 */     return hasKey("join");
/*     */   }
/*     */ 
/*     */   public boolean isQuitOR() {
/* 236 */     return hasKey("leave");
/*     */   }
/*     */ 
/*     */   public String addToOrganizationRegional()
/*     */   {
/* 244 */     String ids = this.request.getParameter("ids");
/* 245 */     String[] id = null;
/* 246 */     id = ids.split(",");
/* 247 */     for (int i = 0; i < id.length; i++) {
/* 248 */       Department dept = this.departmentManager.loadDepartment(Long.valueOf(id[i]));
/* 249 */       dept.addUser(this.user);
/* 250 */       this.departmentManager.storeDepartment(dept);
/*     */     }
/* 252 */     return "success";
/*     */   }
/*     */ 
/*     */   public String quitToOrganizationRegional()
/*     */   {
/* 260 */     String ids = this.request.getParameter("ids");
/* 261 */     String[] id = null;
/* 262 */     id = ids.split(",");
/* 263 */     List depts = new ArrayList();
/* 264 */     for (int i = 0; i < id.length; i++) {
/* 265 */       Department dept = this.departmentManager.loadDepartment(Long.valueOf(id[i]));
/* 266 */       depts.add(dept);
/*     */     }
/* 268 */     for (Iterator i$ = depts.iterator(); i$.hasNext(); ) {
/* 269 */       Department dept = (Department)i$.next();
/* 270 */       dept.removeUser(this.user);
/* 271 */       this.departmentManager.storeDepartment(dept);
/*     */     }
/* 273 */     return "success";
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 280 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user)
/*     */   {
/* 287 */     this.user = user;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.organizationRegional.ORSelectorAction
 * JD-Core Version:    0.6.2
 */