package com.yongjun.tdms.presentation.webwork.action.base.communication.groups;

import com.yongjun.pluto.dao.security.UserDao;
import com.yongjun.pluto.log.service.BusinessLogger;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.pluto.model.security.GroupType;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

public class EditGroupAction extends PrepareAction {
	private static final long serialVersionUID = -6215043842938356415L;
	protected final GroupManager groupManager;
	protected final UserManager userManager;
	private final UserDao userDao;
	private User user;
	private List<User> users;
	private Group group;
	private List<User> disUsers = new ArrayList();

	public EditGroupAction(GroupManager groupManager, UserManager userManager, UserDao userDao) {
		this.groupManager = groupManager;
		this.userManager = userManager;
		this.userDao = userDao;
	}

	public String save() throws IOException {
		if (isDelete()) {
			return delete();
		}

		if (isJoin()) {
			if (hasIds("availableUserIds")) {
				String availableUserIds = this.request.getParameter("availableUserIds");
				String[] availableUserId = availableUserIds.split(",");
				this.groupManager.joinUsersForGroup(availableUserId, this.group);
			}

			getLogger().logStore(this.group,
					"(名称:[" + this.group.getName() + "]有用户[" + logContentUserFromGroup() + "]加入)",
					"communications_group");
			return "new";
		}

		if (isLeave()) {
			if (hasIds("grantedUserIds")) {
				String grantedUserIds = this.request.getParameter("grantedUserIds");
				String[] grantedUserId = grantedUserIds.split(",");
				Long[] result = new Long[grantedUserId.length];
				for (int i = 0; i < grantedUserId.length; i++) {
					result[i] = Long.valueOf(grantedUserId[i]);
				}
				this.users = this.userManager.loadAllUsers(result);
			}
			return leave();
		}

		boolean isNew = this.group.isNew();
		boolean isChangParentGroup = false;

		if ((hasId("parent.group")) && (!this.request.getParameter("parent.group").equals("0"))) {
			Group newParentGroup = this.groupManager.loadGroup(getId("parent.group"));
			if (!newParentGroup.equals(this.group.getParentGroup())) {
				isChangParentGroup = true;
			}
			this.group.setParentGroup(newParentGroup);
		} else {
			this.group.setParentGroup(null);
		}
		this.group.setGroupType(GroupType.COMMUNICATION_GROUP);
		this.groupManager.storeGroup(this.group, isChangParentGroup);

		String logContent = "";
		if (isNew)
			logContent = "被添加";
		else {
			logContent = "被修改";
		}
		getLogger().logStore(this.group,
				"(编码:" + this.group.getCode() + ";名称:" + this.group.getName() + ")" + logContent,
				"communications_group");

		if (isNew) {
			addActionMessage(getText("group.add.success", Arrays.asList(new Object[] { this.group.getName() })));

			return "new";
		}
		addActionMessage(getText("group.edit.success", Arrays.asList(new Object[] { this.group.getName() })));

		return "success";
	}

	private String logContentUserFromGroup() {
		String strUser = "";
		Integer index = null;
		Set<User> users = this.group.getUsers();
		for (User user : users) {
			strUser = strUser + user.getName() + ",";
		}
		index = Integer.valueOf(strUser.lastIndexOf(","));
		strUser = strUser.substring(0, index.intValue());
		return strUser;
	}

	private boolean isGrantRole() {
		return hasKey("grant_role");
	}

	private boolean isRevokeRole() {
		return hasKey("revoke_role");
	}

	private boolean isJoin() {
		if (this.request.getParameter("join").equals("join")) {
			return true;
		}
		return false;
	}

	private boolean isLeave() {
		return hasKey("leave");
	}

	private String delete() {
		this.groupManager.deleteGroup(this.group);
		getLogger().logStore(this.group, "(编码:" + this.group.getCode() + ";名称:" + this.group.getName() + ")被删除",
				"communications_group");
		addActionMessage(getText("group.delete.success", Arrays.asList(new Object[] { this.group.getName() })));

		return "deleteSucess";
	}

	public String join() {
		this.group.addUser(this.user);
		this.groupManager.storeGroup(this.group);
		addActionMessage(getText("group.join.success", Arrays.asList(new Object[] { this.group.getName() })));
		return "new";
	}

	public String leave() {
		String strUser = "";
		for (User user : this.users) {
			this.group.removeUser(user);
			strUser = strUser + user.getName() + ",";
		}
		Integer index = null;
		index = Integer.valueOf(strUser.lastIndexOf(","));
		strUser = strUser.substring(0, index.intValue());
		getLogger().logStore(this.group, "(名称:[" + this.group.getName() + "]有用户[" + strUser + "]离开)",
				"communications_group");

		this.groupManager.storeGroup(this.group);
		addActionMessage(getText("group.leave.success", Arrays.asList(new Object[] { this.group.getName() })));

		return "new";
	}

	public void prepare() throws Exception {
		if (this.group == null) {
			if (hasId("group.id")) {
				this.group = this.groupManager.loadGroup(getId("group.id"));

				List list = this.groupManager.displaySortUser(this.group.getId());
				for (int i = 0; i < list.size(); i++) {
					this.user = this.userManager.loadUser((Long) list.get(i));
					this.disUsers.add(this.user);
				}
			} else {
				this.group = new Group();
			}
		}

		if ((this.user == null) && (hasId("user.id"))) {
			this.user = this.userManager.loadUser(getId("user.id"));
		}

		if ((this.users == null) && (hasIds("userIds")))
			this.users = this.userManager.loadAllUsers(getIds("userIds"));
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<User> getJoinableUsers() {
		List users = this.userManager.loadAllUsers();
		users.removeAll(this.group.getUsers());
		return users;
	}

	public List<Group> getParentGroups() {
		List groups = this.groupManager.createSelectGroupsByGroupType(getText("select.option.non"),
				GroupType.COMMUNICATION_GROUP);
		if ((null != this.group) && (!this.group.isNew())) {
			groups.remove(this.group);
		}
		return groups;
	}

	@Transactional
	public void move() {
		Long upLayerUserId = Long.valueOf(this.request.getParameter("id"));

		Long presentUserId = Long.valueOf(this.request.getParameter("id1"));
		Long groupid = Long.valueOf(this.request.getParameter("groupid"));
		this.groupManager.exchangeSortIdx(upLayerUserId, presentUserId, groupid);
	}

	public void downMove() {
		Long presentUserId = Long.valueOf(this.request.getParameter("id"));

		Long downUserId = Long.valueOf(this.request.getParameter("id1"));
		Long groupid = Long.valueOf(this.request.getParameter("groupid"));
		this.groupManager.downExchangeSortIdx(presentUserId, downUserId, groupid);
	}

	public List<User> getDisUsers() {
		return this.disUsers;
	}

	public void setDisUsers(List<User> disUsers) {
		this.disUsers = disUsers;
	}
}
