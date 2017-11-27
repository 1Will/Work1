package com.yongjun.tdms.dao.personnelFiles.personnel.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.personnelFiles.personnel.PersonnelFilesDao;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class HibernatePersonnelFiles extends BaseHibernateDao implements PersonnelFilesDao {
	public void storePersonnel(PersonnelFiles pf) {
		super.store(pf);
	}

	public void deletePersonnel(PersonnelFiles pf) {
		super.delete(pf);
	}

	public void deleteAllPersonnel(Collection<PersonnelFiles> pfs) {
		super.deleteAll(pfs);
	}

	public List<PersonnelFiles> loadAllPersonnel(Long[] pfIds) {
		return super.loadAll(PersonnelFiles.class, pfIds);
	}

	public PersonnelFiles loadPersonnel(Long pfId) {
		return (PersonnelFiles) super.load(PersonnelFiles.class, pfId);
	}

	public List<PersonnelFiles> loadAllPersonnel() {
		return super.loadAll(PersonnelFiles.class);
	}

	public List<PersonnelFiles> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(PersonnelFiles.class, keyName, keyValue);
	}

	public List<PersonnelFiles> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(PersonnelFiles.class, keyNames, keyValues);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getMaxPFCode(String code, Long orgId) {
		String hql = "select pf.code from PersonnelFiles as pf where pf.organization.id=" + orgId
				+ " and pf.code like '%" + code + "%'";
		List codeList = getSession().createQuery(hql).list();
		if (null != codeList && codeList.size() > 0) {
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
}
