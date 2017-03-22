/*    */ package com.yongjun.tdms.dao.workspace.warnning.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDetailDao;
/*    */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnningDetail;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateWorkWarnningDetail extends BaseHibernateDao
/*    */   implements WorkWarnningDetailDao
/*    */ {
/*    */   public void deleteAllWorkWarnningDetails(Collection<WorkWarnningDetail> workWarnningDetails)
/*    */   {
/* 48 */     deleteAll(workWarnningDetails);
/*    */   }
/*    */ 
/*    */   public void deleteWorkWarnningDetail(WorkWarnningDetail workWarnningDetail) {
/* 52 */     delete(workWarnningDetail);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadAllWorkWarnningDetails(Long[] workWarnningDetailIds)
/*    */   {
/* 57 */     return loadAll(WorkWarnningDetail.class, workWarnningDetailIds);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadAllWorkWarnningDetails() {
/* 61 */     return loadAll(WorkWarnningDetail.class);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 66 */     return loadByKey(WorkWarnningDetail.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 71 */     return loadByKeyArray(WorkWarnningDetail.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public WorkWarnningDetail loadWorkWarnningDetail(Long workWarnningDetailId) {
/* 75 */     return (WorkWarnningDetail)load(WorkWarnningDetail.class, workWarnningDetailId);
/*    */   }
/*    */ 
/*    */   public void storeWorkWarnningDetail(WorkWarnningDetail workWarnningDetail) {
/* 79 */     store(workWarnningDetail);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.warnning.hibernate.HibernateWorkWarnningDetail
 * JD-Core Version:    0.6.2
 */