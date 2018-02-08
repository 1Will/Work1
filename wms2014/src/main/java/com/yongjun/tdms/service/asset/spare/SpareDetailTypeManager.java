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
package com.yongjun.tdms.service.asset.spare;

import java.util.List;

import com.yongjun.tdms.model.base.codevalue.SpareDetailType;

/**
 * <p>Title: SpareDetailTypeManager
 * <p>Description: 备件明细管理业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public interface SpareDetailTypeManager {
	/**
	 * 创建可供选择的备件明细集合
	 * @param name		第一条记录的名称，如 "所有" 或者 ""
	 * @return	List	可选择的备件明细集合
	 */
	public List<SpareDetailType> loadAllSpareDetailTypes();
	
	/**
	 * 根据传入的备件详细类的ID,获取备件详细类
	 * @param detailTypeId 备件详细类的ID
	 * @return SpareDetailType 备件详细类
	 */ 
	public SpareDetailType loadSpareDetailType(Long spareDetailTypeId);
	
	public List createSelectSpareDetailType(String name);
}
