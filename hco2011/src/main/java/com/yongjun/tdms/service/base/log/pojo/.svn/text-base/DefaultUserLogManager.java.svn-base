/*    */ package com.yongjun.tdms.service.base.log.pojo;
/*    */ 
/*    */ import com.yongjun.tdms.dao.base.log.EventLogDao;
/*    */ import com.yongjun.tdms.model.base.log.EventLog;
/*    */ import com.yongjun.tdms.service.base.log.UserLogManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultUserLogManager
/*    */   implements UserLogManager
/*    */ {
/*    */   private final EventLogDao eventLogDao;
/*    */ 
/*    */   public DefaultUserLogManager(EventLogDao eventLogDao)
/*    */   {
/* 23 */     this.eventLogDao = eventLogDao;
/*    */   }
/*    */ 
/*    */   public EventLog loadUserLog(Long userLogId) {
/* 27 */     return this.eventLogDao.loadUserLog(userLogId);
/*    */   }
/*    */ 
/*    */   public List<EventLog> loadAllUserLog(Long[] userLogIds) {
/* 31 */     return this.eventLogDao.loadAllUserLog(userLogIds);
/*    */   }
/*    */ 
/*    */   public List<EventLog> loadAllUserLogs() {
/* 35 */     return this.eventLogDao.loadAllUserLogs();
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.log.pojo.DefaultUserLogManager
 * JD-Core Version:    0.6.2
 */