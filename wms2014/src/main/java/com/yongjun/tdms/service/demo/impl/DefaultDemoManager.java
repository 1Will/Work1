package com.yongjun.tdms.service.demo.impl;

import java.util.List;

import com.yongjun.tdms.dao.demo.DemoDAO;
import com.yongjun.tdms.model.demo.Demo;
import com.yongjun.tdms.service.demo.DemoManager;

public class DefaultDemoManager implements DemoManager {
	private DemoDAO demoDAO;
	
	public void setDemoDAO(DemoDAO dao) {
		this.demoDAO = dao;
	}
	
	public List loadPage(int size) {
		return demoDAO.loadPage(size);
	}

	
	public Demo loadDemo(Long demoId) {
		return demoDAO.loadDemo(demoId);
	}
	

	public List<Demo> loadAllDemoes(Long[] demoIds) {
		return demoDAO.loadAllDemoes(demoIds);
	}

}
