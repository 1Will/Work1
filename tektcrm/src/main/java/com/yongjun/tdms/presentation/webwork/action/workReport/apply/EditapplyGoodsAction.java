package com.yongjun.tdms.presentation.webwork.action.workReport.apply;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox.KeySelectionManager;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.activitiFlow.CopySendPerson;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.apply.ApplyGoods;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.activitiFlow.CopySendPersonManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.apply.ApplyGoodsManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;

import net.sf.json.JSONObject;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditapplyGoodsAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
	private final ApplyGoodsManager applyGoodsManager;
	private final CodeValueManager codeValueManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final WorkflowCaseManager workflowCaseManager;
	private final FlowManager flowManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	protected final GroupManager groupManager;
	private final CopySendPersonManager copySendPersonManager;
	private ApplyGoods applyGoods;
	private PersonnelFiles personnelFiles;
	private String activitiFLow ;

	public EditapplyGoodsAction(ApplyGoodsManager applyGoodsManager, CodeValueManager codeValueManager,
			DepartmentManager departmentManager, UserManager userManager, PersonnelFilesManager personnelFilesManager,
			WorkflowCaseManager workflowCaseManager, FlowManager flowManager,EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, GroupManager groupManager, CopySendPersonManager copySendPersonManager) {
		this.applyGoodsManager = applyGoodsManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.personnelFilesManager = personnelFilesManager;
		this.workflowCaseManager = workflowCaseManager;
		this.flowManager = flowManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.groupManager = groupManager;
		this.copySendPersonManager=copySendPersonManager;
	}

	public void prepare() throws Exception {
		if (request.getParameter("activitiFLow") != null) {
			this.activitiFLow = request.getParameter("activitiFLow");
		}
		if (hasId("applyGoods.id")) {
			this.applyGoods = this.applyGoodsManager.loadApplyGoods(getId("applyGoods.id"));
			this.personnelFiles = this.applyGoods.getApplyPerson();
		} else {
			this.applyGoods = new ApplyGoods();
			User user = getUser();
			if (null != user.getCode()) {
				List list = this.personnelFilesManager.loadByKey("code", user.getCode());
				if (null != list) {
					this.personnelFiles = ((PersonnelFiles) list.get(0));
					this.applyGoods.setApplyPerson(this.personnelFiles);
				}
			}
		}
	}

	public String save() throws Exception {
		boolean isNew = this.applyGoods.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
			this.applyGoods.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("status.id"))));
		} else {
			this.applyGoods.setStatus((CodeValue) this.codeValueManager.loadByKey("code", "02000").get(0));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("purpose.id"))) {
			this.applyGoods.setPurpose(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("purpose.id"))));
		}
		if (!StringUtils.isEmpty(this.request.getParameter("flow.id"))) {
			this.applyGoods.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request
					.getParameter("flow.id"))));;
		}
		if (!StringUtils.isEmpty(this.request.getParameter("goodsName.id"))) {
			this.applyGoods.setGoodsName(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("goodsName.id"))));
			System.out.println(Long.valueOf(this.request.getParameter("goodsName.id")));
			System.out.println(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("goodsName.id"))).getName());
		}
		if (!StringUtils.isEmpty(this.request.getParameter("unit.id"))) {
			this.applyGoods.setUnit(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("unit.id"))));;
		}
		if (null != this.personnelFiles.getDept()) {
			this.applyGoods.setDept(this.personnelFiles.getDept().getName());
		}
		if (null == this.applyGoods.getApplyPerson()) {
			this.applyGoods.setApplyPerson(this.personnelFiles);
		}

		this.applyGoods.setOrganization(this.userManager.getOrganization());
		this.applyGoods.setIsSaved(this.request.getParameter("isSaved"));
		if (isNew) {
			this.applyGoodsManager.storeApplyGoods(this.applyGoods);
			Set<User> users = groupManager.getGroupByGroupName("招待通知组").getUsers();
			if(users!=null && users.size()>0){
				for(User user:users){
					CopySendPerson c=new CopySendPerson();
					c.setBussnessId(this.applyGoods.getId());
					c.setFlow(this.applyGoods.getFlow());
					c.setPerson(personnelFilesManager.loadByKey("code", user.getCode()).get(0));
					copySendPersonManager.storeCopySendPerson(c);
				}
			}
			addActionMessage(getText("applyGoods.save.success"));
//			WorkflowTrigger(this.personnelFiles);
			return "new";
		}
		String submit =null;
		try {
			this.applyGoodsManager.storeApplyGoods(this.applyGoods);
			
			/*if ("1".equals(this.request.getParameter("isSaved"))) {
				Set<User> noticePers = groupManager.getGroupByGroupName("微信日报通知组").getUsers();
				Set<Long> idSet = new HashSet<Long>();
				idSet.add(getUser().getId());
				for (User user : noticePers) {
					idSet.add(user.getId());
				}
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10031");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventTypes不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					event.setUserId(this.userManager.getUser().getId() + "");
					Map<String, String> map = new HashMap<String, String>();
					// 查询领导
					PersonnelFiles pFiles = this.personnelFiles.getSuperiorLeader();
					while (pFiles != null) {
						List<User> leader = userManager.loadByKey("code", pFiles.getCode());
						idSet.add(leader.get(0).getId());
						pFiles = pFiles.getSuperiorLeader();
					}
					String ids = "";
					for (Long id : idSet) {
						ids += id + ",";
					}
					map.put("users", ids.substring(0, ids.length() - 1));
					map.put("applyCarId", this.applyCar.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.applyCar.getCreatedTime())+","+this.applyCar.getApplyPerson().getName()+"提交了请假单");
					map.put("url", "apply/editApplyCar.html?readOnly=false&applyCar.id="+this.applyCar.getId());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					submit = "submit";
			}*/
			}catch (Exception e) {
				e.printStackTrace();
				if (isNew) {
					addActionMessage(getText("applyGoods.save.error"));
					return ERROR;
				}
				if(submit!=null){
					addActionMessage(getText("applyGoods.submit.error"));
					return ERROR;
				}
				addActionMessage(getText("applyGoods.edit.error"));
				return ERROR;
			}
		if (isNew) {
//			WorkflowTrigger(this.personnelFiles);
			addActionMessage(getText("applyGoods.save.success"));
			return "new";
		}
		if(submit!=null){
			addActionMessage(getText("applyGoods.submit.success"));
			return SUCCESS;
		}
		addActionMessage(getText("applyGoods.edit.success"));
		return SUCCESS;
	}
			
/*	public void WorkflowTrigger(PersonnelFiles person) {
		if (null != this.applyGoods.getId()) {
			String url = "apply/editApplyCar.html?applyCar.id=" + String.valueOf(this.applyGoods.getId());
			String aoCode = this.applyGoods.getPurpose().getCode().trim();
			if (aoCode.equals("03000")) {
				this.workflowCaseManager.startWorkflowCase("02101", this.applyGoods.getId(), person, url);
			} else {
				this.workflowCaseManager.startWorkflowCase("02102", this.applyGoods.getId(), person, url);
			}
		} else {
			List lbList = null;
			try {
				lbList = this.applyCarManager.loadByKey("code", this.applyGoods.getCode());
			} catch (Exception e) {
				this.logger.info("根据code查询用车申请出错！");
			}
			if ((null != lbList) && (!lbList.isEmpty())) {
				this.applyGoods = (ApplyCar) lbList.get(0);
				WorkflowTrigger(person);
			}
		}
	}*/
	/*//流程类型
	List<Flow> flowList = flowManager.loadByKey("openOrNot", "0");
	//状态类型
	List<CodeValue> statusList = CodeValueUtil.getCodeValueList("020", "类型", null);
	//单位
	//List<CodeValue> purposeList = CodeValueUtil.getCodeValueList("217", "用途", null);//本地
	
	//用途
	List<CodeValue> unitList = CodeValueUtil.getCodeValueList("068", "", null);*/

	public List<CodeValue> getAllStatus() {
		List codes = null;
		try {
			codes = new ArrayList();
			String[] keys = {"code","name"};
			Object[] values = {"020","类型"};
			List one = this.codeValueManager.loadByKeyArray(keys, values);

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}
	public List<CodeValue> getAllGoodsName() {
		List codes = null;
		try {
			codes = new ArrayList();
			String[] keys = {"code","name"};
			Object[] values = {"304","物品名称"};
			List one = this.codeValueManager.loadByKeyArray(keys, values);

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}
	public List<CodeValue> getAllUnitList() {
		List codes = null;
		try {
			codes = new ArrayList();
			String[] keys = {"code","name"};
			Object[] values = {"068","单位"};
			List one = this.codeValueManager.loadByKeyArray(keys, values);

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}
	public List<CodeValue> getAllPurposes (){
		try {
			List<CodeValue> codeValues = new ArrayList<CodeValue>();
			String [] keys={"code","name"};
			Object [] values={"219","用途"};
			CodeValue one = codeValueManager.loadByKeyArray(keys, values).get(0);
			if (null != one) {
				List<CodeValue> list = codeValueManager.loadByKey("parentCV",one.getId());
				/*if ((null != list) && (list.size() > 0) && (addContent != null)) {
					CodeValue cv = new CodeValue();
					cv.setId(null);
					cv.setName(addContent);
					codeValues.add(0, cv);
				}*/
				codeValues.addAll(list);
			}
			return codeValues;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}
		
		/*List codes = null;
		try {
			codes = new ArrayList();
			String[] keys = {"code","name"};
			Object[] values = {"030","请假/调休"};
			List one = this.codeValueManager.loadByKeyArray(keys, values);
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;*/
	}

	public List<Department> getAllDepts() {
		List depts = this.departmentManager.loadAllDepartments();
		return depts;
	}
	
	public List<Flow> getAllFlows() {
		List flowList = null;
		try {
			CodeValue codeValue =codeValueManager.loadByKey("code","t_applyGoods").get(0);
			String[] keys = {"openOrNot","flowCode"};
			Object[] objects = {"0",codeValue.getId()};
		    flowList = flowManager.loadByKeyArray(keys, objects);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flowList;
	}
	
	
	
	public User getUser() {
		return this.userManager.getUser();
	}



	public ApplyGoods getApplyGoods() {
		return applyGoods;
	}

	public void setApplyGoods(ApplyGoods applyGoods) {
		this.applyGoods = applyGoods;
	}

	public String getActivitiFLow() {
		return activitiFLow;
	}

	public void setActivitiFLow(String activitiFLow) {
		this.activitiFLow = activitiFLow;
	}
	
}
