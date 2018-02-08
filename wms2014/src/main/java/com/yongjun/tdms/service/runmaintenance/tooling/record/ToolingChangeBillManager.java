package com.yongjun.tdms.service.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;

/**
 * <p>Title: ToolingChangeBillManager
 * <p>Description: 工装变更单管理业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */

@Transactional(readOnly = true)
public interface ToolingChangeBillManager {
	/**
	 * 根据传入的工装变更单ID，获取工装变更单
	 * 
	 * @param toolingChangeBillId 工装变更单ID
	 * @return ToolingChangeBill 工装变更单实体
	 */
	ToolingChangeBill loadToolingChangeBill(Long toolingChangeBillId);
	
	/**
	 * 根据传入的工装变更单ID集合，获取集合中的工装变更单
	 * 
	 * @param toolingChangeBillIds 工装变更单ID集合
	 * @return List 工装变更单集合
	 */
	List<ToolingChangeBill> loadAllToolingChangeBills(Long [] toolingChangeBillIds);
	
	/**
	 * 获取集合中的工装变更单
	 * 
	 * @return List 工装变更单集合
	 */
	List<ToolingChangeBill> loadAllToolingChangeBills();

	/**
	 * 保存工装变更单
	 * 
	 * @param toolingChangeBill 工装变更单实体
	 * @param tooling 工装实体
	 */
	@Transactional
	void storeToolingChangeBill(ToolingChangeBill toolingChangeBill, DeviceCard tooling);
	
	/**
	 * 保存工装变更单
	 * 
	 * @param toolingChangeBill 工装变更单实体
	 */
	@Transactional
	void storeToolingChangeBill(ToolingChangeBill toolingChangeBill);
	
	/**
	 * 删除工装变更单
	 * 
	 * @param toolingChangeBill 工装变更单实体
	 */
	@Transactional
	void deleteToolingChangeBill(ToolingChangeBill toolingChangeBill);
	
	/**
	 * 根据传入的工装变更单集合，删除集合中的工装变更单
	 * 
	 * @param toolingChangeBills 工装变更单集合
	 */
	@Transactional
	void deleteAllToolingChangeBills(Collection<ToolingChangeBill> toolingChangeBills);
	
	/**
	 * 根据传入的工装变更单集合，失效集合中的工装变更单
	 * 
	 * @param toolingChangeBills 工装变更单集合
	 */
	@Transactional
	void disabledAllToolingChangeBills(Collection<ToolingChangeBill> toolingChangeBills);
	
	/**
	 * 根据传入的工装变更单集合，有效集合中的工装变更单
	 * 
	 * @param toolingChangeBills 工装变更单集合
	 */
	@Transactional
	void enabledAllToolingChangeBills(Collection<ToolingChangeBill> toolingChangeBills);

}
