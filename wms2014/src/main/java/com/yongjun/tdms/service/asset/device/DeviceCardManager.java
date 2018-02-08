package com.yongjun.tdms.service.asset.device;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.service.Manager;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: DeviceCardManager.java 11057 2008-02-22 01:39:21Z zbzhang $
 */
@Transactional(readOnly = true)
public interface DeviceCardManager extends Manager {
	@Transactional
	public void storeDevice(DeviceCard device);

	public DeviceCard loadDevice(Long deviceId);
	
	public List<DeviceCard> createSelectDevices(String name);

	public List<DeviceCard> loadAllDevices();
	
	public List<DeviceCard> loadAllDevices(Long [] deviceIds);
	
	public DeviceCard getDeviceByNo(String deviceNo);

	/**
	 * 加载除DeviceCard的<b>id</b>及其相关联的附属设备<b>AccessoryDevice</b>数据. 
	 * @param id 设备卡片表PK
	 * @return List<DeviceCard>
	 */
//	public List<DeviceCard> loadAllUnrelatedDevices(Long id);
	
	public String parseAndCalculateDeviceNo(String typeCode, String maxDeviceNo) ;
	
	@Transactional
	void cancelJob(DeviceCard deviceCard);

	@Transactional
	public void submitDoc(DeviceCard device, Long[] ids, Long finalId,
			String comment, String deviceNo, String name) throws Exception ;
	
	@Transactional
	public void storeTooling(DeviceCard tooling);
	
	/**
	 * 根据传入的工装集合，失效集合中的工装
	 * @param toolings 工装集合
	 */
	@Transactional
	public void disabledAllToolings(List<DeviceCard> toolings);
	
	/**
	 * 根据传入的工装集合，有效集合中的工装
	 * @param toolings 工装集合
	 */
	@Transactional
	public void enabledAllToolings(List<DeviceCard> toolings);
	
	/**
	 * 根据传入的设备集合，失效集合中的设备
	 * @param devices 设备集合
	 */
	@Transactional
	public void disabledAllDevices(List<DeviceCard> devices);
	
	/**
	 * 根据传入的设备集合，有效集合中的设备
	 * @param devices 设备集合
	 */
	@Transactional
	public void enabledAllDevices(List<DeviceCard> devices);
	
	/**
	 *  根据传入的字符串（字符串中包含工装id和标定周期的值），保存工装
	 * @param alterToolingDemacrateCycle 字符串
	 */
	@Transactional
	public void storeTooling(String alterToolingDemacrateCycle);
	
	/**
	 * 根据传入的字符串（字符串中包含工装id和标定周期的值,负责人的值），保存工装
	 * @param alterToolingDemacrateCycle   包含标定周期的字符串
	 * @param alterToolingManager          包含负责人的字符串
	 */
	@Transactional
	public void storeTooling(String alterToolingDemacrateCycle, String alterToolingManager);
	
	/**
	 * 根据传入的查询条件，获取符合条件的设备集合,主要用于打印设备报表
	 * @param searchOption  查询条件，[key:查询条件名，value:查询条件的值]
	 * @return List 设备集合
	 */
	public List<DeviceCard> loadAllMatchOptionDevices(Map searchOption);
	
	/**
	 * 根据传入的查询条件，获取符合条件的工装集合,主要用于打印工装报表
	 * @param searchOption 查询条件，[key:查询条件名，value:查询条件的值]
	 * @return List 工装集合
	 */
	public List<DeviceCard> loadAllMatchOptionToolings(Map searchOption);
	public List Query(String[] queryInfo);
	
	/**
	 * 获取资产是设备且状态为正常的设备
	 * @return List 设备集合
	 */ 
	public List<DeviceCard> loadAllDeviceByStatusAndAssetType(); 
	
	DeviceCard loadDeviceByAcceptBill(Long acceptBillId);
	/**
	 * 通过@param groupNo 获取工装台帐中和此编号相同的工装台帐的记录
	 * @param groupNo
	 * @return
	 */
	List getToolingGroupNoByGroupNo(String groupNo);
	
}
