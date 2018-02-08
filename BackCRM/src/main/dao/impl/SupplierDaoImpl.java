package main.dao.impl;

import main.dao.SupplierDao;
import main.pojo.Supplier;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class SupplierDaoImpl extends HibernateDaoSupport implements SupplierDao {
	

	@Override
	public Supplier getSupplierById(Long id) {
		Supplier supplier  = null;
		try {
			supplier=(Supplier) this.getSession().load(Supplier.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return supplier;
	}
	
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}
	
    
	
}
