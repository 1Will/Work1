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
package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.accept;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.business.AcceptResult;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
/**
 * <p>Title: ListAcceptToolingMakeDetail
 * <p>Description: 工装制作明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id:$
 */
public class ListSparePurchaseDetail extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final AcceptBillManager acceptBillManager;
	private final AcceptBillDetailManager acceptBillDetailManager;
	private final DepartmentManager departmentManager;
	private AcceptBill acceptBill;
	private List<AcceptBillDetail> acceptBillDetails;
	private String allPurchaseContractIds;			//从采购合同而来
	private String addSpareDetailId;              //获得所有的备件台帐的ids
	private String spareAccountSelector;          //标识是否从备件台帐选择 
	private String allSparePurchaseDtlId;         //标识所有备件采购的ids 
	private String allSparePurchaseDtlUnitPrice;  //标识所有的备件采购明细的单价
	private String allSparePurchaseDtlAmount;     //标识所有的备件采购明细的数量
	private String allPreRepairProcExecResult;   //标识所有备件采购明细的执行结果
	private String allSparePurchaseDtlMemo;      //标识所有的备件采购明细的备注
	public ListSparePurchaseDetail(AcceptBillManager acceptBillManager,
			AcceptBillDetailManager acceptBillDetailManager,DepartmentManager departmentManager){
		this.acceptBillManager=acceptBillManager;
		this.acceptBillDetailManager=acceptBillDetailManager;
		this.departmentManager=departmentManager;
	}
	public void prepare() {
		if (this.hasId("acceptBill.id")) {         //如果请求中包含"acceptBill.id",则根据"acceptBill.id"获取验收单对象
			this.acceptBill = this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
		if(this.hasIds("sparePurchaseDetailIds")&&acceptBillDetails==null){
			acceptBillDetails=this.acceptBillDetailManager.loadAcceptBillDetais(this.getIds("sparePurchaseDetailIds"));
		}
		//标识是否创建台帐
		if(!StringUtils.isEmpty(request.getParameter("estalishAccountFlag"))){
			for(AcceptBillDetail detail:acceptBillDetails){
				this.acceptBillDetailManager.storeAcceptBillDetail(detail);
			}
		}
		//获得所有的备件台帐的ids
		if(null==this.addSpareDetailId){
			if(!StringUtils.isEmpty(request.getParameter("addSpareDetailIds"))){
				this.addSpareDetailId = request.getParameter("addSpareDetailIds");
			}
		}
		if(null==this.allPurchaseContractIds){
			if(!StringUtils.isEmpty(request.getParameter("addPurchaseContractIds"))){
				this.allPurchaseContractIds = request.getParameter("addPurchaseContractIds");
			}
		}
		this.setFirst(false);
	}
	public String execute() {
       if(this.isAddSpareOfToolingAcceptBillDetail()) {
    	   return saveSpareAccountToAcceptBillSparePurchaseDetail();
       }
       if(this.isSaveSpareAccountToAcceptSparePurchaseDtl()){
    	   return saveSparepurchaseDtl();
       }
       if(this.isAddPurchaseContractDetail()){
		return savePurchaseContractDetail();
       }
       if(this.isDelete()){
    	   return delete();
       }
		return SUCCESS;
	}
	public String delete(){
		try{
			this.acceptBillDetailManager.deleteToolingAllAcceptBillMakeDetails(acceptBillDetails);
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	public String saveSpareAccountToAcceptBillSparePurchaseDetail(){
		this.acceptBillDetailManager.storeSpareAccountToAcceptSparePurchaseDetail(acceptBill,addSpareDetailId);
		return SUCCESS;
	}
	public String saveSparepurchaseDtl(){
		 //如果请求中包含"allSparePurchaseDtlIds",则根据"allSparePurchaseDtlIds"备件采购明细的所有ids
		 if (!StringUtils.isEmpty(request.getParameter("allSparePurchaseDtlIds"))) {
			 this.allSparePurchaseDtlId = request.getParameter("allSparePurchaseDtlIds");
		 }
		 //如果请求中包含"allSparePurchaseDtlUnitPrice",则根据"allSparePurchaseDtlUnitPrice"备件采购明细的所有单价
		 if (!StringUtils.isEmpty(request.getParameter("allSparePurchaseDtlUnitPriceValue"))) {
			 this.allSparePurchaseDtlUnitPrice = request.getParameter("allSparePurchaseDtlUnitPriceValue");
			
		 }
       //如果请求中包含"allToolingMakeDtlAmountValue",则根据"allToolingMakeDtlAmountValue"工装制作明细的所有单价
		 if (!StringUtils.isEmpty(request.getParameter("allSparePurchaseDtlAmountValue"))) {
			 this.allSparePurchaseDtlAmount = request.getParameter("allSparePurchaseDtlAmountValue");
		 }
       //如果请求中包含"allPreRepairProcExecResult",则根据"allPreRepairProcExecResult"工装制作明细的所有单价
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairProcExecResult"))) {
			 this.allPreRepairProcExecResult = request.getParameter("allPreRepairProcExecResult");
		 }
		 //如果请求中包含"allPreRepairProcExecResult",则根据"allPreRepairProcExecResult"工装制作明细的所有单价
		 if (!StringUtils.isEmpty(request.getParameter("allSparePurchaseDtlMemoValue"))) {
			 this.allSparePurchaseDtlMemo = request.getParameter("allSparePurchaseDtlMemoValue");
		 }
		 if(null!=allSparePurchaseDtlId||null!=allSparePurchaseDtlUnitPrice||null!=allSparePurchaseDtlAmount||null!=allPreRepairProcExecResult
				 ||null!=allSparePurchaseDtlMemo){
			 this.acceptBillDetailManager.storeAcceptSparePurchaseDetail(acceptBill,allSparePurchaseDtlId,allSparePurchaseDtlUnitPrice,
					 allSparePurchaseDtlAmount
					 ,allPreRepairProcExecResult,
					 allSparePurchaseDtlMemo);
		 }
		return SUCCESS;
	}
	/**
	 * 判断页面是不是从备件台帐选择而来
	 * @return
	 */
	private boolean isAddSpareOfToolingAcceptBillDetail(){
		if (!StringUtils.isEmpty(request.getParameter("spareAccountSelector"))) {
			if (request.getParameter("spareAccountSelector").equals("spareAccount"))
				return true;
		}
		return false;
	}
	/**
	 * 保存采购合同明细
	 * @return
	 */
	public String savePurchaseContractDetail(){
		this.acceptBillDetailManager.storePurchaseContractDtl(acceptBill,allPurchaseContractIds);
		return SUCCESS;
	}
	/**
	 * 是否保存从备件台帐选择
	 * 
	 */
	private boolean isSaveSpareAccountToAcceptSparePurchaseDtl(){
		if (!StringUtils.isEmpty(request.getParameter("save"))){
			   if(this.hasKey("save")){
				  return true;
			   }
			}
			return false;
	}
	/**
	 * 判断页面是不是从采购合同明细选择而来
	 * @return true | false
	 */
	private boolean isAddPurchaseContractDetail(){
		if (!StringUtils.isEmpty(request.getParameter("purchaseContractDtlSelector"))) {
			if (request.getParameter("purchaseContractDtlSelector").equals("purchaseContract"))
				return true;
		}
		return false;
	}
	/**
	 * 获取明细列表中执行结果集合
	 * @return List 执行结果集合
	 */
	public List<LabelValue> getProcResults() {
		LabelValue[] arrays = this.wrapEnum(AcceptResult.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("acceptBill.id",this.getId("acceptBill.id"));
		return map;
	}

	@Override
	protected String getAdapterName() {
		
		return "sparePurchaseDetail";
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}
	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}
	public List<AcceptBillDetail> getAcceptBillDetails() {
		return acceptBillDetails;
	}
	public void setAcceptBillDetails(List<AcceptBillDetail> acceptBillDetails) {
		this.acceptBillDetails = acceptBillDetails;
	}

}
