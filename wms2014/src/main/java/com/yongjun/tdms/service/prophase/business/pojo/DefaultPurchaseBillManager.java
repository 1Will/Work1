/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.prophase.business.pojo;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDetailDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.dao.prophase.business.ToolingPurchaseBillDetailViewDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.business.PayDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillStatus;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.model.prophase.business.ToolingPurchaseBillDetailView;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;

/**
 * @author qs
 * @version $Id: DefaultPurchaseBillManager.java 11328 2008-03-15 09:39:30Z mwei $
 */
public class DefaultPurchaseBillManager extends BaseManager implements PurchaseBillManager{
	private  final PurchaseBillDao purchaseBilldao;
	private final SequenceManager sequenceManager;
	private  final SubscribeDao subscribeDao;
	private final PurchaseBillDetailDao purchaseBillDetailDao;
	private final SubscribeManager subscribeManager;
	private final ToolingPurchaseBillDetailViewDao toolingPurchaseBillDetailViewDao;
	private BudgetDetailManager budgetDetailManager;
	
	public  DefaultPurchaseBillManager(PurchaseBillDao purchaseBilldao,SequenceManager sequenceManager,
			SubscribeDao subscribeDao,PurchaseBillDetailDao purchaseBillDetailDao, SubscribeManager subscribeManager,
			ToolingPurchaseBillDetailViewDao toolingPurchaseBillDetailViewDao){
		 this.purchaseBilldao=purchaseBilldao;
		 this.sequenceManager=sequenceManager;
		 this.subscribeDao=subscribeDao;
		 this.purchaseBillDetailDao=purchaseBillDetailDao;
		 this.subscribeManager=subscribeManager;
		 this.toolingPurchaseBillDetailViewDao = toolingPurchaseBillDetailViewDao;
		
	}
	public PurchaseBill loadPurchaseBill(Long purchasId) {
		return purchaseBilldao.loadPurchaseBill(purchasId);
	}

	public List<PurchaseBill> loadPurchaseBills(Long[] PurchaseBillIds) {
		
		return purchaseBilldao.loadPurchaseBills(PurchaseBillIds);
	}


	public void storePurchaseBill(PurchaseBill purchaseBill) {
		if (purchaseBill.isNew()) {
			//String billNo = (String) sequenceManager.generate("-");
			String billNo = generateNo(purchaseBill);		//生成采购单编码
			purchaseBill.setBillNo(billNo);
		}
	   updatePurchaseBillInformation(purchaseBill);//累加合同总金额的update方法
	   updatePurchaseBillPayInformation(purchaseBill);//累加已付金额的方法
	   this.purchaseBilldao.storePurchaseBill(purchaseBill);
	   if(purchaseBill.getToolingDevFlag().equals(SysModel.DEVICE)){
			//如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
			if (null != purchaseBill.getBudgetNo()) {
				this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
				//如果已付金额存在，则从预算中已发生费用加上已付金额
				if (null != purchaseBill.getAlreadyPayOut()) {
					this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(purchaseBill.getBudgetNo(),purchaseBill.getAlreadyPayOut());
				}
			}
	   }else{
		   if (null != purchaseBill.getAlreadyPayOut()) {
				this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(purchaseBill.getBudgetNo(),purchaseBill.getAlreadyPayOut());
			}
	   }
	
	}
	
	/**
	 * 生成采购单编码
	 * @param purchaseBill   采购单
	 * @return String 采购单编码
	 */
	public String generateNo(PurchaseBill purchaseBill){
		StringBuffer billCode=new StringBuffer();
		Calendar c = Calendar.getInstance();
		String cYear = String.valueOf(c.get(Calendar.YEAR));
		String cMonth = String.valueOf(c.get(Calendar.MONTH)+1);
		if(cMonth.length()==1){cMonth = "0"+cMonth;}
		
		billCode.append(cYear).append("-").append(cMonth);
		
		String maxBillNo = purchaseBilldao.GetMaxBillNoByBillCode(billCode+"%");
		if(purchaseBill != null){
			billCode.append("-").append(purchaseBill.getSupplier().getSupplierNo().substring(9));
		}
		
		String pattern = "000";
		Format formatter = NumberFormat.getIntegerInstance();
		formatter = new DecimalFormat(pattern);
		if(null == maxBillNo){
			Long n = NumberUtils.createLong(pattern) + 1L;
			return billCode.append("-").append(formatter.format(n)).toString();
		}
		int yearIndex = maxBillNo.indexOf("-");
		String year = maxBillNo.substring(0,yearIndex);
		String subMonth= maxBillNo.substring(maxBillNo.indexOf("-")+1);
		String month = subMonth.substring(0,subMonth.indexOf("-"));
		String billNoSequenceCode="";
		Long n=0L;
		if(cYear.equals(year)){
			if(cMonth.equals(month)){													//同年同月的数据
				billNoSequenceCode = maxBillNo.substring(maxBillNo.lastIndexOf("-")+1);
				n = NumberUtils.createLong(billNoSequenceCode) + 1L;
			}else{																		//同年不同月的数据
				n = NumberUtils.createLong(pattern) + 1L;
			}
		}else{																		    //不同年的数据
			n = NumberUtils.createLong(pattern) + 1L;
		}
		
		return billCode.append("-").append(formatter.format(n)).toString();
	}
	
	public void updatePurchaseBillInformation(PurchaseBill purchaseBill){//具体的实现方法
		   Double totalPrice = NumberUtils.DOUBLE_ZERO;
		   for(PurchaseBillDetail Dtl:purchaseBill.getPurchaseBillDetails()){//通过采购单基本对象获得当前采购单明细的集合
			  if(null != Dtl.getTotalPrice()){
				  totalPrice+=Dtl.getTotalPrice();
			  }
			  
		   }
		   purchaseBill.setTotalPrice(totalPrice);
			purchaseBilldao.storeTotalPrice(purchaseBill);
		  
		 
	}
   public void updatePurchaseBillPayInformation(PurchaseBill purchaseBill){//累加已付金额的具体方法
	   Double totalPayPrice = NumberUtils.DOUBLE_ZERO;
	   for( PayDetail PDtl: purchaseBill.getPayDetails()){
		   totalPayPrice+=PDtl.getPayMoney();
	   }
	   purchaseBill.setAlreadyPayOut(totalPayPrice);
   }
	public void deletePurchaseBill(PurchaseBill purchaseBill) {
		
		this.purchaseBilldao.deletePurchaseBill(purchaseBill);
	}

	public void deleteAllPurchaseBills(Collection<PurchaseBill> PurchaseBills) {
		   this.purchaseBilldao.deleteAllPurchaseBills(PurchaseBills);
		
	}
	//采购单失效方法
	public void disabledAllPurchases(Collection<PurchaseBill> purchases) throws Exception {
		for (PurchaseBill oil : purchases) {
			
			if(oil.getStatus().equals(PurchaseBillStatus.NEWSTATUS)){	   //如果该采购单已被验收或验收中  则不能被失效，抛出异常
				 
				 SubscribeDetail subscribeDetail = null;
                 Set<PurchaseBillDetail> set = oil.getPurchaseBillDetails();//获得采购单明细
                 for(PurchaseBillDetail pd : set){
                	 subscribeDetail = pd.getSubscribeDetail();//获得申购单明细
                	 if(null != subscribeDetail){
    					 subscribeDetail.setAmount(0);
    					 Subscribe subscribe = subscribeDetail.getSubscribe();
    					 
    					// subscribe.setStatus(SubscribeStatus.NEWPURCHASE); //置 申购单状态为 新建
    					 subscribeDao.storeSubscribe(subscribe);
    					 
    					// subscribeDetail.setStatus(SubscribeDetailStatus.NEW); 
    					 
    					// Subscribe subscribe = subscribeDetail.getSubscribe();//获得申购单
    					 
    				 }
                	 
                 }
				
				 
				oil.setDisabled(true);
			 
		 
			}else{
				throw new Exception();
			}
		//如果采购单的预算编号不为空，则减去该预算编号关联的采购总费用
		if (null != oil.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(oil.getBudgetNo(), oil.getTotalPrice());
			//如果已付金额存在，则从预算中已发生费用减去已付金额
			if (null != oil.getAlreadyPayOut()) {
				this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(oil.getBudgetNo(),oil.getAlreadyPayOut());
			}
		}
		this.purchaseBilldao.storePurchaseBill(oil);
		purchaseDisabledChangeSubscribeStatus(oil);
			
		}
	}
	//采购单失效后  与采购单相关联的申购单的状态至为未采购
	public void purchaseDisabledChangeSubscribeStatus( PurchaseBill oil){
		for(PurchaseBillDetail detail:oil.getPurchaseBillDetails()){
			   if(null!=detail.getSubscribeDetail()){
				   SubscribeDetail subDetail =detail.getSubscribeDetail();
				   SubscribeDetailStatus  subscribeDetailStatus  =subDetail.getStatus();
				   if(oil.getDisabled()==true){
					   subDetail.setStatus(subscribeDetailStatus.NEW);
					   subscribeManager.updateSubscribeStatus(subDetail.getSubscribe());
				   }else{
					   subDetail.setStatus(subscribeDetailStatus.PURCHASEED); 
					   subscribeManager.updateSubscribeStatus(subDetail.getSubscribe());
				   }
			       subscribeDao.storeSubscribeDetail(subDetail);
			       subscribeDao.storeSubscribe(subDetail.getSubscribe());
			}
		}
	}
   //采购单有效方法
	public void enabledAllPurchasers(Collection<PurchaseBill> purchases) {
		for (PurchaseBill oil : purchases) {
			 oil.setDisabled(false);
			 
//			 SubscribeDetail subscribeDetail = null;
//             Set<PurchaseBillDetail> set = oil.getPurchaseBillDetails();//获得采购单明细
//             for(PurchaseBillDetail pd : set){
//            	 subscribeDetail = pd.getSubscribeDetail();//获得申购单明细
//            	 if(null != subscribeDetail){
//            		 
//					 
//				 }
//            	 
//             }
 
			this.purchaseBilldao.storePurchaseBill(oil);
			//如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
			if (null != oil.getBudgetNo()) {
				this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(oil.getBudgetNo(), oil.getTotalPrice());
				//如果已付金额存在，则从预算中已发生费用加上已付金额
				if (null != oil.getAlreadyPayOut()) {
					this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(oil.getBudgetNo(),oil.getAlreadyPayOut());
				}
			}
			purchaseDisabledChangeSubscribeStatus(oil);
		}
	}

	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}
	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}

	public void addPurchaseConTotalPrice(PurchaseBill purchaseBill) {
		this.updatePurchaseBillInformation(purchaseBill);
	}
	public List<ToolingPurchaseBillDetailView> loadPurchaseBillDtlByBillId(Long purchasId) {
		return toolingPurchaseBillDetailViewDao.loadToolingPurchaseBillContractDetail(purchasId);
	}
	public List<ToolingPurchaseBillDetailView> loadPurchaseBillDtlByBillId(List ids) {
		return toolingPurchaseBillDetailViewDao.loadToolingPurchaseBillContractDetail(ids);
	}
}
