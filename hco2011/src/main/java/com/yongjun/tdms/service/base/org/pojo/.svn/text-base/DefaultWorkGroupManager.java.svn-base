/*    */ package com.yongjun.tdms.service.base.org.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.base.org.WorkGroupDao;
/*    */ import com.yongjun.tdms.model.base.org.WorkGroup;
/*    */ import com.yongjun.tdms.service.base.org.WorkGroupManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultWorkGroupManager extends BaseManager
/*    */   implements WorkGroupManager
/*    */ {
/*    */   private final WorkGroupDao workGroupDao;
/*    */ 
/*    */   public DefaultWorkGroupManager(WorkGroupDao workGroupDao)
/*    */   {
/* 34 */     this.workGroupDao = workGroupDao;
/*    */   }
/*    */ 
/*    */   public List<WorkGroup> loadAllWorkGroups() {
/* 38 */     return this.workGroupDao.loadAllWorkGroups();
/*    */   }
/*    */ 
/*    */   public List<WorkGroup> createSelectWorkGroups(String label) {
/* 42 */     WorkGroup w = new WorkGroup();
/* 43 */     w.setId(Long.valueOf(-1L));
/* 44 */     w.setName(label);
/* 45 */     List tmp = loadAllWorkGroups();
/* 46 */     tmp.add(0, w);
/* 47 */     return tmp;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.org.pojo.DefaultWorkGroupManager
 * JD-Core Version:    0.6.2
 */