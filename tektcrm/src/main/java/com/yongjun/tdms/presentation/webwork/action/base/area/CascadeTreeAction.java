/*     */ package com.yongjun.tdms.presentation.webwork.action.base.area;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.webwork.action.BaseAction;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CascadeTreeAction extends BaseAction
/*     */ {
/*     */   private static final long serialVersionUID = 7661084478811663169L;
/*     */   private final AreaManager areaManager;
/*     */ 
/*     */   public CascadeTreeAction(AreaManager areaManager)
/*     */   {
/*  47 */     this.areaManager = areaManager;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  55 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Area> getAllCountrys()
/*     */   {
/*  64 */     List countrys = null;
/*     */     try {
/*  66 */       countrys = this.areaManager.loadByKey("type", "country");
/*     */     }
/*     */     catch (DaoException e) {
/*  69 */       e.printStackTrace();
/*  70 */       countrys = new ArrayList();
/*     */     }
/*  72 */     if (countrys == null) {
/*  73 */       countrys = new ArrayList();
/*     */     }
/*  75 */     return countrys;
/*     */   }
/*     */ 
/*     */   public List<Area> getAllProvinces()
/*     */   {
/*  84 */     List provinces = null;
/*     */     try {
/*  86 */       provinces = this.areaManager.loadByKey("type", "province");
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/*  90 */       e.printStackTrace();
/*  91 */       provinces = new ArrayList();
/*     */     }
/*  93 */     if (provinces == null) {
/*  94 */       provinces = new ArrayList();
/*     */     }
/*  96 */     return provinces;
/*     */   }
/*     */ 
/*     */   public List<Area> getAllCitys()
/*     */   {
/* 105 */     List citys = new ArrayList();
/*     */     try {
/* 107 */       citys = this.areaManager.loadByKey("type", "city");
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 111 */       e.printStackTrace();
/* 112 */       citys = new ArrayList();
/*     */     }
/* 114 */     if (citys == null) {
/* 115 */       citys = new ArrayList();
/*     */     }
/* 117 */     return citys;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.area.CascadeTreeAction
 * JD-Core Version:    0.6.2
 */