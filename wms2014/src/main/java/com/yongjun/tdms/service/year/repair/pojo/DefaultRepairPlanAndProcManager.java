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
package com.yongjun.tdms.service.year.repair.pojo;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDao;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDetailDao;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcViewDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcView;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;

/**
 * <p>Title: DefaultRepairPlanAndProcManager
 * <p>Description: 大项修计划和实施业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager
 */
public class DefaultRepairPlanAndProcManager implements RepairPlanAndProcManager{
	private final RepairPlanAndProcDao repairPlanAndProcDao;
	private final SequenceManager sequenceManager;
	private final RepairPlanAndProcDetailDao repairPlanAndProcDetailDao;
	private final RepairPlanAndProcViewDao repairPlanAndProcViewDao;
	
	public DefaultRepairPlanAndProcManager(
			RepairPlanAndProcDao repairPlanAndProcDao,
			SequenceManager sequenceManager,
			RepairPlanAndProcDetailDao repairPlanAndProcDetailDao,
			RepairPlanAndProcViewDao repairPlanAndProcViewDao) {
		this.repairPlanAndProcDao = repairPlanAndProcDao;
		this.sequenceManager = sequenceManager;
		this.repairPlanAndProcDetailDao = repairPlanAndProcDetailDao;
		this.repairPlanAndProcViewDao = repairPlanAndProcViewDao;
	}
	public List<RepairPlanAndProc> loadAllRepairPlanOrProcs(Long[] repairPlanOrProcIds) {
		return this.repairPlanAndProcDao.loadAllRepairPlanOrProcs(repairPlanOrProcIds);
	}

	public void deleteAllRepairPlanOrProcs(Collection<RepairPlanAndProc> repairPlanAndProcs) throws Exception {
		for (RepairPlanAndProc plan : repairPlanAndProcs) {               //如果该计划中的计划明细有被实施，则抛出异常
			for (RepairPlanAndProcDetail detail : plan.getPlanDetails()) {
				if (detail.getProcResult().equals(PreRepairDetailResult.FINISHED)) {
					throw new Exception();
				}
			}
		}
		//如果该计划中计划明细没有被实施，则删除其相关的实施记录，以及计划明细，详细
		for(RepairPlanAndProc plan : repairPlanAndProcs) {
			//删除计划明细
			this.repairPlanAndProcDetailDao.deleteAllRepairPlanAndProcDetails(plan.getPlanDetails());
			for (RepairPlanAndProc proc : plan.getRepairProc()){
				//删除相关联的实施
				this.repairPlanAndProcDao.deleteRepairPlanOrProc(proc);
			}
		}
		this.repairPlanAndProcDao.deleteAllRepairPlanOrProcs(repairPlanAndProcs);
	}
	
	public RepairPlanAndProc loadRepairPlanOrProc(Long repairPlanOrProcId) {
		return this.repairPlanAndProcDao.loadRepairPlanOrProc(repairPlanOrProcId);
	}
	
	public void storeRepairPlan(RepairPlanAndProc repairPlan) {
		if(repairPlan.isNew()){
			String planNo = (String)sequenceManager.generate("-");     //生成大项修计划编号
			repairPlan.setPlanNo(planNo);
			this.repairPlanAndProcDao.storeRepairPlanOrProc(repairPlan);
			copyPlanToProc(repairPlan);             //创建大项修计划相关联的大项修实施
		} else {
			updatePlanToProc(repairPlan);          //更新大项修计划相关联的大项修实施
			this.repairPlanAndProcDao.storeRepairPlanOrProc(repairPlan);
		}
	}
	
	//拷贝新的大项修计划到新的大项修实施中去
	private void copyPlanToProc(RepairPlanAndProc repairPlan) {
		RepairPlanAndProc repairProc = new RepairPlanAndProc();                  //创建大项修实施
		copyPlanContentToProcContent(repairPlan, repairProc);                    //拷贝大项修计划内容到大项修实施中
		repairProc.setPlanProcFlag(PreRepairModel.PROC);                         //设置实施标识
		this.repairPlanAndProcDao.storeRepairPlanOrProc(repairProc);
	}
	
     //	拷贝大项修计划内容到大项修实施中
	private void copyPlanContentToProcContent(RepairPlanAndProc repairPlan, RepairPlanAndProc repairProc) {
		repairProc.setPlanNo(repairPlan.getPlanNo());                         //拷贝大项修计划编号
		repairProc.setName(repairPlan.getName());                             //拷贝大项修名称
		repairProc.setYear(repairPlan.getYear());                             //拷贝年度
		repairProc.setDepartment(repairPlan.getDepartment());                 //拷贝部门
		repairProc.setPlanCreatedTime(repairPlan.getPlanCreatedTime());       //拷贝计划编制日期
		repairProc.setPlanCreator(repairPlan.getPlanCreator());               //拷贝计划编制人
		repairProc.setPlanAuditor(repairPlan.getPlanAuditor());               //拷贝计划审核人
		repairProc.setPlanAllFee(repairPlan.getPlanAllFee());                 //拷贝计划总费用
		repairProc.setToolingDevFlag(repairPlan.getToolingDevFlag());         //拷贝资产标识[设备]|[工装]
		repairProc.setRepairPlan(repairPlan);                                 //设置维修实施中关联的计划
	}
	
	//更新大项修计划内容到大项修实施内容中
	private void updatePlanToProc(RepairPlanAndProc repairPlan) {
		for (RepairPlanAndProc proc : repairPlan.getRepairProc()) {
			copyPlanContentToProcContent(repairPlan, proc);
			this.repairPlanAndProcDao.storeRepairPlanOrProc(proc);
		}
	}
	
	public void resetYearRepairPlanOrProcFee(RepairPlanAndProc plan) {
		double planDetailFeeCount = 0;                  //计算计划明细中所有的计划费用的总计
		for (RepairPlanAndProcDetail detail : plan.getPlanDetails()) {
			if (null != detail.getPlanDetailAllFee()) {
				planDetailFeeCount += detail.getPlanDetailAllFee().doubleValue();
			}
		}
		if (planDetailFeeCount > 0) {     //重新设置维修计划的计划总费用
			plan.setPlanAllFee(Double.valueOf(planDetailFeeCount));       
		} else {
			plan.setPlanAllFee(null); 
		}
		this.repairPlanAndProcDao.storeRepairPlanOrProc(plan);
		
		for (RepairPlanAndProc proc : plan.getRepairProc()) {
			double procDetailFeeCount = 0;
			for (RepairPlanAndProcDetail detail : proc.getProcDetails()) {
				if (null != detail.getProcDetailAllFee()) {
					procDetailFeeCount += detail.getProcDetailAllFee().doubleValue();
				}
			}
			if (procDetailFeeCount > 0) {   //重新设置实施的实际总费用
				proc.setProcAllFee(Double.valueOf(procDetailFeeCount));        
			} else {
				proc.setProcAllFee(null); 
			}
			
			proc.setPlanAllFee(plan.getPlanAllFee());                       //同步计划总费用
			this.repairPlanAndProcDao.storeRepairPlanOrProc(proc);
		}
	}
	public void cancelJob(RepairPlanAndProc repairPlanAndProc) {
//		repairPlanAndProc.setJob(null);
//		this.storeRepairPlanOrProc(repairPlanAndProc);
//		this.wfJobManager.cancelJob(repairPlanAndProc);
	}
	public void storeRepairProc(RepairPlanAndProc repairProc) {
		this.repairPlanAndProcDao.storeRepairPlanOrProc(repairProc);
	}
	
	public List<RepairPlanAndProcView> loadAllRepairPlanAndProcView(String[] array) throws HibernateException{
		return this.repairPlanAndProcViewDao.loadAllRepairPlanAndProcView(array);
	}
}
