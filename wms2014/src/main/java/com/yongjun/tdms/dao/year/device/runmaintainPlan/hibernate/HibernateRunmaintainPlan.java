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
import com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;

/**
 * 
 * <p>Title: HibernateRunmaintainPlan
 * <p>Description: 设备运维计划数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDao
 * @version $Id:$
 */
public class HibernateRunmaintainPlan extends BaseHibernateDao implements
		RunmaintainPlanDao {

	public RunmaintainPlan loadRunmaintainPlan(Long runmaintainPlanId) {
		return this.load(RunmaintainPlan.class, runmaintainPlanId);
	}

	public List<RunmaintainPlan> loadAllRunmaintainPlans(Long[] runmaintainPlanIds) {
		return this.loadAll(RunmaintainPlan.class, runmaintainPlanIds);
	}

	public List<RunmaintainPlan> loadAllRunmaintainPlans() {
		return this.loadAll(RunmaintainPlan.class);
	}

	public void storeRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		this.store(runmaintainPlan);
	}

	public void deleteRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		this.delete(runmaintainPlan);
	}

	public void deleteAllRunmaintainPlans(Collection<RunmaintainPlan> runmaintainPlans) {
		this.deleteAll(runmaintainPlans);
	}

}
