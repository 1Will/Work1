/*     */ package com.yongjun.tdms.service.CustomerRelationship.contactArchives.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;

/*     */ import java.io.PrintStream;
import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultContactArchivesManager extends BaseManager
/*     */   implements ContactArchivesManager
/*     */ {
/*     */   public final ContactArchivesDao contactArchivesDao;
			private final UserManager userManager;
/*     */ 
/*     */   public DefaultContactArchivesManager(ContactArchivesDao contactArchivesDao,UserManager userManager)
/*     */   {
/*  46 */     this.contactArchivesDao = contactArchivesDao;
			  this.userManager =userManager;
/*     */   }
/*     */ 
/*     */   public void storeContactArchives(ContactArchives ca)
/*     */   {
/*  54 */     setInfoIntegrity(ca);
/*  55 */     this.contactArchivesDao.storeContactArchives(ca);
/*     */   }
/*     */ 
/*     */   public void setInfoIntegrity(ContactArchives contactArchives)
/*     */   {
/*  63 */     float base = 60.0F;
/*  64 */     if ((null != contactArchives.getAbbreviations()) && (!"".equals(contactArchives.getAbbreviations()))) {
/*  65 */       base = (float)(base + 2.5D);
/*     */     }
/*     */ 
/*  68 */     if ((null != contactArchives.getDept()) && (!"".equals(contactArchives.getDept()))) {
/*  69 */       base = (float)(base + 2.2D);
/*     */     }
/*     */ 
/*  72 */     if ((null != contactArchives.getDuty()) && (!"".equals(contactArchives.getDuty()))) {
/*  73 */       base = (float)(base + 2.2D);
/*     */     }
/*     */ 
/*  77 */     base = (float)(base + 2.2D);
/*     */ 
/*  79 */     if ((null != contactArchives.getMobilePhone()) && (!"".equals(contactArchives.getMobilePhone()))) {
/*  80 */       base = (float)(base + 2.2D);
/*     */     }
/*  82 */     if ((null != contactArchives.getFax()) && (!"".equals(contactArchives.getFax()))) {
/*  83 */       base = (float)(base + 2.2D);
/*     */     }
/*  85 */     if ((null != contactArchives.getPostCode()) && (!"".equals(contactArchives.getPostCode()))) {
/*  86 */       System.out.println("6");
/*  87 */       base = (float)(base + 2.2D);
/*     */     }
/*  89 */     if ((null != contactArchives.getAddress()) && (!"".equals(contactArchives.getAddress()))) {
/*  90 */       System.out.println("7");
/*  91 */       base = (float)(base + 2.2D);
/*     */     }
/*  93 */     if ((null != contactArchives.getEmail()) && (!"".equals(contactArchives.getEmail()))) {
/*  94 */       base = (float)(base + 2.2D);
/*     */     }
/*  96 */     if ((null != contactArchives.getQq()) && (!"".equals(contactArchives.getQq()))) {
/*  97 */       base = (float)(base + 2.2D);
/*     */     }
/*  99 */     if ((null != contactArchives.getMsn()) && (!"".equals(contactArchives.getMsn()))) {
/* 100 */       base = (float)(base + 2.2D);
/*     */     }
/* 102 */     if ((null != contactArchives.getPhone()) && (!"".equals(contactArchives.getPhone()))) {
/* 103 */       base = (float)(base + 2.2D);
/*     */     }
/* 105 */     if ((null != contactArchives.getSchool()) && (!"".equals(contactArchives.getSchool()))) {
/* 106 */       base = (float)(base + 2.2D);
/*     */     }
/* 108 */     if ((null != contactArchives.getAddress()) && (!"".equals(contactArchives.getAddress()))) {
/* 109 */       base = (float)(base + 2.2D);
/*     */     }
/* 111 */     if ((null != contactArchives.getComment()) && (!"".equals(contactArchives.getComment()))) {
/* 112 */       base = (float)(base + 2.2D);
/*     */     }
/* 114 */     if ((null != contactArchives.getProfessional()) && (!"".equals(contactArchives.getProfessional()))) {
/* 115 */       base = (float)(base + 2.2D);
/*     */     }
/* 117 */     if ((null != contactArchives.getFavorite()) && (!"".equals(contactArchives.getFavorite()))) {
/* 118 */       base = (float)(base + 2.2D);
/*     */     }
/* 120 */     if ((null != contactArchives.getBirthplace()) && (!"".equals(contactArchives.getBirthplace()))) {
/* 121 */       base = (float)(base + 2.2D);
/*     */     }
/* 123 */     if ((null != contactArchives.getNationality()) && (!"".equals(contactArchives.getNationality()))) {
/* 124 */       base = (float)(base + 2.2D);
/*     */     }
/* 126 */     if ((null != contactArchives.getTemperament()) && (!"".equals(contactArchives.getTemperament()))) {
/* 127 */       base = (float)(base + 2.2D);
/*     */     }
/* 129 */     if ((null != contactArchives.getBirthday()) && (!"".equals(contactArchives.getBirthday()))) {
/* 130 */       base = (float)(base + 2.2D);
/*     */     }
/* 132 */     contactArchives.setCustomerInfoIntegrity(Float.valueOf(base));
/*     */   }
/*     */ 
/*     */   public ContactArchives loadContactArchives(Long caId)
/*     */   {
/* 142 */     return this.contactArchivesDao.loadContactArchives(caId);
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadAllContactArchives()
/*     */   {
/* 151 */     return this.contactArchivesDao.loadAllContactArchives();
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadAllContactArchives(Long[] caIds)
/*     */   {
/* 161 */     return this.contactArchivesDao.loadAllContactArchives(caIds);
/*     */   }
/*     */ 
/*     */   public void deleteContactArchives(ContactArchives ca)
/*     */   {
/* 170 */     this.contactArchivesDao.deleteContactArchives(ca);
/*     */   }
/*     */ 
/*     */   public void deleteAllContactArchives(List<ContactArchives> caIds)
/*     */   {
/* 179 */     this.contactArchivesDao.deleteAllContactArchives(caIds);
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/* 190 */     return this.contactArchivesDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public void disabledAllContactArchives(List<ContactArchives> cas)
/*     */   {
/* 199 */     for (ContactArchives archives : cas) {
/* 200 */       archives.setDisabled(true);
/* 201 */       this.contactArchivesDao.storeContactArchives(archives);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllContactArchives(List<ContactArchives> cas)
/*     */   {
/* 211 */     for (ContactArchives archives : cas) {
/* 212 */       archives.setDisabled(false);
/* 213 */       this.contactArchivesDao.storeContactArchives(archives);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 229 */     return this.contactArchivesDao.loadByKeyArray(keyNames, keyValues);
/*     */   }

			/*public List<ContactArchives> loadContactArchivesByDate(String da){*/
			public int loadContactArchivesByDate(String userId,String da){
				User user = userManager.loadUser(Long.parseLong(userId));
				List<ContactArchives> list = this.contactArchivesDao.getContactArchivesByCodeAndDate(da,user.getName());
				int size = list.size();
				return size;
//				List<ContactArchives> clist = new ArrayList<ContactArchives>();
//				for(ContactArchives contactArchives:list){
//					ContactArchives cs =new ContactArchives();
//					cs.setId(contactArchives.getId());
//					clist.add(cs);
//				}
//				return clist;
			}
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.CustomerRelationship.contactArchives.pojo.DefaultContactArchivesManager
 * JD-Core Version:    0.6.2
 */