package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;

import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateDetail;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateDetailManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateManager;


public class ListSupplierEvaluateDetailAction extends PrepareAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final SupplierEavluateDetailManager supplierEavluateDetailManager;
	private final SupplierEavluateManager supplierEavluateManager;
	private SupplierEvaluateDetail supplierEavluateDetail;
	private String toolingDevFlag;
	private Supplier supplier;
	private SupplierEvaluate supplierEvaluate;
	private String supplierEavluategrade;//供应商评估的分值
	private String supplierEavluatecoment;//供应商评估的备注
    private CodeValue level;
	private String designCapabilityInfo;//供应商设计能力信息
	private String makeCapabilityInfo;//供应商制作能力信息
	private String qACapabilityInfo;  //供应商质量保证能力信息
	private String seviceCapabilityInfo;//供应商服务能力信息
	private String basicControlCapabilityInfo;//基本控制能力信息
	public ListSupplierEvaluateDetailAction(SupplierEavluateDetailManager supplierEavluateDetailManager,
			SupplierEavluateManager supplierEavluateManager){
		this.supplierEavluateDetailManager=supplierEavluateDetailManager;
		this.supplierEavluateManager=supplierEavluateManager;
	}
	public void prepare() throws Exception {
		if (null == supplierEavluateDetail) {
			if (this.hasId("supplierEavluateDetail.id")) {
				this.supplierEavluateDetail = this.supplierEavluateDetailManager
						.loadSupplierEvaluateDetail(this.getId("supplierEavluateDetail.id"));
				this.level=supplierEvaluate.getLevel();
				
			} else {
				this.supplierEavluateDetail = new SupplierEvaluateDetail();
			}
		}
		
		if (null == supplierEvaluate) {
			if (this.hasId("supplierEvaluate.id")) {
				this.supplierEvaluate = this.supplierEavluateManager
						.loadSupplierEvaluate(this.getId("supplierEvaluate.id"));
			    this.supplier=supplierEvaluate.getSupplier();
				
			}
		}
		if(this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
    public String execute() {
		
		if(this.isSave()){
			return save();
		}
		
		return SUCCESS;
	}
	/**
	 * 判断是否点击保存按钮
	 * @return true | false
	 */
	public boolean isSave(){
		
		return this.hasKey("save");
	}
	public String save(){
		if (!StringUtils.isEmpty(request.getParameter("designCapabilityInfo"))) {
			this.designCapabilityInfo = request.getParameter("designCapabilityInfo");
		}
		if (!StringUtils.isEmpty(request.getParameter("makeCapabilityInfo"))) {
			this.makeCapabilityInfo = request.getParameter("makeCapabilityInfo");
		}
		if (!StringUtils.isEmpty(request.getParameter("qACapabilityInfo"))) {
			this.qACapabilityInfo = request.getParameter("qACapabilityInfo");
		}
		if (!StringUtils.isEmpty(request.getParameter("seviceCapabilityInfo"))) {
			this.seviceCapabilityInfo = request.getParameter("seviceCapabilityInfo");
		}
		if (!StringUtils.isEmpty(request.getParameter("seviceCapabilityInfo"))) {
			this.seviceCapabilityInfo = request.getParameter("seviceCapabilityInfo");
		}
		if (!StringUtils.isEmpty(request.getParameter("basicControlCapabilityInfo"))) {
			this.basicControlCapabilityInfo = request.getParameter("basicControlCapabilityInfo");
		}
		this.supplierEavluateDetailManager.storeSupplierEvaluateDetail(supplierEvaluate,designCapabilityInfo, makeCapabilityInfo,qACapabilityInfo,seviceCapabilityInfo,basicControlCapabilityInfo);
		return SUCCESS;
	}
	public SupplierEvaluateDetail getSupplierEavluateDetail() {
		return supplierEavluateDetail;
	}
	public void setSupplierEavluateDetail(
			SupplierEvaluateDetail supplierEavluateDetail) {
		this.supplierEavluateDetail = supplierEavluateDetail;
	}
	public SupplierEvaluate getSupplierEvaluate() {
		return supplierEvaluate;
	}
	public void setSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
		this.supplierEvaluate = supplierEvaluate;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
//	public Supplier getSupplier() {
//		return supplier;
//	}
//	public void setSupplier(Supplier supplier) {
//		this.supplier = supplier;
//	}
	public SupplierEvaluateDetail getDesignCapability() {
		return this.supplierEavluateDetailManager.loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(supplierEvaluate.getId(),"DESIGN_CAPABILITY");
	}
	
	public SupplierEvaluateDetail getMakeCapability() {
		return this.supplierEavluateDetailManager.loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(supplierEvaluate.getId(), "MAKE_CAPABILITY");
	}
   
   public SupplierEvaluateDetail getQaCapability(){
	   return this.supplierEavluateDetailManager.loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(supplierEvaluate.getId(), "QA_CAPABILITY");
   }
   public SupplierEvaluateDetail getSeviceCapability(){
	   return this.supplierEavluateDetailManager.loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(supplierEvaluate.getId(), "SERVICE_CAPABILITY");
   }
   public SupplierEvaluateDetail getBasicControlCapability(){
	   return this.supplierEavluateDetailManager.loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(supplierEvaluate.getId(), "BASIC_CONTROL_CAPABILITY");
   }
public CodeValue getLevel() {
	return level;
}
public void setLevel(CodeValue level) {
	this.level = level;
}
}
