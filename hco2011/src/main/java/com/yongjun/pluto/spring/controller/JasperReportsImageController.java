/*    */ package com.yongjun.pluto.spring.controller;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.util.FileCopyUtils;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import org.springframework.web.util.UrlPathHelper;
/*    */ import org.springframework.web.util.WebUtils;
/*    */ 
/*    */ public class JasperReportsImageController extends UrlPathController
/*    */ {
/*    */   public static final String IMAGES_MAP_KEY = "IMAGES_MAP";
/*    */ 
/*    */   public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
/*    */     throws Exception
/*    */   {
/* 36 */     Map imagesMap = (Map)WebUtils.getSessionAttribute(httpServletRequest, "IMAGES_MAP");
/* 37 */     if (imagesMap != null)
/*    */     {
/* 39 */       String imageName = urlPathHelper.getPathWithinServletMapping(httpServletRequest);
/* 40 */       if (imageName != null)
/*    */       {
/* 42 */         if (imageName.startsWith("/"))
/* 43 */           imageName = imageName.substring(1);
/* 44 */         byte[] imageData = (byte[])(byte[])imagesMap.get(imageName);
/* 45 */         FileCopyUtils.copy(imageData, httpServletResponse.getOutputStream());
/* 46 */         httpServletResponse.setContentLength(imageData.length);
/*    */       }
/*    */     }
/* 49 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.pluto.spring.controller.JasperReportsImageController
 * JD-Core Version:    0.6.2
 */