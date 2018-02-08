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
package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.business.SparePart;

/**
 * @author xscchen
 * @version $Id: AcceptBillManager 11237 2008-03-09 10:36:42Z xschen $
 */
/**
 * <p>Title: AcceptBillManager
 * <p>Description: 备品备件业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: SparePartManager.java 11237 2008-03-15 14:21:42Z xschen $
 */
@Transactional(readOnly=true)
public interface SparePartManager {
	/**
	 * 根据传入的备品备件的id 获得备品备件的对象
	 * @param id
	 * @return
	 */
	@Transactional
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
	@Transactional
	void storeSparePart(SparePart sparePart);
	/**
	 * 删除备品备件的对象
	 * @param sparePart
	 */
	@Transactional
	void deleteSparePart(SparePart sparePart);
	/**
	 * 删除所有的备品备件 
	 * @param SparePartIds
	 */
	@Transactional
	void deleteAllSparePart(Collection<SparePart> SparePartIds);
}
