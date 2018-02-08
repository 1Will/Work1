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
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;

/**
 * <p>Title: AcceptBillManager
 * <p>Description: 验收单业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id: SpareBorrowDao 11237 2008-03-15 14:21:42Z xschen $
 */
public interface SpareBorrowDao {
	/**
	 * 根据备件领用单id获得领用单的对象
	 * @param id
	 * @return
	 */
	SpareBorrow loadSpareBorrow(Long id);
	/**
	 * 保存领用单的对象
	 * @param spareBorrow
	 */
	void storeSpareBorrow(SpareBorrow spareBorrow);
	/**
	 * 根据备件领用单的ids 获得所有的备件领用单集合
	 * @param ids
	 * @return
	 */
	List<SpareBorrow> loadAllSpareBorrows(Long[] ids);
	/**
	 * 根据备件领用单的实例  删除此领用单
	 * @param sparePart
	 */
	void deleteSpareBorrow(SpareBorrow sparePart);
	/**
	 * 根据spareBorrows 删除所有的备件领用单
	 * @param spareBorrows
	 */
	void deleteAllSpareBorrows(List<SpareBorrow> spareBorrows);
	
}
