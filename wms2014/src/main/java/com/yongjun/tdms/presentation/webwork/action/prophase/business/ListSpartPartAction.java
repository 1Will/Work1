package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.SparePart;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
import com.yongjun.tdms.service.prophase.business.SparePartManager;

public class ListSpartPartAction extends ValueListAction{

	private static final long serialVersionUID = 1L;
	private final SparePartManager sparePartManager;
	private final AcceptBillManager acceptBillManager;
	private SparePart sparePart;           //验收备品备件的对象
	 private AcceptBill acceptBill;        //验收单的对象
	 private List<SparePart> spareParts;    //备品备件集合
	public ListSpartPartAction(SparePartManager sparePartManager,AcceptBillManager acceptBillManage){
		 this.sparePartManager=sparePartManager;
		 this.acceptBillManager=acceptBillManage;
	}
	public void prepare() {
		if (null == this.acceptBill) {//获得验收单对象,目的是说明当前备品备件对象是属于哪个验收单的
			if (this.hasId("acceptBill.id")) {
				this.acceptBill = this.acceptBillManager.loadAcceptBill(getId("acceptBill.id"));
				
			} 
		}
		if (this.spareParts == null && this.hasIds("sparePartIds")) {//根据页面传来的值,获得备品备件对象
            this.spareParts = this.sparePartManager.loadSpareParts(this.getIds("sparePartIds"));
        } 	
	
		this.setFirst(false);
	}
    public String execute() {
		if(this.hasKey("delete")){
			this.sparePartManager.deleteAllSparePart(spareParts);
			this.addActionMessage(this.getText("PurchaseBillDetails.delete.success"));
		}
		
		return SUCCESS;
	}
    /**
     * 往查询采购单付款明细的hql语句中设置备品备件的ID值
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
		
		return "spareParts";
	}
	public SparePart getSparePart() {
		return sparePart;
	}
	public void setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}
	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}
	public List<SparePart> getSpareParts() {
		return spareParts;
	}
	public void setSpareParts(List<SparePart> spareParts) {
		this.spareParts = spareParts;
	}

}
