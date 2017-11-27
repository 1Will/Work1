/*    */ package com.yongjun.tdms.service.workspace.overTimeBill.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.overTimeBill.OverTimeBillDao;
/*    */ import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
/*    */ import com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultOverTimeBillManager extends BaseManager
/*    */   implements OverTimeBillManager
/*    */ {
/*    */   private final OverTimeBillDao overTimeBillDao;
		   private final YongJunSequenceManager yongJunSequenceManager;
/*    */ 
/*    */   public DefaultOverTimeBillManager(OverTimeBillDao overTimeBillDao,YongJunSequenceManager yongJunSequenceManager)
/*    */   {
/* 18 */     this.overTimeBillDao = overTimeBillDao;
			 this.yongJunSequenceManager = yongJunSequenceManager;
/*    */   }
/*    */ 
/*    */   public void deleteAllOverTimeBill(Collection<OverTimeBill> otbs) {
/* 22 */     this.overTimeBillDao.deleteAllOverTimeBill(otbs);
/*    */   }
/*    */ 
/*    */   public void deleteOverTimeBill(OverTimeBill otb) {
/* 26 */     this.overTimeBillDao.deleteOverTimeBill(otb);
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadAllOverTimeBill(Long[] otbIds) {
/* 30 */     return this.overTimeBillDao.loadAllOverTimeBill(otbIds);
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadAllOverTimeBill() {
/* 34 */     return this.overTimeBillDao.loadAllOverTimeBill();
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 39 */     return this.overTimeBillDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 44 */     return this.overTimeBillDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public OverTimeBill loadOverTimeBill(Long otbId) {
/* 48 */     return this.overTimeBillDao.loadOverTimeBill(otbId);
/*    */   }
/*    */ 
/*    */   public void storeOverTimeBill(OverTimeBill otb) {
			 if(otb.isNew()){
				 otb.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_OVERTIMEBILL));
			 }
/* 52 */     this.overTimeBillDao.storeOverTimeBill(otb);
/*    */   }
/*    */ 
/*    */   public void disabledAllOverTimeBills(List<OverTimeBill> otbs) {
/* 56 */     for (OverTimeBill o : otbs) {
/* 57 */       o.setDisabled(true);
/* 58 */       this.overTimeBillDao.storeOverTimeBill(o);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enabledAllOverTimeBills(List<OverTimeBill> otbs) {
/* 63 */     for (OverTimeBill o : otbs) {
/* 64 */       o.setDisabled(false);
/* 65 */       this.overTimeBillDao.storeOverTimeBill(o);
/*    */     }
/*    */   }
/*    */ 
/*    */   public String getMaxPFCode(String code, Long orgId)
/*    */   {
/* 75 */     return this.overTimeBillDao.getMaxPFCode(code, orgId);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.overTimeBill.pojo.DefaultOverTimeBillManager
 * JD-Core Version:    0.6.2
 */