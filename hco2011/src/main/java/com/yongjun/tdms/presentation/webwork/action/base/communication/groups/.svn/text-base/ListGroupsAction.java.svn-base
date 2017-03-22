/*     */ package com.yongjun.tdms.presentation.webwork.action.base.communication.groups;
/*     */ 
/*     */ import com.yongjun.pluto.log.service.BusinessLogger;
/*     */ import com.yongjun.pluto.model.DomainModel;
/*     */ import com.yongjun.pluto.model.security.Group;
/*     */ import com.yongjun.pluto.model.security.GroupType;
/*     */ import com.yongjun.pluto.service.security.GroupManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ListGroupsAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -409495626512211689L;
/*     */   protected final GroupManager groupManager;
/*     */   private List<Group> groups;
/*     */   private List<Group> groupList;
/*     */ 
/*     */   public ListGroupsAction(GroupManager groupManager)
/*     */   {
/*  47 */     this.groupManager = groupManager;
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception
/*     */   {
/*  52 */     if (isDelete()) {
/*  53 */       return delete();
/*     */     }
/*  55 */     return "success";
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  60 */     if ((this.groups == null) && (hasIds("groupIds")))
/*  61 */       this.groups = this.groupManager.loadAllGroups(getIds("groupIds"));
/*     */   }
/*     */ 
/*     */   private String delete() {
/*  65 */     for (Group g : this.groups) {
/*  66 */       if ((null != g.getChildGroups()) && (!g.getChildGroups().isEmpty())) {
/*  67 */         addActionMessage(getText("groups.delete.error"));
/*  68 */         return "success";
/*     */       }
/*     */     }
/*  71 */     this.groupManager.deleteAllGroups(this.groups);
/*  72 */     getLogger().logStore((DomainModel)this.groups.get(0), "(名称:" + logContentGroups(this.groups) + ")被删除", "communications_group");
/*     */ 
/*  74 */     addActionMessage(getText("groups.delete.success"));
/*  75 */     return "success";
/*     */   }
/*     */ 
/*     */   private String logContentGroups(List<Group> groups2)
/*     */   {
/*  83 */     Integer index = null;
/*  84 */     String strGroup = "";
/*  85 */     for (Group g : groups2) {
/*  86 */       strGroup = strGroup + g.getName() + ",";
/*     */     }
/*  88 */     index = Integer.valueOf(strGroup.lastIndexOf(","));
/*  89 */     strGroup = strGroup.substring(0, index.intValue());
/*  90 */     return strGroup;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName() {
/*  94 */     return "communicationGroups";
/*     */   }
/*     */ 
/*     */   public List<Group> getParentGroups()
/*     */   {
/*  99 */     List groups = this.groupManager.createSelectGroupsByGroupType(getText("select.option.non"), GroupType.COMMUNICATION_GROUP);
/* 100 */     Group group = new Group();
/* 101 */     group.setId(Long.valueOf(-1L));
/* 102 */     group.setName(getText("select.option.all"));
/* 103 */     groups.add(0, group);
/* 104 */     return groups;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.communication.groups.ListGroupsAction
 * JD-Core Version:    0.6.2
 */