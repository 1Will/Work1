package com.yongjun.tdms.dao.notice;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.notice.ReceviceNotice;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public abstract interface ReceviceNoticeDao
{
  public abstract List<ReceviceNotice> loadAllReceviceNotices();

  public abstract ReceviceNotice loadReceviceNotice(Long paramLong);

  public abstract List<ReceviceNotice> loadReceviceNotices(Long[] paramArrayOfLong);

  public abstract void storeReceviceNotice(ReceviceNotice paramReceviceNotice);

  public abstract void deleteReceviceNotice(ReceviceNotice paramReceviceNotice);

  public abstract void deleteReceviceNotices(Collection<ReceviceNotice> paramCollection);

  public abstract Integer getNumberOfUnReadNoticByUserID(Long paramLong, CodeValue paramCodeValue);

  public abstract List<ReceviceNotice> getAllUnReadNoticByUserID(Long paramLong, Date paramDate);

  public abstract List<ReceviceNotice> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ReceviceNotice> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract Integer getAllNumberOfUnReadNoticByUserID(Long paramLong);
  public List<ReceviceNotice> getAllNoticByUserID(Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.notice.ReceviceNoticeDao
 * JD-Core Version:    0.6.2
 */