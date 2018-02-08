/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.plan;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.plan.Plan;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.plan.PlanManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListPlanAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 7336281025855759139L;
/*     */   private final PlanManager planManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private List<Plan> planList;
/*     */ 
/*     */   public ListPlanAction(PlanManager planManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  64 */     this.planManager = planManager;
/*  65 */     this.codeValueManager = codeValueManager;
/*  66 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  76 */     if (hasIds("planIds")) {
/*  77 */       this.planList = this.planManager.loadAllPlan(getIds("planIds"));
/*     */     }
/*     */     else
/*     */     {
/*  81 */       this.planList = new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  89 */     if (isDisabled()) {
/*  90 */       return disable();
/*     */     }
/*  92 */     if (isEnable()) {
/*  93 */       return enable();
/*     */     }
/*  95 */     if (isDelete()) {
/*  96 */       return delete();
/*     */     }
/*  98 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 106 */     return "plan";
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/*     */     try
/*     */     {
/* 114 */       this.planManager.disabledAllPlan(this.planList);
/*     */     }
/*     */     catch (Exception e) {
/* 117 */       e.printStackTrace();
/*     */     }
/* 119 */     addActionMessage(getText("disabled.success"));
/* 120 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 128 */       this.planManager.deleteAllPlan(this.planList);
/* 129 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/* 133 */       addActionMessage(getText("delete.error"));
/*     */     }
/* 135 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/* 142 */     this.planManager.enabledAllPlan(this.planList);
/* 143 */     addActionMessage(getText("enabled.success"));
/* 144 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.plan.ListPlanAction
 * JD-Core Version:    0.6.2
 */