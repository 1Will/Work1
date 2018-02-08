package com.yongjun.tdms.presentation.webwork.action.productionOperation.productionOperationDetail;

import java.util.ArrayList;
import java.util.Arrays;









import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.productionOperation.ProductionOperationManager;
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;

public class EditProductionOperationDetailAction extends PrepareAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private final ProductionOperationManager productionOperationManager;
	private final ProductionOperationDetailManager productionOperationDetailManager;
	private final ProductsManager productsManager;
	private final ContractManagementManager contractManagementManager;
	private ProductListManager productListManager;
	private final CodeValueManager codeValueManager;
	private ProductionOperation productionOperation;
	private ProductionOperationDetail productionOperationDetail;

	public EditProductionOperationDetailAction(ProductionOperationManager productionOperationManager,ProductionOperationDetailManager productionOperationDetailManager
			,ProductsManager productsManager,ContractManagementManager contractManagementManager,CodeValueManager codeValueManager,ProductListManager productListManager) {
		this.productionOperationManager = productionOperationManager;
		this.productionOperationDetailManager=productionOperationDetailManager;
		this.productsManager=productsManager;
		this.contractManagementManager=contractManagementManager;
		this.codeValueManager=codeValueManager;
		this.productListManager=productListManager;
	}


	public void prepare() throws Exception {
			if (hasId("productionOperation.id")) {
				this.productionOperation = this.productionOperationManager.loadProductionOperation(getId("productionOperation.id"));
			} 
			
			if (hasId("productionOperationDetail.id")) {
				this.productionOperationDetail = this.productionOperationDetailManager.loadProductionOperationDetail(getId("productionOperationDetail.id"));
				this.productionOperation =this.productionOperationDetail.getProductionOperation();
			}else {
				this.productionOperationDetail = new ProductionOperationDetail();
			}
	}


	public String save() throws Exception {
		boolean isNew = this.productionOperationDetail.isNew();
		if(isNew){
			//新建产品计划  默认执行状态为正常
			List<CodeValue> one = this.codeValueManager.loadByKey("code", "21601");
			if(one!=null&&one.size()>0){
				this.productionOperationDetail.setProStatu(one.get(0));
			}
			
		}
		
		if(hasId("product.id")){
			this.productionOperationDetail.setProduct(this.productsManager.loadProducts(getId("product.id")));
			
		}
		if(hasId("contractManagement.id")){
			this.productionOperationDetail.setContractManagement(this.contractManagementManager.loadContractManagement(getId("contractManagement.id")));
			
		}
		if(hasId("productList.id")){
			this.productionOperationDetail.setProductList(this.productListManager.loadProductList(getId("productList.id")));
			
		}
		if(hasId("unit.id")){
			this.productionOperationDetail.setUnit(this.codeValueManager.loadCodeValue(getId("unit.id")));	
		}
		
		if(hasId("testType.id")){
			this.productionOperationDetail.setTestType(this.codeValueManager.loadCodeValue(getId("testType.id")));	
		}
		
		this.productionOperationDetail.setProductionOperation(this.productionOperation);
		
		String isSaved = this.request.getParameter("isSaved");
		this.productionOperation.setIsSaved(isSaved);
		this.productionOperationDetailManager.storeProductionOperationDetail(this.productionOperationDetail);
		try {
			if (isNew) {
				addActionMessage(getText("经营计划详细保存成功", Arrays.asList(new Object[] { this.productionOperationDetail.getProduct().getModel() })));
			}else {
				if(isSaved!=null&&isSaved.equals("1")){
					addActionMessage(getText("经营计划详细提交成功", Arrays.asList(new Object[] { this.productionOperationDetail.getProduct().getModel() })));
				}else{
					addActionMessage(getText("经营计划详细修改成功", Arrays.asList(new Object[] { this.productionOperationDetail.getProduct().getModel() })));
					
				}
			}
			
		} catch (Exception e) {
			addActionMessage(getText("productionOperation.add.error", Arrays.asList(new Object[] { this.productionOperation.getName() })));
			e.printStackTrace();
			return "error";
		}

		return "success";
	}
	
	




	public ProductionOperation getProductionOperation() {
		return this.productionOperation;
	}

	public void setProductionOperation(ProductionOperation productionOperation) {
		this.productionOperation = productionOperation;
	}


	public ProductionOperationDetail getProductionOperationDetail() {
		return productionOperationDetail;
	}


	public void setProductionOperationDetail(ProductionOperationDetail productionOperationDetail) {
		this.productionOperationDetail = productionOperationDetail;
	}
	//获取所有单位
	public List<CodeValue> getAllUnit(){
		List<CodeValue> codeValues =null;
		try {
			String [] key={"code","name"};
			String [] value ={"068","单位"};
			List <CodeValue>oneList = this.codeValueManager.loadByKeyArray(key, value);
			
			if(oneList!=null&&oneList.size()>0){
				codeValues = this.codeValueManager.loadByKey("parentCV.id", oneList.get(0).getId());
				
				
			}else {
				codeValues =new ArrayList<CodeValue>();
			}
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return codeValues;
	}
	//获取所有单位
		public List<CodeValue> getAllProStatu(){
			List<CodeValue> codeValues =null;
			try {
				String [] key={"code","name"};
				String [] value ={"068","单位"};
				List <CodeValue>oneList = this.codeValueManager.loadByKeyArray(key, value);
				
				if(oneList!=null&&oneList.size()>0){
					codeValues = this.codeValueManager.loadByKey("parentCV.id", oneList.get(0).getId());
					
					
				}else {
					codeValues =new ArrayList<CodeValue>();
				}
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return codeValues;
		}
		
		//获取所有单位
		public List<CodeValue> getAllTestType(){
			List<CodeValue> codeValues =null;
			try {
				String [] key={"code","name"};
				String [] value ={"239","质检"};
				List <CodeValue>oneList = this.codeValueManager.loadByKeyArray(key, value);
				
				if(oneList!=null&&oneList.size()>0){
					codeValues = this.codeValueManager.loadByKey("parentCV.id", oneList.get(0).getId());
					
					
				}else {
					codeValues =new ArrayList<CodeValue>();
				}
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return codeValues;
		}


}
