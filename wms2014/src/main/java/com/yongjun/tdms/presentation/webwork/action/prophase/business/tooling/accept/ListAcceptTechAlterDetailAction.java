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

public class ListAcceptTechAlterDetailAction extends ValueListAction{

	private static final long serialVersionUID = 1L;
	private final AcceptBillManager  acceptBillManager;
	private final AcceptBillDetailManager  acceptBillDetailManager;
	private AcceptBill  acceptBill;
	private List<AcceptBillDetail>  acceptBillDetails;
	private String addPurchaseContractIds;		//从采购合同选过来的数据
	private String allTechAlterId;      //标识所有的ids 
	private String allTechAlterResult;  //标识所有的维修明细的验收结果
	private String allTechAlterMemo;    //标识所有维修明细的备注
	public ListAcceptTechAlterDetailAction(AcceptBillManager  acceptBillManager,AcceptBillDetailManager  acceptBillDetailManager){
		this.acceptBillManager=acceptBillManager;
		this.acceptBillDetailManager=acceptBillDetailManager;
	}
	public void prepare() {
		if (this.hasId("acceptBill.id")) {         //如果请求中包含"acceptBill.id",则根据"acceptBill.id"获取验收单对象
			this.acceptBill = this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
		if(this.hasIds("techAlterIds")&&acceptBillDetails==null){
			acceptBillDetails=this.acceptBillDetailManager.loadAcceptBillDetais(this.getIds("techAlterIds"));
		}
		if(null==this.addPurchaseContractIds){
			if(!StringUtils.isEmpty(request.getParameter("addPurchaseContractIds"))){
				this.addPurchaseContractIds = request.getParameter("addPurchaseContractIds");
			}
		}
		this.setFirst(false);
	}
	public String saveAcceptTechAlterDetail(){
		//获得维修明细的id
		if (!StringUtils.isEmpty(request.getParameter("allrepairMaintennanceDtlIds"))) {
			 this.allTechAlterId = request.getParameter("allrepairMaintennanceDtlIds");
		 }
		//标识是否创建台帐
		if(!StringUtils.isEmpty(request.getParameter("estalishAccountFlag"))){
			for(AcceptBillDetail detail:acceptBillDetails){
				this.acceptBillDetailManager.storeAcceptBillDetail(detail);
			}
		}
		//获取从页面传来的执行结果
		if (!StringUtils.isEmpty(request.getParameter("allPreRepairProcExecResult"))) {
			 this.allTechAlterResult = request.getParameter("allPreRepairProcExecResult");
		 }
		//获取备注
		
		if (!StringUtils.isEmpty(request.getParameter("allrepairMaintennanceDtlMemoValue"))) {
			 this.allTechAlterMemo = request.getParameter("allrepairMaintennanceDtlMemoValue");
		 }
		if(null!=allTechAlterId||null!=allTechAlterResult||null!=allTechAlterMemo){
			this.acceptBillDetailManager.storeTechAlterAcceptDetail(allTechAlterId,allTechAlterResult,allTechAlterMemo);
		}
		return SUCCESS;
	}
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("acceptBill.id",this.getId("acceptBill.id"));
		return map;
	}
	public String execute() {
		if(this.isSaveDetail()){
			return saveAcceptTechAlterDetail();
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
		
		return "acceptTechAlters";
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
