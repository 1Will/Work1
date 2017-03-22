/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnelcasting;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.personnelcasting.PersonnelCasting;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnelcasting.PersonnelCastingManager;
/*     */ 
/*     */ public class EditPersonnelCastingAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final PersonnelCastingManager personnelCastingManager;
/*  43 */   private PersonnelCasting personnelCasting = null;
/*     */ 
/*     */   public EditPersonnelCastingAction(PersonnelCastingManager personnelCastingManager)
/*     */   {
/*  50 */     this.personnelCastingManager = personnelCastingManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  59 */     if (null == this.personnelCasting)
/*  60 */       if (hasId("personnelCasting.id")) {
/*  61 */         this.personnelCasting = this.personnelCastingManager.loadPersonnelCasting(getId("personnelCasting.id"));
/*     */       }
/*     */       else
/*  64 */         this.personnelCasting = new PersonnelCasting();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  74 */     boolean isNew = this.personnelCasting.isNew();
/*     */     try
/*     */     {
/*  80 */       this.personnelCastingManager.storePersonnelCasting(this.personnelCasting);
/*  81 */       if (isNew) {
/*  82 */         addActionMessage(getText("personnelCasting.add.success"));
/*  83 */         return "new";
/*     */       }
/*  85 */       addActionMessage(getText("personnelCasting.edit.success"));
/*  86 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*  90 */       ex.printStackTrace();
/*  91 */       if (isNew) {
/*  92 */         addActionMessage(getText("personnelCasting.add.error"));
/*  93 */         return "new";
/*     */       }
/*  95 */       addActionMessage(getText("personnelCasting.edit.error"));
/*  96 */     }return "success";
/*     */   }
/*     */ 
/*     */   public PersonnelCasting getPersonnelCasting()
/*     */   {
/* 104 */     return this.personnelCasting;
/*     */   }
/*     */ 
/*     */   public void setPersonnelCasting(PersonnelCasting personnelCasting)
/*     */   {
/* 110 */     this.personnelCasting = personnelCasting;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 118 */     String prefix = "PCM";
/* 119 */     String maxCode = this.personnelCastingManager.getMaxPFCode(prefix);
/* 120 */     if (null != maxCode) {
/* 121 */       int num = Integer.parseInt(maxCode) + 1;
/* 122 */       if (num < 10)
/* 123 */         return prefix + "-00000" + num;
/* 124 */       if (num < 100)
/* 125 */         return prefix + "-0000" + num;
/* 126 */       if (num < 1000)
/* 127 */         return prefix + "-000" + num;
/* 128 */       if (num < 10000)
/* 129 */         return prefix + "-00" + num;
/* 130 */       if (num < 100000) {
/* 131 */         return prefix + "-0" + num;
/*     */       }
/* 133 */       return prefix + "-" + num;
/*     */     }
/*     */ 
/* 136 */     return prefix + "-000001";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnelcasting.EditPersonnelCastingAction
 * JD-Core Version:    0.6.2
 */