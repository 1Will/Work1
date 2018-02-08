package main.dao.impl;

import main.dao.ContractManagementDao;
import main.pojo.ContractManagement;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ContractManagementDaoImpl extends HibernateDaoSupport implements ContractManagementDao {
	
	


	@Override
	public ContractManagement getContractManagementById(Long id) {
		ContractManagement cManagement = null;
		try {
			cManagement=(ContractManagement) this.getSession().load(ContractManagement.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return cManagement;
	}
	
	@Override
	public void updateContractManagementById(ContractManagement cManagement) {
		try {
			this.getHibernateTemplate().merge(cManagement);
		} catch (Exception e) {
		     e.printStackTrace();
		}
		
	}
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}


	
    
	
}
