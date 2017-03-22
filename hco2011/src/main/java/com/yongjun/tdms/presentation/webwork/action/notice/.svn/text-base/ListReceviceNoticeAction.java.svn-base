/*     */ package com.yongjun.tdms.presentation.webwork.action.notice;
/*     */ 
/*     */ import com.yongjun.pluto.model.LabelValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.notice.ReadStatus;
/*     */ import com.yongjun.tdms.model.notice.ReceviceNotice;
/*     */ import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListReceviceNoticeAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -8621959686198720642L;
/*     */   private final ReceviceNoticeManager receviceNoticeManager;
/*     */   private List<ReceviceNotice> receviceNotices;
/*     */ 
/*     */   public ListReceviceNoticeAction(ReceviceNoticeManager receviceNoticeManager)
/*     */   {
/*  45 */     this.receviceNoticeManager = receviceNoticeManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */   {
/*  50 */     if (hasIds("receviceNoticeIds"))
/*  51 */       this.receviceNotices = this.receviceNoticeManager.loadReceviceNotices(getIds("receviceNoticeIds"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  56 */     if (isDisabled()) {
/*  57 */       return disabled();
/*     */     }
/*  59 */     if (isEnabled()) {
/*  60 */       return enabled();
/*     */     }
/*  62 */     if (isDelete()) {
/*  63 */       return delete();
/*     */     }
/*  65 */     if (hasKey("unread")) {
/*  66 */       return unread();
/*     */     }
/*  68 */     return "success";
/*     */   }
/*     */   public String unread() {
/*  71 */     this.receviceNoticeManager.unreadAllReceviceNotices(this.receviceNotices);
/*  72 */     return "success";
/*     */   }
/*     */   public List<LabelValue> getStatus() {
/*  75 */     LabelValue[] arrays = wrapEnum(ReadStatus.class);
/*  76 */     LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(), getText("select.option.all"));
/*  77 */     List tmp = new ArrayList();
/*  78 */     tmp.add(labelValue);
/*  79 */     for (int i = 0; i < arrays.length; i++) {
/*  80 */       tmp.add(arrays[i]);
/*     */     }
/*  82 */     return tmp;
/*     */   }
/*     */   public String disabled() {
/*  85 */     this.receviceNoticeManager.disabledAllReceviceNotices(this.receviceNotices);
/*  86 */     addActionMessage(getText("receviceNotice.disabled.success"));
/*  87 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enabled() {
/*  91 */     this.receviceNoticeManager.enabledAllReceviceNotices(this.receviceNotices);
/*  92 */     addActionMessage(getText("receviceNotice.enabled.success"));
/*  93 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/* 101 */     this.receviceNoticeManager.deleteReceviceNotices(this.receviceNotices);
/* 102 */     addActionMessage(getText("receviceNotice.delete.success"));
/* 103 */     return "success";
/*     */   }
/*     */ 
/*     */   private boolean isEnabled() {
/* 107 */     return hasKey("enabled");
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 112 */     return "receviceNotices";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap() {
/* 116 */     Map map = super.getRequestParameterMap();
/* 117 */     if (!hasId("onlyInvalid")) {
/* 118 */       map.put("onlyValid", Boolean.valueOf(true));
/*     */     }
/* 120 */     if (this.request.getParameter("receviceNotice.id") != null) {
/* 121 */       int rNId = Integer.valueOf(this.request.getParameter("receviceNotice.id")).intValue();
/* 122 */       map.put("receviceNotice.id", Integer.valueOf(rNId));
/*     */     }
/* 124 */     map.put("loginUser.id", getLoginUser().getId());
/* 125 */     return map;
/*     */   }
/*     */ 
/*     */   public User getLoginUser()
/*     */   {
/* 132 */     return this.userManager.getUser();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.notice.ListReceviceNoticeAction
 * JD-Core Version:    0.6.2
 */