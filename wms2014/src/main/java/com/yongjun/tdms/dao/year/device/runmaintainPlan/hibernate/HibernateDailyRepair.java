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
import com.yongjun.tdms.dao.year.device.runmaintainPlan.DailyRepairDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DailyRepair;

/**
 * 
 * <p>Title: HibernateDailyDao
 * <p>Description: 运维计划的日常维修数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.device.runmaintainPlan.DailyRepairDao
 * @version $Id:$
 */
public class HibernateDailyRepair extends BaseHibernateDao implements
		DailyRepairDao {

	public DailyRepair loadDailyRepair(Long dailyRepairId) {
		return this.load(DailyRepair.class, dailyRepairId);
	}

	public List<DailyRepair> loadAllDailyRepairs(Long[] dailyRepairIds) {
		return this.loadAll(DailyRepair.class, dailyRepairIds);
	}

	public List<DailyRepair> loadAllDailyRepairs() {
		return this.loadAll(DailyRepair.class);
	}

	public void storeDailyRepair(DailyRepair dailyRepair) {
		this.store(dailyRepair);
	}

	public void deleteDailyRepair(DailyRepair dailyRepair) {
		this.delete(dailyRepair);
	}

	public void deleteAllDailyRepairs(Collection<DailyRepair> dailyRepairs) {
		this.deleteAll(dailyRepairs);
	}

}
