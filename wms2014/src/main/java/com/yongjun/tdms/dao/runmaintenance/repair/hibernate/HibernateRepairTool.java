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
package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.repair.RepairToolDao;
import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;

/**
 * <p>Title: HibernateRepairTool
 * <p>Description: 维修工具明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateRepairTool.java 9833 2007-12-27 06:56:16Z zbzhang $
 * @see com.yongjun.tdms.dao.runmaintenance.repair.RepairToolDao
 */
public class HibernateRepairTool extends BaseHibernateDao implements
		RepairToolDao {

	public RepairTool loadRepairTool(Long repairToolId) {
		return this.load(RepairTool.class, repairToolId);
	}

	public List<RepairTool> loadAllRepairTools(Long[] repairToolIds) {
		return this.loadAll(RepairTool.class, repairToolIds);
	}

	public List<RepairTool> loadAllRepairTools() {
		return this.loadAll(RepairTool.class);
	}

	public void storeRepairTool(RepairTool repairTool) {
		this.store(repairTool);
	}

	public void deleteRepairTool(RepairTool repairTool) {
		this.delete(repairTool);
	}

	public void deleteAllRepairTools(Collection<RepairTool> repairTools) {
		this.deleteAll(repairTools);
	}

}
