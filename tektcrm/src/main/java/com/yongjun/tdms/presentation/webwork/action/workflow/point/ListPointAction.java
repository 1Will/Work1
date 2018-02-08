/*     */ package com.yongjun.tdms.presentation.webwork.action.workflow.point;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workflow.Flow;
/*     */ import com.yongjun.tdms.model.workflow.Point;
/*     */ import com.yongjun.tdms.service.workflow.flow.FlowManager;
/*     */ import com.yongjun.tdms.service.workflow.point.PointManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListPointAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private PointManager pointManager;
/*     */   private FlowManager flowManager;
/*     */   private List<Point> pointList;
/*     */   private Flow flow;
/*     */   private String backvist;
/*     */   public ListPointAction(PointManager pointManager, FlowManager flowManager)
/*     */   {
/*  67 */     this.pointManager = pointManager;
/*  68 */     this.flowManager = flowManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  77 */     return "point";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  85 */     Map map = super.getRequestParameterMap();
/*  86 */     if (null != this.flow) {
/*  87 */       map.put("flow.id", this.flow.getId());
/*     */     }
/*  89 */     return map;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 100 */     if (hasIds("pointIds"))
/*     */     {
/* 102 */       this.pointList = this.pointManager.loadAllPoints(getIds("pointIds"));
/*     */     }
/* 104 */     System.out.println(hasId("flow.id"));
/* 105 */     System.out.println(this.request.getParameter("flow.id"));
/* 106 */     if (hasId("flow.id")) {
/* 107 */       Long flowId = getId("flow.id");
/* 108 */       this.flow = this.flowManager.loadFlow(flowId);
/*     */     }
				backvist=request.getParameter("backvist");
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 122 */       System.out.println(this.pointList);
/* 123 */       this.pointManager.deleteAllPoints(this.pointList);
/* 124 */       addActionMessage(getText("point.delete.success"));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 128 */       e.printStackTrace();
/* 129 */       addActionMessage(getText("point.delete.error"));
/* 130 */       return "error";
/*     */     }
/* 132 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/* 143 */     String result = this.pointManager.disabled(this.pointList);
/* 144 */     if (result.equals("success"))
/*     */     {
/* 146 */       addActionMessage(getText("point.disabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 150 */       addActionMessage(getText("point.disabled.error"));
/*     */     }
/* 152 */     return result;
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/* 163 */     String result = this.pointManager.enabled(this.pointList);
/* 164 */     if (result.equals("success"))
/*     */     {
/* 166 */       addActionMessage(getText("point.enabled.success"));
/*     */     }
/*     */     else
/*     */     {
/* 170 */       addActionMessage(getText("point.enabled.error"));
/*     */     }
/* 172 */     return result;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 186 */     if (isDelete())
/*     */     {
/* 188 */       return delete();
/*     */     }
/* 190 */     if (isDisabled())
/*     */     {
/* 192 */       return disabled();
/*     */     }
/* 194 */     if (isEnable())
/*     */     {
/* 196 */       return enabled();
/*     */     }
/* 198 */     return "success";
/*     */   }
public String getBackvist() {
	return backvist;
}
public void setBackvist(String backvist) {
	this.backvist = backvist;
}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workflow.point.ListPointAction
 * JD-Core Version:    0.6.2
 */