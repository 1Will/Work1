package com.yongjun.tdms.service.notice.pojo;

import com.yongjun.pluto.dao.security.GroupDao;
import com.yongjun.pluto.dao.security.UserDao;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.notice.ReceviceNoticeDao;
import com.yongjun.tdms.dao.notice.SendNoticeDao;
import com.yongjun.tdms.model.notice.SendNotice;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.notice.SendNoticeManager;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class DefaultSendNoticeManager extends BaseManager implements SendNoticeManager {
	private final SendNoticeDao sendNoticeDao;
	private final UserDao userDao;
	private final ReceviceNoticeDao receviceNoticeDao;
	private final GroupDao groupDao;
	private final GroupManager groupManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	protected static final String GROUP_SEND = "_group";
	protected static final String USER_SEND = "_user";
	protected static final String HAND_SEND = "_hand";
	protected static final String DEPT_SEND = "_dept";

	public DefaultSendNoticeManager(SendNoticeDao sendNoticeDao, UserDao userDao, ReceviceNoticeDao receviceNoticeDao,
			GroupDao groupDao, GroupManager groupManager, UserManager userManager, DepartmentManager departmentManager) {
		this.sendNoticeDao = sendNoticeDao;
		this.userDao = userDao;
		this.receviceNoticeDao = receviceNoticeDao;
		this.groupDao = groupDao;
		this.groupManager = groupManager;
		this.userManager = userManager;
		this.departmentManager = departmentManager;
	}

	public List<SendNotice> loadSendNotices(Long[] sendNoticeIds) {
		return this.sendNoticeDao.loadSendNotices(sendNoticeIds);
	}

	public void storeSendNotice(SendNotice notice) {
		this.sendNoticeDao.storeSendNotice(notice);
	}

	public void deleteSendNotice(SendNotice notice) {
		this.sendNoticeDao.deleteSendNotice(notice);
	}

	public void deleteSendNotices(Collection<SendNotice> notices) {
		for (SendNotice notice : notices) {
			this.receviceNoticeDao.deleteReceviceNotices(notice.getReceviceNotices());
		}

		this.sendNoticeDao.deleteSendNotices(notices);
	}

	public SendNotice loadSendNotice(Long sendNoticeId) {
		return this.sendNoticeDao.loadSendNotice(sendNoticeId);
	}

	public void joinUsersForSend(String[] availableUserId, SendNotice notice, Set<User> userSended) {
		collectionSender(availableUserId, userSended);

		for (User user : userSended)
			notice.getUsers().add(user);
	}

	private void collectionSender(String[] availableUserId, Set<User> userSended) {
		for (int i = 0; i < availableUserId.length; i++) {
			if (availableUserId[i].indexOf("_user") != -1) {
				User user = this.userDao.loadUser(Long.valueOf(availableUserId[i].substring(0,
						availableUserId[i].indexOf("_user"))));

				userSended.add(user);
			} else if (availableUserId[i].indexOf("_group") != -1) {
				Group group = this.groupDao.loadGroup(Long.valueOf(availableUserId[i].substring(0,
						availableUserId[i].indexOf("_group"))));

				List<Group> allGroups = this.groupManager.getGroupsOfAfterTraversal(group);

				for (Group g : allGroups) {
					userSended.addAll(g.getUsers());
				}

			} else if (availableUserId[i].indexOf("_dept") != -1) {
				Long deptId = Long.valueOf(availableUserId[i].substring(0, availableUserId[i].indexOf("_dept")));

				Department dept = this.departmentManager.loadDepartment(deptId);

				List<Department> allDepts = this.departmentManager.getAllChilds(dept);

				if ((null != allDepts) && (allDepts.size() != 0)) {
					for (Department department : allDepts) {
						List deptUsers = this.userManager.getUsersByDeptId(department.getId());
						userSended.addAll(deptUsers);
					}
				} else {
					List deptUsers = this.userManager.getUsersByDeptId(dept.getId());
					userSended.addAll(deptUsers);
				}
			}
		}
	}

	public void disabledAllSendNotices(Collection<SendNotice> sendNotices) {
		for (SendNotice oil : sendNotices) {
			oil.setDisabled(true);
			this.sendNoticeDao.storeSendNotice(oil);
		}
	}

	public void enabledAllSendNotices(Collection<SendNotice> sendNotices) {
		for (SendNotice oil : sendNotices) {
			oil.setDisabled(false);
			this.sendNoticeDao.storeSendNotice(oil);
		}
	}

	public SendNotice getNoticeByNoticeTilteAndContent(String title, String content) {
		return null;
	}
}
