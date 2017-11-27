/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.contractadministrator;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.jobs.JobsManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditContractAdministratorAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 8833243999062386287L;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final DutyManager dutyManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final ContractAdministratorManager contractAdministratorManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final JobsManager jobsManager;
/*     */   private final InstitutionManager institutionManager;
/*     */   private ContractAdministrator contractAdministrator;
/*     */ 
/*     */   public EditContractAdministratorAction(DepartmentManager departmentManager, DutyManager dutyManager, CodeValueManager codeValueManager, ContractAdministratorManager contractAdministratorManager, PersonnelFilesManager personnelFilesManager, JobsManager jobsManager, InstitutionManager institutionManager)
/*     */   {
/*  83 */     this.departmentManager = departmentManager;
/*  84 */     this.dutyManager = dutyManager;
/*  85 */     this.codeValueManager = codeValueManager;
/*  86 */     this.contractAdministratorManager = contractAdministratorManager;
/*  87 */     this.personnelFilesManager = personnelFilesManager;
/*  88 */     this.jobsManager = jobsManager;
/*  89 */     this.institutionManager = institutionManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  97 */     if (hasId("contractAdministrator.id")) {
/*  98 */       this.contractAdministrator = this.contractAdministratorManager.loadContractAdministrator(getId("contractAdministrator.id"));
/*     */     }
/*     */     else
/* 101 */       this.contractAdministrator = new ContractAdministrator();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 111 */     System.out.println(this.contractAdministrator.getPayAccount());
/* 112 */     boolean isNew = this.contractAdministrator.isNew();
/*     */ 
/* 114 */     if (!StringUtils.isEmpty(this.request.getParameter("personnelName.id"))) {
/* 115 */       PersonnelFiles emplyee = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("personnelName.id")));
/*     */ 
/* 117 */       if (null != emplyee) {
/* 118 */         this.contractAdministrator.setPersonnelName(emplyee);
/*     */       }
/*     */     }
/*     */ 
/* 122 */     if (!StringUtils.isEmpty(this.request.getParameter("dept.id"))) {
/* 123 */       this.contractAdministrator.setDept(this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept.id"))));
/*     */     }
/*     */ 
/* 133 */     if (!StringUtils.isEmpty(this.request.getParameter("duty.id"))) {
/* 134 */       this.contractAdministrator.setDuty(this.dutyManager.loadDuty(Long.valueOf(this.request.getParameter("duty.id"))));
/*     */     }
/*     */ 
/* 149 */     if (!StringUtils.isEmpty(this.request.getParameter("contractType.id"))) {
/* 150 */       this.contractAdministrator.setContractType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("contractType.id"))));
/*     */     }
/*     */ 
/* 165 */     if (!StringUtils.isEmpty(this.request.getParameter("principalName.id"))) {
/* 166 */       this.contractAdministrator.setPrincipalName(this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("principalName.id"))));
/*     */     }
/*     */ 
/* 171 */     if (!StringUtils.isEmpty(this.request.getParameter("institution.id"))) {
/* 172 */       this.contractAdministrator.setInstitustion(this.institutionManager.loadInstitution(Long.valueOf(this.request.getParameter("institution.id"))));
/*     */     }
/*     */ 
/* 175 */     if (isNew) {
/* 176 */      /* String newCode = autoCompleteCode();
 177        this.contractAdministrator.setContractCode(newCode);*/
/* 178 */       this.contractAdministratorManager.storeContractAdministrator(this.contractAdministrator);
/* 179 */       addActionMessage(getText("contractAdministrator.save.success"));
/* 180 */       return "new";
/*     */     }
/* 182 */     this.contractAdministratorManager.storeContractAdministrator(this.contractAdministrator);
/* 183 */     addActionMessage(getText("contractAdministrator.edit.success"));
/* 184 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 193 */     List list = this.departmentManager.loadAllDepartments();
/* 194 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Duty> getAllDutys()
/*     */   {
/* 201 */     List list = this.dutyManager.loadAllDuty();
/* 202 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Jobs> getAllJobs()
/*     */   {
/* 209 */     List list = this.jobsManager.loadAllJobs();
/* 210 */     return list;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllCrfts()
/*     */   {
/* 217 */     List codes = null;
/*     */     try {
/* 219 */       codes = new ArrayList();
/* 220 */       List one = this.codeValueManager.loadByKey("code", "055551");
/*     */ 
/* 222 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 224 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 226 */         if ((null != list) && (list.size() > 0)) {
/* 227 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 230 */       return codes;
/*     */     } catch (DaoException e) {
/* 232 */       e.printStackTrace();
/*     */     }
/* 234 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllLevels()
/*     */   {
/* 241 */     List codes = null;
/*     */     try {
/* 243 */       codes = new ArrayList();
/* 244 */       List one = this.codeValueManager.loadByKey("code", "055552");
/*     */ 
/* 246 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 248 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 250 */         if ((null != list) && (list.size() > 0)) {
/* 251 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 254 */       return codes;
/*     */     } catch (DaoException e) {
/* 256 */       e.printStackTrace();
/*     */     }
/* 258 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllContractType()
/*     */   {
/* 265 */     List codes = null;
/*     */     try {
/* 267 */       codes = new ArrayList();
/* 268 */       List one = this.codeValueManager.loadByKey("code", "055555");
/*     */ 
/* 270 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 272 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 274 */         if ((null != list) && (list.size() > 0)) {
/* 275 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 278 */       return codes;
/*     */     } catch (DaoException e) {
/* 280 */       e.printStackTrace();
/*     */     }
/* 282 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllVntu()
/*     */   {
/* 289 */     List codes = null;
/*     */     try {
/* 291 */       codes = new ArrayList();
/* 292 */       List one = this.codeValueManager.loadByKey("code", "055553");
/*     */ 
/* 294 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 296 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 298 */         if ((null != list) && (list.size() > 0)) {
/* 299 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 302 */       return codes;
/*     */     } catch (DaoException e) {
/* 304 */       e.printStackTrace();
/*     */     }
/* 306 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBecomes()
/*     */   {
/* 313 */     List codes = null;
/*     */     try {
/* 315 */       codes = new ArrayList();
/* 316 */       List one = this.codeValueManager.loadByKey("code", "055554");
/*     */ 
/* 318 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 320 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 322 */         if ((null != list) && (list.size() > 0)) {
/* 323 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 326 */       return codes;
/*     */     } catch (DaoException e) {
/* 328 */       e.printStackTrace();
/*     */     }
/* 330 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 338 */     String maxCode = this.contractAdministratorManager.getMaxPFCode("CAM");
/* 339 */     if (null != maxCode) {
/* 340 */       int num = Integer.parseInt(maxCode) + 1;
/* 341 */       if (num < 10)
/* 342 */         return "CAM-00" + num;
/* 343 */       if (num < 100) {
/* 344 */         return "CAM-0" + num;
/*     */       }
/* 346 */       return "CAM-" + num;
/*     */     }
/*     */ 
/* 350 */     return "CAM-001";
/*     */   }
/*     */ 
/*     */   public ContractAdministrator getContractAdministrator()
/*     */   {
/* 359 */     return this.contractAdministrator;
/*     */   }
/*     */ 
/*     */   public void setContractAdministrator(ContractAdministrator contractAdministrator)
/*     */   {
/* 368 */     this.contractAdministrator = contractAdministrator;
/*     */   }
/*     */ 
/*     */   public List getInstitutions()
/*     */   {
/* 376 */     return this.institutionManager.getInstitutions();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.contractadministrator.EditContractAdministratorAction
 * JD-Core Version:    0.6.2
 */