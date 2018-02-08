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
package com.yongjun.tdms.dao.prophase.business;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;

/**
 * @author qs
 * @version $Id: SubscribeDao.java 11237 2008-03-09 10:36:42Z mwei $
 */
public interface SubscribeDao {

	Subscribe loadSubscribe(Long id);

	void storeSubscribe(Subscribe subscribe);

	List<Subscribe> loadAllSubscribes(Long[] ids);

	void deleteAllSubscribes(List<Subscribe> subscribes);

	List<SubscribeDetail> loadAllSubscribeDetails(Long[] ids);

	void deleteAllSubscribeDetails(List<SubscribeDetail> subscribeDetails);

	SubscribeDetail loadSubscribeDetail(Long id);

	void storeSubscribeDetail(SubscribeDetail subscribeDtl);
	
	List Query(String[] queryInfo) throws HibernateException;
	
	List<SubscribeDetail> loadAllSubscribeDetailByDetailType(Long id,String detailType);
	
	void storeToolingAndDevice(SubscribeDetail dtl);

	void storeToolingPurchase(Subscribe subscribe);
	/**
	 * 
	 * @param subscribeId
	 * @param detailType
	 * @return
	 */
	Double getAllSubscribeDetailTotalPriceBySubscribeIdAndDetailType(Long subscribeId);
	
	public List<SubscribeDetail> loadByKey(String key, Object value) throws DaoException;
	
	public List<Regional> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
	
	/**
	 * 根据非主键集合找明细
	 * @param arrys
	 * @param values
	 * @return
	 */
	public List<SubscribeDetail>loadDetailBykeyArry(String[] arrys,Object[] values)throws DaoException;
	
	/**
	 * 取得总金额
	 * @return
	 */
	public Double getSumPrice(SubscribeCollectBill bill);
	
	/**
	 * 取得采购数量 包括入库
	 * @param bill
	 * @return
	 */
	public Integer getPurNum(SubscribeCollectBill bill);
}
