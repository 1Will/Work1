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
package com.yongjun.tdms.dao.year.device.runmaintainPlan.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.device.runmaintainPlan.DeviceChangeDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DeviceChange;

/**
 * <p>Title: HibernateDeviceCheck
 * <p>Description: 运维计划的设备改造数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class HibernateDeviceChange extends BaseHibernateDao implements
		DeviceChangeDao {

	public DeviceChange loadDeviceChange(Long deviceChangeId) {
		return this.load(DeviceChange.class, deviceChangeId);
	}

	public List<DeviceChange> loadAllDeviceChanges(Long[] deviceChangeIds) {
		return this.loadAll(DeviceChange.class, deviceChangeIds);
	}

	public List<DeviceChange> loadAllDeviceChanges() {
		return this.loadAll(DeviceChange.class);
	}

	public void storeDeviceChange(DeviceChange deviceChange) {
		this.store(deviceChange);
	}

	public void deleteDeviceChange(DeviceChange deviceChange) {
		this.delete(deviceChange);
	}

	public void deleteAllDeviceChanges(Collection<DeviceChange> deviceChanges) {
		this.deleteAll(deviceChanges);
	}

}
