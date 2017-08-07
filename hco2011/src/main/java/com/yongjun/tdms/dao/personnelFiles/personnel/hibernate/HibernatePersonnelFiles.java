/*     */ package com.yongjun.tdms.dao.personnelFiles.personnel.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.personnel.PersonnelFilesDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernatePersonnelFiles extends BaseHibernateDao
/*     */   implements PersonnelFilesDao
/*     */ {
/*     */   public void storePersonnel(PersonnelFiles pf)
/*     */   {
/*  46 */     super.store(pf);
/*     */   }
/*     */ 
/*     */   public void deletePersonnel(PersonnelFiles pf)
/*     */   {
/*  55 */     super.delete(pf);
/*     */   }
/*     */ 
/*     */   public void deleteAllPersonnel(Collection<PersonnelFiles> pfs)
/*     */   {
/*  64 */     super.deleteAll(pfs);
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadAllPersonnel(Long[] pfIds)
/*     */   {
/*  74 */     return super.loadAll(PersonnelFiles.class, pfIds);
/*     */   }
/*     */ 
/*     */   public PersonnelFiles loadPersonnel(Long pfId)
/*     */   {
/*  84 */     return (PersonnelFiles)super.load(PersonnelFiles.class, pfId);
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadAllPersonnel()
/*     */   {
/*  93 */     return super.loadAll(PersonnelFiles.class);
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 104 */     return super.loadByKey(PersonnelFiles.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 115 */     return super.loadByKeyArray(PersonnelFiles.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 125 */     String hql = "select pf.code from PersonnelFiles as pf where pf.organization.id=" + orgId + " and pf.code like '%" + code + "%'";
/* 126 */     List codeList = getSession().createQuery(hql).list();
/* 127 */     if (null!= codeList && codeList.size() > 0) {
/* 128 */       List items = new ArrayList();
/* 129 */       for (int i = 0; i < codeList.size(); i++) {
/* 130 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 131 */         items.add(item);
/*     */       }
/* 133 */       Collections.sort(items);
/* 134 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.personnel.hibernate.HibernatePersonnelFiles
 * JD-Core Version:    0.6.2
 */