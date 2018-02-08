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
package com.yongjun.tdms.dao.asset.device;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.device.DeviceSpare;

/**
 * <p>Title: DeviceSpareDao
 * <p>Description: 资产备件数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: DeviceSpareDao.java 10042 2007-12-30 07:04:47Z mwei $
 */
public interface DeviceSpareDao {
	/**
	 * 根据传入的资产备件ID,获取属于资产的备件
	 * @param deviceSpareId
	 * @return DeviceSpare 资产的备件
	 */
	DeviceSpare loadDeviceSpare(Long deviceSpareId);
	
	/**
	 * 根据传入的资产的备件ID集合,获取属于资产的集合中的备件
	 * @param deviceSpareIds 资产的备件ID集合
	 * @return List 资产的备件集合
	 */
	List<DeviceSpare> loadDeviceSpares(Long [] deviceSpareIds);
	
	/**
	 * 获取所有的属于资产的备件
	 * @return List 资产的备件集合
	 */
	List<DeviceSpare> loadDeviceSpares();
	
	/**
	 * 保存资产的备件
	 * @param deviceSpare 资产的备件实体
	 */
	void storeDeviceSpare(DeviceSpare deviceSpare);
	
	/**
	 * 删除资产的备件
	 * @param deviceSpare 资产的备件实体
	 */
	void deleteDeviceSpare(DeviceSpare deviceSpare);
	
	/**
	 *  根据传入的资产的备件集合，删除集合中的资产的备件
	 * @param deviceSpares 资产的备件集合
	 */
	void deleteAllDeviceSpare(Collection<DeviceSpare> deviceSpares);
	
	/**
	 * 根据传入的备件ID，获取资产的备件
	 * @param spareId 备件ID
	 * @return DeviceCard 资产的备件
	 */
	DeviceSpare GetDeviceSpareBySpareId(Long spareId,Long deviceId);

}
