package com.yongjun.tdms.presentation.webwork.action.accessory;

import java.util.Arrays;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.advisory.Advisory;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.advisory.AdvisoryManager;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.supplier.SupplierManager;

public class EditAccessoryAction extends FileTransportAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private UserManager userManager;
	private final ApplicationDocManager applicationDocManager;
	private final AdvisoryManager advisoryManager;
	private final FileTransportManager fileTransportManager;
	private final BackVisitManager backVisitManager;
	private final SupplierManager supplierManager;
	private final ContractAdministratorManager contractAdministratorManager;
	private final ContractManagementManager contractManagementManager;
	private final ProjectInfoManager projectInfoManager;
	private final FinancialManagementManager financialManagementManager;
	private final ProductsManager productsManager;
	
	private ApplicationDoc applicationDoc;
	private Advisory advisory;
	private BackVisit backVisit;
	private Supplier supplier;
	private ProjectInfo projectInfo;
	private ContractAdministrator contractAdministrator;
	private ContractManagement contractManagement;
	private FinancialManagement financialManagement;
	private Products products;

	public EditAccessoryAction(ApplicationDocManager applicationDocManager, AdvisoryManager advisoryManager,
			FileTransportManager fileTransportManager, BackVisitManager backVisitManager,
			SupplierManager supplierManager, ContractAdministratorManager contractAdministratorManager,
			ProjectInfoManager projectInfoManager, ContractManagementManager contractManagementManager,
			FinancialManagementManager financialManagementManager,ProductsManager productsManager) {
		this.applicationDocManager = applicationDocManager;
		this.advisoryManager = advisoryManager;
		this.fileTransportManager = fileTransportManager;
		this.backVisitManager = backVisitManager;
		this.supplierManager = supplierManager;
		this.contractAdministratorManager = contractAdministratorManager;
		this.projectInfoManager = projectInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.financialManagementManager = financialManagementManager;
		this.productsManager = productsManager;
	}

	public void prepare() throws Exception {
		if (this.applicationDoc == null) {
			if (hasId("applicationDoc.id")) {
				this.applicationDoc = this.applicationDocManager.loadApplicationDoc(getId("applicationDoc.id"));
			} else {
				this.applicationDoc = new ApplicationDoc();
				this.applicationDoc.setCreatorName(this.userManager.getUser().getName());
			}
		}
		if (hasId("advisory.id")) {
			this.advisory = this.advisoryManager.loadAdvisory(getId("advisory.id"));
		}
		if (hasId("projectInfo.id")) {
			this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
		if (hasId("backVisit.id")) {
			this.backVisit = this.backVisitManager.loadBackVisit(getId("backVisit.id"));
		}
		if (hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
		}
		if (hasId("contractAdministrator.id")) {
			this.contractAdministrator = this.contractAdministratorManager
					.loadContractAdministrator(getId("contractAdministrator.id"));
		}
		if (hasId("contractManagement.id")) {
			this.contractManagement = this.contractManagementManager
					.loadContractManagement(getId("contractManagement.id"));
		}
		if (hasId("financialManagement.id")) {
			this.financialManagement = this.financialManagementManager
					.loadFinancialManagement(getId("financialManagement.id"));
		}
		if (hasId("products.id")) {
			this.products = this.productsManager
					.loadProducts(getId("products.id"));
		}
	}

	public String save() throws Exception {
		boolean isNew = this.applicationDoc.isNew();
		if ((isNew) || (getFile() != null)) {
			if (!isNew) {
				this.fileTransportManager.delete(this.request, this.applicationDoc.getPosition());
			}
			String location = this.fileTransportManager.upload(this.request, getFile(), "origFileName");

			this.applicationDoc.setPosition(location);
			String orgFileName = this.request.getParameter("origFileName");
			this.applicationDoc.setFileName(orgFileName);
			this.applicationDoc.setFileNo(location);
		}
		try {
			if (this.advisory != null) {
				this.applicationDoc.setAdvisory(this.advisory);
			}
			if (this.backVisit != null) {
				this.applicationDoc.setBackVisit(this.backVisit);
			}
			if (this.projectInfo != null) {
				this.applicationDoc.setProjectInfo(projectInfo);
			}
			if (this.supplier != null) {
				this.applicationDoc.setSupplier(this.supplier);
			}
			if (this.contractAdministrator != null) {
				this.applicationDoc.setContractAdministrator(this.contractAdministrator);
			}
			if (this.contractManagement != null) {
				this.applicationDoc.setContractManagement(this.contractManagement);
			}
			if (this.financialManagement != null) {
				this.applicationDoc.setFinancialManagement(this.financialManagement);
			}
			if (this.products != null) {
				this.applicationDoc.setProducts(this.products);
			}
			this.applicationDocManager.storeApplicationDoc(this.applicationDoc);
		} catch (Exception e) {
			addActionMessage(getText("applicationDoc.add.error",
					Arrays.asList(new Object[] { this.applicationDoc.getName() })));
			e.printStackTrace();
			return "error";
		}
		addActionMessage(getText("applicationDoc.add.success",
				Arrays.asList(new Object[] { this.applicationDoc.getName() })));
		return "success";
	}

	public ApplicationDoc getApplicationDoc() {
		return this.applicationDoc;
	}

	public void setApplicationDoc(ApplicationDoc applicationDoc) {
		this.applicationDoc = applicationDoc;
	}

	public Advisory getAdvisory() {
		return this.advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}

	public BackVisit getBackVisit() {
		return this.backVisit;
	}

	public void setBackVisit(BackVisit backVisit) {
		this.backVisit = backVisit;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ContractAdministrator getContractAdministrator() {
		return this.contractAdministrator;
	}

	public void setContractAdministrator(ContractAdministrator contractAdministrator) {
		this.contractAdministrator = contractAdministrator;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public FinancialManagement getFinancialManagement() {
		return financialManagement;
	}

	public void setFinancialManagement(FinancialManagement financialManagement) {
		this.financialManagement = financialManagement;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

}
