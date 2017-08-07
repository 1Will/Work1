package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditContractManagementAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ContractManagementManager contractManagementManager;
	private final CustomerInfoManager customerInfoManager;
	private final UserManager userManager;
	private final ContactArchivesManager contactArchivesManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final DepartmentManager departmentManager;
	private final InstitutionManager institutionManager;
	private final ProductListManager productListManager;
	private final ProjectInfoManager projectInfoManager;
	private final EventTypeManager eventTypeManager;
	private final EventNewManager eventNewManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private final ContactToHistoryManager contactToHistoryManager;
	private DataManager dataManager;

	private ContractManagement contractManagement = null;
	private CustomerInfo customerInfo = null;
	private ProjectInfo projectInfo = null;
	private Institution institution = null;
	private PersonnelFiles saleman = null;
	private ContactArchives linkman = null;
	private Department deparment;
	private CodeValue contractType;
	private CodeValue moneyType;
	private CodeValue payType;
	private CodeValue state;
	private String popWindowFlag;
	private PersonnelFiles personnelFiles;

	public EditContractManagementAction(ContractManagementManager contractManagementManager,
			CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager,
			CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager,
			DepartmentManager departmentManager, InstitutionManager institutionManager, UserManager userManager,
			ProductListManager productListManager, ProjectInfoManager projectInfoManager,
			EventTypeManager eventTypeManager, EventNewManager eventNewManager,
			PersonnelFilesToUserManager personnelFilesToUserManager, ContactToHistoryManager contactToHistoryManager,DataManager dataManager) {
		this.contractManagementManager = contractManagementManager;
		this.customerInfoManager = customerInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.contactArchivesManager = contactArchivesManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
		this.institutionManager = institutionManager;
		this.userManager = userManager;
		this.productListManager = productListManager;
		this.projectInfoManager = projectInfoManager;
		this.eventTypeManager = eventTypeManager;
		this.eventNewManager = eventNewManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.contactToHistoryManager = contactToHistoryManager;
		this.dataManager=dataManager;
	}

	public void prepare() throws Exception {
		if (null == this.contractManagement) {
			if (hasId("contractManagement.id")) {
				this.contractManagement = this.contractManagementManager
						.loadContractManagement(getId("contractManagement.id"));
				DecimalFormat format = new DecimalFormat("0.00");
				this.contractManagement.setContractMoney(new Double(format.format(this.productListManager
						.getSumTotalPrice(getId("contractManagement.id").longValue()))).doubleValue());
			} else {
				this.contractManagement = new ContractManagement();
				User user = getUser();
				if (null != user.getCode()) {
					List list = this.personnelFilesManager.loadByKey("code", user.getCode());

					if (null != list) {
						this.saleman = ((PersonnelFiles) list.get(0));
						this.contractManagement.setSaleman(this.saleman);
						personnelFiles=((PersonnelFiles) list.get(0));
					}
				}
			}
		}

		if (null == this.customerInfo) {
			if (hasId("customerInfo.id")) {
				this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			} else {
				this.customerInfo = null;
			}
		}

		if (null == this.linkman) {
			if (hasId("linkman.id")) {
				this.linkman = this.contactArchivesManager.loadContactArchives(getId("linkman.id"));
			} else {
				this.linkman = null;
			}
		}

		if (null == this.projectInfo) {
			if (hasId("project.id")) {
				this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("project.id"));
			} else {
				this.projectInfo = null;
			}
		}

		if (null == this.deparment) {
			if (hasId("deparment.id")) {
				this.deparment = this.departmentManager.loadDepartment(getId("deparment.id"));
			} else {
				this.deparment = null;
			}
		}

		if (null == this.contractType) {
			if (hasId("contractType.id")) {
				this.contractType = this.codeValueManager.loadCodeValue(getId("contractType.id"));
			} else {
				this.contractType = null;
			}
		}
		if (null == this.moneyType) {
			if (hasId("moneyType.id")) {
				this.moneyType = this.codeValueManager.loadCodeValue(getId("moneyType.id"));
			} else {
				this.moneyType = null;
			}
		}
		if (null == this.payType) {
			if (hasId("payType.id")) {
				this.payType = this.codeValueManager.loadCodeValue(getId("payType.id"));
			} else {
				this.payType = null;
			}
		}
		if (null == this.state) {
			if (hasId("state.id")) {
				this.state = this.codeValueManager.loadCodeValue(getId("state.id"));
			} else {
				this.state = codeValueManager.loadByKey("code", "06603").get(0);
			}
		}
		if (null == this.institution)
			if (hasId("institution.id")) {
				this.institution = this.institutionManager.loadInstitution(getId("institution.id"));
			} else
				this.institution = null;
		if (this.request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = this.request.getParameter("popWindowFlag");
		}
	}

	public String save() {
		boolean isNew = this.contractManagement.isNew();
		this.contractManagement.setOrganization(this.userManager.getOrganization());
		if (isNew) {
			String code = autoCompleteCode();
			this.contractManagement.setCode(code);
		}

		if (hasId("payWay.id")) {
			CodeValue payWay = this.codeValueManager.loadCodeValue(getId("payWay.id"));
			this.contractManagement.setPayWay(payWay);
		}

		if (hasId("saleman.id")) {
			this.saleman = this.personnelFilesManager.loadPersonnel(getId("saleman.id"));
		}
		this.contractManagement.setIsSaved(request.getParameter("contractManagement.isSaved"));
		try {
			this.contractManagement.setCustomerInfo(this.customerInfo);
			this.contractManagement.setLinkman(this.linkman);
			this.contractManagement.setProject(this.projectInfo);
			this.contractManagement.setContractType(this.contractType);
			this.contractManagement.setSaleman(this.saleman);
			this.contractManagement.setDeparment(this.deparment);
			this.contractManagement.setMoneyType(this.moneyType);
			this.contractManagement.setPayType(this.payType);
			this.contractManagement.setState(this.state);
			this.contractManagement.setInstitution(this.institution);
			this.contractManagement.setDeparment(this.deparment);
			
			if(isNew){
				this.contractManagement.setSubmitNum(0l);
			}else {
				if(this.contractManagement.getIsSaved().equals("1")){
	            	 this.contractManagement.setSubmitNum(this.contractManagement.getSubmitNum()+1);
	             }
			}
			
			this.contractManagementManager.storeContractManagement(this.contractManagement);

			String submit = "";
			if ("1".equals(this.request.getParameter("contractManagement.isSaved"))) {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10006");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					eventType = new EventType();
					eventType.setId(7L);
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName("合同事件");
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.projectInfo.getId(),
						this.projectInfo.getCreateUser());
				map.put("users", pids);
				map.put("contractManagementId", this.contractManagement.getId() + "");
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				
				 HashMap mapData =new HashMap();
	              mapData.put("type", "10006");
	              mapData.put("thisMoney", contractManagement.getContractMoney());
	              mapData.put("lastMoney",contractManagement.getLastSubmitMoney() );
				mapData.put("submitNum", this.contractManagement.getSubmitNum());
				mapData.put("date", contractManagement.getCiemdinghTime());
				this.dataManager.storeData(getPersonnelF(), mapData);
				this.contractManagement.setLastSubmitMoney(contractManagement.getContractMoney());
				this.contractManagementManager.storeContractManagement(this.contractManagement);//更新上次提交金额为本次的合同金额
				submit = "submit";
			}

			// 仅当项目状态为立项的情况下，合同保存后，项目状态改为合同
			if ("20101".equals(projectInfo.getState().getCode())) {
				this.projectInfo.setState(this.codeValueManager.loadByKey("code", "20102").get(0));
				this.projectInfoManager.storeProjectInfo(projectInfo);
			}

			if (isNew) {
				ContactToHistory history = new ContactToHistory();
				history.setCreator(this.userManager.getUser().getName());
				history.setLastOperator(this.userManager.getUser().getName());
				history.setContractManagement(this.contractManagement);
				history.setConment("创建合同");
				this.contactToHistoryManager.storeContactToHistory(history);
			} else {
				ContactToHistory history = new ContactToHistory();
				// history.setCreator(this.userManager.getUser().getName());
				history.setLastOperator(this.userManager.getUser().getName());
				history.setContractManagement(this.contractManagement);
				history.setConment("修改基本信息");
				this.contactToHistoryManager.storeContactToHistory(history);
			}

			if (isNew) {
				addActionMessage(getText("contractManagement.add.success"));
				return "new";
			}
			if ("submit".equals(submit)) {
				addActionMessage(getText("contractManagement.submit.success"));
			} else {
				addActionMessage(getText("contractManagement.edit.success"));
			}

			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			if (isNew) {
				addActionMessage(getText("contractManagement.add.error"));
				return "new";
			}
			addActionMessage(getText("contractManagement.edit.error"));
		}
		return "success";
	}

	public List<Department> getAllDepartment() {
		List depts = this.departmentManager.loadAllDepartments();
		return depts;
	}

	public PersonnelFiles getPersonnelF() throws Exception {
		List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());

		if ((null != pfs) && (pfs.size() > 0)) {
			return (PersonnelFiles) pfs.get(0);
		}
		return null;
	}

	public String getStateDefault() {
		try {
			List list = this.codeValueManager.loadByKey("code", "06603");
			if ((null != list) && (list.size() > 0))
				return ((CodeValue) list.get(0)).getId().toString();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CodeValue> getAllComplaintType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("064"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<CodeValue> getAllPayType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("065"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			List reCodeValues = new ArrayList();
			if (codes.size() >= 1) {
				for (int i = 0; i < codes.size(); i++) {
					reCodeValues.add(codes.get(codes.size() - i - 1));
				}
			}
			return reCodeValues;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<CodeValue> getAllState() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("066"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<CodeValue> getAllMoneyType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("067"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public String autoCompleteCode() {
		String prefix = "HTGLBM";
		String maxCode = this.contractManagementManager.getMaxPFCode(prefix);
		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return prefix + "-00000" + num;
			if (num < 100)
				return prefix + "-0000" + num;
			if (num < 1000)
				return prefix + "-000" + num;
			if (num < 10000)
				return prefix + "-00" + num;
			if (num < 100000) {
				return prefix + "-0" + num;
			}
			return prefix + "-" + num;
		}
		return prefix + "-000001";
	}

	public List<Institution> getAllInsts() {
		List list = this.institutionManager.loadAllInstitution();
		return list;
	}

	public List<Department> getAllDepts() {
		List list = new ArrayList();
		return list;
	}

	public List<CodeValue> getAllPayWay() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public User getUser() {
		return this.userManager.getUser();
	}

	public ContractManagement getContractManagement() {
		return this.contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ProjectInfoManager getProjectInfoManager() {
		return projectInfoManager;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
	

}
