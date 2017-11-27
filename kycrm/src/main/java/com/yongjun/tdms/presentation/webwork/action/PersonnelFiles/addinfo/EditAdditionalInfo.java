/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.addinfo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.AdditionalInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.personnelFiles.addinfo.AdditionalInfoManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditAdditionalInfo extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -6671990476165406622L;
/*     */   private final AdditionalInfoManager additionalManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private CodeValueManager codeValueManager;
/*     */   private AdditionalInfo additionalInfo;
/*     */   private PersonnelFiles personnelFiles;
/*     */   private UserManager userManager;
/*     */ 
/*     */   public EditAdditionalInfo(AdditionalInfoManager additionalManager, PersonnelFilesManager personnelFilesManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  84 */     this.additionalManager = additionalManager;
/*  85 */     this.personnelFilesManager = personnelFilesManager;
/*  86 */     this.codeValueManager = codeValueManager;
/*  87 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  92 */     if (hasId("additionalInfo.id")) {
/*  93 */       this.additionalInfo = this.additionalManager.loadAdditional(getId("additionalInfo.id"));
/*     */     }
/*     */     else
/*     */     {
/*  97 */       this.additionalInfo = new AdditionalInfo();
/*     */     }
/*  99 */     if ((null != this.request.getParameter("personnelFiles.id")) && ("" != this.request.getParameter("personnelFiles.id")))
/*     */     {
/* 101 */       this.personnelFiles = this.personnelFilesManager.loadPersonnel(Long.valueOf(Long.parseLong(this.request.getParameter("personnelFiles.id"))));
/*     */ 
/* 103 */       this.additionalInfo.setPf(this.personnelFiles);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 110 */     if (this.additionalManager.loadByKey("pf", this.personnelFiles) != null) {
/* 111 */       this.additionalInfo = ((AdditionalInfo)this.additionalManager.loadByKey("pf", this.personnelFiles).get(0));
/*     */     }
/*     */ 
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllReligion()
/*     */   {
/* 125 */     List list = new ArrayList();
/*     */     try {
/* 127 */       String[] keyNames = { "code" };
/* 128 */       Object[] keyValues = { "019" };
/* 129 */       List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */ 
/* 131 */       if ((null != cvs) && 
/* 132 */         (cvs.size() > 0)) {
/* 133 */         CodeValue cv = (CodeValue)cvs.get(0);
/* 134 */         keyNames = new String[] { "parentCV" };
/* 135 */         keyValues = new Object[] { cv.getId() };
/* 136 */         list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 144 */     return list;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllHealth()
/*     */   {
/* 155 */     List list = new ArrayList();
/*     */     try {
/* 157 */       String[] keyNames = { "code" };
/* 158 */       Object[] keyValues = { "018" };
/* 159 */       List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */ 
/* 161 */       if ((null != cvs) && 
/* 162 */         (cvs.size() > 0)) {
/* 163 */         CodeValue cv = (CodeValue)cvs.get(0);
/* 164 */         keyNames = new String[] { "parentCV" };
/* 165 */         keyValues = new Object[] { cv.getId() };
/* 166 */         list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 174 */     return list;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 179 */     if (hasId("additionalInfo.religion")) {
/* 180 */       this.additionalInfo.setReligion(this.codeValueManager.loadCodeValue(getId("additionalInfo.religion")));
/*     */     }
/*     */ 
/* 184 */     if (hasId("additionalInfo.health")) {
/* 185 */       this.additionalInfo.setHealth(this.codeValueManager.loadCodeValue(getId("additionalInfo.health")));
/*     */     }
/*     */ 
/* 188 */     this.additionalInfo.setOrganization(this.userManager.getUser().getOrganization());
/*     */     try
/*     */     {
/* 191 */       if (this.additionalManager.loadByKey("pf", this.personnelFiles) == null) {
/* 192 */         this.additionalManager.storeAdditional(this.additionalInfo);
/* 193 */         this.additionalInfo = ((AdditionalInfo)this.additionalManager.loadByKey("pf", this.personnelFiles).get(0));
/*     */ 
/* 195 */         addActionMessage(getText("additionalInfo.add.success"));
/*     */ 
/* 197 */         return "new";
/*     */       }
/*     */ 
/* 200 */       this.additionalManager.storeAdditional(this.additionalInfo);
/* 201 */       addActionMessage(getText("additionalInfo.edit.success"));
/*     */ 
/* 203 */       return "success";
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 207 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 210 */     return "error";
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelFiles() {
/* 214 */     return this.personnelFiles;
/*     */   }
/*     */ 
/*     */   public void setPersonnelFiles(PersonnelFiles personnelFiles) {
/* 218 */     this.personnelFiles = personnelFiles;
/*     */   }
/*     */ 
/*     */   public AdditionalInfo getAdditionalInfo() {
/* 222 */     return this.additionalInfo;
/*     */   }
/*     */ 
/*     */   public void setAdditionalInfo(AdditionalInfo additionalInfo) {
/* 226 */     this.additionalInfo = additionalInfo;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.addinfo.EditAdditionalInfo
 * JD-Core Version:    0.6.2
 */