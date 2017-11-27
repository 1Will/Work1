/*     */ package com.yongjun.tdms.service.base.area.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.base.area.AreaDao;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultAreaManager extends BaseManager
/*     */   implements AreaManager
/*     */ {
/*     */   private final AreaDao areaDao;
/*     */ 
/*     */   public DefaultAreaManager(AreaDao areaDao)
/*     */   {
/*  48 */     this.areaDao = areaDao;
/*     */   }
/*     */ 
/*     */   public void storeArea(Area area)
/*     */   {
/*  57 */     this.areaDao.storeArea(area);
/*     */   }
/*     */ 
/*     */   public Area loadArea(Long areaId)
/*     */   {
/*  67 */     return this.areaDao.loadArea(areaId);
/*     */   }
/*     */ 
/*     */   public List<Area> loadAllArea(Long[] areaIds)
/*     */   {
/*  77 */     return this.areaDao.loadAllArea(areaIds);
/*     */   }
/*     */ 
/*     */   public List<Area> loadAllAreas()
/*     */   {
/*  86 */     return this.areaDao.loadAllAreas();
/*     */   }
/*     */ 
/*     */   public void deleteArea(Area area)
/*     */   {
/*  95 */     this.areaDao.deleteArea(area);
/*     */   }
/*     */ 
/*     */   public void deleteAllArea(Collection<Area> areas)
/*     */   {
/* 104 */     this.areaDao.deleteAllArea(areas);
/*     */   }
/*     */ 
/*     */   public List<Area> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.areaDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<Area> loadAreaKeyProperty(String value)
/*     */   {
/* 124 */     Long v = Long.valueOf(value);
/* 125 */     return this.areaDao.loadAreaKeyProperty(v);
/*     */   }
/*     */ 
/*     */   public Long getLatestLogo()
/*     */   {
/* 135 */     return this.areaDao.getLatestLogo();
/*     */   }
/*     */ 
/*     */   public List<Area> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 139 */     return this.areaDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.area.pojo.DefaultAreaManager
 * JD-Core Version:    0.6.2
 */