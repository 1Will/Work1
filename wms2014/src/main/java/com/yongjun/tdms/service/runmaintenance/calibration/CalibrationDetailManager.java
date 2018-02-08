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
package com.yongjun.tdms.service.runmaintenance.calibration;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;

/**
 * <p>Title: CalibrationDetailManager
 * <p>Description: 工装标定明细接口访问定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface CalibrationDetailManager {
	List<CalibrationDetail> loadAllCalibrationDetails(Long[] calibrationDetailIds);
	
	CalibrationDetail loadCalibrationDetail(Long calibrationDetailId);
	
	@Transactional
	void storeCalibrationDetail(CalibrationDetail calibrationDetail);
	
	@Transactional
	void storeCalibrationDetail(Calibration calibration,String addToolingIds);
	
	@Transactional
	void storeCalibrationPlanDetail(String allCalibrationDetailDutyPeople,
			String allDate,
			String allCalibrationDetailId);
	
	@Transactional
	void storeCalibrationProcDetail(String allResult,
			String allDate,
			String allCalibrationDetailId,
			String allCalibrationResult);
	
	@Transactional
	void storeCalibrationProcDetail(String allCalibrationDetailResult,
			String allCalibrationDetailId);
	
	@Transactional
	void deleteAllcalibrationDetails(Collection<CalibrationDetail> calibrationDetails)throws Exception;
}
