/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.dao.prophase.business;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;

/**
 * <p>Title: SubscribeCollectBillDao
 * <p>Description: 申购汇总单数据库访问接口</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id$
 */
public interface SubscribeCollectBillDao {
	
	/**
	 * 根据id 获得汇总单的对象
	 * @param id
	 * @return
	 */
	SubscribeCollectBill loadSubscribeCollectBill(Long id);
	/**
	 * 根据ids 获得汇总单的集合
	 * @param SparePartIds
	 * @return
	 */
	
    List<SubscribeCollectBill> loadAllSubscribeCollectBills(Long[] SubscribeCollectBillIds);
    /**
     * 保存汇总单的对象
     * @param sparePart
     */
   
	void storeSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill);
	/**
	 * 删除汇总单的对象
	 * @param sparePart
	 */
	
	void deleteSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill);
	/**
	 * 删除所有的汇总单 
	 * @param SparePartIds
	 */
	
	void deleteAllSubscribeCollectBill(Collection<SubscribeCollectBill>SubscribeCollectBillIds);

}
