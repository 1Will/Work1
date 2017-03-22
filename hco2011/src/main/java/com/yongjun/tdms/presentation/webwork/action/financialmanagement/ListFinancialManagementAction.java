/*     */ package com.yongjun.tdms.presentation.webwork.action.financialmanagement;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
/*     */ import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListFinancialManagementAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -1498451112049602252L;
/*     */   private final FinancialManagementManager financialManagementManager;
/*     */   private List<FinancialManagement> financialManagements;
/*     */ 
/*     */   public ListFinancialManagementAction(FinancialManagementManager financialManagementManager)
/*     */   {
/*  32 */     this.financialManagementManager = financialManagementManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  40 */     if (hasIds("financialManagementIds"))
/*  41 */       this.financialManagements = this.financialManagementManager.loadAllFinancialManagement(getIds("financialManagementIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  51 */     return "financialManagements";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  61 */     if (isDisabled()) {
/*  62 */       disabled();
/*     */     }
/*  64 */     if (isEnable()) {
/*  65 */       enabled();
/*     */     }
/*  67 */     if (isDelete()) {
/*  68 */       delete();
/*     */     }
/*  70 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  80 */       this.financialManagementManager.deleteAllFinancialManagement(this.financialManagements);
/*  81 */       addActionMessage(getText("financialManagement.delete.success"));
/*  82 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  84 */       addActionMessage(getText("financialManagement.delete.error"));
/*  85 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/*  95 */       this.financialManagementManager.disabledAllFinancialManagements(this.financialManagements);
/*  96 */       addActionMessage(getText("financialManagement.disabled.success"));
/*  97 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  99 */       addActionMessage(getText("financialManagement.disabled.error"));
/* 100 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 110 */       this.financialManagementManager.enabledAllFinancialManagements(this.financialManagements);
/*     */ 
/* 112 */       addActionMessage(getText("returnPlan.enabled.success"));
/* 113 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 115 */       e.printStackTrace();
/* 116 */       addActionMessage(getText("financialManagement.enabled.error"));
/* 117 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> getFinancialManagements()
/*     */   {
/* 126 */     return this.financialManagements;
/*     */   }
/*     */ 
/*     */   public void setFinancialManagements(List<FinancialManagement> financialManagements)
/*     */   {
/* 136 */     this.financialManagements = financialManagements;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.financialmanagement.ListFinancialManagementAction
 * JD-Core Version:    0.6.2
 */