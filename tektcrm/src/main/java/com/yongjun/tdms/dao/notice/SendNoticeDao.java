package com.yongjun.tdms.dao.notice;

import com.yongjun.tdms.model.notice.SendNotice;
import java.util.Collection;
import java.util.List;

public abstract interface SendNoticeDao
{
  public abstract SendNotice loadSendNotice(Long paramLong);

  public abstract List<SendNotice> loadSendNotices(Long[] paramArrayOfLong);

  public abstract void storeSendNotice(SendNotice paramSendNotice);

  public abstract void deleteSendNotice(SendNotice paramSendNotice);

  public abstract void deleteSendNotices(Collection<SendNotice> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.notice.SendNoticeDao
 * JD-Core Version:    0.6.2
 */