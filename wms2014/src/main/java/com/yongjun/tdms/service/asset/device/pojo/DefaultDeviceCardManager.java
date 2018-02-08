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
package com.yongjun.tdms.service.asset.device.pojo;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.asset.device.DeviceCardDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;


import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.job.WfJobManager;


/**
 * @author qs
 * @version $Id: DefaultDeviceCardManager.java 11057 2008-02-22 01:39:21Z zbzhang $
 */
public class DefaultDeviceCardManager extends BaseManager implements DeviceCardManager {

	private final DeviceCardDao deviceCardDao;
	private final WfJobManager wfJobManager;
	private final CodeValueManager codeValueManager;
	private final UserManager userManager;
	public DefaultDeviceCardManager(DeviceCardDao deviceCardDao,
			 WfJobManager wfJobManager,
			 CodeValueManager codeValueManager,
			 UserManager userManager) {
		this.deviceCardDao = deviceCardDao;
		this.wfJobManager = wfJobManager;
		this.codeValueManager = codeValueManager;
		this.userManager = userManager;
	}

	public void storeDevice(DeviceCard device) {
		if (device.isNew()) {
			String assetCode = device.getDeviceType().getCode();
			String maxDeviceNo = deviceCardDao.getMaxDeviceNoByAssetCode(assetCode +"-%");
			logger.debug("get max code is: " + maxDeviceNo);
			String deviceNo = parseAndCalculateDeviceNo(device.getDeviceType().getCode(), maxDeviceNo);
			device.setDeviceNo(deviceNo);
		}
		
		if (assetNoIsEmpty(device.getAssetNo())) {
			device.setAssetNo(device.getDeviceNo());
		}
		deviceCardDao.storeDevice(device);
	}
	
	private boolean assetNoIsEmpty(String assetNo) {
		return (null == assetNo || StringUtils.isEmpty(assetNo));
	}
	
	public String parseAndCalculateDeviceNo(String typeCode, String maxDeviceNo) {
		String pattern = "000000";
		Format formatter = NumberFormat.getIntegerInstance();
		formatter = formatter = new DecimalFormat(pattern);
		if (null == maxDeviceNo) {
			return  (new StringBuilder())
			.append(typeCode).append("-").append(
					formatter.format(0)).toString();
		}
		logger.debug("device type code is : [" + typeCode + "]");
		String deviceNo = maxDeviceNo.substring(4);
		logger.debug("device no is :[" + deviceNo + "]");
		Long n = NumberUtils.createLong(deviceNo) + 1L;
		
		return  (new StringBuilder())
		.append(typeCode).append("-").append(
				formatter.format(n)).toString();
	}

	public DeviceCard loadDevice(Long deviceId) {
		return this.deviceCardDao.loadDevice(deviceId);
	}

	public List<DeviceCard> createSelectDevices(String name) {
		List<DeviceCard> tmpList = this.loadAllDevices();
		DeviceCard device = new DeviceCard();
		device.setId(Long.valueOf(-1L));
		device.setName(name);
		tmpList.add(0, device);
		return tmpList;
	}

	public List<DeviceCard> loadAllDevices() {
		return deviceCardDao.loadAllDevices();
	}

	public DeviceCard getDeviceByNo(String deviceNo) {
		return deviceCardDao.getDeviceByNo(deviceNo);
	}

//	public List<DeviceCard> loadAllUnrelatedDevices(Long id) {
//		return deviceCardDao.loadAllUnrelatedDevices(id);
//	}
	
	public void cancelJob(DeviceCard deviceCard) {
		deviceCard.setJob(null);
		this.storeDevice(deviceCard);
		this.wfJobManager.cancelJob(deviceCard);
		
	}

	
	public void submitDoc(DeviceCard device, Long[] ids, Long finalId,
			String comment, String deviceNo, String name) throws Exception  {
		Job job = this.wfJobManager.submitJob(device, ids, finalId, comment, deviceNo, name);
		device.setJob(job);
		this.storeDevice(device);
	}

	public void storeTooling(DeviceCard tooling) {
		tooling.setToolingDevFlag(SysModel.TOOLING);
		boolean isNew = tooling.isNew();
		if (isNew) {
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
		}
		this.deviceCardDao.storeDevice(tooling);
	}

	public List<DeviceCard> loadAllDevices(Long[] deviceIds) {
		return this.deviceCardDao.loadAllDevices(deviceIds);
	}

	public void disabledAllToolings(List<DeviceCard> toolings) {
		for (DeviceCard tooling : toolings) {
			tooling.setDisabled(true);
			this.deviceCardDao.storeDevice(tooling);
		}
	}
	
	public void enabledAllToolings(List<DeviceCard> toolings) {
		for (DeviceCard tooling : toolings) {
			tooling.setDisabled(false);
			this.deviceCardDao.storeDevice(tooling);
		}
	}
	
	public void disabledAllDevices(List<DeviceCard> devices) {
		for (DeviceCard device : devices) {
			device.setDisabled(true);
			this.deviceCardDao.storeDevice(device);
		}
	}
	
	public void enabledAllDevices(List<DeviceCard> devices) {
		for (DeviceCard device : devices) {
			device.setDisabled(false);
			this.deviceCardDao.storeDevice(device);
		}
	}
	

	public void storeTooling(String alterToolingDemacrateCycle) {
		String[] ary = alterToolingDemacrateCycle.split(",");
		DeviceCard tooling = new DeviceCard();
		for (int i = 0; i < ary.length; i = i + 2) {
			tooling = this.loadDevice(Long.valueOf(ary[i]));
			tooling.setDemarcateCycle(Short.valueOf(ary[i + 1]));
			this.storeTooling(tooling);
		}
	}

	public void storeTooling(String alterToolingDemacrateCycle, String alterToolingManager) {
		String[] demacrateCycleAry = alterToolingDemacrateCycle.split(",");
		String[] managerAry = alterToolingManager.split(",");
		DeviceCard tooling = new DeviceCard();
		for (int i = 0,j=0; (i < demacrateCycleAry.length) || (j < managerAry.length); i = i + 2,j = j + 2) {
			if (i < demacrateCycleAry.length) {
				tooling = this.loadDevice(Long.valueOf(demacrateCycleAry[i]));
				tooling.setDemarcateCycle(Short.valueOf(demacrateCycleAry[i + 1]));
				if ( (j+1 < managerAry.length) && (!managerAry[j+1].equals(""))) {
					tooling.setManager(this.userManager.loadUser(Long.valueOf(managerAry[j+1])));
				}

			} else if (j < managerAry.length) {
				tooling = this.loadDevice(Long.valueOf(demacrateCycleAry[i]));
				tooling.setManager(this.userManager.loadUser(Long.valueOf(managerAry[j+1])));
			}

			this.storeTooling(tooling);
		}
	}

	public List<DeviceCard> loadAllMatchOptionDevices(Map searchOption) {
		return this.deviceCardDao.loadAllMatchOptionDevices(searchOption);
	}
	
	public List Query(String[] queryInfo){
		return this.deviceCardDao.Query(queryInfo);
	}

	public List<DeviceCard> loadAllMatchOptionToolings(Map searchOption) {
		return this.deviceCardDao.loadAllMatchOptionToolings(searchOption);
	}

	public List<DeviceCard> loadAllDeviceByStatusAndAssetType() {
		return this.deviceCardDao.loadAllDeviceByStatusAndAssetType();
	}

	public DeviceCard loadDeviceByAcceptBill(Long acceptBillId) {
		
		return deviceCardDao.loadDeviceByAcceptBill(acceptBillId);
	}

	public List getToolingGroupNoByGroupNo(String groupNo) {
		return deviceCardDao.getToolingGroupNoByGroupNo(groupNo);
	}

}
