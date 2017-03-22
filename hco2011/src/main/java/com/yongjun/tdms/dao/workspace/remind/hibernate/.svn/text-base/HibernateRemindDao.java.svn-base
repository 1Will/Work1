/*    */ package com.yongjun.tdms.dao.workspace.remind.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.workspace.remind.RemindDao;
/*    */ import com.yongjun.tdms.model.workspace.warnning.Remind;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateRemindDao extends BaseHibernateDao
/*    */   implements RemindDao
/*    */ {
/*    */   public Remind loadRemind(Long remindId)
/*    */   {
/* 47 */     return (Remind)load(Remind.class, remindId);
/*    */   }
/*    */ 
/*    */   public List<Remind> loadReminds(Long[] remindIds) {
/* 51 */     return loadAll(Remind.class, remindIds);
/*    */   }
/*    */ 
/*    */   public void storeRemind(Remind remind) {
/* 55 */     store(remind);
/*    */   }
/*    */ 
/*    */   public void deleteAllReminds(Collection<Remind> Reminds) {
/* 59 */     deleteAll(Reminds);
/*    */   }
/*    */ 
/*    */   public void deleteRemind(Remind remind) {
/* 63 */     delete(remind);
/*    */   }
/*    */ 
/*    */   public List<Remind> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 67 */     return loadByKey(Remind.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<Remind> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 71 */     return loadByKeyArray(Remind.class, keyNames, keyValues);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.remind.hibernate.HibernateRemindDao
 * JD-Core Version:    0.6.2
 */