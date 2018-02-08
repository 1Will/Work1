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
package com.yongjun.tdms.dao.runmaintenance.intactness;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.intactness.IntactnessDetail;

/**
 * <p>Title: IntactnessDetailDao
 * <p>Description: 设备鉴定明细数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public interface IntactnessDetailDao {
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
	void storeIntactnessDetail(IntactnessDetail intactnessDetail);
	
	/**
	 * 删除鉴定单明细
	 * 
	 * @param intactnessDetail 鉴定单明细实体
	 * @return
	 */
	void deleteIntactnessDetail(IntactnessDetail intactnessDetail);
	
	/**
	 * 根据传入的鉴定单明细集合，删除集合中的鉴定单明细
	 * 
	 * @param intactnessDetail 鉴定单明细集合
	 * @return
	 */
	void deleteAllIntactnessDetail(Collection<IntactnessDetail> intactnessDetail);


}
