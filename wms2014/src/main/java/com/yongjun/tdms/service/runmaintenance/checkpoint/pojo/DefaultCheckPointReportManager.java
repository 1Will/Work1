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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointReportDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointReportDetailDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointReportViewDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportView;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;


/**
 * @author wzou
 * @version $Id: DefaultCheckPointReportManager.java 11138 2008-02-27 09:40:47Z zbzhang $
 */
public class DefaultCheckPointReportManager extends BaseManager implements CheckPointReportManager{
	private final CheckPointReportDao checkPointReportDao;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager deviceCardManager;
	private final CheckPointReportDetailDao checkPointReportDetailDao;
	private final SequenceManager sequenceManager;
	private final WorkWarnningManager workWarnningManager;
	private final CheckPointReportViewDao checkPointReportViewDao;
	
	private Properties resourceParameterConfiguration; 
	
	public Properties getResourceParameterConfiguration() {
		return resourceParameterConfiguration;
	}

	public void setResourceParameterConfiguration(
			Properties resourceParameterConfiguration) {
		this.resourceParameterConfiguration = resourceParameterConfiguration;
	}

	public DefaultCheckPointReportManager(CheckPointReportDao checkPointReportDao,
			DepartmentManager departmentManager,
			DeviceCardManager deviceCardManager,
			CheckPointReportDetailDao checkPointReportDetailDao,
			SequenceManager sequenceManager,
			WorkWarnningManager workWarnningManager,
			CheckPointReportViewDao checkPointReportViewDao) {
		this.checkPointReportDao = checkPointReportDao;
		this.departmentManager = departmentManager;
		this.deviceCardManager = deviceCardManager;
		this.checkPointReportDetailDao = checkPointReportDetailDao;
		this.sequenceManager = sequenceManager;
		this.workWarnningManager = workWarnningManager;
		this.checkPointReportViewDao = checkPointReportViewDao;
	}
	
	public CheckPointReport loadCheckPointReport(Long checkPointReportID) {
		return checkPointReportDao.loadCheckPointReport(checkPointReportID);
	}

	public void storeCheckPointReport(CheckPointReport report) {
		if(report.isNew()){
			String reportNo = (String)sequenceManager.generate("-");
			report.setReportNo(reportNo);
		}
		if (null != report.getDepartment()) {
			report.setDeptName(report.getDepartment().getName());			
		}
		this.checkPointReportDao.storeCheckPointReport(report);
	}
	
	public List<Long> loadAllIdsByMonth(String month) {
		return this.checkPointReportDao.loadAllIdsByMonth(month);
	}


//	--------------------------------- 后台设备点检报告单   ----------------------------->
	/* 1)从数据苦中获取所有部门
	 * 2)从设备表中获取所有(重点/生产)设备
	 * 
	 */
	public void createReportByScheduler() {
		List<Department> departments = this.departmentManager.loadAllDepartments();
		List<DeviceCard> devices = this.deviceCardManager.loadAllDevices();
		List<DeviceCard> devicesByDeparments;
//		DeviceExtInfo extInfo;
		CheckPointReport report = null;
		CheckPointReportDetail detail = null;
		for(Department department : departments){
			devicesByDeparments = new ArrayList<DeviceCard>();
			for(DeviceCard device : devices) {
				if (device.getDepartment()!=null) {
					if (device.getDepartment().getId().equals(department.getId())			//生成各部门的点检报告单，在此比较设备是否属于某个部门
							&& device.isEmphasis()){										//判断该设备是否为“重点”设备
						if (device.getUseCategory()!= null){						
							if (device.getUseCategory().getCode().equals("0051")){			//判断该设备是否为“生产”设备
								devicesByDeparments.add(device);
							}
						}
					}
				}
			}
			
			if (devicesByDeparments.size()!=0) {
				logger.info("start create " + department.getName() +"checkPoint report at " + DateUtil.getSystemDate());
				report = new CheckPointReport();
				List <User> userContainer = new ArrayList<User>();            //用来存放该润滑计划需要提醒的人  
				report.setName(department.getName()+DateUtil.getYear(Calendar.getInstance().getTime())+resourceParameterConfiguration.getProperty("year")
						+(DateUtil.getMonth(Calendar.getInstance().getTime())+2)+resourceParameterConfiguration.getProperty("month")
						+resourceParameterConfiguration.getProperty("checkPointReport"));
				report.setReportTime(Calendar.getInstance().getTime());
				String month = null;
				if(DateUtil.getMonth(Calendar.getInstance().getTime())+2<10) {
					month = "0"+String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime())+2);
				}else {
					month = String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime())+2);
				}
				report.setMonth(String.valueOf(DateUtil.getYear(Calendar.getInstance().getTime()))+"-"+ month);
				report.setDepartment(department);
				this.storeCheckPointReport(report);
				for(DeviceCard device : devicesByDeparments){
					detail = new CheckPointReportDetail();
					detail.setDevice(device);
					detail.setCheckPointReport(report);
					this.checkPointReportDetailDao.storeCheckPointReportDetail(detail);
					//收集点检计划生成好,需要提醒的人
					this.workWarnningManager.getWarnningReceiverForDevice(device,userContainer);
				}
				logger.info("end create "+ department.getName() +"checkPoint report at " + DateUtil.getSystemDate());
				if (null != report) {                //如果点检计划创建成功,则发送提醒消息
					//提醒内容
					String warnningContent = report.getName() + "," + this.getResourceParameterConfiguration().getProperty("generated")
					                         + "," + this.getResourceParameterConfiguration().getProperty("please_watch");
					//向每个提醒人发送提醒信息
					this.workWarnningManager.sendWarnningMessage(userContainer, 
							this.getResourceParameterConfiguration().getProperty("GENERATE_POINTCHEKC_REPORT"),
							warnningContent);
				}
			}
		}
	}

	public List<CheckPointReportView> loadAllCheckPointReportView(String[] array) throws HibernateException {
		return this.checkPointReportViewDao.loadAllCheckPointReportView(array);
	}



	
}
