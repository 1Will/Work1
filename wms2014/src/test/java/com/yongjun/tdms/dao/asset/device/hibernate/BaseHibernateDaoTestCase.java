package com.yongjun.tdms.dao.asset.device.hibernate;


import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import junit.framework.TestCase;

/**
 * @author qs
 * @version $Id: BaseHibernateDaoTestCase.java 7614 2007-09-26 01:49:17Z qsun $
 */
public class BaseHibernateDaoTestCase extends TestCase {
	protected ApplicationContext ctx;
	protected DBUnitTemplate template;
	private String resource;
	
	public void init(String resource) {		
		prepareDbUnit(resource, DatabaseOperation.INSERT);
	}
	
	public void setUp() {
		String[] paths = { 
				"classpath*:jdbcContext.xml",
				"classpath*:eam2008-hibernate-daoContextTest.xml",
				"classpath*:hibernate-daoContext.xml",
				"classpath*:serviceContext.xml",
				"classpath*:securityContext.xml",
				"classpath*:workflow.xml"};
		
		if (null == ctx) {
			ctx =new ClassPathXmlApplicationContext(paths);
		}
		
		if (null == template) {
			template = (DBUnitTemplate) ctx.getBean("dbUnitTemplate");			
		}
	}	
	
	public void tearDown() {
		try {
			template.execute(this.resource, DatabaseOperation.DELETE_ALL);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void prepareDbUnit(String resource, DatabaseOperation operation) {
		try {
			this.resource = resource;
			template.execute(this.resource, operation);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
