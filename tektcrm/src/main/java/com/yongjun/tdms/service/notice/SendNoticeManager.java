package com.yongjun.tdms.service.notice;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.notice.SendNotice;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

public abstract interface SendNoticeManager
{
  public abstract SendNotice loadSendNotice(Long paramLong);

  public abstract List<SendNotice> loadSendNotices(Long[] paramArrayOfLong);

  @Transactional
  public abstract void storeSendNotice(SendNotice paramSendNotice);

  @Transactional
  public abstract void deleteSendNotice(SendNotice paramSendNotice);

  @Transactional
  public abstract void deleteSendNotices(Collection<SendNotice> paramCollection);

  @Transactional
  public abstract void disabledAllSendNotices(Collection<SendNotice> paramCollection);

  @Transactional
  public abstract void enabledAllSendNotices(Collection<SendNotice> paramCollection);

  @Transactional
  public abstract void joinUsersForSend(String[] paramArrayOfString, SendNotice paramSendNotice, Set<User> paramSet);

  public abstract SendNotice getNoticeByNoticeTilteAndContent(String paramString1, String paramString2);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.notice.SendNoticeManager
 * JD-Core Version:    0.6.2
 */