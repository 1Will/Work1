package com.yongjun.tdms.presentation.webwork.action.runmaintenance.trusteeship;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
import com.yongjun.tdms.model.runmaintenance.trusteeship.TrusteeshipDetail;
import com.yongjun.tdms.service.runmaintenance.trusteeship.TrusteeshipDetailManager;
import com.yongjun.tdms.service.runmaintenance.trusteeship.TrusteeshipManager;

public class ListTrusteeshipDetailAction extends ValueListAction {
	private static final long serialVersionUID = 1L;

	
	
	private Trusteeship trusteeship;

	private String newDeviceIds;                                //用逗号,分割新建设备的id，
	private String toolingDevFlag;
    private List<TrusteeshipDetail> trusteeshipDetail;
	private final TrusteeshipManager trusteeshipManager;

	private final TrusteeshipDetailManager trusteeshipDetailManager;

	public ListTrusteeshipDetailAction(
			TrusteeshipDetailManager trusteeshipDetailManager,
			TrusteeshipManager trusteeshipManager) {
		this.trusteeshipDetailManager = trusteeshipDetailManager;
		this.trusteeshipManager = trusteeshipManager;
	
	}

	public void prepare() {
		if (this.hasId("trusteeship.id")) {
			this.trusteeship =this.trusteeshipManager.loadTrusteeship(this
					.getId("trusteeship.id"));
		}
		if (this.hasIds("trusteeshipDetailIds")) {
			System.out.println("是否删除成功");
			this.trusteeshipDetail = this.trusteeshipDetailManager.loadAllTrusteeshipDetails(this.getIds("trusteeshipDetailIds"));
					
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		if (null == this.newDeviceIds) {
			if (!StringUtils.isEmpty(request.getParameter("addDeviceIds"))) {
				this.newDeviceIds = request.getParameter("addDeviceIds");
			}
		}
		this.setFirst(false);
	}
	  public String execute() {
			if (this.isAddDeviceTrusteeship()) {
				return saveAddDeviceTrusteeshipDetail();
			}
			if(this.isDelete()) {
			  return delete();
			}
			return SUCCESS;
	  }
	  public String delete() {
			this.trusteeshipDetailManager.deleteAllTrusteeshipDetails(trusteeshipDetail);
			return SUCCESS;
		}
	  public String saveAddDeviceTrusteeshipDetail() {
			this.trusteeshipDetailManager.storeTrusteeshipDetail(trusteeship, newDeviceIds);
			return SUCCESS;
		}
	/**
	 * 判断页面传来的addDevice变量是否有值，且值是否等于addDevices
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddDeviceTrusteeship() {
		if (!StringUtils.isEmpty(request.getParameter("addDevice"))) {
			if (request.getParameter("addDevice").equals("addDevices"))
				return true;
		}
		return false;
	}
	@Override
	protected String getAdapterName() {
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			return "deviceTrusteeshipDetails";
		} 
		else {
			return "toolingTrusteeshipDetails";
		}
	}
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("trusteeship.id",this.getId("trusteeship.id"));
		return map;
	}
	

	public Trusteeship getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(Trusteeship trusteeship) {
		this.trusteeship = trusteeship;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public List<TrusteeshipDetail> getTrusteeshipDetail() {
		return trusteeshipDetail;
	}

	public void setTrusteeshipDetail(List<TrusteeshipDetail> trusteeshipDetail) {
		this.trusteeshipDetail = trusteeshipDetail;
	}

	

	

}
