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
package com.yongjun.tdms.presentation.webwork.action.year.device.runmaintainPlan;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PivotalSpare;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.PivotalSpareManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager;

/**
 * <p>Title: ListPivotalSpareAction
 * <p>Description: 运维计划的关键备件列表页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListPivotalSpareAction extends ValueListAction {
	private static final long serialVersionUID = -5482712586027120112L;
	private final Log log = LogFactory.getLog(getClass());

	
	private final RunmaintainPlanDetailManager runmaintainPlanDetailManager;
	private final PivotalSpareManager pivotalSpareManager;
	
	//运维计划明细
	private RunmaintainPlanDetail runmaintainPlanDetail;
	//关键备件集合
	private List<PivotalSpare> pivotalSpares;
	//用","分割的字符串
	private String addSpareIds;
	//添加新备件标识
	private static final String ADD_SPARES = "addSpares";
	//用","分割的关键备件ID
	private String allPivotalSpareIds;
	//用","分割的使用数量
	private String allUsedNums;
	//用","分割的备注
	private String allComments;
	
	public ListPivotalSpareAction(RunmaintainPlanDetailManager runmaintainPlanDetailManager,
			PivotalSpareManager pivotalSpareManager) {
		this.runmaintainPlanDetailManager = runmaintainPlanDetailManager;
		this.pivotalSpareManager = pivotalSpareManager;
	}
	
	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("runmaintainPlanDetail.id")) {
			//如果请求中包含"runmaintainPlanDetail.id",则根据"runmaintainPlanDetail.id"获取运维计划明细对象
			this.runmaintainPlanDetail = this.runmaintainPlanDetailManager.loadRunmaintainPlanDetail(this.getId("runmaintainPlanDetail.id"));
		}
		if (this.hasIds("pivotalSpareIds")) {
			//如果请求中包含"pivotalSpareIds",则根据"pivotalSpareIds"获取关键备件集合
			this.pivotalSpares = this.pivotalSpareManager.loadAllPivotalSpares(this.getIds("pivotalSpareIds"));
		}
		if (null == this.addSpareIds) {      //从请求中获取选择的备件id
			if (!StringUtils.isEmpty(request.getParameter("addSpareIds"))) {
				this.addSpareIds = request.getParameter("addSpareIds");
				log.debug("spareIds " + addSpareIds);
			}
		}
		this.setFirst(false);
	}
	
	public String execute() {
		//如果是通过新建按钮,选择备件,则添加新的关键备件
		if (this.isAddSpares()) {
			saveAddSpares(); 
		}
		//如果页面点击删除按钮,删除选中的关键备件
		if (this.isDelete()) {
			return delete();
		}
		//如果页面点击保存按钮，则执行更新操作
		if (this.isSave()) {
			return save();
		}
		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "pivotalSpares";
	}
	
	/**
	 * 往查询精度检查的hql语句中设置运维计划明细的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("runmaintainPlanDetail.id")) {
			map.put("runmaintainPlanDetail.id",this.runmaintainPlanDetail.getId());
		}
		return map;
	}
	
	/**
	 * 判断页面传来的addSpare变量是否有值，且值是否等于ADD_SPARES
	 * 
	 * @return boolean true 添加备件|false 不添加
	 */
	private boolean isAddSpares() {
		if (!StringUtils.isEmpty(request.getParameter("addSpares"))) {
			if (request.getParameter("addSpares").equals(ADD_SPARES)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 保存新添加的备件
	 * 
	 */
	
	public void saveAddSpares() {
		this.pivotalSpareManager.storePivotalSpares(this.runmaintainPlanDetail, this.addSpareIds);
		this.addActionMessage(this.getText("pivotalSpare.add.success"));
	}
	
	/**
	 * 判断页面是否点击了保存按钮
	 * @return true | false
	 */
	private boolean isSave() {
		return this.hasKey("save");
	}
	
	/**
	 * 如果页面点击保存按钮，则更新相应的关键备件的值
	 * @return
	 */
	public String save() {
		//从请求中获得allPivotalSpareIds
		if (!StringUtils.isEmpty(request.getParameter("allPivotalSpareIds"))) {
			allPivotalSpareIds = request.getParameter("allPivotalSpareIds");
		}
		//从请求中获得allUsedNums
		if (!StringUtils.isEmpty(request.getParameter("allUsedNums"))) {
			allUsedNums = request.getParameter("allUsedNums");
		}
		//从请求中获得allComments
		if (!StringUtils.isEmpty(request.getParameter("allComments"))) {
			allComments = request.getParameter("allComments");
		}
		//更新关键备件的使用数量，备注的值
		this.pivotalSpareManager.storePivotalSpares(runmaintainPlanDetail, allPivotalSpareIds, allUsedNums, allComments);
		return SUCCESS;
	}
	/**
	 * 删除页面选中的关键备件
	 * @return SUCCESS
	 */
	public String delete() {
		this.pivotalSpareManager.deleteAllPivotalSpares(runmaintainPlanDetail, this.pivotalSpares);
		return SUCCESS;
	}
	public RunmaintainPlanDetail getRunmaintainPlanDetail() {
		return runmaintainPlanDetail;
	}
	public void setRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintainPlanDetail = runmaintainPlanDetail;
	}

}
