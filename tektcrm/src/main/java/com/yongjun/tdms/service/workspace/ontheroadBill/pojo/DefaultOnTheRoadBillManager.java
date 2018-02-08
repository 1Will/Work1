/*    */ package com.yongjun.tdms.service.workspace.ontheroadBill.pojo;
import com.yongjun.pluto.dao.codevalue.CodeValueDao;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.ontheroadBill.OnTheRoadBillDao;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
/*    */ import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
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
/*    */   private final YongJunSequenceManager yongJunSequenceManager;
private final CodeValueDao codeValueDao;
private final ActivitFlowManager activitFlowManager;
/*    */   public DefaultOnTheRoadBillManager(OnTheRoadBillDao onTheRoadBillDao,YongJunSequenceManager yongJunSequenceManager,CodeValueDao codeValueDao,ActivitFlowManager activitFlowManager)
/*    */   {
/* 18 */     this.onTheRoadBillDao = onTheRoadBillDao;
             this.yongJunSequenceManager=yongJunSequenceManager;
             this.codeValueDao=codeValueDao;
             this.activitFlowManager=activitFlowManager;
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
	/**
	 * 第一步 保存请假单
	 */
	       
	       if((otrb.getIsSaved()!=null&&otrb.getIsSaved().equals("1")) || otrb.getIsSaved().equals("2")){
	    	   
	    	   List<CodeValue> bussnessCodeList;
			try {
				bussnessCodeList = this.codeValueDao.loadByKey("code", "02003");
				//预节点待审核状态
				if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
					   otrb.setStatus(bussnessCodeList.get(0));
				   }
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	       if(otrb.isNew()){
	   		otrb.setCode((String)this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_ROADBILL));
	   	    }
	       this.onTheRoadBillDao.storeOnTheRoadBill(otrb);
	       
	       /**
	        * 第二步 开启流程  会根据保存还是提交状态做不同的处理 
	        * 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批 
	        */
	       StartActiviti sActiviti = new StartActiviti();
	       sActiviti.setApplyPerson(otrb.getApplyPerson());
	       sActiviti.setBussnessId(otrb.getId());
	       sActiviti.setIsSaved(otrb.getIsSaved());
	       sActiviti.setContent(otrb.toString());
	       sActiviti.setCreatedTime(otrb.getCreateDate());
	       sActiviti.setFlow(otrb.getFlow());
	       sActiviti.setName("出货单("+otrb.getCode()+")");
	       sActiviti.setLinkHref("/onTheRoadBill/editOnTheRoadBill.html?activitiFLow=activitiFLow&onTheRoadBill.id="+otrb.getId());
	       //根据反射获取类名 
	       sActiviti.setBussnessType(otrb.getClass().getSimpleName());
	       
	       try {
			this.activitFlowManager.startAtiviti(sActiviti);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
public ActivitFlowManager getActivitFlowManager() {
	return activitFlowManager;
}

/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.ontheroadBill.pojo.DefaultOnTheRoadBillManager
 * JD-Core Version:    0.6.2
 */