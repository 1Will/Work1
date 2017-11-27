/*     */ package com.yongjun.tdms.dao.CustomerRelationship.contactArchives.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactToHistoryDao;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactToHistoryDao;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateContactToHistory extends BaseHibernateDao
/*     */   implements ContactToHistoryDao
/*     */ {
/*     */   public void storeContactToHistory(ContactToHistory we)
/*     */   {
/*  44 */     super.store(we);
/*     */   }
/*     */ 
/*     */   public void deleteContactToHistory(ContactToHistory we)
/*     */   {
/*  53 */     super.delete(we);
/*     */   }
/*     */ 
/*     */   public void deleteAllContactToHistory(Collection<ContactToHistory> wes)
/*     */   {
/*  62 */     super.deleteAll(wes);
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadAllContactToHistory(Long[] weIds)
/*     */   {
/*  72 */     return super.loadAll(ContactToHistory.class, weIds);
/*     */   }
/*     */ 
/*     */   public ContactToHistory loadContactToHistory(Long weId)
/*     */   {
/*  82 */     return (ContactToHistory)super.load(ContactToHistory.class, weId);
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadAllContactToHistory()
/*     */   {
/*  91 */     return super.loadAll(ContactToHistory.class);
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(ContactToHistory.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(ContactToHistory.class, keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.work.hibernate.HibernateContactToHistory
 * JD-Core Version:    0.6.2
 */