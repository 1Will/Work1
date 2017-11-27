/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salarymanager;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarystandard.SalaryStandard;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager.SalaryStandardManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListSalaryStandardAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final SalaryStandardManager salaryStandardManager;
/*     */   private final CodeValueManager codeValueManager;
/*  51 */   private List<SalaryStandard> salaryStandards = null;
/*     */ 
/*     */   public ListSalaryStandardAction(SalaryStandardManager salaryStandardManager, CodeValueManager codeValueManager)
/*     */   {
/*  60 */     this.salaryStandardManager = salaryStandardManager;
/*  61 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  71 */     if ((null == this.salaryStandards) && 
/*  72 */       (hasIds("salaryStandardIds")))
/*  73 */       this.salaryStandards = this.salaryStandardManager.loadAllSalaryStandard(getIds("salaryStandardIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  85 */     return "salaryStandardHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  96 */     if (isDisabled()) {
/*  97 */       disabled();
/*     */     }
/*  99 */     if (isEnable()) {
/* 100 */       enabled();
/*     */     }
/* 102 */     if (isDelete()) {
/* 103 */       delete();
/*     */     }
/* 105 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 115 */       this.salaryStandardManager.deleteAllSalaryStandard(this.salaryStandards);
/* 116 */       addActionMessage(getText("salaryStandard.delete.success"));
/* 117 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 119 */       addActionMessage(getText("salaryStandard.delete.error"));
/* 120 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 131 */       this.salaryStandardManager.disabledAllSalaryStandard(this.salaryStandards);
/* 132 */       addActionMessage(getText("salaryStandard.disabled.success"));
/* 133 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 135 */       addActionMessage(getText("salaryStandard.disabled.error"));
/* 136 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 146 */       this.salaryStandardManager.enabledAllSalaryStandard(this.salaryStandards);
/* 147 */       addActionMessage(getText("salaryStandard.enabled.success"));
/* 148 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 150 */       e.printStackTrace();
/* 151 */       addActionMessage(getText("salaryStandard.enabled.error"));
/* 152 */     }return "error";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salarymanager.ListSalaryStandardAction
 * JD-Core Version:    0.6.2
 */