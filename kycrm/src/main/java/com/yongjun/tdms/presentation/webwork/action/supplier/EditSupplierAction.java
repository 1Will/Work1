/*     */ package com.yongjun.tdms.presentation.webwork.action.supplier;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditSupplierAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 3513903077905230816L;
/*     */   private SupplierManager supplierManager;
/*     */   private CodeValueManager codeValueManager;
/*     */   private AreaManager areaManager;
/*     */   private Supplier supplier;
/*     */   private CodeValue supplierType;
/*     */   private CodeValue tradeType;
/*     */   private CodeValue companyType;
/*     */   private Area country;
/*     */   private Area province;
/*     */   private Area city;
/*     */ 
/*     */   public EditSupplierAction(SupplierManager supplierManager, CodeValueManager codeValueManager, AreaManager areaManager)
/*     */   {
/*  76 */     this.supplierManager = supplierManager;
/*  77 */     this.codeValueManager = codeValueManager;
/*  78 */     this.areaManager = areaManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  82 */     if (null == this.supplier)
/*  83 */       if (hasId("supplier.id")) {
/*  84 */         this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/*     */ 
/*  86 */         this.supplierType = this.supplier.getSupplierType();
/*  87 */         this.tradeType = this.supplier.getTradeType();
/*  88 */         this.companyType = this.supplier.getCompanyType();
/*  89 */         this.country = this.supplier.getCountry();
/*  90 */         this.province = this.supplier.getProvince();
/*  91 */         this.city = this.supplier.getCity();
/*     */       } else {
/*  93 */         this.supplier = new Supplier();
/*     */       }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 100 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 105 */     if (!StringUtils.isEmpty(this.request.getParameter("supplier.supplierType"))) {
/* 106 */       this.supplierType = this.codeValueManager.loadCodeValue(getId("supplier.supplierType"));
/*     */ 
/* 108 */       this.supplier.setSupplierType(this.supplierType);
/*     */     }
/*     */ 
/* 111 */     if (!StringUtils.isEmpty(this.request.getParameter("supplier.tradeType"))) {
/* 112 */       this.tradeType = this.codeValueManager.loadCodeValue(getId("supplier.tradeType"));
/*     */ 
/* 114 */       this.supplier.setTradeType(this.tradeType);
/*     */     }
/*     */ 
/* 117 */     if (!StringUtils.isEmpty(this.request.getParameter("supplier.companyType"))) {
/* 118 */       this.companyType = this.codeValueManager.loadCodeValue(getId("supplier.companyType"));
/*     */ 
/* 120 */       this.supplier.setCompanyType(this.companyType);
/*     */     }
/*     */ 
/* 123 */     if (!StringUtils.isEmpty(this.request.getParameter("supplier.country"))) {
/* 124 */       this.country = this.areaManager.loadArea(getId("supplier.country"));
/* 125 */       this.supplier.setCountry(this.country);
/*     */     }
/*     */ 
/* 128 */     if (!StringUtils.isEmpty(this.request.getParameter("supplier.province"))) {
/* 129 */       this.province = this.areaManager.loadArea(getId("supplier.province"));
/*     */ 
/* 131 */       this.supplier.setProvince(this.province);
/*     */     }
/*     */ 
/* 134 */     if ((!StringUtils.isEmpty(this.request.getParameter("supplier.city"))) && (!"-1".equals(this.request.getParameter("supplier.city")))) {
/* 135 */       this.city = this.areaManager.loadArea(getId("supplier.city"));
/* 136 */       this.supplier.setCity(this.city);
/*     */     }
/*     */ 
/* 139 */     if (!StringUtils.isEmpty(this.request.getParameter("supplier.ManagingScope")))
/*     */     {
/* 141 */       this.supplier.setManagingScope(this.request.getParameter("supplier.ManagingScope"));
/*     */     }
/*     */ 
/* 144 */     boolean isNew = this.supplier.isNew();
/*     */     try {
/* 146 */       if (isNew) {
/* 147 */         if (null == this.supplierManager.loadByKey("supplierNo", this.supplier.getSupplierNo()))
/*     */         {
/* 149 */           this.supplierManager.storeSupplier(this.supplier);
/*     */         } else {
/* 151 */           addActionMessage(getText("supplier.add.exist", Arrays.asList(new Object[] { this.supplier.getSupplierNo() })));
/*     */ 
/* 153 */           return "error";
/*     */         }
/*     */       }
/* 156 */       else this.supplierManager.storeSupplier(this.supplier); 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 159 */       e.printStackTrace();
/* 160 */       addActionMessage(getText("supplier.add.error", Arrays.asList(new Object[] { this.supplier.getSupplierNo() })));
/*     */ 
/* 162 */       return "error";
/*     */     }
/* 164 */     if (isNew) {
/* 165 */       addActionMessage(getText("supplier.add.success", Arrays.asList(new Object[] { this.supplier.getSupplierNo() })));
/*     */ 
/* 167 */       return "new";
/*     */     }
/* 169 */     addActionMessage(getText("supplier.edit.success", Arrays.asList(new Object[] { this.supplier.getSupplierNo() })));
/*     */ 
/* 171 */     return "success";
/*     */   }
/*     */ 
/*     */   public List getAllSupplierType()
/*     */   {
/*     */     try
/*     */     {
/* 180 */       List list = this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "008").get(0)).getId());
/*     */ 
/* 183 */       CodeValue cv = new CodeValue();
/* 184 */       cv.setId(Long.valueOf(0L));
/* 185 */       cv.setName(getText(""));
/* 186 */       list.add(0, cv);
/* 187 */       return list;
/*     */     } catch (DaoException e) {
/* 189 */       e.printStackTrace();
/* 190 */     }return null;
/*     */   }
/*     */ 
/*     */   public List getAllTradeType()
/*     */   {
/*     */     try
/*     */     {
/* 199 */       List list = this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "002").get(0)).getId());
/*     */ 
/* 202 */       CodeValue cv = new CodeValue();
/* 203 */       cv.setId(Long.valueOf(0L));
/* 204 */       cv.setName(getText(""));
/* 205 */       list.add(0, cv);
/* 206 */       return list;
/*     */     } catch (DaoException e) {
/* 208 */       e.printStackTrace();
/* 209 */     }return null;
/*     */   }
/*     */ 
/*     */   public List getAllCompanyType()
/*     */   {
/*     */     try
/*     */     {
/* 218 */       List list = this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "003").get(0)).getId());
/*     */ 
/* 221 */       CodeValue cv = new CodeValue();
/* 222 */       cv.setId(Long.valueOf(0L));
/* 223 */       cv.setName(getText(""));
/* 224 */       list.add(0, cv);
/* 225 */       return list;
/*     */     } catch (DaoException e) {
/* 227 */       e.printStackTrace();
/* 228 */     }return null;
/*     */   }
/*     */ 
/*     */   public List getAllCountry()
/*     */   {
/*     */     try
/*     */     {
/* 237 */       List list = this.areaManager.loadByKey("type", "country");
/* 238 */       Area area = new Area();
/* 239 */       area.setId(Long.valueOf(0L));
/* 240 */       area.setName(getText(""));
/* 241 */       list.add(0, area);
/* 242 */       return list;
/*     */     } catch (DaoException e) {
/* 244 */       e.printStackTrace();
/* 245 */     }return null;
/*     */   }
/*     */ 
/*     */   public List getAllProvince()
/*     */   {
/* 264 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List getAllCity()
/*     */   {
/* 282 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public Supplier getSupplier() {
/* 286 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplier(Supplier supplier) {
/* 290 */     this.supplier = supplier;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.supplier.EditSupplierAction
 * JD-Core Version:    0.6.2
 */