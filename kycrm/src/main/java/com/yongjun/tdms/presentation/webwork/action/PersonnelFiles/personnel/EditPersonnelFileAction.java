package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnel;

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
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Organization;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.UserType;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.personnelFiles.leavepersonnelfiles.LeavePerson;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles.LeavePersonManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

@SuppressWarnings({"unchecked","rawtypes"})
public class EditPersonnelFileAction extends PrepareAction {
	private static final long serialVersionUID = -6543880456612721423L;
	private final PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private final InstitutionManager institutionManager;
	private final DutyManager dutyManager;
	private final DepartmentManager departmentManager;
	private final LeavePersonManager leavePersonManager;
	private final CodeValueManager codeValueManager;
	private final AreaManager areaManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	protected final GroupManager groupManager;
	private PersonnelFiles personnelFile;
	private boolean sex;
	private boolean first = true;
	private String backvist;
	public EditPersonnelFileAction(PersonnelFilesManager personnelFilesManager, UserManager userManager,
			InstitutionManager institutionManager, DutyManager dutyManager, CodeValueManager codeValueManager,
			AreaManager areaManager, DepartmentManager departmentManager, LeavePersonManager leavePersonManager,
			EventNewManager eventNewManager, EventTypeManager eventTypeManager, GroupManager groupManager) {
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.institutionManager = institutionManager;
		this.dutyManager = dutyManager;
		this.codeValueManager = codeValueManager;
		this.areaManager = areaManager;
		this.departmentManager = departmentManager;
		this.leavePersonManager = leavePersonManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.groupManager = groupManager;
	}

	public void prepare() throws Exception {
		
		if(request.getParameter("backvist")!=null&& request.getParameter("backvist").length()>0){
       	 this.backvist=request.getParameter("backvist");
       	 
        }  
		if (hasId("personnelFile.id")) {
			this.personnelFile = this.personnelFilesManager.loadPersonnel(getId("personnelFile.id"));

			this.sex = this.personnelFile.isSex();
			setFirst(false);
		} else {
			this.personnelFile = new PersonnelFiles();
		}
	}

	public String save() {
		boolean isNew = this.personnelFile.isNew();

		if (hasId("inst.id")) {
			this.personnelFile.setInst(this.institutionManager.loadInstitution(getId("inst.id")));
		}

		if (hasId("dept.id")) {
			this.personnelFile.setDept(this.departmentManager.loadDepartment(getId("dept.id")));
		}

		if (hasId("duty.id")) {
			this.personnelFile.setDuty(this.dutyManager.loadDuty(getId("duty.id")));
		}

		if (hasId("personnelFile.birthplace")) {
			this.personnelFile.setBirthplace(this.areaManager.loadArea(getId("personnelFile.birthplace")));
		}

		if (hasId("personnelFile.politice")) {
			this.personnelFile.setPolitice(this.codeValueManager.loadCodeValue(getId("personnelFile.politice")));
		}

		if (hasId("personnelFile.status")) {
			this.personnelFile.setStatus(this.codeValueManager.loadCodeValue(getId("personnelFile.status")));
		}

		if ((null != this.request.getParameter("sex")) && ("" != this.request.getParameter("sex"))) {
			this.sex = Boolean.valueOf(this.request.getParameter("sex")).booleanValue();
			this.personnelFile.setSex(this.sex);
		}

		if (hasId("personnelFile.marriage")) {
			this.personnelFile.setMarriage(this.codeValueManager.loadCodeValue(getId("personnelFile.marriage")));
		}

		if (hasId("personnelFile.national")) {
			this.personnelFile.setNational(this.codeValueManager.loadCodeValue(getId("personnelFile.national")));
		}
		//职称
		if (hasId("professor.id")) {
			this.personnelFile.setProfessor(this.codeValueManager.loadCodeValue(getId("professor.id")));
		}
		
		if (hasId("personnelFile.education")) {
			this.personnelFile.setEducation(this.codeValueManager.loadCodeValue(getId("personnelFile.education")));
		}

		if (hasId("personnelFile.workway")) {
			this.personnelFile.setWorkway(this.codeValueManager.loadCodeValue(getId("personnelFile.workway")));
		}
		if (hasId("businessType.id")) {
			this.personnelFile.setBusinessType(this.codeValueManager.loadCodeValue(getId("businessType.id")));
		}
		if (hasId("personnelFile.superiorLeader")) {
			this.personnelFile.setSuperiorLeader(this.personnelFilesManager
					.loadPersonnel(getId("personnelFile.superiorLeader")));
		}

		Organization organization  =this.userManager.getOrganization();
		System.out.println(organization.getId());
		this.personnelFile.setOrganization(organization);
		this.personnelFile.setIsSaved(request.getParameter("isSaved"));
		try {
			if (isNew) {
				String newCode = autoCompleteCode();
				this.personnelFile.setCode(newCode);
				// 职级
				this.personnelFile.setRank(this.codeValueManager.loadByKey("code", "21501").get(0));
				// 等级
				this.personnelFile.setGrade(this.codeValueManager.loadByKey("code", "21705").get(0));
				this.personnelFilesManager.storePersonnel(this.personnelFile);
				
				addActionMessage(getText("personnel.add.success",
						Arrays.asList(new Object[] { this.personnelFile.getCode() })));

				return "success";
			}

			this.personnelFilesManager.storePersonnel(this.personnelFile);
			String submit = null;
			if ("1".equals(this.request.getParameter("isSaved"))) {
				try {
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10033");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventTypes不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					event.setUserId(this.userManager.getUser().getId() + "");
					Map<String, String> map = new HashMap();
					Set<User> noticePers = groupManager.getGroupByGroupName("人力资源管理组").getUsers();
					Set<Long> idSet = new HashSet<Long>();
					idSet.add(getUser().getId());
					for (User user : noticePers) {
						idSet.add(user.getId());
					}
					String ids = "";
					for (Long id : idSet) {
						ids += id + ",";
					}
					map.put("users", ids.substring(0, ids.length() - 1));
					map.put("personnelFileId", this.personnelFile.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.personnelFile.getCreatedTime())
							+ "," + this.personnelFile.getCreator() + "提交了人事档案:" + this.personnelFile.getName());
					map.put("url", "personnelFile/editPersonnelFile.html?readOnly=false&personnelFile.id="
							+ this.personnelFile.getId());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					submit = "submit";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (this.personnelFile.getStatus().getId().longValue() == 177L) {
				List lpList = new ArrayList();
				LeavePerson person = null;
				try {
					String[] keyNames = new String[1];
					Object[] keyValues = new Object[1];
					keyNames[0] = "code";
					keyValues[0] = this.personnelFile.getCode().trim();
					lpList = this.leavePersonManager.loadByKeyArray(keyNames, keyValues);
				} catch (Exception e) {
					System.out.println("error search leavePerson!");
				}
				if ((null == lpList) || (lpList.isEmpty())) {
					person = new LeavePerson();
					person.setCode(this.personnelFile.getCode());
					person.setName(this.personnelFile.getName());
					person.setFileNo(this.personnelFile.getFileNo());
					person.setInst(this.personnelFile.getInst());
					person.setDept(this.personnelFile.getDept());
					person.setDuty(this.personnelFile.getDuty());
					person.setMobile(this.personnelFile.getMobile());
					person.setTelphone(this.personnelFile.getTelphone());
					person.setStatus(this.personnelFile.getStatus());
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					now = sdf.parse(sdf.format(now));
					person.setLeaveDate(now);
					this.leavePersonManager.storePersonnel(person);
				}

			}
			if (submit != null) {
				addActionMessage(getText("personnel.submit.success",
						Arrays.asList(new Object[] { this.personnelFile.getCode() })));
			} else {
				addActionMessage(getText("personnel.edit.success",
						Arrays.asList(new Object[] { this.personnelFile.getCode() })));
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("personnel.submit.exists",
					Arrays.asList(new Object[] { this.personnelFile.getCode() })));
		}
		return "error";
	}

	public void updateUser() {
		List userList = new ArrayList();
		User user = null;
		boolean isNew = false;
		try {
			userList = this.userManager.loadByKey("code", this.personnelFile.getCode().trim());
		} catch (Exception e) {
			this.logger.info("查询对应的用户出错！");
		}
		if ((null != userList) && (!userList.isEmpty())) {
			user = (User) userList.get(0);
			isNew = false;
		} else {
			user = new User();
			isNew = true;
			user.setUserType(UserType.SYSTEM_USER);
			user.setCode(this.personnelFile.getCode());
		}

		user.setName(this.personnelFile.getName());
		user.setOrganization(getUser().getOrganization());
		user.setInstitustion(this.personnelFile.getInst());
		user.setDepartment(this.personnelFile.getDept());
		user.setTelphoneNumber(this.personnelFile.getMobile());
		user.setBrith(this.personnelFile.getBirthday());
		user.setEmail(this.personnelFile.getEmail());
		if (isNew) {
			this.userManager.changePassword(user, "1111");
		} else {
			this.userManager.storeUser(user);
		}
	}

	public String autoCompleteCode() {
		String maxCode = this.personnelFilesManager.getMaxPFCode("yg", this.userManager.getOrganization().getId());

		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return "yg-00" + num;
			if (num < 100) {
				return "yg-0" + num;
			}
			return "yg-" + num;
		}

		return "yg-001";
	}

	public List<CodeValue> getAllMarriage() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { "011", Boolean.valueOf(false) };
			List marriage = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (marriage != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) marriage.get(0)).getId(), Boolean.valueOf(false) };
				List marriages = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);

				if (marriages != null)
					return marriages;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public List<Area> getAllBirthplace() {
		try {
			List areas = new ArrayList();
			String[] keyArray = new String[2];
			Object[] valueArray = new Object[2];

			keyArray[0] = "type";
			keyArray[1] = "parentArea";

			valueArray[0] = "province";
			valueArray[1] = Integer.valueOf(43);

			List list = this.areaManager.loadByKeyArray(keyArray, valueArray);
			if ((null != list) && (list.size() > 0)) {
				areas.addAll(list);
			}
			return areas;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllNational() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { "004", Boolean.valueOf(false) };
			List nationality = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (nationality != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) nationality.get(0)).getId(), Boolean.valueOf(false) };

				List nationalitys = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);

				if (nationalitys != null)
					return nationalitys;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public List<CodeValue> getAllPolitice() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { "005", Boolean.valueOf(false) };
			List politice = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (politice != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) politice.get(0)).getId(), Boolean.valueOf(false) };
				List politices = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);

				if (politices != null)
					return politices;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public List<CodeValue> getAllEducation() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { "017", Boolean.valueOf(false) };
			List education = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (education != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) education.get(0)).getId(), Boolean.valueOf(false) };
				List educations = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);

				if (educations != null)
					return educations;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public List<CodeValue> getAllWorkway() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { "015", Boolean.valueOf(false) };
			List workmode = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (workmode != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) workmode.get(0)).getId(), Boolean.valueOf(false) };
				List workmodes = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);

				if (workmodes != null)
					return workmodes;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public List<CodeValue> getAllStatus() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { "012", Boolean.valueOf(false) };
			List workmode = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);

			if (workmode != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) workmode.get(0)).getId(), Boolean.valueOf(false) };
				List workmodes = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);

				if (workmodes != null)
					return workmodes;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public PersonnelFiles getPersonnelFile() {
		return this.personnelFile;
	}

	public void setPersonnelFile(PersonnelFiles personnelFile) {
		this.personnelFile = personnelFile;
	}

	public List<CodeValue> getAllBusinessType() {
		try {
			List companyNatures = new ArrayList();
			String[] keys = { "code", "name" };
			String[] values = { "210", "客户属性" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code",
																			// Long.valueOf("210"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllProfessor() {
		try {
			List companyNatures = new ArrayList();
			String[] keys = { "code", "name" };
			String[] values = { "216", "职称" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllRank() {
		try {
			List companyNatures = new ArrayList();
			String[] keys = { "code", "name" };
			String[] values = { "215", "职级" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllGrade() {
		try {
			List companyNatures = new ArrayList();
			String[] keys = { "code", "name" };
			String[] values = { "217", "等级" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<Institution> getAllInsts() {
		List list = this.institutionManager.loadAllInstitution();
		Institution agency = new Institution();
		agency.setId(Long.valueOf(-1L));
		agency.setName(getText(""));
		list.add(0, agency);
		return list;
	}

	public List<Department> getAllDepts() {
		List list = new ArrayList();
		return list;
	}

	public List<Duty> getAllDutys() {
		List list = new ArrayList();
		return list;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public boolean isFirst() {
		return this.first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isSex() {
		return this.sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getBackvist() {
		return backvist;
	}
	public void setBackvist(String backvist) {
		this.backvist = backvist;
	}
}
