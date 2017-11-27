/*     */ package com.yongjun.tdms.service.CustomerRelationship.contactArchives.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactsJobResumeDao;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactsJobResumeManager;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultContactsJobResumeManager extends BaseManager
/*     */   implements ContactsJobResumeManager
/*     */ {
/*     */   public final ContactsJobResumeDao contactsJobResumeaDao;
/*     */ 
/*     */   public DefaultContactsJobResumeManager(ContactsJobResumeDao contactsJobResumeaDao)
/*     */   {
/*  48 */     this.contactsJobResumeaDao = contactsJobResumeaDao;
/*     */   }
/*     */ 
/*     */   public void storeContactsJobResume(ContactsJobResume we)
/*     */   {
/*  57 */     this.contactsJobResumeaDao.storeContactsJobResume(we);
/*     */   }
/*     */ 
/*     */   public void deleteContactsJobResume(ContactsJobResume we)
/*     */   {
/*  66 */     this.contactsJobResumeaDao.deleteContactsJobResume(we);
/*     */   }
/*     */ 
/*     */   public void deleteAllContactsJobResume(Collection<ContactsJobResume> wes)
/*     */   {
/*  75 */     this.contactsJobResumeaDao.deleteAllContactsJobResume(wes);
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadAllContactsJobResume(Long[] weIds)
/*     */   {
/*  85 */     return this.contactsJobResumeaDao.loadAllContactsJobResume(weIds);
/*     */   }
/*     */ 
/*     */   public ContactsJobResume loadContactsJobResume(Long weId)
/*     */   {
/*  95 */     return this.contactsJobResumeaDao.loadContactsJobResume(weId);
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadAllContactsJobResume()
/*     */   {
/* 104 */     return this.contactsJobResumeaDao.loadAllContactsJobResume();
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.contactsJobResumeaDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ContactsJobResume> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.contactsJobResumeaDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledContactsJobResumes(Collection<ContactsJobResume> wes)
/*     */   {
/* 135 */     for (ContactsJobResume we : wes) {
/* 136 */       we.setDisabled(true);
/* 137 */       this.contactsJobResumeaDao.storeContactsJobResume(we);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledContactsJobResumes(Collection<ContactsJobResume> wes)
/*     */   {
/* 147 */     for (ContactsJobResume we : wes) {
/* 148 */       we.setDisabled(false);
/* 149 */       this.contactsJobResumeaDao.storeContactsJobResume(we);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.work.pojo.DefaultContactsJobResumeManager
 * JD-Core Version:    0.6.2
 */