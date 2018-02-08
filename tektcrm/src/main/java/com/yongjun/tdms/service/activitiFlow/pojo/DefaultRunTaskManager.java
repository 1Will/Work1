/*     */ package com.yongjun.tdms.service.activitiFlow.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.activitiFlow.RunTaskDao;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.activitiFlow.RunTask;
import com.yongjun.tdms.service.activitiFlow.RunTaskManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;

import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultRunTaskManager extends BaseManager
/*     */   implements RunTaskManager
/*     */ {
/*     */   private RunTaskDao runTaskDao;
/*     */ 
/*     */   public DefaultRunTaskManager(RunTaskDao runTaskDao)
/*     */   {
/*  60 */     this.runTaskDao = runTaskDao;
/*     */   }
/*     */ 
/*     */   public void setRunTaskDao(RunTaskDao runTaskDao)
/*     */   {
/*  68 */     this.runTaskDao = runTaskDao;
/*     */   }
/*     */ 
/*     */   public RunTaskDao geRunTaskDao()
/*     */   {
/*  76 */     return this.runTaskDao;
/*     */   }
/*     */ 
/*     */ 
/*     */   public void deleteRunTask(RunTask Task)
/*     */   {
/* 116 */     this.runTaskDao.deleteRunTask(Task);
/*     */   }
/*     */ 
/*     */ 
/*     */   public List<RunTask> loadAllTasks()
/*     */   {
/* 136 */     return this.runTaskDao.loadAllRunTask();
/*     */   }
/*     */ 
/*     */   public List<RunTask> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 149 */     return this.runTaskDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<RunTask> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 162 */     return this.runTaskDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   public String disabled(List<RunTask> TaskList)
/*     */   {
/*     */     try
/*     */     {
/* 241 */       for (RunTask Task : TaskList)
/*     */       {
/* 246 */           Task.setDisabled(true);
/* 247 */           this.runTaskDao.storeRunTask(Task);
/*     */         }
/*     */       }
/*     */     catch (Exception e)
/*     */     {
/* 258 */       e.printStackTrace();
/* 259 */       return "error";
/*     */     }
/* 261 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enabled(List<RunTask> TaskList)
/*     */   {
/*     */     try
/*     */     {
/* 272 */       for (RunTask Task : TaskList)
/*     */       {
/* 274 */         Task.setDisabled(false);
/* 275 */         this.runTaskDao.storeRunTask(Task);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 280 */       e.printStackTrace();
/* 281 */       return "error";
/*     */     }
/* 283 */     return "success";
/*     */   }
/*     */
public void storeRunTask(RunTask paramTask) {
	// TODO Auto-generated method stub
	this.runTaskDao.storeRunTask(paramTask);
	
}
public void deleteAllRunTasks(Collection<RunTask> paramCollection) {
	// TODO Auto-generated method stub
	 for (RunTask Task : paramCollection)
	 /*     */     {
	 /* 104 */       this.runTaskDao.deleteRunTask(Task);
	 /*     */     }
	
}
public List<RunTask> loadAllRunTasks(Long[] paramArrayOfLong) {
	// TODO Auto-generated method stub
	 return this.runTaskDao.loadAllRunTask(paramArrayOfLong);
}
public RunTask loadRunTask(Long paramLong) {
	// TODO Auto-generated method stub
	return this.runTaskDao.loadRunTask(paramLong);
} }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.pojo.DefaultPointManager
 * JD-Core Version:    0.6.2
 */