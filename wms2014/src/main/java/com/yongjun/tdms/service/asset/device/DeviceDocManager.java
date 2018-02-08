package com.yongjun.tdms.service.asset.device;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceDoc;

/**
 * @author qs
 * @version $Id: $
 */
@Transactional(readOnly = true)
public interface DeviceDocManager {
	@Transactional
	public void deleteDeviceDoc(DeviceDoc deviceDoc);
	
	public DeviceDoc loadDeviceDoc(Long deviceDocId);
	
	@Transactional
	public void storeDeviceDoc(DeviceDoc deviceDoc);

	public List loadAllDeviceDocs(Long[] ids);

	@Transactional
	public void deleteAllDeviceDocs(Collection deviceDocs);
}
