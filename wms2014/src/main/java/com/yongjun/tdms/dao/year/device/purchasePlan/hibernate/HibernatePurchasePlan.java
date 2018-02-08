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
import com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanDao;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;

/**
 * 
 * <p>Title: HibernatePurchasePlan
 * <p>Description: 设备年度采购计划数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanDao
 * @version $Id:$
 */
public class HibernatePurchasePlan extends BaseHibernateDao implements
		PurchasePlanDao {

	public PurchasePlan loadPurchasePlan(Long purchasePlanId) {
		return this.load(PurchasePlan.class, purchasePlanId);
	}

	public List<PurchasePlan> loadAllPurchasePlans(Long[] purchasePlanIds) {
		return this.loadAll(PurchasePlan.class, purchasePlanIds);
	}

	public List<PurchasePlan> loadAllPurchasePlans() {
		return this.loadAll(PurchasePlan.class);
	}

	public void storePurchasePlan(PurchasePlan purchasePlan) {
		this.store(purchasePlan);
	}

	public void deletePurchasePlan(PurchasePlan purchasePlan) {
		this.delete(purchasePlan);
	}

	public void deleteAllPurchasePlans(Collection<PurchasePlan> purchasePlans) {
		this.deleteAll(purchasePlans);
	}

}
