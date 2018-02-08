package com.yongjun.tdms.service.asset.device.pojo;

import static org.easymock.EasyMock.*;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.tdms.dao.asset.device.DeviceCardDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceType;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

import junit.framework.TestCase;

/**
 * @author qs
 * @version $Id: DefaultDeviceCardManagerTest.java 9153 2007-12-09 06:49:42Z qsun $
 */
public class DefaultDeviceCardManagerTest extends TestCase {
	private final Log log = LogFactory.getLog(getClass());
	private DeviceCardDao deviceCardDao;
	private DeviceCardManager deviceCardManager;
	
	public void setUp() {
		this.deviceCardDao = createNiceMock(DeviceCardDao.class);
//		this.deviceCardManager = new DefaultDeviceCardManager(deviceCardDao, null,null);
	}
	
	public void testParseAndCalculateDeviceNo() {
		String deviceNo = deviceCardManager.parseAndCalculateDeviceNo("012", null);
		assertTrue(deviceNo.startsWith("012-"));
		assertTrue("expect 012-000000, but not", deviceNo.equals("012-000000"));
		deviceNo = deviceCardManager.parseAndCalculateDeviceNo("012", "000001");
		assertTrue(deviceNo.equals("012-000002"));
		log.debug("ok");
	}
	
	public void testStoreDevice() {
		DeviceCard device = new DeviceCard();
		DeviceType deviceType = new DeviceType();
		deviceType.setName("a");
		deviceType.setCode("001");
		device.setDeviceType(deviceType);
		
		expect(deviceCardDao.getMaxDeviceNoByAssetCode(device.getDeviceType().getCode())).andReturn("001");
		replay(deviceCardDao);
		
		deviceCardManager.storeDevice(device);
	}
	
	public void testGetClzID() {
		String clz = "class com.yongjun.tdms.model.asset.device.DeviceCard$$EnhancerByCGLIB$$7db8fcaa";
		String clz2 = "class com.yongjun.tdms.model.asset.device.DeviceCard";
		
		assertTrue(clz2.indexOf('$') == -1);
		
		StringTokenizer st = new StringTokenizer(clz, "$$");
		while(st.hasMoreTokens()) {
			clz = st.nextToken();
			break;
		}
		
		assertTrue(clz.indexOf('$') == -1);
		
		System.out.println(clz);
	}
}