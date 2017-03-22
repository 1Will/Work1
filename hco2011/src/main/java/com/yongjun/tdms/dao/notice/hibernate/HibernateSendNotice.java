/*    */ package com.yongjun.tdms.dao.notice.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.notice.SendNoticeDao;
/*    */ import com.yongjun.tdms.model.notice.SendNotice;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateSendNotice extends BaseHibernateDao
/*    */   implements SendNoticeDao
/*    */ {
/*    */   public SendNotice loadSendNotice(Long sendNoticeId)
/*    */   {
/* 13 */     return (SendNotice)load(SendNotice.class, sendNoticeId);
/*    */   }
/*    */ 
/*    */   public List<SendNotice> loadSendNotices(Long[] sendNoticeIds) {
/* 17 */     return loadAll(SendNotice.class, sendNoticeIds);
/*    */   }
/*    */ 
/*    */   public void storeSendNotice(SendNotice notice) {
/* 21 */     store(notice);
/*    */   }
/*    */ 
/*    */   public void deleteSendNotice(SendNotice notice) {
/* 25 */     delete(notice);
/*    */   }
/*    */ 
/*    */   public void deleteSendNotices(Collection<SendNotice> notices) {
/* 29 */     deleteAll(notices);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.notice.hibernate.HibernateSendNotice
 * JD-Core Version:    0.6.2
 */