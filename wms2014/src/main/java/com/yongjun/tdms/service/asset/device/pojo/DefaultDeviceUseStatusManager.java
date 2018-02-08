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
package com.yongjun.tdms.service.asset.device.pojo;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.asset.device.DeviceUseStatusDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.report.DeviceUseStatus;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.DeviceUseStatusManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportDetailManager;

/**
 * <p>Title: DefaultDeviceUseStatusManager
 * <p>Description: 主要生成设备使用状况业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:  $
 */
public class DefaultDeviceUseStatusManager implements DeviceUseStatusManager{
	private final DeviceUseStatusDao deviceUseStatusDao;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager deviceCardManager;
	private final CheckPointReportDetailManager checkPointReportDetailManager;
	private Properties resourceParameterConfiguration; 
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	public Properties getResourceParameterConfiguration() {
		return resourceParameterConfiguration;
	}
	
	public void setResourceParameterConfiguration(
			Properties resourceParameterConfiguration) {
		this.resourceParameterConfiguration = resourceParameterConfiguration;
	}
	
	public DefaultDeviceUseStatusManager (DeviceUseStatusDao deviceUseStatusDao,
			DepartmentManager departmentManager,
			DeviceCardManager deviceCardManager,
			CheckPointReportDetailManager checkPointReportDetailManager) {
		this.deviceUseStatusDao = deviceUseStatusDao;
		this.departmentManager = departmentManager;
		this.deviceCardManager = deviceCardManager;
		this.checkPointReportDetailManager = checkPointReportDetailManager;
	}
	public void storeDeviceUseStatus(DeviceUseStatus deviceUseStatus) {
		this.deviceUseStatusDao.storeDeviceUseStatus(deviceUseStatus);
	}
	public List Query(String month) throws HibernateException {
		return deviceUseStatusDao.Query(month);
	}

	
//	--------------------------------- 后台设备使用状况月报表   ----------------------------->
	/* 
	 * 1) 从设备表中获取所有(重点/生产)设备,该月报表只统计的是重点/生产设备
	 * 2) 根据点检报告单获取该设备上一个月的运行台时
	 * 3) 单独获取各部门的金切设备
	 * 4) 根据点检报告单获取金切设备上一个月的运行台时
	 * 5) 根据设备的各字段值进行计算
	 */ 
	public void createDeviceUseStatusByScheduler() {
		int fullAmount   = 0;			//完好设备数（台）
		int useAmount  = 0;				//实际开动台时（小时），就是运行台时
		int systemDaisTime  = 0 ;		//制度台时(时)
		int JQuseAmount  = 0;			//金切实际开动台时（小时）
		int JQsystemDaisTime   = 0;		//金切制度台时(时)
		
		int allAmount = 0;					//当月各部门的设备总数
		int allFullAmount   = 0;			//当月各部门完好设备数（台）
		int allUseAmount  = 0;				//当月各部门实际开动台时（小时），就是运行台时
		int allSystemDaisTime  = 0 ;		//当月各部门制度台时(时)
		int allJQuseAmount  = 0;			//当月各部门金切实际开动台时（小时）
		int allJQsystemDaisTime   = 0;		//当月各部门金切制度台时(时)
		
		
		
		//获取
		List<Department> departments = this.departmentManager.loadAllDepartments();
		List<DeviceCard> devices = this.deviceCardManager.loadAllDevices();
		List<DeviceCard> devicesByDeparments;
		
		DeviceUseStatus deviceUseStatus;
		CheckPointReportDetail checkPointReportDetail =  new CheckPointReportDetail(); 
		String month = null;
		if(DateUtil.getMonth(Calendar.getInstance().getTime())<10) {
			month = "0"+String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
		}else {
			month = String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
		}
		for(Department department : departments){
			devicesByDeparments = new ArrayList<DeviceCard>();
			for(DeviceCard device : devices) {
				if (device.getDepartment()!=null) {
					if (device.getDepartment().getId().equals(department.getId())			//判断该设备是否属于该部门
							&& device.isEmphasis()){										//判断该设备是否为“重点”设备
						if (device.getUseCategory()!= null){						
							if (device.getUseCategory().getCode().equals("0051")){			//判断该设备是否为“生产”设备
								devicesByDeparments.add(device);
							}
						}
					}
				}
			}
			if (devicesByDeparments.size()!=0) {							//表示该部门存在“重点”和“生产”设备，可以生成"主要生产设备使用状况月报表"
				deviceUseStatus = new DeviceUseStatus();
				deviceUseStatus.setDepartment(department.getName());
				
				for (DeviceCard device : devicesByDeparments) {
					//判断设备是否完好
					if (device.isFull() == true){
						fullAmount ++;
						allFullAmount ++;
					}
					if (this.checkPointReportDetailManager.loadDetailBydeviceID(device.getId(),month)!=null){
						checkPointReportDetail = this.checkPointReportDetailManager.loadDetailBydeviceID(device.getId(),month);
						if (checkPointReportDetail.getRunTime()!=null){
							useAmount += checkPointReportDetail.getRunTime();
							allUseAmount += useAmount;
						}
					}
					
					//计算该设备当月的制度台时,在此以一个月22个工作日计算.
					if (device.getSystemDaisTime()!=null){
						systemDaisTime += device.getSystemDaisTime()*22;
						allSystemDaisTime += systemDaisTime;
					}
					//判断金切设备,单独拿出来计算
					if (device.getDeviceType().getCode().equals("0")){
						if (checkPointReportDetail.getRunTime()!=null){
							JQuseAmount += checkPointReportDetail.getRunTime();
							allJQuseAmount += JQuseAmount;
						}
						if (device.getSystemDaisTime()!=null){
							JQsystemDaisTime += device.getSystemDaisTime()*22;
							allJQsystemDaisTime += JQsystemDaisTime;
						}
					}
					
				}
				deviceUseStatus.setDepartment(department.getName());
				deviceUseStatus.setMonth(String.valueOf(DateUtil.getYear(Calendar.getInstance().getTime()))+"-"+ month);
				deviceUseStatus.setFullAmount(fullAmount);										//设置完好设备台数
				deviceUseStatus.setAllAmount(devicesByDeparments.size());						//设置总台数
				allAmount += devicesByDeparments.size(); 
				deviceUseStatus.setFull(((double)fullAmount/(double)devicesByDeparments.size())*100);	//设置设备完好率
				fullAmount = 0;
				deviceUseStatus.setUseAmount(useAmount); 										//设置实际开动台时间(也就是运行台时)
				deviceUseStatus.setSystemDaisTime(systemDaisTime); systemDaisTime = 0;			//设置制度台时
				if (useAmount!=0){
					deviceUseStatus.setUsing(((double)useAmount/(double)systemDaisTime)*100);
				}else{
					deviceUseStatus.setUsing(0.0);
				}
				useAmount = 0;
				systemDaisTime = 0;
				deviceUseStatus.setJQuseAmount(JQuseAmount);
				deviceUseStatus.setJQsystemDaisTime(JQsystemDaisTime);
				if (JQuseAmount!=0){
					deviceUseStatus.setJQusing(((double)JQuseAmount/(double)JQsystemDaisTime)*100);
				}else {
					deviceUseStatus.setJQusing(0.0);
				}
				JQuseAmount = 0;
				JQsystemDaisTime = 0;
				
				this.deviceUseStatusDao.storeDeviceUseStatus(deviceUseStatus);
			}
		}
		// 统计合计
		deviceUseStatus = new DeviceUseStatus();
		deviceUseStatus.setDepartment(resourceParameterConfiguration.getProperty("total"));
		deviceUseStatus.setMonth(String.valueOf(DateUtil.getYear(Calendar.getInstance().getTime()))+"-"+ month);
		deviceUseStatus.setFullAmount(allFullAmount);
		deviceUseStatus.setAllAmount(allAmount);
		deviceUseStatus.setFull(((double)allFullAmount/(double)allAmount)*100);
		deviceUseStatus.setUseAmount(allUseAmount);
		deviceUseStatus.setSystemDaisTime(allSystemDaisTime);
		if (allUseAmount!=0){
			deviceUseStatus.setUsing(((double)allUseAmount/(double)allSystemDaisTime)*100);
		}else{
			deviceUseStatus.setUsing(0.0);
		}
		deviceUseStatus.setJQuseAmount(allJQuseAmount);
		deviceUseStatus.setJQsystemDaisTime(allJQsystemDaisTime);
		if (allJQuseAmount!=0){
			deviceUseStatus.setJQusing(((double)allJQuseAmount/(double)allJQsystemDaisTime)*100);
		}else {
			deviceUseStatus.setJQusing(0.0);
		}
		
		this.deviceUseStatusDao.storeDeviceUseStatus(deviceUseStatus);
	}

	public List getMonths() {
		return this.deviceUseStatusDao.getMonths();
	}
	
}
