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
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.asset.device.MainProductDeviceCheckDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.report.MainProductDeviceCheck;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.MainProductDeviceCheckManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportDetailManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDetailManager;

/**
 * <p>Title: DefaultMainProductDeviceCheckManager
 * <p>Description: 主要生成设备使用考核业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id:  $
 */
public class DefaultMainProductDeviceCheckManager implements MainProductDeviceCheckManager {
	private final MainProductDeviceCheckDao mainProductDeviceCheckDao;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager deviceCardManager;
	private final MaintenanceDetailManager maintenanceDetailManager;
	private final CheckPointReportDetailManager checkPointReportDetailManager;
	private final CodeValueManager codeValueManager;
	private Properties resourceParameterConfiguration; 
	private final Log logger = LogFactory.getLog(this.getClass());
	public DefaultMainProductDeviceCheckManager(MainProductDeviceCheckDao mainProductDeviceCheckDao, DepartmentManager departmentManager,
			DeviceCardManager deviceCardManager,CheckPointReportDetailManager checkPointReportDetailManager, CodeValueManager codeValueManager,
			MaintenanceDetailManager maintenanceDetailManager){
		  this.mainProductDeviceCheckDao=mainProductDeviceCheckDao;
		  this.departmentManager=departmentManager;
		  this.deviceCardManager=deviceCardManager;
		  this.checkPointReportDetailManager=checkPointReportDetailManager;
		  this.codeValueManager=codeValueManager;
		  this.maintenanceDetailManager=maintenanceDetailManager;
		
		  
	}
	public Properties getResourceParameterConfiguration() {
		return resourceParameterConfiguration;
	}
	public void setResourceParameterConfiguration(
			Properties resourceParameterConfiguration) {
		this.resourceParameterConfiguration = resourceParameterConfiguration;
	}
	public void storeMainProductDeviceCheck(MainProductDeviceCheck mainProductDeviceCheck) {
		this.storeMainProductDeviceCheck(mainProductDeviceCheck);
		
	}
	public List Query(String month) throws HibernateException {
		return mainProductDeviceCheckDao.Query(month);
	}
	
//	--------------------------------- 后台主要设备综合考核月报表   ----------------------------->
	/* 
	 * 1) 从设备表中获取所有(重点/生产)设备,该月报表只统计的是重点/生产设备
	 * 2) 根据点检报告单获取该设备上一个月的运行台时
	 * 3) 单独获取各部门的金切设备
	 * 4) 根据点检报告单获取金切设备上一个月的运行台时
	 * 5) 根据设备的各字段值进行计算
	 */ 
	public void createMainDeviceProductByScheduler() {
		//int totalDeviceAmount=0;       //总设备数
	 	int fullAmount =0;             //完好设备数
	 	int unFullDeviceAmount=0;      //带病设备数
	 	int stopUseAmount=0;           //停机带修台数
        //int deviceFullModulus=0;       //设备完好率
        int useAmount=0;               //实动台数
        int keepAmount=0;              //封存台数
        int useDaisTime=0;             //实动台时(运行台时)
        int systemDaisTime=0;          //制度台时
        int  maintainDate=0;           //维修工时(时)
        int faultStopDate=0;           // 故障停机台时(时)
        int JQuseAmount  = 0;			//金切实际开动台时（小时）
		int JQsystemDaisTime   = 0;		//金切制度台时(时)
        double deviceUseModulus=0.0;    //设备利用率
        double deviceEffectModulus=0.0;  //设备有效利用率
        double faultStopModulus;         //故障停机率
        double JQusing=0.0;              //金切利用率 
        int  planMaintainAmount=0;       //计划保养台数
        int practiceMaintainAmount=0;    //实际保养台数
        Date figureDate; 
        
        int allDeviceFullAmount=0;          //当月各部门的所有的设备总数
        int allFullAmount   = 0;			//当月各部门完好设备数（台）
    	int allUseAmount  = 0;				//当月各部门实际开动台时（小时），就是运行台时
    	int allSystemDaisTime  = 0 ;		//当月各部门制度台时(时)
    	int allJQuseAmount  = 0;			//当月各部门金切实际开动台时（小时）
    	int allJQsystemDaisTime   = 0;		//当月各部门金切制度台时(时)
    	int allunFullDeviceAmount =0;       //当月各部门带病设备台数(台)
    	int allstopUseAmount=0;             //当月各部门停机带修台数
    	int alluseAmount=0;                 //当月各部门设备的实动台数
    	int allkeepAmount=0;                //当月各部门设备的封存台数 
    	int alluseDaisTime=0;               //当月各部门的实动台时
    	
         //获取
		List<Department> departments = this.departmentManager.loadAllDepartments();
		List<DeviceCard> devices = this.deviceCardManager.loadAllDevices();
		MainProductDeviceCheck mainProductDeviceCheck;
		List<DeviceCard> devicesByDeparments;
		CheckPointReportDetail checkPointReportDetail =  new CheckPointReportDetail();   //new 点检报告明细
		String month = null;
		if(DateUtil.getMonth(Calendar.getInstance().getTime())<10) {
			month = "0"+String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
		}else {
			month = String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
		}
		Maintenance maintenance=new Maintenance();
		for(Department department : departments){
			devicesByDeparments = new ArrayList<DeviceCard>();
			for(DeviceCard device : devices) {
				if (device.getDepartment()!=null) {
					if (device.getDepartment().getId().equals(department.getId())			//判断该设备是否属于该部门
							&& device.isEmphasis()){
						//判断该设备是否为“重点”设备
						if (device.getUseCategory()!= null){
							if (device.getUseCategory().getCode().equals("0051")){			//判断该设备是否为“生产”设备
								devicesByDeparments.add(device);
							}
						}
					}
				}
			}
			if (devicesByDeparments.size()!=0) {							//表示该部门存在“重点”和“生产”设备，可以生成"主要生产设备使用状况月报表"
				mainProductDeviceCheck = new MainProductDeviceCheck();
				mainProductDeviceCheck.setDepartment(department.getName());
				for (DeviceCard device : devicesByDeparments) {//判断设备是否完好
					if (device.isFull() == true){
						fullAmount ++;           //完好设备数量 
						allFullAmount ++;        //当月各部门完好设备数
					}else{
						unFullDeviceAmount++;    //带病设备数量
						allunFullDeviceAmount++;  //当月各部门带病设备数
					}
					//如果当前设备状态为有效并且带修  计算停机带修台数  
					if(device.getDisabled()==true&&device.getDeviceStatus().equals(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_REPAIR_STATUS))){
						stopUseAmount++;
						allstopUseAmount++;
					}
					//如果当前设备状态为正常状态 获得设备的实动台数
					if(device.getDeviceStatus().equals(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_NORMAL_STATUS))){
						useAmount++;
						alluseAmount++;
					}
					//如果当前设备状态为封存状态  获得设备的封存台数
					if(device.getDeviceStatus().equals(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_DEMARCATE_STATUS))){
						keepAmount++;
						allkeepAmount++;
					}
					
					if (this.checkPointReportDetailManager.loadDetailBydeviceID(device.getId(),month)!=null){
					checkPointReportDetail = this.checkPointReportDetailManager.loadDetailBydeviceID(device.getId(),month);
					//运行台时
					if (checkPointReportDetail.getRunTime()!=null){
					     useDaisTime += checkPointReportDetail.getRunTime();
					     alluseDaisTime+=useDaisTime;
					     //allUseAmount += useAmount;
					  }
					}
					//计算该设备当月的制度台时,在此以一个月22个工作日计算.
					if (device.getSystemDaisTime()!=null){
					      systemDaisTime += device.getSystemDaisTime()*22;//制度台时
					      allSystemDaisTime += systemDaisTime;
					}
					//判断金切设备,单独拿出来计算
					if (device.getDeviceType().getCode().equals("0")){
						if (checkPointReportDetail.getRunTime()!=null){
							JQusing += checkPointReportDetail.getRunTime();
							allJQuseAmount += JQusing;
						}
						if (device.getSystemDaisTime()!=null){
							JQsystemDaisTime += device.getSystemDaisTime()*22;
							allJQsystemDaisTime += JQsystemDaisTime;
						}
					}
					//从保养明细中通过设备的id  load出保养明细对象  并且判断保养明细中所关联的保养标示是计划还是实施
				 //MaintenanceDetail maintenanceDtl=maintenanceDetailManager.loadMaintenanceDetailBydeviceID(device.getId());
					MaintenanceDetail maintenanceDtl=maintenanceDetailManager.loadMaintenanceDetailBydeviceIDAndMonth(device.getId(),maintenance.getMonth());	
				   if(maintenanceDtl!=null){
					  if(maintenanceDtl.getPlan().getPlanProcFlag().equals(PreRepairModel.PLAN)){
						  planMaintainAmount++;                           //计划保养台数
					   }
					  if(maintenanceDtl.getPlan().getPlanProcFlag().equals(PreRepairModel.PROC)){
						  practiceMaintainAmount++;                       //实际保养台数
					  }
				   }
		      }
				mainProductDeviceCheck.setDepartment(department.getName());
				//String month = null;
//				if(DateUtil.getMonth(Calendar.getInstance().getTime())<10) {
//					month = "0"+String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
//				}else {
//					month = String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
//				}
				mainProductDeviceCheck.setMonth(String.valueOf(DateUtil.getYear(Calendar.getInstance().getTime()))+"-"+ month);	
				mainProductDeviceCheck.setFullDeviceAmount(fullAmount);	                 //设置完好设备台数
				mainProductDeviceCheck.setUnFullDeviceAmount(unFullDeviceAmount);        //设置设备的带病台数
				mainProductDeviceCheck.setDeviceTotal(devicesByDeparments.size());   	 //设置设备的总台数
				allDeviceFullAmount+=devicesByDeparments.size();                               //当月各部门的设备数
				mainProductDeviceCheck.setDeviceFullModulus((double)fullAmount/(double)devicesByDeparments.size());  //每个部门的当月的设备完好利用率
				fullAmount=0;                                                            //当前部门设备台数保存之后 将清0
				unFullDeviceAmount=0;                                                    //当前部门带病设备台数保存之后 将清0
				mainProductDeviceCheck.setStopUseAmount(stopUseAmount);                  //设置当前部门停机代修台数
				stopUseAmount=0;                                                         //当前部门停止带修设备台数保存之后 将清0
				mainProductDeviceCheck.setUseAmount(useAmount);                          //设置当前部门的设备的实动台数
				useAmount=0;                                                             //当前部门实动设备台数保存之后 将清0
				mainProductDeviceCheck.setKeepAmount(keepAmount);                        //设置当前部门设备的封存台数
				keepAmount=0;                                                            //设置当前部门设备的封存台数保存之后将清0
				mainProductDeviceCheck.setUseDaisTime(useDaisTime);                      //设置当前部门设备的实动台时
				mainProductDeviceCheck.setSystemDaisTime(systemDaisTime);                //设置当前部门设备的制度台时
				mainProductDeviceCheck.setDeviceUseModulus((double)useDaisTime/(double)systemDaisTime); //设置当前部门的设备利用率
				useDaisTime=0;                                                                          //设置当前部门设备的实动台时保存之后将清0
				systemDaisTime=0;                                                                       //设置当前部门设备的制度台时保存之后将清0
				mainProductDeviceCheck.setJQuseAmount(JQuseAmount);
				mainProductDeviceCheck.setJQsystemDaisTime(JQsystemDaisTime);
				if (JQuseAmount!=0){
					mainProductDeviceCheck.setJQusing(((double)JQuseAmount/(double)JQsystemDaisTime)*100);
				}else {
					mainProductDeviceCheck.setJQusing(0.0);
				}
				JQuseAmount = 0;
				JQsystemDaisTime = 0;
				mainProductDeviceCheck.setPlanMaintainAmount(planMaintainAmount);                       // 设置当前部门的计划保养台数  
				mainProductDeviceCheck.setPracticeMaintainAmount(practiceMaintainAmount);               //实际保养台数
				mainProductDeviceCheckDao.storeMainProductDeviceCheck(mainProductDeviceCheck);
			}
		}
		//综合统计
		mainProductDeviceCheck = new MainProductDeviceCheck();
		mainProductDeviceCheck.setDepartment(resourceParameterConfiguration.getProperty("total"));
//		String month = null;
//		if(DateUtil.getMonth(Calendar.getInstance().getTime())<10) {
//			month = "0"+String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
//		}else {
//			month = String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime()));
//		}
		mainProductDeviceCheck.setMonth(String.valueOf(DateUtil.getYear(Calendar.getInstance().getTime()))+"-"+ month);	
		mainProductDeviceCheck.setFullDeviceAmount(allFullAmount);                 //完好设备总数   
		mainProductDeviceCheck.setDeviceTotal(allDeviceFullAmount);                //所有设备总数
		mainProductDeviceCheck.setUnFullDeviceAmount(allunFullDeviceAmount);       //所有带病设备数
		mainProductDeviceCheck.setStopUseAmount(allstopUseAmount);                 //停机待修总台数
		mainProductDeviceCheck.setKeepAmount(allkeepAmount);                       //总的封存台数
		mainProductDeviceCheck.setUseAmount(alluseAmount);                         //总的时动台数 
		if(allFullAmount!=0){
			mainProductDeviceCheck.setDeviceFullModulus(((double)allFullAmount/(double)allDeviceFullAmount)*100);//设备完好利用率
		}else{
			mainProductDeviceCheck.setDeviceFullModulus(0.0);
		}
		mainProductDeviceCheck.setUseDaisTime(alluseDaisTime);            //总的时动台时
		mainProductDeviceCheck.setSystemDaisTime(allSystemDaisTime);    //总的制度台时
		if (allUseAmount!=0){
			mainProductDeviceCheck.setJQusing(((double)allUseAmount/(double)allSystemDaisTime)*100);//金切利用率
		}else{
			mainProductDeviceCheck.setJQusing(0.0);
		}
		mainProductDeviceCheck.setJQuseAmount(allJQuseAmount);
		mainProductDeviceCheck.setJQsystemDaisTime(allJQsystemDaisTime);
		if (allJQuseAmount!=0){
			mainProductDeviceCheck.setJQusing(((double)allJQuseAmount/(double)allJQsystemDaisTime)*100);
		}else {
			mainProductDeviceCheck.setJQusing(0.0);
		}
		mainProductDeviceCheckDao.storeMainProductDeviceCheck(mainProductDeviceCheck);
	}
	public List getMonths() {
		return this.mainProductDeviceCheckDao.getMonths();
	}
	
}
