package com.yongjun.tdms.dao.notice;

import com.yongjun.tdms.model.notice.Notice;
import java.util.List;

public abstract interface NoticeDao
{
  public abstract Notice loadNotice(Long paramLong);

  public abstract List<Notice> loadNotices(Long[] paramArrayOfLong);

  public abstract void storeNotice(Notice paramNotice);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.notice.NoticeDao
 * JD-Core Version:    0.6.2
 */