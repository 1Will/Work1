package com.yongjun.tdms.service.prophase.business.tooling.reportmanager;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.business.report.SubscribeCountByDeptReport;

public interface SubscribeDetailMonthViewManager {
	@Transactional
	public void storeMonthView(SubscribeCountByDeptReport monthview);
	
	public List<SubscribeCountByDeptReport> loadByKey(String key,Object value)throws Exception;
	
	public List<SubscribeCountByDeptReport> loadByKeyArrys(String[] keyArrys,Object[] values)throws Exception;
	
	public List<SubscribeCountByDeptReport> loadByAllMonthView();
	@Transactional
	public void deleteAll(Collection<SubscribeCountByDeptReport> coll);
	
	public void deleteAllMonthView();
	
	/**
	 * 或得某月的统计数量 月份
	 * @param startDate 月初时间
	 * @param endDate 月末时间
	 * @return
	 */
	@Transactional
	public List<SubscribeCountByDeptReport> loadDetailViewNumber(String[] array);
	
}
