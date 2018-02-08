package com.yongjun.tdms.model.runmaintenance.checkpoint;

import java.util.Date;

import com.yongjun.pluto.util.DateUtil;


import junit.framework.TestCase;

public class CheckPointPlanTest extends TestCase {

	@SuppressWarnings("deprecation")
	public void testCreatePlanFromRule() {
		/*
		 * d1: 1/1/2007
		 * d2: 1/3/2007
		 * d3: 1/5/2007
		 * so, the priod = 2
		 * auto create the plan in next month.
		 * d1: 2/1/2007
		 * d2: 2/3/2007
		 * d3: 2/5/2007
		 */
		Date d = new Date(2007,9,29);
		
		Date x = DateUtil.addMonthsToDate(d, 1);

		System.out.println(x);
		assertTrue(x.getMonth() == 10);
	}

}
