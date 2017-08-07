package com.yongjun.tdms.service.customercontract.plan;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;

import java.util.Collection;
import java.util.List;

public abstract interface ReturnPlanManager {
	public abstract void storeReturnPlan(ReturnPlan paramReturnPlan);

	public abstract void deleteReturnPlan(ReturnPlan paramReturnPlan);

	public abstract void deleteAllReturnPlans(Collection<ReturnPlan> paramCollection);

	public abstract List<ReturnPlan> loadAllReturnPlans(Long[] paramArrayOfLong);

	public abstract ReturnPlan loadReturnPlan(Long paramLong);

	public abstract List<ReturnPlan> loadAllReturnPlans();

	public abstract List<ReturnPlan> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ReturnPlan> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;

	public abstract void disabledAllReturnPlan(List<ReturnPlan> paramList);

	public abstract void enabledAllReturnPlan(List<ReturnPlan> paramList);

	public abstract List<Object> contractManagementAndBatch(String paramString1, String paramString2,
			boolean paramBoolean);

	public abstract List<CodeValue> contractAndBatch(String paramString, boolean paramBoolean);
	
	public List<CodeValue> batchForBill(Long contractManagementId, boolean isNew);

	public abstract List<String> checkSum(String paramString1, String paramString2, Long returnPlanId);

	public abstract List getReturnPlan(Long contractManagementId, Long batchId);
	
	public List getReturnPlanBill(Long contractManagementId, Long batchId);
}

/*
 * Location: E:\crm2010\110\crm2009\WEB-INF\classes\ Qualified Name:
 * com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager JD-Core
 * Version: 0.6.2
 */