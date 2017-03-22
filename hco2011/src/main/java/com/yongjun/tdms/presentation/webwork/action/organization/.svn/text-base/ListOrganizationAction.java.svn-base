/*     */ package com.yongjun.tdms.presentation.webwork.action.organization;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.OrganizationManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListOrganizationAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final OrganizationManager organizationManager;
/*     */   private List<Organization> orgList;
/*     */ 
/*     */   public ListOrganizationAction(OrganizationManager organizationManager)
/*     */   {
/*  47 */     this.organizationManager = organizationManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  56 */     if ((null == this.orgList) && (hasIds("orgIds")))
/*  57 */       this.orgList = this.organizationManager.loadAllOrganization(getIds("orgIds"));
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  67 */       this.organizationManager.deleteAllOrganization(this.orgList);
/*  68 */       addActionMessage(getText("Organization.delete.success"));
/*  69 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  71 */       addActionMessage(getText("Organization.delete.failure"));
/*  72 */     }return "error";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  83 */     if (isDisabled()) {
/*  84 */       disabled();
/*     */     }
/*  86 */     if (isEnable()) {
/*  87 */       enabled();
/*     */     }
/*  89 */     if (isDelete()) {
/*  90 */       delete();
/*     */     }
/*  92 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 112 */       this.organizationManager.disabledOrganization(this.orgList);
/* 113 */       addActionMessage(getText("Organization.disabled.success"));
/* 114 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 116 */       e.printStackTrace();
/* 117 */       addActionMessage(getText("Organization.disabled.failure"));
/* 118 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 139 */       this.organizationManager.enabledOrganization(this.orgList);
/* 140 */       addActionMessage(getText("Organization.enabled.success"));
/* 141 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 143 */       e.printStackTrace();
/* 144 */       addActionMessage(getText("Organization.enabled.failure"));
/* 145 */     }return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 154 */     return "OrganizationHQL";
/*     */   }
/*     */ 
/*     */   public List<Organization> getOrgList()
/*     */   {
/* 162 */     return this.orgList;
/*     */   }
/*     */ 
/*     */   public void setOrgList(List<Organization> orgList)
/*     */   {
/* 169 */     this.orgList = orgList;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.organization.ListOrganizationAction
 * JD-Core Version:    0.6.2
 */