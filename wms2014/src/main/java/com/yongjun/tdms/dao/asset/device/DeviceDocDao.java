package com.yongjun.tdms.dao.asset.device;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.device.DeviceDoc;

/**
 * @author qs
 * @version $Id: DeviceDocDao.java 8022 2007-10-25 09:44:08Z zbzhang $
 */
public interface DeviceDocDao {
	public void deleteDeviceDoc( DeviceDoc deviceDoc);
	
	public DeviceDoc loadDeviceDoc(Long deviceDocId);
	
	public void storeDeviceDoc(DeviceDoc deviceDoc);

	public void deleteAllDeviceDocs(Collection deviceDocs);

	public List loadAllDeviceDocs(Long[] ids);

}
