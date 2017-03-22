/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListContractManagementAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private final CodeValueManager codeValueManager;
/*  56 */   private List<ContractManagement> contractManagements = null;
/*     */ 
/*     */   public ListContractManagementAction(ContractManagementManager contractManagementManager, CodeValueManager codeValueManager)
/*     */   {
/*  65 */     this.contractManagementManager = contractManagementManager;
/*  66 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  76 */     if ((null == this.contractManagements) && 
/*  77 */       (hasIds("contractManagementIds")))
/*  78 */       this.contractManagements = this.contractManagementManager.loadAllContractManagement(getIds("contractManagementIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  90 */     String flag = this.request.getParameter("flag");
/*  91 */     String flag2 = this.request.getParameter("flag2");
/*  92 */     if (null != flag)
/*  93 */       return "contractManagementHQL2";
/*  94 */     if (null != flag2) {
/*  95 */       return "contractManagementHQL3";
/*     */     }
/*  97 */     return "contractManagementHQL";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 106 */     Map map = super.getRequestParameterMap();
/* 107 */     if (this.request.getParameter("contractManagement.id") != null) {
/* 108 */       int rNId = Integer.valueOf(this.request.getParameter("contractManagement.id")).intValue();
/* 109 */       map.put("contractManagement.id", Integer.valueOf(rNId));
/*     */     }
/* 111 */     return map;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 122 */     if (isDisabled()) {
/* 123 */       disabled();
/*     */     }
/* 125 */     if (isEnable()) {
/* 126 */       enabled();
/*     */     }
/* 128 */     if (isDelete()) {
/* 129 */       delete();
/*     */     }
/* 131 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 141 */       this.contractManagementManager.deleteAllContractManagement(this.contractManagements);
/* 142 */       addActionMessage(getText("contractManagement.delete.success"));
/* 143 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 145 */       addActionMessage(getText("contractManagement.delete.error"));
/* 146 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 157 */       this.contractManagementManager.disabledAllContractManagement(this.contractManagements);
/* 158 */       addActionMessage(getText("contractManagement.disabled.success"));
/* 159 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 161 */       addActionMessage(getText("contractManagement.disabled.error"));
/* 162 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllComplaintType()
/*     */   {
/* 170 */     List codes = null;
/*     */     try {
/* 172 */       codes = new ArrayList();
/* 173 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("058"));
/*     */ 
/* 175 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 177 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 179 */         if ((null != list) && (list.size() > 0)) {
/* 180 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 183 */       CodeValue cv = new CodeValue();
/* 184 */       cv.setId(null);
/* 185 */       cv.setName(getText("所有"));
/* 186 */       codes.add(0, cv);
/* 187 */       return codes;
/*     */     } catch (DaoException e) {
/* 189 */       e.printStackTrace();
/*     */ 
/* 191 */       CodeValue cv = new CodeValue();
/* 192 */       cv.setId(null);
/* 193 */       cv.setName(getText("所有"));
/* 194 */       codes.add(0, cv);
/* 195 */     }return codes;
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 237 */       this.contractManagementManager.enabledAllContractManagement(this.contractManagements);
/* 238 */       addActionMessage(getText("contractManagement.enabled.success"));
/* 239 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 241 */       e.printStackTrace();
/* 242 */       addActionMessage(getText("contractManagement.enabled.error"));
/* 243 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllContractType()
/*     */   {
/* 253 */     List codes = null;
/*     */     try {
/* 255 */       codes = new ArrayList();
/* 256 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("064"));
/*     */ 
/* 258 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 260 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 262 */         if ((null != list) && (list.size() > 0)) {
/* 263 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 266 */       CodeValue cv = new CodeValue();
/* 267 */       cv.setId(null);
/* 268 */       cv.setName(getText("所有"));
/* 269 */       codes.add(0, cv);
/* 270 */       return codes;
/*     */     } catch (DaoException e) {
/* 272 */       e.printStackTrace();
/*     */ 
/* 274 */       CodeValue cv = new CodeValue();
/* 275 */       cv.setId(null);
/* 276 */       cv.setName(getText("所有"));
/* 277 */       codes.add(0, cv);
/* 278 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllState()
/*     */   {
/* 285 */     List codes = null;
/*     */     try {
/* 287 */       codes = new ArrayList();
/* 288 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("066"));
/*     */ 
/* 290 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 292 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 294 */         if ((null != list) && (list.size() > 0)) {
/* 295 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 298 */       CodeValue cv = new CodeValue();
/* 299 */       cv.setId(null);
/* 300 */       cv.setName(getText("所有"));
/* 301 */       codes.add(0, cv);
/* 302 */       return codes;
/*     */     } catch (DaoException e) {
/* 304 */       e.printStackTrace();
/*     */ 
/* 306 */       CodeValue cv = new CodeValue();
/* 307 */       cv.setId(null);
/* 308 */       cv.setName(getText("所有"));
/* 309 */       codes.add(0, cv);
/* 310 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.ListContractManagementAction
 * JD-Core Version:    0.6.2
 */