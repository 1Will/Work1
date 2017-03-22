/*    */ package com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.personnelFiles.leavepersonnelfiles.LeavePersonDao;
/*    */ import com.yongjun.tdms.model.personnelFiles.leavepersonnelfiles.LeavePerson;
/*    */ import com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles.LeavePersonManager;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultLeavePersonManager extends BaseManager
/*    */   implements LeavePersonManager
/*    */ {
/*    */   private final LeavePersonDao leavePersonDao;
/*    */ 
/*    */   public DefaultLeavePersonManager(LeavePersonDao leavePersonDao)
/*    */   {
/* 28 */     this.leavePersonDao = leavePersonDao;
/*    */   }
/*    */ 
/*    */   public void storePersonnel(LeavePerson pf)
/*    */   {
/* 35 */     this.leavePersonDao.storePersonnel(pf);
/*    */   }
/*    */ 
/*    */   public void deletePersonnel(LeavePerson pf)
/*    */   {
/* 43 */     this.leavePersonDao.deletePersonnel(pf);
/*    */   }
/*    */ 
/*    */   public void deleteAllPersonnel(Collection<LeavePerson> pfs)
/*    */   {
/* 51 */     this.leavePersonDao.deleteAllPersonnel(pfs);
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadAllPersonnel(Long[] pfIds)
/*    */   {
/* 60 */     return this.leavePersonDao.loadAllPersonnel(pfIds);
/*    */   }
/*    */ 
/*    */   public LeavePerson loadPersonnel(Long pfId)
/*    */   {
/* 69 */     return this.leavePersonDao.loadPersonnel(pfId);
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadAllPersonnel()
/*    */   {
/* 77 */     return this.leavePersonDao.loadAllPersonnel();
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadByKey(String keyName, Object keyValue)
/*    */     throws DaoException
/*    */   {
/* 88 */     return this.leavePersonDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*    */     throws DaoException
/*    */   {
/* 99 */     return this.leavePersonDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles.pojo.DefaultLeavePersonManager
 * JD-Core Version:    0.6.2
 */