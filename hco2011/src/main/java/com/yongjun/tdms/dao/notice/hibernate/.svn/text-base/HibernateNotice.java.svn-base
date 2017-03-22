/*    */ package com.yongjun.tdms.dao.notice.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.notice.NoticeDao;
/*    */ import com.yongjun.tdms.model.notice.Notice;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateNotice extends BaseHibernateDao
/*    */   implements NoticeDao
/*    */ {
/*    */   public Notice loadNotice(Long NoticeId)
/*    */   {
/* 13 */     return (Notice)load(Notice.class, NoticeId);
/*    */   }
/*    */ 
/*    */   public List<Notice> loadNotices(Long[] NoticeIds)
/*    */   {
/* 18 */     return loadAll(Notice.class, NoticeIds);
/*    */   }
/*    */ 
/*    */   public void storeNotice(Notice notice) {
/* 22 */     store(notice);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.notice.hibernate.HibernateNotice
 * JD-Core Version:    0.6.2
 */