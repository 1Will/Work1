/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.rejection;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.COM.rejection.Rejection;
/*     */ import com.yongjun.tdms.service.COM.rejection.RejectionManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListRejectionAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final RejectionManager rejectionManager;
/*     */   private List<Rejection> rejections;
/*     */ 
/*     */   public ListRejectionAction(RejectionManager rejectionManager)
/*     */   {
/*  36 */     this.rejectionManager = rejectionManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  44 */     if (hasIds("rejectionIds"))
/*  45 */       this.rejections = this.rejectionManager.loadAllRejection(getIds("rejectionIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  54 */     return "rejections";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  65 */     if (isDisabled()) {
/*  66 */       disabled();
/*     */     }
/*  68 */     if (isEnable()) {
/*  69 */       enabled();
/*     */     }
/*  71 */     if (isDelete()) {
/*  72 */       delete();
/*     */     }
/*  74 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  84 */       this.rejectionManager.deleteAllRejection(this.rejections);
/*  85 */       addActionMessage(getText("rejection.delete.success"));
/*  86 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  88 */       addActionMessage(getText("rejection.delete.error"));
/*  89 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/*  99 */       this.rejectionManager.disabledAllRejections(this.rejections);
/* 100 */       addActionMessage(getText("rejection.disabled.success"));
/* 101 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 103 */       addActionMessage(getText("leaveBill.disabled.error"));
/* 104 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 114 */       this.rejectionManager.enabledAllRejections(this.rejections);
/* 115 */       addActionMessage(getText("rejection.enabled.success"));
/* 116 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 118 */       e.printStackTrace();
/* 119 */       addActionMessage(getText("rejection.enabled.error"));
/* 120 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<Rejection> getRejections()
/*     */   {
/* 128 */     return this.rejections;
/*     */   }
/*     */ 
/*     */   public void setRejections(List<Rejection> rejections)
/*     */   {
/* 137 */     this.rejections = rejections;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.rejection.ListRejectionAction
 * JD-Core Version:    0.6.2
 */