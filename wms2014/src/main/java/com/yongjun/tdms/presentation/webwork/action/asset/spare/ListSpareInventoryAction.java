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
 * @author qs
 * @version $Id: ListSpareInventoryAction.java 10406 2008-01-16 01:22:01Z qsun $
 */
public class ListSpareInventoryAction extends ValueListAction{
	private static final long serialVersionUID = 545309928549064632L;

	private final SpareManager spareManager;
	
	private Spare spare;
	
	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public ListSpareInventoryAction(SpareManager spareManager){
		this.spareManager=spareManager;
	}
	
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
	
	@Override
	protected String getAdapterName() {
		return "spareInventory";
	}
}
