/*    */ package com.yongjun.tdms.dao.CustomerRelationship.stepInfo.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.CustomerRelationship.stepInfo.StepInfoDao;
/*    */ import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateStepInfo extends BaseHibernateDao
/*    */   implements StepInfoDao
/*    */ {
/*    */   public void deleteAllStepInfo(List<StepInfo> siIds)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void deleteStepInfo(StepInfo si)
/*    */   {
/*    */   }
/*    */ 
/*    */   public List<StepInfo> loadAllStepInfo()
/*    */   {
/* 20 */     return null;
/*    */   }
/*    */ 
/*    */   public List<StepInfo> loadAllStepInfo(Long[] siId) {
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */   public List<StepInfo> loadByKey(String key, Object value) throws DaoException {
/* 28 */     return null;
/*    */   }
/*    */   public StepInfo loadStepInfo(Long siId) {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   public void storeStepInfo(StepInfo si) {
/* 35 */     store(si);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.stepInfo.hibernate.HibernateStepInfo
 * JD-Core Version:    0.6.2
 */