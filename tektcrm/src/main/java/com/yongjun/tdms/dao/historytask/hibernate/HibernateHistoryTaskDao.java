/*     */ package com.yongjun.tdms.dao.historytask.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.historytask.HistoryTaskDao;
/*     */ import com.yongjun.tdms.model.historytask.HistoryTask;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateHistoryTaskDao extends BaseHibernateDao
/*     */   implements HistoryTaskDao
/*     */ {
/*     */   public void storeHistoryTask(HistoryTask historyTask)
/*     */   {
/*  42 */     store(historyTask);
/*     */   }
/*     */ 
/*     */   public void deleteHistoryTask(HistoryTask historyTask)
/*     */   {
/*  52 */     delete(historyTask);
/*     */   }
/*     */ 
/*     */   public void deleteAllHistoryTask(Collection<HistoryTask> historyTaskIds)
/*     */   {
/*  62 */     deleteAll(historyTaskIds);
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadAllHistoryTask(Long[] historyTaskIds)
/*     */   {
/*  73 */     return loadAll(HistoryTask.class, historyTaskIds);
/*     */   }
/*     */ 
/*     */   public HistoryTask loadHistoryTask(Long historyTaskId)
/*     */   {
/*  84 */     return (HistoryTask)load(HistoryTask.class, historyTaskId);
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadAllHistoryTask()
/*     */   {
/*  93 */     return loadAll(HistoryTask.class);
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 109 */     return loadByKey(HistoryTask.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 125 */     return loadByKeyArray(HistoryTask.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.historytask.hibernate.HibernateHistoryTaskDao
 * JD-Core Version:    0.6.2
 */