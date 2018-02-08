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
package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.service.asset.spare.SpareManager;

/**
 * <p>Title: ListSpareOutHistoryAction
 * <p>Description: 备件出库历史页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author weiming@yj-technology.com
 * @version $Id: ListSpareOutHistoryAction.java 9149 2007-12-09 06:29:38Z mwei $
 */
public class ListSpareOutHistoryAction extends ValueListAction {
	private static final long serialVersionUID = -3007341979780584981L;

	private Spare spare;

	private SpareManager spareManager;

	public ListSpareOutHistoryAction(SpareManager spareManager) {
		this.spareManager = spareManager;
	}

	/**
	 * 获取备件对象
	 * 
	 * @param 
	 * @return 
	 */
	public void prepare() throws Exception {
		if (null == this.spare) {
			if (this.hasId("spare.id")) {
				this.spare = this.spareManager
						.loasSpare(this.getId("spare.id"));
			} else {
				this.spare = new Spare();
			}
		}
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 获取出库历史ValueList文件中的Bean名
	 * 
	 * @param 
	 * @return ValueList文件中的Bean名
	 */
	@Override
	protected String getAdapterName() {
		return "spareOutHistory";
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}
}
