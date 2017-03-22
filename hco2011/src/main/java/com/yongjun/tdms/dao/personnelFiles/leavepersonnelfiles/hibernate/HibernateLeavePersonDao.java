/*    */ package com.yongjun.tdms.dao.personnelFiles.leavepersonnelfiles.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.personnelFiles.leavepersonnelfiles.LeavePersonDao;
/*    */ import com.yongjun.tdms.model.personnelFiles.leavepersonnelfiles.LeavePerson;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateLeavePersonDao extends BaseHibernateDao
/*    */   implements LeavePersonDao
/*    */ {
/*    */   public void storePersonnel(LeavePerson pf)
/*    */   {
/* 23 */     store(pf);
/*    */   }
/*    */ 
/*    */   public void deletePersonnel(LeavePerson pf)
/*    */   {
/* 31 */     delete(pf);
/*    */   }
/*    */ 
/*    */   public void deleteAllPersonnel(Collection<LeavePerson> pfs)
/*    */   {
/* 39 */     deleteAll(pfs);
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadAllPersonnel(Long[] pfIds)
/*    */   {
/* 48 */     return loadAll(LeavePerson.class, pfIds);
/*    */   }
/*    */ 
/*    */   public LeavePerson loadPersonnel(Long pfId)
/*    */   {
/* 57 */     return (LeavePerson)load(LeavePerson.class, pfId);
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadAllPersonnel()
/*    */   {
/* 65 */     return loadAll(LeavePerson.class);
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadByKey(String keyName, Object keyValue)
/*    */     throws DaoException
/*    */   {
/* 76 */     return loadByKey(LeavePerson.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<LeavePerson> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*    */     throws DaoException
/*    */   {
/* 87 */     return loadByKeyArray(LeavePerson.class, keyNames, keyValues);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.leavepersonnelfiles.hibernate.HibernateLeavePersonDao
 * JD-Core Version:    0.6.2
 */