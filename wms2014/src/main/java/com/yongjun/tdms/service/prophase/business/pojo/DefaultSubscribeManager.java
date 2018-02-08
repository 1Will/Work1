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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.base.event.EventNewDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDetailViewDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.spareWareHouse.SpareWareHouse;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;
import com.yongjun.tdms.model.prophase.business.SubscribeStatus;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.spare.spareWareHouse.SpareWareHouseManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;

/**
 * @author qs
 * @version $Id: DefaultSubscribeManager.java 11237 2008-03-09 10:36:42Z mwei $
 */
public class DefaultSubscribeManager extends BaseManager implements
		SubscribeManager {

	private final SubscribeDao subscribeDao;
	private final SequenceManager sequenceManager;
	private final QuarterPlanDetailManager quarterPlanDetailManager;
	private final SpareDao spareDao;
	private final SubscribeDetailViewDao subscribeDetailViewDao;		//打印报表所用
    private final EventNewDao eventNewDao;
	private BudgetDetailManager budgetDetailManager;
	private UserManager userManager;
	private SpareWareHouseManager spareWareHouseManager;
	private User user;
	

	public DefaultSubscribeManager(SubscribeDao subscribeDao,
			SequenceManager sequenceManager,
			QuarterPlanDetailManager quarterPlanDetailManager,
			SpareDao spareDao,
			SubscribeDetailViewDao subscribeDetailViewDao,
			UserManager userManager,
			EventNewDao eventNewDao,
			SpareWareHouseManager spareWareHouseManager) {
		this.subscribeDao = subscribeDao;
		this.sequenceManager = sequenceManager;
		this.quarterPlanDetailManager = quarterPlanDetailManager;
		this.spareDao = spareDao;
		this.subscribeDetailViewDao = subscribeDetailViewDao;
		this.userManager=userManager;
		this.eventNewDao = eventNewDao;
		this.spareWareHouseManager = spareWareHouseManager;
	
		

	}

	/**
	 * 加载申购单（采购单）
	 */
	public Subscribe loadSubscribe(Long id) {
		Subscribe subscribe = subscribeDao.loadSubscribe(id);
		return subscribe;
	}

	/**
	 * 存储申购单（采购单）
	 */
	public void storeSubscribe(Subscribe subscribe) {
		this.subscribeDao.storeSubscribe(subscribe);
		// 如果申购单的预算编号不为空，则加上该预算编号关联的申购总费用
		if(subscribe.getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != subscribe.getBudgetNo()) {
				this.budgetDetailManager.addParchaseFeeFromBudgetDetail(subscribe.getBudgetNo(), subscribe.getTotalPrice());
			}
		}
		
	}

	/**
	 * 根据ID数组加载申购单（采购单）集合
	 */
	public List<Subscribe> loadAllSubscribes(Long[] ids) {
		return subscribeDao.loadAllSubscribes(ids);
	}

	/**
	 * 根据传入的集合删除申购单（采购单）
	 */
	public void deleteAllSubscribes(List<Subscribe> subscribes) {
		subscribeDao.deleteAllSubscribes(subscribes);
	}

	/**
	 * 编号自动生成(申购单、采购单)
	 */
	public String generateSubscribeBillNo() {
		return sequenceManager.generate("-").toString();
	}

	/**
	 * 根据传入的明细数组，删除申购单明细、采购单明细[工装制作明细、备品备件明细、维修计划明细、技术改造明细]
	 */
	public List<SubscribeDetail> loadAllSubscribeDetails(Long[] ids) {
		return subscribeDao.loadAllSubscribeDetails(ids);
	}

	/**
	 * 根据传入的明细结合删除申购单明细，并改变该申购单的状态、总金额
	 */
	public void deleteAllSubscribeDetails(
			List<SubscribeDetail> subscribeDetails, Subscribe subscribe)
			throws Exception {
		// 如果所选的要删除的申购单明细中有一个对象的状态为已采购 那么删除失败
		updateSubscribeDtlStauts(subscribeDetails);
		
		try {
			subscribeDao.deleteAllSubscribeDetails(subscribeDetails);
			
//			 如果申购单的预算编号不为空，则减去该预算编号关联的申购总费用
			if (null != subscribe.getBudgetNo()) {
				this.budgetDetailManager.removeParchaseFeeFromBudgetDetail(
						subscribe.getBudgetNo(), subscribe.getTotalPrice());
			}
			// 清除申购单关联的申购明细
			for (SubscribeDetail detail : subscribeDetails) {
				subscribe.getSubscribeDetails().remove(detail);
				//添加 事件 zzb 删除
				this.storeEventNew_subDetail(1061, detail.getId().intValue(),"删除");
			}
			// 更新申购单总费用
			this.updateSubscribeInformation(subscribe);
			// 更新申购单状态
			updateSubscribeStatus(subscribe);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * 根据ID加载明细
	 */
	public SubscribeDetail loadSubscribeDetail(Long id) {
		return subscribeDao.loadSubscribeDetail(id);
	}

	/**
	 * 失效所有的申购单
	 */
	public void disabledAllSubscribes(Collection<Subscribe> subscribes)
			throws Exception {
		for (Subscribe Oil : subscribes) {
			if (Oil.getStatus().equals(SubscribeStatus.NEWPURCHASE)) {
				Oil.setDisabled(true);
			} else {
				throw new Exception();
			}
			// 如果申购单的预算编号不为空，则减去该预算编号关联的申购总费用
			if (null != Oil.getBudgetNo()) {
				this.budgetDetailManager.removeParchaseFeeFromBudgetDetail(Oil
						.getBudgetNo(), Oil.getTotalPrice());
			}
			this.subscribeDao.storeSubscribe(Oil);
		}
	}

	/**
	 * 使申购单有效
	 */
	public void enabledAllSubscribes(Collection<Subscribe> subscribes) {
		for (Subscribe Oil : subscribes) {
			Oil.setDisabled(false);
			this.subscribeDao.storeSubscribe(Oil);
			// 如果申购单的预算编号不为空，则加上该预算编号关联的申购总费用
			if (null != Oil.getBudgetNo()) {
				this.budgetDetailManager.addParchaseFeeFromBudgetDetail(Oil
						.getBudgetNo(), Oil.getTotalPrice());
			}
		}
	}

	/**
	 * 打印报表所用
	 */
	@SuppressWarnings("unchecked")
	public List Query(String[] queryInfo) throws HibernateException {
		return subscribeDao.Query(queryInfo);
	}

	/**
	 * 从季度计划明细-->申购单明细
	 */
	public void storQuarterPlan(Subscribe subscribe, String addQuarterPlanIds) {
		String[] addQuarterPlanId = null;
		if (addQuarterPlanIds != null) {
			addQuarterPlanId = addQuarterPlanIds.split(",");
		}
		for (int i = 0; i < addQuarterPlanId.length; i++) {
			SubscribeDetail detail = new SubscribeDetail(); // 获得季度计划明细的对象
			QuarterPlanDetail planDetailDetail = quarterPlanDetailManager
					.loadQuarterPlanDetail(Long.valueOf(addQuarterPlanId[i]));
			detail.setQuarterPlanDetail(planDetailDetail);
			detail.setSubscribe(subscribe);
			// 从季度计划复制到采购单明细中
			copyQuarterPlanDetaillToSubscribeDetail(detail, planDetailDetail);
			// 季度计划被复制到采购单明细之后 把季度计划明细中的是否创建采购单的标示至为true
			planDetailDetail.setCreatePurchaseFlag(true);
			this.storeToolingAndDevice(detail);
		}
		//更新申购单总费用
		this.updateSubscribeInformation(subscribe);
		//更新申购单状态
		updateSubscribeStatus(subscribe);
	}

	/**
	 * 从设备管理中的备件台帐-->申购单明细
	 */
	public void storeSpareAccount(Subscribe subscribe,
			String addDeviceSpareAccountIds) {
		String[] addDeviceSpareAccountId = null;
		if (addDeviceSpareAccountIds != null) {
			addDeviceSpareAccountId = addDeviceSpareAccountIds.split(",");
		}
		for (int i = 0; i < addDeviceSpareAccountId.length; i++) {
			// 获得设备管理中的备件台帐的明细对象
			SubscribeDetail detail = new SubscribeDetail();
			Spare spare = spareDao.loasSpare(Long
					.valueOf(addDeviceSpareAccountId[i]));
			// 在明细中设置备件关联
			detail.setSpare(spare);
			// 在明细中设置申购单关联
			detail.setSubscribe(subscribe);
			// 从备件台帐复制到申购单明细中
			copySpareAccountToSubscribeDetail(detail, spare);
			subscribe.getSubscribeDetails().add(detail);
			this.storeToolingAndDevice(detail);
		}
		//更新申购单总费用
		this.updateSubscribeInformation(subscribe);
		//更新申购单状态
		updateSubscribeStatus(subscribe);
	}
	public void storeSubscribeDetailByLessMinStocks(Subscribe subscribe,
			String addSpareWarehouseOfLessMinStocksIds) {
		String [] spareWarehouseOfLessMinStocksIds = null;
		if (null != addSpareWarehouseOfLessMinStocksIds) {
			spareWarehouseOfLessMinStocksIds = addSpareWarehouseOfLessMinStocksIds.split(",");
		}
		for (int i=0;i<spareWarehouseOfLessMinStocksIds.length;i++){
			SubscribeDetail detail = new SubscribeDetail();
			SpareWareHouse spareWareHouse = this.spareWareHouseManager.loadSpareWareHouse(Long
					.valueOf(spareWarehouseOfLessMinStocksIds[i]));
			
			detail.setSubscribe(subscribe);
			detail.setGraphNo(spareWareHouse.getSpare().getSpareNo());
			detail.setName(spareWareHouse.getSpare().getName());
			detail.setModel(spareWareHouse.getSpare().getModelSpecs());
			detail.setOrderNo(spareWareHouse.getSpare().getOrderNo());
			detail.setCategory(spareWareHouse.getSpare().getCategory());
			detail.setCategoryName(spareWareHouse.getSpare().getCategory().getName());
//			detail.setDetailCategory(spareWareHouse.getSpare().getSpareDetailType());
//			detail.setDetailCategoryName(spareWareHouse.getSpare().getSpareDetailType().getName());
			detail.setCalUnit(spareWareHouse.getSpare().getUnit());
			detail.setSpare(spareWareHouse.getSpare());
			detail.setUnitPrice(spareWareHouse.getSpare().getUnitPrice());
			this.subscribeDao.storeSubscribeDetail(detail);
			
		}
		
	}
	/**
	 * 保存申购单明细
	 */
	public void storeSubscribeDetail(String allSubscribeDetailId,
			String allSubscribeDetailAmountNumber,
			String allSubscribeDetailUnitPrice,
			String allSubscribeDetailRequestDate,
			String allSubscribeDetailSupplierName,
			String allSubscribeDetailComment,
			String allSubscribeDetailBuyeAmount,
			String allSubscribeCollectBillDetailBuyer,
			Subscribe subscribe,
			String allBeizhu) {
		String[] dtlIdArr = null;
		String[] amountNumberArr = null;
		String[] utitPriceArr = null;
		String[] requestDateArr = null;
		String[] supplierNameArr = null;
		String[] commentArr = null;
		String[] buyeAmountArr = null;
		String[] buyerArr=null;
		String[] beizhuArr=null;
		
		if (null != allSubscribeDetailId) { 					// 申购单明细所有id
			dtlIdArr = allSubscribeDetailId.split(",");
		}
		if (null != allSubscribeDetailAmountNumber) { 			// 申购单明细所有数量
			amountNumberArr = allSubscribeDetailAmountNumber.split(",");
		}
		if (null != allSubscribeDetailUnitPrice) { 				// 申购单明细所有单价
			utitPriceArr = allSubscribeDetailUnitPrice.split(",");
		}
		if (null != allSubscribeDetailRequestDate) { 			// 申购单明细所有需求日期
			requestDateArr = allSubscribeDetailRequestDate.split(",");
		}
		if (null != allSubscribeDetailSupplierName) { 			// 申购单明细所有供应商名称
			supplierNameArr = allSubscribeDetailSupplierName.split(",");
		}
		if (null != allSubscribeDetailComment) { 				// 申购单明细所有备注
			commentArr = allSubscribeDetailComment.split(",");
		}
		if (null != allSubscribeDetailBuyeAmount) { 				// 申购单明细所有采购数量
			buyeAmountArr = allSubscribeDetailBuyeAmount.split(",");
		}
		
		if (null != allSubscribeCollectBillDetailBuyer) { 				// 采购人		
			buyerArr = allSubscribeCollectBillDetailBuyer.split(",");
		}
		if(null !=allBeizhu){
			beizhuArr = allBeizhu.split(",");//备注 zzb
		}
		
		
		updateSubscribeDetail(dtlIdArr, 
				              amountNumberArr, 
				              utitPriceArr,
				              requestDateArr, 
				              supplierNameArr, 
				              commentArr,
				              buyeAmountArr,
				              buyerArr,
				              beizhuArr);
	
		if(null != allSubscribeCollectBillDetailBuyer){
			
		}else if(null !=subscribe){			
			updateSubscribeStatus(subscribe);
		}
	}

	private void updateSubscribeDetail(String[] dtlIdArr,
			String[] amountNumberArr, String[] utitPriceArr,
			String[] requestDateArr, String[] supplierNameArr,
			String[] commentArr,
			String[] buyeAmountArr,
			String[] buyerArr,
			String[] beizhuArr) {
		// 申购单明细循环次数
		int subscribeDtlIdNum = 0;
//		System.out.println("subscribeDtlIdNum:"+subscribeDtlIdNum + " dtlIdArr.length:"+dtlIdArr.length);
		while (dtlIdArr != null && subscribeDtlIdNum < dtlIdArr.length) {
			SubscribeDetail detail = this.loadSubscribeDetail(Long
					.valueOf(dtlIdArr[subscribeDtlIdNum]));
			
			// 保存数量
			int amountNumber = 0;
			if (amountNumberArr != null) {
				for (int i = 0; i < amountNumberArr.length; i = i + 2) {
					if (amountNumberArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						amountNumber = Integer.valueOf(amountNumberArr[i + 1]);
						detail.setAmount(amountNumber);
						detail.setTotalPrice(detail.getUnitPrice()*amountNumber);
						break;
					} else {
						detail.setAmount(0);
						detail.setTotalPrice(0.0);
					}
				}
			} 
			
			// 保存单价
			Double utitPrice = 0.0;
			if (utitPriceArr != null) {
				for (int i = 0; i < utitPriceArr.length; i = i + 2) {
					if (utitPriceArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						utitPrice = Double.valueOf(utitPriceArr[i + 1]);
						detail.setUnitPrice(utitPrice);
						break;
					} else {
						detail.setUnitPrice(utitPrice);
					}
				}
			}

			// 计算出总价
			Double totalPrice = 0.0;
			if (amountNumber != 0 && utitPrice != 0.0) {
				totalPrice = amountNumber * utitPrice;
				detail.setTotalPrice(totalPrice);
			} else {
				detail.setTotalPrice(totalPrice);
			}

			// 保存需要日期
			if (requestDateArr != null) {
				for (int i = 0; i < requestDateArr.length; i = i + 2) {
					if (requestDateArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						detail.setRequireDate(DateUtil.toDate(
								requestDateArr[i + 1], "yyyy-MM-dd"));
						break;
					} else {
						detail.setRequireDate(null);
					}
				}
			}
			// 保存供应商名称
			if (supplierNameArr != null) {
				for (int i = 0; i < supplierNameArr.length; i = i + 2) {
					if (supplierNameArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						detail.setSupplierName(supplierNameArr[i + 1]);
						break;
					} else {
						detail.setSupplierName(null);
					}
				}
			}
			// 保存备注
			if (commentArr != null) {
				for (int i = 0; i < commentArr.length; i = i + 2) {
					if (commentArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						detail.setComment(commentArr[i + 1]);
						break;
					} else {
						detail.setComment(null);
					}
				}
			} 
			
			// 保存备注 zzb
			if (beizhuArr != null) {
				for (int i = 0; i < beizhuArr.length; i = i + 2) {
					if (beizhuArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						detail.setBeizhu(beizhuArr[i + 1]);
						break;
					} else {
						detail.setBeizhu(null);
					}
				}
			} 
			
			
			//保存采购数量
			int buyeAmount = 0;
			if (buyeAmountArr != null) {
				for (int i = 0; i < buyeAmountArr.length; i = i + 2) {
					if (buyeAmountArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						buyeAmount = Integer.valueOf(buyeAmountArr[i + 1]);
						detail.setBuyeAmount(buyeAmount);

						break;
					} 
				}
			} 
			
			// 保存采购人
			Long uid=null;
//			System.out.println("if setBuyer:"+buyerArr.length + " detail name:"+detail.getName());
			if (buyerArr != null) {
				for (int i = 0; i < buyerArr.length; i = i + 2) {
					if (buyerArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						uid=Long.valueOf(buyerArr[i + 1]);
						this.user=this.userManager.loadUser(uid);
						detail.setBuyer(user);
//						System.out.println("if setBuyer:"+user.getName() + " detail name:"+detail.getName());
						break;
					} else {
						detail.setBuyer(null);
//						System.out.println("else setBuyer:" + " detail name:"+detail.getName());
					}
				}
			} 
			
			//this.storeToolingAndDevice(detail);
			
			if(buyerArr!=null){
				subscribeDao.storeSubscribeDetail(detail);
			}else{				
				this.storeSubscribeDetail(detail);
			}
			//this.updateSubscribeInformation(detail.getSubscribe());
			
           //添加到 event 事件 1060 insert, 1061 delete, 1062 update
			this.storeEventNew_subDetail(1062, detail.getId().intValue(),"更新");
			subscribeDtlIdNum++;
		}
	}

	/*--------------------------申购单明细、采购单明细共用方法-----------------------------------------*/

	/**
	 * 存储申购单明细\采购单明细、更新总费用、更新状态
	 */
	public void storeSubscribeDetail(SubscribeDetail subscribeDtl) {
		// 如果申购单的预算编号不为空，则减去该预算编号关联的申购总费用
		if(subscribeDtl.getSubscribe().getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != subscribeDtl.getSubscribe().getBudgetNo()) {
				this.budgetDetailManager.removeParchaseFeeFromBudgetDetail(
						subscribeDtl.getSubscribe().getBudgetNo(), subscribeDtl
								.getSubscribe().getTotalPrice());
			}
		}
		
		// 更新申购明细总价
		subscribeDtl.setTotalPrice(subscribeDtl.getUnitPrice()*subscribeDtl.getAmount());
		subscribeDao.storeSubscribeDetail(subscribeDtl);

		if(subscribeDtl.getSubscribe().getToolingDevFlag().equals(SysModel.TOOLING)){
			if(subscribeDtl.getSubscribe().getBudgetNo()!=null){
				BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(subscribeDtl.getSubscribe().getBudgetNo());
				Double allPrice=subscribeDao.getAllSubscribeDetailTotalPriceBySubscribeIdAndDetailType(subscribeDtl.getSubscribe().getId());
				budgetDetail.setPurchaseFee(allPrice);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
			}
		}
		// 更新总费用
		updateSubscribeInformation(subscribeDtl.getSubscribe());
		// 更新申购单状态
		updateSubscribeStatus(subscribeDtl.getSubscribe());
	}

	/**
	 * 如果明细已经被采购，如果删除，则不允许删除（用抛出异常方式）
	 * 
	 * @param subscribeDetails
	 * @throws Exception
	 */
	private void updateSubscribeDtlStauts(List<SubscribeDetail> subscribeDetails)
			throws Exception {
		for (SubscribeDetail dtl : subscribeDetails) { // 如果申购单明细中申购单的状态为已采购
			if (dtl.getStatus().equals(SubscribeDetailStatus.PURCHASEED)) {
				throw new Exception();
			}
		}
	}

	/**
	 * 更新总费用(申购单、采购单)
	 * 
	 * @param subscribe
	 */
	private void updateSubscribeInformation(Subscribe subscribe) {
		// 累加总费用
		Double totalPrice = NumberUtils.DOUBLE_ZERO;
		for (SubscribeDetail dtl : subscribe.getSubscribeDetails()) {
			totalPrice += dtl.getTotalPrice();
		}
		subscribe.setTotalPrice(totalPrice);
		if(subscribe.getToolingDevFlag().equals(SysModel.TOOLING)){
			this.subscribeDao.storeToolingPurchase(subscribe);
		}else{
	        //this.subscribeDao.storeSubscribe(subscribe);
			this.storeSubscribe(subscribe);
		}
	}

	/**
	 * 根据申购单明细的状态改变申购单的状态(采购单明细状态来改变采购单状态)
	 */
	public void updateSubscribeStatus(Subscribe subscribe) {
		int unSubscribeNum = 0; // 表示新建
		for (SubscribeDetail subscribeDtl : subscribe.getSubscribeDetails()) {
			if (subscribeDtl.getStatus().equals(
					SubscribeDetailStatus.NEW)) {
				unSubscribeNum++;
				continue;
			}
		}

		if (unSubscribeNum == subscribe.getSubscribeDetails().size()) {
			subscribe.setStatus(SubscribeStatus.NEWPURCHASE);
		} else if (unSubscribeNum < subscribe.getSubscribeDetails().size()
				&& unSubscribeNum > 0) {
			subscribe.setStatus(SubscribeStatus.PURCHASING);
		} else {
			subscribe.setStatus(SubscribeStatus.PURCHASEED);
		}
		
		if(subscribe.getToolingDevFlag().equals(SysModel.TOOLING)){
			this.subscribeDao.storeToolingPurchase(subscribe);
		}else{
			this.subscribeDao.storeSubscribe(subscribe);
		}
	}
	
	public void sss(Subscribe subscribe){
		int notNum=0;
		int newNum=0;
		int purNum=0;
		int colNum=0;
		int insNum=0;
		int size =0;
		for(SubscribeDetail detail : subscribe.getSubscribeDetails()){
			String status = detail.getStatus().toString();
			size = subscribe.getSubscribeDetails().size();
			if("NEW".equals(status)){
				newNum++;
			}if("COLLECTED".equals(status)){
				colNum++;
			}if("PURCHASEED".equals(status)){
				purNum++;
			}if("INSPECTED".equals(status)){
				insNum++;
			}if("NOTPURCHASEED".equals(status)){
				notNum++;
			}
		}
		
		if(size==newNum){
			subscribe.setStatus(SubscribeStatus.NEWPURCHASE);
		}if(size==purNum){
			subscribe.setStatus(SubscribeStatus.PURCHASEED);
		}if(size==insNum){
			subscribe.setStatus(SubscribeStatus.STORAGED);
		}
	}
	
	
	

	/**
	 * 保存申购单明细、采购单明细
	 */
	public void storeToolingAndDevice(SubscribeDetail dtl) {
		subscribeDao.storeToolingAndDevice(dtl);
	}

	// 从备件台帐copy到申购单明细、采购单明细中
	private void copySpareAccountToSubscribeDetail(SubscribeDetail detail,
			Spare spare) {
		if (spare.getSpareNo() != null) { // 复制编号
			detail.setGraphNo(spare.getSpareNo());
		}
		if (spare.getName() != null) { // 复制名称
			detail.setName(spare.getName());
		} else {
			detail.setName(null);
		}
		if (spare.getModelSpecs() != null) { // 复制型号
			detail.setModel(spare.getModelSpecs());
		} else {
			detail.setModel(null);
		}
		if (spare.getSpecification() != null) { // 复制规格
			detail.setSpecification(spare.getSpecification());
		} else {
			detail.setSpecification(null);
		}
		if (spare.getUnitPrice() != null) { // 复制价格
			detail.setUnitPrice(spare.getUnitPrice());
		} else {
			detail.setUnitPrice(null);
		}
		if(spare.getUnit()!=null){			//单位
			detail.setCalUnit(spare.getUnit());
		}else{
			detail.setCalUnit(null);
		}
		if (spare.getSupplier() != null) {
			if (spare.getSupplier().getName() != null) { // 复制供应商
				detail.setSupplierName(spare.getSupplier().getName());
			} else {
				detail.setSupplierName(null);
			}
		}
		if(spare.getCategory()!=null){					//种类
			detail.setCategory(spare.getCategory());
			if(spare.getCategory().getName()!=null){
				detail.setCategoryName(spare.getCategory().getName());
			}
		}
		if(spare.getSpareDetailType()!=null){			//明细种类
			detail.setDetailCategory(spare.getSpareDetailType());
			if(spare.getSpareDetailType().getName()!=null){
				detail.setDetailCategoryName(spare.getSpareDetailType().getName());
			}
		}
	}

	// 该方法申购单明细、采购单明细[工装制作明细、备品备件明细、维修计划明细、技术改造明细]共同调用
	private void copyQuarterPlanDetaillToSubscribeDetail(
			SubscribeDetail detail, QuarterPlanDetail planDetailDetail) {
		if (planDetailDetail.getGraphNo() != null) { 					// 复制图号
			detail.setGraphNo(planDetailDetail.getGraphNo());
		} else {
			detail.setGraphNo(null);
		}
		if(planDetailDetail.getSpare()!=null){							//关联备件
			detail.setSpare(planDetailDetail.getSpare());
		}else{
			detail.setSpare(null);
		}
		if (planDetailDetail.getName() != null) { 						// 复制名称
			detail.setName(planDetailDetail.getName());
		} else {
			detail.setName(null);
		}
		if (planDetailDetail.getToolingCategory() != null) { 			// 复制所属类别关联
			detail.setToolingCategory(planDetailDetail.getToolingCategory());
		}
		if (planDetailDetail.getCategoryName() != null) { 				// 复制类别
			detail.setCategoryName(planDetailDetail.getCategoryName());
		} else {
			detail.setCategoryName(null);
		}
		if(planDetailDetail.getDetailCategory()!=null){					//复制所属明细类别关联
			detail.setDetailCategory(planDetailDetail.getDetailCategory());
		}
		if(planDetailDetail.getDetailCategoryName()!=null){				//复制明细类别
			detail.setDetailCategoryName(planDetailDetail.getDetailCategoryName());
		}
		if (planDetailDetail.getModel() != null) { 						// 复制模型
			detail.setModel(planDetailDetail.getModel());
		} else {
			detail.setModel(null);
		}
		if (planDetailDetail.getSpecification() != null) { 				// 复制规格
			detail.setSpecification(planDetailDetail.getSpecification());
		} else {
			detail.setSpecification(null);
		}
		if (planDetailDetail.getNumber() != null) { 					// 复制数量
			detail.setAmount(planDetailDetail.getNumber());
		}
		if (planDetailDetail.getUnitPrice() != null) { 					// 复制单价
			detail.setUnitPrice(planDetailDetail.getUnitPrice());
		}
		if (planDetailDetail.getAllPrice() != null) { 					// 复制总价
			detail.setTotalPrice(planDetailDetail.getAllPrice());
		}
		if (planDetailDetail.getRequestDate() != null) { 				// 复制需求日期
			detail.setRequireDate(planDetailDetail.getRequestDate());
		} else {
			detail.setRequireDate(null);
		}
		if (planDetailDetail.getRequestReason() != null) { 				// 复制需求原因
			detail.setReqReason(planDetailDetail.getRequestReason());
		} else {
			detail.setReqReason(null);
		}
		if (planDetailDetail.getComment() != null) { 					// 复制备注
			detail.setComment(planDetailDetail.getComment());
		} else {
			detail.setComment(null);
		}
		if (planDetailDetail.getTooling() != null) { 					// 复制所属的工装
			detail.setTooling(planDetailDetail.getTooling());
		} else {
			detail.setTooling(null);
		}
		if(planDetailDetail.getCalUnit()!=null){						//单位
			detail.setCalUnit(planDetailDetail.getCalUnit());
		}else{
			detail.setCalUnit(null);
		}
	}

	/*-------------------------------------------------------------------------------------*/

	/*-----------------------------------以下是工装商务管理------------------------------------*/
	/**
	 * 保存采购单明细[工装制作明细、备品备件明细、维修计划明细、技术改造明细]
	 */
	public void storeToolingSubscribeDtl(String allSubscribeDetailId,
			String allSubscribeDetailAmountNumber,
			String allSubscribeDetailUnitPrice,
			String allSubscribeDetailRequestDate,
			String allSubscribeDetailReqSeaon,
			String allSubscribeDetailComment, Subscribe subscribe) {
		String[] dtlIdArr = null;
		String[] amountNumberArr = null;
		String[] utitPriceArr = null;
		String[] requestDateArr = null;
		String[] reqSeaonArr = null;
		String[] commentArr = null;
		if (null != allSubscribeDetailId) { 					// 采购单明细所有id
			dtlIdArr = allSubscribeDetailId.split(",");
		}
		if (null != allSubscribeDetailAmountNumber) { 			// 采购单明细所有数量
			amountNumberArr = allSubscribeDetailAmountNumber.split(",");
		}
		if (null != allSubscribeDetailUnitPrice) { 				// 采购单明细所有单价
			utitPriceArr = allSubscribeDetailUnitPrice.split(",");
		}
		if (null != allSubscribeDetailRequestDate) { 			// 采购单明细所有需求日期
			requestDateArr = allSubscribeDetailRequestDate.split(",");
		}
		if (null != allSubscribeDetailReqSeaon) { 				// 采购单明细所有原因
			reqSeaonArr = allSubscribeDetailReqSeaon.split(",");
		}
		if (null != allSubscribeDetailComment) { 				// 采购单明细所有备注
			commentArr = allSubscribeDetailComment.split(",");
		}
		updateToolingSubscribeDtl(dtlIdArr, amountNumberArr, utitPriceArr,
				requestDateArr, reqSeaonArr, commentArr);
		updateSubscribeStatus(subscribe);
	}

	private void updateToolingSubscribeDtl(String[] dtlIdArr,
			String[] amountNumberArr, String[] utitPriceArr,
			String[] requestDateArr, String[] reqSeaonArr, String[] commentArr) {
		// 采购单明细循环次数
		int subscribeDtlIdNum = 0;
		while (dtlIdArr != null && subscribeDtlIdNum < dtlIdArr.length) {
			SubscribeDetail detail = this.loadSubscribeDetail(Long
					.valueOf(dtlIdArr[subscribeDtlIdNum]));
			// 保存数量
			int amountNumber = 0;
			if (amountNumberArr != null) {
				for (int i = 0; i < amountNumberArr.length; i = i + 2) {
					if (amountNumberArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						amountNumber = Integer.valueOf(amountNumberArr[i + 1]);
						detail.setAmount(amountNumber);
						break;
					} else {
						detail.setAmount(null);
					}
				}
			} else {
				detail.setAmount(amountNumber);
			}
			// 保存单价
			Double utitPrice = 0.0;
			if (utitPriceArr != null) {
				for (int i = 0; i < utitPriceArr.length; i = i + 2) {
					if (utitPriceArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						utitPrice = Double.valueOf(utitPriceArr[i + 1]);
						detail.setUnitPrice(utitPrice);
						break;
					} else {
						detail.setUnitPrice(utitPrice);
					}
				}
			}

			// 计算出总价
			Double totalPrice = 0.0;
			if (amountNumber != 0 && utitPrice != 0.0) {
				totalPrice = amountNumber * utitPrice;
				detail.setTotalPrice(totalPrice);
			} else {
				detail.setTotalPrice(totalPrice);
			}
			// 保存需求日期
			if (requestDateArr != null) {
				for (int i = 0; i < requestDateArr.length; i = i + 2) {
					if (requestDateArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
						detail.setRequireDate(DateUtil.toDate(
								requestDateArr[i + 1], "yyyy-MM-dd"));
						break;
					} else {
						detail.setRequireDate(null);
					}
				}
			}
			// 如果是保存备品备件明细，则调用
			if (detail.getDetailType().equals(
					YearPlanDetailCategory.SPARE_PURCHASE)) {
				// 保存需求原因
				if (reqSeaonArr != null) {
					for (int i = 0; i < reqSeaonArr.length; i = i + 2) {
						if (reqSeaonArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
							detail.setReqReason(reqSeaonArr[i + 1]);
							break;
						} else {
							detail.setReqReason(null);
						}
					}
				} else {
					detail.setReqReason(null);
				}
				// 保存备注
				if (commentArr != null) {
					for (int i = 0; i < commentArr.length; i = i + 2) {
						if (commentArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
							detail.setComment(commentArr[i + 1]);
							break;
						} else {
							detail.setComment(null);
						}
					}
				} else {
					detail.setComment(null);
				}
				// 如果保存的是[工装制作明细、维修计划明细、技术改造明细]则调用
			} else {
				// 保存需求原因
				if (reqSeaonArr != null) {
					for (int i = 0; i < reqSeaonArr.length; i = i + 2) {
						if (reqSeaonArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
							detail.setReqReason(reqSeaonArr[i + 1]);
							break;
						} else {
							detail.setReqReason(null);
						}
					}
				}
				// 保存备注
				if (commentArr != null) {
					for (int i = 0; i < commentArr.length; i = i + 2) {
						if (commentArr[i].equals(dtlIdArr[subscribeDtlIdNum])) {
							detail.setComment(commentArr[i + 1]);
							break;
						} else {
							detail.setComment(null);
						}
					}
				}
			}
			this.storeToolingAndDevice(detail);
			if(detail.getSubscribe().getBudgetNo()!=null){
				BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(detail.getSubscribe().getBudgetNo());
				Double allPrice=subscribeDao.getAllSubscribeDetailTotalPriceBySubscribeIdAndDetailType(detail.getSubscribe().getId());
				budgetDetail.setPurchaseFee(allPrice);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
			}
			updateSubscribeInformation(detail.getSubscribe());
			subscribeDtlIdNum++;
		}
	}

	// public void updateToolingSubscribeStatus(Subscribe subscribe,String
	// detailType){
	// int unSubscribeNum=0; //表示未采购
	// List<SubscribeDetail> list =
	// subscribeDao.loadAllSubscribeDetailByDetailType(subscribe.getId(),detailType);
	// for(SubscribeDetail subscribeDtl:list){
	// if(subscribeDtl.getStatus().equals(SubscribeDetailStatus.UNPURCHASE)){
	// unSubscribeNum++;
	// continue;
	// }
	// }
	//	
	// if(unSubscribeNum==list.size()){
	// subscribe.setStatus(SubscribeStatus.NEWPURCHASE);
	// }else if(unSubscribeNum<list.size()&&unSubscribeNum>0){
	// subscribe.setStatus(SubscribeStatus.PURCHASING);
	// }else{
	// subscribe.setStatus(SubscribeStatus.PURCHASEED);
	// }
	// this.subscribeDao.storeSubscribe(subscribe);
	// }

	/**
	 * 删除采购单明细
	 * 
	 * @param subscribeDetails
	 * @param subscribe
	 * @throws Exception
	 */
	public void deleteAllToolingSubscribeDetails(
			List<SubscribeDetail> subscribeDetails, Subscribe subscribe)
			throws Exception {
		// 如果所选的要删除的采购单明细中有一个对象的状态为已采购 那么删除失败
		updateSubscribeDtlStauts(subscribeDetails);
		// 如果采购单的预算编号不为空，则减去该预算编号关联的申购总费用
		if (null != subscribe.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseFeeFromBudgetDetail(
					subscribe.getBudgetNo(), subscribe.getTotalPrice());
		}
		// 删除采购明细时，将季度计划明细中的状态值为false
		for (SubscribeDetail detail : subscribeDetails) {
			if (detail.getQuarterPlanDetail() != null) {
				QuarterPlanDetail quarterPlanDetail = detail
						.getQuarterPlanDetail();
				if (quarterPlanDetail.isCreatePurchaseFlag() == true) {
					quarterPlanDetail.setCreatePurchaseFlag(false);
					quarterPlanDetailManager
							.storeQuarterPlanDetail(quarterPlanDetail);
				}
			}
		}
		
		subscribeDao.deleteAllSubscribeDetails(subscribeDetails);
		if(subscribe.getBudgetNo()!=null){
			//如果当前采购单明细的费用属于预算内的 假如某一条记录我现在不想进行预算了  那么删除以后在年度预算明细中列入采购单费用的总费用相应的减少
			BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(subscribe.getBudgetNo());
			Double allPrice=subscribeDao.getAllSubscribeDetailTotalPriceBySubscribeIdAndDetailType(subscribe.getId());
			budgetDetail.setPurchaseFee(allPrice);
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
		// 清除申购单关联的申购明细
		for (SubscribeDetail detail : subscribeDetails) {
			subscribe.getSubscribeDetails().remove(detail);
		}
		// 更新总费用
		this.updateSubscribeInformation(subscribe);
		updateSubscribeStatus(subscribe);
	}

	/**
	 * 备品备件明细记录从备件台帐而来
	 */
	public void storeSpareToolingAcount(Subscribe subscribe,
			String addSpareDetailIds) {
		String[] addSpareDetailId = null;
		if (addSpareDetailIds != null) {
			addSpareDetailId = addSpareDetailIds.split(",");
		}
		for (int i = 0; i < addSpareDetailId.length; i++) {
			SubscribeDetail detail = new SubscribeDetail();
			Spare spare = spareDao.loasSpare(Long.valueOf(addSpareDetailId[i]));
			detail.setSpare(spare);
			detail.setSubscribe(subscribe);
			detail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
			copySpareAccountToSubscribeDetail(detail, spare);// 从备件台帐复制到采购单明细中
			subscribe.getSubscribeDetails().add(detail);
			this.storeToolingAndDevice(detail);
		}
		updateSubscribeInformation(subscribe);
		updateSubscribeStatus(subscribe);
	}

	/**
	 * 从季度计划明细-->采购单明细[工装制作明细、备品备件明细、维修计划明细、技术改造明细]
	 */
	public void storToolingQuarterPlan(Subscribe subscribe,
			String addQuarterPlanIds, String detailType) {
		String[] addQuarterPlanId = null;
		if (addQuarterPlanIds != null) {
			addQuarterPlanId = addQuarterPlanIds.split(",");
		}
		
		for (int i = 0; i < addQuarterPlanId.length; i++) {
			SubscribeDetail detail = new SubscribeDetail();
			QuarterPlanDetail planDetailDetail = quarterPlanDetailManager
					.loadQuarterPlanDetail(Long.valueOf(addQuarterPlanId[i]));
			// 季度计划明细关联
			detail.setQuarterPlanDetail(planDetailDetail);
			// 关联采购单
			detail.setSubscribe(subscribe);
			if (detailType.equals("TOOLING_MAKE")) {
				detail.setDetailType(YearPlanDetailCategory.TOOLING_MAKE);
			} else if (detailType.equals("SPARE_PURCHASE")) {
				detail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
			} else if (detailType.equals("REPAIR_MAINTENANCE")) {
				detail.setDetailType(YearPlanDetailCategory.REPAIR_MAINTENANCE);
			} else if (detailType.equals("TECH_ALTER")) {
				detail.setDetailType(YearPlanDetailCategory.TECH_ALTER);
			}
			// 从季度计划复制到采购单明细中
			copyQuarterPlanDetaillToSubscribeDetail(detail, planDetailDetail);
			// 季度计划被复制到采购单明细之后 把季度计划明细中的是否创建采购单的标示至为true
			planDetailDetail.setCreatePurchaseFlag(true);
			this.storeToolingAndDevice(detail);
		}
		if(subscribe.getBudgetNo()!=null){
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(subscribe.getBudgetNo());
	        //根据工装采购单的id 获得采购单明细的费用
			Double allPrice=subscribeDao.getAllSubscribeDetailTotalPriceBySubscribeIdAndDetailType(subscribe.getId());
			budgetDetail.setPurchaseFee(allPrice);
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
		// 更新总金额
		updateSubscribeInformation(subscribe);
		// 更新采购单状态
		updateSubscribeStatus(subscribe);
	}
	
	/**
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1050表示点检报告的提交 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	@Transactional(readOnly=false)
	public void storeEventNew(int EventType, Date time, 
			int OrgId, int Status, Long UserId, 
			int DocType, int DocId, String MoreInfo){
		
		EventNew eventNew = new EventNew();
		eventNew.setEventType(EventType);		//设置EventType
		eventNew.setTime(time);
		eventNew.setStatus(0);
//		eventNew.setOrgId(OrgId);
		eventNew.setUserId(UserId);
		eventNew.setDocType(EventType);
		eventNew.setDocId(Integer.valueOf(DocId));
		eventNew.setMoreInfo(MoreInfo);
		this.eventNewDao.storeEventNew(eventNew);
		
	}
	
	/**
	 * 添加事件 zzb
	 * @param EventType 事件类型
	 * @param DocId 对象Id
	 */
	public void storeEventNew_subDetail(int EventType,int DocId,String moreInfo){
		this.storeEventNew(EventType, Calendar.getInstance().getTime(),
				1, 0, this.userManager.getUser().getId(), 1060, DocId, moreInfo);
	}
	

	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}

	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}
	/**
	 * 打印报表
	 */
	public List<SubscribeDetailView> loadSubscribeDetailView(List id,String flag) {
		return subscribeDetailViewDao.loadSubscribeDetailView(id,flag);
	}
	/**
	 * 根据申购单ID打印报表
	 * @param BillIds
	 * @return
	 */
	public List <SubscribeDetailView> loadSubscribeDetailViewByBillIds(String BillIds){
		String [] subscribeBillId=null;
		if(BillIds!=null){
			subscribeBillId=BillIds.split(",");
		}
		for(int i=0;i<subscribeBillId.length;i++){
			Subscribe subscribe = this.subscribeDao.loadSubscribe(Long.valueOf(subscribeBillId[i]));
			subscribe.setStatus(SubscribeStatus.AUDITING);    //设置申购单状态为审核中
			this.subscribeDao.storeSubscribe(subscribe);
			
		}
		return subscribeDetailViewDao.loadSubscribeDetailViewByBillIds(BillIds);
	}
	public void storeSubscribeDetail(String allSubscribeDetailId, String allSubscribeCollectBillDetailBuyer, SubscribeDetail subscribeDetail) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 根据非主键集合找明细
	 * @param arrys
	 * @param values
	 * @return
	 */
	public List<SubscribeDetail>loadDetailBykeyArry(String[] arrys,Object[] values)throws DaoException{
		return this.subscribeDao.loadDetailBykeyArry(arrys, values);
	}
	
	/**
	 * 根据非主键集合找明细
	 * @param arrys
	 * @param values
	 * @return
	 */
	public List<SubscribeDetail>loadDetailBykey(String key,Object value)throws DaoException{
		return this.subscribeDao.loadByKey(key, value);
	}
	
	/**
	 * 取得总金额
	 * @return
	 */
	public Double getSumPrice(SubscribeCollectBill bill){
		return this.subscribeDao.getSumPrice(bill);
	}
	
	/**
	 * 取得采购数量 包括入库
	 * @param bill
	 * @return
	 */
	public Integer getPurNum(SubscribeCollectBill bill){
		return subscribeDao.getPurNum(bill);
	}

 
}
