/*     */ package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseapply;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseapply.ExpenseApply;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.expensemanagement.expenseapply.ExpenseApplyManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditExpenseApplyAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ExpenseApplyManager expenseApplyManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final DepartmentManager departmentManager;
/*  82 */   private ExpenseApply expenseApply = null;
/*     */ 
/*  87 */   private PersonnelFiles applyPerson = null;
/*     */ 
/*  91 */   private Institution institution = null;
/*     */   private Department deparment;
/*     */   private CodeValue expenseType;
/*     */   private CodeValue applyState;
/*     */ 
/*     */   public EditExpenseApplyAction(ExpenseApplyManager expenseApplyManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, DepartmentManager departmentManager, InstitutionManager institutionManager, UserManager userManager)
/*     */   {
/* 120 */     this.expenseApplyManager = expenseApplyManager;
/* 121 */     this.personnelFilesManager = personnelFilesManager;
/* 122 */     this.codeValueManager = codeValueManager;
/* 123 */     this.departmentManager = departmentManager;
/* 124 */     this.institutionManager = institutionManager;
/* 125 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 133 */     if (null == this.expenseApply) {
/* 134 */       if (hasId("expenseApply.id")) {
/* 135 */         this.expenseApply = this.expenseApplyManager.loadExpenseApply(getId("expenseApply.id"));
/*     */       }
/*     */       else {
/* 138 */         this.expenseApply = new ExpenseApply();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 143 */     if (null == this.deparment) {
/* 144 */       if (hasId("deparment.id")) {
/* 145 */         this.deparment = this.departmentManager.loadDepartment(getId("deparment.id"));
/*     */       }
/*     */       else {
/* 148 */         this.deparment = null;
/*     */       }
/*     */     }
/*     */ 
/* 152 */     if (null == this.applyPerson) {
/* 153 */       if (hasId("applyPerson.id")) {
/* 154 */         this.applyPerson = this.personnelFilesManager.loadPersonnel(getId("applyPerson.id"));
/*     */       }
/*     */       else {
/* 157 */         this.applyPerson = null;
/*     */       }
/*     */     }
/*     */ 
/* 161 */     if (null == this.expenseType) {
/* 162 */       if (hasId("expenseType.id")) {
/* 163 */         this.expenseType = this.codeValueManager.loadCodeValue(getId("expenseType.id"));
/*     */       }
/*     */       else {
/* 166 */         this.expenseType = null;
/*     */       }
/*     */     }
/* 169 */     if (null == this.institution) {
/* 170 */       if (hasId("institution.id")) {
/* 171 */         this.institution = this.institutionManager.loadInstitution(getId("institution.id"));
/*     */       }
/*     */       else {
/* 174 */         this.institution = null;
/*     */       }
/*     */     }
/* 177 */     if (null == this.applyState)
/* 178 */       if (hasId("applyState.id")) {
/* 179 */         this.applyState = this.codeValueManager.loadCodeValue(getId("applyState.id"));
/*     */       }
/*     */       else
/* 182 */         this.applyState = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 193 */     boolean isNew = this.expenseApply.isNew();
/* 194 */     this.expenseApply.setOrganization(this.userManager.getOrganization());
/* 195 */     if (isNew) {
/* 196 */       String code = autoCompleteCode();
/* 197 */       this.expenseApply.setCode(code);
/*     */     }
/*     */     try {
/* 200 */       this.expenseApply.setApplyPerson(this.applyPerson);
/* 201 */       this.expenseApply.setExpenseType(this.expenseType);
/* 202 */       this.expenseApply.setInstitution(this.institution);
/* 203 */       this.expenseApply.setDeparment(this.deparment);
/* 204 */       this.expenseApply.setApplyState(this.applyState);
/* 205 */       this.expenseApplyManager.storeExpenseApply(this.expenseApply);
/* 206 */       if (isNew) {
/* 207 */         addActionMessage(getText("expenseApply.add.success"));
/* 208 */         return "new";
/*     */       }
/* 210 */       addActionMessage(getText("expenseApply.edit.success"));
/* 211 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 215 */       ex.printStackTrace();
/* 216 */       if (isNew) {
/* 217 */         addActionMessage(getText("expenseApply.add.error"));
/* 218 */         return "new";
/*     */       }
/* 220 */       addActionMessage(getText("expenseApply.edit.error"));
/* 221 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepartment()
/*     */   {
/* 230 */     List depts = this.departmentManager.loadAllDepartments();
/* 231 */     return depts;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 239 */     String prefix = "FY";
/* 240 */     String maxCode = this.expenseApplyManager.getMaxPFCode(prefix);
/* 241 */     if (null != maxCode) {
/* 242 */       int num = Integer.parseInt(maxCode) + 1;
/* 243 */       if (num < 10)
/* 244 */         return prefix + "-00000" + num;
/* 245 */       if (num < 100)
/* 246 */         return prefix + "-0000" + num;
/* 247 */       if (num < 1000)
/* 248 */         return prefix + "-000" + num;
/* 249 */       if (num < 10000)
/* 250 */         return prefix + "-00" + num;
/* 251 */       if (num < 100000) {
/* 252 */         return prefix + "-0" + num;
/*     */       }
/* 254 */       return prefix + "-" + num;
/*     */     }
/*     */ 
/* 257 */     return prefix + "-000001";
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllInsts()
/*     */   {
/* 267 */     List list = this.institutionManager.loadAllInstitution();
/* 268 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 277 */     List list = new ArrayList();
/* 278 */     return list;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllExpenseType()
/*     */   {
/* 285 */     List codes = null;
/*     */     try {
/* 287 */       codes = new ArrayList();
/* 288 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("069"));
/*     */ 
/* 290 */       if ((null != one) && (one.size() > 0)) {
/* 291 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 293 */         if ((null != list) && (list.size() > 0)) {
/* 294 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 297 */       return codes;
/*     */     } catch (DaoException e) {
/* 299 */       e.printStackTrace();
/*     */     }
/* 301 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllApplyState()
/*     */   {
/* 308 */     List codes = null;
/*     */     try {
/* 310 */       codes = new ArrayList();
/* 311 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("071"));
/*     */ 
/* 313 */       if ((null != one) && (one.size() > 0)) {
/* 314 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 316 */         if ((null != list) && (list.size() > 0)) {
/* 317 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 320 */       return codes;
/*     */     } catch (DaoException e) {
/* 322 */       e.printStackTrace();
/*     */     }
/* 324 */     return codes;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 331 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelF()
/*     */     throws Exception
/*     */   {
/* 339 */     List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
/*     */ 
/* 341 */     if ((null != pfs) && (pfs.size() > 0)) {
/* 342 */       return (PersonnelFiles)pfs.get(0);
/*     */     }
/* 344 */     return null;
/*     */   }
/*     */ 
/*     */   public ExpenseApply getExpenseApply()
/*     */   {
/* 352 */     return this.expenseApply;
/*     */   }
/*     */ 
/*     */   public void setExpenseApply(ExpenseApply expenseApply)
/*     */   {
/* 359 */     this.expenseApply = expenseApply;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseapply.EditExpenseApplyAction
 * JD-Core Version:    0.6.2
 */