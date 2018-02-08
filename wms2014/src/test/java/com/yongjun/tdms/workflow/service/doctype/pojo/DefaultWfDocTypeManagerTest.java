package com.yongjun.tdms.workflow.service.doctype.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.workflow.service.SimplePoJo;
import com.yongjun.tdms.workflow.service.doctype.WfDocTypeManager;

import junit.framework.TestCase;

/**
 * @author qs
 * @version $Id: DefaultWfDocTypeManagerTest.java 7940 2007-10-22 08:50:11Z qsun $
 */
public class DefaultWfDocTypeManagerTest extends TestCase {
	private WfDocTypeManager wfDocTypeManager;
	
	public void setUp() {
		String[] paths = { 
				"classpath*:jdbcContext.xml",
				"classpath*:eam2008-hibernate-daoContextTest.xml",
				"classpath*:hibernate-daoContext.xml",
				"classpath*:serviceContext.xml",
				"classpath*:sequenceContext.xml",
				"classpath*:eam2008-serviceContext.xml",
				"classpath*:securityContext.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(paths);

		//wfDocTypeManager = new DefaultWfDocTypeManager();
		wfDocTypeManager = (WfDocTypeManager)context.getBean("wfDocTypeManager");
	}
	
	/*
	 * see http://www.springframework.org/docs/reference/beans.html #3.3.3.4. Collections	
	 */
	public void testIsValidDocType() {
		SimplePoJo pojo = new SimplePoJo();
		assertTrue(true == wfDocTypeManager.isValidDocType(pojo));
		
		FakeX fake = new FakeX();
		assertTrue(false == wfDocTypeManager.isValidDocType(fake));
	}
	
	class FakeX extends BaseInfoEntity {
		private static final long serialVersionUID = 2604272274273950116L;

		public FakeX () { }
		
		@Override
		public int hashCode() {
			return 0;
		}

		@Override
		public boolean equals(Object arg0) {
			return false;
		}
		
	}
}
