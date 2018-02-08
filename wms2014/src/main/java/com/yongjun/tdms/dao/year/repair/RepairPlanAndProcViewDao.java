package com.yongjun.tdms.dao.year.repair;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.year.repair.RepairPlanAndProcView;

public interface RepairPlanAndProcViewDao {

	public List<RepairPlanAndProcView> loadAllRepairPlanAndProcView(String[] array) throws HibernateException;
}
