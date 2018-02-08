package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;

import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

public class EditPurchaseBillDetailAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
    private final PurchaseBillManager purchaseBillManager;
    private final PurchaseBillDetailManager purchaseBillDetailManager;
    private final CodeValueManager codeValueManager;
    private PurchaseBillDetail purchaseBillDtl;      //采购单明细对象
    private PurchaseBill purchaseBill;//采购单明细所关联的采购单对象
    private String toolingDevFlag;    //标示工装或设备
   public  EditPurchaseBillDetailAction( PurchaseBillManager purchaseBillManager,
		   PurchaseBillDetailManager purchaseBillDetailManager,CodeValueManager codeValueManager){
	   this.purchaseBillManager=purchaseBillManager;
	   this.purchaseBillDetailManager=purchaseBillDetailManager;
	   this.codeValueManager=codeValueManager;
   }
	public void prepare() throws Exception {
		if (null == this.purchaseBill) {//从页面传来的采购单ID获得采购单对象
			if (this.hasId("purchaseBill.id")) {
				this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(this.getId("purchaseBill.id"));
			}
		}
		if (null == purchaseBillDtl) {
			if (this.hasId("purchaseBillDtl.id")) {//获得采购单明细对象
				this.purchaseBillDtl = this.purchaseBillDetailManager.loadPurchaseBillDetail(this.getId("purchaseBillDtl.id"));
			  }
		   else {
				  this.purchaseBillDtl = new PurchaseBillDetail();
				}
	     }
		 if(this.hasId("toolingDevFlag")){
	    	  this.toolingDevFlag = request.getParameter("toolingDevFlag");
	      }
		
	}
	
	
	public String save() {
		boolean isNew = this.purchaseBillDtl.isNew();
		purchaseBillDtl.setPurchaseBill(purchaseBill);//保存采购单对象到采购单明细中
         //设置计量单位
		if(this.hasId("calUnit.id")){
			purchaseBillDtl.setCalUnit(this.codeValueManager.loadCodeValue(this.getId("calUnit.id")));
		}
		purchaseBillDetailManager.storePurchaseBillDetail(purchaseBillDtl);
        this.purchaseBillDetailManager.updateNnmber(purchaseBill);

		
		if (isNew) {
			   if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
				   addActionMessage(getText("purchaseBillDetail.add.success",
		                    Arrays.asList(new Object[]{purchaseBillDtl.getName()})));
		            return NEW; 
			   }else{
				   addActionMessage(getText("purchaseBillConDetail.add.success",
		                    Arrays.asList(new Object[]{purchaseBillDtl.getName()})));
		            return NEW; 
			   }
	          
	    } else {
	        	  if(toolingDevFlag.equals(SysModel.DEVICE.toString())) {
	        		  addActionMessage(getText("purchaseBillDetail.edit.success",
		                        Arrays.asList(new Object[]{purchaseBillDtl.getName()})));
		            return SUCCESS;
	        	  }else{
	        		  addActionMessage(getText("purchaseBillConDetail.edit.success",
		                        Arrays.asList(new Object[]{purchaseBillDtl.getName()})));
		            return SUCCESS;
	        	  }
	    }
	}
	
	/**
	 * 更新 采购单的 采购项、入库项
	 * @param purchaseBill
	 */
	public void updateNnmber(PurchaseBill purchaseBill){
		try {
			
			String[] keys= {"status","purchaseBill"};
			Object[] values = {"INSPECTED",purchaseBill.getId()};
			List<PurchaseBillDetail> list = this.purchaseBillDetailManager.loadByKeyArry(keys, values);
			purchaseBill.setInsNum(list.size());
			purchaseBill.setSumDetail(purchaseBill.getPurchaseBillDetails().size());
			this.purchaseBillManager.storePurchaseBill(purchaseBill);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	/**
	 * 获取工装计量单位集合
	 * @return List 工装计量单位
	 */
	public List getCalUnits() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}
	
	
	
	
	public PurchaseBillDetail getPurchaseBillDtl() {
		return purchaseBillDtl;
	}
	public void setPurchaseBillDtl(PurchaseBillDetail purchaseBillDtl) {
		this.purchaseBillDtl = purchaseBillDtl;
	}
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
}
