/*     */ package com.yongjun.tdms.presentation.webwork.action.backvisit;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
/*     */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.service.backvisit.BackVisitManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListChangeStepToHistoryAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValueManager codeValueManager;
/*     */   private BackVisitManager backVisitManager;
/*     */   private List<BackVisit> backVisits;
/*     */   private List<StepInfo> stepInfos;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
/*     */ 
/*     */   public ListChangeStepToHistoryAction(UserManager userManager)
/*     */   {
/*  30 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public List<BackVisit> getBackVisits() {
/*  34 */     return this.backVisits;
/*     */   }
/*     */ 
/*     */   public void setBackVisits(List<BackVisit> backVisits) {
/*  38 */     this.backVisits = backVisits;
/*     */   }
/*     */ 
/*     */   public BackVisitManager getBackVisitManager() {
/*  42 */     return this.backVisitManager;
/*     */   }
/*     */ 
/*     */   public CodeValueManager getCodeValueManager() {
/*  46 */     return this.codeValueManager;
/*     */   }
/*     */ 
/*     */   public PersonnelFilesManager getPersonnelFilesManager() {
/*  50 */     return this.personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFilesManager(PersonnelFilesManager personnelFilesManager) {
/*  54 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public UserManager getUserManager() {
/*  58 */     return this.userManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  64 */     return "stepInfo";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  84 */     if ((this.backVisits == null) && (hasIds("backVisitIds"))) {
/*  85 */       this.backVisits = this.backVisitManager.loadAllBackVisit(getIds("backVisitIds"));
/*     */     }
/*  87 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  92 */     if (isDisabled()) {
/*  93 */       return disabled();
/*     */     }
/*     */ 
/*  96 */     if (isEnable()) {
/*  97 */       return enable();
/*     */     }
/*     */ 
/* 100 */     if (isDelete()) {
/* 101 */       return delete();
/*     */     }
/* 103 */     return "success";
/*     */   }
/*     */ 
/*     */   public boolean getIsPersonnelFiles() throws Exception {
/* 107 */     if (null == this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode())) {
/* 108 */       return false;
/*     */     }
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   private String delete() {
/*     */     try {
/* 115 */       this.backVisitManager.deleteAllBackVisit(this.backVisits);
/* 116 */       addActionMessage(getText("backVisits.delete.success"));
/* 117 */       return "success";
/*     */     } catch (Exception e) {
/* 119 */       addActionMessage(getText("backVisits.delete.failer"));
/* 120 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enable()
/*     */   {
/*     */     try {
/* 126 */       this.backVisitManager.enableBackVisits(this.backVisits);
/* 127 */       addActionMessage(getText("backVisits.enable.success"));
/* 128 */       return "success";
/*     */     } catch (Exception e) {
/* 130 */       addActionMessage(getText("backVisits.enable.failer"));
/* 131 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try {
/* 137 */       this.backVisitManager.disabledBackVisits(this.backVisits);
/* 138 */       addActionMessage(getText("backVisits.disabled.success"));
/* 139 */       return "success";
/*     */     } catch (Exception e) {
/* 141 */       addActionMessage(getText("backVisits.disabled.failer"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBackVisitWay()
/*     */   {
/* 147 */     List cust_types = new ArrayList();
/*     */     try {
/* 149 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "007").get(0);
/* 150 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/* 151 */       if (cust_types != null) {
/* 152 */         CodeValue cv = new CodeValue();
/* 153 */         cv.setId(Long.valueOf(-1L));
/* 154 */         cv.setName(getText("select.option.all"));
/* 155 */         cust_types.add(0, cv);
/* 156 */         return cust_types;
/*     */       }
/* 158 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e) {
/* 161 */       e.printStackTrace();
/* 162 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public void setCodeValueManager(CodeValueManager codeValueManager)
/*     */   {
/* 167 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void setBackVisitManager(BackVisitManager backVisitManager) {
/* 171 */     this.backVisitManager = backVisitManager;
/*     */   }
/*     */ 
/*     */   public List<StepInfo> getStepInfos()
/*     */   {
/* 178 */     return this.stepInfos;
/*     */   }
/*     */ 
/*     */   public void setStepInfos(List<StepInfo> stepInfos)
/*     */   {
/* 185 */     this.stepInfos = stepInfos;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.ListChangeStepToHistoryAction
 * JD-Core Version:    0.6.2
 */