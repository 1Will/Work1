package com.yongjun.tdms.dao.runmaintenance.lubrication;


import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanView;

public interface LubricationPlanViewDao {

	public List<LubricationPlanView> loadAllLubricationPlanView(String[] array) throws HibernateException;		
}
