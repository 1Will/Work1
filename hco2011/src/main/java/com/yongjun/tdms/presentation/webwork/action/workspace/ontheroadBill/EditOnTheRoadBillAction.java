/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.ontheroadBill;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;
/*     */ import com.yongjun.tdms.service.workspace.ontheroadBill.OnTheRoadBillManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditOnTheRoadBillAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final OnTheRoadBillManager onTheRoadBillManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final WorkflowCaseManager workflowCaseManager;
/*     */   private OnTheRoadBill onTheRoadBill;
/*     */   private PersonnelFiles personnelFiles;
/*     */ 
/*     */   public EditOnTheRoadBillAction(OnTheRoadBillManager onTheRoadBillManager, CodeValueManager codeValueManager, DepartmentManager departmentManager, UserManager userManager, PersonnelFilesManager personnelFilesManager, WorkflowCaseManager workflowCaseManager)
/*     */   {
/*  83 */     this.onTheRoadBillManager = onTheRoadBillManager;
/*  84 */     this.codeValueManager = codeValueManager;
/*  85 */     this.departmentManager = departmentManager;
/*  86 */     this.userManager = userManager;
/*  87 */     this.personnelFilesManager = personnelFilesManager;
/*  88 */     this.workflowCaseManager = workflowCaseManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  95 */     if (hasId("onTheRoadBill.id")) {
/*  96 */       this.onTheRoadBill = this.onTheRoadBillManager.loadOnTheRoadBill(getId("onTheRoadBill.id"));
/*     */ 
/*  98 */       this.personnelFiles = this.onTheRoadBill.getApplyPerson();
/*     */     } else {
/* 100 */       this.onTheRoadBill = new OnTheRoadBill();
/* 101 */       User user = getUser();
/* 102 */       if (null != user.getCode()) {
/* 103 */         List list = this.personnelFilesManager.loadByKey("code", user.getCode());
/* 104 */         if (null != list) {
/* 105 */           this.personnelFiles = ((PersonnelFiles)list.get(0));
/* 106 */           this.onTheRoadBill.setApplyPerson(this.personnelFiles);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 119 */     boolean isNew = this.onTheRoadBill.isNew();
/*     */ 
/* 126 */     if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
/* 127 */       this.onTheRoadBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("status.id"))));
/*     */     }
/*     */     else {
/* 130 */       this.onTheRoadBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(219L)));
/*     */     }
/*     */ 
/* 135 */     if (null == this.onTheRoadBill.getDept()) {
/* 136 */       this.onTheRoadBill.setDept(this.personnelFiles.getDept());
/*     */     }
/* 138 */     if (null == this.onTheRoadBill.getApplyPerson()) {
/* 139 */       this.onTheRoadBill.setApplyPerson(this.personnelFiles);
/*     */     }
/*     */ 
/* 142 */     this.onTheRoadBill.setOrganization(this.userManager.getOrganization());
/* 143 */     if (isNew) {
/* 144 */       String newCode = autoCompleteCode();
/* 145 */       this.onTheRoadBill.setCode(newCode);
/* 146 */       this.onTheRoadBillManager.storeOnTheRoadBill(this.onTheRoadBill);
/* 147 */       addActionMessage(getText("onTheRoadBill.save.success"));
/* 148 */       WorkflowTrigger(this.personnelFiles);
/* 149 */       return "new";
/*     */     }
/* 151 */     this.onTheRoadBillManager.storeOnTheRoadBill(this.onTheRoadBill);
/* 152 */     addActionMessage(getText("onTheRoadBill.edit.success"));
/* 153 */     return "success";
/*     */   }
/*     */ 
/*     */   public void WorkflowTrigger(PersonnelFiles person)
/*     */   {
/* 160 */     if (null != this.onTheRoadBill.getId())
/*     */     {
/* 162 */       String url = "onTheRoadBill/editOnTheRoadBill.html?onTheRoadBill.id=" + String.valueOf(this.onTheRoadBill.getId());
/* 163 */       this.workflowCaseManager.startWorkflowCase("02103", this.onTheRoadBill.getId(), person, url);
/*     */     } else {
/* 165 */       List otrList = null;
/*     */       try {
/* 167 */         otrList = this.onTheRoadBillManager.loadByKey("code", this.onTheRoadBill.getCode());
/*     */       } catch (Exception e) {
/* 169 */         this.logger.info("根据code查询公出单出错！");
/*     */       }
/* 171 */       if ((null != otrList) && (!otrList.isEmpty())) {
/* 172 */         this.onTheRoadBill = ((OnTheRoadBill)otrList.get(0));
/* 173 */         WorkflowTrigger(person);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 183 */     String maxCode = this.onTheRoadBillManager.getMaxPFCode("GCD", this.userManager.getOrganization().getId());
/*     */ 
/* 185 */     if (null != maxCode) {
/* 186 */       int num = Integer.parseInt(maxCode) + 1;
/* 187 */       if (num < 10)
/* 188 */         return "GCD-000" + num;
/* 189 */       if (num < 100)
/* 190 */         return "GCD-00" + num;
/* 191 */       if (num < 1000) {
/* 192 */         return "GCD-0" + num;
/*     */       }
/* 194 */       return "GCD-" + num;
/*     */     }
/*     */ 
/* 198 */     return "GCD-0001";
/*     */   }
/*     */ 
/*     */   public OnTheRoadBill getOnTheRoadBill()
/*     */   {
/* 206 */     return this.onTheRoadBill;
/*     */   }
/*     */ 
/*     */   public void setOnTheRoadBill(OnTheRoadBill onTheRoadBill)
/*     */   {
/* 214 */     this.onTheRoadBill = onTheRoadBill;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 223 */     List codes = null;
/*     */     try {
/* 225 */       codes = new ArrayList();
/* 226 */       List one = this.codeValueManager.loadByKey("code", "020");
/*     */ 
/* 228 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 230 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 232 */         if ((null != list) && (list.size() > 0)) {
/* 233 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 236 */       return codes;
/*     */     } catch (DaoException e) {
/* 238 */       e.printStackTrace();
/*     */     }
/* 240 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 248 */     List depts = this.departmentManager.loadAllDepartments();
/* 249 */     return depts;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 257 */     return this.userManager.getUser();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.ontheroadBill.EditOnTheRoadBillAction
 * JD-Core Version:    0.6.2
 */