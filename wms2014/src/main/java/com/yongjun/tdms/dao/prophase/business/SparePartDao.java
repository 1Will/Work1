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

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.prophase.business.SparePart;

/**
 * @author xschen
 * @version $Id: AcceptBillDao.java 11279 2008-03-15 01:12:13Z xschen $
 */

public interface SparePartDao {
	/**
	 * 根据传入的备品备件的id 获得备品备件的对象
	 * @param id
	 * @return
	 */
	SparePart loadSparePart(Long id);
	/**
	 * 根据传入的备品备件的ids 获得备品备件的集合
	 * @param SparePartIds
	 * @return
	 */
	
    List<SparePart> loadSpareParts(Long[] SparePartIds);
    /**
     * 保存备品备件的对象
     * @param sparePart
     */
   
	void storeSparePart(SparePart sparePart);
	/**
	 * 删除备品备件的对象
	 * @param sparePart
	 */
	
	void deleteSparePart(SparePart sparePart);
	/**
	 * 删除所有的备品备件 
	 * @param SparePartIds
	 */
	
	void deleteAllSparePart(Collection<SparePart> SparePartIds);
}
