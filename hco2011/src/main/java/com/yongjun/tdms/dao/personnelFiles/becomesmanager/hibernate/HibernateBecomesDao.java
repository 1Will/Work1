/*    */ package com.yongjun.tdms.dao.personnelFiles.becomesmanager.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.personnelFiles.becomesmanager.BecomesDao;
/*    */ import com.yongjun.tdms.model.personnelFiles.Becomes;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateBecomesDao extends BaseHibernateDao
/*    */   implements BecomesDao
/*    */ {
/*    */   public void storeBecomes(Becomes pf)
/*    */   {
/* 20 */     store(pf);
/*    */   }
/*    */ 
/*    */   public List<Becomes> loadAllBecomes(Long[] pfIds)
/*    */   {
/* 28 */     return loadAll(Becomes.class, pfIds);
/*    */   }
/*    */ 
/*    */   public Becomes loadBecomes(Long pfId)
/*    */   {
/* 36 */     return (Becomes)load(Becomes.class, pfId);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.becomesmanager.hibernate.HibernateBecomesDao
 * JD-Core Version:    0.6.2
 */