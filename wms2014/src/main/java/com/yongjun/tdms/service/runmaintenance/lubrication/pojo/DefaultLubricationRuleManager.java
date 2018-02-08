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
package com.yongjun.tdms.service.runmaintenance.lubrication.pojo;

import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationRuleDao;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationRuleManager;

/**
 * @author qs
 * @version $Id: DefaultLubricationRuleManager.java 9745 2007-12-26 05:20:17Z mwei $
 */
public class DefaultLubricationRuleManager extends BaseManager implements
		LubricationRuleManager {
	private final LubricationRuleDao lubricationRuleDao;
	
	public DefaultLubricationRuleManager(LubricationRuleDao lubricationRuleDao){
		this.lubricationRuleDao=lubricationRuleDao;
	}
	
	public void storeLubricationRule(LubricationRule lubricationRule){
		lubricationRuleDao.storeLubricationRule(lubricationRule);
	}
	
	public void storeLubricationRule(String lubricationInfo){
		String ary[]=lubricationInfo.split(",");
		LubricationRule lubricationRule=new LubricationRule();
		for (int i = 0; i < ary.length; i = i + 3) {
			lubricationRule=lubricationRuleDao.loadLubricationRule(Long.valueOf(ary[i]));
			lubricationRule.setDutyPeople(ary[i+1]);
			lubricationRuleDao.storeLubricationRule(lubricationRule);
		}
	}
	
	public List<LubricationRule> loadAllLubricationRule(Long[] lubricationRuleIds){
		return lubricationRuleDao.loadAllLubricationRule(lubricationRuleIds);
	}
	
	public LubricationRule loadLubricationRule(Long id){
		return lubricationRuleDao.loadLubricationRule(id);
	}
	
	public void deleteAllLubricationRule(List<LubricationRule> list){
		lubricationRuleDao.deleteAllLubricationRule(list);
	}

}
