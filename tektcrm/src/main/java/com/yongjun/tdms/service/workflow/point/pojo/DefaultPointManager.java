/*     */ package com.yongjun.tdms.service.workflow.point.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.workflow.point.PointDao;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Point;
/*     */ import com.yongjun.tdms.service.workflow.point.PointManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultPointManager extends BaseManager
/*     */   implements PointManager
/*     */ {
/*     */   private static final String ERROR = "error";
/*     */   private static final String SUCCESS = "success";
/*     */   private PointDao pointDao;
/*     */ 
/*     */   public DefaultPointManager(PointDao pointDao)
/*     */   {
/*  60 */     this.pointDao = pointDao;
/*     */   }
/*     */ 
/*     */   public void setPointDao(PointDao pointDao)
/*     */   {
/*  68 */     this.pointDao = pointDao;
/*     */   }
/*     */ 
/*     */   public PointDao getPointDao()
/*     */   {
/*  76 */     return this.pointDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllPoints(Collection<Point> points)
/*     */   {
/*  96 */     for (Point point : points)
/*     */     {
/* 104 */       getPointDao().deletePoint(point);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deletePoint(Point point)
/*     */   {
/* 116 */     getPointDao().deletePoint(point);
/*     */   }
/*     */ 
/*     */   public List<Point> loadAllPoints(Long[] pointIds)
/*     */   {
/* 127 */     return getPointDao().loadAllPoints(pointIds);
/*     */   }
/*     */ 
/*     */   public List<Point> loadAllPoints()
/*     */   {
/* 136 */     return getPointDao().loadAllPoints();
/*     */   }
/*     */ 
/*     */   public List<Point> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 149 */     return getPointDao().loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Point> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 162 */     return getPointDao().loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Point loadPoint(Long pointId)
/*     */   {
/* 172 */     return getPointDao().loadPoint(pointId);
/*     */   }
/*     */ 
/*     */   public void storePoint(Point point)
/*     */   {
/* 181 */     Long pointId = point.getId();
/* 182 */     getPointDao().storePoint(point);
/* 183 */     if (null == pointId)
/*     */     {
/* 185 */       String[] keyNames = new String[2];
/* 186 */       keyNames[0] = "flow.id";
/* 187 */       keyNames[1] = "myNum";
/* 188 */       List keyValues = new ArrayList();
/* 189 */       keyValues.add(point.getFlow().getId());
/* 190 */       keyValues.add(Long.valueOf(-1L));
/* 191 */       List pointList = null;
/* 192 */       List pointStartAndLastList = null;
/*     */       try {
/* 194 */         pointList = loadByKeyArray(keyNames, keyValues.toArray());
/* 195 */         pointStartAndLastList = loadByKey("flow.id", point.getFlow().getId());
/*     */       } catch (Exception e) {
/* 197 */         this.logger.info("节点查询出错！");
/*     */       }
/*     */ 
/* 200 */       if (pointStartAndLastList.size() == 1) {
/* 201 */         point.setMyNum(pointStartAndLastList.size());
/*     */       }
/* 203 */       if (pointStartAndLastList.size() >= 2) {
/* 204 */         pointList = pointStartAndLastList;
/*     */ 
/* 206 */         Point lastPoint = new Point();
/* 207 */         Point beginPoint = new Point();
/*     */ 
/* 217 */         point.setMyNum(pointList.size());
/* 218 */         point.setPreviousPointId(((Point)pointList.get(pointList.size() - 2)).getId());
/* 219 */         point.setRearPointId(Long.valueOf(point.getId().longValue() + 1L));
/* 220 */         lastPoint = (Point)pointList.get(pointList.size() - 2);
/* 221 */         lastPoint.setRearPointId(point.getId());
/*     */ 
/* 223 */         getPointDao().storePoint(point);
/*     */ 
/* 225 */         getPointDao().storePoint(lastPoint);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String disabled(List<Point> pointList)
/*     */   {
/*     */     try
/*     */     {
/* 241 */       for (Point point : pointList)
/*     */       {
/* 243 */         if (point.getOpenOrNot() == 0)
/*     */         {
/* 245 */           point.setOpenOrNot(1);
/* 246 */           point.setDisabled(true);
/* 247 */           this.pointDao.storePoint(point);
/*     */         }
/*     */         else
/*     */         {
/* 251 */           point.setDisabled(true);
/* 252 */           this.pointDao.storePoint(point);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 258 */       e.printStackTrace();
/* 259 */       return "error";
/*     */     }
/* 261 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enabled(List<Point> pointList)
/*     */   {
/*     */     try
/*     */     {
/* 272 */       for (Point point : pointList)
/*     */       {
/* 274 */         point.setDisabled(false);
/* 275 */         this.pointDao.storePoint(point);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 280 */       e.printStackTrace();
/* 281 */       return "error";
/*     */     }
/* 283 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.pojo.DefaultPointManager
 * JD-Core Version:    0.6.2
 */