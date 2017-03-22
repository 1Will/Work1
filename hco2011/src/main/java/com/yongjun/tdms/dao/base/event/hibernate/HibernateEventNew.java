/*    */ package com.yongjun.tdms.dao.base.event.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.base.event.EventNewDao;
/*    */ import com.yongjun.tdms.model.base.event.EventNew;
/*    */ 
/*    */ public class HibernateEventNew extends BaseHibernateDao
/*    */   implements EventNewDao
/*    */ {
/*    */   public void storeEventNew(EventNew eventNew)
/*    */   {
/* 29 */     store(eventNew);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.event.hibernate.HibernateEventNew
 * JD-Core Version:    0.6.2
 */