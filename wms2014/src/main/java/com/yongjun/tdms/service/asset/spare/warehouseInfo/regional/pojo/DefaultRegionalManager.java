/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.asset.spare.warehouseInfo.regional.RegionalDao;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;

/**
 * <p>
 * Title:DefaultRegionalManager
 * <p>
 * Description:DefaultRegionalManager
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author bxchen@yj-technology.com
 * @version
 */
public class DefaultRegionalManager
implements RegionalManager {
	private final RegionalDao regionalDao;

	public DefaultRegionalManager(final RegionalDao regionalDao) {
		this.regionalDao = regionalDao;
	}

	public void deleteRegional(Regional regional) {
		this.regionalDao.deleteRegional(regional);
		
	}
	public List<Regional> loadAllRegionals(Long[] regionalIds) {
		return this.regionalDao.loadAllRegional(regionalIds);
	}

	public Regional loadRegionals(Long regionalId) {
		return this.regionalDao.loadRegional(regionalId);
	}

	public void storeRegional(Regional regional) {
		this.regionalDao.storeRegional(regional);
		
	}

	public void deleteAllRegional(Collection<Regional> regionals) {
		this.regionalDao.deleteAllRegional(regionals);
		
	}

	public void disabledAllRegional(Collection<Regional> regional) {
		for(Regional r :regional){
			r.setDisabled(true);
			this.regionalDao.storeRegional(r);
		}
		
	}

	public void enabledAllRegional(Collection<Regional> regional) {
		for(Regional r :regional){
			r.setDisabled(false);
			this.regionalDao.storeRegional(r);
		}
	}

	/**
	 * @function 根据仓库标识查询其包含的库区
	 * @author wliu
	 * @param warehouseId 仓库标识
	 * @param flag 标识查询/维护
	 * @return
	 */
	public List<Regional> loadRegionalByWareId(String warehouseId,String flag) throws DaoException{
		List<Regional> list=new ArrayList<Regional>();
		Long wareId = Long.valueOf(warehouseId);
		List<Regional> RegionalList = new ArrayList<Regional>();
		if(flag.equals("search")){
			String[] keyNames = new String[]{"warehouse.id"};
			Object[] keyValues = new Object[]{wareId};
			RegionalList = this.regionalDao.loadByKeyArray(keyNames, keyValues);
		}else if(flag.equals("edit")){
			String[] keyNames = new String[]{"warehouse.id","disabled"};
			Object[] keyValues = new Object[]{wareId,false};
			RegionalList = this.regionalDao.loadByKeyArray(keyNames, keyValues);
		}
		for (Regional regional : RegionalList) {
			regional.setWarehouse(null);
			regional.setJob(null);
			regional.setOrganization(null);
		}
		for(int i=0;i<RegionalList.size();i++){
			if(!(RegionalList.get(i).getDisabled())){
				list.add(RegionalList.get(i));
			}
		}
		return list;
	}
	

	public List<Regional> loadAllRegionals() {
		
		return this.regionalDao.loadAllRegional();
	}

	public List<Regional> loadByKey(String keyName, Object keyValue) throws DaoException {
		
		return regionalDao.loadByKey(keyName, keyValue);
	}

	public List<Regional> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {

		return regionalDao.loadByKeyArray(keyNames, keyValues);
	}

}
