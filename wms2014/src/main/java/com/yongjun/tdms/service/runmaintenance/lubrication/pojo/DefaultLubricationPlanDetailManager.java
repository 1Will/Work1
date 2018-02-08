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

import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationPlanDetailDao;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanStatus;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationRuleManager;

/**
 * @author qs
 * @version $Id: DefaultLubricationPlanDetailManager.java 11075 2008-02-25 07:25:55Z zbzhang $
 */
public class DefaultLubricationPlanDetailManager extends BaseManager implements LubricationPlanDetailManager{
	private final LubricationPlanDetailDao lubricationPlanDetailDao;
	private final LubricationRuleManager	lubricationRuleManager;

	public DefaultLubricationPlanDetailManager(
			LubricationPlanDetailDao lubricationPlanDetailDao,
			LubricationRuleManager	lubricationRuleManager) {
		this.lubricationPlanDetailDao = lubricationPlanDetailDao;
		this.lubricationRuleManager = lubricationRuleManager;
	}

	public void storeLubricationPlanDetail(
			LubricationPlanDetail lubricationPlanDetail) {
		setLubricationProcDetailDefaultValue(lubricationPlanDetail,lubricationPlanDetail);    //设置润滑计划明细中默认的实施字段
		
		//设置计划明细相关的润滑实施
		for (LubricationPlan proc : lubricationPlanDetail.getPlan().getLubricationProc()) {
			lubricationPlanDetail.setProc(proc);
		}
		lubricationPlanDetailDao.storeLubricationPlanDetail(lubricationPlanDetail);
	}
	
	public List<LubricationPlanDetail> loadAllLubricationPlanDetail(Long[] lubricationPlanDetailIds){
		return lubricationPlanDetailDao.loadAllLubricationPlanDetail(lubricationPlanDetailIds);
	}
	
	public LubricationPlanDetail loadLubricationPlanDetail(Long id){
		return lubricationPlanDetailDao.loadLubricationPlanDetail(id);
	}
	
	public void deleteAllLubricationPlanDetail(List<LubricationPlanDetail> planDetails) throws Exception{
		//如果润滑计划已经实施,删除失败
		updateLubricationPlanDetailByLubricationPlanDetailProc(planDetails);
		lubricationPlanDetailDao.deleteAllLubricationPlanDetail(planDetails);
	}
    public void updateLubricationPlanDetailByLubricationPlanDetailProc(List<LubricationPlanDetail> planDetails)throws Exception{
    	for (LubricationPlanDetail planDetail : planDetails) {
			if (planDetail.getPlanStatus().equals(LubricationPlanStatus.FINISHED)) {
				throw new Exception();
			}
		}
    }
	public void storeLubricationPlanDetail(String allLubricationPlanDetailId, String allLubricationOilQty,
			String allPlanExePeople, String allEstimateExecDate, String allComment,String allPalnExectPeople) {
		String [] detailId = null;                    
		String [] planExePeople = null;
		String [] estimateExecDate = null;
		String [] comment = null;
		String [] lubricationOilQty = null;
		String [] planExcetPeople = null;
		if (null != allLubricationPlanDetailId) {
			detailId = allLubricationPlanDetailId.split(",");
		}
		if (null != allLubricationOilQty) {
			lubricationOilQty = allLubricationOilQty.split(",");
		}
		if (null != allPlanExePeople) {
			planExePeople = allPlanExePeople.split("##");
		}
		if(null != allPalnExectPeople){
			planExcetPeople = allPalnExectPeople.split("##");
		}
		if (null != allEstimateExecDate) {
			estimateExecDate = allEstimateExecDate.split(",");
		}
		if (null != allComment) {
			comment = allComment.split("##");
		}
		updateLubricationPlanDetail(detailId, lubricationOilQty, planExePeople, planExcetPeople, estimateExecDate, comment);
	}
	
	/*
	 * 更新润滑计划明细中计划润滑计量,计划执行人,计划执行时间,备注的值
	 */
	private void updateLubricationPlanDetail(String [] detailId, String [] lubricationOilQty, 
			String [] planExePeople, String [] planExcetPeople, String [] estimateExecDate, String [] comment) {
		int detailNum = 0;
		
		while (detailNum < detailId.length) {
			LubricationPlanDetail planDetail = this.lubricationPlanDetailDao.loadLubricationPlanDetail(Long.valueOf(detailId[detailNum]));
			LubricationRule lubricationRule = planDetail.getRule();
			if (null != lubricationOilQty) {                        //设置计划润滑计量
				for (int i=0; i<lubricationOilQty.length; i = i+2) {                  
					if (lubricationOilQty[i].equals(detailId[detailNum])) {
						planDetail.setPlanLubricatingOilQty(Double.valueOf(lubricationOilQty[i+1]));
						break;
					} else {
						planDetail.setPlanLubricatingOilQty(null);
					}
				}
			} else {
				planDetail.setPlanLubricatingOilQty(null);
			}
			
			if (null != planExePeople) {                        //设置计划负责人
				for (int i=0; i<planExePeople.length; i = i+2) {                  
					if (planExePeople[i].equals(detailId[detailNum])) {
						planDetail.setPlanExePeople(planExePeople[i+1]);
						break;
					} else {
						planDetail.setPlanExePeople(null);
					}
				}
			} else {
				planDetail.setPlanExePeople(null);
			}
			
			if(null != planExcetPeople){						//设置计划执行人
				for (int i=0; i<planExcetPeople.length; i = i+2) {                  
					if (planExcetPeople[i].equals(detailId[detailNum])) {
						planDetail.setPlanExectPeople(planExcetPeople[i+1]);
						planDetail.setActualExePeople(planExcetPeople[i+1]);
						break;
					} else {
						planDetail.setPlanExectPeople(null);
					}
				}
			}else{
				planDetail.setPlanExectPeople(null);
			}
			
			if (null != estimateExecDate) {                      //设置计划执行时间
				for (int i=0; i<estimateExecDate.length; i = i+2) {
					if (estimateExecDate[i].equals(detailId[detailNum])) {
						planDetail.setEstimateExecDate(DateUtil.toDate(estimateExecDate[i+1], "yyyy-MM-dd"));
						//设置上次润滑时间
						lubricationRule.setLastLubricationDate(DateUtil.toDate(estimateExecDate[i+1], "yyyy-MM-dd"));
						this.lubricationRuleManager.storeLubricationRule(lubricationRule);
						break;
					} else {
						planDetail.setEstimateExecDate(null);
					}
				}
			} else {
				planDetail.setEstimateExecDate(null);
			}
			
			if (null != comment) {                            //设置备注
				for (int i=0; i<comment.length; i=i+2) {
					if (comment[i].equals(detailId[detailNum])) {
						planDetail.setComment(comment[i+1]);
						break;
					} else {
						planDetail.setComment(null);
					}
				}
			} else {
				planDetail.setComment(null);
			}
			setLubricationProcDetailDefaultValue(planDetail, planDetail);
			this.lubricationPlanDetailDao.storeLubricationPlanDetail(planDetail);
			detailNum++;

		}
	}
	
	public void setLubricationProcDetailDefaultValue(LubricationPlanDetail procDetail, LubricationPlanDetail planDetail) {
		procDetail.setActualLubricatingOilQty(planDetail.getPlanLubricatingOilQty());      //设置默认的实际润滑计量值
		procDetail.setActualExecDate(planDetail.getEstimateExecDate());                    //设置默认的实际执行时间
		procDetail.setActualExePeople(planDetail.getActualExePeople());                    //设置默认的实际执行人
	}
	
	public void storeLubricationProcDetail(String allLubricationProcDetailId,
			String allProcEstimateExecDate, String allProcLubricationOilQty, String allLubricationResult,String allActualExectPeople) {
		String [] detailId = null;
		String [] procExePeople = null;
		String [] procEstimateExecDate = null;
		String [] procLubricationOilQty = null;
		String [] lubricationResult = null;
		String [] actualExectPeople = null;
		if (null != allLubricationProcDetailId) {
			detailId = allLubricationProcDetailId.split(",");
		}
		if (null != allActualExectPeople){
			actualExectPeople = allActualExectPeople.split("##");
		}
		if (null != allProcEstimateExecDate) {
			procEstimateExecDate = allProcEstimateExecDate.split(",");
		}
		if (null != allProcLubricationOilQty) {
			procLubricationOilQty = allProcLubricationOilQty.split(",");
		}
		if (null != allLubricationResult) {
			lubricationResult = allLubricationResult.split(",");
		}
		updateLubricationProcDetail(detailId, procExePeople, actualExectPeople, procEstimateExecDate, procLubricationOilQty, lubricationResult);
	}
	
	/**
	 * @param detailId
	 * @param procExePeople
	 * @param actualExectPeople
	 * @param procEstimateExecDate
	 * @param procLubricationOilQty
	 * @param lubricationResult
	 */
	private void updateLubricationProcDetail(String [] detailId, String [] procExePeople, String [] actualExectPeople,
			String [] procEstimateExecDate, String [] procLubricationOilQty, String [] lubricationResult) {
		int detailNum = 0;
		
		while (detailNum < detailId.length) {
			LubricationPlanDetail procDetail = this.lubricationPlanDetailDao.loadLubricationPlanDetail(Long.valueOf(detailId[detailNum]));
			LubricationRule lubricationRule = procDetail.getRule();
			if (null != procExePeople) {                          //设置实际执行人的值
				for (int i=0; i<procExePeople.length; i = i+2) {
					if (procExePeople[i].equals(detailId[detailNum])) {
						procDetail.setActualExePeople(procExePeople[i+1]);
						break;
					} else {
						procDetail.setActualExePeople(null);
					}
				}
			} else {
				procDetail.setActualExePeople(null);
			}
			
			if(null != actualExectPeople){						//设置实际执行人
				for (int i=0; i<actualExectPeople.length; i = i+2) {                  
					if (actualExectPeople[i].equals(detailId[detailNum])) {
						procDetail.setActualExePeople(actualExectPeople[i+1]);
						//设置上次执行人
						lubricationRule.setPreExePeople(actualExectPeople[i+1]);
						break;
					} else {
						procDetail.setActualExePeople(null);
					}
				}
			}else{
				procDetail.setActualExePeople(null);
			}
			
			if (null != procEstimateExecDate) {                   //设置实际执行时间的值
				for (int i=0; i<procEstimateExecDate.length; i=i+2) {
					if (procEstimateExecDate[i].equals(detailId[detailNum])) {
						procDetail.setActualExecDate(DateUtil.toDate(procEstimateExecDate[i+1], "yyyy-MM-dd"));
						//设置上次润滑时间
						lubricationRule.setLastLubricationDate(DateUtil.toDate(procEstimateExecDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						procDetail.setActualExecDate(null);
					}
				}
			} else {
				procDetail.setActualExecDate(null);
			}
			
			if (null != procLubricationOilQty) {                 //设置实际润滑计量
				for (int i=0; i<procLubricationOilQty.length; i=i+2) {
					if (procLubricationOilQty[i].equals(detailId[detailNum])) {
						procDetail.setActualLubricatingOilQty(Double.valueOf(procLubricationOilQty[i+1]));
						break;
					} else {
						procDetail.setActualLubricatingOilQty(null);
					}
				}
			} else {
				procDetail.setActualLubricatingOilQty(null);
			}
			
			if (null != lubricationResult) {                         //设置执行结果的值
				for (int i=0; i<lubricationResult.length; i=i+2) {
					if (lubricationResult[i].equals(detailId[detailNum])) {
						if (lubricationResult[i+1].equals(LubricationPlanStatus.UNFINISH.toString())) {
							procDetail.setPlanStatus(LubricationPlanStatus.UNFINISH);
						} else {
							procDetail.setPlanStatus(LubricationPlanStatus.FINISHED);
						}
						break;
					} 
				}
			}
			lubricationRuleManager.storeLubricationRule(lubricationRule);
			this.lubricationPlanDetailDao.storeLubricationPlanDetail(procDetail);
			detailNum++;
		}
	}

}
