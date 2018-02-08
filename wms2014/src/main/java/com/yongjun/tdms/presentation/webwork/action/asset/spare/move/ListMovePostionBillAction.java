/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;
import com.yongjun.tdms.model.asset.spare.move.MovePositionStatus;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.spare.move.MovePositionBillManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author yli-JohnSon
 * @Date 2009-4-22
 */
public class ListMovePostionBillAction extends ValueListAction {

	/*
	 * YONGJUN-TEACHNOLOGY
	 */
	private static final long serialVersionUID = 1L;
	private List<MovePositionBill> movePositionBills;
	//	登陆用户所属权限仓库ID值集合
	private List<Long> warehouseIds = new ArrayList<Long>();
	private final MovePositionBillManager movePositionBillManager;
    private final WarehouseManager warehouseManager;
    private final CodeValueManager codeValueManager;
    private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager;
    private final UserManager userManager;
	public ListMovePostionBillAction(MovePositionBillManager movePositionBillManager,
			WarehouseManager warehouseManager,CodeValueManager codeValueManager,
			UserManager userManager,com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager){
		this.movePositionBillManager = movePositionBillManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager = codeValueManager;
		this.userManager = userManager;
		this.newwarehouseManager=newwarehouseManager;
	}
	/**
	 * 为valuelist提供仓库查询条件
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (null != warehouseIds) {
			map.put("warehouseIds", warehouseIds);
		}
		return map;
	}
	/* (non-Javadoc)
	 * @see com.yongjun.pluto.webwork.action.valuelist.ValueListAction#getAdapterName()
	 */
	@Override
	protected String getAdapterName() {
		// TODO Auto-generated method stub
		return "movePostionBills";
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if(null ==movePositionBills && this.hasIds("movePostionBillIds") ){
			this.movePositionBills = movePositionBillManager.loadAllMovePositionBill(this.getIds("movePostionBillIds"));
		}
		//	获取登陆用户所属权限仓库ID值集合
		User user = this.userManager.loadUser(this.userManager.getUser().getId());
		List<Warehouse> whrlist = newwarehouseManager.loadByUserAndNew(user.getId());
		if(null != whrlist){
			for(Warehouse warehouse:whrlist){
				warehouseIds.add(warehouse.getId());
			}
		}
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(this.isDisabled()){
			return disabled();
		}
		if(this.isEnable()){
			return enabled();
		}
		return SUCCESS;
	}
	/**
	 * 有效
	 * @return
	 */
	private String enabled() {
		// TODO Auto-generated method stub
		try {
			this.movePositionBillManager.enableMovePostionBill(movePositionBills);
			this.addActionMessage(this.getText("movePositionBill.enabled.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return SUCCESS;
		}
	}
	/**
	 * 失效
	 * @return
	 */
	private String disabled() {
		// TODO Auto-generated method stub
		try {
			this.movePositionBillManager.disableMovePostionBill(movePositionBills);
			this.addActionMessage(this.getText("movePositionBill.disasbled.success"));
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.addActionError(this.getText("movePositionBill.disasbled.farile"));
		}
		return SUCCESS;
	}
	/**
	 * 加载移位单的所有状态
	 * @return
	 */
	public List<LabelValue> getMoveStatus(){
		LabelValue[] arrays = this.wrapEnum(MovePositionStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	/**
	 * 获得仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouses(){
		List<Warehouse> list = new ArrayList<Warehouse>();
		Warehouse warehouse = new Warehouse();
		warehouse.setId(-1L);
		warehouse.setName("所有");
		list.add(warehouse);
		return list;
	}
	
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade(){
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}
	
	public User getLoginUser(){
		return this.userManager.getUser();
	}
}
