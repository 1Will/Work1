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
package com.yongjun.tdms.service.asset.spare.inWareHouse;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailView;

/**
 * <p>Title:SpareInBillManager
 * <p>Description:备件入库服务访问接口类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
@Transactional(readOnly = true)
public interface SpareInBillManager {
	/**
	 * 存储备件入库单
	 * @param spareInBill
	 */
	@Transactional
	void storeSpareInBill(SpareInBill spareInBill);
	/**
	 * 根据入库单的ID加载入库单
	 * @param spareInBillId
	 * @return SpareInBill
	 */
	SpareInBill loadSpareInBill(Long spareInBillId);
	/**
	 * 根据入库单的ID集合加载入库单
	 * @param spareInBillIds
	 * @return
	 */
	List<SpareInBill> loadAllSpareInBill(Long[] spareInBillIds);
	/**
	 * 删除入库单
	 * @param spareInBill
	 */
	@Transactional
	void deleteSpareInBill(SpareInBill spareInBill);
	/**
	 * 删除入库单集合
	 * @param list
	 */
	@Transactional
	void deleteAllSpareInBill(List<SpareInBill> list);
	/**
	 *保存明显byt 用于以旧换新。把出库单明显数据带过来
	 * @param list
	 */
	@Transactional
	void storeAllSpareInBillDetail(List<SpareInBillDetail> list);
	/**
	 *保存明显byt 用于以旧换新。把出库单明显数据带过来
	 * @param list
	 */
	@Transactional
	void storeFalseSpareInBillDetail(SpareInBillDetail detail);
	/**
	 * 失效
	 * @param spareInBills
	 */
	@Transactional
	void disabledAllSpareInBill(List<SpareInBill> spareInBills)throws Exception;
	/**
	 * 有效
	 * @param spareInBills
	 */
	@Transactional
	void enabledAllSpareInBill(List<SpareInBill> spareInBills);
	/**
	 * 根据传入的入库单id在入库单明细视图类中查询出属于该入库单的所有入库单明细
	 * @param id
	 * @return
	 */
	List<SpareInBillDetailView> loadSpareInBillDetailView(Long id);
	/**
	 * 打印入库单报表汇总
	 * @param array
	 * @return
	 */
	public List<SpareInBillDetailView> loadSpareInBillDetailView(String[] array)throws HibernateException;
	@Transactional
	void  accordingSpareInBillDetailStatusUpdateSpareInStatus(SpareInBill spareInBill);
	
	/**
	 * 根据传入的条件，统计生成的列表的总金额
	 * @param array
	 * @return
	 */
	Double showTotalPrice(String[] array);
	
	/**
	 * @function 打印验收单后，改变标识为true
	 * @author wliu
	 * @param spareInBillId 入库单标识
	 */
	@Transactional
	public void saveAcceptanceList(String spareInBillId);
	
	public List<SpareInBill> loadByKey(String key, Object value) throws DaoException;
	
	/**
	 * 根据传入的条件，统计生成的旧件列表的总金额
	 * @param array
	 * @return
	 */
	public Double showOldTotalPrice(String[] info);
}
