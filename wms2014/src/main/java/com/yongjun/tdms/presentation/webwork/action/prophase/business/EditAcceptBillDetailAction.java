package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;

import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;

public class EditAcceptBillDetailAction extends PrepareAction{

	private static final long serialVersionUID = 1L;
	private final AcceptBillManager acceptBillManager;
    private final AcceptBillDetailManager acceptBillDetailManager ;
    private AcceptBill acceptBill;  // 验收单对象
    private AcceptBillDetail acceptBillDetail;//验收单明晰的对象
    public EditAcceptBillDetailAction(AcceptBillManager acceptBillManager,AcceptBillDetailManager acceptBillDetailManager){
    	    this.acceptBillManager=acceptBillManager;
    	    this.acceptBillDetailManager=acceptBillDetailManager;
    }

	public void prepare() throws Exception {
		if (this.hasId("acceptBill.id")) {//通过页面传来的验收单的id 获得验收单的对象
			this.acceptBill = this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
	
	if (null == acceptBillDetail) {//通过页面穿来的的验收单明细的id 获得验收单明细对象
		if (this.hasId("acceptBillDetail.id")) {
			this.acceptBillDetail = this.acceptBillDetailManager.loadAcceptBillDetail(this.getId("acceptBillDetail.id"));
		  }
	   else {
			  this.acceptBillDetail = new AcceptBillDetail();
			}
     }
	}



public String save() {
	boolean isNew = this.acceptBillDetail.isNew();
	acceptBillDetail.setAcceptBill(acceptBill);
	acceptBillDetailManager.storeAcceptBillDetail(acceptBillDetail);
 
	if (isNew) {
            addActionMessage(getText("acceptBillDetail.add.success",
                    Arrays.asList(new Object[]{acceptBillDetail.getAcceptProject()})));
            return NEW;
        } else {
                addActionMessage(getText("acceptBillDetail.edit.success",
                        Arrays.asList(new Object[]{acceptBillDetail.getAcceptProject()})));
            return SUCCESS;
        }
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
	
