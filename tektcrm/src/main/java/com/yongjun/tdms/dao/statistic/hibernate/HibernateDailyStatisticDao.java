/*    */ package com.yongjun.tdms.dao.statistic.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.statistic.DailyStatisticDao;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.statistic.DailyStatistic;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/*    */ 
/*    */ public class HibernateDailyStatisticDao extends BaseHibernateDao
/*    */   implements DailyStatisticDao
/*    */ {
/*    */ 
/*    */   public List<DailyStatistic> loadAllDailyStatistic(Long[] lbIds) {
/* 25 */     return loadAll(DailyStatistic.class, lbIds);
/*    */   }
/*    */ 
/*    */   public List<DailyStatistic> loadAllDailyStatistic() {
/* 29 */     return loadAll(DailyStatistic.class);
/*    */   }
/*    */ 
/*    */   public List<DailyStatistic> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 34 */     return loadByKey(DailyStatistic.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<DailyStatistic> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 39 */     return loadByKeyArray(DailyStatistic.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public DailyStatistic loadDailyStatistic(Long lbId) {
/* 43 */     return (DailyStatistic)get(DailyStatistic.class, lbId);
/*    */   }
/*    */ 
/*    */
			public void storeDailyStatistic(DailyStatistic dailyStatistic) {
				store(dailyStatistic);
	
			}
			public List<Object[]> loadAllDailyStatisticByContion() {
				// TODO Auto-generated method stub
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {
					
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select SUM(temp.dailyNum) nd,SUM(temp.dailyReplyNum) dr,SUM(temp.customerNum) cn,SUM(temp.contactArchivesNum) ca,"
								+ "SUM(temp. backVisitNum) bv,SUM(temp.backVisitReplyNum) br,SUM(temp.projectNum) pn,SUM(temp.projectPlanNum) pp,"
                                + "SUM(temp.projectPlanChangeNum) pc,SUM(temp.projectPlanFinishNum) pf,SUM(temp.contractManagementNum) cm,"
                                + "SUM(temp.contractManagementMoney) cmm,SUM(temp.contractManagementPlanNum) cmp,SUM(temp.contractManagementPlanChangeNum) cmpc,"
                                + "SUM(temp.contractManagementPlanFinishNum) cmpf,SUM(temp.financialManagementNum) fmn,SUM(temp.financialManagementMoney) fmm,SUM(temp.billingRecordNum) br,"
                                + "SUM(temp.billingRecordMoney) brm,SUM(temp.businessTravelNum) bt,SUM(temp.leaveNum) ln,SUM(temp.overtimeNum) otn,"
                                + "temp.dailyDate dd from (")
						.append(" select dailyNum dailyNum ,dailyReplyNum dailyReplyNum,customerNum customerNum,contactArchivesNum contactArchivesNum,"
								+ "backVisitNum backVisitNum,backVisitReplyNum backVisitReplyNum,projectNum projectNum,projectPlanNum projectPlanNum,"
								+ "projectPlanChangeNum projectPlanChangeNum,projectPlanFinishNum projectPlanFinishNum,contractManagementNum contractManagementNum,contractManagementMoney contractManagementMoney,"
								+ "contractManagementPlanNum contractManagementPlanNum,contractManagementPlanChangeNum contractManagementPlanChangeNum,contractManagementPlanFinishNum contractManagementPlanFinishNum,"
								+ "financialManagementNum financialManagementNum,financialManagementMoney financialManagementMoney,billingRecordNum billingRecordNum,billingRecordMoney billingRecordMoney,businessTravelNum businessTravelNum,"
								+ "leaveNum leaveNum,overtimeNum overtimeNum,"
								+ "SUBSTRING(convert(char(10),dailyDate,120),1,7) dailyDate  from  t_dailyStatistic ) temp")
						.append(" GROUP BY temp.dailyDate");
						return session.createSQLQuery(sql.toString()).addScalar("nd",Hibernate.LONG).addScalar("dr", Hibernate.LONG)
								.addScalar("cn", Hibernate.LONG).addScalar("ca", Hibernate.LONG).addScalar("bv", Hibernate.LONG).addScalar("br", Hibernate.LONG)
								.addScalar("pn", Hibernate.LONG).addScalar("pp", Hibernate.LONG).addScalar("pc", Hibernate.LONG).addScalar("pf", Hibernate.LONG)
								.addScalar("cm", Hibernate.LONG).addScalar("cmm", Hibernate.DOUBLE).addScalar("cmp", Hibernate.LONG).addScalar("cmpc", Hibernate.LONG)
								.addScalar("cmpf", Hibernate.LONG).addScalar("fmn", Hibernate.LONG).addScalar("fmm", Hibernate.DOUBLE).addScalar("br", Hibernate.LONG)
								.addScalar("brm", Hibernate.DOUBLE).addScalar("bt", Hibernate.LONG).addScalar("ln", Hibernate.LONG).addScalar("otn", Hibernate.LONG)
								.addScalar("dd", Hibernate.STRING).list();
					}
				});
			}
			public List<Object[]> loadAllDailyStatisticDayByContion() {
				// TODO Auto-generated method stub
return this.getHibernateTemplate().executeFind(new HibernateCallback() {
					
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select SUM(temp.dailyNum) dn,SUM(temp.dailyReplyNum) dr,SUM(temp.customerNum) cn,SUM(temp.contactArchivesNum) ca,"
								+ "SUM(temp. backVisitNum) bv,SUM(temp.backVisitReplyNum) br,SUM(temp.projectNum) pn,SUM(temp.projectPlanNum) pp,"
                                + "SUM(temp.projectPlanChangeNum) pc,SUM(temp.projectPlanFinishNum) pf,SUM(temp.contractManagementNum) cm,"
                                + "SUM(temp.contractManagementMoney) cmm,SUM(temp.contractManagementPlanNum) cmp,SUM(temp.contractManagementPlanChangeNum) cmpc,"
                                + "SUM(temp.contractManagementPlanFinishNum) cmpf,SUM(temp.financialManagementNum) fmn,SUM(temp.financialManagementMoney) fmm,SUM(temp.billingRecordNum) br,"
                                + "SUM(temp.billingRecordMoney) brm,SUM(temp.businessTravelNum) bt,SUM(temp.leaveNum) ln,SUM(temp.overtimeNum) otn,"
                                + "temp.dailyDate dd from (")
						.append(" select dailyNum dailyNum ,dailyReplyNum dailyReplyNum,customerNum customerNum,contactArchivesNum contactArchivesNum,"
								+ "backVisitNum backVisitNum,backVisitReplyNum backVisitReplyNum,projectNum projectNum,projectPlanNum projectPlanNum,"
								+ "projectPlanChangeNum projectPlanChangeNum,projectPlanFinishNum projectPlanFinishNum,contractManagementNum contractManagementNum,contractManagementMoney contractManagementMoney,"
								+ "contractManagementPlanNum contractManagementPlanNum,contractManagementPlanChangeNum contractManagementPlanChangeNum,contractManagementPlanFinishNum contractManagementPlanFinishNum,"
								+ "financialManagementNum financialManagementNum,financialManagementMoney financialManagementMoney,billingRecordNum billingRecordNum,billingRecordMoney billingRecordMoney,businessTravelNum businessTravelNum,"
								+ "leaveNum leaveNum,overtimeNum overtimeNum,"
								+ "SUBSTRING(convert(char(10),dailyDate,120),1,10) dailyDate  from  t_dailyStatistic ) temp")
						.append(" GROUP BY temp.dailyDate ORDER BY temp.dailyDate ");
						return session.createSQLQuery(sql.toString()).addScalar("dn",Hibernate.LONG).addScalar("dr", Hibernate.LONG)
								.addScalar("cn", Hibernate.LONG).addScalar("ca", Hibernate.LONG).addScalar("bv", Hibernate.LONG).addScalar("br", Hibernate.LONG)
								.addScalar("pn", Hibernate.LONG).addScalar("pp", Hibernate.LONG).addScalar("pc", Hibernate.LONG).addScalar("pf", Hibernate.LONG)
								.addScalar("cm", Hibernate.LONG).addScalar("cmm", Hibernate.DOUBLE).addScalar("cmp", Hibernate.LONG).addScalar("cmpc", Hibernate.LONG)
								.addScalar("cmpf", Hibernate.LONG).addScalar("fmn", Hibernate.LONG).addScalar("fmm", Hibernate.DOUBLE).addScalar("br", Hibernate.LONG)
								.addScalar("brm", Hibernate.DOUBLE).addScalar("bt", Hibernate.LONG).addScalar("ln", Hibernate.LONG).addScalar("otn", Hibernate.LONG)
								.addScalar("dd", Hibernate.STRING).list();
					}
				});
			}
			
		}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.leaveBill.hibernate.HibernateLeaveBillDao
 * JD-Core Version:    0.6.2
 */