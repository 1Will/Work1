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
package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;

/**
 * @author qs
 * @version $Id: SubscribeManager.java 11237 2008-03-09 10:36:42Z mwei $
 */
/**
 * <p>Title: SubscribeManager
 * <p>Description: 申购单业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: SubscribeManager.java 11237 2008-03-09 10:36:42Z mwei $
 */
@Transactional(readOnly=true)
public interface SubscribeManager {
	
	/**
	 * 根据传入的申购单的Id,返回申购单
	 * @param id 申购单的Id
	 * @return  Subscribe  
	 */
	@Transactional
	Subscribe loadSubscribe(Long id);
	
	/**
	 * 保存传入申购单对象
	 * @param subscribe
	 */
	@Transactional
	void storeSubscribe(Subscribe subscribe);
    
	/**
     * 根据传入的Ids集合 获取集合申购对象
     * @param ids
     * @return List
     */
	List<Subscribe> loadAllSubscribes(Long[] ids);
    
	/**
     * 根据传入的申购单集合，删除所有的申购单
     * @param subscribes
     */
	@Transactional
	void deleteAllSubscribes(List<Subscribe> subscribes);
	
	/**
	 * 获得自动生成的编号
	 * @return
	 */
	@Transactional
	String generateSubscribeBillNo();
	
    /**
     * 根据传入的Id集合加载申购单明细集合
     * @param ids
     * @return
     */  
 	List<SubscribeDetail> loadAllSubscribeDetails(Long[] ids);
 	
 	/**
 	 * 删除申购单明细
 	 * @param subscribeDetails
 	 * @param subscribe
 	 * @throws Exception
 	 */
	@Transactional
	void deleteAllSubscribeDetails(List<SubscribeDetail> subscribeDetails,Subscribe subscribe)throws Exception;
	
	/**
	 * 采购单明细[工装制作明细、备品备件明细、维修计划明细、技术改造明细]
	 * @param subscribeDetails
	 * @param subscribe
	 * @throws Exception
	 */
	@Transactional
	void deleteAllToolingSubscribeDetails(List<SubscribeDetail> subscribeDetails,Subscribe subscribe)throws Exception;
    
	/**
     * 更具ID加载申购单明细(采购单明细[工装制作明细、备品备件明细、维修计划明细、技术改造明细])
     * @param id
     * @return
     */
	SubscribeDetail loadSubscribeDetail(Long id);
    
	/**
     * 存储申购单明细\采购单明细
     * @param subscribeDtl
     */
	@Transactional
	void storeSubscribeDetail(SubscribeDetail subscribeDtl);
	
	/**
	 * 失效所有的申购单(采购单)
	 * @param subscribes
	 */
	@Transactional
	void disabledAllSubscribes(Collection<Subscribe> subscribes)throws Exception;
	
	/**
	 * 有效所有的申购单(采购单)
	 * @param subscribes
	 */
	@Transactional
	void enabledAllSubscribes(Collection<Subscribe> subscribes) ;
	
	/**
	 * 打印报表时所用
	 * @param queryInfo
	 * @return
	 * @throws HibernateException
	 */
	@Transactional
	List<Spare> Query(String[] queryInfo)throws HibernateException;
	
	/**
	 * 保存季度计划(申购单)
	 * @param subscribe
	 * @param addQuarterPlanIds
	 */
	@Transactional
	void storQuarterPlan(Subscribe subscribe ,String addQuarterPlanIds);
	
	/**
	 * 保存季度计划（采购单）
	 * @param subscribe 关联的采购单
	 * @param addQuarterPlanIds	
	 * @param detailType 根据传入的标识detaiType来保存该季度计划是属于[工装制作明细、备品备件明细、维修计划明细、技术改造明细]
	 */
	@Transactional
	void storToolingQuarterPlan(Subscribe subscribe ,String addQuarterPlanIds,String detailType);
	
	/**
	 * 保存备件台帐(申购单)
	 * @param subscribe
	 * @param addDeviceSpareAccountIds
	 */
	@Transactional
	void storeSpareAccount(Subscribe subscribe, String addDeviceSpareAccountIds);
	
	/**
	 * 保存申购单明细
	 * @param allSubscribeDetailId
	 * @param allSubscribeDetailAmountNumber
	 * @param allSubscribeDetailUnitPrice
	 * @param allSubscribeDetailRequestDate
	 * @param allSubscribeDetailSupplierName
	 * @param allSubscribeDetailComment
	 * @param subscribe
	 */
	@Transactional
	void storeSubscribeDetail(String allSubscribeDetailId, 
							  String allSubscribeDetailAmountNumber, 
							  String allSubscribeDetailUnitPrice, 
							  String allSubscribeDetailRequestDate, 
							  String allSubscribeDetailSupplierName, 
							  String allSubscribeDetailComment,
							  String allSubscribeDetailBuyeAmount,
							  String allSubscribeCollectBillDetailBuyer,
							  Subscribe subscribe,String allBeizhu);
	
	/**
	 * 更新申购单(采购单状态)
	 * @param subscribe
	 */
	@Transactional
	void updateSubscribeStatus(Subscribe subscribe);
	
	/**
	 * 保存采购单明细[工装制作明细、备品备件明细、维修计划明细、技术改造明细]
	 * @param allSubscribeDetailId
	 * @param allSubscribeDetailAmountNumber
	 * @param allSubscribeDetailUnitPrice
	 * @param allSubscribeDetailRequestDate
	 * @param allSubscribeDetailReqSeaon
	 * @param allSubscribeDetailComment
	 * @param subscribe
	 */
	@Transactional
	void storeToolingSubscribeDtl(String allSubscribeDetailId, 
			  String allSubscribeDetailAmountNumber, 
			  String allSubscribeDetailUnitPrice, 
			  String allSubscribeDetailRequestDate, 
			  String allSubscribeDetailReqSeaon, 
			  String allSubscribeDetailComment,
			  Subscribe subscribe);
	
	/**
	 * 保存备件台帐[备品备件明细]
	 * @param subscribe
	 * @param addSpareDetailIds
	 */
	@Transactional
	void storeSpareToolingAcount(Subscribe subscribe, String addSpareDetailIds);
	@Transactional
	void storeToolingAndDevice(SubscribeDetail dtl);

	/**
	 * 根据采购单ID到采购单明细视图中查找数据，并打印报表
	 * @param id
	 * @return
	 */
	List<SubscribeDetailView> loadSubscribeDetailView(List id,String flag);
	
	
	/**
	 * 根据非主键找明细
	 * @param arrys
	 * @param values
	 * @return
	 */
	public List<SubscribeDetail>loadDetailBykey(String key,Object value)throws DaoException;
	/**
	 * 根据非主键集合找明细
	 * @param arrys
	 * @param values
	 * @return
	 */
	public List<SubscribeDetail>loadDetailBykeyArry(String[] arrys,Object[] values)throws DaoException;
	/**
	 * 取得总金额
	 * @return
	 */
	public Double getSumPrice(SubscribeCollectBill bill);
	/**
	 * 取得采购数量 包括入库
	 * @param bill
	 * @return
	 */
	public Integer getPurNum(SubscribeCollectBill bill);
	
	/**
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1050表示点检报告的提交 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	@Transactional
	public void storeEventNew(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo);
	/**
	 * 添加事件 zzb
	 * @param EventType 事件类型
	 * @param DocId 对象Id
	 * @param moreInfo 说明
	 */
	@Transactional(readOnly=false)
	public void storeEventNew_subDetail(int EventType,int DocId,String moreInfo);
	
	@Transactional
	public void storeSubscribeDetailByLessMinStocks(Subscribe subscribe,
			String addSpareWarehouseOfLessMinStocksIds);
	/*
	 * 根据申购单ID打印报表,并置申购单位审核中
	 */
	@Transactional
	public List <SubscribeDetailView> loadSubscribeDetailViewByBillIds(String BillIds);

}
