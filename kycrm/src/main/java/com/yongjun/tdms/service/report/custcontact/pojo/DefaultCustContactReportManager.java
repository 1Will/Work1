/*    */ package com.yongjun.tdms.service.report.custcontact.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.report.custcontact.CustContactReportDao;
/*    */ import com.yongjun.tdms.model.report.custcontact.CustContactReport;
/*    */ import com.yongjun.tdms.service.report.custcontact.CustContactReportManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultCustContactReportManager extends BaseManager
/*    */   implements CustContactReportManager
/*    */ {
/*    */   private final CustContactReportDao custContactReportDao;
/*    */ 
/*    */   public DefaultCustContactReportManager(CustContactReportDao custContactReportDao)
/*    */   {
/* 45 */     this.custContactReportDao = custContactReportDao;
/*    */   }
/*    */ 
/*    */   public CustContactReport loadCustContactReport(Long ccrId)
/*    */   {
/* 55 */     return this.custContactReportDao.loadCustContactReport(ccrId);
/*    */   }
/*    */ 
/*    */   public List<CustContactReport> loadAllCustContactReport()
/*    */   {
/* 64 */     return this.custContactReportDao.loadAllCustContactReport();
/*    */   }
/*    */ 
/*    */   public List<CustContactReport> loadAllCustContactReport(Long[] ccrIds)
/*    */   {
/* 74 */     return this.custContactReportDao.loadAllCustContactReport(ccrIds);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.report.custcontact.pojo.DefaultCustContactReportManager
 * JD-Core Version:    0.6.2
 */