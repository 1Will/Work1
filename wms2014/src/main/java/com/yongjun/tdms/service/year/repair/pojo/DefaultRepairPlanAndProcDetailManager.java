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
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDao;
import com.yongjun.tdms.dao.year.repair.RepairPlanAndProcDetailDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;
/**
 * <p>Title: DefaultRepairPlanAndProcDetailManager
 * <p>Description: 大项修计划和实施明细业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager
 */
public class DefaultRepairPlanAndProcDetailManager implements
		RepairPlanAndProcDetailManager {

	private final RepairPlanAndProcDetailDao repairPlanAndProcDetailDao;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final UserManager userManager;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	
	public DefaultRepairPlanAndProcDetailManager(RepairPlanAndProcDetailDao repairPlanAndProcDetailDao,
			DepartmentManager departmentManager, CodeValueManager codeValueManager,
			UserManager userManager,RepairPlanAndProcManager repairPlanAndProcManager) {
		this.repairPlanAndProcDetailDao = repairPlanAndProcDetailDao;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.userManager = userManager;
		this.repairPlanAndProcManager = repairPlanAndProcManager;
	}
	
	public RepairPlanAndProcDetail loadRepairPlanAndProcDetail(
			Long planOrProcDetailId) {
		return this.repairPlanAndProcDetailDao.loadRepairPlanAndProcDetail(planOrProcDetailId);
	}

	public List<RepairPlanAndProcDetail> loadAllRepairPlanAndProcDetails(
			Long[] planOrProcDetailIds) {
		return this.repairPlanAndProcDetailDao.loadAllRepairPlanAndProcDetails(planOrProcDetailIds);
	}

	public List<RepairPlanAndProcDetail> loadAllRepairPlanAndProcDetails() {
		return this.repairPlanAndProcDetailDao.loadAllRepairPlanAndProcDetails();
	}

	public void storeRepairPlanDetail(
			RepairPlanAndProcDetail planDetail) {
		createYearRepairProcDetail(planDetail);             //创建该计划明细所关联的实施
		planDetail.setProcBeginDate(planDetail.getPlanBeginDate());      //设置默认的实际开工时间
		planDetail.setProcEndDate(planDetail.getPlanEndDate());          //设置默认的实际完工时间
		planDetail.setProcRepairDate(planDetail.getPlanRepairDate());    //设置默认的实际维修时间
		//planAndProcDetail.setProcExecPeople(planAndProcDetail.getProcExecPeople());
		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(planDetail);
	}

	public void deleteRepairPlanAndProcDetail(
			RepairPlanAndProcDetail planAndProcDetail) {
		this.repairPlanAndProcDetailDao.deleteRepairPlanAndProcDetail(planAndProcDetail);
	}

	public void deleteAllRepairPlanAndProcDetails(
			Collection<RepairPlanAndProcDetail> planAndProcDetails) {
		this.repairPlanAndProcDetailDao.deleteAllRepairPlanAndProcDetails(planAndProcDetails);
	}

	public List<RepairPlanAndProcDetail> loadAllRepairPlanOrProcDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	public void storeAllRepairPlanDetails(String allYearRepairPlanDetailId, 
			String allYearRepairPlanDetailDepartment, String allYearRepairPlanDetailCategory, 
			String allYearRepairPlanDetailPlanRepairDate, String allYearRepairPlanDetailPlanBeginDate, 
			String allYearRepairPlanDetailPlanEndDate, String allYearRepairPlanDetailExternalHelp) {
		String [] yearRepairPlanDetailId = null;           //大项修计划详细ID
		String [] department = null;                       //承修单位
	//	String [] technicalLevel = null;                   //技术等级
		String [] category = null;                         //种类
		String [] repairDate = null;                       //维修日期
		String [] planBeginDate = null;                    //计划开工时间
		String [] palnEndDate = null;                      //计划完工时间
		String [] externalHelp = null;                     //外协
		
		if (null != allYearRepairPlanDetailId) {
			yearRepairPlanDetailId = allYearRepairPlanDetailId.split(",");
		}
		if (null != allYearRepairPlanDetailDepartment) {
			department = allYearRepairPlanDetailDepartment.split(",");
		}
		if (null != allYearRepairPlanDetailCategory) {
			category = allYearRepairPlanDetailCategory.split(",");
		}
		if (null != allYearRepairPlanDetailPlanRepairDate) {
			repairDate = allYearRepairPlanDetailPlanRepairDate.split(","); 
		}
		if (null != allYearRepairPlanDetailPlanBeginDate) {
			planBeginDate = allYearRepairPlanDetailPlanBeginDate.split(",");
		}
		if (null != allYearRepairPlanDetailPlanEndDate) {
			palnEndDate = allYearRepairPlanDetailPlanEndDate.split(",");
		}
		if (null != allYearRepairPlanDetailExternalHelp) {
			externalHelp = allYearRepairPlanDetailExternalHelp.split(",");
		}
		updateRepairPlanAndProcDetai(yearRepairPlanDetailId, department,
				 category, repairDate, planBeginDate, palnEndDate, externalHelp);
	}
	
	private void updateRepairPlanAndProcDetai(String [] yearRepairPlanDetailId,
			String [] department, String [] category, String [] repairDate,
			 String [] planBeginDate, String [] palnEndDate, String [] externalHelp) {
		int planDetailIdCount = 0;
		
		while (planDetailIdCount < yearRepairPlanDetailId.length) {
			RepairPlanAndProcDetail planDetail = this.repairPlanAndProcDetailDao.loadRepairPlanAndProcDetail(Long.valueOf(yearRepairPlanDetailId[planDetailIdCount]));
			if (null != department) {                              //更新承修单位的值
				for (int i=0; i<department.length; i=i+2) {
					if (department[i].equals(yearRepairPlanDetailId[planDetailIdCount])) {
						planDetail.setDepartment(this.departmentManager.loadDepartment(Long.valueOf(department[i+1])));
						break;
					} else {
						planDetail.setDepartment(null);
					}
				}
			} else {
				planDetail.setDepartment(null);
			}
			
//			if (null != technicalLevel) {                       //更新技术等级的值
//				for (int i=0; i<technicalLevel.length; i=i+2) {
//					if (technicalLevel[i].equals(yearRepairPlanDetailId[planDetailIdCount])) {
//						planDetail.setTechnicalLevel(this.codeValueManager.loadCodeValue(Long.valueOf(technicalLevel[i+1])));
//						break;
//					} else {
//						planDetail.setTechnicalLevel(null);
//					}
//				}
//			} else {
//				planDetail.setTechnicalLevel(null);
//			}
			
			if (null != category) {                           //更新种类
				for (int i=0; i<category.length; i=i+2) {
					if (category[i].equals(yearRepairPlanDetailId[planDetailIdCount])) {
						planDetail.setCategory(this.codeValueManager.loadCodeValue(Long.valueOf(category[i+1])));
						break;
					} else {
						planDetail.setCategory(null);
					}
				}
			} else {
				planDetail.setCategory(null);
			}
			
			if (null != repairDate) {                      //更新计划维修时间
				for (int i=0; i<repairDate.length; i=i+2) {
					if (repairDate[i].equals(yearRepairPlanDetailId[planDetailIdCount])) {
						planDetail.setPlanRepairDate(DateUtil.toDate(repairDate[i+1], "yyyy-MM"));
						break;
					} else {
						planDetail.setPlanRepairDate(null);
					}
				}
			} else {
				planDetail.setPlanRepairDate(null);
			}
			
			if (null != planBeginDate) {                       //更新计划开工日期
				for (int i=0; i<planBeginDate.length; i=i+2) {
					if (planBeginDate[i].equals(yearRepairPlanDetailId[planDetailIdCount])) {
						planDetail.setPlanBeginDate(DateUtil.toDate(planBeginDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						planDetail.setPlanBeginDate(null);
					}
				}
			} else {
				planDetail.setPlanBeginDate(null);
			}
			
			if (null != palnEndDate) {                       //更新计划完工日期
				for (int i=0; i<palnEndDate.length; i=i+2) {
					if (palnEndDate[i].equals(yearRepairPlanDetailId[planDetailIdCount])) {
						planDetail.setPlanEndDate(DateUtil.toDate(palnEndDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						planDetail.setPlanEndDate(null);
					}
				}
			} else {
				planDetail.setPlanEndDate(null);
			}
			if (null != externalHelp) {
				for (int i=0; i<externalHelp.length; i=i+2) {              //设置计划明细中的是否外协
					if (externalHelp[i].equals(yearRepairPlanDetailId[planDetailIdCount])) {
						if (externalHelp[i+1].equals("Y")) {               //"Y"表示是外协
							planDetail.setExternalHelpFlag(true);
						} else {
							planDetail.setExternalHelpFlag(false);
						}
					}
				}
			} 
			planDetail.setProcRepairDate(planDetail.getPlanRepairDate());        //初始化实际维修日期
			planDetail.setProcBeginDate(planDetail.getPlanBeginDate());          //初始化实际开工日期
			planDetail.setProcEndDate(planDetail.getPlanEndDate());              //初始化实际完工日期
			
			this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(planDetail);
			planDetailIdCount++;
			
		}
	}

	public void storeAllRepairProcDetails(String allYearRepairProcDetailId, 
			String allYearRepairPlanDetailProcBeginDate,
			String allYearRepairPlanDetailProcEndDate, 
			String allProcExePeople, String allProcExecResult,
			String allYearRepairPlanDetailProcRepairDate) {
		String [] yearRepairPlanDetailId = null;                        //大项修实施明细ID
		String [] procBeginDate = null;                                //实际开工日期
		String [] procEndDate = null;                                  //实际完工日期
		String [] procExePeople = null;                                //执行人
		String [] procExecResult = null;                               //执行结果
		String [] procRepairDate = null;                             //实际维修日期
		
		if (null != allYearRepairProcDetailId) {
			yearRepairPlanDetailId = allYearRepairProcDetailId.split(",");
		}
		if (null != allYearRepairPlanDetailProcBeginDate) {
			procBeginDate = allYearRepairPlanDetailProcBeginDate.split(",");
		}
		if (null != allYearRepairPlanDetailProcEndDate) {
			procEndDate = allYearRepairPlanDetailProcEndDate.split(",");
		}
		if (null != allProcExePeople) {
			procExePeople = allProcExePeople.split(",");
		}
		if (null != allProcExecResult) {
			procExecResult = allProcExecResult.split(",");
		}
		if (null != allYearRepairPlanDetailProcRepairDate) {
			procRepairDate = allYearRepairPlanDetailProcRepairDate.split(",");
		}
		updateRepairPlanAndProcDetai(yearRepairPlanDetailId, procBeginDate,
				procEndDate, procExePeople, procExecResult, procRepairDate);
	}
	
	private void updateRepairPlanAndProcDetai(String [] yearRepairPlanDetailId,
			String [] procBeginDate, String [] procEndDate, 
			String [] procExePeople, String [] procExecResult,
			String [] procRepairDate) {
		int procDetailIdCount = 0;
		
		while (procDetailIdCount < yearRepairPlanDetailId.length) {
			RepairPlanAndProcDetail procDetail = this.repairPlanAndProcDetailDao.loadRepairPlanAndProcDetail(Long.valueOf(yearRepairPlanDetailId[procDetailIdCount]));
			
			if (null != procBeginDate) {                               //设置实际开工日期
				for (int i=0; i<procBeginDate.length; i = i+2) {
					if (procBeginDate[i].equals(yearRepairPlanDetailId[procDetailIdCount])) {
						procDetail.setProcBeginDate(DateUtil.toDate(procBeginDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						procDetail.setProcBeginDate(null);
					}
				}
			} else {
				procDetail.setProcBeginDate(null);
			}
			
			if (null != procEndDate) {                                //设置实际完工日期
				for (int i=0; i<procEndDate.length; i = i+2) {
					if (procEndDate[i].equals(yearRepairPlanDetailId[procDetailIdCount])) {
						procDetail.setProcEndDate(DateUtil.toDate(procEndDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						procDetail.setProcEndDate(null);
					}
				}
			} else {
				procDetail.setProcEndDate(null);
			}
			
			if (null != procRepairDate) {                                //设置实际维修日期
				for (int i=0; i<procRepairDate.length; i = i+2) {
					if (procRepairDate[i].equals(yearRepairPlanDetailId[procDetailIdCount])) {
						procDetail.setProcRepairDate(DateUtil.toDate(procRepairDate[i+1], "yyyy-MM"));
						break;
					} else {
						procDetail.setProcRepairDate(null);
					}
				}
			} else {
				procDetail.setProcRepairDate(null);
			}
			
			if (null != procExePeople) {                          //设置执行人
				for (int i=0; i<procExePeople.length; i = i+2) {
					if (procExePeople[i].equals(yearRepairPlanDetailId[procDetailIdCount])) {
						procDetail.setProcExecPeople(this.userManager.loadUser(Long.valueOf(procExePeople[i+1])));
					    break;
					} else {
						procDetail.setProcExecPeople(null);
					}
				}
			} else {
				procDetail.setProcExecPeople(null);
			}
			
			if (null != procExecResult) {
				for (int i=0; i<procExecResult.length; i++) {     //设置明细中的执行结果
					if (procExecResult[i].equals(yearRepairPlanDetailId[procDetailIdCount])) {
						if (procExecResult[i+1].equals(PreRepairDetailResult.UNFINISHED.toString())) {
							procDetail.setProcResult(PreRepairDetailResult.UNFINISHED);
						} else if (procExecResult[i+1].equals(PreRepairDetailResult.FINISHED.toString())) {
							procDetail.setProcResult(PreRepairDetailResult.FINISHED);
						} else if (procExecResult[i+1].equals(PreRepairDetailResult.SHIFT.toString())) {
							procDetail.setProcResult(PreRepairDetailResult.SHIFT);
						} else {
							procDetail.setProcResult(PreRepairDetailResult.CANCEL);
						}
					}
				}
			}
			this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(procDetail);
			procDetailIdCount++;
			
		}
	}
	
	/**
	 * 建立大项修实施与计划明细关联
	 * @param yearRepairPlanDetail  计划明细
	 */
	private void createYearRepairProcDetail(RepairPlanAndProcDetail yearRepairPlanDetail) {
		RepairPlanAndProc plan = yearRepairPlanDetail.getPlan();
		for (RepairPlanAndProc proc : plan.getRepairProc()) {
			yearRepairPlanDetail.setProc(proc);
		}
	}

	public void storeRepairProcDetail(RepairPlanAndProcDetail procDetail) {
		this.repairPlanAndProcDetailDao.storeRepairPlanAndProcDetail(procDetail);
	}
	
	public void deleteAllYearRepairPlanDetail(
			Collection<RepairPlanAndProcDetail> yearRepairPlanDetails, RepairPlanAndProc plan) throws Exception{
		for (Iterator iter = yearRepairPlanDetails.iterator(); iter.hasNext(); ) {
			RepairPlanAndProcDetail yearRepairPlanDetail = (RepairPlanAndProcDetail)iter.next();
			if(yearRepairPlanDetail.getProcResult().equals(PreRepairDetailResult.FINISHED)) {
				throw new Exception();
			}
		}
        //清除大项修计划中以被删除的计划明细
		for (RepairPlanAndProcDetail detail : yearRepairPlanDetails) {
			plan.getPlanDetails().remove(detail);
		}
        //清除大项修实施中以被删除的计划明细
		for (RepairPlanAndProc proc : plan.getRepairProc()) {  
			for (RepairPlanAndProcDetail detail : yearRepairPlanDetails) {
				proc.getProcDetails().remove(detail);
			}
		}
		this.repairPlanAndProcDetailDao.deleteAllRepairPlanAndProcDetails(yearRepairPlanDetails);
		this.repairPlanAndProcManager.resetYearRepairPlanOrProcFee(plan);           //重置大项修维修计划和其相关联的实施的总费用
		
	}

}
