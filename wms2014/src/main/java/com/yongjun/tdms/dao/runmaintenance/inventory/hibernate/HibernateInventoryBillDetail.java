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
package com.yongjun.tdms.dao.runmaintenance.inventory.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.inventory.InventoryBillDetailDao;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBillDetail;
/**
 * <p>Title: HibernateInventoryBillDetail
 * <p>Description: 清查单明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.dao.runmaintenance.inventory.InventoryBillDetailDao
 */
public class HibernateInventoryBillDetail extends BaseHibernateDao implements
		InventoryBillDetailDao {

	public InventoryBillDetail loadInventoryBillDetail(
			Long inventoryBillDetailId) {
		return this.load(InventoryBillDetail.class, inventoryBillDetailId);
	}

	public List<InventoryBillDetail> loadAllInventoryBillDetails(
			Long[] inventoryBillDetailIds) {
		return this.loadAll(InventoryBillDetail.class, inventoryBillDetailIds);
	}

	public List<InventoryBillDetail> loadAllInventoryBillDetails() {
		return this.loadAll(InventoryBillDetail.class);
	}

	public void storeInventoryBillDetail(InventoryBillDetail inventoryBillDetail) {
		this.store(inventoryBillDetail);
	}

	public void deleteInventoryBillDetail(
			InventoryBillDetail inventoryBillDetail) {
		this.delete(inventoryBillDetail);
	}

	public void deleteAllInventoryBillDetails(
			Collection<InventoryBillDetail> inventoryBillDetails) {
		this.deleteAll(inventoryBillDetails);
	}

}
