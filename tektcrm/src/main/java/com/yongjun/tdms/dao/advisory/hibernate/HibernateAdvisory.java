/*    */ package com.yongjun.tdms.dao.advisory.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.advisory.AdvisoryDao;
/*    */ import com.yongjun.tdms.model.advisory.Advisory;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateAdvisory extends BaseHibernateDao
/*    */   implements AdvisoryDao
/*    */ {
/*    */   public void deleteAdvisory(Advisory advisory)
/*    */   {
/* 21 */     delete(advisory);
/*    */   }
/*    */ 
/*    */   public void deleteAllAdvisory(Collection<Advisory> advisorys) {
/* 25 */     deleteAll(advisorys);
/*    */   }
/*    */ 
/*    */   public Advisory loadAdvisory(Long advisoryId) {
/* 29 */     return (Advisory)load(Advisory.class, advisoryId);
/*    */   }
/*    */ 
/*    */   public List<Advisory> loadAllAdvisory(Long[] advisoryIds) {
/* 33 */     return loadAll(Advisory.class, advisoryIds);
/*    */   }
/*    */ 
/*    */   public List<Advisory> loadAllAdvisorys() {
/* 37 */     return loadAll(Advisory.class);
/*    */   }
/*    */ 
/*    */   public List<Advisory> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 41 */     return loadByKey(Advisory.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public void storeAdvisory(Advisory advisory) {
/* 45 */     store(advisory);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.advisory.hibernate.HibernateAdvisory
 * JD-Core Version:    0.6.2
 */