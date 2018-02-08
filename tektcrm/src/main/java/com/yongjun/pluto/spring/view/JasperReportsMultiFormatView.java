/*    */ package com.yongjun.pluto.spring.view;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.web.util.UrlPathHelper;
/*    */ import org.springframework.web.util.WebUtils;
/*    */ 
/*    */ public class JasperReportsMultiFormatView extends org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView
/*    */ {
/*    */   public static final String IMAGE_URI = "/images/report/";
/* 63 */   public static final UrlPathHelper urlPathHelper = new UrlPathHelper();
/*    */   public static final String IMAGE_URI_KEY = "net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI";
/*    */   public static final String IMAGE_MAP_KEY = "net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_MAP";
/*    */ 
/*    */   protected void renderMergedOutputModel(Map map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
/*    */     throws Exception
/*    */   {
/* 40 */     Map exporterParameters = getExporterParameters();
/* 41 */     Map imagesMap = new HashMap();
/* 42 */     WebUtils.setSessionAttribute(httpServletRequest, "IMAGES_MAP", imagesMap);
/*    */ 
/* 44 */     exporterParameters.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_MAP", imagesMap);
/*    */ 
/* 48 */     String imageUri = "/images/report/";
/* 49 */     String contextPath = urlPathHelper.getContextPath(httpServletRequest);
/* 50 */     if (contextPath != null) {
/* 51 */       imageUri = contextPath + imageUri;
/*    */     }
/* 53 */     exporterParameters.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI", imageUri);
/*    */ 
/* 57 */     super.renderMergedOutputModel(map, httpServletRequest, httpServletResponse);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.pluto.spring.view.JasperReportsMultiFormatView
 * JD-Core Version:    0.6.2
 */