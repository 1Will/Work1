/*     */ package com.yongjun.tdms.service.workflow.workflowcase.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.dao.codevalue.CodeValueDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.task.TaskDao;
/*     */ import com.yongjun.tdms.dao.workflow.flow.FlowDao;
/*     */ import com.yongjun.tdms.dao.workflow.point.PointDao;
/*     */ import com.yongjun.tdms.dao.workflow.workflow.WorkflowDao;
/*     */ import com.yongjun.tdms.dao.workflow.workflowcase.WorkflowCaseDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.task.Task;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Point;
/*     */ import com.yongjun.tdms.model.workflow.Workflow;
/*     */ import com.yongjun.tdms.model.workflow.WorkflowCase;
/*     */ import com.yongjun.tdms.service.workflow.workflowcase.WorkflowCaseManager;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultWorkflowCaseManager extends BaseManager
/*     */   implements WorkflowCaseManager
/*     */ {
/*     */   private WorkflowCaseDao workflowCaseDao;
/*     */   private WorkflowDao workflowDao;
/*     */   private PointDao pointDao;
/*     */   private FlowDao flowDao;
/*     */   private TaskDao taskDao;
/*     */   private CodeValueDao codeValueDao;
/*     */ 
/*     */   public DefaultWorkflowCaseManager(WorkflowCaseDao workflowCaseDao, WorkflowDao workflowDao, PointDao pointDao, FlowDao flowDao, TaskDao taskDao, CodeValueDao codeValueDao)
/*     */   {
/*  95 */     this.workflowCaseDao = workflowCaseDao;
/*  96 */     this.workflowDao = workflowDao;
/*  97 */     this.pointDao = pointDao;
/*  98 */     this.flowDao = flowDao;
/*  99 */     this.taskDao = taskDao;
/* 100 */     this.codeValueDao = codeValueDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllWorkflowCases(Collection<WorkflowCase> workflowCases)
/*     */   {
/* 110 */     this.workflowCaseDao.deleteAllWorkflowCases(workflowCases);
/*     */   }
/*     */ 
/*     */   public void deleteWorkflowCase(WorkflowCase workflowCase)
/*     */   {
/* 120 */     this.workflowCaseDao.deleteWorkflowCase(workflowCase);
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadAllWorkflowCases(Long[] workflowCaseIds)
/*     */   {
/* 131 */     return this.workflowCaseDao.loadAllWorkflowCases(workflowCaseIds);
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadAllWorkflowCases()
/*     */   {
/* 140 */     return this.workflowCaseDao.loadAllWorkflowCases();
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 153 */     return this.workflowCaseDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<WorkflowCase> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 166 */     return this.workflowCaseDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public WorkflowCase loadWorkflowCase(Long workflowCaseId)
/*     */   {
/* 176 */     return this.workflowCaseDao.loadWorkflowCase(workflowCaseId);
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode(String code, Long orgId)
/*     */   {
/* 185 */     String maxCode = this.workflowCaseDao.getMaxPFCode(code, orgId);
/* 186 */     if (null != maxCode) {
/* 187 */       int num = Integer.parseInt(maxCode) + 1;
/* 188 */       if (num < 10)
/* 189 */         return "WFC-000" + num;
/* 190 */       if (num < 100)
/* 191 */         return "WFC-00" + num;
/* 192 */       if (num < 1000) {
/* 193 */         return "WFC-0" + num;
/*     */       }
/* 195 */       return "WFC-" + num;
/*     */     }
/*     */ 
/* 199 */     return "WFC-0001";
/*     */   }
/*     */ 
/*     */   public String startWorkflowCase(String initiator, Long initiatorId, PersonnelFiles personnelFiles, String url)
/*     */   {
/* 214 */     String[] keyNames = new String[4];
/* 215 */     Object[] keyValues = new Object[4];
/*     */ 
/* 218 */     keyNames[0] = "affectObject.code";
/*     */ 
/* 220 */     keyNames[1] = "disabled";
/*     */ 
/* 222 */     keyNames[2] = "openOrNot";
/*     */ 
/* 224 */     keyNames[3] = "flow.department.id";
/*     */ 
/* 226 */     keyValues[0] = initiator;
/*     */ 
/* 228 */     keyValues[1] = Boolean.valueOf(false);
/*     */ 
/* 230 */     keyValues[2] = Integer.valueOf(0);
/*     */ 
/* 232 */     keyValues[3] = personnelFiles.getDept().getId();
/* 233 */     List workflowList = new ArrayList();
/*     */     try
/*     */     {
/* 236 */       workflowList = this.workflowDao.loadByKeyArray(keyNames, keyValues);
/*     */     } catch (DaoException e) {
/* 238 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 241 */     if ((null != workflowList) && (!workflowList.isEmpty())) {
/* 242 */       Workflow workflow = null;
/* 243 */       for (int i = 0; i < workflowList.size(); i++) {
/* 244 */         if (((Workflow)workflowList.get(i)).getOpenOrNot() == 0) {
/* 245 */           workflow = (Workflow)workflowList.get(i);
/*     */         }
/*     */       }
/* 248 */       List wfcList = new ArrayList();
/* 249 */       WorkflowCase workflowCase = null;
/*     */       try {
/* 251 */         String[] keyWDCNames = new String[2];
/* 252 */         Object[] keyWDCValues = new Object[2];
/* 253 */         keyWDCNames[0] = "url";
/* 254 */         keyWDCNames[1] = "initiatorId";
/* 255 */         keyWDCValues[0] = url.trim();
/* 256 */         keyWDCValues[1] = initiatorId.toString();
/* 257 */         wfcList = this.workflowCaseDao.loadByKeyArray(keyWDCNames, keyWDCValues);
/*     */       } catch (Exception e) {
/* 259 */         this.logger.info("查询工作流实例失败！");
/*     */       }
/*     */ 
/* 262 */       if ((null != wfcList) && (!wfcList.isEmpty()))
/* 263 */         workflowCase = (WorkflowCase)wfcList.get(0);
/*     */       else {
/* 265 */         workflowCase = new WorkflowCase();
/*     */       }
/* 267 */       String newCode = autoCompleteCode("WFC", personnelFiles.getOrganization().getId());
/*     */ 
/* 269 */       workflowCase.setCode(newCode);
/* 270 */       workflowCase.setName(personnelFiles.getName() + "-" + workflow.getName());
/* 271 */       workflowCase.setOpenOrNot(0);
/* 272 */       workflowCase.setWorkflow(workflow);
/* 273 */       workflowCase.setInitiatorId(initiatorId.toString());
/* 274 */       workflowCase.setDisabled(false);
/* 275 */       workflowCase.setUrl(url);
/* 276 */       List statueList = new ArrayList();
/*     */       try {
/* 278 */         statueList = this.codeValueDao.loadByKey("code", "04700");
/*     */       } catch (DaoException e1) {
/* 280 */         this.logger.info("工作流流程状态查询出错，请检查码表！");
/*     */       }
/* 282 */       CodeValue statue = (CodeValue)statueList.get(0);
/* 283 */       workflowCase.setStatue(statue);
/* 284 */       workflowCase.setOrganization(personnelFiles.getOrganization());
/*     */       try
/*     */       {
/* 288 */         this.workflowCaseDao.storeWorkflowCase(workflowCase);
/* 289 */         Point point = new Point();
/* 290 */         Flow flow = new Flow();
/* 291 */         flow = this.flowDao.loadFlow(workflow.getFlow().getId());
/* 292 */         String[] pointKeyNames = new String[1];
/* 293 */         Object[] pointKeyValues = new Object[1];
/* 294 */         pointKeyNames[0] = "flow";
/*     */ 
/* 296 */         pointKeyValues[0] = workflow.getFlow();
/*     */ 
/* 298 */         List points = this.pointDao.loadByKeyArray(pointKeyNames, pointKeyValues);
/* 299 */         for (int i = 0; i < points.size(); i++) {
/* 300 */           if (this.pointDao.loadPoint(((Point)points.get(i)).getId()).getOpenOrNot() == 0) {
/* 301 */             Long id = ((Point)points.get(i)).getId();
/* 302 */             point = this.pointDao.loadPoint(id);
/* 303 */             break;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 308 */         List taskList = new ArrayList();
/* 309 */         Task task = new Task();
/* 310 */         String[] taskNames = new String[5];
/* 311 */         Object[] taskValues = new Object[5];
/* 312 */         taskNames[0] = "code";
/* 313 */         taskNames[1] = "name";
/* 314 */         taskNames[2] = "workflowCase.id";
/* 315 */         taskNames[3] = "flow.id";
/* 316 */         taskNames[4] = "point.id";
/* 317 */         taskValues[0] = point.getCode();
/* 318 */         taskValues[1] = point.getName();
/* 319 */         taskValues[2] = workflowCase.getId();
/* 320 */         taskValues[3] = flow.getId();
/* 321 */         taskValues[4] = point.getId();
/* 322 */         taskList = this.taskDao.loadByKeyArray(taskNames, taskValues);
/* 323 */         if (null != point)
/*     */         {
/* 326 */           if ((null != taskList) && (!taskList.isEmpty())) {
/* 327 */             task = (Task)taskList.get(0);
/* 328 */           } else if (point.getMyNum() != -1)
/*     */           {
/* 330 */             task.setCode(point.getCode() + "-" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString().trim());
/* 331 */             task.setName(point.getName());
/* 332 */             task.setWorkflowCase(workflowCase);
/* 333 */             task.setFlow(flow);
/* 334 */             task.setPoint(point);
/*     */ 
/* 336 */             task.setStatue(0);
/* 337 */             task.setAgreeOrNot(0);
/* 338 */             this.taskDao.storeTask(task);
/*     */           }
/* 340 */           return "success";
/*     */         }
/* 342 */         return "noPoint";
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 347 */         e.printStackTrace();
/* 348 */         return "startworkflowerror";
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 354 */     return "noworkflow";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.workflowcase.pojo.DefaultWorkflowCaseManager
 * JD-Core Version:    0.6.2
 */