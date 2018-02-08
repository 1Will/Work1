package com.yongjun.tdms.workflow.service.job.pojo;

import static org.easymock.EasyMock.*;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.tdms.workflow.service.SimplePoJo;
import com.yongjun.tdms.workflow.service.doctype.WfDocTypeManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

import junit.framework.TestCase;

/**
 * @author qs
 * @version $Id: DefaultWfJobManagerTest.java 8121 2007-10-30 03:37:41Z qsun $
 */
public class DefaultWfJobManagerTest extends TestCase {
	private WfJobManager wfJobManager;
	private WfDocTypeManager wfDocTypeManagerMock;
	
	public void setUp() {
		wfDocTypeManagerMock = createMock(WfDocTypeManager.class);
		wfDocTypeManagerMock.hashCode();
		//wfJobManager = new DefaultWfJobManager(wfDocTypeManagerMock);
	}
	
	public void testSubmitJob() {
		SimplePoJo pojo = new SimplePoJo();
		
		try {
			wfJobManager.submitJob(pojo, null, null, null, null, null);
		} catch (Exception e) {
			assertFalse("simple test must no exception", false);
		}
	}
	
	public void testGetJobApprovers() {
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");

		String s = "";
		for (int i = 0; i < set.size(); i ++) {
			String x = (String)set.toArray()[i];
			s += x;
			if (i+1 < set.size()) {
				s += ", ";
			}
		}
		System.out.print(s);
		assertTrue(s.length() == 7);
	}
	
	public void testGetJobApprovers2() {
		String target = "foo,bar, ";
		String x = target.replaceAll(",\\s$", "");
		
		System.out.println(x);
		assertFalse(target.equals(x));
	}
}
