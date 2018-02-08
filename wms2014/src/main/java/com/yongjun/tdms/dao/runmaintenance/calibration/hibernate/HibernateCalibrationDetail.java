package com.yongjun.tdms.dao.runmaintenance.calibration.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.calibration.CalibrationDetailDao;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;

public class HibernateCalibrationDetail  extends BaseHibernateDao implements CalibrationDetailDao{

	public List<CalibrationDetail> loadAllCalibrationDetails(Long[] calibrationDetailIds) {
		return this.loadAll(CalibrationDetail.class,calibrationDetailIds);
	}

	public CalibrationDetail loadCalibrationDetail(Long calibrationDetailId) {
		return this.load(CalibrationDetail.class,calibrationDetailId);
	}

	public void storeCalibrationDetail(CalibrationDetail calibrationDetail) {
		this.store(calibrationDetail);
	}

	public void deleteAllcalibrationDetails(Collection<CalibrationDetail> calibrationDetails) throws Exception {
		this.deleteAll(calibrationDetails);
	}

}
