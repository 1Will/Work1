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
package com.yongjun.tdms.service.runmaintenance.fault.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.fault.FaultBillDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultBillManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;

/**
 * @author qs
 * @version $Id: $
 */
public class DefaultFaultBillManager implements FaultBillManager {
	private final FaultBillDao faultBillDao;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	private final SequenceManager sequenceManager;
	private FaultRepairManager faultRepairManager;
	
	public DefaultFaultBillManager(FaultBillDao faultBillDao,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager,
			SequenceManager sequenceManager) {
		this.faultBillDao = faultBillDao;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		this.sequenceManager = sequenceManager;
	}

	public FaultBill loadFaultBill(Long faultBillId) {
		return this.faultBillDao.loadFaultBill(faultBillId);
	}

	public List<FaultBill> loadAllFaultBills(Long[] faultBillIds) {
		return this.faultBillDao.loadAllFaultBills(faultBillIds);
	}

	public List<FaultBill> loadAllFaultBills() {
		return this.faultBillDao.loadAllFaultBills();
	}

	public void storeFaultBill(FaultBill faultBill) {
		this.faultBillDao.storeFaultBill(faultBill);
	}

	public void deleteFaultBill(FaultBill faultBill) {
		this.faultBillDao.deleteFaultBill(faultBill);
	}

	public void deleteAllFaultBill(Collection<FaultBill> faultBills) {
		for (Iterator it = faultBills.iterator(); it.hasNext();) {
			FaultBill faultBill = (FaultBill)it.next();
			DeviceCard asset = this.deviceCardManager.loadDevice(faultBill.getToolingDev().getId());
			if (null != asset&& asset.getToolingDevFlag()==SysModel.TOOLING) {
				asset.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
			    this.deviceCardManager.storeTooling(asset);
			}else{
				asset.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_DISCARD_STATUS));
				this.deviceCardManager.storeTooling(asset);
			}
			for (FaultRepair faultRepair : faultBill.getFaultRepair()) {
				
			}
			this.faultBillDao.deleteFaultBill(faultBill);
		}
	}

	public void storeFaultBill(FaultBill faultBill, DeviceCard asset) {
		if (faultBill.isNew()) {
			String billNo = (String)sequenceManager.generate("-");
			faultBill.setBillNo(billNo);
		}
		this.storeFaultBill(faultBill);
		if(asset!=null){
			if(faultBill.getToolingDevFlag().equals(SysModel.TOOLING)){
				if (faultBill.isFaultFlag()) {
					asset.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
				} else {
					asset.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_CHANGE_STATUS));
				}
				this.deviceCardManager.storeTooling(asset);
			}else{
				if (faultBill.isFaultFlag()) {
					asset.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_NORMAL_STATUS));
				} else {
					asset.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_REPAIR_STATUS));
				}
				this.deviceCardManager.storeDevice(asset);
			}
		}
	}

	public void disabledAllFaultBills(Collection<FaultBill> faultBills) {
		for (FaultBill f : faultBills) {
			f.setDisabled(true);
			this.faultBillDao.storeFaultBill(f);
		}
	}

	public void enabledAllFaultBills(Collection<FaultBill> faultBills) {
		for (FaultBill f : faultBills) {
			f.setDisabled(false);
			this.faultBillDao.storeFaultBill(f);
		}
	}

	public FaultRepairManager getFaultRepairManager() {
		return faultRepairManager;
	}

	public void setFaultRepairManager(FaultRepairManager faultRepairManager) {
		this.faultRepairManager = faultRepairManager;
	}

}
