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
package com.yongjun.tdms.model.runmaintenance.discard;

import java.util.Date;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.pluto.model.security.Department;

/**
 * <p>Title: Discard
 * <p>Description: 工装报废类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: Discard.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Discard extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking{
	private static final long serialVersionUID = -720727838548311478L;
	private String discardNo;
	private String name;
	private DeviceCard tooling;			//工装对象
	private Double value;				//工装价值
	private String toolingStatus;		//工装状态
	private DeviceCard device;			//设备对象
	private Department department; 		//申报部门
	private String cause;				//报废原因
	private String status;				//工装现状
	private User manager;				//申请人
	private Date applyDatetime;			//申报日期
	private Date descardDatetime;		//报废时间
	private String qmDeparOpinion;  	//品管部意见
	private String techDeparOpinion;	//技术部意见
	private String manuDeparOpinion; 	//生产部意见
	private String checkupResult; 		//鉴定结果 
	private boolean discardFlag = false;//报废状态
	private boolean toolingDevFlag; // 默认为设备

	public boolean isDiscardFlag() {
		return discardFlag;
	}
	public void setDiscardFlag(boolean discardFlag) {
		this.discardFlag = discardFlag;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public DeviceCard getTooling() {
		return tooling;
	}
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
	public String getDiscardNo() {
		return discardNo;
	}
	public void setDiscardNo(String discardNo) {
		this.discardNo = discardNo;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Date getApplyDatetime() {
		return applyDatetime;
	}
	public void setApplyDatetime(Date applyDatetime) {
		this.applyDatetime = applyDatetime;
	}
	public Date getDescardDatetime() {
		return descardDatetime;
	}
	public void setDescardDatetime(Date descardDatetime) {
		this.descardDatetime = descardDatetime;
	}
	public String getCheckupResult() {
		return checkupResult;
	}
	public void setCheckupResult(String checkupResult) {
		this.checkupResult = checkupResult;
	}
	public String getManuDeparOpinion() {
		return manuDeparOpinion;
	}
	public void setManuDeparOpinion(String manuDeparOpinion) {
		this.manuDeparOpinion = manuDeparOpinion;
	}
	public String getQmDeparOpinion() {
		return qmDeparOpinion;
	}
	public void setQmDeparOpinion(String qmDeparOpinion) {
		this.qmDeparOpinion = qmDeparOpinion;
	}
	public String getTechDeparOpinion() {
		return techDeparOpinion;
	}
	public void setTechDeparOpinion(String techDeparOpinion) {
		this.techDeparOpinion = techDeparOpinion;
	}
	@Override
	public int hashCode() {
		return discardNo.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Discard)){
			return false;
		}
		Discard t =(Discard)o;
		if(this.discardNo.equals(t.getDiscardNo())){
			return true;
		}
		return false;
	}
	
	public String getToolingStatus() {
		return toolingStatus;
	}
	public void setToolingStatus(String toolingStatus) {
		this.toolingStatus = toolingStatus;
	}
	public DeviceCard getDevice() {
		return device;
	}
	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	public boolean isToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(boolean toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	
	
	
}
