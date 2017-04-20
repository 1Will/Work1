/*    */ package com.yongjun.tdms.service.backvisit.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
/*    */ import com.yongjun.tdms.dao.CustomerRelationship.stepInfo.StepInfoDao;
/*    */ import com.yongjun.tdms.dao.backvisit.BackVisitDao;
/*    */ import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
/*    */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*    */ import com.yongjun.tdms.service.backvisit.BackVisitManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultBackVisitManager extends BaseManager
/*    */   implements BackVisitManager
/*    */ {
/*    */   private BackVisitDao backVisitDao;
/*    */   private StepInfoDao stepInfoDao;
		   private UserManager userManager;
/*    */ 
/*    */   public void deleteAllBackVisit(Collection<BackVisit> backVisits)
/*    */   {
/* 28 */     this.backVisitDao.deleteAllBackVisit(backVisits);
/*    */   }
/*    */ 
/*    */   public void deleteBackVisit(BackVisit backVisit) {
/* 32 */     this.backVisitDao.deleteBackVisit(backVisit);
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadAllBackVisit(Long[] backVisitIds) {
/* 36 */     return this.backVisitDao.loadAllBackVisit(backVisitIds);
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadAllBackVisits() {
/* 40 */     return this.backVisitDao.loadAllBackVisits();
/*    */   }
/*    */ 
/*    */   public BackVisit loadBackVisit(Long backVisitId) {
/* 44 */     return this.backVisitDao.loadBackVisit(backVisitId);
/*    */   }
/*    */ 
/*    */   public void storeBackVisit(BackVisit backVisit) {
/* 48 */     this.backVisitDao.storeBackVisit(backVisit);
/*    */   }
/*    */ 
/*    */   public void setBackVisitDao(BackVisitDao backVisitDao) {
/* 52 */     this.backVisitDao = backVisitDao;
/*    */   }
/*    */ 
/*    */   public void disabledBackVisits(List<BackVisit> backVisits) {
/* 56 */     for (BackVisit bv : backVisits) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.backVisitDao.storeBackVisit(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableBackVisits(List<BackVisit> backVisits) {
/* 63 */     for (BackVisit bv : backVisits) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.backVisitDao.storeBackVisit(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.backVisitDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<BackVisit> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.backVisitDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public void storeStepInfo(StepInfo stepInfo) {
/* 78 */     this.stepInfoDao.storeStepInfo(stepInfo);
/*    */   }
/*    */ 
/*    */   public StepInfoDao getStepInfoDao()
/*    */   {
/* 85 */     return this.stepInfoDao;
/*    */   }
/*    */ 
/*    */   public void setStepInfoDao(StepInfoDao stepInfoDao)
/*    */   {
/* 92 */     this.stepInfoDao = stepInfoDao;
/*    */   }
			public UserManager getUserManager() {
				return userManager;
			}
			public void setUserManager(UserManager userManager) {
				this.userManager = userManager;
			}
/*     */   public List<BackVisit> getBackVisitByDate(String da) throws DaoException, ParseException{
				java.util.Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(da);
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS"); 
				String date = formatter.format(newDate);
				User user = userManager.getUser();
				String keyNames[]={"backVisitDate","employee.name"};
				Object[] keyValues={date,user.getName()};
				List<BackVisit> list = this.backVisitDao.loadByKeyArray(keyNames, keyValues);
				return list;
			}
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.backvisit.pojo.DefaultBackVisitManager
 * JD-Core Version:    0.6.2
 */