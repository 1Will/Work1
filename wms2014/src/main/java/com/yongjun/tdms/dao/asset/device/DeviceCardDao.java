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

import java.util.List;
import java.util.Map;

import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: DeviceCardDao.java 11056 2008-02-22 01:39:11Z zbzhang $
 */
public interface DeviceCardDao {

	public void storeDevice(DeviceCard device);

	public DeviceCard loadDevice(Long deviceID);

	public List<DeviceCard> loadAllDevices();

	public DeviceCard getDeviceByNo(String deviceNo);

//	public List<DeviceCard> loadAllUnrelatedDevices(Long id);
	
	public List<DeviceCard> loadAllDevices(Long [] deviceCardIds);
	
	public String getMaxDeviceNoByAssetCode(String code);
	
	/**
	 * 根据传入的查询条件，获取符合条件的设备集合,主要用于打印设备报表
	 * @param searchOption  查询条件，[key:查询条件名，value:查询条件的值]
	 * @return List 设备集合
	 */
	public List<DeviceCard> loadAllMatchOptionDevices(Map searchOption);
	
	/**
	 * 根据传入的查询条件，获取符合条件的工装集合,主要用于打印工装报表
	 * @param searchOption 查询条件，[key:查询条件名，value:查询条件的值]
	 * @return List 工装集合
	 */
	public List<DeviceCard> loadAllMatchOptionToolings(Map searchOption);
	
	public List Query(String[] queryInfo);
	
	/**
	 * 获取资产是设备且状态为正常的设备
	 * @return List 设备集合
	 */ 
	public List<DeviceCard> loadAllDeviceByStatusAndAssetType(); 
	DeviceCard loadDeviceByAcceptBill(Long acceptBillId);
	/**
	 * 通过@param groupNo 获取工装台帐中和此编号相同的工装台帐的记录
	 * @param groupNo
	 * @return
	 */
	List getToolingGroupNoByGroupNo(String groupNo);
	/**
	 * 通过验收单明细所创建台帐的图号  返回根据此图号所创建台帐的记录是否大于0
	 * @param groupNo
	 * @return
	 */
	int getEstalishAfterGroupNumber(String groupNo);
}
