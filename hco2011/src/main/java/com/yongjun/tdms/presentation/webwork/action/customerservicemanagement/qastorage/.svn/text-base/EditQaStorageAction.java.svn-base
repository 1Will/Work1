/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.qastorage;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.qastorage.QaStorage;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.qastorage.QaStorageManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditQaStorageAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final QaStorageManager qaStorageManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private CodeValue type;
/*     */   private CodeValue state;
/*     */   private CodeValue severityDegree;
/*     */   private QaStorage qaStorage;
/*     */ 
/*     */   public EditQaStorageAction(QaStorageManager qaStorageManager, CodeValueManager codeValueManager)
/*     */   {
/*  81 */     this.qaStorageManager = qaStorageManager;
/*  82 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  90 */     if (null == this.qaStorage) {
/*  91 */       if (hasId("qaStorage.id")) {
/*  92 */         this.qaStorage = this.qaStorageManager.loadQaStorage(getId("qaStorage.id"));
/*     */       }
/*     */       else {
/*  95 */         this.qaStorage = new QaStorage();
/*     */       }
/*     */     }
/*     */ 
/*  99 */     if (null == this.type) {
/* 100 */       if (hasId("type.id"))
/* 101 */         this.type = this.codeValueManager.loadCodeValue(getId("type.id"));
/*     */       else {
/* 103 */         this.type = null;
/*     */       }
/*     */     }
/*     */ 
/* 107 */     if (null == this.state) {
/* 108 */       if (hasId("state.id"))
/* 109 */         this.state = this.codeValueManager.loadCodeValue(getId("state.id"));
/*     */       else {
/* 111 */         this.state = null;
/*     */       }
/*     */     }
/*     */ 
/* 115 */     if (null == this.severityDegree)
/* 116 */       if (hasId("severityDegree.id")) {
/* 117 */         this.severityDegree = this.codeValueManager.loadCodeValue(getId("severityDegree.id"));
/*     */       }
/*     */       else
/* 120 */         this.severityDegree = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 132 */     boolean isNew = this.qaStorage.isNew();
/* 133 */     if (isNew) {
/* 134 */       String code = autoCompleteCode();
/* 135 */       this.qaStorage.setCode(code);
/*     */     }
/*     */     try {
/* 138 */       this.qaStorage.setType(this.type);
/* 139 */       this.qaStorage.setSeverityDegree(this.severityDegree);
/* 140 */       this.qaStorage.setState(this.state);
/* 141 */       this.qaStorageManager.storeQaStorage(this.qaStorage);
/* 142 */       if (isNew) {
/* 143 */         addActionMessage(getText("qaStorage.add.success"));
/* 144 */         return "new";
/*     */       }
/* 146 */       addActionMessage(getText("qaStorage.edit.success"));
/* 147 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 151 */       ex.printStackTrace();
/* 152 */       if (isNew) {
/* 153 */         addActionMessage(getText("qaStorage.add.error"));
/* 154 */         return "new";
/*     */       }
/* 156 */       addActionMessage(getText("qaStorage.edit.error"));
/* 157 */     }return "success";
/*     */   }
/*     */ 
/*     */   public QaStorage getQaStorage()
/*     */   {
/* 168 */     return this.qaStorage;
/*     */   }
/*     */ 
/*     */   public void setQaStorage(QaStorage qaStorage)
/*     */   {
/* 175 */     this.qaStorage = qaStorage;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllType()
/*     */   {
/* 184 */     List codes = null;
/*     */     try {
/* 186 */       codes = new ArrayList();
/* 187 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("054"));
/*     */ 
/* 189 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 191 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 193 */         if ((null != list) && (list.size() > 0)) {
/* 194 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 197 */       return codes;
/*     */     } catch (DaoException e) {
/* 199 */       e.printStackTrace();
/*     */     }
/* 201 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllState()
/*     */   {
/* 209 */     List codes = null;
/*     */     try {
/* 211 */       codes = new ArrayList();
/* 212 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("052"));
/*     */ 
/* 214 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 216 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 218 */         if ((null != list) && (list.size() > 0)) {
/* 219 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 222 */       return codes;
/*     */     } catch (DaoException e) {
/* 224 */       e.printStackTrace();
/*     */     }
/* 226 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllSeverityDegree()
/*     */   {
/* 234 */     List codes = null;
/*     */     try {
/* 236 */       codes = new ArrayList();
/* 237 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("053"));
/*     */ 
/* 239 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 241 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 243 */         if ((null != list) && (list.size() > 0)) {
/* 244 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 247 */       return codes;
/*     */     } catch (DaoException e) {
/* 249 */       e.printStackTrace();
/*     */     }
/* 251 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 259 */     String maxCode = this.qaStorageManager.getMaxPFCode("QAK");
/* 260 */     if (null != maxCode) {
/* 261 */       int num = Integer.parseInt(maxCode) + 1;
/* 262 */       if (num < 10)
/* 263 */         return "QAK--000" + num;
/* 264 */       if (num < 100)
/* 265 */         return "QAK--00" + num;
/* 266 */       if (num < 1000) {
/* 267 */         return "QAK--0" + num;
/*     */       }
/* 269 */       return "QAK--" + num;
/*     */     }
/*     */ 
/* 272 */     return "QAK--0001";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.qastorage.EditQaStorageAction
 * JD-Core Version:    0.6.2
 */