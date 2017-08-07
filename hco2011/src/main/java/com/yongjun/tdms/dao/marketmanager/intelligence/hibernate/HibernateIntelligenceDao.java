/*     */ package com.yongjun.tdms.dao.marketmanager.intelligence.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.intelligence.IntelligenceDao;
/*     */ import com.yongjun.tdms.model.marketmanager.intelligence.Intelligence;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateIntelligenceDao extends BaseHibernateDao
/*     */   implements IntelligenceDao
/*     */ {
/*     */   public void storeIntelligence(Intelligence intelligence)
/*     */   {
/*  44 */     store(intelligence);
/*     */   }
/*     */ 
/*     */   public void deleteIntelligence(Intelligence intelligence)
/*     */   {
/*  54 */     delete(intelligence);
/*     */   }
/*     */ 
/*     */   public void deleteAllIntelligence(Collection<Intelligence> intelligenceIds)
/*     */   {
/*  64 */     deleteAll(intelligenceIds);
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadAllIntelligence(Long[] intelligenceIds)
/*     */   {
/*  75 */     return loadAll(Intelligence.class, intelligenceIds);
/*     */   }
/*     */ 
/*     */   public Intelligence loadIntelligence(Long intelligenceId)
/*     */   {
/*  86 */     return (Intelligence)load(Intelligence.class, intelligenceId);
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadAllIntelligence()
/*     */   {
/*  95 */     return loadAll(Intelligence.class);
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 111 */     return loadByKey(Intelligence.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Intelligence> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 127 */     return loadByKeyArray(Intelligence.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 138 */     String hql = "select int.code from Intelligence as int where int.organization.id=" + orgId + " and int.code like '%" + code + "%'";
/*     */ 
/* 140 */     List codeList = getSession().createQuery(hql).list();
/* 141 */     if (null!= codeList && codeList.size() > 0) {
/* 142 */       List items = new ArrayList();
/* 143 */       for (int i = 0; i < codeList.size(); i++) {
/* 144 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 145 */         items.add(item);
/*     */       }
/* 147 */       Collections.sort(items);
/* 148 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.intelligence.hibernate.HibernateIntelligenceDao
 * JD-Core Version:    0.6.2
 */