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
package com.yongjun.tdms.presentation.webwork.action.base.warnning;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.model.base.warnning.WarnningRule;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.service.base.warnning.WarnningRuleManager;

/**
 * <p>Title: EditDailyRepairAction
 * <p>Description: 提醒规则的提醒规则维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditWarnningRuleAction extends PrepareAction {
	private static final long serialVersionUID = -4310718619603835969L;
	
	private final WarnningRuleManager warnningRuleManager;
	private final UserManager userManager;
	
	//提醒规则
	private WarnningRule warnningRule;
	//用户
	private User user;
	//用户集合
	private List<User> users;
	
	public EditWarnningRuleAction(WarnningRuleManager warnningRuleManager, 
			UserManager userManager) {
		this.warnningRuleManager = warnningRuleManager;
		this.userManager = userManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.warnningRule) {    
			if (this.hasId("warnningRule.id")) {       //如果请求中包含提醒规则id,则根据该id获取提醒规则对象
				this.warnningRule = this.warnningRuleManager.loadWarnningRule(this.getId("warnningRule.id"));
			} else {                                  //如果请求中没有提醒规则id,则重新创建提醒规则对象
				this.warnningRule = new WarnningRule();
			}
		}
		if (this.hasId("user.id")) {                  //如果请求中包含用户id,则根据该id获取用户对象
			this.user = this.userManager.loadUser(this.getId("user.id"));
		}
		if (this.hasIds("userIds")) {                 //如果请求中包含用户id集合,则根据该集合id获取用户集合对象
			this.users = this.userManager.loadAllUsers(this.getIds("userIds"));
		}
	}

	/**
	 * 保存提醒规则以及相关的用户
	 * @return
	 * @throws IOException
	 */
    public String save()
                   throws IOException  {
    	if (this.isGrantUser()) {
    		return grantUser();
    	}
    	if (this.isRevokeUser()) {
    		return revokeUser();
    	}
    	boolean isNew = this.warnningRule.isNew();
    	this.warnningRuleManager.storeWarnningRule(this.warnningRule);
        if (isNew) {
            this.addActionMessage(this.getText("warnningRule.add.success",
                    Arrays.asList(new Object[]{warnningRule.getTypeName()})));
            return NEW;
        } else {
            this.addActionMessage(this.getText("warnningRule.edit.success",
                    Arrays.asList(new Object[]{warnningRule.getTypeName()})));
            return SUCCESS;
        }
    }
    
	/**
	 * 是否点击授予按钮
	 * @return true | false
	 */
    private boolean isGrantUser() {
        return this.hasKey("grant_user");
    }
    
    /**
     * 是否点击剥夺按钮
     * @return true | false
     */
    private boolean isRevokeUser() {
        return this.hasKey("revoke_user");
    }
    
    /**
     * 给用户授予该提醒类型的权限
     * @return 
     */
    public String grantUser() {
    	//添加该用户提醒规则
        this.user.getWarnningRules().add(this.warnningRule);
        this.userManager.storeUser(this.user);
        this.addActionMessage(this.getText("warnningRule.grantUser.success",
                Arrays.asList(new Object[]{warnningRule.getTypeName()})));
        return NEW;
    }
    
    /**
     * 移除具有该提醒规则的用户
     * @return
     */
    public String revokeUser() {
        for (User user : users) {
        	//移除具有该提醒规则的用户
            user.getWarnningRules().remove(this.warnningRule);
            this.userManager.storeUser(user);
        }
        this.addActionMessage(this.getText("warnningRule.revokeUser.success",
                Arrays.asList(new Object[]{warnningRule.getTypeName()})));
        return NEW;
    }
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WarnningRule getWarnningRule() {
		return warnningRule;
	}

	public void setWarnningRule(WarnningRule warnningRule) {
		this.warnningRule = warnningRule;
	}
	
	/**
	 * 获取能够被授予该提醒规则的所有用户集合
	 * @return
	 */
    public List<User> getGrantableUsers() {
        List<User> users = this.userManager.loadAllUsers();
        users.removeAll(this.warnningRule.getUsers());
        return users;
    }

}
