/*    */ package com.yongjun.tdms.service.notice.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*    */ import com.yongjun.pluto.service.file.FileTransportManager;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.notice.ReceviceNoticeDao;
/*    */ import com.yongjun.tdms.model.notice.ReadStatus;
/*    */ import com.yongjun.tdms.model.notice.ReceviceNotice;
/*    */ import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultReceviceNoticeManager extends BaseManager
/*    */   implements ReceviceNoticeManager
/*    */ {
/*    */   private final ReceviceNoticeDao receviceNoticeDao;
/*    */   private final FileTransportManager fileTransportManager;
/*    */ 
/*    */   public DefaultReceviceNoticeManager(ReceviceNoticeDao receviceNoticeDao, FileTransportManager fileTransportManager)
/*    */   {
/* 21 */     this.receviceNoticeDao = receviceNoticeDao;
/* 22 */     this.fileTransportManager = fileTransportManager;
/*    */   }
/*    */ 
/*    */   public List<ReceviceNotice> loadAllReceviceNotices() {
/* 26 */     return this.receviceNoticeDao.loadAllReceviceNotices();
/*    */   }
/*    */ 
/*    */   public ReceviceNotice loadReceviceNotice(Long receviceNoticeId)
/*    */   {
/* 31 */     return this.receviceNoticeDao.loadReceviceNotice(receviceNoticeId);
/*    */   }
/*    */ 
/*    */   public List<ReceviceNotice> loadReceviceNotices(Long[] receviceNoticeIds)
/*    */   {
/* 36 */     return this.receviceNoticeDao.loadReceviceNotices(receviceNoticeIds);
/*    */   }
/*    */ 
/*    */   public void storeReceviceNotice(ReceviceNotice notice) {
/* 40 */     this.receviceNoticeDao.storeReceviceNotice(notice);
/*    */   }
/*    */ 
/*    */   public void deleteReceviceNotice(ReceviceNotice notice)
/*    */   {
/* 45 */     this.receviceNoticeDao.deleteReceviceNotice(notice);
/*    */   }
/*    */ 
/*    */   public void deleteReceviceNotices(Collection<ReceviceNotice> notices)
/*    */   {
/* 50 */     this.receviceNoticeDao.deleteReceviceNotices(notices);
/*    */   }
/*    */ 
/*    */   public void disabledAllReceviceNotices(Collection<ReceviceNotice> receviceNotices)
/*    */   {
/* 55 */     for (ReceviceNotice oil : receviceNotices) {
/* 56 */       oil.setDisabled(true);
/* 57 */       this.receviceNoticeDao.storeReceviceNotice(oil);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enabledAllReceviceNotices(Collection<ReceviceNotice> receviceNotices)
/*    */   {
/* 63 */     for (ReceviceNotice oil : receviceNotices) {
/* 64 */       oil.setDisabled(false);
/* 65 */       this.receviceNoticeDao.storeReceviceNotice(oil);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Integer getNumberOfUnReadNoticByUserID(Long userId, CodeValue c) {
/* 70 */     return this.receviceNoticeDao.getNumberOfUnReadNoticByUserID(userId, c);
/*    */   }
/*    */   public List<ReceviceNotice> getAllUnReadNoticByUserID(Long userId, Date validityDate) {
/* 73 */     return this.receviceNoticeDao.getAllUnReadNoticByUserID(userId, validityDate);
/*    */   }
/*    */   public void unreadAllReceviceNotices(Collection<ReceviceNotice> receviceNotices) {
/* 76 */     for (ReceviceNotice oil : receviceNotices) {
/* 77 */       oil.setReadStatus(ReadStatus.UNREAD);
/*    */ 
/* 79 */       this.receviceNoticeDao.storeReceviceNotice(oil);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List LoadAllNoticeByNoticeTilteAndContent(String title, String content)
/*    */   {
/* 86 */     return null;
/*    */   }
/*    */   public List<ReceviceNotice> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 89 */     return this.receviceNoticeDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */   public List<ReceviceNotice> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 92 */     return this.receviceNoticeDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */   public Integer getAllNumberOfUnReadNoticByUserID(Long userId) {
/* 95 */     return this.receviceNoticeDao.getAllNumberOfUnReadNoticByUserID(userId);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.notice.pojo.DefaultReceviceNoticeManager
 * JD-Core Version:    0.6.2
 */