/*     */ package com.yongjun.tdms.service.task.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.task.TaskDao;
/*     */ import com.yongjun.tdms.dao.workflow.flow.FlowDao;
/*     */ import com.yongjun.tdms.dao.workflow.point.PointDao;
/*     */ import com.yongjun.tdms.dao.workflow.workflowcase.WorkflowCaseDao;
/*     */ import com.yongjun.tdms.model.historytask.HistoryTask;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.task.Task;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Point;
/*     */ import com.yongjun.tdms.model.workflow.Workflow;
/*     */ import com.yongjun.tdms.model.workflow.WorkflowCase;
/*     */ import com.yongjun.tdms.service.historytask.HistoryTaskManager;
/*     */ import com.yongjun.tdms.service.task.TaskManager;
/*     */ import com.yongjun.tdms.service.task.UpdateManager;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultTaskManager extends BaseManager
/*     */   implements TaskManager
/*     */ {
/*     */   private static final String ERROR = "error";
/*     */   private static final String SUCCESS = "success";
/*     */   private TaskDao taskDao;
/*     */   private FlowDao flowDao;
/*     */   private PointDao pointDao;
/*     */   private WorkflowCaseDao workflowCaseDao;
/*     */   private CodeValueManager codeValueManager;
/*     */   private UpdateManager updateManager;
/*     */   private HistoryTaskManager historyTaskManager;
/*     */ 
/*     */   public DefaultTaskManager(TaskDao taskDao, FlowDao flowDao, PointDao pointDao, WorkflowCaseDao workflowCaseDao, CodeValueManager codeValueManager, UpdateManager updateManager, HistoryTaskManager historyTaskManager)
/*     */   {
/* 106 */     this.taskDao = taskDao;
/* 107 */     this.flowDao = flowDao;
/* 108 */     this.pointDao = pointDao;
/* 109 */     this.workflowCaseDao = workflowCaseDao;
/* 110 */     this.codeValueManager = codeValueManager;
/* 111 */     this.updateManager = updateManager;
/* 112 */     this.historyTaskManager = historyTaskManager;
/*     */   }
/*     */ 
/*     */   public void setTaskDao(TaskDao taskDao)
/*     */   {
/* 119 */     this.taskDao = taskDao;
/*     */   }
/*     */ 
/*     */   public TaskDao getTaskDao()
/*     */   {
/* 127 */     return this.taskDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllTasks(Collection<Task> tasks)
/*     */   {
/* 137 */     getTaskDao().deleteAllTasks(tasks);
/*     */   }
/*     */ 
/*     */   public void deleteTask(Task task)
/*     */   {
/* 147 */     getTaskDao().deleteTask(task);
/*     */   }
/*     */ 
/*     */   public List<Task> loadAllTasks(Long[] taskIds)
/*     */   {
/* 158 */     return getTaskDao().loadAllTasks(taskIds);
/*     */   }
/*     */ 
/*     */   public List<Task> loadAllTasks()
/*     */   {
/* 167 */     return getTaskDao().loadAllTasks();
/*     */   }
/*     */ 
/*     */   public List<Task> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 180 */     return getTaskDao().loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Task> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 193 */     return getTaskDao().loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public Task loadTask(Long taskId)
/*     */   {
/* 203 */     return getTaskDao().loadTask(taskId);
/*     */   }
/*     */ 
/*     */   public void storeTask(Task task)
/*     */   {
/* 214 */     WorkflowCase workflowCase = task.getWorkflowCase();
/* 215 */     getTaskDao().storeTask(task);
/*     */ 
/* 217 */     Flow flow = task.getFlow();
/* 218 */     System.out.println(flow.getId());
/* 219 */     Point point = task.getPoint();
/*     */ 
/* 221 */     String code = workflowCase.getWorkflow().getAffectObject().getCode();
/* 222 */     Long id = Long.valueOf(workflowCase.getInitiatorId());
/*     */ 
/* 225 */     if (task.getAgreeOrNot() == 1)
/*     */     {
/* 229 */       if (task.getCompleteReview() == 0) {
/* 230 */         String[] pointKeyNames = new String[1];
/* 231 */         Object[] pointKeyValues = new Object[1];
/* 232 */         pointKeyNames[0] = "flow";
/* 233 */         pointKeyValues[0] = flow;
/*     */         try {
/* 235 */           List points = this.pointDao.loadByKeyArray(pointKeyNames, pointKeyValues);
/*     */ 
/* 237 */           for (int i = point.getMyNum(); i < points.size(); i++)
/*     */           {
/* 240 */             if (null != ((Point)points.get(i)).getRearPointId())
/*     */             {
/* 243 */               if (((Point)points.get(i)).getOpenOrNot() == 0) {
/* 244 */                 Task taskNew = new Task();
/* 245 */                 taskNew.setWorkflowCase(workflowCase);
/* 246 */                 taskNew.setFlow(flow);
/* 247 */                 taskNew.setCode(((Point)points.get(i)).getCode() + "-" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString().trim());
/* 248 */                 taskNew.setName(((Point)points.get(i)).getName());
/* 249 */                 taskNew.setPoint((Point)points.get(i));
/*     */ 
/* 251 */                 taskNew.setStatue(0);
/* 252 */                 taskNew.setAgreeOrNot(0);
/*     */ 
/* 254 */                 this.taskDao.storeTask(taskNew);
/* 255 */                 this.taskDao.storeTask(task);
/* 256 */                 int cumount = 0;
/* 257 */                 for (int j = ((Point)points.get(i)).getMyNum(); j < points.size(); j++) {
/* 258 */                   int openOrNot = ((Point)points.get(j)).getOpenOrNot();
/* 259 */                   cumount += openOrNot;
/*     */                 }
/* 261 */                 if (cumount != points.size() - ((Point)points.get(i)).getMyNum()) break;
/* 262 */                 this.updateManager.updateObject(code, id, "02001", null); break;
/*     */               }
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 268 */               this.updateManager.updateObject(code, id, "02001", null);
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 273 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       else {
/* 277 */         this.updateManager.updateObject(code, id, "02001", null);
/*     */       }
/*     */     } else {
/* 280 */       String reason = task.getNotAgreeReason().trim();
/* 281 */       this.updateManager.updateObject(code, id, "02002", reason);
/*     */     }
/*     */ 
/* 284 */     if (task.getStatue() == 1) {
/* 285 */       String[] keyNames = new String[2];
/* 286 */       Object[] keyValues = new Object[2];
/* 287 */       keyNames[0] = "workflowCase.id";
/* 288 */       keyNames[1] = "flow.id";
/* 289 */       keyValues[0] = task.getWorkflowCase().getId();
/* 290 */       keyValues[1] = task.getFlow().getId();
/* 291 */       List historyList = new ArrayList();
/* 292 */       int sum = 1;
/*     */       try {
/* 294 */         historyList = this.historyTaskManager.loadByKeyArray(keyNames, keyValues);
/* 295 */         if ((null != historyList) && (!historyList.isEmpty()))
/* 296 */           sum = historyList.size() + 1;
/*     */       }
/*     */       catch (DaoException e) {
/* 299 */         this.logger.info("查询历史任务出错！");
/*     */       }
/* 301 */       String newCode = task.getCode() + String.valueOf(sum);
/* 302 */       HistoryTask historyTask = new HistoryTask();
/* 303 */       historyTask.setCode(newCode);
/* 304 */       historyTask.setName(task.getName());
/* 305 */       historyTask.setWorkflowCase(task.getWorkflowCase());
/* 306 */       historyTask.setFlow(task.getFlow());
/* 307 */       historyTask.setPersonnelFilesName(task.getPoint().getPersonnelFiles().getName());
/* 308 */       historyTask.setStatue(1);
/* 309 */       historyTask.setApproveDate(task.getApproveDate());
/* 310 */       historyTask.setAgreeOrNot(task.getAgreeOrNot());
/* 311 */       historyTask.setNotAgreeReason(task.getNotAgreeReason());
/* 312 */       this.historyTaskManager.storeHistoryTask(historyTask);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String disabled(List<Task> taskList)
/*     */   {
/*     */     try
/*     */     {
/* 364 */       for (Task task : taskList)
/*     */       {
/* 366 */         task.setDisabled(true);
/* 367 */         this.taskDao.storeTask(task);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 372 */       e.printStackTrace();
/* 373 */       return "error";
/*     */     }
/* 375 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enabled(List<Task> taskList)
/*     */   {
/*     */     try
/*     */     {
/* 386 */       for (Task task : taskList)
/*     */       {
/* 388 */         task.setDisabled(false);
/* 389 */         this.taskDao.storeTask(task);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 394 */       e.printStackTrace();
/* 395 */       return "error";
/*     */     }
/* 397 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.task.pojo.DefaultTaskManager
 * JD-Core Version:    0.6.2
 */