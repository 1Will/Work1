/*     */ package com.yongjun.tdms.service.workflow.flow.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.workflow.flow.FlowDao;
/*     */ import com.yongjun.tdms.dao.workflow.point.PointDao;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.service.workflow.flow.FlowManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultFlowManager extends BaseManager
/*     */   implements FlowManager
/*     */ {
/*     */   private static final String ERROR = "error";
/*     */   private static final String SUCCESS = "success";
/*     */   private FlowDao flowDao;
/*     */   private PointDao pointDao;
/*     */ 
/*     */   public DefaultFlowManager(FlowDao flowDao, PointDao pointDao)
/*     */   {
/*  65 */     this.flowDao = flowDao;
/*  66 */     this.pointDao = pointDao;
/*     */   }
/*     */ 
/*     */   public void setFlowDao(FlowDao flowDao)
/*     */   {
/*  73 */     this.flowDao = flowDao;
/*     */   }
/*     */ 
/*     */   public FlowDao getFlowDao()
/*     */   {
/*  81 */     return this.flowDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllFlows(Collection<Flow> flows)
/*     */   {
/*  91 */     getFlowDao().deleteAllFlows(flows);
/*     */   }
/*     */ 
/*     */   public void deleteFlow(Flow flow)
/*     */   {
/* 101 */     getFlowDao().deleteFlow(flow);
/*     */   }
/*     */ 
/*     */   public List<Flow> loadAllFlows(Long[] flowIds)
/*     */   {
/* 112 */     return getFlowDao().loadAllFlows(flowIds);
/*     */   }
/*     */ 
/*     */   public List<Flow> loadAllFlows()
/*     */   {
/* 121 */     return getFlowDao().loadAllFlows();
/*     */   }
/*     */ 
/*     */   public List<Flow> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 134 */     return getFlowDao().loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Flow> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 147 */     return getFlowDao().loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Flow loadFlow(Long flowId)
/*     */   {
/* 157 */     return getFlowDao().loadFlow(flowId);
/*     */   }
/*     */ 
/*     */   public void storeFlow(Flow flow)
/*     */   {
/* 168 */     getFlowDao().storeFlow(flow);
/*     */   }
/*     */ 
/*     */   public String disabled(List<Flow> flowList)
/*     */   {
/*     */     try
/*     */     {
/* 181 */       for (Flow flow : flowList)
/*     */       {
/* 183 */         if (flow.getOpenOrNot() == 0)
/*     */         {
/* 185 */           flow.setOpenOrNot(1);
/* 186 */           flow.setDisabled(true);
/* 187 */           this.flowDao.storeFlow(flow);
/*     */         }
/*     */         else
/*     */         {
/* 191 */           flow.setDisabled(true);
/* 192 */           this.flowDao.storeFlow(flow);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 198 */       e.printStackTrace();
/* 199 */       return "error";
/*     */     }
/* 201 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enabled(List<Flow> flowList)
/*     */   {
/*     */     try
/*     */     {
/* 212 */       for (Flow flow : flowList)
/*     */       {
/* 214 */         flow.setDisabled(false);
/* 215 */         this.flowDao.storeFlow(flow);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 220 */       e.printStackTrace();
/* 221 */       return "error";
/*     */     }
/* 223 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.flow.pojo.DefaultFlowManager
 * JD-Core Version:    0.6.2
 */