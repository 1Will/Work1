package com.yongjun.tdms.dao.workflow.workflowcase;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workflow.WorkflowCase;
import java.util.Collection;
import java.util.List;

public abstract interface WorkflowCaseDao
{
  public abstract void storeWorkflowCase(WorkflowCase paramWorkflowCase);

  public abstract void deleteWorkflowCase(WorkflowCase paramWorkflowCase);

  public abstract void deleteAllWorkflowCases(Collection<WorkflowCase> paramCollection);

  public abstract List<WorkflowCase> loadAllWorkflowCases(Long[] paramArrayOfLong);

  public abstract WorkflowCase loadWorkflowCase(Long paramLong);

  public abstract List<WorkflowCase> loadAllWorkflowCases();

  public abstract List<WorkflowCase> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<WorkflowCase> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.workflowcase.WorkflowCaseDao
 * JD-Core Version:    0.6.2
 */