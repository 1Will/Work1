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
package com.yongjun.tdms.presentation.webwork.action.year.device.runmaintainPlan;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DeviceChange;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.DeviceChangeManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager;

/**
 * <p>Title: EditDeviceChangeAction
 * <p>Description: 运维计划的精度检查维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class EditDeviceChangeAction extends PrepareAction {

	private static final long serialVersionUID = -4143194970051800304L;
	private final RunmaintainPlanDetailManager runmaintainPlanDetailManager;
	private final DeviceChangeManager deviceChangeManager;
	
	//运维计划明细
	private RunmaintainPlanDetail runmaintainPlanDetail;
	//设备改造
	private DeviceChange deviceChange;

	public EditDeviceChangeAction(RunmaintainPlanDetailManager runmaintainPlanDetailManager,
			DeviceChangeManager deviceChangeManager) {
		this.runmaintainPlanDetailManager = runmaintainPlanDetailManager;
		this.deviceChangeManager = deviceChangeManager;
	}
	
	/**
	 * 为其他方法准备数据
	 */
	public void prepare() throws Exception {
		if (this.hasId("runmaintainPlanDetail.id")) {
			//如果请求中包含"runmaintainPlanDetail.id",则根据"runmaintainPlanDetail.id"获取运维计划明细对象
			this.runmaintainPlanDetail = this.runmaintainPlanDetailManager.loadRunmaintainPlanDetail(this.getId("runmaintainPlanDetail.id"));
		}
		if (null == this.deviceChange) {
			if (this.hasId("deviceChange.id")) {
				//如果请求中包含"deviceChange.id",则根据"deviceChange.id"获取设备改造对象
				this.deviceChange = this.deviceChangeManager.loadDeviceChange(this.getId("deviceChange.id"));
			} else {
				//如果请求中不包含"dailyRepair.id",则创建新的设备改造对象
				this.deviceChange = new DeviceChange();
			}
		}
	}

	/**
	 * 如果点击保存按钮，保存维设备改造对象
	 * @return
	 */
    public String save() {
    	boolean isNew = this.deviceChange.isNew();
        //设置关联的运维计划明细
    	this.deviceChange.setRunmaintainPlanDetail(this.runmaintainPlanDetail);
    	this.deviceChangeManager.storeDeviceChange(this.deviceChange);
    	
		if (isNew) {
			this.addActionMessage(this.getText("deviceChange.add.success", Arrays
					.asList(new Object[] {  })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("deviceChange.edit.success",
							Arrays.asList(new Object[] {  })));
			return SUCCESS;
		} 
    }
    
	public DeviceChange getDeviceChange() {
		return deviceChange;
	}

	public void setDeviceChange(DeviceChange deviceChange) {
		this.deviceChange = deviceChange;
	}

	public RunmaintainPlanDetail getRunmaintainPlanDetail() {
		return runmaintainPlanDetail;
	}

	public void setRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintainPlanDetail = runmaintainPlanDetail;
	}

}
