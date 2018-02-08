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
package com.yongjun.tdms.service.runmaintenance.fault.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.fault.FaultRepairDao;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
/**
 * <p>Title: DefaultFaultRepairManager
 * <p>Description: 故障维修业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager
 */

public class DefaultFaultRepairManager implements FaultRepairManager {
	private final FaultRepairDao faultRepairDao;
	private BudgetDetailManager budgetDetailManager;
	
	public DefaultFaultRepairManager(FaultRepairDao faultRepairDao) {
		this.faultRepairDao = faultRepairDao;
	}
	public FaultRepair loadFaultRepair(Long faultRepairId) {
		return this.faultRepairDao.loadFaultRepair(faultRepairId);
	}

	public List<FaultRepair> loadAllFaultRepairs(Long[] faultRepairIds) {
		return this.faultRepairDao.loadAllFaultRepairs(faultRepairIds);
	}

	public List<FaultRepair> loadAllFaultRepairs() {
		return this.faultRepairDao.loadAllFaultRepairs();
	}

	public void storeFaultRepair(FaultRepair faultRepair) {
		this.faultRepairDao.storeFaultRepair(faultRepair);
		//加上该预算编号关联的已发生费用
		if (null != faultRepair.getBudgetNo()) {
			this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(faultRepair.getBudgetNo(), faultRepair.getAllFee());
		}
	}

	public void deleteFaultRepair(FaultRepair faultRepair) {
		this.faultRepairDao.deleteFaultRepair(faultRepair);
	}

	public void deleteAllFaultRepairs(Collection<FaultRepair> faultRepairs) {
		this.faultRepairDao.deleteAllFaultRepairs(faultRepairs);
	}
	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}
	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}

}
