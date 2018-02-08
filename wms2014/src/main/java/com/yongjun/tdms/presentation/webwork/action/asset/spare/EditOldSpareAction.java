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
package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.spare.ProducingAreaType;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SparePropertyType;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * <p>Title: EditSpareAction
 * <p>Description: 备件页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mwei@yj-technology.com
 * @version $Id: EditSpareAction.java 29428 2010-12-14 07:39:59Z jyang $
 */
public class EditOldSpareAction extends PrepareAction {

	private static final long serialVersionUID = -5057358589326070345L;

	private final CodeValueManager codeValueManager;
	private final SpareManager spareManager;
	private final DepartmentManager departmentManager;
	private final SupplierManager supplierManager;
	private final DeviceCardManager deviceCardManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	private final UserManager userManager;
	private final SpareTypeManager spareTypeManager;
	
	private Spare spare;
	private Supplier supplier;
	private Supplier factory;
//	private DeviceCard device; // 此处的device表示asset,及代表设备和工装
//	private Department department;
	private CodeValue codeValue;
	private String propertyTypeString;
	private String toolingDevFlag;
	private String producingAreaTypeString;
	private String spareLogValue;

	public String getSpareLogValue() {
		return spareLogValue;
	}

	public void setSpareLogValue(String spareLogValue) {
		this.spareLogValue = spareLogValue;
	}

	public EditOldSpareAction(SpareManager spareManager,
			CodeValueManager codeValueManager,
			DepartmentManager departmentManager,
			SupplierManager supplierManager, DeviceCardManager deviceCardManager,
			SpareDetailTypeManager spareDetailTypeManager,UserManager userManager,SpareTypeManager spareTypeManager) {
		this.spareManager = spareManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
		this.supplierManager = supplierManager;
		this.deviceCardManager = deviceCardManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.userManager = userManager;
		this.spareTypeManager = spareTypeManager;
	}

	/**
	 * 根据页面传递的toolingDevFlag判断是工装还是设备
	 * 根据页面面传递的spare.id，获取备件对象，如果没有则初始化备件对象
	 * 根据页面传递的spare.producingAreaType和spare.propertyType，获取取产地类型和备件属性字符串
	 * 根据页面传递的category.id,supplier.id,department.id,获取相应的对象并设置给备件对象
	 * 根据页面传递的oldSpareSafeStock，获取备件id号和值的字符串
	 * 
	 * @param 
	 * @return 
	 */
	public void prepare() throws Exception {
		if (request.getParameter("toolingDevFlag") != null) {
			if (request.getParameter("toolingDevFlag").equals("TOOLING")) {
				this.toolingDevFlag = "TOOLING";
			} 
			if (request.getParameter("toolingDevFlag").equals("DEVICE")) {
				this.toolingDevFlag = "DEVICE";
			} 
			//else {
	//			this.toolingDevFlag = "DEVICE";
	//		}
			if (request.getParameter("toolingDevFlag").equals("TOOLINGDEVICE")) {
				this.toolingDevFlag = "TOOLINGDEVICE";
			}
		}
		if (spare == null) {
			if (this.hasId("spare.id")) {
				this.spare = this.spareManager
						.loasSpare(this.getId("spare.id"));
					if(!this.hasId("supplier.id")){
						this.supplier = this.spare.getSupplier();
					}
					if(!this.hasId("factory.id")){
						this.factory = this.spare.getFactory();
					}
					//this.device = this.spare.getDevice();
				 
				
			} else {
				this.spare = new Spare();
				if (request.getParameter("oldSpare") != null && request.getParameter("oldSpare").equals("0")) {
					this.spare.setOldSpare("0");
				} 
			}
			propertyTypeString = request.getParameter("spare.propertyType");
			producingAreaTypeString = request
					.getParameter("spare.producingAreaType");
		}
		if (this.toolingDevFlag != null) {
			if (this.toolingDevFlag.equals("TOOLING")) {
				this.spare.setToolingDevFlag(SysModel.TOOLING);
			}
			else if(this.toolingDevFlag.equals("DEVICE")) {
				this.spare.setToolingDevFlag(SysModel.DEVICE);
			}
		}

//		if (hasId("tooling.id")) {
//			this.device = this.deviceCardManager.loadDevice(this
//					.getId("tooling.id"));
//			this.spare.setDevice(device);
//		}
//		if (hasId("device.id")) {
//			this.device = this.deviceCardManager.loadDevice(this
//					.getId("device.id"));
//			this.spare.setDevice(device);
//		}
		
		if (hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(this
					.getId("supplier.id"));
			this.spare.setSupplier(supplier);
		}
		if(hasId("factory.id")){
			this.factory = this.supplierManager.loadSupplier(this.getId("factory.id"));
			this.spare.setFactory(factory);
			this.spare.setFactoryStr(factory.getName());
		}
//		if (hasId("supplier.id")) {
//			this.supplier = this.supplierManager.loadSupplier(this
//					.getId("supplier.id"));
//			this.spare.setSupplier(supplier);
//		}
		
		if(spare!=null){
			if(request.getParameter("oldSpareSafeStock")!=null && !request.getParameter("oldSpareSafeStock").equals("")){
				spareLogValue=spare.getId()+","+request.getParameter("oldSpareSafeStock").toString()+",";
			}else{
				spareLogValue=spare.getId()+","+0+",";
			}
			if(request.getParameter("oldSpareStock")!=null && !request.getParameter("oldSpareStock").equals("")){
				spareLogValue+=request.getParameter("oldSpareStock").toString()+",";
			}else{
				spareLogValue+=0+",";
			}
		}		
	}

	/**
	 * 保存spare对象,并提示是否成功消息
	 * 
	 * @param 
	 * @return String SUCCESS 或者 NEW
	 */
	public String save()throws IOException{
		System.out.println("============================");
		
         //xschen/2009/03/09
		 //备件台帐增加型号和规格的唯一性约束,如果备件台帐中存在备件型号和规格重复 则抛出异常
//		if ((!StringUtils.isEmpty(request.getParameter("modelSpec")))){
//			String modelSpare=request.getParameter("modelSpec");
//		
//			try{
//				List list=spareManager.getSpareCollentionByModel(modelSpare);
//				if(list.size()>0){
//					Spare s=(Spare)list.get(0);//从数据库中获取备件
//					if(s.getSpareNo()==spare.getSpareNo()){//判断是否是同种备件
//						spare.setModelSpecs(modelSpare);
//					}else{
//					throw new Exception();
//					}
//				}else{
//					spare.setModelSpecs(modelSpare);
//				}
//			}catch (Exception e) {
//				this.addActionError(this.getText("modelSpare.already.exists",
//	             Arrays.asList(new Object[]{request.getParameter("modelSpec")})));
//				return ERROR;
//			}	
//
//		}
//      //备件台帐增加型号和规格的唯一性约束,如果备件台帐中存在备件型号和规格重复 则抛出异常
//		if (!StringUtils.isEmpty(request.getParameter("specification"))){
//			String specifications=request.getParameter("specification");
//			try{
//				List list=spareManager.getSpareCollentionBySpec(specifications);
//				if(list.size()>0){
//					Spare s=(Spare)list.get(0);//从数据库中获取备件
//					if(s.getSpareNo()==spare.getSpareNo()){//判断是否是同种备件
//						spare.setSpecification(specifications);
//					}else{
//						throw new Exception();
//					}
//				}else{
//					spare.setSpecification(specifications);
//				}
//			}catch (Exception e) {
//				this.addActionError(this.getText("specifications.already.exists",
//	             Arrays.asList(new Object[]{request.getParameter("specification")})));
//				return ERROR;
//			}	
//		}
		
		boolean isNew = this.spare.isNew();
		System.out.println("toolingDevFlag"+this.toolingDevFlag);
		if (request.getParameter("spare.toolingDevFlag").equals("TOOLING")) {
			this.spare.setToolingDevFlag(SysModel.TOOLING);
		}
		else if (request.getParameter("spare.toolingDevFlag").equals("DEVICE")) {
			this.spare.setToolingDevFlag(SysModel.DEVICE);
		}
		else {
			return ERROR;
		}
		/*
		 *临时解决 因为现在新备件数据有些没有型号，有些型号相同，无法建立台帐，所以
		 *本来根据型号来区别唯一备件现在不用    2010-1-7    张志宝
		 */
		String modelSpare=request.getParameter("modelSpecs").trim();   //型号
		//String specifications=request.getParameter("specification").trim(); //规格
		spare.setModelSpecs(modelSpare);
		//spare.setSpecification(specifications);
		
//		//保管人 
//		if(!StringUtils.isEmpty(request.getParameter("custos.id"))){
//			User user = userManager.loadUser(Long.valueOf(request.getParameter("custos.id")));
//			this.spare.setSpareCustos(user);
//			this.spare.setCustos(user.getName());
//		}
		
		if (propertyTypeString.equals("")) {
			spare.setPropertyType(null);
		} else if (propertyTypeString.equals("NORMAL")) {
			spare.setPropertyType(SparePropertyType.NORMAL);
		} else if (propertyTypeString.equals("MAIN")) {
			spare.setPropertyType(SparePropertyType.MAIN);
		}else{
			spare.setPropertyType(SparePropertyType.CRITICAL);
		}
		
		// 产地类型判断
		if (producingAreaTypeString == null || producingAreaTypeString.equals("")) {
			spare.setProducingAreaType(null);
		} else if (producingAreaTypeString.equals("LOCAL")) {
			spare.setProducingAreaType(ProducingAreaType.LOCAL);
		} else {
			spare.setProducingAreaType(ProducingAreaType.FOREIGN);
		}
		if (this.isEnabled()) {
			spare.setDisabled(true);
		}
		if (!StringUtils.isEmpty(request.getParameter("spare.tenderPartFlag"))) {
			if (request.getParameter("spare.tenderPartFlag").equals("N")){
				spare.setTenderPartFlag(false);
			}else {
				spare.setTenderPartFlag(true);
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("spare.wearingPartFlag"))) {
			if (request.getParameter("spare.wearingPartFlag").equals("N")){
				spare.setWearingPartFlag(false);
			}else {
				spare.setWearingPartFlag(true);
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("spare.heavyRepairPartFlag"))) {
			if (request.getParameter("spare.heavyRepairPartFlag").equals("N")){
				spare.setHeavyRepairPartFlag(false);
			}else {
				spare.setHeavyRepairPartFlag(true);
			}
		}
		System.out.println("======= ttt  " + request.getParameter("category.id"));
		if (!StringUtils.isEmpty(request.getParameter("category.id"))) {
			SpareType st = this.spareTypeManager.loadSpareType(this.getId("category.id"));
			this.spare.setCategory(st);
			
		} else {
			this.spare.setCategory(null);

		}
		
		if (!StringUtils.isEmpty(request.getParameter("spareDetailType.id"))) {
			this.spare.setSpareDetailType(this.spareDetailTypeManager.loadSpareDetailType(this
					.getId("spareDetailType.id")));
		} else {
			this.spare.setSpareDetailType(null);
		}
		
//		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
//			this.spare.setDepartment(this.departmentManager.loadDepartment(this
//					.getId("department.id")));
//		}else {
//			this.spare.setDepartment(null);
//		}
		//单位
		if (!StringUtils.isEmpty(request.getParameter("unit.id"))) {
			this.spare.setUnit(this.codeValueManager.loadCodeValue(this
					.getId("unit.id")));
		} else {
			this.spare.setUnit(null);
		}
		//供应商
		if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {
			this.spare.setSupplier(this.supplierManager
			.loadSupplier(getId("supplier.id")));
		} else {
			this.spare.setSupplier(null);
		}
		//生产厂家
		if (!StringUtils.isEmpty(request.getParameter("factory.id"))) {
			this.spare.setFactory(this.supplierManager
			.loadSupplier(getId("factory.id")));
		} else {
			this.spare.setFactory(null);
		}
		//安全库存
		if (spare.getSafeStock()==null){
			spare.setSafeStock(Long.valueOf(0));
		}
		//总库存
		if (spare.getStocks()==null){
			spare.setStocks(Long.valueOf(0));
		}
		//判断备件名称（中文）和型号是否已经存在
//		if (this.spareManager.getSpareNumByModelAndName(spare.getName(), spare.getModelSpecs())>0) {
//			this.addActionError(this.getText("spare.already.exists",
//		             Arrays.asList(new Object[]{spare.getName(),spare.getModelSpecs()})));
//					return ERROR;
//		}
		if(isNew){
			this.spareManager.storeSpare(spare);
		}else{
			this.spareManager.storeSpare(spare,spareLogValue);
		}
		if (isNew) {
			this.addActionMessage(this.getText("spare.add.success", Arrays
					.asList(new Object[] { spare.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("spare.edit.success", Arrays
					.asList(new Object[] { spare.getName() })));
			return SUCCESS;
		}
	}

	/**
	 * 判断是否点击有效按钮
	 * 
	 * @param 
	 * @return true|false
	 */
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	// public void setSupplier(Supplier supplier) {
	// this.supplier = supplier;
	// }

	/**
	 * 获取备件大分类
	 */
	@SuppressWarnings("unchecked")
	public List getSpareType() {
		List allSpareTypeList = new ArrayList();
		List spareTypeList = new ArrayList();
		allSpareTypeList =spareTypeManager.loadAllSpareType();
		for (int i=0;i<allSpareTypeList.size();i++){        //区分是工装[TOOLING]或设备[DEVICE]
			if (((SpareType)allSpareTypeList.get(i)).getToolingDevFlag().toString().equals(this.toolingDevFlag)){
				spareTypeList.add(allSpareTypeList.get(i));
			}
			//获得[工装]和[设备]的备件分类
		if (request.getParameter("toolingDevFlag").equals("TOOLINGDEVICE")) {
				spareTypeList.add(allSpareTypeList.get(i));
			}
		}
//		List allSpareTypeList = new ArrayList();
//		List spareTypeList = new ArrayList();
//		allSpareTypeList =codeValueManager.LoadAllValuesByCode(CodeConstants.SPARE_TYPE);
//		for (int i=0;i<allSpareTypeList.size();i++){        //区分是工装[TOOLING]或设备[DEVICE]
//			if (((CodeValue)allSpareTypeList.get(i)).getRealCode().toString().equals(this.toolingDevFlag)){
//				spareTypeList.add(allSpareTypeList.get(i));
//			}
//          //获得[工装]和[设备]的备件分类
//			if (request.getParameter("toolingDevFlag").equals("TOOLINGDEVICE")) {
//				spareTypeList.add(allSpareTypeList.get(i));
//			}
//		}
		return spareTypeList;
		
	}
	
	/**
	 * 获取备件明细分类
	 * @return
	 */
	public List getSpareDetailType (){
		return spareDetailTypeManager.loadAllSpareDetailTypes();
	}

	public List getSpareUnitType() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}

	public List getDepartments() {
		return departmentManager.loadAllDepartments();
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public CodeValue getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(CodeValue codeValue) {
		this.codeValue = codeValue;
	}

	/**
	 * 加裁备件属性对象的集合
	 * 
	 * @param 
	 * @return 备件属性对象集合
	 */
	public List<LabelValue> getSpareProperty() {
		LabelValue[] arrays = this.wrapEnum(SparePropertyType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public String getPropertyTypeString() {
		return propertyTypeString;
	}

	public void setPropertyTypeString(String propertyTypeString) {
		this.propertyTypeString = propertyTypeString;
	}

	/**
	 * 加裁产地对象的集合
	 * 
	 * @param 
	 * @return 产地对象集合
	 */
	public List<LabelValue> getProducingAreaType() {
		LabelValue[] arrays = this.wrapEnum(ProducingAreaType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public String getProducingAreaTypeString() {
		return producingAreaTypeString;
	}

	public void setProducingAreaTypeString(String producingAreaTypeString) {
		this.producingAreaTypeString = producingAreaTypeString;
	}

	public Supplier getFactory() {
		return factory;
	}

	public void setFactory(Supplier factory) {
		this.factory = factory;
	}

}
