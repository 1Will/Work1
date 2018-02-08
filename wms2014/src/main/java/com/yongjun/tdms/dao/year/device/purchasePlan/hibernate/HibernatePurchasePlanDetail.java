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
package com.yongjun.tdms.dao.year.device.purchasePlan.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanDetailDao;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;

/**
 * 
 * <p>Title: HibernatePurchasePlanDetail
 * <p>Description: 设备年度采购计划明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanDetailDao
 * @version $Id:$
 */
public class HibernatePurchasePlanDetail extends BaseHibernateDao implements
		PurchasePlanDetailDao {

	public PurchasePlanDetail loadPurchasePlanDetail(Long purchasePlanDetailId) {
		return this.load(PurchasePlanDetail.class, purchasePlanDetailId);
	}

	public List<PurchasePlanDetail> loadAllPurchasePlanDetails(Long[] purchasePlanDetailIds) {
		return this.loadAll(PurchasePlanDetail.class, purchasePlanDetailIds);
	}

	public List<PurchasePlanDetail> loadAllPurchasePlanDetails() {
		return this.loadAll(PurchasePlanDetail.class);
	}

	public void storePurchasePlanDetail(PurchasePlanDetail purchasePlanDetail) {
		this.store(purchasePlanDetail);
	}

	public void deletePurchasePlanDetail(PurchasePlanDetail purchasePlanDetail) {
		this.delete(purchasePlanDetail);
	}

	public void deleteAllPurchasePlanDetails(Collection<PurchasePlanDetail> purchasePlanDetails) {
		this.deleteAll(purchasePlanDetails);
	}

}
