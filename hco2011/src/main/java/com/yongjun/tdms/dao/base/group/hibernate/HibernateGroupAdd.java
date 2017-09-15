package com.yongjun.tdms.dao.base.group.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.tdms.dao.base.group.GroupAddDao;

public class HibernateGroupAdd extends BaseHibernateDao implements GroupAddDao{
	
	public void deleteAllGroups(List<Group> groups){
		for(int i = 0;i<groups.size();i++){
			groups.get(i).setParentGroup(null);
			super.store(groups.get(i));
		}
		getHibernateTemplate().clear();
		deleteAll(groups);
	}
}
