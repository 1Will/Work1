/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.VOC;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.COM.VOC.Voc;
/*     */ import com.yongjun.tdms.service.COM.VOC.VocManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListVocAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final VocManager vocManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<Voc> vocs;
/*     */ 
/*     */   public ListVocAction(VocManager vocManager, CodeValueManager codeValueManager)
/*     */   {
/*  47 */     this.vocManager = vocManager;
/*  48 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  56 */     if (hasIds("vocIds"))
/*  57 */       this.vocs = this.vocManager.loadAllVoc(getIds("vocIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  66 */     return "vocses";
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
/*  96 */       this.vocManager.deleteAllVoc(this.vocs);
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
/* 111 */       this.vocManager.disabledAllVocs(this.vocs);
/* 112 */       addActionMessage(getText("voc.disabled.success"));
/* 113 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 115 */       addActionMessage(getText("leaveBill.disabled.error"));
/* 116 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 126 */       this.vocManager.enabledAllVoc(this.vocs);
/* 127 */       addActionMessage(getText("voc.enabled.success"));
/* 128 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 130 */       e.printStackTrace();
/* 131 */       addActionMessage(getText("voc.enabled.error"));
/* 132 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 140 */     List codes = null;
/*     */     try {
/* 142 */       codes = new ArrayList();
/* 143 */       List one = this.codeValueManager.loadByKey("code", "050");
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
/* 171 */       List one = this.codeValueManager.loadByKey("code", "001");
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
/*     */   public List<CodeValue> getAllImportances()
/*     */   {
/* 197 */     List codes = null;
/*     */     try {
/* 199 */       codes = new ArrayList();
/* 200 */       List one = this.codeValueManager.loadByKey("code", "040");
/*     */ 
/* 202 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 204 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 206 */         if ((null != list) && (list.size() > 0)) {
/* 207 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 210 */       CodeValue cv = new CodeValue();
/* 211 */       cv.setId(Long.valueOf(-1L));
/* 212 */       cv.setName(getText("select.option.all"));
/* 213 */       codes.add(0, cv);
/* 214 */       return codes;
/*     */     } catch (DaoException e) {
/* 216 */       e.printStackTrace();
/*     */     }
/* 218 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Voc> getVocs()
/*     */   {
/* 225 */     return this.vocs;
/*     */   }
/*     */ 
/*     */   public void setVocs(List<Voc> vocs)
/*     */   {
/* 234 */     this.vocs = vocs;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.VOC.ListVocAction
 * JD-Core Version:    0.6.2
 */