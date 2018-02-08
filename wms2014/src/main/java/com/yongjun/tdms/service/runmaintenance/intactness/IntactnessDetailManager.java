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
import com.yongjun.tdms.model.runmaintenance.intactness.IntactnessDetail;

/**
 * <p>Title: IntactnessDetailManager
 * <p>Description: 设备鉴定明细业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly=true)
public interface IntactnessDetailManager {
	/**
	 * 根据传入的鉴定单明细ID，获取鉴定单明细
	 * 
	 * @param intactnessDetailId 鉴定单明细ID
	 * @return IntactnessDetail 鉴定单明细
	 */
	IntactnessDetail loadIntactnessDetail(Long intactnessDetailId);
	
	/**
	 * 根据传入的鉴定单明细ID集合，获取集合中的鉴定单明细
	 * 
	 * @param intactnessDetailIds 鉴定单明细ID集合
	 * @return List 鉴定单明细集合
	 */
	List<IntactnessDetail> loadAllIntactnessDetails(Long [] intactnessDetailIds);
	
	/**
	 * 获取集合中的鉴定单明细
	 * 
	 * @return List 鉴定单明细集合
	 */
	List<IntactnessDetail> loadAllIntactnessDetail();
	
	/**
	 * 保存鉴定单明细
	 * 
	 * @param intactnessDetail 鉴定单明细实体
	 * @return
	 */
	@Transactional
	void storeIntactnessDetail(IntactnessDetail intactnessDetail);
	
	/**
	 * 删除鉴定单明细
	 * 
	 * @param intactnessDetail 鉴定单明细实体
	 * @return
	 */
	@Transactional
	void deleteIntactnessDetail(IntactnessDetail intactnessDetail);
	
	/**
	 * 根据传入的鉴定单明细集合，删除集合中的鉴定单明细
	 * 
	 * @param intactnessDetail 鉴定单明细集合
	 * @return
	 */
	@Transactional
	void deleteAllIntactnessDetail(Collection<IntactnessDetail> intactnessDetail);
	
	/**
	 * 根据传入的新添加的设备的id,保存到鉴定明细中,作为相应鉴定单的明细
	 * @param intactness 鉴定单
	 * @param newDeviceIds 设备ID
	 */
	@Transactional
	void storeIntactnessDetail(Intactness intactness, String newDeviceIds);
	
	/**
	 * 根据传入的鉴定明细ID字符串,鉴定结果字符串,详细描述字符串,更新鉴定明细相应的字段
	 * @param allIntactnessDetailId 鉴定明细ID
	 * @param allIntactnessDetailId 鉴定结果字符串
	 * @param allComment 详细描述字符串
	 */
	@Transactional
	void storeIntactnessDetail(String allIntactnessDetailId, 
			String allIntactnessDetailResult,
			String allComment);
}
