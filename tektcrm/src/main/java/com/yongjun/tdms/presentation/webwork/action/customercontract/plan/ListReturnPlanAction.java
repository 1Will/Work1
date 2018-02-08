/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.plan;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
/*     */ import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
/*     */ import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListReturnPlanAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final ReturnPlanManager returnPlanManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final FinancialManagementManager financialManagementManager;
/*     */   private List<ReturnPlan> returnPlanes;
/*     */ 
/*     */   public ListReturnPlanAction(ReturnPlanManager returnPlanManager, CodeValueManager codeValueManager, FinancialManagementManager financialManagementManager)
/*     */   {
/*  53 */     this.returnPlanManager = returnPlanManager;
/*  54 */     this.codeValueManager = codeValueManager;
/*  55 */     this.financialManagementManager = financialManagementManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  63 */     if (hasIds("returnPlanIds"))
/*  64 */       this.returnPlanes = this.returnPlanManager.loadAllReturnPlans(getIds("returnPlanIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  74 */     return "returnPlanes";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  85 */     if (isDisabled()) {
/*  86 */       disabled();
/*     */     }
/*  88 */     if (isEnable()) {
/*  89 */       enabled();
/*     */     }
/*  91 */     if (isDelete()) {
/*  92 */       delete();
/*     */     }
/*  94 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 104 */       for (ReturnPlan r : this.returnPlanes) {
/* 105 */         String[] keyArry = new String[2];
/* 106 */         Object[] keyValue = new Object[2];
/*     */ 
/* 108 */         keyArry[0] = "contractManagement";
/* 109 */         keyArry[1] = "batch";
/*     */ 
/* 111 */         keyValue[0] = r.getContractManagement().getId();
/* 112 */         keyValue[1] = r.getBatch();
/*     */         try {
/* 114 */           List list = this.financialManagementManager.loadByKeyArray(keyArry, keyValue);
/* 115 */           if ((null != list) && (list.size() > 0)) {
/* 116 */             addActionMessage(getText("returnPlan.delete.error"));
/*     */           } else {
/* 118 */             this.returnPlanManager.deleteReturnPlan(r);
/* 119 */             addActionMessage(getText("returnPlan.delete.success"));
/*     */           }
/*     */         } catch (DaoException e) {
/* 122 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     } catch (RuntimeException e) {
/* 126 */       addActionMessage(getText("returnPlan.delete.error"));
/* 127 */       return "error";
/*     */     }
/* 129 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       this.returnPlanManager.disabledAllReturnPlan(this.returnPlanes);
/* 138 */       addActionMessage(getText("returnPlan.disabled.success"));
/* 139 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 141 */       addActionMessage(getText("returnPlan.disabled.error"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 152 */       this.returnPlanManager.enabledAllReturnPlan(this.returnPlanes);
/* 153 */       addActionMessage(getText("returnPlan.enabled.success"));
/* 154 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 156 */       e.printStackTrace();
/* 157 */       addActionMessage(getText("returnPlan.enabled.error"));
/* 158 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayment()
/*     */   {
/* 166 */     List codes = null;
/*     */     try {
/* 168 */       codes = new ArrayList();
/* 169 */       List one = this.codeValueManager.loadByKey("code", "065");
/*     */ 
/* 171 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 173 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 175 */         if ((null != list) && (list.size() > 0)) {
/* 176 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 179 */       CodeValue cv = new CodeValue();
/* 180 */       cv.setId(Long.valueOf(-1L));
/* 181 */       cv.setName(getText("select.option.all"));
/* 182 */       codes.add(0, cv);
/* 183 */       return codes;
/*     */     } catch (DaoException e) {
/* 185 */       e.printStackTrace();
/*     */     }
/* 187 */     return codes;
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 196 */     Map map = super.getRequestParameterMap();
/* 197 */     if (hasId("isOrNot")) {
/* 198 */       if (getId("isOrNot").equals(Long.valueOf("2")))
/* 199 */         map.put("cbv", null);
/*     */       else {
/* 201 */         map.put("cbv", Long.valueOf(getId("isOrNot").longValue()));
/*     */       }
/*     */     }
/* 204 */     return map;
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> getReturnPlanes()
/*     */   {
/* 212 */     return this.returnPlanes;
/*     */   }
/*     */ 
/*     */   public void setReturnPlanes(List<ReturnPlan> returnPlanes)
/*     */   {
/* 221 */     this.returnPlanes = returnPlanes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.plan.ListReturnPlanAction
 * JD-Core Version:    0.6.2
 */