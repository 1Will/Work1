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
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.DemarcatePlanDao;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlanDetail;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcateStatus;
import com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanManager;

/**
 * <p>Title: DefaultToolingChangeBillManager
 * <p>Description: 工装标定计划管理业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanManager
 */
public class DefaultDemarcatePlanManager implements DemarcatePlanManager{
	private final DemarcatePlanDao demarcatePlanDao;
	private final SequenceManager sequenceManager;
	
	  public DefaultDemarcatePlanManager(DemarcatePlanDao demarcatePlanDao,
			  SequenceManager sequenceManager){
		  this.demarcatePlanDao = demarcatePlanDao;
		  this.sequenceManager = sequenceManager;
	  }
	  
	  public DemarcatePlan loadDemarcatePlan(Long demarcatePlanId){
		  return demarcatePlanDao.loadDemarcatePlan(demarcatePlanId);
	  }
		
	  public List<DemarcatePlan> loadAllDemarcatePlans(Long [] demarcatePlanIds){
		  return demarcatePlanDao.loadAllDemarcatePlans(demarcatePlanIds);
	  }
		

	  public void deleteDemarcatePlan(DemarcatePlan demarcatePlan){
		  demarcatePlanDao.deleteDemarcatePlan(demarcatePlan);
	  }
		
	  public void deleteAllDemarcatePlan(Collection<DemarcatePlan> demarcatePlans) throws Exception{
		  for (DemarcatePlan plan : demarcatePlans) {
			  if (plan.getPlanStatus().equals(DemarcateStatus.IMPLEMENTING)) {
				  throw new Exception();
			  }
		  }
		  demarcatePlanDao.deleteAllDemarcatePlan(demarcatePlans);
		}
		
	  public void storeDemarcatePlan(DemarcatePlan demarcatePlan){
		  if (demarcatePlan.isNew()) {
			  String planNo = (String)this.sequenceManager.generate("-");
			  demarcatePlan.setPlanNo(planNo);
			  demarcatePlan.setPlanStatus(DemarcateStatus.NONIMPLEMENT);
		  }
		  demarcatePlanDao.storeDemarcatePlan(demarcatePlan);
	  }

	public void updateDemarcatePlanStatus(Long demarcatePlanId) {
		/*
		 * 下面用来判断标定计划详细是否已全部实施、还是在实施中，还是未实施,用来更新标定计划的状态
		 */
		DemarcatePlan demarcatePlan = this.demarcatePlanDao.loadDemarcatePlan(demarcatePlanId);
		Iterator it = demarcatePlan.getDemarcateDetails().iterator();
		int demarcateResultIsNotNullCount = 0;
		while (it.hasNext()) {
			DemarcatePlanDetail detail = (DemarcatePlanDetail)it.next();
			if (null != detail.getDemarcateResult()) {
				demarcatePlan.setPlanStatus(DemarcateStatus.IMPLEMENTING);
				demarcateResultIsNotNullCount++;
			}
		}
		if ((demarcateResultIsNotNullCount == demarcatePlan.getDemarcateDetails().size()) && (demarcateResultIsNotNullCount != 0)) {
			demarcatePlan.setPlanStatus(DemarcateStatus.IMPLEMENTED);
		} else if (demarcateResultIsNotNullCount == 0){
			demarcatePlan.setPlanStatus(DemarcateStatus.NONIMPLEMENT);
		}
		this.demarcatePlanDao.storeDemarcatePlan(demarcatePlan);		
	}
}
