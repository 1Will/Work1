/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.qastorage;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.qastorage.QaStorage;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.qastorage.QaStorageManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListQaStorageAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final QaStorageManager qaStorageManager;
/*     */   private final CodeValueManager codeValueManager;
/*  54 */   private List<QaStorage> qaStorages = null;
/*     */ 
/*     */   public ListQaStorageAction(QaStorageManager qaStorageManager, CodeValueManager codeValueManager)
/*     */   {
/*  62 */     this.qaStorageManager = qaStorageManager;
/*  63 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  73 */     if ((null == this.qaStorages) && 
/*  74 */       (hasIds("qaStorageIds")))
/*  75 */       this.qaStorages = this.qaStorageManager.loadAllQaStorage(getIds("qaStorageIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  87 */     return "qaStorageHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  98 */     if (isDisabled()) {
/*  99 */       disabled();
/*     */     }
/* 101 */     if (isEnable()) {
/* 102 */       enabled();
/*     */     }
/* 104 */     if (isDelete()) {
/* 105 */       delete();
/*     */     }
/* 107 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       this.qaStorageManager.deleteAllQaStorage(this.qaStorages);
/* 118 */       addActionMessage(getText("qaStorage.delete.success"));
/* 119 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 121 */       addActionMessage(getText("qaStorage.delete.error"));
/* 122 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 133 */       this.qaStorageManager.disabledAllQaStorage(this.qaStorages);
/* 134 */       addActionMessage(getText("qaStorage.disabled.success"));
/* 135 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 137 */       addActionMessage(getText("qaStorage.disabled.error"));
/* 138 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 148 */       this.qaStorageManager.enabledAllQaStorage(this.qaStorages);
/* 149 */       addActionMessage(getText("qaStorage.enabled.success"));
/* 150 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 152 */       e.printStackTrace();
/* 153 */       addActionMessage(getText("qaStorage.enabled.error"));
/* 154 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllType()
/*     */   {
/* 163 */     List codes = null;
/*     */     try {
/* 165 */       codes = new ArrayList();
/* 166 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("054"));
/*     */ 
/* 168 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 170 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 172 */         if ((null != list) && (list.size() > 0)) {
/* 173 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 176 */       CodeValue cv = new CodeValue();
/* 177 */       cv.setId(null);
/* 178 */       cv.setName(getText("所有"));
/* 179 */       codes.add(0, cv);
/* 180 */       return codes;
/*     */     } catch (DaoException e) {
/* 182 */       e.printStackTrace();
/*     */ 
/* 184 */       CodeValue cv = new CodeValue();
/* 185 */       cv.setId(null);
/* 186 */       cv.setName(getText("所有"));
/* 187 */       codes.add(0, cv);
/* 188 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllState()
/*     */   {
/* 195 */     List codes = null;
/*     */     try {
/* 197 */       codes = new ArrayList();
/* 198 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("052"));
/*     */ 
/* 200 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 202 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 204 */         if ((null != list) && (list.size() > 0)) {
/* 205 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 208 */       CodeValue cv = new CodeValue();
/* 209 */       cv.setId(null);
/* 210 */       cv.setName(getText("所有"));
/* 211 */       codes.add(0, cv);
/* 212 */       return codes;
/*     */     } catch (DaoException e) {
/* 214 */       e.printStackTrace();
/*     */ 
/* 216 */       CodeValue cv = new CodeValue();
/* 217 */       cv.setId(null);
/* 218 */       cv.setName(getText("所有"));
/* 219 */       codes.add(0, cv);
/* 220 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllSeverityDegree()
/*     */   {
/* 227 */     List codes = null;
/*     */     try {
/* 229 */       codes = new ArrayList();
/* 230 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("053"));
/*     */ 
/* 232 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 234 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 236 */         if ((null != list) && (list.size() > 0)) {
/* 237 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 240 */       CodeValue cv = new CodeValue();
/* 241 */       cv.setId(null);
/* 242 */       cv.setName(getText("所有"));
/* 243 */       codes.add(0, cv);
/* 244 */       return codes;
/*     */     } catch (DaoException e) {
/* 246 */       e.printStackTrace();
/*     */ 
/* 248 */       CodeValue cv = new CodeValue();
/* 249 */       cv.setId(null);
/* 250 */       cv.setName(getText("所有"));
/* 251 */       codes.add(0, cv);
/* 252 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.qastorage.ListQaStorageAction
 * JD-Core Version:    0.6.2
 */