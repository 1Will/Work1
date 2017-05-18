/*     */ package com.yongjun.tdms.dao.CustomerRelationship.contactArchives.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;

import java.util.List;
/*     */ 
/*     */ public class HibernateContactArchives extends BaseHibernateDao
/*     */   implements ContactArchivesDao
/*     */ {
/*     */   public void storeContactArchives(ContactArchives ca)
/*     */   {
/*  43 */     super.store(ca);
/*     */   }
/*     */ 
/*     */   public ContactArchives loadContactArchives(Long caId)
/*     */   {
/*  53 */     return (ContactArchives)super.load(ContactArchives.class, caId);
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadAllContactArchives()
/*     */   {
/*  62 */     return super.loadAll(ContactArchives.class);
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadAllContactArchives(Long[] caIds)
/*     */   {
/*  72 */     return super.loadAll(ContactArchives.class, caIds);
/*     */   }
/*     */ 
/*     */   public void deleteContactArchives(ContactArchives ca)
/*     */   {
/*  81 */     super.delete(ca);
/*     */   }
/*     */ 
/*     */   public void deleteAllContactArchives(List<ContactArchives> caIds)
/*     */   {
/*  90 */     super.deleteAll(caIds);
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKey(ContactArchives.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 116 */     return loadByKeyArray(ContactArchives.class, keyNames, keyValues);
/*     */   }
			public List<ContactArchives> getContactArchivesByCodeAndDate(String date,String name){
				String hql = "from ContactArchives c where convert(varchar,c.createdTime,120) like '"+date+"%' and c.creator = '" + name + "'";
				return getSession().createQuery(hql).list();
			}
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.contactArchives.hibernate.HibernateContactArchives
 * JD-Core Version:    0.6.2
 */