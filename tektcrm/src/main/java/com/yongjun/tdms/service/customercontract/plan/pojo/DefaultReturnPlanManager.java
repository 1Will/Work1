package com.yongjun.tdms.service.customercontract.plan.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.codevalue.CodeValueDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.ContractManagementDao;
import com.yongjun.tdms.dao.customercontract.plan.ReturnPlanDao;
import com.yongjun.tdms.dao.financialmanagement.FinancialManagementDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;

public class DefaultReturnPlanManager extends BaseManager implements ReturnPlanManager {
	private final ReturnPlanDao returnPlanDao;
	private final CodeValueDao codeValueDao;
	private final FinancialManagementDao financialManagementDao;
	private final ContractManagementDao contractManagementDao;

	public DefaultReturnPlanManager(ReturnPlanDao returnPlanDao, CodeValueDao codeValueDao,
			FinancialManagementDao financialManagementDao, ContractManagementDao contractManagementDao) {
		this.returnPlanDao = returnPlanDao;
		this.codeValueDao = codeValueDao;
		this.financialManagementDao = financialManagementDao;
		this.contractManagementDao = contractManagementDao;
	}

	public void storeReturnPlan(ReturnPlan returnPlan) {
		this.returnPlanDao.storeReturnPlan(returnPlan);
	}

	public void deleteReturnPlan(ReturnPlan returnPlan) {
		this.returnPlanDao.deleteReturnPlan(returnPlan);
	}

	public void deleteAllReturnPlans(Collection<ReturnPlan> returnPlans) {
		this.returnPlanDao.deleteAllReturnPlans(returnPlans);
	}

	public List<ReturnPlan> loadAllReturnPlans(Long[] returnPlanIds) {
		return this.returnPlanDao.loadAllReturnPlans(returnPlanIds);
	}

	public ReturnPlan loadReturnPlan(Long returnPlanId) {
		return this.returnPlanDao.loadReturnPlan(returnPlanId);
	}

	public List<ReturnPlan> loadAllReturnPlans() {
		return this.returnPlanDao.loadAllReturnPlans();
	}

	public List<ReturnPlan> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.returnPlanDao.loadByKey(keyName, keyValue);
	}

	public List<ReturnPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.returnPlanDao.loadByKeyArray(keyNames, keyValues);
	}

	public void disabledAllReturnPlan(List<ReturnPlan> returnPlans) {
		for (ReturnPlan r : returnPlans) {
			r.setDisabled(true);
			this.returnPlanDao.storeReturnPlan(r);
		}
	}

	public void enabledAllReturnPlan(List<ReturnPlan> returnPlans) {
		for (ReturnPlan r : returnPlans) {
			r.setDisabled(false);
			this.returnPlanDao.storeReturnPlan(r);
		}
	}

	public List<Object> contractManagementAndBatch(String contractManagementId, String batchsId, boolean isNew) {
		Long id = Long.valueOf(batchsId);
		List data = new ArrayList();
		CodeValue c = this.codeValueDao.loadCodeValue(id);
		List<ReturnPlan> list = this.returnPlanDao.contractManagementAndBatch(Long.valueOf(contractManagementId),
				Long.valueOf(batchsId), c.getCode(), isNew);

		for (ReturnPlan r : list) {
			try {
				List fmts = this.financialManagementDao.loadByKey("contractManagement",
						Long.valueOf(contractManagementId));

				if (null == fmts) {
					Object[] array = new Object[3];
					array[0] = r.getSum();
					array[1] = r.getSum();
					array[2] = Double.valueOf(r.getContractManagement().getContractMoney() - r.getSum().doubleValue());

					data.add(array);
				} else {
					Object[] array = new Object[3];
					array[0] = r.getSum();
					array[1] = Double.valueOf(((FinancialManagement) fmts.get(0)).getTotalSum().doubleValue()
							+ r.getSum().doubleValue());
					array[2] = Double.valueOf(r.getContractManagement().getContractMoney()
							- (((FinancialManagement) fmts.get(0)).getTotalSum().doubleValue() + r.getSum()
									.doubleValue()));

					data.add(array);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public List<CodeValue> contractAndBatch(String contractManagementId, boolean isNew) {
		List list = this.returnPlanDao.contractAndBatch(Long.valueOf(contractManagementId), isNew);
		return list;
	}

	public List<CodeValue> batchForBill(Long contractManagementId, boolean isNew) {
		List list = this.returnPlanDao.batchForBill(Long.valueOf(contractManagementId), isNew);
		return list;
	}

	public List<String> checkSum(String contractManagementId, String sum, Long returnPlanId) {
		List data = new ArrayList();
		Long id = Long.valueOf(contractManagementId);
		if (null != id) {
			Double i = Double.valueOf(0.0D);
			Double j = Double.valueOf(0.0D);
			ContractManagement cm = this.contractManagementDao.loadContractManagement(id);
			try {
				List<ReturnPlan> list = this.returnPlanDao.loadByKey("contractManagement", id);

				if (null != list)
					for (ReturnPlan r : list)
						i = Double.valueOf(i.doubleValue() + r.getSum().doubleValue());
			} catch (DaoException e) {
				e.printStackTrace();
			}
			if (null != cm) {
				j = Double.valueOf(cm.getContractMoney());
			}
			ReturnPlan returnPlan = loadReturnPlan(returnPlanId);
			Double a = 0D;
			if (returnPlanId == 0L || returnPlan == null) {
				a = Double.valueOf(j.doubleValue() - i.doubleValue());
			} else {
				a = Double.valueOf(returnPlan.getSum() + j.doubleValue() - i.doubleValue());
			}

			Double sums = Double.valueOf(sum);
			if (sums.doubleValue() > a.doubleValue())
				data.add("unuser");
			else {
				data.add("user");
			}
		}
		return data;
	}

	public List getReturnPlan(Long contractManagementId, Long batchId) {
		String keyNames[] = { "contractManagement.id", "batch.id" };
		Long keyValues[] = { contractManagementId, batchId };
		List<Double> money = new ArrayList<Double>();
		try {
			ReturnPlan returnPlan = loadByKeyArray(keyNames, keyValues).get(0);
			money.add(returnPlan.getSum());
			money.add(returnPlan.getFactSum());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return money;
	}
	
	public List getReturnPlanBill(Long contractManagementId, Long batchId) {
		String keyNames[] = { "contractManagement.id", "batch.id" };
		Long keyValues[] = { contractManagementId, batchId };
		List<Double> money = new ArrayList<Double>();
		try {
			ReturnPlan returnPlan = loadByKeyArray(keyNames, keyValues).get(0);
			money.add(returnPlan.getSum());
			money.add(returnPlan.getBillMoney());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return money;
	}
}
