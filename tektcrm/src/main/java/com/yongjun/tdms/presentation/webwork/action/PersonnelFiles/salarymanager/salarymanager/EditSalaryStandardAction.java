/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salarymanager;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarystandard.SalaryStandard;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager.SalaryStandardManager;
/*     */ 
/*     */ public class EditSalaryStandardAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final SalaryStandardManager salaryStandardManager;
/*     */   private final CodeValueManager codeValueManager;
/*  69 */   private SalaryStandard salaryStandard = null;
/*     */ 
/*     */   public EditSalaryStandardAction(SalaryStandardManager salaryStandardManager, CodeValueManager codeValueManager)
/*     */   {
/*  78 */     this.salaryStandardManager = salaryStandardManager;
/*  79 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  87 */     if (null == this.salaryStandard)
/*  88 */       if (hasId("salaryStandard.id")) {
/*  89 */         this.salaryStandard = this.salaryStandardManager.loadSalaryStandard(getId("salaryStandard.id"));
/*     */       }
/*     */       else
/*  92 */         this.salaryStandard = new SalaryStandard();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 102 */     boolean isNew = this.salaryStandard.isNew();
/* 103 */     if (isNew) {
/* 104 */       String code = autoCompleteCode();
/* 105 */       this.salaryStandard.setCode(code);
/*     */     }
/*     */     try {
/* 108 */       this.salaryStandardManager.storeSalaryStandard(this.salaryStandard);
/* 109 */       if (isNew) {
/* 110 */         addActionMessage(getText("salaryStandard.add.success"));
/* 111 */         return "new";
/*     */       }
/* 113 */       addActionMessage(getText("salaryStandard.edit.success"));
/* 114 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 118 */       ex.printStackTrace();
/* 119 */       if (isNew) {
/* 120 */         addActionMessage(getText("salaryStandard.add.error"));
/* 121 */         return "new";
/*     */       }
/* 123 */       addActionMessage(getText("salaryStandard.edit.error"));
/* 124 */     }return "success";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 161 */     String prefix = "XCBZ";
/* 162 */     String maxCode = this.salaryStandardManager.getMaxPFCode(prefix);
/* 163 */     if (null != maxCode) {
/* 164 */       int num = Integer.parseInt(maxCode) + 1;
/* 165 */       if (num < 10)
/* 166 */         return prefix + "-00000" + num;
/* 167 */       if (num < 100)
/* 168 */         return prefix + "-0000" + num;
/* 169 */       if (num < 1000)
/* 170 */         return prefix + "-000" + num;
/* 171 */       if (num < 10000)
/* 172 */         return prefix + "-00" + num;
/* 173 */       if (num < 100000) {
/* 174 */         return prefix + "-0" + num;
/*     */       }
/* 176 */       return prefix + "-" + num;
/*     */     }
/*     */ 
/* 179 */     return prefix + "-000001";
/*     */   }
/*     */ 
/*     */   public SalaryStandard getSalaryStandard()
/*     */   {
/* 190 */     return this.salaryStandard;
/*     */   }
/*     */ 
/*     */   public void setSalaryStandard(SalaryStandard salaryStandard)
/*     */   {
/* 197 */     this.salaryStandard = salaryStandard;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salarymanager.EditSalaryStandardAction
 * JD-Core Version:    0.6.2
 */