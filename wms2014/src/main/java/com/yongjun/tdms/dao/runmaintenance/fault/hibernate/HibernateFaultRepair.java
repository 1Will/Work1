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
package com.yongjun.tdms.dao.runmaintenance.fault.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.fault.FaultRepairDao;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
/**
 * <p>Title: HibernateFaultRepair
 * <p>Description: 故障维修数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.dao.runmaintenance.fault.FaultRepairDao
 */
public class HibernateFaultRepair extends BaseHibernateDao implements
		FaultRepairDao {

	public FaultRepair loadFaultRepair(Long faultRepairId) {
		return this.load(FaultRepair.class, faultRepairId);
	}

	public List<FaultRepair> loadAllFaultRepairs(Long[] faultRepairIds) {
		return this.loadAll(FaultRepair.class, faultRepairIds);
	}

	public List<FaultRepair> loadAllFaultRepairs() {
		return this.loadAll(FaultRepair.class);
	}

	public void storeFaultRepair(FaultRepair faultRepair) {
		this.store(faultRepair);
	}

	public void deleteFaultRepair(FaultRepair faultRepair) {
		this.delete(faultRepair);
	}

	public void deleteAllFaultRepairs(Collection<FaultRepair> faultRepairs) {
		this.deleteAll(faultRepairs);
	}

}
