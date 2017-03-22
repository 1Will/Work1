/*     */ package com.yongjun.tdms.dao.marketmanager.competitor.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.marketmanager.competitor.CompetitorDao;
/*     */ import com.yongjun.tdms.model.marketmanager.competitor.Competitor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateCompetitorDao extends BaseHibernateDao
/*     */   implements CompetitorDao
/*     */ {
/*     */   public void storeCompetitor(Competitor competitor)
/*     */   {
/*  44 */     store(competitor);
/*     */   }
/*     */ 
/*     */   public void deleteCompetitor(Competitor competitor)
/*     */   {
/*  54 */     delete(competitor);
/*     */   }
/*     */ 
/*     */   public void deleteAllCompetitor(Collection<Competitor> competitorIds)
/*     */   {
/*  64 */     deleteAll(competitorIds);
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadAllCompetitor(Long[] competitorIds)
/*     */   {
/*  75 */     return loadAll(Competitor.class, competitorIds);
/*     */   }
/*     */ 
/*     */   public Competitor loadCompetitor(Long competitorId)
/*     */   {
/*  86 */     return (Competitor)load(Competitor.class, competitorId);
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadAllCompetitor()
/*     */   {
/*  95 */     return loadAll(Competitor.class);
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 111 */     return loadByKey(Competitor.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 127 */     return loadByKeyArray(Competitor.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 138 */     String hql = "select com.code from Competitor as com where com.organization.id=" + orgId + " and com.code like '%" + code + "%'";
/*     */ 
/* 140 */     List codeList = getSession().createQuery(hql).list();
/* 141 */     if (codeList.size() > 0) {
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
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.competitor.hibernate.HibernateCompetitorDao
 * JD-Core Version:    0.6.2
 */