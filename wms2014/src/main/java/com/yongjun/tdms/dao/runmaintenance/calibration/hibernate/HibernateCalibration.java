package com.yongjun.tdms.dao.runmaintenance.calibration.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.calibration.CalibrationDao;
import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;

public class HibernateCalibration  extends BaseHibernateDao implements CalibrationDao{

	public List<Calibration> loadAllCalibrations(Long[] calibrationIds) {
		return this.loadAll(Calibration.class,calibrationIds);
	}

	public void deleteAllCalibrations(Collection<Calibration> calibrations) {
		this.deleteAll(calibrations);
	}

	public void storeCalibration(Calibration calibration) {
		this.store(calibration);
	}

	public Calibration loadCalibration(Long calibrationId) {
		return this.load(Calibration.class,calibrationId);
	}

	public void deleteCalibrations(Calibration calibration) {
		this.delete(calibration);
	}
	
	public List<Calibration> loadCalibrationByPlanId(final Long id){
		return (List<Calibration>) this.getHibernateTemplate().execute(
			new HibernateCallback(){
				public Object doInHibernate(Session session)
			       throws HibernateException, SQLException {
				return session.getNamedQuery("GetCalibrationByPlanId").setParameter("id", id).uniqueResult();
			}
			}
		);
	}
}
