/*     */ package com.yongjun.tdms.service.activitiFlow.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.activitiFlow.HistoryTaskinstDao;
import com.yongjun.tdms.model.activitiFlow.HistoryTaskinst;
import com.yongjun.tdms.service.activitiFlow.HistoryTaskinstManager;

/*     */ import java.util.Collection;
/*     */ import java.util.List;

/*     */ 
/*     */ public class DefaultHistoryTaskinstManager extends BaseManager
/*     */   implements HistoryTaskinstManager
/*     */ {
/*     */   private HistoryTaskinstDao historyTaskinstDao;
/*     */ 
/*     */   public DefaultHistoryTaskinstManager(HistoryTaskinstDao HistoryTaskinstDao)
/*     */   {
/*  60 */     this.historyTaskinstDao = HistoryTaskinstDao;
/*     */   }
/*     */ 
/*     */   public void setHistoryTaskinstDao(HistoryTaskinstDao HistoryTaskinstDao)
/*     */   {
/*  68 */     this.historyTaskinstDao = HistoryTaskinstDao;
/*     */   }
/*     */ 
/*     */   public HistoryTaskinstDao geHistoryTaskinstDao()
/*     */   {
/*  76 */     return this.historyTaskinstDao;
/*     */   }
/*     */ 
/*     */ 
/*     */   public void deleteHistoryTaskinst(HistoryTaskinst Task)
/*     */   {
/* 116 */     this.historyTaskinstDao.deleteHistoryTaskinst(Task);
/*     */   }
/*     */ 
/*     */ 
/*     */   public List<HistoryTaskinst> loadAllTasks()
/*     */   {
/* 136 */     return this.historyTaskinstDao.loadAllHistoryTaskinst();
/*     */   }
/*     */ 
/*     */   public List<HistoryTaskinst> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 149 */     return this.historyTaskinstDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<HistoryTaskinst> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 162 */     return this.historyTaskinstDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   public String disabled(List<HistoryTaskinst> TaskList)
/*     */   {
/*     */     try
/*     */     {
/* 241 */       for (HistoryTaskinst Task : TaskList)
/*     */       {
/* 246 */           Task.setDisabled(true);
/* 247 */           this.historyTaskinstDao.storeHistoryTaskinst(Task);
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
/*     */   public String enabled(List<HistoryTaskinst> TaskList)
/*     */   {
/*     */     try
/*     */     {
/* 272 */       for (HistoryTaskinst Task : TaskList)
/*     */       {
/* 274 */         Task.setDisabled(false);
/* 275 */         this.historyTaskinstDao.storeHistoryTaskinst(Task);
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
public void storeHistoryTaskinst(HistoryTaskinst paramTask) {
	// TODO Auto-generated method stub
	this.historyTaskinstDao.storeHistoryTaskinst(paramTask);
	
}
public void deleteAllHistoryTaskinsts(Collection<HistoryTaskinst> paramCollection) {
	// TODO Auto-generated method stub
	 for (HistoryTaskinst Task : paramCollection)
	 /*     */     {
	 /* 104 */       this.historyTaskinstDao.deleteHistoryTaskinst(Task);
	 /*     */     }
	
}
public List<HistoryTaskinst> loadAllHistoryTaskinsts(Long[] paramArrayOfLong) {
	// TODO Auto-generated method stub
	 return this.historyTaskinstDao.loadAllHistoryTaskinst(paramArrayOfLong);
}
public HistoryTaskinst loadHistoryTaskinst(Long paramLong) {
	// TODO Auto-generated method stub
	return this.historyTaskinstDao.loadHistoryTaskinst(paramLong);
} }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.pojo.DefaultPointManager
 * JD-Core Version:    0.6.2
 */