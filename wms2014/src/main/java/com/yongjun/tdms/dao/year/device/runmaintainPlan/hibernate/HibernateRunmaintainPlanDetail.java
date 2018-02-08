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
package com.yongjun.tdms.dao.year.device.runmaintainPlan.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDetailDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;

/**
 * 
 * <p>Title: HibernateRunmaintainPlanDetail
 * <p>Description: 设备运维计划明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDetailDao
 * @version $Id:$
 */
public class HibernateRunmaintainPlanDetail extends BaseHibernateDao implements
		RunmaintainPlanDetailDao {
	public RunmaintainPlanDetail loadRunmaintainPlanDetail(
			Long runmaintainPlanDetailId) {
		return this.load(RunmaintainPlanDetail.class, runmaintainPlanDetailId);
	}

	public List<RunmaintainPlanDetail> loadAllRunmaintainPlanDetails(
			Long[] runmaintainPlanDetailIds) {
		return this.loadAll(RunmaintainPlanDetail.class,
				runmaintainPlanDetailIds);
	}

	public List<RunmaintainPlanDetail> loadAllRunmaintainPlanDetails() {
		return this.loadAll(RunmaintainPlanDetail.class);
	}

	public void storeRunmaintainPlanDetail(
			RunmaintainPlanDetail runmaintainPlanDetail) {
		this.store(runmaintainPlanDetail);
	}

	public void deleteRunmaintainPlanDetail(
			RunmaintainPlanDetail runmaintainPlanDetail) {
		this.delete(runmaintainPlanDetail);
	}

	public void deleteAllRunmaintainPlanDetails(
			Collection<RunmaintainPlanDetail> runmaintainPlanDetails) {
		this.deleteAll(runmaintainPlanDetails);
	}
}
