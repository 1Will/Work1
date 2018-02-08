package com.yongjun.tdms.dao.demo.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.demo.DemoDAO;
import com.yongjun.tdms.model.demo.Demo;

public class DemoDAOImpl extends BaseHibernateDao implements DemoDAO {

	public List loadPage(int size) {
		//return getHibernateTemplate().
		return null;
	}
	
	public Demo loadDemo(Long demoId) {
        Demo demo=(Demo) this.load(Demo.class,demoId);
        return demo;
    }

    public List<Demo> loadAllDemoes(Long[] demoIds) {
        return this.loadAll(Demo.class,demoIds);
    }

}
