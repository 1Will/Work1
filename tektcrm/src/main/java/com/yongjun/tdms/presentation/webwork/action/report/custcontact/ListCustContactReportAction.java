/*    */ package com.yongjun.tdms.presentation.webwork.action.report.custcontact;
/*    */ 
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ListCustContactReportAction extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public String reportType;
/*    */ 
/*    */   public void prepare()
/*    */     throws Exception
/*    */   {
/* 44 */     if (this.request.getParameter("reportType") != null)
/* 45 */       this.reportType = this.request.getParameter("reportType");
/*    */   }
/*    */ 
/*    */   protected String getAdapterName()
/*    */   {
/* 55 */     return "CustContactReportHQL";
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.report.custcontact.ListCustContactReportAction
 * JD-Core Version:    0.6.2
 */