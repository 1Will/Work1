/*     */ package com.yongjun.tdms.dao.CustomerRelationship.contactArchives.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactsJobResumeDao;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateContactsJobResume extends BaseHibernateDao
/*     */   implements ContactsJobResumeDao
/*     */ {
/*     */   public void storeContactsJobResume(ContactsJobResume we)
/*     */   {
/*  44 */     super.store(we);
/*     */   }
/*     */ 
/*     */   public void deleteContactsJobResume(ContactsJobResume we)
/*     */   {
/*  53 */     super.delete(we);
/*     */   }
/*     */ 
/*     */   public void deleteAllContactsJobResume(Collection<ContactsJobResume> wes)
/*     */   {
/*  62 */     super.deleteAll(wes);
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadAllContactsJobResume(Long[] weIds)
/*     */   {
/*  72 */     return super.loadAll(ContactsJobResume.class, weIds);
/*     */   }
/*     */ 
/*     */   public ContactsJobResume loadContactsJobResume(Long weId)
/*     */   {
/*  82 */     return (ContactsJobResume)super.load(ContactsJobResume.class, weId);
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadAllContactsJobResume()
/*     */   {
/*  91 */     return super.loadAll(ContactsJobResume.class);
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(ContactsJobResume.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(ContactsJobResume.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.work.hibernate.HibernateContactsJobResume
 * JD-Core Version:    0.6.2
 */