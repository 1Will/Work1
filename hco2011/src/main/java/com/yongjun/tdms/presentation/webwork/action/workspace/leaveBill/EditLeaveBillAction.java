/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.leaveBill;
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
/*     */ import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;
/*     */ import com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager;
/*     */ import com.yongjun.tdms.service.workspace.workingcycle.WorkingCycleManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditLeaveBillAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final LeaveBillManager leaveBillManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private LeaveBill leaveBill;
/*     */   private PersonnelFiles personnelFiles;
/*     */   private WorkflowCaseManager workflowCaseManager;
/*     */   private WorkingCycleManager workingCycleManager;
/*     */ 
/*     */   public EditLeaveBillAction(LeaveBillManager leaveBillManager, CodeValueManager codeValueManager, DepartmentManager departmentManager, UserManager userManager, PersonnelFilesManager personnelFilesManager, WorkflowCaseManager workflowCaseManager, WorkingCycleManager workingCycleManager)
/*     */   {
/*  87 */     this.leaveBillManager = leaveBillManager;
/*  88 */     this.codeValueManager = codeValueManager;
/*  89 */     this.departmentManager = departmentManager;
/*  90 */     this.userManager = userManager;
/*  91 */     this.personnelFilesManager = personnelFilesManager;
/*  92 */     this.workflowCaseManager = workflowCaseManager;
/*  93 */     this.workingCycleManager = workingCycleManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 101 */     if (hasId("leaveBill.id")) {
/* 102 */       this.leaveBill = this.leaveBillManager.loadLeaveBill(getId("leaveBill.id"));
/* 103 */       this.personnelFiles = this.leaveBill.getApplyPerson();
/*     */     } else {
/* 105 */       this.leaveBill = new LeaveBill();
/* 106 */       User user = getUser();
/* 107 */       if (null != user.getCode()) {
/* 108 */         List list = this.personnelFilesManager.loadByKey("code", user.getCode());
/* 109 */         if (null != list) {
/* 110 */           this.personnelFiles = ((PersonnelFiles)list.get(0));
/* 111 */           this.leaveBill.setApplyPerson(this.personnelFiles);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 123 */     boolean isNew = this.leaveBill.isNew();
/*     */ 
/* 130 */     if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
/* 131 */       this.leaveBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("status.id"))));
/*     */     }
/*     */     else {
/* 134 */       this.leaveBill.setStatus((CodeValue)this.codeValueManager.loadByKey("code", "02000").get(0));
/*     */     }
/*     */ 
/* 138 */     if (!StringUtils.isEmpty(this.request.getParameter("type.id"))) {
/* 139 */       this.leaveBill.setType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("type.id"))));
/*     */     }
/*     */ 
/* 144 */     if (null != this.personnelFiles.getDept()) {
/* 145 */       this.leaveBill.setDept(this.personnelFiles.getDept());
/*     */     }
/* 147 */     if (null == this.leaveBill.getApplyPerson()) {
/* 148 */       this.leaveBill.setApplyPerson(this.personnelFiles);
/*     */     }
/*     */ 
/* 192 */     this.leaveBill.setOrganization(this.userManager.getOrganization());
/* 193 */     if (isNew) {
/* 194 */       String newCode = autoCompleteCode();
/* 195 */       this.leaveBill.setCode(newCode);
/* 196 */       this.leaveBillManager.storeLeaveBill(this.leaveBill);
/* 197 */       if (this.leaveBill.getType().getName().equals("调休"))
/* 198 */         addActionMessage(getText("leaveBill.save.success"));
/*     */       else {
/* 200 */         addActionMessage(getText("leave.save.success"));
/*     */       }
/* 202 */       WorkflowTrigger(this.personnelFiles);
/* 203 */       return "new";
/*     */     }
/* 205 */     this.leaveBillManager.storeLeaveBill(this.leaveBill);
/* 206 */     if (this.leaveBill.getType().getName().equals("调休"))
/* 207 */       addActionMessage(getText("leaveBill.edit.success"));
/*     */     else {
/* 209 */       addActionMessage(getText("leave.edit.success"));
/*     */     }
/* 211 */     return "success";
/*     */   }
/*     */ 
/*     */   public void WorkflowTrigger(PersonnelFiles person)
/*     */   {
/* 218 */     if (null != this.leaveBill.getId())
/*     */     {
/* 220 */       String url = "leaveBill/editLeaveBill.html?leaveBill.id=" + String.valueOf(this.leaveBill.getId());
/* 221 */       String aoCode = this.leaveBill.getType().getCode().trim();
/* 222 */       if (aoCode.equals("03000"))
/*     */       {
/* 224 */         this.workflowCaseManager.startWorkflowCase("02101", this.leaveBill.getId(), person, url);
/*     */       }
/*     */       else {
/* 227 */         this.workflowCaseManager.startWorkflowCase("02102", this.leaveBill.getId(), person, url);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 232 */       List lbList = null;
/*     */       try {
/* 234 */         lbList = this.leaveBillManager.loadByKey("code", this.leaveBill.getCode());
/*     */       } catch (Exception e) {
/* 236 */         this.logger.info("根据code查询请假调休出错！");
/*     */       }
/* 238 */       if ((null != lbList) && (!lbList.isEmpty())) {
/* 239 */         this.leaveBill = ((LeaveBill)lbList.get(0));
/* 240 */         WorkflowTrigger(person);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 250 */     String maxCode = this.leaveBillManager.getMaxPFCode("QJTX", this.userManager.getOrganization().getId());
/* 251 */     if (null != maxCode) {
/* 252 */       int num = Integer.parseInt(maxCode) + 1;
/* 253 */       if (num < 10)
/* 254 */         return "QJTX-000" + num;
/* 255 */       if (num < 100)
/* 256 */         return "QJTX-00" + num;
/* 257 */       if (num < 1000) {
/* 258 */         return "QJTX-0" + num;
/*     */       }
/* 260 */       return "QJTX-" + num;
/*     */     }
/*     */ 
/* 264 */     return "QJTX-0001";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 273 */     List codes = null;
/*     */     try {
/* 275 */       codes = new ArrayList();
/* 276 */       List one = this.codeValueManager.loadByKey("code", "020");
/*     */ 
/* 278 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 280 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 282 */         if ((null != list) && (list.size() > 0)) {
/* 283 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 286 */       return codes;
/*     */     } catch (DaoException e) {
/* 288 */       e.printStackTrace();
/*     */     }
/* 290 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 298 */     List codes = null;
/*     */     try {
/* 300 */       codes = new ArrayList();
/* 301 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("030"));
/*     */ 
/* 303 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 305 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 307 */         if ((null != list) && (list.size() > 0)) {
/* 308 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 311 */       return codes;
/*     */     } catch (DaoException e) {
/* 313 */       e.printStackTrace();
/*     */     }
/* 315 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 323 */     List depts = this.departmentManager.loadAllDepartments();
/* 324 */     return depts;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 332 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public LeaveBill getLeaveBill()
/*     */   {
/* 340 */     return this.leaveBill;
/*     */   }
/*     */ 
/*     */   public void setLeaveBill(LeaveBill leaveBill)
/*     */   {
/* 348 */     this.leaveBill = leaveBill;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.leaveBill.EditLeaveBillAction
 * JD-Core Version:    0.6.2
 */