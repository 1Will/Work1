/*     */ package com.yongjun.tdms.service.expensemanagement.expenseuse.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.expensemanagement.expenseuse.ExpenseUseDao;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseuse.ExpenseUse;
/*     */ import com.yongjun.tdms.service.expensemanagement.expenseuse.ExpenseUseManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultExpenseUseManager extends BaseManager
/*     */   implements ExpenseUseManager
/*     */ {
/*     */   private final ExpenseUseDao dao;
/*     */ 
/*     */   public DefaultExpenseUseManager(ExpenseUseDao dao)
/*     */   {
/*  27 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeExpenseUse(ExpenseUse t)
/*     */   {
/*  34 */     this.dao.storeExpenseUse(t);
/*     */   }
/*     */ 
/*     */   public ExpenseUse loadExpenseUse(Long id)
/*     */   {
/*  43 */     return this.dao.loadExpenseUse(id);
/*     */   }
/*     */ 
/*     */   public List<ExpenseUse> loadExpenseUse()
/*     */   {
/*  51 */     return this.dao.loadExpenseUse();
/*     */   }
/*     */ 
/*     */   public List<ExpenseUse> loadAllExpenseUse(Long[] tIds)
/*     */   {
/*  60 */     return this.dao.loadAllExpenseUse(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteExpenseUse(ExpenseUse t)
/*     */   {
/*  68 */     this.dao.deleteExpenseUse(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllExpenseUse(List<ExpenseUse> ts)
/*     */   {
/*  76 */     this.dao.deleteAllExpenseUse(ts);
/*     */   }
/*     */ 
/*     */   public List<ExpenseUse> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<ExpenseUse> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  97 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 105 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllExpenseUse(List<ExpenseUse> ts)
/*     */   {
/* 112 */     for (ExpenseUse e : ts) {
/* 113 */       e.setDisabled(true);
/* 114 */       this.dao.storeExpenseUse(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllExpenseUse(List<ExpenseUse> ts)
/*     */   {
/* 123 */     for (ExpenseUse e : ts) {
/* 124 */       e.setDisabled(false);
/* 125 */       this.dao.storeExpenseUse(e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.expensemanagement.expenseuse.pojo.DefaultExpenseUseManager
 * JD-Core Version:    0.6.2
 */