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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.business.AcceptEstablishAccount;
import com.yongjun.tdms.model.prophase.business.AcceptResult;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
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
public class ListAcceptToolingMakeDetail extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final AcceptBillManager acceptBillManager;
	private final AcceptBillDetailManager acceptBillDetailManager;
	private final DeviceCardManager deviceCardManager;
	private final DepartmentManager departmentManager;
	private String allToolingMakeDtlId;                    //获得所有的工装制作明细的ids
	private String allToolingMakeDtlGroupNo;               //获得所有的工装制作明细的图号
	private String allToolingMakeDtlAccount;               //获得所有的工装制作明细的数量
	private String allToolingMakeDtlUnitPrice;             //获得所有的工装制作明细的单价
	private String allToolingMakeDtlExecResult;            //获得所有的工装制作明细的验收结果
	private String allToolingMakeDtlEstablish;             //获取所有的工装制作明细的创建台帐的值
	private String allToolingMakeDtlDepartment;            //获取所有的工装制作明细的部门的值
	private String allPurchaseContractIds;
	private AcceptBill acceptBill;
	private List<AcceptBillDetail> acceptBillDetails;
	public ListAcceptToolingMakeDetail(AcceptBillManager acceptBillManager,AcceptBillDetailManager acceptBillDetailManager
			,DeviceCardManager deviceCardManager,DepartmentManager departmentManager){
		this.acceptBillManager=acceptBillManager;
		this.acceptBillDetailManager=acceptBillDetailManager;
		this.deviceCardManager=deviceCardManager;
		this.departmentManager=departmentManager;
	}
	public void prepare() {
		if (this.hasId("acceptBill.id")) {         //如果请求中包含"yearPlan.id",则根据"yearPlan.id"获取年度计划对象
			this.acceptBill = this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
		if(this.hasIds("toolingMakeDetailIds")&&acceptBillDetails==null){
			acceptBillDetails=this.acceptBillDetailManager.loadAcceptBillDetais(this.getIds("toolingMakeDetailIds"));
		}
		//标识是否创建台帐
		if(!StringUtils.isEmpty(request.getParameter("estalishAccountFlag"))){
			for(AcceptBillDetail detail:acceptBillDetails){
				this.acceptBillDetailManager.storeAcceptBillDetail(detail);
			}
		}
		//获得所有从采购合同ids
		if(null==this.allPurchaseContractIds){
			if(!StringUtils.isEmpty(request.getParameter("addPurchaseContractIds"))){
				this.allPurchaseContractIds = request.getParameter("addPurchaseContractIds");
			}
		}
		//acceptBillDetailManager.u
		this.setFirst(false);
	}
	public String execute() {
		if(this.isSaveDetail()){						
			return saveToolingMakeDetail();
		}
		if(this.isAddPurchaseContractDetail()){
			return savePurchaseContractDetail();
		}
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}
	public String savePurchaseContractDetail(){
		this.acceptBillDetailManager.storePurchaseContractDtl(acceptBill,allPurchaseContractIds);
		return SUCCESS;
	}
	public String delete(){
		try{
			acceptBillDetailManager.deleteToolingAllAcceptBillMakeDetails(acceptBillDetails,acceptBill);
		}catch(Exception e){
			System.out.println("=====================删除失败");
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	/**
	 * 判断页面是不是季度计划工装制作明细选择而来
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
	public String saveToolingMakeDetail() {
		
       //如果请求中包含"allToolingMakeDtlIds",则根据"allToolingMakeDtlIds"工装制作明细的所有ids
		 if (!StringUtils.isEmpty(request.getParameter("allToolingMakeDtlIds"))) {
			 this.allToolingMakeDtlId = request.getParameter("allToolingMakeDtlIds");
		 }
		 //如果请求中包含"allToolingMakeDtlGroupNoValue",则根据"allToolingMakeDtlGroupNoValue"工装制作明细的所有图号
		 if (!StringUtils.isEmpty(request.getParameter("allToolingMakeDtlGroupNoValue"))) {
			 this.allToolingMakeDtlGroupNo = request.getParameter("allToolingMakeDtlGroupNoValue");
		 }
		 //如果请求中包含"allToolingMakeDtlUnitPrice",则根据"allToolingMakeDtlUnitPrice"工装制作明细的所有单价
		 if (!StringUtils.isEmpty(request.getParameter("allToolingMakeDtlUnitPriceValue"))) {
			 this.allToolingMakeDtlUnitPrice = request.getParameter("allToolingMakeDtlUnitPriceValue");
			 System.out.println("fdgdfgdfgdg"+allToolingMakeDtlUnitPrice);
		 }
        //如果请求中包含"allToolingMakeDtlAmountValue",则根据"allToolingMakeDtlAmountValue"工装制作明细的所有单价
		 if (!StringUtils.isEmpty(request.getParameter("allToolingMakeDtlAmountValue"))) {
			 this.allToolingMakeDtlAccount = request.getParameter("allToolingMakeDtlAmountValue");
		 }
        //如果请求中包含"allPreRepairProcExecResult",则根据"allPreRepairProcExecResult"工装制作明细的所有单价
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairProcExecResult"))) {
			 this.allToolingMakeDtlExecResult = request.getParameter("allPreRepairProcExecResult");
		 }
       //如果请求中包含"allAcceptToolingMakeEstablishAccount",则根据"allAcceptToolingMakeEstablishAccount"工装制作明细的所有创建台帐
		 if (!StringUtils.isEmpty(request.getParameter("allAcceptToolingMakeEstablishAccount"))) {
			 this.allToolingMakeDtlEstablish = request.getParameter("allAcceptToolingMakeEstablishAccount");
		 }
		  //如果请求中包含"allListToolingMakeDetailDepartment",则根据"allListToolingMakeDetailDepartment"工装制作明细的所有部门
		 if (!StringUtils.isEmpty(request.getParameter("allListToolingMakeDetailDepartment"))) {
			 this.allToolingMakeDtlDepartment = request.getParameter("allListToolingMakeDetailDepartment");
		 }
		 if(null!=allToolingMakeDtlId||null!=allToolingMakeDtlGroupNo||
				 null!=allToolingMakeDtlUnitPrice||null!=allToolingMakeDtlAccount
				 ||null!=allToolingMakeDtlExecResult||null!=allToolingMakeDtlExecResult
				 ||null!=allToolingMakeDtlDepartment){
			 this.acceptBillDetailManager.storeToolingMakeDetail(allToolingMakeDtlId,allToolingMakeDtlGroupNo,allToolingMakeDtlUnitPrice,allToolingMakeDtlAccount
					 ,allToolingMakeDtlExecResult,allToolingMakeDtlEstablish,allToolingMakeDtlDepartment);
		 }
		return SUCCESS;
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
	/**
	 * 获取明细列表中创建台帐的集合
	 * @return List 创建台帐的集合
	 */
	public List<LabelValue> getEstablishAccounts() {
		LabelValue[] arrays = this.wrapEnum(AcceptEstablishAccount.class);
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
	/**
	 * 获取所有部门的集合
	 * @return List 部门的集合
	 */
	
	public List getDepartments() {
			return departmentManager.createSelectDepartments(this
					.getText(""));

	}
	
	@Override
	protected String getAdapterName() {
		
		return "acceptToolingMakeDtl";
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
