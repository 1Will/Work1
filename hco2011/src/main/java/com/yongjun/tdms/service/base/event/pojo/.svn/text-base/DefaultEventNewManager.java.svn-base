/*    */ package com.yongjun.tdms.service.base.event.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.base.event.EventNewDao;
/*    */ import com.yongjun.tdms.model.base.event.EventNew;
/*    */ import com.yongjun.tdms.service.base.event.EventNewManager;
/*    */ 
/*    */ public class DefaultEventNewManager extends BaseManager
/*    */   implements EventNewManager
/*    */ {
/*    */   private EventNewDao eventNewDao;
/*    */ 
/*    */   public void storeEventNew(EventNew eventNew)
/*    */   {
/* 19 */     this.eventNewDao.storeEventNew(eventNew);
/*    */   }
/*    */   public void setEventNewDao(EventNewDao eventNewDao) {
/* 22 */     this.eventNewDao = eventNewDao;
/*    */   }
			public DefaultEventNewManager(EventNewDao eventNewDao) {
					this.eventNewDao = eventNewDao;
			}

/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.event.pojo.DefaultEventNewManager
 * JD-Core Version:    0.6.2
 */