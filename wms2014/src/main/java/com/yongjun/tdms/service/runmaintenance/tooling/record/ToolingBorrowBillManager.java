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
package com.yongjun.tdms.service.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingBorrowBill;

/**
 * @author qs
 * @version $Id: $
 */
@Transactional(readOnly = true)
public interface ToolingBorrowBillManager {
	ToolingBorrowBill loadToolingBorrowBill(Long toolingBorrowBillId);
	
	List<ToolingBorrowBill> loadAllToolingBorrowBills(Long[] toolingBorrowBillIds);
	
	List<ToolingBorrowBill> loadAllToolingBorrowBills();
	
	@Transactional
	void storeToolingBorrowBill(ToolingBorrowBill toolingBorrowBill);
	
	@Transactional
	void storeToolingBorrowBill(ToolingBorrowBill toolingBorrowBill, DeviceCard tooling);
	
	@Transactional
	void storeToolingBorrowBill(ToolingBorrowBill toolingBorrowBill, DeviceCard tooling, Long oldProduceNum);
	
	@Transactional
	void deleteToolingBorrowBill(ToolingBorrowBill toolingBorrowBill);
	
	@Transactional
	void deleteAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills);
	
	@Transactional
	void deleteAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills, String statusCode);
	
	@Transactional
	void disabledAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills);
	
	@Transactional
	void enabledAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBill);
	
	/**
	 * 设备领用 根据toolingDevFalg,returnFlag  收集需要过滤的设备ID集合
	 * @return list
	 */
	List<Long> getOldDeviceIds(String toolingDev_Flag,Boolean returnFlag);
	
}
