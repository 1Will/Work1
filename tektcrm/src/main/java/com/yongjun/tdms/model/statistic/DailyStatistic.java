/*     */ package com.yongjun.tdms.model.statistic;
/*     */ 
/*     */ import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ 
/*     */ public class DailyStatistic extends BaseInfoEntity
/*     */ {
//			用户数量，登录次数，PC登录数，手机登录数，合同关联回复数，
//	新增发货单，发货金额，	项目关联回复数，（日报数，日报回复数，新增客户，新增联系人，客户完整度变更数，
//			联系人完整度变更，客户回访数，回访回复数，新增项目，新增项目计划数，项目计划变更数，项目计划完成数），
//		（新增合同，合同金额，新增合同计划数，合同计划变更数，合同计划完成数），新增收款数，收款金额，新增开篇记录，开票金额，出差申请数，加班单数，请假单数；
/*     */   private static final long serialVersionUID = 8908956162303433991L;
			private   Date dailyDate;//年月日
			private String dnDate;
			private long dailyNum;//日报数
			private long dailyReplyNum;//日报回复数
			private long customerNum;//新增客户
			private double customerIntegrity;//客户完整度
			private long contactArchivesNum;//新增联系人 
			private double contactArchivesIntegrity;//客户完整度
			private long backVisitNum;//回访次数
			private long backVisitReplyNum;//回访回复数
			private long projectNum;//新建项目数
			private long projectPlanNum;//项目计划数；
			private long projectPlanChangeNum;//项目计划改变数;
			private long projectPlanFinishNum;//项目计划完成数；
			private long contractManagementNum;//合同数量 
			private double contractManagementMoney;//合同金额
			private long contractManagementPlanNum;//合同计划数量。
			private long contractManagementPlanChangeNum;//合同计划改变数;
			private long contractManagementPlanFinishNum;//合同计划完成数；
			private long financialManagementNum;//新建收款单数量
			private double financialManagementMoney;//收款金额
			private long billingRecordNum;//新建开票数量
			private double billingRecordMoney;//开票金额
			private long businessTravelNum;//出差数量
			private long leaveNum;//请假数量
			private long overtimeNum;//加班数量
			private PersonnelFiles personnelFiles; //发生人
			
			
			public Date getDailyDate() {
				return dailyDate;
			}
			public void setDailyDate(Date dailyDate) {
				this.dailyDate = dailyDate;
			}
			public long getDailyNum() {
				return dailyNum;
			}
			public void setDailyNum(long dailyNum) {
				this.dailyNum = dailyNum;
			}
			public long getDailyReplyNum() {
				return dailyReplyNum;
			}
			public void setDailyReplyNum(long dailyReplyNum) {
				this.dailyReplyNum = dailyReplyNum;
			}
			public long getCustomerNum() {
				return customerNum;
			}
			public void setCustomerNum(long customerNum) {
				this.customerNum = customerNum;
			}
			public double getCustomerIntegrity() {
				return customerIntegrity;
			}
			public void setCustomerIntegrity(double customerIntegrity) {
				this.customerIntegrity = customerIntegrity;
			}
			public long getContactArchivesNum() {
				return contactArchivesNum;
			}
			public void setContactArchivesNum(long contactArchivesNum) {
				this.contactArchivesNum = contactArchivesNum;
			}
			public double getContactArchivesIntegrity() {
				return contactArchivesIntegrity;
			}
			public void setContactArchivesIntegrity(double contactArchivesIntegrity) {
				this.contactArchivesIntegrity = contactArchivesIntegrity;
			}
			public long getBackVisitNum() {
				return backVisitNum;
			}
			public void setBackVisitNum(long backVisitNum) {
				this.backVisitNum = backVisitNum;
			}
			public long getBackVisitReplyNum() {
				return backVisitReplyNum;
			}
			public void setBackVisitReplyNum(long backVisitReplyNum) {
				this.backVisitReplyNum = backVisitReplyNum;
			}
			public long getProjectNum() {
				return projectNum;
			}
			public void setProjectNum(long projectNum) {
				this.projectNum = projectNum;
			}
			public long getProjectPlanNum() {
				return projectPlanNum;
			}
			public void setProjectPlanNum(long projectPlanNum) {
				this.projectPlanNum = projectPlanNum;
			}
			public long getProjectPlanChangeNum() {
				return projectPlanChangeNum;
			}
			public void setProjectPlanChangeNum(long projectPlanChangeNum) {
				this.projectPlanChangeNum = projectPlanChangeNum;
			}
			public long getProjectPlanFinishNum() {
				return projectPlanFinishNum;
			}
			public void setProjectPlanFinishNum(long projectPlanFinishNum) {
				this.projectPlanFinishNum = projectPlanFinishNum;
			}
			public long getContractManagementNum() {
				return contractManagementNum;
			}
			public void setContractManagementNum(long contractManagementNum) {
				this.contractManagementNum = contractManagementNum;
			}
			public double getContractManagementMoney() {
				return contractManagementMoney;
			}
			public void setContractManagementMoney(double contractManagementMoney) {
				this.contractManagementMoney = contractManagementMoney;
			}
			public long getContractManagementPlanNum() {
				return contractManagementPlanNum;
			}
			public void setContractManagementPlanNum(long contractManagementPlanNum) {
				this.contractManagementPlanNum = contractManagementPlanNum;
			}
			public long getContractManagementPlanChangeNum() {
				return contractManagementPlanChangeNum;
			}
			public void setContractManagementPlanChangeNum(long contractManagementPlanChangeNum) {
				this.contractManagementPlanChangeNum = contractManagementPlanChangeNum;
			}
			public long getContractManagementPlanFinishNum() {
				return contractManagementPlanFinishNum;
			}
			public void setContractManagementPlanFinishNum(long contractManagementPlanFinishNum) {
				this.contractManagementPlanFinishNum = contractManagementPlanFinishNum;
			}
			public long getFinancialManagementNum() {
				return financialManagementNum;
			}
			public void setFinancialManagementNum(long financialManagementNum) {
				this.financialManagementNum = financialManagementNum;
			}
			public double getFinancialManagementMoney() {
				return financialManagementMoney;
			}
			public void setFinancialManagementMoney(double financialManagementMoney) {
				this.financialManagementMoney = financialManagementMoney;
			}
			public long getBillingRecordNum() {
				return billingRecordNum;
			}
			public void setBillingRecordNum(long billingRecordNum) {
				this.billingRecordNum = billingRecordNum;
			}
			public double getBillingRecordMoney() {
				return billingRecordMoney;
			}
			public void setBillingRecordMoney(double billingRecordMoney) {
				this.billingRecordMoney = billingRecordMoney;
			}
			public long getBusinessTravelNum() {
				return businessTravelNum;
			}
			public void setBusinessTravelNum(long businessTravelNum) {
				this.businessTravelNum = businessTravelNum;
			}
			public long getLeaveNum() {
				return leaveNum;
			}
			public void setLeaveNum(long leaveNum) {
				this.leaveNum = leaveNum;
			}
			public long getOvertimeNum() {
				return overtimeNum;
			}
			public void setOvertimeNum(long overtimeNum) {
				this.overtimeNum = overtimeNum;
			}
			public PersonnelFiles getPersonnelFiles() {
				return personnelFiles;
			}
			public void setPersonnelFiles(PersonnelFiles personnelFiles) {
				this.personnelFiles = personnelFiles;
			}
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 166 */     return 0;
/*     */   }
			public String getDnDate() {
				return dnDate;
			}
			public void setDnDate(String dnDate) {
				this.dnDate = dnDate;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.workspace.leaveBill.LeaveBill
 * JD-Core Version:    0.6.2
 */