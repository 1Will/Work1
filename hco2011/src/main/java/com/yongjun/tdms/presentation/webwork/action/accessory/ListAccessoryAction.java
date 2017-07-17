package com.yongjun.tdms.presentation.webwork.action.accessory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.DomainModel;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
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

public class ListAccessoryAction extends ValueListAction {
	private static final long serialVersionUID = 5616960016127750986L;
	private final ApplicationDocManager applicationDocManager;
	private final AdvisoryManager advisoryManager;
	private final FileTransportManager fileTransportManager;
	private final BackVisitManager backVisitManager;
	private final SupplierManager supplierManager;
	private final ProjectInfoManager projectInfoManager;
	private final ContractAdministratorManager contractAdministratorManager;
	private final ContractManagementManager contractManagementManager;
	private final FinancialManagementManager financialManagementManager;
	private final ProductsManager productsManager;

	private List<ApplicationDoc> applicationDocs;
	private ApplicationDoc applicationDoc;
	private Advisory advisory;
	private BackVisit backVisit;
	private Supplier supplier;
	private ProjectInfo projectInfo;
	private ContractAdministrator contractAdministrator;
	private ContractManagement contractManagement;
	private FinancialManagement financialManagement;
	private Products products;

	public ListAccessoryAction(ApplicationDocManager applicationDocManager, FileTransportManager fileTransportManager,
			AdvisoryManager advisoryManager, BackVisitManager backVisitManager, SupplierManager supplierManager,
			ContractAdministratorManager contractAdministratorManager, ProjectInfoManager projectInfoManager,
			ContractManagementManager contractManagementManager,FinancialManagementManager financialManagementManager,
			ProductsManager productsManager) {
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
		this.advisoryManager = advisoryManager;
		this.backVisitManager = backVisitManager;
		this.supplierManager = supplierManager;
		this.contractAdministratorManager = contractAdministratorManager;
		this.projectInfoManager = projectInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.financialManagementManager = financialManagementManager;
		this.productsManager = productsManager;
	}

	public void prepare() throws Exception {
		if (hasId("applicationDoc.id")) {
			this.applicationDoc = this.applicationDocManager.loadApplicationDoc(getId("applicationDoc.id"));
			download();
		}
		if (hasId("advisory.id")) {
			this.advisory = this.advisoryManager.loadAdvisory(getId("advisory.id"));
		}
		if (hasId("backVisit.id")) {
			this.backVisit = this.backVisitManager.loadBackVisit(getId("backVisit.id"));
		}
		if (hasId("projectInfo.id")) {
			this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
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
		if ((this.applicationDocs == null) && (hasIds("applicationDocIds"))) {
			this.applicationDocs = this.applicationDocManager.loadAllApplicationDocs(getIds("applicationDocIds"));
		}
		setFirst(false);
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return "success";
	}

	private void delete() {
		String strDoc = "";
		for (Iterator iter = this.applicationDocs.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc) iter.next();
			strDoc = strDoc + doc.getName() + ",";

			this.fileTransportManager.delete(this.request, doc.getPosition());

			this.applicationDocManager.deleteApplicationDoc(doc);
		}
		Integer index = null;
		index = Integer.valueOf(strDoc.lastIndexOf(","));
		strDoc = strDoc.substring(0, index.intValue());
		getLogger().logStore((DomainModel) this.applicationDocs.get(0), "(名称为:[" + strDoc + "]的文件)被删除",
				"applicationDoc");

		addActionMessage(getText("applicationDocs.delete.success"));
	}

	protected String getAdapterName() {
		return "applicationDoc";
	}

	public String download() {
		this.fileTransportManager.download(this.request, this.response, this.applicationDoc.getFileName(),
				this.applicationDoc.getPosition());
		return null;
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		// if ((null != this.request.getParameter("advisory.id")) && ("" !=
		// this.request.getParameter("advisory.id"))) {
		// map.put("advisory.id",
		// Long.valueOf(this.request.getParameter("advisory.id")));
		// }
		// if ((null != this.request.getParameter("backVisit.id")) && ("" !=
		// this.request.getParameter("backVisit.id"))) {
		// map.put("backVisit.id",
		// Long.valueOf(this.request.getParameter("backVisit.id")));
		// }
		// if ((null != this.request.getParameter("supplier.id")) && ("" !=
		// this.request.getParameter("supplier.id"))) {
		// map.put("supplier.id",
		// Long.valueOf(this.request.getParameter("supplier.id")));
		// }
		// if ((null != this.request.getParameter("contractAdministrator.id"))
		// && ("" != this.request.getParameter("contractAdministrator.id"))) {
		// System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		// map.put("contractAdministrator.id",
		// Long.valueOf(this.request.getParameter("contractAdministrator.id")));
		// }
		return map;
	}

	public List<ApplicationDoc> getApplicationDocs() {
		return this.applicationDocs;
	}

	public void setApplicationDocs(List<ApplicationDoc> applicationDocs) {
		this.applicationDocs = applicationDocs;
	}

	public Advisory getAdvisory() {
		return this.advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}

	public ApplicationDoc getApplicationDoc() {
		return this.applicationDoc;
	}

	public void setApplicationDoc(ApplicationDoc applicationDoc) {
		this.applicationDoc = applicationDoc;
	}

	public BackVisit getBackVisit() {
		return this.backVisit;
	}

	public void setBackVisit(BackVisit backVisit) {
		this.backVisit = backVisit;
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
