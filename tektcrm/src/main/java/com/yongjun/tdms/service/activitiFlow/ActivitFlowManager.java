package com.yongjun.tdms.service.activitiFlow;


import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.ActiviFlow;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;

public abstract interface ActivitFlowManager
{
  public abstract void storeAtiviti(ActiviFlow activiFlow)throws DaoException ;
  public abstract void startAtiviti(StartActiviti startActiviti)throws Exception ;
  public abstract void storeAtivitiList(ActiviFlow activiFlow)throws DaoException;

  
}

