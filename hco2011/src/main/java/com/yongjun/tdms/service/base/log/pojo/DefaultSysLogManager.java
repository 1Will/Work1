/*    */ package com.yongjun.tdms.service.base.log.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.base.log.SysLogDao;
/*    */ import com.yongjun.tdms.model.base.log.SysLog;
/*    */ import com.yongjun.tdms.service.base.log.SysLogManager;
/*    */ 
/*    */ public class DefaultSysLogManager extends BaseManager
/*    */   implements SysLogManager
/*    */ {
/*    */   private final SysLogDao sysLogDao;
/*    */ 
/*    */   public DefaultSysLogManager(SysLogDao sysLogDao)
/*    */   {
/* 31 */     this.sysLogDao = sysLogDao;
/*    */   }
/*    */ 
/*    */   public void write(SysLog sysLog) {
/* 35 */     this.sysLogDao.storeLog(sysLog);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.log.pojo.DefaultSysLogManager
 * JD-Core Version:    0.6.2
 */