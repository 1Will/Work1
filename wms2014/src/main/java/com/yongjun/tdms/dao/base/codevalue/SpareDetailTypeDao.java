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
package com.yongjun.tdms.dao.base.codevalue;

import java.util.List;

import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;

/**
 * @author wzou
 * @version $Id:  $
 */
public interface SpareDetailTypeDao {
	List<SpareDetailType> loadAllSpareDetailTypes();
	List<SpareDetailType> loadAllSpareDetailTypes(Long[] ids);
	SpareDetailType loadSpareDetailTypeByCode(String code);
	
	SpareDetailType loadSpareDetailType(Long id);
	
	void storeSpareDetailType(SpareDetailType spareDetailType);
	
	void deleteSpareDetailType(SpareDetailType spareDetailType);
	
	void deleteAllSpareDetailType(List<SpareDetailType> list);
	
	List loadSpareDetailBySpareTypeId(Long value);
}