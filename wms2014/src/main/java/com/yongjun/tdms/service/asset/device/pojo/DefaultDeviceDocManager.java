package com.yongjun.tdms.service.asset.device.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.asset.device.DeviceDocDao;
import com.yongjun.tdms.model.asset.device.DeviceDoc;
import com.yongjun.tdms.service.asset.device.DeviceDocManager;

/**
 * @author qs
 * @version $Id: $
 */
public class DefaultDeviceDocManager implements DeviceDocManager {
	private DeviceDocDao deviceDocDao;
	
	public DefaultDeviceDocManager(DeviceDocDao deviceDocDao) {
		this.deviceDocDao = deviceDocDao;
	}
	
	public void deleteDeviceDoc(DeviceDoc deviceDoc) {
		this.deviceDocDao.deleteDeviceDoc(deviceDoc);
	}

	public DeviceDoc loadDeviceDoc(Long deviceDocId) {
		return this.deviceDocDao.loadDeviceDoc(deviceDocId);
	}

	public void storeDeviceDoc(DeviceDoc deviceDoc) {
		this.deviceDocDao.storeDeviceDoc(deviceDoc);
	}
	
	public List loadAllDeviceDocs(Long[] ids) {
		return deviceDocDao.loadAllDeviceDocs(ids);
	}

	public void deleteAllDeviceDocs(Collection deviceDocs) {
		deviceDocDao.deleteAllDeviceDocs(deviceDocs);
	}

}
