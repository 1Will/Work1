/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.departmenttarget;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.departmenttarget.DepartmentTarget;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.departmenttarget.DepartmentTargetManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListDepartmentTargetAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final DepartmentTargetManager departmentTargetManager;
/*  51 */   private List<DepartmentTarget> targetManagements = null;
/*     */ 
/*     */   public ListDepartmentTargetAction(DepartmentTargetManager departmentTargetManager)
/*     */   {
/*  58 */     this.departmentTargetManager = departmentTargetManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  68 */     if ((null == this.targetManagements) && 
/*  69 */       (hasIds("departmentTargetIds")))
/*  70 */       this.targetManagements = this.departmentTargetManager.loadAllDepartmentTarget(getIds("departmentTargetIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  82 */     return "deparmentTargetHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  93 */     if (isDisabled()) {
/*  94 */       disabled();
/*     */     }
/*  96 */     if (isEnable()) {
/*  97 */       enabled();
/*     */     }
/*  99 */     if (isDelete()) {
/* 100 */       delete();
/*     */     }
/* 102 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 112 */       this.departmentTargetManager.deleteAllDepartmentTarget(this.targetManagements);
/* 113 */       addActionMessage(getText("targetManagement.delete.success"));
/* 114 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 116 */       addActionMessage(getText("targetManagement.delete.error"));
/* 117 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 128 */       this.departmentTargetManager.disabledAllDepartmentTarget(this.targetManagements);
/* 129 */       addActionMessage(getText("targetManagement.disabled.success"));
/* 130 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 132 */       addActionMessage(getText("targetManagement.disabled.error"));
/* 133 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 143 */       this.departmentTargetManager.enabledAllDepartmentTarget(this.targetManagements);
/* 144 */       addActionMessage(getText("targetManagement.enabled.success"));
/* 145 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 147 */       e.printStackTrace();
/* 148 */       addActionMessage(getText("targetManagement.enabled.error"));
/* 149 */     }return "error";
/*     */   }
/*     */ 
/*     */   public Map<String, String> getPlanType()
/*     */   {
/* 159 */     Map pt = new LinkedHashMap();
/* 160 */     pt.put("", "所有");
/* 161 */     pt.put("1", "年度计划");
/* 162 */     pt.put("2", "季度计划");
/* 163 */     pt.put("3", "月度计划");
/* 164 */     return pt;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getQuarterMap()
/*     */   {
/* 173 */     Map qu = new LinkedHashMap();
/* 174 */     qu.put("", "所有");
/* 175 */     qu.put("1", "第一季度");
/* 176 */     qu.put("2", "第二季度");
/* 177 */     qu.put("3", "第三季度");
/* 178 */     qu.put("4", "第四季度");
/* 179 */     return qu;
/*     */   }
/*     */ 
/*     */   public List<Integer> getMonth()
/*     */   {
/* 187 */     List ml = new ArrayList();
/* 188 */     ml.add(Integer.valueOf(0));
/* 189 */     int d = 1;
/* 190 */     while (d <= 12) {
/* 191 */       ml.add(Integer.valueOf(d));
/* 192 */       d++;
/*     */     }
/* 194 */     return ml;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.departmenttarget.ListDepartmentTargetAction
 * JD-Core Version:    0.6.2
 */