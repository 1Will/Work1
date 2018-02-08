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
package com.yongjun.tdms.dao.asset.spare.inWareHouse;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;

/**
 * <p>Title: SpareInBillDao
 * <p>Description: 备件入库数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface SpareInBillDao {
	/**
	 * 存储入库单
	 * @param spareInBill
	 */
	void storeSpareInBill(SpareInBill spareInBill);
	/**
	 * 存储入库单
	 * @param spareInBill
	 */
	void storeSpareInBillDetail(SpareInBillDetail spareInBill);
	/**
	 * 根据ID加载入库单
	 * @param spareInBillId
	 * @return SpareInBill类型实体
	 */
	SpareInBill loadSpareInBill(Long spareInBillId);
	/**
	 * 根据传入的Ids加载入库单
	 * @param spareInBillIds
	 * @return list<SpareInBill>
	 */
	List<SpareInBill> loadAllSpareInBill(Long[] spareInBillIds);
	/**
	 * 加载
	 * @param list
	 * @return
	 */
	List<SpareInBill> loadAllSpareInBill();
	/**
	 * 删除入库单
	 * @param spareInBill
	 */
	void deleteSpareInBill(SpareInBill spareInBill);
	/**
	 * 删除选中的所有SpareInBill
	 * @param list
	 */
	void deleteAllSpareInBill(List<SpareInBill> list);
	/**
	 * 根据传入的条件，统计生成的列表的总金额
	 * @param array
	 * @return
	 */
	Double showTotalPrice(String[] array);
	
	/**
	 * 根据传入的条件，统计生成的旧件列表的总金额
	 * @param array
	 * @return
	 */
	Double showOldTotalPrice(String[] array);
	
	List<SpareInBill> loadByKey(String key, Object value)throws DaoException;	
	
	SpareInBill loadSpareInBillBySpareOutBillId(Long spareOutBillId);
}
