/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.asset.device.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.asset.device.DeviceSpareDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.service.asset.device.DeviceSpareManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;

/**
 * <p>
 * Title: DefaultDepartmentManager
 * <p>
 * Description: 属工装|设备备件业务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author qs@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.dao.asset.device.DeviceSpareDao
 */
public class DefaultDeviceSpareManager implements DeviceSpareManager {

	private final DeviceSpareDao deviceSpareDao;

	private final SpareManager spareManager;

	public DefaultDeviceSpareManager(DeviceSpareDao deviceSpareDao,
			SpareManager spareManager) {
		this.deviceSpareDao = deviceSpareDao;
		this.spareManager = spareManager;
	}

	public DeviceSpare loadDeviceSpare(Long deviceSpareId) {
		return this.deviceSpareDao.loadDeviceSpare(deviceSpareId);
	}

	public List<DeviceSpare> loadDeviceSpares(Long[] deviceSpareIds) {
		return this.deviceSpareDao.loadDeviceSpares(deviceSpareIds);
	}

	public List<DeviceSpare> loadDeviceSpares() {
		return this.deviceSpareDao.loadDeviceSpares();
	}

	public void storeDeviceSpare(DeviceSpare deviceSpare) {
		this.deviceSpareDao.storeDeviceSpare(deviceSpare);
	}

	public void deleteDeviceSpare(DeviceSpare deviceSpare) {
		this.deviceSpareDao.deleteDeviceSpare(deviceSpare);
	}

	public void deleteAllDeviceSpare(Collection<DeviceSpare> deviceSpares) {
		this.deviceSpareDao.deleteAllDeviceSpare(deviceSpares);
	}
    //TODO 未关联资产编号
	public void storeToolingDevSpare(DeviceCard toolingDev, String addSpareIds) {
		String[] spareIds = addSpareIds.split(",");
		for (int i = 0; i < spareIds.length; i++) {
			DeviceSpare toolingDevSpare = new DeviceSpare();
			toolingDevSpare.setAsset(toolingDev);
			toolingDevSpare.setSpare(this.spareManager.loasSpare(Long
					.valueOf(spareIds[i])));
			this.deviceSpareDao.storeDeviceSpare(toolingDevSpare);
		}
	}

	public void storeDeviceSpare(String saveSpareIds) {
		String[] spareIds = saveSpareIds.split(",");
		DeviceSpare toolingDevSpare=new DeviceSpare();
		for (int i = 0; i < spareIds.length; i = i + 5) {
			 toolingDevSpare = deviceSpareDao.loadDeviceSpare(Long
					.valueOf(spareIds[i]));
			if(i+1>=spareIds.length){
				toolingDevSpare.setInstallPlace(null);
				toolingDevSpare.setNumber(Long.valueOf(0));
				toolingDevSpare.setAmount(Long.valueOf(0));
				toolingDevSpare.setLastReplaceTime(null);
			}else{
				toolingDevSpare.setInstallPlace(spareIds[i+1]);
				if(spareIds[i+2].equals("")){
					toolingDevSpare.setNumber(Long.valueOf(0));
				}else{
					toolingDevSpare.setNumber(Long.valueOf(spareIds[i+2]));
				}
				if(spareIds[i+3].equals("")){
					toolingDevSpare.setAmount(Long.valueOf(0));
				}else{
					toolingDevSpare.setAmount(Long.valueOf(spareIds[i+3]));
				}
				if(spareIds[i+4].equals(" ")){
					toolingDevSpare.setLastReplaceTime(null);
				}else{
					toolingDevSpare.setLastReplaceTime(DateUtil.toDate(spareIds[i+4].substring(0,10),"yyyy-mm-dd"));
				}
			}
			storeDeviceSpare(toolingDevSpare);
		}
	}

	public DeviceSpare GetDeviceSpareBySpareId(Long spareId,Long deviceId) {
		return this.deviceSpareDao.GetDeviceSpareBySpareId(spareId,deviceId);
	}
}
