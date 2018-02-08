/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.asset.device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceType;
import com.yongjun.tdms.model.asset.device.ManagementLevel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.productionline.ProductionLineManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.approver.WfDocApproverManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: EditDeviceAction.java 11436 2008-03-18 14:16:50Z wzou $
 */
public class EditDeviceAction extends PrepareAction {
	private static final long serialVersionUID = 834922333359952990L;
	private final Log log = LogFactory.getLog(getClass());
	private final DeviceCardManager deviceCardManager;
	private final DepartmentManager departmentManager;
	private final SupplierManager supplierManager;
	private final UserManager userManager;
	private final ProductionLineManager productionLineManager;
	private final DeviceTypeManager deviceTypeManager;
	private final CodeValueManager codeValueManager;
	private final WfDocApproverManager wfDocApproverManager;
	private final WfJobManager wfJobManager;
	private  FilialeManager filialeManager;
	private DeviceCard device;
	
	public EditDeviceAction(DeviceCardManager deviceCardManager,
			DepartmentManager departmentManager,
			SupplierManager supplierManager, UserManager userManager,
			ProductionLineManager productionLineManager,
			DeviceTypeManager deviceTypeManager,
			CodeValueManager codeValueManager,
			WfDocApproverManager wfDocApproverManager,
			WfJobManager wfJobManager) {
		this.deviceCardManager = deviceCardManager;
		this.departmentManager = departmentManager;
		this.supplierManager = supplierManager;
		this.userManager = userManager;
		this.productionLineManager = productionLineManager;
		this.deviceTypeManager = deviceTypeManager;
		this.codeValueManager = codeValueManager;
		this.wfDocApproverManager = wfDocApproverManager;
		this.wfJobManager = wfJobManager;
	}

	public void prepare() throws Exception {
		if (null == this.device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
				//System.out.println("this.device.power888=="+this.device.getPower());
			} else {
				this.device = new DeviceCard();
			}
		}
//		if (this.hasId("calUnit.id")) {
//			if (!request.getParameter("calUnit.id").equals("")) {
//				this.device.setCalUnit(this.codeValueManager.loadCodeValue(this
//						.getId("calUnit.id")));
//			} else {
//				this.device.setCalUnit(null);
//			}
//		}
	}

	private boolean isSubmitDoc() {
		return hasKey("submitDoc");
	}
	
	private boolean isCancelSubmittedDoc() {
		return hasKey("cancelSubmitDoc");
	}
	
	public String ectInfoSave(){
		//System.out.println("this.device.power=="+this.device.getPower());
		if (!StringUtils.isEmpty(request.getParameter("country.name"))) {
			this.device.setMadeIn(request.getParameter("country.name"));
		}
		this.deviceCardManager.storeDevice(device);
		return SUCCESS;
	}
	
	public String save() {
		if (this.isSubmitDoc()) {
			return this.submitDoc();
		}
		
		if (this.isCancelSubmittedDoc()) {
			return this.cancelSubmittedDoc();
		}
		
		if (this.isEnabled()) {
			device.setEnabled(true);
		}
		boolean isNew = this.device.isNew();
		
		if (isNew) {
			this.device.setToolingDevFlag(SysModel.DEVICE);
			this.device.setDeviceStatus(this.codeValueManager.loadCodeTypeByCode("0031"));
		}
		
		if (!StringUtils.isEmpty(request.getParameter("manager.id"))) {
			device.setManager(userManager.loadUser(this.getId("manager.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			device.setDepartment(departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("deviceType.id"))) {
			device.setDeviceType(deviceTypeManager.loadDeviceType(this.getId("deviceType.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {
			device.setSupplier(supplierManager.loadSupplier(this.getId("supplier.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("productionLine.id"))) {
			device.setProductionLine(productionLineManager.loadProductionLine(this.getId("productionLine.id")));
		} else {
			device.setProductionLine(null);
		}
		if (!StringUtils.isEmpty(request.getParameter("calUnit.id"))) {
			this.device.setCalUnit(this.codeValueManager.loadCodeValue(this
					.getId("calUnit.id")));
		} else {
			this.device.setCalUnit(null);
		}
		if (!StringUtils.isEmpty(request.getParameter("filiale.id"))) {
//			this.device.setFiliale(this.codeValueManager.loadCodeValue(this
//					.getId("filiale.id")));
//			this.device.setFilialeString(this.codeValueManager.loadCodeValue(this
//					.getId("filiale.id")).getValue());
			this.device.setFiliale(this.filialeManager.loadFiliale(this.
					getId("filiale.id")));


		} else {
			this.device.setFiliale(null);
		}
		
		if (!StringUtils.isEmpty(request.getParameter("property.id"))) {
			device.setProperty(codeValueManager.loadCodeValue(this.getId("property.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("specificationProp.id"))) {
			device.setSpecificationProp(codeValueManager.loadCodeValue(this.getId("specificationProp.id")));
			device.setSpecificationPropString(codeValueManager.loadCodeValue(this.getId("specificationProp.id")).getValue());
		}else {
			this.device.setSpecificationProp(null);
		}
		if (!StringUtils.isEmpty(request.getParameter("device.status.id"))) {
			device.setDeviceStatus(codeValueManager.loadCodeValue(this.getId("device.status.id")));
		}
		
		if (!StringUtils.isEmpty(request.getParameter("country.name"))) {
			this.device.setMadeIn(request.getParameter("country.name"));
		}

//		精大稀已经更改				
//		if (this.hasId("excellentBigSparse.id")) {
//			if (!request.getParameter("excellentBigSparse.id").equals("-1")) {
//				this.device.setExcellentBigSparse(this.codeValueManager.loadCodeValue(this
//						.getId("excellentBigSparse.id")));
//			} else {
//				this.device.setExcellentBigSparse(null);
//			}
//		}
		
		if (!StringUtils.isEmpty(request.getParameter("useCategory.id"))) {
			this.device.setUseCategory(this.codeValueManager.loadCodeValue(this.getId("useCategory.id")));
			this.device.setUseCategoryString(this.codeValueManager.loadCodeValue(this.getId("useCategory.id")).getValue());
		}
		if (!StringUtils.isEmpty(request.getParameter("device.emphasis"))) {
			if (request.getParameter("device.emphasis").equals("N")){
				this.device.setEmphasis(false);
			}else {
				this.device.setEmphasis(true);
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("device.full"))) {
			if (request.getParameter("device.full").equals("N")){
				this.device.setFull(false);
			}else {
				this.device.setFull(true);
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("device.accuracy"))) {
			if (request.getParameter("device.accuracy").equals("N")){
				this.device.setAccuracy(false);
			}else {
				this.device.setAccuracy(true);
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("device.large"))) {
			if (request.getParameter("device.large").equals("N")){
				this.device.setLarge(false);
			}else {
				this.device.setLarge(true);
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("device.rare"))) {
			if (request.getParameter("device.rare").equals("N")){
				this.device.setRare(false);
			}else {
				this.device.setRare(true);
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("device.standard"))) {
			if (request.getParameter("device.standard").equals("N")){
				this.device.setStandard(false);
			}else {
				this.device.setStandard(true);
			}
		}
		
		this.deviceCardManager.storeDevice(device);
		
		if (isNew) {
			this.addActionMessage(this.getText("device.add.success", Arrays
					.asList(new Object[] { device.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("device.edit.success", Arrays
					.asList(new Object[] { device.getName() })));
			return SUCCESS;
		}
	}

	public String execute() throws Exception {
		if (this.isSubmitDoc()) {
			submitDoc();
		}
		if (this.isCancelSubmittedDoc()) {
			cancelSubmittedDoc();
		}
		return SUCCESS;
	}
	
	public String submitDoc() {
		log.debug("begin submitDoc...");
		Long[] ids = null;
		if (this.hasIds("approverIds")) {
			ids = getIds("approverIds");			
		}
		Long finalId = null;
		finalId = this.getId("finalApproverId");
		
		String comment = request.getParameter("approv.comment");
		
		try{			
			this.deviceCardManager.submitDoc(device, ids, finalId, comment, device.getDeviceNo(), device.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String cancelSubmittedDoc() {
		log.debug("begin cancel submitted doc ...");
		try {
			this.deviceCardManager.cancelJob(device);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			return list;
		}
		return departmentManager.createSelectDepartments(StringUtils.EMPTY);
	}
	/**
	 * 获取部门的所有值
	 * @return
	 */
	public List getAllDepartments() {
		return departmentManager.createSelectDepartments(StringUtils.EMPTY);
	}
	public List getProductionLines() {
		return productionLineManager.createSelectProductionLines(StringUtils.EMPTY);
	}
	
	public List getDeviceProps() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.DEVICE_PROP);
	}
	
	public List getDeviceStatus() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.DEVICE_STATUS);
	}
	
	public List getDeviceSpecifictionProps() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.DEVICE_SPECIFICATION_PROF);
	}
	
	public List<User> getDocFinalApprovers() {
		return wfDocApproverManager.loadAllFinalApprovers(DeviceCard.class);
	}
	
	public boolean isDocSubmitted() {
		if (device.isNew()) {
			return false;
		}
		return wfJobManager.isJobSubmitted(device);
	}
	
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	public String getApprovers() {
		return wfJobManager.getJobApprovers(device);
	}
	
	public Long getFinalApprover() {
		return wfJobManager.getJobFinalApproverId(device);
	}
	
	public Job getJob() {
		return wfJobManager.getJob(device);
	}
	
	/**
	 * 获取管理级别的所有值
	 * @return
	 */
	public List<LabelValue> getManagementLevels() {
		LabelValue [] arrays = this.wrapEnum(ManagementLevel.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	/**
	 * 获取设备类别的所有值
	 * @return
	 */
	public List<DeviceType> getDeviceTypes() {
		return this.deviceTypeManager.loadAllDeviceTypes();
	}
	
	public List getUnitType() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}
	
	public List getUseCategory() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.PRODUCT_USED_TYPE);
	}
	
	public List getExcellentBigSparseType() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.EXCELLENT_BIG_SPARSE);
	}
	
	public List getFiliales(){
		return this.filialeManager.loadAllFiliale();
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public FilialeManager getFilialeManager() {
		return filialeManager;
	}

	public void setFilialeManager(FilialeManager filialeManager) {
		this.filialeManager = filialeManager;
	}
}
