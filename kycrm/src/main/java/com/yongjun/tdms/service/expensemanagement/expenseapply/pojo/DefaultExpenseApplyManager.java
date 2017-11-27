/*     */ package com.yongjun.tdms.service.expensemanagement.expenseapply.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.expensemanagement.expenseapply.ExpenseApplyDao;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseapply.ExpenseApply;
/*     */ import com.yongjun.tdms.service.expensemanagement.expenseapply.ExpenseApplyManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultExpenseApplyManager extends BaseManager
/*     */   implements ExpenseApplyManager
/*     */ {
/*     */   private final ExpenseApplyDao dao;
/*     */ 
/*     */   public DefaultExpenseApplyManager(ExpenseApplyDao dao)
/*     */   {
/*  26 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeExpenseApply(ExpenseApply t)
/*     */   {
/*  33 */     this.dao.storeExpenseApply(t);
/*     */   }
/*     */ 
/*     */   public ExpenseApply loadExpenseApply(Long id)
/*     */   {
/*  42 */     return this.dao.loadExpenseApply(id);
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadExpenseApply()
/*     */   {
/*  50 */     return this.dao.loadExpenseApply();
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadAllExpenseApply(Long[] tIds)
/*     */   {
/*  59 */     return this.dao.loadAllExpenseApply(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteExpenseApply(ExpenseApply t)
/*     */   {
/*  67 */     this.dao.deleteExpenseApply(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllExpenseApply(List<ExpenseApply> ts)
/*     */   {
/*  75 */     this.dao.deleteAllExpenseApply(ts);
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  86 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  96 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 104 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllExpenseApply(List<ExpenseApply> ts)
/*     */   {
/* 111 */     for (ExpenseApply expenseApply : ts) {
/* 112 */       expenseApply.setDisabled(true);
/* 113 */       this.dao.storeExpenseApply(expenseApply);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllExpenseApply(List<ExpenseApply> ts)
/*     */   {
/* 122 */     for (ExpenseApply expenseApply : ts) {
/* 123 */       expenseApply.setDisabled(false);
/* 124 */       this.dao.storeExpenseApply(expenseApply);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.expensemanagement.expenseapply.pojo.DefaultExpenseApplyManager
 * JD-Core Version:    0.6.2
 */