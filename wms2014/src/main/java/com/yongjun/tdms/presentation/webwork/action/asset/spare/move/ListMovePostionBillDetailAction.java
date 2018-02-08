/**
 * 
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.move;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBillDetail;
import com.yongjun.tdms.service.asset.spare.move.MovePositionBillDetailManager;
import com.yongjun.tdms.service.asset.spare.move.MovePositionBillManager;
/**
 * 
 * @author yli-JohnSon
 * @Date 2009-4-23
 */
public class ListMovePostionBillDetailAction extends ValueListAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2131621733443790636L;
	private String addSpareLocationAccountIds;
	private String allMovePostionBillDtlIds;
	private String allLocationCodeValue;
	private String allMovePostionDetailNumber;
	private Long warehouseId;
	private List<MovePositionBillDetail> movePostionBillDetails;
	private MovePositionBill movePostionBill;
	private final MovePositionBillManager movePositionBillManager;
	private final MovePositionBillDetailManager movePositionBillDetailManager;
	
	public ListMovePostionBillDetailAction(MovePositionBillManager movePositionBillManager,
										   MovePositionBillDetailManager movePositionBillDetailManager){
		this.movePositionBillManager = movePositionBillManager;
		this.movePositionBillDetailManager = movePositionBillDetailManager;
	}
	
	@Override
	public void prepare() throws Exception {
		if(this.hasId("movePostionBill.id")){
			movePostionBill = this.movePositionBillManager.loadMovePositionBill(this.getId("movePostionBill.id"));
			this.warehouseId = this.movePostionBill.getWarehouse().getId();
		}
		if(movePostionBillDetails==null&& this.hasIds("movePostionBillDtlIds")){
			this.movePostionBillDetails = movePositionBillDetailManager.loadAllMovePositionBillDtl(this.getIds("movePostionBillDtlIds"));
		}
		if(this.addSpareLocationAccountIds==null){
			if(!StringUtils.isEmpty(request.getParameter("addSpareLocationDetailIds"))){
				this.addSpareLocationAccountIds = request.getParameter("addSpareLocationDetailIds");
			}
		}
		this.setFirst(false);
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(this.isAddSpareLocationAcount()){
			return saveAddSpareLocationToMovePostionDtl();
		}
		if(this.isSaveDetail()){
			return saveMovePostionDetail();
		}
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}
	
	private String delete() {
		// TODO Auto-generated method stub
		try {
			this.movePositionBillDetailManager.deleteAllMovePositionBillDtl(movePostionBillDetails,movePostionBill);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
	}

	private String saveMovePostionDetail() {
		// TODO Auto-generated method stub
		if(!StringUtils.isEmpty(request.getParameter("allMovePostionBillDtlIds"))){
			this.allMovePostionBillDtlIds = request.getParameter("allMovePostionBillDtlIds");
		}
		if(!StringUtils.isEmpty(request.getParameter("allLocationCodeValue"))){
			this.allLocationCodeValue = request.getParameter("allLocationCodeValue");
		}
		if(!StringUtils.isEmpty(request.getParameter("allMovePostionDetailNumber"))){
			this.allMovePostionDetailNumber = request.getParameter("allMovePostionDetailNumber");
		}
		if(this.allMovePostionBillDtlIds!=null||this.allLocationCodeValue!=null||this.allMovePostionDetailNumber!=null){
			movePositionBillDetailManager.storeMovePositionBillDtl(allMovePostionBillDtlIds,allLocationCodeValue,allMovePostionDetailNumber,this.warehouseId);
		}
		return SUCCESS;
	}

	private String saveAddSpareLocationToMovePostionDtl(){
		this.movePositionBillDetailManager.storeSpareLocationToMovePostionDtl(movePostionBill,addSpareLocationAccountIds);
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see com.yongjun.pluto.webwork.action.valuelist.ValueListAction#getAdapterName()
	 */
	@Override
	protected String getAdapterName() {
		// TODO Auto-generated method stub
		return "movePostionDtls";
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("movePostionBill.id",this.getId("movePostionBill.id"));
		return map;
	}
	
	/**
	 * 判断页面是不是备件台帐选择而来
	 * @return true | false
	 */
	private boolean isAddSpareLocationAcount(){
		if (!StringUtils.isEmpty(request.getParameter("spareLocationAccountSelector"))) {
			if (request.getParameter("spareLocationAccountSelector").equals("spareLocationAccount"))
				return true;
		}
		return false;
	}
	/**
	 * 判断页面是否保存入库单明细
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
	public MovePositionBill getMovePostionBill() {
		return movePostionBill;
	}
	public void setMovePostionBill(MovePositionBill movePostionBill) {
		this.movePostionBill = movePostionBill;
	}

}
