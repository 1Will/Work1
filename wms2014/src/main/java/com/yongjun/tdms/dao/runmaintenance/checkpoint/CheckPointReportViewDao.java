package com.yongjun.tdms.dao.runmaintenance.checkpoint;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportView;

public interface CheckPointReportViewDao {

	public List<CheckPointReportView> loadAllCheckPointReportView(String[] array) throws HibernateException;
}
