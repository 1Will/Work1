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
package com.yongjun.tdms.service.runmaintenance.tooling.record.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingBorrowBillDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingBorrowBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingBorrowBillManager;

/**
 * @author qs
 * @version $Id: $
 */
public class DefaultToolingBorrowBillManager implements ToolingBorrowBillManager{
	private final ToolingBorrowBillDao toolingBorrowBillDao;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	private final SequenceManager sequenceManager;

	public DefaultToolingBorrowBillManager(ToolingBorrowBillDao toolingBorrowBillDao, 
			                     DeviceCardManager deviceCardManager,
			                     CodeValueManager codeValueManager,
			                     SequenceManager sequenceManager) {
		this.toolingBorrowBillDao = toolingBorrowBillDao;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		this.sequenceManager = sequenceManager;
	}

	public ToolingBorrowBill loadToolingBorrowBill(Long toolingBorrowBillId) {
		return this.toolingBorrowBillDao.loadToolingBorrowBill(toolingBorrowBillId);
	}

	public List<ToolingBorrowBill> loadAllToolingBorrowBills(Long[] toolingBorrowBillIds) {
		return this.toolingBorrowBillDao.loadAllToolingBorrowBills(toolingBorrowBillIds);
	}

	public List<ToolingBorrowBill> loadAllToolingBorrowBills() {
		return this.toolingBorrowBillDao.loadAllToolingBorrowBills();
	}

	public void storeToolingBorrowBill(ToolingBorrowBill toolingBorrowBill) {
		this.toolingBorrowBillDao.storeToolingBorrowBill(toolingBorrowBill);
	}

	public void deleteToolingBorrowBill(ToolingBorrowBill toolingBorrowBill) {
		this.toolingBorrowBillDao.deleteToolingBorrowBill(toolingBorrowBill);
	}

	public void deleteAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills) {
		this.toolingBorrowBillDao.deleteAllToolingBorrowBills(toolingBorrowBills);
	}

	public void storeToolingBorrowBill(ToolingBorrowBill toolingBorrowBill, DeviceCard tooling) {
		toolingBorrowBill.setTotalOutput(toolingBorrowBill.getProduceNum());
		storeToolingBorrowBill(toolingBorrowBill);
		tooling.setBorrower(toolingBorrowBill.getBorrower().getName());
		if (null != toolingBorrowBill.getDevice()) {
			tooling.setUsingDevice(toolingBorrowBill.getDevice().getName());
		}
		if (null == tooling.getTotalOutput()) {
			tooling.setTotalOutput(toolingBorrowBill.getProduceNum());			
		} else {
			if (null != toolingBorrowBill.getProduceNum()) {
				long totalOutputNum = tooling.getTotalOutput().longValue() + toolingBorrowBill.getProduceNum().longValue();
				tooling.setTotalOutput(new Long(totalOutputNum));
			}

		}
		if (toolingBorrowBill.isReturnFlag()) {
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
		} else {
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_BORROW_STATUS));
		}
		this.deviceCardManager.storeTooling(tooling);
	}
	
	/**
	 * 保存工装领用单,同时更新工装台帐里的"领用人"、"使用设备"、"累计产量"的值
	 * @param toolingBorrowBill  工装领用单对象
	 * @param tooling  工装对象
	 * @param oldProduceNum 工装领用单旧的"生产数量"的值
	 */
	public void storeToolingBorrowBill(ToolingBorrowBill toolingBorrowBill, DeviceCard tooling, Long oldProduceNum) {
		if (toolingBorrowBill.isNew()) {
			String billNo = (String)sequenceManager.generate("-");
			toolingBorrowBill.setBillNo(billNo);
		}
		storeToolingBorrowBill(toolingBorrowBill);
		tooling.setBorrower(toolingBorrowBill.getBorrower().getName());
		if (null != toolingBorrowBill.getDevice()) {
			tooling.setUsingDevice(toolingBorrowBill.getDevice().getName());
		}
		if (null == tooling.getTotalOutput()) {
			tooling.setTotalOutput(toolingBorrowBill.getProduceNum());
			toolingBorrowBill.setTotalOutput(toolingBorrowBill.getProduceNum());//工装累计为空，表示该领用单是第一次新建立。
		} else if (oldProduceNum != null) {
			long totalOutputNum = tooling.getTotalOutput().longValue();
			if (null != oldProduceNum) {
				totalOutputNum = totalOutputNum - oldProduceNum.longValue();     //减去原来数量
			}
			if (null != toolingBorrowBill.getProduceNum()) {
				totalOutputNum = totalOutputNum + (toolingBorrowBill.getProduceNum()).longValue();   //加上新数据
			}
			tooling.setTotalOutput(new Long(totalOutputNum));
			toolingBorrowBill.setTotalOutput(new Long(totalOutputNum));
		} else if (null == oldProduceNum) {
			System.out.println("null");
			if (null != toolingBorrowBill.getProduceNum()) {
				long totalOutputNum = tooling.getTotalOutput().longValue() + toolingBorrowBill.getProduceNum().longValue();
				System.out.println("totalOutputNum=="+totalOutputNum);
				tooling.setTotalOutput(new Long(totalOutputNum));
				toolingBorrowBill.setTotalOutput(new Long(totalOutputNum));
			}
		}
		if("TOOLING".equals(tooling.getToolingDevFlag())){
			this.deviceCardManager.storeTooling(tooling);
		}
		storeToolingBorrowBill(toolingBorrowBill);
	}

	/**
	 * 删除工装领用单，并置工装的状态为"正常"
	 * @param toolingBorrowBills  工装领用单的集合对象
	 * @param statusCode 工装的realCode
	 */
	public void deleteAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills, String realCode) {
		for (Iterator it = toolingBorrowBills.iterator(); it.hasNext();) {
			ToolingBorrowBill borrowBill = (ToolingBorrowBill)it.next();
			DeviceCard tooling = this.deviceCardManager.loadDevice(borrowBill.getTooling().getId());
			if ((null != borrowBill.getProduceNum()) && (null != tooling.getTotalOutput())) {
				long totalOutputNum = tooling.getTotalOutput().longValue();
				totalOutputNum = totalOutputNum - borrowBill.getProduceNum().longValue();
				tooling.setTotalOutput(new Long(totalOutputNum));
			}
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(realCode));
			this.deleteToolingBorrowBill(borrowBill);
			this.deviceCardManager.storeTooling(tooling);
		}
	}

	public void disabledAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills) {
		for(ToolingBorrowBill dtl: toolingBorrowBills){
			dtl.setDisabled(true);
			toolingBorrowBillDao.storeToolingBorrowBill(dtl);
		}
		
		
	}

	public void enabledAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills) {
		
		for(ToolingBorrowBill dtl: toolingBorrowBills){
			dtl.setDisabled(false);
			toolingBorrowBillDao.storeToolingBorrowBill(dtl);
		}
	}

	public List<Long> getOldDeviceIds(String toolingDev_Flag, Boolean returnFlag) {
		return this.toolingBorrowBillDao.getOldDeviceIds(toolingDev_Flag, returnFlag);
	}
		


}
