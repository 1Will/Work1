/*     */ package com.yongjun.tdms.service.activitiFlow.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.activitiFlow.RunPointDao;
/*     */ import com.yongjun.tdms.dao.workflow.point.PointDao;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Point;
import com.yongjun.tdms.service.activitiFlow.RunPointManager;
/*     */ import com.yongjun.tdms.service.workflow.point.PointManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;

import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultRunPointManager extends BaseManager
/*     */   implements RunPointManager
/*     */ {
/*     */   private RunPointDao runPointDao;
/*     */ 
/*     */   public DefaultRunPointManager(RunPointDao runPointDao)
/*     */   {
/*  60 */     this.runPointDao = runPointDao;
/*     */   }
/*     */ 
/*     */   public void setRunPointDao(RunPointDao runPointDao)
/*     */   {
/*  68 */     this.runPointDao = runPointDao;
/*     */   }
/*     */ 
/*     */   public RunPointDao geRunPointDao()
/*     */   {
/*  76 */     return this.runPointDao;
/*     */   }
/*     */ 
/*     */ 
/*     */   public void deleteRunPoint(RunPoint point)
/*     */   {
/* 116 */     this.runPointDao.deletePoint(point);
/*     */   }
/*     */ 
/*     */ 
/*     */   public List<RunPoint> loadAllPoints()
/*     */   {
/* 136 */     return this.runPointDao.loadAllPoints();
/*     */   }
/*     */ 
/*     */   public List<RunPoint> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 149 */     return this.runPointDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<RunPoint> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 162 */     return this.runPointDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   public String disabled(List<RunPoint> pointList)
/*     */   {
/*     */     try
/*     */     {
/* 241 */       for (RunPoint point : pointList)
/*     */       {
/* 246 */           point.setDisabled(true);
/* 247 */           this.runPointDao.storeRunPoint(point);
/*     */         }
/*     */       }
/*     */     catch (Exception e)
/*     */     {
/* 258 */       e.printStackTrace();
/* 259 */       return "error";
/*     */     }
/* 261 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enabled(List<RunPoint> pointList)
/*     */   {
/*     */     try
/*     */     {
/* 272 */       for (RunPoint point : pointList)
/*     */       {
/* 274 */         point.setDisabled(false);
/* 275 */         this.runPointDao.storeRunPoint(point);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 280 */       e.printStackTrace();
/* 281 */       return "error";
/*     */     }
/* 283 */     return "success";
/*     */   }
/*     */
public void storeRunPoint(RunPoint paramPoint) {
	// TODO Auto-generated method stub
	this.runPointDao.storeRunPoint(paramPoint);
	
}
public void deleteAllRunPoints(Collection<RunPoint> paramCollection) {
	 for (RunPoint point : paramCollection)
	     {
		 int myNum  = point.getMyNum();//获取当前要删除的审批预节点的顺序号
	    this.runPointDao.deletePoint(point);//删除当前审批预节点
	    String hql = "from RunPoint r where r.bussnessId = " + point.getBussnessId() 
		+ " and r.flow.id = " + point.getFlow().getId() + " and r.myNum >" + myNum;// 拼接
														// 查询删除的预节点以后的所有审批预节点
	    List<RunPoint> nextRunPoints = this.runPointDao.loadAllByHql(hql);
	    if(nextRunPoints!=null&&nextRunPoints.size()>0){
	    for(RunPoint nextRunPoint : nextRunPoints){
	    	//将查询结果遍历循环，同时将每个结果的顺序号减少1 保证顺序号是按照连续有效数字的
	    	nextRunPoint.setMyNum(nextRunPoint.getMyNum()-1);//
	    	  this.runPointDao.storeRunPoint(nextRunPoint);
	    }
	    }
	    
   }
	
}
	public List<RunPoint> loadAllRunPoints(Long[] paramArrayOfLong) {
		// TODO Auto-generated method stub
		 return this.runPointDao.loadAllPoints(paramArrayOfLong);
	}
	public RunPoint loadRunPoint(Long paramLong) {
		// TODO Auto-generated method stub
		return this.runPointDao.loadRunPoint(paramLong);
	}
	public void saveOrderNum(String id1, String id2) {
		if((!"".equals(id1) && id1 != null) && (!"".equals(id2) && id2 != null)){
			   RunPoint runPoint1 = runPointDao.loadRunPoint(Long.parseLong(id1));
			   RunPoint runPoint2 = runPointDao.loadRunPoint(Long.parseLong(id2));
			   int myNum1 = runPoint1.getMyNum();
			   int myNum2 = runPoint2.getMyNum();
			   
			   runPoint1.setMyNum(myNum2);
			   runPoint2.setMyNum(myNum1);
			   this.runPointDao.storeRunPoint(runPoint1);
			   this.runPointDao.storeRunPoint(runPoint2);
			   }
	} 


}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.pojo.DefaultPointManager
 * JD-Core Version:    0.6.2
 */