/*     */ package com.yongjun.tdms.presentation.webwork.action.base.communication.groups;
/*     */ 
/*     */ import com.yongjun.pluto.dao.security.UserDao;
/*     */ import com.yongjun.pluto.log.service.BusinessLogger;
/*     */ import com.yongjun.pluto.model.security.Group;
/*     */ import com.yongjun.pluto.model.security.GroupType;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.security.GroupManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ public class EditGroupAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -6215043842938356415L;
/*     */   protected final GroupManager groupManager;
/*     */   protected final UserManager userManager;
/*     */   private final UserDao userDao;
/*     */   private User user;
/*     */   private List<User> users;
/*     */   private Group group;
/*  51 */   private List<User> disUsers = new ArrayList();
/*     */ 
/*     */   public EditGroupAction(GroupManager groupManager, UserManager userManager, UserDao userDao) {
/*  54 */     this.groupManager = groupManager;
/*  55 */     this.userManager = userManager;
/*  56 */     this.userDao = userDao;
/*     */   }
/*     */ 
/*     */   public String save() throws IOException
/*     */   {
/*  61 */     if (isDelete()) {
/*  62 */       return delete();
/*     */     }
/*     */ 
/*  65 */     if (isJoin()) {
/*  66 */       if (hasIds("availableUserIds")) {
/*  67 */         String availableUserIds = this.request.getParameter("availableUserIds");
/*  68 */         String[] availableUserId = availableUserIds.split(",");
/*  69 */         this.groupManager.joinUsersForGroup(availableUserId, this.group);
/*     */       }
/*     */ 
/*  72 */       getLogger().logStore(this.group, "(名称:[" + this.group.getName() + "]有用户[" + logContentUserFromGroup() + "]加入)", "communications_group");
/*  73 */       return "new";
/*     */     }
/*     */ 
/*  76 */     if (isLeave()) {
/*  77 */       if (hasIds("grantedUserIds")) {
/*  78 */         String grantedUserIds = this.request.getParameter("grantedUserIds");
/*  79 */         String[] grantedUserId = grantedUserIds.split(",");
/*  80 */         Long[] result = new Long[grantedUserId.length];
/*  81 */         for (int i = 0; i < grantedUserId.length; i++) {
/*  82 */           result[i] = Long.valueOf(grantedUserId[i]);
/*     */         }
/*  84 */         this.users = this.userManager.loadAllUsers(result);
/*     */       }
/*  86 */       return leave();
/*     */     }
/*     */ 
/*  89 */     boolean isNew = this.group.isNew();
/*  90 */     boolean isChangParentGroup = false;
/*     */ 
/*  92 */     if ((hasId("parent.group")) && (!this.request.getParameter("parent.group").equals("0"))) {
/*  93 */       Group newParentGroup = this.groupManager.loadGroup(getId("parent.group"));
/*  94 */       if (!newParentGroup.equals(this.group.getParentGroup())) {
/*  95 */         isChangParentGroup = true;
/*     */       }
/*  97 */       this.group.setParentGroup(newParentGroup);
/*     */     }
/*  99 */     this.group.setGroupType(GroupType.COMMUNICATION_GROUP);
/* 100 */     this.groupManager.storeGroup(this.group, isChangParentGroup);
/*     */ 
/* 102 */     String logContent = "";
/* 103 */     if (isNew)
/* 104 */       logContent = "被添加";
/*     */     else {
/* 106 */       logContent = "被修改";
/*     */     }
/* 108 */     getLogger().logStore(this.group, "(编码:" + this.group.getCode() + ";名称:" + this.group.getName() + ")" + logContent, "communications_group");
/*     */ 
/* 110 */     if (isNew) {
/* 111 */       addActionMessage(getText("group.add.success", Arrays.asList(new Object[] { this.group.getName() })));
/*     */ 
/* 113 */       return "new";
/*     */     }
/* 115 */     addActionMessage(getText("group.edit.success", Arrays.asList(new Object[] { this.group.getName() })));
/*     */ 
/* 117 */     return "success";
/*     */   }
/*     */ 
/*     */   private String logContentUserFromGroup()
/*     */   {
/* 126 */     String strUser = "";
/* 127 */     Integer index = null;
/* 128 */     Set<User> users = this.group.getUsers();
/* 129 */     for (User user : users) {
/* 130 */       strUser = strUser + user.getName() + ",";
/*     */     }
/* 132 */     index = Integer.valueOf(strUser.lastIndexOf(","));
/* 133 */     strUser = strUser.substring(0, index.intValue());
/* 134 */     return strUser;
/*     */   }
/*     */ 
/*     */   private boolean isGrantRole() {
/* 138 */     return hasKey("grant_role");
/*     */   }
/*     */ 
/*     */   private boolean isRevokeRole() {
/* 142 */     return hasKey("revoke_role");
/*     */   }
/*     */ 
/*     */   private boolean isJoin()
/*     */   {
/* 147 */     if (this.request.getParameter("join").equals("join")) {
/* 148 */       return true;
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean isLeave() {
/* 154 */     return hasKey("leave");
/*     */   }
/*     */   private String delete() {
/* 157 */     this.groupManager.deleteGroup(this.group);
/* 158 */     getLogger().logStore(this.group, "(编码:" + this.group.getCode() + ";名称:" + this.group.getName() + ")被删除", "communications_group");
/* 159 */     addActionMessage(getText("group.delete.success", Arrays.asList(new Object[] { this.group.getName() })));
/*     */ 
/* 162 */     return "deleteSucess";
/*     */   }
/*     */ 
/*     */   public String join()
/*     */   {
/* 167 */     this.group.addUser(this.user);
/* 168 */     this.groupManager.storeGroup(this.group);
/* 169 */     addActionMessage(getText("group.join.success", Arrays.asList(new Object[] { this.group.getName() })));
/*     */ 
/* 171 */     return "new";
/*     */   }
/*     */ 
/*     */   public String leave() {
/* 175 */     String strUser = "";
/* 176 */     for (User user : this.users) {
/* 177 */       this.group.removeUser(user);
/*     */ 
/* 180 */       strUser = strUser + user.getName() + ",";
/*     */     }
/* 182 */     Integer index = null;
/* 183 */     index = Integer.valueOf(strUser.lastIndexOf(","));
/* 184 */     strUser = strUser.substring(0, index.intValue());
/* 185 */     getLogger().logStore(this.group, "(名称:[" + this.group.getName() + "]有用户[" + strUser + "]离开)", "communications_group");
/*     */ 
/* 187 */     this.groupManager.storeGroup(this.group);
/* 188 */     addActionMessage(getText("group.leave.success", Arrays.asList(new Object[] { this.group.getName() })));
/*     */ 
/* 190 */     return "new";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 202 */     if (this.group == null) {
/* 203 */       if (hasId("group.id")) {
/* 204 */         this.group = this.groupManager.loadGroup(getId("group.id"));
/*     */ 
/* 207 */         List list = this.groupManager.displaySortUser(this.group.getId());
/* 208 */         for (int i = 0; i < list.size(); i++) {
/* 209 */           this.user = this.userManager.loadUser((Long)list.get(i));
/* 210 */           this.disUsers.add(this.user);
/*     */         }
/*     */       } else {
/* 213 */         this.group = new Group();
/*     */       }
/*     */     }
/*     */ 
/* 217 */     if ((this.user == null) && 
/* 218 */       (hasId("user.id"))) {
/* 219 */       this.user = this.userManager.loadUser(getId("user.id"));
/*     */     }
/*     */ 
/* 223 */     if ((this.users == null) && 
/* 224 */       (hasIds("userIds")))
/* 225 */       this.users = this.userManager.loadAllUsers(getIds("userIds"));
/*     */   }
/*     */ 
/*     */   public Group getGroup()
/*     */   {
/* 231 */     return this.group;
/*     */   }
/*     */ 
/*     */   public void setGroup(Group group) {
/* 235 */     this.group = group;
/*     */   }
/*     */ 
/*     */   public List<User> getJoinableUsers() {
/* 239 */     List users = this.userManager.loadAllUsers();
/* 240 */     users.removeAll(this.group.getUsers());
/* 241 */     return users;
/*     */   }
/*     */ 
/*     */   public List<Group> getParentGroups()
/*     */   {
/* 246 */     List groups = this.groupManager.createSelectGroupsByGroupType(getText("select.option.non"), GroupType.COMMUNICATION_GROUP);
/* 247 */     if ((null != this.group) && (!this.group.isNew())) {
/* 248 */       groups.remove(this.group);
/*     */     }
/* 250 */     return groups;
/*     */   }
/*     */ 
/*     */   @Transactional
/*     */   public void move()
/*     */   {
/* 256 */     Long upLayerUserId = Long.valueOf(this.request.getParameter("id"));
/*     */ 
/* 258 */     Long presentUserId = Long.valueOf(this.request.getParameter("id1"));
/* 259 */     Long groupid = Long.valueOf(this.request.getParameter("groupid"));
/* 260 */     this.groupManager.exchangeSortIdx(upLayerUserId, presentUserId, groupid);
/*     */   }
/*     */ 
/*     */   public void downMove()
/*     */   {
/* 265 */     Long presentUserId = Long.valueOf(this.request.getParameter("id"));
/*     */ 
/* 267 */     Long downUserId = Long.valueOf(this.request.getParameter("id1"));
/* 268 */     Long groupid = Long.valueOf(this.request.getParameter("groupid"));
/* 269 */     this.groupManager.downExchangeSortIdx(presentUserId, downUserId, groupid);
/*     */   }
/*     */   public List<User> getDisUsers() {
/* 272 */     return this.disUsers;
/*     */   }
/*     */ 
/*     */   public void setDisUsers(List<User> disUsers) {
/* 276 */     this.disUsers = disUsers;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.communication.groups.EditGroupAction
 * JD-Core Version:    0.6.2
 */