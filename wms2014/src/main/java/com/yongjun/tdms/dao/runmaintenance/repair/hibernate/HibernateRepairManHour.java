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
import com.yongjun.tdms.dao.runmaintenance.repair.RepairManHourDao;
import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;

/**
 * <p>Title: HibernateRepairManHour
 * <p>Description: 维修工时明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateRepairManHour.java 9851 2007-12-27 10:03:46Z zbzhang $
 * @see com.yongjun.tdms.dao.runmaintenance.repair.RepairManHourDao
 */
public class HibernateRepairManHour extends BaseHibernateDao implements
		RepairManHourDao {

	public RepairManHour loadRepairManHour(Long repairManHourId) {
		return this.load(RepairManHour.class, repairManHourId);
	}

	public List<RepairManHour> loadAllRepairManHours(Long[] repairManHourIds) {
		return this.loadAll(RepairManHour.class, repairManHourIds);
	}

	public List<RepairManHour> loadAllRepairManHours() {
		return this.loadAll(RepairManHour.class);
	}

	public void storeRepairManHour(RepairManHour repairManHour) {
		this.store(repairManHour);
	}

	public void deleteRepairManHour(RepairManHour repairManHour) {
		this.delete(repairManHour);
	}

	public void deleteAllRepairManHours(Collection<RepairManHour> repairManHours) {
		this.deleteAll(repairManHours);		
	}

}
