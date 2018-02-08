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
package com.yongjun.tdms.service.runmaintenance.lubrication.pojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.HibernateException;

import com.yongjun.pluto.exception.CustomizeRuntimeException;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationPlanDao;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationPlanDetailDao;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationPlanViewDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanStatus;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanView;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationRuleManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

/**
 * <p>Title: DefaultLubricationPlanManager
 * <p>Description: 润滑计划业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: DefaultLubricationPlanManager.java 11075 2008-02-25 07:25:55Z zbzhang $
 */
public class DefaultLubricationPlanManager extends BaseManager implements
		LubricationPlanManager {
	private final LubricationPlanDao lubricationPlanDao;
	private final LubricationRuleManager lubricationRuleManager;
	private final SequenceManager sequenceManager;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager deviceCardManager;
	private final LubricationPlanDetailDao lubricationPlanDetailDao;
	private final WorkWarnningManager workWarnningManager;
	private final LubricationPlanViewDao lubricationPlanViewDao;
	
	private Properties resourceParameterConfiguration; 

	public DefaultLubricationPlanManager(LubricationPlanDao lubricationPlanDao,
			LubricationRuleManager lubricationRuleManager,
			SequenceManager sequenceManager,
			DepartmentManager departmentManager,
			DeviceCardManager deviceCardManager,
			LubricationPlanDetailDao lubricationPlanDetailDao,
			WorkWarnningManager workWarnningManager,
			LubricationPlanViewDao lubricationPlanViewDao) {
		this.lubricationPlanDao = lubricationPlanDao;
		this.lubricationRuleManager = lubricationRuleManager;
		this.sequenceManager = sequenceManager;
		this.departmentManager = departmentManager;
		this.deviceCardManager = deviceCardManager;
		this.lubricationPlanDetailDao = lubricationPlanDetailDao;
		this.workWarnningManager = workWarnningManager;
		this.lubricationPlanViewDao = lubricationPlanViewDao;
	}

	public void storeLubricationPlan(LubricationPlan lubricationPlan) {
		if (lubricationPlan.isNew()) {
			String planNo = (String)this.sequenceManager.generate("-");
			lubricationPlan.setPlanNo(planNo);
			lubricationPlan.setPlanProcFlag(PreRepairModel.PLAN);
			lubricationPlanDao.storeLubricationPlan(lubricationPlan);
			copyPlanToProc(lubricationPlan);
		} else {
			updatePlanToProc(lubricationPlan);
			lubricationPlanDao.storeLubricationPlan(lubricationPlan);
		}
	}
	/**
	 * 拷贝清洗计划，生成清洗实施
	 * @param washPlan 清洗计划
	 */
	private void copyPlanToProc(LubricationPlan lubricationPlan) {
		LubricationPlan lubricationProc = new LubricationPlan();
		copyPlanContentToProc(lubricationPlan, lubricationProc);
		lubricationProc.setPlanProcFlag(PreRepairModel.PROC);
		lubricationPlanDao.storeLubricationPlan(lubricationProc);
		//计划关联实施
		lubricationPlan.getLubricationProc().add(lubricationProc);
		this.lubricationPlanDao.storeLubricationPlan(lubricationPlan);
	}
	
	/**
	 * 拷贝清洗计划的内容到清洗实施中
	 * @param washPlan 清洗计划
	 * @param washProc 清洗实施
	 */
	private void copyPlanContentToProc(LubricationPlan lubricationPlan, LubricationPlan lubricationProc) {
		lubricationProc.setPlanNo(lubricationPlan.getPlanNo());
		lubricationProc.setName(lubricationPlan.getName());
		lubricationProc.setDepartment(lubricationPlan.getDepartment());
		lubricationProc.setMonth(lubricationPlan.getMonth());
		lubricationProc.setPlanCreatedTime(lubricationPlan.getPlanCreatedTime());
		lubricationProc.setPlanCreator(lubricationPlan.getPlanCreator());
		lubricationProc.setLubricationPlan(lubricationPlan);
		
	}
	
	/**
	 * 更新清洗计划内容到清洗实施中
	 * @param washPlan 清洗计划
	 */
	private void updatePlanToProc(LubricationPlan lubricationPlan) {
		for (LubricationPlan lubricationProc : lubricationPlan.getLubricationProc()) {
			copyPlanContentToProc(lubricationPlan, lubricationProc);
			lubricationPlanDao.storeLubricationPlan(lubricationProc);
		}
	}
	
	public void storeLubricationPlan(String lubricationRuleInfo) {
		String ary[] = lubricationRuleInfo.split(",");
		LubricationPlan lubricationPlan;
		LubricationRule lubricationRule = new LubricationRule();
		for (int i = 0; i < ary.length; i = i + 3) {
			lubricationPlan = new LubricationPlan();
			lubricationRule = lubricationRuleManager.loadLubricationRule(Long
					.valueOf(ary[i]));
			lubricationRule.setDutyPeople(ary[i+1]);
			lubricationRuleManager.storeLubricationRule(lubricationRule);//更新润滑标准
			/*
			lubricationPlan.setRule(lubricationRule);
			lubricationPlan.setDevice(lubricationRule.getDevice());
			lubricationPlan.setPosition(lubricationRule.getPosition());
			lubricationPlan.setContent(lubricationRule.getContent());
			lubricationPlan.setMethodDsp(lubricationRule.getMethodDsp());
			lubricationPlan.setLubricatingOil(lubricationRule.getLubricatingOil());
			lubricationPlan.setLubricatingOilQty(lubricationRule.getLubricatingOilQty());
			lubricationPlan.setLastLubricationDate(lubricationRule.getLastLubricationDate());
			lubricationPlan.setDutyPeople(lubricationRule.getDutyPeople());
			if(!ary[i+2].equals("0")){
				lubricationPlan.setEstimateExecDate(DateUtil.toDate(ary[i+2],"yyyy-mm-dd"));
			}
			*/
			lubricationPlanDao.storeLubricationPlan(lubricationPlan);
		}
	}
	
	private boolean existsLubricatePlanRecord(LubricationPlan lubricationPlan,Date date){
		//查询相同设备的润滑计划
		//FIXME: 需要这么查询吗？！
		List<LubricationPlan>list=lubricationPlanDao.loadAllLubricationPlansByDevice(lubricationPlan);
		if(list!=null){
			for(LubricationPlan plan:list){
				/*
				if(date.equals(plan.getEstimateExecDate())){
					return false;
				}
				*/
			}
		}
		return true;
	}
	
	//TODO: 不是 yyyy-mm-dd 而是 yyyy-MM-dd
	public void storeLubricationPuleModify(String lubricationInfo)throws Exception {
		String ary[]=lubricationInfo.split(",");
		LubricationPlan lubricationPlan=new LubricationPlan();
		for (int i = 0; i < ary.length; i = i + 3) {
			lubricationPlan=lubricationPlanDao.loadLubricationPlan(Long.valueOf(ary[i]));
			//lubricationPlan.setDutyPeople(ary[i+1]);
			if(!ary[i+2].equals("0")){
				//是否有时间记录
				if(!existsLubricatePlanRecord(lubricationPlan,DateUtil.toDate(ary[i+2],"yyyy-mm-dd"))){
					throw new Exception();
				}else{
					//是否小于当前系统时间
					Date currentSystemDate=new Date();
					//int s=DateUtil.toDate(ary[i+2],"yyyy-mm-dd").compareTo(currentSystemDate);
					//System.out.println(s);
					if(DateUtil.toDate(ary[i+2],"yyyy-mm-dd").before(currentSystemDate)){
						throw new Exception();
					}
				}
				//lubricationPlan.setEstimateExecDate(DateUtil.toDate(ary[i+2],"yyyy-mm-dd"));
				
			}else{
				//日期为空,返回失败信息
				//lubricationPlan.setEstimateExecDate(null);
				throw new Exception();
			}
			lubricationPlanDao.storeLubricationPlan(lubricationPlan);
		}
	}

	public List<LubricationPlan> loadAllLubricationPlan(
			Long[] lubricationPlanIds) {
		return lubricationPlanDao.loadAllLubricationPlan(lubricationPlanIds);
	}

	public LubricationPlan loadLubricationPlan(Long id) {
		return lubricationPlanDao.loadLubricationPlan(id);
	}

	public void deleteAllLubricationPlan(List<LubricationPlan> lubricationPlans) throws Exception{
		for (LubricationPlan lubricationPlan : lubricationPlans) {
			if (null != lubricationPlan.getPlanDetails()) {
				for (LubricationPlanDetail planDetail : lubricationPlan.getPlanDetails()) {
					if (planDetail.getPlanStatus().equals(LubricationPlanStatus.FINISHED)) {
						throw new CustomizeRuntimeException(planDetail.getPlan().getPlanNo());
					}
				}
			}
		}
		lubricationPlanDao.deleteAllLubricationPlan(lubricationPlans);
	}
	
	public List<LubricationPlan> loadAllMatchOptionLubricationPlan(Map map){
		return lubricationPlanDao.loadAllMatchOptionLubricationPlans(map);
	}

	public Properties getResourceParameterConfiguration() {
		return resourceParameterConfiguration;
	}

	public void setResourceParameterConfiguration(
			Properties resourceParameterConfiguration) {
		this.resourceParameterConfiguration = resourceParameterConfiguration;
	}
	
	//--------------------------------- 后台生成润滑计划   ----------------------------->
	public void createPlanByScheduler() {
		logger.trace("start running createPlanByScheduler for lubrication");
		// 1) 从设备卡片获取所有状态正常的设备（有效的，不在维修、封存中的设备，不是工装，）
		// 2) 判断设备是否有润滑标准，没有则继续判定下一个设备
		// 3) 根据设备的润滑标准中的周期和上次润滑日期，计算下次润滑日期
		// 4) 如果出现设备润滑周期超过一个月的，则判断计算日期的设备润计划是否已经存在，如果存在则跳过，不处理
		
		//获取所有部门
		List<Department> departments = this.departmentManager.loadAllDepartments();  
		//获取所有状态为正常的设备
		List<DeviceCard> devices = this.deviceCardManager.loadAllDeviceByStatusAndAssetType();
		for (Department department : departments) {
			boolean isFirst = true;                                      //用来标识生成润滑计划基础信息
			LubricationPlan plan = null;                                  //润滑计划
			List <User> userContainer = new ArrayList<User>();            //用来存放该润滑计划需要提醒的人  
			logger.debug("start create lubrication plan " + department.getName() + "at " + DateUtil.getSystemDate());
			for (DeviceCard device : devices) {
				if (isCreateLubricationPlanForDevice(device, department)) {
					if (isFirst) {
						try {
							//创建润滑计划
							plan = createNextMonthLubricationPlanBasedDepartment(department);       
						} catch(Exception e) {
							logger.error("create lubrication plan failure!",e);
						}
						isFirst = false;
					}
					logger.debug("... ... create plan detail " + device.getId());
					for (LubricationRule rule : device.getLubricationRules()) {
						if (null == rule.getCycle() || rule.getCycle() == 0) {     //如果润滑标准的润滑周期为空或为0，则跳过
							continue;
						}
						//创建润滑计划明细
						createNextMonthLubricationPlanDetailForDeviceBasedDeviceLubricationRule(device,rule,plan);
					}
					//收集润滑计划生成好,需要提醒的人
					this.workWarnningManager.getWarnningReceiverForDevice(device,userContainer);
				}
			}
			logger.debug("end create lubrication plan " + department.getName() + "at " + DateUtil.getSystemDate());
			if (null != plan) {                //如果润滑计划创建成功,则发送提醒消息
				//提醒内容
				String warnningContent = plan.getName() + "," + this.getResourceParameterConfiguration().getProperty("generated")
				                         + "," + this.getResourceParameterConfiguration().getProperty("please_watch");
				//向每个提醒人发送提醒信息
				this.workWarnningManager.sendWarnningMessage(userContainer, 
						this.getResourceParameterConfiguration().getProperty("GENERATE_LUBRICATION_PLAN"),
						warnningContent);
			}
		}
		logger.debug("... ...");
		logger.trace("end running createPlanByScheduler for lubrication,lubrication plan create successful!");
	}
	
	/**
	 * 判断设备是否具有生成润滑计划的条件
	 * @param device 设备实体
	 * @param department 部门实体
	 * @return  true | fasle    true:表示具有生成计划的条件,fasle:表示不具有
	 */
	private boolean isCreateLubricationPlanForDevice(DeviceCard device, Department department) {
		if (null != device.getDepartment()) {
			if (device.getDepartment().getId().equals(department.getId())) {              //设备部门是否与传进的部门参数相同
				if (device.getLubricationRules() != null) {                               //设备是否有润滑标准
					return true;
				}
			}
		}
		return false;
	}
	
	/***
	 * 创建一个部门的润滑计划
	 * @param department  部门
	 * @throws Exception
	 */
	private LubricationPlan createNextMonthLubricationPlanBasedDepartment(Department department)  throws Exception{
		logger.trace(" start create " + department.getName() + "lubrication plan " + DateUtil.getSystemDate());
		LubricationPlan plan = new LubricationPlan();
		//获取下个月
		int nextMonth = DateUtil.getMonth(Calendar.getInstance().getTime()) + 2;
		//生成润滑计划名称
		String planName = department.getName() + nextMonth + resourceParameterConfiguration.getProperty("lubricationPlanName");
		plan.setName(planName);              //设置计划名称
		//设置计划开始日期,默认是下个月1号
		//plan.setMonth(getTheFirstDayOfNextMonth(DateUtil.getSystemDate())); 
        //设置计划创建日期,默认是当前时间
		plan.setPlanCreatedTime(DateUtil.getSystemDate());
		//设置部门
		plan.setDepartment(department);
		//保存润滑计划
		this.storeLubricationPlan(plan);
		logger.trace(" end create " + department.getName() + "lubrication plan " + DateUtil.getSystemDate());
		return plan;
	}
	
	/**
	 * 创建该设备润滑标准在一个月内所有的润滑计划明细
	 * @param device 设备实体
	 * @param rule    设备润滑标准
	 * @param plan    润滑计划
	 */
	private void createNextMonthLubricationPlanDetailForDeviceBasedDeviceLubricationRule(
			DeviceCard device, LubricationRule rule, LubricationPlan plan) {
		logger.trace(" start create " + rule.getPosition() + "lubrication detail plan at " + DateUtil.getSystemDate());
		//获取润滑计划明细的计划执行日期
		Date planBeginExeDate = getTheFirstPlanBeginExeDateOfTheMonth(device, rule);
		//获取该明细在下个月最后执行的日期
		int lastDayOfTheMoth = DateUtil.getLastDayOfTheMonth(planBeginExeDate);
		//获取该明细的计划执行日期的月份
		int currentMonth = DateUtil.getMonth(this.getTheFirstDayOfNextMonth(DateUtil.getSystemDate()));
		//获取该明细的计划执行日期的年度
		int currentYear =  DateUtil.getYear(this.getTheFirstDayOfNextMonth(DateUtil.getSystemDate()));
		for (Date planExeDate = planBeginExeDate; getDay(planExeDate) < lastDayOfTheMoth
				&& currentMonth == DateUtil.getMonth(planExeDate) && currentYear == DateUtil.getYear(planExeDate); planExeDate = DateUtil
				.addDaysToDate(planExeDate, rule.getCycle())) {
			if (!isRestDate(planExeDate)) {    //TODO   接口,为休息日准备的接口
				try {
					//创建润滑计划明细
					createLubricatePlanDetail(planExeDate, plan, device, rule);
				} catch (Exception e) {
					logger.error("create lubrication plan detail error!" + e);
				}
			}
		}
		logger.trace(" end create " + rule.getPosition() + "lubrication detail plan at " + DateUtil.getSystemDate());
	}
	
	private boolean isMoreThanTheLastDayOfTheCurrentMonth(Date currentDate, Date compareDate) {
		if (getDay(currentDate) > getDay(compareDate)) {
			return true;
		}
		if (DateUtil.getMonth(currentDate) <= DateUtil.getMonth(compareDate)) {
			return true;
		}
		if (DateUtil.getYear(currentDate) <=DateUtil.getYear(compareDate)) {
			return true;
		}
		return false;
	}
	/**
	 * 获取每个设备中各个部位计划执行日期
	 * @param device   设备
	 * @param rule     润滑标准
	 * @return    Date   计划执行日期
	 */
	private Date getTheFirstPlanBeginExeDateOfTheMonth(DeviceCard device, LubricationRule rule) {
		Date lastActualExecDateOfMonth = null;
		Date lastLubricationDate = null;
		Date planBeginExeDate = null;
		//通过润滑标准中获取上次润滑日期
		if (null != rule.getLastLubricationDate()) {
			lastLubricationDate = rule.getLastLubricationDate();
		}
		 //获得此设备中该项润滑标准在本月中的最后润滑日期
		lastActualExecDateOfMonth = this.lubricationPlanDetailDao                            
				.loadMaxActualExecDateByDeviceIDAndRuleID(device.getId(), rule
						.getId());
		if (null != lastActualExecDateOfMonth && null != lastLubricationDate) {
			//如果润滑明细中的最后润滑日期大于润滑标准中的上次润滑日期,则根据最后润滑日期和润滑周期获取计划执行日期
			if (DateUtil.isStartBeforeEndDate(lastLubricationDate,lastActualExecDateOfMonth)) {
				planBeginExeDate = DateUtil.addDate(Calendar.DAY_OF_MONTH, lastActualExecDateOfMonth,rule.getCycle());
			} else {
		    //如果润滑明细中的最后润滑日期小于润滑标准中的上次润滑日期,则根据上次润滑日期和润滑周期获取计划执行日期
				planBeginExeDate = DateUtil.addDate(Calendar.DAY_OF_MONTH, lastLubricationDate,rule.getCycle());
			}
		} else if (null != lastLubricationDate) {
			//如果润滑明细中的最后润滑日期为空,则根据上次润滑日期和润滑周期获取计划执行日期
			planBeginExeDate = DateUtil.addDate(Calendar.DAY_OF_MONTH, lastLubricationDate,rule.getCycle());
		} else if (null != lastActualExecDateOfMonth) {
			//如果润滑标准中的上次润滑日期为空,则根据最后润滑日期和润滑周期获取计划执行日期
			planBeginExeDate = DateUtil.addDate(Calendar.DAY_OF_MONTH, lastLubricationDate,rule.getCycle());
		}
        // 获取下月的第一天的日期
		Date theFirstDayOfTheMonth = getTheFirstDayOfNextMonth(lastLubricationDate);
		//如果下个月1号的日期大于计算出来的计划执行日期,则把下个月1号的日期赋给计划执行日期
		if (DateUtil.isStartBeforeEndDate(planBeginExeDate,theFirstDayOfTheMonth)) {
			planBeginExeDate = theFirstDayOfTheMonth;
		}
		if (null == planBeginExeDate) {
			planBeginExeDate = getTheFirstDayOfNextMonth(DateUtil.getSystemDate());
		}
		return planBeginExeDate;
	}
	/**
	 * 根据计划执行时间,润滑计划,设备,润滑标准 创建润滑计划明细
	 * @param planExeDate
	 * @param plan
	 * @param device
	 * @param rule
	 * @throws Exception
	 */
	private void createLubricatePlanDetail(Date planExeDate,
			LubricationPlan plan, DeviceCard device, LubricationRule rule) throws Exception{
		logger.trace(" start create device " + device.getAssetNo() + " " + rule.getPosition() + " lubrication plan  detail " + DateUtil.getSystemDate());
		LubricationPlanDetail planDetail = new LubricationPlanDetail();
		planDetail.setDevice(device);                      //设置润滑的设备
		planDetail.setRule(rule);                          //设置润滑的标准
		planDetail.setPlan(plan);                          //设置润滑详细所关联的计划
		for (LubricationPlan proc : plan.getLubricationProc()) {   //设置润滑详细的所关联的实施
			planDetail.setProc(proc);
		}
		planDetail.setPosition(rule.getPosition());                  //设置维修部位
		planDetail.setLubricatingOil(rule.getLubricatingOil());      //设置润滑材质
		planDetail.setMethodDsp(rule.getMethodDsp());                //设置润滑方法
		planDetail.setRuleDsp(rule.getRuleDsp());                    //设置润滑标准
		planDetail.setEstimateExecDate(planExeDate);       //设置润滑详细计划执行日期
		planDetail.setActualExecDate(planExeDate);         //设置润滑详细实际执行日期
		planDetail.setPlanLubricatingOilQty(rule.getLubricatingOilQty());    //设置计划润滑计量
		planDetail.setActualLubricatingOilQty(rule.getLubricatingOilQty());  //设置实际润滑计量
		planDetail.setLubricatingOilMeasureUnit(rule.getLubricatingOilMeasureUnit());    //设置润滑计量单位
		planDetail.setDutyPeople(rule.getDutyPeople());               //设置润滑负责人
	    planDetail.setPlanExePeople(rule.getDutyPeople());            //设置计划执行人
	    planDetail.setActualExePeople(rule.getDutyPeople());          //设置实际执行人
	    
	    this.lubricationPlanDetailDao.storeLubricationPlanDetail(planDetail);
	    logger.trace(" end create device " + device.getAssetNo() + " " + rule.getPosition() + " lubrication plan  detail " + DateUtil.getSystemDate());
	}
	
	/**
	 * 根据传进来的日期，获取下个月1号的时间
	 * @param date  
	 * @return
	 */
	public Date getTheFirstDayOfNextMonth(Date date) {
		if (null == date) {
			Calendar c = Calendar.getInstance();
			date = c.getTime();
		}
		int year = DateUtil.getYear(date);     //获取本年度
		int month = DateUtil.getMonth(date) + 1;        //获取下个月
		//如果month>11,表示已经是第二年了
		if (month > 11) {
			year = DateUtil.getYear(date) + 1;      //获取第二年度         
			month = 0;     //置month为一月
		}
		Calendar cal = new GregorianCalendar(year, month, 1);         //构造下个月1号的时间对象
		return cal.getTime();
	}
	
	/**
	 * 获取天数
	 * @param date
	 * @return
	 */
	public static final int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_MONTH);

	}
	
	/**
	 * 接口
	 * @param date
	 * @return
	 */
	public boolean isRestDate(Date date) {
		return false;
	}
	public List<LubricationPlanView> loadAllLubricationPlanView(String[] array) throws HibernateException {
		return this.lubricationPlanViewDao.loadAllLubricationPlanView(array);
	}
}
