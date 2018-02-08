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
package com.yongjun.tdms.service.year.device.runmaintainPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDetailDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager;

/**
 * 
 * <p>Title: DefaultRunmaintainPlanDetailManager
 * <p>Description: 年度运维计划业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager
 * @version $Id:$
 */
public class DefaultRunmaintainPlanDetailManager  extends CalculateFeeManager implements
		RunmaintainPlanDetailManager {

	private final RunmaintainPlanDetailDao runmaintianPlanDetailDao;
	private final DeviceCardManager deviceCardManager;
	
	public DefaultRunmaintainPlanDetailManager(RunmaintainPlanDetailDao runmaintianPlanDetailDao,
			DeviceCardManager deviceCardManager) {
		this.runmaintianPlanDetailDao = runmaintianPlanDetailDao;
		this.deviceCardManager = deviceCardManager;
	}
	
	public RunmaintainPlanDetail loadRunmaintainPlanDetail(Long runmaintainPlanDetailId) {
		return this.runmaintianPlanDetailDao.loadRunmaintainPlanDetail(runmaintainPlanDetailId);
	}

	public List<RunmaintainPlanDetail> loadAllRunmaintainPlanDetails(Long[] runmaintainPlanDetailIds) {
		return this.runmaintianPlanDetailDao.loadAllRunmaintainPlanDetails(runmaintainPlanDetailIds);
	}

	public List<RunmaintainPlanDetail> loadAllRunmaintainPlanDetails() {
		return this.runmaintianPlanDetailDao.loadAllRunmaintainPlanDetails();
	}

	public void storeRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintianPlanDetailDao.storeRunmaintainPlanDetail(runmaintainPlanDetail);
	}

	public void deleteRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintianPlanDetailDao.deleteRunmaintainPlanDetail(runmaintainPlanDetail);
	}

	public void deleteAllRunmaintainPlanDetails(RunmaintainPlan runmaintainPlan, Collection<RunmaintainPlanDetail> runmaintainPlanDetails) {
		this.runmaintianPlanDetailDao.deleteAllRunmaintainPlanDetails(runmaintainPlanDetails);
		for (RunmaintainPlanDetail detail : runmaintainPlanDetails) {
			runmaintainPlan.getRunmaintainPlanDetails().remove(detail);
		}
		//计算运维计划总费用
		this.calculateRunmaintainPlanAllFee(runmaintainPlan);
	}

	public void storeRunmaintainPlanDetail(RunmaintainPlan runmaintainPlan, String addDeviceIds) {
		String [] deviceId = null;
		if (null != addDeviceIds) {
			//用","分割字符串
			deviceId = addDeviceIds.split(",");
		}
		//保存新添加的设备为运维计划明细
		addNewRunmaintainPlanDetail(runmaintainPlan, deviceId);
	}
	/**
	 * 添加新的设备运维计划明细
	 * @param runmaintainPlan 设备鉴定实体
	 * @param deviceId 设备ID
	 */
	private void addNewRunmaintainPlanDetail(RunmaintainPlan runmaintainPlan, String [] deviceId) {
		for (int i=0; i<deviceId.length; i++) {
			//创建一个新的设备运维计划明细
			RunmaintainPlanDetail detail = new RunmaintainPlanDetail();
			//设置计划明细关联的设备
			detail.setDevice(this.deviceCardManager.loadDevice(Long.valueOf(deviceId[i])));
			//设置计划明细关联的运维计划明细
			detail.setRunmaintainPlan(runmaintainPlan);
			this.runmaintianPlanDetailDao.storeRunmaintainPlanDetail(detail);
		}
	}

}
