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

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.dao.asset.device.DeviceFullUsingDao;
import com.yongjun.tdms.model.report.DeviceFullUsing;
import com.yongjun.tdms.service.asset.device.DeviceFullUsingManager;

/**
 * <p>Title: DefaultDeviceFullUsingManager
 * <p>Description: 主要生产设备完好利用情况业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:  $
 */
public class DefaultDeviceFullUsingManager implements DeviceFullUsingManager{
	private final DeviceFullUsingDao deviceFullUsingDao;
	
	public DefaultDeviceFullUsingManager (DeviceFullUsingDao deviceFullUsingDao) {
		this.deviceFullUsingDao = deviceFullUsingDao;
	}
	
	public void storeDeviceFullUsing(DeviceFullUsing deviceFullUsing) {
	}

	public List Query(String month) throws HibernateException {
		return deviceFullUsingDao.Query(month);
	}

	public List getMonths() {
		return deviceFullUsingDao.getMonths();
	}

}
