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
package com.yongjun.tdms.dao.asset.device.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceSpareDao;
import com.yongjun.tdms.model.asset.device.DeviceSpare;

/**
 * <p>Title: HibernateDeviceSpare
 * <p>Description: 资产备件数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: HibernateDepartment.java 8835 2007-11-30 03:40:27Z qsun $
 * @see com.yongjun.tdms.dao.asset.device.DeviceSpareDao
 */
public class HibernateDeviceSpare extends BaseHibernateDao implements DeviceSpareDao{

	public DeviceSpare loadDeviceSpare(Long deviceSpareId) {
		return this.load(DeviceSpare.class, deviceSpareId);
	}

	public List<DeviceSpare> loadDeviceSpares(Long[] deviceSpareIds) {
		return this.loadAll(DeviceSpare.class, deviceSpareIds);
	}

	public List<DeviceSpare> loadDeviceSpares() {
		return this.loadAll(DeviceSpare.class);
	}

	public void storeDeviceSpare(DeviceSpare deviceSpare) {
		this.store(deviceSpare);
	}

	public void deleteDeviceSpare(DeviceSpare deviceSpare) {
		this.delete(deviceSpare);
	}

	public void deleteAllDeviceSpare(Collection<DeviceSpare> deviceSpares) {
		this.deleteAll(deviceSpares);
	}

	public DeviceSpare GetDeviceSpareBySpareId(final Long spareId,final Long deviceId) {
		return (DeviceSpare) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetDeviceSpareBySpareId")
								.setParameter("spareId", spareId).setParameter("deviceId",deviceId)
								.uniqueResult();
					}
				});
	}
}
