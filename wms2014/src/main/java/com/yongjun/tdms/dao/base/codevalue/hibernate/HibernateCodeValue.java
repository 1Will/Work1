package com.yongjun.tdms.dao.base.codevalue.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.codevalue.CodeValueDao;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * @author qs
 * @version $Id: HibernateCodeValue.java 11319 2008-03-14 08:25:24Z wzou $
 */
public class HibernateCodeValue extends BaseHibernateDao implements
		CodeValueDao {

	@SuppressWarnings("unchecked")
	public List<CodeValue> loadAllValuesByCodeId(final Long id) {
		return (List<CodeValue>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllValuesByCodeId")
								.setParameter("id", id).list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Long> loadAllIdsByCodeId(final Long id) {
		return (List<Long>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllIdsByCodeId")
								.setParameter("id", id).list();
					}
				});
	}
	
	public CodeValue loadCodeValue(Long id) {
		return this.load(CodeValue.class, id);
	}

	@SuppressWarnings("unchecked")
	public CodeValue loadCodeTypeByCode(final String code) {
		return (CodeValue) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetCodeTypeByCode")
								.setParameter("code", code).uniqueResult();
					}
				});
	}

	public CodeValue loadCodeTypeByRealCode( final String realCode) {
		return (CodeValue) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
					       throws HibernateException, SQLException {
						return session.getNamedQuery("GetCodeTypeByRealCode")
						.setParameter("realCode", realCode).uniqueResult();
					}
				});
	}
	
	public CodeValue loadCodeTypeByReferCode( final String referCode) {
		return (CodeValue) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
					       throws HibernateException, SQLException {
						return session.getNamedQuery("GetSpareTypeByReferCode")
						.setParameter("referCode", referCode).uniqueResult();
					}
				});
	}
	
	
	public int getCodeValueByValue(final String codeValue){
		return (Integer)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Integer doInHibernate(Session session)
						throws HibernateException , SQLException{
						return ((Integer)session.getNamedQuery("GetCodeValueByValue")
						.setParameter("codeValue", codeValue).iterate().next()).intValue();
					}
				});
	}

	public int getcodeValueDetailByValueCount(final String codeValue,final Long id) {
		return (Integer)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Integer doInHibernate(Session session)
						throws HibernateException , SQLException {
						return ((Integer)session.getNamedQuery("GetcodeValueDetailByValue")
						.setParameter("codeValue", codeValue).setParameter("id", id)
						.iterate().next()).intValue();
					}
				});
	}
	
	public void storeCodeValue(CodeValue codeValue) {
		this.store(codeValue);
	}

	public List<CodeValue> loadAllValues() {
		return this.loadAll(CodeValue.class);
	}

	public void deleteAllCodeValue(Collection<CodeValue> codeValueDetails) {
		this.deleteAll(codeValueDetails);
	}

	public List<CodeValue> loadAllValues(Long[] codeValueDetailIds) {
		return this.loadAll(CodeValue.class,codeValueDetailIds);
	}


}
