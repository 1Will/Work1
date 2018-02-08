package com.yongjun.tdms.presentation.webwork.action.runmaintenance.migrate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.model.runmaintenance.migrate.MigrateDetail;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateDetailManager;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateManager;

public class ListMigrateDetailAction extends ValueListAction{
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MigrateDetailManager migrateDetailManager;
	private final MigrateManager migrateManager;
	
	private List<MigrateDetail> migrateDetail;
	private String allMigrateDeviceId;          //设备转移明细单的所有Id;
	private String allDeviceMigratenewManager;  //设备转移明细列表中的新负责人的id;
	private String migrateDeviceNewPlace;       //设备转移到新地点
	private Migrate migrate;
	private MigrateDetail detail;
    private String newDeviceIds;                          // 用","分割的设备ID的字符串
    private String toolingDevFlag; 
    public ListMigrateDetailAction( MigrateManager migrateManager,
    		MigrateDetailManager migrateDetailManager){
    	     this.migrateManager=migrateManager;
    	     this.migrateDetailManager=migrateDetailManager;
    	
    }
    public void prepare() {
		if (this.hasId("migrate.id")) {
			this.migrate = this.migrateManager.loadMigrate(this.getId("migrate.id"));
		}
		if (this.hasIds("migrateDetailIds")) {
			this.migrateDetail = this.migrateDetailManager.loadAllMigrateDetails(this.getIds("migrateDetailIds"));
		}
		if (null == this.newDeviceIds) {
			if (!StringUtils.isEmpty(request.getParameter("addDeviceIds"))) {
				this.newDeviceIds = request.getParameter("addDeviceIds");
			}
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		
		}
		this.setFirst(false);
	}
    public String execute() {
		if (this.isAddDeviceMigrate()) {
			return saveAddDeviceMigrateDetail();
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
		this.migrateDetailManager.deleteAllMigrateDetails(migrateDetail);
		return SUCCESS;
	}
	/**
	 * 判断页面传来的addDevice变量是否有值，且值是否等于addDevices
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddDeviceMigrate() {
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
		
		if(!StringUtils.isEmpty(request.getParameter("allDeviceMigratenewManager"))){
			this.allDeviceMigratenewManager=request.getParameter("allDeviceMigratenewManager");
		}
		
		if(!StringUtils.isEmpty(request.getParameter("migrateDeviceNewPlace"))){
			this.migrateDeviceNewPlace=request.getParameter("migrateDeviceNewPlace");
		}
		
		if(!StringUtils.isEmpty(request.getParameter("allMigrateDetailId"))){
			this.allMigrateDeviceId=request.getParameter("allMigrateDetailId");
		}
		
		this.migrateDetailManager.storeMigrateDetail(allMigrateDeviceId,allDeviceMigratenewManager,migrateDeviceNewPlace);
		return SUCCESS;
	}
	/**
	 * 保存新添加的设备为鉴定明细
	 * @return SUCCESS
	 */
	public String saveAddDeviceMigrateDetail() {
		
		    
		this.migrateDetailManager.storemigrateDetail(migrate, newDeviceIds);
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("migrate.id",this.getId("migrate.id"));
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			return "devicemigratedetails";
		} 
		else {
			return "toolingmigratedetails";
		}
		
	}

	public Migrate getMigrate() {
		return migrate;
	}
	public void setMigrate(Migrate migrate) {
		this.migrate = migrate;
	}
	public List<MigrateDetail> getMigrateDetail() {
		return migrateDetail;
	}
	public void setMigrateDetail(List<MigrateDetail> migrateDetail) {
		this.migrateDetail = migrateDetail;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public MigrateDetail getDetail() {
		return detail;
	}
	public void setDetail(MigrateDetail detail) {
		this.detail = detail;
	}
	

}
