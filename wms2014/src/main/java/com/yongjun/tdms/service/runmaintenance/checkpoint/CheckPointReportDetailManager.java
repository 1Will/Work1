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
package com.yongjun.tdms.service.runmaintenance.checkpoint;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;

/**
 * <p>Title: CheckPointReportDetailManager
 * <p>Description: 属工装|设备备件业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly = true)
public interface CheckPointReportDetailManager {
	/**
	 * 根据传入的设备点检报告明细ID,获取报告明细List集合
	 * @param checkPointReportDetailID 设备点检报告明细ID集合
	 * @return List 设备点检报告明细集合
	 */
	List<CheckPointReportDetail>  loadAllCheckPointReportDetails(Long[] checkPointReportDetailID);
	
	public CheckPointReportDetail loadCheckPointReportDetail(Long checkPointReportDetailID);
	
	/**
	 * 根据修改的运行台时、保养台时、故障台时来保存设备点检报告明细
	 * @param saveDetail 运行台时、保养台时、故障台时
	 */
	@Transactional
	void storeCheckPointReportDetail (String saveDetail);
	
	/**
	 * 保存设备点检报告明细
	 * @param checkPointReportDetail 传入的设备点检报告明细对象
	 */
	@Transactional
	void storeCheckPointReportDetail (CheckPointReportDetail checkPointReportDetail);
	
	/**
	 * 根据传入的设备对象，保存点检报告明细
	 * @param report 点检报告对象
	 * @param addDeviceIds 设备ID集合
	 */
	@Transactional
	void storeCheckPointReportDetail(CheckPointReport report, String addDeviceIds);
	
	@Transactional
	void deleteAllCheckPointReportDetails(Collection<CheckPointReportDetail> details);
	
	public CheckPointReportDetail loadDetailBydeviceID(Long deviceId,String month); 
}
