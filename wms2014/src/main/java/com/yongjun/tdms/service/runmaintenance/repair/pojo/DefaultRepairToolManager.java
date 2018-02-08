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

import com.yongjun.tdms.dao.runmaintenance.repair.RepairToolDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;
import com.yongjun.tdms.service.runmaintenance.repair.RepairToolManager;
/**
 * <p>Title: DefaultRepairToolManager
 * <p>Description: 维修工具明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: DefaultRepairToolManager.java 11172 2008-03-03 01:47:39Z zbzhang $
 * @see com.yongjun.tdms.service.runmaintenance.repair.RepairToolManager
 */
public class DefaultRepairToolManager implements RepairToolManager {
	private final RepairToolDao repairToolDao;
	
	public DefaultRepairToolManager(RepairToolDao repairToolDao) {
		this.repairToolDao = repairToolDao;
	}
	
	public RepairTool loadRepairTool(Long repairToolId) {
		return this.repairToolDao.loadRepairTool(repairToolId);
	}

	public List<RepairTool> loadAllRepairTools(Long[] repairToolIds) {
		return this.repairToolDao.loadAllRepairTools(repairToolIds);
	}

	public List<RepairTool> loadAllRepairTools() {
		return this.repairToolDao.loadAllRepairTools();
	}

	public void storeRepairTool(RepairTool repairTool) {
		setRepairToolProcDefaultValue(repairTool);
		this.repairToolDao.storeRepairTool(repairTool);
	}

	public void deleteRepairTool(RepairTool repairTool) {
		this.repairToolDao.deleteRepairTool(repairTool);
	}

	public void deleteAllRepairTools(Collection<RepairTool> repairTools) {
		this.repairToolDao.deleteAllRepairTools(repairTools);
	}

	public void resetRepairTool(PreRepairPlanDetail detail) {
		for (RepairTool item : detail.getRepairTools()) {
			item.setProcUseNum(null);
			this.storeRepairTool(item);
		}
	}

	public void storeRepairTool(String allRepairToolId, String allRepairToolProcUseNum) {
		String [] repairToolId = null;
		String [] procUseNum = null;
		int procUseNumCount = 0;
		if (null != allRepairToolId) {
			repairToolId = allRepairToolId.split(",");
		}
		if (null != allRepairToolProcUseNum) {
			procUseNum = allRepairToolProcUseNum.split(",");
		}
		while (null != repairToolId && procUseNumCount<repairToolId.length) {
			RepairTool repairTool = this.repairToolDao.loadRepairTool(Long.valueOf(repairToolId[procUseNumCount]));
			if (null != procUseNum) {
				for (int i=0; i<procUseNum.length; i=i+2) {
					if (procUseNum[i].equals(repairToolId[procUseNumCount]))  {
						repairTool.setProcUseNum(Integer.valueOf(procUseNum[i+1]));
						break;
					} else {
						repairTool.setProcUseNum(null);
					}
				}
			} else {
				repairTool.setProcUseNum(null);
			}
			procUseNumCount++;
			this.repairToolDao.storeRepairTool(repairTool);
		}
	}

	public void resetRepairTool(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
		for (RepairTool oldRepairTool : oldDetail.getRepairTools()) {
			RepairTool newRepairTool = new RepairTool();                   //新的维修工具
			newRepairTool.setPreRepairDetail(newDetail);                   //设置新维修工具关联的维修计划明细
			copyRepairTool(newRepairTool, oldRepairTool);                  //拷贝老的维修工具至新的维修工具
			this.repairToolDao.storeRepairTool(newRepairTool);
			newDetail.getRepairTools().add(newRepairTool);
		}			
	}
	
    //	拷贝老的维修工具至新的维修工具
	private void copyRepairTool(RepairTool newRepairTool, RepairTool oldRepairTool) {
		newRepairTool.setName(oldRepairTool.getName());                      //拷贝工具名称
		newRepairTool.setSpecification(oldRepairTool.getSpecification());    //拷贝规格
		newRepairTool.setModel(newRepairTool.getModel());                    //拷贝型号
		newRepairTool.setCalcUnit(oldRepairTool.getCalcUnit());              //拷贝计量单位
		newRepairTool.setPlanUseNum(oldRepairTool.getPlanUseNum());         //拷贝计划使用数量
		newRepairTool.setComment(oldRepairTool.getComment());               //拷贝备注
		setRepairToolProcDefaultValue(newRepairTool);
	}
	
	//设置维修工具中的实际使用数量的默认值
	private void setRepairToolProcDefaultValue(RepairTool repairTool) {
		if (null == repairTool.getProcUseNum()) {
			repairTool.setProcUseNum(repairTool.getPlanUseNum());              //设置实际使用数量	
		}
		
	}
}
