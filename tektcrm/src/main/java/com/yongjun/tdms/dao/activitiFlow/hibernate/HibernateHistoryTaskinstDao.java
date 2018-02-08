/*     */ package com.yongjun.tdms.dao.activitiFlow.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.activitiFlow.HistoryTaskinstDao;
import com.yongjun.tdms.dao.activitiFlow.RunPointDao;
import com.yongjun.tdms.dao.activitiFlow.RunTaskDao;
import com.yongjun.tdms.model.activitiFlow.HistoryTaskinst;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.activitiFlow.RunTask;
/*     */ import com.yongjun.tdms.model.workflow.Point;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateHistoryTaskinstDao extends BaseHibernateDao
/*     */   implements HistoryTaskinstDao
/*     */ {
/*     */   public void deleteAllHistoryTaskinst(Collection<HistoryTaskinst> historyTaskinsts)
/*     */   {
/*  45 */     super.deleteAll(historyTaskinsts);
/*     */   }
/*     */ 
/*     */   public void deleteHistoryTaskinst(HistoryTaskinst point)
/*     */   {
/*  55 */     super.delete(point);
/*     */   }
/*     */ 
/*     */   public List<HistoryTaskinst> loadAllHistoryTaskinst(Long[] pointIds)
/*     */   {
/*  66 */     return super.loadAll(HistoryTaskinst.class, pointIds);
/*     */   }
/*     */ 
/*     */   public List<HistoryTaskinst> loadAllHistoryTaskinst()
/*     */   {
/*  75 */     return super.loadAll(HistoryTaskinst.class);
/*     */   }
/*     */ 
/*     */   public List<HistoryTaskinst> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(HistoryTaskinst.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<HistoryTaskinst> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(HistoryTaskinst.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public HistoryTaskinst loadHistoryTaskinst(Long pointId)
/*     */   {
/* 111 */     return (HistoryTaskinst)super.load(HistoryTaskinst.class, pointId);
/*     */   }
/*     */ 
/*     */   public void storeHistoryTaskinst(HistoryTaskinst point)
/*     */   {
/* 120 */     super.store(point);
/*     */   }
/*     */

}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.hibernate.HibernatePointDao
 * JD-Core Version:    0.6.2
 */