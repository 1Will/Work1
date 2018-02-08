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
package com.yongjun.tdms.dao.base.productionline;

import java.util.List;

import com.yongjun.pluto.model.security.Organization;
import com.yongjun.pluto.model.security.ProductionLine;

/**
 * @author qs
 * @version $Id: ProductionLineDao.java 10818 2008-01-31 02:25:29Z qsun $
 */
public interface ProductionLineDao {
	List<ProductionLine> loadAllProductionLines(Organization organization);
	
	ProductionLine loadProductionLine(Long id);
	
	void StoreProductionLine(ProductionLine productionLine);
	
	List<ProductionLine> loadAllProductionLines(Long[] ids);
}
