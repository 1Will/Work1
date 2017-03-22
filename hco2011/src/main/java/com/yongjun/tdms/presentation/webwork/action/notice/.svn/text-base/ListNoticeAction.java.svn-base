/*     */ package com.yongjun.tdms.presentation.webwork.action.notice;
/*     */ 
/*     */ import com.yongjun.pluto.model.LabelValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.notice.SendNotice;
/*     */ import com.yongjun.tdms.model.notice.SendStatus;
/*     */ import com.yongjun.tdms.service.notice.SendNoticeManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListNoticeAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final SendNoticeManager sendNoticeManager;
/*     */   private List<SendNotice> sendNotices;
/*     */ 
/*     */   public ListNoticeAction(SendNoticeManager sendNoticeManager)
/*     */   {
/*  23 */     this.sendNoticeManager = sendNoticeManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  27 */     if ((this.sendNotices == null) && (hasIds("sendNoticeIds")))
/*  28 */       this.sendNotices = this.sendNoticeManager.loadSendNotices(getIds("sendNoticeIds"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */   {
/*  33 */     if (isDisabled()) {
/*  34 */       return disabled();
/*     */     }
/*  36 */     if (isEnabled()) {
/*  37 */       return enabled();
/*     */     }
/*  39 */     if (isDelete()) {
/*  40 */       return delete();
/*     */     }
/*  42 */     return "success";
/*     */   }
/*     */   public String disabled() {
/*  45 */     this.sendNoticeManager.disabledAllSendNotices(this.sendNotices);
/*  46 */     addActionMessage(getText("sendNotice.disabled.success"));
/*  47 */     return "success";
/*     */   }
/*     */ 
/*     */   public String enabled() {
/*  51 */     this.sendNoticeManager.enabledAllSendNotices(this.sendNotices);
/*  52 */     addActionMessage(getText("sendNotice.enabled.success"));
/*  53 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*  60 */     this.sendNoticeManager.deleteSendNotices(this.sendNotices);
/*  61 */     addActionMessage(getText("sendNotice.delete.success"));
/*  62 */     return "success";
/*     */   }
/*     */   private boolean isEnabled() {
/*  65 */     return hasKey("enabled");
/*     */   }
/*     */   public List<LabelValue> getStatus() {
/*  68 */     LabelValue[] arrays = wrapEnum(SendStatus.class);
/*  69 */     LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(), getText("select.option.all"));
/*  70 */     List tmp = new ArrayList();
/*  71 */     tmp.add(labelValue);
/*  72 */     for (int i = 0; i < arrays.length; i++) {
/*  73 */       tmp.add(arrays[i]);
/*     */     }
/*  75 */     return tmp;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  80 */     return "notice";
/*     */   }
/*     */ 
/*     */   public List<SendNotice> getSendNotices()
/*     */   {
/*  85 */     return this.sendNotices;
/*     */   }
/*     */   public void setSendNotices(List<SendNotice> sendNotices) {
/*  88 */     this.sendNotices = sendNotices;
/*     */   }
/*     */ 
/*     */   public User getLoginUser()
/*     */   {
/*  95 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap() {
/*  99 */     System.out.println("************************");
/* 100 */     Map map = super.getRequestParameterMap();
/* 101 */     User user = this.userManager.getUser();
/* 102 */     if (!hasId("onlyInvalid")) {
/* 103 */       map.put("sendUser.id", user.getId());
/* 104 */       map.put("onlyValid", Boolean.valueOf(true));
/*     */     }
/* 106 */     return map;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.notice.ListNoticeAction
 * JD-Core Version:    0.6.2
 */