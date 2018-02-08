package com.yongjun.tdms.presentation.webwork.action.repairRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.repairRecord.RepairRecord;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.repairRecord.RepairRecordManager;

/** 
  * @author 创建人: xichunguang
  * @date 创建时间：2017年12月21日 上午9:41:19 
  * 维修记录action
  */
public class ListRepairRecordAction extends ValueListAction{
	private CodeValueManager codeValueManager;
	private RepairRecordManager repairRecordManager;
	private GroupManager groupManager;
	private List<RepairRecord> recordsList;
	private UserManager userManager;
	private PersonnelFilesManager personnelFilesManager;
	public ListRepairRecordAction(CodeValueManager codeValueManager,RepairRecordManager repairRecordManager,GroupManager groupManager,UserManager userManager,PersonnelFilesManager personnelFilesManager) {
		// TODO Auto-generated constructor stub
		this.codeValueManager = codeValueManager;
		this.repairRecordManager = repairRecordManager;
		this.groupManager=groupManager;
		this.userManager=userManager;
		this.personnelFilesManager=personnelFilesManager;
	}
	
	@Override
	protected String getAdapterName() {
		return "repairRecordHQL";
	}
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		Set<User> users = groupManager.getGroupByGroupName("售后服务组").getUsers();
		User user=userManager.getUser();
		boolean b=true;
		for(User u:users){
			if(user.getId().equals(u.getId())||user.getId()==u.getId()){
				b=false;
			}
		}
		if(b){
			try {
				List<PersonnelFiles>personnelFileses=this.personnelFilesManager.loadByKey("code", user.getCode());
				if(personnelFileses!=null && personnelFileses.size()>0){
					map.put("personnelFiles.id", personnelFileses.get(0).getId());
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public void prepare() throws Exception {
		if ((this.recordsList == null) && (hasIds("repairRecordIds"))) {
			this.recordsList = this.repairRecordManager.loadAllRepairRecord(getIds("repairRecordIds"));
		}
		
	}
	
	
	public String execute() throws Exception {
		if (isDisabled()) {
			return disabled();
		}

		if (isEnable()) {
			return enable();
		}

		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	private String delete() {
		try {
			this.repairRecordManager.deleteAllRepairRecord(this.recordsList);
			addActionMessage(getText("维修记录删除成功！"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("维修记录删除失败！"));
		}
		return "error";
	}

	private String enable() {
		// TODO Auto-generated method stub
		return null;
	}

	private String disabled() {
		// TODO Auto-generated method stub
		return null;
	}
	//获取所有的兵种 242
	public List<CodeValue> getAllBranch() {
		List<CodeValue> codeValueList = getCodeValueList("242","所有");
		return codeValueList;
	}
	//获取所有的空调类型 243
	public List<CodeValue> getAllAirType() {
		List<CodeValue> codeValueList = getCodeValueList("243","所有");
		return codeValueList;
	}
	//获取所有的维修类型 244
	public List<CodeValue> getAllRepirType() {
		List<CodeValue> codeValueList = getCodeValueList("244","所有");
		return codeValueList;
	}
	//获取所有的故障分类 245
	public List<CodeValue> getAllFaultType() {
		List<CodeValue> codeValueList = getCodeValueList("245","所有");
		return codeValueList;
	}
	//获取所有的故障原因  246
	public List<CodeValue> getAllFaultReason() {
		List<CodeValue> codeValueList = getCodeValueList("246","所有");
		return codeValueList;
	}
	
	
	public List<RepairRecord> getRecordsList() {
		return recordsList;
	}

	public void setRecordsList(List<RepairRecord> recordsList) {
		this.recordsList = recordsList;
	}
	
	/**
	 * 根据code查询codevaluelist
	 * @param code 父级元素的code值
	 * @param addContent 增加的内容，（为null时不增加任何内容）
	 * @return
	 */
	public List<CodeValue> getCodeValueList(String code,String addContent){
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = codeValueManager.loadByKey("code", code);
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0) && (addContent != null)) {
					CodeValue cv = new CodeValue();
					cv.setId(null);
					cv.setName(addContent);
					codes.add(0, cv);
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}
	}
}
