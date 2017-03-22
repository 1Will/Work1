/*    */ package com.yongjun.tdms.service.workspace.warnning.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDetailDao;
/*    */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnningDetail;
/*    */ import com.yongjun.tdms.service.workspace.warnning.WorkWarnningDetailManager;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultWordWarnningDetailManager extends BaseManager
/*    */   implements WorkWarnningDetailManager
/*    */ {
/*    */   private WorkWarnningDetailDao workWarnningDetailDao;
/*    */ 
/*    */   public DefaultWordWarnningDetailManager(WorkWarnningDetailDao workWarnningDetailDao)
/*    */   {
/* 49 */     this.workWarnningDetailDao = workWarnningDetailDao;
/*    */   }
/*    */ 
/*    */   public void deleteAllWorkWarnningDetails(Collection<WorkWarnningDetail> workWarnningDetails) {
/* 53 */     this.workWarnningDetailDao.deleteAllWorkWarnningDetails(workWarnningDetails);
/*    */   }
/*    */ 
/*    */   public void deleteWorkWarnningDetail(WorkWarnningDetail workWarnningDetail) {
/* 57 */     this.workWarnningDetailDao.deleteWorkWarnningDetail(workWarnningDetail);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadAllWorkWarnningDetails(Long[] workWarnningDetailIds)
/*    */   {
/* 62 */     return this.workWarnningDetailDao.loadAllWorkWarnningDetails(workWarnningDetailIds);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadAllWorkWarnningDetails() {
/* 66 */     return this.workWarnningDetailDao.loadAllWorkWarnningDetails();
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 71 */     return this.workWarnningDetailDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnningDetail> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 76 */     return this.workWarnningDetailDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public WorkWarnningDetail loadWorkWarnningDetail(Long workWarnningDetailId) {
/* 80 */     return this.workWarnningDetailDao.loadWorkWarnningDetail(workWarnningDetailId);
/*    */   }
/*    */ 
/*    */   public void storeWorkWarnningDetail(WorkWarnningDetail workWarnningDetail) {
/* 84 */     this.workWarnningDetailDao.storeWorkWarnningDetail(workWarnningDetail);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.warnning.pojo.DefaultWordWarnningDetailManager
 * JD-Core Version:    0.6.2
 */