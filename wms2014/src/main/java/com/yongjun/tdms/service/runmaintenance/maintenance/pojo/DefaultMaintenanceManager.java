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
package com.yongjun.tdms.service.runmaintenance.maintenance.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDao;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDetailDao;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDetailViewDao;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDeviceDetailDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetailView;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDeviceDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceResultType;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceRule;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

/**
 * <p>Title: DefaultMaintenanceManager
 * <p>Description: 设备保养业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:  $
 */
public class DefaultMaintenanceManager implements MaintenanceManager{
	private final MaintenanceDao maintenanceDao;
	private final SequenceManager sequenceManager;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager deviceCardManager;
	private final MaintenanceDetailDao maintenanceDetailDao;
	private final CodeValueManager codeValueManager;
	private final MaintenanceDeviceDetailDao maintenanceDeviceDetailDao;
	private final WorkWarnningManager workWarnningManager;
	private final MaintenanceDetailViewDao maintenanceDetailViewDao;
	
	private Properties resourceParameterConfiguration; 
	private final Log logger = LogFactory.getLog(this.getClass());
	
	public Properties getResourceParameterConfiguration() {
		return resourceParameterConfiguration;
	}
	
	public void setResourceParameterConfiguration(
			Properties resourceParameterConfiguration) {
		this.resourceParameterConfiguration = resourceParameterConfiguration;
	}
	
	public DefaultMaintenanceManager (MaintenanceDao maintenanceDao,
			SequenceManager sequenceManager,
			DepartmentManager departmentManager,
			DeviceCardManager deviceCardManager,
			MaintenanceDetailDao maintenanceDetailDao,
			CodeValueManager codeValueManager,
			MaintenanceDeviceDetailDao maintenanceDeviceDetailDao,
			WorkWarnningManager workWarnningManager,
			MaintenanceDetailViewDao maintenanceDetailViewDao){
		this.maintenanceDao = maintenanceDao;
		this.sequenceManager = sequenceManager;
		this.departmentManager = departmentManager;
		this.deviceCardManager = deviceCardManager;
		this.maintenanceDetailDao = maintenanceDetailDao;
		this.codeValueManager = codeValueManager;
		this.maintenanceDeviceDetailDao = maintenanceDeviceDetailDao;
		this.workWarnningManager = workWarnningManager;
		this.maintenanceDetailViewDao = maintenanceDetailViewDao;
	}
	public List<Maintenance> loadAllMaintenances(Long[] maintenanceIds) {
		return this.maintenanceDao.loadAllMaintenances(maintenanceIds);
	}

	public void deleteAllMaintenances(Collection<Maintenance> maintenances) {
		this.maintenanceDao.deleteAllMaintenances(maintenances);
		//如果保养计划的设备在实施中已经实施,那么实施的设备在保养计划总不能够删除
		//updateMaintenancePlanByMaintenanceProcNotDelete(maintenances);
	}
//     public void updateMaintenancePlanByMaintenanceProcNotDelete(Collection<Maintenance> maintenances){
//    	 for(Maintenance mainten:maintenances){
//    		 
//    	 }
//     }
	public void storeMaintenance(Maintenance maintenance) {
		if(maintenance.isNew()) {
			String planNo = (String)sequenceManager.generate("-");
			maintenance.setPlanNo(planNo);
			this.maintenanceDao.storeMaintenance(maintenance);
			copyMaintenance(maintenance);
		}else {
			updatePlanToProc(maintenance);
			this.maintenanceDao.storeMaintenance(maintenance);
		}
	}

	public Maintenance loadMaintenance(Long maintenanceId) {
		return this.maintenanceDao.loadMaintenance(maintenanceId);
	}
	
	private void copyMaintenance(Maintenance plan){
		Maintenance proc = new Maintenance();
		copyPlanToProc(plan,proc);
		proc.setPlanProcFlag(PreRepairModel.PROC);                 //设置实施标识 
		this.maintenanceDao.storeMaintenance(proc);
		plan.getMaintenanceProc().add(proc);
		this.maintenanceDao.storeMaintenance(plan);
	}
	
	private void copyPlanToProc(Maintenance plan,Maintenance proc){
		proc.setMaintenancePlan(plan);
		proc.setPlanNo(plan.getPlanNo());
		proc.setPlanName(plan.getPlanName());
//		proc.setWriter(plan.getWriter());
		proc.setMakeDate(plan.getMakeDate());
		proc.setMonth(plan.getMonth());
		proc.setDepartment(plan.getDepartment());
//		proc.setScheduleDate(plan.getScheduleDate());
//		proc.setVerifyPeople(plan.getVerifyPeople());
		proc.setResult(plan.getResult());
		//proc.setDisabled(true);
	}
	
	private void updatePlanToProc(Maintenance plan) {
		for (Maintenance proc : plan.getMaintenanceProc()) {
			copyPlanToProc(plan,proc);
			this.maintenanceDao.storeMaintenance(proc);
		}
	}
	public void disabledAllMaintenances(Collection<Maintenance> maintenances) throws Exception{
		for (Maintenance m : maintenances) {
			//如果在当前的保养单编号的保养明细列表中,次保养明细在保养实施中的状态为已完成,那么不能失效当前保养单
			if(m.getMaintenancePlanDetials().size()>0){
				for(MaintenanceDetail mDtl:m.getMaintenancePlanDetials()){
					if(mDtl.getResult().equals(MaintenanceResultType.FINISHED)){
						throw new Exception();
			        }
				}
			}	
			m.setDisabled(true);
			this.maintenanceDao.storeMaintenance(m);
			 for(Maintenance proc :m.getMaintenanceProc()){
				 //如果保养计划单失效了 那么实施中的此保养单也要失效   
				  copyPlanDisableToProcDisable(m,proc);
			  }
     		}
	   }
	 public void copyPlanDisableToProcDisable(Maintenance plan,Maintenance proc){
		 proc.setDisabled(true);
		 this.maintenanceDao.storeMaintenance(proc);
	 }
	public void enabledAllMaintenances(Collection<Maintenance> maintenances) {
		for (Maintenance m : maintenances) {
			m.setDisabled(false);
			this.maintenanceDao.storeMaintenance(m);
			 for(Maintenance proc :m.getMaintenanceProc()){
				 //如果保养计划单有效了 那么实施的此保养单也要有效 
				  copyPlanEnabledToProcEnabled(m,proc);
			  }
		}
	}
	
	public void copyPlanEnabledToProcEnabled(Maintenance plan,Maintenance proc){
		 proc.setDisabled(false);
		 this.maintenanceDao.storeMaintenance(proc);
	}
//	--------------------------------- 后台设备保养单   ----------------------------->
	/* 1) 从设备表中获取所有(重点/生产)设备
	 * 2) 获取该设备的一保\二保 总台时，一保\二保台时。
	 * 3) 根据该设备的制度台*22个工作日，算出该设备当月的运行台时
	 *   （因为保养单是月中生成，点检报告单是月底提交，所以当月的运行台时无法统计出，只能根据制度台时*工作日来计算）
	 * 4) 判断一保\二保 总台时，是否分别大于等于一保\二保台时，从而生成保养计划，同时将该设备的一保\二保 总台时清0
	 *   
	 */
	
	public void createMaintenancePlanByScheduler() {
		List<Department> departments = this.departmentManager.loadAllDepartments();
		List<DeviceCard> devices = this.deviceCardManager.loadAllDevices();
		List<DeviceCard> devicesByDeparments;
		//各部门的计划保养单
		List<Maintenance> plans;
		Set<MaintenanceRule> maintenanceRules;
		Maintenance maintenance = null;
		MaintenanceDetail maintenanceDetail = null;
		MaintenanceDeviceDetail maintenanceDeviceDetail = null;
		// 获取各部门的设备
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
			if (devicesByDeparments.size()!=0) {              //表示该部门存在“重点”和“生产”设备，可以生成保养计划
				logger.info("start create " + department.getName() +"maintenance plan at " + DateUtil.getSystemDate());
				maintenance = new Maintenance();
				List <User> userContainer = new ArrayList<User>();            //用来存放该润滑计划需要提醒的人  
				maintenance.setPlanName(department.getName()+DateUtil.getYear(Calendar.getInstance().getTime())
						+resourceParameterConfiguration.getProperty("year")
						+(DateUtil.getMonth(Calendar.getInstance().getTime())+2)+resourceParameterConfiguration.getProperty("month")
						+resourceParameterConfiguration.getProperty("maintenance"));
				maintenance.setDepartment(department);
				//设置部门保养单的计划执行月份
				String month = null;
				if(DateUtil.getMonth(Calendar.getInstance().getTime())+2<10) {
					month = "0"+String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime())+2);
				}else {
					month = String.valueOf(DateUtil.getMonth(Calendar.getInstance().getTime())+2);
				}
				maintenance.setMonth(String.valueOf(DateUtil.getYear(Calendar.getInstance().getTime()))+"-"+ month);
				maintenance.setMakeDate(Calendar.getInstance().getTime());
				maintenance.setPlanProcFlag(PreRepairModel.PLAN);
				this.storeMaintenance(maintenance);			//保存部门的保养单,在此创建每个部门的保养单
				for(DeviceCard device : devicesByDeparments){
					if (device.getOneMaintenanceTime()!= null && device.getOneMaintenanceTime()!=0){		//没有一保台时时，不创建一保计划	
						//一保计划
						if (device.getOneMaintenanceHour()+device.getSystemDaisTime()*22 >= device.getOneMaintenanceTime()){
							maintenanceDetail = new MaintenanceDetail();
							maintenanceDeviceDetail = new MaintenanceDeviceDetail();
							maintenanceRules = new HashSet();
							//保存保养明细
							maintenanceDetail.setResultType(this.codeValueManager.loadCodeTypeByRealCode("one_maintenance"));
							maintenanceDetail.setDevice(device);
							maintenanceDetail.setDutyPeople(device.getManager());
							//设置保养计划明细完成日期为下一个月的28号
							String sDate = DateUtil.getYear(Calendar.getInstance().getTime())+"-"+(DateUtil.getMonth(Calendar.getInstance().getTime())+2)+"-"+"28";
							SimpleDateFormat   df   =new   SimpleDateFormat("yyyy-MM-dd");   
							java.util.Date cDate = null;
							try {
								cDate = df.parse(sDate);
							} catch (ParseException e) {
								e.printStackTrace();
							}   
							java.sql.Date   dd   =   new   java.sql.Date(cDate.getTime()); 
							maintenanceDetail.setPlanDate(dd);
							maintenanceDetail.setPlan(maintenance);
							for(Maintenance proc : maintenance.getMaintenanceProc()){
								maintenanceDetail.setProc(proc);
							}
							this.maintenanceDetailDao.storeMaintenanceDetail(maintenanceDetail);
							//因为事务还未提交，现数据还未进入数据库
							maintenance.getMaintenancePlanDetials().add(maintenanceDetail);
							this.maintenanceDao.storeMaintenance(maintenance);
							//在保存保养明细后，再保存设备保养明细对象
							maintenanceDeviceDetail.setMaintenanceDetail(maintenanceDetail);
							for(MaintenanceRule rule : device.getMaintenanceRules()) {
								//0251表示一保,获取该设备的所有一保保养标准，保存至Set集合的maintenanceRules对象中
								if(rule.getMaintenanceType().getCode().equals("0251")){
									maintenanceRules.add(rule);
								}
							}
							//将该设备的一保标准的部位、要求、方法复制到maintenanceDeviceDetail中
							for (MaintenanceRule rule : maintenanceRules){
								maintenanceDeviceDetail.setPart(rule.getPart());
								maintenanceDeviceDetail.setAppeal(rule.getAppeal());
								maintenanceDeviceDetail.setMethod(rule.getMethod());
								maintenanceDeviceDetail.setDevice(device);
								this.maintenanceDeviceDetailDao.storeMaintenanceDeviceDetail(maintenanceDeviceDetail);
							}
						}
					}
					if (device.getTwoMaintenanceTime()!=null && device.getTwoMaintenanceTime()!=0){			//没有二保台时时，不创建二保计划
						//二保计划
						if (device.getTwoMaintenanceHour()+device.getSystemDaisTime()*22 >= device.getTwoMaintenanceTime()){
							maintenanceDetail = new MaintenanceDetail();
							maintenanceDeviceDetail = new MaintenanceDeviceDetail();
							maintenanceRules = new HashSet();
							maintenanceDetail.setResultType(this.codeValueManager.loadCodeTypeByRealCode("two_maintenance"));
							maintenanceDetail.setDevice(device);
							maintenanceDetail.setDutyPeople(device.getManager());
							//设置保养计划完成日期为下一个月的28号
							String sDate = DateUtil.getYear(Calendar.getInstance().getTime())+"-"+(DateUtil.getMonth(Calendar.getInstance().getTime())+2)+"-"+"28";
							SimpleDateFormat   df   =new   SimpleDateFormat("yyyy-MM-dd");   
							java.util.Date cDate = null;
							try {
								cDate = df.parse(sDate);
							} catch (ParseException e) {
								e.printStackTrace();
							}   
							java.sql.Date   dd   =   new   java.sql.Date(cDate.getTime()); 
							maintenanceDetail.setPlanDate(dd);
							maintenanceDetail.setPlan(maintenance);
							for(Maintenance proc : maintenance.getMaintenanceProc()){
								maintenanceDetail.setProc(proc);
							}
							this.maintenanceDetailDao.storeMaintenanceDetail(maintenanceDetail);
							maintenance.getMaintenancePlanDetials().add(maintenanceDetail);
							this.maintenanceDao.storeMaintenance(maintenance);
                            //在保存保养明细后，再保存设备保养明细对象
							maintenanceDeviceDetail.setMaintenanceDetail(maintenanceDetail);
							for(MaintenanceRule rule : device.getMaintenanceRules()) {
								//0252表示一保,获取该设备的所有二保保养标准，保存至Set集合的maintenanceRules对象中
								if(rule.getMaintenanceType().getCode().equals("0252")){
									maintenanceRules.add(rule);
								}
							}
							//将该设备的一保标准的部位、要求、方法复制到maintenanceDeviceDetail中
							for (MaintenanceRule rule : maintenanceRules){
								maintenanceDeviceDetail.setPart(rule.getPart());
								maintenanceDeviceDetail.setAppeal(rule.getAppeal());
								maintenanceDeviceDetail.setMethod(rule.getMethod());
								maintenanceDeviceDetail.setDevice(device);
								this.maintenanceDeviceDetailDao.storeMaintenanceDeviceDetail(maintenanceDeviceDetail);
							}
						}
					}
					//收集润滑计划生成好,需要提醒的人
					this.workWarnningManager.getWarnningReceiverForDevice(device,userContainer);
				}
				//根据判断该部门是否有保养计划单，如果没有删除之前建立的下一个月部门保养单
				if (maintenance.getMaintenancePlanDetials().size() == 0){
					//删除该部门的保养实施单
					for( Maintenance proc : maintenance.getMaintenanceProc()){
						maintenanceDao.deleteMaintenance(proc);
				        maintenance.getMaintenanceProc().remove(proc);
					}
					maintenanceDao.deleteMaintenance(maintenance);
					maintenance = null;
					logger.info("end create " + department.getName() +"maintenance plan at " + DateUtil.getSystemDate() + " create failure,reason: not maintenance plan detail!");
				}
				logger.info("end create " + department.getName() +"maintenance plan at " + DateUtil.getSystemDate() + " create successful!");
				if (null != maintenance) {                //如果保养计划创建成功,则发送提醒消息
					//提醒内容
					String warnningContent = maintenance.getPlanName() + "," + this.getResourceParameterConfiguration().getProperty("generated")
					                         + "," + this.getResourceParameterConfiguration().getProperty("please_watch");
					//向每个提醒人发送提醒信息
					this.workWarnningManager.sendWarnningMessage(userContainer, 
							this.getResourceParameterConfiguration().getProperty("GENERATE_MAINTENANCE_PLAN"),
							warnningContent);
				}
			  }	
		   }
       }
	public List<MaintenanceDetailView> loadAllMaintenanceDetailView(String[] array) throws HibernateException {
		return this.maintenanceDetailViewDao.loadAllMaintenanceDetailView(array);
	}
	public Maintenance getMaintenanceByPlanNoAndProc(String planNo) {
		
		return maintenanceDao.getMaintenanceByPlanNoAndProc(planNo);
	}
}
