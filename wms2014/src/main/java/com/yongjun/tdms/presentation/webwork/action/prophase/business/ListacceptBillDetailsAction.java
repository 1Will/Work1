package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;

public class ListacceptBillDetailsAction extends ValueListAction{

	private static final long serialVersionUID = 1L;
    
	private final AcceptBillDetailManager acceptBillDetailManager;
	private final AcceptBillManager acceptBillManager;
    private AcceptBill acceptBill;        //验收单的对象
    private AcceptBillDetail  acceptBillDetail;   //验收单明细的对象
    private List<AcceptBillDetail> acceptBillDtls;
	public ListacceptBillDetailsAction(AcceptBillDetailManager acceptBillDetailManager,
			AcceptBillManager acceptBillManager){
		 this.acceptBillDetailManager=acceptBillDetailManager;
		 this.acceptBillManager=acceptBillManager;
	}
	public void prepare() {
		if (null == this.acceptBill) {//获得验收单对象,目的是说明当前验收单明细对象是属于哪个验收单的
			if (this.hasId("acceptBill.id")) {
				this.acceptBill = this.acceptBillManager.loadAcceptBill(getId("acceptBill.id"));
				
			} 
		}
		if (this.acceptBillDtls == null && this.hasIds("acceptBillDtlIds")) {//获得验收单明细对象
            this.acceptBillDtls = this.acceptBillDetailManager.loadAcceptBillDetais(this.getIds("acceptBillDtlIds"));
        } 	
	
		this.setFirst(false);
	}
	
   public String execute() {
		if(this.hasKey("delete")){
			this.acceptBillDetailManager.deleteAllAcceptBillDetails(acceptBillDtls);
			this.addActionMessage(this.getText("PurchaseBillDetails.delete.success"));
		}
		
		return SUCCESS;
	}
  /**
    * 往查询采购明细的hql语句中设置验收单的ID值
  */
 @SuppressWarnings("unchecked") 
 protected Map getRequestParameterMap() {
	Map map = super.getRequestParameterMap();
    if (this.hasId("acceptBill.id")){
		map.put("acceptBill.id", this.getId("acceptBill.id"));
	}
	return map;
}
	@Override
	protected String getAdapterName() {
		
		return "acceptDetail";
	}

	public AcceptBill getAcceptBill() {
		return acceptBill;
	}

	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}

	public AcceptBillDetail getAcceptBillDetail() {
		return acceptBillDetail;
	}

	public void setAcceptBillDetail(AcceptBillDetail acceptBillDetail) {
		this.acceptBillDetail = acceptBillDetail;
	}

}
