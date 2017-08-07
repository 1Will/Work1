package com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditBillingRecordAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
	private final BillingRecordManager billingRecordManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContractManagementManager contractManagementManager;
	private final ContactArchivesManager contactArchivesManager;
	private final UserManager userManager;
	private final EventTypeManager eventTypeManager;
	private final EventNewManager eventNewManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private final CodeValueManager codeValueManager;
	private final ReturnPlanManager returnPlanManager;
	private DataManager dataManager;
	private BillingRecord billingRecord;
	private ContractManagement contractManagement;
	private String popWindowFlag;
	private List<CodeValue> batchs = new ArrayList();

	public EditBillingRecordAction(BillingRecordManager billingRecordManager,
			ContactArchivesManager contactArchivesManager, PersonnelFilesManager personnelFilesManager,
			CustomerInfoManager customerInfoManager, ContractManagementManager contractManagementManager,
			UserManager userManager, EventTypeManager eventTypeManager, EventNewManager eventNewManager,
			PersonnelFilesToUserManager personnelFilesToUserManager, CodeValueManager codeValueManager,
			ReturnPlanManager returnPlanManager, DataManager dataManager) {
		this.billingRecordManager = billingRecordManager;
		this.contactArchivesManager = contactArchivesManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.userManager = userManager;
		this.eventTypeManager = eventTypeManager;
		this.eventNewManager = eventNewManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.codeValueManager = codeValueManager;
		this.returnPlanManager = returnPlanManager;
		this.dataManager=dataManager;
	}

	public void prepare() throws Exception {
		if (hasId("billingRecord.id")) {
			this.billingRecord = this.billingRecordManager.loadBillingRecord(getId("billingRecord.id"));
			CodeValue c = this.billingRecord.getBatch();
			if (null != c)
				this.batchs.add(c);
		} else {
			this.billingRecord = new BillingRecord();
		}

		if (hasId("contractManagement.id")) {
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			this.billingRecord.setContractManagement(contractManagement);
			this.billingRecord.setCustomerInfo(this.contractManagement.getCustomerInfo());
			this.billingRecord.setContactArchives(this.contractManagement.getLinkman());
		}

		if (hasId("contactArchives.id")) {
			ContactArchives contactArchives = this.contactArchivesManager
					.loadContactArchives(getId("contactArchives.id"));
			if (null != contactArchives) {
				this.billingRecord.setContactArchives(contactArchives);
			}
		}

		if (hasId("customer.id")) {
			CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
			if (null != customer) {
				this.billingRecord.setCustomerInfo(customer);
			}
		}

		User user = this.userManager.getUser();
		List list = this.personnelFilesManager.loadByKey("code", user.getCode());
		if (null != list) {
			PersonnelFiles payee = (PersonnelFiles) list.get(0);
			this.billingRecord.setPayee(payee);
		}

		if (null != request.getParameter("popWindowFlag")) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
	}

	public String save() throws Exception {
		boolean isNew = this.billingRecord.isNew();

		if (hasId("payee.id")) {
			PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(getId("payee.id"));
			if (null != payee) {
				this.billingRecord.setPayee(payee);
			}
		}
		if (hasId("batch.id")) {
			this.billingRecord.setBatch(this.codeValueManager.loadCodeValue(getId("batch.id")));
		}

		this.billingRecord.setIsSaved(request.getParameter("billingRecord.isSaved"));

		String submit = "";
		try {
			if(isNew){
				this.billingRecord.setSubmitNum(0l);
			}else {
				if(this.billingRecord.getIsSaved().equals("1")){
	            	 this.billingRecord.setSubmitNum(this.billingRecord.getSubmitNum()+1);
	             } 
			}
			this.billingRecordManager.storeBillingRecord(this.billingRecord);
			// 提交事件
			if ("1".equals(this.request.getParameter("billingRecord.isSaved"))) {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10013");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventType不存在！");
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName("开票事件");
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.billingRecord
						.getContractManagement().getProject().getId(), this.billingRecord.getContractManagement()
						.getProject().getCreateUser());
				map.put("users", pids);
				map.put("billingRecordId", this.billingRecord.getId() + "");
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				
				HashMap mapData =new HashMap();
	              mapData.put("type", "10013");
	              mapData.put("thisMoney", billingRecord.getSum());
	              mapData.put("lastMoney",billingRecord.getLastSubmitMoney() );
				mapData.put("submitNum", this.billingRecord.getSubmitNum());
				mapData.put("date", billingRecord.getBillingTime());
				this.dataManager.storeData(getPersonnelF(), mapData);
				this.billingRecord.setLastSubmitMoney(billingRecord.getSum());
				this.billingRecordManager.storeBillingRecord(billingRecord);
				
				submit = "submit";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if ("submit".equals(submit)) {
				addActionMessage(getText("billingRecord.submit.error"));
				return ERROR;
			}
			addActionMessage(getText("billingRecord.save.error"));
			return ERROR;
		}

		String[] keyArray = { "contractManagement", "batch" };
		Object[] valueArray = { this.billingRecord.getContractManagement().getId(), this.billingRecord.getBatch() };
		// 获取同一开票计划的所有的开票记录
		List<BillingRecord> billList = billingRecordManager.loadByKeyArray(keyArray, valueArray);
		// 判断收款计划是否全部到款，并修改状态
		List<ReturnPlan> rplist = this.returnPlanManager.loadByKeyArray(keyArray, valueArray);
		Double money = 0D;
		for (int i = 0; i < billList.size(); i++) {
			money += billList.get(i).getSum();
		}
		// 修改收款计划中开票相关项
		if (null != rplist && rplist.size() > 0) {
			ReturnPlan r = rplist.get(0);
			r.setBillDate(this.billingRecord.getBillingTime());
			r.setBillMoney(money);
			r.setBillingOrNot("1");
			if (money >= r.getSum()) {
				r.setIsBill("0");
				r.setBillingOrNot("2");
			}
			this.returnPlanManager.storeReturnPlan(r);
		}

		if (isNew) {
			addActionMessage(getText("billingRecord.save.success"));
			return NEW;
		}
		if ("submit".equals(submit)) {
			addActionMessage(getText("billingRecord.submit.success"));
			return SUCCESS;
		}
		addActionMessage(getText("billingRecord.edit.success"));
		return SUCCESS;
	}

	public List<CodeValue> getAllBatchs() {
		return this.batchs;
	}

	public BillingRecord getBillingRecord() {
		return this.billingRecord;
	}

	public void setBillingRecord(BillingRecord billingRecord) {
		this.billingRecord = billingRecord;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
	public PersonnelFiles getPersonnelF() throws Exception {
		List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());

		if ((null != pfs) && (pfs.size() > 0)) {
			return (PersonnelFiles) pfs.get(0);
		}
		return null;
	}
}
