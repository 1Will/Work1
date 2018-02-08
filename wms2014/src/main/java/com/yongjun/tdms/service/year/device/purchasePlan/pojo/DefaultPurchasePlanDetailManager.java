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
package com.yongjun.tdms.service.year.device.purchasePlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanDetailDao;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanDetailManager;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

/**
 * 
 * <p>Title: DefaultPurchaseDetailManager
 * <p>Description: 设备年度采购计划明细业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanDetailManager
 * @version $Id:$
 */
public class DefaultPurchasePlanDetailManager implements PurchasePlanDetailManager {
	
	private final PurchasePlanDetailDao purchasePlanDetailDao;
	private final PurchasePlanManager purchasePlanManager;
	
	public DefaultPurchasePlanDetailManager(PurchasePlanDetailDao purchasePlanDetailDao,
			PurchasePlanManager purchasePlanManager) {
		this.purchasePlanDetailDao = purchasePlanDetailDao;
		this.purchasePlanManager = purchasePlanManager;
	}
	
	public PurchasePlanDetail loadPurchasePlanDetail(Long purchasePlanDetailId) {
		return this.purchasePlanDetailDao.loadPurchasePlanDetail(purchasePlanDetailId);
	}

	public List<PurchasePlanDetail> loadAllPurchasePlanDetails(
			Long[] purchasePlanDetailIds) {
		return this.purchasePlanDetailDao.loadAllPurchasePlanDetails(purchasePlanDetailIds);
	}

	public List<PurchasePlanDetail> loadAllPurchasePlanDetails() {
		return this.purchasePlanDetailDao.loadAllPurchasePlanDetails();
	}

	public void storePurchasePlanDetail(PurchasePlanDetail purchasePlanDetail) {
		this.purchasePlanDetailDao.storePurchasePlanDetail(purchasePlanDetail);
		//更新采购计划的总费用
		this.purchasePlanManager.calulateAllFeeForPurchasePlan(purchasePlanDetail.getPurchasePlan());
	}

	public void deletePurchasePlanDetail(PurchasePlanDetail purchasePlanDetail) {
		this.purchasePlanDetailDao.deletePurchasePlanDetail(purchasePlanDetail);
	}

	public void deleteAllPurchasePlanDetails(PurchasePlan purchasePlan,
			Collection<PurchasePlanDetail> purchasePlanDetails) {
		this.purchasePlanDetailDao.deleteAllPurchasePlanDetails(purchasePlanDetails);
		 //清除采购计划关联的计划明细
		for (PurchasePlanDetail detail : purchasePlanDetails) {
			purchasePlan.getPurchasePlanDetail().remove(detail);
		}
		this.purchasePlanManager.calulateAllFeeForPurchasePlan(purchasePlan);
	}

}
