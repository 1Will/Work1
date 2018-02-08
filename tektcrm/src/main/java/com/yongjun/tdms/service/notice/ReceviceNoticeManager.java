package com.yongjun.tdms.service.notice;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.notice.ReceviceNotice;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public abstract interface ReceviceNoticeManager
{
  public abstract List<ReceviceNotice> loadAllReceviceNotices();

  public abstract ReceviceNotice loadReceviceNotice(Long paramLong);

  public abstract List<ReceviceNotice> loadReceviceNotices(Long[] paramArrayOfLong);

  @Transactional
  public abstract void storeReceviceNotice(ReceviceNotice paramReceviceNotice);

  @Transactional
  public abstract void deleteReceviceNotice(ReceviceNotice paramReceviceNotice);

  @Transactional
  public abstract void deleteReceviceNotices(Collection<ReceviceNotice> paramCollection);

  @Transactional
  public abstract void disabledAllReceviceNotices(Collection<ReceviceNotice> paramCollection);

  @Transactional
  public abstract void enabledAllReceviceNotices(Collection<ReceviceNotice> paramCollection);

  @Transactional
  public abstract void unreadAllReceviceNotices(Collection<ReceviceNotice> paramCollection);

  public abstract Integer getNumberOfUnReadNoticByUserID(Long paramLong, CodeValue paramCodeValue);

  public abstract List<ReceviceNotice> getAllUnReadNoticByUserID(Long paramLong, Date paramDate);

  public abstract Integer getAllNumberOfUnReadNoticByUserID(Long paramLong);

  public abstract List LoadAllNoticeByNoticeTilteAndContent(String paramString1, String paramString2);

  public abstract List<ReceviceNotice> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ReceviceNotice> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  public List<ReceviceNotice> getAllNoticByUserID(Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.notice.ReceviceNoticeManager
 * JD-Core Version:    0.6.2
 */