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
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDao;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;

/**
 * <p>Title: HibernateRepairPlanAndProc
 * <p>Description: 大项修计划和实施数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @see com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDao
 * @version $Id:  $
 */
public class HibernateRepairPlanAndProc extends BaseHibernateDao implements RepairPlanAndProcDao {

	public RepairPlanAndProc loadRepairPlanOrProc(Long planOrProcId) {
		return this.load(RepairPlanAndProc.class, planOrProcId);
	}

	public List<RepairPlanAndProc> loadAllRepairPlanOrProcs(Long[] planOrProcIds) {
		return this.loadAll(RepairPlanAndProc.class, planOrProcIds);
	}

	public List<RepairPlanAndProc> loadAllRepairPlanOrProcs() {
		return this.loadAll(RepairPlanAndProc.class);
	}

	public void storeRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc) {
		this.store(repairPlanOrProc);
	}

	public void deleteRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc) {
		this.delete(repairPlanOrProc);
	}

	public void deleteAllRepairPlanOrProcs(Collection<RepairPlanAndProc> repairPlanOrProcs) {
		this.deleteAll(repairPlanOrProcs);
	}



}
