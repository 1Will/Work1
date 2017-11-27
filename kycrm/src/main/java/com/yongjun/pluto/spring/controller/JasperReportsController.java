/*    */ package com.yongjun.pluto.spring.controller;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import org.springframework.web.util.UrlPathHelper;
/*    */ 
/*    */ public abstract class JasperReportsController extends UrlPathController
/*    */ {
/*    */   public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
/*    */     throws Exception
/*    */   {
/* 38 */     Map model = getModel(httpServletRequest);
/* 39 */     String uri = urlPathHelper.getPathWithinApplication(httpServletRequest);
/* 40 */     model.put("format", StringUtils.substringAfterLast(uri, "."));
/* 41 */     return new ModelAndView(StringUtils.substringBeforeLast(uri, "."), model);
/*    */   }
/*    */ 
/*    */   protected abstract Map getModel(HttpServletRequest paramHttpServletRequest)
/*    */     throws Exception;
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.pluto.spring.controller.JasperReportsController
 * JD-Core Version:    0.6.2
 */