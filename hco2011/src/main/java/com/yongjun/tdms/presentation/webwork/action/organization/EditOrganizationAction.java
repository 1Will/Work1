/*     */ package com.yongjun.tdms.presentation.webwork.action.organization;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.OrganizationManager;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditOrganizationAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final OrganizationManager organizationManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private Organization org;
/*     */ 
/*     */   public EditOrganizationAction(OrganizationManager organizationManager, CodeValueManager codeValueManager)
/*     */   {
/*  56 */     this.organizationManager = organizationManager;
/*  57 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  65 */     if (null == this.org)
/*  66 */       if (hasId("org.id"))
/*  67 */         this.org = this.organizationManager.loadOrganization(getId("org.id"));
/*     */       else
/*  69 */         this.org = new Organization();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  80 */     boolean isNew = this.org.isNew();
/*     */ 
/*  83 */     if (hasId("industry.id")) {
/*  84 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("industry.id"));
/*  85 */       this.org.setIndustry(cv);
/*     */     }
/*     */ 
/*  88 */     if (hasId("nature.id")) {
/*  89 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("nature.id"));
/*  90 */       this.org.setNature(cv);
/*     */     }
/*     */     try
/*     */     {
/*  94 */       if (this.org.isNew())
/*     */       {
/*  96 */         if (null == this.organizationManager.loadByKey("code", this.org.getCode()))
/*     */         {
/*  98 */           this.organizationManager.storeOrganization(this.org);
/*     */         }
/*     */         else {
/* 101 */           addActionMessage(getText("organization.add.error", Arrays.asList(new Object[] { this.org.getCode() })));
/*     */ 
/* 103 */           return "error";
/*     */         }
/*     */       }
/* 106 */       else this.organizationManager.storeOrganization(this.org); 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 109 */       e.printStackTrace();
/* 110 */       if (isNew) {
/* 111 */         addActionMessage(getText("organization.add.error", Arrays.asList(new Object[] { this.org.getCode() })));
/*     */       }
/*     */       else {
/* 114 */         addActionMessage(getText("organization.edit.error", Arrays.asList(new Object[] { this.org.getCode() })));
/*     */       }
/*     */ 
/* 118 */       return "error";
/*     */     }
/*     */ 
/* 121 */     if (isNew) {
/* 122 */       addActionMessage(getText("organization.add.success", Arrays.asList(new Object[] { this.org.getCode() })));
/*     */ 
/* 124 */       return "new";
/*     */     }
/* 126 */     addActionMessage(getText("organization.edit.success", Arrays.asList(new Object[] { this.org.getCode() })));
/*     */ 
/* 128 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllIndustry()
/*     */   {
/*     */     try
/*     */     {
/* 138 */       List arr = this.codeValueManager.loadByKey("code", "002");
/* 139 */       CodeValue industry = null;
/* 140 */       if (null != arr) {
/* 141 */         industry = (CodeValue)arr.get(0);
/* 142 */         List industrys = this.codeValueManager.loadByKey("parentCV", industry.getId());
/* 143 */         if (null != industrys) {
/* 144 */           return industrys;
/*     */         }
/*     */       }
/* 147 */       return new ArrayList();
/*     */     } catch (DaoException e) {
/* 149 */       e.printStackTrace();
/* 150 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllNature()
/*     */   {
/*     */     try
/*     */     {
/* 160 */       List arr = this.codeValueManager.loadByKey("code", "003");
/* 161 */       CodeValue industry = null;
/* 162 */       if (null != arr) {
/* 163 */         industry = (CodeValue)arr.get(0);
/* 164 */         List industrys = this.codeValueManager.loadByKey("parentCV", industry.getId());
/* 165 */         if (null != industrys) {
/* 166 */           return industrys;
/*     */         }
/*     */       }
/* 169 */       return new ArrayList();
/*     */     } catch (DaoException e) {
/* 171 */       e.printStackTrace();
/* 172 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public Organization getOrg()
/*     */   {
/* 180 */     return this.org;
/*     */   }
/*     */ 
/*     */   public void setOrg(Organization org)
/*     */   {
/* 187 */     this.org = org;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.organization.EditOrganizationAction
 * JD-Core Version:    0.6.2
 */