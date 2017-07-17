package com.yongjun.tdms.model.base.document;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.advisory.Advisory;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.supplier.Supplier;

public class ApplicationDoc extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking,
		LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = 1L;
	private String fileNo;
	private String fileName;
	private String name;
	private String description;
	private String position;
	private String manualVersion;
	private String creatorName;
	private Date uploadDate;
	private ApplicationDocType docFlag;
	private Advisory advisory;
	private BackVisit backVisit;
	private Supplier supplier;
	private ProjectInfo projectInfo;
	private ContractAdministrator contractAdministrator;
	private ContractManagement contractManagement;
	private FinancialManagement financialManagement;
	private Products products;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int hashCode() {
		return getId().hashCode();
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ApplicationDoc))
			return false;

		return true;
	}

	public ApplicationDocType getDocFlag() {
		return this.docFlag;
	}

	public void setDocFlag(ApplicationDocType docFlag) {
		this.docFlag = docFlag;
	}

	public String getManualVersion() {
		return this.manualVersion;
	}

	public void setManualVersion(String manualVersion) {
		this.manualVersion = manualVersion;
	}

	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getDomainModelProperty(String mark) {
		return getProperties().getProperty(mark);
	}

	public Advisory getAdvisory() {
		return this.advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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
