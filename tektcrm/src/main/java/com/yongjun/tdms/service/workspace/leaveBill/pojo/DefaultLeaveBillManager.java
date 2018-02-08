/*    */ package com.yongjun.tdms.service.workspace.leaveBill.pojo;
/*    */ 
import com.yongjun.pluto.dao.codevalue.CodeValueDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.workspace.leaveBill.LeaveBillDao;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
/*    */ import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
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
           private final ActivitFlowManager activitFlowManager;
           private final CodeValueDao codeValueDao;
           private final YongJunSequenceManager yongJunSequenceManager;
/*    */ 
/*    */   public DefaultLeaveBillManager(LeaveBillDao leaveBillDao,ActivitFlowManager activitFlowManager,CodeValueDao codeValueDao,YongJunSequenceManager yongJunSequenceManager)
/*    */   {
/* 18 */     this.leaveBillDao = leaveBillDao;
             this.activitFlowManager=activitFlowManager;
             this.codeValueDao=codeValueDao;
             this.yongJunSequenceManager=yongJunSequenceManager;
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
/*    */   public void storeLeaveBill(LeaveBill lb) throws DaoException {
	
	/**
	 * 第一步 保存请假单
	 */
	       
	       if((lb.getIsSaved()!=null&&lb.getIsSaved().equals("1")) || lb.getIsSaved().equals("2")){
	    	   
	    	   List<CodeValue> bussnessCodeList =this.codeValueDao.loadByKey("code", "02003");//预节点待审核状态
			   if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
				   lb.setStatus(bussnessCodeList.get(0));
			   }
	    	  
	       }
	       if (lb.isNew()) {
	    	   lb.setCode((String) this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_LEAVEBILL));
			}
	       this.leaveBillDao.storeLeaveBill(lb);
	       
	       /**
	        * 第二步 开启流程  会根据保存还是提交状态做不同的处理 
	        * 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批 
	        */
	       StartActiviti sActiviti = new StartActiviti();
	       sActiviti.setApplyPerson(lb.getApplyPerson());
	       sActiviti.setBussnessId(lb.getId());
	       sActiviti.setIsSaved(lb.getIsSaved());
	       sActiviti.setContent(lb.toString());
	       sActiviti.setCreatedTime(lb.getCreateDate());
	       sActiviti.setFlow(lb.getFlow());
	       sActiviti.setName("请假单("+lb.getCode()+")");
	       sActiviti.setLinkHref("/leaveBill/editLeaveBill.html?activitiFLow=activitiFLow&leaveBill.id="+lb.getId());
	       //根据反射获取类名 
	       sActiviti.setBussnessType(lb.getClass().getSimpleName());
	       
	       try {
			this.activitFlowManager.startAtiviti(sActiviti);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/* 68 */    
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