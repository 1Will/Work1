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
import com.yongjun.tdms.dao.runmaintenance.repair.RepairItemDao;
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;

/**
 * <p>Title: HibernateRepairItem
 * <p>Description: 维修内容明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateRepairItem.java 9827 2007-12-27 06:18:45Z wzou $
 */
public class HibernateRepairItem extends BaseHibernateDao implements
		RepairItemDao {

	public RepairItem loadRepairItem(Long repairItemId) {
		return this.load(RepairItem.class,repairItemId);
	}

	public void storeRepairItem(RepairItem repairItem) {
		this.store(repairItem);
	}

	public void deleteAllRepairItem(Collection<RepairItem> RepairItems) {
		this.deleteAll(RepairItems);
	}

	public List<RepairItem> loadAllRepairItems(Long[] itemIds) {
		return this.loadAll(RepairItem.class,itemIds);
	}

}
