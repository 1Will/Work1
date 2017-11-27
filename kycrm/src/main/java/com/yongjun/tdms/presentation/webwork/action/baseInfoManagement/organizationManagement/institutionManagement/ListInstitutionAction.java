/*     */ package com.yongjun.tdms.presentation.webwork.action.baseInfoManagement.organizationManagement.institutionManagement;
/*     */ 
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListInstitutionAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5750148747977992025L;
/*     */   private final InstitutionManager institutionManager;
/*     */   private List<Institution> institutions;
/*     */ 
/*     */   public ListInstitutionAction(InstitutionManager institutionManager)
/*     */   {
/*  50 */     this.institutionManager = institutionManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  60 */     if ((this.institutions == null) && (hasIds("institutionIds")))
/*  61 */       this.institutions = this.institutionManager.loadAllInstitution(getIds("institutionIds"));
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  73 */       this.institutionManager.deleteAllInstitution(this.institutions);
/*  74 */       addActionMessage(getText("Institution.delete.success"));
/*  75 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  77 */       addActionMessage(getText("Institution.delete.failer"));
/*  78 */     }return "error";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  89 */     if (isDisabled()) {
/*  90 */       disabled();
/*     */     }
/*  92 */     if (isEnable()) {
/*  93 */       enabled();
/*     */     }
/*  95 */     if (isDelete()) {
/*  96 */       delete();
/*     */     }
/*  98 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 107 */       this.institutionManager.disabledAllInstitution(this.institutions);
/* 108 */       addActionMessage(getText("Institution.disabled.success"));
/* 109 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 111 */       addActionMessage(getText("Institution.disabled.failer"));
/* 112 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 122 */       this.institutionManager.enabledAllInstitution(this.institutions);
/* 123 */       addActionMessage(getText("Institution.enabled.success"));
/* 124 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 126 */       addActionMessage(getText("Institution.enabled.failer"));
/* 127 */     }return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 137 */     return "InstitutionHQL";
/*     */   }
/*     */ 
/*     */   public List<Institution> getAllParentInsts()
/*     */   {
/* 146 */     List list = this.institutionManager.loadAllInstitution(getText("select.option.all"));
/* 147 */     Institution inst = new Institution();
/* 148 */     inst.setId(Long.valueOf(-2L));
/* 149 */     inst.setName(getText("institution.none"));
/* 150 */     list.add(1, inst);
/* 151 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Institution> getInstitutions()
/*     */   {
/* 159 */     return this.institutions;
/*     */   }
/*     */ 
/*     */   public void setInstitutions(List<Institution> institutions)
/*     */   {
/* 166 */     this.institutions = institutions;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.baseInfoManagement.organizationManagement.institutionManagement.ListInstitutionAction
 * JD-Core Version:    0.6.2
 */