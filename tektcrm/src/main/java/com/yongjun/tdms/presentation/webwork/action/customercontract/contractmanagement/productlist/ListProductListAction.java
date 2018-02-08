package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.productlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.invoiceList.InvoicesList;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.invoicesList.InvoicesListManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
@SuppressWarnings({"rawtypes", "unchecked"})
public class ListProductListAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ProductListManager productListManager;
	private final ContractManagementManager contractManagementManager;
	private final CodeValueManager codeValueManager;
	private List<ProductList> productLists = null;
	private Long contractManagementId;
	private ProjectInfoManager projectInfoManager;
	private String invoicesList;
	private String productionOperation;
	private InvoicesListManager invoicesListManager;
	private InvoicesManager invoicesManager;
	private String customerInfoId;
	public ListProductListAction(ProductListManager productListManager,
			ContractManagementManager contractManagementManager, CodeValueManager codeValueManager,ProjectInfoManager projectInfoManager
			,InvoicesListManager invoicesListManager,InvoicesManager invoicesManager) {
		this.productListManager = productListManager;
		this.contractManagementManager = contractManagementManager;
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.invoicesListManager = invoicesListManager;
		this.invoicesManager = invoicesManager;
	}

	public void prepare() throws Exception {
		if ((null == this.productLists) && (hasIds("productListIds"))) {
			this.productLists = this.productListManager.loadAllProductList(getIds("productListIds"));
		}

		if (hasId("contractManagement.id")) {
			this.contractManagementId = getId("contractManagement.id");
		}
		String invoicesListStr = request.getParameter("invoicesList");
		if(invoicesListStr != null && !"".equals(invoicesListStr)){
			this.invoicesList = invoicesListStr;
		}
		if(request.getParameter("productionOperation")!=null&&!request.getParameter("productionOperation").equals("")){
			this.productionOperation = request.getParameter("productionOperation");
		}
		
		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id")+"";
		}
		setFirst(false);
	}

	protected String getAdapterName() {
		return "productListHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.contractManagementId != null) {
			map.put("contractManagementid", this.contractManagementId);
		}
		String invoicesID = request.getParameter("invoices.id");
		if(invoicesID!=null && !"".equals(invoicesID)){
			try {
				List<InvoicesList> invoicesLists = invoicesListManager.loadByKey("invoices", invoicesManager.loadInvoices(Long.parseLong(invoicesID)));
				if(invoicesLists!=null && invoicesLists.size()>0){
					List<Long> iDList = new ArrayList<Long>();
					for (InvoicesList list : invoicesLists) {
						if(list.getProductList() != null){
							iDList.add(list.getProductList().getId());
						}
					}
					map.put("iDList", iDList);
				}
			} catch (DaoException e) {
				map.remove("iDList");
			}
		}
		String flag = request.getParameter("invoicesList");
		if(flag!=null && !"".equals(flag)){
			map.put("filter", "filter");
		}
		String customerInfoId = request.getParameter("customerInfo.id");
		if(customerInfoId!=null && !"".equals(customerInfoId)){
			map.put("customerInfo.id", customerInfoId);
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
			this.productListManager.deleteAllProductList(this.productLists);
			//删除产品明细后，更新项目对应项目的合同总金额
			double TempContractManagementMoney = 0;
			for (ProductList productList : productLists) {
				TempContractManagementMoney += productList.getTotalPrice();
			}
			ContractManagement contractManagement = this.productLists.get(0).getContractManagement();
			ProjectInfo projectInfo = this.productLists.get(0).getContractManagement().getProject();
			contractManagement.setContractMoney(contractManagement.getContractMoney()-TempContractManagementMoney);
			projectInfo.setContractManagementMoney(projectInfo.getContractManagementMoney()-TempContractManagementMoney);
			contractManagementManager.storeContractManagement(contractManagement);
			projectInfoManager.storeProjectInfo(projectInfo);
			
			addActionMessage(getText("productList.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("productList.delete.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.productListManager.disabledAllProductList(this.productLists);
			addActionMessage(getText("productList.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("productList.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.productListManager.enabledAllProductList(this.productLists);
			addActionMessage(getText("productList.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("productList.enabled.error"));
		}
		return "error";
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
			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();

			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
		}
		return codes;
	}
	public Long getContractManagementId() {
		return contractManagementId;
	}

	public void setContractManagementId(Long contractManagementId) {
		this.contractManagementId = contractManagementId;
	}

	public String getInvoicesList() {
		return invoicesList;
	}

	public void setInvoicesList(String invoicesList) {
		this.invoicesList = invoicesList;
	}

	public String getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(String customerInfoId) {
		this.customerInfoId = customerInfoId;
	}

	public String getProductionOperation() {
		return productionOperation;
	}

	public void setProductionOperation(String productionOperation) {
		this.productionOperation = productionOperation;
	}
	
	
}
