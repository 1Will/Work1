/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.intelligence;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.marketmanager.intelligence.Intelligence;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.marketmanager.intelligence.IntelligenceManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditIntelligenceAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final IntelligenceManager intelligenceManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private Intelligence intelligence;
/*     */ 
/*     */   public EditIntelligenceAction(IntelligenceManager intelligenceManager, CodeValueManager codeValueManager, UserManager userManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager)
/*     */   {
/*  93 */     this.intelligenceManager = intelligenceManager;
/*  94 */     this.codeValueManager = codeValueManager;
/*  95 */     this.userManager = userManager;
/*  96 */     this.personnelFilesManager = personnelFilesManager;
/*  97 */     this.customerInfoManager = customerInfoManager;
/*  98 */     this.contactArchivesManager = contactArchivesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 107 */     if (hasId("intelligence.id"))
/* 108 */       this.intelligence = this.intelligenceManager.loadIntelligence(getId("intelligence.id"));
/*     */     else
/* 110 */       this.intelligence = new Intelligence();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 119 */     boolean isNew = this.intelligence.isNew();
/*     */ 
/* 121 */     if (hasId("persons.id")) {
/* 122 */       PersonnelFiles persons = new PersonnelFiles();
/* 123 */       persons = this.personnelFilesManager.loadPersonnel(getId("persons.id"));
/* 124 */       this.intelligence.setPersons(persons);
/*     */     }
/*     */ 
/* 127 */     if (hasId("customerInfo.id")) {
/* 128 */       CustomerInfo customerInfo = new CustomerInfo();
/* 129 */       customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/* 130 */       this.intelligence.setCustomerInfo(customerInfo);
/*     */     }
/*     */ 
/* 133 */     if (hasId("contactArchives.id")) {
/* 134 */       ContactArchives contactArchives = new ContactArchives();
/* 135 */       contactArchives = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));
/*     */ 
/* 137 */       this.intelligence.setContactArchives(contactArchives);
/*     */     }
/*     */ 
/* 140 */     if (hasId("important.id")) {
/* 141 */       CodeValue important = new CodeValue();
/* 142 */       important = this.codeValueManager.loadCodeValue(getId("important.id"));
/*     */ 
/* 144 */       this.intelligence.setImportant(important);
/*     */     }
/*     */ 
/* 148 */     this.intelligence.setOrganization(this.userManager.getOrganization());
/*     */     try
/*     */     {
/* 151 */       if (isNew) {
/* 152 */         String newCode = autoCompleteCode();
/* 153 */         this.intelligence.setCode(newCode);
/* 154 */         this.intelligenceManager.storeIntelligence(this.intelligence);
/* 155 */         addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.intelligence.getCode() })));
/*     */ 
/* 157 */         return "new";
/*     */       }
/* 159 */       this.intelligenceManager.storeIntelligence(this.intelligence);
/* 160 */       addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.intelligence.getCode() })));
/*     */ 
/* 162 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 165 */       e.printStackTrace();
/* 166 */       if (isNew) {
/* 167 */         addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.intelligence.getCode() })));
/*     */       }
/*     */       else
/*     */       {
/* 171 */         addActionMessage(getText("edit.error", Arrays.asList(new Object[] { this.intelligence.getCode() })));
/*     */       }
/*     */     }
/* 174 */     return "error";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 184 */     String maxCode = this.intelligenceManager.getMaxPFCode("SCQB", this.userManager.getOrganization().getId());
/*     */ 
/* 186 */     if (null != maxCode) {
/* 187 */       int num = Integer.parseInt(maxCode) + 1;
/* 188 */       if (num < 10)
/* 189 */         return "SCQB-000" + num;
/* 190 */       if (num < 100)
/* 191 */         return "SCQB00" + num;
/* 192 */       if (num < 1000) {
/* 193 */         return "SCQB-0" + num;
/*     */       }
/* 195 */       return "SCQB-" + num;
/*     */     }
/*     */ 
/* 199 */     return "SCQB-0001";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllImportant()
/*     */   {
/* 207 */     List codes = null;
/*     */     try {
/* 209 */       codes = new ArrayList();
/* 210 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("041"));
/*     */ 
/* 212 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 214 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 216 */         if ((null != list) && (list.size() > 0)) {
/* 217 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 220 */       return codes;
/*     */     } catch (DaoException e) {
/* 222 */       e.printStackTrace();
/*     */     }
/* 224 */     return codes;
/*     */   }
/*     */ 
/*     */   public Intelligence getIntelligence()
/*     */   {
/* 231 */     return this.intelligence;
/*     */   }
/*     */ 
/*     */   public void setIntelligence(Intelligence intelligence)
/*     */   {
/* 239 */     this.intelligence = intelligence;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.intelligence.EditIntelligenceAction
 * JD-Core Version:    0.6.2
 */