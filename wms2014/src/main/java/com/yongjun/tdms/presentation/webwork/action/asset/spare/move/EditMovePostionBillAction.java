/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;
import com.yongjun.tdms.model.asset.spare.move.MovePositionStatus;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.spare.move.MovePositionBillManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author yli-JohnSon
 * @Date 2009-4-22
 */
public class EditMovePostionBillAction extends PrepareAction {

	/*
	 * YONGJUN-TEACHNOLOGY
	 */
	private static final long serialVersionUID = 1L;
	
	private MovePositionBill movePostionBill;
	private User user;
	
	private final UserManager userManager;
	private final MovePositionBillManager movePositionBillManager;
    private final WarehouseManager warehouseManager;
    private final CodeValueManager codeValueManager;
    
	public EditMovePostionBillAction(UserManager userManager,
			MovePositionBillManager movePositionBillManager,
			WarehouseManager warehouseManager,
			CodeValueManager codeValueManager
			){
		this.userManager = userManager;
		this.movePositionBillManager = movePositionBillManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager = codeValueManager;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if(null == movePostionBill){
			if(this.hasId("movePostionBill.id")){
				movePostionBill = movePositionBillManager.loadMovePositionBill(this.getId("movePostionBill.id"));
			}else{
				movePostionBill = new MovePositionBill();
				movePostionBill.setUser(userManager.getUser());
				movePostionBill.setMoveStatus(MovePositionStatus.unMovePosition);
			}
		}

	}
	
	public String save(){
		//获得对应的入库人
		if(!StringUtils.isEmpty(request.getParameter("movePostionBill.movePeople.id"))){
			this.user = userManager.loadUser(this.getId("movePostionBill.movePeople.id"));
			movePostionBill.setUser(user);
		}
        if(!StringUtils.isEmpty(request.getParameter("status"))){	
			if (request.getParameter("status").equals("unMovePosition")) {
				movePostionBill.setMoveStatus(MovePositionStatus.unMovePosition);
			}
			if(request.getParameter("status").equals("movePositioning")){
				movePostionBill.setMoveStatus(MovePositionStatus.movePositioning);
			}
			if(request.getParameter("status").equals("movePositioned")){
				movePostionBill.setMoveStatus(MovePositionStatus.movePositioned);
			}
		}
		String sId = this.request.getParameter("storageGrade.id");
		String wId = this.request.getParameter("warehouse.id");
		if(!StringUtils.isEmpty(sId)){
			movePostionBill.setStorageGrade(this.codeValueManager.loadCodeValue(Long.parseLong(sId)));
		}
		if(!StringUtils.isEmpty(wId)){
			movePostionBill.setWarehouse(this.warehouseManager.loadWarehouse(Long.parseLong(wId)));
		}
        
        boolean isNew = this.movePostionBill.isNew();
        this.movePositionBillManager.storeMovePositionBill(movePostionBill);
		if (isNew) {
			this.addActionMessage(this.getText("movePostionBill.add.success",
					Arrays.asList(new Object[] { movePostionBill.getBillNo()})));
			return NEW;
		} else {
			this.addActionMessage(this.getText("movePostionBill.edit.success",
					Arrays.asList(new Object[] { movePostionBill.getBillNo()})));
			return SUCCESS;
		}
	}
	
	public MovePositionBill getMovePostionBill() {
		return movePostionBill;
	}
	public void setMovePostionBill(MovePositionBill movePostionBill) {
		this.movePostionBill = movePostionBill;
	}
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade(){
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}
	
	/**
	 * 获得仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouses(){
		List<Warehouse> list = new ArrayList<Warehouse>();
		Warehouse warehouse = new Warehouse();
		warehouse.setId(-1L);
		warehouse.setName("");
		list.add(warehouse);
		return list;
	}
	/**
	 * @function 获得所有仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
		
		List<Warehouse> wareList = new ArrayList<Warehouse>();
 
		List<Warehouse> list = new ArrayList<Warehouse>();
		Long userId=this.userManager.getUser().getId();
		User user=userManager.loadUser(userId);
		list.addAll(user.getWarehouses());
		for(Warehouse warehouse:list){
			if(!(warehouse.getDisabled())){
				wareList.add(warehouse);
			}
		}

		return wareList;
	}

	public User getLoginUser(){
		return this.userManager.getUser();
	}
}
