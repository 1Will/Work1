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
package com.yongjun.tdms.dao.base.financeType;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.financeType.FinanceType;

/**
 * <p>Title: FinanceTypeDao
 * <p>Description: 财务分类数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface FinanceTypeDao {
	/**
	 * 根据传入的财务分类ID,获取财务分类实体
	 * @param financeTypeId 财务分类ID
	 * @return FinanceType 财务分类实体
	 */
	FinanceType loadFinanceType(Long financeTypeId);
	
	/**
	 * 根据传入的财务分类ID集合,获取集合财务分类实体
	 * @param financeTypeIds 财务分类ID集合
	 * @return List 财务分类集合
	 */
	List<FinanceType> loadAllFinanceTypes(Long [] financeTypeIds);
	
	/**
	 * 获取集合财务分类实体
	 * @return List 财务分类集合
	 */
	List<FinanceType> loadAllFinanceTypes();
		
	/**
	 * 保存财务分类对象
	 * @param financeType  财务分类
	 */
	void storeFinanceType(FinanceType financeType);
	
	/**
	 * 删除财务分类对象
	 * @param financeType 财务分类
	 */
	void deleteFinanceType(FinanceType financeType);
	
	/**
	 * 根据传入财务分类集合,删除集合中的财务分类
	 * @param financeTypes 财务分类集合
	 */
	void deleteAllFinanceTypes(Collection<FinanceType> financeTypes);
	
}
