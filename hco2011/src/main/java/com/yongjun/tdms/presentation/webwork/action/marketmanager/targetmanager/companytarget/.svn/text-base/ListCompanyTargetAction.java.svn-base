/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.companytarget;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.companytarget.CompanyTarget;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.companytarget.CompanyTargetManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListCompanyTargetAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CompanyTargetManager companyTargetManager;
/*  52 */   private List<CompanyTarget> targetManagements = null;
/*     */ 
/*     */   public ListCompanyTargetAction(CompanyTargetManager companyTargetManager)
/*     */   {
/*  59 */     this.companyTargetManager = companyTargetManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  69 */     if ((null == this.targetManagements) && 
/*  70 */       (hasIds("targetManagementIds")))
/*  71 */       this.targetManagements = this.companyTargetManager.loadAllCompanyTarget(getIds("targetManagementIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  83 */     return "companyTargetHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  94 */     if (isDisabled()) {
/*  95 */       disabled();
/*     */     }
/*  97 */     if (isEnable()) {
/*  98 */       enabled();
/*     */     }
/* 100 */     if (isDelete()) {
/* 101 */       delete();
/*     */     }
/* 103 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 113 */       this.companyTargetManager.deleteAllCompanyTarget(this.targetManagements);
/* 114 */       addActionMessage(getText("targetManagement.delete.success"));
/* 115 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 117 */       addActionMessage(getText("targetManagement.delete.error"));
/* 118 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 129 */       this.companyTargetManager.disabledAllCompanyTarget(this.targetManagements);
/* 130 */       addActionMessage(getText("targetManagement.disabled.success"));
/* 131 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 133 */       addActionMessage(getText("targetManagement.disabled.error"));
/* 134 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 144 */       this.companyTargetManager.enabledAllCompanyTarget(this.targetManagements);
/* 145 */       addActionMessage(getText("targetManagement.enabled.success"));
/* 146 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 148 */       e.printStackTrace();
/* 149 */       addActionMessage(getText("targetManagement.enabled.error"));
/* 150 */     }return "error";
/*     */   }
/*     */ 
/*     */   public Map<String, String> getPlanType()
/*     */   {
/* 160 */     Map pt = new LinkedHashMap();
/* 161 */     pt.put("", "所有");
/* 162 */     pt.put("1", "年度计划");
/* 163 */     pt.put("2", "季度计划");
/* 164 */     pt.put("3", "月度计划");
/* 165 */     return pt;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getQuarterMap()
/*     */   {
/* 174 */     Map qu = new LinkedHashMap();
/* 175 */     qu.put("", "所有");
/* 176 */     qu.put("1", "第一季度");
/* 177 */     qu.put("2", "第二季度");
/* 178 */     qu.put("3", "第三季度");
/* 179 */     qu.put("4", "第四季度");
/* 180 */     return qu;
/*     */   }
/*     */ 
/*     */   public List<Integer> getMonth()
/*     */   {
/* 188 */     List ml = new ArrayList();
/* 189 */     ml.add(Integer.valueOf(0));
/* 190 */     int d = 1;
/* 191 */     while (d <= 12) {
/* 192 */       ml.add(Integer.valueOf(d));
/* 193 */       d++;
/*     */     }
/* 195 */     return ml;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.companytarget.ListCompanyTargetAction
 * JD-Core Version:    0.6.2
 */