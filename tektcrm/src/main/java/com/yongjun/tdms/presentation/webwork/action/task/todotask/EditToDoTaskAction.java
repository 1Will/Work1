/*     */ package com.yongjun.tdms.presentation.webwork.action.task.todotask;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.task.Task;
/*     */ import com.yongjun.tdms.service.task.TaskManager;
/*     */ import java.util.Arrays;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditToDoTaskAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 732668911930478662L;
/*     */   private TaskManager taskManager;
/*     */   private Task toDoTask;
/*  50 */   private boolean first = true;
/*     */ 
/*     */   public EditToDoTaskAction(TaskManager taskManager)
/*     */   {
/*  58 */     this.taskManager = taskManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  70 */     if (null == this.toDoTask)
/*     */     {
/*  72 */       if (hasId("toDoTask.id"))
/*     */       {
/*  74 */         this.toDoTask = this.taskManager.loadTask(getId("toDoTask.id"));
/*  75 */         setFirst(false);
/*     */       }
/*     */       else
/*     */       {
/*  79 */         this.toDoTask = new Task();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  84 */       this.toDoTask = new Task();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  96 */     boolean isNew = this.toDoTask.isNew();
/*     */ 
/*  98 */     String flag = this.request.getParameter("toDoTask.agreeOrNot");
/*  99 */     if (flag.equals("0"))
/* 100 */       this.toDoTask.setAgreeOrNot(0);
/*     */     else {
/* 102 */       this.toDoTask.setAgreeOrNot(1);
/*     */     }
/* 104 */     this.toDoTask.setStatue(1);
/*     */     try
/*     */     {
/* 107 */       if (isNew) {
/* 108 */         this.taskManager.storeTask(this.toDoTask);
/* 109 */         addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.toDoTask.getCode() })));
/*     */ 
/* 111 */         return "new";
/*     */       }
/* 113 */       this.taskManager.storeTask(this.toDoTask);
/* 114 */       addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.toDoTask.getCode() })));
/*     */ 
/* 116 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 121 */       if (isNew) {
/* 122 */         addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.toDoTask.getCode() })));
/*     */       }
/*     */       else
/*     */       {
/* 126 */         addActionMessage(getText("edit.error", Arrays.asList(new Object[] { this.toDoTask.getCode() })));
/*     */       }
/*     */     }
/* 129 */     return "error";
/*     */   }
/*     */ 
/*     */   public Task getToDoTask()
/*     */   {
/* 138 */     return this.toDoTask;
/*     */   }
/*     */ 
/*     */   public void setToDoTask(Task toDoTask)
/*     */   {
/* 146 */     this.toDoTask = toDoTask;
/*     */   }
/*     */ 
/*     */   public boolean isFirst()
/*     */   {
/* 154 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(boolean first)
/*     */   {
/* 162 */     this.first = first;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.task.todotask.EditToDoTaskAction
 * JD-Core Version:    0.6.2
 */