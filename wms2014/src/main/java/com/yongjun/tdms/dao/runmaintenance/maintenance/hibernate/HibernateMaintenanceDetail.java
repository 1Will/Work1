package com.yongjun.tdms.dao.runmaintenance.maintenance.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDetailDao;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;

public class HibernateMaintenanceDetail  extends BaseHibernateDao implements MaintenanceDetailDao{

	public List<MaintenanceDetail> loadAllMaintenanceDetails(Long[] maintenanceDetailIds) {
		return this.loadAll(MaintenanceDetail.class,maintenanceDetailIds);
	}

	public MaintenanceDetail loadMaintenanceDetail(Long maintenanceDetailId) {
		return this.load(MaintenanceDetail.class,maintenanceDetailId);
	}

	public void storeMaintenanceDetail(MaintenanceDetail maintenanceDetail) {
		this.store(maintenanceDetail);
	}

	public void deleteAllMaintenanceDetails(Collection<MaintenanceDetail> maintenanceDetails) {
		this.deleteAll(maintenanceDetails);
	}
	 
	@SuppressWarnings("unchecked")
	public MaintenanceDetail loadMaintenanceDetailBydeviceID(final Long id) {
		return (MaintenanceDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetdeviceIdLoadMaintenanceDetail")
								.setParameter("id", id).uniqueResult();
					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public MaintenanceDetail loadMaintenanceDetailBydeviceIDAndMonth(final Long id,final String month) {
		return (MaintenanceDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetdeviceIdAndMonthLoadMaintenanceDetail")
								.setParameter("id", id).setParameter("month",month).uniqueResult();
					}
				});
	}
	
}
