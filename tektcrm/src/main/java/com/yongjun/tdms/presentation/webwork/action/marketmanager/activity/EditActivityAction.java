/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.activity;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.marketmanager.activity.Activity;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.marketmanager.activity.ActivityManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditActivityAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 7946494736211593585L;
/*     */   private final ActivityManager activityManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private Activity activity;
/*     */ 
/*     */   public EditActivityAction(ActivityManager activityManager, CodeValueManager codeValueManager, UserManager userManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  81 */     this.activityManager = activityManager;
/*  82 */     this.codeValueManager = codeValueManager;
/*  83 */     this.userManager = userManager;
/*  84 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  93 */     if (hasId("activity.id"))
/*  94 */       this.activity = this.activityManager.loadActivity(getId("activity.id"));
/*     */     else
/*  96 */       this.activity = new Activity();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 105 */     boolean isNew = this.activity.isNew();
/*     */ 
/* 108 */     if (hasId("activityType.id")) {
/* 109 */       CodeValue activityType = new CodeValue();
/* 110 */       activityType = this.codeValueManager.loadCodeValue(getId("activityType.id"));
/* 111 */       this.activity.setActivityType(activityType);
/*     */     }
/*     */ 
/* 115 */     if (hasId("status.id")) {
/* 116 */       CodeValue status = new CodeValue();
/* 117 */       status = this.codeValueManager.loadCodeValue(getId("status.id"));
/* 118 */       this.activity.setStatus(status);
/*     */     }
/*     */ 
/* 122 */     if (hasId("progress.id")) {
/* 123 */       CodeValue progress = new CodeValue();
/* 124 */       progress = this.codeValueManager.loadCodeValue(getId("progress.id"));
/* 125 */       this.activity.setProgress(progress);
/*     */     }
/*     */ 
/* 129 */     if (hasId("priority.id")) {
/* 130 */       CodeValue priority = new CodeValue();
/* 131 */       priority = this.codeValueManager.loadCodeValue(getId("priority.id"));
/* 132 */       this.activity.setPriority(priority);
/*     */     }
/*     */ 
/* 135 */     if (!StringUtils.isEmpty(this.request.getParameter("activity.fee"))) {
/* 136 */       String feeStr = String.valueOf(this.request.getParameter("activity.fee"));
/* 137 */       Double fee = Double.valueOf(feeStr);
/* 138 */       this.activity.setFee(fee);
/*     */     }
/*     */ 
/* 141 */     if (hasId("persons.id")) {
/* 142 */       PersonnelFiles persons = new PersonnelFiles();
/* 143 */       persons = this.personnelFilesManager.loadPersonnel(getId("persons.id"));
/* 144 */       this.activity.setPersons(persons);
/*     */     }
/*     */ 
/* 147 */     this.activity.setOrganization(this.userManager.getOrganization());
/*     */     try {
/* 149 */       if (isNew) {
/* 150 */         String newCode = autoCompleteCode();
/* 151 */         this.activity.setCode(newCode);
/* 152 */         this.activityManager.storeActivity(this.activity);
/* 153 */         addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.activity.getCode() })));
/*     */ 
/* 155 */         return "new";
/*     */       }
/* 157 */       this.activityManager.storeActivity(this.activity);
/* 158 */       addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.activity.getCode() })));
/*     */ 
/* 160 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 163 */       e.printStackTrace();
/* 164 */       if (isNew) {
/* 165 */         addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.activity.getCode() })));
/*     */       }
/*     */       else
/*     */       {
/* 169 */         addActionMessage(getText("edit.error", Arrays.asList(new Object[] { this.activity.getCode() })));
/*     */       }
/*     */     }
/* 172 */     return "error";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 182 */     String maxCode = this.activityManager.getMaxPFCode("SCHD", this.userManager.getOrganization().getId());
/* 183 */     if (null != maxCode) {
/* 184 */       int num = Integer.parseInt(maxCode) + 1;
/* 185 */       if (num < 10)
/* 186 */         return "SCHD-000" + num;
/* 187 */       if (num < 100)
/* 188 */         return "SCHD-00" + num;
/* 189 */       if (num < 1000) {
/* 190 */         return "SCHD-0" + num;
/*     */       }
/* 192 */       return "SCHD-" + num;
/*     */     }
/*     */ 
/* 196 */     return "SCHD-0001";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllActivityType()
/*     */   {
/* 205 */     List codes = null;
/*     */     try {
/* 207 */       codes = new ArrayList();
/* 208 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("041"));
/*     */ 
/* 210 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 212 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 214 */         if ((null != list) && (list.size() > 0)) {
/* 215 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 218 */       return codes;
/*     */     } catch (DaoException e) {
/* 220 */       e.printStackTrace();
/*     */     }
/* 222 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllProgress()
/*     */   {
/* 229 */     List codes = null;
/*     */     try {
/* 231 */       codes = new ArrayList();
/* 232 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("043"));
/*     */ 
/* 234 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 236 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 238 */         if ((null != list) && (list.size() > 0)) {
/* 239 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 242 */       return codes;
/*     */     } catch (DaoException e) {
/* 244 */       e.printStackTrace();
/*     */     }
/* 246 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPriority()
/*     */   {
/* 253 */     List codes = null;
/*     */     try {
/* 255 */       codes = new ArrayList();
/* 256 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("044"));
/*     */ 
/* 258 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 260 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 262 */         if ((null != list) && (list.size() > 0)) {
/* 263 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 266 */       return codes;
/*     */     } catch (DaoException e) {
/* 268 */       e.printStackTrace();
/*     */     }
/* 270 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 277 */     List codes = null;
/*     */     try {
/* 279 */       codes = new ArrayList();
/* 280 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("042"));
/*     */ 
/* 282 */       if ((null != one) && (one.size() > 0)) {
/* 283 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 285 */         if ((null != list) && (list.size() > 0)) {
/* 286 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 289 */       return codes;
/*     */     } catch (DaoException e) {
/* 291 */       e.printStackTrace();
/*     */     }
/* 293 */     return codes;
/*     */   }
/*     */ 
/*     */   public Activity getActivity()
/*     */   {
/* 301 */     return this.activity;
/*     */   }
/*     */ 
/*     */   public void setActivity(Activity activity)
/*     */   {
/* 309 */     this.activity = activity;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.activity.EditActivityAction
 * JD-Core Version:    0.6.2
 */