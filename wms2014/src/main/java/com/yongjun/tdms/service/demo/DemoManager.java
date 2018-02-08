package com.yongjun.tdms.service.demo;

import java.util.List;

import com.yongjun.tdms.model.demo.Demo;

public interface DemoManager {
	List loadPage(int size);
	
	Demo loadDemo(Long demoId);
	
	List<Demo> loadAllDemoes(Long[] demoIds);
}
