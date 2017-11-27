package com.yongjun.tdms.service.base.org;

import com.yongjun.tdms.model.base.org.WorkGroup;
import java.util.List;

public abstract interface WorkGroupManager
{
  public abstract List<WorkGroup> loadAllWorkGroups();

  public abstract List<WorkGroup> createSelectWorkGroups(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.org.WorkGroupManager
 * JD-Core Version:    0.6.2
 */