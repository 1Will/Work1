/*     */ package com.yongjun.tdms.dao.COM.VOC.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.COM.VOC.VocDao;
/*     */ import com.yongjun.tdms.model.COM.VOC.Voc;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateVocDao extends BaseHibernateDao
/*     */   implements VocDao
/*     */ {
/*     */   public void deleteAllVoc(Collection<Voc> vocs)
/*     */   {
/*  24 */     deleteAll(vocs);
/*     */   }
/*     */ 
/*     */   public void deleteVoc(Voc voc)
/*     */   {
/*  31 */     delete(voc);
/*     */   }
/*     */ 
/*     */   public List<Voc> loadAllVoc(Long[] vocIds)
/*     */   {
/*  39 */     return loadAll(Voc.class, vocIds);
/*     */   }
/*     */ 
/*     */   public List<Voc> loadAllVoc()
/*     */   {
/*  46 */     return loadAll(Voc.class);
/*     */   }
/*     */ 
/*     */   public List<Voc> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  58 */     return loadByKey(Voc.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Voc> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  69 */     return loadByKeyArray(Voc.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Voc loadVoc(Long vocId)
/*     */   {
/*  77 */     return (Voc)load(Voc.class, vocId);
/*     */   }
/*     */ 
/*     */   public void storeVoc(Voc voc)
/*     */   {
/*  84 */     store(voc);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  92 */     String hql = "select v.code from Voc as v where  v.code like '%" + code + "%'";
/*  93 */     List codeList = getSession().createQuery(hql).list();
/*  94 */     if (codeList.size() > 0) {
/*  95 */       List items = new ArrayList();
/*  96 */       for (int i = 0; i < codeList.size(); i++) {
/*  97 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/*  98 */         items.add(item);
/*     */       }
/* 100 */       Collections.sort(items);
/* 101 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.VOC.hibernate.HibernateVocDao
 * JD-Core Version:    0.6.2
 */