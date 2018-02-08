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
package com.yongjun.tdms.dao.runmaintenance.spareBorrow;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;

/**
 * <p>Title: SpareBorrowDetailDao
 * <p>Description: 备件零用单数据接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id: SpareBorrowDetailDao 11237 2008-03-15 14:21:42Z xschen $
 */
@Transactional(readOnly=true)
public interface SpareBorrowDetailDao {
	/**
	 * 根据备件领用单明细的id获得备件领用单明细的对象 
	 * @param id
	 * @return
	 */
	SpareBorrowDetail loadSpareBorrowDetail(Long id);
	/**
	 * 保存备件领用单对象
	 * @param spareBorrowDetail
	 */
	@Transactional
	void storeSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail);
	/**
	 * 根据备件领用单的ids获得年备件领用单明细的集合
	 * @param ids
	 * @return
	 */
	List<SpareBorrowDetail> loadAllSpareBorrowDetails(Long[] ids);
   /**
    * 删除备件领用单明细
    * @param spareBorrowDetail
    */
	@Transactional
	void deleteSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail);
	/**
	 * 删除备件领用单明细的集合
	 * @param spareBorrowDetails
	 */
	@Transactional
	void deleteAllSpareBorrowDetails(List<SpareBorrowDetail> spareBorrowDetails);
}
