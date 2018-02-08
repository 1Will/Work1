package com.yongjun.tdms.presentation.webwork.action.delivery.invoicesList;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.invoiceList.InvoicesList;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.invoicesList.InvoicesListManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;

/** 
  * @author 创建人: xichunguang
  * @date 创建时间：2017年11月14日 下午2:20:43 
  *       产品明细action类 
  */
@SuppressWarnings("serial")
public class ListInvoicesListAction extends ValueListAction{
	
	 
	 private InvoicesListManager invoicesListManager;
	 private ProductListManager productListManager;
	 private InvoicesManager invoicesManager;
	 
	 private String customerInfoId;
	 private List<InvoicesList> invoicesList;
	 private String backCheckBox;
	 private Invoices invoices;
	 
	 public ListInvoicesListAction(InvoicesListManager invoicesListManager,ProductListManager productListManager,InvoicesManager invoicesManager) {
		// TODO Auto-generated constructor stub
		 this.invoicesListManager = invoicesListManager;
		 this.productListManager = productListManager;
		 this.invoicesManager = invoicesManager;
	}
	
	public void prepare() throws Exception {
		if(hasId("invoicesListIds")){
			this.invoicesList = invoicesListManager.loadAllInvoicesList(getIds("invoicesListIds"));
		}
		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id")+"";
		}
		if(hasId("invoices.id")){
			this.invoices = invoicesManager.loadInvoices(getId("invoices.id"));
		}
	}

	private void saveInvoicesList(String saveStr) {
		String[] arr1 = saveStr.split(";");
		int deliveryCount = this.invoices.getDeliveryCount();
		int tempDeliveryCount = 0;
		double deliveryPrice = 0;
		if(arr1 != null && arr1.length > 0){
			for (String str : arr1) {
				String[] arr2 = str.split(",");
				InvoicesList invoicesList = invoicesListManager.loadInvoicesList(Long.parseLong(arr2[0]));
				ProductList productList = invoicesList.getProductList();
				Products products = invoicesList.getProducts();
				if(productList != null){
					//更新发货单明细的‘已发数量’，‘剩余数量’，‘发货金额’
					invoicesList.setFinishedSum(productList.getDeliveryedCount());
					invoicesList.setRestSum(invoicesList.getProductList().getCount()-invoicesList.getFinishedSum()-Integer.parseInt(arr2[1]));//未发数量
					invoicesList.setDeliveryPrice(productList.getUnitPrice()*Long.parseLong(arr2[1]));
					//累加发“货单明细本次发货金额”为“发货单发货金额”
					deliveryPrice += productList.getUnitPrice()*Long.parseLong(arr2[1]);
					//更新产产品明细的已发数量
					if ("1".equals(invoicesList.getIsSaved())) {
						productList.setDeliveryedCount(invoicesList.getProductList().getDeliveryedCount()-Integer.parseInt(invoicesList.getCurrentSum()+"")+Integer.parseInt(arr2[1]));
					}else{
						productList.setDeliveryedCount(invoicesList.getProductList().getDeliveryedCount()+Integer.parseInt(arr2[1]));
					}
					productListManager.storeProductList(productList);
				}else if (products != null) {
					try {
						List<InvoicesList> inLists = invoicesListManager.loadByKey("products", products);
						int finishedSum = 0;
						if(inLists != null && inLists.size() > 0){
							for (InvoicesList eachList : inLists) {
								finishedSum += eachList.getCurrentSum();
							}
						}
						invoicesList.setFinishedSum(finishedSum);
						invoicesList.setRestSum(0);
						invoicesList.setDeliveryPrice(products.getSalePrice()*Long.parseLong(arr2[1]));
						//累加发“货单明细本次发货金额”为“发货单发货金额”
						deliveryPrice += products.getSalePrice()*Long.parseLong(arr2[1]);
					} catch (DaoException e) {
						return;
					}
				}
				
				
				
				//累加发“货单明细本次发货数量”为“发货单发货数量”
				deliveryCount += Integer.parseInt(arr2[1]);
				tempDeliveryCount += invoicesList.getCurrentSum();
				
				invoicesList.setCurrentSum(Long.parseLong(arr2[1]));
				invoicesList.setIsSaved("1");
				if(arr2.length < 3){
					invoicesList.setMark("");
				}else{
					invoicesList.setMark(arr2[2]);
				}
				
				invoicesListManager.storeInvoicesList(invoicesList);
			}
			//更新发货单的‘发货数量’，‘发货金额’
			this.invoices.setDeliveryCount(deliveryCount-tempDeliveryCount);
			this.invoices.setDeliveryPrice(deliveryPrice);
			invoicesManager.storeInvoices(this.invoices);
		}
	}
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		
		String invoicesId = request.getParameter("invoices.id");
		if(invoicesId!=null && !"".equals(invoicesId)){
			map.put("invoices.id", invoicesId);
			map.put("sortColumn", "id");
			map.put("sortDirection", "1");
		}
		
		return map;
	}
	
	@Override
	public String execute(){
		if (this.isDelete()) {
			this.delete();
		}
		String saveStr = request.getParameter("saveStr");
		if(saveStr != null && !"".equals(saveStr)){
			//保存新建的发货单明细数据
			saveInvoicesList(saveStr);
		}
		return SUCCESS;
	}
	
	private String delete() {
		try {
			double deliveryPrice = 0;
			int deliveryCount = 0;
			//更新产品的发货数量
			for (InvoicesList list : invoicesList) {
				ProductList productList = list.getProductList();
				Products products = list.getProducts();
				if(productList != null){
					productList.setDeliveryedCount(productList.getDeliveryedCount()-Integer.parseInt(list.getCurrentSum()+""));
					productListManager.storeProductList(productList);
				}
				deliveryPrice += list.getDeliveryPrice();
				deliveryCount += list.getCurrentSum();
			}
			//更新发货单的发货金额
			this.invoices.setDeliveryPrice(this.invoices.getDeliveryPrice()-deliveryPrice);
			this.invoices.setDeliveryCount(this.invoices.getDeliveryCount()-deliveryCount);
			invoicesManager.storeInvoices(this.invoices);
			
			this.invoicesListManager.deleteAllInvoicesList(this.invoicesList);
			addActionMessage(getText("发货单明细删除成功"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("发货单明细删除失败"));
		}
		return "error";
	}
	@Override
	protected String getAdapterName() {
		// TODO Auto-generated method stub
		return "invoicesListHQL";
	}
	public String getBackCheckBox() {
		return backCheckBox;
	}
	public void setBackCheckBox(String backCheckBox) {
		this.backCheckBox = backCheckBox;
	}

	public String getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(String customerInfoId) {
		this.customerInfoId = customerInfoId;
	}
	
}
