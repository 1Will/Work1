/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryset;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset.SalarySet;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryset.SalarySetManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditSalarySetAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -6543880456612721423L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final SalarySetManager salarySetManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private SalarySet salarySet;
/*     */ 
/*     */   public EditSalarySetAction(SalarySetManager salarySetManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  84 */     this.salarySetManager = salarySetManager;
/*  85 */     this.codeValueManager = codeValueManager;
/*  86 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  95 */     if ((this.salarySet == null) && (hasId("salarySet.id"))) {
/*  96 */       this.salarySet = this.salarySetManager.loadSalarySet(getId("salarySet.id"));
/*     */     }
/*     */     else
/*  99 */       this.salarySet = new SalarySet();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 108 */     boolean isNew = this.salarySet.isNew();
/*     */ 
/* 110 */     if (hasId("status.id")) {
/* 111 */       this.salarySet.setStatus(this.codeValueManager.loadCodeValue(getId("status.id")));
/*     */     }
/*     */ 
/* 114 */     if (hasId("emplyee.id"))
/* 115 */       this.salarySet.setEmplyee(this.personnelFilesManager.loadPersonnel(getId("emplyee.id")));
/*     */     try
/*     */     {
/* 118 */       if (isNew) {
/* 119 */         this.salarySetManager.storeSalarySet(this.salarySet);
/* 120 */         addActionMessage(getText("salarySet.add.success"));
/* 121 */         return "success";
/*     */       }
/* 123 */       this.salarySetManager.storeSalarySet(this.salarySet);
/* 124 */       addActionMessage(getText("salarySet.edit.success"));
/* 125 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 128 */       e.printStackTrace();
/* 129 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 138 */     List codes = null;
/*     */     try {
/* 140 */       codes = new ArrayList();
/* 141 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055558"));
/*     */ 
/* 143 */       if ((null != one) && (one.size() > 0)) {
/* 144 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 146 */         if ((null != list) && (list.size() > 0)) {
/* 147 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 150 */       return codes;
/*     */     } catch (DaoException e) {
/* 152 */       e.printStackTrace();
/*     */     }
/* 154 */     return codes;
/*     */   }
/*     */ 
/*     */   public SalarySet getSalarySet()
/*     */   {
/* 164 */     return this.salarySet;
/*     */   }
/*     */ 
/*     */   public void setSalarySet(SalarySet salarySet)
/*     */   {
/* 173 */     this.salarySet = salarySet;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryset.EditSalarySetAction
 * JD-Core Version:    0.6.2
 */