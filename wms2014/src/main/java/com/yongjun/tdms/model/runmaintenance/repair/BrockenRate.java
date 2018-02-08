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
package com.yongjun.tdms.model.runmaintenance.repair;


import java.util.Date;


import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
/**
 * <p>Title: RepairItem
 * <p>Description: 维修明细内容标准类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: BrockenRate.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class BrockenRate extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	//故障发生的日期
    private Date month;
    //故障所关联的设备
    private DeviceCard deviceCard;
    //设备编号
    private String deviceNo;
	//设备名称
    private String deviceName;
    //设备故障所关联的部门
    private Department department;
	// 部门名称 
    private String departName;
    //设备使用率
    private Double usAgeRate; 
    //设备停用率
    private Double usAbleRate;
    //设备故障率
	private Double devBrockenRate;
	private Date createTime;
	//备注
	private String comment;
	
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}


	public DeviceCard getDeviceCard() {
		return deviceCard;
	}

	public void setDeviceCard(DeviceCard deviceCard) {
		this.deviceCard = deviceCard;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Double getUsAgeRate() {
		return usAgeRate;
	}

	public void setUsAgeRate(Double usAgeRate) {
		this.usAgeRate = usAgeRate;
	}

	public Double getUsAbleRate() {
		return usAbleRate;
	}

	public void setUsAbleRate(Double usAbleRate) {
		this.usAbleRate = usAbleRate;
	}

	public Double getDevBrockenRate() {
		return devBrockenRate;
	}

	public void setDevBrockenRate(Double devBrockenRate) {
		this.devBrockenRate = devBrockenRate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	

	
	
}
