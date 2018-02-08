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
package com.yongjun.tdms.service.runmaintenance.repair.pojo;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDetailDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;
import com.yongjun.tdms.model.runmaintenance.repair.RepairFee;
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;
import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.asset.device.DeviceSpareManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.repair.BrockenRateManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairRuleManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairFeeManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairItemManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairManHourManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairSpareManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairToolManager;
import com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;

/**
 * <p>Title: DefaultPreRepairPlanDetailManager
 * <p>Description: 预防性维修计划详细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: DefaultPreRepairPlanDetailManager.java 11198 2008-03-05 09:17:12Z zbzhang $
 * @see com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager
 */
public class DefaultPreRepairPlanDetailManager implements
		PreRepairPlanDetailManager {
	private final PreRepairPlanDetailDao preRepairPlanDetailDao;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final PreRepairPlanDao preRepairPlanDao;
	private final CodeValueManager codeValueManager;
	private final RepairItemManager repairItemManager;
	private final RepairSpareManager repairSpareManager;
	private final RepairFeeManager repairFeeManager;
	private final RepairManHourManager repairManHourManager;
	private final RepairToolManager repairToolManager;
	private final ApplicationDocManager repairDocManager;
	private final PreRepairPlanManager preRepairPlanManager;
	private final CheckManager checkManager;
	private final BrockenRateManager  brockenRateManager ;
	private final PreRepairRuleManager preRepairRuleManager;
	private final DeviceSpareManager toolingDevSpareManager;
	private final BudgetDetailManager budgetDetailManager;
	
	
	public DefaultPreRepairPlanDetailManager(PreRepairPlanDetailDao preRepairPlanDetailDao,
			DepartmentManager departmentManager,
			UserManager userManager,
			PreRepairPlanDao preRepairPlanDao,
			CodeValueManager codeValueManager,
			RepairItemManager repairItemManager,
			RepairSpareManager repairSpareManager,
			RepairFeeManager repairFeeManager,
			RepairManHourManager repairManHourManager,
			RepairToolManager repairToolManager,
			ApplicationDocManager repairDocManager,
			PreRepairPlanManager preRepairPlanManager,CheckManager checkManager,
			BrockenRateManager  brockenRateManager,
			PreRepairRuleManager preRepairRuleManager,
			DeviceSpareManager toolingDevSpareManager,
			BudgetDetailManager budgetDetailManager) {
		this.preRepairPlanDetailDao = preRepairPlanDetailDao;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.preRepairPlanDao = preRepairPlanDao;
		this.codeValueManager = codeValueManager;
		this.repairItemManager = repairItemManager;
		this.repairSpareManager = repairSpareManager;
		this.repairFeeManager = repairFeeManager;
		this.repairManHourManager = repairManHourManager;
		this.repairToolManager = repairToolManager;
		this.repairDocManager = repairDocManager;
		this.preRepairPlanManager = preRepairPlanManager;
		this.checkManager=checkManager;
		this.brockenRateManager=brockenRateManager;
		this.preRepairRuleManager = preRepairRuleManager;
		this.toolingDevSpareManager=toolingDevSpareManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	public PreRepairPlanDetail loadPreRepairPlanDetail(
			Long preRepairPlanDetailId) {
		return this.preRepairPlanDetailDao.loadPreRepairPlanDetail(preRepairPlanDetailId);
	}

	public List<PreRepairPlanDetail> loadAllPreRepairPlanDetails(
			Long[] preRepairPlanDetailIds) {
		return this.preRepairPlanDetailDao.loadAllPreRepairPlanDetails(preRepairPlanDetailIds);
	}

	public List<PreRepairPlanDetail> loadAllPreRepairPlanDetails() {
		return this.preRepairPlanDetailDao.loadAllPreRepairPlanDetails();
	}

	public void storePreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) throws Exception{
		
		//预防性维修计划中选中的设备已经制定了计划，则不能重新制定
		if (preRepairPlanDetail.isNew() && null != preRepairPlanDetail.getAsset()) {
			PreRepairPlanDetail detail = null;
			try {
				detail = this.preRepairPlanDetailDao.getPreRepairPlanDetailByPlanIdAndDeviceId(preRepairPlanDetail.getPlan().getId(),
						preRepairPlanDetail.getAsset().getId());
				if (null != detail) {
					throw new Exception();
				}
			} catch (Exception ex) {
				throw new Exception();
			}
		}
//		if(preRepairPlanDetailDao.getPreRepairPlanDetailByAndDeviceNo(preRepairPlanDetail.getAsset().getDeviceNo())!=null){
//			List PreRepairPlanDetailByDeviceNo=null;
//			PreRepairPlanDetailByDeviceNo=preRepairPlanDetailDao.getPreRepairPlanDetailByAndDeviceNo(preRepairPlanDetail.getAsset().getDeviceNo());
//			if(PreRepairPlanDetailByDeviceNo.size()>1){
//				throw new Exception();
//			}
//		}
		createPreRepairProcDetail(preRepairPlanDetail);
		preRepairPlanDetail.setProcExecPeople(preRepairPlanDetail.getExecPeople());                      //设置实际执行人默认为计划执行人
		preRepairPlanDetail.setProcEstimateFinishDate(preRepairPlanDetail.getPlanEstimateFinishDate());     //设置实际执行时间默认为计划执行时间
		this.preRepairPlanDetailDao.storePreRepairPlanDetail(preRepairPlanDetail);  //保存从日常检查单到维修明细之后 改变日常检查单状态
		
	
		//加上该预算编号关联的年度预算总维修费用
		if (null != preRepairPlanDetail.getBudgetNo()) {
			this.budgetDetailManager.addRepairFeeFromBudgetDetail(preRepairPlanDetail.getBudgetNo(),preRepairPlanDetail.getPlanAllFee());
		}
		
	}
	
	public void storePreRepairProcDetail(PreRepairPlanDetail preRepairProcDetail) {
		this.preRepairPlanDetailDao.storePreRepairPlanDetail(preRepairProcDetail);
		
		//加上该预算编号关联的年度预算已发生费用
		if (null != preRepairProcDetail.getBudgetNo()) {
			this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(preRepairProcDetail.getBudgetNo(),preRepairProcDetail.getProcAllFee());
		}
	}
	
	
	public void deletePreRepairPlanDetail(
			PreRepairPlanDetail preRepairPlanDetail){
		this.preRepairPlanDetailDao.deletePreRepairPlanDetail(preRepairPlanDetail);
	}

	public void deleteAllPreRepairPlamDetail(
			Collection<PreRepairPlanDetail> preRepairPlanDetails, PreRepairPlan plan) throws Exception{
		for (PreRepairPlanDetail preRepairPlanDetail : preRepairPlanDetails) {
			if(preRepairPlanDetail.getProcResult().equals(PreRepairDetailResult.FINISHED)) {
				throw new Exception();
			}
			if(preRepairPlanDetail.getCheck()!=null){
				Check che=preRepairPlanDetail.getCheck();
				checkManager.chooseUausalCheckStatusunEnrol(che);
			}
		}
		
		//删除选中的预防性维修计划
		this.preRepairPlanDetailDao.deleteAllPreRepairPlamDetail(preRepairPlanDetails);
        //清除预防性维修计划中以被删除的计划明细
		for (PreRepairPlanDetail detail : preRepairPlanDetails) {
			//减去该预算编号关联的年度预算已发生费用
			if (null != detail.getBudgetNo()) {
				this.budgetDetailManager.removeRepairFeeFromBudgetDetail(detail.getBudgetNo(),detail.getPlanAllFee());
			}
			plan.getPlanDetails().remove(detail);
		}
        //清除预防性维修实施中以被删除的计划明细
		for (PreRepairPlan proc : plan.getPreRepairProc()) {  
			for (PreRepairPlanDetail detail : preRepairPlanDetails) {
				proc.getProcDetails().remove(detail);
			}
		}
		this.preRepairPlanManager.resetPreRepairPlanOrProcFee(plan);            //重置预防性维修计划和其相关联的实施的总费用
		
	}
	
	public void storePreRepairPlanDetail(String allPreRepairPlanDetailDutyDepartment, 
			                             String allPreRepairPlanDetailExternalHelp,
			                             String allPreRepairPlanDetailDutyPeople, 
			                             String allPreRepairPlanDetailExecPeople, 
			                             String allPreRepairPlanDetailEstimateExecDate,
			                             String allPreRepairPlanDetailDutyRepairLevel,
			                             String allPreRepairProcExecResult,
			                             String allPreRepairPlanDetailId) {
		String [] dutyDepartment = null;
		String [] externalHelp = null;
		String [] dutyPeople = null;
		String [] execPeople =null;
		String [] estimateExecDate = null;
		String [] repairLevel = null;
		String [] procExecResult = null;
		String [] preRepairPlanDetailId = null;
		if (null != allPreRepairPlanDetailId) {
			preRepairPlanDetailId = allPreRepairPlanDetailId.split(",");    //预防性维修计划明细ID  
		}
		if (null != allPreRepairPlanDetailDutyDepartment) {
			dutyDepartment = allPreRepairPlanDetailDutyDepartment.split(",");   //计划明细中的责任单位
		}
		if (null != allPreRepairPlanDetailExternalHelp) {                 //计划明细中是否外协
			externalHelp = allPreRepairPlanDetailExternalHelp.split(",");
		}
		if (null != allPreRepairPlanDetailDutyPeople) {
			dutyPeople = allPreRepairPlanDetailDutyPeople.split(",");          //计划明细中的责任人
		}
		if (null != allPreRepairPlanDetailExecPeople) {
			execPeople = allPreRepairPlanDetailExecPeople.split(",");         //计划明细中的执行人
		}
		if (null != allPreRepairPlanDetailEstimateExecDate) {
			estimateExecDate = allPreRepairPlanDetailEstimateExecDate.split(",");   //计划明细中的预计执行时间
		}
		if (null != allPreRepairPlanDetailDutyRepairLevel) {                        //计划明细中的维修等级
			repairLevel = allPreRepairPlanDetailDutyRepairLevel.split(",");
		}
		if (null != allPreRepairProcExecResult) {
			procExecResult = allPreRepairProcExecResult.split(",");             //执行结果
		}
		updatePreRepairPlanDetail(dutyDepartment, externalHelp,
				dutyPeople, execPeople, estimateExecDate,
				repairLevel, procExecResult, preRepairPlanDetailId);
	}
	
	private void updatePreRepairPlanDetail( String [] dutyDepartment,String [] externalHelp,
			String [] dutyPeople, String [] execPeople, String [] estimateExecDate,
			String [] repairLevel, String [] procExecResult,String [] preRepairPlanDetailId) {
		int preRepairPlanDetailIdNum = 0;               //计算明细ID的循环数
		while((preRepairPlanDetailId!=null) && (preRepairPlanDetailIdNum<preRepairPlanDetailId.length)) {
			PreRepairPlanDetail preRepairPlanDetail = this.preRepairPlanDetailDao.loadPreRepairPlanDetail(Long.valueOf(preRepairPlanDetailId[preRepairPlanDetailIdNum]));
			if (null != dutyDepartment) {
				for (int i=0; i<dutyDepartment.length; i=i+2) {              //设置计划明细中的责任单位
					if (dutyDepartment[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])) {
						Department dept = this.departmentManager.loadDepartment(Long.valueOf(dutyDepartment[i+1]));
						preRepairPlanDetail.setDepartment(dept);
						preRepairPlanDetail.setDeptName(dept.getName());
					    break;
					} else {
						preRepairPlanDetail.setDepartment(null);
					}
				}
			} else {
				preRepairPlanDetail.setDepartment(null);
			}
			if (null != externalHelp) {
				for (int i=0; i<externalHelp.length; i=i+2) {              //设置计划明细中的是否外协
					if (externalHelp[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])) {
						if (externalHelp[i+1].equals("Y")) {               //"Y"表示是外协
							preRepairPlanDetail.setExternalHelpFlag(true);
						} else {
							preRepairPlanDetail.setExternalHelpFlag(false);
						}
					}
				}
			} 
//			if (null != dutyPeople) {     
//				for (int i=0; i<dutyPeople.length; i= i+2) {                 //设置计划明细中的责任人
//					if (dutyPeople[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])) {
//						preRepairPlanDetail.setDutyPeople(this.userManager.loadUser(Long.valueOf(dutyPeople[i+1])));
//						break;
//					} else {
//						preRepairPlanDetail.setDutyPeople(null);
//					}
//				}
//			} else {
//				preRepairPlanDetail.setDutyPeople(null);
//			}
			
			if(null != dutyPeople){                       //设置计划明细中的责任人
				for(int i=0;i<dutyPeople.length;i=i+2){
					if(dutyPeople[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])){
						preRepairPlanDetail.setDutypeople(dutyPeople[i+1]);
						break;
					}else{
						preRepairPlanDetail.setDutypeople(null);
					}
				}
			}else{
				preRepairPlanDetail.setDutypeople(null);
			}
			
			if (null != execPeople) {
				for (int i=0; i<execPeople.length; i=i+2) {                  //设置计划明细中的计划执行人
					if (execPeople[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])) {
						//preRepairPlanDetail.setExecPeople(this.userManager.loadUser(Long.valueOf(execPeople[i+1])));
						//preRepairPlanDetail.setProcExecPeople(preRepairPlanDetail.getExecPeople());  //初始化实际执行人为计划执行人
						preRepairPlanDetail.setPlanExecPeople(execPeople[i+1]);
						preRepairPlanDetail.setPracticeExecPeople(preRepairPlanDetail.getPlanExecPeople());  //初始化实际执行人为计划执行人
						break;
					} else {
						preRepairPlanDetail.setExecPeople(null);
						preRepairPlanDetail.setPracticeExecPeople(null);
					}
				}	
			} else {
				preRepairPlanDetail.setExecPeople(null);
				preRepairPlanDetail.setPracticeExecPeople(null);
			}
			if (null != estimateExecDate) {
				for (int i=0; i<estimateExecDate.length; i=i+2) {                //设置计划明细中的计划完成时间
					if (estimateExecDate[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])) {
						preRepairPlanDetail.setPlanEstimateFinishDate(DateUtil.toDate(estimateExecDate[i+1], "yyyy-MM-dd"));
						preRepairPlanDetail.setProcEstimateFinishDate(DateUtil.toDate(estimateExecDate[i+1], "yyyy-MM-dd"));    //初始化实际完成时间为计划完成时间
						break;
					} else {
						preRepairPlanDetail.setPlanEstimateFinishDate(null);
						preRepairPlanDetail.setProcEstimateFinishDate(null);
					}
				}
			} else {
				preRepairPlanDetail.setPlanEstimateFinishDate(null);
				preRepairPlanDetail.setProcEstimateFinishDate(null);
			}
			if (null != repairLevel) {
				for (int i=0; i<repairLevel.length; i=i+2) {                       //设置计划明细中的维修等级
					if (repairLevel[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])) {
						preRepairPlanDetail.setRepairLevel(this.codeValueManager.loadCodeValue(Long.valueOf(repairLevel[i+1])));	
						break;
					} else {
						preRepairPlanDetail.setRepairLevel(null);
					}
				}
			} else {
				preRepairPlanDetail.setRepairLevel(null);
			}
			if (null != procExecResult) {
				for (int i=0; i<procExecResult.length; i++) {     //设置明细中的执行结果
					if (procExecResult[i].equals(preRepairPlanDetailId[preRepairPlanDetailIdNum])) {
						if (procExecResult[i+1].equals(PreRepairDetailResult.UNFINISHED.toString())) {
							preRepairPlanDetail.setProcResult(PreRepairDetailResult.UNFINISHED);
						} else if (procExecResult[i+1].equals(PreRepairDetailResult.FINISHED.toString())) {
							preRepairPlanDetail.setProcResult(PreRepairDetailResult.FINISHED);
						} else if (procExecResult[i+1].equals(PreRepairDetailResult.SHIFT.toString())) {
							preRepairPlanDetail.setProcResult(PreRepairDetailResult.SHIFT);
						} else {
							preRepairPlanDetail.setProcResult(PreRepairDetailResult.CANCEL);
						}
					}
				}
			}
			this.preRepairPlanDetailDao.storePreRepairPlanDetail(preRepairPlanDetail);
			preRepairPlanDetailIdNum++;
		}
	}
	public void storePreRepairProcDetail(String allPreRepairProcDetailExecPeople,
			String allPreRepairProcDetailEstimateExecDate, 
			String allPreRepairProcExecResult,
			String allPreRepairProcDetailId) {
		String [] procExecPeople = null;
		String [] procExecDate = null;
		String [] procExecResult = null;
		String [] procDetailId = null;
		int preRepairPlanDetailIdNum = 0;               //计算明细ID的循环数
		PreRepairPlanDetail preRepairProcDetail = null;
		if (null != allPreRepairProcDetailExecPeople) {
			procExecPeople = allPreRepairProcDetailExecPeople.split(",");       //实际执行人
		}
		if (null != allPreRepairProcDetailEstimateExecDate) {
			procExecDate = allPreRepairProcDetailEstimateExecDate.split(",");   //实际执行时间
		}
		if (null != allPreRepairProcExecResult) {
			procExecResult = allPreRepairProcExecResult.split(",");             //执行结果
		}
		if (null != allPreRepairProcDetailId) {
			procDetailId = allPreRepairProcDetailId.split(",");                //明细ID
		}
		while ((procDetailId!=null) && (preRepairPlanDetailIdNum<procDetailId.length)) {
			preRepairProcDetail = this.preRepairPlanDetailDao.loadPreRepairPlanDetail(Long.valueOf(procDetailId[preRepairPlanDetailIdNum]));
			if (null != procExecPeople) {     //设置明细中的实际执行人
				for (int i=0; i<procExecPeople.length; i=i+2) {
					if (procExecPeople[i].equals(procDetailId[preRepairPlanDetailIdNum])) {
						//preRepairProcDetail.setProcExecPeople(this.userManager.loadUser(Long.valueOf(procExecPeople[i+1])));
						
						preRepairProcDetail.setPracticeExecPeople(procExecPeople[i+1]);
						break;
					} else {
						preRepairProcDetail.setPracticeExecPeople(null);
					}
				}
			}
			if (null != procExecDate) {
				for (int i=0; i<procExecDate.length; i=i+2) {      //设置明细中的实际完成时间
					if (procExecDate[i].equals(procDetailId[preRepairPlanDetailIdNum])) {
						preRepairProcDetail.setProcEstimateFinishDate(DateUtil.toDate(procExecDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						preRepairProcDetail.setProcEstimateFinishDate(null);
					}
				}
			}
			if (null != procExecResult) {
				for (int i=0; i<procExecResult.length; i++) {     //设置明细中的执行结果
					if (procExecResult[i].equals(procDetailId[preRepairPlanDetailIdNum])) {
						if (procExecResult[i+1].equals(PreRepairDetailResult.UNFINISHED.toString())) {
							preRepairProcDetail.setProcResult(PreRepairDetailResult.UNFINISHED);
						} else if (procExecResult[i+1].equals(PreRepairDetailResult.FINISHED.toString())) {
							preRepairProcDetail.setProcResult(PreRepairDetailResult.FINISHED);
						} else if (procExecResult[i+1].equals(PreRepairDetailResult.SHIFT.toString())) {
							preRepairProcDetail.setProcResult(PreRepairDetailResult.SHIFT);
						} else {
							preRepairProcDetail.setProcResult(PreRepairDetailResult.CANCEL);
						}
					}
				}
			}
			this.preRepairPlanDetailDao.storePreRepairPlanDetail(preRepairProcDetail);
			preRepairPlanDetailIdNum++;
		}
		//updatePreRepairProcResult(preRepairProcDetail.getProc());       //更新预防性维修实施中的执行结果和实际执行时间
	}
	
	public void resetPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
	//	preRepairPlanDetail.setProc(null);				
		preRepairPlanDetail.setProcResult(PreRepairDetailResult.UNFINISHED);
		//preRepairPlanDetail.setProc(null);
		preRepairPlanDetail.setProcExecPeople(null);
		this.preRepairPlanDetailDao.storePreRepairPlanDetail(preRepairPlanDetail);
	}
	
	public Date GetMinProcEstimateExecDateByPreRepairProcId(Long procId) {
		return this.preRepairPlanDetailDao.GetMinProcEstimateExecDateByPreRepairProcId(procId);
	}
	
	public void resetPreRepairPlanOrProcAllFee(PreRepairPlanDetail preRepairDetail,
			Double planAllFee, Double procAllFee) {
		if (null != planAllFee) {
			preRepairDetail.setPlanAllFee(planAllFee);
		}
		if (null != procAllFee) {
			preRepairDetail.setProcAllFee(procAllFee);
		}
		this.preRepairPlanDetailDao.storePreRepairPlanDetail(preRepairDetail);
	}

	public void storePreRepairPlanDetails(PreRepairPlan preRepairPlan, String preRepairPlanDetailHistoryIds) {
		String [] preRepairPlanDetailHistoryId = null;
		if (null != preRepairPlanDetailHistoryIds) {
			preRepairPlanDetailHistoryId = preRepairPlanDetailHistoryIds.split(",");
		}
		for (int i=0; i<preRepairPlanDetailHistoryId.length; i++) {
			//PreRepairPlanDetail detail = new PreRepairPlanDetail();        //新的计划明细
			//老的计划明细
			PreRepairPlanDetail detailHistory = this.preRepairPlanDetailDao.loadPreRepairPlanDetail(Long.valueOf(preRepairPlanDetailHistoryId[i]));
			PreRepairPlanDetail detail = null;
			if (null != detailHistory.getAsset()) {
				 detail = this.preRepairPlanDetailDao
				.getPreRepairPlanDetailByPlanIdAndDeviceId(preRepairPlan.getId(), detailHistory.getAsset().getId());
			}
			if(detail==null){
			   detail = new PreRepairPlanDetail();
			   detail.setPlan(preRepairPlan); 
			}
			copyPreRepairPlanDetail(detail, detailHistory);           //把老的计划明细内容拷贝到新的计划明细中
			detail.setProcResult(PreRepairDetailResult.UNFINISHED);   //设置新计划明细的执行结果为默认值[未完成]
			
			createPreRepairProcDetail(detail);                         //创建新计划明细所关联的维修实施
			this.preRepairPlanDetailDao.storePreRepairPlanDetail(detail);
			
			/**
			 * 更新新计划的总费用
			 */
			resetPreRepairPlanAllFeeCurrentPlanDetail(detail);
			/**
			 * 如果复制历史计划明细的执行状态为未完成,则置其执行状态为转移
			 */
			if (null != detailHistory.getProcResult()) {
				if (detailHistory.getProcResult().equals(PreRepairDetailResult.UNFINISHED)) {
					detailHistory.setProcResult(PreRepairDetailResult.SHIFT);
				}
			}

			this.preRepairPlanDetailDao.storePreRepairPlanDetail(detailHistory);
		}
		this.preRepairPlanManager.resetPreRepairPlanOrProcFee(preRepairPlan);  //重置预防性维修计划和其相关联的实施的总费用
		
	}
	
	/**
	 * 把老的计划明细内容拷贝到新的计划明细中
	 * @param newDetail 新的计划明细
	 * @param oldDetail 老的计划明细
	 */
	private void copyPreRepairPlanDetail(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
		copyOldPreRepairPlanDetailToNewPreRepairPlanDetail(newDetail, oldDetail); //拷贝老的维修计划明细内容到新的计划明细中
		this.repairDocManager.resetRepairDoc(newDetail, oldDetail);              //复制维修关联的文档，并与新的计划明细关联
		this.repairFeeManager.resetRepairFee(newDetail, oldDetail);              //复制维修关联的维修费用，并与新的计划明细关联
		this.repairItemManager.resetRepairItem(newDetail, oldDetail);            //复制维修关联的维修费用，并与新的计划明细关联
		this.repairManHourManager.resetRepairManHour(newDetail, oldDetail);      //复制维修关联的维修工时，并与新的计划明细关联
		this.repairSpareManager.resetRepairSpare(newDetail, oldDetail);          //复制维修关联的维修费用，并与新的计划明细关联
		this.repairToolManager.resetRepairTool(newDetail, oldDetail);            //复制维修关联的维修工具，并与新的计划明细关联  

	}
	
	//拷贝老的维修计划明细内容到新的计划明细中
	private void copyOldPreRepairPlanDetailToNewPreRepairPlanDetail(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
		newDetail.setAsset(oldDetail.getAsset());                                //拷贝资产[工装]|[设备]
		newDetail.setToolingName(oldDetail.getToolingName());                    //拷贝名称
		newDetail.setBudgetNo(oldDetail.getBudgetNo());                          //拷贝预算编号
		newDetail.setContent(oldDetail.getContent());                            //拷贝工作内容
		newDetail.setDepartment(oldDetail.getDepartment());                      //拷贝部门
		newDetail.setDutyPeople(oldDetail.getDutyPeople());                      //拷贝负责人[已经没用字段]
		newDetail.setExecPeople(oldDetail.getExecPeople());                      //拷贝计划执行人[已经没用字段]
		newDetail.setDutypeople(oldDetail.getDutypeople());                      //拷贝责任人(string类型)
		newDetail.setPlanExecPeople(oldDetail.getPlanExecPeople());              //拷贝计划执行人(string类型)
		newDetail.setExecSituation(oldDetail.getExecSituation());                //拷贝执行情况
		newDetail.setExternalHelpFlag(oldDetail.isExternalHelpFlag());           //拷贝是否外协
		newDetail.setPlanAllFee(oldDetail.getPlanAllFee());                      //拷贝计划总费用
		newDetail.setPlanEstimateFinishDate(oldDetail.getPlanEstimateFinishDate());    //拷贝计划执行日期
		newDetail.setRepairLevel(oldDetail.getRepairLevel());                   //拷贝维修级别
		//newDetail.setSourceType(oldDetail.getSourceType());                     //拷贝费用来源类型 
		newDetail.setFeeSource(oldDetail.getFeeSource());                         
		newDetail.setTargetDemand(oldDetail.getTargetDemand());                 //拷贝目标要求
		newDetail.setToolingDevFlag(oldDetail.getToolingDevFlag());             //拷贝资产类型[DEVICE]|[TOOLING]
		setProcDetailDefaultValue(newDetail);                                   //设置明细中实施字段的默认值
	}
	
	//设置明细中实施字段的默认值
	private void setProcDetailDefaultValue(PreRepairPlanDetail detail) {
		detail.setProcExecPeople(detail.getExecPeople());                             //设置默认的实际执行人[已经没用字段]
		detail.setPracticeExecPeople(detail.getPlanExecPeople());                     //设置默认的实际执行人(string类型)
		detail.setProcAllFee(detail.getPlanAllFee());                                 //设置默认的实际总费用
		detail.setProcEstimateFinishDate(detail.getPlanEstimateFinishDate());         //设置默认的实际完成时间
	}
	
	private void resetPreRepairPlanAllFeeCurrentPlanDetail(PreRepairPlanDetail detail) {
		PreRepairPlan plan = detail.getPlan();
		if (null != plan.getPlanAllFee()) {
			if (null != detail.getPlanAllFee()) {
				plan.setPlanAllFee(plan.getPlanAllFee().doubleValue() + detail.getPlanAllFee().doubleValue());
			}
		} else {
			if (null != detail.getPlanAllFee()) {
				plan.setPlanAllFee(detail.getPlanAllFee().doubleValue());
			}
		}
		this.preRepairPlanDao.storePreRepairPlan(plan);
	}
	
	/**
	 * 建立预防性维修实施与计划明细关联
	 * @param preRepairPlanDetail  计划明细
	 */
	private void createPreRepairProcDetail(PreRepairPlanDetail preRepairPlanDetail) {
		PreRepairPlan plan = preRepairPlanDetail.getPlan();
		for (PreRepairPlan proc : plan.getPreRepairProc()) {
			preRepairPlanDetail.setProc(proc);
		}
	}

	public void storeUausalCheck(Check check,PreRepairPlan preRepairPlan, String addusualCheckIds) {
		String [] addusualCheckId=null;
		if(addusualCheckIds!=null){
			addusualCheckId=addusualCheckIds.split(",");
		}
		 for(int i=0;i<addusualCheckId.length;i++){
			// PreRepairPlanDetail detail=new PreRepairPlanDetail();
			  //获得日常检查的对象 
			  Check uausCheck=checkManager.loadCheck(Long.valueOf(addusualCheckId[i]));
			  //detail.setCheck(uausCheck);
			  PreRepairPlanDetail detail = null;
			  if (null != uausCheck.getAsset()) {
				  detail = this.preRepairPlanDetailDao
					.getPreRepairPlanDetailByPlanIdAndDeviceId(preRepairPlan.getId(), uausCheck.getAsset().getId());
			  }
			  if(detail==null){
				  detail = new PreRepairPlanDetail();   
				  detail.setCheck(uausCheck);
			  }
			  copyUausalCheckDetailToPreRepairDetail(detail,uausCheck);// 日常检查复制到维护明细明细中
			  detail.setPlan(preRepairPlan);
			  createPreRepairProcDetail(detail);                       //设置预防性维修明细关联预防性维修实施
			  preRepairPlanDetailDao.storePreRepairPlanDetail(detail);
			  saveuausalCheckChooseChangeUausalCheckStatus(uausCheck);      
		  }
	}
	public void saveuausalCheckChooseChangeUausalCheckStatus(Check uausCheck){
		checkManager.chooseUausalCheckStatusEnrol(uausCheck);
	}
	public void  copyUausalCheckDetailToPreRepairDetail(PreRepairPlanDetail detail,Check uausCheck){
		if(uausCheck.getAsset()!=null){
			detail.setAsset(uausCheck.getAsset());
		}else{
			detail.setAsset(null);
			detail.setToolingName(uausCheck.getToolingName());
		}
		  
	}
     //保存从故障率选择
	public void storeBrockenRate(BrockenRate brocken, PreRepairPlan preRepairPlan, String addBrockenRateListIds) {
		
		String [] addBrockenRateListId=null;
		if(addBrockenRateListIds!=null){
			addBrockenRateListId=addBrockenRateListIds.split(",");
		}
		 for(int i=0;i<addBrockenRateListId.length;i++){
			 PreRepairPlanDetail detail=new PreRepairPlanDetail();
			  //获得故障率对象 
			  BrockenRate rate=brockenRateManager.loadBrockenRate(Long.valueOf(addBrockenRateListId[i]));
			  //detail.setBrockenRate(rate);
			  if(rate.getDeviceCard()!=null){
					  detail = this.preRepairPlanDetailDao
						.getPreRepairPlanDetailByPlanIdAndDeviceId(preRepairPlan.getId(), rate.getDeviceCard().getId());
				  }
				  if(detail==null){
					  detail = new PreRepairPlanDetail();   
					  detail.setBrockenRate(rate);
				 
			  }
			  copyBrockenRateDetailToPreRepairDetail(detail,rate);// 日常检查复制到维护明细明细中
			  detail.setPlan(preRepairPlan);
			  createPreRepairProcDetail(detail);                       //设置预防性维修明细关联预防性维修实施
			  preRepairPlanDetailDao.storePreRepairPlanDetail(detail);
			 // saveuausalCheckChooseChangeUausalCheckStatus(rate);      
		  }
	  }
	public void  copyBrockenRateDetailToPreRepairDetail(PreRepairPlanDetail detail,BrockenRate rate){
		if(rate.getDeviceCard()!=null){
			detail.setAsset(rate.getDeviceCard());
		}else{
			detail.setAsset(null);
		}
		
	}
	public void storePreRepairPlanDetailFromPreRepairRule(PreRepairPlan preRepairPlan, String addPreRepairRuleIds) {
		String [] preRepairRuleIds = null;
		if (null != addPreRepairRuleIds) {
			preRepairRuleIds = addPreRepairRuleIds.split(",");
		}
		for (int i=0; i<preRepairRuleIds.length; i++) {
			PreRepairRule rule = this.preRepairRuleManager.loadPreRepairRule(Long.valueOf(preRepairRuleIds[i]));
			//根据预防性维修计划id以及设备id获取预防性维修计划明细对象
			PreRepairPlanDetail detail = this.preRepairPlanDetailDao
					.getPreRepairPlanDetailByPlanIdAndDeviceId(preRepairPlan.getId(), rule.getAsset().getId());
			if (null == detail) {
		    	detail = new PreRepairPlanDetail();          //创建新的预防性维修明细
		    	detail.setPlan(preRepairPlan);               //设置预防性维修明细关联的计划
		    	//拷贝预维标准关联的设备信息到防性维修计划明细
		    	copyDeviceInfoToPreRepairPlanDetailFromPreRepairRule(rule, detail);
		    	preRepairPlan.getPlanDetails().add(detail);
		    }
		    //TODO 维修明细关联预标
		    RepairItem item = new RepairItem();
		    copyPreRepairRuleToRepairItem(rule, item);              //
		    item.setPreRepairDetail(detail);                        //设置维修明细关联的预防性维修明细
		    this.repairItemManager.storeRepairItem(item);
		    rule.setPreRepairTime(0.0);                           //清空预维标准累计运行台时
		    this.preRepairRuleManager.storePreRepairRule(rule);
		}
	}
	/**
	 * 拷贝预维标准关联的设备信息到防性维修计划明细
	 * @param rule 预维标准
	 * @param detail 防性维修计划明细
	 * @throws Exception 
	 */
	private void copyDeviceInfoToPreRepairPlanDetailFromPreRepairRule(PreRepairRule rule, PreRepairPlanDetail detail)  {
		DeviceCard device = rule.getAsset();            //获取预维标准关联的设备
		detail.setAsset(device);                        //设置预防性维修计划明细关联的设备
		detail.setDutyPeople(device.getManager());      //设置预防性维修计划明细责任人
		//detail.setPreRepairRule(rule);                  //设置预防性维修计划明细关联的预维标准
		try {
			this.storePreRepairPlanDetail(detail);
		} catch (Exception e) {
			
		}
		
		
	}
	/**
	 * 拷贝预维标准的信息到维修明细中
	 * @param rule 预维标准
	 * @param item 维修明细
	 */
	private void copyPreRepairRuleToRepairItem(PreRepairRule rule,RepairItem item) {
		item.setPosition(rule.getPosition());       //设置维修明细的部位
		item.setContent(rule.getContent());         //设置维修明细内容
		item.setPreRepairRule(rule);               //设置关联的预维标准
	}

	public void storeEasilyDamagedSpare(PreRepairPlan preRepairPlan, String addeasilydamagedSpartIds) {
		 
		String [] addeasilydamagedSpartId=null;
		if(addeasilydamagedSpartIds!=null){
		
			addeasilydamagedSpartId=addeasilydamagedSpartIds.split(",");
		}
		 for(int i=0;i<addeasilydamagedSpartId.length;i++){
			  DeviceSpare spar=toolingDevSpareManager.loadDeviceSpare(Long.valueOf(addeasilydamagedSpartId[i]));  //获得日常检查的对象 
			  PreRepairPlanDetail detail = this.preRepairPlanDetailDao.getSparePreRepairPlanDetailByPlanIdAndDeviceId(preRepairPlan.getId(), spar.getAsset().getId());
			
			  if(null==detail){
				  detail=new PreRepairPlanDetail();
				  detail.setDevieSpare(spar);
				  detail.setPlan(preRepairPlan);
				  copyEasilyDamageDetailToPreRepairDetail( detail,  spar);
				  preRepairPlanDetailDao.storePreRepairPlanDetail(detail);
				  preRepairPlan.getPlanDetails().add(detail);
				  //createPreRepairProcDetail(detail);    
			  }
			  RepairSpare rSpare =new RepairSpare();
			  copySparDeviceInfoToPreRepairPlanDetailFromPreRepairRule(spar,rSpare);// 日常检查复制到维护明细明细中 
			  rSpare.setPreRepairDetail(detail);
			  repairSpareManager.storeRepairSpare(rSpare);
			  spar.setUseTime(0.0);
			 this.toolingDevSpareManager.storeDeviceSpare(spar);
			 createPreRepairProcDetail(detail);    
			  
			// this.storePreRepairPlanDetail(detail);     //设置预防性维修明细关联预防性维修实施
				  //preRepairPlanDetailDao.storePreRepairPlanDetail(detail);
			 this.preRepairPlanDao.storePreRepairPlan(preRepairPlan);
		  }
	}
	public void copySparDeviceInfoToPreRepairPlanDetailFromPreRepairRule(DeviceSpare spare,RepairSpare rSpare){
		  Spare sp=spare.getSpare();
		  rSpare.setSpare(sp);
	}
	public void copyEasilyDamageDetailToPreRepairDetail(
			                    PreRepairPlanDetail detail, DeviceSpare spare) {

		if (spare.getAsset() != null) {
			detail.setAsset(spare.getAsset());
		} else {
			detail.setAsset(null);
		}
	}

}

	
//----------------------------------- 未用方法  ---------------------------------------------------	
	
//	private void updatePreRepairProcResult(PreRepairPlan preRepairProc) {
//	//int procDetailNum = preRepairProc.getProcDetails().size();
//	double count = 0;
//	for (PreRepairPlanDetail detail : preRepairProc.getProcDetails()) {
//		if (detail.getProcResult().equals(PreRepairDetailResult.FINISHED)) {        //统计完成的实施明细个数
//			count ++; 
//		} 
//	}
//	if (count == procDetailNum) {    //如果预防性维修明细都已全部执行，置维修实施的执行状态为"已完成"
//		preRepairProc.setProcResult(PreRepairResult.EXECUTED);
//		preRepairProc.setProcFinishStatus(String.valueOf(((int)(count/procDetailNum*100))) + '%');
//	} else if (count != 0){          //如果预防性维修明细都未全部执行，置维修实施的执行状态为"执行中"
//		preRepairProc.setProcResult(PreRepairResult.EXECUTEING);
//		preRepairProc.setProcFinishStatus(String.valueOf(((int)(count/procDetailNum*100))) + '%');
//	} else {                        //如果预防性维修明细都全部未执行，置维修实施的执行状态为"未执行"       
//		preRepairProc.setProcResult(PreRepairResult.NONEXECUTE);
//		preRepairProc.setProcFinishStatus(String.valueOf(((int)(count/procDetailNum*100))) + '%');
//	}
//	preRepairProc.setProcBeginDate(GetMinProcEstimateExecDateByPreRepairProcId(preRepairProc.getId()));      //设置预防性维修实施的实际开始时间，即明细中的最小时间
//	this.preRepairPlanDao.storePreRepairPlan(preRepairProc);
//}

