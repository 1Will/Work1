package com.yongjun.tdms.presentation.webwork.action.workspace.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workspace.data.Data;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workspace.data.DataManager;

@SuppressWarnings({"unchecked","rawtypes"})
public class ListDataAction extends ValueListAction {
	private static final long serialVersionUID = 675686785046421962L;
	private final DataManager dataManager;
	private final PersonnelFilesManager personnelFilesManager;
	private List<Data> lbs;

	public ListDataAction(DataManager dataManager, PersonnelFilesManager personnelFilesManager) {
		this.dataManager = dataManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public void prepare() throws Exception {
		if (hasIds("dataIds"))
			this.lbs = this.dataManager.loadAllData(getIds("dataIds"));
	}

	protected String getAdapterName() {
		return "datas";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		User u = this.userManager.getUser();
		List<PersonnelFiles> subordinateList = null;
		try {// 根据当前人code获取下属的列表
			subordinateList = this.personnelFilesManager.loadByKey("superiorLeader.code", this.userManager.getUser()
					.getCode());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<PersonnelFiles> list2 = this.personnelFilesManager.loadBySuperiorLeader(u.getCode());
		List<Long> personnelFilesIds = new ArrayList<Long>();
		for (PersonnelFiles p : list2) {
			personnelFilesIds.add(p.getId());
		}

		if (personnelFilesIds != null && personnelFilesIds.size() > 0) {
			map.put("personnelFilesId", personnelFilesIds);
		}
		if (request.getParameter("year") == null || request.getParameter("year").equals("")) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年");
			String year = sf.format(new Date());
			map.put("year", "%" + year + "%");
		}
		if (subordinateList == null || subordinateList.size() < 0) {
			map.put("notData", "1");
		}
		
		if(!map.containsKey("sortColumn")){
			map.put("sortColumn", "month");
			map.put("sortDirection", "desc");
		}
		return map;
	}

	public String execute() throws Exception {
		return "success";
	}

}
