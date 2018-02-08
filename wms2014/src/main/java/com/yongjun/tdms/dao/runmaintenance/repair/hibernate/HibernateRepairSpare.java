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
import com.yongjun.tdms.dao.runmaintenance.repair.RepairSpareDao;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;

/**
 * <p>Title: RepairItemDao
 * <p>Description: 维修内容明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateRepairSpare.java 9886 2007-12-28 03:03:41Z qsun $
 */
public class HibernateRepairSpare extends BaseHibernateDao implements
		RepairSpareDao {

	public List<RepairSpare> loadRepairSpares(Long[] spareIds) {
		return this.loadAll(RepairSpare.class,spareIds);
	}

	public void deleteAllRepairSpare(Collection<RepairSpare> repairSpare) {
		this.deleteAll(repairSpare);
	}

	public void storeRepairSpare(RepairSpare repairSpare) {
		this.store(repairSpare);
	}

	public RepairSpare loadRepairSpare(Long repairSpareId) {
		return this.load(RepairSpare.class,repairSpareId);
	}

}
