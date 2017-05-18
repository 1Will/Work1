/*     */ package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListContactArchivesAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final SupplierManager supplierManager;
/*     */   private List<ContactArchives> cas;
/*     */   private Long customerId;
            private Long projectInfoId;
/*     */   private Long customerTypeId;
/*     */   private String backVistiFlag;
            private String backVisitCheckBox;
/*     */   private Supplier supplier;
/*     */ 
/*     */   public ListContactArchivesAction(ContactArchivesManager contactArchivesManager, CodeValueManager codeValueManager, SupplierManager supplierManager)
/*     */   {
/*  70 */     this.contactArchivesManager = contactArchivesManager;
/*  71 */     this.codeValueManager = codeValueManager;
/*  72 */     this.supplierManager = supplierManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  82 */     if (hasIds("contactArchivesIds")) {
/*  83 */       this.cas = this.contactArchivesManager.loadAllContactArchives(getIds("contactArchivesIds"));
/*     */     }
/*     */ 
/*  86 */     if (hasId("type.id")) {
/*  87 */       this.customerTypeId = getId("type.id");
/*     */     }
/*     */ 
/*  90 */     if (hasId("customerInfo.id")) {
/*  91 */       this.customerId = getId("customerInfo.id");
/*  92 */       setFirst(false);
/*     */     }
				if (hasId("projectInfo.id")) {
/*  91 */       this.projectInfoId = getId("projectInfo.id");
/*  92 */       setFirst(false);
/*     */     }
/*  94 */     if (hasId("supplier.id")) {
/*  95 */       this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/*  96 */       setFirst(false);
/*     */     }
/*  98 */     if (this.request.getParameter("backVisitFlag") != null) {
/*  99 */       this.backVistiFlag = this.request.getParameter("backVisitFlag");
/* 100 */       this.customerId = Long.valueOf(this.request.getParameter("customer.id"));
/*     */     }
			 if (this.request.getParameter("backVisitCheckBox") != null) {
/*  99 */       this.backVisitCheckBox = this.request.getParameter("backVisitCheckBox");
/*     */     }
/*     */ 
/* 103 */     if (hasIds("contactInfoIds"))
/* 104 */       this.cas = this.contactArchivesManager.loadAllContactArchives(getIds("contactInfoIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 113 */     return "contactArchivesHQL";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 121 */     Map map = super.getRequestParameterMap();
/* 122 */     if ((null != this.request.getParameter("sex")) && ("" != this.request.getParameter("sex"))) {
/* 123 */       map.put("sex", Boolean.valueOf(this.request.getParameter("sex")));
/*     */     }
/* 125 */     if (hasId("customerInfo.id")) {
/* 126 */       map.put("customerId", getId("customerInfo.id"));
/*     */     }
/* 125 */     if (this.request.getParameter("contactArchives.createdTime")!=null) {
	/* 126 */       map.put("contactArchives.createdTime", this.request.getParameter("contactArchives.createdTime")+"%");
/*     */     }
			  if (hasId("projectInfo.id")) {
/* 126 */       map.put("projectInfoId", getId("projectInfo.id"));
/*     */     }
/*     */ 
/* 129 */     if (hasId("customer.id")) {
/* 130 */       map.put("customerId", getId("customer.id"));
/*     */     }
/* 132 */     if ((null != this.request.getParameter("supplier.id")) && ("" != this.request.getParameter("supplier.id"))) {
/* 133 */       map.put("supplierId", Long.valueOf(this.request.getParameter("supplier.id")));
/*     */     }
/* 135 */     return map;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 144 */     if (isDisabled()) {
/* 145 */       disabled();
/*     */     }
/* 147 */     if (isEnable()) {
/* 148 */       enabled();
/*     */     }
/* 150 */     if (isDelete()) {
/* 151 */       delete();
/*     */     }
/* 153 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
				//客户档案移除联系人使用，不删除联系人
				if("remove".equals(request.getParameter("remove"))){
					for(ContactArchives contactArchives : cas){
						contactArchives.setCustomerName(null);
						contactArchives.setCustName(null);
						this.contactArchivesManager.storeContactArchives(contactArchives);
					}
					addActionMessage(getText("contactArchives.remove.success"));
					return "success";
				}
				this.contactArchivesManager.deleteAllContactArchives(this.cas);
/* 164 */       addActionMessage(getText("contactArchives.delete.success"));
/* 165 */       return "success";
/*     */     } catch (RuntimeException e) {
				e.printStackTrace();
/* 167 */       addActionMessage(getText("contactArchives.delete.error"));
/* 168 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 179 */       this.contactArchivesManager.disabledAllContactArchives(this.cas);
/* 180 */       addActionMessage(getText("contactArchives.disabled.success"));
/* 181 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 183 */       addActionMessage(getText("contactArchives.disabled.error"));
/* 184 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 194 */       this.contactArchivesManager.enabledAllContactArchives(this.cas);
/* 195 */       addActionMessage(getText("contactArchives.enabled.success"));
/* 196 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 198 */       e.printStackTrace();
/* 199 */       addActionMessage(getText("contactArchives.enabled.error"));
/* 200 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/*     */     try
/*     */     {
/* 210 */       List codes = new ArrayList();
/* 211 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
/* 212 */       if ((null != one) && (one.size() > 0)) {
/* 213 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 214 */         if ((null != list) && (list.size() > 0)) {
/* 215 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 218 */       CodeValue cv = new CodeValue();
/* 219 */       cv.setId(Long.valueOf(-1L));
/* 220 */       cv.setName(getText("select.option.all"));
/* 221 */       codes.add(0, cv);
/* 222 */       return codes;
/*     */     } catch (DaoException e) {
/* 224 */       e.printStackTrace();
/* 225 */       List codes = new ArrayList();
/* 226 */       CodeValue cv = new CodeValue();
/* 227 */       cv.setId(Long.valueOf(-1L));
/* 228 */       cv.setName(getText("select.option.all"));
/* 229 */       codes.add(0, cv);
/* 230 */       return codes;
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<ContactArchives> getCas()
/*     */   {
/* 238 */     return this.cas;
/*     */   }
/*     */ 
/*     */   public void setCas(List<ContactArchives> cas)
/*     */   {
/* 245 */     this.cas = cas;
/*     */   }
/*     */ 
/*     */   public String getBackVistiFlag() {
/* 249 */     return this.backVistiFlag;
/*     */   }
/*     */ 
/*     */   public void setBackVistiFlag(String backVistiFlag) {
/* 253 */     this.backVistiFlag = backVistiFlag;
/*     */   }
/*     */ 
/*     */   public Long getCustomerId() {
/* 257 */     return this.customerId;
/*     */   }
/*     */ 
/*     */   public void setCustomerId(Long customerId) {
/* 261 */     this.customerId = customerId;
/*     */   }
/*     */ 
/*     */   public Long getCustomerTypeId()
/*     */   {
/* 268 */     return this.customerTypeId;
/*     */   }
/*     */ 
/*     */   public void setCustomerTypeId(Long customerTypeId)
/*     */   {
/* 275 */     this.customerTypeId = customerTypeId;
/*     */   }
/*     */ 
/*     */   public Supplier getSupplier() {
/* 279 */     return this.supplier;
/*     */   }
			public String getBackVisitCheckBox() {
				return backVisitCheckBox;
			}
			public void setBackVisitCheckBox(String backVisitCheckBox) {
				this.backVisitCheckBox = backVisitCheckBox;
			}
			public Long getProjectInfoId() {
				return projectInfoId;
			}
			public void setProjectInfoId(Long projectInfoId) {
				this.projectInfoId = projectInfoId;
			}
			
 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.ListContactArchivesAction
 * JD-Core Version:    0.6.2
 */