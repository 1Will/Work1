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
package com.yongjun.tdms.service.runmaintenance.discard;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

/**
 * <p>Title: DiscardManager
 * <p>Description: 工装报废业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DiscardManager.java 8870 2007-12-02 02:37:23Z wzou $
 */
@Transactional(readOnly=true)
public interface DiscardManager {
	
	/**
     * 传入工装报废单ID集合，获取集合到数据库
     * 
     * @param discardIds	报废单ID集合
     * @return List				报废单集合
     */
	List<Discard> loadAllDiscards(Long[] discardIds);
	
	/**
	 * 根据传入的报废单ID集合，删除集合中的报废单
	 * 
	 * @param Discards	报废单ID集合
	 * @return
	 */
	@Transactional
	void deleteAllDiscard(Collection<Discard> Discards) throws WFJobInProcException;
	
	/**
	 * 根据传入的报废单ID，获取报废单
	 * 
	 * @param DiscardId		报废单ID
	 * @return Discard		报废单
	 */
	public Discard loadDiscard(Long DiscardId);
	
	/**
     * 传入报废单实体，保存到数据库
     * 
     * @param Discard	报废单
     * @return
     */
	@Transactional
	void storeDiscard(Discard Discard);
	
	/**
     * 根据传入的工装ID，获取该工装的报废单
     * 
     * @param Id			工装ID
     * @return  Discard	报废单
     */
	public Discard getToolingDiscardByToolingId(final Long toolingId);
	
	/**
     * 根据传入的设备ID，获取该设备的报废单
     * 
     * @param Id			设备ID
     * @return  Discard	报废单
     */
	public Discard getDeviceDiscardByToolingId(final Long deviceId);
	@Transactional
	void disabledAllDiscards(Collection<Discard> DiscardBills);
	
	@Transactional
	void enabledAllDiscards(Collection<Discard> DiscardBills);
	
}
