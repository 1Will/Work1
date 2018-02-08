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

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailView;

/**
 * <p>Title:SpareInBillDetailViewDao
 * <p>Description:入库单明细视图接口类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public interface SpareInBillDetailViewDao {
	/**
	 * 根据入库单id找出视图查询记录中属于该入库单id的所有入库明细
	 * @param spareInBillId
	 * @return
	 */
	List<SpareInBillDetailView> loadSpareInBillDetail(Long spareInBillId);
	/**
	 * 
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	public List<SpareInBillDetailView>loadSpareInBillDetailView(String[] array)throws HibernateException;
}
