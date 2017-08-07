/*     */ package com.yongjun.tdms.presentation.webwork.action;
/*     */ 
/*     */ import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.BaseAction;
/*     */ import com.yongjun.tdms.model.base.document.ApplicationDoc;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.notice.NoticeUtil;
/*     */ import com.yongjun.tdms.model.notice.ReceviceNotice;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
/*     */ import com.yongjun.tdms.model.task.Task;
/*     */ import com.yongjun.tdms.model.workReport.daily.Daily;
import com.yongjun.tdms.model.workspace.data.Data;
/*     */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
/*     */ import com.yongjun.tdms.service.base.document.ApplicationDocManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
/*     */ import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
/*     */ import com.yongjun.tdms.service.task.TaskManager;
/*     */ import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.tdms.service.workspace.data.DataManager;
/*     */ import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
/*     */ import java.util.List;

/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class FramesetAction extends BaseAction
/*     */ {
/*     */   private static final long serialVersionUID = -2286308339990443246L;
/*  47 */   private final Log logger = LogFactory.getLog(getClass());
/*     */   private final UserManager userManager;
/*     */   private final ApplicationDocManager applicationDocManager;
/*     */   private final WorkWarnningManager workWarnningManager;
/*     */   private final ReceviceNoticeManager receviceNoticeManager;
/*     */   private final DailyManager dailyManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private final TaskManager taskManager;
			private final FinancialManagementManager financialManagementManager;
			private final ProjectInfoPlanManager projectInfoPlanManager;
			private final DataManager dataManager;
/*     */   private User user;
/*     */   private NoticeUtil noticeUtil;
/*     */ 
/*     */   public FramesetAction(UserManager userManager, ApplicationDocManager applicationDocManager, WorkWarnningManager workWarnningManager, ReceviceNoticeManager receviceNoticeManager, ContractManagementManager contractManagementManager, TaskManager taskManager, DailyManager dailyManager, PersonnelFilesManager personnelFilesManager,FinancialManagementManager financialManagementManager,ProjectInfoPlanManager projectInfoPlanManager
		,DataManager dataManager)
/*     */   {
/*  80 */     this.userManager = userManager;
/*  81 */     this.applicationDocManager = applicationDocManager;
/*  82 */     this.workWarnningManager = workWarnningManager;
/*  83 */     this.receviceNoticeManager = receviceNoticeManager;
/*  84 */     this.contractManagementManager = contractManagementManager;
/*  85 */     this.taskManager = taskManager;
/*  86 */     this.dailyManager = dailyManager;
/*  87 */     this.personnelFilesManager = personnelFilesManager;
              this.financialManagementManager =financialManagementManager;
              this.projectInfoPlanManager=projectInfoPlanManager;
              this.dataManager = dataManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception {
/*  91 */     if (null != this.userManager.getUser()) {
/*  92 */       this.logger.info("登录用户名：" + this.userManager.getUser().getName());
/*  93 */       this.user = this.userManager.getUser();
/*     */     }
/*  95 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<ApplicationDoc> getManualDocs()
/*     */   {
/* 103 */     return this.applicationDocManager.getAllManualDoc();
/*     */   }
/*     */ 
/*     */   public List<ReceviceNotice> getReceiveNoticeList(Long userId, String readStaus, Boolean disabled, String noticeType)
/*     */   {
/* 115 */     List receiveNoticeList = new ArrayList();
/*     */     try {
/* 117 */       String[] keyNames = new String[4];
/* 118 */       Object[] keyValues = new Object[4];
/* 119 */       keyNames[0] = "receviceUser.id";
/* 120 */       keyNames[1] = "readStatus";
/* 121 */       keyNames[2] = "disabled";
/* 122 */       keyNames[3] = "noticeType.name";
/*     */ 
/* 124 */       keyValues[0] = userId;
/* 125 */       keyValues[1] = String.valueOf(readStaus);
/* 126 */       keyValues[2] = disabled;
/* 127 */       keyValues[3] = String.valueOf(noticeType);
/*     */ 
/* 129 */       receiveNoticeList = this.receviceNoticeManager.loadByKeyArray(keyNames, keyValues);
/*     */     } catch (Exception e) {
/* 131 */       this.logger.info("查询未读通知有错！");
/*     */     }
/* 133 */     if ((null != receiveNoticeList) && (!receiveNoticeList.isEmpty())) {
/* 134 */       return receiveNoticeList;
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */   public long getReceiveNoticeSize(Long userId, String readStaus, Boolean disabled, String noticeType)
/*     */   {
/* 146 */     List receiveNoticeList = getReceiveNoticeList(userId, readStaus, disabled, noticeType);
/*     */ 
/* 148 */     if ((null != receiveNoticeList) && (!receiveNoticeList.isEmpty())) {
/* 149 */       return Long.valueOf(receiveNoticeList.size()).longValue();
/*     */     }
/* 151 */     return 0L;
/*     */   }
/*     */ 
/*     */   public List<ReceviceNotice> getNoticeList()
/*     */   {
/* 160 */     Long userId = getLoginUser().getId();
/* 161 */     List noticeList = new ArrayList();
/*     */ 
/* 163 */     List unReadNoticeList = getReceiveNoticeList(userId, "UNREAD", Boolean.valueOf(false), "新闻");
/*     */ 
/* 166 */     List readNoticeList = getReceiveNoticeList(userId, "READED", Boolean.valueOf(false), "新闻");
/*     */ 
/* 169 */     List unReadAnnounceList = getReceiveNoticeList(userId, "UNREAD", Boolean.valueOf(false), "公告");
/*     */ 
/* 172 */     List readAnnounceList = getReceiveNoticeList(userId, "READED", Boolean.valueOf(false), "公告");
/*     */ 
/* 175 */     if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
/* 176 */       noticeList.addAll(unReadNoticeList);
/*     */     }
/* 178 */     if ((null != readNoticeList) && (!readNoticeList.isEmpty())) {
/* 179 */       noticeList.addAll(readNoticeList);
/*     */     }
/* 181 */     if ((null != unReadAnnounceList) && (!unReadAnnounceList.isEmpty())) {
/* 182 */       noticeList.addAll(unReadAnnounceList);
/*     */     }
/* 184 */     if ((null != readAnnounceList) && (!readAnnounceList.isEmpty())) {
/* 185 */       noticeList.addAll(readAnnounceList);
/*     */     }
/* 187 */     if ((null != noticeList) && (!noticeList.isEmpty())) {
/* 188 */       return noticeList;
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */   public long getUnReadNoticeSize()
/*     */   {
/* 199 */     long userId = getLoginUser().getId().longValue();
/* 200 */     long unReadNum = getReceiveNoticeSize(Long.valueOf(userId), "UNREAD", Boolean.valueOf(false), "通知") + getReceiveNoticeSize(Long.valueOf(userId), "UNREAD", Boolean.valueOf(false), "公告")+getReceiveNoticeSize(Long.valueOf(userId), "UNREAD", Boolean.valueOf(false), "新闻") ;
/*     */ 
/* 202 */     if ((0L == unReadNum) || (unReadNum < 1L)) {
/* 203 */       return 0L;
/*     */     }
/* 205 */     return unReadNum;
/*     */   }
/*     */ 
/*     */   public Integer getNumberOfUnRead()
/*     */   {
/* 214 */     Integer number = Integer.valueOf(0);
/* 215 */     if (null != this.userManager.getUser()) {
/* 216 */       number = this.workWarnningManager.GetNumberOfUnReadWarnningByUserID(this.userManager.getUser().getId());
/*     */     }
/* 218 */     return number;
/*     */   }
/*     */ 
/*     */   public List<WorkWarnning> getWorkWarnningList()
/*     */   {
/* 225 */     Long userId = getLoginUser().getId();
/* 226 */     List unReadWorkWarnningList = new ArrayList();
/*     */     try {
/* 228 */       String[] keyNames = new String[2];
/* 229 */       Object[] keyValues = new Object[2];
/* 230 */       keyNames[0] = "warnedPeople.id";
/*     */ 
/* 232 */       keyNames[1] = "readFlag";
/*     */ 
/* 234 */       keyValues[0] = userId;
/*     */ 
/* 236 */       keyValues[1] = Boolean.valueOf(false);
/*     */ 
/* 238 */       unReadWorkWarnningList = this.workWarnningManager.loadByKeyArray(keyNames, keyValues);
/*     */     } catch (Exception e) {
/* 240 */       this.logger.info("查询当前登录人未读提醒有错！");
/*     */     }
/* 242 */     if ((null != unReadWorkWarnningList) && (!unReadWorkWarnningList.isEmpty())) {
/* 243 */       return unReadWorkWarnningList;
/*     */     }
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */   public List<ContractManagement> getNewSignings()
/*     */   {
/* 254 */     List conMList = new ArrayList();
/* 255 */     conMList = this.contractManagementManager.loadContractManagement();
/* 256 */     return conMList;
/*     */   }
/*     */ 
/*     */   public Long getNewSigningSize()
/*     */   {
/* 264 */     Long size = Long.valueOf(0L);
/* 265 */     if ((null != getNewSignings()) && (!getNewSignings().isEmpty())) {
/* 266 */       size = Long.valueOf(getNewSignings().size());
/*     */     }
/* 268 */     return size;
/*     */   }
/*     */ 
/*     */   public List<Task> getToDoTasks()
/*     */   {
/* 276 */     List toDoList = new ArrayList();
/* 277 */     String userCode = getLoginUser().getCode();
/*     */     try
/*     */     {
/* 280 */       String[] keyNames = new String[3];
/* 281 */       Object[] keyValues = new Object[3];
/* 282 */       keyNames[0] = "point.personnelFiles.code";
/* 283 */       keyNames[1] = "statue";
/* 284 */       keyNames[2] = "disabled";
/*     */ 
/* 286 */       keyValues[0] = userCode;
/* 287 */       keyValues[1] = Integer.valueOf(0);
/* 288 */       keyValues[2] = Boolean.valueOf(false);
/*     */ 
/* 290 */       toDoList = this.taskManager.loadByKeyArray(keyNames, keyValues);
/*     */     } catch (DaoException e) {
/* 292 */       this.logger.info("查询未审核任务出错！");
/*     */     }
/* 294 */     if ((null != toDoList) && (!toDoList.isEmpty())) {
/* 295 */       return toDoList;
/*     */     }
/* 297 */     return null;
/*     */   }
/*     */ 
			public Long getMyTeamSize()
/*     */   {
/* 362 */     Long size = Long.valueOf(0L);
              List<PersonnelFiles> personnelFiles =getMyTeam();
/* 363 */     if ((null != personnelFiles) && (!personnelFiles.isEmpty())) {
/* 364 */       size = Long.valueOf(personnelFiles.size());
/*     */     }
/* 369 */     return size;
/*     */   }
/*     */   public List<Daily> getDailys()
/*     */   {
/* 322 */     List reList = new ArrayList();
/*     */ 
/* 328 */     if ((null != reList) && (!reList.isEmpty())) {
/* 329 */       return reList;
/*     */     }
/* 331 */     return null;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelFilseByCode(String code)
/*     */   {
/* 340 */     PersonnelFiles personnelFiles = null;
/* 341 */     List pfList = new ArrayList();
/* 342 */     if (null != code) {
/*     */       try {
/* 344 */         pfList = this.personnelFilesManager.loadByKey("code", code);
/*     */       } catch (DaoException e) {
/* 346 */         this.logger.info("根据编码查询人事档案出错！");
/*     */       }
/*     */     }
/* 349 */     if ((null != pfList) && (!pfList.isEmpty())) {
/* 350 */       personnelFiles = (PersonnelFiles)pfList.get(0);
/* 351 */       return personnelFiles;
/*     */     }
/* 353 */     return null;
/*     */   }
/*     */ 
/*     */   public Long getToDoTaskSize()
/*     */   {
/* 362 */     Long size = Long.valueOf(0L);
/* 363 */     if ((null != getToDoTasks()) && (!getToDoTasks().isEmpty())) {
/* 364 */       size = Long.valueOf(getToDoTasks().size());
/*     */     }
/* 366 */     if ((null != getDailys()) && (!getDailys().isEmpty())) {
/* 367 */       size = Long.valueOf(size.longValue() + Long.valueOf(getDailys().size()).longValue());
/*     */     }
/* 369 */     return size;
/*     */   }
/*     */ 
/*     */   public List<ReceviceNotice> getTaskList()
/*     */   {
/* 377 */     Long userId = getLoginUser().getId();
/* 378 */     List unReadNoticeList = getReceiveNoticeList(userId, "UNREAD", Boolean.valueOf(false), String.valueOf("待办任务"));
/*     */ 
/* 380 */     if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
/* 381 */       return unReadNoticeList;
/*     */     }
/* 383 */     return null;
/*     */   }
            public List<ProjectInfoPlan> getMyProjectInfoPlan(){
            	 List<ProjectInfoPlan> plans = null;
            	 try {
					plans = this.projectInfoPlanManager.loadByKey("personnelFiles.code",getLoginUser().getCode());
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	return plans;
            	
            }
/*     */ 
/*     */   public Long getTaskSize()
/*     */   {
/* 392 */     List unReadNoticeList = new ArrayList();
/* 393 */     unReadNoticeList = getTaskList();
/* 394 */     if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
/* 395 */       return Long.valueOf(unReadNoticeList.size());
/*     */     }
/* 397 */     return Long.valueOf(0L);
/*     */   }
/*     */ 
/*     */   public List<ReceviceNotice> getNewsList()
/*     */   {
/* 406 */     Long userId = getLoginUser().getId();
/* 407 */     List reNoticeList = new ArrayList();
/* 408 */     List unReadNoticeList = getReceiveNoticeList(userId, "UNREAD", Boolean.valueOf(false), String.valueOf("新闻"));
/*     */ 
/* 410 */     List readNoticeList = getReceiveNoticeList(userId, "READED", Boolean.valueOf(false), String.valueOf("新闻"));
/*     */ 
/* 413 */     if ((null != unReadNoticeList) && (!unReadNoticeList.isEmpty())) {
/* 414 */       reNoticeList.addAll(unReadNoticeList);
/*     */     }
/* 416 */     if ((null != readNoticeList) && (!readNoticeList.isEmpty())) {
/* 417 */       reNoticeList.addAll(readNoticeList);
/*     */     }
/* 419 */     return reNoticeList;
/*     */   }
/*     */ 
/*     */   public Long getNewsSize()
/*     */   {
/* 427 */     List noticeList = new ArrayList();
/* 428 */     noticeList = getNewsList();
/* 429 */     if ((null != noticeList) && (!noticeList.isEmpty())) {
/* 430 */       return Long.valueOf(noticeList.size());
/*     */     }
/* 432 */     return Long.valueOf(0L);
/*     */   }
            public HashMap getMyDataMap(){
            	 SimpleDateFormat sfMonth = new SimpleDateFormat("yyyy年MM月");
          	   SimpleDateFormat sfYear = new SimpleDateFormat("yyyy年"); 
//          	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
            	
//	             String  MFirstday = format.format(getCurrMouthFirst());  //获取当月第一天
//	             String mLastday = format.format(getCurrMouthLast());  //获取当月最后一天
//	             String yFirstday= format.format(getCurrYearFirst());//获取当年第一天
//	             String yLastday= format.format(getCurrYearLast());//获取当年最后一天
//            	
//            	HashMap mapYear = this.contractManagementManager.getDataMap(yFirstday, yLastday);
//            	HashMap mapMouth = this.contractManagementManager.getDataMap(MFirstday, mLastday);
//            	HashMap mapThisMouth = this.financialManagementManager.getDataMap(MFirstday, mLastday);
//            	map.put("yearCount", mapYear.get("count"));
//            	map.put("yearMoney", mapYear.get("money"));
//            	map.put("mouthCount", mapMouth.get("count"));
//            	map.put("mouthMoney", mapMouth.get("money"));
//            	map.put("payCount", mapThisMouth.get("count"));
//            	map.put("payMoney", mapThisMouth.get("money"));
          	String[]keyStrings={"personnelFiles.code","month"};
        	String[]valueStrings={getLoginUser().getCode(),sfMonth.format(new Date())};
        	Data data=null;
        	Object [] objectArr =null;
        	try {
				List<Data> myDatas = this.dataManager.loadByKeyArray(keyStrings, valueStrings);
				HashMap map = new HashMap();
				map.put("year", sfYear.format(new Date()));
				map.put("code", getLoginUser().getCode());
				Object object = this.dataManager.loadAllDataByYear(map);
				 objectArr =(Object [])object;
				if(myDatas!=null&&myDatas.size()>0){
					data = myDatas.get(0);
				}
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	HashMap map = new HashMap();
        	if(objectArr!=null){
        	map.put("yearCMCount", objectArr[1]);
        	map.put("yearCMMoney", objectArr[0]);
        	map.put("yearFMCount", objectArr[2]);
        	map.put("yearFMMoney", objectArr[3]);
        	map.put("yearBRCount", objectArr[4]);
        	map.put("yearBRMoney", objectArr[5]);
        	}else {
        		map.put("yearCMCount", 0);
            	map.put("yearCMMoney",0);
            	map.put("yearFMCount", 0);
            	map.put("yearFMMoney", 0);
            	map.put("yearBRCount", 0);
            	map.put("yearBRMoney", 0);
			}
        	if(data!=null){
        	map.put("monthCMCount", data.getContractManagementNum());
        	map.put("monthCMMoney", data.getContractManagementMoney());
        	map.put("monthFMCount", data.getFinancialManagementNum());
        	map.put("monthFMMoney", data.getFinancialManagementMoney());
        	map.put("monthBRCount", data.getBillingRecordNum());
        	map.put("monthBRMoney", data.getBillingRecordMoney());
        	}else {
        		map.put("monthCMCount", 0);
            	map.put("monthCMMoney", 0);
            	map.put("monthFMCount", 0);
            	map.put("monthFMMoney", 0);
            	map.put("monthBRCount", 0);
            	map.put("monthBRMoney", 0);
			}
        	
            	return map;
            }
            public List<PersonnelFiles> getMyTeam(){
            	List  list =this.projectInfoPlanManager.loadByTeam(getLoginUser().getCode());
            	return list;
            	
            }
/*     */ 
/*     */   public Integer getAllNumberOfUnRead()
/*     */   {
/* 437 */     Integer number = Integer.valueOf(0);
/* 438 */     if (null != this.userManager.getUser()) {
/* 439 */       number = this.receviceNoticeManager.getAllNumberOfUnReadNoticByUserID(this.userManager.getUser().getId());
/*     */     }
/* 441 */     return number;
/*     */   }
/*     */ 
/*     */   public User getLoginUser()
/*     */   {
/* 449 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public String index() {
/* 453 */     return "success";
/*     */   }
/*     */ 
/*     */   public NoticeUtil getNoticeUtil()
/*     */   {
/* 461 */     return this.noticeUtil;
/*     */   }
/*     */ 
/*     */   public void setNoticeUtil(NoticeUtil noticeUtil)
/*     */   {
/* 470 */     this.noticeUtil = noticeUtil;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 478 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user)
/*     */   {
/* 487 */     this.user = user;
/*     */   }


		public static Date getCurrYearFirst(){  
			Calendar currCal=Calendar.getInstance();    
			int currentYear = currCal.get(Calendar.YEAR);  
			return getYearFirst(currentYear);  
		} 
		public static Date getYearFirst(int year){  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.clear();  
	        calendar.set(Calendar.YEAR, year);  
	        Date currYearFirst = calendar.getTime();  
	        return currYearFirst;  
	    }  
		public static Date getCurrYearLast(){  
	        Calendar currCal=Calendar.getInstance();    
	        int currentYear = currCal.get(Calendar.YEAR);  
	        return getYearLast(currentYear);  
	    }  
		public static Date getYearLast(int year){  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.clear();  
	        calendar.set(Calendar.YEAR, year);  
	        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
	        Date currYearLast = calendar.getTime();  
	          
	        return currYearLast;  
	    }  
		public static Date getCurrMouthFirst(){  
			Calendar cale = null;  
            cale = Calendar.getInstance();  
            cale.add(Calendar.MONTH, 0);  
            cale.set(Calendar.DAY_OF_MONTH, 1);  
			return cale.getTime();  
		} 
		public static Date getCurrMouthLast(){  
			Calendar cale = null;  
            cale = Calendar.getInstance();  
             cale.add(Calendar.MONTH, 1);  
             cale.set(Calendar.DAY_OF_MONTH, 0);  
             return cale.getTime();  
		}
		public Integer getMyPlanSize() {
			Integer sizeInteger=0;
			List<ProjectInfoPlan> plans =this.getMyProjectInfoPlan();
			if(plans!=null&&plans.size()>0){
				sizeInteger=plans.size();
			}
			return sizeInteger;
		}
		
		
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.FramesetAction
 * JD-Core Version:    0.6.2
 */