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
package com.yongjun.tdms.service.base.codevalue;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.codevalue.SpareDetailType;

/**
 * <p>Title:SpareDetailTypeManager
 * <p>Description:备件类型明细服务控制类接口</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
@Transactional(readOnly = true)
public interface SpareDetailTypeManager {
	/**
	 * 加载所有的备件分类明细
	 * @return
	 */
	List<SpareDetailType> loadAllSpareDetailTypes();
	/**
	 * 根据传入的ID集合加载采购单明细
	 * @param ids
	 * @return
	 */
	List<SpareDetailType> loadAllSpareDetailTypes(Long[] ids);
	/**
	 * 根据code加载采购单明细
	 * @param code
	 * @return
	 */
	SpareDetailType loadSpareDetailTypeByCode(String code);
	/**
	 * 根据id加载采购单明细
	 * @param id
	 * @return
	 */
	SpareDetailType loadSpareDetailType(Long id);
	/**
	 * 存储采购明细
	 * @param spareDetailType
	 */
	@Transactional
	void storeSpareDetailType(SpareDetailType spareDetailType);
	/**
	 * 删除采购单明细
	 * @param spareDetailType
	 */
	@Transactional
	void deleteSpareDetailType(SpareDetailType spareDetailType);
	/**
	 * 删除采购单明细集合
	 * @param list
	 */
	@Transactional
	void deleteAllSpareDetailType(List<SpareDetailType> list);
	
	/**
	 * 根据种类ID查询明细种类
	 * @param list
	 */
	@Transactional
	List loadSpareDetailBySpareTypeId(String value);
}
