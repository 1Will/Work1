/*    */ package com.yongjun.tdms.service.workspace.leaveBill.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.leaveBill.LeaveBillDao;
/*    */ import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
/*    */ import com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultLeaveBillManager extends BaseManager
/*    */   implements LeaveBillManager
/*    */ {
/*    */   private final LeaveBillDao leaveBillDao;
		   private final YongJunSequenceManager yongJunSequenceManager;
			
/*    */ 
/*    */   public DefaultLeaveBillManager(LeaveBillDao leaveBillDao,YongJunSequenceManager yongJunSequenceManager)
/*    */   {
/* 18 */     this.leaveBillDao = leaveBillDao;
			 this.yongJunSequenceManager = yongJunSequenceManager;
/*    */   }
/*    */ 
/*    */   public void deleteAllLeaveBill(Collection<LeaveBill> lbs) {
/* 22 */     this.leaveBillDao.deleteAllLeaveBill(lbs);
/*    */   }
/*    */ 
/*    */   public void deleteLeaveBill(LeaveBill lb) {
/* 26 */     this.leaveBillDao.deleteLeaveBill(lb);
/*    */   }
/*    */ 
/*    */   public void disabledAllLeaveBills(List<LeaveBill> lbs) {
/* 30 */     for (LeaveBill l : lbs) {
/* 31 */       l.setDisabled(true);
/* 32 */       this.leaveBillDao.storeLeaveBill(l);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enabledAllLeaveBills(List<LeaveBill> lbs)
/*    */   {
/* 38 */     for (LeaveBill l : lbs) {
/* 39 */       l.setDisabled(false);
/* 40 */       this.leaveBillDao.storeLeaveBill(l);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadAllLeaveBill(Long[] lbIds)
/*    */   {
/* 46 */     return this.leaveBillDao.loadAllLeaveBill(lbIds);
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadAllLeaveBill() {
/* 50 */     return this.leaveBillDao.loadAllLeaveBill();
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 55 */     return this.leaveBillDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 60 */     return this.leaveBillDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public LeaveBill loadLeaveBill(Long lbId) {
/* 64 */     return this.leaveBillDao.loadLeaveBill(lbId);
/*    */   }
/*    */ 
/*    */   public void storeLeaveBill(LeaveBill lb) {
			 if(lb.isNew()){
				 lb.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_LEAVEBILL));
			 }
/* 68 */     this.leaveBillDao.storeLeaveBill(lb);
/*    */   }
/*    */ 
/*    */   public String getMaxPFCode(String code, Long orgId)
/*    */   {
/* 77 */     return this.leaveBillDao.getMaxPFCode(code, orgId);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.leaveBill.pojo.DefaultLeaveBillManager
 * JD-Core Version:    0.6.2
 */