/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.remit;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.COM.remit.Remit;
/*     */ import com.yongjun.tdms.service.COM.remit.RemitManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListRemitAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final RemitManager remitManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<Remit> remits;
/*     */ 
/*     */   public ListRemitAction(RemitManager remitManager, CodeValueManager codeValueManager)
/*     */   {
/*  47 */     this.remitManager = remitManager;
/*  48 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  56 */     if (hasIds("remitIds"))
/*  57 */       this.remits = this.remitManager.loadAllRemit(getIds("remitIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  66 */     return "remits";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  77 */     if (isDisabled()) {
/*  78 */       disabled();
/*     */     }
/*  80 */     if (isEnable()) {
/*  81 */       enabled();
/*     */     }
/*  83 */     if (isDelete()) {
/*  84 */       delete();
/*     */     }
/*  86 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  96 */       this.remitManager.deleteAllRemit(this.remits);
/*  97 */       addActionMessage(getText("remit.delete.success"));
/*  98 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 100 */       addActionMessage(getText("remit.delete.error"));
/* 101 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 111 */       this.remitManager.disabledAllRemits(this.remits);
/* 112 */       addActionMessage(getText("remit.disabled.success"));
/* 113 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 115 */       addActionMessage(getText("remit.disabled.error"));
/* 116 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 126 */       this.remitManager.enabledAllRemits(this.remits);
/* 127 */       addActionMessage(getText("remit.enabled.success"));
/* 128 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 130 */       e.printStackTrace();
/* 131 */       addActionMessage(getText("remit.enabled.error"));
/* 132 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBillings()
/*     */   {
/* 140 */     List codes = null;
/*     */     try {
/* 142 */       codes = new ArrayList();
/* 143 */       List one = this.codeValueManager.loadByKey("code", "084001");
/*     */ 
/* 145 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 147 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 149 */         if ((null != list) && (list.size() > 0)) {
/* 150 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 153 */       CodeValue cv = new CodeValue();
/* 154 */       cv.setId(Long.valueOf(-1L));
/* 155 */       cv.setName(getText("select.option.all"));
/* 156 */       codes.add(0, cv);
/* 157 */       return codes;
/*     */     } catch (DaoException e) {
/* 159 */       e.printStackTrace();
/*     */     }
/* 161 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 168 */     List codes = null;
/*     */     try {
/* 170 */       codes = new ArrayList();
/* 171 */       List one = this.codeValueManager.loadByKey("code", "083001");
/*     */ 
/* 173 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 175 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 177 */         if ((null != list) && (list.size() > 0)) {
/* 178 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 181 */       CodeValue cv = new CodeValue();
/* 182 */       cv.setId(Long.valueOf(-1L));
/* 183 */       cv.setName(getText("select.option.all"));
/* 184 */       codes.add(0, cv);
/* 185 */       return codes;
/*     */     } catch (DaoException e) {
/* 187 */       e.printStackTrace();
/*     */     }
/* 189 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Remit> getRemits()
/*     */   {
/* 197 */     return this.remits;
/*     */   }
/*     */ 
/*     */   public void setRemits(List<Remit> remits)
/*     */   {
/* 206 */     this.remits = remits;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.remit.ListRemitAction
 * JD-Core Version:    0.6.2
 */