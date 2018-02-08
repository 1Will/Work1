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

import com.yongjun.tdms.dao.runmaintenance.fault.FaultRepairDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDetailDao;
import com.yongjun.tdms.dao.runmaintenance.repair.RepairFeeDao;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDao;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDetailDao;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairFee;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairFeeManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;

/**
 * <p>Title: DefaultRepairFeeManager
 * <p>Description: 预防性维修计划费用业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DefaultRepairFeeManager.java 11225 2008-03-09 05:57:04Z zbzhang $
 * @see com.yongjun.tdms.service.runmaintenance.repair.RepairFeeManager
 */
public class DefaultRepairFeeManager implements RepairFeeManager{
	private final RepairFeeDao repairFeeDao;
	//private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final PreRepairPlanDao preRepairPlanDao;
	private final PreRepairPlanDetailDao preRepairPlanDetailDao;
	private final RepairPlanAndProcDao repairPlanAndProcDao;
	private final RepairPlanAndProcDetailDao repairPlanAndProcDetailDao;
	private final FaultRepairDao faultRepairDao;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	private final PreRepairPlanManager preRepairPlanManager;
	
	private BudgetDetailManager budgetDetailManager;
	
	public DefaultRepairFeeManager(RepairFeeDao repairFeeDao,
			PreRepairPlanDao preRepairPlanDao,
			PreRepairPlanDetailDao preRepairPlanDetailDao,
			RepairPlanAndProcDao repairPlanAndProcDao,
			RepairPlanAndProcDetailDao repairPlanAndProcDetailDao,
			FaultRepairDao faultRepairDao,
			RepairPlanAndProcManager repairPlanAndProcManager,
			PreRepairPlanManager preRepairPlanManager) {
		this.repairFeeDao = repairFeeDao;
		this.preRepairPlanDao = preRepairPlanDao;
		this.preRepairPlanDetailDao = preRepairPlanDetailDao;
		this.repairPlanAndProcDao = repairPlanAndProcDao;
		this.repairPlanAndProcDetailDao = repairPlanAndProcDetailDao;
		this.faultRepairDao = faultRepairDao;
		this.repairPlanAndProcManager = repairPlanAndProcManager;
		this.preRepairPlanManager = preRepairPlanManager;
	}
	public RepairFee loadRepairFee(Long repairFeeId) {
		return this.repairFeeDao.loadRepairFee(repairFeeId);
	}

	public void storeRepairFee(RepairFee repairFee) {
		this.repairFeeDao.storeRepairFee(repairFee);
	}

	public void deleteAllRepairItem(Collection<RepairFee> RepairFees) {
		this.repairFeeDao.deleteAllRepairItem(RepairFees);
	}

	public List<RepairFee> loadAllRepairFees(Long[] feeIds) {
		return this.repairFeeDao.loadAllRepairFees(feeIds);
	}

	public void resetRepairFee(PreRepairPlanDetail detail) {
		for (RepairFee item : detail.getRepairFees()) {
			item.setProcFee(null);
			this.storeRepairFee(item);
		}
	}
	//------------------------------预防性维修计划维修费用的操作Begin---------------------------
	public void deleteAllRepairFee(Collection<RepairFee> repairFees, PreRepairPlanDetail planDetail) {
		double delPlanRepairFees = 0;
		double delProcRepairFees = 0;
		double planAllFee = 0;
		double procAllFee = 0;
		
		//移除该预算编号关联的年度预算总维修费用
		if (null != planDetail.getBudgetNo()) {
			this.budgetDetailManager.removeRepairFeeFromBudgetDetail(planDetail.getBudgetNo(), planDetail.getPlanAllFee());
			//如果实际维修费用不为空，则把该年度计划的已发生费用减去实际维修费用
			if (null != planDetail.getProcAllFee() && planDetail.getProcAllFee() > 0) {
				this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(planDetail.getBudgetNo(), planDetail.getProcAllFee());
			}
		}
		
		/**
		 * 累计要删除的维修费用的价格
		 */
		for (Iterator it = repairFees.iterator(); it.hasNext();) {
			RepairFee repairFee = (RepairFee)it.next();
			if (null != repairFee.getPlanFee()) {
				//累计要删除计划维修费用
				delPlanRepairFees += repairFee.getPlanFee().doubleValue();
			}
			if (null != repairFee.getProcFee()) {
				//累计要删除实际维修费用
				delProcRepairFees += repairFee.getProcFee().doubleValue();
			}
		}
		//更新计划的总费用
		if (null != planDetail.getPlanAllFee()) {
			planAllFee = planDetail.getPlanAllFee().doubleValue();
			planDetail.setPlanAllFee(Double.valueOf((planAllFee-delPlanRepairFees)));     //更新计划明细总费用
		}
	    //更新实际明细总费用
		if (null != planDetail.getProcAllFee()) {
			procAllFee = planDetail.getProcAllFee().doubleValue();
			planDetail.setProcAllFee(Double.valueOf(procAllFee-delProcRepairFees));      //更新实际明细总费用
		}
		this.preRepairPlanDetailDao.storePreRepairPlanDetail(planDetail);
		
		PreRepairPlan preRepairPlan = planDetail.getPlan();
		if (null != preRepairPlan.getPlanAllFee()) {
			preRepairPlan.setPlanAllFee(preRepairPlan.getPlanAllFee()-delPlanRepairFees);  //更新计划总费用
		}
		this.preRepairPlanDao.storePreRepairPlan(preRepairPlan);
		 //移除计划明细中相关的维修费用
		for (RepairFee fee : repairFees) {         
			planDetail.getRepairFees().remove(fee);
		}
		this.repairFeeDao.deleteAllRepairItem(repairFees);
		//this.preRepairPlanDetailDao.storePreRepairPlanDetail(planDetail);
		
		calculateProcDetailFeeByRepairProcFee(planDetail);                          //计算实际维修中的实际总费用
		
		//加上该预算编号关联的年度预算总维修费用
		if (null != planDetail.getBudgetNo()) {
			this.budgetDetailManager.addRepairFeeFromBudgetDetail(planDetail.getBudgetNo(),planDetail.getPlanAllFee());
			//如果实际维修费用不为空，则把该年度计划的已发生费用加上实际维修费用
			if (null != planDetail.getProcAllFee() && planDetail.getProcAllFee() > 0) {
				this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(planDetail.getBudgetNo(), planDetail.getProcAllFee());
			}
		}
	}
	
	public void storeRepairFee(PreRepairPlan preRepairPlan,RepairFee repairFee, String planAllFee, String oldPlanFee) {
		double newRepairFee = 0;				//从repairFee中获取的修改后该维修详细的费用值
		if(repairFee.getPlanFee()!=null){
			newRepairFee = repairFee.getPlanFee().doubleValue();
		}
		if (repairFee.isNew()) {
			if(planAllFee!=null){				//在preRepairPlan第一次新建时planAllFee为空
				preRepairPlan.setPlanAllFee(Double.parseDouble(planAllFee)+newRepairFee);
			}else {
				preRepairPlan.setPlanAllFee(newRepairFee);
			}
			this.preRepairPlanDao.storePreRepairPlan(preRepairPlan);
		}else {
			if (null != oldPlanFee){
				preRepairPlan.setPlanAllFee(Double.parseDouble(planAllFee)-Double.parseDouble(oldPlanFee)+newRepairFee);
			} else {
				preRepairPlan.setPlanAllFee(Double.parseDouble(planAllFee)-Double.parseDouble(oldPlanFee)+newRepairFee);
			}
			this.preRepairPlanDao.storePreRepairPlan(preRepairPlan);
		}
	}
	
    public void storeRepairFee(PreRepairPlanDetail planDetail, RepairFee repairFee, String oldPlanFee) {
		double newRepairFee = 0;				//从repairFee中获取的修改后该维修详细的费用值
		if(repairFee.getPlanFee()!=null){
			newRepairFee = repairFee.getPlanFee().doubleValue();               //获得修改后的值
		}
		
		//移除该预算编号关联的年度预算总维修费用
		if (null != planDetail.getBudgetNo()) {
			this.budgetDetailManager.removeRepairFeeFromBudgetDetail(planDetail.getBudgetNo(), planDetail.getPlanAllFee());
			//如果实际维修费用存在，则从该预算的已发生费用中移除实际维修费用
			if (null != planDetail.getProcAllFee()) {
				this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(planDetail.getBudgetNo(),planDetail.getProcAllFee());
			}
		}
		
		PreRepairPlan plan = planDetail.getPlan();
		if (repairFee.isNew()) {       //新建维修费用
			/**
			 * 更新计划总费用
			 */
			if (null != plan.getPlanAllFee()) {
				plan.setPlanAllFee(plan.getPlanAllFee().doubleValue() + newRepairFee);
			} 
			/**
			 * 更新计划明细总费用
			 */
			if (null != planDetail.getPlanAllFee()) {
				planDetail.setPlanAllFee(planDetail.getPlanAllFee().doubleValue() + newRepairFee);
			} 
		} else {                      //更新维修费用
			if (null != oldPlanFee) {
				/**
				 * 更新计划总费用
				 */
				if (null != plan.getPlanAllFee()) {
					plan.setPlanAllFee(plan.getPlanAllFee().doubleValue() - Double.parseDouble(oldPlanFee)  + newRepairFee);
				}
				/**
				 * 更新计划明细总费用
				 */
				if (null != planDetail.getPlanAllFee()) {
					planDetail.setPlanAllFee(planDetail.getPlanAllFee().doubleValue() - Double.parseDouble(oldPlanFee) + newRepairFee);
				} 
			} else {
				/**
				 * 更新计划总费用
				 */
				if (null != plan.getPlanAllFee()) {
					plan.setPlanAllFee(plan.getPlanAllFee().doubleValue() + newRepairFee);
				}
				/**
				 * 更新计划明细总费用
				 */
				if (null != planDetail.getPlanAllFee()) {
					planDetail.setPlanAllFee(planDetail.getPlanAllFee().doubleValue() + newRepairFee);
				}
			}
		}
		if (null == plan.getPlanAllFee()) {                   //如果计划总费用为空,则直接赋值
			plan.setPlanAllFee(newRepairFee);
		}
		if (null == planDetail.getPlanAllFee()) {             //如过计划明细总费用为空,则直接赋值
			planDetail.setPlanAllFee(newRepairFee);
		}
		//this.preRepairPlanManager.storePreRepairPlan(plan);
		this.preRepairPlanDao.storePreRepairPlan(plan);
		this.preRepairPlanDetailDao.storePreRepairPlanDetail(planDetail);
		//加上该预算编号关联的年度预算总维修费用
		if (null != planDetail.getBudgetNo()) {
			this.budgetDetailManager.addRepairFeeFromBudgetDetail(planDetail.getBudgetNo(),planDetail.getPlanAllFee());
			//如果实际维修费用存在，则从该预算的已发生费用中加上实际维修费用
			if (null != planDetail.getProcAllFee()) {
				this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(planDetail.getBudgetNo(),planDetail.getProcAllFee());
			}
		}
		this.repairFeeDao.storeRepairFee(repairFee);
		setProcAllDefaultFeeValue(repairFee);                 //设置维修费用的实际费用,以及相关的计划明细和计划中的实际总费用
    }
    
    //设置维修费用的实际费用,以及相关的预防性相关的计划明细和计划中的实际总费用
    private void setProcAllDefaultFeeValue(RepairFee repairFee) {
    	if (null == repairFee.getProcFee()) {                        //如果实际费用为空,则把计划维修自费用设置成它的默认值
    		repairFee.setProcFee(repairFee.getPlanFee());            //设置实际费用的默认值
    		PreRepairPlanDetail detail = repairFee.getPreRepairDetail();         //获取该维修费用关联的计划明细
    		PreRepairPlan proc = detail.getProc();                               //获取计划明细关联的计划
    		double planDetailRepairFee = 0;                                         //用来累计计划明细中维修费用的所有值
    		for (RepairFee procFee : detail.getRepairFees()) {
    			if (null != procFee.getProcFee()) {
    				planDetailRepairFee += procFee.getProcFee();	
    			}
    		}
    		if (0 != planDetailRepairFee) {                          //如果累计费用不为0,则更新所关联的计划明细以及计划中的实际总费用
        		if (null != detail.getProcAllFee() && null != proc.getProcAllFee()) {
        			//从计划实际总费用中减去计划明细中的老的实际总费用
        			proc.setProcAllFee(proc.getProcAllFee().doubleValue()-detail.getProcAllFee().doubleValue());
        		}
        		//设置计划明细中实际总费用新值
        		detail.setProcAllFee(Double.valueOf(planDetailRepairFee));
        		if (null !=  proc.getProcAllFee()) {              //如果计划中实际总费用不为空,加上计划明中新的实际总费用值
        			proc.setProcAllFee(proc.getProcAllFee().doubleValue() + planDetailRepairFee);
        		} else {                                         //如果为空,则把计划明细中的新的实际总费用值赋与它
        			proc.setProcAllFee(detail.getProcAllFee());
        		}
    		}
    		proc.setPlanAllFee(proc.getPreRepairPlan().getPlanAllFee());                  //设置维修实施中计划总费用的值
    		this.preRepairPlanDao.storePreRepairPlan(proc);
    		this.preRepairPlanDetailDao.storePreRepairPlanDetail(detail);

    	}
    }
	public void deletePlanProcAllFee(Collection<RepairFee> repairFees, PreRepairPlan preRepairPlan) {
		double delRepairFees = 0;
		for (Iterator it = repairFees.iterator(); it.hasNext();) {
			RepairFee repairFee = (RepairFee)it.next();
			if (null != repairFee.getPlanFee()) {
				delRepairFees += repairFee.getPlanFee().doubleValue();
			}
		}
		preRepairPlan.setPlanAllFee(preRepairPlan.getPlanAllFee()-delRepairFees);
		this.preRepairPlanDao.storePreRepairPlan(preRepairPlan);
	}
	
	public void storeRepairFee(Long preRepairPlanId,Long preRepairPlanDetailId,
			String allRepairFeeId, String allRepairFeeProcFee) {
		String [] repairFeeId = null;
		String [] procFee = null;
		double repairProcDetailAllFee =0;
		int repairFeeNum = 0;
		if (null != allRepairFeeId) {
			repairFeeId = allRepairFeeId.split(",");
		}
		if (null != allRepairFeeProcFee) {
			procFee = allRepairFeeProcFee.split(",");
		}
		while (null != repairFeeId && repairFeeNum<repairFeeId.length) {
			RepairFee repairFee = this.repairFeeDao.loadRepairFee(Long.valueOf(repairFeeId[repairFeeNum]));
			if (null != procFee) {
				for (int i=0; i<procFee.length; i=i+2) {
					if (procFee[i].equals(repairFeeId[repairFeeNum]))  {
						repairFee.setProcFee(Double.valueOf(procFee[i+1]));       //设置预防性维修的实际维修费用
						repairProcDetailAllFee += Double.valueOf(procFee[i+1]);
						break;
					} else {
						repairFee.setProcFee(null);
					}
				}
			} else {
				repairFee.setProcFee(null);
			}
			repairFeeNum++;
			this.repairFeeDao.storeRepairFee(repairFee);
		}
		PreRepairPlanDetail procDetail = this.preRepairPlanDetailDao.loadPreRepairPlanDetail(preRepairPlanDetailId);
		//如果实际维修费用存在，则从该预算的已发生费用中移除实际维修费用
		if (null != procDetail.getProcAllFee()) {
			this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(procDetail.getBudgetNo(),procDetail.getProcAllFee());
		}
		calculateProcDetailFeeByRepairProcFee(procDetail);             //根据实施明细，计算与他关联的实际维修费用的总计
		
		//如果实际维修费用存在，则从该预算的已发生费用中加上实际维修费用
		if (null != procDetail.getProcAllFee()) {
			this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(procDetail.getBudgetNo(),procDetail.getProcAllFee());
		}
	}

	//根据实施明细，计算与他关联的实际维修费用的总计
	private void calculateProcDetailFeeByRepairProcFee(PreRepairPlanDetail procDetail) {
		double countFee = 0;            //用来累计实施的维修费用
		for (RepairFee procFee : procDetail.getRepairFees()) {
			if (null != procFee.getProcFee()) {
				countFee += procFee.getProcFee().doubleValue();
			}
		}
//		PreRepairPlan proc = procDetail.getProc();
//		if ((null != proc.getProcAllFee()) && (null != procDetail.getProcAllFee())) {
//			//从维修实施中实际总费用减去相应维修明细的实际总费用
//			proc.setProcAllFee(proc.getProcAllFee().doubleValue() - oldProcDetailFee);
//		}
//		if (countFee > 0 ) {
//			if ((null != proc.getProcAllFee()) && (proc.getProcAllFee().doubleValue() > 0)) {
//				//如果实施中的实际总费用不为空且不为负数时，加上现在的维修明细中的实际总费用
//				proc.setProcAllFee(proc.getProcAllFee().doubleValue() + countFee);
//			} else {
//				//如果实施中的实际总费用为空或为负数，则直接赋予现在维修明细中的实际费用
//				proc.setProcAllFee(Double.valueOf(countFee));
//			}
//			procDetail.setProcAllFee(countFee);            //设置维修明细中实际总费用新的费用值
//		}
		if (countFee>0) {
			procDetail.setProcAllFee(Double.valueOf(countFee));
		} else {
			procDetail.setProcAllFee(null);
		}
		
		this.preRepairPlanDetailDao.storePreRepairPlanDetail(procDetail);
		calculateProcFeeByRepairProcDetailFee(procDetail.getProc());             //根据实施明细计算实施总费用
	}
	
//	根据实施明细计算实施总费用
	private void calculateProcFeeByRepairProcDetailFee(PreRepairPlan proc) {
		int countFee = 0;
		for (PreRepairPlanDetail detail : proc.getProcDetails()) {
			if (null != detail.getProcAllFee()) {
				countFee += detail.getProcAllFee().doubleValue();
			}
		}
		if (countFee>0) {
			proc.setProcAllFee(Double.valueOf(countFee));                       //设置维修实际总费用新的费用值
		} else {
			proc.setProcAllFee(null);
		}
		
		proc.setPlanAllFee(proc.getPreRepairPlan().getPlanAllFee());       //同步实施维修中的计划总费用
		this.preRepairPlanDao.storePreRepairPlan(proc);
	}
	
	//根据老的计划明细的维修费用创建新的计划明细的维修费用
	public void resetRepairFee(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
		for (RepairFee oldRepairFee : oldDetail.getRepairFees()) {
			RepairFee newRepairFee = new RepairFee();                 //新的维修费用
			newRepairFee.setPreRepairDetail(newDetail);               //设置新维修费用关联的维修计划明细
			copyRepairFee(newRepairFee, oldRepairFee);                //拷贝老的维修费用到新的维修费用
			this.repairFeeDao.storeRepairFee(newRepairFee);
			newDetail.getRepairFees().add(newRepairFee);
		}
	}
	
	//拷贝老的维修费用到新的维修费用
	private void copyRepairFee(RepairFee newRepairFee, RepairFee oldRepairFee) {
		newRepairFee.setFeeItem(oldRepairFee.getFeeItem());              //拷贝费用名称
		newRepairFee.setPlanFee(oldRepairFee.getPlanFee());              //拷贝计划费用
		newRepairFee.setComment(oldRepairFee.getComment());              //拷贝备注
		setRepairFeeProcDetailValue(newRepairFee);                      //设置维修费用的实际默认值
	}
	
	//设置维修费用的实际默认值
	private void setRepairFeeProcDetailValue(RepairFee repairFee) {
		repairFee.setProcFee(repairFee.getProcFee());                 //设置实际费用
	}
	//------------------------------预防性维修计划维修费用的操作End---------------------------
	
	//------------------------------------- 大项修计划维修费用的操作Begin ------------------------------------------
	//大项修计划费用
	public void storeRepairFee(RepairPlanAndProcDetail planDetail, RepairFee repairFee, String oldPlanFee) {
		double newRepairFee = 0;				//从repairFee中获取的修改后该维修详细的费用值
		if(repairFee.getPlanFee()!=null){
			newRepairFee = repairFee.getPlanFee().doubleValue();
		}
		RepairPlanAndProc plan = planDetail.getPlan();
		if (repairFee.isNew()) {       //新建维修费用
			/**
			 * 更新计划总费用
			 */
			if (null != plan.getPlanAllFee()) {
				plan.setPlanAllFee(plan.getPlanAllFee().doubleValue() + newRepairFee);
			} 
			/**
			 * 更新计划明细总费用
			 */
			if (null != planDetail.getPlanDetailAllFee()) {
				planDetail.setPlanDetailAllFee(planDetail.getPlanDetailAllFee().doubleValue() + newRepairFee);
			} 
		} else {                      //更新维修费用
			if (null != oldPlanFee) {
				/**
				 * 更新计划总费用
				 */
				if (null != plan.getPlanAllFee()) {
					plan.setPlanAllFee(plan.getPlanAllFee().doubleValue() - Double.parseDouble(oldPlanFee)  + newRepairFee);
				}
				/**
				 * 更新计划明细总费用
				 */
				if (null != planDetail.getPlanDetailAllFee()) {
					planDetail.setPlanDetailAllFee(planDetail.getPlanDetailAllFee().doubleValue() - Double.parseDouble(oldPlanFee) + newRepairFee);
				} 
			} else {
				/**
				 * 更新计划总费用
				 */
				if (null != plan.getPlanAllFee()) {
					plan.setPlanAllFee(plan.getPlanAllFee().doubleValue() + newRepairFee);
				}
				/**
				 * 更新计划明细总费用
				 */
				if (null != planDetail.getPlanDetailAllFee()) {
					planDetail.setPlanDetailAllFee(planDetail.getPlanDetailAllFee().doubleValue() + newRepairFee);
				}
			}
		}
		if (null == plan.getPlanAllFee()) {
			plan.setPlanAllFee(newRepairFee);
		}
		if (null == planDetail.getPlanDetailAllFee()) {
			planDetail.setPlanDetailAllFee(newRepairFee);
		}
		//this.repairPlanAndProcDao.storeRepairPlanOrProc(plan);
		this.repairPlanAndProcManager.storeRepairPlan(plan);
		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(planDetail);
		this.repairFeeDao.storeRepairFee(repairFee);
		setYearRepairProcAllDefaultFeeValue(repairFee);                  //设置维修费用的实际费用,以及相关的大项修相关的计划明细和计划中的实际总费用
	}
	
    //设置维修费用的实际费用,以及相关的大项修相关的计划明细和计划中的实际总费用
    private void setYearRepairProcAllDefaultFeeValue(RepairFee repairFee) {
    	if (null == repairFee.getProcFee()) {                        //如果实际费用为空,则把计划维修自费用设置成它的默认值
    		repairFee.setProcFee(repairFee.getPlanFee());            //设置实际费用的默认值
    		RepairPlanAndProcDetail detail = repairFee.getRepairPlanAndProcDetail();       //获取该维修费用关联的计划明细
    		RepairPlanAndProc proc = detail.getProc();                //获取计划明细关联的计划
    		double planDetailRepairFee = 0;                           //用来累计计划明细中维修费用的所有值
    		for (RepairFee procFee : detail.getRepairFees()) {        //累计维修费用                     
    			if (null != procFee.getProcFee()) {
    				planDetailRepairFee += procFee.getProcFee();	
    			}
    		}
    		if (0 != planDetailRepairFee) {                          //如果累计费用不为0,则更新所关联的计划明细以及计划中的实际总费用
        		if (null != detail.getProcDetailAllFee() && null != proc.getProcAllFee()) {
        			//从计划实际总费用中减去计划明细中的老的实际总费用
        			proc.setProcAllFee(proc.getProcAllFee().doubleValue()-detail.getProcDetailAllFee().doubleValue());
        		}
        		//设置计划明细中实际总费用新值
        		detail.setProcDetailAllFee(Double.valueOf(planDetailRepairFee));
        		if (null !=  proc.getProcAllFee()) {              //如果计划中实际总费用不为空,加上计划明中新的实际总费用值
        			proc.setProcAllFee(proc.getProcAllFee().doubleValue() + planDetailRepairFee);
        		} else {                                         //如果为空,则把计划明细中的新的实际总费用值赋与它
        			proc.setProcAllFee(detail.getProcDetailAllFee());
        		}
        		
    		}
    		proc.setPlanAllFee(proc.getRepairPlan().getPlanAllFee());                  //设置维修实施中计划总费用的值
    		this.repairPlanAndProcDao.storeRepairPlanOrProc(proc);                     //保存大项修实施
    		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(detail);      //保存大项修实施明细

    	}
    }
    
//	public void deleteAllRepairFee1(Collection<RepairFee> repairFees, RepairPlanAndProcDetail planDetail) {
//		double delRepairFees = 0;
//		double planAllFee = 0;
//		/**
//		 * 累计要删除的维修费用的价格
//		 */
//		for (Iterator it = repairFees.iterator(); it.hasNext();) {
//			RepairFee repairFee = (RepairFee)it.next();
//			if (null != repairFee.getPlanFee()) {
//				delRepairFees += repairFee.getPlanFee().doubleValue();
//			}
//		}
//		/**
//		 * 取得计划的总费用
//		 */
//		if (null != planDetail.getPlanDetailAllFee()) {
//			planAllFee = planDetail.getPlanDetailAllFee().doubleValue();
//		}
//		planDetail.setPlanDetailAllFee(Double.valueOf((planAllFee-delRepairFees)));     //更新计划明细总费用
//		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(planDetail);
//		RepairPlanAndProc repairPlanAndProc = planDetail.getPlan();
//		repairPlanAndProc.setPlanAllFee(repairPlanAndProc.getPlanAllFee()-delRepairFees);  //更新计划总费用
//		this.repairPlanAndProcDao.storeRepairPlanOrProc(repairPlanAndProc);
//		this.repairFeeDao.deleteAllRepairItem(repairFees);		
//	}
	public void deleteAllRepairFee(Collection<RepairFee> repairFees, RepairPlanAndProcDetail planDetail) {
		double delPlanRepairFees = 0;
		double delProcRepairFees = 0;
		double planAllFee = 0;
		double procAllFee = 0;
		
		/**
		 * 累计要删除的维修费用的价格
		 */
		for (Iterator it = repairFees.iterator(); it.hasNext();) {
			RepairFee repairFee = (RepairFee)it.next();
			if (null != repairFee.getPlanFee()) {
				//累计要删除计划维修费用
				delPlanRepairFees += repairFee.getPlanFee().doubleValue();
			}
			if (null != repairFee.getProcFee()) {
				//累计要删除实际维修费用
				delProcRepairFees += repairFee.getProcFee().doubleValue();
			}
		}
		//更新计划的总费用
		if (null != planDetail.getPlanDetailAllFee()) {
			planAllFee = planDetail.getPlanDetailAllFee().doubleValue();
			planDetail.setPlanDetailAllFee(Double.valueOf((planAllFee-delPlanRepairFees)));     //更新计划明细总费用
		}
		// TODO 多余语句
	    //更新实际明细总费用
		if (null != planDetail.getProcDetailAllFee()) {
			procAllFee = planDetail.getProcDetailAllFee().doubleValue();
			planDetail.setProcDetailAllFee(Double.valueOf(procAllFee-delProcRepairFees));      //更新实际明细总费用
		}
		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(planDetail);              //保存更新费用的计划和实施明细
		
		RepairPlanAndProc yearRepairPlan = planDetail.getPlan();
		if (null != yearRepairPlan.getPlanAllFee()) {
			yearRepairPlan.setPlanAllFee(yearRepairPlan.getPlanAllFee()-delPlanRepairFees);  //更新计划总费用
		}
		this.repairPlanAndProcDao.storeRepairPlanOrProc(yearRepairPlan);                     //保存更新费用的计划
		this.repairFeeDao.deleteAllRepairItem(repairFees);
		 //移除计划明细中相关的维修费用
		for (RepairFee fee : repairFees) {
			planDetail.getRepairFees().remove(fee);
		}
		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(planDetail); 
		
		calculateYearProcDetailFeeByRepairProcFee(planDetail);                          //计算实际维修中的实际总费用
	}
	
	public void storeRepairFee(Long yearRepairPlanDetailId,
			String allRepairFeeId, String allRepairFeeProcFee) {
		String [] repairFeeId = null;
		String [] procFee = null;
		double repairProcDetailAllFee =0;
		int repairFeeNum = 0;
		if (null != allRepairFeeId) {
			repairFeeId = allRepairFeeId.split(",");
		}
		if (null != allRepairFeeProcFee) {
			procFee = allRepairFeeProcFee.split(",");
		}
		while (null != repairFeeId && repairFeeNum<repairFeeId.length) {
			RepairFee repairFee = this.repairFeeDao.loadRepairFee(Long.valueOf(repairFeeId[repairFeeNum]));
			if (null != procFee) {
				for (int i=0; i<procFee.length; i=i+2) {
					if (procFee[i].equals(repairFeeId[repairFeeNum]))  {
						repairFee.setProcFee(Double.valueOf(procFee[i+1]));       //设置大项修维修的实际维修费用
						repairProcDetailAllFee += Double.valueOf(procFee[i+1]);
						break;
					} else {
						repairFee.setProcFee(null);
					}
				}
			} else {
				repairFee.setProcFee(null);
			}
			repairFeeNum++;
			this.repairFeeDao.storeRepairFee(repairFee);
		}
		RepairPlanAndProcDetail procDetail = this.repairPlanAndProcDetailDao.loadRepairPlanAndProcDetail(yearRepairPlanDetailId);
		calculateYearProcDetailFeeByRepairProcFee(procDetail);             //根据大项修实施明细，计算与他关联的实际维修费用的总计

	}
	//根据大项修实施明细，计算与他关联的实际维修费用的总计
	private void calculateYearProcDetailFeeByRepairProcFee(RepairPlanAndProcDetail procDetail) {
		double countFee = 0;            //用来累计实施的维修费用
		for (RepairFee procFee : procDetail.getRepairFees()) {
			if (null != procFee.getProcFee()) {
				countFee += procFee.getProcFee().doubleValue();
			}
		}
		procDetail.setProcDetailAllFee(Double.valueOf(countFee));
		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(procDetail); 
		calculateYearProcFeeByRepairProcDetailFee(procDetail.getProc());             //根据实施明细计算实施总费用
	}
	
    //	根据实施明细计算实施总费用
	private void calculateYearProcFeeByRepairProcDetailFee(RepairPlanAndProc proc) {
		int countFee = 0;
		for (RepairPlanAndProcDetail detail : proc.getProcDetails()) {
			if (null != detail.getProcDetailAllFee()) {
				countFee += detail.getProcDetailAllFee().doubleValue();
			}
		}
		proc.setProcAllFee(Double.valueOf(countFee));                       //设置维修实际总费用新的费用值
		proc.setPlanAllFee(proc.getRepairPlan().getPlanAllFee());       //同步实施维修中的计划总费用
		this.repairPlanAndProcDao.storeRepairPlanOrProc(proc);     
	}
  //	------------------------------------- 故障维修计划维修费用的操作End ------------------------------------------
	public void storeRepairFee(FaultRepair faultRepair, RepairFee repairFee, String oldPlanFee) {
		double newRepairFee = 0;				//从repairFee中获取的修改后该维修详细的费用值
		if(repairFee.getProcFee()!=null){
			newRepairFee = repairFee.getProcFee().doubleValue();               //获得修改后的值
		}
		
		//移除该预算编号关联的故障记录总维修费用
		if (null != faultRepair.getBudgetNo()) {
			this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(faultRepair.getBudgetNo(), faultRepair.getAllFee());
		}
		if (repairFee.isNew()) {       //新建维修费用
			/**
			 * 更新故障维修总费用
			 */
			if (null != faultRepair.getAllFee()) {
				faultRepair.setAllFee(faultRepair.getAllFee().doubleValue() + newRepairFee);
			} 
		} else {                      //更新维修费用
			if (null != oldPlanFee) {
				/**
				 * 更新故障维修总费用
				 */
				if (null != faultRepair.getAllFee()) {
					faultRepair.setAllFee(faultRepair.getAllFee().doubleValue() - Double.valueOf(oldPlanFee).doubleValue() + newRepairFee);
				}
			} else {
				/**
				 * 更新故障维修总费用
				 */
				if (null != faultRepair.getAllFee()) {
					faultRepair.setAllFee(faultRepair.getAllFee().doubleValue() + newRepairFee);
				}
			}
		}
		if (null == faultRepair.getAllFee()) {                   //如果故障维修总费用为空,则直接赋值
			faultRepair.setAllFee(Double.valueOf(newRepairFee));
		}
		this.faultRepairDao.storeFaultRepair(faultRepair);       //保存已更新故障维修总费用的故障维修对象
		this.repairFeeDao.storeRepairFee(repairFee);
		
		//加上该预算编号关联的年度预算总维修费用
		if (null != faultRepair.getBudgetNo()) {
			this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(faultRepair.getBudgetNo(),faultRepair.getAllFee());
		}
	}
	public void deleteAllRepairFee(Collection<RepairFee> repairFees, FaultRepair faultRepair) {
		double delProcRepairFees = 0;
		double procAllFee = 0;
		
		//移除该预算编号关联的故障记录已发生费用
		if (null != faultRepair.getBudgetNo()) {
			this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(faultRepair.getBudgetNo(), faultRepair.getAllFee());
		}
		/**
		 * 累计要删除的维修费用的价格
		 */
		for (Iterator it = repairFees.iterator(); it.hasNext();) {
			RepairFee repairFee = (RepairFee)it.next();
			if (null != repairFee.getProcFee()) {
				//累计要删除实际维修费用
				delProcRepairFees += repairFee.getProcFee().doubleValue();
			}
		}
		//更新计划的总费用
		if (null != faultRepair.getAllFee()) {
			procAllFee = faultRepair.getAllFee().doubleValue();
			faultRepair.setAllFee(Double.valueOf(procAllFee-delProcRepairFees));     //更新故障维修总费用
		}
		this.faultRepairDao.storeFaultRepair(faultRepair);
		this.repairFeeDao.deleteAllRepairItem(repairFees);
		
		//加上该预算编号关联的年度预算已发生费用
		if (null != faultRepair.getBudgetNo()) {
			this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(faultRepair.getBudgetNo(),faultRepair.getAllFee());
		}
	}
	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}
	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}
}
