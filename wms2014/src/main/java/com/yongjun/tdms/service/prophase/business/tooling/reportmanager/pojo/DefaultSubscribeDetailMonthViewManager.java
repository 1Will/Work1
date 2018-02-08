package com.yongjun.tdms.service.prophase.business.tooling.reportmanager.pojo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.SubscribeDetailMonthViewDao;
import com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.hibernate.HibernateSubscribeDetailMonthViewDao;
import com.yongjun.tdms.model.prophase.business.report.SubscribeCountByDeptReport;
import com.yongjun.tdms.service.prophase.business.tooling.reportmanager.SubscribeDetailMonthViewManager;

public class DefaultSubscribeDetailMonthViewManager implements
		SubscribeDetailMonthViewManager {
	private final SubscribeDetailMonthViewDao subscribeDetailMonthViewDao;
	
	public DefaultSubscribeDetailMonthViewManager(SubscribeDetailMonthViewDao subscribeDetailMonthViewDao){
		this.subscribeDetailMonthViewDao = subscribeDetailMonthViewDao;
	}

	public void deleteAll(Collection<SubscribeCountByDeptReport> coll) {

	}

	public void deleteAllMonthView() {
		this.subscribeDetailMonthViewDao.deleteAllMonthView();
	}

	public List<SubscribeCountByDeptReport> loadByAllMonthView() {
		return this.subscribeDetailMonthViewDao.loadByAllMonthView();
	}

	public List<SubscribeCountByDeptReport> loadByKey(String key, Object value)
			throws Exception {
		return this.subscribeDetailMonthViewDao.loadByKey(key, value);
	}

	public List<SubscribeCountByDeptReport> loadByKeyArrys(String[] keyArrys,
			Object[] values) throws Exception {
		return this.subscribeDetailMonthViewDao.loadByKeyArrys(keyArrys, values);
	}

	public void storeMonthView(SubscribeCountByDeptReport monthview) {
		this.subscribeDetailMonthViewDao.storeMonthView(monthview);
	}

	public List<SubscribeCountByDeptReport> loadDetailViewNumber(String[] array) {
		return this.subscribeDetailMonthViewDao.loadDetailViewNumber(array);
	}

	public List<SubscribeCountByDeptReport> loadDetailViewNumberSupplier(String purIds, Date year_month) {
		return this.subscribeDetailMonthViewDao.loadDetailViewNumberSupplier(purIds, year_month);
	}


}
