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
package com.yongjun.tdms.service.year.device.runmaintainPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.year.device.runmaintainPlan.DeviceChangeDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DeviceChange;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.DeviceChangeManager;

/**
 * <p>Title: DefaultDeviceChangeManager
 * <p>Description: 运维计划的设备改造业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.runmaintainPlan.DeviceChangeManager
 * @version $Id:$
 */
public class DefaultDeviceChangeManager extends CalculateFeeManager implements DeviceChangeManager {
	
	private final DeviceChangeDao deviceChangeDao;
	
	public DefaultDeviceChangeManager(DeviceChangeDao deviceChangeDao) {
		this.deviceChangeDao = deviceChangeDao;
	}
	
	public DeviceChange loadDeviceChange(Long deviceChangeId) {
		return this.deviceChangeDao.loadDeviceChange(deviceChangeId);
	}

	public List<DeviceChange> loadAllDeviceChanges(Long[] deviceChangeIds) {
		return this.deviceChangeDao.loadAllDeviceChanges(deviceChangeIds);
	}

	public List<DeviceChange> loadAllDeviceChanges() {
		return this.deviceChangeDao.loadAllDeviceChanges();
	}

	public void storeDeviceChange(DeviceChange deviceChange) {
		this.deviceChangeDao.storeDeviceChange(deviceChange);
		//更新与其相关联的运维计划以及运维计划明细的费用
		this.calculateChangeFee(deviceChange.getRunmaintainPlanDetail());
	}

	public void deleteDeviceChange(DeviceChange deviceChange) {
		this.deviceChangeDao.deleteDeviceChange(deviceChange);
	}

	public void deleteAllDeviceChanges(RunmaintainPlanDetail runmaintainPlanDetail, Collection<DeviceChange> deviceChanges) {
		this.deviceChangeDao.deleteAllDeviceChanges(deviceChanges);
		for (DeviceChange deviceChange : deviceChanges) {
			runmaintainPlanDetail.getDeviceChanges().remove(deviceChange);
		}
		//更新与其相关联的运维计划以及运维计划明细的费用
		this.calculateChangeFee(runmaintainPlanDetail);
	}

}
