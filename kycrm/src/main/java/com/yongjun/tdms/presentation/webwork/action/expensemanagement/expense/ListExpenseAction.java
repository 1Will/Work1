package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expense;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.expensemanagement.expense.Expense;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.expensemanagement.expense.ExpenseManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;

public class ListExpenseAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ExpenseManager expenseManager;
	private final ReturnPlanManager returnPlanManager;
	private final FinancialManagementManager financialManagementManager;
	private final BillingRecordManager  billingRecordManager;
	private List<Expense> expenses = new ArrayList<Expense>();
	
	public ListExpenseAction(ExpenseManager expenseManager,ReturnPlanManager returnPlanManager,FinancialManagementManager financialManagementManager,BillingRecordManager  billingRecordManager) {
		this.expenseManager = expenseManager;
		this.returnPlanManager=returnPlanManager;
		this.financialManagementManager=financialManagementManager;
		this.billingRecordManager =billingRecordManager ;
	}

	public void prepare() throws Exception {
		if (hasIds("expenseIds")) {
			this.expenses = this.expenseManager.loadAllExpense(getIds("expenseIds"));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(!hasId("parentEP.id") && !hasId("customerInfo.id")){
			map.put("parent", "parent");
		}
		return map;
	}
	
	protected String getAdapterName() {
		return "expenseHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	@SuppressWarnings("unused")
	public String delete() throws DaoException {
			//遍历所有的父类
			for(Expense e:expenses){
				try {
					//根据该父类查找，所有的该子类
					List <Expense> expense_childs=this.expenseManager.loadByKey("parentEP", e);
					if(expense_childs!=null){
						Iterator<Expense> expense_childs_= expense_childs.iterator();
						while(expense_childs_.hasNext()){
							Expense e_1=expense_childs_.next();
							//根据该水电类收费信息，查找改水电费的收款计划
							String [] keys={"customerInfo","planDate","mold.code","disabled"};
							Object [] values={e_1.getCustomerInfo(),e_1.getEndTime(),"23702",false};
							List <ReturnPlan>returnPlans=this.returnPlanManager.loadByKeyArray(keys, values);
							if(returnPlans!=null&& returnPlans.size()>0){
								Iterator< ReturnPlan> its= returnPlans.iterator();
								while(its.hasNext()){
									ReturnPlan r_now=its.next();
									//判断该收款计划是否可以删除(即该收款计划是否有开票记录和收款单)
									List financials=this.financialManagementManager.loadByKey("returnPlan", r_now);
									List billingRecords= this.billingRecordManager.loadByKey("returnPlan", r_now);
	                                if(financials==null && billingRecords==null){
	                                	this.returnPlanManager.deleteReturnPlan(r_now);
	    								its.remove();
	                                }
								}
							}
							if(!(returnPlans!=null&& returnPlans.size()>0)){
								this.expenseManager.deleteExpense(e_1);
								expense_childs_.remove();
							}
						}
						
						if(!(expense_childs!=null&& expense_childs.size()>0)){
							this.expenseManager.deleteExpense(e);
						}
					}else{
						this.expenseManager.deleteExpense(e);
					}
					
				} catch (DaoException e1) {
					addActionMessage(getText("expense.delete.error"));
					e1.printStackTrace();
					return ERROR;
				}
			}
			addActionMessage(getText("expense.delete.success"));
			return SUCCESS;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

}
