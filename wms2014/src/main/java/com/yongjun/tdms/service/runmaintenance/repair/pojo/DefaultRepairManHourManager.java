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
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.repair.RepairManHourDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;
import com.yongjun.tdms.service.runmaintenance.repair.RepairManHourManager;

/**
 * <p>Title: DefaultRepairManHourManager
 * <p>Description: 维修工时明细业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DefaultRepairManHourManager.java 11172 2008-03-03 01:47:39Z zbzhang $
 * @see  com.yongjun.tdms.service.runmaintenance.repair.RepairManHourManager
 */
public class DefaultRepairManHourManager implements RepairManHourManager {

	private final RepairManHourDao repairManHourDao;
	//private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	
	public DefaultRepairManHourManager(RepairManHourDao repairManHourDao) {
		this.repairManHourDao = repairManHourDao;
	}
	public RepairManHour loadRepairManHour(Long repairManHourId) {
		return this.repairManHourDao.loadRepairManHour(repairManHourId);
	}

	public List<RepairManHour> loadAllRepairManHours(Long[] repairManHourIds) {
		return this.repairManHourDao.loadAllRepairManHours(repairManHourIds);
	}

	public List<RepairManHour> loadAllRepairManHours() {
		return this.repairManHourDao.loadAllRepairManHours();
	}

	public void storeRepairManHour(RepairManHour repairManHour) {
		if (null == repairManHour.getProcManHourNum()) {
			//设置维修工时中的实际工时数量的默认值
			setRepairManHourProcDefaultValue(repairManHour);
		}
		this.repairManHourDao.storeRepairManHour(repairManHour);
	}

	public void deleteRepairManHour(RepairManHour repairManHour) {
		this.repairManHourDao.deleteRepairManHour(repairManHour);
	}

	public void deleteAllRepairManHours(Collection<RepairManHour> repairManHours) {
		this.repairManHourDao.deleteAllRepairManHours(repairManHours);
	}

	public void resetRepairManHour(PreRepairPlanDetail detail) {
		for (RepairManHour item : detail.getManHours()) {
			item.setProcManHourNum(null);
			this.storeRepairManHour(item);
		}
	}
	
	public void resetRepairManHour(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
		for (RepairManHour oldRepairManHour : oldDetail.getManHours()) {
			RepairManHour newRepairManHour = new RepairManHour();             //新的维修工时
			newRepairManHour.setPreRepairDetail(newDetail);                   //设置新维修工时关联的维修计划明细
			copyRepairManHour(newRepairManHour, oldRepairManHour);            //拷贝老维修工时到新维修工时中
			this.repairManHourDao.storeRepairManHour(newRepairManHour);
			newDetail.getManHours().add(newRepairManHour);
		}
	}
	
    //	拷贝老维修工时到新维修工时中
	private void copyRepairManHour(RepairManHour newRepairManHour, RepairManHour oldRepairManHour) {
		newRepairManHour.setWorkType(oldRepairManHour.getWorkType());      //拷贝工种类型
		newRepairManHour.setManHourNum(oldRepairManHour.getManHourNum());  //拷贝计划工时数量
		newRepairManHour.setComment(oldRepairManHour.getComment());        //拷贝备注
		setRepairManHourProcDefaultValue(newRepairManHour);                //设置维修工时中的实际工时数量的默认值
	}
	
	//设置维修工时中的实际工时数量的默认值
	private void setRepairManHourProcDefaultValue(RepairManHour manHour) {
		manHour.setProcManHourNum(manHour.getManHourNum());               //设置实际工时数量
	}
	
	public void storeRepairManHour(String allRepairManHourId, String allRepairProcManHourNum) {
		String [] manHourId = null;
		String [] procManHourNum = null;
		
		if (null != allRepairManHourId) {
			manHourId = allRepairManHourId.split(",");
		}
		if (null != allRepairProcManHourNum) {
			procManHourNum = allRepairProcManHourNum.split(",");
		}
		updateRepairManHour(manHourId, procManHourNum);
	}
	
	//更形维修工时的实际工时数量
	private void updateRepairManHour(String [] manHourId, String [] procManHourNum) {
		int manHourCount = 0; 
		
		while (manHourCount < manHourId.length) {
			RepairManHour manHour = this.repairManHourDao.loadRepairManHour(Long.valueOf(manHourId[manHourCount]));
			if (null != procManHourNum) {
				for (int i=0; i<procManHourNum.length; i=i+2) {
					if (procManHourNum[i].equals(manHourId[manHourCount])) {
						manHour.setProcManHourNum(Double.valueOf(procManHourNum[i+1]));
						break;
					} else {
						manHour.setProcManHourNum(null);
					}
				}
			} else {
				manHour.setProcManHourNum(null);
			}
			this.repairManHourDao.storeRepairManHour(manHour);
			manHourCount++;
		}
	}
	
//	public void storeRepairManHour(PreRepairPlanDetail preRepairDetail, RepairManHour repairManHour, String oldManHourNum, String oldProcManHourNum, String planProcFlag) {
//		double allPlanFee = 0;
//		double allProcFee = 0;
//		if (null != preRepairDetail.getPlanAllFee()) {
//			allPlanFee = preRepairDetail.getPlanAllFee().doubleValue();
//		}
//		if (null != preRepairDetail.getProcAllFee()) {
//			allProcFee = preRepairDetail.getProcAllFee().doubleValue();
//		}
//		if (repairManHour.isNew()) {      //新增维修工时
//			if (null != repairManHour.getUnitPrice() && null != repairManHour.getManHourNum()) {
//				allPlanFee += repairManHour.getUnitPrice().doubleValue() * repairManHour.getManHourNum().doubleValue();
//			}
//			this.preRepairPlanDetailManager.resetPreRepairPlanOrProcAllFee(preRepairDetail,Double.valueOf(allPlanFee), null);
//			this.repairManHourDao.storeRepairManHour(repairManHour);
//		} else {                          //更新维修工时
//			if (planProcFlag.equals(PreRepairModel.PLAN.toString())) {        //更新计划总费用
//				if (null != oldManHourNum && allPlanFee != 0) {
//					allPlanFee -= Double.parseDouble(oldManHourNum);
//				}
//				if (null != repairManHour.getUnitPrice() && null != repairManHour.getManHourNum()) {
//					allPlanFee += repairManHour.getUnitPrice().doubleValue() * repairManHour.getManHourNum().doubleValue();
//				}
//				this.preRepairPlanDetailManager.resetPreRepairPlanOrProcAllFee(preRepairDetail,allPlanFee, null);
//			} else {                                                          //更新实际总费用
//				if (null != oldProcManHourNum && allProcFee != 0) {
//					allProcFee -= Double.parseDouble(oldProcManHourNum);
//				}
//				if (null != repairManHour.getUnitPrice() && null != repairManHour.getManHourNum()) {
//					allProcFee += repairManHour.getUnitPrice().doubleValue() * repairManHour.getProcManHourNum().doubleValue();	
//				}
//				this.preRepairPlanDetailManager.resetPreRepairPlanOrProcAllFee(preRepairDetail,null, allProcFee);
//			}
//			this.repairManHourDao.storeRepairManHour(repairManHour);
//		}
//	}
//	public void deleteAllRepairManHour(Collection<RepairManHour> repairManHours, PreRepairPlanDetail planDetail) {
//		double delRepairManHours = 0;
//		double planAllFee = 0;
//		/**
//		 * 累计要删除的维修工时的价格
//		 */
//		for (Iterator it = repairManHours.iterator(); it.hasNext();) {
//			RepairManHour repairManHour = (RepairManHour)it.next();
//			if (null != repairManHour.getUnitPrice() && null != repairManHour.getManHourNum()) {
//				delRepairManHours += repairManHour.getUnitPrice().doubleValue() * repairManHour.getManHourNum().doubleValue();
//			}
//		}
//		/**
//		 * 取得计划的总费用
//		 */
//		if (null != planDetail.getPlanAllFee()) {
//			planAllFee = planDetail.getPlanAllFee().doubleValue();
//		}
//		planDetail.setPlanAllFee(Double.valueOf((planAllFee-delRepairManHours)));     //更新计划总费用
//		this.preRepairPlanDetailManager.storePreRepairPlanDetail(planDetail);
//		this.repairManHourDao.deleteAllRepairManHours(repairManHours);			
//	}
	

}
