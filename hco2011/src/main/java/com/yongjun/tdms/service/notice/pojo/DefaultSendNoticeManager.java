/*     */ package com.yongjun.tdms.service.notice.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.dao.security.GroupDao;
/*     */ import com.yongjun.pluto.dao.security.UserDao;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.Group;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.pluto.service.security.GroupManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.tdms.dao.notice.ReceviceNoticeDao;
/*     */ import com.yongjun.tdms.dao.notice.SendNoticeDao;
/*     */ import com.yongjun.tdms.model.notice.SendNotice;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.notice.SendNoticeManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DefaultSendNoticeManager extends BaseManager
/*     */   implements SendNoticeManager
/*     */ {
/*     */   private final SendNoticeDao sendNoticeDao;
/*     */   private final UserDao userDao;
/*     */   private final ReceviceNoticeDao receviceNoticeDao;
/*     */   private final GroupDao groupDao;
/*     */   private final GroupManager groupManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final UserManager userManager;
/*     */   protected static final String GROUP_SEND = "_group";
/*     */   protected static final String USER_SEND = "_user";
/*     */   protected static final String HAND_SEND = "_hand";
/*     */   protected static final String DEPT_SEND = "_dept";
/*     */ 
/*     */   public DefaultSendNoticeManager(SendNoticeDao sendNoticeDao, UserDao userDao, ReceviceNoticeDao receviceNoticeDao, GroupDao groupDao, GroupManager groupManager, UserManager userManager, DepartmentManager departmentManager)
/*     */   {
/*  39 */     this.sendNoticeDao = sendNoticeDao;
/*  40 */     this.userDao = userDao;
/*  41 */     this.receviceNoticeDao = receviceNoticeDao;
/*  42 */     this.groupDao = groupDao;
/*  43 */     this.groupManager = groupManager;
/*  44 */     this.userManager = userManager;
/*  45 */     this.departmentManager = departmentManager;
/*     */   }
/*     */ 
/*     */   public List<SendNotice> loadSendNotices(Long[] sendNoticeIds) {
/*  49 */     return this.sendNoticeDao.loadSendNotices(sendNoticeIds);
/*     */   }
/*     */ 
/*     */   public void storeSendNotice(SendNotice notice) {
/*  53 */     this.sendNoticeDao.storeSendNotice(notice);
/*     */   }
/*     */ 
/*     */   public void deleteSendNotice(SendNotice notice)
/*     */   {
/*  58 */     this.sendNoticeDao.deleteSendNotice(notice);
/*     */   }
/*     */ 
/*     */   public void deleteSendNotices(Collection<SendNotice> notices)
/*     */   {
/*  63 */     for (SendNotice notice : notices)
/*     */     {
/*  65 */       this.receviceNoticeDao.deleteReceviceNotices(notice.getReceviceNotices());
/*     */     }
/*     */ 
/*  68 */     this.sendNoticeDao.deleteSendNotices(notices);
/*     */   }
/*     */ 
/*     */   public SendNotice loadSendNotice(Long sendNoticeId) {
/*  72 */     return this.sendNoticeDao.loadSendNotice(sendNoticeId);
/*     */   }
/*     */   public void joinUsersForSend(String[] availableUserId, SendNotice notice, Set<User> userSended) {
/*  75 */     collectionSender(availableUserId, userSended);
/*     */ 
/*  77 */     for (User user : userSended)
/*  78 */       notice.getUsers().add(user);
/*     */   }
/*     */ 
/*     */   private void collectionSender(String[] availableUserId, Set<User> userSended)
/*     */   {
/*  84 */     for (int i = 0; i < availableUserId.length; i++)
/*     */     {
/*  86 */       if (availableUserId[i].indexOf("_user") != -1) {
/*  87 */         User user = this.userDao.loadUser(Long.valueOf(availableUserId[i].substring(0, availableUserId[i].indexOf("_user"))));
/*     */ 
/*  90 */         userSended.add(user);
/*     */       }
/*  94 */       else if (availableUserId[i].indexOf("_group") != -1) {
/*  95 */         Group group = this.groupDao.loadGroup(Long.valueOf(availableUserId[i].substring(0, availableUserId[i].indexOf("_group"))));
/*     */ 
/*  99 */         List<Group> allGroups = this.groupManager.getGroupsOfAfterTraversal(group);
/*     */ 
/* 101 */         for (Group g : allGroups) {
/* 102 */           userSended.addAll(g.getUsers());
/*     */         }
/*     */ 
/*     */       }
/* 107 */       else if (availableUserId[i].indexOf("_dept") != -1) {
/* 108 */         Long deptId = Long.valueOf(availableUserId[i].substring(0, availableUserId[i].indexOf("_dept")));
/*     */ 
/* 110 */         Department dept = this.departmentManager.loadDepartment(deptId);
/*     */ 
/* 112 */         List<Department> allDepts = this.departmentManager.getAllChilds(dept);
/*     */ 
/* 115 */         if ((null != allDepts) && (allDepts.size() != 0)) {
/* 116 */           for (Department department : allDepts) {
/* 117 */             List deptUsers = this.userManager.getUsersByDeptId(department.getId());
/* 118 */             userSended.addAll(deptUsers);
/*     */           }
/*     */         }
/*     */         else {
/* 122 */           List deptUsers = this.userManager.getUsersByDeptId(dept.getId());
/* 123 */           userSended.addAll(deptUsers);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void disabledAllSendNotices(Collection<SendNotice> sendNotices) {
/* 130 */     for (SendNotice oil : sendNotices) {
/* 131 */       oil.setDisabled(true);
/* 132 */       this.sendNoticeDao.storeSendNotice(oil);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllSendNotices(Collection<SendNotice> sendNotices) {
/* 137 */     for (SendNotice oil : sendNotices) {
/* 138 */       oil.setDisabled(false);
/* 139 */       this.sendNoticeDao.storeSendNotice(oil);
/*     */     }
/*     */   }
/*     */ 
/*     */   public SendNotice getNoticeByNoticeTilteAndContent(String title, String content) {
/* 144 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.notice.pojo.DefaultSendNoticeManager
 * JD-Core Version:    0.6.2
 */