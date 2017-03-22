/*    */ package com.yongjun.tdms.dao.workspace.warnning.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDao;
/*    */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class HibernateWorkWarnning extends BaseHibernateDao
/*    */   implements WorkWarnningDao
/*    */ {
/*    */   public WorkWarnning loadWorkWarnning(Long workWarnningId)
/*    */   {
/* 47 */     return (WorkWarnning)load(WorkWarnning.class, workWarnningId);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnning> loadAllWorkWarnnings(Long[] workWarnningIds) {
/* 51 */     return loadAll(WorkWarnning.class, workWarnningIds);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnning> loadAllWorkWarnnings() {
/* 55 */     return loadAll(WorkWarnning.class);
/*    */   }
/*    */ 
/*    */   public void storeWorkWarnning(WorkWarnning workWarnning) {
/* 59 */     store(workWarnning);
/*    */   }
/*    */ 
/*    */   public void deleteWorkWarnning(WorkWarnning workWarnning) {
/* 63 */     delete(workWarnning);
/*    */   }
/*    */ 
/*    */   public void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
/* 67 */     deleteAll(workWarnnings);
/*    */   }
/*    */ 
/*    */   public Integer GetNumberOfUnReadWarnningByUserID(final Long userId)
/*    */   {
/* 72 */     return (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 76 */         return session.getNamedQuery("GetNumberOfUnReadWarnningByUserID").setParameter("userId", userId).uniqueResult();
/*    */       }
/*    */     });
/*    */   }
/*    */ 
/*    */   public List<WorkWarnning> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 82 */     return loadByKeyArray(WorkWarnning.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public List<WorkWarnning> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 86 */     return loadByKey(WorkWarnning.class, keyName, keyValue);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.warnning.hibernate.HibernateWorkWarnning
 * JD-Core Version:    0.6.2
 */