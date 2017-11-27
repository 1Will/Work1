/*     */ package com.yongjun.tdms.service.CustomerRelationship.contactArchives.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactToHistoryDao;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultContactToHistoryManager extends BaseManager
/*     */   implements ContactToHistoryManager
/*     */ {
/*     */   public final ContactToHistoryDao contactToHistoryDao;
/*     */ 
/*     */   public DefaultContactToHistoryManager(ContactToHistoryDao contactToHistoryDao)
/*     */   {
/*  48 */     this.contactToHistoryDao = contactToHistoryDao;
/*     */   }
/*     */ 
/*     */   public void storeContactToHistory(ContactToHistory we)
/*     */   {
/*  57 */     this.contactToHistoryDao.storeContactToHistory(we);
/*     */   }
/*     */ 
/*     */   public void deleteContactToHistory(ContactToHistory we)
/*     */   {
/*  66 */     this.contactToHistoryDao.deleteContactToHistory(we);
/*     */   }
/*     */ 
/*     */   public void deleteAllContactToHistory(Collection<ContactToHistory> wes)
/*     */   {
/*  75 */     this.contactToHistoryDao.deleteAllContactToHistory(wes);
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadAllContactToHistory(Long[] weIds)
/*     */   {
/*  85 */     return this.contactToHistoryDao.loadAllContactToHistory(weIds);
/*     */   }
/*     */ 
/*     */   public ContactToHistory loadContactToHistory(Long weId)
/*     */   {
/*  95 */     return this.contactToHistoryDao.loadContactToHistory(weId);
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadAllContactToHistory()
/*     */   {
/* 104 */     return this.contactToHistoryDao.loadAllContactToHistory();
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.contactToHistoryDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContactToHistory> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.contactToHistoryDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledContactToHistorys(Collection<ContactToHistory> wes)
/*     */   {
/* 135 */     for (ContactToHistory we : wes) {
/* 136 */       we.setDisabled(true);
/* 137 */       this.contactToHistoryDao.storeContactToHistory(we);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledContactToHistorys(Collection<ContactToHistory> wes)
/*     */   {
/* 147 */     for (ContactToHistory we : wes) {
/* 148 */       we.setDisabled(false);
/* 149 */       this.contactToHistoryDao.storeContactToHistory(we);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.work.pojo.DefaultContactToHistoryManager
 * JD-Core Version:    0.6.2
 */