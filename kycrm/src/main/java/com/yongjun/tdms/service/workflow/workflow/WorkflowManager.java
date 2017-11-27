package com.yongjun.tdms.service.workflow.workflow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workflow.Workflow;
import java.util.Collection;
import java.util.List;

public abstract interface WorkflowManager
{
  public abstract void storeWorkflow(Workflow paramWorkflow);

  public abstract void deleteWorkflow(Workflow paramWorkflow);

  public abstract void deleteAllWorkflows(Collection<Workflow> paramCollection);

  public abstract List<Workflow> loadAllWorkflows(Long[] paramArrayOfLong);

  public abstract Workflow loadWorkflow(Long paramLong);

  public abstract List<Workflow> loadAllWorkflows();

  public abstract List<Workflow> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Workflow> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String disabled(List<Workflow> paramList);

  public abstract String enabled(List<Workflow> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.workflow.WorkflowManager
 * JD-Core Version:    0.6.2
 */