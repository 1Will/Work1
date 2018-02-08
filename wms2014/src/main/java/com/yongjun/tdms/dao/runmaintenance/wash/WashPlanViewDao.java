package com.yongjun.tdms.dao.runmaintenance.wash;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.wash.WashPlanView;


public interface WashPlanViewDao {
	public List<WashPlanView> loadAllWashPlanView(String[] array) throws HibernateException;

}
