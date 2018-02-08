package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.productlist;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

public class EditProductListAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ProductListManager productListManager;
	private final ContractManagementManager contractManagementManager;
	private final ProductsManager productsManager;
	private final CodeValueManager codeValueManager;
	private ProductList productList = null;
	private ContractManagement contractManagement;
	private Products product;
	private CodeValue unit;
	private long contractManagementid;
	private CodeValue qualityControl;  
	private final InvoicesManager invoicesManager;
	private int dNum=0;
	private ProjectInfoManager projectInfoManager;
	private double TempContractManagementMoney;
	
	public EditProductListAction(ProductListManager productListManager,
			ContractManagementManager contractManagementManager, CodeValueManager codeValueManager,
			ProductsManager productsManager,InvoicesManager invoicesManager,ProjectInfoManager projectInfoManager) {
		this.productListManager = productListManager;
		this.contractManagementManager = contractManagementManager;
		this.codeValueManager = codeValueManager;
		this.productsManager = productsManager;
		this.invoicesManager = invoicesManager;
		this.projectInfoManager = projectInfoManager;
	}

	public void prepare() throws Exception {
		if (hasId("productList.id")) {
			this.productList = this.productListManager.loadProductList(getId("productList.id"));
			TempContractManagementMoney = this.productList.getTotalPrice();
			this.contractManagement = this.productList.getContractManagement();
			this.contractManagementid = this.contractManagement.getId().longValue();
		} else {
			this.productList = new ProductList();
			this.contractManagementid = Long.valueOf(this.request.getParameter("contractManagementid")).longValue();
			this.contractManagement = this.contractManagementManager.loadContractManagement(Long
					.valueOf(this.contractManagementid));

			this.productList.setContractManagement(this.contractManagement);
		}
	}

	public String execute() throws Exception {
		return super.execute();
	}

	public String save() throws Exception, DaoException {
		boolean isNew = this.productList.isNew();
		if(!isNew){
			 List<Invoices>invoiceses=invoicesManager.loadByKey("productList.id", this.productList.getId());
			 if(invoiceses!=null&&invoiceses.size()>0){
				 for(Invoices invoices:invoiceses){
						dNum+=invoices.getDeliveryCount();
					 }
			 }
			if(!"2".equals(productList.getIsExport())){
				this.productList.setIsExport("1");
			}
		}else {
			this.productList.setIsExport("2");
		}
		
        this.productList.setDeliveryedCount(dNum);        
		if (hasId("product.id")) {
			this.product = this.productsManager.loadProducts(getId("product.id"));
		}

		this.productList.setProduct(this.product);

		if (hasId("unit.id")) {
			this.unit = this.codeValueManager.loadCodeValue(getId("unit.id"));
		}
        if(hasId("qualityControl.id")){
        	this.qualityControl=this.codeValueManager.loadCodeValue(getId("qualityControl.id"));
        }
		this.productList.setUnit(this.unit);
		this.productList.setQualityControl(this.qualityControl);

		this.productListManager.storeProductList(this.productList);

		DecimalFormat format = new DecimalFormat("0.00");
		this.contractManagement.setContractMoney(new Double(format.format(this.productListManager.getSumTotalPrice(this.contractManagement.getId().longValue())))
				.doubleValue());
		this.contractManagement.setIsExport("1");
		this.contractManagementManager.storeContractManagement(this.contractManagement);
		//更新项目中的合同总金额
		ProjectInfo projectInfo = this.contractManagement.getProject();
		double contractManagementMoney = projectInfo.getContractManagementMoney();//原项目下的合同总金额
		if(isNew){
			projectInfo.setContractManagementMoney(contractManagementMoney+productList.getTotalPrice());
		}else{
			projectInfo.setContractManagementMoney(contractManagementMoney-TempContractManagementMoney+productList.getTotalPrice());
		}
		projectInfoManager.storeProjectInfo(projectInfo);
		
		try {
			if (isNew) {
				addActionMessage(getText("productList.add.success"));
				return "new";
			}
			addActionMessage(getText("productList.edit.success"));
			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			if (isNew) {
				addActionMessage(getText("productList.add.error"));
				return "new";
			}
			addActionMessage(getText("productList.edit.error"));
		}
		return "success";
	}

	public List<CodeValue> getAllUnit() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("068"));

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
	public List<CodeValue> getAllQualityControl() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("239"));

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
	public List<Products> getAllProduct() {
		List pros = new ArrayList();
		List<Products> proTemps = this.productsManager.getAllProductsByNull("所有");
		for (Products p : proTemps) {
			if (!p.getDisabled()) {
				pros.add(p);
			}
		}
		return pros;
	}

	public ProductList getProductList() {
		return this.productList;
	}

	public void setProductList(ProductList productList) {
		this.productList = productList;
	}

	public long getContractManagementid() {
		return this.contractManagementid;
	}

	public void setContractManagementid(long contractManagementid) {
		this.contractManagementid = contractManagementid;
	}
}
