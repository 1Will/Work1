/*     */ package com.yongjun.tdms.presentation.webwork.action.backvisit;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.service.backvisit.BackVisitManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListBackVisitTabAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValueManager codeValueManager;
/*     */   private BackVisitManager backVisitManager;
/*     */   private List<BackVisit> backVisits;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
/*     */ 
/*     */   public ListBackVisitTabAction(UserManager userManager)
/*     */   {
/*  35 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public List<BackVisit> getBackVisits() {
/*  39 */     return this.backVisits;
/*     */   }
/*     */ 
/*     */   public void setBackVisits(List<BackVisit> backVisits) {
/*  43 */     this.backVisits = backVisits;
/*     */   }
/*     */ 
/*     */   public BackVisitManager getBackVisitManager() {
/*  47 */     return this.backVisitManager;
/*     */   }
/*     */ 
/*     */   public CodeValueManager getCodeValueManager() {
/*  51 */     return this.codeValueManager;
/*     */   }
/*     */ 
/*     */   public PersonnelFilesManager getPersonnelFilesManager() {
/*  55 */     return this.personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFilesManager(PersonnelFilesManager personnelFilesManager) {
/*  59 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public UserManager getUserManager() {
/*  63 */     return this.userManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  69 */     return "backvisit";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  74 */     Map map = super.getRequestParameterMap();
/*  75 */     if (hasId("continueBackVisit")) {
/*  76 */       if (getId("continueBackVisit").equals(Long.valueOf("2")))
/*  77 */         map.put("cbv", null);
/*     */       else {
/*  79 */         map.put("cbv", Long.valueOf(getId("continueBackVisit").longValue()));
/*     */       }
/*     */     }
/*  82 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  89 */     if ((this.backVisits == null) && (hasIds("backVisitIds"))) {
/*  90 */       this.backVisits = this.backVisitManager.loadAllBackVisit(getIds("backVisitIds"));
/*     */     }
/*  92 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  97 */     if (isDisabled()) {
/*  98 */       return disabled();
/*     */     }
/*     */ 
/* 101 */     if (isEnable()) {
/* 102 */       return enable();
/*     */     }
/*     */ 
/* 105 */     if (isDelete()) {
/* 106 */       return delete();
/*     */     }
/* 108 */     return "success";
/*     */   }
/*     */ 
/*     */   public boolean getIsPersonnelFiles() throws Exception {
/* 112 */     if (null == this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode())) {
/* 113 */       return false;
/*     */     }
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   private String delete() {
/*     */     try {
/* 120 */       this.backVisitManager.deleteAllBackVisit(this.backVisits);
/* 121 */       addActionMessage(getText("backVisits.delete.success"));
/* 122 */       return "success";
/*     */     } catch (Exception e) {
/* 124 */       addActionMessage(getText("backVisits.delete.failer"));
/* 125 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enable()
/*     */   {
/*     */     try {
/* 131 */       this.backVisitManager.enableBackVisits(this.backVisits);
/* 132 */       addActionMessage(getText("backVisits.enable.success"));
/* 133 */       return "success";
/*     */     } catch (Exception e) {
/* 135 */       addActionMessage(getText("backVisits.enable.failer"));
/* 136 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try {
/* 142 */       this.backVisitManager.disabledBackVisits(this.backVisits);
/* 143 */       addActionMessage(getText("backVisits.disabled.success"));
/* 144 */       return "success";
/*     */     } catch (Exception e) {
/* 146 */       addActionMessage(getText("backVisits.disabled.failer"));
/* 147 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBackVisitWay()
/*     */   {
/* 152 */     List cust_types = new ArrayList();
/*     */     try {
/* 154 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "007").get(0);
/* 155 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/* 156 */       if (cust_types != null) {
/* 157 */         CodeValue cv = new CodeValue();
/* 158 */         cv.setId(Long.valueOf(-1L));
/* 159 */         cv.setName(getText("select.option.all"));
/* 160 */         cust_types.add(0, cv);
/* 161 */         return cust_types;
/*     */       }
/* 163 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e) {
/* 166 */       e.printStackTrace();
/* 167 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public void setCodeValueManager(CodeValueManager codeValueManager)
/*     */   {
/* 172 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void setBackVisitManager(BackVisitManager backVisitManager) {
/* 176 */     this.backVisitManager = backVisitManager;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.ListBackVisitTabAction
 * JD-Core Version:    0.6.2
 */