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
package com.yongjun.tdms.model.runmaintenance.usualcheck;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.document.ApplicationDoc;

/**
 * 
 * @author zbzhang
 * @version $Id: Check.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Check extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -1284652648159129300L;

	//日常检查单编号
	private String billNo;
	
	//日常检查单名称
	private String name;
	
	//检查的所属单位，在默认情况下，是通过选择工装，将工装部门保存进来，在没有选择部门，而是手工输入名称的，默认保存创建检查的部门。
	private Department department;
	
	//检查时间
	private Date checkDate;
	
	//检查人
	private String checker;
	
	//检查结果
	private String result;
	
	//处理结果
	private String handle;
	
	//资产 [工装]|[设备]
	private DeviceCard asset;
	
	//资产标识 [工装] | [设备]
	private SysModel toolingDevFlag = SysModel.DEVICE;
	
    //日常检查单checkBox是否选中/ 默认[没选中]
	private boolean unEnrol=false;
	
	//日常检查单的状态   默认为未列入
	private CheckStatus status;
	
	private Set<ApplicationDoc> changeDoc = new HashSet<ApplicationDoc>(); 
	/*--------------------------------------新增加的字段-------------------------------------------------------*/
	private String toolingName;
	
	public Check(){
		
	}
	
	@Override
	public int hashCode() {
		//return this.getBillNo().hashCode();
		return 0;
	}

	@Override
	public boolean equals(Object o) {
//		if (!(o instanceof Check)) {return false;}
//		Check check = (Check)o;
//		if (this.getBillNo().equals(check.getBillNo())) {return true;}
		return false;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CheckStatus getStatus() {
		return status;
	}

	public void setStatus(CheckStatus status) {
		this.status = status;
	}

	public boolean getUnEnrol() {
		return unEnrol;
	}

	public void setUnEnrol(boolean unEnrol) {
		this.unEnrol = unEnrol;
	}

	public Set<ApplicationDoc> getChangeDoc() {
		return changeDoc;
	}

	public void setChangeDoc(Set<ApplicationDoc> changeDoc) {
		this.changeDoc = changeDoc;
	}

	public String getToolingName() {
		return toolingName;
	}

	public void setToolingName(String toolingName) {
		this.toolingName = toolingName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String getDomainModelProperty() {
		return this.getProperties().getProperty("usual_check");
	}
}
