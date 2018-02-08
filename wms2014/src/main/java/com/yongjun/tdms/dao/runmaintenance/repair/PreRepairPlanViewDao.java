package com.yongjun.tdms.dao.runmaintenance.repair;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanView;

public interface PreRepairPlanViewDao {

	public List<PreRepairPlanView> loadAllPreRepairPlanView(String[] array) throws HibernateException;
}
