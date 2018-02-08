/**
 * 
 */
package com.yongjun.tdms.model.runmaintenance.fault;

import com.yongjun.tdms.model.asset.device.DeviceType;


/**
 * @author Administrator
 *
 */
public class FaultAnalysisByDeviceType extends BaseFaultAnalysis {

	private static final long serialVersionUID = 1L;
	private DeviceType deviceType;	//关联的设备分类
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

}
