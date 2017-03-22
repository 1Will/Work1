/*    */ package com.yongjun.pluto.spring.view;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.springframework.web.servlet.view.AbstractUrlBasedView;
/*    */ import org.springframework.web.servlet.view.UrlBasedViewResolver;
/*    */ import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
/*    */ 
/*    */ public class JasperReportsViewResolver extends UrlBasedViewResolver
/*    */ {
/*    */   private Map exporterParameters;
/*    */ 
/*    */   protected AbstractUrlBasedView buildView(String viewName)
/*    */     throws Exception
/*    */   {
/* 33 */     AbstractJasperReportsView view = (AbstractJasperReportsView)super.buildView(viewName);
/*    */ 
/* 35 */     view.setExporterParameters(this.exporterParameters);
/* 36 */     return view;
/*    */   }
/*    */ 
/*    */   public void setExporterParameters(Map exporterParameters) {
/* 40 */     this.exporterParameters = exporterParameters;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.pluto.spring.view.JasperReportsViewResolver
 * JD-Core Version:    0.6.2
 */