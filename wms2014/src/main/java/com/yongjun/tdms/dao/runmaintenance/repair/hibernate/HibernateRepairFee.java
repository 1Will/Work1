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
import com.yongjun.tdms.dao.runmaintenance.repair.RepairFeeDao;
import com.yongjun.tdms.model.runmaintenance.repair.RepairFee;

/**
 * <p>Title: HibernateRepairFee
 * <p>Description: 维修工具明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateRepairFee.java 9827 2007-12-27 06:18:45Z wzou $
 */
public class HibernateRepairFee extends BaseHibernateDao implements
		RepairFeeDao {

	public RepairFee loadRepairFee(Long repairFeeId) {
		return this.load(RepairFee.class,repairFeeId);
	}

	public void storeRepairFee(RepairFee repairFee) {
		this.store(repairFee);
	}

	public void deleteAllRepairItem(Collection<RepairFee> RepairFees) {
		this.deleteAll(RepairFees);
	}

	public List<RepairFee> loadAllRepairFees(Long[] feeIds) {
		return this.loadAll(RepairFee.class,feeIds);
	}

}
