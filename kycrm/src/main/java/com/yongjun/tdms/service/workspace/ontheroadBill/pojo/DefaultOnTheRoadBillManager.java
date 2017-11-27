/*    */ package com.yongjun.tdms.service.workspace.ontheroadBill.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.ontheroadBill.OnTheRoadBillDao;
/*    */ import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
/*    */ import com.yongjun.tdms.service.workspace.ontheroadBill.OnTheRoadBillManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultOnTheRoadBillManager extends BaseManager
/*    */   implements OnTheRoadBillManager
/*    */ {
/*    */   private final OnTheRoadBillDao onTheRoadBillDao;
		   private final YongJunSequenceManager yongJunSequenceManager; 	
/*    */ 
/*    */   public DefaultOnTheRoadBillManager(OnTheRoadBillDao onTheRoadBillDao,YongJunSequenceManager yongJunSequenceManager)
/*    */   {
/* 18 */     this.onTheRoadBillDao = onTheRoadBillDao;
			 this.yongJunSequenceManager = yongJunSequenceManager;
/*    */   }
/*    */   public void deleteAllOnTheRoadBill(Collection<OnTheRoadBill> otrbs) {
/* 21 */     this.onTheRoadBillDao.deleteAllOnTheRoadBill(otrbs);
/*    */   }
/*    */ 
/*    */   public void deleteOnTheRoadBill(OnTheRoadBill otrb) {
/* 25 */     this.onTheRoadBillDao.deleteOnTheRoadBill(otrb);
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadAllOnTheRoadBill(Long[] otrbIds) {
/* 29 */     return this.onTheRoadBillDao.loadAllOnTheRoadBill(otrbIds);
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadAllOnTheRoadBill() {
/* 33 */     return this.onTheRoadBillDao.loadAllOnTheRoadBill();
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 38 */     return this.onTheRoadBillDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 43 */     return this.onTheRoadBillDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public OnTheRoadBill loadOnTheRoadBill(Long otrbId) {
/* 47 */     return this.onTheRoadBillDao.loadOnTheRoadBill(otrbId);
/*    */   }
/*    */ 
/*    */   public void storeOnTheRoadBill(OnTheRoadBill otrb) {
			if(otrb.isNew()){
				otrb.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_ROADBILL));
			}
/* 51*/     this.onTheRoadBillDao.storeOnTheRoadBill(otrb);
/*    */   }
/*    */   public void disabledAllOnTheRoadBills(List<OnTheRoadBill> otrbs) {
/* 54 */     for (OnTheRoadBill o : otrbs) {
/* 55 */       o.setDisabled(true);
/* 56 */       this.onTheRoadBillDao.storeOnTheRoadBill(o);
/*    */     }
/*    */   }
/*    */ 
/* 60 */   public void enabledAllOnTheRoadBills(List<OnTheRoadBill> otrbs) { for (OnTheRoadBill o : otrbs) {
/* 61 */       o.setDisabled(false);
/* 62 */       this.onTheRoadBillDao.storeOnTheRoadBill(o);
/*    */     }
/*    */   }
/*    */ 
/*    */   public String getMaxPFCode(String code, Long orgId)
/*    */   {
/* 72 */     return this.onTheRoadBillDao.getMaxPFCode(code, orgId);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.ontheroadBill.pojo.DefaultOnTheRoadBillManager
 * JD-Core Version:    0.6.2
 */