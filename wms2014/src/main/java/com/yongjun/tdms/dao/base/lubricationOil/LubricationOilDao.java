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
package com.yongjun.tdms.dao.base.lubricationOil;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;

/**
 * <p>Title: LubricationOilDao
 * <p>Description: 润滑油数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface LubricationOilDao {
	
	/**
	 * 根据传入润滑油ID,获取润滑油对象
	 * @param lubricationOilId    润滑油ID
	 * @return LubricationOil 
	 */
	LubricationOil loadLubricationOil(Long lubricationOilId);
	
	/**
	 * 根据传入润滑油ID集合,获取集合润滑油
	 * @param lubricationOilId  润滑油ID集合
	 * @return List 润滑油集合
	 */
	List<LubricationOil> loadAllLubricationOils(Long [] lubricationOilId);
	
	/**
	 * 获取集合润滑油
	 * @return List 润滑油集合
	 */
	List<LubricationOil> loadAllLubricationOils();
	
	/**
	 * 保存润滑油
	 * @param lubricationOil 润滑油实体
	 */
	void storeLubricationOil(LubricationOil lubricationOil);
	
	/**
	 * 删除润滑油
	 * @param lubricationOil 润滑油实体
	 */
	void deleteLubricationOil(LubricationOil lubricationOil);
	
	/**
	 * 根据传入的润滑油集合,删除集合中的润滑油
	 * @param lubricationOil 润滑油集合
	 */
	void deleteAllLubricationOils(Collection<LubricationOil> lubricationOils);
}
