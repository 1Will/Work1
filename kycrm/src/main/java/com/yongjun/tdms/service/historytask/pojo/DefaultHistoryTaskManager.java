/*     */ package com.yongjun.tdms.service.historytask.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.historytask.HistoryTaskDao;
/*     */ import com.yongjun.tdms.model.historytask.HistoryTask;
/*     */ import com.yongjun.tdms.service.historytask.HistoryTaskManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultHistoryTaskManager extends BaseManager
/*     */   implements HistoryTaskManager
/*     */ {
/*     */   private final HistoryTaskDao historyTaskDao;
/*     */ 
/*     */   public DefaultHistoryTaskManager(HistoryTaskDao historyTaskDao)
/*     */   {
/*  45 */     this.historyTaskDao = historyTaskDao;
/*     */   }
/*     */ 
/*     */   public void storeHistoryTask(HistoryTask historyTask)
/*     */   {
/*  54 */     this.historyTaskDao.storeHistoryTask(historyTask);
/*     */   }
/*     */ 
/*     */   public void deleteHistoryTask(HistoryTask historyTask)
/*     */   {
/*  64 */     this.historyTaskDao.deleteHistoryTask(historyTask);
/*     */   }
/*     */ 
/*     */   public void deleteAllHistoryTask(Collection<HistoryTask> historyTasks)
/*     */   {
/*  74 */     this.historyTaskDao.deleteAllHistoryTask(historyTasks);
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadAllHistoryTask(Long[] historyTaskIds)
/*     */   {
/*  86 */     return this.historyTaskDao.loadAllHistoryTask(historyTaskIds);
/*     */   }
/*     */ 
/*     */   public HistoryTask loadHistoryTask(Long historyTaskId)
/*     */   {
/*  97 */     return this.historyTaskDao.loadHistoryTask(historyTaskId);
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadAllHistoryTask()
/*     */   {
/* 106 */     return this.historyTaskDao.loadAllHistoryTask();
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 122 */     return this.historyTaskDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<HistoryTask> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 138 */     return this.historyTaskDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllHistoryTask(Collection<HistoryTask> historyTaskList)
/*     */   {
/* 148 */     for (HistoryTask a : historyTaskList) {
/* 149 */       a.setDisabled(true);
/* 150 */       this.historyTaskDao.storeHistoryTask(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllHistoryTask(Collection<HistoryTask> historyTaskList)
/*     */   {
/* 161 */     for (HistoryTask a : historyTaskList) {
/* 162 */       a.setDisabled(false);
/* 163 */       this.historyTaskDao.storeHistoryTask(a);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.historytask.pojo.DefaultHistoryTaskManager
 * JD-Core Version:    0.6.2
 */