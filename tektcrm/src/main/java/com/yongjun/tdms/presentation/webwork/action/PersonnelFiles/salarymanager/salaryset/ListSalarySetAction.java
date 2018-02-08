/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryset;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset.SalarySet;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryset.SalarySetManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListSalarySetAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final SalarySetManager salarySetManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<SalarySet> salarySets;
/*     */ 
/*     */   public ListSalarySetAction(SalarySetManager salarySetManager, CodeValueManager codeValueManager)
/*     */   {
/*  72 */     this.salarySetManager = salarySetManager;
/*  73 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  83 */     if (isDisabled()) {
/*  84 */       return disable();
/*     */     }
/*  86 */     if (isEnable()) {
/*  87 */       return enable();
/*     */     }
/*  89 */     if (isDelete()) {
/*  90 */       return delete();
/*     */     }
/*  92 */     return "success";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 101 */     if (hasIds("salarySetIds"))
/* 102 */       this.salarySets = this.salarySetManager.loadAllSalarySet(getIds("salarySetIds"));
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/* 112 */     this.salarySetManager.disabledSalarySets(this.salarySets);
/* 113 */     addActionMessage(getText("salarySet.disable.success"));
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 123 */       this.salarySetManager.deleteAllSalarySet(this.salarySets);
/* 124 */       addActionMessage(getText("salarySet.delete.success"));
/*     */     } catch (Exception e) {
/* 126 */       addActionMessage(getText("salarySet.delete.error"));
/*     */     }
/* 128 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/* 136 */     this.salarySetManager.enabledSalarySets(this.salarySets);
/* 137 */     addActionMessage(getText("salarySet.enable.success"));
/* 138 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 147 */     return "salarySetes";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 157 */     List codes = null;
/*     */     try {
/* 159 */       codes = new ArrayList();
/* 160 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055558"));
/*     */ 
/* 162 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 164 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 166 */         if ((null != list) && (list.size() > 0)) {
/* 167 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 170 */       CodeValue cv = new CodeValue();
/* 171 */       cv.setId(Long.valueOf(-1L));
/* 172 */       cv.setName(getText("select.option.all"));
/* 173 */       codes.add(0, cv);
/* 174 */       return codes;
/*     */     } catch (DaoException e) {
/* 176 */       e.printStackTrace();
/*     */     }
/* 178 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<SalarySet> getSalarySets()
/*     */   {
/* 186 */     return this.salarySets;
/*     */   }
/*     */ 
/*     */   public void setSalarySets(List<SalarySet> salarySets)
/*     */   {
/* 195 */     this.salarySets = salarySets;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryset.ListSalarySetAction
 * JD-Core Version:    0.6.2
 */