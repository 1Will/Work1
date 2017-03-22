/*     */ package com.yongjun.tdms.dao.task.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.task.TaskDao;
/*     */ import com.yongjun.tdms.model.task.Task;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateTaskDao extends BaseHibernateDao
/*     */   implements TaskDao
/*     */ {
/*     */   public void deleteAllTasks(Collection<Task> tasks)
/*     */   {
/*  45 */     super.deleteAll(tasks);
/*     */   }
/*     */ 
/*     */   public void deleteTask(Task task)
/*     */   {
/*  55 */     super.delete(task);
/*     */   }
/*     */ 
/*     */   public List<Task> loadAllTasks(Long[] taskIds)
/*     */   {
/*  66 */     return super.loadAll(Task.class, taskIds);
/*     */   }
/*     */ 
/*     */   public List<Task> loadAllTasks()
/*     */   {
/*  75 */     return super.loadAll(Task.class);
/*     */   }
/*     */ 
/*     */   public List<Task> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(Task.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Task> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(Task.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Task loadTask(Long taskId)
/*     */   {
/* 111 */     return (Task)super.load(Task.class, taskId);
/*     */   }
/*     */ 
/*     */   public void storeTask(Task task)
/*     */   {
/* 120 */     super.store(task);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.task.hibernate.HibernateTaskDao
 * JD-Core Version:    0.6.2
 */