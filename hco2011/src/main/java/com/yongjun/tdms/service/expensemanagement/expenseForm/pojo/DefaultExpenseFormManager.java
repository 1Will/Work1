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

public class DefaultExpenseFormManager extends BaseManager implements ExpenseFormManager {
	private final ExpenseFormDao dao;
	private final CodeValueDao codeValueDao;
	private final ActivitFlowManager activitFlowManager;

	public DefaultExpenseFormManager(ExpenseFormDao dao,CodeValueDao codeValueDao,ActivitFlowManager activitFlowManager) {
		this.dao = dao;
		this.codeValueDao=codeValueDao;
		this.activitFlowManager=activitFlowManager;
	}

	public void storeExpenseForm(ExpenseForm t) throws Exception {
		
		/**
		 * 第一步保存报销单
		 */
			try {
				 if(t.getIsSaved()!=null&&t.getIsSaved().equals("1")){
			    	   
			    	   List<CodeValue> bussnessCodeList=this.codeValueDao.loadByKey("code", "02003");
				
				 if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
					 t.setStatus(bussnessCodeList.get(0));
				 }
				 }
				 this.dao.storeExpenseForm(t);
				 /**
			        * 第二步 开启流程  会根据保存还是提交状态做不同的处理 
			        * 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批 
			        */
			       StartActiviti sActiviti = new StartActiviti();
			       sActiviti.setApplyPerson(t.getApplyPeople());
			       sActiviti.setBussnessId(t.getId());
			       sActiviti.setIsSaved(t.getIsSaved());
			       sActiviti.setContent(t.toString());
			       sActiviti.setCreatedTime(t.getApplyDate());
			       sActiviti.setFlow(t.getFlow());
			       sActiviti.setName("报销单审批");
			       sActiviti.setLinkHref("/expenseForm/editExpenseFormAction.html?activitiFLow=activitiFLow&expenseForm.id="+t.getId());
			       //根据反射获取类名 
			       sActiviti.setBussnessType(t.getClass().getSimpleName());
			       this.activitFlowManager.startAtiviti(sActiviti);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//预节点待审核状态
			
	    	  
		
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
