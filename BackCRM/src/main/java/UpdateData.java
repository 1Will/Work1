package main.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.pojo.BackVisit;
import main.pojo.BillingRecord;
import main.pojo.ContractManagement;
import main.pojo.Daily;
import main.pojo.EventNew;
import main.pojo.FinancialManagement;
import main.pojo.PersonnelFiles;
import main.pojo.ProjectInfo;
import main.pojo.UsersInfo;
import main.pojo.Weekly;
import main.service.BackVisitService;
import main.service.BillingRecordService;
import main.service.ContractManagementService;
import main.service.DailyService;
import main.service.DataService;
import main.service.FinancialManagementService;
import main.service.PersonnelFilesService;
import main.service.ProjectInfoService;
import main.service.UsersInfoService;
import main.service.WeeklyService;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UpdateData {

	public ApplicationContext context;
	private static Logger log = Logger.getRootLogger();
	public DataService dataService;
	public UsersInfoService usersInfoService;
	public PersonnelFilesService personnelFilesService;
	public DailyService dailyService;
	public WeeklyService weeklyService;
	public BackVisitService backVisitService;
	public ProjectInfoService projectInfoService;
	public ContractManagementService cManagementService;
	public FinancialManagementService fManagementService;
	public BillingRecordService billingRecordService;

	public UpdateData(ApplicationContext context) {
		this.context = context;
	}

	public Map<String, Object> getMap(EventNew event) {
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();// 得到传递的参数，解析成map
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		return map;
	}

	public HashMap getMapData(Object type, Object thisMoney, Object lastMoney, Object submitNum, Object date) {
		HashMap mapData = new HashMap();
		mapData.put("type", type);
		mapData.put("thisMoney", thisMoney);
		mapData.put("lastMoney", lastMoney);
		mapData.put("submitNum", submitNum);
		mapData.put("date", date);
		return mapData;
	}

	public void init() {
		this.personnelFilesService = (PersonnelFilesService) context.getBean("personnelFilesService");
		this.usersInfoService = (UsersInfoService) context.getBean("usersInfoService");
		this.dataService = (DataService) context.getBean("dataService");
		this.dailyService = (DailyService) context.getBean("dailyService");
		this.weeklyService = (WeeklyService) context.getBean("weeklyService");
		this.backVisitService = (BackVisitService) context.getBean("backVisitService");
		this.projectInfoService = (ProjectInfoService) context.getBean("projectInfoService");
		this.cManagementService = (ContractManagementService) context.getBean("contractManagementService");
		this.fManagementService = (FinancialManagementService) context.getBean("financialManagementService");
		this.billingRecordService = (BillingRecordService) context.getBean("billingRecordService");
	}

	public PersonnelFiles getPersonnelFilesByUserid(String userid) {
		UsersInfo uInfo = usersInfoService.findUsersInfoById(Long.parseLong(userid));
		String code = uInfo.getCode();
		List<PersonnelFiles> pFilesList = personnelFilesService.findPersonnelFilesByCode(code);
		PersonnelFiles personnelFiles = pFilesList.get(0);
		return personnelFiles;
	}

	// 同步更新 日报数据
	public void updateDailyData(EventNew event) {
		log.info("处理日报事件时，同步更新 数据 :");
		init();
		Map<String, Object> map = getMap(event);
		String dailyId = (String) map.get("dailyId");
		Daily daily = dailyService.getDailyById(Long.parseLong(dailyId));
		String userid = daily.getRapporteurId() + "";
		PersonnelFiles personnelFiles = getPersonnelFilesByUserid(userid);
		HashMap mapData = getMapData(event.getEventType().getCode(), "0", "0", daily.getSubmitNum(),
				daily.getCurrentDate());
		dataService.storeData(personnelFiles, mapData);
	}

	// 同步更新 周计划数据
	public void updateWeeklyData(EventNew event) {
		log.info("处理周计划事件时，同步更新 数据 :");
		init();
		Map<String, Object> map = getMap(event);
		String weekId = (String) map.get("weeklyId");
		Weekly weekly = weeklyService.getWeeklyById(Long.parseLong(weekId));
		String userid = weekly.getRapporteurId() + "";
		// 调用方法
		PersonnelFiles personnelFiles = getPersonnelFilesByUserid(userid);
		HashMap mapData = getMapData(event.getEventType().getCode(), "0", "0", weekly.getSubmitNum(),
				weekly.getStartDate());
		this.dataService.storeData(personnelFiles, mapData);
	}

	// 同步更新 回访数据
	public void updateBackvisitData(EventNew event) {
		log.info("处理回访事件时，同步更新 数据 :");
		init();
		Map<String, Object> map = getMap(event);
		String backVisitId = (String) map.get("backVisitId");
		BackVisit backVisit = backVisitService.getBackVisitById(Long.parseLong(backVisitId));
		// 调用方法
		PersonnelFiles personnelFiles = personnelFilesService.getPersonnelFilesById(backVisit.getVisiterid());
		HashMap mapData = getMapData(event.getEventType().getCode(), "0", "0", backVisit.getSubmitNum(),
				backVisit.getVisitDate());
		this.dataService.storeData(personnelFiles, mapData);

	}

	// 同步更新 新建项目 数据
	public void updateProjectInfoData(EventNew event) {
		log.info("处理新建项目事件时，同步更新 数据 :");
		init();
		Map<String, Object> map = getMap(event);
		String projectInfoId = (String) map.get("projectInfoId");
		ProjectInfo projectInfo = projectInfoService.getProjectInfo_ById(Long.parseLong(projectInfoId));
		// 调用方法
		PersonnelFiles personnelFiles = personnelFilesService.getPersonnelFilesById(projectInfo.getControllerId());
		HashMap mapData = getMapData(event.getEventType().getCode(), "0", "0", projectInfo.getSubmitNum(),
				projectInfo.getCreatedTime());
		this.dataService.storeData(personnelFiles, mapData);

	}

	// 新建合同时 同步更新 数据
	public void updateContractManagementData(EventNew event) {
		log.info("处理新建合同事件时，同步更新 数据 :");
		init();
		Map<String, Object> map = getMap(event);
		String contractManagementId = (String) map.get("contractManagementId");
		ContractManagement cManagement = cManagementService.getContractManagementById(Long
				.parseLong(contractManagementId));
		// 调用方法
		PersonnelFiles personnelFiles = cManagement.getSaleman();
		HashMap mapData = getMapData(event.getEventType().getCode(), cManagement.getContractMoney(),
				cManagement.getLastSubmitMoney(), cManagement.getSubmitNum(), cManagement.getCiemdinghTime());
		this.dataService.storeData(personnelFiles, mapData);
		cManagement.setLastSubmitMoney(cManagement.getContractMoney());
		cManagementService.updateContractManagementById(cManagement); // 更新上次提交金额为本次的合同金额

	}

	// 收款事件时 同步更新 数据
	public void updateFinancialManagementData(EventNew event) {
		log.info("处理收款事件时，同步更新 数据 :");
		init();
		Map<String, Object> map = getMap(event);
		String financialManagementId = (String) map.get("financialManagementId");
		FinancialManagement financialManagement = fManagementService.getFinancialManagementById(Long
				.parseLong(financialManagementId));
		// 调用方法
		PersonnelFiles personnelFiles = financialManagement.getPayee();
		HashMap mapData = getMapData(event.getEventType().getCode(), financialManagement.getTrueSum(),
				financialManagement.getLastSubmitMoney(), financialManagement.getSubmitNum(),
				financialManagement.getCollectionDate());
		this.dataService.storeData(personnelFiles, mapData);
		financialManagement.setLastSubmitMoney(financialManagement.getTrueSum());
		fManagementService.updateFinancialManagementById(financialManagement);// 更新上次金额

	}

	// 开票事件时 同步更新 数据
	public void updateBillingRecordData(EventNew event) {
		log.info("处理开票事件时，同步更新 数据 :");
		init();
		Map<String, Object> map = getMap(event);
		String billingRecordId = (String) map.get("billingRecordId");
		BillingRecord billingRecord = billingRecordService.getBillingRecordById(Long.parseLong(billingRecordId));
		// 调用方法
		PersonnelFiles personnelFiles = billingRecord.getPayee();
		HashMap mapData = getMapData(event.getEventType().getCode(), billingRecord.getSum(),
				billingRecord.getLastSubmitMoney(), billingRecord.getSubmitNum(), billingRecord.getBillingTime());
		this.dataService.storeData(personnelFiles, mapData);
		billingRecord.setLastSubmitMoney(billingRecord.getSum());
		billingRecordService.updateBillingRecordById(billingRecord);
	}
}
