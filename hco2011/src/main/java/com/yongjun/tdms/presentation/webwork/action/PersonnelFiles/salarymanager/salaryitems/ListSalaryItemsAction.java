/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryitems;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems.SalaryItems;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems.SalaryItemsManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListSalaryItemsAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final SalaryItemsManager salaryItemsManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<SalaryItems> salaryItemses;
/*     */ 
/*     */   public ListSalaryItemsAction(SalaryItemsManager salaryItemsManager, CodeValueManager codeValueManager)
/*     */   {
/*  72 */     this.salaryItemsManager = salaryItemsManager;
/*  73 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  83 */     if (isDisabled()) {
/*  84 */       return disable();
/*     */     }
/*  86 */     if (isEnable()) {
/*  87 */       return enable();
/*     */     }
/*  89 */     if (isDelete()) {
/*  90 */       return delete();
/*     */     }
/*  92 */     return "success";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 101 */     if (hasIds("salaryItemsIds"))
/* 102 */       this.salaryItemses = this.salaryItemsManager.loadAllSalaryItems(getIds("salaryItemsIds"));
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/* 112 */     this.salaryItemsManager.disabledSalaryItems(this.salaryItemses);
/* 113 */     addActionMessage(getText("salaryItemse.disable.success"));
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 123 */       this.salaryItemsManager.deleteAllSalaryItems(this.salaryItemses);
/* 124 */       addActionMessage(getText("salaryItemse.delete.success"));
/*     */     } catch (Exception e) {
/* 126 */       addActionMessage(getText("salaryItemse.delete.error"));
/*     */     }
/* 128 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/* 136 */     this.salaryItemsManager.enabledSalaryItems(this.salaryItemses);
/* 137 */     addActionMessage(getText("salaryItemse.enable.success"));
/* 138 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 147 */     return "salaryItemses";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 157 */     List codes = null;
/*     */     try {
/* 159 */       codes = new ArrayList();
/* 160 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055556"));
/*     */ 
/* 162 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 164 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 166 */         if ((null != list) && (list.size() > 0)) {
/* 167 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 170 */       CodeValue cv = new CodeValue();
/* 171 */       cv.setId(Long.valueOf(-1L));
/* 172 */       cv.setName(getText("select.option.all"));
/* 173 */       codes.add(0, cv);
/* 174 */       return codes;
/*     */     } catch (DaoException e) {
/* 176 */       e.printStackTrace();
/*     */     }
/* 178 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllOrders()
/*     */   {
/* 188 */     List codes = null;
/*     */     try {
/* 190 */       codes = new ArrayList();
/* 191 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055557"));
/*     */ 
/* 193 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 195 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 197 */         if ((null != list) && (list.size() > 0)) {
/* 198 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 201 */       CodeValue cv = new CodeValue();
/* 202 */       cv.setId(Long.valueOf(-1L));
/* 203 */       cv.setName(getText("select.option.all"));
/* 204 */       codes.add(0, cv);
/* 205 */       return codes;
/*     */     } catch (DaoException e) {
/* 207 */       e.printStackTrace();
/*     */     }
/* 209 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<SalaryItems> getSalaryItemses()
/*     */   {
/* 216 */     return this.salaryItemses;
/*     */   }
/*     */ 
/*     */   public void setSalaryItemses(List<SalaryItems> salaryItemses)
/*     */   {
/* 225 */     this.salaryItemses = salaryItemses;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryitems.ListSalaryItemsAction
 * JD-Core Version:    0.6.2
 */