package com.yongjun.tdms.dao.runmaintenance.maintenance;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetailView;
public interface MaintenanceDetailViewDao {
	
	public List<MaintenanceDetailView> loadAllMaintenanceDetailView(String[] array) throws HibernateException;
}
