/*     */ package com.yongjun.tdms.service.workReport.daily.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.workReport.daily.DailyDao;
import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.model.workReport.daily.Daily;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
/*     */ import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.pluto.service.security.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultDailyManager extends BaseManager
/*     */   implements DailyManager
/*     */ {
/*     */   private final DailyDao dailyDao;
			private UserManager userManager;
			private BackVisitManager backVisitManager;
			private CustomerInfoManager customerInfoManager;
			private ContactArchivesManager contactArchivesManager;
			private ProjectInfoManager projectInfoManager;
			
/*     */ 
/*     */   public DefaultDailyManager(DailyDao dailyDao,UserManager userManager,BackVisitManager backVisitManager,CustomerInfoManager customerInfoManager,ContactArchivesManager contactArchivesManager,ProjectInfoManager projectInfoManager)
/*     */   {
/*  48 */     this.dailyDao = dailyDao;
			  this.userManager=userManager;
			  this.backVisitManager=backVisitManager;
			  this.customerInfoManager=customerInfoManager;
			  this.contactArchivesManager=contactArchivesManager;
			  this.projectInfoManager=projectInfoManager;
/*     */   }
/*     */ 
/*     */   public void storeDaily(Daily daily)
/*     */   {
/*  57 */     this.dailyDao.storeDaily(daily);
/*     */   }
/*     */ 
/*     */   public void deleteDaily(Daily daily)
/*     */   {
/*  66 */     this.dailyDao.deleteDaily(daily);
/*     */   }
/*     */ 
/*     */   public void deleteAllDaily(Collection<Daily> dailys)
/*     */   {
/*  75 */     this.dailyDao.deleteAllDaily(dailys);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadAllDaily(Long[] dailyIds)
/*     */   {
/*  85 */     return this.dailyDao.loadAllDaily(dailyIds);
/*     */   }
/*     */ 
/*     */   public Daily loadDaily(Long dailyId)
/*     */   {
/*  95 */     return this.dailyDao.loadDaily(dailyId);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadAllDaily()
/*     */   {
/* 104 */     return this.dailyDao.loadAllDaily();
/*     */   }
/*     */ 
/*     */   public List<Daily> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.dailyDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Daily> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.dailyDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
			public int loadDailyByDate(String da) throws ParseException, DaoException{
				java.util.Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(da);
				String date = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS").format(newDate);
				User user = userManager.getUser();
				String[] keyNames={"currentDate","rapporteur.code"};
				Object[] keyValues={date,user.getCode()};
				List<Daily> list = loadByKeyArray(keyNames, keyValues);
				int size =list.size();
				return size;
			}
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public List<Object> loadDailyNeed(String userId, String da){
				List<BackVisit> backVisitList =null;
				List customerInfoList =new ArrayList();
				List contactArchivesList =new ArrayList();
				List projectList =new ArrayList();
				try {
					backVisitList = backVisitManager.loadBackVisitByDate(userId, da);
				} catch (DaoException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				customerInfoList.add(customerInfoManager.loadCustomerInfoByDate(userId, da));
				contactArchivesList.add(contactArchivesManager.loadContactArchivesByDate(userId, da));
				projectList.add(projectInfoManager.loadProjectByDate(userId, da));
				List obj =new ArrayList();
				obj.add(backVisitList);
				obj.add(customerInfoList);
				obj.add(contactArchivesList);
				obj.add(projectList);
				return obj;
			}
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workReport.daily.pojo.DefaultDailyManager
 * JD-Core Version:    0.6.2
 */