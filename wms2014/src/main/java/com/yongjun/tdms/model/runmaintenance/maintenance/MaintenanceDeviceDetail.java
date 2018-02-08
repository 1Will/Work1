package com.yongjun.tdms.model.runmaintenance.maintenance;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanStatus;

/**
 * <p>Title: MaintenanceDeviceDetail
 * <p>Description: 设备保养的基本信息类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: MaintenanceDeviceDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class MaintenanceDeviceDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 4066472821233002673L;
	
	private String part;		//	 保养部位
	private String method;		// 保养方法
	private String appeal;		// 保养要求
	private Integer cycle;		// 保养周期(单位:天)
	private Date lastMaintenanceDate;	// 上次保养日期
	private String dutyPeople;	// 责任人
	private DeviceCard device;	// 设备
	private MaintenanceDetail maintenanceDetail;   //关联保养明细单
	private CodeValue maintenanceType;
	private MaintenanceResultType maintenanceResult=MaintenanceResultType.UNFINISHED;
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

	public CodeValue getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(CodeValue maintenanceType) {
		this.maintenanceType = maintenanceType;
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

	public MaintenanceDetail getMaintenanceDetail() {
		return maintenanceDetail;
	}

	public void setMaintenanceDetail(MaintenanceDetail maintenanceDetail) {
		this.maintenanceDetail = maintenanceDetail;
	}
	
}
