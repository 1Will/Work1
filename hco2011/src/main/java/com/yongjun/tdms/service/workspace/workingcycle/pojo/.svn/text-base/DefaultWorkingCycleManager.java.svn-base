/*    */ package com.yongjun.tdms.service.workspace.workingcycle.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.workingcycle.WorkingCycleDao;
/*    */ import com.yongjun.tdms.model.workspace.workingcycle.WorkingCycle;
/*    */ import com.yongjun.tdms.service.workspace.workingcycle.WorkingCycleManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultWorkingCycleManager extends BaseManager
/*    */   implements WorkingCycleManager
/*    */ {
/*    */   private final WorkingCycleDao workingCycleDao;
/*    */ 
/*    */   public DefaultWorkingCycleManager(WorkingCycleDao workingCycleDao)
/*    */   {
/* 26 */     this.workingCycleDao = workingCycleDao;
/*    */   }
/*    */ 
/*    */   public WorkingCycle loadWorkingCycle(Long workingCycleId)
/*    */   {
/* 34 */     return this.workingCycleDao.loadWorkingCycle(workingCycleId);
/*    */   }
/*    */ 
/*    */   public List<WorkingCycle> loadAllWorkingCycles()
/*    */   {
/* 41 */     return this.workingCycleDao.loadAllWorkingCycles();
/*    */   }
/*    */ 
/*    */   public void storeWorkingCycle(WorkingCycle workingCycle)
/*    */   {
/* 49 */     this.workingCycleDao.storeWorkingCycle(workingCycle);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.workingcycle.pojo.DefaultWorkingCycleManager
 * JD-Core Version:    0.6.2
 */