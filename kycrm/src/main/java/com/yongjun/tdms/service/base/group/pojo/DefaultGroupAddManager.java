package com.yongjun.tdms.service.base.group.pojo;

import java.util.List;

import com.yongjun.pluto.model.security.Group;
import com.yongjun.tdms.dao.base.group.GroupAddDao;
import com.yongjun.tdms.service.base.group.GroupAddManager;

public class DefaultGroupAddManager implements GroupAddManager{
	protected final GroupAddDao groupAddDao;

	public DefaultGroupAddManager(GroupAddDao groupAddDao) {
		this.groupAddDao = groupAddDao;
	}
	
	public void deleteAllGroups(List<Group> groups) {
		groupAddDao.deleteAllGroups(groups);
	}
}
