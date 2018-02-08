package com.yongjun.tdms.presentation.webwork.action.backvisit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditBackVisitAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final BackVisitManager backVisitManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactArchivesManager contactArchivesManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	private final UserManager userManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private BackVisit backVisit;
	private String fromType;
	private String openFlag;// 如果存在就是从客户管理、联系人管理=项目管理中的弹出修改页面，控制点击返回是关闭
	private CustomerInfo customerInfo;
	private StepInfo stepInfo;
	private PersonnelFiles personnelFiles;
	private DataManager dataManager;

	public EditBackVisitAction(CodeValueManager codeValueManager, BackVisitManager backVisitManager,
			PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager,
			ContactArchivesManager contactArchivesManager, UserManager userManager, EventNewManager eventNewManager,
			ProjectInfoManager projectInfoManager, EventTypeManager eventTypeManager,
			PersonnelFilesToUserManager personnelFilesToUserManager, DataManager dataManager) {
		this.codeValueManager = codeValueManager;
		this.backVisitManager = backVisitManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contactArchivesManager = contactArchivesManager;
		this.userManager = userManager;
		this.eventNewManager = eventNewManager;
		this.projectInfoManager = projectInfoManager;
		this.eventTypeManager = eventTypeManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.dataManager = dataManager;
	}

	public BackVisit getBackVisit() {
		return this.backVisit;
	}

	public void setBackVisit(BackVisit backVisit) {
		this.backVisit = backVisit;
	}

	public void prepare() throws Exception {
		if (this.backVisit == null)
			if (hasId("backVisit.id")) {
				this.backVisit = this.backVisitManager.loadBackVisit(getId("backVisit.id"));
			} else {
				this.backVisit = new BackVisit();
				if (this.userManager.getUser().getCode() != null) {
					List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
					if ((null != pfs) && (pfs.size() > 0)) {
						this.personnelFiles = ((PersonnelFiles) pfs.get(0));
						this.backVisit.setEmployee(this.personnelFiles);

						this.backVisit.setBackVisiter(this.personnelFiles.getName());
					}
				}
			}
		if (this.request.getParameter("openFlag") != null) {
			this.openFlag = this.request.getParameter("openFlag");
		}
	}

	public String save() {
		boolean isNew = this.backVisit.isNew();
		if (request.getParameter("fromType") != null) {
			fromType = request.getParameter("fromType");
		}

		if (hasId("customer.id")) {
			customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
			this.backVisit.setCustomerName(customerInfo.getName());
			this.backVisit.setCustomerInfo(customerInfo);
			if (isNew) {
				this.backVisit.setCustomerStating(customerInfo.getState());
				this.backVisit.setCustomerSteping(customerInfo.getStep());

			}
		}

		if (hasId("backVisitType.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("backVisitType.id"));
			this.backVisit.setBackVisitType(cv);
		}
		if (hasId("importanceType.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("importanceType.id"));
			this.backVisit.setImportanceType(cv);
		}
		if (hasId("projectInfo.id")) {
			ProjectInfo pInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			this.backVisit.setProjectInfo(pInfo);
			this.backVisit.setProjectName(pInfo.getName());
		}
		if (hasId("contactArchive.id")) {
			ContactArchives ca = this.contactArchivesManager.loadContactArchives(getId("contactArchive.id"));
			this.backVisit.setCaName(ca.getName());
			this.backVisit.setContactArchive(ca);
		}
		if (hasId("backVisiter.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("backVisiter.id"));
			this.backVisit.setBackVisiter(this.personnelFiles.getName());
			this.backVisit.setEmployee(this.personnelFiles);
		}
		if (hasId("backVisitWay.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("backVisitWay.id"));
			this.backVisit.setBackVisitWay(cv);
		}
		// if ("" != this.request.getParameter("customerStepingId")) {
		// CodeValue cv =
		// this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId"))));
		// this.backVisit.setCustomerSteping(cv);
		// }
		// if (hasId("customerSteped.id")) {
		// CodeValue cv =
		// this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
		// this.backVisit.setCustomerSteped(cv);
		// }
		// if ("" != this.request.getParameter("changeReason")) {
		// this.backVisit.setChangReason(this.request.getParameter("changeReason"));
		// }
		//
		// if ("" != this.request.getParameter("customerStatingId")) {
		// CodeValue cv =
		// this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStatingId"))));
		// this.backVisit.setCustomerStating(cv);
		// }
		// if (hasId("customerStated.id")) {
		// CodeValue cv =
		// this.codeValueManager.loadCodeValue(getId("customerStated.id"));
		// this.backVisit.setCustomerStated(cv);
		// }
		// if ("" != this.request.getParameter("changStateReason")) {
		// this.backVisit.setChangStateReason(this.request.getParameter("changStateReason"));
		// }

		if (hasId("employeesIds")) {
			this.backVisit.setEmployeesIds(this.request.getParameter("employeesIds"));
		}
		if (hasId("contactArchiveIds")) {
			this.backVisit.setContactArchiveIds(this.request.getParameter("contactArchiveIds"));
		}
		if (hasId("contactArchives")) {
			this.backVisit.setContactArchives(this.request.getParameter("contactArchives"));
		}
		if (hasId("employees")) {
			this.backVisit.setEmployees(this.request.getParameter("employees"));
		}

		// if (hasId("customerSteped.id")) {
		// CodeValue cve =
		// this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
		// CodeValue cvState =
		// this.codeValueManager.loadCodeValue(getId("customerStated.id"));
		// CustomerInfo cif = this.backVisit.getCustomerInfo();
		//
		// cif.setStep(cve);
		// cif.setState(cvState);
		// this.backVisit.setCustomerInfo(cif);
		// }
		if ("" != this.request.getParameter("isSaved")) {
			this.backVisit.setIsSaved(this.request.getParameter("isSaved"));
		}

		if (isNew) {

			try {
				List<BackVisit> backVisits = this.backVisitManager.loadByKey("customerInfo",
						this.backVisit.getCustomerInfo());
				Long sum = null;
				if (backVisits != null) {
					sum = Long.valueOf(backVisits.size() + 1);
				} else {
					sum = 1L;
				}
				customerInfo.setBackVisitSum(sum);

				Date da = this.backVisit.getBackVisitDate();
				customerInfo.setNearestBackVisitDate(da);
				customerInfo.setState(codeValueManager.loadByKey("code", "20001").get(0));
				customerInfo.setUnconnect(0L);
				customerInfoManager.storeCustomerInfo(customerInfo);

			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		if (isNew) {
			this.backVisit.setReplyTime(0L);
			this.backVisit.setSubmitNum(0l);
			this.backVisitManager.storeBackVisit(this.backVisit);

			// if (("0" == this.request.getParameter("gradeChange")) ||
			// ("0".equals(this.request.getParameter("gradeChange")))) {
			// CodeValue cve =
			// this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
			// CustomerInfo cif = this.backVisit.getCustomerInfo();
			// this.stepInfo = new StepInfo();
			// this.stepInfo.setBackVisitId(this.backVisit);
			// this.stepInfo.setCustomerId(cif);
			// this.stepInfo.setCustomerSteping(this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId")))));
			// this.stepInfo.setCustomerSteped(cve);
			// this.stepInfo.setChangeReason(this.request.getParameter("changeReason"));
			// this.backVisitManager.storeStepInfo(this.stepInfo);
			// }
		} else {
			if (this.backVisit.getIsSaved().equals("1")) {
				this.backVisit.setSubmitNum(this.backVisit.getSubmitNum() + 1);
			}
			this.backVisitManager.storeBackVisit(this.backVisit);
			// if (("0" == this.request.getParameter("gradeChange")) ||
			// ("0".equals(this.request.getParameter("gradeChange")))) {
			// CodeValue cve =
			// this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
			// CustomerInfo cif = this.backVisit.getCustomerInfo();
			// this.stepInfo = new StepInfo();
			// this.stepInfo.setBackVisitId(this.backVisit);
			// this.stepInfo.setCustomerId(cif);
			// this.stepInfo.setCustomerSteping(this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId")))));
			// this.stepInfo.setCustomerSteped(cve);
			// this.stepInfo.setChangeReason(this.request.getParameter("changeReason"));
			// this.backVisitManager.storeStepInfo(this.stepInfo);
			// }
		}
		if ("" != this.request.getParameter("isSaved") && this.request.getParameter("isSaved").endsWith("1")) {
			try {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10001");
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
				if (this.backVisit.getProjectInfo() != null) {
					map.put("users", this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.backVisit
							.getProjectInfo().getId(), this.backVisit.getProjectInfo().getCreateUser()));
//				} else {
//					map.put("users", this.personnelFilesToUserManager.loadUserIdToStrByEnable());
//				}
				map.put("backVisitId", this.backVisit.getId() + "");
				map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.backVisit.getBackVisitDate())+","+this.backVisit.getBackVisiter()+"拜访了:"+this.backVisit.getCaName());
				map.put("url", "backvisit/editBackVisit.html?openFlag=openFlag&backVisit.id="
						+ this.backVisit.getId());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				// HashMap mapData =new HashMap();
				// mapData.put("type", "10001");
				// mapData.put("thisMoney", "0");
				// mapData.put("lastMoney", "0");
				// mapData.put("submitNum", this.backVisit.getSubmitNum());
				// mapData.put("date", this.backVisit.getBackVisitDate());
				// this.dataManager.storeData(personnelFiles, mapData);
				}
				addActionMessage(getText("backvisit.submit.success",
						Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
				return "success";
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (isNew) {
			addActionMessage(getText("backvisit.add.success",
					Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));

			return "success";
		}
		addActionMessage(getText("backvisit.edit.success",
				Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
		// if (hasId("from")) {
		// String s=request.getParameter("from");
		// if(s.equals("h")||s=="h"){
		// return "success1";
		// }
		//
		// }
		return "success";
	}

	public List<CodeValue> getAllBackVisitWay() {
		List cust_types = new ArrayList();
		try {
			CodeValue custType = (CodeValue) this.codeValueManager.loadByKey("code", "007").get(0);
			cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
			if (cust_types != null) {
				CodeValue cv = new CodeValue();
				cv.setId(Long.valueOf(-1L));
				cv.setName(getText(""));
				cust_types.add(0, cv);
				return cust_types;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public List<CodeValue> getAllSteps() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "022");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(1)).getId());
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
			List one = this.codeValueManager.loadByKey("code", "200");
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

	public StepInfo getStepInfo() {
		return this.stepInfo;
	}

	public void setStepInfo(StepInfo stepInfo) {
		this.stepInfo = stepInfo;
	}

	public List<CodeValue> getAllBackVisitType() {
		List cust_types = new ArrayList();
		try {
			CodeValue custType = (CodeValue) this.codeValueManager.loadByKey("code", "888").get(0);
			cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
			if (cust_types != null) {
				CodeValue cv = new CodeValue();
				cv.setId(Long.valueOf(-1L));
				cv.setName(getText(""));
				cust_types.add(0, cv);
				return cust_types;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public List<CodeValue> getAllImportanceType() {
		try {
			List codes = new ArrayList();
			String[] keys = { "name", "code" };
			String[] values = { "重要程度", "212" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code",
																			// Long.valueOf("211"));
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

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	// public CodeValue getCustomerSteped()
	// {
	// return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	// }
}
