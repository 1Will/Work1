/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.co;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import com.yongjun.tdms.service.COM.co.CoManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListCoAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final CoManager coManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<Co> cos;
/*     */ 
/*     */   public ListCoAction(CoManager coManager, CodeValueManager codeValueManager)
/*     */   {
/*  47 */     this.coManager = coManager;
/*  48 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  56 */     if (hasIds("coIds"))
/*  57 */       this.cos = this.coManager.loadAllCo(getIds("coIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  66 */     return "cos";
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
/*  96 */       this.coManager.deleteAllCo(this.cos);
/*  97 */       addActionMessage(getText("voc.delete.success"));
/*  98 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 100 */       addActionMessage(getText("voc.delete.error"));
/* 101 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 111 */       this.coManager.disabledAllCos(this.cos);
/* 112 */       addActionMessage(getText("co.disabled.success"));
/* 113 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 115 */       addActionMessage(getText("co.disabled.error"));
/* 116 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 126 */       this.coManager.enabledAllCos(this.cos);
/* 127 */       addActionMessage(getText("co.enabled.success"));
/* 128 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 130 */       e.printStackTrace();
/* 131 */       addActionMessage(getText("co.enabled.error"));
/* 132 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllDeliveryWays()
/*     */   {
/* 140 */     List codes = null;
/*     */     try {
/* 142 */       codes = new ArrayList();
/* 143 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("070"));
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
/* 171 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("060"));
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
/*     */   public List<Co> getCos()
/*     */   {
/* 197 */     return this.cos;
/*     */   }
/*     */ 
/*     */   public void setCos(List<Co> cos)
/*     */   {
/* 206 */     this.cos = cos;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.co.ListCoAction
 * JD-Core Version:    0.6.2
 */