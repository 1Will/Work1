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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.Inventory;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.service.asset.spare.SpareManager;

/**
 * <p>Title:ListSpareInventoryBillHistoryAction
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListSpareInventoryBillHistoryAction.java 2008-11-14 10:27:29 yli$
 */
public class ListSpareInventoryBillHistoryAction extends ValueListAction {
	
	private static final long serialVersionUID = -8300673477112875132L;
	private Spare spare;
	private final SpareManager spareManger;
	public ListSpareInventoryBillHistoryAction(SpareManager spareManger){
		this.spareManger = spareManger;
	}
	@Override
	public void prepare() throws Exception {
		if(null == spare){
			if(this.hasId("spare.id")){
				spare = spareManger.loasSpare(this.getId("spare.id"));
			}
		}
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "spareInventoryBillDtls";
	}
	public Spare getSpare() {
		return spare;
	}

}
