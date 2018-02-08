/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.runmaintenance.tooling.record.pojo;


import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.DemarcatePlanDetailDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlanDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanManager;

/**
 * <p>Title: DefaultToolingChangeBillManager
 * <p>Description: 工装标定计划明细管理业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanDetailManager
 */
public class DefaultDemarcatePlanDetailManager implements
		DemarcatePlanDetailManager {
	private final DemarcatePlanDetailDao demarcatePlanDetailDao;
	private final DeviceCardManager deviceCardManager;
	private final UserManager userManager;
	private final DemarcatePlanManager demarcatePlanManager;

	public DefaultDemarcatePlanDetailManager(
			DemarcatePlanDetailDao demarcatePlanDetailDao,
			DeviceCardManager deviceCardManager,
			UserManager userManager,
			DemarcatePlanManager demarcatePlanManager) {
		this.demarcatePlanDetailDao = demarcatePlanDetailDao;
		this.deviceCardManager = deviceCardManager;
		this.userManager = userManager;
		this.demarcatePlanManager = demarcatePlanManager;
	}

	public DemarcatePlanDetail loadDemarcatePlanDetail(
			Long demarcatePlanDetailId) {
		return demarcatePlanDetailDao
				.loadDemarcatePlanDetail(demarcatePlanDetailId);
	}

	public List<DemarcatePlanDetail> loadAllDemarcatePlanDetails(
			Long[] demarcatePlanDetailIds) {
		return demarcatePlanDetailDao
				.loadAllDemarcatePlanDetails(demarcatePlanDetailIds);
	}

	public void deleteDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail) {
		demarcatePlanDetailDao.deleteDemarcatePlanDetail(demarcatePlanDetail);
	}

	public void deleteAllDemarcatePlanDetail(Long toolingDemarcatePlanId,
			Collection<DemarcatePlanDetail> demarcatePlanDetail) {
		demarcatePlanDetailDao.deleteAllDemarcatePlanDetail(demarcatePlanDetail);	
	}

	public void storeDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail) {
		demarcatePlanDetailDao.storeDemarcatePlanDetail(demarcatePlanDetail);
	}

	public void storeDemarcatePlanDetail(String storeDemarcateDetailString) {
		// TODO Auto-generated method stub
		
	}

	public void storeDemarcatePlanDetail(String newDemarcateRuleIds, DemarcatePlan demarcatePlan) {
		String [] newDemarcateRuleId = newDemarcateRuleIds.split(",");
		DeviceCard tooling = null;
		for (int i=0; i<newDemarcateRuleId.length; i++) {
			DemarcatePlanDetail planDetail = new DemarcatePlanDetail();
			tooling = this.deviceCardManager.loadDevice(Long.valueOf(newDemarcateRuleId[i]));
			if (null != tooling.getPreDemarcateTime()&& null != tooling.getDemarcateCycle()) {
				planDetail.setThisDemarcateDateTm(DateUtil.addMonthsToDate(tooling.getPreDemarcateTime(), tooling.getDemarcateCycle()));
			}
			planDetail.setDemarcatePlan(demarcatePlan);
			planDetail.setManager(tooling.getManager());
			planDetail.setTooling(tooling);
			this.demarcatePlanDetailDao.storeDemarcatePlanDetail(planDetail);
			demarcatePlan.getDemarcateDetails().add(planDetail);
			tooling.setPreDemarcateTime(planDetail.getThisDemarcateDateTm());
			this.deviceCardManager.storeTooling(tooling);
		}
		this.demarcatePlanManager.storeDemarcatePlan(demarcatePlan);
	}

	public void storeDemarcatePlamDetail(DemarcatePlan demarcaretePlan, 
			                          String allToolingDemarcateManager, 
			                          String allThisDemarcateDateTm, 
			                          String allDemarcateDetailComment) {
		String [] toolingDemarcateManagers = allToolingDemarcateManager.split(",");
		String [] thisDemarcateDateTms = allThisDemarcateDateTm.split(",");
		String [] demarcateDetailComments = null;
		if (null != allDemarcateDetailComment) {
			demarcateDetailComments = allDemarcateDetailComment.split(",");
		}
	
        DeviceCard tooling = null;
        DemarcatePlanDetail detail = null;
		for (int i=0,j=0; (i<toolingDemarcateManagers.length) && (j<thisDemarcateDateTms.length); i=i+2,j=j+2) {
			detail = this.demarcatePlanDetailDao.loadDemarcatePlanDetail(Long.valueOf(toolingDemarcateManagers[i]));
			if (null != allDemarcateDetailComment ) {
				int k;
				for (k=0; k<demarcateDetailComments.length; k=k+2) {
					if (toolingDemarcateManagers[i].equals(demarcateDetailComments[k])) {
						detail.setComment(demarcateDetailComments[k+1]);
						break;
					} 
				}
				if (k>=demarcateDetailComments.length) {
					detail.setComment(null);
				}
			} else {
				detail.setComment(null);
			}

			detail.setManager(this.userManager.loadUser(Long.valueOf(toolingDemarcateManagers[i+1])));
			detail.setThisDemarcateDateTm(DateUtil.toDate(thisDemarcateDateTms[j+1], "yyyy-MM-dd"));
			this.demarcatePlanDetailDao.storeDemarcatePlanDetail(detail);
			tooling = detail.getTooling();
			tooling.setPreDemarcateTime(detail.getThisDemarcateDateTm());
			this.deviceCardManager.storeTooling(tooling);
        }
	}

	public void storeDemarcatePlamDetailResult(Long toolingDemarcatePlanId, String allDemarcateResult) {
		String [] demarcateResults = null;
		if (null != allDemarcateResult) {  
			/*
			 * 该条件内的语句用来更新标定计划详细中标定结果的值
			 */
			demarcateResults = allDemarcateResult.split(",");
	        DemarcatePlanDetail detail = null;
			for (int i=0; i<demarcateResults.length; i=i+2) {
				detail = this.demarcatePlanDetailDao.loadDemarcatePlanDetail(Long.valueOf(demarcateResults[i]));
				if (demarcateResults[i+1].equals("")) {
					detail.setDemarcateResult(null);
				} else {
					detail.setDemarcateResult(demarcateResults[i+1]);
				}
				this.demarcatePlanDetailDao.storeDemarcatePlanDetail(detail);
			}
		}
		this.demarcatePlanManager.updateDemarcatePlanStatus(toolingDemarcatePlanId);

	}
}
