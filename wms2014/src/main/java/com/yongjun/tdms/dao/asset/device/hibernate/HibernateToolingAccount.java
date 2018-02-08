package com.yongjun.tdms.dao.asset.device.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.ToolingAccountDao;
import com.yongjun.tdms.model.asset.device.ToolingAccount;

public class HibernateToolingAccount extends BaseHibernateDao implements ToolingAccountDao{
	
	@SuppressWarnings("unchecked")
	public List<ToolingAccount> loadAllToolingAccountByToolingID(final Long id) {
		return (List<ToolingAccount>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
					       throws HibernateException, SQLException {
						return session.getNamedQuery("GetToolingAccountList")
						.setParameter("id", id).list();
					}
				});
	}
}
