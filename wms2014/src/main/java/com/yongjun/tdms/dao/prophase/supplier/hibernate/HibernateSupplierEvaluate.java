package com.yongjun.tdms.dao.prophase.supplier.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierEvaluateDao;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;

public class HibernateSupplierEvaluate extends BaseHibernateDao implements SupplierEvaluateDao {

	public SupplierEvaluate loadSupplierEvaluate(Long supplierEvaluateId) {
		
		return this.load(SupplierEvaluate.class,supplierEvaluateId);
	}

	public List<SupplierEvaluate> loadSupplierEvaluates(Long[] supplierEvaluateIds) {
	
		return this.loadAll(SupplierEvaluate.class,supplierEvaluateIds);
	}

	public List<SupplierEvaluate> loadSupplierEvaluates() {
	
		return this.loadAll(SupplierEvaluate.class);
	}

	public void storeSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
		this.store(supplierEvaluate);
		
	}

	public void deleteSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
		this.delete(supplierEvaluate);
		
	}

	public void deleteAllSupplierEvaluates(Collection<SupplierEvaluate> supplierEvaluates) {
		this.deleteAll(supplierEvaluates);
		
	}
	@SuppressWarnings("unchecked")
	public List<SupplierEvaluate> loadAllSupplierEvaluateBySupplier(final Supplier supplier) {
		return (List<SupplierEvaluate>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadAllSupplierEvaluateBySupplier")
								.setParameter("supplierId", supplier.getId()).list();
					}
				});
	}

}
