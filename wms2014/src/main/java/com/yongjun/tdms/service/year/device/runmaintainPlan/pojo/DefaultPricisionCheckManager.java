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
package com.yongjun.tdms.service.year.device.runmaintainPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.year.device.runmaintainPlan.PricisionCheckDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PricisionCheck;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.PricisionCheckManager;

/**
 * <p>Title: DefaultPricisionCheckManager
 * <p>Description: 运维计划的精度检查业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.runmaintainPlan.PricisionCheckManager
 * @version $Id:$
 */
public class DefaultPricisionCheckManager extends CalculateFeeManager implements PricisionCheckManager {

	private final PricisionCheckDao pricisionCheckDao;
	
	public DefaultPricisionCheckManager(PricisionCheckDao pricisionCheckDao) {
		this.pricisionCheckDao = pricisionCheckDao;
	}
	
	public PricisionCheck loadPricisionCheck(Long pricisionCheckId) {
		return this.pricisionCheckDao.loadPricisionCheck(pricisionCheckId);
	}

	public List<PricisionCheck> loadAllPricisionChecks(Long[] pricisionCheckIds) {
		return this.pricisionCheckDao.loadAllPricisionChecks(pricisionCheckIds);
	}

	public List<PricisionCheck> loadAllPricisionChecks() {
		return this.pricisionCheckDao.loadAllPricisionChecks();
	}

	public void storePricisionCheck(PricisionCheck pricisionCheck) {
		this.pricisionCheckDao.storePricisionCheck(pricisionCheck);
		//更新与其相关联的运维计划以及运维计划明细的费用
		this.calculatePricisionCheckFee(pricisionCheck.getRunmaintainPlanDetail());
	}

	public void deletePricisionCheck(PricisionCheck pricisionCheck) {
		this.pricisionCheckDao.deletePricisionCheck(pricisionCheck);
	}

	public void deleteAllPricisionChecks(RunmaintainPlanDetail runmaintainPlanDetail, Collection<PricisionCheck> pricisionChecks) {
		this.pricisionCheckDao.deleteAllPricisionChecks(pricisionChecks);
		for (PricisionCheck pricisionCheck : pricisionChecks) {
			runmaintainPlanDetail.getPricisionChecks().remove(pricisionCheck);
		}
		//更新与其相关联的运维计划以及运维计划明细的费用
		this.calculatePricisionCheckFee(runmaintainPlanDetail);
	}

}
