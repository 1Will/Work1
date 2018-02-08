package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;

public class ListPreRepairPlabDetailHistoryAction extends ValueListAction {
	private static final long serialVersionUID = 8834798916591634221L;

	//资产标识[TOOLING 工装][DEVICE 设备]
	private String toolingDevFlag;
	
	public ListPreRepairPlabDetailHistoryAction(){}
	
	@Override
	protected String getAdapterName() {
		return "preRepairPlanDetailHistorys";
	}
	
	public List<LabelValue> getProcStatus() {
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		LabelValue[] arrays = this.wrapEnum(PreRepairDetailResult.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
