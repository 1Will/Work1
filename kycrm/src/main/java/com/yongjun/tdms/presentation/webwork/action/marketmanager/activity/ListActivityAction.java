/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.activity;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.Activity;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.ActivityManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListActivityAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ActivityManager activityManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private List<Activity> activityList;
/*     */ 
/*     */   public ListActivityAction(ActivityManager activityManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  68 */     this.activityManager = activityManager;
/*  69 */     this.codeValueManager = codeValueManager;
/*  70 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  79 */     if (hasIds("activityIds")) {
/*  80 */       this.activityList = this.activityManager.loadAllActivity(getIds("activityIds"));
/*     */     }
/*     */     else
/*     */     {
/*  84 */       this.activityList = new ArrayList();
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
/* 109 */     return "activity";
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       this.activityManager.disabledAllActivity(this.activityList);
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
/* 131 */       this.activityManager.deleteAllActivity(this.activityList);
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
/* 145 */     this.activityManager.enabledAllActivity(this.activityList);
/* 146 */     addActionMessage(getText("enabled.success"));
/* 147 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllActivityType()
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
/*     */ 
/*     */   public List<CodeValue> getAllProgress()
/*     */   {
/* 187 */     List codes = null;
/*     */     try {
/* 189 */       codes = new ArrayList();
/* 190 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("043"));
/*     */ 
/* 192 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 194 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 196 */         if ((null != list) && (list.size() > 0)) {
/* 197 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 200 */       CodeValue cv = new CodeValue();
/* 201 */       cv.setId(Long.valueOf(-1L));
/* 202 */       cv.setName(getText("select.option.all"));
/* 203 */       codes.add(0, cv);
/* 204 */       return codes;
/*     */     } catch (DaoException e) {
/* 206 */       e.printStackTrace();
/*     */ 
/* 208 */       CodeValue cv = new CodeValue();
/* 209 */       cv.setId(Long.valueOf(-1L));
/* 210 */       cv.setName(getText("select.option.all"));
/* 211 */       codes.add(0, cv);
/* 212 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPriority()
/*     */   {
/* 219 */     List codes = null;
/*     */     try {
/* 221 */       codes = new ArrayList();
/* 222 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("044"));
/*     */ 
/* 224 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 226 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 228 */         if ((null != list) && (list.size() > 0)) {
/* 229 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 232 */       CodeValue cv = new CodeValue();
/* 233 */       cv.setId(Long.valueOf(-1L));
/* 234 */       cv.setName(getText("select.option.all"));
/* 235 */       codes.add(0, cv);
/* 236 */       return codes;
/*     */     } catch (DaoException e) {
/* 238 */       e.printStackTrace();
/*     */ 
/* 240 */       CodeValue cv = new CodeValue();
/* 241 */       cv.setId(Long.valueOf(-1L));
/* 242 */       cv.setName(getText("select.option.all"));
/* 243 */       codes.add(0, cv);
/* 244 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 251 */     List codes = null;
/*     */     try {
/* 253 */       codes = new ArrayList();
/* 254 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("042"));
/*     */ 
/* 256 */       if ((null != one) && (one.size() > 0)) {
/* 257 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 259 */         if ((null != list) && (list.size() > 0)) {
/* 260 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 263 */       CodeValue cv = new CodeValue();
/* 264 */       cv.setId(Long.valueOf(-1L));
/* 265 */       cv.setName(getText("select.option.all"));
/* 266 */       codes.add(0, cv);
/* 267 */       return codes;
/*     */     } catch (DaoException e) {
/* 269 */       e.printStackTrace();
/*     */ 
/* 271 */       CodeValue cv = new CodeValue();
/* 272 */       cv.setId(Long.valueOf(-1L));
/* 273 */       cv.setName(getText("select.option.all"));
/* 274 */       codes.add(0, cv);
/* 275 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.ListActivityAction
 * JD-Core Version:    0.6.2
 */