/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.overTimeBill;
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
/*     */ import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;
/*     */ import com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class EditOverTimeBillAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private OverTimeBillManager overTimeBillManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final WorkflowCaseManager workflowCaseManager;
/*     */   private OverTimeBill overTimeBill;
/*     */   private PersonnelFiles personnelFiles;
/*     */ 
/*     */   public EditOverTimeBillAction(OverTimeBillManager overTimeBillManager, CodeValueManager codeValueManager, DepartmentManager departmentManager, UserManager userManager, PersonnelFilesManager personnelFilesManager, WorkflowCaseManager workflowCaseManager)
/*     */   {
/*  85 */     this.overTimeBillManager = overTimeBillManager;
/*  86 */     this.codeValueManager = codeValueManager;
/*  87 */     this.departmentManager = departmentManager;
/*  88 */     this.userManager = userManager;
/*  89 */     this.personnelFilesManager = personnelFilesManager;
/*  90 */     this.workflowCaseManager = workflowCaseManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  97 */     if (hasId("overTimeBill.id")) {
/*  98 */       this.overTimeBill = this.overTimeBillManager.loadOverTimeBill(getId("overTimeBill.id"));
/*  99 */       this.personnelFiles = this.overTimeBill.getApplyPerson();
/*     */     } else {
/* 101 */       this.overTimeBill = new OverTimeBill();
/*     */ 
/* 106 */       User user = getUser();
/* 107 */       if (null != user.getCode()) {
/* 108 */         List list = this.personnelFilesManager.loadByKey("code", user.getCode());
/* 109 */         if (null != list) {
/* 110 */           this.personnelFiles = ((PersonnelFiles)list.get(0));
/* 111 */           this.overTimeBill.setApplyPerson(this.personnelFiles);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 123 */     boolean isNew = this.overTimeBill.isNew();
/*     */ 
/* 125 */     if (!StringUtils.isEmpty(this.request.getParameter("dept.id"))) {
/* 126 */       this.overTimeBill.setDept(this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept.id"))));
/*     */     }
/*     */ 
/* 131 */     if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
/* 132 */       this.overTimeBill.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("status.id"))));
/*     */     }
/*     */     else {
/* 135 */       this.overTimeBill.setStatus((CodeValue)this.codeValueManager.loadByKey("code", "02000").get(0));
/*     */     }
/*     */ 
/* 139 */     if (null == this.overTimeBill.getDept()) {
/* 140 */       this.overTimeBill.setDept(this.personnelFiles.getDept());
/*     */     }
/* 142 */     if (null == this.overTimeBill.getApplyPerson()) {
/* 143 */       this.overTimeBill.setApplyPerson(this.personnelFiles);
/*     */     }
/*     */ 
/* 146 */     Date startTime = this.overTimeBill.getStartTime();
/* 147 */     Date endTime = this.overTimeBill.getEndTime();
/* 148 */     Long a = Long.valueOf(endTime.getTime());
/* 149 */     Double a1 = Double.valueOf(a.doubleValue());
/*     */ 
/* 151 */     Long b = Long.valueOf(startTime.getTime());
/* 152 */     Double b1 = Double.valueOf(b.doubleValue());
/* 153 */     Double sum1 = Double.valueOf((a1.doubleValue() - b1.doubleValue()) / 3600000.0D);
/*     */ 
/* 156 */     DecimalFormat df = new DecimalFormat("0.0");
/* 157 */     String sum2 = df.format(sum1);
/* 158 */     Double sum = Double.valueOf(Double.parseDouble(sum2));
/* 159 */     this.overTimeBill.setManHour(sum);
/*     */ 
/* 161 */     this.overTimeBill.setOrganization(this.userManager.getOrganization());
/*     */ 
/* 163 */     if (isNew) {
/* 164 */       String newCode = autoCompleteCode();
/* 165 */       this.overTimeBill.setCode(newCode);
/* 166 */       this.overTimeBillManager.storeOverTimeBill(this.overTimeBill);
/* 167 */       addActionMessage(getText("overTimeBill.save.success"));
/* 168 */       WorkflowTrigger(this.personnelFiles);
/* 169 */       return "new";
/*     */     }
/* 171 */     this.overTimeBillManager.storeOverTimeBill(this.overTimeBill);
/* 172 */     addActionMessage(getText("overTimeBill.edit.success"));
/* 173 */     return "success";
/*     */   }
/*     */ 
/*     */   public void WorkflowTrigger(PersonnelFiles person)
/*     */   {
/* 180 */     if (null != this.overTimeBill.getId())
/*     */     {
/* 182 */       String url = "overTimeBill/editOverTimeBill.html?overTimeBill.id=" + String.valueOf(this.overTimeBill.getId());
/* 183 */       this.workflowCaseManager.startWorkflowCase("02104", this.overTimeBill.getId(), person, url);
/*     */     } else {
/* 185 */       List lbList = null;
/*     */       try {
/* 187 */         lbList = this.overTimeBillManager.loadByKey("code", this.overTimeBill.getCode());
/*     */       } catch (Exception e) {
/* 189 */         this.logger.info("根据code查询加班单出错！");
/*     */       }
/* 191 */       if ((null != lbList) && (!lbList.isEmpty())) {
/* 192 */         this.overTimeBill = ((OverTimeBill)lbList.get(0));
/* 193 */         WorkflowTrigger(person);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 203 */     String maxCode = this.overTimeBillManager.getMaxPFCode("JBD", this.userManager.getOrganization().getId());
/*     */ 
/* 205 */     if (null != maxCode) {
/* 206 */       int num = Integer.parseInt(maxCode) + 1;
/* 207 */       if (num < 10)
/* 208 */         return "JBD-000" + num;
/* 209 */       if (num < 100)
/* 210 */         return "JBD-00" + num;
/* 211 */       if (num < 1000) {
/* 212 */         return "JBD-0" + num;
/*     */       }
/* 214 */       return "JBD-" + num;
/*     */     }
/*     */ 
/* 218 */     return "JBD-0001";
/*     */   }
/*     */ 
/*     */   public OverTimeBill getOverTimeBill()
/*     */   {
/* 226 */     return this.overTimeBill;
/*     */   }
/*     */ 
/*     */   public void setOverTimeBill(OverTimeBill overTimeBill)
/*     */   {
/* 234 */     this.overTimeBill = overTimeBill;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 242 */     List codes = null;
/*     */     try {
/* 244 */       codes = new ArrayList();
/* 245 */       List one = this.codeValueManager.loadByKey("code", "020");
/*     */ 
/* 247 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 249 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 251 */         if ((null != list) && (list.size() > 0)) {
/* 252 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 255 */       return codes;
/*     */     } catch (DaoException e) {
/* 257 */       e.printStackTrace();
/*     */     }
/* 259 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 267 */     List depts = this.departmentManager.loadAllDepartments();
/* 268 */     return depts;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 276 */     return this.userManager.getUser();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.overTimeBill.EditOverTimeBillAction
 * JD-Core Version:    0.6.2
 */