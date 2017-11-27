package com.yongjun.tdms.presentation.webwork.action.workReport.daily;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.daily.Daily;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditDailyAction extends PrepareAction {
	private static final long serialVersionUID = 3022668162752790885L;
	private final DailyManager dailyManager;
	private final WeeklyManager weeklyManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final BackVisitManager backVisitManager;
	private final InstitutionManager institutionManager;
	private final DepartmentManager departmentManager;
	private final DutyManager dutyManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	protected final GroupManager groupManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private String startHour;
	private String startMinute;
	private String endHour;
	private String endMinute;
	private Long weeklyId;
	private String perType;
	private String backVisitIds;
	private Daily daily;
	private String popWindowFlag;
	private PersonnelFiles personnelFiles;
	private DataManager dataManager;

	public EditDailyAction(DailyManager dailyManager, WeeklyManager weeklyManager, UserManager userManager,
			CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager,
			BackVisitManager backVisitManager, InstitutionManager institutionManager,
			DepartmentManager departmentManager, DutyManager dutyManager, EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, GroupManager groupManager, DataManager dataManager,
			PersonnelFilesToUserManager personnelFilesToUserManager) {
		this.dailyManager = dailyManager;
		this.weeklyManager = weeklyManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager = personnelFilesManager;
		this.backVisitManager = backVisitManager;
		this.institutionManager = institutionManager;
		this.departmentManager = departmentManager;
		this.dutyManager = dutyManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.groupManager = groupManager;
		this.dataManager = dataManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
	}

	public void prepare() throws Exception {

		List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
		if ((null != pfs) && (pfs.size() > 0)) {
			this.personnelFiles = ((PersonnelFiles) pfs.get(0));
		} else {
			this.personnelFiles = new PersonnelFiles();
		}
		if (hasId("weekly.id")) {
			this.weeklyId = Long.valueOf(this.request.getParameter("weekly.id"));
		}
		if ((this.daily == null) && (hasId("daily.id"))) {
			this.daily = this.dailyManager.loadDaily(getId("daily.id"));
			this.startHour = new SimpleDateFormat("kk").format(this.daily.getStartTime());

			this.startMinute = new SimpleDateFormat("mm").format(this.daily.getStartTime());

			this.endHour = new SimpleDateFormat("kk").format(this.daily.getEndTime());

			this.endMinute = new SimpleDateFormat("mm").format(this.daily.getEndTime());
		} else {
			this.daily = new Daily();
		}
		this.perType = permission();
		if (null != this.request.getParameter("popWindowFlag")) {
			this.popWindowFlag = this.request.getParameter("popWindowFlag");
		}
	}

	public String save() {
		boolean isNew1 = this.daily.isNew();
		String currentDate = this.request.getParameter("daily.currentDate");
		this.startHour = this.request.getParameter("startHour");
		this.startMinute = this.request.getParameter("startMinute");
		this.endHour = this.request.getParameter("endHour");
		this.endMinute = this.request.getParameter("endMinute");
		// 获取记录人
		if (hasId("personId")) {
			this.daily.setRapporteur(this.userManager.loadUser(getId("personId")));
		}

	   this.daily.setPerson(personnelFiles);
		if (hasId("inst.id")) {
			this.daily.setInst(this.institutionManager.loadInstitution(getId("inst.id")));
		}

		if (hasId("dept.id")) {
			this.daily.setDept(this.departmentManager.loadDepartment(getId("dept.id")));
		}

		if (hasId("duty.id")) {
			this.daily.setDuty(this.dutyManager.loadDuty(getId("duty.id")));
		}

		// 获取回访记录的所有id
//		this.backVisitIds = this.request.getParameter("backVisitIds");
//		if (!"".equals(backVisitIds)) {
//			String idsTemp[] = backVisitIds.split("-");
//			Long bvtIds[] = new Long[idsTemp.length];
//			for (int i = 0; i < idsTemp.length; i++) {
//				bvtIds[i] = Long.parseLong(idsTemp[i]);
//			}
//			List<BackVisit> bvtList = backVisitManager.loadAllBackVisit(bvtIds);
//			this.daily.getBvtList().addAll(bvtList);
//			bvtList.clear();
//		}

		// this.daily.setBackVisitContext(this.request.getParameter("daily.backVisitContext"));
		// Calendar c = Calendar.getInstance();
		// String d = new SimpleDateFormat("yyyy-MM-dd").format(c) + " ";

		try {
			Date startTime = new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(currentDate + " " + this.startHour + ":"
					+ this.startMinute);

			Date endTime = new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(currentDate + " " + this.endHour + ":"
					+ this.endMinute);

			this.daily.setStartTime(startTime);
			this.daily.setEndTime(endTime);
			this.daily.setWeekDate(this.request.getParameter("daily.week"));

		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		Boolean isNew = Boolean.valueOf(this.daily.isNew());
		if (hasId("weekly.id")) {
			Weekly weekly = this.weeklyManager.loadWeekly(getId("weekly.id"));

			this.daily.setWeekly(weekly);
			this.daily.setOrganization(weekly.getOrganization());
			this.daily.setInst(weekly.getInst());
			this.daily.setDept(weekly.getDept());
			this.daily.setDuty(weekly.getDuty());
			this.daily.setRapporteur(weekly.getRapporteur());
		}
		// 提交后保存事件
		String submit = null;
		

		this.daily.setIsSaved(this.request.getParameter("isSaved"));
		try {
			if(isNew){
				this.daily.setSubmitNum(0l);
				
			}else {
				if(this.daily.getIsSaved().equals("1")){
	            	 this.daily.setSubmitNum(this.daily.getSubmitNum()+1);
	             } 
			}
			this.dailyManager.storeDaily(this.daily);
			
			
			if ("1".equals(this.request.getParameter("isSaved"))) {
				Set<User> noticePers = groupManager.getGroupByGroupName("微信日报通知组").getUsers();
				if(personnelFiles.getBusinessType()!=null){
					noticePers = personnelFilesToUserManager.getTypeUser(noticePers,personnelFiles.getBusinessType().getCode());
				}
				Set<Long> idSet = new HashSet<Long>();
				idSet.add(getUser().getId());
				for (User user : noticePers) {
					idSet.add(user.getId());
				}
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10002");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventTypes不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					event.setUserId(userManager.loadByKey("code", this.daily.getPerson().getCode()).get(0).getId()+"");
					Map<String, String> map = new HashMap<String, String>();
					// 查询领导
					PersonnelFiles pFiles = getPeronnelF().getSuperiorLeader();
					while (pFiles != null) {
						List<User> leader = userManager.loadByKey("code", pFiles.getCode());
						idSet.add(leader.get(0).getId());
						pFiles = pFiles.getSuperiorLeader();
					}
					String ids = "";
					for (Long id : idSet) {
						ids += id + ",";
					}
					map.put("users", ids.substring(0, ids.length() - 1));
					map.put("dailyId", this.daily.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.daily.getCurrentDate())+","+this.daily.getRapporteur().getName()+"提交了日报");
					map.put("url", "workReport/editDaily.html?popWindowFlag=popWindowFlag&daily.id="+this.daily.getId());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
//					HashMap mapData =new HashMap();
//		              mapData.put("type", "10002");
//		              mapData.put("thisMoney", "0");
//		              mapData.put("lastMoney", "0");
//					mapData.put("submitNum", this.daily.getSubmitNum());
//					mapData.put("date", this.daily.getCurrentDate());
//					this.dataManager.storeData(personnelFiles, mapData);
					submit = "submit";
			}
			
			

			if (isNew.booleanValue()) {
				addActionMessage(getText("daily.add.success", Arrays.asList(new Object[] { new SimpleDateFormat(
						"yyyy-MM-dd").format(this.daily.getCurrentDate()) })));

				return "new";
			}
			if (submit != null) {
				addActionMessage(getText("daily.submit.success", Arrays.asList(new Object[] { new SimpleDateFormat(
						"yyyy-MM-dd").format(this.daily.getCurrentDate()) })));
			} else {
				addActionMessage(getText("daily.edit.success", Arrays.asList(new Object[] { new SimpleDateFormat(
						"yyyy-MM-dd").format(this.daily.getCurrentDate()) })));
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("daily.add.error", Arrays.asList(new Object[] { new SimpleDateFormat("yyyy-MM-dd")
					.format(this.daily.getCurrentDate()) })));
		}
		return "error";
	}

	public String permission() {
		String flag = "0";
		boolean isNew = this.daily.isNew();

		if (isNew)
			flag = "0";
		else if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser()))
				|| (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理"))) {
			flag = "2";
		} else if (dutyByUser(getUser()).equals("部门经理")) {
			if ((null != this.daily) && (null != this.daily.getRapporteur())
					&& (this.daily.getRapporteur().getId().equals(getUser().getId()))) {
				flag = "0";
			} else
				flag = "1";
		} else {
			flag = "0";
		}
		return flag;
	}

	public String dutyByUser(User user) {
		List list = new ArrayList();
		PersonnelFiles personnelFiles = new PersonnelFiles();
		String dutyName = null;
		try {
			list = this.personnelFilesManager.loadByKey("code", user.getCode());
		} catch (DaoException e) {
			this.logger.info("查询用户对应的人事档案出错！");
		}
		if ((null != list) && (list.size() > 0)) {
			personnelFiles = (PersonnelFiles) list.get(0);
			dutyName = personnelFiles.getDuty().getPerType().getName().trim();
		}
		return dutyName;
	}

	public List<CodeValue> getAllReportType() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { String.valueOf("049"), Boolean.valueOf(false) };
			List reportTypeList = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (reportTypeList != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) reportTypeList.get(0)).getId(), Boolean.valueOf(false) };
				List reportTypes = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);

				if (reportTypes != null)
					return reportTypes;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public PersonnelFiles getPeronnelF() throws DaoException {
		List list = this.personnelFilesManager.loadByKey("code", getUser().getCode().trim());
		if ((null != list) && (list.size() > 0)) {
			return (PersonnelFiles) list.get(0);
		}
		return new PersonnelFiles();
	}

	public Daily getDaily() {
		return this.daily;
	}

	public void setDaily(Daily daily) {
		this.daily = daily;
	}

	public String getEndHour() {
		return this.endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getEndMinute() {
		return this.endMinute;
	}

	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}

	public String getStartHour() {
		return this.startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMinute() {
		return this.startMinute;
	}

	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}

	public Long getWeeklyId() {
		return this.weeklyId;
	}

	public void setWeeklyId(Long weeklyId) {
		this.weeklyId = weeklyId;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public String getPerType() {
		return this.perType;
	}

	public void setPerType(String perType) {
		this.perType = perType;
	}

	public String getBackVisitIds() {
		return backVisitIds;
	}

	public void setBackVisitIds(String backVisitIds) {
		this.backVisitIds = backVisitIds;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}

}
