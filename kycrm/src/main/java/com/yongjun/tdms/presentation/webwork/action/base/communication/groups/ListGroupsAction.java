package com.yongjun.tdms.presentation.webwork.action.base.communication.groups;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.DomainModel;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.pluto.model.security.GroupType;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.group.GroupAddManager;

public class ListGroupsAction extends ValueListAction {
	private static final long serialVersionUID = -409495626512211689L;
	protected final GroupManager groupManager;
	protected final GroupAddManager groupAddManager;
	private List<Group> groups;
	private List<Group> groupList;

	public ListGroupsAction(GroupManager groupManager,GroupAddManager groupAddManager) {
		this.groupManager = groupManager;
		this.groupAddManager = groupAddManager;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public void prepare() throws Exception {
		if ((this.groups == null) && (hasIds("groupIds")))
			this.groups = this.groupManager.loadAllGroups(getIds("groupIds"));
	}

	private String delete() {
		 for (Group g : this.groups) {
		 if ((null != g.getChildGroups()) && (!g.getChildGroups().isEmpty()))
		 {
		 addActionMessage(getText("groups.delete.has"));
		 return SUCCESS;
		 }
		 }
		try {
			this.groupAddManager.deleteAllGroups(this.groups);
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("groups.delete.error"));
			return ERROR;
		}
		
		getLogger().logStore((DomainModel) this.groups.get(0), "(名称:" + logContentGroups(this.groups) + ")被删除",
				"communications_group");

		addActionMessage(getText("groups.delete.success"));
		return SUCCESS;
	}

	private String logContentGroups(List<Group> groups2) {
		Integer index = null;
		String strGroup = "";
		for (Group g : groups2) {
			strGroup = strGroup + g.getName() + ",";
		}
		index = Integer.valueOf(strGroup.lastIndexOf(","));
		strGroup = strGroup.substring(0, index.intValue());
		return strGroup;
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if ("-1".equals(request.getParameter("parent.group"))) {
			map.remove("parent.group");
		}
		return map;
	}

	protected String getAdapterName() {
		return "communicationGroups";
	}

	public List<Group> getParentGroups() {
		List groups = this.groupManager.createSelectGroupsByGroupType(getText("select.option.non"),
				GroupType.COMMUNICATION_GROUP);
		Group group = new Group();
		group.setId(Long.valueOf(-1L));
		group.setName(getText("select.option.all"));
		groups.add(0, group);
		return groups;
	}
}
