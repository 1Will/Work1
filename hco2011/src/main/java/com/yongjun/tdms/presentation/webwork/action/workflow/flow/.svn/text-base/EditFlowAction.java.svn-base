/*     */ package com.yongjun.tdms.presentation.webwork.action.workflow.flow;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.workflow.flow.FlowManager;
/*     */ import com.yongjun.tdms.service.workflow.point.PointManager;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditFlowAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 732668911930478662L;
/*     */   private FlowManager flowManager;
/*     */   private DepartmentManager departmentManager;
/*     */   private PointManager pointManager;
/*     */   private Flow flow;
/*     */ 
/*     */   public EditFlowAction(FlowManager flowManager, DepartmentManager departmentManager, PointManager pointManager)
/*     */   {
/*  71 */     this.flowManager = flowManager;
/*  72 */     this.departmentManager = departmentManager;
/*  73 */     this.pointManager = pointManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  85 */     if (null == this.flow)
/*     */     {
/*  87 */       if (hasId("flow.id"))
/*     */       {
/*  89 */         this.flow = this.flowManager.loadFlow(getId("flow.id"));
/*     */       }
/*     */       else
/*     */       {
/*  93 */         this.flow = new Flow();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  98 */       this.flow = new Flow();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 110 */     if (!StringUtils.isEmpty(this.request.getParameter("flow.department")))
/*     */     {
/* 112 */       Department department = this.departmentManager.loadDepartment(getId("flow.department"));
/* 113 */       this.flow.setDepartment(department);
/*     */     }
/* 115 */     boolean isNew = this.flow.isNew();
/*     */     try
/*     */     {
/* 118 */       if (isNew)
/*     */       {
/* 120 */         if (null == this.flowManager.loadByKey("code", this.flow.getCode()))
/*     */         {
/* 122 */           this.flowManager.storeFlow(this.flow);
/*     */         }
/*     */         else
/*     */         {
/* 155 */           addActionMessage(getText("add.exist", Arrays.asList(new Object[] { this.flow.getCode() })));
/*     */ 
/* 157 */           return "error";
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 162 */         this.flowManager.storeFlow(this.flow);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 167 */       e.printStackTrace();
/* 168 */       addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.flow.getCode() })));
/*     */ 
/* 170 */       return "error";
/*     */     }
/*     */ 
/* 173 */     if (isNew)
/*     */     {
/* 175 */       addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.flow.getCode() })));
/*     */ 
/* 177 */       return "new";
/*     */     }
/*     */ 
/* 181 */     addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.flow.getCode() })));
/*     */ 
/* 183 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepartment()
/*     */   {
/* 194 */     List departments = this.departmentManager.loadAllDepartments();
/* 195 */     return departments;
/*     */   }
/*     */ 
/*     */   public Flow getFlow()
/*     */   {
/* 203 */     return this.flow;
/*     */   }
/*     */ 
/*     */   public void setFlow(Flow flow)
/*     */   {
/* 211 */     this.flow = flow;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workflow.flow.EditFlowAction
 * JD-Core Version:    0.6.2
 */