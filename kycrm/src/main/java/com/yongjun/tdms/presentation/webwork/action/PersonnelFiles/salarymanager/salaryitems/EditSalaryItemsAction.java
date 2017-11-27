/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryitems;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems.SalaryItems;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems.SalaryItemsManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditSalaryItemsAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -6543880456612721423L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final SalaryItemsManager salaryItemsManager;
/*     */   private SalaryItems salaryItems;
/*     */ 
/*     */   public EditSalaryItemsAction(SalaryItemsManager salaryItemsManager, CodeValueManager codeValueManager)
/*     */   {
/*  78 */     this.salaryItemsManager = salaryItemsManager;
/*  79 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  88 */     if ((this.salaryItems == null) && (hasId("salaryItems.id"))) {
/*  89 */       this.salaryItems = this.salaryItemsManager.loadSalaryItems(getId("salaryItems.id"));
/*     */     }
/*     */     else
/*  92 */       this.salaryItems = new SalaryItems();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 101 */     boolean isNew = this.salaryItems.isNew();
/*     */ 
/* 103 */     if (hasId("type.id")) {
/* 104 */       this.salaryItems.setType(this.codeValueManager.loadCodeValue(getId("type.id")));
/*     */     }
/*     */ 
/* 107 */     if (hasId("orders.id"))
/* 108 */       this.salaryItems.setOrders(this.codeValueManager.loadCodeValue(getId("orders.id")));
/*     */     try
/*     */     {
/* 111 */       if (isNew) {
/* 112 */         String newCode = autoCompleteCode();
/* 113 */         this.salaryItems.setCode(newCode);
/* 114 */         this.salaryItemsManager.storeSalaryItems(this.salaryItems);
/* 115 */         addActionMessage(getText("salaryItems.add.success", Arrays.asList(new Object[] { this.salaryItems.getCode() })));
/*     */ 
/* 118 */         return "success";
/*     */       }
/* 120 */       this.salaryItemsManager.storeSalaryItems(this.salaryItems);
/* 121 */       addActionMessage(getText("salaryItems.edit.success", Arrays.asList(new Object[] { this.salaryItems.getCode() })));
/*     */ 
/* 124 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 128 */       e.printStackTrace();
/* 129 */       addActionMessage(getText("salaryItems.add.exists", Arrays.asList(new Object[] { this.salaryItems.getCode() })));
/*     */     }
/* 131 */     return "error";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 140 */     String maxCode = this.salaryItemsManager.getMaxPFCode("SALARY");
/* 141 */     if (null != maxCode) {
/* 142 */       int num = Integer.parseInt(maxCode) + 1;
/* 143 */       if (num < 10)
/* 144 */         return "SALARY-00" + num;
/* 145 */       if (num < 100) {
/* 146 */         return "SALARY-0" + num;
/*     */       }
/* 148 */       return "SALARY-" + num;
/*     */     }
/*     */ 
/* 151 */     return "SALARY-001";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 160 */     List codes = null;
/*     */     try {
/* 162 */       codes = new ArrayList();
/* 163 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055556"));
/*     */ 
/* 165 */       if ((null != one) && (one.size() > 0)) {
/* 166 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 168 */         if ((null != list) && (list.size() > 0)) {
/* 169 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 172 */       return codes;
/*     */     } catch (DaoException e) {
/* 174 */       e.printStackTrace();
/*     */     }
/* 176 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllOrders()
/*     */   {
/* 184 */     List codes = null;
/*     */     try {
/* 186 */       codes = new ArrayList();
/* 187 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055557"));
/*     */ 
/* 189 */       if ((null != one) && (one.size() > 0)) {
/* 190 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 192 */         if ((null != list) && (list.size() > 0)) {
/* 193 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 196 */       return codes;
/*     */     } catch (DaoException e) {
/* 198 */       e.printStackTrace();
/*     */     }
/* 200 */     return codes;
/*     */   }
/*     */ 
/*     */   public SalaryItems getSalaryItems()
/*     */   {
/* 209 */     return this.salaryItems;
/*     */   }
/*     */ 
/*     */   public void setSalaryItems(SalaryItems salaryItems)
/*     */   {
/* 218 */     this.salaryItems = salaryItems;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryitems.EditSalaryItemsAction
 * JD-Core Version:    0.6.2
 */