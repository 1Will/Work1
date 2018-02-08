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
package com.yongjun.tdms.service.year.device.runmaintainPlan;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.device.runmaintainPlan.DeviceChange;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;

/**
 * <p>Title: DeviceChangeManager
 * <p>Description: 运维计划的设备改造业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface DeviceChangeManager {
	/**
	 * 根据传入运维计划的设备改造ID,获取运维计划的设备改造对象
	 * @param deviceChangeId 运维计划的设备改造ID
	 * @return DeviceChange 运维计划的设备改造对象
	 */
	DeviceChange loadDeviceChange(Long deviceChangeId);
	
	/**
	 * 根据传入运维计划的设备改造ID集合,获取集合运维计划的设备改造对象
	 * @param deviceChangeIds 运维计划的设备改造ID集合
	 * @return List 集合运维计划的设备改造对象
	 */
	List<DeviceChange> loadAllDeviceChanges(Long [] deviceChangeIds);
	
	/**
	 * 获取集合运维计划的设备改造对象
	 * @return  List 集合运维计划的设备改造对象
	 */
	List<DeviceChange> loadAllDeviceChanges();
	
	/**
	 * 保存传入的运维计划的设备改造对象
	 * @param deviceChange 运维计划的设备改造对象
	 */
	@Transactional
	void storeDeviceChange(DeviceChange deviceChange);
	
	/**
	 * 删除传入的运维计划的设备改造对象
	 * @param deviceChange 运维计划的设备改造对象
	 */
	@Transactional
	void deleteDeviceChange(DeviceChange deviceChange);
	
	/**
	 * 删除传入的集合中的运维计划的设备改造对象
	 * @param deviceChanges 运维计划的设备改造对象集合
	 */
	@Transactional
	void deleteAllDeviceChanges(RunmaintainPlanDetail runmaintainPlanDetail, Collection<DeviceChange> deviceChanges);
}
