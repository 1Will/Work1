/*    */ package com.yongjun.tdms.dao.workspace.workingcycle.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.workspace.workingcycle.WorkingCycleDao;
/*    */ import com.yongjun.tdms.model.workspace.workingcycle.WorkingCycle;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateWorkingCycleDao extends BaseHibernateDao
/*    */   implements WorkingCycleDao
/*    */ {
/*    */   public WorkingCycle loadWorkingCycle(Long workingCycleId)
/*    */   {
/* 21 */     return (WorkingCycle)load(WorkingCycle.class, workingCycleId);
/*    */   }
/*    */ 
/*    */   public List<WorkingCycle> loadAllWorkingCycles()
/*    */   {
/* 28 */     return loadAll(WorkingCycle.class);
/*    */   }
/*    */ 
/*    */   public void storeWorkingCycle(WorkingCycle workingCycle)
/*    */   {
/* 36 */     store(workingCycle);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.workingcycle.hibernate.HibernateWorkingCycleDao
 * JD-Core Version:    0.6.2
 */