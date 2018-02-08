 
package com.yongjun.tdms.presentation.webwork.action.asset.spare.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryDetailManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;
import com.yongjun.tdms.service.asset.spare.spareLocation.SpareLocationManager;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * @author Administrator
 *
 */
public class EditOldSpareInventyBillDetailAction extends PrepareAction{
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpareInventory spareInventoryBill;
	private SpareInventoryDetail spareInventoryDetail;
	private String toolingDevFlag;
	 
	/**
	 * 是否是从盘点明细列表传值
	 */
	private boolean fromListDtl;
	
	private final SpareInventoryDetailManager spareInventoryDetailManager;
	private final SpareInventoryManager spareInventoryManager;
	private final CodeValueManager codeValueManager;
	private final SpareTypeManager spareTypeManager;
	private final UserManager userManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	private final WarehouseManager warehouseManager; 
	private final RegionalManager regionalManager;
	private final LocationManager locationManager;
	private final SpareLocationManager spareLocationManager;
	private final SpareManager spareManager;
	private final SupplierManager supplierManager;
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
    newwarehouseManager;
	
	public  EditOldSpareInventyBillDetailAction(SpareInventoryDetailManager spareInventoryDetailManager,
			SpareInventoryManager spareInventoryManager,
			CodeValueManager codeValueManager,
			 SpareTypeManager spareTypeManager,
			 UserManager userManager,
			 SpareDetailTypeManager spareDetailTypeManager,
			 WarehouseManager warehouseManager,
			 RegionalManager regionalManager,
			 LocationManager locationManager,
			 SpareLocationManager spareLocationManager,
			 SpareManager spareManager,
			 SupplierManager supplierManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
			    newwarehouseManager){
		this.spareInventoryDetailManager = spareInventoryDetailManager;
		this.spareInventoryManager = spareInventoryManager;
		this.codeValueManager = codeValueManager;
		this.spareTypeManager = spareTypeManager;
		this.userManager = userManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.warehouseManager = warehouseManager;
		this.regionalManager = regionalManager;
		this.locationManager = locationManager;
		this.spareLocationManager = spareLocationManager;
		this.spareManager = spareManager;
		this.supplierManager =supplierManager;
		this.newwarehouseManager = newwarehouseManager;
		
	}
 
	public void prepare() throws Exception {
		fromListDtl = Boolean.parseBoolean(this.request.getParameter("fromListDtl"));
		if( this.hasIds("spareInventoryBill.id")){
			spareInventoryBill = this.spareInventoryManager.loadSpareInventory(this.getId("spareInventoryBill.id"));
		}else{
			spareInventoryBill = new SpareInventory();
		}
		if( this.hasIds("spareInventoryDetail.id")){
			spareInventoryDetail = this.spareInventoryDetailManager.loadSpareInventoryDetail(this.getId("spareInventoryDetail.id"));
		}
		if(null == spareInventoryDetail){
			spareInventoryDetail = new SpareInventoryDetail();
			spareInventoryDetail.setInventory(spareInventoryBill);
			spareInventoryDetail.setStorageGrade(spareInventoryBill.getStorageGrade());			//仓库级别
			spareInventoryDetail.setWarehouse(spareInventoryBill.getWarehouse());				//仓库名称
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	/**
	 * 保存盘点单明细
	 * @return
	 * @throws DaoException 
	 */
	public String save() throws DaoException{
		boolean isNew = this.spareInventoryDetail.isNew();
		CodeValue unit = null;
		SpareLocation spareLocation = null;
		Spare spare = null;
	
		//加载 种类
		if(!StringUtils.isEmpty(this.request.getParameter("category.id"))){
			spareInventoryDetail.setCategory(this.spareTypeManager.
					loadSpareType(Long.parseLong(this.request.getParameter("category.id"))));
		}
		//加载 明细
		if(!StringUtils.isEmpty(this.request.getParameter("detailCategory.id"))){
			spareInventoryDetail.setSpareDetailType(this.spareDetailTypeManager.
					loadSpareDetailType(Long.parseLong(this.request.getParameter("detailCategory.id"))));
		}
		//加载 单位
		if(!StringUtils.isEmpty(this.request.getParameter("calUnit.id"))){
			
			unit = this.codeValueManager.
			loadCodeValue(Long.parseLong(this.request.getParameter("calUnit.id")));
			spareInventoryDetail.setUnit(unit);
		}
		//加载 仓库级别
		if(!StringUtils.isEmpty(this.request.getParameter("storageGrade.id"))){
			spareInventoryDetail.setStorageGrade(this.codeValueManager.
					loadCodeValue(Long.parseLong(this.request.getParameter("storageGrade.id"))));
		}
		//加载 仓库
		if(!StringUtils.isEmpty(this.request.getParameter("warehouse.id"))){
			spareInventoryDetail.setWarehouse(this.warehouseManager.
					loadWarehouse(Long.parseLong(this.request.getParameter("warehouse.id"))));
		}
		//加载 库位
		if(!StringUtils.isEmpty(this.request.getParameter("regional.id"))){
			spareInventoryDetail.setRegional(this.regionalManager.
					loadRegionals(Long.parseLong(this.request.getParameter("regional.id"))));
		}
		//加载 库区
		if(!StringUtils.isEmpty(this.request.getParameter("location.id"))){
			Location location = this.locationManager.
			loadLocation(Long.parseLong(this.request.getParameter("location.id")));
			spareInventoryDetail.setLocation(location);
			spareInventoryDetail.setLocationCode(location.getCode());
		}
		
		//加载 备件库台账
		if(!StringUtils.isEmpty(this.request.getParameter("spareLocation.id"))){
			spareLocation = this.spareLocationManager.
			loadSpareLocation(Long.parseLong(this.request.getParameter("spareLocation.id")));
			spare = spareLocation.getSpare();
			spareInventoryDetail.setSpareLocation(spareLocation);
			spareInventoryDetail.setSpare(spare);
			spareLocation.getSpare().setUnit(unit);
			spareInventoryDetail.setCurrentSysStocks(spareLocation.getStocks());
			//添加 盘点前数量 = 备件库台账的库存
			//spareInventoryDetail.setActualNumber(spareLocation.getStocks());
			// 盘点前金额= 盘点前数量*单价
			
		}else{
			 
			spare = this.haveOldSpare(spareInventoryDetail);
			if(null == spare){
				spare = this.storeSpare(spareInventoryDetail);
			} 
			spareLocation = this.storeSpareLocation(spare, spareInventoryDetail);
			spareInventoryDetail.setSpareLocation(spareLocation);
			spareInventoryDetail.setSpare(spare);
			spareInventoryDetail.setCode(spare.getSpareNo());
			if(null != spareLocation.getStocks()){
				spareInventoryDetail.setCurrentSysStocks(spareLocation.getStocks());
				spareInventoryDetail.setActualNumber(spareLocation.getStocks());
				if(null != spareLocation.getUnitPrice()){
					spareInventoryDetail.setActualTotalPrice(spareLocation.getStocks()*spareLocation.getUnitPrice());
				}
			}else{
				spareInventoryDetail.setCurrentSysStocks(0L);
				spareInventoryDetail.setActualNumber(0L);
				spareInventoryDetail.setActualTotalPrice(0.00);
			}
			
		}

		
		//DecimalFormat df = new DecimalFormat("###,###.00"); 
		if(null != spareInventoryDetail.getInventoryNum() && null != spareInventoryDetail.getUnitPrice()){
//			盘点金额=盘点数量*单价
			spareInventoryDetail.setInventoryTotalPrice(spareInventoryDetail.getInventoryNum()*spareInventoryDetail.getUnitPrice());
			//差异 = 盘点数量-盘点前数量
			spareInventoryDetail.setDifferent(spareInventoryDetail.getInventoryNum()-
					((null == spareInventoryDetail.getActualNumber())?0:spareInventoryDetail.getActualNumber()));
			//差额 = 盘点金额-盘点前金额
			spareInventoryDetail.setMarginPrice(spareInventoryDetail.getInventoryTotalPrice()-spareInventoryDetail.getActualTotalPrice());
			
		} 
	 
		String strName = spareInventoryDetail.getName();
		strName =  strName.replaceAll("\"", "&quot;");
		spareInventoryDetail.setName(strName);
		
        String model =  spareInventoryDetail.getModel();
	    model = model.replaceAll("\"", "&quot;");
	    model = model.replaceAll("\'", "&#39;");
	    spareInventoryDetail.setModel(model);
	    
		this.spareInventoryDetailManager.storeSpareInventoryDetail(spareInventoryDetail);
		this.updateFatherTotalPrice(spareInventoryBill);
		if(isNew){
			this.addActionMessage(getText("spareInventoryDetail.add.success",
                    Arrays.asList(new Object[]{spareInventoryDetail.getName()})));
            return NEW;
		}else{
			 addActionMessage(getText("spareInventoryDetail.edit.success",
                     Arrays.asList(new Object[]{spareInventoryDetail.getName()})));
			 
		}
		return SUCCESS;
	}
	/**
	 * 查找是否有相同的备件
	 * @param spareInventoryDetail
	 * @return
	 */
	public Spare haveOldSpare(SpareInventoryDetail spareInventoryDetail){
		
		String strFactoryId = null;
		Spare spare = null;
		if(null != spareInventoryDetail.getFactoryName() && !"".equals(spareInventoryDetail.getFactoryName())){
			List<Supplier> slist = this.supplierManager.loadSuppliersByName(spareInventoryDetail.getFactoryName());
			if(null != slist && slist.size()>0){
				strFactoryId = slist.get(0).getId().toString();
			}
		}
		
		
		String [] value={spareInventoryDetail.getName(),spareInventoryDetail.getModel(),spareInventoryDetail.getOrderNo(),
				strFactoryId,spareInventoryDetail.getCategory().getId().toString(), 
				spareInventoryDetail.getSpareDetailType().getId().toString()};
		
//		String[] keyName= {name,model,orderNo,strFactoryId,category,detailCategory};
		
		try {
			List<Spare> list = this.spareManager.loadByKeyArry(value);
			if(null !=list && list.size()>0){
				spare = list.get(0);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return spare;
	}
	/**
	 * 新建备件
	 * @param spare
	 * @param spareInventoryDetail
	 */
	public Spare storeSpare(SpareInventoryDetail spareInventoryDetail){
		Spare spare = new Spare();
		spare.setName(this.spareInventoryDetail.getName());
		spare.setCategory(spareInventoryDetail.getCategory());
		spare.setSpareDetailType(spareInventoryDetail.getSpareDetailType());
		spare.setSpareNo(spareInventoryDetail.getCode());
		spare.setFactoryStr(spareInventoryDetail.getFactoryName());
		spare.setModelSpecs(spareInventoryDetail.getModel());
		spare.setOrderNo(spareInventoryDetail.getOrderNo());
		spare.setUnit(spareInventoryDetail.getUnit());
		spare.setUnitPrice(spareInventoryDetail.getUnitPrice());
		spare.setToolingDevFlag(SysModel.DEVICE);
		spare.setStocks(spareInventoryDetail.getInventoryNum());
		this.spareManager.storeSpare(spare);
		return spare;
	}
	/**
	 * 新建备件库台账
	 * @param spareLocation
	 * @param spare
	 * @param spareInventoryDetail
	 */
	public SpareLocation storeSpareLocation(Spare spare,SpareInventoryDetail spareInventoryDetail){
		SpareLocation spareLocation = new SpareLocation();
		spareLocation.setSpare(spare);
		
		spareLocation.setWarehouse(spareInventoryDetail.getWarehouse());
		spareLocation.setRegional(spareInventoryDetail.getRegional());
		spareLocation.setLocation(spareInventoryDetail.getLocation());
		//spareLocation.setStocks(spareInventoryDetail.getInventoryNum());
		spareLocation.setUnitPrice(spareInventoryDetail.getUnitPrice());
		this.spareLocationManager.storeSpareLocation(spareLocation);
		return spareLocation;
	}
	
	/**
	 * 更新单头的盘点总金额
	 * @param spareInventoryBill
	 */
	public void updateFatherTotalPrice(SpareInventory spareInventoryBill){
		Double totalPrice = 0.0;
		Set<SpareInventoryDetail> set = spareInventoryBill.getInventoryDetails();
		for(SpareInventoryDetail detail : set){
			totalPrice = totalPrice + detail.getInventoryTotalPrice();
		}
		spareInventoryBill.setTotalPrice(totalPrice);
		this.spareInventoryManager.storeSpareInventory(spareInventoryBill);
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
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		User user = this.userManager.getUser();
		List<Warehouse> allWa = null;
		try {
			allWa = newwarehouseManager.loadByUser(user.getId());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Warehouse  ws :allWa){
			if(ws.getOldWarehouse()!=null&&ws.getOldWarehouse().equals("Y")){
				wareList.add(ws);
			}
			
		}
		return wareList;
	}
	/**
	 * 库区
	 * @return
	 */
	public List<Regional> getAllRegionals(){
		List<Regional> list = new ArrayList<Regional>();
		Regional regional = new Regional();
		regional.setId(-1L);
		regional.setName("");
		list.add(regional);
		return list;
	}
	/**
	 * 库位
	 * @return
	 */
	public List<Location> getAllLocations(){
		List<Location> list = new ArrayList<Location>();
		Location location = new Location();
		location.setId(-1L);
		location.setCode("");
		list.add(location);
		return list;
	}
	
	/**
	 * 获取种类
	 * @return List 种类
	 */
	public List getAllCategory() {
		return this.spareTypeManager.loadAllSpareTypeByToolingDevFlag(toolingDevFlag);
	}
	
	/**
	 * 获取明细种类
	 * @return List 明细种类
	 */
	public List getAllDetailCategory(){
		List list = new ArrayList();
		return list;
	}
	
	/**
	 * 获取工装计量单位集合
	 * @return List 工装计量单位
	 */
	public List getCalUnits() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}
	
    public User getLoginUser(){
    	return this.userManager.getUser();
    }
	public SpareInventory getSpareInventoryBill() {
		return spareInventoryBill;
	}

	public void setSpareInventoryBill(SpareInventory spareInventoryBill) {
		this.spareInventoryBill = spareInventoryBill;
	}

	public SpareInventoryDetail getSpareInventoryDetail() {
		return spareInventoryDetail;
	}

	public void setSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail) {
		this.spareInventoryDetail = spareInventoryDetail;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public boolean isFromListDtl() {
		return fromListDtl;
	}

	public void setFromListDtl(boolean fromListDtl) {
		this.fromListDtl = fromListDtl;
	}

 
	
	
}
