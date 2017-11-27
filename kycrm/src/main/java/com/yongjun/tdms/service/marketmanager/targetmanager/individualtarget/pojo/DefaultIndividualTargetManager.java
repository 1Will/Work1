/*     */ package com.yongjun.tdms.service.marketmanager.targetmanager.individualtarget.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.marketmanager.targetmanager.individualtarget.IndividualTargetDao;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.individualtarget.IndividualTarget;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.individualtarget.IndividualTargetManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultIndividualTargetManager extends BaseManager
/*     */   implements IndividualTargetManager
/*     */ {
/*     */   private final IndividualTargetDao dao;
/*     */ 
/*     */   public DefaultIndividualTargetManager(IndividualTargetDao dao)
/*     */   {
/*  27 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void deleteAllIndividualTarget(List<IndividualTarget> ts)
/*     */   {
/*  35 */     this.dao.deleteAllIndividualTarget(ts);
/*     */   }
/*     */ 
/*     */   public void deleteIndividualTarget(IndividualTarget t)
/*     */   {
/*  42 */     this.dao.deleteIndividualTarget(t);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadAllIndividualTarget(Long[] tIds)
/*     */   {
/*  50 */     return this.dao.loadAllIndividualTarget(tIds);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  61 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  72 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public IndividualTarget loadIndividualTarget(Long id)
/*     */   {
/*  80 */     return this.dao.loadIndividualTarget(id);
/*     */   }
/*     */ 
/*     */   public List<IndividualTarget> loadIndividualTarget()
/*     */   {
/*  87 */     return this.dao.loadIndividualTarget();
/*     */   }
/*     */ 
/*     */   public void storeIndividualTarget(IndividualTarget t)
/*     */   {
/*  94 */     this.dao.storeIndividualTarget(t);
/*     */   }
/*     */ 
/*     */   public void disabledAllIndividualTarget(List<IndividualTarget> ts)
/*     */   {
/* 101 */     for (IndividualTarget individualTarget : ts) {
/* 102 */       individualTarget.setDisabled(true);
/* 103 */       this.dao.storeIndividualTarget(individualTarget);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllIndividualTarget(List<IndividualTarget> ts)
/*     */   {
/* 111 */     for (IndividualTarget individualTarget : ts) {
/* 112 */       individualTarget.setDisabled(false);
/* 113 */       this.dao.storeIndividualTarget(individualTarget);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 122 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.targetmanager.individualtarget.pojo.DefaultIndividualTargetManager
 * JD-Core Version:    0.6.2
 */