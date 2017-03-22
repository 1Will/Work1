/*    */ package com.yongjun.tdms.service.personnelFiles.becomesmanager.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.personnelFiles.becomesmanager.BecomesDao;
/*    */ import com.yongjun.tdms.model.personnelFiles.Becomes;
/*    */ import com.yongjun.tdms.service.personnelFiles.becomesmanager.BecomesManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultBecomesManager extends BaseManager
/*    */   implements BecomesManager
/*    */ {
/*    */   private final BecomesDao becomesDao;
/*    */ 
/*    */   public DefaultBecomesManager(BecomesDao becomesDao)
/*    */   {
/* 25 */     this.becomesDao = becomesDao;
/*    */   }
/*    */ 
/*    */   public void storeBecomes(Becomes pf)
/*    */   {
/* 32 */     this.becomesDao.storeBecomes(pf);
/*    */   }
/*    */ 
/*    */   public List<Becomes> loadAllBecomes(Long[] pfIds)
/*    */   {
/* 40 */     return this.becomesDao.loadAllBecomes(pfIds);
/*    */   }
/*    */ 
/*    */   public Becomes loadBecomes(Long pfId)
/*    */   {
/* 49 */     return this.becomesDao.loadBecomes(pfId);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.becomesmanager.pojo.DefaultBecomesManager
 * JD-Core Version:    0.6.2
 */