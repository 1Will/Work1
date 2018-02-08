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
 * <p>Title: ListSpareInHistroyAction
 * <p>Description: 备件入库历史页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mwei@yj-technology.com
 * @version $Id: EditSpareAction.java 10294 2008-01-10 03:06:14Z mwei $
 */
public class ListSpareInHistroyAction extends ValueListAction {
	private static final long serialVersionUID = 2865524802509429045L;

	private Spare spare;
	
	private SpareManager spareManager;
	
	public ListSpareInHistroyAction(SpareManager spareManager){
		this.spareManager=spareManager;
	}

	/**
	 * 根据页面传递的spare.id,获得备件对象，如果没有则初始化备件对象
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
	 * 获取ValueList文件中的Bean名
	 * 
	 * @param 
	 * @return ValueList文件中的Bean名
	 */
	@Override
	protected String getAdapterName() {
		return "spareInHistories";
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

}
