/*     */ package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProfiles;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.util.comparator.CodeValueComparator;

/*     */ import java.util.ArrayList;
import java.util.Collections;
/*     */ import java.util.List; 
import java.util.Map;
/*     */
/*     */ public class ListCustomerInfoAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final AreaManager areaManager;
/*     */   private List<CustomerInfo> customers;
/*     */ 
/*     */   public ListCustomerInfoAction(CustomerInfoManager customerInfoManager, CodeValueManager codeValueManager, AreaManager areaManager)
/*     */   {
/*  63 */     this.customerInfoManager = customerInfoManager;
/*  64 */     this.codeValueManager = codeValueManager;
/*  65 */     this.areaManager = areaManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  75 */     if ((null == this.customers) && 
/*  76 */       (hasIds("customerInfoIds")))
/*  77 */       this.customers = this.customerInfoManager.loadAllCustomerInfo(getIds("customerInfoIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  87 */     return "customerInfoHQL";
/*     */   }

			protected Map getRequestParameterMap()
/*     */   {
/* 121 */     Map map = super.getRequestParameterMap();
 			 if (this.request.getParameter("customerInfo.createdTime")!=null) {
/* 126 */       map.put("customerInfo.createdTime", this.request.getParameter("customerInfo.createdTime")+"%");
/*     */     }
			  return map;
			}
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  96 */     if (isDisabled()) {
/*  97 */       disabled();
/*     */     }
/*  99 */     if (isEnable()) {
/* 100 */       enabled();
/*     */     }
/* 102 */     if (isDelete()) {
/* 103 */       delete();
/*     */     }
/* 105 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 115 */       this.customerInfoManager.deleteAllCustomerInfo(this.customers);
/* 116 */       addActionMessage(getText("customerInfo.delete.success"));
/* 117 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 119 */       addActionMessage(getText("customerInfo.delete.error"));
/* 120 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 131 */       this.customerInfoManager.disabledAllCustomerInfo(this.customers);
/* 132 */       addActionMessage(getText("customerInfo.disabled.success"));
/* 133 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 135 */       addActionMessage(getText("customerInfo.disabled.error"));
/* 136 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 146 */       this.customerInfoManager.enabledAllCustomerInfo(this.customers);
/* 147 */       addActionMessage(getText("customerInfo.enabled.success"));
/* 148 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 150 */       e.printStackTrace();
/* 151 */       addActionMessage(getText("customerInfo.enabled.error"));
/* 152 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/*     */     try
/*     */     {
/* 162 */       List codes = new ArrayList();
/* 163 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
/* 164 */       if ((null != one) && (one.size() > 0)) {
/* 165 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 166 */         codes.addAll(list);
/*     */       }
/* 168 */       CodeValue cv = new CodeValue();
/* 169 */       cv.setId(Long.valueOf(-1L));
/* 170 */       cv.setName(getText("select.option.all"));
/* 171 */       codes.add(0, cv);
/* 172 */       return codes;
/*     */     } catch (DaoException e) {
/* 174 */       e.printStackTrace();
/* 175 */       List codes = new ArrayList();
/* 176 */       CodeValue cv = new CodeValue();
/* 177 */       cv.setId(Long.valueOf(-1L));
/* 178 */       cv.setName(getText("select.option.all"));
/* 179 */       codes.add(0, cv);
/* 180 */       return codes;
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Area> getAllCountrys()
/*     */   {
/*     */     try
/*     */     {
/* 190 */       List countrys = new ArrayList();
/* 191 */       List list = this.areaManager.loadByKey("type", "country");
/* 192 */       if ((null != list) && (list.size() > 0)) {
/* 193 */         countrys.addAll(list);
/*     */       }
/* 195 */       Area area = new Area();
/* 196 */       area.setId(Long.valueOf(-1L));
/* 197 */       area.setName(getText("select.option.all"));
/* 198 */       countrys.add(0, area);
/* 199 */       return countrys;
/*     */     } catch (Exception e) {
/* 201 */       e.printStackTrace();
/* 202 */       List countrys = new ArrayList();
/* 203 */       Area area = new Area();
/* 204 */       area.setId(Long.valueOf(-1L));
/* 205 */       area.setName(getText("select.option.all"));
/* 206 */       countrys.add(0, area);
/* 207 */       return countrys;
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Area> getAllProvince()
/*     */   {
/* 216 */     List province = new ArrayList();
/* 217 */     Area area = new Area();
/* 218 */     area.setId(Long.valueOf(-1L));
/* 219 */     area.setName(getText("select.option.all"));
/* 220 */     province.add(0, area);
/* 221 */     return province;
/*     */   }
/*     */ 
/*     */   public List<Area> getAllCity()
/*     */   {
/* 229 */     List city = new ArrayList();
/* 230 */     Area area = new Area();
/* 231 */     area.setId(Long.valueOf(-1L));
/* 232 */     area.setName(getText("select.option.all"));
/* 233 */     city.add(0, area);
/* 234 */     return city;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllIndustrys()
/*     */   {
/*     */     try
/*     */     {
/* 243 */       List industrys = new ArrayList();
/* 244 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("002"));
/* 245 */       if ((null != one) && (one.size() > 0) && 
/* 246 */         (null != one) && (one.size() > 0)) {
/* 247 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 248 */         industrys.addAll(list);
/*     */       }
				Collections.sort(industrys, new CodeValueComparator());
/*     */ 
/* 251 */       CodeValue cv = new CodeValue();
/* 252 */       cv.setId(Long.valueOf(-1L));
/* 253 */       cv.setName(getText("select.option.all"));
/* 254 */       industrys.add(0, cv);
/* 255 */       return industrys;
/*     */     } catch (Exception e) {
/* 257 */       e.printStackTrace();
/* 258 */       List industrys = new ArrayList();
/* 259 */       CodeValue cv = new CodeValue();
/* 260 */       cv.setId(Long.valueOf(-1L));
/* 261 */       cv.setName(getText("select.option.all"));
/* 262 */       industrys.add(0, cv);
/* 263 */       return industrys;
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllCompanyNatures()
/*     */   {
/*     */     try
/*     */     {
/* 273 */       List companyNatures = new ArrayList();
/* 274 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("003"));
/* 275 */       if ((null != one) && (one.size() > 0)) {
/* 276 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 277 */         if ((null != list) && (list.size() > 0)) {
/* 278 */           companyNatures.addAll(list);
/*     */         }
/*     */       }
/* 281 */       CodeValue cv = new CodeValue();
/* 282 */       cv.setId(Long.valueOf(-1L));
/* 283 */       cv.setName(getText("select.option.all"));
/* 284 */       companyNatures.add(0, cv);
/* 285 */       return companyNatures;
/*     */     } catch (Exception e) {
/* 287 */       e.printStackTrace();
/* 288 */       List companyNatures = new ArrayList();
/* 289 */       CodeValue cv = new CodeValue();
/* 290 */       cv.setId(Long.valueOf(-1L));
/* 291 */       cv.setName(getText("select.option.all"));
/* 292 */       companyNatures.add(0, cv);
/* 293 */       return companyNatures;
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllSteps()
/*     */   {
/*     */     try
/*     */     {
/* 302 */       List codes = new ArrayList();
/* 303 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("022"));
/* 304 */       if ((null != one) && (one.size() > 0)) {
/* 305 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(1)).getId());
/* 306 */         if ((null != list) && (list.size() > 0)) {
/* 307 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 310 */       CodeValue cv = new CodeValue();
/* 311 */       cv.setId(Long.valueOf(-1L));
/* 312 */       cv.setName(getText("select.option.all"));
/* 313 */       codes.add(0, cv);
/* 314 */       return codes;
/*     */     } catch (DaoException e) {
/* 316 */       e.printStackTrace();
/* 317 */       List codes = new ArrayList();
/* 318 */       CodeValue cv = new CodeValue();
/* 319 */       cv.setId(Long.valueOf(-1L));
/* 320 */       cv.setName(getText("select.option.all"));
/* 321 */       codes.add(0, cv);
/* 322 */       return codes;
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProfiles.ListCustomerInfoAction
 * JD-Core Version:    0.6.2
 */