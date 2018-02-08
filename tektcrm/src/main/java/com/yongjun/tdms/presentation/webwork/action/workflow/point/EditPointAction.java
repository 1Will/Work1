/*     */ package com.yongjun.tdms.presentation.webwork.action.workflow.point;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Point;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.workflow.flow.FlowManager;
/*     */ import com.yongjun.tdms.service.workflow.point.PointManager;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditPointAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 732668911930478662L;
/*     */   private PointManager pointManager;
/*     */   private PersonnelFilesManager personnelFilesManager;
/*     */   private FlowManager flowManager;
/*     */   private Point point;
/*     */   private Long flowId;
/*     */   private String backvist;
/*     */   public EditPointAction(PointManager pointManager, PersonnelFilesManager personnelFilesManager, FlowManager flowManager)
/*     */   {
/*  51 */     this.pointManager = pointManager;
/*  52 */     this.personnelFilesManager = personnelFilesManager;
/*  53 */     this.flowManager = flowManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
	         backvist=request.getParameter("backvist");
/*  59 */     if (null == this.point)
/*     */     {
/*  61 */       if (hasId("point.id"))
/*     */       {
/*  63 */         this.point = this.pointManager.loadPoint(getId("point.id"));
/*  64 */         this.flowId = this.point.getFlow().getId();
/*     */       } else {
/*  66 */         if ((null != this.request.getParameter("flow.id")) && ("" != this.request.getParameter("flow.id"))) {
/*  67 */           this.flowId = Long.valueOf(this.request.getParameter("flow.id"));
/*     */         }
/*  69 */         this.point = new Point();
/*     */       }
/*     */     } else {
/*  72 */       this.flowId = Long.valueOf(this.request.getParameter("flow.id"));
/*  73 */       this.point = new Point();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  80 */     boolean isNew = this.point.isNew();
/*     */     try
/*     */     {
/*  83 */       if (hasId("point.personnelFiles"))
/*     */       {
/*  85 */         if (this.request.getParameter("flag").equals("0")) {
/*  86 */           this.point.setPersonnelFiles(this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("point.personnelFiles"))));
/*     */         }
/*     */         else
/*     */         {
/*  91 */           this.point.setPersonnelFiles(this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("point.personnelFiles.id"))));
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  97 */       if (hasId("flow.id"))
/*     */       {
/*  99 */         this.point.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request.getParameter("flow.id"))));
/*     */       }
/*     */ 
/* 102 */       if (isNew)
/*     */       {
/* 113 */         this.pointManager.storePoint(this.point);
/*     */       }
/*     */       else
/*     */       {
/* 137 */         this.pointManager.storePoint(this.point);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 144 */       e.printStackTrace();
/* 145 */       addActionMessage(getText("point.add.error", Arrays.asList(new Object[] { this.point.getCode() })));
/*     */ 
/* 147 */       return "error";
/*     */     }
/*     */ 
/* 150 */     if (isNew)
/*     */     {
/* 152 */       addActionMessage(getText("point.add.success", Arrays.asList(new Object[] { this.point.getCode() })));
/*     */ 
/* 154 */       return "new";
/*     */     }
/*     */ 
/* 158 */     addActionMessage(getText("point.edit.success", Arrays.asList(new Object[] { this.point.getCode() })));
/*     */ 
/* 160 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Point> getAllPreviousPoint()
/*     */   {
/* 167 */     List pointList = this.pointManager.loadAllPoints();
/* 168 */     return pointList;
/*     */   }
/*     */ 
/*     */   public List<Point> getAllRearPoint()
/*     */   {
/* 173 */     List pointList = this.pointManager.loadAllPoints();
/* 174 */     return pointList;
/*     */   }
/*     */ 
/*     */   public Point getPoint()
/*     */   {
/* 182 */     return this.point;
/*     */   }
/*     */ 
/*     */   public void setPoint(Point point)
/*     */   {
/* 190 */     this.point = point;
/*     */   }
/*     */ 
/*     */   public Long getFlowId()
/*     */   {
/* 198 */     return this.flowId;
/*     */   }
/*     */ 
/*     */   public void setFlowId(Long flowId)
/*     */   {
/* 206 */     this.flowId = flowId;
/*     */   }
public String getBackvist() {
	return backvist;
}
public void setBackvist(String backvist) {
	this.backvist = backvist;
}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workflow.point.EditPointAction
 * JD-Core Version:    0.6.2
 */