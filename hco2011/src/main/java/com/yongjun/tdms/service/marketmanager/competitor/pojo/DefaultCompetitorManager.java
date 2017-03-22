/*     */ package com.yongjun.tdms.service.marketmanager.competitor.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.marketmanager.competitor.CompetitorDao;
/*     */ import com.yongjun.tdms.model.marketmanager.competitor.Competitor;
/*     */ import com.yongjun.tdms.service.marketmanager.competitor.CompetitorManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultCompetitorManager extends BaseManager
/*     */   implements CompetitorManager
/*     */ {
/*     */   private final CompetitorDao competitorDao;
/*     */ 
/*     */   public DefaultCompetitorManager(CompetitorDao competitorDao)
/*     */   {
/*  45 */     this.competitorDao = competitorDao;
/*     */   }
/*     */ 
/*     */   public void storeCompetitor(Competitor competitor)
/*     */   {
/*  54 */     this.competitorDao.storeCompetitor(competitor);
/*     */   }
/*     */ 
/*     */   public void deleteCompetitor(Competitor competitor)
/*     */   {
/*  64 */     this.competitorDao.deleteCompetitor(competitor);
/*     */   }
/*     */ 
/*     */   public void deleteAllCompetitor(Collection<Competitor> competitors)
/*     */   {
/*  74 */     this.competitorDao.deleteAllCompetitor(competitors);
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadAllCompetitor(Long[] competitorIds)
/*     */   {
/*  86 */     return this.competitorDao.loadAllCompetitor(competitorIds);
/*     */   }
/*     */ 
/*     */   public Competitor loadCompetitor(Long competitorId)
/*     */   {
/*  97 */     return this.competitorDao.loadCompetitor(competitorId);
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadAllCompetitor()
/*     */   {
/* 106 */     return this.competitorDao.loadAllCompetitor();
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 122 */     return this.competitorDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Competitor> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 138 */     return this.competitorDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllCompetitor(Collection<Competitor> competitorList)
/*     */   {
/* 148 */     for (Competitor a : competitorList) {
/* 149 */       a.setDisabled(true);
/* 150 */       this.competitorDao.storeCompetitor(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllCompetitor(Collection<Competitor> competitorList)
/*     */   {
/* 161 */     for (Competitor a : competitorList) {
/* 162 */       a.setDisabled(false);
/* 163 */       this.competitorDao.storeCompetitor(a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 173 */     return this.competitorDao.getMaxPFCode(code, orgId);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.competitor.pojo.DefaultCompetitorManager
 * JD-Core Version:    0.6.2
 */