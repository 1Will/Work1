package main.dao.impl;

import main.dao.ExpenseFormDao;
import main.pojo.ExpenseForm;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ExpenseFormDaoImpl extends HibernateDaoSupport implements ExpenseFormDao {
	

	@Override
	public ExpenseForm getExpenseFormById(Long id) {
		ExpenseForm expenseForm = null;
		try {
			expenseForm=(ExpenseForm) this.getSession().load(ExpenseForm.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return expenseForm;
	}
	
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}
	
    
	
}
