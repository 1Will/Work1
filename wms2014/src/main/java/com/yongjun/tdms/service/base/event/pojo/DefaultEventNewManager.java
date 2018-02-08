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
package com.yongjun.tdms.service.base.event.pojo;

import java.util.Date;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.event.EventNewDao;
import com.yongjun.tdms.model.asset.spare.SpareInOutBill;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.service.asset.spare.SpareInOutBillManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;

/**
 * <p>Title: DefaultEventNewManager
 * <p>Description: 事件消息业务实现定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DefaultEventNewManager.java 9931 2007-12-29 02:26:21Z wzou $
 */
public class DefaultEventNewManager extends BaseManager implements EventNewManager{
	private final EventNewDao eventNewDao;
	private final CheckPointReportManager checkPointReportManager;
	private final SpareInOutBillManager spareInOutBillManager;
	private final SpareInventoryManager spareInventoryManager;
	private final SpareInBillManager spareInBillManager;
	private final SpareOutBillManager spareOutBillManager;
	private final PurchaseBillManager purchaseBillManager;
	private final AcceptBillManager acceptBillManager;
	
	public DefaultEventNewManager(EventNewDao eventNewDao,
			CheckPointReportManager checkPointReportManager,
			SpareInOutBillManager spareInOutBillManager,
			SpareInventoryManager spareInventoryManager,
			SpareInBillManager spareInBillManager,
			SpareOutBillManager spareOutBillManager,
			PurchaseBillManager purchaseBillManager,
			AcceptBillManager acceptBillManager
			) {
		this.eventNewDao = eventNewDao;
		this.checkPointReportManager = checkPointReportManager;
		this.spareInOutBillManager = spareInOutBillManager;
		this.spareInventoryManager = spareInventoryManager;
		this.spareInBillManager = spareInBillManager;
		this.spareOutBillManager = spareOutBillManager;
		this.purchaseBillManager = purchaseBillManager;
		this.acceptBillManager = acceptBillManager;
	}
	public void storeEventNew(EventNew eventNew) {
		this.eventNewDao.storeEventNew(eventNew);
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1000表示点检报告的提交 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	public void storeEventNew(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, String DocIds, String MoreInfo) {
		String[] docIds = DocIds.split(",");
		String month = null;
		for (int i = 0; i < docIds.length; i++) {
			EventNew eventNew = new EventNew();
			CheckPointReport checkPointReport = this.checkPointReportManager.loadCheckPointReport(Long.valueOf(docIds[i]));
			month = checkPointReport.getMonth();
			eventNew.setEventType(EventType);		//设置EventType
			eventNew.setTime(time);
			eventNew.setStatus(0);
//			eventNew.setOrgId(1);
			eventNew.setUserId(UserId);
			eventNew.setDocType(EventType);
			eventNew.setDocId(Integer.valueOf(docIds[i]));
			eventNew.setMoreInfo(MoreInfo);
			checkPointReport.setSubmit(true);
			this.checkPointReportManager.storeCheckPointReport(checkPointReport);
			this.storeEventNew(eventNew);
		}
		if (EventType == 1000) {
			//在EventType=1000，即点检报告的提交后，下面保存的EventType=1010,表示‘主要生产设备完好利用情况月报表’的计算
			EventNew eventNew = new EventNew();
			eventNew.setEventType(1010);
			eventNew.setTime(time);
			eventNew.setStatus(0);
//			eventNew.setOrgId(1);
			eventNew.setUserId(UserId);
			eventNew.setDocType(1010);
			eventNew.setDocId(0);//0没有任何意义，只是赋个值
			eventNew.setMoreInfo(month);
			this.storeEventNew(eventNew);
		}
	}
	
	/*
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
	public void storeEventNew(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo) {
		EventNew eventNew = new EventNew();
		SpareInOutBill spareInBill = this.spareInOutBillManager.loadSpareInOutBill(Long.valueOf(DocId));
		eventNew.setEventType(EventType);		//设置EventType
		eventNew.setTime(time);
		eventNew.setStatus(0);
//		eventNew.setOrgId(1);
		eventNew.setUserId(UserId);
		eventNew.setDocType(EventType);
		eventNew.setDocId(Integer.valueOf(DocId));
		eventNew.setMoreInfo(MoreInfo);
		spareInBill.setSubmit(true);
		this.spareInOutBillManager.storeSpareInOutBill(spareInBill);
		this.storeEventNew(eventNew);
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1050表示入库提交 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	public void storeEventNew_InBill(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo) {
		EventNew eventNew = new EventNew();
		SpareInBill spareInBill = this.spareInBillManager.loadSpareInBill(Long.valueOf(DocId));
		eventNew.setEventType(EventType);		//设置EventType
		eventNew.setTime(time);
		eventNew.setStatus(0);
//		eventNew.setOrgId(1);
		eventNew.setUserId(UserId);
		eventNew.setDocType(EventType);
		eventNew.setDocId(Integer.valueOf(DocId));
		eventNew.setMoreInfo(MoreInfo);
		spareInBill.setSubmit(false);
		this.spareInBillManager.storeSpareInBill(spareInBill);
		this.storeEventNew(eventNew);
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1050表示出库提交 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	public void storeEventNew_OutBill(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo) {
		EventNew eventNew = new EventNew();
		SpareOutBill spareOutBill = this.spareOutBillManager.loadSpareOutBill(Long.valueOf(DocId));
		eventNew.setEventType(EventType);		//设置EventType
		eventNew.setTime(time);
		eventNew.setStatus(0);
//		eventNew.setOrgId(1);
		eventNew.setUserId(UserId);
		eventNew.setDocType(EventType);
		eventNew.setDocId(Integer.valueOf(DocId));
		eventNew.setMoreInfo(MoreInfo);
		spareOutBill.setSubmit(false);
		this.spareOutBillManager.storeSpareOutBill(spareOutBill);
		this.storeEventNew(eventNew);
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1052表示盘点的提交 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	public void storeEventNew_InvenTory(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo) {
		EventNew eventNew = new EventNew();
		SpareInventory spareInventory= this.spareInventoryManager.loadSpareInventory(Long.valueOf(DocId));
		eventNew.setEventType(EventType);		//设置EventType
		eventNew.setTime(time);
		eventNew.setStatus(0);
//		eventNew.setOrgId(1);
		eventNew.setUserId(UserId);
		eventNew.setDocType(EventType);
		eventNew.setDocId(Integer.valueOf(DocId));
		eventNew.setMoreInfo(MoreInfo);
		spareInventory.setSubmit(false);
		this.spareInventoryManager.storeSpareInventory(spareInventory);
		this.storeEventNew(eventNew);
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1053表示采购的提交 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	public void storeEventNew_Purchase(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo) {
		EventNew eventNew = new EventNew();
		PurchaseBill purchaseBill= this.purchaseBillManager.loadPurchaseBill(Long.valueOf(DocId));
		eventNew.setEventType(EventType);		//设置EventType
		eventNew.setTime(time);
		eventNew.setStatus(0);
//		eventNew.setOrgId(1);
		eventNew.setUserId(UserId);
		eventNew.setDocType(EventType);
		eventNew.setDocId(Integer.valueOf(DocId));
		eventNew.setMoreInfo(MoreInfo);
		purchaseBill.setSubmit(false);
		this.purchaseBillManager.storePurchaseBill(purchaseBill);
		this.storeEventNew(eventNew);
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1055表示先手提醒 int
	 * 2.Time	data
	 * 3.Status: 目前为0	int
	 * 4.OrgId: 目前为1 	int
	 * 5.UserId	int
	 * 6.DocType:目前和EventType内容一致	int	
	 * 7.DocId ：提交的多个报告ID,是字符串型	int
	 * 8.MoreInfo:一些相关信息	 String
	 */
	public void storeEventNew_Accept(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo) {
		EventNew eventNew = new EventNew();
		AcceptBill acceptBill = this.acceptBillManager.loadAcceptBill(Long.valueOf(DocId));
		eventNew.setEventType(EventType);		//设置EventType
		eventNew.setTime(time);
		eventNew.setStatus(0);
//		eventNew.setOrgId(1);
		eventNew.setUserId(UserId);
		eventNew.setDocType(EventType);
		eventNew.setDocId(Integer.valueOf(DocId));
		eventNew.setMoreInfo(MoreInfo);
		acceptBill.setSubmit(false);
		this.acceptBillManager.storeAcceptBill(acceptBill);
		this.storeEventNew(eventNew);
	}
}
