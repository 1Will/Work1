/*    */ package com.yongjun.pluto.spring.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.sf.jasperreports.engine.JasperReport;
/*    */ import net.sf.jasperreports.engine.design.JRCompiler;
/*    */ import net.sf.jasperreports.engine.design.JRDefaultCompiler;
/*    */ import net.sf.jasperreports.engine.design.JasperDesign;
/*    */ import net.sf.jasperreports.engine.xml.JRXmlLoader;
/*    */ import org.springframework.context.ApplicationContextException;
/*    */ import org.springframework.core.io.ClassPathResource;
/*    */ import org.springframework.core.io.Resource;
/*    */ 
/*    */ public class JasperReportsLoader
/*    */ {
/* 53 */   private static final JRCompiler jrCompiler = JRDefaultCompiler.getInstance();
/*    */ 
/*    */   public static JasperReport load(String path)
/*    */   {
/*    */     try
/*    */     {
/* 39 */       Resource resource = new ClassPathResource(path);
/* 40 */       JasperDesign design = JRXmlLoader.load(resource.getInputStream());
/* 41 */       return jrCompiler.compileReport(design);
/*    */     } catch (IOException ex) {
/* 43 */       throw new ApplicationContextException("Could not load JasperReports report for URL [" + path + "]", ex);
/*    */     }
/*    */     catch (Exception ex)
/*    */     {
/* 47 */       throw new ApplicationContextException("Could not parse JasperReports report for URL [" + path + "]", ex);
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.pluto.spring.util.JasperReportsLoader
 * JD-Core Version:    0.6.2
 */