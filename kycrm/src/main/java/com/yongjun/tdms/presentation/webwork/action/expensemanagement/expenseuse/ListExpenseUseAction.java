/*     */ package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseuse;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseuse.ExpenseUse;
/*     */ import com.yongjun.tdms.service.expensemanagement.expenseuse.ExpenseUseManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListExpenseUseAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ExpenseUseManager expenseUseManager;
/*     */   private final CodeValueManager codeValueManager;
/*  53 */   private List<ExpenseUse> expenseUses = null;
/*     */ 
/*     */   public ListExpenseUseAction(ExpenseUseManager expenseUseManager, CodeValueManager codeValueManager)
/*     */   {
/*  61 */     this.expenseUseManager = expenseUseManager;
/*  62 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  72 */     if ((null == this.expenseUses) && 
/*  73 */       (hasIds("expenseUseIds")))
/*  74 */       this.expenseUses = this.expenseUseManager.loadAllExpenseUse(getIds("expenseUseIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  86 */     return "expenseUseHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  97 */     if (isDisabled()) {
/*  98 */       disabled();
/*     */     }
/* 100 */     if (isEnable()) {
/* 101 */       enabled();
/*     */     }
/* 103 */     if (isDelete()) {
/* 104 */       delete();
/*     */     }
/* 106 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 116 */       this.expenseUseManager.deleteAllExpenseUse(this.expenseUses);
/* 117 */       addActionMessage(getText("expenseUse.delete.success"));
/* 118 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 120 */       addActionMessage(getText("expenseUse.delete.error"));
/* 121 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 132 */       this.expenseUseManager.disabledAllExpenseUse(this.expenseUses);
/* 133 */       addActionMessage(getText("expenseUse.disabled.success"));
/* 134 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 136 */       addActionMessage(getText("expenseUse.disabled.error"));
/* 137 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 147 */       this.expenseUseManager.enabledAllExpenseUse(this.expenseUses);
/* 148 */       addActionMessage(getText("expenseApply.enabled.success"));
/* 149 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 151 */       e.printStackTrace();
/* 152 */       addActionMessage(getText("expenseApply.enabled.error"));
/* 153 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllExpenseType()
/*     */   {
/* 161 */     List codes = null;
/*     */     try {
/* 163 */       codes = new ArrayList();
/* 164 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("069"));
/*     */ 
/* 166 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 168 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 170 */         if ((null != list) && (list.size() > 0)) {
/* 171 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 174 */       CodeValue cv = new CodeValue();
/* 175 */       cv.setId(null);
/* 176 */       cv.setName(getText("所有"));
/* 177 */       codes.add(0, cv);
/* 178 */       return codes;
/*     */     } catch (DaoException e) {
/* 180 */       e.printStackTrace();
/*     */ 
/* 182 */       CodeValue cv = new CodeValue();
/* 183 */       cv.setId(null);
/* 184 */       cv.setName(getText("所有"));
/* 185 */       codes.add(0, cv);
/* 186 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseuse.ListExpenseUseAction
 * JD-Core Version:    0.6.2
 */