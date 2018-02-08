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
package com.yongjun.tdms.dao.runmaintenance.wash;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;

/**
 * <p>Title: WashDetailDao
 * <p>Description: 清洗明细数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public interface WashDetailDao {
	/**
	 * 根据传入的清洗明细ID，获取清洗明细对象
	 * @param detailId 清洗明细ID
	 * @return WashDetail 清洗明细对象
	 */
	WashDetail loadWashDetail(Long detailId);
	
	/**
	 * 根据传入的清洗明细ID集合，获取清洗明细对象集合
	 * @param detailId 清洗明细ID集合
	 * @return List 清洗明细对象集合
	 */
	List<WashDetail> loadAllWashDetails(Long [] detailIds);
	
	/**
	 * 获取清洗明细对象集合
	 * @return List 清洗明细对象集合
	 */
	List<WashDetail> loadAllWashDetails();
	
	/**
	 * 保存传入的清洗明细对象
	 * @param detail 清洗明细对象
	 */
	void storeWashDetail(WashDetail detail);
	
	/**
	 * 删除传入的清洗明细对象
	 * @param detail 清洗明细对象
	 */
	void deleteWashDetail(WashDetail detail);
	
	/**
	 * 根据传入清洗明细集合，删除集合中的清洗明细对象
	 * @param details 清洗明细集合
	 */
	void delteAllWashDetail(Collection<WashDetail> details);
}
