/*    */ package com.yongjun.tdms.dao.expensemanagement.expenseuse.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.expensemanagement.expenseuse.ExpenseUseDao;
/*    */ import com.yongjun.tdms.model.expensemanagement.expenseuse.ExpenseUse;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateExpenseUse extends BaseHibernateDao
/*    */   implements ExpenseUseDao
/*    */ {
/*    */   public void storeExpenseUse(ExpenseUse t)
/*    */   {
/* 21 */     store(t);
/*    */   }
/*    */ 
/*    */   public ExpenseUse loadExpenseUse(Long id)
/*    */   {
/* 30 */     return (ExpenseUse)load(ExpenseUse.class, id);
/*    */   }
/*    */ 
/*    */   public List<ExpenseUse> loadExpenseUse()
/*    */   {
/* 38 */     return loadAll(ExpenseUse.class);
/*    */   }
/*    */ 
/*    */   public List<ExpenseUse> loadAllExpenseUse(Long[] tIds)
/*    */   {
/* 47 */     return loadAll(ExpenseUse.class, tIds);
/*    */   }
/*    */ 
/*    */   public void deleteExpenseUse(ExpenseUse t)
/*    */   {
/* 55 */     delete(t);
/*    */   }
/*    */ 
/*    */   public void deleteAllExpenseUse(List<ExpenseUse> ts)
/*    */   {
/* 63 */     deleteAll(ts);
/*    */   }
/*    */ 
/*    */   public List<ExpenseUse> loadByKey(String key, Object value)
/*    */     throws DaoException
/*    */   {
/* 74 */     return loadByKey(ExpenseUse.class, key, value);
/*    */   }
/*    */ 
/*    */   public List<ExpenseUse> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*    */     throws DaoException
/*    */   {
/* 84 */     return loadByKeyArray(ExpenseUse.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public String getMaxPFCode(String code)
/*    */   {
/* 92 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.expensemanagement.expenseuse.hibernate.HibernateExpenseUse
 * JD-Core Version:    0.6.2
 */