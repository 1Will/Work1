package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.SpareDetailTypeManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

public class ListSubscribeDetailChooseAction extends ValueListAction {

	private static final long serialVersionUID = 1L;

	private final SubscribeManager subscribeManager;

	private final DepartmentManager departmentManager;

	private final SpareTypeManager spareTypeManager;// 备件类别接口

	private final SpareDetailTypeManager sdTypeManager;// 备件明细类别接口
	
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private final PurchaseBillManager purchaseBillManager;
	private final SubscribeDao subscribeDao;
	
	private final CodeValueManager codeValueManager;

	private List<SubscribeDetail> subscribeDetails;

	private SubscribeDetail subscribeDetail = new SubscribeDetail();

	private Department department = new Department();

	private String toolingDevFlag;
	
	private String buyUserId;
	
	private PurchaseBillDetail purchaseBillDtl;//采购单明细的对象
	private PurchaseBill purchaseBill;         //采购单对象
	private String addSubscribeIds;                       // 新添加到申购单的Ids  
	

	public ListSubscribeDetailChooseAction(SubscribeManager subscribeManager,
			DepartmentManager departmentManager,
			SpareTypeManager spareTypeManager,
			SpareDetailTypeManager sdTypeManager,
			SubscribeDao subscribeDao,
			CodeValueManager codeValueManager,PurchaseBillDetailManager purchaseBillDetailManager,
			PurchaseBillManager purchaseBillManager) {
		this.subscribeManager = subscribeManager;
		this.departmentManager = departmentManager;
		this.spareTypeManager = spareTypeManager;
		this.sdTypeManager = sdTypeManager;
		this.subscribeDao=subscribeDao;
		this.codeValueManager = codeValueManager;
		this.purchaseBillDetailManager = purchaseBillDetailManager;
		this.purchaseBillManager = purchaseBillManager;
	}

	public void prepare() throws DaoException {
		
		if (this.subscribeDetails == null && this.hasIds("subscribeDtlIds")) {
			//this.subscribeDetails = this.subscribeManager.loadAllSubscribeDetails(this.getIds("subscribeDtlIds"));
			this.subscribeDetails=this.subscribeDao.loadByKey("stocked", "0");
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
//		buyUserId = request.getParameter("buyUserId");
		buyUserId = this.getLoginUser().getId().toString();
		
		if (this.hasId("purchaseBill.id")) {     //获得采购单对象,目的是说明当前采购单明细对象是属于哪个采购单的
			this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
			
		} 
		if (null == this.addSubscribeIds) {//给据页面传来的申购单的ids,获得所有的申购单ids,
			if (!StringUtils.isEmpty(request.getParameter("addSubscribeIds"))) {
				this.addSubscribeIds = request.getParameter("addSubscribeIds");
			}
		}

	}
	public String execute() {
		if (this.isAddSubscribe()) {//判断是否添加申购单明细到采购单明细中来
			return this.saveAddSubscribe();
		}
		return SUCCESS;
	}
	/**
	 * 保存页面传来的所有申购单明细
	 */
	public String saveAddSubscribe() {
		this.purchaseBillDetailManager.storSubscribeDtl( purchaseBill,addSubscribeIds);
//		this.purchaseBillDetailManager.updateNnmber(purchaseBill);	废除不用	jyang 2011-5-18
		return SUCCESS;
	}
	/**
	 * 判断页面传来的addSubscribe变量是否有值，且值是否等于addSubscribes
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddSubscribe() {
		if (!StringUtils.isEmpty(request.getParameter("addSubscribe"))) {
			if (request.getParameter("addSubscribe").equals("addSubscribes"))
				return true;
		}
		return false;
	}
	/**
	 * 为valuelist 设置参数
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		//String status = this.request.getParameter("status");
//	    if(null != request.getParameter("buyUserId")){
//	    	  map.put("buyerId",request.getParameter("buyUserId"));
//	    }
	    
	    map.put("buyerId",this.getLoginUser().getId());
 
		return map;
	}
	
	 /**
	 * 获得单据类型
	 * @return
	 */
	
	public List getAllDetailKind() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.DETAIL_KIND);
		
	}
	
	
	public List getDepartments() {

		if (!this.userManager.getUser().isViewAll()) { // 如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) { // 如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager
						.getUser().getDepartment().getId()));
			}
			// 显示附属部门
			Set<Department> depts = userManager.getUser().getDepartments();
			if (depts != null) {
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));

	}

	/**
	 * 查询汇总单人 的单位
	 * 
	 * @returnR
	 */
	public List getCollDepartments() {

		List<Department> list = new ArrayList<Department>();
		list = this.departmentManager.loadAllDepartments();
		Department department = new Department();
		department.setId(Long.valueOf(-1L));
		department.setName(this.getText("select.option.all"));
		list.add(0, department);

		return list;

	}

	/**
	 * 获取备件大分类
	 */
	@SuppressWarnings("unchecked")
	public List getSpareType() {
		List allSpareTypeList = new ArrayList();
		// List spareTypeList = new ArrayList();

		allSpareTypeList = spareTypeManager.loadAllSpareType();

		// for (int i=0;i<allSpareTypeList.size();i++){
		// //区分是工装[TOOLING]或设备[DEVICE]
		// if
		// (((SpareType)allSpareTypeList.get(i)).getToolingDevFlag().toString().equals(this.toolingDevFlag)){
		// spareTypeList.add(allSpareTypeList.get(i));
		// }
		// //获得[工装]和[设备]的备件分类
		// if (request.getParameter("toolingDevFlag").equals("TOOLINGDEVICE")) {
		// spareTypeList.add(allSpareTypeList.get(i));
		// }
		// }
		SpareType type = new SpareType();
		type.setId(Long.valueOf(-1L));
		type.setName(this.getText("select.option.all"));
		allSpareTypeList.add(0, type);
		return allSpareTypeList;

	}
 


	 
	/**
	 * 备件明细分类类
	 * 
	 * @return
	 */
	public List getSpareDetailType() {
		List<SpareDetailType> list = new ArrayList<SpareDetailType>();
//		list = this.sdTypeManager.loadAllSpareDetailTypes();
		SpareDetailType type = new SpareDetailType();
		type.setId(-1l);
		type.setName(this.getText("select.option.all"));
		list.add(0, type);
		return list;
	}
 
 
	
    /**
     * 申购单明细状态
     * @return
     */
	public List<LabelValue> getCustomerTypes() { // 获得闲置状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(SubscribeDetailStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
	 
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			if("NEW".equals(arrays[i].getValue())){
				tmp.add(arrays[i]);
			}if("COLLECTED".equals(arrays[i].getValue())){
				tmp.add(arrays[i]);
			}
			
		}
		return tmp;
	}
	
	
	
	/**
	 * 获取备件大分类 用于 选择申购单明细 弹出页的列表
	 */
	@SuppressWarnings("unchecked")
	public List getAllSpareType() {
		List allSpareTypeList = new ArrayList();

		allSpareTypeList = spareTypeManager.loadAllSpareType();
 
		SpareType type = new SpareType();
		type.setId(Long.valueOf(-1L));
		type.setName(this.getText(""));
		allSpareTypeList.add(0, type);
		return allSpareTypeList;

	}

	@Override
	protected String getAdapterName() {
		if ((SysModel.DEVICE.toString().equals(toolingDevFlag))) {
			return "chooseSubscribes";
		} else {
			return "toolingchooseSubscribes";
		}
	}

	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return userManager.getUser();
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public SubscribeDetail getSubscribeDetail() {
		return subscribeDetail;
	}

	public void setSubscribeDetail(SubscribeDetail subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public List<SubscribeDetail> getSubscribeDetails() {
		return subscribeDetails;
	}

	public void setSubscribeDetails(List<SubscribeDetail> subscribeDetails) {
		this.subscribeDetails = subscribeDetails;
	}

	public String getBuyUserId() {
		return buyUserId;
	}

	public void setBuyUserId(String buyUserId) {
		this.buyUserId = buyUserId;
	}

 

}
