package com.yongjun.tdms.util.myremind;

public abstract interface MyRemindManager
{
  public abstract void storInfoByTime();

  public abstract void storBackInfoByTime();

  public abstract void storAccountAuditByTime();

  public abstract void storUnReadByTime();

  public abstract void cleanRemind();

  public abstract void contractExpires();

  public abstract void returnPlanRemind();
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.util.myremind.MyRemindManager
 * JD-Core Version:    0.6.2
 */