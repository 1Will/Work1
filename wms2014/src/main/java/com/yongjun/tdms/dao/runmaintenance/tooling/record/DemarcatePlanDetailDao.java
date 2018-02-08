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
package com.yongjun.tdms.dao.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlanDetail;

/**
 * @author qs
 * @version $Id: DemarcatePlanDetailDao.java 8881 2007-12-02 03:05:28Z qsun $
 */
public interface DemarcatePlanDetailDao {
    DemarcatePlanDetail loadDemarcatePlanDetail(Long demarcatePlanDetailId);
	
	List<DemarcatePlanDetail> loadAllDemarcatePlanDetails(Long [] demarcatePlanDetailIds);
	
	void storeDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail);
	
	void deleteDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail);
	
	void deleteAllDemarcatePlanDetail(Collection<DemarcatePlanDetail> DemarcatePlanDetails);
}
