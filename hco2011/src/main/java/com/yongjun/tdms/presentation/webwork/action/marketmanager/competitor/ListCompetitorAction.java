/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.competitor;
/*     */ 
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.marketmanager.competitor.Competitor;
/*     */ import com.yongjun.tdms.service.marketmanager.competitor.CompetitorManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListCompetitorAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CompetitorManager competitorManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private List<Competitor> competitorList;
/*     */ 
/*     */   public ListCompetitorAction(CompetitorManager competitorManager, CodeValueManager codeValueManager, UserManager userManager)
/*     */   {
/*  67 */     this.competitorManager = competitorManager;
/*  68 */     this.codeValueManager = codeValueManager;
/*  69 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  78 */     if (hasIds("competitorIds")) {
/*  79 */       this.competitorList = this.competitorManager.loadAllCompetitor(getIds("competitorIds"));
/*     */     }
/*     */     else
/*     */     {
/*  83 */       this.competitorList = new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  91 */     if (isDisabled()) {
/*  92 */       return disable();
/*     */     }
/*  94 */     if (isEnable()) {
/*  95 */       return enable();
/*     */     }
/*  97 */     if (isDelete()) {
/*  98 */       return delete();
/*     */     }
/* 100 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 108 */     return "competitor";
/*     */   }
/*     */ 
/*     */   public String disable()
/*     */   {
/*     */     try
/*     */     {
/* 116 */       this.competitorManager.disabledAllCompetitor(this.competitorList);
/*     */     }
/*     */     catch (Exception e) {
/* 119 */       e.printStackTrace();
/*     */     }
/* 121 */     addActionMessage(getText("disabled.success"));
/* 122 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 130 */       this.competitorManager.deleteAllCompetitor(this.competitorList);
/* 131 */       addActionMessage(getText("delete.success"));
/*     */     }
/*     */     catch (Exception e) {
/* 134 */       e.printStackTrace();
/* 135 */       addActionMessage(getText("delete.error"));
/*     */     }
/* 137 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enable()
/*     */   {
/* 144 */     this.competitorManager.enabledAllCompetitor(this.competitorList);
/* 145 */     addActionMessage(getText("enabled.success"));
/* 146 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllIndustry()
/*     */   {
/*     */     try
/*     */     {
/* 154 */       List industrys = new ArrayList();
/* 155 */       List one = this.codeValueManager.loadByKey("code", "002");
/* 156 */       if ((null != one) && (one.size() > 0) && 
/* 157 */         (null != one) && (one.size() > 0)) {
/* 158 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 159 */         industrys.addAll(list);
/*     */       }
/*     */ 
/* 162 */       CodeValue cv = new CodeValue();
/* 163 */       cv.setId(Long.valueOf(-1L));
/* 164 */       cv.setName(getText("select.option.all"));
/* 165 */       industrys.add(0, cv);
/* 166 */       return industrys;
/*     */     } catch (Exception e) {
/* 168 */       e.printStackTrace();
/* 169 */       List industrys = new ArrayList();
/* 170 */       CodeValue cv = new CodeValue();
/* 171 */       cv.setId(Long.valueOf(-1L));
/* 172 */       cv.setName(getText("select.option.all"));
/* 173 */       industrys.add(0, cv);
/* 174 */       return industrys;
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllNature()
/*     */   {
/*     */     try
/*     */     {
/* 184 */       List companyNatures = new ArrayList();
/* 185 */       List one = this.codeValueManager.loadByKey("code", "003");
/* 186 */       if ((null != one) && (one.size() > 0)) {
/* 187 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 188 */         if ((null != list) && (list.size() > 0)) {
/* 189 */           companyNatures.addAll(list);
/*     */         }
/*     */       }
/* 192 */       CodeValue cv = new CodeValue();
/* 193 */       cv.setId(Long.valueOf(-1L));
/* 194 */       cv.setName(getText("select.option.all"));
/* 195 */       companyNatures.add(0, cv);
/* 196 */       return companyNatures;
/*     */     } catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       List companyNatures = new ArrayList();
/* 200 */       CodeValue cv = new CodeValue();
/* 201 */       cv.setId(Long.valueOf(-1L));
/* 202 */       cv.setName(getText("select.option.all"));
/* 203 */       companyNatures.add(0, cv);
/* 204 */       return companyNatures;
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.competitor.ListCompetitorAction
 * JD-Core Version:    0.6.2
 */