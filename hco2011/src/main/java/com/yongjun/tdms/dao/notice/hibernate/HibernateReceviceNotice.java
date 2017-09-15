/*    */ package com.yongjun.tdms.dao.notice.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*    */ import com.yongjun.tdms.dao.notice.ReceviceNoticeDao;
/*    */ import com.yongjun.tdms.model.notice.ReceviceNotice;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class HibernateReceviceNotice extends BaseHibernateDao
/*    */   implements ReceviceNoticeDao
/*    */ {
/*    */   public List<ReceviceNotice> loadAllReceviceNotices()
/*    */   {
/* 21 */     return loadAll(ReceviceNotice.class);
/*    */   }
/*    */ 
/*    */   public ReceviceNotice loadReceviceNotice(Long sendNoticeId) {
/* 25 */     return (ReceviceNotice)load(ReceviceNotice.class, sendNoticeId);
/*    */   }
/*    */ 
/*    */   public List<ReceviceNotice> loadReceviceNotices(Long[] receviceNoticeIds) {
/* 29 */     return loadAll(ReceviceNotice.class, receviceNoticeIds);
/*    */   }
/*    */ 
/*    */   public void storeReceviceNotice(ReceviceNotice notice) {
/* 33 */     store(notice);
/*    */   }
/*    */ 
/*    */   public void deleteReceviceNotice(ReceviceNotice notice) {
/* 37 */     delete(notice);
/*    */   }
/*    */ 
/*    */   public void deleteReceviceNotices(Collection<ReceviceNotice> notices) {
/* 41 */     deleteAll(notices);
/*    */   }
/*    */ 
/*    */   public Integer getNumberOfUnReadNoticByUserID(final Long userId, final CodeValue c)
/*    */   {
/* 46 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 50 */         return session.getNamedQuery("GetNumberOfUnReadNoticByUserID").setParameter("userId", userId).setParameter("c", c).uniqueResult();
/*    */       }
/*    */     });
/*    */   }
/*    */ 
/*    */   public List<ReceviceNotice> getAllUnReadNoticByUserID(final Long userId, final Date validityDate)
/*    */   {
/* 60 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 64 */         return session.getNamedQuery("GetAllUnReadNoticByUserID").setParameter("userId", userId).setParameter("validityDate", validityDate).list();
/*    */       }
/*    */     });
/*    */   }

/*    */   public List<ReceviceNotice> getAllNoticByUserID(final Long userId)
/*    */   {
/* 60 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 64 */         return session.getNamedQuery("GetAllNoticByUserID").setParameter("userId", userId).list();
/*    */       }
/*    */     });
/*    */   }
/*    */ 
/*    */   public List<ReceviceNotice> loadByKey(String keyName, Object keyValue)
/*    */     throws DaoException
/*    */   {
/* 73 */     return loadByKey(ReceviceNotice.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ReceviceNotice> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 77 */     return loadByKeyArray(ReceviceNotice.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public Integer getAllNumberOfUnReadNoticByUserID(final Long userId) {
/* 81 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 85 */         return Integer.valueOf(session.getNamedQuery("GetAllUnReadNoticByUserIDTwo").setParameter("userId", userId).list().size());
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.notice.hibernate.HibernateReceviceNotice
 * JD-Core Version:    0.6.2
 */