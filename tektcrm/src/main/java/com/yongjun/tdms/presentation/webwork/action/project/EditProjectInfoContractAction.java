package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;

public class EditProjectInfoContractAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactArchivesManager contactArchivesManager;
	private final ProjectInfoContractManager projectInfoContractManager;
	private final ProjectInfoCustomerManager projectInfoCustomerManager;
	private final UserManager userManager;
	private String projectInfoId;
	private String customerInfoId;
	private String projectInfoCus;
	private ProjectInfoContract projectInfoContract;

	private ContactArchives contactArchives;

	public EditProjectInfoContractAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager,
			ContactArchivesManager contactArchivesManager, UserManager userManager,
			ProjectInfoContractManager projectInfoContractManager,ProjectInfoCustomerManager projectInfoCustomerManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contactArchivesManager = contactArchivesManager;
		this.userManager = userManager;
		this.projectInfoContractManager = projectInfoContractManager;
		this.projectInfoCustomerManager=projectInfoCustomerManager;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfoId = getId("projectInfo.id") + "";
			projectInfoCus="";
			if(hasId("customerInfo.id")){
				projectInfoCus=getId("customerInfo.id")+"";	
			}
			List<ProjectInfoCustomer> pms = this.projectInfoCustomerManager.loadByKey("projectInfo.id",getId("projectInfo.id"));
			if(pms!=null){
				for(ProjectInfoCustomer pm:pms){
					if(projectInfoCus.equals("")){
						projectInfoCus=pm.getCustomerInfo().getId()+"";
					}else {
						projectInfoCus+=","+pm.getCustomerInfo().getId();
					}
				}
				
			}
		}
		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id") + "";
		}

		if (this.projectInfoContract == null)
			if (hasId("projectInfoContract.id")) {
				this.projectInfoContract = this.projectInfoContractManager
						.loadProjectInfoContract(getId("projectInfoContract.id"));
			} else {
				this.projectInfoContract = new ProjectInfoContract();
			}
	}

	public String save() {
		boolean isNew = this.projectInfoContract.isNew();

		if (hasId("projectInfo.id")) {
			ProjectInfo projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
			this.projectInfoContract.setProjectInfo(projectInfo);
		}

		if (hasId("businessType.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
			this.projectInfoContract.setBusinessType(cv);
		}

		if (hasId("contactArchives.id")) {
			this.contactArchives = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));
			this.projectInfoContract.setContactArchives(contactArchives);
		}
		User u = this.userManager.getUser();
		if (isNew) {
			this.projectInfoContract.setCreator(u.getName());
			this.projectInfoContract.setLastOperator(u.getName());
		} else {
			this.projectInfoContract.setLastOperator(u.getName());
		}
		this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);

		if (isNew) {
			addActionMessage(getText("projectInfoContacts.add.success",
					Arrays.asList(new Object[] { this.projectInfoContract.getContactArchives().getName() })));

			return "success";
		}
		addActionMessage(getText("projectInfoContacts.edit.success",
				Arrays.asList(new Object[] { this.projectInfoContract.getContactArchives().getName() })));
		return "success";
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public List<CodeValue> getAllBusinessType() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "207");
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

	public String getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(String projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

	public String getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(String customerInfoId) {
		this.customerInfoId = customerInfoId;
	}

	public ProjectInfoContract getProjectInfoContract() {
		return projectInfoContract;
	}

	public void setProjectInfoContract(ProjectInfoContract projectInfoContract) {
		this.projectInfoContract = projectInfoContract;
	}

	public ContactArchives getContactArchives() {
		return contactArchives;
	}

	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}

	public String getProjectInfoCus() {
		return projectInfoCus;
	}

	public void setProjectInfoCus(String projectInfoCus) {
		this.projectInfoCus = projectInfoCus;
	}
	

	// public CodeValue getCustomerSteped()
	// {
	// return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	// }
}
