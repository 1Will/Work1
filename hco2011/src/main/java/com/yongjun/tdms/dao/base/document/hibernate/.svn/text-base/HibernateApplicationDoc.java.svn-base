/*    */ package com.yongjun.tdms.dao.base.document.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.base.document.ApplicationDocDao;
/*    */ import com.yongjun.tdms.model.base.document.ApplicationDoc;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class HibernateApplicationDoc extends BaseHibernateDao
/*    */   implements ApplicationDocDao
/*    */ {
/*    */   public ApplicationDoc loadApplicationDoc(Long applicationDocId)
/*    */   {
/* 20 */     return (ApplicationDoc)load(ApplicationDoc.class, applicationDocId);
/*    */   }
/*    */ 
/*    */   public List<ApplicationDoc> loadAllApplicationDocs(Long[] applicationDocIds) {
/* 24 */     return loadAll(ApplicationDoc.class, applicationDocIds);
/*    */   }
/*    */ 
/*    */   public List<ApplicationDoc> loadAllApplicationDocs() {
/* 28 */     return loadAll(ApplicationDoc.class);
/*    */   }
/*    */ 
/*    */   public void storeApplicationDoc(ApplicationDoc applicationDoc) {
/* 32 */     store(applicationDoc);
/*    */   }
/*    */ 
/*    */   public void deleteApplicationDoc(ApplicationDoc applicationDoc) {
/* 36 */     delete(applicationDoc);
/*    */   }
/*    */ 
/*    */   public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs) {
/* 40 */     deleteAll(applicationDocs);
/*    */   }
/*    */ 
/*    */   public Integer getNumberOfManualDoc()
/*    */   {
/* 45 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 49 */         return session.getNamedQuery("GetNumberOfManualDoc").uniqueResult();
/*    */       }
/*    */     });
/*    */   }
/*    */ 
/*    */   public List<ApplicationDoc> getAllManualDoc()
/*    */   {
/* 58 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 62 */         return session.getNamedQuery("GetAllManualDoc").list();
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.document.hibernate.HibernateApplicationDoc
 * JD-Core Version:    0.6.2
 */