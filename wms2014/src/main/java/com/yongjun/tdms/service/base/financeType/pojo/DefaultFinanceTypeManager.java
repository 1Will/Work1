/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.base.financeType.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.base.financeType.FinanceTypeDao;
import com.yongjun.tdms.model.base.financeType.FinanceType;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.service.base.financeType.FinanceTypeManager;

/**
 * <p>Title: DefaultFinanceTypeManager
 * <p>Description: 财务分类业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.base.financeType.FinanceTypeManager
 */
public class DefaultFinanceTypeManager implements FinanceTypeManager {
	private final FinanceTypeDao financeTypeDao;
	
	public DefaultFinanceTypeManager(FinanceTypeDao financeTypeDao) {
		this.financeTypeDao = financeTypeDao;
	}
	
	public FinanceType loadFinanceType(Long financeTypeId) {
		return this.financeTypeDao.loadFinanceType(financeTypeId);
	}

	public List<FinanceType> loadAllFinanceTypes(Long[] financeTypeIds) {
		return this.financeTypeDao.loadAllFinanceTypes(financeTypeIds);
	}

	public List<FinanceType> loadAllFinanceTypes() {
		return this.financeTypeDao.loadAllFinanceTypes();
	}

	public void storeFinanceType(FinanceType financeType) {
		this.financeTypeDao.storeFinanceType(financeType);
	}

	public void deleteFinanceType(FinanceType financeType) {
		this.financeTypeDao.deleteFinanceType(financeType);
	}

	public void deleteAllFinanceTypes(Collection<FinanceType> financeTypes) {
		this.financeTypeDao.deleteAllFinanceTypes(financeTypes);
	}

	public void disabledAllFinanceTypes(Collection<FinanceType> financeTypes) {
		for(FinanceType financeType : financeTypes){
			financeType.setDisabled(true);
			this.financeTypeDao.storeFinanceType(financeType);
		}
	}

	public void enabledAllFinanceTypes(Collection<FinanceType> financeTypes) {
		for(FinanceType financeType : financeTypes){
			financeType.setDisabled(false);
			this.financeTypeDao.storeFinanceType(financeType);
		}
	}

}
