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

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanDao;
import com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanViewDao;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanView;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

/**
 * 
 * <p>Title: DefaultPurchasePlanManager
 * <p>Description: 设备年度采购计划业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager
 * @version $Id:$
 */
public class DefaultPurchasePlanManager implements PurchasePlanManager {
	
	private final PurchasePlanDao purchasePlanDao;
	private final SequenceManager sequenceManager;
	private final PurchasePlanViewDao purchasePlanViewDao;
	
	public DefaultPurchasePlanManager(PurchasePlanDao purchasePlanDao,
			SequenceManager sequenceManager,PurchasePlanViewDao purchasePlanViewDao) {
		this.purchasePlanDao = purchasePlanDao;
		this.sequenceManager = sequenceManager;
		this.purchasePlanViewDao = purchasePlanViewDao;
	}
	
	public PurchasePlan loadPurchasePlan(Long purchasePlanId) {
		return this.purchasePlanDao.loadPurchasePlan(purchasePlanId);
	}

	public List<PurchasePlan> loadAllPurchasePlans(Long[] purchasePlanIds) {
		return this.purchasePlanDao.loadAllPurchasePlans(purchasePlanIds);
	}

	public List<PurchasePlan> loadAllPurchasePlans() {
		return this.purchasePlanDao.loadAllPurchasePlans();
	}

	public void storePurchasePlan(PurchasePlan purchasePlan) {
		if (purchasePlan.isNew()) {
			//生成采购计划编号
			String planNo = (String)sequenceManager.generate("-");
			purchasePlan.setPlanNo(planNo);
		}
		this.purchasePlanDao.storePurchasePlan(purchasePlan);
	}

	public void deletePurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlanDao.deletePurchasePlan(purchasePlan);
	}

	public void deleteAllPurchasePlans(Collection<PurchasePlan> purchasePlans) {
		this.purchasePlanDao.deleteAllPurchasePlans(purchasePlans);
	}

	public void disabledAllPurchasePlans(Collection<PurchasePlan> purchasePlans) {
		for (PurchasePlan plan : purchasePlans) {
			//失效改设备年度采购计划
			plan.setDisabled(true);
			this.purchasePlanDao.storePurchasePlan(plan);
		}
	}

	public void enabledAllPurchasePlans(Collection<PurchasePlan> purchasePlans) {
		for (PurchasePlan plan : purchasePlans) {
			//有效改设备年度采购计划
			plan.setDisabled(false);
			this.purchasePlanDao.storePurchasePlan(plan);
		}
	}

	public void calulateAllFeeForPurchasePlan(PurchasePlan purchasePlan) {
		double allFee = 0.0;                //用来累计采购计划的总费用
		for (PurchasePlanDetail detail : purchasePlan.getPurchasePlanDetail()) {
			//累计采购计划明细的费用
			allFee += detail.getAllPrice().doubleValue();
		}
		//更新采购计划的总费用
		purchasePlan.setPlanAllFee(Double.valueOf(allFee));
		this.purchasePlanDao.storePurchasePlan(purchasePlan);
	}

	public List<PurchasePlanView> loadAllPurchasePlanView(String[] array) throws HibernateException{
		return this.purchasePlanViewDao.loadAllPurchasePlanView(array);
	}
}
