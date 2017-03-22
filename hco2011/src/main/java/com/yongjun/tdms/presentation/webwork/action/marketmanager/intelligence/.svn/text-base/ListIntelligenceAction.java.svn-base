/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.intelligence;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.marketmanager.intelligence.Intelligence;
/*     */ import com.yongjun.tdms.service.marketmanager.intelligence.IntelligenceManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListIntelligenceAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final IntelligenceManager intelligenceManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private List<Intelligence> intelligenceList;
/*     */ 
/*     */   public ListIntelligenceAction(IntelligenceManager intelligenceManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  68 */     this.intelligenceManager = intelligenceManager;
/*  69 */     this.codeValueManager = codeValueManager;
/*  70 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  79 */     if (hasIds("intelligenceIds")) {
/*  80 */       this.intelligenceList = this.intelligenceManager.loadAllIntelligence(getIds("intelligenceIds"));
/*     */     }
/*     */     else
/*     */     {
/*  84 */       this.intelligenceList = new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  92 */     if (isDisabled()) {
/*  93 */       return disable();
/*     */     }
/*  95 */     if (isEnable()) {
/*  96 */       return enable();
/*     */     }
/*  98 */     if (isDelete()) {
/*  99 */       return delete();
/*     */     }
/* 101 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 109 */     return "intelligence";
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       this.intelligenceManager.disabledAllIntelligence(this.intelligenceList);
/*     */     }
/*     */     catch (Exception e) {
/* 120 */       e.printStackTrace();
/*     */     }
/* 122 */     addActionMessage(getText("disabled.success"));
/* 123 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 131 */       this.intelligenceManager.deleteAllIntelligence(this.intelligenceList);
/* 132 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e) {
/* 135 */       e.printStackTrace();
/* 136 */       addActionMessage(getText("delete.error"));
/*     */     }
/* 138 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/* 145 */     this.intelligenceManager.enabledAllIntelligence(this.intelligenceList);
/* 146 */     addActionMessage(getText("enabled.success"));
/* 147 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllImportant()
/*     */   {
/* 155 */     List codes = null;
/*     */     try {
/* 157 */       codes = new ArrayList();
/* 158 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("041"));
/*     */ 
/* 160 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 162 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 164 */         if ((null != list) && (list.size() > 0)) {
/* 165 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 168 */       CodeValue cv = new CodeValue();
/* 169 */       cv.setId(Long.valueOf(-1L));
/* 170 */       cv.setName(getText("select.option.all"));
/* 171 */       codes.add(0, cv);
/* 172 */       return codes;
/*     */     } catch (DaoException e) {
/* 174 */       e.printStackTrace();
/*     */ 
/* 176 */       CodeValue cv = new CodeValue();
/* 177 */       cv.setId(Long.valueOf(-1L));
/* 178 */       cv.setName(getText("select.option.all"));
/* 179 */       codes.add(0, cv);
/* 180 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.intelligence.ListIntelligenceAction
 * JD-Core Version:    0.6.2
 */