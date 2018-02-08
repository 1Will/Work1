package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.csvreader.CsvWriter;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditContractManagementAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ContractManagementManager contractManagementManager;
	private final CustomerInfoManager customerInfoManager;
	private final UserManager userManager;
	private final ContactArchivesManager contactArchivesManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final DepartmentManager departmentManager;
	private final InstitutionManager institutionManager;
	private final ProductListManager productListManager;
	private final ProjectInfoManager projectInfoManager;
	private final EventTypeManager eventTypeManager;
	private final EventNewManager eventNewManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private final ContactToHistoryManager contactToHistoryManager;
	private final DataManager dataManager;
	private final ReturnPlanManager returnPlanManager;
	private final BillingRecordManager billingRecordManager;
	private final FinancialManagementManager financialManagementManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;

	private ContractManagement contractManagement = null;
	private CustomerInfo customerInfo = null;
	private ProjectInfo projectInfo = null;
	private Institution institution = null;
	private PersonnelFiles saleman = null;
	private ContactArchives linkman = null;
	private Department deparment;
	private CodeValue contractType;
	private CodeValue moneyType;
	private CodeValue payType;
	private CodeValue state;
	private String popWindowFlag;
	private PersonnelFiles personnelFiles;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public EditContractManagementAction(ContractManagementManager contractManagementManager,CustomerInfoManager customerInfoManager, 
			ContactArchivesManager contactArchivesManager,CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager,
			DepartmentManager departmentManager, InstitutionManager institutionManager, UserManager userManager,
			ProductListManager productListManager, ProjectInfoManager projectInfoManager,EventTypeManager eventTypeManager,
			EventNewManager eventNewManager,PersonnelFilesToUserManager personnelFilesToUserManager, ContactToHistoryManager contactToHistoryManager,
			DataManager dataManager,ReturnPlanManager returnPlanManager,BillingRecordManager billingRecordManager,
			FinancialManagementManager financialManagementManager,ProjectInfoPlanManager projectInfoPlanManager) {
		this.contractManagementManager = contractManagementManager;
		this.customerInfoManager = customerInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.contactArchivesManager = contactArchivesManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
		this.institutionManager = institutionManager;
		this.userManager = userManager;
		this.productListManager = productListManager;
		this.projectInfoManager = projectInfoManager;
		this.eventTypeManager = eventTypeManager;
		this.eventNewManager = eventNewManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.contactToHistoryManager = contactToHistoryManager;
		this.dataManager=dataManager;
		this.returnPlanManager=returnPlanManager;
		this.billingRecordManager=billingRecordManager;
		this.financialManagementManager=financialManagementManager;
		this.projectInfoPlanManager=projectInfoPlanManager;
	}

	public void prepare() throws Exception {
		if (null == this.contractManagement) {
			if (hasId("contractManagement.id")) {
				this.contractManagement = this.contractManagementManager
						.loadContractManagement(getId("contractManagement.id"));
				DecimalFormat format = new DecimalFormat("0.00");
				this.contractManagement.setContractMoney(new Double(format.format(this.productListManager
						.getSumTotalPrice(getId("contractManagement.id").longValue()))).doubleValue());
			} else {
				this.contractManagement = new ContractManagement();
				User user = getUser();
				if (null != user.getCode()) {
					List list = this.personnelFilesManager.loadByKey("code", user.getCode());

					if (null != list) {
						this.saleman = ((PersonnelFiles) list.get(0));
						this.contractManagement.setSaleman(this.saleman);
						personnelFiles=((PersonnelFiles) list.get(0));
					}
				}
			}
		}

		if (null == this.customerInfo) {
			if (hasId("customerInfo.id")) {
				this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			} else {
				this.customerInfo = null;
			}
		}

		if (null == this.linkman) {
			if (hasId("linkman.id")) {
				this.linkman = this.contactArchivesManager.loadContactArchives(getId("linkman.id"));
			} else {
				this.linkman = null;
			}
		}

		if (null == this.projectInfo) {
			if (hasId("project.id")) {
				this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("project.id"));
			} else {
				this.projectInfo = null;
			}
		}

		if (null == this.deparment) {
			if (hasId("deparment.id")) {
				this.deparment = this.departmentManager.loadDepartment(getId("deparment.id"));
			} else {
				this.deparment = null;
			}
		}

		if (null == this.contractType) {
			if (hasId("contractType.id")) {
				this.contractType = this.codeValueManager.loadCodeValue(getId("contractType.id"));
			} else {
				this.contractType = null;
			}
		}
		if (null == this.moneyType) {
			if (hasId("moneyType.id")) {
				this.moneyType = this.codeValueManager.loadCodeValue(getId("moneyType.id"));
			} else {
				this.moneyType = null;
			}
		}
		if (null == this.payType) {
			if (hasId("payType.id")) {
				this.payType = this.codeValueManager.loadCodeValue(getId("payType.id"));
			} else {
				this.payType = null;
			}
		}
		if (null == this.state) {
			if (hasId("state.id")) {
				this.state = this.codeValueManager.loadCodeValue(getId("state.id"));
			} else {
				this.state = codeValueManager.loadByKey("code", "06603").get(0);
			}
		}
		if (null == this.institution)
			if (hasId("institution.id")) {
				this.institution = this.institutionManager.loadInstitution(getId("institution.id"));
			} else
				this.institution = null;
		if (this.request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = this.request.getParameter("popWindowFlag");
		}
	}

	public String save() {
		boolean isNew = this.contractManagement.isNew();
		if (isNew) {
			this.contractManagement.setIsExport("2");
		}else {
			if(!"2".equals(contractManagement.getIsExport())){
				this.contractManagement.setIsExport("1");
			}
		}
		
		this.contractManagement.setOrganization(this.userManager.getOrganization());
		/*if (isNew) {
			String code = autoCompleteCode();
			this.contractManagement.setCode(code);
		}*/

		if (hasId("payWay.id")) {
			CodeValue payWay = this.codeValueManager.loadCodeValue(getId("payWay.id"));
			this.contractManagement.setPayWay(payWay);
		}

		if (hasId("saleman.id")) {
			this.saleman = this.personnelFilesManager.loadPersonnel(getId("saleman.id"));
		}
		this.contractManagement.setIsSaved(request.getParameter("contractManagement.isSaved"));
		try {
			this.contractManagement.setCustomerInfo(this.customerInfo);
			this.contractManagement.setLinkman(this.linkman);
			this.contractManagement.setProject(this.projectInfo);
			this.contractManagement.setContractType(this.contractType);
			this.contractManagement.setSaleman(this.saleman);
			this.contractManagement.setDeparment(this.deparment);
			this.contractManagement.setMoneyType(this.moneyType);
			this.contractManagement.setPayType(this.payType);
			this.contractManagement.setState(this.state);
			this.contractManagement.setInstitution(this.institution);
			this.contractManagement.setDeparment(this.deparment);
			
			if(isNew){
				this.contractManagement.setSubmitNum(0l);
			}else {
				if(this.contractManagement.getIsSaved().equals("1")){
	            	 this.contractManagement.setSubmitNum(this.contractManagement.getSubmitNum()+1);
	             }
			}
			//更新合同所属项目的   contractManagementSum 
			if (isNew) {
				try {
					List<ContractManagement> contractManagements = this.contractManagementManager.loadByKey("project", this.contractManagement.getProject());
					Long contractManagementSum = null;
					if(contractManagements != null){
						contractManagementSum = Long.valueOf(contractManagements.size()+1);
					}else{
						contractManagementSum = 1L;
					}
					this.projectInfo.setContractManagementSum(contractManagementSum);
					this.projectInfoManager.storeProjectInfo(this.projectInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.contractManagementManager.storeContractManagement(this.contractManagement);
			
			
			String submit = "";
			if ("1".equals(this.request.getParameter("contractManagement.isSaved"))) {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10006");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					eventType = new EventType();
					eventType.setId(7L);
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName(eventType.getName());
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.projectInfo.getId(),
						this.projectInfo.getCreateUser());
				map.put("users", pids);
				map.put("contractManagementId", this.contractManagement.getId() + "");
				map.put("url", "contractManagement/editContractManagementAction.html?popWindowFlag=popWindowFlag&contractManagement.id="+this.contractManagement.getId());
				map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.contractManagement.getCreatedTime())+","+this.contractManagement.getSaleman().getName()+"提交了合同:"+this.contractManagement.getContractName());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				
//				 HashMap mapData =new HashMap();
//	              mapData.put("type", "10006");
//	              mapData.put("thisMoney", contractManagement.getContractMoney());
//	              mapData.put("lastMoney",contractManagement.getLastSubmitMoney() );
//				mapData.put("submitNum", this.contractManagement.getSubmitNum());
//				mapData.put("date", contractManagement.getCiemdinghTime());
//				this.dataManager.storeData(getPersonnelF(), mapData);
//				this.contractManagement.setLastSubmitMoney(contractManagement.getContractMoney());
//				this.contractManagementManager.storeContractManagement(this.contractManagement);//更新上次提交金额为本次的合同金额
				submit = "submit";
			}

// 仅当项目状态为立项的情况下，合同保存后，项目状态改为合同
//			if ("20101".equals(projectInfo.getState().getCode())) {
//				this.projectInfo.setState(this.codeValueManager.loadByKey("code", "20102").get(0));
//				this.projectInfoManager.storeProjectInfo(projectInfo);
//			}

			if (isNew) {
				ContactToHistory history = new ContactToHistory();
				history.setCreator(this.userManager.getUser().getName());
				history.setLastOperator(this.userManager.getUser().getName());
				history.setContractManagement(this.contractManagement);
				history.setConment("创建合同");
				this.contactToHistoryManager.storeContactToHistory(history);
			} else {
				ContactToHistory history = new ContactToHistory();
				// history.setCreator(this.userManager.getUser().getName());
				history.setLastOperator(this.userManager.getUser().getName());
				history.setContractManagement(this.contractManagement);
				history.setConment("修改基本信息");
				this.contactToHistoryManager.storeContactToHistory(history);
			}

			if (isNew) {
				addActionMessage(getText("contractManagement.add.success"));
				return "new";
			}
			if ("submit".equals(submit)) {
				addActionMessage(getText("contractManagement.submit.success"));
			} else {
				addActionMessage(getText("contractManagement.edit.success"));
			}

			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			if (isNew) {
				addActionMessage(getText("contractManagement.add.error"));
				return "new";
			}
			addActionMessage(getText("contractManagement.edit.error"));
		}
		return "success";
	}
	
	public String exportContractManagement(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String name = "ht_"+format.format(new Date())+".csv";
			response.setContentType("application/csv");
			response.setHeader("Content-Disposition", "attachment; filename="+name);
			CsvWriter csvWriter = new CsvWriter(response.getOutputStream(), ',',Charset.forName("GBK"));
			Map<String, List> data = selectContractManagement();
			List<List<String>> cmList = data.get("cms");
			List<List<String>> plList = data.get("pls");
			List<List<String>> rpList = data.get("rps");
			List<List<String>> brList = data.get("brs");
			List<List<String>> fmList = data.get("fms");
			List<List<String>> ppList = data.get("pps");
			
			csvWriter.writeRecord(new String[]{"ContractManagement"});
			if(cmList != null && cmList.size()>0){
				for (int i = 0; i < cmList.size(); i++) {
					csvWriter.writeRecord((String[])cmList.get(i).toArray(new String[cmList.get(i).size()]));
				}
			}
			
			csvWriter.writeRecord(new String[]{"ProductList"});
			if(plList != null && plList.size()>0){
				for (int i = 0; i < plList.size(); i++) {
					csvWriter.writeRecord((String[])plList.get(i).toArray(new String[plList.get(i).size()]));
				}
			}
			
			csvWriter.writeRecord(new String[]{"ReturnPlan"});
			if(rpList != null && rpList.size()>0){
				for (int i = 0; i < rpList.size(); i++) {
					csvWriter.writeRecord((String[])rpList.get(i).toArray(new String[rpList.get(i).size()]));
				}
			}
			
			csvWriter.writeRecord(new String[]{"BillingRecord"});
			if(brList != null && brList.size()>0){
				for (int i = 0; i < brList.size(); i++) {
					csvWriter.writeRecord((String[])brList.get(i).toArray(new String[brList.get(i).size()]));
				}
			}
			
			csvWriter.writeRecord(new String[]{"FinancialManagement"});
			if(fmList != null && fmList.size()>0){
				for (int i = 0; i < fmList.size(); i++) {
					csvWriter.writeRecord((String[])fmList.get(i).toArray(new String[fmList.get(i).size()]));
				}
			}
			
			csvWriter.writeRecord(new String[]{"ProjectInfoPlan"});
			if(ppList != null && ppList.size()>0){
				for (int i = 0; i < ppList.size(); i++) {
					csvWriter.writeRecord((String[])ppList.get(i).toArray(new String[ppList.get(i).size()]));
				}
			}
			csvWriter.writeRecord(new String[]{"END"});
			csvWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, List> selectContractManagement() throws DaoException{
		Map<String, List> data = new HashMap<String, List>();
		List<List<String>> cmList =new ArrayList<List<String>>();
		List<ContractManagement > cms = new ArrayList<ContractManagement>();
		cms.addAll(this.contractManagementManager.loadByKeyArray(new String[]{"disabled","isExport"}, new Object[]{"0","1"}));
		cms.addAll(this.contractManagementManager.loadByKeyArray(new String[]{"disabled","isExport"}, new Object[]{"0","2"}));
		if(cms !=null && cms.size()>0){
			String[] headers = {"CODE","CONTRACTNAME","CUSTOMERINFO_ID","LINKMAN","ADDRESS","TELEPHONE","SALEMAN_ID","DEPARMENT",
					"CIEMDINGHTIME","STARTTIME","ENDTIME","CONTRACTTYPE","CONTRACTMONEY","PAIDMONEY","MONEYTYPE","PAYTYPE",
					"STATE","CONTRACTCONTENT","REMART","DISABLED","CREATED_TIME","OVER_GET","OVER_RETURN_PLAN","PROJECT_ID","isSaved",
					"submitNum","lastSubmitMoney","twoCFourF","receipt","back","hasBillSum","isExport"};
			List<String> cmtop = Arrays.asList(headers); 
			cmList.add(cmtop);
			for (int i = 0; i < cms.size(); i++) {
				List<String> cm = new ArrayList<String>();
				ContractManagement c = cms.get(i);
				cm.add(c.getCode() ==null ? "" :c.getCode());
				cm.add(c.getContractName() ==null ? "" :c.getContractName());
				cm.add(c.getCustomerInfo()==null ? "" : c.getCustomerInfo().getId()+"");
				cm.add(c.getLinkman()==null ? "" : c.getLinkman().getId()+"");
				cm.add(c.getAddress() ==null ? "" :c.getAddress());
				cm.add(c.getTelephone() ==null ? "" :c.getTelephone());
				cm.add(c.getSaleman() ==null ? "" : c.getSaleman().getId()+"");
				cm.add(c.getDeparment() ==null ? "" :c.getDeparment().getId()+"");
				cm.add(c.getCiemdinghTime() ==null ? "" : format.format(c.getCiemdinghTime()));
				cm.add(c.getStartTime() ==null ? "" :format.format(c.getStartTime()));
				cm.add(c.getEndTime() ==null ? "" :format.format(c.getEndTime()));
				cm.add(c.getContractType() ==null ? "" :c.getContractType().getId()+"");
				cm.add(c.getContractMoney()+"");
				cm.add(c.getPaidMoney()+"");
				cm.add(c.getMoneyType() ==null ? "" :c.getMoneyType().getId()+"");
				cm.add(c.getPayType() ==null ? "" :c.getPayType().getId()+"");
				cm.add(c.getState() ==null ? "" :c.getState().getId()+"");
				cm.add(c.getContractContent() ==null ? "" :c.getContractContent());
				cm.add(c.getRemark() ==null ? "" :c.getRemark());
				cm.add(c.getDisabled()+"");
				cm.add(c.getCreatedTime() ==null ? "" :format.format(c.getCreatedTime()));
				cm.add(c.getOverGet() ==null ? "" :c.getOverGet());
				cm.add(c.getOverReturnPlan() ==null ? "" :c.getOverReturnPlan());
				cm.add(c.getProject() ==null ? "" :c.getProject().getId()+"");
				cm.add(c.getIsSaved() ==null ? "" :c.getIsSaved());
				cm.add(c.getSubmitNum()+"");
				cm.add(c.getLastSubmitMoney()+"");
				cm.add(c.getTwoCFourF() ==null ? "" :c.getTwoCFourF());
				cm.add(c.getReceipt() ==null ? "" :c.getReceipt());
				cm.add(c.getBack() ==null ? "" :c.getBack());
				cm.add(c.getHasBillSum()+"");
				cm.add(c.getIsExport() ==null ? "" :c.getIsExport());
				cmList.add(cm);
				c.setIsExport("0");
			}
			data.put("cms", cmList);
			data.put("pls", selectProductList(cms));
			data.put("rps", selectReturnPlan(cms));
			data.put("brs", selectBillingRecord(cms));
			data.put("fms", selectFinancialManagement(cms));
			this.contractManagementManager.saveContractManagementfoByImp(cms);
		}
		data.put("pps", selectProjectInfoPlan());//跟合同没关系
		return data;
	}
	
	public List<List<String>> selectProductList(List<ContractManagement > cms ) throws DaoException{
		List<List<String>> plList =new ArrayList<List<String>>();
		String[] headers = {"CONTRACTMANAGEMENT","PRODUCT","UNIT","COUNT","UNITPRICE","DISCOUNT","TOTALPRICE",
				"REMART","DISABLED","CREATED_TIME","LAST_MODIFIED_TIME","plannedDeliveryDate","qualityControl","deliveryedCount","isExport"};
		List<String> pltop = Arrays.asList(headers);
		plList.add(pltop);
		for (int i = 0; i < cms.size(); i++) {
			List<ProductList> pls = new ArrayList<ProductList>();
			pls.addAll(this.productListManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"1"}));
			pls.addAll(this.productListManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"2"}));
			if (pls != null && pls.size() >0) {
				for (int j = 0; j < pls.size(); j++) {
					List<String> pl = new ArrayList<String>();
					ProductList p = pls.get(j);
					pl.add( p.getContractManagement() ==null ? "" :p.getContractManagement().getCode());
					pl.add( p.getProduct() ==null ? "" :p.getProduct().getId()+"");
					pl.add( p.getUnit() ==null ? "" :p.getUnit().getId()+"");
					pl.add( p.getCount()+"");
					pl.add( p.getUnitPrice()+"");
					pl.add( p.getDiscount()+"");
					pl.add( p.getTotalPrice()+"");
					pl.add(p.getRemark() ==null ? "" : p.getRemark().replaceAll("[\\t\\n\\r]",""));
					pl.add( p.getDisabled()+"");
					pl.add(p.getCreatedTime() ==null ? "" : format.format(p.getCreatedTime()));
					pl.add(p.getLastModifiedTime() ==null ? "" : format.format(p.getLastModifiedTime()));
					pl.add(p.getPlannedDeliveryDate() ==null ? "" : format.format(p.getPlannedDeliveryDate()));
					pl.add(p.getQualityControl() ==null ? "" : p.getQualityControl().getId()+"");
					pl.add( p.getDeliveryedCount()+"");
					pl.add(p.getIsExport() ==null ? "" :p.getIsExport());
					plList.add(pl);
					p.setIsExport("0");
					this.productListManager.storeProductList(p);
				}
			}
		}
		return plList;
	}
	
	public List<List<String>> selectReturnPlan(List<ContractManagement > cms ) throws DaoException{
		List<List<String>> rpList =new ArrayList<List<String>>();
		String[] headers = {"PAYEE_ID","CUSTOMERINFO_ID","CONTACT_ID","CONTRACT_MANAGEMENT_ID","PAYTIME","PLAN_DATE","PAYMENT","BATCH","SUM","FACT_SUM","IS_OR_NOT","NOT_OR_NOT"
				,"BILLING_OR_IS","DISABLED","CREATED_TIME","LAST_MODIFIED_TIME","REMARK","CHARGMAN_ID","ISBILL","PERCENTT","planState","BILLMONEY","BILLDATE","isExport"};
		List<String> rptop = Arrays.asList(headers); 
		rpList.add(rptop);
		for (int i = 0; i < cms.size(); i++) {
			List<ReturnPlan> rps = new ArrayList<ReturnPlan>();
			rps.addAll(this.returnPlanManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"1"}));
			rps.addAll(this.returnPlanManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"2"}));
			if(rps != null && rps.size()>0){
				for (int j = 0; j < rps.size(); j++) {
					List<String> rp = new ArrayList<String>();
					ReturnPlan r = rps.get(j);
					rp.add( r.getPayee() ==null ? "" :r.getPayee().getId()+"");
					rp.add( r.getCustomerInfo() ==null ? "" :r.getCustomerInfo().getId()+"");
					rp.add( r.getContactArchives() ==null ? "" :r.getContactArchives().getId()+"");
					rp.add( r.getContractManagement() ==null ? "" :r.getContractManagement().getCode());
					rp.add(r.getPaytime() ==null ? "" : format.format(r.getPaytime()));
					rp.add(r.getPlanDate() ==null ? "" : format.format(r.getPlanDate()));
					rp.add( r.getPayment() ==null ? "" : r.getPayment().getId()+"");
					rp.add( r.getBatch() ==null ? "" : r.getBatch().getId()+"");
					rp.add( r.getSum() ==null ? "" : r.getSum().doubleValue() +"");
					rp.add( r.getFactSum() ==null ? "" : r.getFactSum().doubleValue() +"");
					rp.add( r.getIsOrNot() ==null ? "" : r.getIsOrNot());
					rp.add( r.getNotOrIs() ==null ? "" : r.getNotOrIs());
					rp.add( r.getBillingOrNot() ==null ? "" : r.getBillingOrNot());
					rp.add( r.getDisabled()+"");
					rp.add(r.getCreatedTime() ==null ? "" : format.format(r.getCreatedTime()));
					rp.add(r.getLastModifiedTime() ==null ? "" : format.format(r.getLastModifiedTime()));
					rp.add(r.getRemark() ==null ? "" : r.getRemark().replaceAll("[\\t\\n\\r]",""));
					rp.add( r.getChargMan() ==null ? "" : r.getChargMan().getId()+"");
					rp.add( r.getIsBill() ==null ? "" : r.getIsBill());
					rp.add( r.getPercentt()+"");
					rp.add( r.getPlanState() ==null ? "" : r.getPlanState().getId()+"");
					rp.add( r.getBillMoney() ==null ? "" : r.getBillMoney().doubleValue() +"");
					rp.add(r.getBillDate() ==null ? "" : format.format(r.getBillDate()));
					rp.add(r.getIsExport() ==null ? "" :r.getIsExport());
					rpList.add(rp);
					r.setIsExport("0");
					this.returnPlanManager.storeReturnPlan(r);
				}
			}
		}
		return rpList;
	}
	
	public List<List<String>> selectBillingRecord(List<ContractManagement > cms ) throws DaoException{
		List<List<String>> brList =new ArrayList<List<String>>();
		String[] headers = {"PAYEE_ID","CUSTOMERINFO_ID","CONTRACT_MANAGEMENT_ID","CODE","INVOICE_TITLE","BILLING_TIME","SUM",
				"CONTENT","DISABLED","CREATED_TIME","LAST_MODIFIED_TIME","CONTACT_ID","HASBILLSUM","PLANSUM","RESTSUM",
				"BATCH","isSaved","submitNum","lastSubmitMoney","ISPAY","PAYCODE","MYCODE","isExport"};
		List<String> brtop = Arrays.asList(headers); 
		brList.add(brtop);
		for (int i = 0; i < cms.size(); i++) {
			List<BillingRecord> brs = new ArrayList<BillingRecord>();
			brs.addAll(this.billingRecordManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"1"}));
			brs.addAll(this.billingRecordManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"2"}));
			if(brs != null && brs.size()>0){
				for (int j = 0; j < brs.size(); j++) {
					BillingRecord b = brs.get(j);
					List<String> br = new ArrayList<String>();
					br.add( b.getPayee() ==null ? "" :b.getPayee().getId()+"");
					br.add( b.getCustomerInfo() ==null ? "" :b.getCustomerInfo().getId()+"");
					br.add( b.getContractManagement() ==null ? "" :b.getContractManagement().getCode());
					br.add( b.getCode() ==null ? "" : b.getCode());
					br.add( b.getInvoiceTitle() ==null ? "" : b.getInvoiceTitle());
					br.add(b.getBillingTime() ==null ? "" : format.format(b.getBillingTime()));
					br.add( b.getSum() ==null ? "" : b.getSum().doubleValue() +"");
					br.add(b.getContent() ==null ? "" : b.getContent().replaceAll("[\\t\\n\\r]",""));
					br.add( b.getDisabled()+"");
					br.add(b.getCreatedTime() ==null ? "" : format.format(b.getCreatedTime()));
					br.add(b.getLastModifiedTime() ==null ? "" : format.format(b.getLastModifiedTime()));
					br.add( b.getContactArchives() ==null ? "" :b.getContactArchives().getId()+"");
					br.add( b.getHasBillSum() ==null ? "" : b.getHasBillSum().doubleValue() +"");
					br.add( b.getPlanSum() ==null ? "" : b.getPlanSum().doubleValue() +"");
					br.add( b.getRestSum() ==null ? "" : b.getRestSum().doubleValue() +"");
					br.add( b.getBatch() ==null ? "" : b.getBatch().getId()+"");
					br.add(b.getIsSaved() ==null ? "" :b.getIsSaved());
					br.add( b.getSubmitNum() +"");         
					br.add( b.getLastSubmitMoney() +"");
					br.add( b.getIsPay() ==null ? "" : b.getIsPay());
					br.add( b.getPayCode() ==null ? "" : b.getPayCode());
					br.add( b.getMyCode() ==null ? "" : b.getMyCode());
					br.add(b.getIsExport() ==null ? "" :b.getIsExport());
					brList.add(br);
					b.setIsExport("0");
					this.billingRecordManager.storeBillingRecord(b);
				}
			}
		}
		return brList;
	}
	
	public List<List<String>> selectFinancialManagement(List<ContractManagement > cms ) throws DaoException{
		List<List<String>> fmList =new ArrayList<List<String>>();
		String[] headers = {"CODE","CONTRACT_MANAGEMENT_ID","CUSTOMERINFO_ID","SALEMAN_ID","COLLECTION_TYPE","PAYMENT",
				"ACCOUNT_NUMBER","BATCH","SUM_RECEIVABLE","TRUE_SUM","TOTAL_SUM","WITHOUT_GO_SUM","INVOICE","INVOICE_CODE",
				"COLLECTION_DATE","PAYEE_ID","DEPT_ID","REMARK","DISABLED","CREATED_TIME","LAST_MODIFIED_TIME","ACCOUNT_NAME",
				"isSaved","PAY_NUMBER","submitNum","lastSubmitMoney","isExport"};
		List<String> fmtop = Arrays.asList(headers); 
		fmList.add(fmtop);
		for (int i = 0; i < cms.size(); i++) {
			List<FinancialManagement> fms = new ArrayList<FinancialManagement>();
			fms.addAll(this.financialManagementManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"1"}));
			fms.addAll(this.financialManagementManager.loadByKeyArray(new String[]{"contractManagement.id","isExport"}, new Object[]{cms.get(i).getId(),"2"}));
			if(fms != null && fms.size()>0){
				for (int j = 0; j < fms.size(); j++) {
					List<String> fm = new ArrayList<String>();
					FinancialManagement f = fms.get(j);
					fm.add( f.getCode() ==null ? "" : f.getCode());
					fm.add( f.getContractManagement() ==null ? "" :f.getContractManagement().getCode());
					fm.add( f.getCustomerInfo() ==null ? "" :f.getCustomerInfo().getId()+"");
					fm.add( f.getSaleman() ==null ? "" :f.getSaleman().getId()+"");
					fm.add( f.getCollectionType() ==null ? "" :f.getCollectionType().getId()+"");
					fm.add( f.getPayment() ==null ? "" :f.getPayment().getId()+"");
					fm.add( f.getAccountNumber() ==null ? "" : f.getAccountNumber());
					fm.add( f.getBatch() ==null ? "" : f.getBatch().getId()+"");
					fm.add( f.getSumReceivable() ==null ? "" : f.getSumReceivable().doubleValue() +"");
					fm.add( f.getTrueSum() ==null ? "" : f.getTrueSum().doubleValue() +"");
					fm.add( f.getTotalSum() ==null ? "" : f.getTotalSum().doubleValue() +"");
					fm.add( f.getWithoutGotSum() ==null ? "" : f.getWithoutGotSum().doubleValue() +"");
					fm.add( f.getInvoice() ==null ? "" : f.getInvoice());
					fm.add( f.getInvoiceCode() ==null ? "" : f.getInvoiceCode());
					fm.add(f.getCollectionDate() ==null ? "" : format.format(f.getCollectionDate()));
					fm.add( f.getPayee() ==null ? "" :f.getPayee().getId()+"");
					fm.add( f.getDept() ==null ? "" :f.getDept().getId()+"");
					fm.add(f.getRemark() ==null ? "" : f.getRemark().replaceAll("[\\t\\n\\r]",""));
					fm.add( f.getDisabled()+"");
					fm.add(f.getCreatedTime() ==null ? "" : format.format(f.getCreatedTime()));
					fm.add(f.getLastModifiedTime() ==null ? "" : format.format(f.getLastModifiedTime()));
					fm.add( f.getAccountName() ==null ? "" : f.getAccountName());
					fm.add(f.getIsSaved() ==null ? "" :f.getIsSaved());
					fm.add( f.getPayNumber() ==null ? "" : f.getPayNumber());
					fm.add( f.getSubmitNum() +"");         
					fm.add( f.getLastSubmitMoney() +"");
					fm.add(f.getIsExport() ==null ? "" :f.getIsExport());
					fmList.add(fm);
					f.setIsExport("0");
					this.financialManagementManager.storeFinancialManagement(f);
				}
			}
		}
		return fmList;
	}
	
	
	public List<List<String>> selectProjectInfoPlan() throws DaoException{
		List<List<String>> ppList =new ArrayList<List<String>>();
		String[] headers = {"outline","name","personnelFiles_id","assist","start_Date","end_Date","DISABLED","CREATED_TIME","LAST_MODIFIED_TIME",
				"PERCENTT","PRIORITY","contractManagement_id","planState","isSaved","startFact_Date","endFact_Date","assistIds","PROJECTINFOPLAN_ID",
				"ORDERNUMBER","productionOperationDetail","productionPoint","isExport"};
		List<String> pptop = Arrays.asList(headers); 
		ppList.add(pptop);
		List<ProjectInfoPlan> pps = new ArrayList<ProjectInfoPlan>();
		pps.addAll(this.projectInfoPlanManager.loadByKey("isExport","1"));
		pps.addAll(this.projectInfoPlanManager.loadByKey("isExport","2"));
		if(pps != null && pps.size()>0){
			for (int j = 0; j < pps.size(); j++) {
				List<String> pp = new ArrayList<String>();
				ProjectInfoPlan p = pps.get(j);
				pp.add(p.getOutline() ==null ? "" : p.getOutline().replaceAll("[\\t\\n\\r]",""));
				pp.add( p.getName() ==null ? "" : p.getName());
				pp.add( p.getPersonnelFiles() ==null ? "" :p.getPersonnelFiles().getId()+"");
				pp.add( p.getAssist() ==null ? "" : p.getAssist());
				pp.add(p.getStartDate() ==null ? "" : format.format(p.getStartDate()));
				pp.add(p.getEndDate() ==null ? "" : format.format(p.getEndDate()));
				pp.add( p.getDisabled()+"");
				pp.add(p.getCreatedTime() ==null ? "" : format.format(p.getCreatedTime()));
				pp.add(p.getLastModifiedTime() ==null ? "" : format.format(p.getLastModifiedTime()));
				pp.add( p.getPercentt() +"");
				pp.add( p.getPriority() +"");
				pp.add( p.getContractManagement() ==null ? "" :p.getContractManagement().getCode());
				pp.add( p.getPlanState() ==null ? "" :p.getPlanState().getId()+"");
				pp.add( p.getIsSaved() ==null ? "" : p.getIsSaved() +"");
				pp.add(p.getStartFactDate() ==null ? "" : format.format(p.getStartFactDate()));
				pp.add(p.getEndFactDate() ==null ? "" : format.format(p.getEndFactDate()));
				pp.add( p.getAssistIds() ==null ? "" : p.getAssistIds() +"");
				pp.add( p.getProjectInfoPlan_2() ==null ? "" :format.format(p.getProjectInfoPlan_2().getCreatedTime()));
				pp.add( p.getOrderNumber() ==null ? "" : p.getOrderNumber().intValue() +"");
				pp.add( p.getProductionOperationDetail() ==null ? "" :p.getProductionOperationDetail().getId()+"");
				pp.add( p.getProductionPoint() ==null ? "" : p.getProductionPoint().getId()+"");
				pp.add(p.getIsExport() ==null ? "" :p.getIsExport());
				ppList.add(pp);
				p.setIsExport("0");
				this.projectInfoPlanManager.storeProjectInfoPlan(p);
			}
		}
		return ppList;
	}
	
	
	public List<Department> getAllDepartment() {
		List depts = this.departmentManager.loadAllDepartments();
		return depts;
	}

	public PersonnelFiles getPersonnelF() throws Exception {
		List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());

		if ((null != pfs) && (pfs.size() > 0)) {
			return (PersonnelFiles) pfs.get(0);
		}
		return null;
	}

	public String getStateDefault() {
		try {
			List list = this.codeValueManager.loadByKey("code", "06603");
			if ((null != list) && (list.size() > 0))
				return ((CodeValue) list.get(0)).getId().toString();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CodeValue> getAllComplaintType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("064"));

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

	public List<CodeValue> getAllPayType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("065"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			List reCodeValues = new ArrayList();
			if (codes.size() >= 1) {
				for (int i = 0; i < codes.size(); i++) {
					reCodeValues.add(codes.get(codes.size() - i - 1));
				}
			}
			return reCodeValues;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<CodeValue> getAllState() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("066"));

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

	public List<CodeValue> getAllMoneyType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("067"));
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

	public String autoCompleteCode() {
		String prefix = "HTGLBM";
		String maxCode = this.contractManagementManager.getMaxPFCode(prefix);
		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return prefix + "-00000" + num;
			if (num < 100)
				return prefix + "-0000" + num;
			if (num < 1000)
				return prefix + "-000" + num;
			if (num < 10000)
				return prefix + "-00" + num;
			if (num < 100000) {
				return prefix + "-0" + num;
			}
			return prefix + "-" + num;
		}
		return prefix + "-000001";
	}

	public List<Institution> getAllInsts() {
		List list = this.institutionManager.loadAllInstitution();
		return list;
	}

	public List<Department> getAllDepts() {
		List list = new ArrayList();
		return list;
	}

	public List<CodeValue> getAllPayWay() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));

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

	public User getUser() {
		return this.userManager.getUser();
	}

	public ContractManagement getContractManagement() {
		return this.contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ProjectInfoManager getProjectInfoManager() {
		return projectInfoManager;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
	

}
