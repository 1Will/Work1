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

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceDocDao;
import com.yongjun.tdms.model.asset.device.DeviceDoc;

/**
 * @author qs
 * @version $Id: HibernateDeviceDoc.java 8099 2007-10-29 04:08:46Z qsun $
 */
public class HibernateDeviceDoc extends BaseHibernateDao implements DeviceDocDao {

	public void deleteDeviceDoc(DeviceDoc deviceDoc) {
		this.delete(deviceDoc);
	}

	public DeviceDoc loadDeviceDoc(Long deviceDocId) {
		return this.load(DeviceDoc.class,deviceDocId);
	}

	public void storeDeviceDoc(DeviceDoc deviceDoc) {
		this.store(deviceDoc);
	}

	@SuppressWarnings("unchecked")
	public void deleteAllDeviceDocs(Collection deviceDocs) {
		this.deleteAll(deviceDocs);
	}

	public List loadAllDeviceDocs(Long[] ids) {
		return this.loadAll(DeviceDoc.class, ids);
	}
	
}
