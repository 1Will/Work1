/*    */ package com.yongjun.tdms.service.workspace.remind.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.remind.RemindDao;
/*    */ import com.yongjun.tdms.model.workspace.warnning.Remind;
/*    */ import com.yongjun.tdms.service.workspace.remind.RemindManager;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultRemindManager extends BaseManager
/*    */   implements RemindManager
/*    */ {
/*    */   private RemindDao remindDao;
/*    */ 
/*    */   public DefaultRemindManager(RemindDao remindDao)
/*    */   {
/* 49 */     this.remindDao = remindDao;
/*    */   }
/*    */   public void deleteAllReminds(Collection<Remind> Reminds) {
/* 52 */     this.remindDao.deleteAllReminds(Reminds);
/*    */   }
/*    */   public void deleteRemind(Remind remind) {
/* 55 */     this.remindDao.deleteRemind(remind);
/*    */   }
/*    */   public List<Remind> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 58 */     return this.remindDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */   public List<Remind> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 61 */     return this.remindDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */   public Remind loadRemind(Long remindId) {
/* 64 */     return this.remindDao.loadRemind(remindId);
/*    */   }
/*    */   public List<Remind> loadReminds(Long[] remindIds) {
/* 67 */     return this.remindDao.loadReminds(remindIds);
/*    */   }
/*    */   public void storeRemind(Remind remind) {
/* 70 */     this.remindDao.storeRemind(remind);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.remind.pojo.DefaultRemindManager
 * JD-Core Version:    0.6.2
 */