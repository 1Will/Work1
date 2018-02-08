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
package com.yongjun.tdms.service.base.productionline.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.base.productionline.ProductionLineDao;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.service.base.productionline.ProductionLineManager;

/**
 * @author qs
 * @version $Id: DefaultProductionLineManager.java 11319 2008-03-14 08:25:24Z wzou $
 */
public class DefaultProductionLineManager extends BaseManager implements
		ProductionLineManager {
	private final ProductionLineDao productionLineDao;
	private final UserManager userManager;
	
	public DefaultProductionLineManager(ProductionLineDao productionLineDao, UserManager userManager) {
		this.productionLineDao = productionLineDao;
		this.userManager = userManager;
	}
	
	public List<ProductionLine> loadAllProductionLines() {
		return productionLineDao.loadAllProductionLines(userManager.getOrganization());
	}

	public List<ProductionLine> createSelectProductionLines(String name) {
		List<ProductionLine> list = productionLineDao.loadAllProductionLines(userManager.getOrganization());
		ProductionLine pl = new ProductionLine();
		pl.setId(Long.valueOf(-1L));
		pl.setName(name);
		list.add(0, pl);
		return list;
	}
	
	public List<ProductionLine> loadAllProductionLines(Long[] ids){
		return productionLineDao.loadAllProductionLines(ids);
	}

	public ProductionLine loadProductionLine(Long id) {
		return productionLineDao.loadProductionLine(id);
	}
	
	public void joinProductionLine(Department department,ProductionLine productionLine){
		productionLine.setDepartment(department);
		productionLineDao.StoreProductionLine(productionLine);
	}
	
	public void deleteProductionLine(List<ProductionLine> productionLines){
		for(ProductionLine productionLine:productionLines){
			productionLine.setDepartment(null);
			productionLineDao.StoreProductionLine(productionLine);
		}
	}

	public void storeProductionLine(ProductionLine productionLine) {
		productionLineDao.StoreProductionLine(productionLine);
	}

	public void disableAllProductions(List<ProductionLine> productionLines) {
		for (ProductionLine productionLine : productionLines) {
			productionLine.setDisabled(true);
			productionLineDao.StoreProductionLine(productionLine);
		}
	}

	public void enabledAllProductions(Collection<ProductionLine> productionLines) {
		for (ProductionLine productionLine : productionLines) {
			productionLine.setDisabled(false);
			productionLineDao.StoreProductionLine(productionLine);
		}
	}

}
