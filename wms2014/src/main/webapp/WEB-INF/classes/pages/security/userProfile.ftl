<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
   shall not disclose such Confidential Information and shall use it only in
   accordance with the terms of the license agreement you entered into with
   YongJun.
   
   YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
   SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
   WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
   NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
   LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
   DERIVATIVES.
 -->
<#-- $$Id: userProfile.ftl 6134 2007-07-30 10:05:53Z qsun $$ -->

<#include "../includes/macros.ftl" />

<@htmlPage>
    <@ww.form name="'user'" action="'saveUserProfile'" method="'post'" validate="true">
        <@ww.token name="saveUserProfileToken"/>
        <@titleBar title="${action.getText('title')}"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('user.loginName')}'"
                    value="'${user.loginName?if_exists}'" readonly="true" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('user.name')}'"  name="'user.name'"
                    value="'${user.name?if_exists}'" cssClass="'underline'" required="true"/>
            </tr>
            <tr>
            <#--
            	 <@ww.select label="'${action.getText('user.department')}'" required="false" name="'department.id'" 
    				  value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
        	          list="departments" emptyOption="true" disabled="false">
	      		</@ww.select>
	      	-->
                <@ww.textfield label="'${action.getText('user.email')}'" name="'user.email'"
                    value="'${user.email?if_exists}'" size="30" cssClass="'underline'"/>                    
            </tr>
        </@inputTable>
        <@buttonBar>
                <@vsubmit name="'save'" value="'${action.getText('save')}'"/>
        </@buttonBar>
    </@ww.form>
    <hr/>
    <@ww.form name="'password'" action="'changePassword'" method="'post'" validate="true">
        <@ww.token name="changePasswordToken"/>
        <@titleBar title="${action.getText('password.change')}"/>
        <@inputTable>
            <tr>
                <@ww.password label="'${action.getText('password.current')}'"  name="'currentPassword'"  cssClass="'underline'" required="true"/>
            </tr>
            <tr>
                <@ww.password label="'${action.getText('password.new')}'"  name="'newPassword'"  cssClass="'underline'" required="true"/>
                <@ww.password label="'${action.getText('password.confirm')}'" name="'confirmPassword'"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
                <@vsubmit name="'save'" value="'${action.getText('ok')}'"/>
        </@buttonBar>
    </@ww.form>

    <script type="text/javascript">
        document.forms["password"].elements["currentPassword"].focus();
    </script>
</@htmlPage>
