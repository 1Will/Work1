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
package com.yongjun.tdms.service.asset.spare;

import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.yongjun.tdms.model.asset.spare.SpareInOutBill;

/**
 * @author qs
 * @version $Id: SpareInOutBillManager.java 10380 2008-01-15 02:43:28Z mwei $
 */
@Transactional(readOnly = true)
public interface SpareInOutBillManager {

	/**
	 * 根据备件出、入库单的ID号数组，加载所有的出、入库单对象
	 * 
	 * @param spareInOutBillIds出、入库单ID号数组
	 * @return 备件出、入库单对象集合
	 */
	List<SpareInOutBill> loadAllSpareInOutBill(Long[] spareInOutBillIds);

	/**
	 * 根据备件出、入库单的ID号，加载出、入库单对象
	 * 
	 * @param spareInOutBillIds出、入库单ID号
	 * @return 备件出、入库单对象集合
	 */
	SpareInOutBill loadSpareInOutBill(Long spareInOutBillIds);

	/**
	 * 根据备件出、入库单，备件相关值的数组和出、入库标志，存储备件出、入库单
	 * 
	 * @param spareInOutBillIds出、入库ID号,备件出、入库单相关值的字符串，input出、入库标志
	 * @return 
	 */
	@Transactional
	void storeSpareInOutBill(SpareInOutBill spareInOutBill,String stringMapValue,boolean input);
	
	/**
	 * 存储备件出、入库单
	 * 
	 * @param spareInOutBill出、入库单
	 * @return 
	 */
	@Transactional
	void storeSpareInOutBill(SpareInOutBill spareInOutBill);

	/**
	 * 存储备件出、入库单
	 * 
	 * @param spareInOutBill出、入库单
	 * @return 
	 */
	@Transactional
	void deleteSpareInOutBill(SpareInOutBill spareInOutBill);

	@Transactional
	void deleteAllSpareInOutBill(Collection<SpareInOutBill> spareInOutBill);
}
