package com.yongjun.tdms.dao.prophase.supplier.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierEvaluateViewDao;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateView;


public class HibernateSupplierEvaluateView extends BaseHibernateDao implements SupplierEvaluateViewDao{
	@SuppressWarnings("unchecked")
	public List<SupplierEvaluateView> loadSupplierEvaluateView(final Long supplierEvaluateId) {
		return (List<SupplierEvaluateView>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetsupplierEvaluateBySupplierEvaluateid")
								.setParameter("supplierEvaluateId", supplierEvaluateId).list();
					}
				});
		
	}

}
