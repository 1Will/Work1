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
package com.yongjun.tdms.service.runmaintenance.wash;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashPlanView;

/**
 * <p>Title: WashManager
 * <p>Description: 清洗业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly=true)
public interface WashManager {
	/**
	 * 根据传入的清洗计划或实施ID，获取清洗对象
	 * @param washId 清洗计划或实施ID
	 * @return Wash 清洗对象
	 */
	Wash loadWash(Long washId);
	
	/**
	 * 根据传入的清洗计划或实施ID集合，获取集合清洗对象
	 * @param washIds 清洗计划或实施ID集合
	 * @return List 清洗对象集合
	 */
	List<Wash> loadAllWashs(Long [] washIds);
	
	/**
	 * 获取集合清洗对象
	 * @return  List 清洗对象集合
	 */
	List<Wash> loadAllWashs();
	
	/**
	 * 保存传入的清洗对象
	 * @param wash 清洗对象
	 */
	@Transactional
	void storeWash(Wash wash);
	
	/**
	 * 删除传入的清洗对象
	 * @param wash 清洗对象
	 */
	@Transactional
	void deleteWash(Wash wash);
	
	/**
	 * 根据传入的清洗对象集合，删除集合中的清洗对象
	 * @param washs 清洗对象集合
	 */
	@Transactional
	void deleteAllWashs(Collection<Wash> washs);
	
	/**
	 * 根据传入的清洗对象集合，删除集合中的清洗对象;如果清洗计划明细中有被实施，则抛出异常
	 * @param washs 清洗对象集合
	 */
	@Transactional
	void deleteAllWashPlans(Collection<Wash> washPlans) throws Exception;
	public List<WashPlanView> loadAllWashPlanView(String[] array) throws HibernateException;
}
