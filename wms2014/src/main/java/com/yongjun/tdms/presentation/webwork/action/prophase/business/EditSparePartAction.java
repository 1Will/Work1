package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.SparePart;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
import com.yongjun.tdms.service.prophase.business.SparePartManager;

public class EditSparePartAction extends PrepareAction{

	
	private static final long serialVersionUID = 1L;
	private final AcceptBillManager acceptBillManager;
	private final SparePartManager sparePartManager;
	private AcceptBill acceptBill;  // 验收单对象
	private SparePart sparePart;
	
    public EditSparePartAction(AcceptBillManager acceptBillManager,SparePartManager sparePartManager) {
    	this.acceptBillManager=acceptBillManager;
    	this.sparePartManager=sparePartManager;
    }
    
	public void prepare() throws Exception {
		if (this.hasId("acceptBill.id")) {//通过页面传来的验收单的id 获得验收单的对象
			this.acceptBill = this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
	
	if (null == sparePart) {//通过页面穿来的的验收单明细的id 获得验收单明细对象
		if (this.hasId("sparePart.id")) {
			this.sparePart = this.sparePartManager.loadSparePart(this.getId("sparePart.id"));
		  }
	   else {
			  this.sparePart = new SparePart();
			}
     }
  }
	public String save() {
		boolean isNew = this.sparePart.isNew();
		if (!StringUtils.isEmpty(request.getParameter("sparePart.damage"))) {//获取备件易损值 
			if (request.getParameter("sparePart.damage").equals("N")){
				this.sparePart.setDamage(false);
			}else {
				this.sparePart.setDamage(true);
			}
		}
		sparePart.setAccept(acceptBill);
		sparePartManager.storeSparePart(sparePart);
	 
		if (isNew) {
	            addActionMessage(getText("sparePart.add.success",
	                    Arrays.asList(new Object[]{sparePart.getSpareName()})));
	            return NEW;
	        } else {
	                addActionMessage(getText("sparePart.edit.success",
	                        Arrays.asList(new Object[]{sparePart.getSpareName()})));
	            return SUCCESS;
	        }
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}
	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}
	public SparePart getSparePart() {
		return sparePart;
	}
	public void setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

}
