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
package com.yongjun.tdms.dao.year.tooling.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.RepairMaintenanceDetailDao;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;

/**
 * 
 * <p>Title: YearPlanDao
 * <p>Description: 维修保养明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.tooling.RepairMaintenanceDetailDao
 * @version $Id:$
 */
public class HibernateRepairMaintenanceDetail extends BaseHibernateDao
		implements RepairMaintenanceDetailDao {

	public RepairMaintenanceDetail loadRepairMaintenanceDetail(Long repairMaintenanceDetailId) {
		return this.load(RepairMaintenanceDetail.class, repairMaintenanceDetailId);
	}

	public List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails(Long[] repairMaintenanceDetailIds) {
		return this.loadAll(RepairMaintenanceDetail.class, repairMaintenanceDetailIds);
	}

	public List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails() {
		return this.loadAll(RepairMaintenanceDetail.class);
	}

	public void storeRepairMaintenanceDetail(RepairMaintenanceDetail repairMaintenanceDetail) {
		this.store(repairMaintenanceDetail);
	}

	public void deleteRepairMaintenanceDetail(RepairMaintenanceDetail repairMaintenanceDetail) {
		this.delete(repairMaintenanceDetail);
	}

	public void deleteAllRepairMaintenanceDetail(Collection<RepairMaintenanceDetail> repairMaintenanceDetails) {
		this.deleteAll(repairMaintenanceDetails);
	}

}
