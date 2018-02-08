package com.yongjun.tdms.dao.runmaintenance.maintenance.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDao;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;

public class HibernateMaintenance  extends BaseHibernateDao implements MaintenanceDao{

	public List<Maintenance> loadAllMaintenances(Long[] maintenanceIds) {
		return this.loadAll(Maintenance.class,maintenanceIds);
	}

	public void deleteAllMaintenances(Collection<Maintenance> maintenances) {
		this.deleteAll(maintenances);
	}

	public void storeMaintenance(Maintenance maintenance) {
		this.store(maintenance);
	}

	public Maintenance loadMaintenance(Long maintenanceId) {
		return this.load(Maintenance.class,maintenanceId);
	}

	public void deleteMaintenance(Maintenance maintenance) {
		this.delete(maintenance);
	}

	public List<Maintenance> loadAllMaintenances() {
		return this.loadAll(Maintenance.class);
	}

	public Maintenance getMaintenanceByPlanNoAndProc(final String planNo) {
		return (Maintenance) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getMaintenceByPlanNoAndProc")
								.setParameter("planNo", planNo)
								.uniqueResult();
					}
				});
	}

}
