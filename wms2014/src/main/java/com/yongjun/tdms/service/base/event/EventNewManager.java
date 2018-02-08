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
package com.yongjun.tdms.service.base.event;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.event.EventNew;

/**
 * <p>Title: EventNewManager
 * <p>Description: 事件消息业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ApplicationDocManager.java 9931 2007-12-29 02:26:21Z wzou $
 */
@Transactional(readOnly = true)
public interface EventNewManager {
	
	@Transactional
	public void storeEventNew(EventNew eventNew);
	
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
	@Transactional
	public void storeEventNew(int EventType,Date time,int OrgId,int Status,Long UserId,int DocType,String DocIds ,String MoreInfo);

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
	@Transactional
	public void storeEventNew_InBill(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo);
	
	@Transactional
	public void storeEventNew_OutBill(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo);
	
	
	@Transactional
	public void storeEventNew(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo);
	@Transactional
	public void storeEventNew_Purchase(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo);
	
	@Transactional
	public void storeEventNew_InvenTory(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo);
	@Transactional
	public void storeEventNew_Accept(int EventType, Date time, int OrgId, int Status, Long UserId, int DocType, int DocId, String MoreInfo);
}
