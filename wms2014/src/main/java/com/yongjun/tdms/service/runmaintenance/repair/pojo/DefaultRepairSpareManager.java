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

import com.yongjun.tdms.dao.runmaintenance.repair.RepairSpareDao;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.asset.device.DeviceSpareManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairSpareManager;

/**
 * <p>Title: DefaultRepairSpareManager
 * <p>Description: 预防性维修计划备件详细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DefaultRepairSpareManager.java 11225 2008-03-09 05:57:04Z zbzhang $
 */
public class DefaultRepairSpareManager implements RepairSpareManager{
	private final RepairSpareDao repairSpareDao;

	private final SpareManager spareManager;
	private final DeviceSpareManager toolingDevSpareManager;
	public DefaultRepairSpareManager(RepairSpareDao repairSpareDao,
			SpareManager spareManager,DeviceSpareManager toolingDevSpareManager) {
		this.repairSpareDao = repairSpareDao;
		this.spareManager = spareManager;
		this.toolingDevSpareManager = toolingDevSpareManager;
	}

	public List<RepairSpare> loadRepairSpares(Long[] spareIds) {
		return this.repairSpareDao.loadRepairSpares(spareIds);
	}

	public void storeRepairSpare(PreRepairPlanDetail preRepairPlanDetail,
			String addSpareIds) {
		String[] spareIds = addSpareIds.split(",");
		for (int i = 0; i < spareIds.length; i++) {
			RepairSpare repairSpare = new RepairSpare();
			repairSpare.setPreRepairDetail(preRepairPlanDetail);
			repairSpare.setSpare(this.spareManager.loasSpare(Long
					.valueOf(spareIds[i])));
			repairSpare.setPlanUsedNum(0);
			repairSpare.setProcUsedNum(0);
			this.repairSpareDao.storeRepairSpare(repairSpare);
		}
	}

	public void deleteAllRepairSpare(Collection<RepairSpare> repairSpares) {
		this.repairSpareDao.deleteAllRepairSpare(repairSpares);
	}

	public RepairSpare loadRepairSpare(Long repairSpareId) {
		return this.repairSpareDao.loadRepairSpare(repairSpareId);
	}

	public void storeRepairSpare(RepairSpare repairSpare) {
		this.repairSpareDao.storeRepairSpare(repairSpare);
	
	}

	public void resetRepairSpare(PreRepairPlanDetail detail) {
		for (RepairSpare item : detail.getSpares()) {
			item.setProcUsedNum(0);
			this.storeRepairSpare(item);
		}
	}
	public void storeRepairSpare(String saveSpareIds,String planProcFlag) {
		String[] spareIds = saveSpareIds.split(",");
		RepairSpare repairSpare = new RepairSpare();
		for (int i = 0; i < spareIds.length; i = i + 2) {
			repairSpare = repairSpareDao.loadRepairSpare(Long
					.valueOf(spareIds[i]));
			if (planProcFlag.equals(PreRepairModel.PLAN.toString())) {
				if (spareIds[i+1].equals("0")) {
					repairSpare.setPlanUsedNum(0);
				} else {
					repairSpare.setPlanUsedNum(Integer.valueOf(spareIds[i+1]));
					//如果该维修备件是从易损件中选来的,则置该易损件的上次更换日期为实际完成日期
					if (null != repairSpare.getDeviceSpare()) {
						PreRepairPlanDetail detail = repairSpare.getPreRepairDetail();
						DeviceSpare deviceSpare = repairSpare.getDeviceSpare();
						deviceSpare.setLastReplaceTime(detail.getProcEstimateFinishDate());
						this.toolingDevSpareManager.storeDeviceSpare(deviceSpare);
					}
				}
				setRepairSpareProcDefaultValue(repairSpare);
			} else {
				if (spareIds[i+1].equals("0")) {
					repairSpare.setProcUsedNum(0);
				} else {
					repairSpare.setProcUsedNum(Integer.valueOf(spareIds[i+1]));
				}
			}
			storeRepairSpare(repairSpare);
		}
	}

	public void resetRepairSpare(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
		for (RepairSpare oldRepairSpare : oldDetail.getSpares()) {
			RepairSpare newRepairSpare = new RepairSpare();                //新的维修备件
			newRepairSpare.setPreRepairDetail(newDetail);                  //设置新维修备件关联的维修计划明细
			copyRepairSpare(newRepairSpare, oldRepairSpare);               //拷贝老维修备件至新维修备件中
			this.repairSpareDao.storeRepairSpare(newRepairSpare);
			newDetail.getSpares().add(newRepairSpare);
		}		
	}
	
    //	拷贝老维修备件至新维修备件中
	private void copyRepairSpare(RepairSpare newRepairSpare, RepairSpare oldRepairSpare) {
		newRepairSpare.setSpare(oldRepairSpare.getSpare());                     //拷贝备件
		newRepairSpare.setPlanUsedNum(oldRepairSpare.getPlanUsedNum());         //拷贝计划备件使用数量
		setRepairSpareProcDefaultValue(newRepairSpare);
	}
	
	//设置维修备件中实际备件使用数量的默认值
	private void setRepairSpareProcDefaultValue(RepairSpare repairSpare) {
		if (null == repairSpare.getProcUsedNum() || repairSpare.getProcUsedNum().intValue() == 0) {
			repairSpare.setProcUsedNum(repairSpare.getPlanUsedNum());             //设置实际备件使用数量
		}
	}
	
	//添加大项修的维修备件
	public void storeRepairSpare(RepairPlanAndProcDetail repairPlanAndProcDetail, String addSpareIds) {
		String[] spareIds = addSpareIds.split(",");
		for (int i = 0; i < spareIds.length; i++) {
			RepairSpare repairSpare = new RepairSpare();
			repairSpare.setRepairPlanAndProcDetail(repairPlanAndProcDetail);
			repairSpare.setSpare(this.spareManager.loasSpare(Long
					.valueOf(spareIds[i])));
			repairSpare.setPlanUsedNum(0);
			repairSpare.setProcUsedNum(0);
			this.repairSpareDao.storeRepairSpare(repairSpare);
		}
	}
	
	public void storeRepairSpare(FaultRepair faultRepair, String addSpareIds) {
		String[] spareIds = addSpareIds.split(",");
		for (int i = 0; i < spareIds.length; i++) {
			RepairSpare repairSpare = new RepairSpare();
			repairSpare.setFaultRepair(faultRepair);
			repairSpare.setSpare(this.spareManager.loasSpare(Long
					.valueOf(spareIds[i])));
			repairSpare.setPlanUsedNum(0);
			repairSpare.setProcUsedNum(0);
			this.repairSpareDao.storeRepairSpare(repairSpare);
		}
	}

}
