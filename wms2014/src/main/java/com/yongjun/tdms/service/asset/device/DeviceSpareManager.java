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
package com.yongjun.tdms.service.asset.device;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceSpare;

/**
 * <p>Title: DeviceSpareManager
 * <p>Description: 属工装|设备备件业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly = true)
public interface DeviceSpareManager {
	/**
	 * 根据传入的工装|设备备件ID,获取属于工装|设备的备件
	 * @param deviceSpareId
	 * @return DeviceSpare 工装|设备的备件
	 */
	DeviceSpare loadDeviceSpare(Long deviceSpareId);
	
	/**
	 * 根据传入的工装|设备的备件ID集合,获取属于工装|设备的集合中的备件
	 * @param deviceSpareIds 工装|设备的备件ID集合
	 * @return List 工装|设备的备件集合
	 */
	List<DeviceSpare> loadDeviceSpares(Long [] deviceSpareIds);
	
	/**
	 * 获取所有的属于工装|设备的备件
	 * @return List 工装|设备的备件集合
	 */
	List<DeviceSpare> loadDeviceSpares();
	
	/**
	 * 保存工装|设备的备件
	 * @param deviceSpare 工装|设备的备件实体
	 */
	@Transactional
	void storeDeviceSpare(DeviceSpare deviceSpare);
	
	/**
	 * 删除工装|设备的备件
	 * @param deviceSpare 工装|设备的备件实体
	 */
	@Transactional
	void deleteDeviceSpare(DeviceSpare deviceSpare);
	
	/**
	 *  根据传入的工装|设备的备件集合，删除集合中的工装|设备的备件
	 * @param deviceSpares 工装|设备的备件集合
	 */
	@Transactional
	void deleteAllDeviceSpare(Collection<DeviceSpare> deviceSpares);
	
	/**
	 * 根据传入的备件ID集合和设备|工装对象，保存工装|设备的备件
	 * @param toolingDev 设备|工装对象
	 * @param addSpareIds 备件ID集合
	 */
	@Transactional
	void storeToolingDevSpare(DeviceCard toolingDev, String addSpareIds);
	
	
	/**
	 * 根据传入的备件ID、以及位置的集合，保存工装|设备的备件
	 * @param toolingDev 备件ID集合和位置集合的字符串
	 */
	@Transactional
	void storeDeviceSpare(String saveSpareIds);
	
	/**
	 * 根据传入的备件ID，获取工装|设备的备件
	 * @param spareId 备件ID
	 * @return DeviceCard 工装|设备的备件
	 */
	DeviceSpare GetDeviceSpareBySpareId(Long spareId,Long deviceId);
}
