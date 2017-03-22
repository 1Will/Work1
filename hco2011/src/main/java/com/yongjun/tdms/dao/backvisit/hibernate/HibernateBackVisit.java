/*    */ package com.yongjun.tdms.dao.backvisit.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.backvisit.BackVisitDao;
/*    */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateBackVisit extends BaseHibernateDao
/*    */   implements BackVisitDao
/*    */ {
/*    */   public void deleteAllBackVisit(Collection<BackVisit> backVisits)
/*    */   {
/* 22 */     deleteAll(backVisits);
/*    */   }
/*    */ 
/*    */   public void deleteBackVisit(BackVisit backVisit) {
/* 26 */     delete(backVisit);
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadAllBackVisit(Long[] backVisitIds) {
/* 30 */     return loadAll(BackVisit.class, backVisitIds);
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadAllBackVisits() {
/* 34 */     return loadAll(BackVisit.class);
/*    */   }
/*    */ 
/*    */   public BackVisit loadBackVisit(Long backVisitId) {
/* 38 */     return (BackVisit)load(BackVisit.class, backVisitId);
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 42 */     return loadByKey(BackVisit.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 46 */     return loadByKeyArray(BackVisit.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public void storeBackVisit(BackVisit backVisit) {
/* 50 */     store(backVisit);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.backvisit.hibernate.HibernateBackVisit
 * JD-Core Version:    0.6.2
 */