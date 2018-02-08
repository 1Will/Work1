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

import java.util.List;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;

/**
 * @author qs
 * @version $Id: UserProfileAction.java 6186 2007-08-01 08:05:30Z qsun $
 */
public class UserProfileAction
        extends PrepareAction {
	private static final long serialVersionUID = -748045157523446454L;
	private final UserManager userManager;
    private User user;

    public UserProfileAction(UserManager userManager) {
        this.userManager = userManager;
    }

    public void prepare()
            throws Exception {
        user = this.userManager.getUser();
    }

    public String save() {
//    	TODO: 临时解决
    	this.user.setOrganization(this.userManager.getUser().getOrganization());
        this.userManager.storeUser(this.user);
        this.addActionMessage(this.getText("userProfile.edit.success"));
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }
    
    public List<Department> getDepartments() {
    	return userManager.getDepartments();
    }
}

