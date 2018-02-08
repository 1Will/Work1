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
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanManager;

/**
 * 
 * <p>Title: EditRunmaintainPlanDetailAction
 * <p>Description: 年度运维计划明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditRunmaintainPlanDetailAction extends PrepareAction {

	private static final long serialVersionUID = -1355303628871861256L;
	
	private final RunmaintainPlanManager runmaintainPlanManager;
	private final RunmaintainPlanDetailManager runmaintainPlanDetailManager;
	private final DeviceCardManager deviceCardManager;
	
	//运维计划
	private RunmaintainPlan runmaintainPlan;
	//运维计划明细
	private RunmaintainPlanDetail runmaintainPlanDetail;
	
	public EditRunmaintainPlanDetailAction(RunmaintainPlanManager runmaintainPlanManager,
			RunmaintainPlanDetailManager runmaintainPlanDetailManager,
			DeviceCardManager deviceCardManager) {
		this.runmaintainPlanManager = runmaintainPlanManager;
		this.runmaintainPlanDetailManager = runmaintainPlanDetailManager;
		this.deviceCardManager = deviceCardManager;
	}
	
	/**
	 * 为执行save()方法准备数据
	 */
	public void prepare() throws Exception {
		if (this.hasId("runmaintainPlan.id")) {
			//如果请求中包含"runmaintainPlan.id",则根据"runmaintainPlan.id"获取设备年度运维计划对象
			this.runmaintainPlan = this.runmaintainPlanManager.loadRunmaintainPlan(this.getId("runmaintainPlan.id"));
		}
		if (null == this.runmaintainPlanDetail) {
			if (this.hasId("runmaintainPlanDetail.id")) {
				//如果请求中包含"runmaintainPlanDetail.id",则根据"runmaintainPlanDetail.id"获取设备年度运维计划明细对象
				this.runmaintainPlanDetail = this.runmaintainPlanDetailManager.loadRunmaintainPlanDetail(this.getId("runmaintainPlanDetail.id"));
			} else {
				//如果请求中不包含"runmaintainPlanDetail.id",则根据创建一个新的设备年度运维计划明细对象
				this.runmaintainPlanDetail = new RunmaintainPlanDetail();
			}
		}
	}
	/**
	 * 当页面点击保存按钮,保存设备年度运维计划明细的信息
	 * @return SUCCESS
	 */
	public String save() {
		boolean isNew = this.runmaintainPlanDetail.isNew();
		
		if (this.hasId("device.id")) { //设置关联的设备
			this.runmaintainPlanDetail.setDevice(this.deviceCardManager.loadDevice(this.getId("device.id")));
		}
		this.runmaintainPlanDetailManager.storeRunmaintainPlanDetail(this.runmaintainPlanDetail);
		if (isNew) {
			this.addActionMessage(this.getText("runmaintainPlanDetail.add.success", Arrays
					.asList(new Object[] { runmaintainPlanDetail.getDevice().getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("runmaintainPlanDetail.edit.success",
							Arrays.asList(new Object[] { runmaintainPlanDetail.getDevice().getName() })));
			return SUCCESS;
		} 
	}
	
	public RunmaintainPlan getRunmaintainPlan() {
		return runmaintainPlan;
	}

	public void setRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		this.runmaintainPlan = runmaintainPlan;
	}

	public RunmaintainPlanDetail getRunmaintainPlanDetail() {
		return runmaintainPlanDetail;
	}

	public void setRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintainPlanDetail = runmaintainPlanDetail;
	}

}
