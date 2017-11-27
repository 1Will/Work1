package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.supplier.SupplierManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditContactArchivesAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ContactArchivesManager contactArchivesManager;
	private final CodeValueManager codeValueManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactToHistoryManager contactToHistoryManager;
	private final ProjectInfoContractManager projectInfoContractManager;
	private final UserManager userManager;
	private final AreaManager areaManager;
	private final SupplierManager supplierManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private ProjectInfoManager projectInfoManager;
	private ContactArchives contactArchives;
	private String birthplace;
	private CodeValue nationality;
	private CustomerInfo customer;
	private CodeValue customerType;
	private CodeValue temperament;
	private CodeValue type;
	private boolean sex;
	private Supplier supplier;
	private ProjectInfo projectInfo;
	private String popWindowFlag;
	private String notNewFlag;
	// 区分提交和编辑，不用set和get
	String submit = null;

	public EditContactArchivesAction(ContactArchivesManager contactArchivesManager, CodeValueManager codeValueManager,
			CustomerInfoManager customerInfoManager, AreaManager areaManager, SupplierManager supplierManager,
			ProjectInfoManager projectInfoManager, ContactToHistoryManager contactToHistoryManager,
			UserManager userManager, ProjectInfoContractManager projectInfoContractManager,
			EventNewManager eventNewManager, EventTypeManager eventTypeManager,
			PersonnelFilesToUserManager personnelFilesToUserManager) {
		this.contactArchivesManager = contactArchivesManager;
		this.codeValueManager = codeValueManager;
		this.customerInfoManager = customerInfoManager;
		this.areaManager = areaManager;
		this.supplierManager = supplierManager;
		this.projectInfoManager = projectInfoManager;
		this.contactToHistoryManager = contactToHistoryManager;
		this.userManager = userManager;
		this.projectInfoContractManager = projectInfoContractManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
	}

	public void prepare() throws Exception {
		if (null == this.contactArchives) {
			if (hasId("contactArchives.id")) {
				this.contactArchives = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));

				this.sex = this.contactArchives.isSex();
			} else {
				this.contactArchives = new ContactArchives();
			}
		}

		if (hasId("customer.id")) {
			this.customer = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
			this.contactArchives.setCustomerName(this.customer);
			this.contactArchives.setCustName(this.customer.getName());
			this.contactArchives.setIndustry(this.customer.getIndustry().getName());
			this.contactArchives.setCustomerType(this.customer.getCustomerType());
			this.contactArchives.setCustomerInfoCode(this.customer.getCode());
		} else if (this.contactArchives.getCustomerName() != null
				&& this.contactArchives.getCustomerName().getId() != null) {
			this.customer = this.contactArchives.getCustomerName();
		}

		if (hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
			this.contactArchives.setSupplier(this.supplier);
		}
		if (hasId("projectInfo.id")) {
			this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			this.contactArchives.setProjectInfo(this.projectInfo);
			this.contactArchives.setProName(this.projectInfo.getName());
		}
		if (request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
		if (request.getParameter("notNewFlag") != null) {
			this.notNewFlag = request.getParameter("notNewFlag");
		}
	}

	public String save() {
		boolean isNew = this.contactArchives.isNew();
		try {
			if ((null != this.request.getParameter("contactArchivesSex"))
					&& ("" != this.request.getParameter("contactArchivesSex"))) {
				String flag = this.request.getParameter("contactArchivesSex");
				if (flag.equals("0"))
					this.contactArchives.setSex(false);
				else {
					this.contactArchives.setSex(true);
				}
			}

			if (!StringUtils.isEmpty("contactArchives.birthplace")) {
				String bs = this.request.getParameter("contactArchives.birthplace");
				this.contactArchives.setBirthplace(bs);
			}
//			if (!StringUtils.isEmpty("contactArchives.mobilePhone")) {
//				String phon = this.request.getParameter("contactArchives.mobilePhone");
//				this.contactArchives.setMobilePhone(phon);
//			}

			if (hasId("leader.id")) {
				ContactArchives leader = this.contactArchivesManager.loadContactArchives(getId("leader.id"));
				this.contactArchives.setLeader(leader);
			}

			if (hasId("nationality.id")) {
				// this.nationality =
				// this.codeValueManager.loadCodeValue(getId("nationality.id"));
				nationality = new CodeValue();
				nationality.setId(getId("nationality.id"));
			} else {
				this.nationality = null;
			}
			this.contactArchives.setNationality(this.nationality);

			if (hasId("temperament.id"))
				this.temperament = this.codeValueManager.loadCodeValue(getId("temperament.id"));
			else {
				this.temperament = null;
			}
			this.contactArchives.setTemperament(this.temperament);

			if (hasId("type.id")) {
				// this.type =
				// this.codeValueManager.loadCodeValue(getId("type.id"));
				type = new CodeValue();
				type.setId(getId("type.id"));
			}

			if (!StringUtils.isEmpty("contactArchives.comment")) {
				this.contactArchives.setComment(this.request.getParameter("contactArchives.comment"));
				this.contactArchives.setType(this.type);
			}

			if (hasId("contactArchives.enterpriseSynopsis")) {
				this.contactArchives.setEnterpriseSynopsis(this.request
						.getParameter("contactArchives.enterpriseSynopsis"));
			}
			if (hasId("businessType.id")) {
				CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
				this.contactArchives.setBusinessType(cv);
			}
			if (isNew) {
				this.contactArchives.setCreator(this.userManager.getUser().getName());
				this.contactArchives.setIsKeyContact("0");
			}
			this.contactArchives.setIsSaved(this.request.getParameter("isSaved"));
			this.contactArchivesManager.storeContactArchives(this.contactArchives);
			
			if ("1".equals(this.request.getParameter("isSaved"))) {
				try {
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10009");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						eventType = new EventType();
						eventType.setId(10L);
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					event.setUserId(this.userManager.getUser().getId() + "");
					Map<String, String> map = new HashMap();
					
					CodeValue code = this.contactArchives.getCustomerName().getBusinessType();
					String pids = "";
					if(code == null){
						pids = this.personnelFilesToUserManager.loadUserIdToStrByEnable();
					}else {
						pids = this.personnelFilesToUserManager.loadUserIdToStrByType(code.getCode());
					}
					map.put("users", pids);
					map.put("contactArchivesId", this.contactArchives.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.contactArchives.getCreatedTime())+","+this.contactArchives.getCreator()+"提交了联系人:"+this.contactArchives.getName());
					map.put("url", "customerRelationship/editContactArchives.html?popWindowFlag=popWindowFlag&contactArchives.id="+this.contactArchives.getId());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					submit = "submit";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			ProjectInfoContract projectInfoContract = null;
			// 先判断项目是否绑定了最新的联系人和其业务属性
			if (this.contactArchives.getProjectInfo() != null && this.contactArchives.getBusinessType() != null) {

				String[] arrayKey = { "projectInfo.id", "contactArchives.id" };
				Long[] arrayValue = { this.contactArchives.getProjectInfo().getId(), this.contactArchives.getId() };
				// 根据项目id和联系人id查询关系表中是否有记录， 有的话就修改，没有就增加
				List<ProjectInfoContract> list = this.projectInfoContractManager.loadByKeyArray(arrayKey, arrayValue);
				if (list != null && list.size() > 0) {
					projectInfoContract = list.get(0);
					projectInfoContract.setBusinessType(contactArchives.getBusinessType());
					projectInfoContract.setOutline(this.contactArchives.getOutline());
					this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);
				} else {
					projectInfoContract = new ProjectInfoContract();
					projectInfoContract.setProjectInfo(this.contactArchives.getProjectInfo());
					projectInfoContract.setContactArchives(this.contactArchives);
					projectInfoContract.setBusinessType(this.contactArchives.getBusinessType());
					projectInfoContract.setOutline(this.contactArchives.getOutline());
					this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);

				}

			}

			if (isNew) {
				ContactToHistory history = new ContactToHistory();
				history.setCreator(this.userManager.getUser().getName());
				history.setLastOperator(this.userManager.getUser().getName());
				history.setContactArchivesId(this.contactArchives);
				history.setConment("创建联系人");
				this.contactToHistoryManager.storeContactToHistory(history);

			} else {
				ContactToHistory history = new ContactToHistory();
				// history.setCreator(this.userManager.getUser().getName());
				history.setLastOperator(this.userManager.getUser().getName());
				history.setContactArchivesId(this.contactArchives);
				history.setConment("修改基本信息");
				this.contactToHistoryManager.storeContactToHistory(history);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("contactArchives.add.error",
						Arrays.asList(new Object[] { this.contactArchives.getName() })));

				return "error";
			}
			addActionMessage(getText("contactArchives.edit.error",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));

			return "error";
		}

		if (isNew) {
			addActionMessage(getText("contactArchives.add.success",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));
			return "new";
		}
		if ("submit".equals(submit)) {
			addActionMessage(getText("contactArchives.submit.success",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));
		} else {
			addActionMessage(getText("contactArchives.edit.success",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));
		}
		//判断是否需要同步主要联系人
		if("1".equals(contactArchives.getIsKeyContact())){
			//如果是主联系人，要更新customer表信息
			this.customer.setKeyContacter(contactArchives.getName());
			customerInfoManager.storeCustomerInfo(this.customer);
		}
		return "success";
	}

	public String saveContactInformation() {
		boolean isNew = this.contactArchives.isNew();
		try {
			if (!StringUtils.isEmpty("contactArchives.mobilePhone")) {
				String phon = this.request.getParameter("contactArchives.mobilePhone");
				this.customer.setMobilePhone(phon);
			}
			if (!StringUtils.isEmpty("contactArchives.chuanzhen")) {
				String cz = this.request.getParameter("contactArchives.chuanzhen");
				this.customer.setChuanzhen(cz);
			}

			if (!StringUtils.isEmpty("contactArchives.chuanzhen")) {
				String cz = this.request.getParameter("contactArchives.chuanzhen");
				this.customer.setChuanzhen(cz);
			}

			this.contactArchivesManager.storeContactArchives(this.contactArchives);

			ContactToHistory history = new ContactToHistory();
			// history.setCreator(this.userManager.getUser().getName());
			history.setLastOperator(this.userManager.getUser().getName());
			history.setContactArchivesId(this.contactArchives);
			history.setConment("修改联系方式信息");
			this.contactToHistoryManager.storeContactToHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("contactArchives.add.error",
						Arrays.asList(new Object[] { this.contactArchives.getName() })));

				return "error";
			}
			addActionMessage(getText("contactArchives.edit.error",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));

			return "error";
		}

		if (isNew) {
			addActionMessage(getText("contactArchives.add.success",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));

			return "new";
		}
		addActionMessage(getText("contactArchives.edit.success",
				Arrays.asList(new Object[] { this.contactArchives.getName() })));

		return "success";
	}

	public String saveAdditional() {
		boolean isNew = this.contactArchives.isNew();
		try {

			if (hasId("nationality.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("nationality.id"));
				this.contactArchives.setNationality(code);
			}

			if (hasId("temperament.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("temperament.id"));
				this.contactArchives.setTemperament(code);
			}

			if (hasId("bloodType.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("bloodType.id"));
				this.contactArchives.setBloodType(code);
			}

			if (hasId("constellation.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("constellation.id"));
				this.contactArchives.setConstellation(code);
			}

			if (hasId("chineseZodiac.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("chineseZodiac.id"));
				this.contactArchives.setChineseZodiac(code);
			}

			if (hasId("enneagram.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("enneagram.id"));
				this.contactArchives.setEnneagram(code);
			}

			if (hasId("religiousBelief.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("religiousBelief.id"));
				this.contactArchives.setReligiousBelief(code);
			}

			if (hasId("health.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("health.id"));
				this.contactArchives.setHealth(code);
			}

			if (hasId("vision.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("vision.id"));
				this.contactArchives.setVision(code);
			}

			if (hasId("maritalStatus.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("maritalStatus.id"));
				this.contactArchives.setMaritalStatus(code);
			}

			if (hasId("education.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("education.id"));
				this.contactArchives.setEducation(code);
			}

			if (hasId("politicalOutlook.id")) {
				CodeValue code = this.codeValueManager.loadCodeValue(getId("politicalOutlook.id"));
				this.contactArchives.setPoliticalOutlook(code);
			}

			this.contactArchivesManager.storeContactArchives(this.contactArchives);
			ContactToHistory history = new ContactToHistory();
			// history.setCreator(this.userManager.getUser().getName());
			history.setLastOperator(this.userManager.getUser().getName());
			history.setContactArchivesId(this.contactArchives);
			history.setConment("修改附加信息");
			this.contactToHistoryManager.storeContactToHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("contactArchives.add.error",
						Arrays.asList(new Object[] { this.contactArchives.getName() })));
				return "error";
			}
			addActionMessage(getText("contactArchives.edit.error",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));
			return "error";
		}

		if (isNew) {
			addActionMessage(getText("contactArchives.add.success",
					Arrays.asList(new Object[] { this.contactArchives.getName() })));
			return "new";
		}
		addActionMessage(getText("contactArchives.edit.success",
				Arrays.asList(new Object[] { this.contactArchives.getName() })));
		return "success";
	}

	// 血型
	public List<CodeValue> getAllBlood() {
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("203"));
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	// 生肖
	public List<CodeValue> getAllChineseZodiac() {
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("204"));
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	// 星座
	public List<CodeValue> getAllConstellation() {
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("205"));
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	// 星座
	public List<CodeValue> getAllEnneagram() {
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("206"));
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllNationality() {
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("004"));
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<Area> getAllBirthplace() {
		try {
			List areas = new ArrayList();
			List list = this.areaManager.loadByKey("type", "province");
			if ((null != list) && (list.size() > 0)) {
				areas.addAll(list);
			}
			return areas;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllTemperament() {
		try {
			List temperaments = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("014"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					temperaments.addAll(list);
				}
			}
			return temperaments;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllTypes() {
		try {
			List types = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("010"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					types.addAll(list);
				}
			}
			return types;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
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

	public List<CodeValue> getAllReligion() {
		List list = new ArrayList();
		try {
			String[] keyNames = { "code" };
			Object[] keyValues = { "019" };
			List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);

			if ((null != cvs) && (cvs.size() > 0)) {
				CodeValue cv = (CodeValue) cvs.get(0);
				keyNames = new String[] { "parentCV" };
				keyValues = new Object[] { cv.getId() };
				list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
			}

		} catch (Exception e) {
		}

		return list;
	}

	public List<CodeValue> getAllBusinessType() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("207"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}

			}

			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllHealth() {
		List list = new ArrayList();
		try {
			String[] keyNames = { "code" };
			Object[] keyValues = { "018" };
			List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);

			if ((null != cvs) && (cvs.size() > 0)) {
				CodeValue cv = (CodeValue) cvs.get(0);
				keyNames = new String[] { "parentCV" };
				keyValues = new Object[] { cv.getId() };
				list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
			}

		} catch (Exception e) {
		}

		return list;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public CodeValue getType() {
		return this.type;
	}

	public void setType(CodeValue type) {
		this.type = type;
	}

	public ContactArchives getContactArchives() {
		return this.contactArchives;
	}

	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}

	public CodeValue getNationality() {
		return this.nationality;
	}

	public void setNationality(CodeValue nationality) {
		this.nationality = nationality;
	}

	public boolean isSex() {
		return this.sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public CodeValue getTemperament() {
		return this.temperament;
	}

	public void setTemperament(CodeValue temperament) {
		this.temperament = temperament;
	}

	public CodeValue getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CodeValue customerType) {
		this.customerType = customerType;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}

	public String getNotNewFlag() {
		return notNewFlag;
	}

	public void setNotNewFlag(String notNewFlag) {
		this.notNewFlag = notNewFlag;
	}
}
