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
package com.yongjun.tdms.service.runmaintenance.checkpoint.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointReportDetailDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportDetailManager;

/**
 * <p>Title: DefaultCheckPointReportDetailManager
 * <p>Description: 设备点检报告明细业务类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
public class DefaultCheckPointReportDetailManager extends BaseManager implements CheckPointReportDetailManager{
	private final CheckPointReportDetailDao checkPointReportDetailDao;
	private final DeviceCardManager deviceCardManager;
	
	public DefaultCheckPointReportDetailManager(CheckPointReportDetailDao checkPointReportDetailDao,
			DeviceCardManager deviceCardManager) {
		this.checkPointReportDetailDao = checkPointReportDetailDao;
		this.deviceCardManager = deviceCardManager;
	}

	public List<CheckPointReportDetail> loadAllCheckPointReportDetails(Long[] checkPointReportDetailID) {
		return checkPointReportDetailDao.loadAllCheckPointReportDetails(checkPointReportDetailID);
	}

	public void storeCheckPointReportDetail(String saveDetail) {
		String[] details = saveDetail.split(",");
		CheckPointReportDetail detail = new CheckPointReportDetail();
		DeviceCard device = new DeviceCard ();
		for (int i = 0;i<details.length; i = i+7){
			detail = checkPointReportDetailDao.loadCheckPointReportDetail(
					Long.valueOf(details[i]));
			if (details[i+1].equals("")){
				detail.setRunTime(Float.valueOf(0));
			}else {
				//device = detail.getDevice();
//				获取用户提交的新运行台时
				detail.setRunTime(Float.valueOf(details[i+1]));
				//先将设备一保\二报总台时减去该设备点检报告单中的运行台时，目的是防止用户重复修改运行台时间。
//				if (detail.getRunTime() != null) {
//					device.setOneMaintenanceHour((device.getOneMaintenanceHour()-detail.getRunTime().intValue()));
//					device.setTwoMaintenanceHour(device.getTwoMaintenanceHour()-detail.getRunTime().intValue());
//				}
//				//重新设置设备一保\二报总台时
//				device.setOneMaintenanceHour(device.getOneMaintenanceHour()+Float.valueOf(details[i+1]).intValue());
//				device.setTwoMaintenanceHour(device.getTwoMaintenanceHour()+Float.valueOf(details[i+1]).intValue());
			}
			if (details[i+2].equals("")){
				detail.setMaintenanceTime(Float.valueOf(0));
			}else {
				detail.setMaintenanceTime(Float.valueOf(details[i+2]));
			}
			if (details[i+3].equals("")){
				detail.setFaultTime(Float.valueOf(0));
			}else {
				detail.setFaultTime(Float.valueOf(details[i+3]));
			}
			if(details[i+4].equals("")){
				detail.setProductTotalOutput(Long.valueOf(0));
			}else{
				detail.setProductTotalOutput(Long.valueOf(details[i+4]));
			}
			if(details[i+5].equals("")){
				detail.setInferiorProductOutput(Long.valueOf(0));
			}else{
				detail.setInferiorProductOutput(Long.valueOf(details[i+5]));
			}
			detail.setComment(details[i+6]);
			
		}
	}

	public void storeCheckPointReportDetail(CheckPointReportDetail checkPointReportDetail) {
		this.checkPointReportDetailDao.storeCheckPointReportDetail(checkPointReportDetail);
	}

	public void storeCheckPointReportDetail(CheckPointReport report, String addDeviceIds) {
		String[] detailIds = addDeviceIds.split(",");
		for (int i = 0; i < detailIds.length; i++) {
			CheckPointReportDetail detail = new CheckPointReportDetail();
			detail.setCheckPointReport(report);
			detail.setDevice(this.deviceCardManager.loadDevice(Long .valueOf(detailIds[i])));
			this.checkPointReportDetailDao.storeCheckPointReportDetail(detail);
		}
	}

	public CheckPointReportDetail loadCheckPointReportDetail(Long checkPointReportDetailID) {
		return this.checkPointReportDetailDao.loadCheckPointReportDetail(checkPointReportDetailID);
	}

	public void deleteAllCheckPointReportDetails(Collection<CheckPointReportDetail> details) {
		this.checkPointReportDetailDao.deleteAllCheckPointReportDetails(details);
	}
	
	public CheckPointReportDetail loadDetailBydeviceID(Long deviceId,String month) {
		return this.checkPointReportDetailDao.loadDetailBydeviceID(deviceId, month);
	}
	
}
