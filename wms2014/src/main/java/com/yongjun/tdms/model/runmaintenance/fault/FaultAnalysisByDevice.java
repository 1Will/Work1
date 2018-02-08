/**
 * 
 */
package com.yongjun.tdms.model.runmaintenance.fault;

import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author Administrator
 *
 */
public class FaultAnalysisByDevice extends BaseFaultAnalysis {
	private DeviceCard deviceCard;
	private Double mtbf;
	private Double mttf;
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

	public DeviceCard getDeviceCard() {
		return deviceCard;
	}

	public void setDeviceCard(DeviceCard deviceCard) {
		this.deviceCard = deviceCard;
	}

	public Double getMtbf() {
		return mtbf;
	}

	public void setMtbf(Double mtbf) {
		this.mtbf = mtbf;
	}

	public Double getMttf() {
		return mttf;
	}

	public void setMttf(Double mttf) {
		this.mttf = mttf;
	}

}
