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
package com.yongjun.tdms.dao.year.repair.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDetailDao;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: HibernateRepairPlanAndProcDetail
 * <p>Description: 大项修计划和实施明细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @see com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDetailDao
 * @version $Id:  $
 */
public class HibernateRepairPlanAndProcDetail extends BaseHibernateDao
		implements RepairPlanAndProcDetailDao {

	public RepairPlanAndProcDetail loadRepairPlanAndProcDetail(Long planOrProcDetailId) {
		return this.load(RepairPlanAndProcDetail.class, planOrProcDetailId);
	}

	public List<RepairPlanAndProcDetail> loadAllRepairPlanAndProcDetails(Long[] planOrProcDetailIds) {
		return this.loadAll(RepairPlanAndProcDetail.class, planOrProcDetailIds);
	}

	public List<RepairPlanAndProcDetail> loadAllRepairPlanAndProcDetails() {
		return this.loadAll(RepairPlanAndProcDetail.class);
	}

	public void storeRepairPlanAndProcDetail(RepairPlanAndProcDetail planAndProcDetail) {
		this.store(planAndProcDetail);
	}

	public void deleteRepairPlanAndProcDetail(RepairPlanAndProcDetail planAndProcDetail) {
		this.delete(planAndProcDetail);
	}

	public void deleteAllRepairPlanAndProcDetails(Collection<RepairPlanAndProcDetail> planAndProcDetails) {
		this.deleteAll(planAndProcDetails);
	}

}
