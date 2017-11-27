package com.yongjun.tdms.presentation.webwork.action.workspace.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workspace.data.Data;
import com.yongjun.tdms.service.workspace.data.DataManager;

public class ListMyDataAction extends ValueListAction {
	private static final long serialVersionUID = 675686785046421962L;
	private final DataManager dataManager;
	private List<Data> lbs;

	public ListMyDataAction(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public void prepare() throws Exception {
		if (hasIds("dataIds"))
			this.lbs = this.dataManager.loadAllData(getIds("dataIds"));
	}

	protected String getAdapterName() {
		return "myDatas";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		User u = this.userManager.getUser();
		if (null != u) {
			map.put("personnelFiles.code", u.getCode());
		}
		if (request.getParameter("data.year") == null || request.getParameter("data.year").equals("")) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年");
			String year = sf.format(new Date());
			map.put("data.year", "%" + year + "%");
		}
		return map;
	}

	public String execute() throws Exception {
		return "success";
	}

}
