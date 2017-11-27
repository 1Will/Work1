/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.repairs;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.repairs.Repairs;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.repairs.RepairsManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditRepairsAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final RepairsManager repairsManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*  62 */   private Repairs repairs = null;
/*     */   private CustomerInfo customerInfo;
/*     */   private CodeValue applyMode;
/*     */   private CodeValue callbackValidate;
/*     */   private CodeValue disposalState;
/*     */ 
/*     */   public EditRepairsAction(RepairsManager repairsManager, CodeValueManager codeValueManager, CustomerInfoManager customerInfoManager)
/*     */   {
/*  89 */     this.repairsManager = repairsManager;
/*  90 */     this.codeValueManager = codeValueManager;
/*  91 */     this.customerInfoManager = customerInfoManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  99 */     if (null == this.repairs) {
/* 100 */       if (hasId("repairs.id")) {
/* 101 */         this.repairs = this.repairsManager.loadRepairs(getId("repairs.id"));
/*     */       }
/*     */       else {
/* 104 */         this.repairs = new Repairs();
/*     */       }
/*     */     }
/*     */ 
/* 108 */     if (null == this.customerInfo) {
/* 109 */       if (hasId("customerInfo.id"))
/* 110 */         this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/*     */       else {
/* 112 */         this.customerInfo = null;
/*     */       }
/*     */     }
/* 115 */     if (null == this.applyMode) {
/* 116 */       if (hasId("applyMode.id"))
/* 117 */         this.applyMode = this.codeValueManager.loadCodeValue(getId("applyMode.id"));
/*     */       else {
/* 119 */         this.applyMode = null;
/*     */       }
/*     */     }
/* 122 */     if (null == this.callbackValidate) {
/* 123 */       if (hasId("callbackValidate.id"))
/* 124 */         this.callbackValidate = this.codeValueManager.loadCodeValue(getId("callbackValidate.id"));
/*     */       else {
/* 126 */         this.callbackValidate = null;
/*     */       }
/*     */     }
/*     */ 
/* 130 */     if (null == this.disposalState)
/* 131 */       if (hasId("disposalState.id"))
/* 132 */         this.disposalState = this.codeValueManager.loadCodeValue(getId("disposalState.id"));
/*     */       else
/* 134 */         this.disposalState = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 145 */     boolean isNew = this.repairs.isNew();
/* 146 */     if (isNew) {
/* 147 */       String code = autoCompleteCode();
/* 148 */       this.repairs.setCode(code);
/*     */     }
/*     */     try {
/* 151 */       this.repairs.setCustomerInfo(this.customerInfo);
/* 152 */       this.repairs.setApplyMode(this.applyMode);
/* 153 */       this.repairs.setCallbackValidate(this.callbackValidate);
/* 154 */       this.repairs.setDisposalState(this.disposalState);
/* 155 */       this.repairsManager.storeRepairs(this.repairs);
/* 156 */       if (isNew) {
/* 157 */         addActionMessage(getText("repairs.add.success"));
/* 158 */         return "new";
/*     */       }
/* 160 */       addActionMessage(getText("repairs.edit.success"));
/* 161 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 165 */       ex.printStackTrace();
/* 166 */       if (isNew) {
/* 167 */         addActionMessage(getText("repairs.add.error"));
/* 168 */         return "new";
/*     */       }
/* 170 */       addActionMessage(getText("repairs.edit.error"));
/* 171 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllApplyMode()
/*     */   {
/* 183 */     List codes = null;
/*     */     try {
/* 185 */       codes = new ArrayList();
/* 186 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("111"));
/*     */ 
/* 188 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 190 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 192 */         if ((null != list) && (list.size() > 0)) {
/* 193 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 196 */       return codes;
/*     */     } catch (DaoException e) {
/* 198 */       e.printStackTrace();
/*     */     }
/* 200 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllCallbackValidate()
/*     */   {
/* 207 */     List codes = null;
/*     */     try {
/* 209 */       codes = new ArrayList();
/* 210 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("112"));
/*     */ 
/* 212 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 214 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 216 */         if ((null != list) && (list.size() > 0)) {
/* 217 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 220 */       return codes;
/*     */     } catch (DaoException e) {
/* 222 */       e.printStackTrace();
/*     */     }
/* 224 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllDisposalState()
/*     */   {
/* 232 */     List codes = null;
/*     */     try {
/* 234 */       codes = new ArrayList();
/* 235 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("113"));
/*     */ 
/* 237 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 239 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 241 */         if ((null != list) && (list.size() > 0)) {
/* 242 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 245 */       return codes;
/*     */     } catch (DaoException e) {
/* 247 */       e.printStackTrace();
/*     */     }
/* 249 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 257 */     String prefix = "REP";
/* 258 */     String maxCode = this.repairsManager.getMaxPFCode(prefix);
/* 259 */     if (null != maxCode) {
/* 260 */       int num = Integer.parseInt(maxCode) + 1;
/* 261 */       if (num < 10)
/* 262 */         return prefix + "-00000" + num;
/* 263 */       if (num < 100)
/* 264 */         return prefix + "-0000" + num;
/* 265 */       if (num < 1000)
/* 266 */         return prefix + "-000" + num;
/* 267 */       if (num < 10000)
/* 268 */         return prefix + "-00" + num;
/* 269 */       if (num < 100000) {
/* 270 */         return prefix + "-0" + num;
/*     */       }
/* 272 */       return prefix + "-" + num;
/*     */     }
/*     */ 
/* 275 */     return prefix + "-000001";
/*     */   }
/*     */ 
/*     */   public Repairs getRepairs()
/*     */   {
/* 286 */     return this.repairs;
/*     */   }
/*     */ 
/*     */   public void setRepairs(Repairs repairs)
/*     */   {
/* 293 */     this.repairs = repairs;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.repairs.EditRepairsAction
 * JD-Core Version:    0.6.2
 */