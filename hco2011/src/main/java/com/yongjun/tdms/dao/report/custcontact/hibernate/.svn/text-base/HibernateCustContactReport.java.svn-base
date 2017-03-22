/*    */ package com.yongjun.tdms.dao.report.custcontact.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.tdms.dao.report.custcontact.CustContactReportDao;
/*    */ import com.yongjun.tdms.model.report.custcontact.CustContactReport;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateCustContactReport extends BaseHibernateDao
/*    */   implements CustContactReportDao
/*    */ {
/*    */   public CustContactReport loadCustContactReport(Long ccrId)
/*    */   {
/* 43 */     return (CustContactReport)super.load(CustContactReport.class, ccrId);
/*    */   }
/*    */ 
/*    */   public List<CustContactReport> loadAllCustContactReport()
/*    */   {
/* 52 */     return super.loadAll(CustContactReport.class);
/*    */   }
/*    */ 
/*    */   public List<CustContactReport> loadAllCustContactReport(Long[] ccrIds)
/*    */   {
/* 62 */     return super.loadAll(CustContactReport.class, ccrIds);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.report.custcontact.hibernate.HibernateCustContactReport
 * JD-Core Version:    0.6.2
 */