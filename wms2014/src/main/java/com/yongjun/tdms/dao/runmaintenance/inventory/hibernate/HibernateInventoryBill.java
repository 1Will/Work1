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
import com.yongjun.tdms.dao.runmaintenance.inventory.InventoryBillDao;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;

/**
 * <p>Title: HibernateInventoryBill
 * <p>Description: 清查单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.dao.runmaintenance.inventory.InventoryBillDao
 */

public class HibernateInventoryBill extends BaseHibernateDao implements
		InventoryBillDao {

	public InventoryBill loadInventoryBill(Long inventoryBillId) {
		return this.load(InventoryBill.class, inventoryBillId);
	}

	public List<InventoryBill> loadInventoryBills(Long[] inventoryBillIds) {
		return this.loadAll(InventoryBill.class, inventoryBillIds);
	}

	public List<InventoryBill> loadInventoryBills() {
		return this.loadAll(InventoryBill.class);
	}

	public void storeInventoryBill(InventoryBill inventoryBill) {
		this.store(inventoryBill);
	}

	public void deleteInventoryBill(InventoryBill inventoryBill) {
		this.delete(inventoryBill);
	}

	public void deleteAllInventoryBills(Collection<InventoryBill> inventoryBills) {
		this.deleteAll(inventoryBills);
	}

}
