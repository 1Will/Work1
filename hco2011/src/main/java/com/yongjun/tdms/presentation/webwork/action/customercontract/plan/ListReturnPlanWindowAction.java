/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.plan;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
/*     */ import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListReturnPlanWindowAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final ReturnPlanManager returnPlanManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<ReturnPlan> returnPlanes;
/*     */ 
/*     */   public ListReturnPlanWindowAction(ReturnPlanManager returnPlanManager, CodeValueManager codeValueManager)
/*     */   {
/*  47 */     this.returnPlanManager = returnPlanManager;
/*  48 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  56 */     if (hasIds("returnPlanIds"))
/*  57 */       this.returnPlanes = this.returnPlanManager.loadAllReturnPlans(getIds("returnPlanIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  67 */     return "returnPlaneWindows";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  78 */     if (isDisabled()) {
/*  79 */       disabled();
/*     */     }
/*  81 */     if (isEnable()) {
/*  82 */       enabled();
/*     */     }
/*  84 */     if (isDelete()) {
/*  85 */       delete();
/*     */     }
/*  87 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  97 */       this.returnPlanManager.deleteAllReturnPlans(this.returnPlanes);
/*  98 */       addActionMessage(getText("returnPlan.delete.success"));
/*  99 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 101 */       addActionMessage(getText("returnPlan.delete.error"));
/* 102 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 112 */       this.returnPlanManager.disabledAllReturnPlan(this.returnPlanes);
/* 113 */       addActionMessage(getText("returnPlan.disabled.success"));
/* 114 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 116 */       addActionMessage(getText("returnPlan.disabled.error"));
/* 117 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 127 */       this.returnPlanManager.enabledAllReturnPlan(this.returnPlanes);
/* 128 */       addActionMessage(getText("returnPlan.enabled.success"));
/* 129 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 131 */       e.printStackTrace();
/* 132 */       addActionMessage(getText("returnPlan.enabled.error"));
/* 133 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayment()
/*     */   {
/* 141 */     List codes = null;
/*     */     try {
/* 143 */       codes = new ArrayList();
/* 144 */       List one = this.codeValueManager.loadByKey("code", "065");
/*     */ 
/* 146 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 148 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 150 */         if ((null != list) && (list.size() > 0)) {
/* 151 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 154 */       CodeValue cv = new CodeValue();
/* 155 */       cv.setId(Long.valueOf(-1L));
/* 156 */       cv.setName(getText("select.option.all"));
/* 157 */       codes.add(0, cv);
/* 158 */       return codes;
/*     */     } catch (DaoException e) {
/* 160 */       e.printStackTrace();
/*     */     }
/* 162 */     return codes;
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 171 */     Map map = super.getRequestParameterMap();
/* 172 */     if (hasId("isOrNot")) {
/* 173 */       if (getId("isOrNot").equals(Long.valueOf("2")))
/* 174 */         map.put("cbv", null);
/*     */       else {
/* 176 */         map.put("cbv", Long.valueOf(getId("isOrNot").longValue()));
/*     */       }
/*     */     }
/* 179 */     return map;
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> getReturnPlanes()
/*     */   {
/* 187 */     return this.returnPlanes;
/*     */   }
/*     */ 
/*     */   public void setReturnPlanes(List<ReturnPlan> returnPlanes)
/*     */   {
/* 196 */     this.returnPlanes = returnPlanes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.plan.ListReturnPlanWindowAction
 * JD-Core Version:    0.6.2
 */