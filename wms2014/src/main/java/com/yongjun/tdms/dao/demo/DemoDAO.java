package com.yongjun.tdms.dao.demo;

import java.util.List;

import com.yongjun.tdms.model.demo.Demo;

/**
 * @author qs
 * @version $Id: DemoDAO.java 6888 2007-09-05 13:24:56Z qsun $
 */
public interface DemoDAO {
	List loadPage(int size);
	
	Demo loadDemo(Long demoId);
	
	List<Demo> loadAllDemoes(Long[] demoIds);
}
