/*     */ package com.yongjun.tdms.dao.workflow.point.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.workflow.point.PointDao;
/*     */ import com.yongjun.tdms.model.workflow.Point;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernatePointDao extends BaseHibernateDao
/*     */   implements PointDao
/*     */ {
/*     */   public void deleteAllPoints(Collection<Point> points)
/*     */   {
/*  45 */     super.deleteAll(points);
/*     */   }
/*     */ 
/*     */   public void deletePoint(Point point)
/*     */   {
/*  55 */     super.delete(point);
/*     */   }
/*     */ 
/*     */   public List<Point> loadAllPoints(Long[] pointIds)
/*     */   {
/*  66 */     return super.loadAll(Point.class, pointIds);
/*     */   }
/*     */ 
/*     */   public List<Point> loadAllPoints()
/*     */   {
/*  75 */     return super.loadAll(Point.class);
/*     */   }
/*     */ 
/*     */   public List<Point> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(Point.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Point> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(Point.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Point loadPoint(Long pointId)
/*     */   {
/* 111 */     return (Point)super.load(Point.class, pointId);
/*     */   }
/*     */ 
/*     */   public void storePoint(Point point)
/*     */   {
/* 120 */     super.store(point);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.hibernate.HibernatePointDao
 * JD-Core Version:    0.6.2
 */