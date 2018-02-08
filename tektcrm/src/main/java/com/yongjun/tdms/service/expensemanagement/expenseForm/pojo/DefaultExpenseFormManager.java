package com.yongjun.tdms.service.expensemanagement.expenseForm.pojo;

import java.util.List;

import com.yongjun.pluto.dao.codevalue.CodeValueDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.expenseForm.ExpenseFormDao;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
import com.yongjun.tdms.service.expensemanagement.expenseForm.ExpenseFormManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultExpenseFormManager extends BaseManager implements ExpenseFormManager {
	private final ExpenseFormDao dao;
	private final YongJunSequenceManager yongJunSequenceManager;
	private final ActivitFlowManager activitFlowManager;
	private final CodeValueDao codeValueDao;
 
	public DefaultExpenseFormManager(ExpenseFormDao dao, YongJunSequenceManager yongJunSequenceManager,
			ActivitFlowManager activitFlowManager,CodeValueDao codeValueDao) {
		this.dao = dao;
		this.yongJunSequenceManager = yongJunSequenceManager;
		this.activitFlowManager = activitFlowManager;
		this.codeValueDao = codeValueDao;
	}

	public void storeExpenseForm(ExpenseForm ef) throws DaoException {

		/**
		 * 第一步 保存请假单
		 */
		if (ef.isNew()) {
			ef.setCode(
					(String) this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_EXPENSEFORM));
		}
		if (ef.getIsSaved() != null && (ef.getIsSaved().equals("1") || ef.getIsSaved().equals("2"))) {

			List<CodeValue> bussnessCodeList = this.codeValueDao.loadByKey("code", "02003");// 预节点待审核状态
			if (bussnessCodeList != null && bussnessCodeList.size() > 0) {// 将当前业务状态更新我审核中
				ef.setStatus(bussnessCodeList.get(0));
			}

		}
		this.dao.storeExpenseForm(ef);

		/**
		 * 第二步 开启流程 会根据保存还是提交状态做不同的处理 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批
		 */
		StartActiviti sActiviti = new StartActiviti();
		sActiviti.setApplyPerson(ef.getApplyPeople());
		sActiviti.setBussnessId(ef.getId());
		sActiviti.setIsSaved(ef.getIsSaved());
		sActiviti.setContent(ef.toString());
		sActiviti.setCreatedTime(ef.getCreatedTime());
		sActiviti.setFlow(ef.getFlow());
		sActiviti.setName("报销单("+ef.getCode()+")");
		sActiviti.setLinkHref("/expenseForm/editExpenseFormAction.html?activitiFLow=activitiFLow&expenseForm.id=" + ef.getId());
		// 根据反射获取类名
		sActiviti.setBussnessType(ef.getClass().getSimpleName());

		try {
			this.activitFlowManager.startAtiviti(sActiviti);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ExpenseForm loadExpenseForm(Long id) {
		return this.dao.loadExpenseForm(id);
	}

	public List<ExpenseForm> loadExpenseForm() {
		return this.dao.loadExpenseForm();
	}

	public List<ExpenseForm> loadAllExpenseForm(Long[] tIds) {
		return this.dao.loadAllExpenseForm(tIds);
	}

	public void deleteExpenseForm(ExpenseForm t) {
		this.dao.deleteExpenseForm(t);
	}

	public void deleteAllExpenseForm(List<ExpenseForm> ts) {
		this.dao.deleteAllExpenseForm(ts);
	}

	public List<ExpenseForm> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<ExpenseForm> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public void disabledAllExpenseForm(List<ExpenseForm> ts) {
		for (ExpenseForm e : ts) {
			e.setDisabled(true);
			this.dao.storeExpenseForm(e);
		}
	}

	public void enabledAllExpenseForm(List<ExpenseForm> ts) {
		for (ExpenseForm e : ts) {
			e.setDisabled(false);
			this.dao.storeExpenseForm(e);
		}
	}
}
