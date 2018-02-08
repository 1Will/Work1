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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.outWareHouse;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillDetailManager;
/**
 * <p>Title: ListSpareOutHistoryAction
 * <p>Description: 备件出库历史页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author weiming@yj-technology.com
 * @version $Id: ListSpareOutBillHistoryAction 9149 2007-12-09 06:29:38Z mwei $
 */
public class ListSpareOutBillHistoryAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private Spare spare;
	private SpareOutBillDetail spareOutBillDetail;
	private final SpareManager spareManager;
	private final SpareOutBillDetailManager spareOutBillDetailManager;
	public ListSpareOutBillHistoryAction(SpareManager spareManager,SpareOutBillDetailManager spareOutBillDetailManager){
		   this.spareManager=spareManager;
		   this.spareOutBillDetailManager=spareOutBillDetailManager;
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
		spareOutBillDetail=this.spareOutBillDetailManager.loadSpareOutBillDetail(Long.valueOf(request.getParameter("spare.id")));	
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		
		return "spareOutBillHistorys";
	}
	public Spare getSpare() {
		return spare;
	}
	public void setSpare(Spare spare) {
		this.spare = spare;
	}
	public SpareOutBillDetail getSpareOutBillDetail() {
		return spareOutBillDetail;
	}
	public void setSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail) {
		this.spareOutBillDetail = spareOutBillDetail;
	}

}
