/*     */ package com.yongjun.pluto.service.file.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.service.file.FileTransportManager;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.pluto.util.GenerateUUID;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Properties;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultFileTransportManager extends BaseManager
/*     */   implements FileTransportManager
/*     */ {
/*     */   protected Properties systemParameterConfiguration;
/*     */ 
/*     */   public Properties getSystemParameterConfiguration()
/*     */   {
/*  42 */     return this.systemParameterConfiguration;
/*     */   }
/*     */ 
/*     */   public void setSystemParameterConfiguration(Properties systemParameterConfiguration) {
/*  46 */     this.systemParameterConfiguration = systemParameterConfiguration;
/*     */   }
/*     */ 
/*     */   public void download(HttpServletRequest request, HttpServletResponse response, String fileName, String position)
/*     */   {
/*  51 */     response.setContentType("unknown");
/*  52 */     String name = "";
/*     */     try
/*     */     {
/*  55 */       name = new String(fileName.getBytes("gb2312"), "iso8859-1");
/*     */     } catch (UnsupportedEncodingException e) {
/*  57 */       e.printStackTrace();
/*     */     }
/*  59 */     response.setHeader("Content-Disposition", "attachment; filename=" + name);
/*     */     try
/*     */     {
/*  63 */       OutputStream os = response.getOutputStream();
/*  64 */       File f = new File(getFileName(request, position));
/*  65 */       FileInputStream fis = new FileInputStream(f);
/*     */ 
/*  67 */       byte[] b = new byte[1048576];
/*  68 */       int i = 0;
/*     */ 
/*  70 */       while ((i = fis.read(b)) > 0) {
/*  71 */         os.write(b, 0, i);
/*     */       }
/*     */ 
/*  74 */       fis.close();
/*  75 */       os.flush();
/*  76 */       os.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  80 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String upload(HttpServletRequest request, File file, String sourceFileName) throws Exception {
/*  85 */     String uuid = GenerateUUID.getInstance().getUUID();
/*  86 */     this.logger.debug("[name] : " + file.getName());
/*  87 */     this.logger.debug("[fileUUID] : " + uuid);
/*  88 */     this.logger.debug("[origFileName] : " + request.getParameter(sourceFileName));
/*  89 */     String[] location = createFileName(request, uuid);
/*     */ 
/*  91 */     String realPath = location[0];
/*     */     try
/*     */     {
/*  96 */       FileOutputStream fos = new FileOutputStream(realPath);
/*  97 */       InputStream is = new FileInputStream(file);
/*     */ 
/*  99 */       byte[] b = new byte[1048576];
/* 100 */       int i = 0;
/*     */ 
/* 102 */       while ((i = is.read(b)) > 0)
/*     */       {
/* 104 */         fos.write(b, 0, i);
/*     */       }
/*     */ 
/* 107 */       fos.close();
/* 108 */       is.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 112 */       e.printStackTrace();
/* 113 */       throw e;
/*     */     }
/*     */ 
/* 116 */     return uuid;
/*     */   }
/*     */ 
/*     */   private String getSysDir() {
/* 120 */     String sysDir = this.systemParameterConfiguration.getProperty("file_upload_dir");
/* 121 */     if (StringUtils.isEmpty(sysDir)) {
/* 122 */       this.logger.fatal("system's [file_upload_dir] not configurate in systemParameterConfiguration.properties!");
/* 123 */       this.logger.fatal("using default: c:\\");
/* 124 */       sysDir = "c:\\";
/*     */     }
/* 126 */     return sysDir;
/*     */   }
/*     */ 
/*     */   private String getFileName(HttpServletRequest request, String position)
/*     */   {
/* 131 */     String f = getSysDir() + position;
/* 132 */     return f;
/*     */   }
/*     */ 
/*     */   private String[] createFileName(HttpServletRequest request, String position)
/*     */   {
/* 137 */     File file = new File(getSysDir());
/*     */ 
/* 139 */     if (!file.exists()) {
/* 140 */       file.mkdir();
/*     */     }
/*     */ 
/* 143 */     String[] paths = new String[2];
/* 144 */     paths[0] = (file.getPath() + File.separator + position);
/*     */ 
/* 146 */     return paths;
/*     */   }
/*     */ 
/*     */   public void delete(HttpServletRequest request, String position)
/*     */   {
/* 151 */     File file = new File(getSysDir() + position);
/* 152 */     if (file.isFile()) {
/* 153 */       boolean success = file.delete();
/* 154 */       if (!success)
/* 155 */         this.logger.debug("delete file " + position + " failed!");
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isFileValid(File file)
/*     */   {
/* 162 */     return false;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.pluto.service.file.pojo.DefaultFileTransportManager
 * JD-Core Version:    0.6.2
 */