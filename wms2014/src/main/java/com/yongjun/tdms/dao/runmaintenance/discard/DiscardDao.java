/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.dao.runmaintenance.discard;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

/**
 * <p>Title: DiscardDao
 * <p>Description: 报废单数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DiscardDao.java 8876 2007-12-02 02:47:27Z wzou $
 */
public interface DiscardDao {
	/**
	 * 根据传入的报废单ID集合，获取集合中的报废单
	 * 
	 * @param discardIds	报废单ID集合
	 * @return List				部门集合
	 */
	List<Discard> loadAllDiscards(Long[] discardIds);
	
	/**
	 * 根据传入的报废单ID集合，删除集合中的报废单
	 * 
	 * @param discards	部门ID集合
	 * @return
	 */
	void deleteAllDiscard(Collection<Discard> discards) throws WFJobInProcException;
	
	/**
	 * 根据传入的报废单ID，获取报废单
	 * 
	 * @param discardId		报废单ID
	 * @return discard 		报废单实体
	 */
	public Discard loadDiscard(Long discardId);
	
	/**
	 * 保存报废单
	 * @param discard	报废单实体
	 * @return
	 */
	void storeDiscard(Discard discard);
	
	/**
     * 根据传入的工装ID，获取该工装的报废单
     * 
     * @param toolingId			工装ID
     * @return  toolingDiscard	报废单
     */
	public Discard getToolingDiscardByToolingId(final Long toolingId);
	
	/**
     * 根据传入的设备ID，获取该设备的报废单
     * 
     * @param toolingId			设备ID
     * @return  toolingDiscard	报废单
     */
	public Discard getDeviceDiscardByToolingId(final Long deviceId);
}
