/*    */ package com.yongjun.tdms.service.workspace.overTimeBill.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.dao.codevalue.CodeValueDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.overTimeBill.OverTimeBillDao;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
/*    */ import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
/*    */ import com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultOverTimeBillManager extends BaseManager
/*    */   implements OverTimeBillManager
/*    */ {
/*    */   private final OverTimeBillDao overTimeBillDao;
           private final CodeValueDao codeValueDao;
           private final ActivitFlowManager activitFlowManager;
/*    */ 
/*    */   public DefaultOverTimeBillManager(OverTimeBillDao overTimeBillDao,CodeValueDao codeValueDao,ActivitFlowManager activitFlowManager)
/*    */   {
/* 18 */     this.overTimeBillDao = overTimeBillDao;
             this.codeValueDao=codeValueDao;
             this.activitFlowManager=activitFlowManager;
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
/**
 * 第一步保存加班单
 */
/*    */   public void storeOverTimeBill(OverTimeBill otb) {
	         try {
	        	 if(otb.getIsSaved()!=null&&otb.getIsSaved().equals("1")){
	  		  	   
	  		  	   List<CodeValue> bussnessCodeList=this.codeValueDao.loadByKey("code", "02003");
	  			
	  			 if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
	  				 otb.setStatus(bussnessCodeList.get(0));
	  			 }
	  			 }
	        	 
	        	 this.overTimeBillDao.storeOverTimeBill(otb);
	        	 /**
			        * 第二步 开启流程  会根据保存还是提交状态做不同的处理 
			        * 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批 
			        */
			       StartActiviti sActiviti = new StartActiviti();
			       sActiviti.setApplyPerson(otb.getApplyPerson());
			       sActiviti.setBussnessId(otb.getId());
			       sActiviti.setIsSaved(otb.getIsSaved());
			       sActiviti.setContent(otb.toString());
			       sActiviti.setCreatedTime(otb.getCreatedTime());
			       sActiviti.setFlow(otb.getFlow());
			       sActiviti.setName("加班单审批");
			       sActiviti.setLinkHref("/overTimeBill/editOverTimeBill.html?activitiFLow=activitiFLow&overTimeBill.id="+otb.getId());
			       //根据反射获取类名 
			       sActiviti.setBussnessType(otb.getClass().getSimpleName());
			       this.activitFlowManager.startAtiviti(sActiviti);
	        	 
	        	 
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
	
/* 52 */    
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