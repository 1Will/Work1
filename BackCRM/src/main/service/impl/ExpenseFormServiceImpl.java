package main.service.impl;

import org.hibernate.Session;

import main.dao.ExpenseFormDao;
import main.pojo.ExpenseForm;
import main.service.ExpenseFormService;

public class ExpenseFormServiceImpl implements ExpenseFormService {
         
   private ExpenseFormDao expenseFormDao;

@Override
public ExpenseForm getExpenseFormById(Long id) {
	return expenseFormDao.getExpenseFormById(id);
}

@Override
public Session getSuperSession() {
	return expenseFormDao.getSuperSession();
}

public ExpenseFormDao getExpenseFormDao() {
	return expenseFormDao;
}

public void setExpenseFormDao(ExpenseFormDao expenseFormDao) {
	this.expenseFormDao = expenseFormDao;
}



	
	
}
