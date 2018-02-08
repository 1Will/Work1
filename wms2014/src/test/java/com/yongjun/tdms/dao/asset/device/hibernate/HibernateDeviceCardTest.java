package com.yongjun.tdms.dao.asset.device.hibernate;


import java.util.Iterator;
import java.util.List;

import com.yongjun.tdms.dao.asset.device.DeviceCardDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;


/**
 * @author qs
 * @version $Id: HibernateDeviceCardTest.java 7614 2007-09-26 01:49:17Z qsun $
 */
public class HibernateDeviceCardTest extends BaseHibernateDaoTestCase {
	private DeviceCardDao deviceCardDao;

	public void testGetDeviceByNo() {
		assertNotNull(this.deviceCardDao.getDeviceByNo("111-000001"));
		assertNotNull(this.deviceCardDao.getDeviceByNo("111-000003"));
	}

	public void testLoadAllUnrelatedDevices() {
//		List results = this.deviceCardDao.loadAllUnrelatedDevices(1001L);
//		assertTrue(results.size() == 2);
//		
//		for (Iterator iter = results.iterator(); iter.hasNext(); ) {
//			DeviceCard device = (DeviceCard)iter.next();
//			assertFalse(device.getId().equals(1001L));			
//		}
	}

	public void testGetMaxDeviceNoByAssetCode() {
		assertEquals(this.deviceCardDao.getMaxDeviceNoByAssetCode("111-%"), "111-000003");
	}

	public void setUp() {
		super.setUp();
		init("com/yongjun/tdms/dao/asset/device/dbunit/deviceCard.xml");
		this.deviceCardDao = (DeviceCardDao)ctx.getBean("deviceCardDao");
	}
}
