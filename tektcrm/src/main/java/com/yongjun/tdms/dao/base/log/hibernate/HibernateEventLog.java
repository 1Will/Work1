/*    */ package com.yongjun.tdms.dao.base.log.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.base.log.EventLogDao;
/*    */ import com.yongjun.tdms.model.base.log.EventLog;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateEventLog extends BaseHibernateDao
/*    */   implements EventLogDao
/*    */ {
/*    */   public EventLog loadUserLog(Long userLogId)
/*    */   {
/* 35 */     return (EventLog)load(EventLog.class, userLogId);
/*    */   }
/*    */ 
/*    */   public List<EventLog> loadAllUserLog(Long[] userLogIds) {
/* 39 */     return loadAll(EventLog.class, userLogIds);
/*    */   }
/*    */ 
/*    */   public List<EventLog> loadAllUserLogs() {
/* 43 */     return loadAll(EventLog.class);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.log.hibernate.HibernateEventLog
 * JD-Core Version:    0.6.2
 */