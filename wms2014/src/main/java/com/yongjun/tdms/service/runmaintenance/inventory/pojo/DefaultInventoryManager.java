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
package com.yongjun.tdms.service.runmaintenance.inventory.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.inventory.InventoryBillDao;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryManager;

/**
 * @author qs
 * @version $Id: DefaultInventoryManager.java 10842 2008-02-01 02:23:10Z qsun $
 */
public class DefaultInventoryManager extends BaseManager implements
		InventoryManager {
	private final InventoryBillDao inventorybillDao;
	private final SequenceManager sequenceManager;

	public DefaultInventoryManager(InventoryBillDao inventorybillDao
			,SequenceManager sequenceManager) {
		this.inventorybillDao = inventorybillDao;
		this.sequenceManager=sequenceManager;

	}

	public List<InventoryBill> loadAllInventoryBill(Long[] InventoryIds) {
		return this.inventorybillDao.loadInventoryBills(InventoryIds);

	}

	public void deleteAllInventoryBill(List inventorybills) {
		this.inventorybillDao.deleteAllInventoryBills(inventorybills);

	}

	public InventoryBill loadInventoryBill(Long inventorybillId) {
		return this.inventorybillDao.loadInventoryBill(inventorybillId);

	}

	public void storeInventoryBill(InventoryBill inventorybill) {
		if (inventorybill.isNew()) {
			String billNo = (String)sequenceManager.generate("-");
			inventorybill.setBillNo(billNo);
		}
		this.inventorybillDao.storeInventoryBill(inventorybill);

	}
	public void disabledAllInventoryBills(Collection<InventoryBill> inventoryBills) {
		for (InventoryBill oil : inventoryBills) {
			oil.setDisabled(true);
			this.inventorybillDao.storeInventoryBill(oil);
		}
	}

	public void enabledAllInventoryBills(Collection<InventoryBill> inventoryBills) {
		for (InventoryBill oil : inventoryBills) {
			oil.setDisabled(false);
			this.inventorybillDao.storeInventoryBill(oil);
		}		
	}

}
