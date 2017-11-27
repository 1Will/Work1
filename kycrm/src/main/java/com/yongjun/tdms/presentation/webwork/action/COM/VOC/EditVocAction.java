/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.VOC;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.COM.VOC.Voc;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.COM.VOC.VocManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditVocAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final VocManager vocManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private Voc voc;
/*     */ 
/*     */   public EditVocAction(VocManager vocManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager)
/*     */   {
/*  71 */     this.vocManager = vocManager;
/*  72 */     this.codeValueManager = codeValueManager;
/*  73 */     this.personnelFilesManager = personnelFilesManager;
/*  74 */     this.customerInfoManager = customerInfoManager;
/*  75 */     this.contactArchivesManager = contactArchivesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  83 */     if (hasId("voc.id"))
/*  84 */       this.voc = this.vocManager.loadVoc(getId("voc.id"));
/*     */     else
/*  86 */       this.voc = new Voc();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/*  96 */     boolean isNew = this.voc.isNew();
/*     */ 
/*  98 */     if (!StringUtils.isEmpty(this.request.getParameter("status.id"))) {
/*  99 */       this.voc.setStatus(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("status.id"))));
/*     */     }
/*     */ 
/* 104 */     if (!StringUtils.isEmpty(this.request.getParameter("type.id"))) {
/* 105 */       this.voc.setType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("type.id"))));
/*     */     }
/*     */ 
/* 110 */     if (!StringUtils.isEmpty(this.request.getParameter("importance.id"))) {
/* 111 */       this.voc.setImportance(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("importance.id"))));
/*     */     }
/*     */ 
/* 116 */     if (!StringUtils.isEmpty(this.request.getParameter("resource.id"))) {
/* 117 */       this.voc.setResource(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("resource.id"))));
/*     */     }
/*     */ 
/* 121 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 122 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 124 */       if (null != customer) {
/* 125 */         this.voc.setCustomerInfo(customer);
/*     */       }
/*     */     }
/*     */ 
/* 129 */     if (!StringUtils.isEmpty(this.request.getParameter("salesman.id"))) {
/* 130 */       PersonnelFiles salesman = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("salesman.id")));
/*     */ 
/* 132 */       if (null != salesman) {
/* 133 */         this.voc.setSalesman(salesman);
/*     */       }
/*     */     }
/*     */ 
/* 137 */     if (!StringUtils.isEmpty(this.request.getParameter("principal.id"))) {
/* 138 */       PersonnelFiles principal = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("principal.id")));
/*     */ 
/* 140 */       if (null != principal) {
/* 141 */         this.voc.setPrincipal(principal);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 146 */     if (!StringUtils.isEmpty(this.request.getParameter("linkman.id"))) {
/* 147 */       ContactArchives linkman = this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request.getParameter("linkman.id")));
/*     */ 
/* 149 */       if (null != linkman) {
/* 150 */         this.voc.setLinkman(linkman);
/*     */       }
/*     */     }
/*     */ 
/* 154 */     if (isNew) {
/* 155 */       String code = autoCompleteCode();
/* 156 */       this.voc.setCode(code);
/* 157 */       this.vocManager.storeVoc(this.voc);
/* 158 */       addActionMessage(getText("voc.save.success"));
/* 159 */       return "new";
/*     */     }
/* 161 */     this.vocManager.storeVoc(this.voc);
/* 162 */     addActionMessage(getText("voc.edit.success"));
/* 163 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 173 */     List codes = null;
/*     */     try {
/* 175 */       codes = new ArrayList();
/* 176 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("050"));
/*     */ 
/* 178 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 180 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 182 */         if ((null != list) && (list.size() > 0)) {
/* 183 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 186 */       return codes;
/*     */     } catch (DaoException e) {
/* 188 */       e.printStackTrace();
/*     */     }
/* 190 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 198 */     List codes = null;
/*     */     try {
/* 200 */       codes = new ArrayList();
/* 201 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
/*     */ 
/* 203 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 205 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 207 */         if ((null != list) && (list.size() > 0)) {
/* 208 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 211 */       return codes;
/*     */     } catch (DaoException e) {
/* 213 */       e.printStackTrace();
/*     */     }
/* 215 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllImportances()
/*     */   {
/* 223 */     List codes = null;
/*     */     try {
/* 225 */       codes = new ArrayList();
/* 226 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("040"));
/*     */ 
/* 228 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 230 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 232 */         if ((null != list) && (list.size() > 0)) {
/* 233 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 236 */       return codes;
/*     */     } catch (DaoException e) {
/* 238 */       e.printStackTrace();
/*     */     }
/* 240 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllResources()
/*     */   {
/* 248 */     List codes = null;
/*     */     try {
/* 250 */       codes = new ArrayList();
/* 251 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("006"));
/*     */ 
/* 253 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 255 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 257 */         if ((null != list) && (list.size() > 0)) {
/* 258 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 261 */       return codes;
/*     */     } catch (DaoException e) {
/* 263 */       e.printStackTrace();
/*     */     }
/* 265 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 274 */     String maxCode = this.vocManager.getMaxPFCode("KHXQ");
/* 275 */     if (null != maxCode) {
/* 276 */       int num = Integer.parseInt(maxCode) + 1;
/* 277 */       if (num < 10)
/* 278 */         return "KHXQ-000" + num;
/* 279 */       if (num < 100)
/* 280 */         return "KHXQ-00" + num;
/* 281 */       if (num < 1000) {
/* 282 */         return "KHXQ-0" + num;
/*     */       }
/* 284 */       return "KHXQ-" + num;
/*     */     }
/*     */ 
/* 287 */     return "KHXQ-0001";
/*     */   }
/*     */ 
/*     */   public Voc getVoc()
/*     */   {
/* 295 */     return this.voc;
/*     */   }
/*     */ 
/*     */   public void setVoc(Voc voc)
/*     */   {
/* 304 */     this.voc = voc;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.VOC.EditVocAction
 * JD-Core Version:    0.6.2
 */