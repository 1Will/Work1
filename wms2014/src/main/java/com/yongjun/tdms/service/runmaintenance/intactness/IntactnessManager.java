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
package com.yongjun.tdms.service.runmaintenance.intactness;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.yongjun.tdms.model.runmaintenance.intactness.Intactness;

/**
 * <p>Title: IntactnessManager
 * <p>Description: 设备鉴定业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly = true)
public interface IntactnessManager {
	/**
	 * 根据传入的鉴定单ID，获取鉴定单
	 * 
	 * @param IntactnessId 鉴定单ID
	 * @return Intactness 鉴定单
	 */
	Intactness loadIntactness(Long IntactnessId);
	
	/**
	 * 根据传入的鉴定单ID集合，获取集合中的鉴定单
	 * 
	 * @param IntactnessIds 鉴定单ID集合
	 * @return List 鉴定单集合
	 */
	List<Intactness> loadAllIntactnesss(Long [] IntactnessIds);
	
	/**
	 * 获取集合中的鉴定单
	 * 
	 * @return List 鉴定单集合
	 */
	List<Intactness> loadAllIntactnesss();
	
	/**
	 * 保存鉴定单
	 * 
	 * @param Intactness 鉴定单实体
	 * @return
	 */
	@Transactional
	void storeIntactness(Intactness intactness);
	
	/**
	 * 删除鉴定单
	 * 
	 * @param intactness 鉴定单实体
	 * @return
	 */
	@Transactional
	void deleteIntactness(Intactness intactness);
	
	/**
	 * 根据传入的鉴定单集合，删除集合中的鉴定单
	 * 
	 * @param intactnesss 鉴定单集合
	 * @return
	 */
	@Transactional
	void deleteAllIntactness(Collection<Intactness> intactnesss);
	
	/**
	 * 根据传入的鉴定单集合，失效集合中的鉴定单
	 * 
	 * @param intactnesss 鉴定单集合
	 * @return
	 */
	@Transactional
	void disabledAllIntactness(Collection<Intactness> intactnesss);
	
	/**
	 * 根据传入的鉴定单集合，有效集合中的鉴定单
	 * 
	 * @param intactnesss 鉴定单集合
	 * @return
	 */
	@Transactional
	void enabledAllIntactness(Collection<Intactness> intactnesss);


}
