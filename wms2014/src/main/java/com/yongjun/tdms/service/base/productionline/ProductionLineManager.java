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
package com.yongjun.tdms.service.base.productionline;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;

/**
 * @author qs
 * @version $Id: ProductionLineManager.java 11319 2008-03-14 08:25:24Z wzou $
 */
@Transactional(readOnly = true)
public interface ProductionLineManager {
	List<ProductionLine> loadAllProductionLines();
	
	List<ProductionLine> createSelectProductionLines(String name);

	ProductionLine loadProductionLine(Long id);
	
	@Transactional
	void joinProductionLine(Department department,ProductionLine productionLine);
	
	List<ProductionLine> loadAllProductionLines(Long[] ids);
	
	@Transactional
	void deleteProductionLine(List<ProductionLine> productionLines);
	
	@Transactional
	void storeProductionLine(ProductionLine productionLine);
	
	@Transactional
	public abstract void disableAllProductions(List<ProductionLine> productionLines);
	
	@Transactional
	void enabledAllProductions(Collection<ProductionLine> productionLines);

}
