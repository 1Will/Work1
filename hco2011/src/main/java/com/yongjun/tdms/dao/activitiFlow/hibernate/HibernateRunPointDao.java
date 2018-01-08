/*     */ package com.yongjun.tdms.dao.activitiFlow.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.activitiFlow.RunPointDao;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
/*     */ import com.yongjun.tdms.model.workflow.Point;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateRunPointDao extends BaseHibernateDao
/*     */   implements RunPointDao
/*     */ {
/*     */   public void deleteAllPoints(Collection<RunPoint> points)
/*     */   {
/*  45 */     super.deleteAll(points);
/*     */   }
/*     */ 
/*     */   public void deletePoint(RunPoint point)
/*     */   {
/*  55 */     super.delete(point);
/*     */   }
/*     */ 
/*     */   public List<RunPoint> loadAllPoints(Long[] pointIds)
/*     */   {
/*  66 */     return super.loadAll(RunPoint.class, pointIds);
/*     */   }
/*     */ 
/*     */   public List<RunPoint> loadAllPoints()
/*     */   {
/*  75 */     return super.loadAll(RunPoint.class);
/*     */   }
/*     */ 
/*     */   public List<RunPoint> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(RunPoint.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<RunPoint> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(RunPoint.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public RunPoint loadRunPoint(Long pointId)
/*     */   {
/* 111 */     return (RunPoint)super.load(RunPoint.class, pointId);
/*     */   }
/*     */ 
/*     */   public void storeRunPoint(RunPoint point)
/*     */   {
/* 120 */     super.store(point);
/*     */   }
/*     */
			public List<RunPoint> loadAllByHql(String hql) {
				
				return getHibernateTemplate().find(hql);
			}
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.hibernate.HibernatePointDao
 * JD-Core Version:    0.6.2
 */