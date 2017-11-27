package com.yongjun.tdms.presentation.webwork.action.customercontract.plan;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

import javassist.expr.NewArray;

public class EditReturnPlanAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
	private final ReturnPlanManager returnPlanManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactArchivesManager contactArchivesManager;
	private final ContractManagementManager contractManagementManager;
	private final ProjectInfoManager projectInfoManager;
	private ReturnPlan returnPlan;
	private ProjectInfo projectInfo;
	private ContractManagement contractManagement;
	private String popWindowFlag;
	private Properties systemParameterConfiguration;
	private int num=0;//批次 
	public DecimalFormat format = new DecimalFormat("0.00");
	public EditReturnPlanAction(ReturnPlanManager returnPlanManager, CodeValueManager codeValueManager,
			PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager,
			ContactArchivesManager contactArchivesManager, ContractManagementManager contractManagementManager,
			ProjectInfoManager projectInfoManager) {
		this.returnPlanManager = returnPlanManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contactArchivesManager = contactArchivesManager;
		this.contractManagementManager = contractManagementManager;
		this.projectInfoManager = projectInfoManager;
	}

	public void prepare() throws Exception {
		if (hasId("returnPlan.id")) {
			this.returnPlan = this.returnPlanManager.loadReturnPlan(getId("returnPlan.id"));
		} else {
			this.returnPlan = new ReturnPlan();
		}
		if (this.request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = this.request.getParameter("popWindowFlag");
		}

		if (hasId("contractManagement.id")) {
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			if (null != this.contractManagement) {
				this.returnPlan.setContractManagement(contractManagement);
				this.returnPlan.setCustomerInfo(this.contractManagement.getCustomerInfo());
				this.returnPlan.setContactArchives(this.contractManagement.getLinkman());
				this.returnPlan.setPhone(this.contractManagement.getTelephone());
			}
		}
	}

	public String save() throws Exception {
		boolean isNew = this.returnPlan.isNew();

		if (hasId("payment.id")) {
			this.returnPlan.setPayment(this.codeValueManager.loadCodeValue(getId("payment.id")));
		}

		if (hasId("batch.id")) {
			this.returnPlan.setBatch(this.codeValueManager.loadCodeValue(getId("batch.id")));
		}

		if (hasId("customer.id")) {
			CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));

			if (null != customer) {
				this.returnPlan.setCustomerInfo(customer);
			}
		}

		if (hasId("chargMan.id")) {
			this.returnPlan.setChargMan(this.personnelFilesManager.loadPersonnel(getId("chargMan.id")));
		}

		if (hasId("payee.id")) {
			PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(getId("payee.id"));

			if (null != payee) {
				this.returnPlan.setPayee(payee);
			}

		}

		if (hasId("contactArchives.id")) {
			ContactArchives linkman = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));

			if (null != linkman) {
				this.returnPlan.setContactArchives(linkman);
			}
		}

		if (hasId("planState.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("planState.id"));
			if (null != cv) {
				this.returnPlan.setPlanState(cv);
			}
		}
		if(hasId("mold.id")){
			CodeValue mold = this.codeValueManager.loadCodeValue(getId("mold.id"));
			if (null != mold) {
				this.returnPlan.setMold(mold);
			}
		}
		this.returnPlan.setIsBill("1");
		this.returnPlan.setIsOrNot("1");

		if (hasId("notOrIs")) {
			String notOrIs = this.request.getParameter("notOrIs");
			this.returnPlan.setNotOrIs(notOrIs);
		}

		if (hasId("billingOrNot")) {
			String billingOrNot = this.request.getParameter("billingOrNot");
			this.returnPlan.setBillingOrNot(billingOrNot);
		}
		if (StringUtils.isEmpty(this.request.getParameter("returnPlan.remark"))) {
			String remark = this.request.getParameter("returnPlan.remark");
			this.returnPlan.setRemark(remark);
		}
		try {
			this.returnPlanManager.storeReturnPlan(this.returnPlan);
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("returnPlan.save.error"));
				return ERROR;
			}
			addActionMessage(getText("returnPlan.edit.error"));
			return ERROR;
		}
		// 收款计划完成后，项目状态改为付费
		// this.projectInfo =
		// this.returnPlan.getContractManagement().getProject();
		// this.projectInfo.setState(this.codeValueManager.loadByKey("code",
		// "20103").get(0));
		// this.projectInfoManager.storeProjectInfo(this.projectInfo);
		List<ReturnPlan> lists = this.returnPlanManager.loadAllReturnPlans();
		Double sum = Double.valueOf(0.0D);
		if (null != lists) {
			for (ReturnPlan p : lists) {
				sum = Double.valueOf(format.format(sum.doubleValue() + p.getSum().doubleValue()));
			}
		}
		if (!hasId("contractManagement.id")) {
			this.contractManagement = this.returnPlan.getContractManagement();
		}
		if ((null != contractManagement) && (sum.doubleValue() == contractManagement.getContractMoney())) {
			contractManagement.setOverReturnPlan("yes");
			this.contractManagementManager.storeContractManagement(contractManagement);
		}

		if (isNew) {
			addActionMessage(getText("returnPlan.save.success"));
			return NEW;
		}
		addActionMessage(getText("returnPlan.edit.success"));
		return SUCCESS;
	}

	public List<CodeValue> getAllBatchs() {
		List<CodeValue> codes =  new ArrayList<CodeValue>();
		try {
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("03330"));
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			if (this.contractManagement != null) {
				List<ReturnPlan> returnPlans = this.returnPlanManager.loadByKey("contractManagement.id",
						this.contractManagement.getId());
				if (returnPlans != null && returnPlans.size() > 0) {
					for (int j = codes.size() - 1; j >= 0; j--) {
						for (int i = 0; i < returnPlans.size(); i++) {
							if (codes.get(j).getId() == returnPlans.get(i).getBatch().getId()) {
								codes.remove(j);
							}
						}

					}
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<CodeValue> getAllPlanState() {
		try {
			List codes = new ArrayList();
			String[] keys = { "name", "code" };
			String[] values = { "收款计划状态", "213" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code",
																			// Long.valueOf("211"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllPayments() {
		List<CodeValue> codes = null;
		try {
			codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("046"));

			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

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

	/*//根据房源明细生成收款计划
		@SuppressWarnings("unchecked")
		public int autoReturnPlan(Long cmId) {
			int size_row=0;//房间时间跨度最大一个（长度）
			int num=0;//批次 
			List<HouseList> houseLists = this.contractManagementManager.loadHouseListByConId(cmId);
			if(houseLists!=null&&houseLists.size()>0){
				List <List>size_col=new ArrayList<List>();
				for(int i=0;i<houseLists.size();i++){
					List<ReturnPlan> returnPlans=new ArrayList<ReturnPlan>();
					HouseList houseList=houseLists.get(i);
					Date start = houseList.getStartTime();
					Date end = houseList.getEndTime();
					Double contractMoney =houseList.getSum();
					DecimalFormat format = new DecimalFormat("0.00");
					List<CodeValue> payments = getAllPayments();
					List<CodeValue> planStates = getAllPlanState();
					List<Date> dates =null;
					try {
						dates = splitDates(start,end);
						if(dates.size()>size_row){
							size_row=dates.size();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(dates!=null && dates.size()>0){
						Double money = Double.valueOf(format.format(contractMoney/dates.size()));
						for(int j=0;j<dates.size();j++){
							ReturnPlan r = new ReturnPlan();
							r.setContractManagement(houseList.getContractManagement());
							r.setCustomerInfo(houseList.getContractManagement().getCustomerInfo());
							r.setContactArchives(houseList.getContractManagement().getLinkman());
							r.setSum(money);
							r.setPlanDate(dates.get(j));
							r.setPayment(payments.get(0));
							r.setPlanState(planStates.get(0));
							r.setDisabled(false);
							r.setIsBill("1");
							r.setIsOrNot("1");
							r.setBillingOrNot("0");
							r.setHouse(houseList.getHouse());
							returnPlans.add(r);
						}
						
					}
					size_col.add(returnPlans);	
				}
				List<CodeValue> batchs = getAllBatchs();
				for(int i=0;i<size_row;i++){
					for(int j=size_col.size()-1;j>=0;j--){
						List<ReturnPlan> rps=size_col.get(j);
						if(rps!=null&&rps.size()>0){
							ReturnPlan returnPlan=rps.get(0);
							returnPlan.setBatch(batchs.get(num));
							num++;
							returnPlanManager.storeReturnPlan(returnPlan);
							rps.remove(0);
						}
					}
				}
				return 1;
			}
			return 0;
		}*/
	//根据房源明细生成收款计划
	public int autoReturnPlan(Long cmId) {
		List<HouseList> houseLists = this.contractManagementManager.loadHouseListByConId(cmId);
		//判断收款计划类型(年、半年、季度)
		int mons=0;
		ContractManagement contractManagement=this.contractManagementManager.loadContractManagement(cmId);
		CodeValue codeValue=contractManagement.getPayType();
		if(codeValue!=null){
			if("06502".equals(codeValue.getCode())){
	        	mons=12;
	        }else if("06501".equals(codeValue.getCode())){
	        	mons=6;
	        }else {
	        	mons=3;
	        }
		}else{
        	mons=6;
		}
        //收款类型
		CodeValue mold=null;
		try {
			 mold=this.codeValueManager.loadByKey("code", "23701").get(0);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
		List<ReturnPlan> returnPlans=new ArrayList<ReturnPlan>();//所有回款计划集合
		if(houseLists!=null&&houseLists.size()>0){
			for(int i=0;i<houseLists.size();i++){
				HouseList houseList=houseLists.get(i);
				Date start = houseList.getStartTime();
				Date end = houseList.getEndTime();
				Double contractMoney =houseList.getSum();
				List<CodeValue> payments = getAllPayments();
				List<CodeValue> planStates = getAllPlanState();
				List<Date> dates =null;
				try {
					dates = splitDates(start,end,mons);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(dates!=null && dates.size()>0){
					Double money = Double.valueOf(format.format(contractMoney/dates.size()));
					for(int j=0;j<dates.size();j++){
						ReturnPlan r = new ReturnPlan();
						r.setMold(mold);
						r.setContractManagement(houseList.getContractManagement());
						r.setCustomerInfo(houseList.getContractManagement().getCustomerInfo());
						r.setContactArchives(houseList.getContractManagement().getLinkman());
						r.setSum(money);
						r.setPlanDate(dates.get(j));
						r.setPayment(payments.get(0));
						r.setPlanState(planStates.get(0));
						r.setDisabled(false);
						r.setIsBill("1");
						r.setIsOrNot("1");
						r.setBillingOrNot("0");
						r.setHouse(houseList.getHouse().getCode());
						returnPlans.add(r);
					}
					
				}
			}
			findFirtReturnPlan(returnPlans);
			return 1;
		}
		return 0;
	}
	//遍历找出集合中startTime最早的集合
	public void findFirtReturnPlan(List<ReturnPlan>returnPlans){
		ReturnPlan returnPlan=null;
		if(returnPlans!=null && returnPlans.size()>0){
			returnPlan=returnPlans.get(0);
			for(ReturnPlan r:returnPlans){
				if(r.getPlanDate().before(returnPlan.getPlanDate())){
					returnPlan=r;
				}
			}
		}
		save_returnPlan(returnPlans , returnPlan);
	}
	//遍历找出集合中时间等于某值的集合（取出金额并从集合中移除）
	public  void save_returnPlan(List<ReturnPlan>returnPlans,ReturnPlan returnPlan_now){
		if(returnPlans!=null && returnPlans.size()>0){
			double money=0.0;
			String house="";
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
			String dateString_now=f.format(returnPlan_now.getPlanDate());
			Iterator<ReturnPlan> it=returnPlans.iterator();
			while(it.hasNext()){
				ReturnPlan r_1=it.next();
				String date_string =f.format(r_1.getPlanDate());
				if(dateString_now.equals(date_string)){
					if("".equals(house)){
						house+=r_1.getHouse();
					}else{
						house+=","+r_1.getHouse();
					}
					money+=r_1.getSum();
					it.remove();
				}
			}
			returnPlan_now.setSum(money);
			returnPlan_now.setHouse(house);
			List<CodeValue> batchs = getAllBatchs();
			returnPlan_now.setBatch(batchs.get(num));
			num++;
			returnPlanManager.storeReturnPlan(returnPlan_now);
			if(returnPlans!=null && returnPlans.size()>0){
				findFirtReturnPlan(returnPlans);
			}
		}	
	}
	
	
//	生成所有收款计划
	public void autoAllReturnPlan(){
		try {
			List<ContractManagement> contractManagements=this.contractManagementManager.loadByKey("disabled", 0);
			System.out.println(contractManagements.size());
			for(ContractManagement con :contractManagements){
				List<ReturnPlan> returnPlans=this.returnPlanManager.loadByKey("contractManagement.id",con.getId());
				if(!(returnPlans!=null && returnPlans.size()>0)){
					autoReturnPlan(con.getId());
					 num=0;
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Date> splitDates(Date dBegin, Date dEnd,int mon) throws Exception {
		List<Date> listDate = new ArrayList<Date>();
		listDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (calEnd.after(calBegin)) {
			calBegin.add(Calendar.MONTH, mon);
			if (calEnd.after(calBegin)) {
				listDate.add(calBegin.getTime());
			}
		}
		return listDate;
	}

	public void setReturnPlan(ReturnPlan returnPlan) {
		this.returnPlan = returnPlan;
	}

	public ReturnPlan getReturnPlan() {
		return this.returnPlan;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}

	public Properties getSystemParameterConfiguration() {
		return systemParameterConfiguration;
	}

	public void setSystemParameterConfiguration(Properties systemParameterConfiguration) {
		this.systemParameterConfiguration = systemParameterConfiguration;
	}
	public List getAllMold() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "237").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
}
