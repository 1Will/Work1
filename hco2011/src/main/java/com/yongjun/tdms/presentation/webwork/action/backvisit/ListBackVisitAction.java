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
/*     */ public class ListBackVisitAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private CodeValueManager codeValueManager;
/*     */   private BackVisitManager backVisitManager;
/*     */   private List<BackVisit> backVisits;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private final UserManager userManager;
/*     */ 
/*     */   public ListBackVisitAction(UserManager userManager)
/*     */   {
/*  35 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void setBackVisitManager(BackVisitManager backVisitManager)
/*     */   {
/*  42 */     this.backVisitManager = backVisitManager;
/*     */   }
/*     */ 
/*     */   public List<BackVisit> getBackVisits() {
/*  46 */     return this.backVisits;
/*     */   }
/*     */ 
/*     */   public void setBackVisits(List<BackVisit> backVisits) {
/*  50 */     this.backVisits = backVisits;
/*     */   }
/*     */ 
/*     */   public BackVisitManager getBackVisitManager() {
/*  54 */     return this.backVisitManager;
/*     */   }
/*     */ 
/*     */   public CodeValueManager getCodeValueManager() {
/*  58 */     return this.codeValueManager;
/*     */   }
/*     */ 
/*     */   public PersonnelFilesManager getPersonnelFilesManager() {
/*  62 */     return this.personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFilesManager(PersonnelFilesManager personnelFilesManager) {
/*  66 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public UserManager getUserManager() {
/*  70 */     return this.userManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  76 */     return "backvisit";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  81 */     Map map = super.getRequestParameterMap();
/*  82 */     if (hasId("continueBackVisit")) {
/*  83 */       if (getId("continueBackVisit").equals(Long.valueOf("2")))
/*  84 */         map.put("cbv", null);
/*     */       else {
/*  86 */         map.put("cbv", Long.valueOf(getId("continueBackVisit").longValue()));
/*     */       }
/*     */     }
/*  89 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  96 */     if ((this.backVisits == null) && (hasIds("backVisitIds")))
/*  97 */       this.backVisits = this.backVisitManager.loadAllBackVisit(getIds("backVisitIds"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 103 */     if (isDisabled()) {
/* 104 */       return disabled();
/*     */     }
/*     */ 
/* 107 */     if (isEnable()) {
/* 108 */       return enable();
/*     */     }
/*     */ 
/* 111 */     if (isDelete()) {
/* 112 */       return delete();
/*     */     }
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */   public boolean getIsPersonnelFiles() throws Exception {
/* 118 */     if (null == this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode())) {
/* 119 */       return false;
/*     */     }
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   private String delete() {
/*     */     try {
/* 126 */       this.backVisitManager.deleteAllBackVisit(this.backVisits);
/* 127 */       addActionMessage(getText("backVisits.delete.success"));
/* 128 */       return "success";
/*     */     } catch (Exception e) {
/* 130 */       addActionMessage(getText("backVisits.delete.failer"));
/* 131 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enable()
/*     */   {
/*     */     try {
/* 137 */       this.backVisitManager.enableBackVisits(this.backVisits);
/* 138 */       addActionMessage(getText("backVisits.enable.success"));
/* 139 */       return "success";
/*     */     } catch (Exception e) {
/* 141 */       addActionMessage(getText("backVisits.enable.failer"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try {
/* 148 */       this.backVisitManager.disabledBackVisits(this.backVisits);
/* 149 */       addActionMessage(getText("backVisits.disabled.success"));
/* 150 */       return "success";
/*     */     } catch (Exception e) {
/* 152 */       addActionMessage(getText("backVisits.disabled.failer"));
/* 153 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBackVisitType()
/*     */   {
/* 161 */     List cust_types = new ArrayList();
/*     */     try {
/* 163 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "888").get(0);
/* 164 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/* 165 */       if (cust_types != null) {
/* 166 */         CodeValue cv = new CodeValue();
/* 167 */         cv.setId(Long.valueOf(-1L));
/* 168 */         cv.setName(getText("select.option.all"));
/* 169 */         cust_types.add(0, cv);
/* 170 */         return cust_types;
/*     */       }
/* 172 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e) {
/* 175 */       e.printStackTrace();
/* 176 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBackVisitWay() {
/* 180 */     List cust_types = new ArrayList();
/*     */     try {
/* 182 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "007").get(0);
/* 183 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/* 184 */       if (cust_types != null) {
/* 185 */         CodeValue cv = new CodeValue();
/* 186 */         cv.setId(Long.valueOf(-1L));
/* 187 */         cv.setName(getText("select.option.all"));
/* 188 */         cust_types.add(0, cv);
/* 189 */         return cust_types;
/*     */       }
/* 191 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e) {
/* 194 */       e.printStackTrace();
/* 195 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllSteps()
/*     */   {
/*     */     try
/*     */     {
/* 205 */       List codes = new ArrayList();
/* 206 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("022"));
/* 207 */       if ((null != one) && (one.size() > 0)) {
/* 208 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(1)).getId());
/* 209 */         if ((null != list) && (list.size() > 0)) {
/* 210 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 213 */       CodeValue cv = new CodeValue();
/* 214 */       cv.setId(Long.valueOf(-1L));
/* 215 */       cv.setName(getText("select.option.all"));
/* 216 */       codes.add(0, cv);
/* 217 */       return codes;
/*     */     } catch (DaoException e) {
/* 219 */       e.printStackTrace();
/* 220 */       List codes = new ArrayList();
/* 221 */       CodeValue cv = new CodeValue();
/* 222 */       cv.setId(Long.valueOf(-1L));
/* 223 */       cv.setName(getText("select.option.all"));
/* 224 */       codes.add(0, cv);
/* 225 */       return codes;
/*     */     }
/*     */   }
/*     */ 
/* 229 */   public void setCodeValueManager(CodeValueManager codeValueManager) { this.codeValueManager = codeValueManager; }
/*     */ 
/*     */   public void BackVisitManager(BackVisitManager backVisitManager)
/*     */   {
/* 233 */     this.backVisitManager = backVisitManager;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.ListBackVisitAction
 * JD-Core Version:    0.6.2
 */