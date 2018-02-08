package com.yongjun.tdms.dao.runmaintenance.calibration;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;

public interface CalibrationDetailDao {
List<CalibrationDetail> loadAllCalibrationDetails(Long[] calibrationDetailIds);
	
	CalibrationDetail loadCalibrationDetail(Long calibrationDetailId);
	
	@Transactional
	void storeCalibrationDetail(CalibrationDetail calibrationDetail);
	
	@Transactional
	void deleteAllcalibrationDetails(Collection<CalibrationDetail> calibrationDetails)throws Exception;
}
