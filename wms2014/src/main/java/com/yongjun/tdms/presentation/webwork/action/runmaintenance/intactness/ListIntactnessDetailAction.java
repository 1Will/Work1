package com.yongjun.tdms.presentation.webwork.action.runmaintenance.intactness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.intactness.Intactness;
import com.yongjun.tdms.model.runmaintenance.intactness.IntactnessDetail;
import com.yongjun.tdms.model.runmaintenance.intactness.IntactnessResultType;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetailResult;
import com.yongjun.tdms.service.runmaintenance.intactness.IntactnessDetailManager;
import com.yongjun.tdms.service.runmaintenance.intactness.IntactnessManager;

public class ListIntactnessDetailAction extends ValueListAction {
	private static final long serialVersionUID = -4029255443190215488L;
	
	private final IntactnessDetailManager intactnessDetailManager;
	private final IntactnessManager intactnessManager;
	
	private List<IntactnessDetail> intactnessDetail;
	private Intactness intactnessBill;
	private String newDeviceIds;                                 // 用","分割的设备ID的字符串
	private String allIntactnessDetailId;                        // 明细列表中所有明细ID字符串
	private String allIntactnessDetailResult;                    // 明细列表中所有明细ID和鉴定结果的字符串
	private String allComment;                                   // 明细列表中所有明细ID和详细描述的字符串
	
	public ListIntactnessDetailAction(IntactnessDetailManager intactnessDetailManager,
			IntactnessManager intactnessManager) {
		this.intactnessDetailManager = intactnessDetailManager;
		this.intactnessManager = intactnessManager;
	}
	
	public void prepare() {
		if (this.hasId("intactnessBill.id")) {
			this.intactnessBill = this.intactnessManager.loadIntactness(this.getId("intactnessBill.id"));
		}
		if (this.hasIds("intactnessDetailIds")) {
			this.intactnessDetail = this.intactnessDetailManager.loadAllIntactnessDetails(this.getIds("intactnessDetailIds"));
		}
		if (null == this.newDeviceIds) {
			if (!StringUtils.isEmpty(request.getParameter("addDeviceIds"))) {
				this.newDeviceIds = request.getParameter("addDeviceIds");
			}
		}
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isAddDeviceIntactness()) {
			return saveAddDeviceIntactnessDetail();
		}
		if (this.isSave()) {
			return save();
		}
		if(this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断页面传来的addDevice变量是否有值，且值是否等于addDevices
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddDeviceIntactness() {
		if (!StringUtils.isEmpty(request.getParameter("addDevice"))) {
			if (request.getParameter("addDevice").equals("addDevices"))
				return true;
		}
		return false;
	}
	
	/**
	 * 保存新添加的设备为鉴定明细
	 * @return SUCCESS
	 */
	public String saveAddDeviceIntactnessDetail() {
		this.intactnessDetailManager.storeIntactnessDetail(intactnessBill, newDeviceIds);
		return SUCCESS;
	}
	
	/**
	 * 判断是否点击保存按钮
	 * @return true | false
	 */
	private boolean isSave() {
		return this.hasKey("save");
	}
	
	public String save() {
		if (!StringUtils.isEmpty(request.getParameter("allIntactnessDetailId"))) {
			this.allIntactnessDetailId = request.getParameter("allIntactnessDetailId");
		}
		if (!StringUtils.isEmpty(request.getParameter("allIntactnessDetailResult"))) {
			this.allIntactnessDetailResult = request.getParameter("allIntactnessDetailResult");
		}
		if (!StringUtils.isEmpty(request.getParameter("allComment"))) {
			this.allComment = request.getParameter("allComment");
		}
		this.intactnessDetailManager.storeIntactnessDetail(allIntactnessDetailId,
				allIntactnessDetailResult, allComment);
		return SUCCESS;
	}
	
	public String delete() {
		this.intactnessDetailManager.deleteAllIntactnessDetail(intactnessDetail);
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		return "intactnessDetails";
	}

	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("intactnessBill.id",this.getId("intactnessBill.id"));
		return map;
	}
	
	public Intactness getIntactnessBill() {
		return intactnessBill;
	}

	public void setIntactnessBill(Intactness intactnessBill) {
		this.intactnessBill = intactnessBill;
	}
	
	/**
	 * 获取鉴定结果的所有值
	 * @return
	 */
	public List getIntactnessResults() {
		//LabelValue labelValue = new LabelValue(Long.valueOf(-1).toString(), this.getText(""));
		LabelValue[] arrays = this.wrapEnum(IntactnessResultType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		//tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
}
