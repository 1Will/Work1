package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectPartner.ProjectPartnerManager;
import com.yongjun.tdms.service.supplier.SupplierManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListContactArchivesAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ContactArchivesManager contactArchivesManager;
	private final CodeValueManager codeValueManager;
	private final SupplierManager supplierManager;
	private final ContactToHistoryManager contactToHistoryManager;
	private final ProjectPartnerManager projectPartnerManager;
	private final ProjectInfoContractManager projectInfoContractManager;
	private final GroupManager groupManager;
	private List<ContactArchives> cas;
	private Long customerId;
	private Long projectInfoId;
	private Long customerTypeId;
	private String backVistiFlag;
	private String backVisitCheckBox;
	private String contactArchiveIds;
	private Supplier supplier;
	private PersonnelFilesManager personnelFilesManager;

	public ListContactArchivesAction(ContactArchivesManager contactArchivesManager, CodeValueManager codeValueManager,
			SupplierManager supplierManager, ContactToHistoryManager contactToHistoryManager,
			ProjectPartnerManager projectPartnerManager, PersonnelFilesManager personnelFilesManager,
			ProjectInfoContractManager projectInfoContractManager,GroupManager groupManager) {
		this.contactArchivesManager = contactArchivesManager;
		this.codeValueManager = codeValueManager;
		this.supplierManager = supplierManager;
		this.contactToHistoryManager = contactToHistoryManager;
		this.projectPartnerManager = projectPartnerManager;
		this.personnelFilesManager = personnelFilesManager;
		this.projectInfoContractManager = projectInfoContractManager;
		this.groupManager = groupManager;
	}

	public void prepare() throws Exception {
		if (hasIds("contactArchivesIds")) {
			this.cas = this.contactArchivesManager.loadAllContactArchives(getIds("contactArchivesIds"));
		}

		if (hasId("type.id")) {
			this.customerTypeId = getId("type.id");
		}

		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
			setFirst(false);
		}
		if (hasId("projectInfo.id")) {
			this.projectInfoId = getId("projectInfo.id");
			setFirst(false);
		}
		if (hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
			setFirst(false);
		}
		if (hasId("backVisitFlag")) {
			this.backVistiFlag = this.request.getParameter("backVisitFlag");
			if (hasId("customer.id")) {
				this.customerId = Long.valueOf(this.request.getParameter("customer.id"));
			}
		}
		if (hasId("backVisitCheckBox")) {
			this.backVisitCheckBox = this.request.getParameter("backVisitCheckBox");
		}

		if (hasIds("contactInfoIds"))
			this.cas = this.contactArchivesManager.loadAllContactArchives(getIds("contactInfoIds"));

		if (hasId("contactArchiveIds_a")) {
			this.contactArchiveIds = request.getParameter("contactArchiveIds_a");
		}
	}

	protected String getAdapterName() {
		return "contactArchivesHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();

//		PersonnelFiles personnelFiles = null;
//		try {
//			if(isCustomerGroup()){
//				List<PersonnelFiles> tempList = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
//				if (tempList != null && tempList.size() > 0) {
//					personnelFiles = tempList.get(0);
//					if (personnelFiles.getBusinessType() != null) {
//						if (personnelFiles.getBusinessType().getName().equals("军品")
//								|| personnelFiles.getBusinessType().getName().equals("民品")) {
//							map.put("businessType", "%" + personnelFiles.getBusinessType().getName() + "%");
//						}
//					}
//				}
//			}else {
//				List<PersonnelFiles> tempList = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
//				if (tempList != null && tempList.size() > 0) {
//					personnelFiles = tempList.get(0);
//					if (personnelFiles.getDept() != null) {
//						map.put("classificationId", personnelFiles.getDept().getId());
//					}
//				}
//			}
//			
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
		if (hasId("contactArchives.creator")) {
			User user = this.userManager.loadUser(getId("contactArchives.creator"));
			map.put("contactArchives.creator", user.getName());
		}
		if (hasId("sex")) {
			map.put("sex", Boolean.valueOf(this.request.getParameter("sex")));
		}
		if (hasId("customerInfo.id")) {
			map.put("customerId", getId("customerInfo.id"));
		}
		if (hasId("customer.id")) {
			map.put("customerId", getId("customer.id"));
		}
		if (hasId("contactArchives.createdTime")) {
			map.put("contactArchives.createdTime", this.request.getParameter("contactArchives.createdTime") + "%");
		}
		if (hasId("projectInfo.id")) {
			map.put("projectInfoId", getId("projectInfo.id"));
		}
		if (hasId("con_Project.id")) {
			List<ProjectPartner> projectPartners = null;
			List<Long> conIds = new ArrayList<Long>();
			try {
				projectPartners = projectPartnerManager.loadByKey("projectInfo.id", getId("con_Project.id"));
			} catch (DaoException e) {
				e.printStackTrace();
			}
			if (projectPartners != null) {
				for (int i = 0; i < projectPartners.size(); i++) {
					conIds.add(projectPartners.get(i).getCustomerInfo().getId());
				}
				System.out.println(conIds.toString().replace("[", "").replace("]", ""));
				map.put("con_projectIds", conIds);
			} else {
				map.put("con_projectIds", "0");
			}
		}

		if (hasId("projectId")) {
			try {
				List<ProjectInfoContract> projectInfoContracts = this.projectInfoContractManager.loadByKey(
						"projectInfo.id", getId("projectId"));
				List<Long> pcIds = new ArrayList<Long>();
				if (projectInfoContracts != null && projectInfoContracts.size() > 0) {
					for (int i = 0; i < projectInfoContracts.size(); i++) {
						pcIds.add(projectInfoContracts.get(i).getContactArchives().getId());
					}
				}
				if (pcIds != null && pcIds.size() > 0) {
					map.put("caIds", pcIds);
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}

		}
		if (hasId("projectInfoCus")) {
			String projectInfoCus = this.request.getParameter("projectInfoCus");
			String[] alStrings = projectInfoCus.split(",");
			List<Long> cList = new ArrayList<Long>();
			if (alStrings != null && alStrings.length > 0) {
				for (int i = 0; i < alStrings.length; i++) {
					cList.add(Long.parseLong(alStrings[i]));
				}
			}
			map.put("projectInfoCus", cList);
		}
		if (hasId("supplier.id")) {
			map.put("supplierId", Long.valueOf(this.request.getParameter("supplier.id")));
		}
		return map;
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}
		if (isDelete()) {
			delete();
		}
		return "success";
	}

	public String delete() {
		try {
			// 客户档案移除联系人使用，不删除联系人
			if ("remove".equals(request.getParameter("remove"))) {
				for (ContactArchives contactArchives : cas) {
					contactArchives.setCustomerName(null);
					contactArchives.setCustName(null);
					this.contactArchivesManager.storeContactArchives(contactArchives);
				}
				addActionMessage(getText("contactArchives.remove.success"));
				return "success";
			}
			for (int i = 0; i < this.cas.size(); i++) {
				List<ContactToHistory> contactToHistorys = this.contactToHistoryManager.loadByKey(
						"contactArchivesId.id", this.cas.get(i).getId());
				if (contactToHistorys != null) {
					this.contactToHistoryManager.deleteAllContactToHistory(contactToHistorys);
				}
			}
			this.contactArchivesManager.deleteAllContactArchives(this.cas);
			addActionMessage(getText("contactArchives.delete.success"));
			return "success";
		} catch (DaoException e) {
			e.printStackTrace();
			addActionMessage(getText("contactArchives.delete.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.contactArchivesManager.disabledAllContactArchives(this.cas);
			addActionMessage(getText("contactArchives.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("contactArchives.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.contactArchivesManager.enabledAllContactArchives(this.cas);
			addActionMessage(getText("contactArchives.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("contactArchives.enabled.error"));
		}
		return "error";
	}

	public List<CodeValue> getAllTypes() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			List codes = new ArrayList();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		}
	}
	
	public boolean isCustomerGroup(){
		boolean isCustomerGroup =false;
		Set<User> noticePers = groupManager.getGroupByGroupName("客户管理组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
			System.out.println(u.getId()+"=="+user.getId());
			if(user.getId().longValue() == u.getId().longValue()){
				isCustomerGroup = true;
			}
		}
		return isCustomerGroup;
	}

	public List<ContactArchives> getCas() {
		return this.cas;
	}

	public void setCas(List<ContactArchives> cas) {
		this.cas = cas;
	}

	public String getBackVistiFlag() {
		return this.backVistiFlag;
	}

	public void setBackVistiFlag(String backVistiFlag) {
		this.backVistiFlag = backVistiFlag;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(Long customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public String getBackVisitCheckBox() {
		return backVisitCheckBox;
	}

	public void setBackVisitCheckBox(String backVisitCheckBox) {
		this.backVisitCheckBox = backVisitCheckBox;
	}

	public Long getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(Long projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

	public String getContactArchiveIds() {
		return contactArchiveIds;
	}

	public void setContactArchiveIds(String contactArchiveIds) {
		this.contactArchiveIds = contactArchiveIds;
	}

}
