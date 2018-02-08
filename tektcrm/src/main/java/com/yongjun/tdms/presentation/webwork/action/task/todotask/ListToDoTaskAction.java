/*     */ package com.yongjun.tdms.presentation.webwork.action.task.todotask;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.task.Task;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.task.TaskManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class ListToDoTaskAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private TaskManager taskManager;
/*     */   private UserManager userManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private List<Task> toDoTaskList;
/*     */   private PersonnelFiles personnelFiles;
/*     */ 
/*     */   public ListToDoTaskAction(TaskManager taskManager, UserManager userManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  76 */     this.taskManager = taskManager;
/*  77 */     this.userManager = userManager;
/*  78 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  87 */     return "toDoTask";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  94 */     Map map = super.getRequestParameterMap();
/*  95 */     if (null != this.personnelFiles) {
/*  96 */       map.put("pf.id", this.personnelFiles.getId());
/*     */     }
/*  98 */     if (this.request.getParameter("task.id") != null) {
/*  99 */       int rNId = Integer.valueOf(this.request.getParameter("task.id")).intValue();
/* 100 */       map.put("task.id", Integer.valueOf(rNId));
/*     */     }
/* 102 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 113 */     if (hasIds("toDoTaskIds")) {
/* 114 */       this.toDoTaskList = this.taskManager.loadAllTasks(getIds("toDoTaskIds"));
/*     */     }
/* 116 */     setFirst(false);
/* 117 */     this.personnelFiles = getPersonnelFiles();
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 130 */       this.taskManager.deleteAllTasks(this.toDoTaskList);
/* 131 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 135 */       e.printStackTrace();
/* 136 */       addActionMessage(getText("delete.error"));
/* 137 */       return "error";
/*     */     }
/* 139 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/* 150 */     String result = this.taskManager.disabled(this.toDoTaskList);
/* 151 */     if (result.equals("success"))
/*     */     {
/* 153 */       addActionMessage(getText("disabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 157 */       addActionMessage(getText("disabled.failer"));
/*     */     }
/* 159 */     return result;
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/* 170 */     String result = this.taskManager.enabled(this.toDoTaskList);
/* 171 */     if (result.equals("success"))
/*     */     {
/* 173 */       addActionMessage(getText("enabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 177 */       addActionMessage(getText("enabled.failer"));
/*     */     }
/* 179 */     return result;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 192 */     if (isDelete())
/*     */     {
/* 194 */       return delete();
/*     */     }
/* 196 */     if (isDisabled())
/*     */     {
/* 198 */       return disabled();
/*     */     }
/* 200 */     if (isEnable())
/*     */     {
/* 202 */       return enabled();
/*     */     }
/* 204 */     return "success";
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelFiles()
/*     */   {
/* 211 */     List pfList = null;
/* 212 */     PersonnelFiles rePF = null;
/* 213 */     User user = this.userManager.getUser();
/* 214 */     if (null != user)
/*     */       try {
/* 216 */         String[] keyNames = new String[2];
/* 217 */         Object[] keyValues = new Object[2];
/* 218 */         keyNames[0] = "code";
/* 219 */         keyNames[1] = "disabled";
/* 220 */         keyValues[0] = user.getCode().trim();
/* 221 */         keyValues[1] = Boolean.valueOf(false);
/* 222 */         pfList = this.personnelFilesManager.loadByKeyArray(keyNames, keyValues);
/* 223 */         if ((null != pfList) && (!pfList.isEmpty()));
/* 224 */         return (PersonnelFiles)pfList.get(0);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 228 */         this.logger.info("查询当前登录人的人事档案出错！");
/*     */       }
/*     */     else {
/* 231 */       return null;
/*     */     }
/* 233 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.task.todotask.ListToDoTaskAction
 * JD-Core Version:    0.6.2
 */