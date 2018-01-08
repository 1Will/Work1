/*     */ package com.yongjun.tdms.dao.activitiFlow.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.activitiFlow.CopySendPersonDao;
import com.yongjun.tdms.model.activitiFlow.CopySendPerson;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateCopySendPersonDao extends BaseHibernateDao
/*     */   implements CopySendPersonDao
/*     */ {
/*     */   public void deleteAllCopySendPersons(Collection<CopySendPerson> CopySendPersons)
/*     */   {
/*  45 */     super.deleteAll(CopySendPersons);
/*     */   }
/*     */ 
/*     */   public void deleteCopySendPerson(CopySendPerson copySendPerson)
/*     */   {
/*  55 */     super.delete(copySendPerson);
/*     */   }
/*     */ 
/*     */   public List<CopySendPerson> loadAllCopySendPersons(Long[] pointIds)
/*     */   {
/*  66 */     return super.loadAll(CopySendPerson.class, pointIds);
/*     */   }
/*     */ 
/*     */   public List<CopySendPerson> loadAllCopySendPersons()
/*     */   {
/*  75 */     return super.loadAll(CopySendPerson.class);
/*     */   }
/*     */ 
/*     */   public List<CopySendPerson> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(CopySendPerson.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<CopySendPerson> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(CopySendPerson.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public CopySendPerson loadCopySendPerson(Long pointId)
/*     */   {
/* 111 */     return (CopySendPerson)super.load(CopySendPerson.class, pointId);
/*     */   }
/*     */ 
/*     */   public void storeCopySendPerson(CopySendPerson point)
/*     */   {
/* 120 */     super.store(point);
/*     */   }
/*     */
			public List<CopySendPerson> loadAllByHql(String hql) {
				
				return getHibernateTemplate().find(hql);
			}
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.hibernate.HibernatePointDao
 * JD-Core Version:    0.6.2
 */