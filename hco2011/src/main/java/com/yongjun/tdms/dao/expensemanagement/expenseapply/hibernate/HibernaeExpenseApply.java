/*     */ package com.yongjun.tdms.dao.expensemanagement.expenseapply.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.expensemanagement.expenseapply.ExpenseApplyDao;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseapply.ExpenseApply;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernaeExpenseApply extends BaseHibernateDao
/*     */   implements ExpenseApplyDao
/*     */ {
/*     */   public void storeExpenseApply(ExpenseApply t)
/*     */   {
/*  24 */     store(t);
/*     */   }
/*     */ 
/*     */   public ExpenseApply loadExpenseApply(Long id)
/*     */   {
/*  33 */     return (ExpenseApply)load(ExpenseApply.class, id);
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadExpenseApply()
/*     */   {
/*  41 */     return loadAll(ExpenseApply.class);
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadAllExpenseApply(Long[] tIds)
/*     */   {
/*  51 */     return loadAll(ExpenseApply.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deleteExpenseApply(ExpenseApply t)
/*     */   {
/*  59 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllExpenseApply(List<ExpenseApply> ts)
/*     */   {
/*  67 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  78 */     return loadByKey(ExpenseApply.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<ExpenseApply> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  88 */     return loadByKeyArray(ExpenseApply.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  97 */     String hql = "select c.code from ExpenseApply as c where  c.code like '%" + code + "%'";
/*  98 */     List codeList = getSession().createQuery(hql).list();
/*  99 */     if (codeList.size() > 0) {
/* 100 */       List items = new ArrayList();
/* 101 */       for (int i = 0; i < codeList.size(); i++) {
/* 102 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 103 */         items.add(item);
/*     */       }
/* 105 */       Collections.sort(items);
/* 106 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.expensemanagement.expenseapply.hibernate.HibernaeExpenseApply
 * JD-Core Version:    0.6.2
 */