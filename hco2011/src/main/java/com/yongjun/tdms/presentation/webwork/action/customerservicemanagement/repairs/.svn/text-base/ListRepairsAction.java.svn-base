/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.repairs;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.repairs.Repairs;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.repairs.RepairsManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListRepairsAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final RepairsManager repairsManager;
/*     */   private final CodeValueManager codeValueManager;
/*  51 */   private List<Repairs> repairss = null;
/*     */ 
/*     */   public ListRepairsAction(RepairsManager repairsManager, CodeValueManager codeValueManager)
/*     */   {
/*  60 */     this.repairsManager = repairsManager;
/*  61 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  71 */     if ((null == this.repairss) && 
/*  72 */       (hasIds("repairsIds")))
/*  73 */       this.repairss = this.repairsManager.loadAllRepairs(getIds("repairsIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  85 */     return "repairsHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  96 */     if (isDisabled()) {
/*  97 */       disabled();
/*     */     }
/*  99 */     if (isEnable()) {
/* 100 */       enabled();
/*     */     }
/* 102 */     if (isDelete()) {
/* 103 */       delete();
/*     */     }
/* 105 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 115 */       this.repairsManager.deleteAllRepairs(this.repairss);
/* 116 */       addActionMessage(getText("repairs.delete.success"));
/* 117 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 119 */       addActionMessage(getText("repairs.delete.error"));
/* 120 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 131 */       this.repairsManager.disabledAllRepairs(this.repairss);
/* 132 */       addActionMessage(getText("repairs.disabled.success"));
/* 133 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 135 */       addActionMessage(getText("repairs.disabled.error"));
/* 136 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 146 */       this.repairsManager.enabledAllRepairs(this.repairss);
/* 147 */       addActionMessage(getText("repairs.enabled.success"));
/* 148 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 150 */       e.printStackTrace();
/* 151 */       addActionMessage(getText("repairs.enabled.error"));
/* 152 */     }return "error";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.repairs.ListRepairsAction
 * JD-Core Version:    0.6.2
 */