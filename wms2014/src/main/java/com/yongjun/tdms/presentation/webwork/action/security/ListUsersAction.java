/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.UserType;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;


/**
 * @author qs
 * @version $Id: ListUsersAction.java 6133 2007-07-30 10:05:27Z qsun $
 */
public class ListUsersAction
        extends ValueListAction {
	private static final long serialVersionUID = 6596923569257153107L;
	protected final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final FilialeManager filialeManager;
	
    private List<User> users;
    private String multipleSelect;
    //需要过滤得用户ID集合
	private List<Long> listFilterUserIds; 
    //需要过滤得设备ID字符串
	private String filterUserIds;
	private String excludedisabled;
	
    public String getMultipleSelect() {
		return multipleSelect;
	}

	public void setMultipleSelect(String multipleSelect) {
		this.multipleSelect = multipleSelect;
	}

	public ListUsersAction(UserManager userManager, DepartmentManager departmentManager,FilialeManager filialeManager) {
        this.userManager = userManager;
        this.departmentManager = departmentManager;
        this.filialeManager = filialeManager;
    }

    public String execute()
            throws Exception {
        if (this.isDisable()) {
            return disable();
        }

        return SUCCESS;
    }

    private boolean isDisable() {
        return this.hasKey("disable");
    }

    private String disable() {
    	for (User user : users) {
    		if (user.equals(this.userManager.getUser())) {
    	        this.addActionMessage(this.getText("users.disable.failure"));
    	        return ERROR;
    		}
    	}
        this.userManager.disableAllUsers(this.users);
        this.addActionMessage(this.getText("users.disable.success"));
        return SUCCESS;
    }

    public void prepare()
            throws Exception {
        if (this.users == null && this.hasIds("userIds")) {
            this.users = this.userManager.loadAllUsers(this.getIds("userIds"));
        }
        if(this.hasId("multiple")){
        	this.multipleSelect=request.getParameter("multiple");
        }else{
        	this.multipleSelect="F";
        }
		if (null == listFilterUserIds) {
			if (this.hasId("filterUserIds")) {
				if (!this.isFirst() || this.isSearch()) {
					//从请求中获取需要过滤得设备ID字符串
					String[] filterUserId = request.getParameter("filterUserIds")
							.split(",");
					listFilterUserIds = new ArrayList<Long>();
					for (int i = 0; i < filterUserId.length; i++) {
						//将需要过滤得设备ID放入List集合中
						listFilterUserIds.add(Long.valueOf(filterUserId[i]));
					}
				}
				this.filterUserIds = request.getParameter("filterUserIds");
			}
		}
		
		if(!StringUtils.isEmpty(request.getParameter("hiddenexcludedisabled"))){
			this.excludedisabled=request.getParameter("hiddenexcludedisabled");
			System.out.println("excludedisabled="+excludedisabled);
		}
		System.out.println("excludedisabled=+++++++++++++++++"+excludedisabled);
    }

	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("filterUserIds")) {
			map.put("filterUserIds", listFilterUserIds);
		}
		return map;
	}
	/**
	 * 判断是否点击了search按钮
	 * 
	 * @param 
	 * @return 是为true,否为false
	 */
	public boolean isSearch() {
		return this.hasKey("search");
	}
	
    protected String getAdapterName() {
        return "users";
    }
    
    /**
     * 获取部门的所有值
     * @return List 部门集合
     */
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
    /**
     * 获取分公司的所有值
     * @return List 分公司集合
     */
	public List getFiliales() {
		return filialeManager.createSelectFilailes(this
				.getText("select.option.all"));
	}
	public String getFilterUserIds() {
		return filterUserIds;
	}

	public void setFilterUserIds(String filterUserIds) {
		this.filterUserIds = filterUserIds;
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
//	public List getUserType(){
//		return null;
//	}
	
    //获取用户类型   系统用户|非系统用户
	public List<LabelValue> getUserType() { 
		LabelValue[] arrays = this.wrapEnum(UserType.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public String getExcludedisabled() {
		return excludedisabled;
	}

	public void setExcludedisabled(String excludedisabled) {
		this.excludedisabled = excludedisabled;
	}
	
}

