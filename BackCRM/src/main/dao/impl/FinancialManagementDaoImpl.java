package main.dao.impl;

import main.dao.FinancialManagementDao;
import main.pojo.FinancialManagement;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class FinancialManagementDaoImpl extends HibernateDaoSupport implements FinancialManagementDao {
	

	@Override
	public FinancialManagement getFinancialManagementById(Long id) {
		FinancialManagement fManagement = null;
		try {
			fManagement=(FinancialManagement) this.getSession().load(FinancialManagement.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return fManagement;
	}
	
	@Override
	public void updateFinancialManagementById(FinancialManagement fManagement) {
	     try {
			this.getHibernateTemplate().merge(fManagement);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}


    
	
}
