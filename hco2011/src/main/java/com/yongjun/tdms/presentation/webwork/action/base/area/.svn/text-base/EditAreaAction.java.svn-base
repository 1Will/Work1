/*     */ package com.yongjun.tdms.presentation.webwork.action.base.area;
/*     */ 
/*     */ import com.opensymphony.webwork.ServletActionContext;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
/*     */ import java.util.Arrays;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class EditAreaAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 873830192531853814L;
/*     */   private final AreaManager areaManager;
/*     */   private Area area;
/*     */   private String flag;
/*     */   private Area parentArea;
/*     */   HttpServletRequest request;
/*     */ 
/*     */   public EditAreaAction(AreaManager areaManager)
/*     */   {
/*  58 */     this.areaManager = areaManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  66 */     this.request = ServletActionContext.getRequest();
/*  67 */     if (null == this.area) {
/*  68 */       if (hasId("area.id")) {
/*  69 */         this.area = this.areaManager.loadArea(getId("area.id"));
/*  70 */         this.request.getSession().setAttribute("area", this.area);
/*     */       } else {
/*  72 */         this.area = new Area();
/*     */       }
/*     */     }
/*  75 */     if ((this.request.getParameter("flag") != null) && (this.request.getParameter("flag") != "")) {
/*  76 */       this.flag = this.request.getParameter("flag");
/*  77 */       this.area.setType(this.flag);
/*     */     }
/*  79 */     if (hasId("parentId"))
/*  80 */       this.parentArea = this.areaManager.loadArea(getId("parentId"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  90 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  99 */     if (isDelete()) {
/* 100 */       return delete();
/*     */     }
/*     */ 
/* 103 */     boolean isNew = this.area.isNew();
/*     */     try {
/* 105 */       if (isNew)
/*     */       {
/* 107 */         if (null == this.areaManager.loadByKey("code", this.area.getCode())) {
/* 108 */           if (this.area.getType() == "country")
/* 109 */             this.area.setType("country");
/*     */           else {
/* 111 */             this.area.setParentArea(this.parentArea);
/*     */           }
/* 113 */           this.areaManager.storeArea(this.area);
/*     */         } else {
/* 115 */           if (this.flag.equals("country")) {
/* 116 */             addActionMessage(getText("country.add.exist", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */           }
/* 118 */           else if (this.flag.equals("province")) {
/* 119 */             addActionMessage(getText("province.add.exist", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */           }
/* 121 */           else if (this.flag.equals("city")) {
/* 122 */             addActionMessage(getText("city.add.exist", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */           }
/*     */           else {
/* 125 */             addActionMessage(getText("area.add.exist", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */           }
/*     */ 
/* 128 */           return "error";
/*     */         }
/*     */       }
/* 131 */       else this.areaManager.storeArea(this.area); 
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 134 */       e.printStackTrace();
/* 135 */       addActionMessage(getText("area.add.exist", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */ 
/* 137 */       return "error";
/*     */     }
/*     */ 
/* 140 */     if (isNew) {
/* 141 */       if (this.flag.equals("country")) {
/* 142 */         addActionMessage(getText("country.add.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */       }
/* 144 */       else if (this.flag.equals("province")) {
/* 145 */         addActionMessage(getText("province.add.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */       }
/* 147 */       else if (this.flag.equals("city")) {
/* 148 */         addActionMessage(getText("city.add.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */       }
/*     */       else {
/* 151 */         addActionMessage(getText("area.add.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */       }
/*     */ 
/* 154 */       return "success";
/*     */     }
/* 156 */     if (this.flag.equals("country")) {
/* 157 */       addActionMessage(getText("country.edit.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */     }
/* 159 */     else if (this.flag.equals("province")) {
/* 160 */       addActionMessage(getText("province.edit.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */     }
/* 162 */     else if (this.flag.equals("city")) {
/* 163 */       addActionMessage(getText("city.edit.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */     }
/*     */     else {
/* 166 */       addActionMessage(getText("area.edit.success", Arrays.asList(new Object[] { this.area.getCode() })));
/*     */     }
/*     */ 
/* 169 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 179 */       this.areaManager.deleteArea(this.area);
/* 180 */       this.area = null;
/*     */     } catch (Exception e) {
/* 182 */       e.printStackTrace();
/* 183 */       if (this.flag.equals("country"))
/* 184 */         addActionMessage(getText("country.delete.error"));
/* 185 */       else if (this.flag.equals("province"))
/* 186 */         addActionMessage(getText("province.delete.error"));
/* 187 */       else if (this.flag.equals("city"))
/* 188 */         addActionMessage(getText("city.delete.error"));
/*     */       else {
/* 190 */         addActionMessage(getText("area.delete.error"));
/*     */       }
/*     */     }
/* 193 */     if (this.flag.equals("country"))
/* 194 */       addActionMessage(getText("country.delete.success"));
/* 195 */     else if (this.flag.equals("province"))
/* 196 */       addActionMessage(getText("province.delete.success"));
/* 197 */     else if (this.flag.equals("city"))
/* 198 */       addActionMessage(getText("city.delete.success"));
/*     */     else {
/* 200 */       addActionMessage(getText("area.delete.success"));
/*     */     }
/* 202 */     return "deleteTO";
/*     */   }
/*     */ 
/*     */   public Area getArea()
/*     */   {
/* 209 */     return this.area;
/*     */   }
/*     */ 
/*     */   public void setArea(Area area)
/*     */   {
/* 216 */     this.area = area;
/*     */   }
/*     */ 
/*     */   public Area getParentArea()
/*     */   {
/* 223 */     return this.parentArea;
/*     */   }
/*     */ 
/*     */   public void setParentArea(Area parentArea)
/*     */   {
/* 230 */     this.parentArea = parentArea;
/*     */   }
/*     */ 
/*     */   public String getFlag()
/*     */   {
/* 237 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(String flag)
/*     */   {
/* 244 */     this.flag = flag;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.area.EditAreaAction
 * JD-Core Version:    0.6.2
 */