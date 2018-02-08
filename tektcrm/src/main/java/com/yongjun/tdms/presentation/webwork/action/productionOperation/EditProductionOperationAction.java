package com.yongjun.tdms.presentation.webwork.action.productionOperation;

import java.util.Arrays;



import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.productionOperation.ProductionOperationManager;

public class EditProductionOperationAction extends PrepareAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private final ProductionOperationManager productionOperationManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private ProductionOperation productionOperation;
	private PersonnelFiles personnelFiles;
	private String managerType ;
	private String pageTitle ;

	public EditProductionOperationAction(ProductionOperationManager productionOperationManager, PersonnelFilesManager personnelFilesManager,
			 UserManager userManager) {
		this.productionOperationManager = productionOperationManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
	}


	public void prepare() throws Exception {
		if (this.productionOperation == null)
			if (hasId("productionOperation.id")) {
				this.productionOperation = this.productionOperationManager.loadProductionOperation(getId("productionOperation.id"));
			} else {
				this.productionOperation = new ProductionOperation();
				if ((null != this.userManager.getUser().getCode())
						&& (!"".equals(this.userManager.getUser().getCode()))) {
					this.personnelFiles = ((PersonnelFiles) this.personnelFilesManager
							.loadByKey("code", this.userManager.getUser().getCode()).get(0));
					this.productionOperation.setMakeUpPerson(personnelFiles);
				}
			}
		 if(hasId("managerType")){
       	  this.managerType = request.getParameter("managerType");
       	  if(this.managerType.equals("design")&&!"".equals(this.managerType)){
       		pageTitle  = "设计通知单";
       	  }else {
       		pageTitle  = "生产经营";
		}
       	  
         }
		if(hasId("makeUpPerson.id")){
			this.productionOperation.setMakeUpPerson(this.personnelFilesManager.loadPersonnel(getId("makeUpPerson.id")));
			
		}
		if(hasId("auditingPerson.id")){
			this.productionOperation.setAuditingPerson(this.personnelFilesManager.loadPersonnel(getId("auditingPerson.id")));
			
		}
	}


	public String save() throws Exception {
		boolean isNew = this.productionOperation.isNew();
		
		String isSaved = this.request.getParameter("isSaved");
		this.productionOperation.setIsSaved(isSaved);
		if(isNew){
			if(request.getParameter("managerType")!=null&&!"".equals(managerType)){
			this.productionOperation.setManagerType(request.getParameter("managerType"));
			}
		}
		this.productionOperationManager.storeProductionOperation(this.productionOperation);
		try {
			if (isNew) {
				addActionMessage(pageTitle+getText("保存成功", Arrays.asList(new Object[] { this.productionOperation.getName() })));
			}else {
				if(isSaved!=null&&isSaved.equals("1")){
					addActionMessage(pageTitle+getText("提交成功", Arrays.asList(new Object[] { this.productionOperation.getName() })));
				}else{
					addActionMessage(pageTitle+getText("修改成功", Arrays.asList(new Object[] { this.productionOperation.getName() })));
					
				}
				
			}
			
		} catch (Exception e) {
			addActionMessage(getText("productionOperation.add.error", Arrays.asList(new Object[] { this.productionOperation.getName() })));
			e.printStackTrace();
			return "error";
		}

		return "success";
	}




	public String getManagerType() {
		return managerType;
	}


	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}


	public ProductionOperation getProductionOperation() {
		return this.productionOperation;
	}

	public void setProductionOperation(ProductionOperation productionOperation) {
		this.productionOperation = productionOperation;
	}


	public String getPageTitle() {
		return pageTitle;
	}


	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}


}
