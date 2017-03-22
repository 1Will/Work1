/*    */ package com.yongjun.tdms.presentation.webwork.action.report.custcontact;
/*    */ 
/*    */ import com.yongjun.pluto.spring.controller.JasperReportsController;
/*    */ import com.yongjun.tdms.model.report.custcontact.CustContactReport;
/*    */ import com.yongjun.tdms.service.report.custcontact.CustContactReportManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ListCustContactReportController extends JasperReportsController
/*    */ {
/*    */   private final CustContactReportManager custContactReportManager;
/*    */   private List<CustContactReport> contacts;
/*    */ 
/*    */   public ListCustContactReportController(CustContactReportManager custContactReportManager)
/*    */   {
/* 49 */     this.custContactReportManager = custContactReportManager;
/*    */   }
/*    */ 
/*    */   protected Map getModel(HttpServletRequest httpservletrequest)
/*    */     throws Exception
/*    */   {
/* 60 */     Map model = new HashMap();
/* 61 */     String[] contactIds = httpservletrequest.getParameter("contactIds").toString().split(",");
/* 62 */     Long[] ccrIds = new Long[contactIds.length];
/* 63 */     for (int i = 0; i < contactIds.length; i++) {
/* 64 */       ccrIds[i] = Long.valueOf(contactIds[i]);
/*    */     }
/* 66 */     if ((null != contactIds) && (!"".equals(contactIds))) {
/* 67 */       this.contacts = this.custContactReportManager.loadAllCustContactReport(ccrIds);
/*    */     }
/* 69 */     model.put("contactsData", this.contacts);
/* 70 */     return model;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.report.custcontact.ListCustContactReportController
 * JD-Core Version:    0.6.2
 */