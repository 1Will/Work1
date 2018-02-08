package com.yongjun.tdms.model.runmaintenance.maintenance;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeValue;


/**
 * <p>Title: MaintenanceRule
 * <p>Description: 保养标准类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: MaintenanceRule.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class MaintenanceRule extends BaseInfoEntity {

	private static final long serialVersionUID = 8574878315327590420L;
	
	private String part;		//	 保养部位
	private String method;		// 保养方法
	private String appeal;		// 保养要求
	private Integer cycle;		// 保养周期(单位:天)
	private Date lastMaintenanceDate;	// 上次保养日期
	private String dutyPeople;	// 责任人
	private DeviceCard device;	// 设备
	private CodeValue maintenanceType;
	

	public CodeValue getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(CodeValue maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public Date getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}

	public void setLastMaintenanceDate(Date lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
