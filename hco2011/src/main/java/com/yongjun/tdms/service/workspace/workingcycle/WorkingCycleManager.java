package com.yongjun.tdms.service.workspace.workingcycle;

import com.yongjun.tdms.model.workspace.workingcycle.WorkingCycle;
import java.util.List;

public abstract interface WorkingCycleManager
{
  public abstract WorkingCycle loadWorkingCycle(Long paramLong);

  public abstract List<WorkingCycle> loadAllWorkingCycles();

  public abstract void storeWorkingCycle(WorkingCycle paramWorkingCycle);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.workingcycle.WorkingCycleManager
 * JD-Core Version:    0.6.2
 */