/**
 * 
 */
package com.yongjun.tdms.service.base.codevalue;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.codevalue.SpareType;

/**
 *<p>Title:SpareTypeManager.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
@Transactional(readOnly = true)
public interface SpareTypeManager {
	
	SpareType loadSpareType(Long spareTypeId);
	
	List<SpareType> loadAllSpareType(Long [] spareTypeIds);

	List<SpareType> loadAllSpareType();
	@Transactional
	void storeSpareType(SpareType spareType);
	@Transactional
	void deleteSpareType(SpareType spareType);
	@Transactional
	void deleteAllSpareType(Collection<SpareType> spareTypes);
	@Transactional
	void disabledAllSpareType(Collection<SpareType> spareTypes);
	@Transactional
	void endabledAllSpareType(java.util.Collection<SpareType> spareTypes);
	/**
	 * 根据设备|工装标识获取备件大类
	 * @param toolingDevFlag 工装|设备
	 * @return List<SpareType> 工装|设备的备件集合
	 */
	List<SpareType> loadAllSpareTypeByToolingDevFlag(String toolingDevFlag);
}
