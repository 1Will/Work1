package com.yongjun.tdms.service.asset.device;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceType;

/**
 * @author qs
 * @version $Id: DeviceTypeManager.java 7219 2007-09-11 06:15:28Z qsun $
 */
@Transactional(readOnly = true)
public interface DeviceTypeManager {
	List<DeviceType> loadAllDeviceTypes();
	
	DeviceType loadDeviceType(Long id);

	List createSelectDeviceTypes(String label);
}
