package com.yongjun.tdms.dao.runmaintenance.calibration;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetailView;


public interface CalibrationDetailViewDao {
	public List<CalibrationDetailView> loadAllCalibrationDetailView(String[] array) throws HibernateException;

}
