package com.yongjun.tdms.dao.prophase.supplier.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierDao;
import com.yongjun.tdms.model.prophase.supplier.Supplier;

/**
 * <p>Title: HibernateSupplier
 * <p>Description: 供应商据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.prophase.supplier.SupplierDao
 * @version $Id:$
 */
public class HibernateSupplier extends BaseHibernateDao implements SupplierDao {

	public void deleteSupplier(Supplier supplier) {
		this.delete(supplier);
	}

	public void deleteAllSuppliers(Collection<Supplier> supplierIds) {
		this.deleteAll(supplierIds);
	}

	public void storeSupplier(Supplier supplier) {
		this.store(supplier);
	}

	public List<Supplier> loadAllSupplier(Long[] supplierIds) {
		return this.loadAll(Supplier.class, supplierIds);
	}

	public Supplier loadSupplier(Long supplierIds) {
		return this.load(Supplier.class, supplierIds);
	}
	
	public List<Supplier> loadAllSupplier(){
		return this.loadAll(Supplier.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Supplier> loadAllExternalHelpSupplier(final Long supplierCategoryId) {
		return (List<Supplier>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadAllExternalHelpSupplier")
								.setParameter("supplierCategoryId", supplierCategoryId).list();
					}
				});
	}

	public Supplier getSupplierByCode(final String code) {
		return (Supplier)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSupplierByCode")
								.setParameter("code",code).uniqueResult();
					}
				});
	}

	public String getMaxSupplierNoBySupplierCode(final String code) {
		return (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetMaxSupplierNoBySupplierCode")
								.setParameter("code", code).uniqueResult();
					}
				});
	}

	public List loadSuppliersByName(final String name) {
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadSuppliersByName")
								.setParameter("name", name+"%").setParameter("category", "FACTORY").list();
					}
				});
	}

	public Supplier loadSupplierByName(final String name) {
		return (Supplier)this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadSupplierByName")
								.setParameter("name", name).setParameter("category", "FACTORY").uniqueResult();
					}
				});
	}
}
