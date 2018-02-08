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
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
/**
 * <p>Title: ListRepairMaintenanceDetail
 * <p>Description: 工装制作明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id:$
 */
public class ListAcceptRepairMaintenanceDetailAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final AcceptBillManager  acceptBillManager;
	private final AcceptBillDetailManager  acceptBillDetailManager;
	private AcceptBill  acceptBill;
	private List<AcceptBillDetail>  acceptBillDetails;
	private String addPurchaseContractIds;		//从采购合同选过来的数据
	private String allRepairMaintenanceId;      //标识所有的维修明细的ids 
	private String allRepairMaintenanceResult;  //标识所有的维修明细的验收结果
	private String allRepairMaintenanceMemo;    //标识所有维修明细的备注
	public ListAcceptRepairMaintenanceDetailAction(AcceptBillManager  acceptBillManager,AcceptBillDetailManager  acceptBillDetailManager){
		this.acceptBillManager=acceptBillManager;
		this.acceptBillDetailManager=acceptBillDetailManager;
	}
	public void prepare() {
		if (this.hasId("acceptBill.id")) {         //如果请求中包含"acceptBill.id",则根据"acceptBill.id"获取验收单对象
			this.acceptBill = this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
		if(this.hasIds("repairMaintennanceIds")&&acceptBillDetails==null){
			acceptBillDetails=this.acceptBillDetailManager.loadAcceptBillDetais(this.getIds("repairMaintennanceIds"));
		}
		//标识是否创建台帐
		if(!StringUtils.isEmpty(request.getParameter("estalishAccountFlag"))){
			for(AcceptBillDetail detail:acceptBillDetails){
				this.acceptBillDetailManager.storeAcceptBillDetail(detail);
			}
		}
		if(null==this.addPurchaseContractIds){
			if(!StringUtils.isEmpty(request.getParameter("addPurchaseContractIds"))){
				this.addPurchaseContractIds = request.getParameter("addPurchaseContractIds");
			}
		}
		this.setFirst(false);
	}
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("acceptBill.id",this.getId("acceptBill.id"));
		return map;
	}
	public String execute() {
		if(this.isSaveDetail()){
			return saveRepairMaintenanceDetail();
		}
		if(this.isAddPurchaseContract()){
			return addPurchaseContract();
		}
		if(this.isDelete()){
			return delete();
		}
			return SUCCESS;
	}
	public String delete(){
		try{
			acceptBillDetailManager.deleteToolingAllAcceptBillMakeDetails(acceptBillDetails);	
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	public String addPurchaseContract() {
		acceptBillDetailManager.storePurchaseContractDtl(acceptBill,addPurchaseContractIds);
		return  SUCCESS;
	}
	public String saveRepairMaintenanceDetail(){
		//获得维修明细的id
		if (!StringUtils.isEmpty(request.getParameter("allrepairMaintennanceDtlIds"))) {
			 this.allRepairMaintenanceId = request.getParameter("allrepairMaintennanceDtlIds");
		 }
		//获取从页面传来的执行结果
		if (!StringUtils.isEmpty(request.getParameter("allPreRepairProcExecResult"))) {
			 this.allRepairMaintenanceResult = request.getParameter("allPreRepairProcExecResult");
		 }
		//获取备注
		
		if (!StringUtils.isEmpty(request.getParameter("allrepairMaintennanceDtlMemoValue"))) {
			 this.allRepairMaintenanceMemo = request.getParameter("allrepairMaintennanceDtlMemoValue");
		 }
		if(null!=allRepairMaintenanceId||null!=allRepairMaintenanceResult||null!=allRepairMaintenanceMemo){
			this.acceptBillDetailManager.storeAcceptRepairMaintenanceDetail(allRepairMaintenanceId,allRepairMaintenanceResult,allRepairMaintenanceMemo);
		}
		return SUCCESS;
	}
	/**
	 * 判断页面是否保存工装制作明细
	 * @return true | false
	 */
	private boolean isSaveDetail() {
		if (!StringUtils.isEmpty(request.getParameter("save"))){
		   if(this.hasKey("save")){
			  return true;
		   }
		}
		return false;
	}
	
	private boolean isAddPurchaseContract(){
		if(!StringUtils.isEmpty(request.getParameter("purchaseContractDtlSelector"))){
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
	protected String getAdapterName() {
		
		return "repairMaintenances";
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
