package com.yongjun.tdms.dao.base.codevalue.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.codevalue.SpareDetailTypeDao;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;

public class HibernateSpareDetailType extends BaseHibernateDao implements SpareDetailTypeDao{

	public SpareDetailType loadSpareDetailTypeByCode(String code) {
		return null;
	}

	public SpareDetailType loadSpareDetailType(Long id) {
		return this.load(SpareDetailType.class, id);
	}

	public List<SpareDetailType> loadAllSpareDetailTypes() {
		return this.loadAll(SpareDetailType.class);
	}

	public void storeSpareDetailType(SpareDetailType spareDetailType) {
		this.store(spareDetailType);
	}

	public void deleteSpareDetailType(SpareDetailType spareDetailType) {
		this.delete(spareDetailType);
	}

	public void deleteAllSpareDetailType(List<SpareDetailType> list) {
		this.deleteAll(list);
	}

	public List<SpareDetailType> loadAllSpareDetailTypes(Long[] ids) {
		return this.loadAll(SpareDetailType.class,ids);
	}

	public List loadSpareDetailBySpareTypeId(Long value) {
		String hql = "select s.id,s.name from SpareDetailType as s where s.spareType.id=" + value;
		return this.getSession().createQuery(hql).list();
	}

}
