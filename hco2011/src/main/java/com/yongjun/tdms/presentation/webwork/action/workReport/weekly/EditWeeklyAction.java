package com.yongjun.tdms.presentation.webwork.action.workReport.weekly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.week.Week;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;
import com.yongjun.tdms.model.workReport.weekly.Weekly;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.week.WeekManager;
import com.yongjun.tdms.service.workReport.weekPlan.WeekPlanManager;
import com.yongjun.tdms.service.workReport.weekly.WeeklyManager;
import com.yongjun.tdms.service.workspace.data.DataManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditWeeklyAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final DutyManager dutyManager;
	private final InstitutionManager institutionManager;
	private final WeeklyManager weeklyManager;
	private final WeekManager weekManager;
	private final WeekPlanManager weekPlanManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	protected final GroupManager groupManager;
	private DataManager dataManager;
	private Weekly weekly;
	private Week week;
	private boolean first = false;
	private String isSuperSys;
	private String perType;
	private PersonnelFiles personnelFiles;

	public EditWeeklyAction(PersonnelFilesManager personnelFilesManager, InstitutionManager institutionManager,
			UserManager userManager, DutyManager dutyManager, WeeklyManager weeklyManager,
			DepartmentManager departmentManager, WeekManager weekManager, WeekPlanManager weekPlanManager,
			EventNewManager eventNewManager, EventTypeManager eventTypeManager, GroupManager groupManager,DataManager dataManager) {
		this.personnelFilesManager = personnelFilesManager;
		this.institutionManager = institutionManager;
		this.userManager = userManager;
		this.dutyManager = dutyManager;
		this.weeklyManager = weeklyManager;
		this.departmentManager = departmentManager;
		this.weekManager = weekManager;
		this.weekPlanManager = weekPlanManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.groupManager = groupManager;
		this.dataManager =dataManager;
	}

	public String execute() throws Exception {
		return super.execute();
	}

	public void prepare() throws Exception {
		 List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
		      if ((null != pfs) && (pfs.size() > 0))
		         {
		   this.personnelFiles = ((PersonnelFiles)pfs.get(0));
		 }else {
			 this.personnelFiles = new PersonnelFiles();
		}
		if (hasId("weekly.id")) {
			this.weekly = this.weeklyManager.loadWeekly(getId("weekly.id"));
		} else {
			this.weekly = new Weekly();
			this.first = true;
		}

		if (hasId("week.id")) {
			this.week = this.weekManager.loadWeek(getId("week.id"));
		}

		boolean b = isSuperAdmin();
		if (b) {
			this.isSuperSys = "true";
		} else {
			this.isSuperSys = "false";
		}
		this.perType = permission();
	}

	public String save() {
		boolean isNew = this.weekly.isNew();

		if (hasId("personId")) {
			this.weekly.setRapporteur(this.userManager.loadUser(getId("personId")));
		}

		if (hasId("inst.id")) {
			this.weekly.setInst(this.institutionManager.loadInstitution(getId("inst.id")));
		}

		if (hasId("dept.id")) {
			this.weekly.setDept(this.departmentManager.loadDepartment(getId("dept.id")));
		}

		if (hasId("duty.id")) {
			this.weekly.setDuty(this.dutyManager.loadDuty(getId("duty.id")));
		}

		String submit = null;
		this.weekly.setIsSaved(this.request.getParameter("weekly.isSaved"));

		this.weekly.setOrganization(this.userManager.getOrganization());
		try {
			if (isNew) {
				if (hasId("week.id")) {
					this.weekly.setWeek(this.week);
				}
				this.weekly.setSubmitNum(0l);
				String newCode = autoCompleteCode();
				this.weekly.setCode(newCode);
				this.weekly.setName(this.week.getName() + "_" + userManager.getUser().getName() + "周计划");
			}else {
				if(this.weekly.getIsSaved().equals("1")){
	            	 this.weekly.setSubmitNum(this.weekly.getSubmitNum()+1);
	             }  
			}

			this.weeklyManager.storeWeekly(this.weekly);
			
			
			if ("1".equals(this.request.getParameter("weekly.isSaved"))) {
				Set<User> noticePers = groupManager.getGroupByGroupName("微信日报通知组").getUsers();
				Set<Long> idSet = new HashSet<Long>();
				idSet.add(getUser().getId());
				for (User user : noticePers) {
					idSet.add(user.getId());
				}
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10011");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						eventType = new EventType();
						eventType.setId(12L);
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName("周计划提交");
					event.setUserId(this.userManager.getUser().getId() + "");
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
					map.put("weeklyId", this.weekly.getId() + "");
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					 HashMap mapData =new HashMap();
		              mapData.put("type", "10011");
		              mapData.put("thisMoney", "0");
		              mapData.put("lastMoney", "0");
					mapData.put("submitNum", this.weekly.getSubmitNum());
					mapData.put("date", this.weekly.getStartDate());
					this.dataManager.storeData(personnelFiles, mapData);
					submit = "submit";
			}
			
			
			
			if (isNew) {
				List<WeekPlan> weekPlans = weekPlanManager.loadByKeyArray(new String[] { "user.id", "week.id" },
						new Object[] { userManager.getUser().getId(), this.week.getId() });
				if (weekPlans.size() != 0) {
					WeekPlan weekPlan = weekPlans.get(0);
					weekPlans.get(0).setWeekly(this.weekly);
					weekPlanManager.storeWeekPlan(weekPlan);
				}
				addActionMessage(getText("weekly.add.success", Arrays.asList(new Object[] { this.weekly.getCode() })));
				return "new";
			}
			if ("submit".equals(submit)) {
				addActionMessage(getText("weekly.submit.success", Arrays.asList(new Object[] { this.weekly.getCode() })));
				return "success";
			}
			addActionMessage(getText("weekly.edit.success", Arrays.asList(new Object[] { this.weekly.getCode() })));

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("weekly.add.error", Arrays.asList(new Object[] { this.weekly.getName() })));
			} else {
				addActionMessage(getText("weekly.edit.error", Arrays.asList(new Object[] { this.weekly.getCode() })));
			}
		}
		return "error";
	}

	public List<Institution> getAllInsts() {
		User user = this.userManager.loadUser(getUser().getId());

		List agencyList = this.institutionManager.loadAllInstitution();
		Institution first = new Institution();
		first.setId(Long.valueOf(-1L));
		first.setName(getText(""));
		agencyList.add(0, first);
		return agencyList;
	}

	public List<Department> getAllDepts() {
		List list = new ArrayList();
		Department agency = new Department();
		agency.setId(Long.valueOf(-1L));
		agency.setName(getText(""));
		list.add(0, agency);
		return list;
	}

	public List<Duty> getAllDutys() {
		List list = new ArrayList();
		Duty duty = new Duty();
		duty.setId(Long.valueOf(-1L));
		duty.setName(getText(""));
		list.add(0, duty);
		return list;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public PersonnelFiles getPeronnelF() throws DaoException {
		List list = this.personnelFilesManager.loadByKey("code", getUser().getCode().trim());
		if ((null != list) && (list.size() > 0)) {
			return (PersonnelFiles) list.get(0);
		}
		return new PersonnelFiles();
	}

	public String autoCompleteCode() {
		Calendar time = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String currentDate = sdf.format(time.getTime());
		String maxCode = this.weeklyManager.getMaxWeeklyCode(this.userManager.getUser().getLoginName() + "_"
				+ currentDate, this.userManager.getUser().getId(), this.userManager.getOrganization().getId());

		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			return this.userManager.getUser().getLoginName() + "_" + currentDate + "_0" + num;
		}

		return this.userManager.getUser().getLoginName() + "_" + currentDate + "_" + "01";
	}

	public boolean isSuperAdmin() {
		boolean flag = false;
		String[] keyNames = { "code", "organization.id" };
		Object[] keyValues = { this.userManager.getUser().getCode(),
				this.userManager.getUser().getOrganization().getId() };
		try {
			List pf = this.personnelFilesManager.loadByKeyArray(keyNames, keyValues);

			if (null!=pf && pf.size() > 0)
				flag = false;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String permission() {
		String flag = "0";
		boolean isNew = this.weekly.isNew();

		if (isNew)
			flag = "0";
		else if ((getUser().getId().longValue() == 2L) || (null == dutyByUser(getUser()))
				|| (dutyByUser(getUser()).isEmpty()) || (dutyByUser(getUser()).equals("公司经理"))) {
			flag = "2";
		} else if (dutyByUser(getUser()).equals("部门经理")) {
			if ((null != this.weekly) && (null != this.weekly.getRapporteur())
					&& (this.weekly.getRapporteur().getId().equals(getUser().getId()))) {
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
	public boolean hasWeekly(Long userId , Long weekId){
		String key[] ={"rapporteur.id","week.id"};
		Object value[]={userId,weekId};
		List<Weekly> weeklies =null;
		try {
			weeklies = weeklyManager.loadByKeyArray(key, value);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		if(weeklies.size()==0){
			return true;
		}else {
			return false;
		}
	}

	public Weekly getWeekly() {
		return this.weekly;
	}

	public void setWeekly(Weekly weekly) {
		this.weekly = weekly;
	}

	public boolean isFirst() {
		return this.first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public String getIsSuperSys() {
		return this.isSuperSys;
	}

	public String getPerType() {
		return this.perType;
	}

	public void setPerType(String perType) {
		this.perType = perType;
	}

	public void setIsSuperSys(String isSuperSys) {
		this.isSuperSys = isSuperSys;
	}
}
