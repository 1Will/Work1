/*     */ package com.yongjun.tdms.dao.personnelFiles.personnelcasting.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.personnelFiles.personnelcasting.PersonnelCastingDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.personnelcasting.PersonnelCasting;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernatePersonnelCasting extends BaseHibernateDao
/*     */   implements PersonnelCastingDao
/*     */ {
/*     */   public void storePersonnelCasting(PersonnelCasting t)
/*     */   {
/*  19 */     store(t);
/*     */   }
/*     */ 
/*     */   public PersonnelCasting loadPersonnelCasting(Long id)
/*     */   {
/*  28 */     return (PersonnelCasting)load(PersonnelCasting.class, id);
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadPersonnelCasting()
/*     */   {
/*  36 */     return loadAll(PersonnelCasting.class);
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadAllPersonnelCasting(Long[] tIds)
/*     */   {
/*  45 */     return loadAll(PersonnelCasting.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deletePersonnelCasting(PersonnelCasting t)
/*     */   {
/*  53 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllPersonnelCasting(List<PersonnelCasting> ts)
/*     */   {
/*  61 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  72 */     return loadByKey(PersonnelCasting.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  82 */     return loadByKeyArray(PersonnelCasting.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  91 */     String hql = "select c.code from PersonnelCasting as c where  c.code like '%" + code + "%'";
/*  92 */     List codeList = getSession().createQuery(hql).list();
/*  93 */     if (null!= codeList && codeList.size() > 0) {
/*  94 */       List items = new ArrayList();
/*  95 */       for (int i = 0; i < codeList.size(); i++) {
/*  96 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/*  97 */         items.add(item);
/*     */       }
/*  99 */       Collections.sort(items);
/* 100 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 102 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.personnelcasting.hibernate.HibernatePersonnelCasting
 * JD-Core Version:    0.6.2
 */