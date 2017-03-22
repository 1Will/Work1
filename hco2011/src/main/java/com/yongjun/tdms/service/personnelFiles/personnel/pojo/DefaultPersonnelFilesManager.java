/*     */ package com.yongjun.tdms.service.personnelFiles.personnel.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.dao.security.UserDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.personnel.PersonnelFilesDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultPersonnelFilesManager extends BaseManager
/*     */   implements PersonnelFilesManager
/*     */ {
/*     */   private final PersonnelFilesDao personnelFilesDao;
/*     */   private final UserDao userDao;
/*     */ 
/*     */   public DefaultPersonnelFilesManager(PersonnelFilesDao personnelFilesDao, UserDao userDao)
/*     */   {
/*  55 */     this.personnelFilesDao = personnelFilesDao;
/*  56 */     this.userDao = userDao;
/*     */   }
/*     */ 
/*     */   public void storePersonnel(PersonnelFiles pf)
/*     */   {
/*  65 */     this.personnelFilesDao.storePersonnel(pf);
/*     */   }
/*     */ 
/*     */   public void deletePersonnel(PersonnelFiles pf)
/*     */   {
/*  74 */     this.personnelFilesDao.deletePersonnel(pf);
/*     */   }
/*     */ 
/*     */   public void deleteAllPersonnel(Collection<PersonnelFiles> pfs)
/*     */   {
/*  83 */     this.personnelFilesDao.deleteAllPersonnel(pfs);
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadAllPersonnel(Long[] pfIds)
/*     */   {
/*  93 */     return this.personnelFilesDao.loadAllPersonnel(pfIds);
/*     */   }
/*     */ 
/*     */   public PersonnelFiles loadPersonnel(Long pfId)
/*     */   {
/* 103 */     return this.personnelFilesDao.loadPersonnel(pfId);
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadAllPersonnel()
/*     */   {
/* 112 */     return this.personnelFilesDao.loadAllPersonnel();
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 123 */     return this.personnelFilesDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<PersonnelFiles> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 134 */     return this.personnelFilesDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledPersonnels(Collection<PersonnelFiles> pfs)
/*     */   {
/* 142 */     for (PersonnelFiles p : pfs) {
/* 143 */       p.setDisabled(true);
/* 144 */       this.personnelFilesDao.storePersonnel(p);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledPersonnels(Collection<PersonnelFiles> pfs)
/*     */   {
/* 154 */     for (PersonnelFiles p : pfs) {
/* 155 */       p.setDisabled(false);
/* 156 */       this.personnelFilesDao.storePersonnel(p);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code, Long orgId)
/*     */   {
/* 166 */     return this.personnelFilesDao.getMaxPFCode(code, orgId);
/*     */   }
/*     */ 
/*     */   public boolean loginNameByCode(String code, String loginName)
/*     */   {
/* 174 */     List userList = new ArrayList();
/* 175 */     User user = new User();
/*     */ 
/* 177 */     if ((null != code) && (!code.isEmpty()))
/*     */       try {
/* 179 */         userList = this.userDao.loadByKey("code", code.trim());
/*     */       } catch (DaoException e) {
/* 181 */         this.logger.info("code查询对应的用户出错！");
/*     */       }
/*     */     else {
/* 184 */       return true;
/*     */     }
/* 186 */     if ((null != userList) && (!userList.isEmpty()))
/* 187 */       user = (User)userList.get(0);
/*     */     else {
/* 189 */       return true;
/*     */     }
/*     */ 
/* 192 */     String codeByLoginName = userCodeByLoginName(loginName);
/* 193 */     if ((null == codeByLoginName) || (codeByLoginName.isEmpty()))
/* 194 */       return true;
/* 195 */     if (!codeByLoginName.equals(code)) {
/* 196 */       return false;
/*     */     }
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */   public String userCodeByLoginName(String loginName)
/*     */   {
/* 203 */     List userList = null;
/*     */     try {
/* 205 */       userList = this.userDao.loadByKey("loginName", loginName.trim());
/*     */     } catch (Exception e) {
/* 207 */       this.logger.error("execute userCodeByLoginName methed exception;reason maybe is loginName null or database errorloginName:" + loginName);
/*     */     }
/*     */ 
/* 211 */     if ((null == userList) || (userList.isEmpty())) {
/* 212 */       this.logger.info("get userList sucess and userList is null");
/* 213 */       return null;
/*     */     }
/* 215 */     User user = (User)userList.get(0);
/* 216 */     String code = user.getCode();
/* 217 */     this.logger.info("get userList sucess and return code");
/* 218 */     return code;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.personnel.pojo.DefaultPersonnelFilesManager
 * JD-Core Version:    0.6.2
 */