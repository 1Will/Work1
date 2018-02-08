package com.yongjun.tdms.service.base.group;

import java.util.List;

import com.yongjun.pluto.model.security.Group;


public abstract interface GroupAddManager {
	 void deleteAllGroups(List<Group> groups);
}
