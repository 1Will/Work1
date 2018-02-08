package com.yongjun.tdms.presentation.webwork.action.runmaintenance.discard;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBillDtl;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardBillDtlManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardBillManager;

public class ListDiscardBillDetailAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final DiscardBillDtlManager discardBillDtlManager;
	private final DiscardBillManager discardBillManager;
	private String allmemoInfo;                                 //所有报废设备的备注信息 
	private String allDiscardDeviceId;                          //所有报废设备的ids    
	private String newDeviceIds;                                //用逗号,分割新建设备的id，
    private DiscardBillDtl  discardBillDtl;     //报废单明细的对象
    private List<DiscardBillDtl>  discardBillDtls;//报废单明细集合
    private DiscardBill discardBill;    //报废单明细所关联的报废单
    public ListDiscardBillDetailAction(DiscardBillDtlManager discardBillDtlManager,DiscardBillManager discardBillManager){
    	this.discardBillDtlManager=discardBillDtlManager;
    	this.discardBillManager=discardBillManager;
    }   
public void prepare() throws Exception {
		if(this.discardBill==null){
			if(this.hasId("discardBill.id")){  //获得报废单的对象
				this.discardBill=discardBillManager.loadDiscardBill(this.getId("discardBill.id"));
			}
		}
		if (this.discardBillDtls == null && this.hasIds("discardBillDtlIds")) {  //给据页面传来的Ids获得报废单明细集合
			this.discardBillDtls = this.discardBillDtlManager.loadAllDiscardBillDtls(this
					.getIds("discardBillDtlIds"));
			
		}
		if (null == this.newDeviceIds) {  //判断是否添加设备
			if (!StringUtils.isEmpty(request.getParameter("addDeviceIds"))) {
				this.newDeviceIds = request.getParameter("addDeviceIds");
			}
		}
		this.setFirst(false);
	}
    public String execute() {
	       if (this.isAddDiscardDevice()) {
		        return saveAddDeviceDiscardDetail();
	        }
	       if(this.isSave()){
				return save();
			}
	      if(this.isDelete()) {
	        return delete();
	       }
	      return SUCCESS;
      }
     public String delete() {
	        this.discardBillDtlManager.deleteAllDiscardBillDtl(discardBillDtls);
	        return SUCCESS;
     }
    public String saveAddDeviceDiscardDetail() {
	        this.discardBillDtlManager.storeDiscardDevDetail(discardBill, newDeviceIds);
	         return SUCCESS;
    }
     /**
     * 判断页面传来的addDevice变量是否有值，且值是否等于addDevices
     * 
      * @return boolean true 添加新的设备 | false 不添加
     */
     private boolean isAddDiscardDevice() {
        if (!StringUtils.isEmpty(request.getParameter("addDevice"))) {
	       if (request.getParameter("addDevice").equals("addDevices"))
		   return true;
      }
     return false;
    }
     /**
      * 判断是否点击保存按钮
     * @return true | false
     */
     public boolean isSave(){
	      return this.hasKey("save");
     }
     
     public String save(){
 		
 		if(!StringUtils.isEmpty(request.getParameter("allmemoInfo"))){
 			this.allmemoInfo=request.getParameter("allmemoInfo");
 		}
 		
 		if(!StringUtils.isEmpty(request.getParameter("allDiscardDeviceId"))){
 			this.allDiscardDeviceId=request.getParameter("allDiscardDeviceId");
 		}
 		
 		this.discardBillDtlManager.storeDiscardDevBillDtl(allDiscardDeviceId,allmemoInfo);
 		return SUCCESS;
 	}
	@Override
	protected String getAdapterName() {
		
		return "discardBillDtl";
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("discardBill.id")){
        	map.put("discardBill.id",this.getId("discardBill.id"));
		}
		return map;
	}
	
	public DiscardBillDtl getDiscardBillDtl() {
		return discardBillDtl;
	}
	public void setDiscardBillDtl(DiscardBillDtl discardBillDtl) {
		this.discardBillDtl = discardBillDtl;
	}
	public DiscardBill getDiscardBill() {
		return discardBill;
	}
	public void setDiscardBill(DiscardBill discardBill) {
		this.discardBill = discardBill;
	}

}
