package com.yongjun.tdms.presentation.webwork.action.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditProjectInfoAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactArchivesManager contactArchivesManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private final ProjectInfoContractManager projectInfoContractManager;
	private final ProjectInfoCustomerManager projectInfoCustomerManager;
	private final UserManager userManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private ProjectInfo projectInfo;
	private ProjectInfoPersonnels projectInfoPersonnels;
	private String openFlag;
	private String notNewFlag;
	private DataManager dataManager;

	private PersonnelFiles personnelFiles;
    private Long businessTypeId; //业务属性id
	public EditProjectInfoAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager,
			ContactArchivesManager contactArchivesManager, UserManager userManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager,
			ProjectInfoContractManager projectInfoContractManager, EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, PersonnelFilesToUserManager personnelFilesToUserManager,ProjectInfoCustomerManager projectInfoCustomerManager,DataManager dataManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contactArchivesManager = contactArchivesManager;
		this.userManager = userManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
		this.projectInfoContractManager = projectInfoContractManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.projectInfoCustomerManager=projectInfoCustomerManager;
		this.dataManager=dataManager;
	}

	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void prepare() throws Exception {
		if (this.projectInfo == null)
			if (hasId("projectInfo.id")) {
				this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			} else {
				this.businessTypeId=codeValueManager.loadByKey("code","20702").get(0).getId();
				this.projectInfo = new ProjectInfo();
				if (this.userManager.getUser().getCode() != null) {
					List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
					if ((null != pfs) && (pfs.size() > 0)) {
						this.personnelFiles = ((PersonnelFiles) pfs.get(0));
					}
				}
			}
		if (this.request.getParameter("openFlag") != null) {
			this.openFlag = this.request.getParameter("openFlag");
		}
		if (request.getParameter("notNewFlag") != null) {
			this.notNewFlag = request.getParameter("notNewFlag");
		}
	}

	public String save() throws NumberFormatException, DaoException {
		this.projectInfoPersonnels = new ProjectInfoPersonnels();
		boolean isNew = this.projectInfo.isNew();

		if (hasId("customer.id")) {
			CustomerInfo ci = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
			this.projectInfo.setCustomer(ci);
		}

		if (request.getParameter("projectInfo.isSaved") != null) {
			this.projectInfo.setIsSaved(request.getParameter("projectInfo.isSaved"));
		}

		if (hasId("stateId")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("stateId"));
			this.projectInfo.setState(cv);
		}

		if (hasId("contact.id")) {
			ContactArchives ca = this.contactArchivesManager.loadContactArchives(getId("contact.id"));
			this.projectInfo.setContact(ca);
		}
		if (hasId("businessType.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
			this.projectInfo.setBusinessType(cv);
		}
		if (hasId("controller.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("controller.id"));
			this.projectInfo.setController(personnelFiles);
			this.projectInfoPersonnels.setProPerson(personnelFiles);
		}
		User u = this.userManager.getUser();
		if (isNew) {
			// CodeValue cv = this.codeValueManager.loadCodeValue(464l);//立项
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("20101"));
			if ((null != one) && (one.size() > 0)) {
				CodeValue co = (CodeValue) one.get(0);
				this.projectInfo.setState(co);
			}
			this.projectInfo.setSubmitNum(0l);
			this.projectInfo.setCreator(u.getName());
			this.projectInfo.setLastOperator(u.getName());
			this.projectInfo.setCreateUser(u);

		} else {
			if (hasId("stateId")) {
				CodeValue cv = this.codeValueManager.loadCodeValue(getId("stateId"));
				this.projectInfo.setState(cv);
			}
			if(this.projectInfo.getIsSaved().equals("1")){
           	 this.projectInfo.setSubmitNum(this.projectInfo.getSubmitNum()+1);
            } 
			this.projectInfo.setLastOperator(u.getName());
		}
		// 保存项目
		this.projectInfoManager.storeProjectInfo(projectInfo);
		
		String submit = null;
		if ("1".equals(this.request.getParameter("projectInfo.isSaved"))) {
			try {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10003");
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
				String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.projectInfo.getId(),
						this.projectInfo.getCreateUser());
				map.put("users", pids);
				map.put("projectInfoId", this.projectInfo.getId() + "");
				map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.projectInfo.getCreatedTime())+","+this.projectInfo.getCreator()+"提交了项目:"+this.projectInfo.getName());
				map.put("url", "projectInfo/editProjectInfo.html?openFlag=openFlag&projectInfo.id="+this.projectInfo.getId());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				
//				 HashMap mapData =new HashMap();
//	              mapData.put("type", "10003");
//	              mapData.put("thisMoney", "0");
//	              mapData.put("lastMoney", "0");
//				mapData.put("submitNum", this.projectInfo.getSubmitNum());
//				this.dataManager.storeData(getPersonnelF(), mapData);
				
				submit = "submit";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		ProjectInfoContract projectInfoContract = null;
		// 先判断项目是否绑定了最新的联系人和其业务属性
		if (this.projectInfo.getContact() != null && this.projectInfo.getBusinessType() != null) {

			String[] arrayKey = { "projectInfo.id", "contactArchives.id" };
			Long[] arrayValue = { projectInfo.getId(), projectInfo.getContact().getId() };
			// 根据项目id和联系人id查询关系表中是否有记录， 有的话就修改，没有就增加
			List<ProjectInfoContract> list = this.projectInfoContractManager.loadByKeyArray(arrayKey, arrayValue);
			if (list != null && list.size() > 0) {

				projectInfoContract = list.get(0);
				projectInfoContract.setBusinessType(projectInfo.getBusinessType());
				projectInfoContract.setOutline(this.projectInfo.getConOutline());
				this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);
			} else {
				projectInfoContract = new ProjectInfoContract();
				projectInfoContract.setProjectInfo(this.projectInfo);
				projectInfoContract.setContactArchives(this.projectInfo.getContact());
				projectInfoContract.setBusinessType(this.projectInfo.getBusinessType());
				projectInfoContract.setOutline(this.projectInfo.getConOutline());
				this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);

			}

		}

		if (isNew) {
			this.projectInfoPersonnels.setProjectInfo(projectInfo);
			try {
				List one = this.codeValueManager.loadByKey("code", Long.valueOf("20201"));
				if ((null != one) && (one.size() > 0)) {
					CodeValue co = (CodeValue) one.get(0);
					this.projectInfoPersonnels.setBusinessType(co);
				}
				this.projectInfoPersonnels.setOutline("项目负责人");
				if (hasId("customer.id")) {
					CustomerInfo ci = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
					ProjectInfoCustomer pm = new ProjectInfoCustomer();
					pm.setCustomerInfo(ci);
					pm.setProjectInfo(projectInfo);
					pm.setIsKeyCustomer("1");
					this.projectInfoCustomerManager.storeProjectInfoCustomer(pm);
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (DaoException e) {
				e.printStackTrace();
			}
			this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(projectInfoPersonnels);

		}

		if (isNew) {
			addActionMessage(getText("projectInfo.add.success",
					Arrays.asList(new Object[] { this.projectInfo.getName() })));
			return "success";
		}
		if (submit != null) {
			addActionMessage(getText("projectInfo.submit.success",
					Arrays.asList(new Object[] { this.projectInfo.getName() })));
		} else {
			addActionMessage(getText("projectInfo.edit.success",
					Arrays.asList(new Object[] { this.projectInfo.getName() })));
		}
		return "success";
	}

	public UserManager getUserManager() {
		return this.userManager;
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

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("201"));
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

	public ProjectInfoPersonnels getProjectInfoPersonnels() {
		return projectInfoPersonnels;
	}

	public void setProjectInfoPersonnels(ProjectInfoPersonnels projectInfoPersonnels) {
		this.projectInfoPersonnels = projectInfoPersonnels;
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getNotNewFlag() {
		return notNewFlag;
	}

	public void setNotNewFlag(String notNewFlag) {
		this.notNewFlag = notNewFlag;
	}
	public PersonnelFiles getPersonnelF() throws Exception {
		List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());

		if ((null != pfs) && (pfs.size() > 0)) {
			return (PersonnelFiles) pfs.get(0);
		}
		return null;
	}
	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	
	// public CodeValue getCustomerSteped()
	// {
	// return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	// }
}
