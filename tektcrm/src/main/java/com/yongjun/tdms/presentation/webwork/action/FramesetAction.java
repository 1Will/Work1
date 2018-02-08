package com.yongjun.tdms.presentation.webwork.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.BaseAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.notice.NoticeUtil;
import com.yongjun.tdms.model.notice.ReceviceNotice;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.model.task.Task;
import com.yongjun.tdms.model.workReport.daily.Daily;
import com.yongjun.tdms.model.workspace.data.Data;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
import com.yongjun.tdms.service.task.TaskManager;
import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FramesetAction extends BaseAction {
	private static final long serialVersionUID = -2286308339990443246L;
	private final Log logger = LogFactory.getLog(getClass());
	private final UserManager userManager;
	private final ApplicationDocManager applicationDocManager;
	private final WorkWarnningManager workWarnningManager;
	private final ReceviceNoticeManager receviceNoticeManager;
	private final DailyManager dailyManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final ContractManagementManager contractManagementManager;
	private final TaskManager taskManager;
	private final FinancialManagementManager financialManagementManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private final GroupManager groupManager;
	private final DataManager dataManager;
	private User user;
	private NoticeUtil noticeUtil;

	public FramesetAction(UserManager userManager, ApplicationDocManager applicationDocManager,
			WorkWarnningManager workWarnningManager, ReceviceNoticeManager receviceNoticeManager,
			ContractManagementManager contractManagementManager, TaskManager taskManager, DailyManager dailyManager,
			PersonnelFilesManager personnelFilesManager, FinancialManagementManager financialManagementManager,
			ProjectInfoPlanManager projectInfoPlanManager, DataManager dataManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager, GroupManager groupManager) {
		this.userManager = userManager;
		this.applicationDocManager = applicationDocManager;
		this.workWarnningManager = workWarnningManager;
		this.receviceNoticeManager = receviceNoticeManager;
		this.contractManagementManager = contractManagementManager;
		this.taskManager = taskManager;
		this.dailyManager = dailyManager;
		this.personnelFilesManager = personnelFilesManager;
		this.financialManagementManager = financialManagementManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
		this.dataManager = dataManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
		this.groupManager = groupManager;
	}

	public String execute() throws Exception {
		if (null != this.userManager.getUser()) {
			this.logger.info("登录用户名：" + this.userManager.getUser().getName());
			this.user = this.userManager.getUser();
		}
		return "success";
	}

	public List<ApplicationDoc> getManualDocs() {
		return this.applicationDocManager.getAllManualDoc();
	}

	public List<ReceviceNotice> getReceiveNoticeList(Long userId, String readStaus, Boolean disabled, String noticeType) {
		List receiveNoticeList = new ArrayList();
		try {
			String[] keyNames = new String[4];
			Object[] keyValues = new Object[4];
			keyNames[0] = "receviceUser.id";
			keyNames[1] = "readStatus";
			keyNames[2] = "disabled";
			keyNames[3] = "noticeType.name";

			keyValues[0] = userId;
			keyValues[1] = String.valueOf(readStaus);
			keyValues[2] = disabled;
			keyValues[3] = String.valueOf(noticeType);

			receiveNoticeList = this.receviceNoticeManager.loadByKeyArray(keyNames, keyValues);
		} catch (Exception e) {
			this.logger.info("查询未读通知有错！");
		}
		if ((null != receiveNoticeList) && (!receiveNoticeList.isEmpty())) {
			return receiveNoticeList;
		}
		return null;
	}

	public long getReceiveNoticeSize(Long userId, String readStaus, Boolean disabled, String noticeType) {
		List receiveNoticeList = getReceiveNoticeList(userId, readStaus, disabled, noticeType);

		if ((null != receiveNoticeList) && (!receiveNoticeList.isEmpty())) {
			return Long.valueOf(receiveNoticeList.size()).longValue();
		}
		return 0L;
	}

	public List<ReceviceNotice> getNoticeList() {
		Long userId = getLoginUser().getId();
		List noticeList = new ArrayList();
		noticeList = this.receviceNoticeManager.getAllNoticByUserID(userId);

		// List unReadNoticeList = getReceiveNoticeList(userId, "UNREAD",
		// Boolean.valueOf(false), "新闻");
		//
		// List readNoticeList = getReceiveNoticeList(userId, "READED",
		// Boolean.valueOf(false), "新闻");
		//
		// List unReadAnnounceList = getReceiveNoticeList(userId, "UNREAD",
		// Boolean.valueOf(false), "公告");
		//
		// List readAnnounceList = getReceiveNoticeList(userId, "READED",
		// Boolean.valueOf(false), "公告");
		//
		// if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
		// noticeList.addAll(unReadNoticeList);
		// }
		// if ((null != readNoticeList) && (!readNoticeList.isEmpty())) {
		// noticeList.addAll(readNoticeList);
		// }
		// if ((null != unReadAnnounceList) && (!unReadAnnounceList.isEmpty()))
		// {
		// noticeList.addAll(unReadAnnounceList);
		// }
		// if ((null != readAnnounceList) && (!readAnnounceList.isEmpty())) {
		// noticeList.addAll(readAnnounceList);
		// }
		if ((null != noticeList) && (!noticeList.isEmpty())) {
			return noticeList;
		}
		return null;
	}

	public long getUnReadNoticeSize() {
		long userId = getLoginUser().getId().longValue();
		long unReadNum = getReceiveNoticeSize(Long.valueOf(userId), "UNREAD", Boolean.valueOf(false), "通知")
				+ getReceiveNoticeSize(Long.valueOf(userId), "UNREAD", Boolean.valueOf(false), "公告")
				+ getReceiveNoticeSize(Long.valueOf(userId), "UNREAD", Boolean.valueOf(false), "新闻");

		if ((0L == unReadNum) || (unReadNum < 1L)) {
			return 0L;
		}
		return unReadNum;
	}

	public Long getNumberOfUnRead() {
		Long number = Long.valueOf(0);
		if (null != this.userManager.getUser()) {
			number = this.workWarnningManager.GetNumberOfUnReadWarnningByUserID(this.userManager.getUser().getId());
		}
		return number;
	}

	public List<WorkWarnning> getWorkWarnningList() {
		Long userId = getLoginUser().getId();
		List unReadWorkWarnningList = new ArrayList();
		try {
			unReadWorkWarnningList = this.workWarnningManager.loadWarByUser(userId);
		} catch (Exception e) {
			this.logger.info("查询当前登录人未读提醒有错！");
		}
		if ((null != unReadWorkWarnningList) && (!unReadWorkWarnningList.isEmpty())) {
			return unReadWorkWarnningList;
		}
		return null;
	}

	public List<ContractManagement> getNewSignings() {
		List conMList = new ArrayList();
		try {
			if (isProjectInfoGroup()) {
				// 再次区分是军品还是民品的
				List<PersonnelFiles> tempList = this.personnelFilesManager.loadByKey("code", userManager.getUser().getCode());
				if (tempList != null && tempList.size() > 0) {
					PersonnelFiles personnelFiles = tempList.get(0);
					if (personnelFiles.getBusinessType() != null) {
						// 只有这个人是军品或者 民品会绑定检索条件。只能看所属权限的
						if (personnelFiles.getBusinessType().getName().equals("军品")|| personnelFiles.getBusinessType().getName().equals("民品")) {
							conMList = this.contractManagementManager.loadContractManagementByBType("%"+ personnelFiles.getBusinessType().getName() + "%");
						}else {
							//军民品，查看所有
							conMList = this.contractManagementManager.loadContractManagement();
						}
					}else {
						//适配永君服务，查看所有
						conMList = this.contractManagementManager.loadContractManagement();
					}
				}else {
					//适配admin，查看所有
					conMList = this.contractManagementManager.loadContractManagement();
				}

			} else {
				List<Long> proIdList = this.projectInfoPersonnelsManager.loadProjectInfoIdByPersonnel(userManager
						.getUser().getCode());
				if (proIdList == null || proIdList.size() > 1) {
					String pjIds = "0";
					for (int i = 0; i < proIdList.size(); i++) {
						pjIds += "," + proIdList.get(i);
					}
					conMList = this.contractManagementManager.loadContractManagementByPj(pjIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conMList;
	}

	public boolean isProjectInfoGroup() {
		boolean isDailyGroup = false;
		Set<User> noticePers = groupManager.getGroupByGroupName("项目管理组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
//			System.out.println(u.getId() + "==" + user.getId());
			if (user.getId().longValue() == u.getId().longValue()) {
				isDailyGroup = true;
			}
		}

		return isDailyGroup;
	}

	public Long getNewSigningSize() {
		Long size = Long.valueOf(0L);
		if ((null != getNewSignings()) && (!getNewSignings().isEmpty())) {
			size = Long.valueOf(getNewSignings().size());
		}
		return size;
	}

	public List<Task> getToDoTasks() {
		List toDoList = new ArrayList();
		String userCode = getLoginUser().getCode();
		try {
			String[] keyNames = new String[3];
			Object[] keyValues = new Object[3];
			keyNames[0] = "point.personnelFiles.code";
			keyNames[1] = "statue";
			keyNames[2] = "disabled";

			keyValues[0] = userCode;
			keyValues[1] = Integer.valueOf(0);
			keyValues[2] = Boolean.valueOf(false);

			toDoList = this.taskManager.loadByKeyArray(keyNames, keyValues);
		} catch (DaoException e) {
			this.logger.info("查询未审核任务出错！");
		}
		if ((null != toDoList) && (!toDoList.isEmpty())) {
			return toDoList;
		}
		return null;
	}

	// public Long getMyTeamSize()
	// {
	// Long size = Long.valueOf(0L);
	// List<Data> personnelFiles =getMyTeam();
	// if ((null != personnelFiles) && (!personnelFiles.isEmpty())) {
	// size = Long.valueOf(personnelFiles.size());
	// }
	// return size;
	// }
	public List<Daily> getDailys() {
		List reList = new ArrayList();

		if ((null != reList) && (!reList.isEmpty())) {
			return reList;
		}
		return null;
	}

	public PersonnelFiles getPersonnelFilseByCode(String code) {
		PersonnelFiles personnelFiles = null;
		List pfList = new ArrayList();
		if (null != code) {
			try {
				pfList = this.personnelFilesManager.loadByKey("code", code);
			} catch (DaoException e) {
				this.logger.info("根据编码查询人事档案出错！");
			}
		}
		if ((null != pfList) && (!pfList.isEmpty())) {
			personnelFiles = (PersonnelFiles) pfList.get(0);
			return personnelFiles;
		}
		return null;
	}

	public Long getToDoTaskSize() {
		Long size = Long.valueOf(0L);
		if ((null != getToDoTasks()) && (!getToDoTasks().isEmpty())) {
			size = Long.valueOf(getToDoTasks().size());
		}
		if ((null != getDailys()) && (!getDailys().isEmpty())) {
			size = Long.valueOf(size.longValue() + Long.valueOf(getDailys().size()).longValue());
		}
		return size;
	}

	public List<ReceviceNotice> getTaskList() {
		Long userId = getLoginUser().getId();
		List unReadNoticeList = getReceiveNoticeList(userId, "UNREAD", Boolean.valueOf(false), String.valueOf("待办任务"));

		if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
			return unReadNoticeList;
		}
		return null;
	}

	public List<ProjectInfoPlan> getMyProjectInfoPlan() {
		HashMap map = new HashMap();
		map.put("code", getLoginUser().getCode());
		map.put("state", "21101,21102");
		List list = this.projectInfoPlanManager.loadForMyTeam(map);

		return list;

	}

	public Long getTaskSize() {
		List unReadNoticeList = new ArrayList();
		unReadNoticeList = getTaskList();
		if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
			return Long.valueOf(unReadNoticeList.size());
		}
		return Long.valueOf(0L);
	}

	public List<ReceviceNotice> getNewsList() {
		Long userId = getLoginUser().getId();
		List reNoticeList = new ArrayList();
		List unReadNoticeList = getReceiveNoticeList(userId, "UNREAD", Boolean.valueOf(false), String.valueOf("新闻"));

		List readNoticeList = getReceiveNoticeList(userId, "READED", Boolean.valueOf(false), String.valueOf("新闻"));

		if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
			reNoticeList.addAll(unReadNoticeList);
		}
		if ((null != readNoticeList) && (!readNoticeList.isEmpty())) {
			reNoticeList.addAll(readNoticeList);
		}
		return reNoticeList;
	}

	public Long getNewsSize() {
		List noticeList = new ArrayList();
		noticeList = getNewsList();
		if ((null != noticeList) && (!noticeList.isEmpty())) {
			return Long.valueOf(noticeList.size());
		}
		return Long.valueOf(0L);
	}

	public HashMap getMyDataMap() {
		SimpleDateFormat sfMonth = new SimpleDateFormat("yyyy年MM月");
		SimpleDateFormat sfYear = new SimpleDateFormat("yyyy年");
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// String MFirstday = format.format(getCurrMouthFirst()); //获取当月第一天
		// String mLastday = format.format(getCurrMouthLast()); //获取当月最后一天
		// String yFirstday= format.format(getCurrYearFirst());//获取当年第一天
		// String yLastday= format.format(getCurrYearLast());//获取当年最后一天
		//
		// HashMap mapYear =
		// this.contractManagementManager.getDataMap(yFirstday, yLastday);
		// HashMap mapMouth =
		// this.contractManagementManager.getDataMap(MFirstday, mLastday);
		// HashMap mapThisMouth =
		// this.financialManagementManager.getDataMap(MFirstday, mLastday);
		// map.put("yearCount", mapYear.get("count"));
		// map.put("yearMoney", mapYear.get("money"));
		// map.put("mouthCount", mapMouth.get("count"));
		// map.put("mouthMoney", mapMouth.get("money"));
		// map.put("payCount", mapThisMouth.get("count"));
		// map.put("payMoney", mapThisMouth.get("money"));
		String[] keyStrings = { "personnelFiles.code", "month" };
		String[] valueStrings = { getLoginUser().getCode(), sfMonth.format(new Date()) };
		Data data = null;
		Object[] objectArr = null;
		try {
			List<Data> myDatas = this.dataManager.loadByKeyArray(keyStrings, valueStrings);
			HashMap map = new HashMap();
			map.put("year", sfYear.format(new Date()));
			map.put("code", getLoginUser().getCode());
			Object object = this.dataManager.loadAllDataByYear(map);
			objectArr = (Object[]) object;
			if (myDatas != null && myDatas.size() > 0) {
				data = myDatas.get(0);
			}

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap map = new HashMap();
		if (objectArr != null) {
			map.put("yearCMCount", objectArr[1]);
			map.put("yearCMMoney", objectArr[0]);
			map.put("yearFMCount", objectArr[2]);
			map.put("yearFMMoney", objectArr[3]);
			map.put("yearBRCount", objectArr[4]);
			map.put("yearBRMoney", objectArr[5]);
		} else {
			map.put("yearCMCount", 0);
			map.put("yearCMMoney", 0);
			map.put("yearFMCount", 0);
			map.put("yearFMMoney", 0);
			map.put("yearBRCount", 0);
			map.put("yearBRMoney", 0);
		}
		if (data != null) {
			map.put("monthCMCount", data.getContractManagementNum());
			map.put("monthCMMoney", data.getContractManagementMoney());
			map.put("monthFMCount", data.getFinancialManagementNum());
			map.put("monthFMMoney", data.getFinancialManagementMoney());
			map.put("monthBRCount", data.getBillingRecordNum());
			map.put("monthBRMoney", data.getBillingRecordMoney());
		} else {
			map.put("monthCMCount", 0);
			map.put("monthCMMoney", 0);
			map.put("monthFMCount", 0);
			map.put("monthFMMoney", 0);
			map.put("monthBRCount", 0);
			map.put("monthBRMoney", 0);
		}

		return map;
	}

	public List<Data> getMyTeam() {
		SimpleDateFormat sfMonth = new SimpleDateFormat("yyyy年MM月");
		// String[] key = { "personnelFiles.superiorLeader.code", "month" };
		// String[] value = { getLoginUser().getCode(), sfMonth.format(new
		// Date()) };
		// try {
		// List<Data> listDatas = this.dataManager.loadByKeyArray(key, value);
		// } catch (DaoException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		List<PersonnelFiles> subordinateList = null;
		try {// 根据当前人code获取下属的列表
			subordinateList = this.personnelFilesManager.loadByKey("superiorLeader.code", getLoginUser().getCode());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = null;// 需要返回的数据
		if (subordinateList != null && subordinateList.size() > 0) {

			// 根据当前人的id获取
			List<PersonnelFiles> list2 = this.personnelFilesManager.loadBySuperiorLeader(getLoginUser().getCode());
			String personnelFilesId = "";
			for (PersonnelFiles p : list2) {
				if (personnelFilesId.equals("")) {
					personnelFilesId = p.getId() + "";
				} else {
					personnelFilesId += "," + p.getId();
				}
			}
			HashMap map = new HashMap();
			map.put("personnelFilesId", personnelFilesId);
			map.put("sfMonth", sfMonth.format(new Date()));
			list = this.dataManager.loadAllDataByTeam(map);
		}
		return list;

	}

	public Integer getAllNumberOfUnRead() {
		Integer number = Integer.valueOf(0);
		if (null != this.userManager.getUser()) {
			number = this.receviceNoticeManager.getAllNumberOfUnReadNoticByUserID(this.userManager.getUser().getId());
		}
		return number;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public String index() {
		return "success";
	}

	public NoticeUtil getNoticeUtil() {
		return this.noticeUtil;
	}

	public void setNoticeUtil(NoticeUtil noticeUtil) {
		this.noticeUtil = noticeUtil;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	public static Date getCurrMouthFirst() {
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		return cale.getTime();
	}

	public static Date getCurrMouthLast() {
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return cale.getTime();
	}

	public Integer getMyPlanSize() {
		Integer sizeInteger = 0;
		List<ProjectInfoPlan> plans = this.getMyProjectInfoPlan();
		if (plans != null && plans.size() > 0) {
			sizeInteger = plans.size();
		}
		return sizeInteger;
	}

}
