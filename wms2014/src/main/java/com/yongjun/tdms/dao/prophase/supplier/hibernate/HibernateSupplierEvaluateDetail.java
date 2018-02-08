package com.yongjun.tdms.dao.prophase.supplier.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierEvaluateDetailDao;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateDetail;

public class HibernateSupplierEvaluateDetail extends BaseHibernateDao implements SupplierEvaluateDetailDao {

	public SupplierEvaluateDetail loadSupplierEvaluateDetail(Long SupplierEvaluateDetailId) {
		
		return this.load(SupplierEvaluateDetail.class,SupplierEvaluateDetailId);
	}

	public List<SupplierEvaluateDetail> loadSupplierSupplierEvaluateDetails(Long[] SupplierEvaluateDetailIds) {
		
		return this.loadAll(SupplierEvaluateDetail.class,SupplierEvaluateDetailIds);
	}

	public List<SupplierEvaluateDetail> loadSupplierEvaluateDetails() {
	
		return this.loadAll(SupplierEvaluateDetail.class);
	}

	public void storeSupplierEvaluateDetail(SupplierEvaluateDetail supplierEvaluateDetail) {
	            this.store(supplierEvaluateDetail);
		
	}

	public void deleteSupplierEvaluateDetail(SupplierEvaluateDetail supplierEvaluateDetail) {
		this.delete(supplierEvaluateDetail);
		
	}

	public void deleteAllSupplierEvaluateDetails(Collection<SupplierEvaluateDetail> SupplierEvaluateDetails) {
		  this.deleteAll(SupplierEvaluateDetails);
		
	}

	public SupplierEvaluateDetail loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(final Long supplierEvaluateId, final String gradeFlag) {
		return (SupplierEvaluateDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag")
								.setParameter("supplierEvaluateId", supplierEvaluateId)
								.setParameter("flag", gradeFlag)
								.uniqueResult();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List<SupplierEvaluateDetail> loadSupplierEvaluate(final Long supplierEvaluateId) {
		return (List<SupplierEvaluateDetail>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadSupplierEvaluateDetailBySupplierEvaluateId")
								.setParameter("supplierEvaluateId", supplierEvaluateId)
								.list();
					}
				});
	}
	
}
