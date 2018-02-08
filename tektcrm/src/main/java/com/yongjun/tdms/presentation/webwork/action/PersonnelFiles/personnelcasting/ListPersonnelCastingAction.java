/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnelcasting;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.personnelcasting.PersonnelCasting;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnelcasting.PersonnelCastingManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListPersonnelCastingAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final PersonnelCastingManager personnelCastingManager;
/*  47 */   private List<PersonnelCasting> personnelCastings = null;
/*     */ 
/*     */   public ListPersonnelCastingAction(PersonnelCastingManager personnelCastingManager)
/*     */   {
/*  54 */     this.personnelCastingManager = personnelCastingManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  64 */     if ((null == this.personnelCastings) && 
/*  65 */       (hasIds("personnelCastingIds")))
/*  66 */       this.personnelCastings = this.personnelCastingManager.loadAllPersonnelCasting(getIds("personnelCastingIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  78 */     return "personnelCastingHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  89 */     if (isDisabled()) {
/*  90 */       disabled();
/*     */     }
/*  92 */     if (isEnable()) {
/*  93 */       enabled();
/*     */     }
/*  95 */     if (isDelete()) {
/*  96 */       delete();
/*     */     }
/*  98 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 108 */       this.personnelCastingManager.deleteAllPersonnelCasting(this.personnelCastings);
/* 109 */       addActionMessage(getText("personnelCasting.delete.success"));
/* 110 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 112 */       addActionMessage(getText("personnelCasting.delete.error"));
/* 113 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 124 */       this.personnelCastingManager.disabledAllPersonnelCasting(this.personnelCastings);
/* 125 */       addActionMessage(getText("personnelCasting.disabled.success"));
/* 126 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 128 */       addActionMessage(getText("personnelCasting.disabled.error"));
/* 129 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 139 */       this.personnelCastingManager.enabledAllPersonnelCasting(this.personnelCastings);
/* 140 */       addActionMessage(getText("personnelCasting.enabled.success"));
/* 141 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 143 */       e.printStackTrace();
/* 144 */       addActionMessage(getText("personnelCasting.enabled.error"));
/* 145 */     }return "error";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnelcasting.ListPersonnelCastingAction
 * JD-Core Version:    0.6.2
 */