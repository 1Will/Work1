/*     */ package com.yongjun.tdms.service.base.duty.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.base.duty.DutyDao;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultDutyManager extends BaseManager
/*     */   implements DutyManager
/*     */ {
/*     */   private final DutyDao dutyDao;
			private final YongJunSequenceManager yongJunSequenceManager;
/*     */ 
/*     */   public DefaultDutyManager(DutyDao dutyDao,YongJunSequenceManager yongJunSequenceManager)
/*     */   {
/*  26 */     this.dutyDao = dutyDao;
		      this.yongJunSequenceManager = yongJunSequenceManager;
/*     */   }
/*     */   public void deleteAllDuty(Collection<Duty> duty) {
/*  29 */     this.dutyDao.deleteAllDuty(duty);
/*     */   }
/*     */ 
/*     */   public void deleteDuty(Duty duty)
/*     */   {
/*  34 */     this.dutyDao.deleteDuty(duty);
/*     */   }
/*     */ 
/*     */   public void disabledAllDuties(Collection<Duty> duties)
/*     */   {
/*  39 */     for (Duty duty : duties) {
/*  40 */       duty.setDisabled(true);
/*  41 */       this.dutyDao.storeDuty(duty);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllChecks(Collection<Duty> duties) {
/*  46 */     for (Duty duty : duties) {
/*  47 */       duty.setDisabled(false);
/*  48 */       this.dutyDao.storeDuty(duty);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Duty> loadAllDuty(Long[] dutyIds) {
/*  53 */     return this.dutyDao.loadAllDuty(dutyIds);
/*     */   }
/*     */ 
/*     */   public List<Duty> loadAllDuty() {
/*  57 */     return this.dutyDao.loadAllDuty();
/*     */   }
/*     */ 
/*     */   public List<Duty> loadByKey(String keyName, Object keyValue) throws DaoException {
/*  61 */     return this.dutyDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public Duty loadDuty(Long dutyId) {
/*  65 */     return this.dutyDao.loadDuty(dutyId);
/*     */   }
/*     */ 
/*     */   public void storeDuty(Duty duty) {
			  if(duty.isNew()){
				  duty.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_DUTY));;
			  }
/*  69 */     this.dutyDao.storeDuty(duty);
/*     */   }
/*     */ 
/*     */   public List loadDutiesByDept(String deptId, Long orgId, String flag)
/*     */     throws DaoException
/*     */   {
/*  83 */     Long agencyId = Long.valueOf(deptId);
/*  84 */     List<Duty> dutyList =null;
/*     */ 
/*  86 */     if (flag.equals("search")) {
/*  87 */       String[] keyNames = { "dept.id", "organization.id" };
/*  88 */       Object[] keyValues = { agencyId, orgId };
/*  89 */       dutyList = this.dutyDao.loadByKeyArray(keyNames, keyValues);
/*  90 */     } else if (flag.equals("edit")) {
/*  91 */       String[] keyNames = { "dept.id", "disabled", "organization.id" };
/*  92 */       Object[] keyValues = { agencyId, Boolean.valueOf(false), orgId };
/*  93 */       dutyList = this.dutyDao.loadByDeptId(deptId);
/*     */     }
/*     */ 	  List<Duty> list = new ArrayList();
/*  96 */     for (Duty duty : dutyList) {
				Duty du=new Duty();
/*  97 */       du.setId(duty.getId());
/*  97 */       du.setName(duty.getName());
				list.add(du);
/*     */     }
/* 104 */     return list;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.duty.pojo.DefaultDutyManager
 * JD-Core Version:    0.6.2
 */