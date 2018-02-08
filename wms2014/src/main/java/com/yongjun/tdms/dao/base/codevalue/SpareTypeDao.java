/**
 * 
 */
package com.yongjun.tdms.dao.base.codevalue;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.codevalue.SpareType;

/**
 *<p>Title:SpareTypeDao.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
public interface SpareTypeDao {
	
	SpareType loadSpareType(Long spareTypeId);
	
	List<SpareType> loadAllSpareType(Long [] spareTypeIds);

	List<SpareType> loadAllSpareType();
	
	void storeSpareType(SpareType spareType);
	
	void deleteSpareType(SpareType spareType);
	
	void deleteAllSpareType(Collection<SpareType> spareTypes);
	
	/**
	 * 根据设备|工装标识获取备件大类
	 * @param toolingDevFlag 工装|设备
	 * @return List<SpareType> 工装|设备的备件集合
	 */
	List<SpareType> loadAllSpareTypeByToolingDevFlag(String toolingDevFlag);
}
