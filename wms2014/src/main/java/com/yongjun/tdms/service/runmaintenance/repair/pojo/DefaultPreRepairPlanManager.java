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
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDetailDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanViewDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanView;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairResult;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairItemManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairManHourManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairSpareManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairToolManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * <p>Title: DefaultPreRepairPlanManager
 * <p>Description: 预防性维修计划业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: DefaultPreRepairPlanManager.java 11198 2008-03-05 09:17:12Z smzhu $
 * @see com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager
 */
public  class DefaultPreRepairPlanManager  implements PreRepairPlanManager{
	private final PreRepairPlanDao preRepairPlanDao;
	private final SequenceManager sequenceManager;
	private final RepairItemManager repairItemManager;
	private final RepairSpareManager repairSpareManager;
	private final RepairManHourManager repairManHourManager;
	private final RepairToolManager repairToolManager;
	private final PreRepairPlanDetailDao preRepairPlanDetailDao;
	private final WfJobManager wfJobManager;
	private final PreRepairPlanViewDao preRepairPlanViewDao;
	
	private BudgetDetailManager budgetDetailManager;
	
	public DefaultPreRepairPlanManager(
			PreRepairPlanDao preRepairPlanDao,
			SequenceManager sequenceManager,
			RepairItemManager repairItemManager,
			RepairSpareManager repairSpareManager,
			RepairManHourManager repairManHourManager,
			RepairToolManager repairToolManager,
			PreRepairPlanDetailDao preRepairPlanDetailDao,
			WfJobManager wfJobManager,
			PreRepairPlanViewDao preRepairPlanViewDao){
		this.preRepairPlanDao = preRepairPlanDao;
		this.sequenceManager = sequenceManager;
		this.repairItemManager = repairItemManager;
		this.repairSpareManager = repairSpareManager;
		this.repairManHourManager = repairManHourManager;
		this.repairToolManager = repairToolManager;
		this.preRepairPlanDetailDao = preRepairPlanDetailDao;
		this.wfJobManager = wfJobManager;
		this.preRepairPlanViewDao = preRepairPlanViewDao;
	}
	
	public List<PreRepairPlan> loadAllPreRepairPlans(Long[] preRepairPlanIds) {
		return this.preRepairPlanDao.loadAllPreRepairPlans(preRepairPlanIds);
	}
	
	public void storePreRepairPlan(PreRepairPlan preRepairPlan) {
		if(preRepairPlan.isNew()){
			String planNo = (String)sequenceManager.generate("-");
			preRepairPlan.setPlanNo(planNo);
			this.preRepairPlanDao.storePreRepairPlan(preRepairPlan);
			copyPlanToProc(preRepairPlan);
		} else {
			updatePlanToProc(preRepairPlan);
			this.preRepairPlanDao.storePreRepairPlan(preRepairPlan);
		}
		
	}
	public void deleteAllPreRepairPlan(Collection<PreRepairPlan> preRepairPlans) throws Exception{
		for(PreRepairPlan plan : preRepairPlans) {                //如果该计划中的计划明细有被实施的，则抛出异常
			for (PreRepairPlanDetail detail : plan.getPlanDetails()) {
				if (detail.getProcResult().equals(PreRepairDetailResult.FINISHED)) {
					throw new Exception();
				}
			}
		}
		//如果该计划没有被实施,则删除其相关的计划明细,详细,相关联的实施
		for(PreRepairPlan plan : preRepairPlans) {
			for (PreRepairPlanDetail detail : plan.getPlanDetails()) {
				//减去该预算编号关联的年度预算已发生费用
				if (null != detail.getBudgetNo()) {
					this.budgetDetailManager.removeRepairFeeFromBudgetDetail(detail.getBudgetNo(),detail.getPlanAllFee());
				}
			}
			//删除该计划明细
			this.preRepairPlanDetailDao.deleteAllPreRepairPlamDetail(plan.getPlanDetails());
			for (PreRepairPlan proc : plan.getPreRepairProc()){
				//删除相关的实施
				this.preRepairPlanDao.deletePreRepairPlan(proc);
			}
		}
		this.preRepairPlanDao.deleteAllPreRepairPlan(preRepairPlans);
	}
	public PreRepairPlan loadPreRepairPlan(Long preRepairPlanId) {
		return this.preRepairPlanDao.loadPreRepairPlan(preRepairPlanId);
	}
	
	public List<PreRepairPlan> Query(String[] queryInfo) throws HibernateException {
		return this.preRepairPlanDao.Query(queryInfo);
	}
	
	public void deletePreRepairPlan(PreRepairPlan preRepairPlan) {
		this.preRepairPlanDao.deletePreRepairPlan(preRepairPlan);
	}
	
	public void cancelJob(PreRepairPlan preRepairProc) {
		preRepairProc.setJob(null);
		this.storePreRepairPlan(preRepairProc);
		this.wfJobManager.cancelJob(preRepairProc);
	}
	

	
	/**
	 * 拷贝预防性维修计划到预防性维修实施记录中,并生成实施记录
	 * @param plan 预防性维修计划
	 */
	private void copyPlanToProc(PreRepairPlan plan) {
		PreRepairPlan proc = new PreRepairPlan();
		copyPlanContentToProc(plan, proc);
		proc.setPlanProcFlag(PreRepairModel.PROC);                 //设置实施标识 
		proc.setProcResult(PreRepairResult.NONEXECUTE);             //设置实施的默认执行结果[未执行]
 		this.preRepairPlanDao.storePreRepairPlan(proc);
	}
	/**
	 * 拷贝预防性维修计划内容到预防性维修实施中
	 * @param plan 预防性维修计划
	 * @param proc 预防性维修实施
	 */
	private void copyPlanContentToProc(PreRepairPlan plan, PreRepairPlan proc) {
		proc.setPlanNo(plan.getPlanNo());                          //拷贝计划编号
		proc.setName(plan.getName());                              //拷贝计划名称
		proc.setDepartment(plan.getDepartment());                  //拷贝部门
		proc.setBeginDate(plan.getBeginDate());                    //拷贝计划完成时间
		proc.setPlanCreator(plan.getPlanCreator());                //拷贝计划编制人
		proc.setPlanCreatedTime(plan.getPlanCreatedTime());        //拷贝计划编制日期
		proc.setPlanAuditor(plan.getPlanAuditor());                //拷贝计划审核人
		proc.setPlanAllFee(plan.getPlanAllFee());                  //拷贝计划总费用
		proc.setProcAllFee(plan.getPlanAllFee());                  //设置实际总费用
		proc.setToolingDevFlag(plan.getToolingDevFlag());          //拷贝资产的标识
		proc.setPreRepairPlan(plan);                               //设置实施关联的计划
	}
	
	/**
	 * 把预防性维修计划内容更新到预防性维修实施内容
	 * @param plan 预防性维修计划
	 */
	private void updatePlanToProc(PreRepairPlan plan) {
		for (PreRepairPlan proc : plan.getPreRepairProc()) {
			copyPlanContentToProc(plan, proc);
			this.preRepairPlanDao.storePreRepairPlan(proc);
		}
	}

	//-----------------------------  以下方法暂时未用  -----------------------------------------------------------
//	public void deleteAllPreRepairPlan(Collection<PreRepairPlan> preRepairPlans) throws Exception{
//		this.preRepairPlanDao.deleteAllPreRepairPlan(preRepairPlans);
//	}
	public PreRepairPlan storePreRepairProc(Long preRepairPlanId) {
		PreRepairPlan plan = this.preRepairPlanDao.loadPreRepairPlan(preRepairPlanId);
		PreRepairPlan proc = new PreRepairPlan();
		/**
		 * 拷贝计划到实施记录中
		 */
		proc.setPlanNo(plan.getPlanNo());
		proc.setName(plan.getName());
		proc.setDepartment(plan.getDepartment());
		proc.setBeginDate(plan.getBeginDate());
		proc.setPlanAuditor(plan.getPlanAuditor());
		proc.setPlanCreator(plan.getPlanCreator());
		proc.setToolingDevFlag(plan.getToolingDevFlag());
		proc.setPlanCreatedTime(plan.getPlanCreatedTime());
		/**
		 * 初始化计划明细中实施的字段值
		 */
		for (Iterator it =  plan.getPlanDetails().iterator(); it.hasNext();) {
			PreRepairPlanDetail procDetail = (PreRepairPlanDetail)it.next();
			procDetail.setProcExecPeople(procDetail.getExecPeople());                     //初始化明细实际执行人
			procDetail.setProcEstimateFinishDate(procDetail.getPlanEstimateFinishDate());         //初始化明细实际执行时间
			procDetail.setProcResult(PreRepairDetailResult.UNFINISHED);                          //初始化明细执行结果
			//proc.getProcDetails().add(procDetail); 
			//procDetail.setProc(proc);
		}
		proc.setProcResult(PreRepairResult.NONEXECUTE);                                 //初始化实施中的执行结果[未执行]                             
		//proc.setPlanProcFlag(PreRepairModel.PROC);                                      //标识为实施
		this.preRepairPlanDao.storePreRepairPlan(proc);
		this.resetPreRepairProcEstimateExecDate(proc);                                  //初始化实施实际开始时间
		return proc;
	}

	public void deleteAllPreRepairProc(Long[] preRepairProcIds) {
		for (int i=0; i<preRepairProcIds.length; i++) {
			PreRepairPlan proc = this.preRepairPlanDao.loadPreRepairPlan(preRepairProcIds[i]);
//			for (PreRepairPlanDetail procDetail : proc.getProcDetails()) {
//				/**
//				 * 重置已经设置了实施值得明细值
//				 */
//				preRepairPlanDetailManager.resetPreRepairPlanDetail(procDetail);
//				repairItemManager.resetRepairItem(procDetail);
//				repairSpareManager.resetRepairSpare(procDetail);
//				repairFeeManager.resetRepairFee(procDetail);
//				repairManHourManager.resetRepairManHour(procDetail);
//				repairToolManager.resetRepairTool(procDetail);
//			}
//			proc.setProcDetails(null);      //   删除实施记录与明细记录之间的关联关系
		}
		/**
		 * 根据传入的预防性维修实施ID集合值，删除集合中的预防性维修实施
		 */
		this.preRepairPlanDao.deleteAllPreRepairPlan(this.preRepairPlanDao.loadAllPreRepairPlans(preRepairProcIds));
	}
	
	public void resetPreRepairProcEstimateExecDate(PreRepairPlan preRepairProc) {
	//	preRepairProc.setProcBeginDate(this.preRepairPlanDetailManager.GetMinProcEstimateExecDateByPreRepairProcId(preRepairProc.getId()));
		this.preRepairPlanDao.storePreRepairPlan(preRepairProc);
	}

	public void storePreRepairProc(PreRepairPlan preRepairProc) {
		this.preRepairPlanDao.storePreRepairPlan(preRepairProc);
	}
	
	public void resetPreRepairPlanOrProcFee(PreRepairPlan plan) {
		double planDetailFeeCount = 0;                  //计算计划明细中所有的计划费用的总计
		for (PreRepairPlanDetail detail : plan.getPlanDetails()) {
			if (null != detail.getPlanAllFee()) {
				planDetailFeeCount += detail.getPlanAllFee().doubleValue();
			}
		}
		plan.setPlanAllFee(Double.valueOf(planDetailFeeCount));       //重新设置维修计划的计划总费用
		this.preRepairPlanDao.storePreRepairPlan(plan);
		
		for (PreRepairPlan proc : plan.getPreRepairProc()) {
			double procDetailFeeCount = 0;
			for (PreRepairPlanDetail detail : proc.getProcDetails()) {
				if (null != detail.getProcAllFee()) {
					procDetailFeeCount += detail.getProcAllFee().doubleValue();
				}
			}
			proc.setProcAllFee(Double.valueOf(procDetailFeeCount));         //重新设置实施的实际总费用
			proc.setPlanAllFee(plan.getPlanAllFee());                       //同步计划总费用
			this.preRepairPlanDao.storePreRepairPlan(proc);
		}
	}

	public List<PreRepairPlanView> loadAllPreRepairPlanView(String[] array) throws HibernateException {
		return this.preRepairPlanViewDao.loadAllPreRepairPlanView(array);
	}

	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}

	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}

}
