/*     */ package com.yongjun.tdms.presentation.webwork.action.advisory;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.advisory.Advisory;
/*     */ import com.yongjun.tdms.service.advisory.AdvisoryManager;
import com.yongjun.tdms.util.comparator.CodeValueComparator;

/*     */ import java.util.ArrayList;
import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListAdvisoryAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5616960016127750986L;
/*     */   private final AdvisoryManager advisoryManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<Advisory> advisorys;
/*     */ 
/*     */   public ListAdvisoryAction(AdvisoryManager advisoryManager, CodeValueManager codeValueManager)
/*     */   {
/*  30 */     this.advisoryManager = advisoryManager;
/*  31 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  38 */     if ((this.advisorys == null) && (hasIds("advisoryIds")))
/*  39 */       this.advisorys = this.advisoryManager.loadAllAdvisory(getIds("advisoryIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  44 */     return "advisory";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  50 */     Map map = super.getRequestParameterMap();
/*  51 */     if ((null != this.request.getParameter("isNoB")) && (this.request.getParameter("isNoB") != "")) {
/*  52 */       map.put("isNoB", Boolean.valueOf(this.request.getParameter("isNoB")));
/*     */     }
//				map.put("isNoB",true);
				if((map.get("industry")+"").equals("-1")){
					
					map.remove("industry");
				}
/*  54 */     return map;
/*     */   }
/*     */ 
/*     */   public List getAllCustTypes()
/*     */   {
/*  60 */     List cust_types = new ArrayList();
/*     */     try {
/*  62 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "001").get(0);
/*  63 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/*  64 */       if (cust_types != null) {
/*  65 */         CodeValue cv = new CodeValue();
/*  66 */         cv.setId(Long.valueOf(-1L));
/*  67 */         cv.setName(getText("select.option.all"));
/*  68 */         cust_types.add(0, cv);
/*  69 */         return cust_types;
/*     */       }
/*  71 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/*  75 */       e.printStackTrace();
/*  76 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllIndustrys()
/*     */   {
/*  84 */     List industrys = new ArrayList();
/*     */     try {
/*  86 */       CodeValue industry = (CodeValue)this.codeValueManager.loadByKey("code", "002").get(0);
/*  87 */       industrys = this.codeValueManager.loadByKey("parentCV.id", industry.getId());
/*  88 */      			Collections.sort(industrys, new CodeValueComparator());
				if (industrys != null) {
/*  89 */         CodeValue cv = new CodeValue();
/*  90 */         cv.setId(Long.valueOf(-1L));
/*  91 */         cv.setName(getText("select.option.all"));
/*  92 */         industrys.add(0, cv);
/*  93 */         return industrys;
/*     */       }
/*  95 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/*  99 */       e.printStackTrace();
/* 100 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 108 */     if (isDelete()) {
/* 109 */       delete();
/*     */     }
/* 111 */     if (isDisabled()) {
/* 112 */       disabled();
/*     */     }
/* 114 */     if (isEnable()) {
/* 115 */       enabled();
/*     */     }
/* 117 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 124 */       this.advisoryManager.disabledAllAdvisorys(this.advisorys);
/* 125 */       addActionMessage(getText("advisory.disabled.success"));
/* 126 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 128 */       addActionMessage(getText("advisory.disabled.failer"));
/* 129 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       this.advisoryManager.enabledAllAdvisorys(this.advisorys);
/* 138 */       addActionMessage(getText("advisory.enabled.success"));
/* 139 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 141 */       addActionMessage(getText("advisory.enabled.failer"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 151 */       this.advisoryManager.deleteAllAdvisory(this.advisorys);
/* 152 */       addActionMessage(getText("advisory.delete.success"));
/* 153 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 155 */       addActionMessage(getText("advisory.delete.failer"));
/* 156 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<Advisory> getAdvisorys()
/*     */   {
/* 161 */     return this.advisorys;
/*     */   }
/*     */ 
/*     */   public void setAdvisorys(List<Advisory> advisorys) {
/* 165 */     this.advisorys = advisorys;
/*     */   }
/*     */ 
/*     */   public List getAllInfoOriginId()
/*     */   {
/*     */     try
/*     */     {
/* 176 */       CodeValue codeValue = (CodeValue)this.codeValueManager.loadByKey("code", "006").get(0);
/*     */ 
/* 178 */       List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
/* 179 */       if (list != null) {
/* 180 */         CodeValue cv = new CodeValue();
/* 181 */         cv.setName(getText("所有"));
/* 182 */         cv.setId(null);
/* 183 */         list.add(0, cv);
/* 184 */         return list;
/*     */       }
/* 186 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 190 */       e.printStackTrace();
/* 191 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllCustomerRanks()
/*     */   {
/* 200 */     List cust_types = new ArrayList();
/*     */     try {
/* 202 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "022").get(0);
/* 203 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/* 204 */       if (cust_types != null) {
/* 205 */         CodeValue cv = new CodeValue();
/* 206 */         cv.setId(Long.valueOf(-1L));
/* 207 */         cv.setName(getText("select.option.all"));
/* 208 */         cust_types.add(0, cv);
/* 209 */         return cust_types;
/*     */       }
/* 211 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 215 */       e.printStackTrace();
/* 216 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.advisory.ListAdvisoryAction
 * JD-Core Version:    0.6.2
 */