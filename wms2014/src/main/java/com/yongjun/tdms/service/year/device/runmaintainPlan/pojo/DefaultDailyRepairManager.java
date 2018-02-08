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
package com.yongjun.tdms.service.year.device.runmaintainPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.year.device.runmaintainPlan.DailyRepairDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DailyRepair;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.DailyRepairManager;

/**
 * 
 * <p>Title: DefaultDailyRepairManager
 * <p>Description: 运维计划的日常维修业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.runmaintainPlan.DailyRepairManager
 * @version $Id:$
 */
public class DefaultDailyRepairManager extends CalculateFeeManager implements DailyRepairManager {
	
	private DailyRepairDao dailyRepairDao;
	
	public DefaultDailyRepairManager(DailyRepairDao dailyRepairDao) {
		this.dailyRepairDao = dailyRepairDao;
	}
	
	public DailyRepair loadDailyRepair(Long dailyRepairId) {
		return this.dailyRepairDao.loadDailyRepair(dailyRepairId);
	}

	public List<DailyRepair> loadAllDailyRepairs(Long[] dailyRepairIds) {
		return this.dailyRepairDao.loadAllDailyRepairs(dailyRepairIds);
	}

	public List<DailyRepair> loadAllDailyRepairs() {
		return this.dailyRepairDao.loadAllDailyRepairs();
	}

	public void storeDailyRepair(DailyRepair dailyRepair) {
		this.dailyRepairDao.storeDailyRepair(dailyRepair);
		//更新与其相关联的运维计划以及运维计划明细的费用
		this.calculateDailyRepairFee(dailyRepair.getRunmaintainPlanDetail());
	}

	public void deleteDailyRepair(DailyRepair dailyRepair) {
		this.dailyRepairDao.deleteDailyRepair(dailyRepair);
	}

	public void deleteAllDailyRepairs(RunmaintainPlanDetail runmaintainPlanDetail, Collection<DailyRepair> dailyRepairs) {
		this.dailyRepairDao.deleteAllDailyRepairs(dailyRepairs);
		for (DailyRepair dailyRepair : dailyRepairs) {
			runmaintainPlanDetail.getDailyRepairs().remove(dailyRepair);
		}
        //更新与其相关联的运维计划以及运维计划明细的费用
		this.calculateDailyRepairFee(runmaintainPlanDetail);
	}

}
