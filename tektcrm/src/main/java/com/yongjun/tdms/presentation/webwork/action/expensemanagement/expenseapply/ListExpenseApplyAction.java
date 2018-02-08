/*     */ package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseapply;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseapply.ExpenseApply;
/*     */ import com.yongjun.tdms.service.expensemanagement.expenseapply.ExpenseApplyManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListExpenseApplyAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ExpenseApplyManager expenseApplyManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CodeValueManager codeValueManager;
/*  59 */   private List<ExpenseApply> expenseApplys = null;
/*     */ 
/*     */   public ListExpenseApplyAction(ExpenseApplyManager expenseApplyManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  69 */     this.expenseApplyManager = expenseApplyManager;
/*  70 */     this.codeValueManager = codeValueManager;
/*  71 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  81 */     if ((null == this.expenseApplys) && 
/*  82 */       (hasIds("expenseApplyIds")))
/*  83 */       this.expenseApplys = this.expenseApplyManager.loadAllExpenseApply(getIds("expenseApplyIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  95 */     return "expenseApplyHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 106 */     if (isDisabled()) {
/* 107 */       disabled();
/*     */     }
/* 109 */     if (isEnable()) {
/* 110 */       enabled();
/*     */     }
/* 112 */     if (isDelete()) {
/* 113 */       delete();
/*     */     }
/* 115 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 125 */       this.expenseApplyManager.deleteAllExpenseApply(this.expenseApplys);
/* 126 */       addActionMessage(getText("expenseApply.delete.success"));
/* 127 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 129 */       addActionMessage(getText("expenseApply.delete.error"));
/* 130 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 141 */       this.expenseApplyManager.disabledAllExpenseApply(this.expenseApplys);
/* 142 */       addActionMessage(getText("expenseApply.disabled.success"));
/* 143 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 145 */       addActionMessage(getText("expenseApply.disabled.error"));
/* 146 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 156 */       this.expenseApplyManager.enabledAllExpenseApply(this.expenseApplys);
/* 157 */       addActionMessage(getText("expenseApply.enabled.success"));
/* 158 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 160 */       e.printStackTrace();
/* 161 */       addActionMessage(getText("expenseApply.enabled.error"));
/* 162 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllExpenseType()
/*     */   {
/* 170 */     List codes = null;
/*     */     try {
/* 172 */       codes = new ArrayList();
/* 173 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("069"));
/*     */ 
/* 175 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 177 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 179 */         if ((null != list) && (list.size() > 0)) {
/* 180 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 183 */       CodeValue cv = new CodeValue();
/* 184 */       cv.setId(null);
/* 185 */       cv.setName(getText("所有"));
/* 186 */       codes.add(0, cv);
/* 187 */       return codes;
/*     */     } catch (DaoException e) {
/* 189 */       e.printStackTrace();
/*     */ 
/* 191 */       CodeValue cv = new CodeValue();
/* 192 */       cv.setId(null);
/* 193 */       cv.setName(getText("所有"));
/* 194 */       codes.add(0, cv);
/* 195 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseapply.ListExpenseApplyAction
 * JD-Core Version:    0.6.2
 */