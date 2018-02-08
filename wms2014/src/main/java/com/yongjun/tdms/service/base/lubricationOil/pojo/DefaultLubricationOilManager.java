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
package com.yongjun.tdms.service.base.lubricationOil.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.base.lubricationOil.LubricationOilDao;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;
import com.yongjun.tdms.service.base.lubricationOil.LubricationOilManager;
/**
 * <p>Title: DefaultLubricationOilManager
 * <p>Description: 财务分类业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.base.lubricationOil.LubricationOilManager
 */
public class DefaultLubricationOilManager implements LubricationOilManager{

	private final LubricationOilDao lubricationOilDao;
	
	public DefaultLubricationOilManager(LubricationOilDao lubricationOilDao) {
		this.lubricationOilDao = lubricationOilDao;
	}
	
	public LubricationOil loadLubricationOil(Long lubricationOilId) {
		return this.lubricationOilDao.loadLubricationOil(lubricationOilId);
	}

	public List<LubricationOil> loadAllLubricationOils(Long[] lubricationOilId) {
		return this.lubricationOilDao.loadAllLubricationOils(lubricationOilId);
	}

	public List<LubricationOil> loadAllLubricationOils() {
		return this.lubricationOilDao.loadAllLubricationOils();
	}

	public void storeLubricationOil(LubricationOil lubricationOil) {
		this.lubricationOilDao.storeLubricationOil(lubricationOil);
	}

	public void deleteLubricationOil(LubricationOil lubricationOil) {
		this.lubricationOilDao.deleteLubricationOil(lubricationOil);
	}

	public void deleteAllLubricationOils(Collection<LubricationOil> lubricationOils) {
		this.lubricationOilDao.deleteAllLubricationOils(lubricationOils);
	}

	public void disabledAllLubricationOils(Collection<LubricationOil> lubricationOils) {
		for (LubricationOil oil : lubricationOils) {
			oil.setDisabled(true);
			this.lubricationOilDao.storeLubricationOil(oil);
		}
	}

	public void enabledAllLubricationOils(Collection<LubricationOil> lubricationOils) {
		for (LubricationOil oil : lubricationOils) {
			oil.setDisabled(false);
			this.lubricationOilDao.storeLubricationOil(oil);
		}
	}

}
