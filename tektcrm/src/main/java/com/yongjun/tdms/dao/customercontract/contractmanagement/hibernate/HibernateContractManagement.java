package com.yongjun.tdms.dao.customercontract.contractmanagement.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.ContractManagementDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class HibernateContractManagement extends BaseHibernateDao implements ContractManagementDao {
	public void storeContractManagement(ContractManagement t) {
		store(t);
	}

	public ContractManagement loadContractManagement(Long id) {
		return (ContractManagement) load(ContractManagement.class, id);
	}

	public List<ContractManagement> loadContractManagement() {
		
		return getHibernateTemplate().find("from ContractManagement where disabled =0  order by ciemdinghTime desc  ");
	}
	
	public List<ContractManagement> loadContractManagementByPj(String pjIds) {
		return getHibernateTemplate().find("from ContractManagement as c where c.disabled =0 and c.project.id in (" +pjIds +") order by c.ciemdinghTime desc  ");
	}
	
	public List<ContractManagement> loadContractManagementByBType(String type) {
		return getHibernateTemplate().find("from ContractManagement as c where c.disabled =0 and c.customerInfo.businessType.name like '" +type +"' order by c.ciemdinghTime desc  ");
	}

	public List<ContractManagement> loadAllContractManagement(Long[] tIds) {
		return loadAll(ContractManagement.class, tIds);
	}

	public void deleteContractManagement(ContractManagement t) {
		delete(t);
	}

	public void deleteAllContractManagement(List<ContractManagement> ts) {
		deleteAll(ts);
	}

	public List<ContractManagement> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(ContractManagement.class, key, value);
	}

	public List<ContractManagement> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ContractManagement.class, keyNames, keyValues);
	}

	public String getMaxPFCode(String code) {
		String hql = "select c.code from ContractManagement as c where  c.code like '%" + code + "%'";
		List codeList = getSession().createQuery(hql).list();
		if (null!=codeList && codeList.size() > 0) {
			List items = new ArrayList();
			for (int i = 0; i < codeList.size(); i++) {
				String item = ((String) codeList.get(i)).substring(((String) codeList.get(i)).lastIndexOf("-") + 1);
				items.add(item);
			}
			Collections.sort(items);
			return (String) items.get(items.size() - 1);
		}
		return null;
	}

	public double getSumReturnPrice(long id) throws DaoException {
		String hql = "select sum(c.sum) from ReturnPlan as c where c.contractManagement.id=" + id;
		List codeList = getSession().createQuery(hql).list();

		if ((null != codeList) && (null!= codeList && codeList.size() > 0)) {
			Double item = (Double) codeList.get(0);
			if (null == item) {
				return 0.0D;
			}
			return item.doubleValue();
		}
		return 0.0D;
	}
	public HashMap getDataMap(String staDate,String endDate){
		HashMap map =new HashMap();
		Session session =getSession();
		String hqlCount ="select count(*) from ContractManagement where ciemdinghTime >= CAST('"+staDate+"' AS DATETIME )  AND ciemdinghTime <= CAST('"+endDate+"' AS DATETIME )  ";
		List countList =session.createQuery(hqlCount).list();
		if ((null != countList) && (countList.size() > 0)) {
			Integer object  =  (Integer) countList.get(0);
		map.put("count",object);
		}
		String hqlMoney ="select isnull(sum(c.contractMoney),0) from ContractManagement c where c.ciemdinghTime >= CAST('"+staDate+"' AS DATETIME )  AND ciemdinghTime <= CAST('"+endDate+"' AS DATETIME )  ";
		List moneytList =session.createQuery(hqlMoney).list();
		if ((null != moneytList) && (moneytList.size() > 0)) {
			Double object  =  (Double) moneytList.get(0);
		map.put("money", object);
		}
		return map;
		
	}
}
