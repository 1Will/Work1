package com.yongjun.tdms.dao.base.group;

import java.util.List;

import com.yongjun.pluto.model.security.Group;

public interface GroupAddDao {
	void deleteAllGroups(List<Group> groups);
}
