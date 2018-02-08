package com.yongjun.tdms.dao.runmaintenance.calibration;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;

public interface CalibrationDao {
	List<Calibration>  loadAllCalibrations(Long[] calibrationIds);
	
	@Transactional
	void deleteCalibrations(Calibration calibration);
	
	@Transactional
	void deleteAllCalibrations(Collection<Calibration> calibrations);
	
	@Transactional
	void storeCalibration(Calibration calibration);
	
	public Calibration loadCalibration(Long calibrationId);
	
	public List<Calibration> loadCalibrationByPlanId(Long id);
}
